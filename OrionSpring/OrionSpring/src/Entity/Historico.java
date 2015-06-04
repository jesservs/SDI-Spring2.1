package Entity;

import java.util.Date;

public class Historico {
	
	private Integer idHistorico;
	private String dtHrAbertura;
	private String dtHrFechamento;
	private String dtHrSolucao;
	private String dtHrAlteracao;
	private String descChamado;
	private Status status;
	private Usuario responsavel;
	private Usuario solicitante;
	private Usuario atendente;
	private Categoria categoria;
	private Prioridade novaPrioridade;
	private Chamado chamado;
	
	public Historico() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
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

	public String getDtHrSolucao() {
		return dtHrSolucao;
	}

	public void setDtHrSolucao(String dtHrSolucao) {
		this.dtHrSolucao = dtHrSolucao;
	}

	public String getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(String dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	public String getDescChamado() {
		return descChamado;
	}

	public void setDescChamado(String descChamado) {
		this.descChamado = descChamado;
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

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	
}
