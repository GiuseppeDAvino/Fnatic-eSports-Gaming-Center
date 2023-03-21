package model;

public class TesseraBean {
	private int codice;
	private int saldo;
	private String tipo;

	private String email_utente;
	
	public TesseraBean() {
		codice = 0;
		saldo = 0;
		tipo = "";
		email_utente = "";
	}

	public String toString() {
		return "TesseraBean [codice=" + codice + ", saldo=" + saldo + ", tipo=" + tipo + ", email_utente="
				+ email_utente + "]";
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail_utente() {
		return email_utente;
	}

	public void setEmail_utente(String email_utente) {
		this.email_utente = email_utente;
	}

	
	
}
