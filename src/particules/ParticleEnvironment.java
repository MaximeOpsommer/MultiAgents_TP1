package particules;

import java.util.ArrayList;
import java.util.List;

import core.AgentNotFoundException;
import core.Coord;
import core.Environment;

public class ParticleEnvironment extends Environment {
	
	private final int particleNumber;
	private List<Particle> particles = new ArrayList<Particle>();
	
	
	
	public ParticleEnvironment() {
		configs = new ParticleConfigs();
		particleNumber = ((ParticleConfigs) (configs)).getParticleNumber();
		
	}
	
	protected void init() {
		int total = grid.length * grid[0].length;
		int reste = particleNumber;
		int rand;
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = 1;
					reste--;
					particles.add(new Particle(this, new Coord(column, line)));
				} else {
					grid[line][column] = 0;
				}
				total--;
			}
		}
	}
	
	
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	public Particle getParticle(int column, int line) throws AgentNotFoundException {
		for(Particle agent : particles) {
			Coord coords = agent.getCoords();
			if(coords.getColumn() == column && coords.getLine() == line) {
				return agent;
			}
		}
		throw new AgentNotFoundException();
	}
	
	public void collision(Coord c1, Coord c2) {
		grid[c1.getLine()][c1.getColumn()] = ParticleConstants.PARTICLE_COLLISION;
		grid[c2.getLine()][c2.getColumn()] = ParticleConstants.PARTICLE_COLLISION;
	}
	
}
