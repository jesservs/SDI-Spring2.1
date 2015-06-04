package Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Perfil;
import Entity.Prioridade;
import Persistence.PrioridadeDao;


@Controller
@RequestMapping(value="prioridade")
public class PrioridadeAction {
	
	@Autowired(required=true)
	private PrioridadeDao prioridadeDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "prioridade/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Prioridade> findAllJSON(Model model){
		List<Prioridade> lista;
		try {
			lista = prioridadeDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Prioridade findByCode(Prioridade prioridade){
		try {
			prioridade = prioridadeDao.findByCode(prioridade.getIdPrioridade());
			return prioridade;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Prioridade prioridade){
		try {
			prioridadeDao.create(prioridade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Prioridade prioridade){
		try {
			prioridadeDao.update(prioridade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Prioridade prioridade){
		try {
			prioridadeDao.delete(prioridade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
