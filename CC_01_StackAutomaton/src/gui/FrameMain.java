package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.AutomataReader;
import structs.AutomatonData;
import util.StringProcessing;
import util.TokenizedLines;

public class FrameMain {

	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	String chosenFileFullPath = null;
	AutomatonData automatonData = null;
	
	final String[] STR_WINDOW_TITLE = {"Pushdown Automaton", "Automata de Pila"};
	final String[] STR_WINDOW_LOAD =
		{"Load automaton definition...", "Cargar definición de autómata..."};
	
	final String[] STR_LOAD = {"Load", "Cargar"};
	final String[] STR_RUN = {"Run", "Correr"};
	final String[] STR_TRACE = {"Trace", "Traza"};
	final String[] STR_TRANS = {"Transitions", "Transiciones"};

	private JFrame frameMain;
	private FrameTrace frameTrace;
	private JTable tableTrans;
	private JScrollPane scrollPane;

	String[][] tableTransDummyData =
		{
			{"q1", "q2", "a1", "A1", "A2"},
		};
	String[][] tableTransColumns = 
		{
			{"State 1", "State 2", "Input", "Stack Pop", "Stack Push"},
			{"Estado 1", "Estado 2", "Entrada", "De pila", "A pila"},
		};
	
	String[][] tableTraceDummyData =
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
					FrameMain window = new FrameMain();
					window.frameMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMain = new JFrame(STR_WINDOW_TITLE[lang]);
		frameMain.setBounds(100, 100, 450, 300);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorth = new JPanel();
		frameMain.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		frameMain.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnLoad = new JButton(STR_LOAD[lang]);
		panelNorth.add(btnLoad, BorderLayout.NORTH);

		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog openFile = new FileDialog((JFrame)SwingUtilities.getWindowAncestor(btnLoad), 
						STR_WINDOW_LOAD[lang], FileDialog.LOAD);
				openFile.setDirectory(System.getProperty("user.dir"));
				openFile.setVisible(true);
				chosenFileFullPath = openFile.getDirectory() + File.separator + openFile.getFile();
//				chosenFileFullPath = openFile.getDirectory() + openFile.getFile();
				System.out.println(chosenFileFullPath);
				updateLoadedAutomaton();
			}
			
		});
		
		JButton btnRun = new JButton(STR_RUN[lang]);
		panelSouth.add(btnRun);
		
		btnRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameTrace = new FrameTrace(automatonData);
            }
            
        });
		
		JLabel lblMaquina = new JLabel(STR_TRANS[lang]);
		panelNorth.add(lblMaquina);
		
		JPanel panelTrans = new JPanel();
		frameMain.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		tableTrans = new JTable(tableTransDummyData, tableTransColumns[lang]);

		tableTrans.setEnabled(false);
		scrollPane = new JScrollPane(tableTrans);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		JLabel lblTraza = new JLabel(STR_TRACE[lang]);
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

	public void updateLoadedAutomaton(){
        try {
            TokenizedLines tl = AutomataReader.prepareAutomatonData(chosenFileFullPath);
            System.out.println("Here goes ICHI:");
            System.out.println(tl.toString());
            automatonData = AutomataReader.readAutomatonData(chosenFileFullPath);
            TokenizedLines transitions = 
                    AutomataReader.getTransitionsAsTokenizedLines(automatonData);
            MyTableModel tableTransModel = new MyTableModel(transitions);
            System.out.println("Here goes NI:");
            System.out.println(transitions.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        TokenizedLines transitions = 
//                AutomataReader.getTransitionsAsTokenizedLines(automatonData);
        
//        tableTrans.setModel(new MyTableModel(transitions));
//      tableTrans.setColumnModel(tableTransColumns[lang]);
	}
}