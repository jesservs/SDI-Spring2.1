package Entity;

public class Qualidade {
	
	private Integer idQualidade;
	private String descQualidade;
	private Integer nota;
	private Usuario solicitante;
	private Chamado chamado;
	
	public Qualidade() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdQualidade() {
		return idQualidade;
	}

	public void setIdQualidade(Integer idQualidade) {
		this.idQualidade = idQualidade;
	}

	public String getDescQualidade() {
		return descQualidade;
	}

	public void setDescQualidade(String descQualidade) {
		this.descQualidade = descQualidade;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	

}
