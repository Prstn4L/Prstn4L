package app.model.impiegato;

public class StatoImpiegato {
    private boolean attivo;
    private boolean assegnato;
    private boolean riposo;

    public StatoImpiegato() {
        setRiposo(); // Stato iniziale di riposo
    }

    // Metodi per impostare gli stati
    public void setAttivo() {
        this.attivo = true;
        this.assegnato = false;
        this.riposo = false;
    }

    public void setAssegnato() {
        this.attivo = false;
        this.assegnato = true;
        this.riposo = false;
    }

    public void setRiposo() {
        this.attivo = false;
        this.assegnato = false;
        this.riposo = true;
    }

    // Metodi per verificare lo stato
    public boolean isAttivo() {
        return attivo;
    }

    public boolean isAssegnato() {
        return assegnato;
    }

    public boolean isRiposo() {
        return riposo;
    }
}