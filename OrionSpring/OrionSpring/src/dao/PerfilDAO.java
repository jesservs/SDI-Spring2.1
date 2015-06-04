package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import modelo.sistema.Perfil;

@Component
public class PerfilDAO extends DAO implements CrudDAO<Perfil> {
	
	public List<Perfil> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT IDPERFIL, NOME FROM PERFIL");
		ResultSet rs = stmt.executeQuery();

		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		while (rs.next()) {
			Perfil perfil = new Perfil();
			perfil.setIdPerfil(rs.getInt("idperfil"));
			perfil.setNomePerfil(rs.getString("nome"));
			listaPerfis.add(perfil);
		}
		close();
		return listaPerfis;
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
			perfilAchado.setNomePerfil(rs.getString("nome"));
		}
		close();
		return perfilAchado;
	}
	
	@Override
	public void cadastrar(Perfil perfil) throws Exception {
		open();
		stmt = con.prepareStatement("insert into perfil values(null, ?)");
		stmt.setString(1, perfil.getNomePerfil());
		stmt.execute();
		stmt.close();
		close();
	}

	@Override
	public void alterar(Perfil perfil) throws Exception {
		open();
		stmt = con.prepareStatement("update perfil set nomePerfil = ? where idPerfil = ?");
		stmt.setString(1, perfil.getNomePerfil());
		stmt.setInt(2, perfil.getIdPerfil());
		stmt.execute();
		stmt.close();
		close();
		
	}
}
