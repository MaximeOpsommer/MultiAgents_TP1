package core;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import particules.Particle;
import particules.ParticleEnvironment;

public class SMA extends Observable {

	private ParticleEnvironment env;
	private final int nbTicks;
	private int delay;
	private int refresh;
	private int scheduling;
	
	public SMA(ParticleEnvironment env) {
		this.env = env;
		nbTicks = env.getConfigs().getNbTicks();
		delay = env.getConfigs().getDelay();
		refresh = env.getConfigs().getRefresh();
		scheduling = env.getConfigs().getScheduling();
	}
	
	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers();
	}
	
	public void update() {
		notifyObservers();
	}
	
	public void run() {
		switch(scheduling) {
		case 0:
			equitableRun();
			break;
		case 1:
			sequentialRun();
			break;
		case 2:
			randomRun();
			break;
		default:
			System.err.println("Error while defining scheduling mdoe");
			break;
		}
	}
	
	private void equitableRun() {
		int tick = 0;
		List<Particle> agents = env.getParticles();
		while(nbTicks == 0 || tick < nbTicks) {
			
			for(Particle agent : agents) {
				agent.decide();
			}
			Collections.shuffle(agents, env.getRandom());
			
			tick++;
			if(env.getConfigs().trace()) {
				System.out.println("Tick;" + tick);
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(tick % refresh == 0 ) {				
				update();
			}
		}
	}
	
	private void sequentialRun() {
		int tick = 0;
		while(nbTicks == 0 || tick < nbTicks) {
			
			for(Particle agent : env.getParticles()) {
				agent.decide();
			}
			tick++;
			if(env.getConfigs().trace()) {
				System.out.println("Tick;" + tick);
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(tick % refresh == 0 ) {				
				update();
			}
		}
	}
	
	private void randomRun() {
		int tick = 0;
		List<Particle> agents = env.getParticles();
		while(nbTicks == 0 || tick < nbTicks) {
			
			for(int i = 0; i < agents.size(); i++) {
				agents.get(env.getRandom().nextInt(agents.size())).decide();
			}
			tick++;
			if(env.getConfigs().trace()) {
				System.out.println("Tick;" + tick);
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(tick % refresh == 0 ) {				
				update();
			}
		}
	}
	
}
