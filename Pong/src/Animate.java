
public class Animate implements Runnable{
	PongPanel pp; 
	
	Animate(PongPanel p){
		pp = p;
	}
	
	public void run(){
		while(true) {
			pp.update();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
