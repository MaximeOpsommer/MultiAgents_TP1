package wator;

import java.util.ArrayList;
import java.util.List;

public class Fish extends AquaticAnimal {
	
	public Fish(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getFishBreedTime());
	}

	public void decide() {
		int[][] grid = env.getGrid();
		List<Integer> voisinsLibres = getVoisinsLibres(grid, WatorConstants.BABY_FISH);
		// If cannot move, do nothing
		if(!voisinsLibres.isEmpty()) {			
			int random = 4;
			while(random == 4) {
				random = voisinsLibres.get(env.getRandom().nextInt(voisinsLibres.size()));
			}
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			if(env.getConfigs().isTorus()) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			
			// Move
			env.moveAgent(column, line, verticalDirection, horizontalDirection, WatorConstants.ADULT_FISH);			
			if(breedTime < 1) {
				// reproduction
				((WatorEnvironment) env).addFish(line, column);
				breedTime = ((WatorConfigs) env.getConfigs()).getFishBreedTime() + 1;
			}
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
			
		}
		breedTime--;
		((WatorEnvironment) env).grow(line, column, WatorConstants.ADULT_FISH);
	}

	public WatorConfigs getConfigs() {
		return (WatorConfigs) env.getConfigs();
	}

}
