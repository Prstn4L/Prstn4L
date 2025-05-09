package app.model.deposito;

import java.util.ArrayList;

import app.model.livello.LivelloDeposito;
import app.model.officina.Officina;
import app.model.veicolo.Veicolo;

public class Deposito {
	private LivelloDeposito livelloDeposito;
	private ArrayList<Veicolo> veicoli;
	private Officina officina;

	public Deposito(Officina officina) {
		this.veicoli = new ArrayList<>();
		this.officina = officina;
		this.livelloDeposito=new LivelloDeposito();
		}

	public boolean aggiungiVeicolo(Veicolo v) {
		if (veicoli.size() + 1 >= livelloDeposito.getPostiDisponibili()) {
			return false;
		}
		veicoli.add(v);
		return true;
	}

	// controllare se il veicolo è asseganto ecc
	public boolean vendiVeicolo(Veicolo v, double denaro) {
		// controllo che sia al depsoito,non sia asegnato e che non sia attivo
		if (!v.isInOfficina() && !v.isAssegnato() && !v.isAttivo()) {
			double d = 0;
			if (v.isDanneggiato()) {
				d = v.getPrezzoVeicolo() / 4;
				veicoli.remove(v);
				return true;
			}
			if (v.isDeposito()) {
				d = v.getPrezzoVeicolo() / 2;
				veicoli.remove(v);
				return true;
			}
		}
		return false;
	}

	public boolean miglioraDeposito(double denaro) {
		return livelloDeposito.miglioraLivello(denaro);
	}

	public boolean mandaInOfficina(Veicolo v) {
		return officina.aggiungiVeicolo(v);
		
	}

	public LivelloDeposito getLivelloDeposito() {
		return livelloDeposito;
	}

	public void setLivelloDeposito(LivelloDeposito livelloDeposito) {
		this.livelloDeposito = livelloDeposito;
	}

	public ArrayList<Veicolo> getVeicoli() {
		return veicoli;
	}

	public void setVeicoli(ArrayList<Veicolo> veicoli) {
		this.veicoli = veicoli;
	}

	public Officina getOfficina() {
		return officina;
	}

	public void setOfficina(Officina officina) {
		this.officina = officina;
	}

}
