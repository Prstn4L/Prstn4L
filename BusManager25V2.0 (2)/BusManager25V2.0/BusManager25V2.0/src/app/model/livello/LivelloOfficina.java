package app.model.livello;

public class LivelloOfficina extends Livello {
    @Override
    void inizializzaSoglie() {
        soglieAvanzamento.put(2, 3000.0);
        soglieAvanzamento.put(3, 9000.0);
        soglieAvanzamento.put(4, 20000.0);
        soglieAvanzamento.put(5, 45000.0);
        soglieAvanzamento.put(6, 100000.0);
        soglieAvanzamento.put(7, 200000.0);
        soglieAvanzamento.put(8, 400000.0);
        soglieAvanzamento.put(9, 800000.0);
        soglieAvanzamento.put(10, 1600000.0);
        soglieAvanzamento.put(11, 3000000.0);
        soglieAvanzamento.put(12, 6000000.0);
    }
    
    public int getPostiDisponibili() {
        return livelloAttuale + 1; // 2 posti al livello 1, +1 per ogni livello
    }
}