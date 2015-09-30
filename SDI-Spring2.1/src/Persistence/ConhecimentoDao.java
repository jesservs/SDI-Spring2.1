package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Categoria;
import Entity.Conhecimento;

@Component
public class ConhecimentoDao extends Dao {

	public void create(Conhecimento conhecimento) throws Exception {
		String sql = "INSERT INTO CONHECIMENTO (IDCONHECIMENTO, DTHRULTALTERACAO, DESCCONHECIMENTO, ID_USUARIO, ID_CATEGORIA)";
		sql += " VALUES (NULL, now(), ?,?,?)";	
		open();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, conhecimento.getDescConhecimento());
		stmt.setInt(2, conhecimento.getResponsavel().getIdUsuario());
		stmt.setInt(3, conhecimento.getCategoria().getIdCategoria());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public List<Conhecimento> findAll() throws Exception {
		String sql = "SELECT IDCONHECIMENTO, DTHRULTALTERACAO, DESCCONHECIMENTO, ID_USUARIO, ID_CATEGORIA";
		sql += " FROM CONHECIMENTO";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Conhecimento> listaConhecimento = new ArrayList<Conhecimento>();
		while (rs.next()) {
			Conhecimento conhecimento = new Conhecimento();
			conhecimento.setIdConhecimento(rs.getInt("IDCONHECIMENTO"));
			conhecimento.setDtHrUltAlteracao(rs.getDate("DTHRULTALTERACAO"));
			conhecimento.setDescConhecimento(rs.getString("DESCCONHECIMENTO"));
			conhecimento.setResponsavel(  new UsuarioDao().findByCod(rs.getInt("ID_USUARIO")) );
			conhecimento.setCategoria( new CategoriaDao().finByCode(rs.getInt("ID_CATEGORIA")));
			listaConhecimento.add(conhecimento);
		}
		close();
		return listaConhecimento;
	}
	
	public List<Conhecimento> findAllComFiltro(Categoria categoria) throws Exception {
		String sql = "SELECT IDCONHECIMENTO, DESCCONHECIMENTO, DTHRULTALTERACAO, CONHECIMENTO.ID_CATEGORIA, CONHECIMENTO.ID_USUARIO";
		sql += " FROM CONHECIMENTO";
		sql += " INNER JOIN CATEGORIA ON CATEGORIA.IDCATEGORIA = CONHECIMENTO.ID_CATEGORIA";
		sql += " INNER JOIN TIPO ON TIPO.IDTIPO = CATEGORIA.ID_TIPO";
		sql += " WHERE (TIPO.IDTIPO = "+categoria.getTipo().getIdTipo()+")";
		if(categoria.getIdCategoria() != null) 
			sql += " AND (CATEGORIA.IDCATEGORIA="+categoria.getIdCategoria()+")";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Conhecimento> listaConhecimento = new ArrayList<Conhecimento>();
		while (rs.next()) {
			Conhecimento conhecimento = new Conhecimento();
			conhecimento.setIdConhecimento(rs.getInt("IDCONHECIMENTO"));
			conhecimento.setDtHrUltAlteracao(rs.getDate("DTHRULTALTERACAO"));
			conhecimento.setDescConhecimento(rs.getString("DESCCONHECIMENTO"));
			conhecimento.setResponsavel(  new UsuarioDao().findByCod(rs.getInt("ID_USUARIO")) );
			conhecimento.setCategoria( new CategoriaDao().finByCode(rs.getInt("ID_CATEGORIA")));
			listaConhecimento.add(conhecimento);
		}
		close();
		return listaConhecimento;
	}
	
	public void delete(Conhecimento conhecimento) throws Exception {
		open();
		stmt = con.prepareStatement("delete from CONHECIMENTO where IDCONHECIMENTO = ?");
		stmt.setInt(1, conhecimento.getIdConhecimento());
		stmt.execute();
		stmt.close();
		close();
	}
	/*
	public Tipo findByCod(Integer id) throws Exception {
		open();
		stmt = con.prepareStatement("select idtipo, desctipo from tipo"
				+ " where idtipo = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Tipo tipoAchado = new Tipo();

		if (rs.next()) {
			tipoAchado.setIdTipo(rs.getInt("idtipo"));
			tipoAchado.setDescTipo(rs.getString("desctipo"));
		}
		close();
		return tipoAchado;
	}
	
	public void update(Tipo tipo) throws Exception {
		open();
		stmt = con.prepareStatement("update tipo set desctipo = ? where idtipo = ?");
		stmt.setString(1, tipo.getDescTipo());
		stmt.setInt(2, tipo.getIdTipo());
		stmt.execute();
		stmt.close();
		close();
	}
	*/
}
