package demo.actualgame;

public class ConnectFourGame {
	private char[][] boardMatrix;

	public ConnectFourGame(char[][] field){
		this.boardMatrix=field;
	}
	
	public int putColor( int column, char color) {
        if (boardMatrix[0][column] != ' ')
            return -1;

        for (int row = 0; row < 7; ++row) {
            if (boardMatrix[row][column] != ' ') {
            	boardMatrix[row-1][column] = color;
                return row-1;
            }
        }
        
        boardMatrix[6][column] = color;
        
        return 6; //last row
    }
	
	private  char getWinnerInRows() {
	        for (int row = 0; row < 7; ++row) {
	            int count = 0;
	            for (int column = 1; column < 7; ++column) {
	                if (boardMatrix[row][column] != ' ' &&
	                    boardMatrix[row][column] == boardMatrix[row][column-1])
	                    ++count;
	                else
	                    count = 1;

	                if (count >= 4) {
	                    return boardMatrix[row][column];
	                }
	            }
	        }
	        return ' ';
	}
	
    private char getWinnerInColumns() {
        for (int column = 0; column < 7; ++column) {
            int count = 0;
            for (int row = 1; row < 7; ++row) {
                if (boardMatrix[row][column] != ' ' &&
                    boardMatrix[row][column] == boardMatrix[row-1][column])
                    ++count;
                else
                    count = 1;

                if (count >= 4) {
                    return boardMatrix[row][column];
                }
            }
        }
        return ' ';
    }
    
    private  char getWinnerInDiagonals() {

        for (int column = 0; column < 7; ++column) {
            int count = 0;
            for (int row = 1; row < 7; ++row) {
                if (column + row >= 7) break;
                if (boardMatrix[row][column+row] != ' ' &&
                    boardMatrix[row-1][column + row - 1] == boardMatrix[row][column+row])
                    ++count;
                else
                    count = 1;
                if (count >= 4) return boardMatrix[row][column+row];
            }
        }

        for (int row = 0; row < 7; ++row) {
            int count = 0;
            for (int column = 1; column < 7; ++column) {
                if (column + row >= 7) break;
                if (boardMatrix[row + column][column] != ' ' &&
                    boardMatrix[row+column - 1][column - 1] == boardMatrix[row + column][column])
                    ++count;
                else
                    count = 1;
                if (count >= 4) return boardMatrix[row + column][column];
            }
        }

        for (int column = 0; column < 7; ++column) {
            int count = 0;
            for (int row = 1; row < 7; ++row) {
                if (column - row < 0) break;
                if (boardMatrix[row][column-row] != ' ' &&
                    boardMatrix[row - 1][column - row + 1] == boardMatrix[row][column-row])
                    ++count;
                else
                    count = 1;
                if (count >= 4) return boardMatrix[row][column-row];
            }
        }

        for (int row = 0; row < 7; ++row) {
            int count = 0;
            for (int column = 5; column >= 0; --column) {
                if (column - row < 0) break;
                if (boardMatrix[column - row][column] != ' ' &&
                    boardMatrix[column - row - 1][column + 1] == boardMatrix[column - row][column])
                    ++count;
                else
                    count = 1;
                if (count >= 4) return boardMatrix[column - row][column];
            }
        }

        return ' ';
    }
    
    
    public char getWinner() {
        char winner = getWinnerInRows();
        
        if (winner != ' ') return winner;
        	winner = getWinnerInColumns();
        
        if(winner!= ' ') return winner;
        
        winner = getWinnerInDiagonals();
        if(winner!= ' ') return winner;
        
        // check for tie
        for (int i = 0; i < boardMatrix.length; ++i)
            for (int j = 0; j < boardMatrix[i].length; ++j)
                if (boardMatrix[i][j] == ' ') return ' ';

        return 'T';
    }

	
	

}
