import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JOptionPane;

public class DFAMachine {

	private GUI gui;
	private FileHandler dfaHandler = null;
	private FileHandler inputHandler = null;

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
				try {
					loadFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	 * @throws IOException
	 */
	private void loadFile() throws IOException {
		FileHandler fileHandler = new FileHandler();
		File selectedFile = fileHandler.chooseFile(gui.frame);
		if (selectedFile != null) {
			FileReader file = new FileReader(fileHandler.getFileChooser().getSelectedFile().getAbsolutePath());
			fileHandler.reader = new BufferedReader(file);
			String fileExt = fileHandler.getFileExtension(fileHandler.getFileName());
			if (fileExt.equals("in")) {
				inputHandler = fileHandler;
			} else if (fileExt.equals("dfa")) {
				System.out.println("dfa!");
				dfaHandler = fileHandler;
			}
			// String line = fileHandler.reader.readLine();

			// for (int lineNum = 1; line != null; lineNum++) {
			// 	System.out.println(line);
			// 	line = fileHandler.reader.readLine();
			// }
		} else {

		}
	}

	/**
	 * Processes input based on transition table.
	 */
	private void process() {
		if(inputHandler == null){
			gui.setStatus("Cannot process. You have not loaded an .inp file!");
			JOptionPane.showMessageDialog(gui.frame, "Cannot process. You have not loaded an .inp file!", "File Error", JOptionPane.PLAIN_MESSAGE);
		} else if(dfaHandler == null){
			JOptionPane.showMessageDialog(gui.frame, "Cannot process. You have not loaded a .dfa file!", "File Error", JOptionPane.PLAIN_MESSAGE);
		} else {

		}
	}

}
