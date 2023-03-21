package model;


public class SalaBean {
	private int codice_sala;
	private int capacita;
	
	public SalaBean() {
		codice_sala = -1;
		capacita = -1;
	}

	public int getCodice_sala() {
		return codice_sala;
	}

	public void setCodice_sala(int codice_sala) {
		this.codice_sala = codice_sala;
	}

	public int getCapacita() {
		return capacita;
	}

	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}
	
}
