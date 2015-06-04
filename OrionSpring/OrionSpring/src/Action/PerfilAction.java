package Action;

import java.util.List;

import modelo.sistema.Perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.PerfilDAO;


@Controller
@RequestMapping(value="perfil")
public class PerfilAction {
	
	@Autowired(required=true)
	private PerfilDAO perfilDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "perfil/listar";
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Perfil> findAll(){
		List<Perfil> lista;
		try {
			lista = perfilDao.findAll();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findByCodeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Perfil findByCode(Perfil perfil){
		try {
			perfil = perfilDao.findByCod(perfil.getIdPerfil());
			return perfil;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Perfil perfil){
		try {
			perfilDao.cadastrar(perfil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(Perfil perfil){
		try {
			perfilDao.alterar(perfil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Perfil perfil){
		try {
			perfilDao.delete(perfil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
