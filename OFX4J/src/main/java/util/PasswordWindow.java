package util;

import javax.swing.*;

import command.Cli;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class PasswordWindow extends JPanel
							implements ActionListener{
    private static String OK = "ok";
    private static String HELP = "help";

    private JFrame controllingFrame; //needed for dialogs
    
    private JTextField nameField;
       
    private JPasswordField passwordField;
    

	public PasswordWindow(JFrame f) {
        //Use the default FlowLayout.
        controllingFrame = f;

        //Create everything.
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);
        
        nameField = new JTextField(10);
        

        JLabel label0 = new JLabel("Enter your username: ");
        label0.setLabelFor(nameField);
        
        JLabel label = new JLabel("Enter your password: ");
        label.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        //Lay out everything.
        JPanel textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.Y_AXIS));
        textPane.add(label0);
        textPane.add(nameField);
        textPane.add(label);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton(OK);
        JButton helpButton = new JButton("Help");

        okButton.setActionCommand(OK);
        helpButton.setActionCommand(HELP);
        okButton.addActionListener(this);
        helpButton.addActionListener(this);

        p.add(okButton);
        p.add(helpButton);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
    	System.out.println(e);
    	System.out.println(e.getActionCommand());
        String cmd = e.getActionCommand();
        
        if (OK.equals(cmd)) { //Process the username and password
        	char[] nameInput = nameField.getText().toCharArray();
            char[] passInput = passwordField.getPassword();
            
            Cli.user1.setUsername(new String(nameInput));
            Cli.user1.setPassword(new String(passInput));
            //Zero out the possible password, for security.
            Arrays.fill(nameInput, '0');
            Arrays.fill(passInput, '0');
            
            //passwordField.selectAll();
            resetFocus();
            System.out.println("finish");
            
            controllingFrame.dispatchEvent(new WindowEvent(
                  controllingFrame, WindowEvent.WINDOW_CLOSING));
            
            System.out.println(Cli.user1);
        } else { //The user has asked for help.
            JOptionPane.showMessageDialog(controllingFrame,
                "You can get the password by searching this example's\n"
              + "source code for the string \"correctPassword\".\n"
              + "Or look at the section How to Use Password Fields in\n"
              + "the components section of The Java Tutorial.");
        }
        
        synchronized (Cli.user1) {
		    Cli.user1.notify();
		}
    }


    //Must be called from the event dispatch thread.
    protected void resetFocus() {
        nameField.requestFocusInWindow();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Username & Password");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        final PasswordWindow newContentPane = new PasswordWindow(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Make sure the focus goes to the right component
        //whenever the frame is initially given the focus.
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.toFront();
        //frame.repaint();
    }
    
}
