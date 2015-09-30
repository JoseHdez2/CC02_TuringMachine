package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow {

	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	
	final String[] STR_LOAD = {"Load", "Cargar"};
	final String[] STR_RUN = {"Run", "Correr"};
	final String[] STR_TRACE = {"Trace", "Traza"};
	final String[] STR_TRANS = {"Transitions", "Transiciones"};
	
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnLoad = new JButton(STR_LOAD[lang]);
		panelNorth.add(btnLoad, BorderLayout.NORTH);
		
		JButton btnRun = new JButton(STR_RUN[lang]);
		panelSouth.add(btnRun);
		
		JLabel lblMaquina = new JLabel(STR_TRANS[lang]);
		panelNorth.add(lblMaquina);
		
		JPanel panelTrans = new JPanel();
		frame.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		String[][] tableData =
			{
				{"q1", "q2", "a1", "A1", "A2"},
			};
		String[] tableColumns = {"State In", "State Out", "String In", "Stack In", "Stack Out"};
		table = new JTable(tableData, tableColumns);
		table.setEnabled(false);
		scrollPane = new JScrollPane(table);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		JLabel lblTraza = new JLabel(STR_TRACE[lang]);
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

}
