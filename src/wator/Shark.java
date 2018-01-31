package wator;

import java.util.ArrayList;
import java.util.List;

import core.Agent;

public class Shark extends AquaticAnimal {
	
	private int sharkStarveTime;

	public Shark(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getSharkBreedTime());
		this.sharkStarveTime = ((WatorConfigs) (env.getConfigs())).getSharkStarveTime();
	}

	public void decide() {
		int[][] grid = env.getGrid();
		List<Integer> voisinsVides = getVoisinsLibres(grid, WatorConstants.BABY_FISH);
		List<Integer> voisinsProies = getVoisinsLibres(grid, WatorConstants.BABY_SHARK);
		voisinsProies.removeAll(voisinsVides);
		// If cannot move, do nothing
		if(!voisinsProies.isEmpty() || !voisinsVides.isEmpty()) {
			int random = 4;
			boolean eat = false;
			if(!voisinsProies.isEmpty()) {
				while(random == 4) {
					random = voisinsProies.get(env.getRandom().nextInt(voisinsProies.size()));
				}
				eat = true;
			}
			else {
				while(random == 4) {
					random = voisinsVides.get(env.getRandom().nextInt(voisinsVides.size()));
				}
			}
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			if(env.getConfigs().isTorus()) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			// Eat if fish
			if(eat) {
				((WatorEnvironment) env).removeFish(Math.floorMod(line + verticalDirection, grid.length), Math.floorMod(column + horizontalDirection, grid[0].length));
				sharkStarveTime = ((WatorConfigs) env.getConfigs()).getSharkStarveTime() + 1;
			}
			
			// Move
			env.moveAgent(column, line, verticalDirection, horizontalDirection, WatorConstants.ADULT_SHARK);			
			if(breedTime < 1) {
				// reproduction
				((WatorEnvironment) env).addShark(line, column);
				breedTime = ((WatorConfigs) env.getConfigs()).getSharkBreedTime() + 1;
			}
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
		}
		breedTime--;
		sharkStarveTime--;
		if(sharkStarveTime < 1) {
			((WatorEnvironment) env).removeShark(line, column);
		} else {			
			((WatorEnvironment) env).grow(line, column, WatorConstants.ADULT_SHARK);
		}
	}

	protected void eat() {
		
	}
	
	
	
}
