package gui;

import java.awt.BorderLayout;
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

import main.AutomataReader;
import structs.AutomatonData;
import util.TokenizedLines;

public class FrameTrace {

    AutomatonData automatonData = null;
    
	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	String chosenFileFullPath = null;

	final String[] STR_WINDOW_TITLE = {"Trace", "Traza"};
	final String[] STR_ACCEPT = {
        "The input string was accepted.",
        "La cadena de entrada fue aceptada."
	};
	final String[] STR_DENY = {
        "The input string was denied.",
        "La cadena de entrada fue denegada."
	};

	private JFrame frameMain;
	private JFrame frameTrace;
	private JTable tableTrace;
	private JScrollPane scrollPane;
	
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
	 * Create the application.
	 */
	public FrameTrace(AutomatonData automatonData) {
	    this.automatonData = automatonData;
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
		
		JButton btnLoad = new JButton("Cargar");
		panelNorth.add(btnLoad, BorderLayout.NORTH);

		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog openFile = new FileDialog((JFrame)SwingUtilities.getWindowAncestor(btnLoad), 
						"Cargar", FileDialog.LOAD);
				openFile.setDirectory(System.getProperty("user.dir"));
				openFile.setVisible(true);
				chosenFileFullPath = openFile.getDirectory() + File.separator + openFile.getFile();
				
				// Update table (load data from new file into table)
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
                    tableTrace.setModel(tableTransModel);
//                    tableTrans.setColumnModel(tableTransColumns[lang]);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
			}
			
		});
		
		JButton btnRun = new JButton("Correr");
		panelSouth.add(btnRun);
		
		btnRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameTrace = new JFrame("Traza");
                frameTrace.setSize(400, 400);
                frameTrace.setVisible(true);
            }
            
        });
		
		JLabel lblMaquina = new JLabel("asd");
		panelNorth.add(lblMaquina);
		
		JPanel panelTrans = new JPanel();
		frameMain.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		tableTrace = new JTable(tableTraceDummyData, tableTraceColumns[lang]);

		tableTrace.setEnabled(false);
		scrollPane = new JScrollPane(tableTrace);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		JLabel lblTraza = new JLabel("hue");
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

}
