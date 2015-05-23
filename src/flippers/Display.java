package flippers;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;
import static java.lang.System.out;

public class Display {
	HashMap<Character, Image> imagesHashMap;
	char[] charArray, charTileSlots;			
	ArrayList<Flipper> flippersList;
	static Timer t;
	
	public Display(javax.swing.Timer t2, int numberOfFlippers) {
		flippersList = new ArrayList<Flipper>();
		Display.t = t2;
		charTileSlots = new char[numberOfFlippers];
		imagesHashMap = new HashMap<Character, Image>();
	}
	
	void setImageAndChar(Image givenImage, char givenChar){
		imagesHashMap.put(givenChar,  givenImage);
	}
	public void printGreeting(String string) {
		
		for (int i = 0; i < charTileSlots.length; i++){
			Flipper e = new Flipper(charTileSlots, i);
			e.assignNewChar(' ');
			flippersList.add(e);
		}
		print(string);
	}

	public void printf(String format, Object ... objs) {
		print(String.format(format, objs));
	}
	public void print(String word) {
	    
	    // tidy up and sanitise 'word'
	    word = word.toUpperCase();
	    String temp = "";
	    for (char ch : word.toCharArray()) {
	    	if (Character.isLetter(ch) 
	    			|| ch == ' ' 
	    			|| ch == '.'
	    			|| Character.isDigit(ch)
	    			) 
	    		temp += ch;
	    }
	    
	    //flippersList.clear();
	    
		charArray = word.toCharArray();
		for (int i = 0; i < charTileSlots.length; i++){

			Flipper e = flippersList.get(i);
			if (i < charArray.length) {
				e.assignNewChar(charArray[i]);
			} else {
				// no more letters left
				e.assignNewChar(' ');
			}
		}
		t.start();
	}
	void draw(Graphics g){
		for (int i=0; i < charTileSlots.length; i++){
			Image j = imagesHashMap.get(charTileSlots[i]);
					//imagesArray[tileSlots[i]];
			g.drawImage(j, i*25 + 15, 15, null);
		}
		//g.drawImage(db[0], 0, 0, null);
	}

	boolean doATickAndFlip() {
		boolean allFinish = true;
		for (Flipper p: flippersList){
			p.doAFlip();
			allFinish = allFinish && p.finish;
		}
		return allFinish;
	}

	public void updateCharSet() {
		char[] charSet = new char[imagesHashMap.size()];
		int i = 0;
		for (char c: imagesHashMap.keySet()) {
			charSet[i++] = c;
		}
		java.util.Arrays.sort(charSet);
		Flipper.charSet = charSet;
		//out.printf("loaded %d images", charSet.length);
		//out.println(imagesHashMap.keySet());
	}


	
}
