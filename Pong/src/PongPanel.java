import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.Font;

public class PongPanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Ent> ball = new ArrayList<Ent>(); 
	Ent leftPaddle;
	Ent rightPaddle;
	Thread thread;
	Animate animate;
	int size = 25;
	int leftPlayer = 0;
	int rightPlayer = 0;
	Random randomx = new Random();
	Random randomy = new Random();
	Random sign = new Random();
	int signpm;
	boolean start = false;
	String startPhrase = "Press enter to start, whoever gets 10 points wins.";
	PongPanel(){
		leftPaddle = new Ent(10, 200, 5, 125, "ppaddle.png");
		rightPaddle = new Ent(880, 200, 5, 125, "ppaddle.png");
		ball.add(new Ent(450,250,25,25,"ball.png"));
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Ent b : ball) 
			b.draw(g, this);
		leftPaddle.draw(g, this);
		rightPaddle.draw(g, this);
	
		//scoring system//
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 16));
		g.drawString("Player 1: " + leftPlayer, 10, 20);
		g.drawString("Player 2: " + rightPlayer, 800, 20);
		
		if(!start) {
			g.setColor(Color.black);
			g.setFont(new Font("arial", Font.BOLD, 18));
			g.drawString(startPhrase, 240, 200);
		} else {
			startPhrase =" ";
		}
		
		for(Ent b : ball) {
			if(leftPlayer >= 10) {
				b.x = 450;
				b.y = 250;
				b.dx = 0;
				b.dy = 0;
				g.setColor(Color.black);
				g.setFont(new Font("arial", Font.BOLD, 16));
				g.drawString("Player 1 wins", 420, 200);
				//g.drawString("Press enter to play again", 390, 300);
				//start = false;
			}
			if(rightPlayer >= 10) {
				b.x = 450;
				b.y = 250;
				b.dx = 0;
				b.dy = 0;
				g.setColor(Color.black);
				g.setFont(new Font("arial", Font.BOLD, 16));
				g.drawString("Player 2 wins", 420, 200);
				//g.drawString("Press enter to play again", 390, 300);
				//start = false;
			}
		}
	}

	public void update() {
		for(Ent ba : ball) {
			ba.y+=ba.dy;			
			if(ba.y>(getHeight()-size) && ba.dy>0 || ba.y<0)
				ba.dy*=-1;
			ba.x+=ba.dx;
			if(ba.intersects(leftPaddle) || ba.intersects(rightPaddle)) {
				ba.dx*=-1;	
				//ba.dy*=-1;
			}
			if(ba.x<0) {
				signpm = sign.nextInt(2); //0 to 1//
				System.out.println(signpm);
				ba.x = 450;
				ba.y = 250;
				rightPlayer+=1;
				ba.dx = -(randomx.nextInt(2) + 3);
				if(signpm == 0) {
					ba.dy = randomx.nextInt(2) + 3;
				} else if(signpm == 1) {
					ba.dy = -(randomx.nextInt(2) + 3);
				}
			}
			if(ba.x>900) {
				signpm = sign.nextInt(2); //0 to 1//
				System.out.println(signpm);
				ba.x = 450;
				ba.y = 250;	
				leftPlayer+=1;
				ba.dx = randomx.nextInt(2) + 3;
				if(signpm == 0) {
					ba.dy = randomx.nextInt(2) + 3;
				} else if(signpm == 1){
					ba.dy = -(randomx.nextInt(2) + 3);
				}
			}
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//if(leftPlayer>= 10 || rightPlayer >= 10) {
			//	leftPlayer = 0;
			//	rightPlayer = 0;
			//}
			if(!start) {
				animate = new Animate(this);
				thread = new Thread(animate);
				thread.start();
				start = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_W && leftPaddle.y > 0) {
			leftPaddle.y-=25;
		}
		if(e.getKeyCode() == KeyEvent.VK_S && leftPaddle.y < (getHeight()-leftPaddle.height)) {
			leftPaddle.y+=25;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && rightPaddle.y > 0) {
			rightPaddle.y-=25;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && rightPaddle.y < (getHeight()-rightPaddle.height)) {
			rightPaddle.y+=25;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
