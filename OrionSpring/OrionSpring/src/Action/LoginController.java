package Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import modelo.sistema.Usuario;
import dao.UsuarioDAO;

@Controller
public class LoginController {
	
	@Autowired(required=true)
	private UsuarioDAO usuarioDao;
	
	@RequestMapping(value="loginForm")
	public String loginForm(){
		try {
			return "loginForm";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="logar", method = RequestMethod.GET)
	public String logar(Usuario usuario, HttpSession session){
		try {
			usuario = usuarioDao.logar(usuario);
			if( usuario.getIdUsuario() != null ){
				session.setAttribute("usuarioLogado", usuario);
				return "chamado/listar";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("mensagem", "Usuario ou Senha invalidos!");
		return "redirect:loginForm";
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		try {
			session.removeAttribute("usuarioLogado");
			session.invalidate();
			return "redirect:loginForm";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:loginForm";
	}

	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	
}
