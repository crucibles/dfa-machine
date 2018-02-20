
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class GUI {

	public JFrame frame;
	private JTable tbTransition;
	public JButton btnLoadFile;
	public JButton btnProcess;

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/*
	* Setting a value for a cell of the DFA Table
	*/
	public setDFACell(int row, int column, String value){
		tbTransition.getModel().setValueAt(value, row, column);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 644, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane spTransition = new JScrollPane();
		spTransition.setBounds(10, 80, 195, 187);
		frame.getContentPane().add(spTransition);
		
		String[] tableHeader = { "", "State", "0", "1" };
		

		tbTransition = new JTable(new DefaultTableModel(tableHeader, 0)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		spTransition.setViewportView(tbTransition);

		tbTransition.setBounds(30, 97, 266, 171);
		
		JScrollPane spInput = new JScrollPane();
		spInput.setBounds(215, 80, 195, 187);
		frame.getContentPane().add(spInput);
		
		JTextPane tpInput = new JTextPane();
		tpInput.setEditable(false);
		spInput.setViewportView(tpInput);
		
		JScrollPane spOutput = new JScrollPane();
		spOutput.setBounds(420, 80, 195, 187);
		frame.getContentPane().add(spOutput);
		
		JTextPane tpOutput = new JTextPane();
		tpOutput.setEditable(false);
		spOutput.setViewportView(tpOutput);
		
		JLabel lblTransition = new JLabel("Transition Table");
		lblTransition.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblTransition.setBounds(10, 61, 120, 21);
		frame.getContentPane().add(lblTransition);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblInput.setBounds(216, 61, 120, 21);
		frame.getContentPane().add(lblInput);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblOutput.setBounds(421, 61, 120, 21);
		frame.getContentPane().add(lblOutput);
		
		JLabel lblStatus = new JLabel("STATUS:");
		lblStatus.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblStatus.setBounds(36, 286, 69, 21);
		frame.getContentPane().add(lblStatus);
		
		JTextPane tpStatus = new JTextPane();
		tpStatus.setBackground(new Color(240, 240, 240, 240));
		tpStatus.setEditable(false);
		tpStatus.setBounds(99, 286, 496, 50);
		frame.getContentPane().add(tpStatus);
		
		btnLoadFile = new JButton("Load File");
		btnLoadFile.setBounds(10, 11, 400, 33);
		frame.getContentPane().add(btnLoadFile);
		
		btnProcess = new JButton("Process");
		btnProcess.setBounds(420, 11, 195, 33);
		frame.getContentPane().add(btnProcess);
	}
}
