import java.io.IOException;

import javax.swing.JFrame;

public class OyunEkrani extends JFrame {
	
	public OyunEkrani(String title) {
		super(title);
	}
	

	public static void main(String[] args) throws IOException {
		
		
		OyunEkrani ekran = new OyunEkrani("Proje");
		ekran.setResizable(false);
		ekran.setFocusable(false);
		
		ekran.setSize(800, 600);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game game = new Game();
		
		game.requestFocus();
		
		game.addKeyListener(game);
		
		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);
		
		
		ekran.add(game);
		ekran.setLocationRelativeTo(null);
		
		ekran.setVisible(true);
		
		
	}

}
