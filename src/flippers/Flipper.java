package flippers;
public class Flipper {
	public static char[] charSet;
	char tileSlots[], myCurrentChar, myGoalChar;	
	int mySlotIndex, myCharIndexFromSet;
	boolean finish = false;
	
	public Flipper(char[] i, int index){
		tileSlots = i;
		this.mySlotIndex = index;
		tileSlots[mySlotIndex] = ' ';
		myCurrentChar = tileSlots[mySlotIndex];
		
	}
	public void assignNewChar(char newChar) {
		myGoalChar = newChar;
		finish = false;
	}
	public boolean doAFlip() {
		if (myCurrentChar != myGoalChar){
			myCharIndexFromSet = (myCharIndexFromSet+1) % charSet.length;
			myCurrentChar = charSet[myCharIndexFromSet];
			tileSlots[mySlotIndex] = myCurrentChar;
		} else {
			finish = true;
		}
		
		return finish;
	}
	public int getIndexFromSet(char search) {
		for (int i = 0; i < charSet.length; i++) {
			if (search == charSet[i]) return i;
		}
		return -1;
	}
}
