package app.model.veicolo;

import app.model.livello.LivelloMiglioramenti;
import app.model.rotta.Rotta;
import app.model.impiegato.*;

public class Veicolo {
    private double velocitaBase; // km al secondo
    private int postiBase;
    private String modello;
    private double prezzoVeicolo;
    private String targa;
    private Impiegato impiegatoAssegnato;
    private Rotta rottaAssegnata;
    private LivelloMiglioramenti livelloMiglioramenti;
    private StatoVeicolo statoVeicolo;
    private int livelloVeicolo; // 1-12 per determinare i requisiti di acquisto
    
    public Veicolo(String modello, double prezzoVeicolo, double velocitaBase, int postiBase, int livelloVeicolo,String targa) {
        this.modello = modello;
        this.prezzoVeicolo = prezzoVeicolo;
        this.velocitaBase = velocitaBase;
        this.postiBase = postiBase;
        this.livelloVeicolo = livelloVeicolo;
        this.statoVeicolo = new StatoVeicolo();
        this.livelloMiglioramenti = new LivelloMiglioramenti();
        this.targa=targa;
        this.impiegatoAssegnato=null;
        this.rottaAssegnata=null;
    }
    
    public Impiegato getImpiegatoAssegnato() {
		return impiegatoAssegnato;
	}

	public void setImpiegatoAssegnato(Impiegato impiegatoAssegnato) {
		this.impiegatoAssegnato = impiegatoAssegnato;
	}
	

	public double getVelocitaBase() {
		return velocitaBase;
	}


	public void setVelocitaBase(double velocitaBase) {
		this.velocitaBase = velocitaBase;
	}


	public int getPostiBase() {
		return postiBase;
	}


	public void setPostiBase(int postiBase) {
		this.postiBase = postiBase;
	}


	public StatoVeicolo getStatoVeicolo() {
		return statoVeicolo;
	}


	public void setStatoVeicolo(StatoVeicolo statoVeicolo) {
		this.statoVeicolo = statoVeicolo;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public void setPrezzoVeicolo(double prezzoVeicolo) {
		this.prezzoVeicolo = prezzoVeicolo;
	}


	public void setLivelloMiglioramenti(LivelloMiglioramenti livelloMiglioramenti) {
		this.livelloMiglioramenti = livelloMiglioramenti;
	}


	public void setLivelloVeicolo(int livelloVeicolo) {
		this.livelloVeicolo = livelloVeicolo;
	}


	public void setTarga(String targa) {
        this.targa = targa;
    }
    
    public String getTarga() {
        return targa;
    }
    
    public String getModello() {
        return modello;
    }
    
    public double getPrezzoVeicolo() {
        return prezzoVeicolo;
    }
    
    public int getLivelloVeicolo() {
        return livelloVeicolo;
    }
    
    public double getVelocitaEffettiva() {
        return velocitaBase * livelloMiglioramenti.getFattoreVelocita();
    }
    
    public int getPostiEffettivi() {
        return postiBase + livelloMiglioramenti.getBonusPosti();
    }
    
    public void assegnaImpiegato(Impiegato impiegato) {
        this.impiegatoAssegnato = impiegato;
        impiegato.assegnaVeicolo(this);
    }
    
    public void rilasciaImpiegato() {
        if (impiegatoAssegnato != null) {
            impiegatoAssegnato.rilasciaVeicolo();
            impiegatoAssegnato = null;
        }
    }
    
    
    
    
    public Rotta getRottaAssegnata() {
		return rottaAssegnata;
	}

	public void setRottaAssegnata(Rotta rottaAssegnata) {
		this.rottaAssegnata = rottaAssegnata;
	}

	public void assegnaRotta(Rotta rotta) {
        this.rottaAssegnata = rotta;
    }
    
    public void rilasciaRotta() {
        this.rottaAssegnata = null;
    }
    
    // Metodo per ottenere l'oggetto StatoVeicolo completo
    public StatoVeicolo getStato() {
        return statoVeicolo;
    }
    
    // Metodi di stato del veicolo - getter
    public boolean isAttivo() {
        return statoVeicolo.isAttivo();
    }
    
    public boolean isDanneggiato() {
        return statoVeicolo.isDanneggiato();
    }
    
    public boolean isDeposito() {
        return statoVeicolo.isDeposito();
    }
    
    public boolean isInMiglioramento() {
        return statoVeicolo.isInMiglioramento();
    }
    
    public boolean isInRiparazione() {
        return statoVeicolo.isInRiparazione();
    }
    
    public boolean isInOfficina() {
        return statoVeicolo.isInOfficina();
    }
    
    public boolean isAssegnato() {
        return statoVeicolo.isAssegnato();
    }
    
    // Metodi di stato del veicolo - setter
    public void setAttivo(boolean attivo) {
        statoVeicolo.setAttivo(attivo);
    }
    
    public void setDanneggiato(boolean danneggiato) {
        statoVeicolo.setDanneggiato(danneggiato);
    }
    
    public void setDeposito(boolean deposito) {
        statoVeicolo.setDeposito(deposito);
    }
    
    public void setInMiglioramento(boolean inMiglioramento) {
        statoVeicolo.setInMiglioramento(inMiglioramento);
    }
    
    public void setInRiparazione(boolean inRiparazione) {
        statoVeicolo.setInRiparazione(inRiparazione);
    }
    
    public void setInOfficina(boolean inOfficina) {
        statoVeicolo.setInOfficina(inOfficina);
    }
    
    public void setAssegnato(boolean assegnato) {
        statoVeicolo.setAssegnato(assegnato);
    }
    
    public boolean isDisponibile() {
        return statoVeicolo.isDeposito() && !statoVeicolo.isAttivo() && 
               !statoVeicolo.isDanneggiato() && !statoVeicolo.isInMiglioramento() && 
               !statoVeicolo.isInRiparazione() && !statoVeicolo.isInOfficina() && 
               !statoVeicolo.isAssegnato();
    }
    
    public LivelloMiglioramenti getLivelloMiglioramenti() {
        return livelloMiglioramenti;
    }
    
    public boolean miglioraPosti(double denaro) {
        return livelloMiglioramenti.miglioraPosti(denaro);
    }
    
    public boolean miglioraVelocita(double denaro) {
        return livelloMiglioramenti.miglioraVelocita(denaro);
    }
    
    public boolean puoMigliorare(String tipoMiglioramento, double denaro) {
        if (tipoMiglioramento.equalsIgnoreCase("velocita")) {
            return livelloMiglioramenti.puoMigliorareVelocita(denaro);
        } else if (tipoMiglioramento.equalsIgnoreCase("posti")) {
            return livelloMiglioramenti.puoMigliorarePosti(denaro);
        }
        return false;
    }
    
    public double getCostoMiglioramento(String tipoMiglioramento) {
        if (tipoMiglioramento.equalsIgnoreCase("velocita")) {
            return livelloMiglioramenti.getCostoMiglioramentoVelocita();
        } else if (tipoMiglioramento.equalsIgnoreCase("posti")) {
            return livelloMiglioramenti.getCostoMiglioramentoPosti();
        }
        return Double.MAX_VALUE;
    }
    
    
    
    public boolean migliora(String tipoMiglioramento, double denaro) {
        if (tipoMiglioramento.equalsIgnoreCase("velocita")) {
            return livelloMiglioramenti.miglioraVelocita(denaro);
        } else if (tipoMiglioramento.equalsIgnoreCase("posti")) {
            return livelloMiglioramenti.miglioraPosti(denaro);
        }
        return false;
    }
    
    public double calcolaTempoPer(double km) {
        return km / getVelocitaEffettiva();
    }

	@Override
	public String toString() {
		return "Veicolo [velocitaBase=" + velocitaBase + ", postiBase=" + postiBase + ", modello=" + modello
				+ ", prezzoVeicolo=" + prezzoVeicolo + ", targa=" + targa + ", impiegatoAssegnato=" + impiegatoAssegnato
				+ ", rottaAssegnata=" + rottaAssegnata + ", livelloMiglioramenti=" + livelloMiglioramenti
				+ ", statoVeicolo=" + statoVeicolo + ", livelloVeicolo=" + livelloVeicolo + "]";
	}
    
    
}