package Action;

import java.util.List;

import modelo.sistema.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UsuarioDAO;

@Controller
@RequestMapping(value="usuario")
public class UsuarioAction {
	
	@Autowired(required=true)
	private UsuarioDAO usuarioDao;
	
	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Usuario findByCode(Usuario usuario){
		try {
			usuario = usuarioDao.findByCod(usuario.getIdUsuario());
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="listar")
	public String listar(){
		try {
			return "usuario/listar";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> listarUsuarios(){
		List<Usuario> lista;
		try {
			lista = usuarioDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findTecnicosJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> findTecnicosJSON(){
		List<Usuario> lista;
		try {
			lista = usuarioDao.findTecnicos();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findAtendentesJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> findAtendentesJSON(){
		List<Usuario> lista;
		try {
			lista = usuarioDao.findAtendentes();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findAllByPerfilJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> findAllByPerfilJSON(){
		List<Usuario> lista;
		try {
			lista = usuarioDao.findAllByPerfilJSON(4);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create", method = RequestMethod.GET)
	@ResponseBody
	public void create(Usuario usuario){
		try {
			usuarioDao.cadastrar(usuario);
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Usuario usuario){
		try {
			usuarioDao.alterar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	@ResponseBody
	public void delete(Usuario usuario){
		try {
			usuarioDao.delete(usuario);
		} catch (Exception e) {
			
		}
	}
}
