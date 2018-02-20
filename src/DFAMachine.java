import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;

public class DFAMachine {

	private GUI gui;
	FileHandler fileHandler = new FileHandler();
	DFAState dState = new DFAState();

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
		createTable();
		//initialize();
	}

	/*
	* test
	*/
	private void createTable() {

		String transitions = "-,A,B,A\n$,B,B,C\n+,C,B,A";
		String inp = "110011\n0111110001\n1001010\n100";

		if (checker(transitions)) {
			String[] lines = transitions.trim().split("\\s");

			for (int x = 0; x < lines.length; x++) {

				String line = lines[x];
				System.out.println(line);
				String[] tokens = line.trim().split(",");

				dState.getVector().add(new DFAState(tokens[0], tokens[1], tokens[2], tokens[3]));

			}
		}

		System.out.println(dState.getVector().get(0).getStateName());
		System.out.println(dState.getVector().get(1).getStateName());
		System.out.println(dState.getVector().get(2).getStateName());
	}

	private boolean checker(String transitions) {
		if (transitions.indexOf('-') != transitions.lastIndexOf('-')) {
			System.out.println("more than 1 start state!");
			return false;
		}

		if (transitions.indexOf('+') == transitions.lastIndexOf('-')) {
			System.out.println("has a state that is both start and final!");
			return false;
		}

		return true;
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
	private void loadFile() {

		File selectedFile = fileHandler.chooseFile(gui.frame);
		System.out.println(selectedFile.getName());
		if ( selectedFile != null) {
			if (fileHandler.getFileExtension(fileHandler.getFileName()) == "inp") {
				// fill the input table area
			} else if(fileHandler.getFileExtension(fileHandler.getFileName()) == "dfa"){
				// fill the dfa table
			}
		}
	}

	

	/**
	 * Processes input based on transition table.
	 */
	private void process() {

	}

}
