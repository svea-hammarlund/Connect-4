package hw3;

public class Board {
	// TODO
	// Add whatever private fields you need here.
	// Remember, only variables of type int, char, boolean, and 1D and 2D arrays
	// of these types are allowed.
	//
	// As always, you may also add private helper methods to the class.  That will
	// likely be very useful on this assignment.
	private char[][] board;
	private char currentPlayer;
	private boolean gameOver;
	private void switchPlayer() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}
	private void checkGameOver(int row, int col) {
		if (isWinningMove(row, col)) {
			gameOver = true;
		} else if (isBoardFull()) {
			gameOver = true;
		}
	}
	private boolean isWinningMove(int row, int col) {
		char player = board[row][col];
		int count = 0;
		for(int i = col -3; i <= col +3; i++) {
			if(i >= 0 && i < 7 && board[row][i] == player) {
				count++;
				if (count == 4) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		count = 0;
		for (int i = row -3; i <= row +3; i++) {
			if (i >= 0 && i < 6 && board[i][col] == player) {
				count++;
				if (count == 4) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		count = 0;
		for (int i= -3; i <= 3; i++) {
			int r = row + i;
			int c = col + i;
			if (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
				count ++;
				if (count == 4) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		count = 0;
		for (int i = -3; i <= 3; i++) {
			int r = row -i;
			int c = col +i;
			if (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
				count++;
				if (count == 4) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		return false;
	}
	private boolean isBoardFull() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				if (board[row][col] == '.') {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Constructs a new empty connect 4 game board with player X being the first player
	 * and player 'O' being the second player.
	 */
	public Board() {
		board = new char [6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col]='.';
			}
		}
		currentPlayer = 'X';
		gameOver = false;
	}

	/**
	 * Gets the current player (either 'X' or 'O')
	 * 
	 * @return the current player
	 */
	public char currentPlayer() {
		return currentPlayer;
	}

	/**
	 * The current player makes a move in a given column if it is a valid move.
	 * Throws an exception if the game is already over.
	 * 
	 * @param column the column in which to make a move.  For the move to be valid,
	 * the column value must an {@code int} between 1 and 7 inclusive, and
	 * there must have been fewer than 6 moves already made in the given column.
	 * @return {@code true} if the move is valid and false if it is not valid.
	 * @throws RuntimeException if the game is already over.
	 */
	public boolean play(int col) {
		if (gameStatus() !='U') {
			throw new RuntimeException("The game has already ended.");
		}
		if (col < 1 || col > 7 || board[0][col - 1] != '.') {
			return false;
		}
		int row = 5;
		while(row >= 0 && board[row][col - 1] != '.') {
			row--;
		}
		board[row][col - 1] = currentPlayer;
		checkGameOver(row, col - 1);
		if (!gameOver) {
			switchPlayer();
		}
		return true;
	}

	/**
	 * Determine the status of the game.
	 * 
	 * @return {@code 'X'} or {@code 'O'} if either player has won, {@code 'D'} if
	 * the game is a draw, and {@code 'U'} if the game is still undecided.
	 */
	public char gameStatus() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				if (board[row][col] != '.' && isWinningMove(row,col)) {
					return board[row][col];
				}
			}
		}
		if (isBoardFull()) {
			return 'D';
		}
		return 'U';
	}

	/**
	 * Construct a string that depicts the sate of the game.
	 * (See the writeup for what that string should look like.)
	 * 
	 * @return a string depicting the game board
	 */
	public String toString() {
		String result = "";
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col <7; col++) {
				result += board[row][col] + " ";
			}
			result += "\n";
		}
		result += "_ _ _ _ _ _ _\n";
		result += "1 2 3 4 5 6 7\n";
		return result;
	}
}
