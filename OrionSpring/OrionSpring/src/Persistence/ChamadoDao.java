package Persistence;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Categoria;
import Entity.Chamado;
import Entity.Tipo;
import Entity.Usuario;

@Component
public class ChamadoDao extends Dao {
	
	private TipoDao tipoDao = new TipoDao();
	private PrioridadeDao prioridadeDao = new PrioridadeDao();
	private StatusDao statusDao = new StatusDao();
	private UsuarioDao usuarioDao = new UsuarioDao();
	private CategoriaDao categoriaDao = new CategoriaDao();
	
	public Chamado findByCode(Integer id) throws Exception{
		String sql = "SELECT IDCHAMADO, DTHRABERTURA, DTHRFECHAMENTO, DESCCHAMADO, ID_STATUS,";
		sql += " ID_RESPONSAVEL, ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE";
		sql += " FROM CHAMADO";	
		sql += " WHERE IDCHAMADO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Chamado chamado = new Chamado();
		if (rs.next()) {
			chamado.setIdChamado( rs.getInt("IDCHAMADO") );
			chamado.setDtHrAbertura(  rs.getTimestamp("DTHRABERTURA")  + "");
			chamado.setDtHrFechamento( rs.getTimestamp("DTHRFECHAMENTO") + "");
			
			chamado.setDateAbertura(rs.getTimestamp("DTHRABERTURA"));
			chamado.setDateFechamento(rs.getTimestamp("DTHRFECHAMENTO"));
			
			chamado.setDescChamado(  rs.getString("DESCCHAMADO")  );
			chamado.setStatus( statusDao.findByCod(rs.getInt("ID_STATUS"))  );
			chamado.setResponsavel(  usuarioDao.findByCod(rs.getInt("ID_RESPONSAVEL"))  );
			chamado.setSolicitante(   usuarioDao.findByCod( rs.getInt("ID_SOLICITANTE")  ));
			chamado.setAtendente(  usuarioDao.findByCod(rs.getInt("ID_ATENDENTE")) );
			chamado.setCategoria( categoriaDao.finByCode(rs.getInt("ID_CATEGORIA"))  );
			chamado.setNovaPrioridade( chamado.getCategoria().getPrioridade()  );
		}
		close();
		return chamado;
	}
	
	public Chamado create(Chamado chamado) throws Exception {
		chamado.setCategoria(categoriaDao.finByCode(chamado.getCategoria().getIdCategoria()));
		
		String sql = "INSERT INTO CHAMADO (idchamado, dthrabertura, descchamado, id_status,";
		sql += " id_solicitante, id_categoria, dthrsolucao, id_atendente) VALUES(null, ?,?,?,?,?,?";
		if(chamado.getAtendente() != null){
			sql += "," + chamado.getAtendente().getIdUsuario();
		}else{
			sql += ",null";
		}
		sql += ")";
		open();
		stmt = con.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		
		//chamado.setDtHrAbertura( new Date() + "");
		stmt.setTimestamp(1, new java.sql.Timestamp(chamado.getDateAbertura().getTime()));
		stmt.setString(2, chamado.getDescChamado());
		chamado.setStatus( statusDao.findByCod(1));
		stmt.setInt(3, chamado.getStatus().getIdStatus());
		stmt.setInt(4, chamado.getSolicitante().getIdUsuario());
		stmt.setInt(5, chamado.getCategoria().getIdCategoria());
		
		Calendar temp = Calendar.getInstance();
		temp.setTime(chamado.getDateAbertura());
		temp.add(Calendar.HOUR, chamado.getCategoria().getPrioridade().getTempoResolucao());
		chamado.setDtHrSolucao(temp.getTime());
		
		stmt.setTimestamp(6, new java.sql.Timestamp(chamado.getDtHrSolucao().getTime()));
		stmt.execute();
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) chamado.setIdChamado(rs.getInt(1));
		close();
		return chamado;
	}
	
	public Integer getNumberElements(Usuario usuario) throws Exception {
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		//se ele é técnico
		if(usuario.getPerfil().getIdPerfil() == 2) 
			sql += " WHERE (ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(ID_RESPONSAVEL))";
		
		//se ele é usuario
		if(usuario.getPerfil().getIdPerfil() == 4) 
			sql += " WHERE (ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
		
		//se ele é atendente
		if(usuario.getPerfil().getIdPerfil() == 1) 
			sql += " WHERE (ID_ATENDENTE=" + usuario.getIdUsuario() + ")";

		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosFechadosDentroSLAPorTecnico(Usuario usuario) throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE !ISNULL(DTHRFECHAMENTO) AND (DTHRFECHAMENTO <= DTHRSOLUCAO)";
		//sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 2) sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 1) sql += " AND ID_ATENDENTE = " + usuario.getIdUsuario();
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosFechadosForaSLAPorTecnico(Usuario usuario) throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE !ISNULL(DTHRFECHAMENTO) AND (DTHRFECHAMENTO > DTHRSOLUCAO)";
		//sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 2) sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 1) sql += " AND ID_ATENDENTE = " + usuario.getIdUsuario();
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosAbertosForaSLAPorTecnico(Usuario usuario) throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE ISNULL(DTHRFECHAMENTO) AND (now() > DTHRSOLUCAO)";
		//sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 2) sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 1) sql += " AND ID_ATENDENTE = " + usuario.getIdUsuario();
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosAbertosDentroSLAPorTecnico(Usuario usuario) throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE ISNULL(DTHRFECHAMENTO) AND (now() <= DTHRSOLUCAO)";
		//sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 2) sql += " AND ID_RESPONSAVEL = " + usuario.getIdUsuario();
		if(usuario.getPerfil().getIdPerfil() == 1) sql += " AND ID_ATENDENTE = " + usuario.getIdUsuario();
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosFechadosDentroSLA() throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE !ISNULL(DTHRFECHAMENTO) AND (DTHRFECHAMENTO <= DTHRSOLUCAO)";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosFechadosForaSLA() throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE !ISNULL(DTHRFECHAMENTO) AND (DTHRFECHAMENTO > DTHRSOLUCAO)";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosAbertosForaSLA() throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE ISNULL(DTHRFECHAMENTO) AND (now() > DTHRSOLUCAO)";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public Integer getElementosAbertosDentroSLA() throws Exception{
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " INNER JOIN PRIORIDADE ON PRIORIDADE.IDPRIORIDADE = ID_PRIORIDADE";
		sql += " WHERE ISNULL(DTHRFECHAMENTO) AND (now() <= DTHRSOLUCAO)";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}

	public Integer getNumberElementsComFiltro(Usuario usuario, Chamado chamadoPassado) throws Exception {
		String sql = "SELECT COUNT(IDCHAMADO) AS TOTAL FROM CHAMADO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
		sql += " WHERE IDCHAMADO = IDCHAMADO";
		//se ele é técnico
		if(usuario.getPerfil().getIdPerfil() == 2) 
			sql += " AND ((ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(ID_RESPONSAVEL)))";
		
		//se ele é usuario
		if(usuario.getPerfil().getIdPerfil() == 4) 
			sql += " AND (ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
		
		//se ele é atendente
		if(usuario.getPerfil().getIdPerfil() == 1) 
			sql += " AND (ID_ATENDENTE=" + usuario.getIdUsuario() + ")";
		
		if(chamadoPassado.getStatus().getIdStatus() != null)
			sql += " AND ID_STATUS = " + chamadoPassado.getStatus().getIdStatus();
		if(chamadoPassado.getCategoria().getPrioridade().getIdPrioridade() != null)
			sql += " AND ID_PRIORIDADE = " + chamadoPassado.getCategoria().getPrioridade().getIdPrioridade();
		
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public void setNovoStatus(Chamado chamado) throws Exception{
		String sql = "UPDATE CHAMADO SET ID_STATUS=?, DESCCHAMADO=?";
		if(chamado.getStatus().getIdStatus() == 6){
			sql+= ", DTHRFECHAMENTO = now()";
		}
		sql += " WHERE IDCHAMADO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, chamado.getStatus().getIdStatus());
		stmt.setString(2, chamado.getDescChamado());
		stmt.setInt(3, chamado.getIdChamado());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public void setResponsavel(Chamado chamado) throws Exception{
		String sql = "UPDATE CHAMADO SET ID_RESPONSAVEL = ?, ID_STATUS=?, DESCCHAMADO=?";
		if(chamado.getStatus().getIdStatus() == 6){
			sql+= ", DTHRFECHAMENTO = now()";
		}
		sql += " WHERE IDCHAMADO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, chamado.getResponsavel().getIdUsuario());
		stmt.setInt(2, chamado.getStatus().getIdStatus());
		stmt.setString(3, chamado.getDescChamado());
		stmt.setInt(4, chamado.getIdChamado());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public List<Chamado> findAll(Integer pagina, Usuario usuario) throws Exception {
		open();
		String sql = "";
		if(pagina == 1){
			sql += "SELECT IDCHAMADO, DTHRABERTURA, DTHRFECHAMENTO, DESCCHAMADO, ID_STATUS,";
			sql += " ID_RESPONSAVEL, CHAMADO.ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE, QUALIDADE.IDQUALIDADE";
			sql += " FROM CHAMADO";	
			sql += " LEFT JOIN QUALIDADE ON QUALIDADE.ID_CHAMADO = CHAMADO.IDCHAMADO";
			
			//se ele é técnico
			if(usuario.getPerfil().getIdPerfil() == 2) 
				sql += " WHERE (CHAMADO.ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(CHAMADO.ID_RESPONSAVEL))";
			//se ele é usuario
			if(usuario.getPerfil().getIdPerfil() == 4) 
				sql += " WHERE (CHAMADO.ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
			//se ele é atendente
			if(usuario.getPerfil().getIdPerfil() == 1) 
				sql += " WHERE (CHAMADO.ID_ATENDENTE=" + usuario.getIdUsuario() + ")";
			sql += " LIMIT 10";
		}else{
			pagina--;
			pagina *= 10;
			sql += "SELECT IDCHAMADO, DTHRABERTURA, DTHRFECHAMENTO, DESCCHAMADO, ID_STATUS,";
			sql += " ID_RESPONSAVEL, CHAMADO.ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE, QUALIDADE.IDQUALIDADE";
			sql += " FROM CHAMADO";
			sql += " LEFT JOIN QUALIDADE ON QUALIDADE.ID_CHAMADO = CHAMADO.IDCHAMADO";
			
			//se ele é técnico
			if(usuario.getPerfil().getIdPerfil() == 2) 
				sql += " WHERE (CHAMADO.ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(CHAMADO.ID_RESPONSAVEL))";
			
			//se ele é usuario
			if(usuario.getPerfil().getIdPerfil() == 4) 
				sql += " WHERE (CHAMADO.ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
			
			//se ele é atendente
			if(usuario.getPerfil().getIdPerfil() == 1) 
				sql += " WHERE (CHAMADO.ID_ATENDENTE=" + usuario.getIdUsuario() + ")";
			
			sql += " LIMIT " + pagina + ",10";
		}
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Chamado> listaChamados = new ArrayList<Chamado>();
		while (rs.next()) { 
			Chamado chamado = new Chamado();
			chamado.setIdChamado( rs.getInt("IDCHAMADO") );
			chamado.setDtHrAbertura(rs.getTimestamp("DTHRABERTURA") + "");
			chamado.setDtHrFechamento( rs.getTimestamp("DTHRFECHAMENTO") + "");
			
			chamado.setDateAbertura(rs.getTimestamp("DTHRABERTURA"));
			chamado.setDateFechamento(rs.getTimestamp("DTHRABERTURA"));
			
			chamado.setDescChamado( rs.getString("DESCCHAMADO") );
			chamado.setStatus( statusDao.findByCod( rs.getInt("ID_STATUS") ) );
			
			if(rs.getString("ID_RESPONSAVEL") != null)
				chamado.setResponsavel( usuarioDao.findByCod( rs.getInt("ID_RESPONSAVEL") ) );
			if(rs.getString("ID_SOLICITANTE") != null)
				chamado.setSolicitante(  usuarioDao.findByCod( rs.getInt("ID_SOLICITANTE")  ) );
			if(rs.getString("ID_ATENDENTE") != null)
				chamado.setAtendente( usuarioDao.findByCod( rs.getInt("ID_ATENDENTE") )  );
			
			chamado.setCategoria( categoriaDao.finByCode( rs.getInt("ID_CATEGORIA") ) );
			if(rs.getString("ID_NOVAPRIORIDADE") != null){
				chamado.setNovaPrioridade( prioridadeDao.findByCode(rs.getInt("ID_NOVAPRIORIDADE")) );
			}else{
				chamado.setNovaPrioridade(chamado.getCategoria().getPrioridade());
			}
			chamado.setQualidade( new QualidadeDao().findByCode(rs.getInt("IDQUALIDADE")));
			listaChamados.add(chamado);
		}
		close();
		return listaChamados;
	}
	
	public List<Chamado> findAllComFiltro(Integer pagina, Usuario usuario, Chamado chamadoPassado) throws Exception {
		open();
		String sql = "";
		if(pagina == 1){
			sql += "SELECT IDCHAMADO, DTHRABERTURA, DTHRFECHAMENTO, DESCCHAMADO, ID_STATUS,";
			sql += " ID_RESPONSAVEL, CHAMADO.ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE, QUALIDADE.IDQUALIDADE";
			sql += " FROM CHAMADO";
			sql += " LEFT JOIN QUALIDADE ON QUALIDADE.ID_CHAMADO = CHAMADO.IDCHAMADO";
			sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
			sql += " WHERE IDCHAMADO = IDCHAMADO";
			//se ele é técnico
			if(usuario.getPerfil().getIdPerfil() == 2) 
				sql += " AND ((CHAMADO.ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(CHAMADO.ID_RESPONSAVEL)))";
			//se ele é usuario
			if(usuario.getPerfil().getIdPerfil() == 4) 
				sql += " AND (CHAMADO.ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
			//se ele é atendente
			if(usuario.getPerfil().getIdPerfil() == 1) 
				sql += " AND (CHAMADO.ID_ATENDENTE=" + usuario.getIdUsuario() + ")";
			
			if(chamadoPassado.getStatus().getIdStatus() != null)
				sql += " AND ID_STATUS = " + chamadoPassado.getStatus().getIdStatus();
			if(chamadoPassado.getCategoria().getPrioridade().getIdPrioridade() != null)
				sql += " AND ID_PRIORIDADE = " + chamadoPassado.getCategoria().getPrioridade().getIdPrioridade();
			sql += " LIMIT 10";
			
		}else{
			pagina--;
			pagina *= 10;
			sql += "SELECT IDCHAMADO, DTHRABERTURA, DTHRFECHAMENTO, DESCCHAMADO, ID_STATUS,";
			sql += " ID_RESPONSAVEL, CHAMADO.ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE, QUALIDADE.IDQUALIDADE";
			sql += " FROM CHAMADO";
			sql += " LEFT JOIN QUALIDADE ON QUALIDADE.ID_CHAMADO = CHAMADO.IDCHAMADO";
			sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = ID_CATEGORIA";
			sql += " WHERE IDCHAMADO = IDCHAMADO";
			
			//se ele é técnico
			if(usuario.getPerfil().getIdPerfil() == 2) 
				sql += " AND (CHAMADO.ID_RESPONSAVEL=" + usuario.getIdUsuario() + ") OR (ISNULL(ID_RESPONSAVEL))";
			
			//se ele é usuario
			if(usuario.getPerfil().getIdPerfil() == 4) 
				sql += " AND (CHAMADO.ID_SOLICITANTE=" + usuario.getIdUsuario() + ")";
			
			//se ele é atendente
			if(usuario.getPerfil().getIdPerfil() == 1) 
				sql += " AND (CHAMADO.ID_ATENDENTE=" + usuario.getIdUsuario() + ")";
			
			if(chamadoPassado.getStatus().getIdStatus() != null)
				sql += " AND ID_STATUS = " + chamadoPassado.getStatus().getIdStatus();
			if(chamadoPassado.getCategoria().getPrioridade().getIdPrioridade() != null)
				sql += " AND ID_PRIORIDADE = " + chamadoPassado.getCategoria().getPrioridade().getIdPrioridade();
			
			sql += " LIMIT " + pagina + ",10";
		}
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Chamado> listaChamados = new ArrayList<Chamado>();
		while (rs.next()) { 
			Chamado chamado = new Chamado();
			chamado.setIdChamado( rs.getInt("IDCHAMADO") );
			chamado.setDtHrAbertura(rs.getTimestamp("DTHRABERTURA") + "");
			chamado.setDtHrFechamento( rs.getTimestamp("DTHRFECHAMENTO") + "" );
			
			chamado.setDateAbertura(rs.getTimestamp("DTHRABERTURA"));
			chamado.setDateFechamento(rs.getTimestamp("DTHRABERTURA"));
			
			chamado.setDescChamado( rs.getString("DESCCHAMADO") );
			chamado.setStatus( statusDao.findByCod( rs.getInt("ID_STATUS") ) );
			
			if(rs.getString("ID_RESPONSAVEL") != null)
				chamado.setResponsavel( usuarioDao.findByCod( rs.getInt("ID_RESPONSAVEL") ) );
			if(rs.getString("ID_SOLICITANTE") != null)
				chamado.setSolicitante(  usuarioDao.findByCod( rs.getInt("ID_SOLICITANTE")  ) );
			if(rs.getString("ID_ATENDENTE") != null)
				chamado.setAtendente( usuarioDao.findByCod( rs.getInt("ID_ATENDENTE") )  );
			
			chamado.setCategoria( categoriaDao.finByCode( rs.getInt("ID_CATEGORIA") ) );
			if(rs.getString("ID_NOVAPRIORIDADE") != null){
				chamado.setNovaPrioridade( prioridadeDao.findByCode(rs.getInt("ID_NOVAPRIORIDADE")) );
			}else{
				chamado.setNovaPrioridade(chamado.getCategoria().getPrioridade());
			}
			chamado.setQualidade( new QualidadeDao().findByCode(rs.getInt("IDQUALIDADE")));
			listaChamados.add(chamado);
		}
		close();
		return listaChamados;
	}
	
}
