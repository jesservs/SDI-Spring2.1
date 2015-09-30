package Entity;

import java.util.Date;

public class Chamado {
	
	private Integer idChamado;
	private String descChamado;
	private String dtHrAbertura;
	private String dtHrFechamento;
	private Status status;
	private Usuario responsavel;
	private Usuario solicitante;
	private Usuario atendente;
	private Categoria categoria;
	private Prioridade novaPrioridade;
	private Qualidade qualidade;
	private Date dateAbertura;
	private Date dateFechamento;
	private Date dtHrSolucao;
	

	public Date getDtHrSolucao() {
		return dtHrSolucao;
	}
	public void setDtHrSolucao(Date dtHrSolucao) {
		this.dtHrSolucao = dtHrSolucao;
	}
	public Date getDateAbertura() {
		return dateAbertura;
	}
	public void setDateAbertura(Date dateAbertura) {
		this.dateAbertura = dateAbertura;
	}
	public Date getDateFechamento() {
		return dateFechamento;
	}
	public void setDateFechamento(Date dateFechamento) {
		this.dateFechamento = dateFechamento;
	}
	public Qualidade getQualidade() {
		return qualidade;
	}
	public void setQualidade(Qualidade qualidade) {
		this.qualidade = qualidade;
	}
	public Integer getIdChamado() {
		return idChamado;
	}
	public void setIdChamado(Integer idChamado) {
		this.idChamado = idChamado;
	}
	public String getDescChamado() {
		return descChamado;
	}
	public void setDescChamado(String descChamado) {
		this.descChamado = descChamado;
	}
	public String getDtHrAbertura() {
		return dtHrAbertura;
	}
	public void setDtHrAbertura(String dtHrAbertura) {
		this.dtHrAbertura = dtHrAbertura;
	}
	public String getDtHrFechamento() {
		return dtHrFechamento;
	}
	public void setDtHrFechamento(String dtHrFechamento) {
		this.dtHrFechamento = dtHrFechamento;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	public Usuario getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}
	public Usuario getAtendente() {
		return atendente;
	}
	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Prioridade getNovaPrioridade() {
		return novaPrioridade;
	}
	public void setNovaPrioridade(Prioridade novaPrioridade) {
		this.novaPrioridade = novaPrioridade;
	}
	@Override
	public String toString() {
		return "Chamado [idChamado=" + idChamado + ", descChamado="
				+ descChamado + ", dtHrAbertura=" + dtHrAbertura
				+ ", dtHrFechamento=" + dtHrFechamento + ", status=" + status
				+ ", responsavel=" + responsavel + ", solicitante="
				+ solicitante + ", atendente=" + atendente + ", categoria="
				+ categoria + ", novaPrioridade=" + novaPrioridade
				+ ", qualidade=" + qualidade + "]";
	}
	
	
}
