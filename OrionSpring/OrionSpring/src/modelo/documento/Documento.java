package modelo.documento;

import java.util.Map;

public abstract class Documento {

	private String identificador;

	private String nomeDocumento;

	private Map<String, String> glossario;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public Map<String, String> getGlossario() {
		return glossario;
	}

	public void setGlossario(Map<String, String> glossario) {
		this.glossario = glossario;
	}

}
