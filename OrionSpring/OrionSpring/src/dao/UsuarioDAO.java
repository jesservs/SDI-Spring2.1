package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.sistema.Perfil;
import modelo.sistema.Usuario;

import org.springframework.stereotype.Component;

import dao.PerfilDAO;

@Component
public class UsuarioDAO extends DAO implements CrudDAO<Usuario>{
	
	public Usuario logar(Usuario usuario) throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nome, cpf, tel, email, "
				+ "idperfil from usuario where login='"+usuario.getLogin()+"' and senha=md5('"+usuario.getSenha()+"');");
		ResultSet rs = stmt.executeQuery();
		if( rs.next() ){
			usuario.setIdUsuario( rs.getInt( "idUsuario" ) );
			usuario.setNome( rs.getString( "nome" ) );
			usuario.setCpf( rs.getString( "cpf" ) );
			usuario.setEmail( rs.getString( "email" ) );
			usuario.setTelefone( rs.getString( "tel" ) );
			usuario.setPerfil( new PerfilDAO().findByCod( rs.getInt( "idperfil" ) ) );
		}
		stmt.close();
		close();
		return usuario;
	}
	
	public void cadastrar(Usuario usuario) throws Exception {
		String sql = "";
		sql += "INSERT INTO USUARIO (IDUSUARIO, LOGIN, SENHA, NOME, CPF, TEL, EMAIL, IDPERFIL)";
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
		stmt.execute();
		stmt.close();
		close();
	}

	public List<Usuario> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nome, cpf, tel, idperfil from usuario");
		ResultSet rs = stmt.executeQuery();
		
		PerfilDAO perfilDao = new PerfilDAO();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setTelefone(rs.getString("tel"));
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("idperfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public List<Usuario> findTecnicos() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT * FROM USUARIO WHERE IDPERFIL = 2");
		ResultSet rs = stmt.executeQuery();
		
		PerfilDAO perfilDao = new PerfilDAO();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setTelefone(rs.getString("tel"));
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("idperfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public List<Usuario> findAtendentes() throws Exception {
		open();
		stmt = con.prepareStatement("SELECT * FROM USUARIO WHERE IDPERFIL = 1");
		ResultSet rs = stmt.executeQuery();
	
		PerfilDAO perfilDao = new PerfilDAO();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setTelefone(rs.getString("tel"));
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("idperfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	
	
	public List<Usuario> findAllByPerfilJSON(Integer codPerfil) throws Exception {
		open();
		stmt = con.prepareStatement("select idUsuario, nome, cpf, tel, idperfil from usuario where idperfil = ?");
		stmt.setInt(1, codPerfil);
		ResultSet rs = stmt.executeQuery();
		
		PerfilDAO perfilDao = new PerfilDAO();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setTelefone(rs.getString("tel"));
			usuario.setPerfil(  perfilDao.findByCod( rs.getInt("idperfil") )  );

			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public void alterar(Usuario usuario) throws Exception {
		String sql = "";
		sql += "UPDATE USUARIO SET LOGIN=?, SENHA=md5(?), NOME=?, CPF=?, TEL=?, EMAIL=?, IDPERFIL=?";
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
		stmt.setInt(8, usuario.getIdUsuario());
		stmt.execute();
		stmt.close();
		close();
	}

	public Usuario findByCPF(Usuario usuario) throws Exception {
		open();
		stmt = con
				.prepareStatement("select idUsuario, nome, cpf, tel "
						+ "from usuario "
						+ "where cpf = ?");
		stmt.setString(1, usuario.getCpf());

		ResultSet rs = stmt.executeQuery();

		Usuario usuarioAchado = new Usuario();
		if (rs.next()) {
			usuarioAchado.setIdUsuario(rs.getInt("idUsuario"));
			usuarioAchado.setNome(rs.getString("nome"));
			usuarioAchado.setCpf(rs.getString("cpf"));
			usuarioAchado.setTelefone(rs.getString("tel"));
		}
		close();
		return usuarioAchado;
	}

	public Usuario findByCod(Integer idUsuario) throws Exception {
		String sql = "";
		sql += "SELECT * FROM USUARIO";
		sql += " INNER JOIN PERFIL ON PERFIL.IDPERFIL = USUARIO.IDPERFIL";
		sql += " WHERE USUARIO.IDUSUARIO = ?";
		open();
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		ResultSet rs = stmt.executeQuery();

		Usuario usuarioAchado = new Usuario();
		Perfil perfil = new Perfil();
		if (rs.next()) {
			usuarioAchado.setIdUsuario(rs.getInt("idUsuario"));
			usuarioAchado.setNome(rs.getString("usuario.nome"));
			usuarioAchado.setCpf(rs.getString("cpf"));
			usuarioAchado.setEmail(rs.getString("email"));
			usuarioAchado.setTelefone(rs.getString("tel"));
			usuarioAchado.setLogin( rs.getString("login"));
			perfil.setIdPerfil(rs.getInt("idperfil"));
			perfil.setNomePerfil(rs.getString("perfil.nome"));
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
