import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class PiecePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public PieceCreate.Piece[][] pieces;
	public PieceCreate[][] piecesBoard;
	private int colorIndex;
	private ArrayList<Point> points = new ArrayList<Point>();
	private JLabel turn;

	public PiecePanel(int width, int height) {
		this.setLayout(new BorderLayout(width, height));
		this.setPreferredSize(new Dimension(600,700));
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(width, height));
		gamePanel.setPreferredSize(new Dimension(600,600));

		colorIndex = 0;
		pieces = new PieceCreate.Piece[height][width];
		piecesBoard = new PieceCreate[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				piecesBoard[i][j] = new PieceCreate();
				if((colorIndex % 2 == 0 && colorIndex < 7) || (colorIndex % 2 != 0 && colorIndex > 8 && colorIndex < 16) || (colorIndex % 2 == 0 && colorIndex < 23 && colorIndex > 15)) {
					piecesBoard[i][j].colorRed();
					pieces[i][j] = PieceCreate.Piece.RED;
				}
				else if((colorIndex % 2 == 0 && colorIndex > 47 && colorIndex < 55) || (colorIndex % 2 != 0 && colorIndex > 40 && colorIndex < 48) || (colorIndex % 2 != 0 && colorIndex < 64 && colorIndex > 56)) {
					piecesBoard[i][j].colorBlack();
					pieces[i][j] = PieceCreate.Piece.BLACK;
				}
				else if((colorIndex % 2 != 0 && colorIndex > 24 && colorIndex < 32) || (colorIndex % 2 == 0 && colorIndex > 31 && colorIndex < 40)) {
					piecesBoard[i][j].colorWhite();
					pieces[i][j] = PieceCreate.Piece.WHITE;
				}
				else {
					piecesBoard[i][j].colorGray();
					pieces[i][j] = PieceCreate.Piece.GRAY;
				}
				piecesBoard[i][j].setPreferredSize(new Dimension(70,70));
				points.add(new Point(i,j));
				gamePanel.add(piecesBoard[i][j]);
				piecesBoard[i][j].addActionListener(new PieceListener());
				colorIndex++;
			}
		}
		this.add(gamePanel);

		JPanel controlsPanel = new JPanel();

		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());

		JLabel colorTurn = new JLabel("Turn: RED");
		turn = colorTurn;

		controlsPanel.add(newGame);
		controlsPanel.add(colorTurn);

		this.add(controlsPanel, BorderLayout.SOUTH);
	}


	private class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			points.clear();
			colorIndex = 0;
			for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if((colorIndex % 2 == 0 && colorIndex < 7) || (colorIndex % 2 != 0 && colorIndex > 8 && colorIndex < 16) || (colorIndex % 2 == 0 && colorIndex < 23 && colorIndex > 15)) {
					piecesBoard[i][j].colorRed();
					pieces[i][j] = PieceCreate.Piece.RED;
				}
				else if((colorIndex % 2 == 0 && colorIndex > 47 && colorIndex < 55) || (colorIndex % 2 != 0 && colorIndex > 40 && colorIndex < 48) || (colorIndex % 2 != 0 && colorIndex < 64 && colorIndex > 56)) {
					piecesBoard[i][j].colorBlack();
					pieces[i][j] = PieceCreate.Piece.BLACK;
				}
				else if((colorIndex % 2 != 0 && colorIndex > 24 && colorIndex < 32) || (colorIndex % 2 == 0 && colorIndex > 31 && colorIndex < 40)) {
					piecesBoard[i][j].colorWhite();
					pieces[i][j] = PieceCreate.Piece.WHITE;
				}
				else {
					piecesBoard[i][j].colorGray();
					pieces[i][j] = PieceCreate.Piece.GRAY;
				}
				points.add(new Point(i,j));
				colorIndex++;
			}
		}
		}
	}


	Point prevClick;
	PieceCreate.Piece prevColor;
	int clickMoves = 0;
	private class PieceListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			PieceCreate click = (PieceCreate) e.getSource();
			int clickY = click.getY() / 75;
			int clickX = click.getX() / 75;
			if(click.getColor() == Color.red && pieces[clickY][clickX] == PieceCreate.Piece.RED) {
				prevClick = new Point(clickX, clickY);
				prevColor = PieceCreate.Piece.RED;
			}
			if(click.getColor() == Color.black && pieces[clickY][clickX] == PieceCreate.Piece.BLACK) {
				prevClick = new Point(clickX, clickY);
				prevColor = PieceCreate.Piece.BLACK;
			}
			int prevY = (int) prevClick.getY();
			int prevX = (int) prevClick.getX();
			int changeY = clickY - prevY;
			int changeX = clickX - prevX;
			
			if(click.getColor() == Color.white && pieces[clickY][clickX] == PieceCreate.Piece.WHITE && prevColor == PieceCreate.Piece.RED && clickMoves % 2 == 0) {
				if(piecesBoard[prevY][prevX].validMove(prevX, prevY, clickX, clickY, Color.red)) {
					piecesBoard[prevY][prevX].colorWhite();
					pieces[prevY][prevX] = PieceCreate.Piece.WHITE;
					piecesBoard[clickY][clickX].colorRed();
					pieces[clickY][clickX] = PieceCreate.Piece.RED;
					
					if(clickY == 7) {
						piecesBoard[clickY][clickX].setQueen();
					}
					
					prevClick = new Point(clickX, clickY);
					clickMoves++;
					turn.setText("Turn: BLACK");
				}
				else if(piecesBoard[prevY][prevX].validMove(prevX, prevY, clickX, clickY, Color.red, piecesBoard[prevY + (changeY / 2)][prevX + (changeX / 2)].getColor())) {
					piecesBoard[prevY][prevX].colorWhite();
					pieces[prevY][prevX] = PieceCreate.Piece.WHITE;
					piecesBoard[clickY][clickX].colorRed();
					pieces[clickY][clickX] = PieceCreate.Piece.RED;

					piecesBoard[prevY + (changeY / 2)][prevX + (changeX / 2)].colorWhite();
					pieces[prevY + (changeY / 2)][prevX + (changeX / 2)] = PieceCreate.Piece.WHITE;

					if(clickY == 7) {
						piecesBoard[clickY][clickX].setQueen();
					}

					prevClick = new Point(clickX, clickY);
					clickMoves++;
					turn.setText("Turn: BLACK");
				}
			}
			else if(click.getColor() == Color.white && pieces[clickY][clickX] == PieceCreate.Piece.WHITE && prevColor == PieceCreate.Piece.BLACK && clickMoves % 2 != 0) {
				if(piecesBoard[prevY][prevX].validMove(prevX, prevY, clickX, clickY, Color.black)) {
					piecesBoard[prevY][prevX].colorWhite();
					pieces[prevY][prevX] = PieceCreate.Piece.WHITE;
					piecesBoard[clickY][clickX].colorBlack();
					pieces[clickY][clickX] = PieceCreate.Piece.BLACK;

					if(clickY == 0) {
						piecesBoard[clickY][clickX].setQueen();
					}
					
					prevClick = new Point(clickX, clickY);
					clickMoves++;
					turn.setText("Turn: RED");
				}
				else if(piecesBoard[prevY][prevX].validMove(prevX, prevY, clickX, clickY, Color.black, piecesBoard[prevY + (changeY / 2)][prevX + (changeX / 2)].getColor())) {
					piecesBoard[prevY][prevX].colorWhite();
					pieces[prevY][prevX] = PieceCreate.Piece.WHITE;
					piecesBoard[clickY][clickX].colorBlack();
					pieces[clickY][clickX] = PieceCreate.Piece.BLACK;

					piecesBoard[prevY + (changeY / 2)][prevX + (changeX / 2)].colorWhite();
					pieces[prevY + (changeY / 2)][prevX + (changeX / 2)] = PieceCreate.Piece.WHITE;

					if(clickY == 0) {
						piecesBoard[clickY][clickX].setQueen();
					}

					prevClick = new Point(clickX, clickY);
					clickMoves++;
					turn.setText("Turn: RED");
				}
			}
		}		
	}
}
