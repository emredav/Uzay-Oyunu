import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


class spaceship{
	private int x,y;
	
	public spaceship(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
class alien{
	private int x,y;
	
	public alien(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
class Ates{
	private int x,y;
	private int angle;
	
	public Ates(int x,int y,int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
}



public class Game extends JPanel implements KeyListener,ActionListener {
	Timer timer = new Timer(5,this);
	spaceship ship = new spaceship(10,10);
//	private BufferedImage image;
	Image img = Toolkit.getDefaultToolkit().getImage("gora.png");
	
	static String name;
	static String surname;
	
	JPanel info;
	JLabel Angle;
	JLabel AngleText;
	JLabel ScoreText;
	JLabel Score;
	JLabel TimeText;
	JLabel Time;
	JLabel Level;
	JLabel LevelText;
	
	double time = 0;
	boolean rotation = false;
	int angle = 0;
	int score = 0;
	int level = 1;
	
	
	private ArrayList<alien> aliens  = new ArrayList<alien>();
	private ArrayList<Ates> atesler  = new ArrayList<Ates>();
	
	public void hit() {
		for (Ates ates:atesler) {
			for(alien alien:aliens) {
				if(new Rectangle(ates.getX()+40,ates.getY()+15,20,10).intersects(new Rectangle(alien.getX(), alien.getY(), 40, 40))) {
					aliens.remove(alien);
					atesler.remove(ates);
					score++;
				}
			}
		}
	}
	
	
	private static void display() {
        String[] items = {"One", "Two", "Three", "Four", "Five"};//deneme
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField field1 = new JTextField("");
        JTextField field2 = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(combo);
        panel.add(new JLabel("Name"));
        panel.add(field1);
        panel.add(new JLabel("Surname"));
        panel.add(field2);
        
        Object[] options = {"Start","Cancel"};
        
        int login = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]); 
        
        if (login == JOptionPane.YES_OPTION) {
            
        	name = field1.getText();
        	surname = field2.getText();
        	
        	System.out.println(combo.getSelectedItem()
                + " " + name
                + " " + surname);
        } else {
            System.out.println("Cancelled");
            System.exit(0);
        }
    }
	
	public Game() throws IOException {
		timer.start();
		display();
		
		setLayout(new BorderLayout());
		AngleText = new JLabel("Angle:");
		Angle = new JLabel("0");
		
		ScoreText = new JLabel("Score:");
		Score = new JLabel(Integer.toString(score));
		
		TimeText = new JLabel("Time");
		Time = new JLabel(timer.toString());
		
		LevelText = new JLabel("Level");
		Level = new JLabel("1");
		
		info = new JPanel(new GridLayout());
		add(info,BorderLayout.SOUTH);
		
		info.add(AngleText);
		info.add(Angle);
		info.add(ScoreText);
		info.add(Score);
		info.add(TimeText);
		info.add(Time);
		info.add(LevelText);
		info.add(Level);
		
		aliens.add(new alien(700,10));
//		aliens.add(new alien(700,250));
//		aliens.add(new alien(700,500));
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
		time += 5;
		String angleString=Integer.toString(angle);
		Angle.setText(angleString);
		
		Level.setText(Integer.toString(level));
		
		String scoreString=Integer.toString(score);
		Score.setText(scoreString);
		
		String timeString=Double.toString(time/1000.0);
		if(Double.parseDouble(timeString)>10.0) {
			timeString = timeString.substring(0, 4);
		}else {
			timeString = timeString.substring(0, 3);
		}
		Time.setText(timeString);
		
		
		if(aliens.size()==0) {
			
			if(level == 1) {
				level++;
				score+=10;
				aliens.add(new alien(600,10));
				aliens.add(new alien(600,250));
				aliens.add(new alien(600,500));
			}else if(level == 2) {
				level++;
				score+=10;
				aliens.add(new alien(500,10));
				aliens.add(new alien(500,250));
				aliens.add(new alien(500,500));
//				aliens.add(new alien(400,10));
//				aliens.add(new alien(400,250));
//				aliens.add(new alien(400,500));
			}else if(level == 3) {
				score+=10;
				timer.stop();
			    try {
			        FileWriter myWriter = new FileWriter("scores.txt",true);
			        myWriter.write(name + " " + surname +" Score: "+ score+ "\n");
			        myWriter.close();
			        System.out.println("Successfully wrote to the file.");
			      } catch (IOException e) {
			        System.out.println("An error occurred.");
			        e.printStackTrace();
			      }
			    
			    ArrayList<String> allScores = new ArrayList<String>();
			    
			    try {
			        File myObj = new File("scores.txt");
			        Scanner myReader = new Scanner(myObj);
			        while (myReader.hasNextLine()) {
			          String data = myReader.nextLine();
			          
			          allScores.add(data);
			          
			          System.out.println(data);
			        }
			        myReader.close();
			      } catch (FileNotFoundException e) {
			        System.out.println("An error occurred.");
			        e.printStackTrace();
			      }
			    
			    allScores.toArray();
			    
			    JList scoreList = new JList(allScores.toArray());
			    
			    JPanel scorePanel = new JPanel(new GridLayout(0, 1));
			    scorePanel.add(scoreList);
			    
			    JOptionPane.showMessageDialog(this, scoreList, "SCOREBOARD",JOptionPane.PLAIN_MESSAGE);
			    
				System.exit(0);
			}
			
		}
		
		g.setColor(Color.blue);
		
		g.fillRect(ship.getX(), ship.getY(), 40, 40);
		
		
		g.setColor(Color.green);
		for(alien alien:aliens) {
			g.fillRect(alien.getX(), alien.getY(), 40, 40);
		}
		
		
		for(Ates ates:atesler) {
			if(ates.getX()>800) {
				atesler.remove(ates);
			}
		}
		
		g.setColor(Color.red);
		for(Ates ates:atesler) {
			g.fillRect(ates.getX()+40,ates.getY()+15 ,20 ,10 );
		}
		hit();
		
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_UP) {
			System.out.println(ship.getX()+" "+ship.getY());
			if(ship.getY()<=0) {
				ship.setY(0);
			}else {
				ship.setY(ship.getY()-10);
			}
			
		}else if(c == KeyEvent.VK_DOWN) {
			System.out.println(ship.getX()+" "+ship.getY());
			if(ship.getY()>=560) {
				ship.setY(560);
			}else {
				ship.setY(ship.getY()+10);
			}
		}else if(c == KeyEvent.VK_Z) {
			atesler.add(new Ates(ship.getX()+15,ship.getY(),angle));
			score--;
		}
		else if(c == KeyEvent.VK_RIGHT) {
			angle++;
		}else if(c == KeyEvent.VK_LEFT) {
			angle--;
		}
		
		
		
		
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Random rand = new Random();
		for(Ates ates:atesler) {
			ates.setX(ates.getX()+4);
			ates.setY(ates.getY()+ates.getAngle());
		}
		
		if(time%400==0) {
			if(rotation == false) {
				for(alien alien:aliens) {
					if(alien.getY()>=500) {
						alien.setY(500);
					}else {
						alien.setY(alien.getY()+rand.nextInt(40));
						rotation = true;
					}
				}
			}else {
				for(alien alien:aliens) {
					if(alien.getY()<=0) {
						alien.setY(40);
					}else {
						alien.setY(alien.getY()-rand.nextInt(40));
						rotation = false;
					}
				}
			}
		}
		
		
		repaint();

	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
