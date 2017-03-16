package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import tree.InputValidator;
import tree.Node;
import tree.TreePrettyPrinter;
import tree.TreeConstructor;
import tree.TreeParser;
 
/*
 * UI is modified from that found at: http://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea
 */

public class SwingUI extends JFrame {
	
	private static final int COLUMN_WIDTH=52;
	private static final int FONT_SIZE = 24;

	/**
     * The text area which is used for displaying the tree and the level found for a given input.
     */
    private JTextArea textArea;
    
    private JLabel treeLabel = new JLabel("Comma Separated Tree:");
    private JLabel intLabel = new JLabel("Integer to Find in Tree:");
    
    private JButton buttonStart = new JButton("Submit");
    private JButton buttonClear = new JButton("Clear");
    private JTextField textTree = new JTextField(30);
    private JTextField textInt = new JTextField(30);
     
    private PrintStream standardOut;
     
    public SwingUI() {
        super("Binary Tree Level Parsing Application");
        
        Font UI_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
        
        treeLabel.setFont(UI_FONT);
        buttonStart.setFont(UI_FONT);
        buttonClear.setFont(UI_FONT);
        intLabel.setFont(UI_FONT);
        textTree.setFont(UI_FONT);
        textInt.setFont(UI_FONT);
         
        textArea = new JTextArea(25, COLUMN_WIDTH);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
         
        // keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
 
        // creates the GUI
       
        JPanel northPanel = new JPanel();
        northPanel.add(treeLabel);
        northPanel.add(textTree);
        northPanel.add(intLabel);
        northPanel.add(textInt);
        northPanel.add(buttonStart);
        northPanel.add(buttonClear);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
        northPanel.setAlignmentX(CENTER_ALIGNMENT);
       

        
        JPanel southPanel = new JPanel(); 
        southPanel.add(new JScrollPane(textArea));
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
         
        // adds event handler for button Start
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(constructAndParseTree()){
                	buttonStart.setEnabled(false);
                }
            }
        });
         
        // adds event handler for button Clear
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	buttonStart.setEnabled(true);
                // clears the text area
                try {
                    textArea.getDocument().remove(0,
                            textArea.getDocument().getLength());
                    standardOut.println("Text area cleared and Submit button renabled.");
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(southPanel, BorderLayout.PAGE_END);
        setSize(600, 600);
        setLocationRelativeTo(null);    // centers on screen
    }
     
    //This constructs and parses the tree for the user to see on screen.
    private boolean constructAndParseTree() {
        String binaryTree = textTree.getText();
        try{
        	Integer desiredInt = Integer.parseInt(textInt.getText());
        	if(binaryTree == null || "".equals(binaryTree)){
                JOptionPane.showMessageDialog(this, "Binary Tree input must be provided!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        	else if(!InputValidator.isValidBinaryTreeString(binaryTree)){
        		JOptionPane.showMessageDialog(this, "Binary Tree is not formatted correctly! E.g. 1,2,3,4,,5,7", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
        		buttonStart.setEnabled(true);
        		return false;
        	}
        	Node root = TreeConstructor.constructTree(binaryTree);
        	TreePrettyPrinter.printTree(root, COLUMN_WIDTH);
        	System.out.println(TreeParser.parse(desiredInt, root));
        	return true;
        }
        catch(NumberFormatException e){
        	JOptionPane.showMessageDialog(this, "Integer to find in tree must be provided and must be an int!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
        	buttonStart.setEnabled(true);
        	return false;
        }
    }
     
    /**
     * Launches the GUI.
     */
    public static void launchGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingUI().setVisible(true);
            }
        });
    }
}
