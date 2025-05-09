package app.model.officina;

import app.model.veicolo.*;
import app.model.gestionePersonale.GestionePersonale;
import app.model.impiegato.Meccanico;
import app.model.livello.LivelloOfficina;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

public class Officina {
	private LivelloOfficina livelloOfficina;
	private final Map<Veicolo, Meccanico> assegnazioni = new ConcurrentHashMap<>();
	private final ExecutorService threadPool = Executors.newCachedThreadPool();

	public Officina() {
		this.livelloOfficina = new LivelloOfficina();
	}

	public boolean puoAccettareVeicolo() {
		return assegnazioni.size() < livelloOfficina.getPostiDisponibili();
	}

	public void statoVeicoloOfficina(Veicolo v) {
		synchronized (v) {
			v.setInOfficina(true);
			v.setDeposito(false);
			v.setAttivo(false);
		}
	}

	public boolean aggiungiVeicolo(Veicolo veicolo) {
		if (veicolo.isDeposito() && puoAccettareVeicolo()) {
			statoVeicoloOfficina(veicolo);
			assegnazioni.put(veicolo, null);
			return true;
		}
		return false;
	}

	public boolean miglioraOfficina(double denaro) {
		return this.livelloOfficina.miglioraLivello(denaro);
	}

	public boolean assegnaMeccanico(Veicolo veicolo, String id, ArrayList<Meccanico> meccanici) {
		for (Meccanico meccanico : meccanici) {
			if (meccanico.getId().equals(id)) {
				if (meccanico == null || !veicolo.getStato().isInOfficina() || !meccanico.isDisponibile())
					return false;

				synchronized (veicolo) {
					assegnazioni.put(veicolo, meccanico);
				}

				synchronized (meccanico) {
					meccanico.assegnaVeicolo(veicolo);
					meccanico.setAssegnato();
				}
				return true;
			}
		}
		return false;
	}

	public void riparaVeicolo(Veicolo veicolo, double denaro) {
		Meccanico meccanico = assegnazioni.get(veicolo);
		if (meccanico == null)
			return;

		synchronized (meccanico) {
			meccanico.setUltimoCostoVeicolo(veicolo.getPrezzoVeicolo());
			if (denaro - meccanico.pagaStipendio() < 0)
				return;

			threadPool.execute(() -> {
				try {
					Thread.sleep((long) meccanico.getTempoAzione());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				synchronized (veicolo) {
					veicolo.setInRiparazione(false);
					resetStatoPostOfficina(veicolo);
				}

				synchronized (meccanico) {
					meccanico.rilasciaVeicolo();
					meccanico.setRiposo();
				}

				assegnazioni.remove(veicolo);
			});
		}
	}

	public void miglioraVeicolo(Veicolo veicolo) {
		Meccanico meccanico = assegnazioni.get(veicolo);
		if (meccanico == null)
			return;

		threadPool.execute(() -> {
			try {
				Thread.sleep((long) meccanico.getTempoAzione());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			synchronized (veicolo) {
				veicolo.setInMiglioramento(false);
				resetStatoPostOfficina(veicolo);
			}

			synchronized (meccanico) {
				meccanico.rilasciaVeicolo();
				meccanico.setRiposo();
			}

			assegnazioni.remove(veicolo);
		});
	}

	private void resetStatoPostOfficina(Veicolo veicolo) {
		synchronized (veicolo) {
			veicolo.setDeposito(true);
			veicolo.setInOfficina(false);
			veicolo.setInRiparazione(false);
			veicolo.setInMiglioramento(false);
		}
	}

	public boolean dissociaMeccanico(Veicolo veicolo) {
		Meccanico meccanico = assegnazioni.get(veicolo);
		if (meccanico != null) {
			synchronized (meccanico) {
				meccanico.rilasciaVeicolo();
				meccanico.setRiposo();
			}

			synchronized (veicolo) {
				veicolo.setInRiparazione(false);
				veicolo.setInMiglioramento(false);
			}

			assegnazioni.put(veicolo, null);
			return true;
		}
		return false;
	}

	public boolean rimandaADeposito(Veicolo veicolo) {
		if (assegnazioni.containsKey(veicolo)) {
			Meccanico meccanico = assegnazioni.get(veicolo);
			if (meccanico != null) {
				synchronized (meccanico) {
					meccanico.rilasciaVeicolo();
					meccanico.setRiposo();
				}
			}

			assegnazioni.remove(veicolo);

			synchronized (veicolo) {
				veicolo.setInOfficina(false);
				veicolo.setDeposito(true);
				veicolo.setInRiparazione(false);
				veicolo.setInMiglioramento(false);
			}
			return true;
		}
		return false;
	}

	public void shutdown() {
		threadPool.shutdown();
	}

	public LivelloOfficina getLivelloOfficina() {
		return livelloOfficina;
	}

	public void setLivelloOfficina(LivelloOfficina livelloOfficina) {
		this.livelloOfficina = livelloOfficina;
	}

	public Map<Veicolo, Meccanico> getAssegnazioni() {
		return assegnazioni;
	}

	public ExecutorService getThreadPool() {
		return threadPool;
	}
}
