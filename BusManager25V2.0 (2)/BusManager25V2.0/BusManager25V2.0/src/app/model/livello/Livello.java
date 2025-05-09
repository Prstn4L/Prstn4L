package app.model.livello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Abstract class for levels
public abstract class Livello {
    protected int livelloAttuale;
    protected HashMap<Integer, Double> soglieAvanzamento;
    
    public Livello() {
        this.livelloAttuale = 1;
        this.soglieAvanzamento = new HashMap<>();
        inizializzaSoglie();
    }
    
    abstract void inizializzaSoglie();
    
    public boolean puoMigliorare(double denaro) {
        return denaro >= soglieAvanzamento.getOrDefault(livelloAttuale + 1, Double.MAX_VALUE);
    }
    
    public double getCostoMiglioramento() {
        return soglieAvanzamento.getOrDefault(livelloAttuale + 1, Double.MAX_VALUE);
    }
    
    public int getLivelloAttuale() {
        return livelloAttuale;
    }
    
    public boolean miglioraLivello(double denaro) {
        if (livelloAttuale >= soglieAvanzamento.size()) {
            return false; // Livello massimo raggiunto
        }
        
        if (denaro >= soglieAvanzamento.get(livelloAttuale + 1)) {
            livelloAttuale++;
            return true;
        }
        return false;
    }
}
