package app.model.livello;

public class LivelloUtente extends Livello {
    
    @Override
    void inizializzaSoglie() {
        soglieAvanzamento.put(2, 5000.0);
        soglieAvanzamento.put(3, 15000.0);
        soglieAvanzamento.put(4, 30000.0);
        soglieAvanzamento.put(5, 60000.0);
        soglieAvanzamento.put(6, 120000.0);
        soglieAvanzamento.put(7, 240000.0);
        soglieAvanzamento.put(8, 500000.0);
        soglieAvanzamento.put(9, 1000000.0);
        soglieAvanzamento.put(10, 2000000.0);
        soglieAvanzamento.put(11, 4000000.0);
        soglieAvanzamento.put(12, 8000000.0);
    }
    
    // Sbloccano funzionalità specifiche in base al livello
    public boolean puo_comprare_bus_livello(int livelloBus) {
        return livelloAttuale >= livelloBus;
    }
}
