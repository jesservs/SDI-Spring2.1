package Persistence;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Categoria;
import Entity.Chamado;
import Entity.Historico;
import Entity.Usuario;

@Component
public class HistoricoDao extends Dao {
	
	private TipoDao tipoDao = new TipoDao();
	private PrioridadeDao prioridadeDao = new PrioridadeDao();
	private StatusDao statusDao = new StatusDao();
	private UsuarioDao usuarioDao = new UsuarioDao();
	private CategoriaDao categoriaDao = new CategoriaDao();
	private ChamadoDao chamadoDao = new ChamadoDao();
	
	public List<Historico> listarHistoricoChamado(Integer id) throws Exception {
		open();
		String sql = "";
		sql += "SELECT IDHISTORICO, DTHRABERTURA, DTHRFECHAMENTO, DTHRSOLUCAO, DTHRALTERACAO, DESCCHAMADO, ID_CHAMADO,";
		sql += " ID_STATUS, id_responsavel, ID_SOLICITANTE, ID_ATENDENTE, ID_CATEGORIA, ID_NOVAPRIORIDADE, ID_OPERADOR";
		sql += " FROM HISTORICO";	
		sql += " WHERE ID_CHAMADO = " + id;
		//stmt.setInt(1, id);
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Historico> listaHistorico = new ArrayList<Historico>();
		while (rs.next()) { 
			Historico historico = new Historico();
			
			historico.setIdHistorico( rs.getInt("IDHISTORICO") );
			historico.setDtHrAbertura( rs.getTimestamp("DTHRABERTURA") +"" );
			historico.setDtHrFechamento(  rs.getTimestamp("DTHRFECHAMENTO")+"");
			historico.setDtHrSolucao(  rs.getTimestamp("DTHRSOLUCAO")+""  );
			historico.setDtHrAlteracao(  rs.getTimestamp("DTHRALTERACAO")+""  );
			historico.setDescChamado(  rs.getString("DESCCHAMADO")  );
			historico.setStatus(  statusDao.findByCod(rs.getInt("ID_STATUS"))  );
			if(rs.getString("ID_RESPONSAVEL") != null)
				historico.setResponsavel( usuarioDao.findByCod( rs.getInt("ID_RESPONSAVEL") ) );
			if(rs.getString("ID_SOLICITANTE") != null)
				historico.setSolicitante(  usuarioDao.findByCod( rs.getInt("ID_SOLICITANTE")  ) );
			if(rs.getString("ID_ATENDENTE") != null)
				historico.setAtendente(  usuarioDao.findByCod( rs.getInt("ID_ATENDENTE") )  );
			if(rs.getString("ID_OPERADOR") != null)
				historico.setAtendente(  usuarioDao.findByCod( rs.getInt("ID_OPERADOR") )  );
			
			historico.setCategoria( categoriaDao.finByCode( rs.getInt("ID_CATEGORIA") ) );
			
			if(rs.getString("ID_NOVAPRIORIDADE") != null){
				historico.setNovaPrioridade( prioridadeDao.findByCode(rs.getInt("ID_NOVAPRIORIDADE")) );
			}else{
				historico.setNovaPrioridade(  historico.getCategoria().getPrioridade()  );
			}
			
			historico.setChamado(   new ChamadoDao().findByCode(rs.getInt("ID_CHAMADO"))  );
			
			listaHistorico.add(historico);
		}
		rs.close();
		close();
		return listaHistorico;
	}
	
}
