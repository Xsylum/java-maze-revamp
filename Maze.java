import java.util.Arrays;
public class Maze {
	
	private enum Tile { WALL, EMPTY };
	private Tile[] tiles;  // A 2D maze can be kept as a 1D array due to currentIndex
	private int playerIndex;  // (y * width + x) turns a 2D coordinate into an array Index!
	private int width;
	private int height;
	
	public Maze(int width, int height) {
		this(width, height, 0); // Default playerPosition
	}
	
	public Maze(int width, int height, int playerPos) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width*height];
		Arrays.fill(tiles, Tile.EMPTY);
		
		
		playerIndex = playerPos;
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[y * width + x] = tile;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y * width + x];
	}
	
	/**
	 * Conversion equation from 2D coordinate to single int index system
	 */
	public void setPlayerPosition(int x, int y) {
		playerIndex = y * width + x;
	}
	
	/**
	 * setting directly via index system
	 */
	public void setPlayerIndex(int index) {
		playerIndex = index;
	}
	
	/**
	 * returning the player's index
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/**
	 * returning the player's coordinates [CURRENTLY STRING]
	 */
	public String getPlayerPosition() {
		String s = "(" + ((int) playerIndex/width) + ", " + (playerIndex % width) + ")";
		return s;
	}
	
	public String toString() {
		String s = "This is a " + width + " wide and " + height + " high maze; ";
		s += "The player is currently at (x, y): (" 
				+ ((int) playerIndex/width) + ", " + (playerIndex % width) + ")";
		return s;
	}
}