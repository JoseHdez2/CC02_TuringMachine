package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	final String[] STR_WINDOW_TITLE = {"Pushdown Automaton", "Automata de Pila"};
	final String[] STR_LOAD = {"Load", "Cargar"};
	final String[] STR_RUN = {"Run", "Correr"};
	final String[] STR_TRACE = {"Trace", "Traza"};
	final String[] STR_TRANS = {"Transitions", "Transiciones"};
	
	private JFrame frame;
	private JTable tableTrans;
	private JScrollPane scrollPane;

	String[][] tableTransData =
		{
			{"q1", "q2", "a1", "A1", "A2"},
		};
	String[][] tableTransColumns = 
		{
			{"State 1", "State 2", "Input", "Stack Pop", "Stack Push"},
			{"Estado 1", "Estado 2", "Entrada", "De pila", "A pila"},
		};
	
	String[][] tableTraceData =
		{
			{"q1", "a1", "A1"},
		};
	String[][] tableTraceColumns = 
		{
			{"Current State", "Remaining String", "Stack Content"},
			{"Estado Actual", "Cadena Restante", "Contenido de Pila"},
		};
	
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
		frame = new JFrame(STR_WINDOW_TITLE[lang]);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnLoad = new JButton(STR_LOAD[lang]);
		panelNorth.add(btnLoad, BorderLayout.NORTH);

		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		JButton btnRun = new JButton(STR_RUN[lang]);
		panelSouth.add(btnRun);
		
		JLabel lblMaquina = new JLabel(STR_TRANS[lang]);
		panelNorth.add(lblMaquina);
		
		JPanel panelTrans = new JPanel();
		frame.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		tableTrans = new JTable(tableTransData, tableTransColumns[lang]);
		tableTrans.setEnabled(false);
		scrollPane = new JScrollPane(tableTrans);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		JLabel lblTraza = new JLabel(STR_TRACE[lang]);
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

}
