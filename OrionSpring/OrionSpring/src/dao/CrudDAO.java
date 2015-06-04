package dao;

import java.util.List;

public interface CrudDAO<T> {
	
	public List<T> findAll() throws Exception;
	
	public T findByCod(Integer idT) throws Exception;
	
	public void cadastrar(T entidade) throws Exception;
	
	public void alterar(T entidade) throws Exception;
	
	public void delete(T entidade) throws Exception;
}
