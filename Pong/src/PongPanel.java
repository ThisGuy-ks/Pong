import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

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
	PongPanel(){
		leftPaddle = new Ent(25, 200, 25, 150, "pongpaddle.png");
		rightPaddle = new Ent(850, 200, 25, 150, "pongpaddle.png");
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
	}

	public void update() {
		for(Ent ba : ball) {
			ba.y+=ba.dy;			
			if(ba.y>(getHeight()-size) && ba.dy>0 || ba.y<0)
				ba.dy*=-1;
			ba.x+=ba.dx;
			if(ba.intersects(leftPaddle) || ba.intersects(rightPaddle))
				ba.dx*=-1;	
			if(ba.x<0 || ba.x>900) {
				ba.x = 450;
				ba.y = 250;		
			}
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
		}
		if(e.getKeyCode() == KeyEvent.VK_W && leftPaddle.y > 0) {
			leftPaddle.y-=20;
		}
		if(e.getKeyCode() == KeyEvent.VK_S && leftPaddle.y < (getHeight()-leftPaddle.height)) {
			leftPaddle.y+=20;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && rightPaddle.y > 0) {
			rightPaddle.y-=20;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && rightPaddle.y < (getHeight()-rightPaddle.height)) {
			rightPaddle.y+=20;
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
