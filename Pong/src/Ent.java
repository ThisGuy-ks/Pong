import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Ent extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image pic;
	
	Random randomx = new Random();
	Random randomy = new Random();
	int dx = (randomx.nextInt(2) + 3);
	int dy = -(randomy.nextInt(2) + 3);
	Rectangle up, down;
	Ent(int a, int b, int w, int h, String s){
		x=a;
		y=b;
		width=w;
		height=h;
		up = new Rectangle(a,b-1,1,h);
		down = new Rectangle(a,b+h+1, 1, h);
		pic = Toolkit.getDefaultToolkit().getImage(s);
	}
	public void draw(Graphics g, Component c) {
		g.drawImage(pic, x, y, width, height, c);
	}
}
