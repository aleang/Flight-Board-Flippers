package flippers;
public class Flipper {
	int[] place;	int curr, c, index;
	boolean finish = false;
	
	public Flipper(int[] i, int index){
		place = i;		this.index = index;
		curr = i[index];
	}
	public void newWord(char s) {
		c = s-64;
		if (s == 32) c = 0;
		finish = false;
	}
	public void flip() {
		if (curr != c){
			curr = (curr+1) % 27;
			place[index] = curr;
		} else {
			finish = true;
		}
	}

}
