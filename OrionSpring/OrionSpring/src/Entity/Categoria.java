package Entity;

public class Categoria {

	private Integer idCategoria;
	private String descCategoria;
	private Tipo tipo;
	private Prioridade prioridade;

	public Categoria() {
		tipo = new Tipo();
		prioridade = new Prioridade();
	}
	
	

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", descCategoria="
				+ descCategoria + ", tipo=" + tipo + ", prioridade="
				+ prioridade + "]" + "\n";
	}



	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

}
