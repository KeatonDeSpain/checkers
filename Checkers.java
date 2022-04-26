import javax.swing.JFrame;

public class Checkers {

	public static void main(String[] args) {
		System.out.println("");
		JFrame frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PiecePanel(8, 8));
		frame.pack();
		frame.setVisible(true);
	}

}
