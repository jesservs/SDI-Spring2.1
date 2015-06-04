package Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Categoria;
import Entity.Prioridade;
import Entity.Tipo;
import Persistence.CategoriaDao;
import Persistence.PrioridadeDao;
import Persistence.TipoDao;

@Controller
@RequestMapping(value="categoria")
public class CategoriaAction {
	
	@Autowired(required=true)
	private CategoriaDao categoriaDao;
	
	@Autowired(required=true)
	private TipoDao tipoDao;
	
	@Autowired(required=true)
	private PrioridadeDao prioridadeDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "categoria/listar";
	}
	
	@RequestMapping(value="create")
	@ResponseBody
	public void create(Categoria categoria){
		try {
			categoriaDao.create(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public void delete(Categoria categoria){
		try {
			categoriaDao.delete(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getNumberElementos")
	@ResponseBody
	public Integer getNumberElementos(Categoria categoria){
		try {
			return categoriaDao.getNumberElements();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Categoria> findAll(Integer pagina){
		List<Categoria> lista;
		try {
			lista = categoriaDao.findAll(pagina);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findAllByTipoJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Categoria> findAllByTipoJSON(Integer codTipo){
		List<Categoria> lista;
		try {
			lista = categoriaDao.buscarPorTipo(codTipo);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="findAllTipoAndPrioridadeJSON", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findAllTipoAndPrioridadeJSON(){
		List<Tipo> tipos;
		List<Prioridade> prioridades;
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			tipos = tipoDao.findAll();
			prioridades = prioridadeDao.findAll();
			mapa.put("tipo", tipos);
			mapa.put("prioridade", prioridades);
			return mapa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
