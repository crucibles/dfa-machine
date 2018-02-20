import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class DFAMachine {

	private GUI gui;
	private FileHandler dfaHandler = null;
	private FileHandler inputHandler = null;
	FileHandler fileHandler = new FileHandler();
	DFATable dfaTable = null;

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

	/*
	* Creates the table based on a .dfa file.
	*/
	private boolean createTable(String transitions) {
		DFATable temp = new DFATable();

		//String transitions = "-,A,B,A\n$,B,B,C\n+,C,B,A";

		if (checker(transitions)) {
			String[] lines = transitions.trim().split("\\s");

			for (int x = 0; x < lines.length; x++) {

				String line = lines[x];
				String[] tokens = line.trim().split(",");
				// int occurrences = dfaTable.getStateOccurrence(tokens[1]);
				// if(occurrences > 0){
				// 	tokens[1] += occurrences; 
				// }
				DFAState newState = new DFAState(tokens[0], tokens[1], tokens[2], tokens[3]);

				temp.addState(newState);
			}

			if (!temp.isValidStates()) {
				return false;
			} else {
				dfaTable = temp;
				gui.setTable(dfaTable);
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks whether a state is valid or not.
	 */
	private boolean checker(String transitions) {
		if (transitions.indexOf('-') != transitions.lastIndexOf('-')) {
			System.out.println("more than 1 start state!");
			return false;
		}

		if (transitions.indexOf('+') == transitions.lastIndexOf('-')) {
			System.out.println("has a state that is both start and final!");
			return false;
		}

		//AHJ: unimplemented; if same state name

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
				try {
					process();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Loads file.
	 * @throws IOException
	 */
	private void loadFile() throws IOException {
		File selectedFile = fileHandler.chooseFile(gui.frame);
		if (selectedFile != null) {
			System.out.println(selectedFile.getName());
			FileReader file = new FileReader(fileHandler.getFileChooser().getSelectedFile().getAbsolutePath());
			fileHandler.reader = new BufferedReader(file);
			String fileExt = fileHandler.getFileExtension(fileHandler.getFileName());
			if (fileExt.equals("inp")) {
				inputHandler = new FileHandler();
				inputHandler = fileHandler;
				gui.setInputText(inputHandler.getFileContent());
				gui.setStatus("Input from file " + inputHandler.getFileName() + " has been successfully loaded.");
			} else if (fileExt.equals("dfa")) {
				if (createTable(fileHandler.getFileContent())) {
					dfaHandler = new FileHandler();
					dfaHandler = fileHandler;
					gui.setStatus("Input from file " + dfaHandler.getFileName() + " has been successfully loaded.");
				} else {
					String errorStatus = dfaHandler == null
							? "Unable to load content from " + fileHandler.getFileName() + " due to invalid content."
							: "Unable to load content from " + fileHandler.getFileName() + " due to invalid content. The program will be using the content from the most recently successfully loaded " + dfaHandler.getFileName() + ".";
					gui.setStatus(errorStatus);
				}
			}
			// String line = fileHandler.reader.readLine();

			// for (int lineNum = 1; line != null; lineNum++) {
			// 	System.out.println(line);
			// 	line = fileHandler.reader.readLine();
			// }
		} else {
			System.out.println("hello im else");
		}
	}

	/**
	 * Processes input based on transition table.
	 * @throws IOException
	 */
	private void process() throws IOException {
		//String inp = "110011\n0111110001\n1001010\n100";

		if (inputHandler == null) {

			gui.setStatus("Cannot process. You have not loaded an .inp file!");
			JOptionPane.showMessageDialog(gui.frame, "Cannot process. You have not loaded an .inp file!", "File Error",
					JOptionPane.PLAIN_MESSAGE);

		} else if (dfaHandler == null) {

			JOptionPane.showMessageDialog(gui.frame, "Cannot process. You have not loaded a .dfa file!", "File Error",
					JOptionPane.PLAIN_MESSAGE);

		} else {
			gui.setStatus("Input from file " + inputHandler.inpName
					+ " has been successfully loaded using DFA table from " + dfaHandler.dfaName + ". Output saved to "
					+ inputHandler.getFileName().replace(".inp", ".out"));
			gui.resetText();
			String line = gui.tpInput.getText();
			String[] lines = line.trim().split("\\s");
			String output = "";
			for (int i = 0; i < lines.length; i++) {

				String inp = lines[i];
				String isValid = dfaTable.isValidString(inp) ? "VALID\n" : "INVALID\n";
				output += isValid;
				gui.addOutput(isValid);

			}

			fileHandler.createFile(output, gui.frame);
		}
	}

}
