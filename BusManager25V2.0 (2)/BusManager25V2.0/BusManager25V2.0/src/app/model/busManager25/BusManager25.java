package app.model.busManager25;

import app.model.deposito.Deposito;
import app.model.gestionePersonale.GestionePersonale;
import app.model.impiegato.Impiegato;
import app.model.impiegato.Meccanico;
import app.model.livello.LivelloDeposito;
import app.model.livello.LivelloUtente;
import app.model.mappa.Mappa;
import app.model.officina.Officina;
import app.model.rotta.Rotta;
import app.model.showroom.ShowRoom;
import app.model.veicolo.Veicolo;

public class BusManager25 {
    private String nomeAzienda;
    private double denaro;
    private LivelloUtente livelloUtente;
    private Officina officina;
    private Deposito deposito;
    private ShowRoom showRoom;
    private Mappa mappa;
    private GestionePersonale gestionePersonale;

    public BusManager25(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
        this.denaro = 20000.00;
        this.livelloUtente = new LivelloUtente();
        this.officina = new Officina();
        this.deposito = new Deposito(officina);
        this.deposito.setLivelloDeposito(new LivelloDeposito());
        this.showRoom = new ShowRoom(deposito);
        this.gestionePersonale = new GestionePersonale();
        this.mappa=new Mappa();
    }

    // PARTE DI METODI DEPOSITO 
    
    public boolean vendiVeicolo(Veicolo v) {
        return this.deposito.vendiVeicolo(v, denaro);
    }

    public boolean mandaInOfficina(Veicolo v) {
        return this.deposito.mandaInOfficina(v);
    }
    
    
    // PARTE DI METODI SHOWROOM
    
    public boolean compraVeicolo(Veicolo v) {
        return this.showRoom.compraVeicolo(v, denaro, this.livelloUtente.getLivelloAttuale());
    }
        
    // PARTE DI METODI OFFICINA
    
    public boolean assegnaMeccanicoAVeicolo(Veicolo v, String id) {
        return this.officina.assegnaMeccanico(v, id, this.gestionePersonale.getMeccanici());
    }
    
    public boolean rimandaInDeposito(Veicolo v) {
    	return this.officina.rimandaADeposito(v);
    }
    
    // !!!!!!!!!!!!!!!!!!!!!!! mancano i metodi ripara/migliora !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    // PARTE DI METODI GESTIONE MAPPA
    
    public boolean avviaRotta(Rotta r) {
    	return this.mappa.avviaRotta(r);
    }
    
    public boolean fermaRotta(Rotta r) {
    	return this.mappa.fermaRotta(r);
    }
    
    public boolean assegnaVeicoloARotta(Rotta r,String targa) {
    	return this.mappa.assegnaVeicoloARotta(r,targa,this.deposito.getVeicoli());
    }
    
    public boolean assegnaAutistaARotta(Rotta r,String id) {
    	return this.mappa.assegnaAutistaARotta(r,id,this.gestionePersonale.getAutisti());
    }
    
    
    // PARTE DI METODI GESTIONE PERSONALE
   
    public boolean assumiImpiegato(String tipo) { // passo dalla combobox o "autista" o "meccanico"
        return gestionePersonale.assumi(tipo);
    }

    public boolean licenziaImpiegato(Impiegato x) {
        return gestionePersonale.licenzia(x);
    }
    
    public boolean miglioraImpiegato(Impiegato x) {
    	return this.gestionePersonale.migliora(x, denaro);
    }

    // PARTE DI MIGLIORIE STRUTTURE
    
    public boolean miglioraOfficina() {
        return this.officina.miglioraOfficina(denaro);
    }

    public boolean miglioraDeposito() {
       return this.deposito.miglioraDeposito(denaro);
    }
    
    public boolean milgioraGestionePersonale() {
    	return this.gestionePersonale.miglioraGestPers(denaro);
    }

    
}
