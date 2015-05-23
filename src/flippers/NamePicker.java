package flippers;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import static java.lang.System.out;

public class NamePicker extends JPanel implements ActionListener{
	//KeyListener,MouseListener,MouseMotionListener {
	static private String TITLE="Name Picker";
	static private Point DIM=new Point(700,150);
	static private ArrayList<String> dataList = new ArrayList<String>();
	public static final int NUMBER_OF_FLIPPERS = 25;
	
	String fileName = "names.txt";
	CustomField input; 	
	String name;
	Display displayFlipperPanel;
	Timer timer;
	JTextArea txtAreaData;
	
	public NamePicker() {
	    timer = new Timer(50, this);
	    displayFlipperPanel = new Display(timer, NUMBER_OF_FLIPPERS);
	    loadLetters();
		displayFlipperPanel.printGreeting("Welcome");
	}
	public void setInteractedComponents(CustomField txtUserInput, JTextArea txtAreaData) {
		this.input = txtUserInput;
		this.txtAreaData = txtAreaData;
	}
	public void btnGenerateActionPerformed(ActionEvent arg) {
        if (dataList.size() == 0) {
        	name = "please load data below";
        } else {
        	String temp;
	        do {
	            temp = dataList.get(random(0, dataList.size()-1)); 
	        } while (temp.equals(name));
	        name = temp;    
	        dataList.remove(name);
        }
		displayFlipperPanel.print(name.toUpperCase()); 
		input.requestFocusInWindow();
        JButton but = (JButton)arg.getSource();
        but.setToolTipText("Current word displayed is '" + name + '\'');
    }
	public void btnDisplayActionPerformed() {
		displayFlipperPanel.print(input.getText());
		input.setText("");
		input.requestFocusInWindow();
		repaint();
	}
	public void btnClearActionPerformed() {
		displayFlipperPanel.print(""); 
		input.setText(""); 
		repaint();
		input.requestFocusInWindow();
	}
	public void loadDataFromFile(File givenFile) {
		clearDataList();
		try {
            Scanner fileScanner = new Scanner(givenFile);
            String input = "", temp;
            while (fileScanner.hasNextLine()) {
            	temp = fileScanner.nextLine();
            	dataList.add(temp);
            	input += temp + "\n";
            }
            txtAreaData.setText(input);
            displayFlipperPanel.printf("Loaded %d lines", dataList.size());
        } catch (FileNotFoundException e) {
            System.err.println("can't load file");
        }
        
    }
	public void loadDataFromText(String[] strings) {
		clearDataList();
		
		if (strings.length == 0 || (strings.length > 1 && strings[0].length() == 0)) {
			// invalid data
			displayFlipperPanel.print("no data loaded");
			return;
		}

		for (String s: strings) {
			dataList.add(s);
		}
		displayFlipperPanel.print(String.format("Loaded %d entries", dataList.size()));
	}
	public void clearDataList() { dataList.clear(); }
	
    private void add(JComponent ... comps){
	    for (JComponent c: comps){
	        super.add(c);
	    }
	}
	private void loadLetters() {
		for (char c = 'A'; c <= 'Z'; c++){
			String filename = c + ".png";
			displayFlipperPanel.setImageAndChar(loadImage(filename), c);
		}
		for (char c = '0'; c <= '9'; c++){
			String filename = c + ".png";
			displayFlipperPanel.setImageAndChar(loadImage(filename), c);
		} //*/
		
		displayFlipperPanel.setImageAndChar(loadImage("_.png"), ' ');
		displayFlipperPanel.setImageAndChar(loadImage("dot.png"), '.');
		displayFlipperPanel.updateCharSet();
		
	}

	public void actionPerformed(ActionEvent e) {
		if (displayFlipperPanel.doATickAndFlip()) timer.stop();
		repaint();
	}
	public void applyStyles(JButton ... btns){
	    for (JButton b: btns){
	        b.setFont(new Font("Segoe UI",1,18));
	    }
	}
	public void paintComponent(Graphics g){
		drawBG(g);
		displayFlipperPanel.draw(g);
	}
	
	void drawBG(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Color startCLR = new Color(5,5,5), endCLR = new Color(70,70,70);
		GradientPaint background = new GradientPaint(0, 0, startCLR, 0, 200, endCLR);
		g2d.setPaint(background);
		g2d.fillRect(0, 0, 10000, 10000);
	}

	int random(int x,int y){return (int)(Math.random()*(y-x)+x);}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	private AudioClip loadSound(String fileName) {URL url = getClass().getResource(fileName);
		return Applet.newAudioClip(url);}
	private BufferedImage loadImage(String f) {
		BufferedImage img = null;
		try {
			URL url = NamePicker.class.getResource("/resources/" + f);
			//out.println(f + url);
		    img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
			out.println(f);
		}
		return img;
	}
}