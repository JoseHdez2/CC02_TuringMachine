package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.AutomataReader;
import structs.AutomatonData;
import util.TokenizedLines;

public class FrameMain {
//    TODO: be able to push more than one symbol in the stack, per transition
//    TODO: be able to consider acceptance states to accept string
    
	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	String chosenFileFullPath = null;
	AutomatonData automatonData = null;
	JTextField inputStringField = null;
	
	final String[] STR_WINDOW_TITLE = {"Pushdown Automaton", "Automata de Pila"};
	final String[] STR_WINDOW_LOAD =
		{"Load automaton definition...", "Cargar definición de autómata..."};
	
	final String[] STR_LOAD = {"Load", "Cargar"};
	final String[] STR_RUN = {"Run", "Correr"};
	final String[] STR_TRACE = {"Trace", "Traza"};
	final String[] STR_TRANS = {"Transitions", "Transiciones"};

	private JFrame frameMain;
	private FrameTrace frameTrace;
	private JLabel labelFilename = new JLabel("---");
	private JTable tableTrans;
	private JScrollPane scrollPane;
	
	private JButton buttonRun = null;

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
	    
	    Stack<String> s = new Stack<String>();
	    s.push("a");
	    s.push("b");
	    for (String str : s){
	        System.out.println(str);
	    }
	    
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
				if(!(openFile.getFile() == null)){
    				chosenFileFullPath = openFile.getDirectory() + File.separator + openFile.getFile();
    //				chosenFileFullPath = openFile.getDirectory() + openFile.getFile();
    				System.out.println(chosenFileFullPath);
    				labelFilename.setText(openFile.getFile());
    				labelFilename.setFont(labelFilename.getFont().deriveFont(Font.BOLD));
    				updateLoadedAutomaton();
    				lockTraceGUI(false);
				}
			}
			
		});
		
		inputStringField = new JTextField(10);
		panelSouth.add(inputStringField);
		
		buttonRun = new JButton(STR_RUN[lang]);
		panelSouth.add(buttonRun);
		
		buttonRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputStringField.getText();
                frameTrace = new FrameTrace(automatonData, inputString);
                frameTrace.setVisible(true);
            }
            
        });
		
		panelNorth.add(labelFilename);
		
		JPanel panelTrans = new JPanel();
		frameMain.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		tableTrans = new JTable(tableTransDummyData, tableTransColumns[lang]);

		tableTrans.setEnabled(false);
		scrollPane = new JScrollPane(tableTrans);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
		
		lockTraceGUI(true);
	}

	private void updateLoadedAutomaton(){
        try {
            automatonData = AutomataReader.readAutomatonData(chosenFileFullPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        TokenizedLines transitions = 
                AutomataReader.getTransitionsAsTokenizedLines(automatonData);
        
        tableTrans.setModel(new MyTableModel(transitions));
//      tableTrans.setColumnModel(tableTransColumns[lang]);
	}
	
	/**
	 * Disable/Enable GUI elements that lead to the trace window.
	 * @param lock Elements will be locked.
	 */
	private void lockTraceGUI(boolean lock){
//	    tableTrans.setVisible(!lock);
	    buttonRun.setEnabled(!lock);
	    inputStringField.setEnabled(!lock);
	}
}
