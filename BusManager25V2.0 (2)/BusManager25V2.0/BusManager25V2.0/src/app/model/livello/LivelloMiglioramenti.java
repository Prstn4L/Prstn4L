package app.model.livello;

import java.util.HashMap;

public class LivelloMiglioramenti {
    private HashMap<Integer, Double> soglieVelocita;
    private HashMap<Integer, Double> sogliePosti;
    private int livelloVelocita;
    private int livelloPosti;
    private int maxLivello;
    
    public LivelloMiglioramenti() {
        this.livelloVelocita = 1;
        this.livelloPosti = 1;
        this.maxLivello = 5;
        this.soglieVelocita = new HashMap<>();
        this.sogliePosti = new HashMap<>();
        inizializzaSoglie();
    }
    
    private void inizializzaSoglie() {
        // Costi per migliorare la velocità
        soglieVelocita.put(2, 1000.0);
        soglieVelocita.put(3, 2500.0);
        soglieVelocita.put(4, 5000.0);
        soglieVelocita.put(5, 10000.0);
        
        // Costi per migliorare i posti
        sogliePosti.put(2, 1500.0);
        sogliePosti.put(3, 3000.0);
        sogliePosti.put(4, 6000.0);
        sogliePosti.put(5, 12000.0);
    }
    
    public boolean puoMigliorareVelocita(double denaro) {
        return livelloVelocita < maxLivello && denaro >= soglieVelocita.getOrDefault(livelloVelocita + 1, Double.MAX_VALUE);
    }
    
    public boolean puoMigliorarePosti(double denaro) {
        return livelloPosti < maxLivello && denaro >= sogliePosti.getOrDefault(livelloPosti + 1, Double.MAX_VALUE);
    }
    
    public double getCostoMiglioramentoVelocita() {
        return soglieVelocita.getOrDefault(livelloVelocita + 1, Double.MAX_VALUE);
    }
    
    public double getCostoMiglioramentoPosti() {
        return sogliePosti.getOrDefault(livelloPosti + 1, Double.MAX_VALUE);
    }
    
    public boolean miglioraVelocita(double denaro) {
        if (livelloVelocita >= maxLivello) {
            return false;
        }
        
        if (denaro >= soglieVelocita.get(livelloVelocita + 1)) {
            livelloVelocita++;
            return true;
        }
        return false;
    }
    
    public boolean miglioraPosti(double denaro) {
        if (livelloPosti >= maxLivello) {
            return false;
        }
        
        if (denaro >= sogliePosti.get(livelloPosti + 1)) {
            livelloPosti++;
            return true;
        }
        return false;
    }
    
    public int getLivelloVelocita() {
        return livelloVelocita;
    }
    
    public int getLivelloPosti() {
        return livelloPosti;
    }
    
    // Applicare i miglioramenti ai parametri effettivi del veicolo
    public double getFattoreVelocita() {
        return 1.0 + (livelloVelocita - 1) * 0.2; // +20% per livello
    }
    
    public int getBonusPosti() {
        return (livelloPosti - 1) * 5; // +5 posti per livello
    }
}
