package particules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Agent;
import core.Environment;

public class ParticleEnvironment extends Environment {
	
	// 1 = gris : pas encore de collision
	// 2 = orange : collision avec un mur
	// 3 = rouge : collision avec une bille
	
	private final int particleNumber;
	private Map<Integer, Particle> particles = new HashMap<Integer, Particle>();
	
	
	
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
					particles.put((line*width)+column, new Particle(this, line, column));
				} else {
					grid[line][column] = 0;
				}
				total--;
			}
		}
	}
	
	public List<? extends Agent> getAllAgents() {
		return new ArrayList<Agent>(particles.values());
	}
	
	public Particle getParticle(int column, int line) {
		return particles.get((line*width)+column);
	}
	
	public void particleMove(final int oldLine, final int oldColumn, final int newLine, final int newColumn) {
		particles.put((newLine*width)+newColumn, particles.remove((oldLine*width)+oldColumn));
	}
	
	public void collision(final int particleLine, final int particleColumn,
						final int targetLine, final int targetColumn) {
		grid[particleLine][particleColumn] = ParticleConstants.PARTICLE_COLLISION;
		grid[targetLine][targetColumn] = ParticleConstants.PARTICLE_COLLISION;
	}
	
}
