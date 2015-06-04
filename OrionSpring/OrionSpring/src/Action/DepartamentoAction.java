package Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Departamento;
import Entity.Departamento;
import Persistence.DepartamentoDao;

@Controller
@RequestMapping(value="departamento")
public class DepartamentoAction {
	
	@Autowired(required=true)
	private DepartamentoDao departamentoDao;
	
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "departamento/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Departamento> findAllJSON(){
		List<Departamento> lista;
		try {
			lista = departamentoDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Departamento findByCode(Departamento departamento){
		try {
			departamento = departamentoDao.findByCode(departamento.getIdDepto());
			return departamento;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Departamento departamento){
		try {
			departamentoDao.create(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Departamento departamento){
		try {
			departamentoDao.update(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Departamento departamento){
		try {
			departamentoDao.delete(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
