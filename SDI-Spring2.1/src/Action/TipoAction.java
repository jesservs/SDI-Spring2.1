package Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Perfil;
import Entity.Tipo;
import Persistence.TipoDao;


@Controller
@RequestMapping(value="tipo")
public class TipoAction {
	
	@Autowired(required=true)
	private TipoDao tipoDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "tipo/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Tipo> findAll(){
		List<Tipo> lista;
		try {
			lista = tipoDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Tipo tipo){
		try {
			tipoDao.create(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Tipo tipo){
		try {
			tipoDao.delete(tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
