import java.awt.Color;

import javax.swing.JButton;

public class PieceCreate extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Color currentColor;
	public static enum Piece {BLACK, RED, WHITE, GRAY};
	private boolean isQueen = false;
	
	public PieceCreate () {
		this.setBackground(currentColor);
	}
	
	public void colorGray() {
		currentColor = Color.lightGray;
		this.setBackground(currentColor);
	}
	
	public void colorRed() {
		currentColor = Color.red;
		this.setBackground(currentColor);
	}
	
	public void colorBlack() {
		currentColor = Color.black;
		this.setBackground(currentColor);
	}
	
	public void colorWhite() {
		currentColor = Color.white;
		this.setBackground(currentColor);
	}
	
	public Color getColor() {
		return currentColor;
	}

	public void setQueen() {
		this.isQueen = true;
	}

	public boolean isQueen() {
		return isQueen;
	}
	
	public boolean validMove(int pieceX, int pieceY, int moveX, int moveY, Color c) {
		boolean validMove = false;
		int changeX = moveX - pieceX;
		int changeY = moveY - pieceY;
		if(c == Color.black) {
			if(this.isQueen()) {
				if((changeX == 1 || changeX == -1) && (changeY == -1 || changeY == 1)) {
					validMove = true;
				}
			}
			if((changeX == 1 || changeX == -1) && changeY == -1) {
				validMove = true;
			}
		}
		else if(c == Color.red) {
			if(this.isQueen()) {
				if((changeX == 1 || changeX == -1) && (changeY == -1 || changeY == 1)) {
					validMove = true;
				}
			}
			if((changeX == 1 || changeX == -1) && changeY == 1) {
				validMove = true;
			}
		}
		return validMove;
	}
	
	public boolean validMove(int pieceX, int pieceY, int moveX, int moveY, Color c, Color c2) {
		boolean valid = false;
		int changeX = moveX - pieceX;
		int changeY = moveY - pieceY;
		if(c == Color.black && c2 == Color.red) {
			if((changeX == 2 || changeX == -2) && changeY == -2) {
				valid = true;
			}
		}
		else if(c == Color.red && c2 == Color.black) {
			if((changeX == 2 || changeX == -2) && changeY == 2) {
				valid= true;
			}
		}
		return valid;
	}
	
	public boolean validMove(int pieceX, int pieceY, int moveX, int moveY, Color c, Color c2, Color c3) {
		boolean valid = false;
		int changeX = moveX - pieceX;
		int changeY = moveY - pieceY;
		if(c == Color.black && c2 == Color.red && c3 == Color.red) {
			if((changeX == 2 || changeX == -2 || changeX == 0 || changeX == 4 || changeX == -4) && changeY == -4) {
				valid = true;
			}
		}
		else if(c == Color.red && c2 == Color.black && c3 == Color.black) {
			if((changeX == 2 || changeX == -2 || changeX == 0 || changeX == 4 || changeX == -4) && changeY == 4) {
				valid= true;
			}
		}
		return valid;
	}
}
