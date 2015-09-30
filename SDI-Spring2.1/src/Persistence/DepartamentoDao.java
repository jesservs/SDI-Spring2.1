package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Departamento;
import Entity.Perfil;

@Component
public class DepartamentoDao extends Dao {

	public Departamento create(Departamento departamento) throws Exception {
		open();
		stmt = con.prepareStatement("insert into departamento values(null,?)");
		stmt.setString(1, departamento.getDescDepto());
		stmt.execute();
		stmt.close();
		close();
		return departamento;
	}

	public List<Departamento> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select iddepto, descdepto from departamento");
		ResultSet rs = stmt.executeQuery();

		List<Departamento> listaDepto = new ArrayList<Departamento>();
		while (rs.next()) {
			Departamento departamento = new Departamento();
			departamento.setIdDepto(rs.getInt("iddepto"));
			departamento.setDescDepto(rs.getString("descdepto"));
			listaDepto.add(departamento);
		}
		close();
		return listaDepto;
	}
	
	public void update(Departamento departamento) throws Exception {
		open();
		stmt = con.prepareStatement("update DEPARTAMENTO set DESCDEPTO = ? where IDDEPTO = ?");
		stmt.setString(1, departamento.getDescDepto());
		stmt.setInt(2, departamento.getIdDepto());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public Departamento findByCode(Integer id) throws Exception {
		open();
		stmt = con.prepareStatement("SELECT IDDEPTO, DESCDEPTO FROM DEPARTAMENTO WHERE IDDEPTO = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Departamento departamento = new Departamento();
			while (rs.next()) {
				departamento.setIdDepto(rs.getInt("IDDEPTO"));
				departamento.setDescDepto(rs.getString("DESCDEPTO"));
			}
		close();
		return departamento;
	}

	public void delete(Departamento departamento) throws Exception {
		open();
		stmt = con.prepareStatement("DELETE FROM DEPARTAMENTO WHERE IDDEPTO = ?");
		stmt.setInt(1, departamento.getIdDepto());
		stmt.execute();
		stmt.close();
		close();
	}
	
}
