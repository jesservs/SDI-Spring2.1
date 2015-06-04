package modelo.sistema;

public class Perfil {

	private Integer idPerfil;
	private String nomePerfil;

	public Perfil() {

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
