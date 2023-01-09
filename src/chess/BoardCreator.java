package chess;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 *A class containing a variety of board creating methods.
 *
 */
public class BoardCreator {
	private int width;
	private int height;
	
	/**
	 * 
	 * @param width the maximum width of every board.
	 * @param height the maximum height of every board.
	 */
	public BoardCreator(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates a rectangular board of specified width and height
	 * 
	 * @return a 2d integer array representation of the board. The inner arrays contains an x and y int value.
	 */
	public ArrayList<Location> rectBoard(){
		ArrayList<Location> board = new ArrayList<>();
		
		for(int x = 1; x <= width; x++) {
			for(int y = 1; y <= height; y++) {
				board.add(new Location(x, y));
			}
		}
		return board;
	}
	
	/**
	 * Creates a right angle triangle board of specified width and height
	 * 
	 * @return a 2d integer array representation of the board. The inner arrays contains an x and y int value
	 */
	public ArrayList<Location> triBoard(){
		int rowWidth = 1; //Width of n row in triangle
		
		ArrayList<Location> board = new ArrayList<>();
		
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= rowWidth; x++) {
				board.add(new Location(x, y));
			}
			rowWidth++;
		}
		
		return board;
		
	}
	
	/**
	 * Creates a pyramid board of specified width and height
	 * 
	 * @return a 2d integer array representation of the board. The inner arrays contain an x and y int value
	 */
	public ArrayList<Location> pyramidBoard(){
		int startIndex = 1;
		int endIndex = width;
		ArrayList<Location> board = new ArrayList<>();
		
		for(int y = 1; y <= height; y++) {
			for(int x = startIndex; x <= endIndex; x++) {
				board.add(new Location(x, y));
			}
			if(y % 2 == 0) {
				startIndex++;
				endIndex--;
			}
		}
		
		return board;
	}
	
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public ArrayList<Location> potholeBoard(Location loc) { //Parameter loc to prevent pothole on piece location
		Random rand = new Random();
		ArrayList<Location> board = new ArrayList<>();
		
		for(int x = 1; x <= width; x++) {
			for(int y = 1; y <= height; y++) {
				Location newPos = new Location(x, y);
				
				if(rand.nextInt(4) < 3 || loc.equals(newPos)) { //A 1/4 chance for a square to be empty.
					board.add(newPos);
				}
			}
		}
		
		return board;
		
	}
	
}
