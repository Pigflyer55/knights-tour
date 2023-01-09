package chess;

public enum Piece {
	KNIGHT(new int[][] {{2,1},{-2,-1},{1,2},{2,-1},{-1,2},{1,-2},{-2,1},{-1,-2}}), 
	KING(new int[][] {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}}),
	PAWN(new int[][] {{0,1}}),
	MADJACK(new int[][] {{-1,0},{2,1},{-2,0},{1,1},{2,-1}}); //Joke piece
	
	private final int[][] offset;
	
	private Piece(int[][] offset) {
		this.offset = offset;
	}
	
	public int[][] getOffset() {
		return this.offset;
	}
	
}
