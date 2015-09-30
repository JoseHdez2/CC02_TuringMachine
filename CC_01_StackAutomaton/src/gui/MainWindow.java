package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow {

	boolean englishGUI = true;
	private static final HashMap<String, String> guiVocab;
	static
	{
		guiVocab = new HashMap<String, String>();
		guiVocab.put("Load", "Cargar");
		guiVocab.put("Run", "Correr");
	}
	
	String STR_LOAD = englishGUI ? "Load" : "Cargar";
	String STR_RUN = englishGUI ? "Run" : "Correr";
	
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		JButton btnLoad = new JButton(guiVocab.get("Load"));
		
		JButton btnRun = new JButton(guiVocab.get("Run"));
		panelSouth.add(btnRun);
		
		JLabel lblMaquina = new JLabel("Transiciones");
		panelNorth.add(lblMaquina);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{1, 2, 3, 4, 5},
				{1, 2, 3, 4, 5},
				{1, 2, 3, 4, 5},
			},
			new String[] {
				"State In", "State Out", "String In", "Stack In", "Stack Out"
			}
		));
		panelNorth.add(table);
		
		JLabel lblTraza = new JLabel("Traza");
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

}
