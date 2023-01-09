package chess;

import princeton.introcs.StdDraw;
import java.util.ArrayList;

public class TourViewer3 {

	/**
	 * Draws a regular rectangular chess board of the specified size.
	 * 
	 * <p>
	 * Students will need to modify this method to draw irregular boards
	 * if their solutions allows for irregular boards. 
	 * 
	 * @param width the number of squares in the width of the board
	 * @param height the number of squares in the height of the board
	 * @param board an array containing all the coordinates of tiles in the board
	 */
	private static void drawBoard(int width, int height, ArrayList<Location> board) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		int max = Math.max(width, height);
		StdDraw.setScale(0.5, max + 0.5);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledRectangle((width + 1)/2.0, (height + 1)/2.0, width/2.0, height/2.0);
		
		for(int index = 0; index < board.size(); index++) {
			int x = board.get(index).x();
			int y = board.get(index).y();
			
			if ((x + y - 2) % 2 == 0) {
				StdDraw.setPenColor(StdDraw.BLUE);
			} else {
				StdDraw.setPenColor(StdDraw.WHITE);
			}
			StdDraw.filledSquare(x, y, 0.5);
		}
	}

	public static void main(String[] args) throws Exception {
		// edit the next line to draw a board of the size that you are testing
		int width = 10;
		int height = 10;
		int ruleType = 0; //0 is Warnsdorff rule. 1 is Random Move rule. There is a major difference between them on the pyramid board
		BoardCreator create = new BoardCreator(width, height);
		ArrayList<Location> board = create.pyramidBoard();
		Piece piece = Piece.KNIGHT;
		
		drawBoard(width, height, board);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		// create a Tour object on the next line
		Tour t = new Tour(width, height, piece.getOffset(), board, ruleType);
		
		// depending on the structure of your solution you may have to make
		// some more objects here...
		
		Location start = new Location(3, 5);
		t.startTour(start);
		Location curr = start;
		int i = 0;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			curr = new Location(next);
			// uncomment the next line to slow down the viewer; 500 is the pause time in milliseconds
			Thread.sleep(100);
			i++;
		}
		
	}
}
