package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

// TODO: Change all 'char' named variables to 'sym(bol)'.
// TODO: Add level of abstraction over Automaton, rename to NonDeterministic.

public class Main {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainMenuFrame frame = new MainMenuFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
