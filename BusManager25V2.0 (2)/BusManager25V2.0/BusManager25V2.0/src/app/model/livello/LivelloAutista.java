package app.model.livello;

public class LivelloAutista extends Livello {
    /**
     * Inizializza le soglie di avanzamento per i livelli dell'autista
     */
    @Override
    protected void inizializzaSoglie() {
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

    /**
     * Calcola il bonus di guadagno in base al livello dell'autista
     * 
     * @return Bonus moltiplicativo al guadagno della rotta
     */
    public double getBonusGuadagno() {
        // +5% guadagno per ogni livello oltre il primo
        return (livelloAttuale - 1) * 0.05;
    }

    /**
     * Calcola la riduzione della probabilità di rottura del veicolo
     * in base al livello dell'autista
     * 
     * @return Valore di riduzione della probabilità (0-1)
     */
    public double getRiduzioneProbabilitaRottura() {
        // -3% probabilità rottura per ogni livello oltre il primo
        return (livelloAttuale - 1) * 0.03;
    }
}