package app.model.impiegato;
import app.model.livello.*;

public class Meccanico extends Impiegato {
    private LivelloMeccanico livello;
    private double tempoAzione; // in secondi
    private double ultimoCostoVeicolo;
    
    public Meccanico(String nome, String cognome) {
        super(nome, cognome, 0.05); // 5% del costo del veicolo riparato/migliorato
        this.livello = new LivelloMeccanico();
        this.tempoAzione = 60.0; // 60 secondi base per riparare
        this.ultimoCostoVeicolo = 0.0;
    }
    
    public LivelloMeccanico getLivello() {
        return livello;
    }
    
    public double getTempoAzione() {
        // Tempo ridotto in base al livello
        return tempoAzione / livello.getFattoreVelocitaRiparazione();
    }
    
    public void setUltimoCostoVeicolo(double costo) {
        this.ultimoCostoVeicolo = costo;
    }
    
    @Override
    public double pagaStipendio() {
        double stipendio = ultimoCostoVeicolo * getStipendioPercentuale();
        ultimoCostoVeicolo = 0.0;
        return stipendio;
    }
}