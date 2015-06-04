package Action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Usuario;
import Persistence.ChamadoDao;
import Persistence.UsuarioDao;


@Controller
@RequestMapping(value="sla")
public class SLAAction {
	
	@Autowired(required=true)
	private ChamadoDao chamadoDao;
	
	@RequestMapping(value="listar")
	public String exibirPagListar(Model model){
		return "sla/listar";
	}
	
	@RequestMapping(value="carregarGraficoSLA", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> carregarGraficoSLA(){
		Map<String, Integer> grafico = new HashMap<String, Integer>();
		try {
			grafico.put("CRDSLA", chamadoDao.getElementosFechadosDentroSLA());
			grafico.put("CRFSLA", chamadoDao.getElementosFechadosForaSLA());
			grafico.put("CNRFSLA", chamadoDao.getElementosAbertosForaSLA());
			grafico.put("CNRDSLA", chamadoDao.getElementosAbertosDentroSLA());
			return grafico;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="carregarGraficoSLAPorTecnico", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> carregarGraficoSLAPorTecnico(HttpSession session){
		Map<String, Integer> grafico = new HashMap<String, Integer>();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		try {
			grafico.put("perfil", usuario.getPerfil().getIdPerfil());
			grafico.put("CRDSLA", chamadoDao.getElementosFechadosDentroSLAPorTecnico(usuario));
			grafico.put("CRFSLA", chamadoDao.getElementosFechadosForaSLAPorTecnico(usuario));
			grafico.put("CNRFSLA", chamadoDao.getElementosAbertosForaSLAPorTecnico(usuario));
			grafico.put("CNRDSLA", chamadoDao.getElementosAbertosDentroSLAPorTecnico(usuario));
			return grafico;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="carregarGraficoSLAAdministrador", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> carregarGraficoSLAAdministrador(Integer id) throws Exception{
		Map<String, Integer> grafico = new HashMap<String, Integer>();
		Usuario usuario = new UsuarioDao().findByCod(id);
		try {
			grafico.put("perfil", usuario.getPerfil().getIdPerfil());
			grafico.put("CRDSLA", chamadoDao.getElementosFechadosDentroSLAPorTecnico(usuario));
			grafico.put("CRFSLA", chamadoDao.getElementosFechadosForaSLAPorTecnico(usuario));
			grafico.put("CNRFSLA", chamadoDao.getElementosAbertosForaSLAPorTecnico(usuario));
			grafico.put("CNRDSLA", chamadoDao.getElementosAbertosDentroSLAPorTecnico(usuario));
			return grafico;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
