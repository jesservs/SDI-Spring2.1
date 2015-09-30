package Entity;


public class Tipo {
	
	private Integer idTipo;
	private String descTipo;
	
	public Integer getIdTipo() {
		return idTipo;
	}
	
	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", descTipo=" + descTipo + "]";
	}
	
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public String getDescTipo() {
		return descTipo;
	}
	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}

}
