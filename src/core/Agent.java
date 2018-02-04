package core;

public abstract class Agent {
	
	protected Environment env;
	protected int line;
	protected int column;
	protected int verticalDirection = 0;
	protected int horizontalDirection = 0;
	protected final boolean isTorus;

	public Agent(final Environment env, final int line, final int column) {
		this.env = env;
		this.line = line;
		this.column = column;
		this.verticalDirection = 0;
		this.verticalDirection = 0;
		isTorus = env.getConfigs().isTorus();
	}
	
	public int[][] getGrid() {
		return env.getGrid();
	}
	
	public Configs getConfigs() {
		return env.getConfigs();
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getVerticalDirection() {
		return verticalDirection;
	}
	
	public void setVerticalDirection(final int verticalDirection) {
		this.verticalDirection = verticalDirection;
	}
	
	public int getHorizontalDirection() {
		return horizontalDirection;
	}
	
	public void setHorizontalDirection(final int horizontalDirection) {
		this.horizontalDirection = horizontalDirection;
	}
	
	public abstract void decide();
	
	protected abstract void move();
	
}
