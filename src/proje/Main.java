package proje;

import javax.swing.JFrame;

import proje.gui.JProgram;

public class Main {

	public static void main(String[] args) {
		
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                createAndShowGUI();
            }
        });
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JProgram program = new JProgram();
        program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        program.setSize(600, 500);
        program.setVisible(true);
    }
}
