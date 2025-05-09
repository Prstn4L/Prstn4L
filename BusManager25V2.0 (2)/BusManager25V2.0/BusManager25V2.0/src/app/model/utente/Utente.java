package app.model.utente;


import app.model.livello.LivelloUtente;

/**
 * Classe che rappresenta l'utente/giocatore nel gioco BusManager25.
 * Gestisce i dati personali, il saldo, il livello e le statistiche di gioco.
 */
public class Utente {
    private String nome;
    private double saldo;
    private LivelloUtente livello;
    private int giorniGiocati;
    private double guadagnoTotale;
    private double spesaTotale;

    /**
     * Costruttore della classe Utente
     * 
     * @param nome Nome dell'utente
     * @param saldoIniziale Saldo iniziale dell'utente
     */
    public Utente(String nome, double saldoIniziale) {
        this.nome = nome;
        this.saldo = saldoIniziale;
        this.livello = new LivelloUtente();
        this.giorniGiocati = 0;
        this.guadagnoTotale = 0;
        this.spesaTotale = 0;
    }

    /**
     * Aggiunge denaro al saldo dell'utente e aggiorna il guadagno totale
     * 
     * @param importo Importo da aggiungere
     */
    public void aggiungiDenaro(double importo) {
        if (importo > 0) {
            this.saldo += importo;
            this.guadagnoTotale += importo;
        }
    }

    /**
     * Sottrae denaro dal saldo dell'utente e aggiorna la spesa totale
     * 
     * @param importo Importo da sottrarre
     * @return true se l'operazione è riuscita, false se il saldo è insufficiente
     */
    public boolean sottraiDenaro(double importo) {
        if (importo <= 0) {
            return false;
        }
        
        if (this.saldo >= importo) {
            this.saldo -= importo;
            this.spesaTotale += importo;
            return true;
        }
        
        return false;
    }

    /**
     * Incrementa il contatore dei giorni giocati
     */
    public void incrementaGiorniGiocati() {
        this.giorniGiocati++;
    }

    /**
     * Verifica se l'utente può permettersi un determinato importo
     * 
     * @param importo Importo da verificare
     * @return true se l'utente ha saldo sufficiente, false altrimenti
     */
    public boolean puoPermettere(double importo) {
        return this.saldo >= importo;
    }

    /**
     * Calcola il bilancio (guadagno - spesa)
     * 
     * @return Bilancio totale
     */
    public double calcolaBilancio() {
        return this.guadagnoTotale - this.spesaTotale;
    }

    /**
     * Restituisce il nome dell'utente
     * 
     * @return Nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente
     * 
     * @param nome Nuovo nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il saldo dell'utente
     * 
     * @return Saldo dell'utente
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Imposta il saldo dell'utente
     * 
     * @param saldo Nuovo saldo dell'utente
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Restituisce il livello dell'utente
     * 
     * @return Livello dell'utente
     */
    public LivelloUtente getLivello() {
        return livello;
    }

    /**
     * Imposta il livello dell'utente
     * 
     * @param livello Nuovo livello dell'utente
     */
    public void setLivello(LivelloUtente livello) {
        this.livello = livello;
    }

    /**
     * Restituisce il numero di giorni giocati
     * 
     * @return Giorni giocati
     */
    public int getGiorniGiocati() {
        return giorniGiocati;
    }

    /**
     * Imposta il numero di giorni giocati
     * 
     * @param giorniGiocati Nuovo numero di giorni giocati
     */
    public void setGiorniGiocati(int giorniGiocati) {
        this.giorniGiocati = giorniGiocati;
    }

    /**
     * Restituisce il guadagno totale
     * 
     * @return Guadagno totale
     */
    public double getGuadagnoTotale() {
        return guadagnoTotale;
    }

    /**
     * Imposta il guadagno totale
     * 
     * @param guadagnoTotale Nuovo guadagno totale
     */
    public void setGuadagnoTotale(double guadagnoTotale) {
        this.guadagnoTotale = guadagnoTotale;
    }

    /**
     * Restituisce la spesa totale
     * 
     * @return Spesa totale
     */
    public double getSpesaTotale() {
        return spesaTotale;
    }

    /**
     * Imposta la spesa totale
     * 
     * @param spesaTotale Nuova spesa totale
     */
    public void setSpesaTotale(double spesaTotale) {
        this.spesaTotale = spesaTotale;
    }
}