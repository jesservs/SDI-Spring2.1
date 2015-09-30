package Entity;


public class Prioridade {

	private Integer idPrioridade;
	private String descPrioridade;
	private Integer tempoResolucao;

	public Integer getIdPrioridade() {
		return idPrioridade;
	}

	@Override
	public String toString() {
		return "Prioridade [idPrioridade=" + idPrioridade + ", descPrioridade="
				+ descPrioridade + ", tempoResolucao=" + tempoResolucao + "]";
	}

	public void setIdPrioridade(Integer idPrioridade) {
		this.idPrioridade = idPrioridade;
	}

	public String getDescPrioridade() {
		return descPrioridade;
	}

	public void setDescPrioridade(String descPrioridade) {
		this.descPrioridade = descPrioridade;
	}

	public Integer getTempoResolucao() {
		return tempoResolucao;
	}

	public void setTempoResolucao(Integer tempoResolucao) {
		this.tempoResolucao = tempoResolucao;
	}
	
}
