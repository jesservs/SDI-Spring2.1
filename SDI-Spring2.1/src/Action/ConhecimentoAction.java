package Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Conhecimento;
import Entity.Usuario;
import Persistence.ConhecimentoDao;


@Controller
@RequestMapping(value="conhecimento")
public class ConhecimentoAction {
	
	@Autowired(required=true)
	private ConhecimentoDao conhecimentoDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "conhecimento/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Conhecimento> findAll(){
		List<Conhecimento> lista;
		try {
			lista = conhecimentoDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Conhecimento conhecimento, HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			conhecimento.setResponsavel(usuario);
			conhecimentoDao.create(conhecimento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Conhecimento conhecimento){
		try {
			conhecimentoDao.delete(conhecimento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="findAllJSONComFiltro")
	@ResponseBody
	public List<Conhecimento> findAllJSONComFiltro(Conhecimento conhecimento){
		try {
			List<Conhecimento> lista;
			lista = conhecimentoDao.findAllComFiltro(conhecimento.getCategoria());
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Tipo findByCode(Tipo tipo){
		try {
			tipo = tipoDao.findByCod(tipo.getIdTipo());
			return tipo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Tipo tipo){
		try {
			tipoDao.update(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}

