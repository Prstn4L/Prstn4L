package app.model.rotta;

import app.model.impiegato.Autista;
import app.model.veicolo.Veicolo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Rotta implements Runnable {
    private String nome;
    private double kilometri;
    private int indiceAffollamento;
    private int livelloRotta;
    private Veicolo veicolo; // Riferimento diretto al veicolo assegnato alla rotta
    private boolean percorsa;
    private AtomicBoolean inEsecuzione;
    private Thread threadPercorrenza;
    private double probabilitaRottura;
    private double guadagnoBase;
    private Random random;

    /**
     * Costruttore della classe Rotta
     * 
     * @param nome               Nome della rotta
     * @param kilometri          Distanza in km della rotta
     * @param indiceAffollamento Numero minimo di posti richiesti per il veicolo
     * @param livello       Oggetto che gestisce il livello della rotta
     * @param guadagnoBase       Guadagno base per la rotta
     */
    public Rotta(String nome, double kilometri, int indiceAffollamento, int livello,
            double guadagnoBase) {
        this.nome = nome;
        this.kilometri = kilometri;
        this.indiceAffollamento = indiceAffollamento;
        this.livelloRotta = livello;
        this.guadagnoBase = guadagnoBase;
        this.veicolo = null;
        this.percorsa = false;
        this.inEsecuzione = new AtomicBoolean(false);
        this.random = new Random();
    }

    // assegno un veicolo alla rotta, passata la targa
    public boolean assegnaVeicolo(String targa,ArrayList<Veicolo> veicoli) {
    	for(Veicolo veicolo:veicoli) {
    		if(veicolo.getTarga().equals(targa)) {
    			if (veicolo == null || !veicolo.isDisponibile() || veicolo.getPostiEffettivi() < indiceAffollamento) {
                    return false;
                }

                if (this.veicolo != null) {
                    // Se c'è già un veicolo assegnato, lo dissociamo prima
                    dissociaVeicolo(this.veicolo);
                }

                this.veicolo = veicolo;
                veicolo.assegnaRotta(this);
                veicolo.setAssegnato(true);
                veicolo.setDeposito(false);

                return true;
    		}
    	}
    	return false;
        
    }

    // assegno un autista alla rotta tramite id
    public boolean assegnaAutista(String id,ArrayList<Autista> autisti) {
    	for(Autista a:autisti) {
    		if(a.getId().equals(id)) {
    			
    			if (a == null || this.veicolo == null) {
    	            return false;
    	        }

    	        this.veicolo.assegnaImpiegato(a);
    	        return true;
    		}
    	}
    	return false;
        
    }

    // dissocio il veicolo dalla rotta
    public void dissociaVeicolo(Veicolo veicolo) {
        if (veicolo != null && this.veicolo == veicolo) {
            veicolo.rilasciaRotta();
            veicolo.setAssegnato(false);
            veicolo.setDeposito(true);
            this.veicolo = null;
        }
    }

    // dissocio l'autista dalla rotta
    public void dissociaAutista() {
        if (this.veicolo != null && this.veicolo.getImpiegatoAssegnato() != null) {
            this.veicolo.rilasciaImpiegato();
        }
    }

    
    private double calcolaProbabilitaRottura(Veicolo veicolo) {
        // Probabilità base di rottura dipende dal livello della rotta (rotte basse = più probabilità)
        double probabilitaBase = 0.5 - (livelloRotta * 0.03);
        // Riduzione della probabilità in base all'autista
        double riduzioneAutista = 0;
        if (veicolo.getImpiegatoAssegnato() instanceof Autista) {
            Autista autista = (Autista) veicolo.getImpiegatoAssegnato();
            riduzioneAutista = autista.getRiduzioneProbabilitaRottura();
        }

        // La probabilità finale è tra 0.01 e 0.5
        return Math.max(0.01, Math.min(0.5, probabilitaBase - riduzioneAutista));
    }

    public boolean avviaRotta() {
        // Controllo se la rotta è già in corso
        if (percorsa || inEsecuzione.get()) {
            return false;
        }

        // Controllo se c'è un veicolo valido con un autista
        if (veicolo != null && veicolo.getImpiegatoAssegnato() instanceof Autista
                && veicolo.getPostiEffettivi() >= indiceAffollamento) {

            // Imposta lo stato del veicolo come attivo
            veicolo.setAttivo(true);
            veicolo.setAssegnato(false);

            // Calcoliamo la probabilità di rottura per questo veicolo e autista
            probabilitaRottura = calcolaProbabilitaRottura(veicolo);
            
            // Avvia il thread per la percorrenza
            percorsa = true;
            inEsecuzione.set(true);
            threadPercorrenza = new Thread(this);
            threadPercorrenza.start();

            return true;
        }

        return false;
    }

    /**
     * Gestisce la rottura di un veicolo durante la percorrenza
     * Aggiorna lo stato del veicolo a danneggiato, lo invia al deposito
     * e interrompe la rotta corrente
     */
    private void gestisciRottura() {
        if (veicolo != null) {
            // Cambia lo stato del veicolo in danneggiato
            veicolo.setAttivo(false);
            veicolo.setDanneggiato(true);
            
            // Termina la percorrenza della rotta
            percorsa = false;
            inEsecuzione.set(false);

            // Rimuoviamo l'assegnazione di questo veicolo dalla rotta
            dissociaVeicolo(veicolo);
            
            // Interrompiamo il thread di percorrenza
            if (threadPercorrenza != null && threadPercorrenza.isAlive()) {
                threadPercorrenza.interrupt();
            }
        }
    }

    /**
     * Verifica se il veicolo si rompe in base alla probabilità di rottura
     * 
     * @return true se il veicolo si è rotto, false altrimenti
     */
    private boolean verificaRottura() {
        return random.nextDouble() < probabilitaRottura;
    }
    
    
    public double calcolaTempoPercorrenza(Veicolo veicolo) {
        if (veicolo != null) {
            return veicolo.calcolaTempoPer(kilometri);
        }
        return Double.MAX_VALUE;
    }

    /**
     * Calcola il guadagno per la rotta completata
     * 
     * @param veicolo Veicolo utilizzato
     * @return Guadagno totale della rotta
     */
    private double calcolaGuadagnoRotta(Veicolo veicolo) {
        // Il guadagno base viene moltiplicato per il livello della rotta
    	Autista cast = (Autista) veicolo.getImpiegatoAssegnato();
    	// il guadagno è --> guadagno base rotta, + bonus - stipendio del autista
        double guadagno = (guadagnoBase * (1 + (livelloRotta * 0.2)))+cast.getBonusGuadagno()-cast.getStipendioPercentuale();

        // Aggiungiamo il bonus dell'autista se presente
        if (veicolo.getImpiegatoAssegnato() instanceof Autista) {
            Autista autista = (Autista) veicolo.getImpiegatoAssegnato();
            guadagno *= (1 + autista.getBonusGuadagno());
            // Impostiamo l'ultimo guadagno dell'autista per il calcolo dello stipendio
            autista.setUltimoGuadagnoRotta(guadagno);
        }

        return guadagno;
    }

    /**
     * Metodo run eseguito dal thread durante la percorrenza della rotta
     * Implementa la verifica periodica della rottura del veicolo durante il percorso
     */
    @Override
    public void run() {
        // Verifichiamo che ci sia un veicolo attivo
        if (veicolo == null || !veicolo.isAttivo()) {
            inEsecuzione.set(false);
            percorsa = false;
            return;
        }

        // Calcoliamo il tempo di percorrenza
        double tempoPercorrenza = calcolaTempoPercorrenza(veicolo);

        try {
            // Simuliamo il tempo di percorrenza
            long tempoSimulazione = (long) (tempoPercorrenza * 1000); // Conversione in millisecondi

            // Dividiamo il tempo totale in intervalli per controllare eventuali rotture
            int intervalli = 10;
            long intervalloMs = tempoSimulazione / intervalli;

            for (int i = 0; i < intervalli; i++) {
                // Se il thread è stato interrotto, usciamo
                if (Thread.currentThread().isInterrupted()) {
                    inEsecuzione.set(false);
                    percorsa = false;
                    return;
                }

                // Aspettiamo per l'intervallo
                Thread.sleep(intervalloMs);

                // Controlliamo se avviene una rottura
                if (verificaRottura()) {
                    // Il veicolo si è rotto!
                    gestisciRottura();
                    return;
                }
            }

            // La rotta è stata completata con successo
            double guadagno = calcolaGuadagnoRotta(veicolo);
            System.out.println("Rotta " + nome + " completata con successo. Guadagno: " + guadagno);

            // Ripristiniamo lo stato del veicolo
            veicolo.setAttivo(false);
            veicolo.setDeposito(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Percorrenza della rotta " + nome + " interrotta.");
        } finally {
            // Assicuriamoci che la rotta sia contrassegnata come non in corso
            inEsecuzione.set(false);
            percorsa = false;
        }
    }

    public String getNome() {
        return nome;
    }

    public double getKilometri() {
        return kilometri;
    }

    public int getIndiceAffollamento() {
        return indiceAffollamento;
    }

    public int getLivelloRotta() {
        return livelloRotta;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public boolean isPercorsa() {
        return percorsa;
    }

    public boolean isInEsecuzione() {
        return inEsecuzione.get();
    }

    public double getProbabilitaRottura() {
        return probabilitaRottura;
    }

    public double getGuadagnoBase() {
        return guadagnoBase;
    }

    
    // Ferma la percorrenza della rotta se è in esecuzione
    
    public boolean fermaRotta() {
        if (threadPercorrenza != null && threadPercorrenza.isAlive()) {
            threadPercorrenza.interrupt();
        }else {
        	return false;
        }

        // Ripristina lo stato del veicolo se era attivo
        if (veicolo != null && veicolo.isAttivo() && veicolo.getRottaAssegnata() == this) {
            veicolo.setAttivo(false);
            veicolo.setAssegnato(true);
        }

        percorsa = false;
        inEsecuzione.set(false);
        return true;
    }
}