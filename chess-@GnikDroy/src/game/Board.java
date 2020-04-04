package game;

import custom_exceptions.MalformedRequestException;
import pieces.*;
import player.PlayerType;

import static game.common.Constants.*;


/**
 * @author gnik
 */

public class Board {

    private Square[][] squares = new Square[BOARD_SIZE][BOARD_SIZE];

    /**
     * This initialized the board to it's original position.
     */
    public Board() {
        setSquares();
        setWhitePieces();
        setBlackPieces();
    }

    /**
     * Resets the board to it's original position
     */
    public void resetBoard() {
        setSquares();
        setWhitePieces();
        setBlackPieces();
    }

    /**
     * Initializes all the squares
     */
    private void setSquares() {
        for (int row = FIRST_POSITION; row < BOARD_SIZE; row++) {
            for (int col = FIRST_POSITION; col < BOARD_SIZE; col++) {
                squares[row][col] = new Square(new Coordinate(row, col));
            }
        }

    }

    /**
     * Initializes and assigns all white Pieces.
     */
    private void setWhitePieces() {
        squares[THIRD_POSITION][FIRST_POSITION].setPiece(new Bishop(PlayerType.WHITE));
        squares[SIXTH_POSITION][FIRST_POSITION].setPiece(new Bishop(PlayerType.WHITE));
        squares[SECOND_POSITION][FIRST_POSITION].setPiece(new Knight(PlayerType.WHITE));
        squares[SEVENTH_POSITION][FIRST_POSITION].setPiece(new Knight(PlayerType.WHITE));
        squares[FIRST_POSITION][FIRST_POSITION].setPiece(new Rook(PlayerType.WHITE));
        squares[LAST_POSITION][FIRST_POSITION].setPiece(new Rook(PlayerType.WHITE));
        squares[FOURTH_POSITION][FIRST_POSITION].setPiece(new Queen(PlayerType.WHITE));
        squares[FIFTH_POSITION][FIRST_POSITION].setPiece(new King(PlayerType.WHITE));
        squares[FIRST_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[SECOND_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[THIRD_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[FOURTH_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[FIFTH_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[SIXTH_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[SEVENTH_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));
        squares[LAST_POSITION][SECOND_POSITION].setPiece(new Pawn(PlayerType.WHITE));

    }

    /**
     * Initializes and sets all Black Pieces.
     */
    private void setBlackPieces() {
        squares[THIRD_POSITION][LAST_POSITION].setPiece(new Bishop(PlayerType.BLACK));
        squares[SIXTH_POSITION][LAST_POSITION].setPiece(new Bishop(PlayerType.BLACK));
        squares[SECOND_POSITION][LAST_POSITION].setPiece(new Knight(PlayerType.BLACK));
        squares[SEVENTH_POSITION][LAST_POSITION].setPiece(new Knight(PlayerType.BLACK));
        squares[FIRST_POSITION][LAST_POSITION].setPiece(new Rook(PlayerType.BLACK));
        squares[LAST_POSITION][LAST_POSITION].setPiece(new Rook(PlayerType.BLACK));
        squares[FOURTH_POSITION][LAST_POSITION].setPiece(new Queen(PlayerType.BLACK));
        squares[FIFTH_POSITION][LAST_POSITION].setPiece(new King(PlayerType.BLACK));
        squares[FIRST_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[SECOND_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[THIRD_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[FOURTH_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[FIFTH_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[SIXTH_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[SEVENTH_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));
        squares[LAST_POSITION][SEVENTH_POSITION].setPiece(new Pawn(PlayerType.BLACK));

    }


    /**
     * Returns all the squares on the board.
     *
     * @return Square[][] A dimensional array of all the Squares
     */
    public Square[][] getSquares() {
        return squares;
    }


    /**
     * This gets the square with a specific coordinate.
     *
     * @param coordinate Coordinate of the square
     * @return Square The Square object
     */
    public Square getSquare(Coordinate coordinate) {
        Square result = null;
        for (int row = FIRST_POSITION; row < BOARD_SIZE; row++) {
            for (int col = FIRST_POSITION; col < BOARD_SIZE; col++) {
                if (squares[row][col].getCoordinate().equals(coordinate)) {
                    result = squares[row][col];
                    return result;
                }
            }
        }

        throw new MalformedRequestException("Requested coordinate is invalid");
    }

    /**
     * Makes a Move from initial Coordinate to final coordinate
     *
     * @param initCoordinate  The initial Coordinate.
     * @param finalCoordinate The final Coordinate.
     */
    public void makeMove(Coordinate initCoordinate, Coordinate finalCoordinate) {
        makeMove(getSquare(initCoordinate), getSquare(finalCoordinate));
    }

    /**
     * This set the piece in the specified coordinate
     *
     * @param coordinate Coordinate of the piece
     * @param piece      The piece object to be set.
     */
    public void setPiece(Coordinate coordinate, Piece piece) {
        getSquare(coordinate).setPiece(piece);
    }

    /**
     * Captures the piece that is present in the square.
     *
     * @param square The square of the piece
     */
    public void capturePiece(Square square) {
        if (square.isOccupied()) {
            square.releasePiece();
        }
    }

    /**
     * This makes a move from a square to another. **Move may be invalid**
     *
     * @param initSquare  The initial Square.
     * @param finalSquare The final Square
     */
    public void makeMove(Square initSquare, Square finalSquare) {
        //Has a piece been captured;
        if (finalSquare.isOccupied()) {
            capturePiece(finalSquare);
        }
        //Make the move
        finalSquare.setPiece(initSquare.getPiece());
        initSquare.releasePiece();
    }

    /**
     * This prints the board in the command line.
     */
    public void printBoard() {
        for (int col = LAST_POSITION; col >= FIRST_POSITION; col--) {
            for (int row = FIRST_POSITION; row < BOARD_SIZE; row++) {
                System.out.print(squares[row][col].getPieceString() + " ");
            }
            System.out.print('\n');
        }
    }

}
