package Action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Categoria;
import Entity.Chamado;
import Entity.Prioridade;
import Entity.Tipo;
import Entity.Usuario;
import Persistence.CategoriaDao;
import Persistence.ChamadoDao;
import Persistence.PrioridadeDao;
import Persistence.TipoDao;

@Controller
@RequestMapping(value="chamado")
public class ChamadoAction {
	
	@Autowired(required=true)
	private CategoriaDao categoriaDao;
	
	@Autowired(required=true)
	private TipoDao tipoDao;
	
	@Autowired(required=true)
	private PrioridadeDao prioridadeDao;
	
	@Autowired(required=true)
	private ChamadoDao chamadoDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "chamado/listar";
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public Chamado create(Chamado chamado, HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			if(usuario.getPerfil().getIdPerfil() == 4) chamado.setSolicitante(usuario);
			if(usuario.getPerfil().getIdPerfil() == 1) chamado.setAtendente(usuario);
			chamado.setDateAbertura(new Date());
			return chamadoDao.create(chamado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="setResponsavel")
	@ResponseBody
	public void setResponsavel(Chamado chamado){
		try {
			chamadoDao.setResponsavel(chamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="setNovoStatus")
	@ResponseBody
	public void setNovoStatus(Chamado chamado){
		try {
			chamadoDao.setNovoStatus(chamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getNumberElementos", method = RequestMethod.GET)
	@ResponseBody
	public Integer getNumberElementos(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			return chamadoDao.getNumberElements(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="getNumberElementosComFiltro", method = RequestMethod.GET)
	@ResponseBody
	public Integer getNumberElementos(HttpSession session, Chamado chamado){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			return chamadoDao.getNumberElementsComFiltro(usuario, chamado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Chamado> findAll(Integer pagina, HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			return chamadoDao.findAll(pagina, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="findAllComFiltro", method = RequestMethod.GET)
	@ResponseBody
	public List<Chamado> findAllComFiltro(Integer pagina, HttpSession session, Chamado chamado){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			return chamadoDao.findAllComFiltro(pagina, usuario, chamado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
