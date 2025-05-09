package app.model.veicolo;


public class StatoVeicolo {
    private boolean attivo;          // Sta percorrendo la rotta
    private boolean danneggiato;     // Nel deposito ma deve essere mandato all'officina
    private boolean deposito;    // Semplicemente nel deposito ma tutto il rsto false
    private boolean inMiglioramento; // In officina che aspetta il tempo calcolato per miglioramento
    private boolean inRiparazione;   // In officina che aspetta il tempo calcolato per riparazione
    private boolean inOfficina;      // Quando è solo in officina e aspetta il match con meccanico
    private boolean assegnato;       // Quando è assegnato a una rotta ma non è ancora attivo

    /**
     * Costruttore che inizializza lo stato di default come "deposito".
     */
    public StatoVeicolo() {
        this.deposito= true;
        this.attivo = false;
        this.danneggiato = false;
        this.inMiglioramento = false;
        this.inRiparazione = false;
        this.inOfficina = false;
        this.assegnato = false;
    }

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public boolean isDanneggiato() {
		return danneggiato;
	}

	public void setDanneggiato(boolean danneggiato) {
		this.danneggiato = danneggiato;
	}

	public boolean isDeposito() {
		return deposito;
	}

	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}

	public boolean isInMiglioramento() {
		return inMiglioramento;
	}

	public void setInMiglioramento(boolean inMiglioramento) {
		this.inMiglioramento = inMiglioramento;
	}

	public boolean isInRiparazione() {
		return inRiparazione;
	}

	public void setInRiparazione(boolean inRiparazione) {
		this.inRiparazione = inRiparazione;
	}

	public boolean isInOfficina() {
		return inOfficina;
	}

	public void setInOfficina(boolean inOfficina) {
		this.inOfficina = inOfficina;
	}

	public boolean isAssegnato() {
		return assegnato;
	}

	public void setAssegnato(boolean assegnato) {
		this.assegnato = assegnato;
	}

    
   
}