package app.model.impiegato;

import app.model.livello.LivelloAutista;

public class Autista extends Impiegato {
    private LivelloAutista livello;
    private double ultimoGuadagnoRotta;

    /**
     * Costruttore della classe Autista
     * 
     * @param nome    Nome dell'autista
     * @param cognome Cognome dell'autista
     */
    public Autista(String nome, String cognome) {
        super(nome, cognome, 0.10); // 10% base della rotta come stipendio percentuale
        this.livello = new LivelloAutista();
        this.ultimoGuadagnoRotta = 0.0;
    }

    /**
     * Restituisce l'oggetto che gestisce il livello dell'autista
     * 
     * @return Oggetto LivelloAutista
     */
    public LivelloAutista getLivello() {
        return livello;
    }

    /**
     * Restituisce la percentuale di stipendio dell'autista
     * Questa percentuale viene applicata al guadagno della rotta
     * 
     * @return Percentuale di stipendio (0-1)
     */
    @Override
    public double getStipendioPercentuale() {
        // Chiamiamo il metodo della classe padre per evitare la ricorsione infinita
        return super.getStipendioPercentuale();
    }

    /**
     * Imposta l'ultimo guadagno generato dalla rotta per il calcolo dello stipendio
     * 
     * @param guadagno Guadagno generato dall'ultima rotta completata
     */
    public void setUltimoGuadagnoRotta(double guadagno) {
        this.ultimoGuadagnoRotta = guadagno;
    }

    /**
     * Calcola e restituisce lo stipendio dell'autista basato sull'ultimo guadagno
     * della rotta e sulla percentuale di stipendio
     * 
     * @return Importo dello stipendio
     */
    @Override
    public double pagaStipendio() {
        double stipendio = ultimoGuadagnoRotta * getStipendioPercentuale();
        ultimoGuadagnoRotta = 0.0; // Reset dopo il pagamento
        return stipendio;
    }

    /**
     * Restituisce il bonus di guadagno basato sul livello dell'autista
     * 
     * @return Bonus di guadagno (moltiplicatore)
     */
    public double getBonusGuadagno() {
        return livello.getBonusGuadagno();
    }

    /**
     * Restituisce il valore di riduzione della probabilità di rottura del veicolo
     * basato sul livello dell'autista
     * 
     * @return Riduzione della probabilità di rottura (0-1)
     */
    public double getRiduzioneProbabilitaRottura() {
        return livello.getRiduzioneProbabilitaRottura();
    }
    
    /**
     * Restituisce l'ultimo guadagno generato dalla rotta
     * 
     * @return Ultimo guadagno della rotta
     */
    public double getUltimoGuadagnoRotta() {
        return ultimoGuadagnoRotta;
    }
}