package Action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Qualidade;
import Entity.Usuario;
import Persistence.QualidadeDao;


@Controller
@RequestMapping(value="qualidade")
public class QualidadeAction {
	
	@Autowired(required=true)
	private QualidadeDao qualidadeDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "qualidade/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Qualidade> findAll(){
		List<Qualidade> lista;
		try {
			lista = qualidadeDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="getNotas", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getNotas(){
		Map<String, Integer> notas;
		try {
			notas = qualidadeDao.getNotas();
			return notas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Qualidade qualidade, HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			qualidade.setSolicitante(usuario);
			qualidadeDao.create(qualidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
