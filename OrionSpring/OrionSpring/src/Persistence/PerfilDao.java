package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Perfil;
import Entity.Usuario;

@Component
public class PerfilDao extends Dao {
	public List<Perfil> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT IDPERFIL, NOMEPERFIL FROM PERFIL");
		ResultSet rs = stmt.executeQuery();

		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		while (rs.next()) {
			Perfil perfil = new Perfil();
			perfil.setIdPerfil(rs.getInt("idperfil"));
			perfil.setNomePerfil(rs.getString("nomeperfil"));
			listaPerfis.add(perfil);
		}
		close();
		return listaPerfis;
	}
	
	public void create(Perfil perfil) throws Exception {
		open();
		stmt = con.prepareStatement("insert into perfil values(null, ?)");
		stmt.setString(1, perfil.getNomePerfil());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public void delete(Perfil perfil) throws Exception {
		open();
		stmt = con.prepareStatement("delete from perfil where idPerfil = ?");
		stmt.setInt(1, perfil.getIdPerfil());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public Perfil findByCod(Integer id) throws Exception {
		open();
		stmt = con.prepareStatement("select * from perfil where idPerfil = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Perfil perfilAchado = new Perfil();
		if (rs.next()) {
			perfilAchado.setIdPerfil(rs.getInt("idperfil"));
			perfilAchado.setNomePerfil(rs.getString("nomeperfil"));
		}
		close();
		return perfilAchado;
	}
	
	public void update(Perfil perfil) throws Exception {
		open();
		stmt = con.prepareStatement("update perfil set nomePerfil = ? where idPerfil = ?");
		stmt.setString(1, perfil.getNomePerfil());
		stmt.setInt(2, perfil.getIdPerfil());
		stmt.execute();
		stmt.close();
		close();
	}
}
