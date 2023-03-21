package model;

public class PostazioneBean {


	private int n_posto;
	private String tipo;
	private String nome_postazione;
	private int costo;
	private String descrizione;
	private int codice_sala;
	private String img;
	

	public PostazioneBean() {
		n_posto = -1;
		tipo = "";
		nome_postazione = "";
		costo = -1;
		descrizione = "";
		codice_sala = -1;
	}

	public String getNome_postazione() {
		return nome_postazione;
	}

	public void setNome_postazione(String nome_postazione) {
		this.nome_postazione = nome_postazione;
	}

	public String toString() {
		return "PostazioneBean [n_posto=" + n_posto + ", tipo=" + tipo + ", costo=" + costo + ", descrizione="
				+ descrizione + ", codice_sala=" + codice_sala + ", img=" + img + "]";
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getN_posto() {
		return n_posto;
	}

	public void setN_posto(int n_posto) {
		this.n_posto = n_posto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCodice_sala() {
		return codice_sala;
	}

	public void setCodice_sala(int codice_sala) {
		this.codice_sala = codice_sala;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
