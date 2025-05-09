package app.model.showroom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import app.model.deposito.Deposito;
import app.model.veicolo.Veicolo;

public class ShowRoom {
	private ArrayList<Veicolo> veicoli;
	private final Random r=new Random();
	private Deposito deposito;
	
	public ShowRoom(Deposito deposito) {
		this.veicoli=new ArrayList<Veicolo>();
		this.deposito=deposito;
		if(r.nextInt()%2==0) {
			inizializzaShowRoom("src/app/model/fileTesto/ListaVeicoli1");
		}else {
			inizializzaShowRoom("src/app/model/fileTesto/ListaVeicoli2");
		}
	}
	public boolean compraVeicolo(Veicolo v,double denaroUtente,int livelloUtente) {
		if(denaroUtente>=v.getPrezzoVeicolo() && livelloUtente>=v.getLivelloVeicolo() && deposito.aggiungiVeicolo(v)) {
			return true;
		}
		return false;
	}
	// Formato file testo --> modello,prezzoVeicolo,velocitaBase,postiBase,livelloVeicolo
	private void inizializzaShowRoom(String percorsoFile) {

	    try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
	        String riga;
	        while ((riga = reader.readLine()) != null) {
	            String[] dati = riga.split(",");
	            if (dati.length != 5) continue;

	            String modello = dati[0].trim();
	            double prezzo = Double.parseDouble(dati[1].trim());
	            double velocita = Double.parseDouble(dati[2].trim());
	            int posti = Integer.parseInt(dati[3].trim());
	            int livello = Integer.parseInt(dati[4].trim());

	            Veicolo v = new Veicolo(modello, prezzo, velocita, posti, livello,generaTarga());
	            veicoli.add(v);
	            
	        }
	    } catch (IOException e) {
	        return;
	    }
	}
	
	// Targa esempio --> LLNNNLL  (L)lettera e (N)numero
		public static String generaTarga() { 
		    Random r = new Random();
		    char[] lettere = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		    String parteLettera = "" + lettere[r.nextInt(26)] + lettere[r.nextInt(26)];
		    String parteNumerica = String.format("%03d", r.nextInt(1000));
		    return parteLettera + parteNumerica + parteLettera;
		}
	
	public ArrayList<Veicolo> getVeicoli() {
		return veicoli;
	}
	public void setVeicoli(ArrayList<Veicolo> veicoli) {
		this.veicoli = veicoli;
	}
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	public Random getR() {
		return r;
	}
	
	


	
}
