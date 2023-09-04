import java.util.Arrays;
public class Maze {
	
	private Tile[] tiles;  // A 2D maze can be kept as a 1D array due to currentIndex
	private int startIndex;
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
	
	/**
	 * Takes an array of chars and sets each tile in tiles based
	 * based on the following conversion table:
	 *   - char E == Tile.EMPTY
	 *   - char W == Tile.WALL
	 */
	public void setAllTiles(char[] tilesAsCharArray) {
		if (tilesAsCharArray.length != tiles.length){
			throw new IllegalArgumentException("mismatched array length");
		}
		
		for (int i = 0; i < tilesAsCharArray.length; i++) {
			char currentChar = tilesAsCharArray[i];
			
			if (currentChar == 'O') { // Empty
				tiles[i] = Tile.EMPTY;
			}
			else if (currentChar == 'W') {
				tiles[i] = Tile.WALL;
			}
			else if (currentChar == 'S') {
				tiles[i] = Tile.START;
				startIndex = i;
			}
			else if (currentChar == 'F') 
			{
				tiles[i] = Tile.FINISH;
			}
		} 
	}
	
	public Tile getTile(int index) {
		return tiles[index];
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y * width + x];
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[y * width + x] = tile;
	}
	
	/**
	 * Sets the specified index in tiles to be of type tile
	 */
	public void setTile(int index, Tile tile) {
		if (index < 0 || index >= tiles.length) {
			throw new IndexOutOfBoundsException();
		}
		
		tiles[index] = tile;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getMazeSize() {
		return tiles.length;
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
	
	/**
	 * Creates a (string) 2D representation of the maze map
	 *
	 */
	public String toStringMap() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < tiles.length; i++) {
			
			// new line to seperate rows, space to seperate columns
			if (i != 0 && i % width == 0) {
				s.append("\n");
			}
			else if (i != 0) {
				s.append(" ");
			}
			
			switch (tiles[i]) {
				case WALL:
					s.append("W");
					break;
				case EMPTY:
					s.append("O"); // "Open"
					break;
				case START:
					s.append("S");
					break;
				case TRAVELLED:
					s.append("X"); // Opposite of O
					break;
				case FINISH:
					s.append("F");
					
			}
		}
		
		return s.toString();
	}
	
	public String toString() {
		String s = "This is a " + width + " wide and " + height + " high maze; ";
		s += "The player is currently at (x, y): (" 
				+ ((int) playerIndex/width) + ", " + (playerIndex % width) + ")";
		return s;
	}
}