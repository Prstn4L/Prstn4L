package app.model.mappa;

import app.model.impiegato.Autista;
import app.model.rotta.Rotta;
import app.model.veicolo.Veicolo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mappa {
    private List<Rotta> rotte;
    private final Random r = new Random();

    public Mappa() {
        this.rotte = new ArrayList<>();
        if(r.nextInt()%2 == 0) {
            inizializzaRotte("src/app/model/fileTesto/ListaRotte1.txt");
        } else {
            inizializzaRotte("src/app/model/fileTesto/ListaRotte2.txt");
        }
    }

    
    public void inizializzaRotte(String percorsoFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] dati = riga.split(",");
                if (dati.length != 5) continue;

                String nome = dati[0].trim();
                double kilometri = Double.parseDouble(dati[1].trim());
                int indiceAffollamento = Integer.parseInt(dati[2].trim());
                int livello = Integer.parseInt(dati[3].trim());
                double guadagnoBase = Double.parseDouble(dati[4].trim());

                Rotta rotta = new Rotta(nome, kilometri, indiceAffollamento, livello, guadagnoBase);
                rotte.add(rotta);
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Formato dati non valido nel file: " + e.getMessage());
        }
    }
    
    public boolean avviaRotta(Rotta r) {
    	return r.avviaRotta();
    }
    
    public boolean fermaRotta(Rotta r) {
    	return r.fermaRotta();
    }
    
    public boolean assegnaAutistaARotta(Rotta r,String id,ArrayList<Autista> autisti) {
    	return r.assegnaAutista(id,autisti);
    }
    
    public boolean assegnaVeicoloARotta(Rotta r,String targa,ArrayList<Veicolo> veicoli) {
    	return r.assegnaVeicolo(targa,veicoli);
    }
    
    public List<Rotta> getRotte() {
        return new ArrayList<>(rotte); // Restituiamo una copia per evitare modifiche esterne
    }

    public int getNumeroRotte() {
        return rotte.size();
    }
    

    public boolean isEmpty() {
        return rotte.isEmpty();
    }
}