package Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.Historico;
import Entity.Usuario;
import Persistence.HistoricoDao;

@Controller
@RequestMapping(value="historico")
public class HistoricoAction {
	
	@Autowired(required=true)
	private HistoricoDao historicoDao;
	
	@RequestMapping(value="findAllJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Historico> listarHistorico(Integer id){
		List<Historico> lista;
		try {
			lista = historicoDao.listarHistoricoChamado(id);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
