package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Entity.Categoria;
import Entity.Prioridade;
import Entity.Tipo;

@Component
public class CategoriaDao extends Dao {
	
	private TipoDao tipoDao = new TipoDao();
	private PrioridadeDao prioridadeDao = new PrioridadeDao();
	
	public Integer getNumberElements() throws Exception {
		open();
		stmt = con.prepareStatement("select COUNT(idcategoria) as TOTAL from categoria");
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("TOTAL");
		}
		close();
		return null;
	}
	
	public void create(Categoria categoria) throws Exception {
		open();
		stmt = con.prepareStatement("insert into categoria values (null,?,?,?,1);");
		stmt.setString(1, categoria.getDescCategoria());
		stmt.setInt(2, categoria.getTipo().getIdTipo());
		stmt.setInt(3, categoria.getPrioridade().getIdPrioridade());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public void delete(Categoria categoria) throws Exception {
		open();
		stmt = con.prepareStatement("delete from categoria where idcategoria = ?");
		stmt.setInt(1, categoria.getIdCategoria());
		stmt.execute();
		stmt.close();
		close();
	}

	
	public List<Categoria> buscarPorTipo(Integer codTipo) throws Exception {
		open();
		stmt = con
				.prepareStatement("select idcategoria, desccategoria, "
						+ "idtipo, desctipo, "
						+ "idprioridade, descprioridade, temporesolucao "
						+ "from categoria "
						+ "inner join tipo on categoria.id_tipo=tipo.idtipo "
						+ "inner join prioridade on categoria.id_prioridade=prioridade.idprioridade "
						+ "where idtipo = ?");
		stmt.setInt(1, codTipo);

		ResultSet rs = stmt.executeQuery();

		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		while (rs.next()) {
			Categoria categoria = new Categoria();

			categoria.setIdCategoria(rs.getInt("idcategoria"));
			categoria.setDescCategoria(rs.getString("desccategoria"));

			categoria.getTipo().setIdTipo(rs.getInt("idtipo"));
			categoria.getTipo().setDescTipo(rs.getString("desctipo"));

			categoria.getPrioridade()
					.setIdPrioridade(rs.getInt("idprioridade"));
			categoria.getPrioridade().setDescPrioridade(
					rs.getString("descprioridade"));
			categoria.getPrioridade().setTempoResolucao(
					rs.getInt("temporesolucao"));

			listaCategorias.add(categoria);
		}
		close();
		return listaCategorias;
	}

	public List<Categoria> findAll(Integer pagina) throws Exception {
		open();
		
		if(pagina == 1){
			stmt = con.prepareStatement("select idcategoria, desccategoria, id_tipo, id_prioridade from categoria limit 10");
		}else{
			pagina--;
			pagina *= 10;
			stmt = con.prepareStatement("select idcategoria, desccategoria, id_tipo, id_prioridade from categoria limit " + pagina + ",10");
		}
		
		ResultSet rs = stmt.executeQuery();
		TipoDao tipoDao = new TipoDao();
		PrioridadeDao prioridadeDao = new PrioridadeDao();
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		while (rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(rs.getInt("idcategoria"));
			categoria.setDescCategoria(rs.getString("desccategoria"));
			categoria.setTipo(		tipoDao.findByCod(rs.getInt("id_tipo"))		);
			categoria.setPrioridade(		prioridadeDao.findByCode(rs.getInt("id_prioridade"))		);
			listaCategoria.add(categoria);
		}
		close();
		return listaCategoria;
	}
	
	public Categoria finByCode(Integer id) throws Exception {
		open();
		stmt = con.prepareStatement("select idcategoria, desccategoria, id_tipo, id_prioridade from categoria WHERE idcategoria = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Categoria categoria = new Categoria();
		if (rs.next()) {
			categoria.setIdCategoria(rs.getInt("idcategoria"));
			categoria.setDescCategoria(rs.getString("desccategoria"));
			categoria.setTipo( tipoDao.findByCod( rs.getInt("id_tipo") ));
			categoria.setPrioridade( prioridadeDao.findByCode( rs.getInt("id_prioridade") ));
		}
		close();
		return categoria;
	}
	

	public List<Categoria> finByTipo(Integer id) throws Exception {
		open();
		stmt = con.prepareStatement("SELECT DISTINCT DESCCATEGORIA FROM TIPO, CATEGORIA C WHERE C.ID_TIPO = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		while (rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(rs.getInt("idcategoria"));
			categoria.setDescCategoria(rs.getString("desccategoria"));
			listaCategoria.add(categoria);
		}
		close();
		return listaCategoria;
	}
	
}
