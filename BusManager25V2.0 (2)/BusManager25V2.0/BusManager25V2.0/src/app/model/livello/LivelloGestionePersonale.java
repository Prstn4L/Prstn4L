package app.model.livello;

public class LivelloGestionePersonale extends Livello {
    @Override
    void inizializzaSoglie() {
        soglieAvanzamento.put(2, 5000.0);
        soglieAvanzamento.put(3, 15000.0);
        soglieAvanzamento.put(4, 35000.0);
        soglieAvanzamento.put(5, 75000.0);
        soglieAvanzamento.put(6, 150000.0);
        soglieAvanzamento.put(7, 300000.0);
        soglieAvanzamento.put(8, 600000.0);
        soglieAvanzamento.put(9, 1200000.0);
        soglieAvanzamento.put(10, 2400000.0);
        soglieAvanzamento.put(11, 4800000.0);
        soglieAvanzamento.put(12, 9600000.0);
    }
    
    public int getNumeroMassimoDipendenti() {
        return livelloAttuale + 2; // 3 dipendenti al livello 1, +1 per livello
    }
}