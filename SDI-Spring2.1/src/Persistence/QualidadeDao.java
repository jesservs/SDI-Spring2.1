package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import Entity.Qualidade;

@Component
public class QualidadeDao extends Dao {

	public List<Qualidade> findAll() throws Exception {
		String sql = "SELECT IDQUALIDADE, DESCQUALIDADE, NOTA, ID_SOLICITANTE, id_chamado";
		sql += " FROM QUALIDADE";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Qualidade> listaQualidade = new ArrayList<Qualidade>();
		while (rs.next()) {
			Qualidade qualidade = new Qualidade();
			qualidade.setIdQualidade( rs.getInt("IDQUALIDADE") );
			qualidade.setDescQualidade( rs.getString("DESCQUALIDADE") );
			qualidade.setNota( rs.getInt("NOTA") );
			qualidade.setSolicitante( 	new UsuarioDao().findByCod(rs.getInt("ID_SOLICITANTE")) 	);
			qualidade.setChamado(  new ChamadoDao().findByCode(rs.getInt("id_chamado")) );
			listaQualidade.add(qualidade);
		}
		close();
		return listaQualidade;
	}
	
	public void create(Qualidade qualidade) throws Exception {
		String sql = "INSERT INTO QUALIDADE (IDQUALIDADE, DESCQUALIDADE, NOTA, ID_SOLICITANTE, ID_CHAMADO)";
		sql += " VALUES (null, ?, ?, ?, ?)";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, qualidade.getDescQualidade()	);
		stmt.setInt(2, qualidade.getNota()	);
		stmt.setInt(3, qualidade.getSolicitante().getIdUsuario()	);
		stmt.setInt(4, qualidade.getChamado().getIdChamado()	);
		stmt.execute();
		stmt.close();
		close();
	}
	
	public Qualidade findByCode(Integer id) throws Exception{
		String sql = "SELECT IDQUALIDADE, DESCQUALIDADE, NOTA, ID_SOLICITANTE, id_chamado";
		sql += " FROM QUALIDADE";
		sql += " WHERE IDQUALIDADE = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Qualidade qualidade = new Qualidade();
		if (rs.next()) {
			qualidade.setIdQualidade( rs.getInt("IDQUALIDADE") );
			qualidade.setDescQualidade( rs.getString("DESCQUALIDADE") );
			qualidade.setNota( rs.getInt("NOTA") );
			qualidade.setSolicitante( 	new UsuarioDao().findByCod(rs.getInt("ID_SOLICITANTE")) 	);
			qualidade.setChamado(  new ChamadoDao().findByCode(rs.getInt("id_chamado")) );
		}
		close();
		return qualidade;
	}
	
	public Map<String, Integer> getNotas() throws Exception{
		String sql = "select  nota, COUNT(qualidade.idqualidade) AS total";
		sql += " from qualidade";
		sql += " group by nota";
		open();
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Map<String, Integer> notas = new HashMap<String, Integer>();
		//inicializando notas
		notas.put("minsatisfeito", 0);
		notas.put("insatisfeito", 0);
		notas.put("satisfeito", 0);
		notas.put("msatisfeito", 0);
		while (rs.next()) {
			String qualidade = rs.getString("nota");
			if(qualidade.equals("1")) notas.put("minsatisfeito", rs.getInt("total"));
			if(qualidade.equals("2")) notas.put("insatisfeito", rs.getInt("total"));
			if(qualidade.equals("3")) notas.put("satisfeito", rs.getInt("total"));
			if(qualidade.equals("4")) notas.put("msatisfeito", rs.getInt("total"));
		}
		rs.close();
		close();
		return notas;
	}
	
}
