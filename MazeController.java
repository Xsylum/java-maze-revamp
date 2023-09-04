import java.util.LinkedList;


/**

 The maze should really be copied, not overwritten!
*/
public class MazeController {
	
	Maze maze;
	LinkedList<Integer> branches;
	private int currentTile;
	// NEED TO CODE A LIST, ARRAY WON'T WORK
	
	public MazeController (Maze maze) {
		this.maze = maze;
		currentTile = maze.getStartIndex();
		branches = new java.util.LinkedList<Integer>();
	}
	
	public int getCurrentTile() {
		return currentTile;
	}
	
	/**
	 * Takes an input direction up/right/down/left and checks if
	 *   1. The tile is EMPTY (EXPLORED tiles should not be visited again)
	 *   2. If the direction is possible given the currentIndex
	 *		(e.g. Cannot move right if on the rightside of the maze)
	 */
	public boolean canMove(String input) {
		int targetIndex = -1;
		
		// Based on input direction, check what index would be moved to, and
		// if that index is within bounds/a valid move given currentIndex
		switch (input) {
			case "up":
				targetIndex = currentTile - maze.getWidth();
				if (targetIndex < 0) {
					return false;
				}
				break;
				
			case "down":
				targetIndex = currentTile + maze.getWidth();
				if (targetIndex >= maze.getMazeSize()) {
					return false;
				}
				break;
				
			case "right":
				targetIndex = currentTile + 1;
				if (targetIndex % maze.getWidth() == 0 || targetIndex >= maze.getMazeSize()) { // looped around to next row
					return false;
				}
				break;
				
			case "left":
				targetIndex = currentTile - 1;
				if (targetIndex % maze.getWidth() == maze.getWidth() - 1 || targetIndex < 0) { // looped around to previous row
					return false;
				}
				break;
		}
		
		// The input direction was not covered by switch statement, therefore illegal
		if (targetIndex == -1) {
			throw new IllegalArgumentException("Invalid input direction to canMove");
		}
		
		Tile targetTile = maze.getTile(targetIndex);
		
		if (targetTile == Tile.EMPTY || targetTile == Tile.FINISH) {
			return true;
		}
		return false;
	}
	
	/**
	 * changes current tile to the input index.
	 * essentially it allows jumping anywhere in the maze, useful
	 * for returning to branches once a path has been travelled
	 *
	 * Presumption: the index entered is not a WALL tile in maze
	 */
	public void MoveToIndex(int index) {
		currentTile = index;
	}
	
	/**
	 * 
	 */
	public boolean AutomatedSolver() {
		
		// First check if last move brought us to the finish!
		if (maze.getTile(currentTile) == Tile.FINISH) {
			System.out.println("Finish tile at index: " + currentTile + " has been reached, congratulations!");
			return true;
		}
		
		int nextTile = -1; // The next tile that will be travelled
		if (canMove("right")) {
			nextTile = currentTile + 1;
		}
		if (canMove("down")) {
			if (nextTile == -1) {
				nextTile = currentTile + maze.getWidth();
			}
			else {
				branches.add(currentTile + maze.getWidth());
			}
		}
		if (canMove("left")) {
			if (nextTile == -1) {
				nextTile = currentTile - 1;
			}
			else {
				branches.add(currentTile + maze.getWidth());
			}
		}
		if (canMove("up")) {
			if (nextTile == -1) {
				nextTile = currentTile - maze.getWidth();
			}
			else {
				branches.add(currentTile - maze.getWidth());
			}
		}
		
		if (nextTile == -1) {
			if (branches.isEmpty()) {
				throw new IllegalStateException("No start-to-finish route using given maze configuration!");
			}
			
			nextTile = branches.remove(0);
			System.out.println("MazeControlled DEBUG: jumping to " + nextTile);
		}
		
		maze.setTile(currentTile, Tile.TRAVELLED);
		currentTile = nextTile;
		return false;
	}
	
}