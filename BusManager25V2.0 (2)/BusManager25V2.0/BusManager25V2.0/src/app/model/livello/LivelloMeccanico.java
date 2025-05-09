package app.model.livello;

public class LivelloMeccanico extends Livello {
    @Override
    void inizializzaSoglie() {
        soglieAvanzamento.put(2, 2000.0);
        soglieAvanzamento.put(3, 6000.0);
        soglieAvanzamento.put(4, 15000.0);
        soglieAvanzamento.put(5, 35000.0);
        soglieAvanzamento.put(6, 80000.0);
        soglieAvanzamento.put(7, 150000.0);
        soglieAvanzamento.put(8, 300000.0);
        soglieAvanzamento.put(9, 600000.0);
        soglieAvanzamento.put(10, 1200000.0);
        soglieAvanzamento.put(11, 2500000.0);
        soglieAvanzamento.put(12, 5000000.0);
    }
    
    public double getFattoreVelocitaRiparazione() {
        return 1.0 + (livelloAttuale - 1) * 0.1; // +10% velocità per livello
    }
}