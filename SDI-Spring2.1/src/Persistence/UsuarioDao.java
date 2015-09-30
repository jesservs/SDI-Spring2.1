package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Departamento;
import Entity.Perfil;
import Entity.Usuario;

@Component
public class UsuarioDao extends Dao {
	
	public Usuario logar(Usuario usuario) throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nomeUsuario, cpfUsuario, telUsuario, email, "
				+ "id_depto, id_perfil from usuario where login='"+usuario.getLogin()+"' and senha=md5('"+usuario.getSenha()+"');");
		ResultSet rs = stmt.executeQuery();
		if( rs.next() ){
			usuario.setIdUsuario( rs.getInt( "idUsuario" ) );
			usuario.setNome( rs.getString( "nomeUsuario" ) );
			usuario.setCpf( rs.getString( "cpfUsuario" ) );
			usuario.setEmail( rs.getString( "email" ) );
			usuario.setTelefone( rs.getString( "telUsuario" ) );
			usuario.setDepartamento( new DepartamentoDao().findByCode( rs.getInt( "id_depto" ) ) );
			usuario.setPerfil( new PerfilDao().findByCod( rs.getInt( "id_perfil" ) ) );
		}
		stmt.close();
		close();
		return usuario;
	}
	
	public void cadastrarUsuario(Usuario usuario) throws Exception {
		String sql = "";
		sql += "INSERT INTO USUARIO (IDUSUARIO, LOGIN, SENHA, NOMEUSUARIO, CPFUSUARIO, TELUSUARIO, EMAIL, ID_PERFIL, ID_DEPTO)";
		sql += " VALUES (NULL, ?, md5(?), ?, ?, ?, ?, ?, ?)";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getLogin());
		stmt.setString(2, usuario.getSenha());
		stmt.setString(3, usuario.getNome());
		stmt.setString(4, usuario.getCpf());
		stmt.setString(5, usuario.getTelefone());
		stmt.setString(6, usuario.getEmail());
		stmt.setInt(7, usuario.getPerfil().getIdPerfil());
		stmt.setInt(8, usuario.getDepartamento().getIdDepto());
		stmt.execute();
		stmt.close();
		close();
	}

	public List<Usuario> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nomeUsuario, cpfUsuario, telUsuario, id_depto, id_perfil from usuario");
		ResultSet rs = stmt.executeQuery();
		
		DepartamentoDao departamentoDao = new DepartamentoDao();
		PerfilDao perfilDao = new PerfilDao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nomeUsuario"));
			usuario.setCpf(rs.getString("cpfUsuario"));
			usuario.setTelefone(rs.getString("telUsuario"));
			usuario.setDepartamento(  departamentoDao.findByCode( rs.getInt("id_depto") )  );
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("id_perfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public List<Usuario> findTecnicos() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT * FROM USUARIO WHERE ID_PERFIL = 2");
		ResultSet rs = stmt.executeQuery();
		
		DepartamentoDao departamentoDao = new DepartamentoDao();
		PerfilDao perfilDao = new PerfilDao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nomeUsuario"));
			usuario.setCpf(rs.getString("cpfUsuario"));
			usuario.setTelefone(rs.getString("telUsuario"));
			usuario.setDepartamento(  departamentoDao.findByCode( rs.getInt("id_depto") )  );
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("id_perfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public List<Usuario> findAtendentes() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT * FROM USUARIO WHERE ID_PERFIL = 1");
		ResultSet rs = stmt.executeQuery();
		
		DepartamentoDao departamentoDao = new DepartamentoDao();
		PerfilDao perfilDao = new PerfilDao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nomeUsuario"));
			usuario.setCpf(rs.getString("cpfUsuario"));
			usuario.setTelefone(rs.getString("telUsuario"));
			usuario.setDepartamento(  departamentoDao.findByCode( rs.getInt("id_depto") )  );
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("id_perfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	
	
	public List<Usuario> findAllByPerfilJSON(Integer codPerfil) throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nomeUsuario, cpfUsuario, telUsuario, id_depto, id_perfil from usuario where id_perfil = ?");
		stmt.setInt(1, codPerfil);
		ResultSet rs = stmt.executeQuery();
		
		DepartamentoDao departamentoDao = new DepartamentoDao();
		PerfilDao perfilDao = new PerfilDao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nomeUsuario"));
			usuario.setCpf(rs.getString("cpfUsuario"));
			usuario.setTelefone(rs.getString("telUsuario"));
			usuario.setDepartamento(  departamentoDao.findByCode( rs.getInt("id_depto") )  );
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("id_perfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public void alterarUsuario(Usuario usuario) throws Exception {
		String sql = "";
		sql += "UPDATE USUARIO SET LOGIN=?, SENHA=md5(?), NOMEUSUARIO=?, CPFUSUARIO=?, TELUSUARIO=?, EMAIL=?, ID_PERFIL=?, ID_DEPTO=?";
		sql += " WHERE IDUSUARIO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getLogin());
		stmt.setString(2, usuario.getSenha());
		stmt.setString(3, usuario.getNome());
		stmt.setString(4, usuario.getCpf());
		stmt.setString(5, usuario.getTelefone());
		stmt.setString(6, usuario.getEmail());
		stmt.setInt(7, usuario.getPerfil().getIdPerfil());
		stmt.setInt(8, usuario.getDepartamento().getIdDepto());
		stmt.setInt(9, usuario.getIdUsuario());
		stmt.execute();
		stmt.close();
		close();
	}

	public Usuario findByCPF(Usuario usuario) throws Exception {
		open();
		stmt = con
				.prepareStatement("select idUsuario, nomeUsuario, cpfUsuario, telUsuario, iddepto, descdepto "
						+ "from usuario "
						+ "inner join departamento on id_depto = iddepto "
						+ "where cpfUsuario = ?");
		stmt.setString(1, usuario.getCpf());

		ResultSet rs = stmt.executeQuery();

		Usuario usuarioAchado = new Usuario();
		if (rs.next()) {
			usuarioAchado.setIdUsuario(rs.getInt("idUsuario"));
			usuarioAchado.setNome(rs.getString("nomeUsuario"));
			usuarioAchado.setCpf(rs.getString("cpfUsuario"));
			usuarioAchado.setTelefone(rs.getString("telUsuario"));
			usuarioAchado.getDepartamento().setIdDepto(rs.getInt("iddepto"));
			usuarioAchado.getDepartamento().setDescDepto(rs.getString("descdepto"));
		}
		close();
		return usuarioAchado;
	}

	public Usuario findByCod(Integer idUsuario) throws Exception {
		String sql = "";
		sql += "SELECT * FROM USUARIO";
		sql += " INNER JOIN DEPARTAMENTO ON DEPARTAMENTO.IDDEPTO = USUARIO.ID_DEPTO";
		sql += " INNER JOIN PERFIL ON PERFIL.IDPERFIL = USUARIO.ID_PERFIL";
		sql += " WHERE USUARIO.IDUSUARIO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		ResultSet rs = stmt.executeQuery();

		Usuario usuarioAchado = new Usuario();
		Departamento departamento = new Departamento();
		Perfil perfil = new Perfil();
		if (rs.next()) {
			usuarioAchado.setIdUsuario(rs.getInt("idUsuario"));
			usuarioAchado.setNome(rs.getString("nomeUsuario"));
			usuarioAchado.setCpf(rs.getString("cpfUsuario"));
			usuarioAchado.setEmail(rs.getString("email"));
			usuarioAchado.setTelefone(rs.getString("telUsuario"));
			usuarioAchado.setLogin( rs.getString("login"));
			departamento.setIdDepto(rs.getInt("iddepto"));
			departamento.setDescDepto(rs.getString("descdepto"));
			perfil.setIdPerfil(rs.getInt("idperfil"));
			perfil.setNomePerfil(rs.getString("nomeperfil"));
			usuarioAchado.setDepartamento(departamento);
			usuarioAchado.setPerfil(perfil);
		}
		close();
		return usuarioAchado;
	}

	public void delete(Usuario usuario) throws Exception {
		open();
		stmt = con.prepareStatement("delete from usuario where idUsuario = ?");
		stmt.setInt(1, usuario.getIdUsuario());
		stmt.execute();
		stmt.close();
		close();
	}

}
