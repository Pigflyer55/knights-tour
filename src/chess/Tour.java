package chess;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * 
 * @author Pigflyer55
 *
 */
public class Tour {
	private Location piece;
	private int width;
	private int height;
	private ArrayList<Location> unvisited;
	private int[][] offset; //Index 0 is x coord, Index 1 is y coord.
	private ArrayList<Location> board;
	private Rules rule;
	private int ruleType;
	
	/**
	 * Initializes the height, width, offset and board.
	 * 
	 * @param width the maximum width of the board.
	 * @param height the maximum height of the board.
	 * @param offset the different movements the piece can make.
	 * @param board contains all the coordinates of squares on the board.
	 */
	public Tour(int width, int height, int[][] offset, ArrayList<Location> board, int ruleType) {
		this.height = height;
		this.width = width;
		this.offset = offset;
		this.board = board;
		this.ruleType = ruleType;
		rule = new Rules(offset, width, height);
	}
	
	/**
	 * Initializes all the unvisited locations of the board. Initializes the piece
	 * 
	 * @param loc the location of the piece on the board
	 */
	public void startTour(Location loc){
		this.unvisited = new ArrayList<>();
		this.piece = loc;
		boolean pieceLoc = false;
		
		for(int index = 0; index < this.board.size(); index++) {
			Location check = board.get(index);
			
			if(!check.equals(this.piece)) {
				unvisited.add(check);
			}else {
				pieceLoc = true;
			}
			
		}
		
		if(!pieceLoc) {
			throw new IllegalArgumentException("Piece at [" + loc.x() + "][" + loc.y() + "] is not on the board");
		}
		
	}
	
	
	/**
	 * Determines if there are any possible moves left in the tour.
	 * 
	 * @return true if there is a possible move and false if there is no possible move.
	 */
	public boolean hasNext() {
		for(int[] cord: offset) {
			int posX = cord[0] + piece.x();
			int posY = cord[1] + piece.y();
			
			if(posX >= 1 && posX <= width && posY >= 1 && posY <= height) {
				for(Location loc: unvisited) {
					if(loc.equals(new Location(posX, posY))) {
						return true;
					}
				}
			}
		}
		
		System.out.println("Tour ends at [" + piece.x() + "][" + piece.y() + "]");
		return false;
	}
	
	/**
	 * Determines the next move the piece should make in the tour.
	 * 
	 * @param bestLoc the location for the piece to move to.
	 * @return the location of the next move.
	 */
	public Location next() {
		Location bestLoc = null;
		if(ruleType == 0) {
			bestLoc = rule.warnsdorff(piece, unvisited);
		}else{ //Default case
			bestLoc = rule.randomMove(piece, unvisited);
		}
		
		unvisited.remove(bestLoc);
		piece = bestLoc; 
		return bestLoc;
		
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Location getPiece() {
		return this.piece;
	}
	
	public ArrayList<Location> getUnvisited(){
		return this.unvisited;
	}
	
	public int[][] getOffset(){
		return this.offset;
	}
}
