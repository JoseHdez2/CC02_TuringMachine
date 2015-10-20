package pushdown.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import automaton.gui.AutomatonFrameTrace;
import pushdown.main.PushdownAutomaton;
import pushdown.structs.PushdownData;
import util.MyTableModel;

@SuppressWarnings("serial")
public class PushdownFrameTrace extends AutomatonFrameTrace {

    PushdownData pushdownData = null;
    PushdownAutomaton myAutomaton = null;
    String inputString = null;
    
	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	String chosenFileFullPath = null;

	final String[] STR_WINDOW_TITLE = {"Trace", "Traza"};
	final String[] STR_INP_STR_INTRO = {"Input string: ", "Cadena de entrada: "};
	final String[] STR_ACCEPT = {
        "The input string was accepted.",
        "La cadena de entrada fue aceptada."
	};
	final String[] STR_DENY = {
        "The input string was denied.",
        "La cadena de entrada fue denegada."
	};

	private JTable tableTrace;
	private JScrollPane scrollPane;
	private JLabel labelResult = new JLabel("---");
	
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
	public PushdownFrameTrace(PushdownData pushdownData, String inputString) {
	    super(pushdownData, inputString);
	    this.pushdownData = pushdownData;
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
		
		JLabel labelInputIntro = new JLabel(STR_INP_STR_INTRO[lang]);
		panelNorth.add(labelInputIntro);
		
		JLabel labelInputString = new JLabel('"' + inputString + '"');
        labelInputString.setFont(labelInputString.getFont().deriveFont(Font.BOLD));
        panelNorth.add(labelInputString);
		
		JPanel panelTrace = new JPanel();
		getContentPane().add(panelTrace, BorderLayout.CENTER);
		
		tableTrace = new JTable(tableTraceDummyData, tableTraceColumns[lang]);

		tableTrace.setEnabled(false);
		scrollPane = new JScrollPane(tableTrace);
		
		panelTrace.add(scrollPane, BorderLayout.SOUTH);
		
		labelResult.setFont(labelResult.getFont().deriveFont(Font.BOLD));
		panelSouth.add(labelResult);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
		
		runTrace();
	}

	protected void runTrace(){
	    myAutomaton = new PushdownAutomaton(pushdownData);
	    labelResult.setText(String.valueOf(myAutomaton.evaluateString(inputString)));
	    tableTrace.setModel(new MyTableModel(myAutomaton.getTraceHist()));
	    System.out.print(myAutomaton.getDebugStr());
	}

    @Override
    protected void updateAutomatonWithGUIData() {
        // TODO Auto-generated method stub
        
    }
}
