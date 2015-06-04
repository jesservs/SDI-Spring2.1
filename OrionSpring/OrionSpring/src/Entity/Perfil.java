package Entity;

import java.io.Serializable;

public class Perfil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8153351569174930917L;
	private Integer 	idPerfil;
	private String  	nomePerfil;

	public Perfil() {
		// TODO Auto-generated constructor stub
	}
	
	public Perfil(Integer idPerfil, String nomePerfil) {
		super();
		this.idPerfil = idPerfil;
		this.nomePerfil = nomePerfil;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	
}
