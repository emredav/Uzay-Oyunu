import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame(String title) {
		super(title);
	}
	

	public static void main(String[] args) throws IOException {
		
		
		GameFrame frame = new GameFrame("Goralı");
		frame.setResizable(false);
		frame.setFocusable(false);
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game game = new Game();
		
		game.requestFocus();
		
		game.addKeyListener(game);
		game.addMouseListener(game);
		
		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);
		
		
		frame.add(game);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		
	}

}
