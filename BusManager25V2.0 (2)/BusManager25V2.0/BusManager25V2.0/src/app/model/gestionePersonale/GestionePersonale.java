package app.model.gestionePersonale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import app.model.impiegato.Autista;
import app.model.impiegato.Impiegato;
import app.model.impiegato.Meccanico;
import app.model.livello.LivelloGestionePersonale;

public class GestionePersonale {
	private LivelloGestionePersonale lvlGP;
    private ArrayList<Autista> autisti;
    private ArrayList<Meccanico> meccanici;

    public GestionePersonale() {
        this.autisti = new ArrayList<>();
        this.meccanici = new ArrayList<>();
        this.lvlGP=new LivelloGestionePersonale();
    }
    
    public boolean miglioraGestPers(double denaro) {
    	return this.lvlGP.miglioraLivello(denaro);
    }

    public boolean assumi(String tipo) {
    	// se non cè spazio in base al livello
    	if(autisti.size()+meccanici.size()>this.lvlGP.getNumeroMassimoDipendenti())
    		return false;
        ArrayList<String[]> candidati = new ArrayList<>();
        // se il tipo è autista prendo dal file autisti, se no meccanici
        String fileName = tipo.equalsIgnoreCase("autista") ? "autisti.txt" : "meccanici.txt";
        
        // leggo il file e prendi una coppia nome cognome
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.trim().split(" ");
                if (parts.length >= 2) {
                    candidati.add(new String[]{parts[0], parts[1]});
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + fileName);
            return false;
        }
        // controllo che la coppia nome congome non sia già stata presa
        for (String[] candidato : candidati) {
            String nome = candidato[0];
            String cognome = candidato[1];

            boolean esiste = false;

            if (tipo.equalsIgnoreCase("autista")) {
                for (Autista a : autisti) {
                    if (a.getNome().equalsIgnoreCase(nome) && a.getCognome().equalsIgnoreCase(cognome)) {
                        esiste = true;
                        break;
                    }
                }
                if (!esiste) {
                    Autista nuovo = new Autista(nome, cognome);
                    autisti.add(nuovo);
                    return true;
                }
            } else if (tipo.equalsIgnoreCase("meccanico")) {
                for (Meccanico m : meccanici) {
                    if (m.getNome().equalsIgnoreCase(nome) && m.getCognome().equalsIgnoreCase(cognome)) {
                        esiste = true;
                        break;
                    }
                }
                if (!esiste) {
                    Meccanico nuovo = new Meccanico(nome, cognome);
                    meccanici.add(nuovo);
                    return true;
                }
            }
        }

        // Nessuna coppia nome cognome disponibile che non sia già presente
        return false;
    }

    // licenzio di un impiegato
    public boolean licenzia(Impiegato impiegato) {
        if (!impiegato.isRiposo()) {
            return false;
        }
        // controllo l'istanza, autista prendo dall'array autisti, se no meccanici
        if (impiegato instanceof Autista) {
            return autisti.remove(impiegato);
        } else if (impiegato instanceof Meccanico) {
            return meccanici.remove(impiegato);
        }

        return false;
    }


    public boolean migliora(Impiegato x,double denaro) {
    	if(x instanceof Autista) {
    		for (Autista a : autisti) {
                if (a.equals(x)) {
                    a.getLivello().miglioraLivello(denaro);
                    return true;
                }
            }
    		return false;
    	}
        for (Meccanico m : meccanici) {
            if (m.equals(x)) {
                m.getLivello().miglioraLivello(denaro);
                return true;
            }
        }
        return false;
    }
    
    public Meccanico trovaById(String id) {
    	for(Meccanico m:meccanici) {
    		if(m.getId().equals(id))
    			return m;
    	}
    	return null;
    }

    public ArrayList<Autista> getAutisti() {
        return autisti;
    }

    public ArrayList<Meccanico> getMeccanici() {
        return meccanici;
    }
}
