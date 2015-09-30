package Entity;

public class Departamento implements Comparable<Departamento> {

	private Integer idDepto;
	private String descDepto;

	/* private Boolean deptoAtivo; */

	@Override
	public String toString() {
		return "Departamento [idDepto=" + idDepto + ", descDepto=" + descDepto
				+ "]\n";
	}

	public Integer getIdDepto() {
		return idDepto;
	}

	public void setIdDepto(Integer idDepto) {
		this.idDepto = idDepto;
	}

	public String getDescDepto() {
		return descDepto;
	}

	public void setDescDepto(String descDepto) {
		this.descDepto = descDepto;
	}

	/*
	 * public Boolean getDeptoAtivo() { return deptoAtivo; }
	 * 
	 * public void setDeptoAtivo(Boolean deptoAtivo) { this.deptoAtivo =
	 * deptoAtivo; }
	 */

	@Override
	public boolean equals(Object obj) {
		return this.descDepto.equals(((Departamento) obj).getDescDepto());
	}

	public int compareTo(Departamento departamento) {
		return this.descDepto.compareTo(departamento.getDescDepto());
	}

}
