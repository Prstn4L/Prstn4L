package app.model.impiegato;

import java.util.Random;
import app.model.veicolo.Veicolo;

public abstract class Impiegato {
    private String nome;
    private String cognome;
    private String id;
    private Veicolo veicoloAssegnato;
    private double stipendioPercentuale;
    private StatoImpiegato stato;

    public Impiegato(String nome, String cognome, double stipendioPercentuale) {
        this.nome = nome;
        this.cognome = cognome;
        this.stipendioPercentuale = stipendioPercentuale;
        this.stato = new StatoImpiegato();
        this.id = generaId();
    }

    private String generaId() {
        String classeLettera = this.getClass().getSimpleName().substring(0, 1);
        String nomeLettera = nome.substring(0, 1);
        String cognomeLettera = cognome.substring(0, 1);
        Random random = new Random();
        int numeroRandom = random.nextInt(100);
        return classeLettera + nomeLettera + cognomeLettera + String.format("%02d", numeroRandom);
    }
    
    
    
    public void assegnaVeicolo(Veicolo veicolo) {
        this.veicoloAssegnato = veicolo;
        setAssegnato();
    }

    public void rilasciaVeicolo() {
        this.veicoloAssegnato = null;
        setRiposo();
    }

    public boolean isDisponibile() {
        return stato.isRiposo();
    }

    // === Metodi per accedere e modificare direttamente lo stato ===
    public void setAttivo() {
        stato.setAttivo();
    }

    public void setAssegnato() {
        stato.setAssegnato();
    }

    public void setRiposo() {
        stato.setRiposo();
    }

    public boolean isAttivo() {
        return stato.isAttivo();
    }

    public boolean isAssegnato() {
        return stato.isAssegnato();
    }

    public boolean isRiposo() {
        return stato.isRiposo();
    }

    // Getter e setter
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getStipendioPercentuale() { return stipendioPercentuale; }
    public void setStipendioPercentuale(double stipendioPercentuale) { this.stipendioPercentuale = stipendioPercentuale; }

    public Veicolo getVeicoloAssegnato() { return veicoloAssegnato; }
    public void setVeicoloAssegnato(Veicolo veicoloAssegnato) { this.veicoloAssegnato = veicoloAssegnato; }

    public StatoImpiegato getStato() { return stato; }
    public void setStato(StatoImpiegato stato) { this.stato = stato; }

    public abstract double pagaStipendio();
}
