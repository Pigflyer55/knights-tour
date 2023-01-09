package chess;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Rules {
	
	private int[][] offset;
	private int width;
	private int height;
	
	public Rules(int[][] offset, int width, int height) {
		this.offset = offset;
		this.width = width;
		this.height = height;
	}
	
	public Location warnsdorff(Location piece, ArrayList<Location> unvisited){
		HashMap<Location, Integer> visitable = new HashMap<>();
		Location bestLoc = null; //The next location the piece should move to.
		
		for(int[] cord: offset) {
			int posX = piece.x() + cord[0];
			int posY = piece.y() + cord[1];
			
			if(posX >= 1 && posX <= width && posY >= 1 && posY <= height) {
				Location nextPosLoc = new Location(posX, posY); //The location of a next possible move.
					
				if(unvisited.contains(nextPosLoc)) {
					visitable.put(nextPosLoc, countNext(nextPosLoc, unvisited));
				}
				
				
			}
		}
		
		for(Location checkLoc: visitable.keySet()) {
			if(bestLoc == null || visitable.get(bestLoc) == 0 || 
					visitable.get(checkLoc) < visitable.get(bestLoc) && visitable.get(checkLoc) != 0) {
				
				bestLoc = checkLoc;
			}
		}
		
		return bestLoc;
	}
	
	public Location randomMove(Location piece, ArrayList<Location> unvisited) {
		Location bestLoc = null;
		ArrayList<Location> possibleMoves = new ArrayList<>();
		Random rand = new Random();
		
		for(int[] cord: offset) {
			int posX = piece.x() + cord[0];
			int posY = piece.y() + cord[1];
			
			if(posX >= 1 && posX <= width && posY >= 1 && posY <= height) {
				Location nextPosLoc = new Location(posX, posY); //The location of a next possible move.
				
				if(unvisited.contains(nextPosLoc)) {
					possibleMoves.add(nextPosLoc);
				}
			}
		}
		
		if(possibleMoves.size() != 0) {
			bestLoc = possibleMoves.get(rand.nextInt(possibleMoves.size()));
		}
		
		return bestLoc;
	}
	
	/**
	 * Counts the amount of possible moves from a specific location.
	 * 
	 * @param pos a location on the board.
	 * @return an integer representing the amount of next possible moves
	 */
	private int countNext(Location pos, ArrayList<Location> unvisited) {
		int count = 0;
		
		for(int[] cord: offset) {
			int posX = pos.x() + cord[0];
			int posY = pos.y() + cord[1];
			
			if(posX >= 1 && posX <= width && posY >= 1 && posY <= height) {
				Location posLoc = new Location(posX, posY);
				for(Location loc: unvisited) {
					if(loc.equals(posLoc)) {
						count += 1;
						break;
					}
				}
			}
		}
		//Uncomment to see counters for each position.
		//System.out.println("posX:" + pos.x() + " posY:" + pos.y() + " counter:" + count);
		return count;
	}
}
