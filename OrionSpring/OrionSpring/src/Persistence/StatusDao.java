package Persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import Entity.Status;

@Component
public class StatusDao extends Dao {

	public List<Status> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select idStatus, descStatus from status");
		ResultSet rs = stmt.executeQuery();

		List<Status> listaStatus = new ArrayList<Status>();
		while (rs.next()) {
			Status status = new Status();
			status.setIdStatus(rs.getInt("idStatus"));
			status.setDescStatus(rs.getString("descStatus"));
			listaStatus.add(status);
		}
		close();
		return listaStatus;
	}
	
	public void create(Status status) throws Exception {
		open();
		stmt = con.prepareStatement("insert into status values(null, ?)");
		stmt.setString(1, status.getDescStatus());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public void delete(Status status) throws Exception {
		open();
		stmt = con.prepareStatement("delete from status where idStatus = ?");
		stmt.setInt(1, status.getIdStatus());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public void update(Status status) throws Exception {
		open();
		stmt = con.prepareStatement("update status set descStatus = ? where idStatus = ?");
		stmt.setString(1, status.getDescStatus());
		stmt.setInt(2, status.getIdStatus());
		stmt.execute();
		stmt.close();
		close();
	}
	
	public Status findByCod(Integer codStatus)throws Exception {
		open();
		stmt = con.prepareStatement("select * from status where idStatus = ?");
		stmt.setInt(1, codStatus);

		ResultSet rs = stmt.executeQuery();

		Status statusAchado = new Status();
		if (rs.next()) {
			statusAchado.setIdStatus(rs.getInt("idStatus"));
			statusAchado.setDescStatus(rs.getString("descStatus"));
		}
		close();
		return statusAchado;
	}
	
	
}
