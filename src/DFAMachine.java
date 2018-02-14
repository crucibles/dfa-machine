import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;

public class DFAMachine {
	
	private GUI gui;
	private File fileDFA;
	private File fileInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DFAMachine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Constructor
	 */
	public DFAMachine() {
		initialize();
	}

	/**
	 * Sets up the program's GUI.
	 */
	private void initialize() {
		gui = new GUI();
		gui.frame.setVisible(true);

		gui.btnLoadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadFile();
			}
		});
		gui.btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				process();
			}
		});
	}

	/**
	 * Loads file.
	 */
	private void loadFile(){
		FileHandler fileHandler = new FileHandler();
		File selectedFile = fileHandler.chooseFile(gui.frame);
		if( selectedFile != null ){
			
		} else {
						
		}
	}

	/**
	 * Processes input based on transition table.
	 */
	private void process() {

	}

}
