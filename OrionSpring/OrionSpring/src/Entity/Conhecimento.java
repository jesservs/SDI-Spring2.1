package Entity;

import java.util.Date;

public class Conhecimento {
	
	private Integer idConhecimento;
	private String descConhecimento;
	private Categoria categoria;
	private Usuario responsavel;
	private Date dtHrUltAlteracao;
	
	public Conhecimento() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdConhecimento() {
		return idConhecimento;
	}

	public void setIdConhecimento(Integer idConhecimento) {
		this.idConhecimento = idConhecimento;
	}

	public String getDescConhecimento() {
		return descConhecimento;
	}

	public void setDescConhecimento(String descConhecimento) {
		this.descConhecimento = descConhecimento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDtHrUltAlteracao() {
		return dtHrUltAlteracao;
	}

	public void setDtHrUltAlteracao(Date dtHrUltAlteracao) {
		this.dtHrUltAlteracao = dtHrUltAlteracao;
	}
	
	

}
