package flippers;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Timer;

public class Display {
	Image[] db = new Image[27];
	int count = 0;		int[] place;
	char[] set;			ArrayList<Flipper> f;
	static Timer t;
	
	public Display(javax.swing.Timer t2, int numberOfFlippers) {
		f = new ArrayList<Flipper>();
		this.t = t2;
		place = new int[numberOfFlippers];
	}
	
	void giveImg(Image i, char c){
		if (c == 32) {
			db[0] = i;
		} else {
			db[c-64] = i;
		} count++;
	}
	
	public void newWord(String word) {
	    t.start();
	    f.clear();
	   
	    String temp = "";
	    // sanitise 'word'
	    for (char ch : word.toCharArray()) {
	    	if (Character.isLetter(ch) || ch == ' ') temp += ch;
	    }
	    word = temp.toUpperCase();
	    
		set = word.toCharArray();
		for (int i=0; i<place.length; i++){
			//place[i] = set[i]-64;
			//if (set[i] == 32) place[i] = 0;
			Flipper e = new Flipper(place, i);
			if (i < set.length) {
				e.newWord(set[i]);
			} else {
				e.newWord(' ');
			}
			f.add(e);
		}
		//place = new int[20];	
	}
	void draw(Graphics g){
		for (int i=0; i < place.length; i++){
			Image j = db[place[i]];
			g.drawImage(j, i*25 + 15, 15, null);
		}
		//g.drawImage(db[0], 0, 0, null);
	}
	void println(Object o){System.out.println(o.toString());}
	void println(){System.out.println();}
	void print(Object o){System.out.print(o.toString());}

	boolean timer() {
		boolean allFinish = true;
		for (Flipper p: f){
			p.flip();
			allFinish = allFinish && p.finish;
		}
		return allFinish;
	}

	
}
