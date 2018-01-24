package core;

public enum Direction {

	NORTH(0,-1) {
		public Direction reverseDirection() {
			return SOUTH;
		}
		
		public Direction reverseVerticalDirection() {
			return SOUTH;
		}
	},
	
	NORTH_EAST(1,-1) {
		public Direction reverseDirection() {
			return SOUTH_WEST;
		}
		
		public Direction reverseHorizontalDirection() {
			return NORTH_WEST;
		}
		
		public Direction reverseVerticalDirection() {
			return SOUTH_EAST;
		}
	},
	
	EAST(1,0) {
		public Direction reverseDirection() {
			return WEST;
		}
		
		public Direction reverseHorizontalDirection() {
			return WEST;
		}
	},
	
	SOUTH_EAST(1,1) {
		public Direction reverseDirection() {
			return NORTH_WEST;
		}
		
		public Direction reverseHorizontalDirection() {
			return SOUTH_WEST;
		}
		
		public Direction reverseVerticalDirection() {
			return NORTH_EAST;
		}
	},
	
	SOUTH(0,1) {
		public Direction reverseDirection() {
			return NORTH;
		}
		
		public Direction reverseVerticalDirection() {
			return NORTH;
		}
	},
	
	SOUTH_WEST(-1,1) {
		public Direction reverseDirection() {
			return NORTH_EAST;
		}
		
		public Direction reverseHorizontalDirection() {
			return SOUTH_EAST;
		}
		
		public Direction reverseVerticalDirection() {
			return NORTH_WEST;
		}
	},
	WEST(-1,0) {
		public Direction reverseDirection() {
			return EAST;
		}
		
		public Direction reverseHorizontalDirection() {
			return EAST;
		}
	},
	
	NORTH_WEST(-1,-1) {
		public Direction reverseDirection() {
			return SOUTH_EAST;
		}
		
		public Direction reverseHorizontalDirection() {
			return NORTH_EAST;
		}
		
		public Direction reverseVerticalDirection() {
			return SOUTH_WEST;
		}
	};
	
	
	private final int horizontalMove;
	private final int verticalMove;
	
	
	// Constructor
	Direction(int horizontalMove, int verticalMove) {
		this.horizontalMove = horizontalMove;
		this.verticalMove = verticalMove;
	}
	
	// Getters
	public int getHorizontalMove() {
		return horizontalMove;
	}
	
	
	public int getVerticalMove() {
		return verticalMove;
	}
	
	// Reverse directions methods
	public Direction reverseVerticalDirection() {
		return this;
	};
	
	public Direction reverseHorizontalDirection() {
		return this;
	}
	
	public abstract Direction reverseDirection();
		
}
