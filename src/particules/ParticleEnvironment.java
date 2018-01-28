package particules;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.AgentNotFoundException;
import core.Environment;

public class ParticleEnvironment extends Environment {
	
	private final int particleNumber;
	private List<Particle> particles = new ArrayList<Particle>();
	
	
	
	public ParticleEnvironment() {
		configs = new ParticleConfigs();
		particleNumber = ((ParticleConfigs) (configs)).getParticleNumber();
		init();
	}
	
	protected void init() {
		super.init();
		int total = grid.length * grid[0].length;
		int reste = particleNumber;
		int rand;
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = 1;
					reste--;
					particles.add(new Particle(this, line, column));
				} else {
					grid[line][column] = 0;
				}
				total--;
			}
		}
	}
	
	public List<? extends Agent> getAllAgents() {
		return particles;
	}
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	public Particle getParticle(int column, int line) throws AgentNotFoundException {
		for(Particle agent : particles) {
			if(agent.getColumn() == column && agent.getLine() == line) {
				return agent;
			}
		}
		throw new AgentNotFoundException();
	}
	
	public void collision(final int particleLine, final int particleColumn,
						final int targetLine, final int targetColumn) {
		grid[particleLine][particleColumn] = ParticleConstants.PARTICLE_COLLISION;
		grid[targetLine][targetColumn] = ParticleConstants.PARTICLE_COLLISION;
	}
	
}
