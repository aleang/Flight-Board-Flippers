package flippers;

import static java.lang.System.out;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Dimension;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class MyApplication extends JFrame {

	private JPanel contentPane;
	private NamePicker flipperPanel;
	private CustomField txtUserInput;
	public JButton btnDisplay, btnPickOne, btnClear,
	btnOpenFile, btnClearData;
	public JPanel topBarPanel, dataPanelMenu;
	final JTextArea txtAreaData;
	private JButton btnMyHomepage;
	private JPanel bottomBarPanel;
	private JPanel mainPanel;
	public static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789. ";
	
	/**
	 * Launch the MyApplication.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MyApplication frame = new MyApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public MyApplication() {
		
		// Initialise application layout **************************************
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Board Flight Flipper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		// end ****************************************************************
		
		
		// Create objects *****************************************************
		txtAreaData = new JTextArea();
		flipperPanel = new NamePicker();
		MyDragDropListener myDragDropListener = new MyDragDropListener(flipperPanel);
		new DropTarget(this.getContentPane(), myDragDropListener);
		JDesktopPane desktopPane = new JDesktopPane();
		mainPanel = new JPanel();
		JInternalFrame intFrameData = new JInternalFrame("Data Panel");
		btnOpenFile = new JButton("Browse File...");
		dataPanelMenu = new JPanel();
		JLabel lblOrDropA = new JLabel("or drop a text file");
		JPanel panel = new JPanel();
		btnClearData = new JButton("Clear");
		topBarPanel = new JPanel();
		btnDisplay = new JButton("Display");
		txtUserInput = new CustomField(flippers.NamePicker.NUMBER_OF_FLIPPERS);
		final JComboBox delimiterComboBox = new JComboBox();
		
		// end ****************************************************************

		
		// Initialise and add features	***************************************
		flipperPanel.setInteractedComponents(txtUserInput, txtAreaData);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		
		desktopPane.add(topBarPanel, BorderLayout.NORTH);
		
		
		txtUserInput.setToolTipText("Enter your own string");
		txtUserInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flipperPanel.btnDisplayActionPerformed();
			}
		});
		txtUserInput.setFont(new Font("Clear Sans",1,20));
		TextPrompt tp7 = new TextPrompt("Enter text to display", txtUserInput);
		tp7.setForeground( Color.gray );
		tp7.changeAlpha(0.5f);
		topBarPanel.add(txtUserInput);
		
		
		// ************* Flipper Frame ********************************
		btnDisplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flipperPanel.btnDisplayActionPerformed();
			}
		});
		topBarPanel.add(btnDisplay);
		
		btnPickOne = new JButton("Pick One");
		btnPickOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flipperPanel.btnGenerateActionPerformed(e);
			}
		});
		topBarPanel.add(btnPickOne);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flipperPanel.btnClearActionPerformed();
			}
		});
		topBarPanel.add(btnClear);
		desktopPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		JInternalFrame intFrameFlippers = new JInternalFrame("Flippers");
		intFrameFlippers.setBounds(39, 34, 660, 100);
		mainPanel.add(intFrameFlippers);
		intFrameFlippers.setMinimumSize(new Dimension(660, 100));
		intFrameFlippers.setFocusable(false);
		intFrameFlippers.setRequestFocusEnabled(false);
		intFrameFlippers.setVerifyInputWhenFocusTarget(false);
		intFrameFlippers.setVisible(true);
		intFrameFlippers.getContentPane().add(flipperPanel);
		
		

		intFrameData.setIconifiable(true);
		intFrameData.setBounds(228, 146, 350, 250);
		mainPanel.add(intFrameData);
		
		
		intFrameData.setVerifyInputWhenFocusTarget(false);
		intFrameData.setMinimumSize(new Dimension(300, 250));
		intFrameData.setResizable(true);
		intFrameData.getContentPane().setLayout(new BorderLayout(0, 0));
		
		intFrameData.getContentPane().add(dataPanelMenu, BorderLayout.NORTH);
		
		
		
		dataPanelMenu.add(btnOpenFile);
		
		
		dataPanelMenu.add(lblOrDropA);		
		JButton btnLoadToFlippers = new JButton("Load Data");
		dataPanelMenu.add(btnLoadToFlippers);
		
		
		btnLoadToFlippers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String delimiter = (String) delimiterComboBox.getSelectedItem();
				delimiter = getRegexFromCombo(delimiter);
				
				flipperPanel.loadDataFromText(txtAreaData.getText().split(delimiter));
			}
		});
		intFrameData.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnOpenFile, txtAreaData}));
		
		btnOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		        chooser.setFileFilter(filter);
		        chooser.setAcceptAllFileFilterUsed(false);
		        chooser.setMultiSelectionEnabled(false);

		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            File f = chooser.getSelectedFile();
		            
		        	flipperPanel.loadDataFromFile(f);
		        }
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		txtAreaData.setWrapStyleWord(true);
		txtAreaData.setLineWrap(true);
		scrollPane.setViewportView(txtAreaData);
		intFrameData.getContentPane().add(scrollPane, BorderLayout.CENTER);
		intFrameData.setVisible(true);
		
		
		intFrameData.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblDelimiter = new JLabel("Delimiter:");
		panel.add(lblDelimiter);
		
		
		delimiterComboBox.setModel(new DefaultComboBoxModel(new String[] {"comma (,)", "colon (:)", "semi-colon (;)", "whitespace (\\t )"}));
		delimiterComboBox.setSelectedIndex(3);
		delimiterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String delimiter = (String) delimiterComboBox.getSelectedItem();
				delimiter = getRegexFromCombo(delimiter);
				
				String currentInput = txtAreaData.getText();
				//currentInput = currentInput.replaceAll("\n", "");
				currentInput = currentInput.replaceAll(delimiter, "\n");
				txtAreaData.setText(currentInput);
			}
		});
		panel.add(delimiterComboBox);
		
		
		btnClearData.setBackground(Color.PINK);
		panel.add(btnClearData);
		
		bottomBarPanel = new JPanel();
		btnMyHomepage = new JButton("Visit my page");
		desktopPane.add(bottomBarPanel, BorderLayout.SOUTH);
		bottomBarPanel.setLayout(new BorderLayout(0, 0));
		btnMyHomepage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(new URI("http://bit.ly/pixport"));
			        } catch (Exception e) { e.printStackTrace(); }
			    }
			    
			}
		});
		bottomBarPanel.add(btnMyHomepage, BorderLayout.EAST);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserInput, flipperPanel, dataPanelMenu, btnOpenFile, btnClearData}));
		btnClearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtAreaData.setText("");
				flipperPanel.clearDataList();
			}
		});
		
		//flipperPanel.displayFlipperPanel.printGreeting("welcome");
	}
	
	public String getRegexFromCombo(String delimiter) {
		String[] pieces = delimiter.split("[()]");
		delimiter = pieces[pieces.length - 1];
		delimiter = String.format("[%s%s]+", delimiter, "\\n");
		out.println(delimiter);
		return delimiter;
	}
	
	class MyDragDropListener implements DropTargetListener {
		NamePicker flipperPanel;
		
	    public MyDragDropListener(NamePicker flipperPanel) {
			this.flipperPanel = flipperPanel;
		}
	    

		@Override
	    public void drop(DropTargetDropEvent event) {
	        // Accept copy drops
	        event.acceptDrop(DnDConstants.ACTION_COPY);

	        // Get the transfer which can provide the dropped item data
	        Transferable transferable = event.getTransferable();

	        // Get the data formats of the dropped item
	        DataFlavor[] flavors = transferable.getTransferDataFlavors();

	        // Loop through the flavors
	        for (DataFlavor flavor : flavors) {
	            try {
	                // If the drop items are files
	                if (flavor.isFlavorJavaFileListType()) {

	                    // Get all of the dropped files
	                    List files = (List) transferable.getTransferData(flavor);

	                    File file;
	                    for (Object objFile : files) {
	                    	file = (File) objFile;
	                        
	                        String extension = file.getName();
	                        int i = extension.lastIndexOf('.');
	                        if (i > 0) extension = extension.substring(i+1);
	                        
	                     // Print out the file path of valid txt files
	                        if (extension.equals("txt") || extension.equals("TXT")){
	                        	//System.out.println("File path is '" + file.getPath() + "'.");
	                        	flipperPanel.loadDataFromFile(file);
	                        	break;
	                        }
	                    }


	                }

	            } catch (Exception e) { e.printStackTrace();}
	        }

	        // Inform that the drop is complete
	        event.dropComplete(true);
	    }

	    @Override
	    public void dragEnter(DropTargetDragEvent event) {}

	    @Override
	    public void dragExit(DropTargetEvent event) {}

	    @Override
	    public void dragOver(DropTargetDragEvent event) {}

	    @Override
	    public void dropActionChanged(DropTargetDragEvent event) {}

	}
}
