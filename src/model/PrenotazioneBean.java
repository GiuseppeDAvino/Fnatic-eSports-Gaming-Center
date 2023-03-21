package model;

public class PrenotazioneBean {
	private int id_prenotazione;
	private String data_prenotazione;
	private String email_utente;
	private int numero_postazione;
	
	public PrenotazioneBean() {
		id_prenotazione = -1;
		data_prenotazione = "";
		email_utente = "";
		numero_postazione = -1;
	}

	public int getId_prenotazione() {
		return id_prenotazione;
	}

	public void setId_prenotazione(int id_prenotazione) {
		this.id_prenotazione = id_prenotazione;
	}

	public String getData_prenotazione() {
		return data_prenotazione;
	}

	public void setData_prenotazione(String data_prenotazione) {
		this.data_prenotazione = data_prenotazione;
	}

	public String getEmail_utente() {
		return email_utente;
	}

	public void setEmail_utente(String email_utente) {
		this.email_utente = email_utente;
	}

	public int getNumero_postazione() {
		return numero_postazione;
	}

	public void setNumero_postazione(int numero_postazione) {
		this.numero_postazione = numero_postazione;
	}
	
	
}
