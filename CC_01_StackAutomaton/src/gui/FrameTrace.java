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

public class FrameTrace extends JFrame {

    AutomatonData automatonData = null;
    String inputString;
    
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
	public FrameTrace(AutomatonData automatonData, String inputString) {
	    this.automatonData = automatonData;
	    this.inputString = inputString;
	    setTitle(STR_WINDOW_TITLE[lang]);
        setBounds(600, 150, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JLabel lblMaquina = new JLabel("asd");
		panelNorth.add(lblMaquina);
		
		JPanel panelTrace = new JPanel();
		getContentPane().add(panelTrace, BorderLayout.CENTER);
		
		tableTrace = new JTable(tableTraceDummyData, tableTraceColumns[lang]);

		tableTrace.setEnabled(false);
		scrollPane = new JScrollPane(tableTrace);
		
		panelTrace.add(scrollPane, BorderLayout.SOUTH);
		
		JLabel lblTraza = new JLabel("hue");
		panelSouth.add(lblTraza);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
	}

	private void runTrace(){
	    TokenizedLines traceLines = new TokenizedLines();
	}
}
