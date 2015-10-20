package automaton.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import automaton.main.AutomatonIO;
import automaton.structs.AutomatonData;
import i18n.GUIStr;
import i18n.I18n;
import util.MyTableModel;
import util.TokenizedLines;

public abstract class AutomatonFrameLoad {
//    TODO: be able to consider acceptance states to accept string (in pushdown)
//    TODO: have the tables in the GUI be scrollable (somehow they aren't)
    
    /*
     * GUI constants.
     */
    
    final int FRAME_X = 100;
    final int FRAME_Y = 100;
    final int FRAME_WIDTH = 450;
    final int FRAME_HEIGHT = 300;
    
    final int FIELD_INPUT_STR_SIZE = 10;
    
    // TODO this doesn't work
    // change to true if you want an English interface.
	boolean englishGUI = false;
	int lang = englishGUI ? 0 : 1;
	
	String chosenFileFullPath = null;
	AutomatonData automatonData = null;
	JTextField inputStringField = null;
	
	final String STR_WINDOW_TITLE = I18n.getString(GUIStr.PUSHDOWN_WINDOW_TITLE);
	final String STR_WINDOW_LOAD = I18n.getString(GUIStr.PUSHDOWN_WINDOW_LOAD);
	
	final String STR_LOAD = I18n.getString(GUIStr.LOAD);
	final String STR_RUN = I18n.getString(GUIStr.RUN);
	final String STR_TRACE = I18n.getString(GUIStr.TRACE);
	final String STR_TRANS = I18n.getString(GUIStr.TRANSITIONS);
	
	protected JFrame frameMain;
	protected AutomatonFrameTrace automatonFrameTrace;
	protected JLabel labelFilename = new JLabel("---");
	protected JTable tableTrans;
	protected JScrollPane scrollPane;
	
	protected JButton buttonRun = null;

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
	 * Create the application.
	 */
	public AutomatonFrameLoad() {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    AutomatonFrameLoad window = createAutomatonFrameLoad();
//                    if(window != null){
                        window.frameMain.setVisible(true);
                        window.initialize();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	protected abstract AutomatonFrameLoad createAutomatonFrameLoad();

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frameMain = new JFrame(STR_WINDOW_TITLE);
		frameMain.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorth = buildLoadPanel();
        frameMain.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
        JPanel panelSouth = buildRunPanel();
        frameMain.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JPanel panelTrans = new JPanel();
		frameMain.getContentPane().add(panelTrans, BorderLayout.CENTER);
		
		tableTrans = new JTable(tableTransDummyData, tableTransColumns[lang]);

		tableTrans.setEnabled(false);
		scrollPane = new JScrollPane(tableTrans);
		
		panelTrans.add(scrollPane, BorderLayout.SOUTH);
		
		panelNorth.setMinimumSize(panelNorth.getPreferredSize());
		
		lockTraceGUI(true);
	}
	
	/**
	 * @return JPanel with GUI for automaton file loading.
	 */
	protected JPanel buildLoadPanel(){
        
        JPanel panelNorth = new JPanel();
        
        JButton btnLoad = new JButton(STR_LOAD);
        panelNorth.add(btnLoad, BorderLayout.NORTH);

        btnLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog openFile = new FileDialog((JFrame)SwingUtilities.getWindowAncestor(btnLoad), 
                        STR_WINDOW_LOAD, FileDialog.LOAD);
                openFile.setDirectory(System.getProperty("user.dir"));
                openFile.setVisible(true);
                if(!(openFile.getFile() == null)){
                    chosenFileFullPath = openFile.getDirectory() + File.separator + openFile.getFile();
                    System.out.println(chosenFileFullPath);
                    labelFilename.setText(openFile.getFile());
                    labelFilename.setFont(labelFilename.getFont().deriveFont(Font.BOLD));
                    updateLoadedAutomaton();
                    lockTraceGUI(false);
                }
            }
            
        });
        
        panelNorth.add(labelFilename);
        
        return panelNorth;
	}
	
	/**
	 * @return JPanel with GUI for entering an input string and running a trace.
	 */
	protected JPanel buildRunPanel(){
	    
	    JPanel runPanel = new JPanel();
        
        inputStringField = new JTextField(FIELD_INPUT_STR_SIZE);
        runPanel.add(inputStringField);
        
        buttonRun = new JButton(STR_RUN);
        runPanel.add(buttonRun);
        
        buttonRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputStringField.getText();
                automatonFrameTrace = createAutomatonFrameTrace(automatonData, inputString);
                automatonFrameTrace.setVisible(true);
            }
            
        });
	    
	    return runPanel;
	}
	
	protected abstract AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString);
	// automatonFrameTrace = new AutomatonFrameTrace(automatonData, inputString);

	protected void updateLoadedAutomaton(){
        try {
            automatonData = AutomatonIO.readAutomatonData(chosenFileFullPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        TokenizedLines transitions = 
                AutomatonIO.getTransitionsAsTokenizedLines(automatonData);
        
        tableTrans.setModel(new MyTableModel(transitions));
//      tableTrans.setColumnModel(tableTransColumns[lang]);
	}
	
	/**
	 * Disable/Enable GUI elements that lead to the trace window.
	 * @param lock If true, elements will be locked.
	 */
	protected void lockTraceGUI(boolean lock){
//	    tableTrans.setVisible(!lock);
	    buttonRun.setEnabled(!lock);
	    inputStringField.setEnabled(!lock);
	}
}
