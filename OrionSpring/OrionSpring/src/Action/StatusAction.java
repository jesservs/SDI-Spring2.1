package Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import Entity.Status;
import Persistence.StatusDao;



@Controller
@RequestMapping(value="status")
public class StatusAction {
	
	@Autowired(required=true)
	private StatusDao statusDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "status/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Status> findAll(){
		List<Status> lista;
		try {
			lista = statusDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Status findByCode(Status status){
		try {
			status = statusDao.findByCod(status.getIdStatus());
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Status status){
		try {
			statusDao.create(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Status status){
		try {
			statusDao.update(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Status status){
		try {
			statusDao.delete(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
