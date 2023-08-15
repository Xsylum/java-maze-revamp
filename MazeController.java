public class MazeController {
	
	Maze maze;
	int[] futureMoves; // holds list of possible paths to explore!
	// NEED TO CODE A LIST, ARRAY WON'T WORK
	
	public MazeController (Maze maze) {
		this.maze = maze;
	}
	
	public boolean canMove(int index) {
		Tile targetTile = maze.getTile(index);
		
		if (targetTile == PATH) {
			return true;
		}
		return false;
	}
	
	public void Move(String input) {
		
	}
	
}