package pl.dominikas.calculatorApp;
import java.awt.BorderLayout;
import org.mariuszgromada.math.mxparser.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.dominikas.calculatorApp.Operation;

public class MainFrame extends JFrame {

	private JTextField enterb;
	private JTextArea  area;
	private JButton btn;
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem reset, exit;
	private JList<Operation> list;
	private TheHandler handler=new TheHandler();
	private String string;
	
	public MainFrame() {                         // constructor
    	 setSize(400,400);
    	 setTitle("SciCalc");
    	 JPanel p1= new JPanel();
    	 p1.setLayout(new BorderLayout());
         p1.setBackground(Color.orange);
    	 JPanel p2=new JPanel();
    	 p2.setLayout(new BorderLayout());
    	 JPanel p3=new JPanel();
    	 p3.setLayout(new BorderLayout());
         
    	 bar= new JMenuBar();
   	     menu= new JMenu("Menu");    // "up" component
   	     reset= new JMenuItem("RESET");
   	     exit= new JMenuItem("EXIT");
   	     bar.setBackground(Color.orange);
   	     reset.addActionListener(handler);
   	     exit.addActionListener(handler);
   	     menu.add(reset);
   	     menu.add(exit);
   	     bar.add(menu);
   	     p1.add(bar,BorderLayout.WEST);
   	     add(p1,BorderLayout.NORTH);
   	     
   	     area= new JTextArea("");  // left component
   	     area.setEditable(false);
   	     area.setLineWrap(true);  
   	     p2.add(area,BorderLayout.CENTER);
   	     JScrollPane scrollPane = new JScrollPane(area);  // scroll function
   	     p2.add(scrollPane);
   	     enterb = new JTextField(15);
   	     enterb.addActionListener(handler);  // adding action
   	     enterb.setFocusable(true);
	     enterb.requestFocus();
	     enterb.addKeyListener(new KeyAdapter()
   {
       @Override
       public void keyPressed(KeyEvent e) {
           switch(e.getKeyCode())
           {
               case KeyEvent.VK_UP:
             	  enterb.setText(string);
           }
       }
   }                                           );

	     p2.add(enterb,BorderLayout.SOUTH);
   	     add(p2,BorderLayout.CENTER);
   	     
   	     final DefaultListModel<Operation> listModel = new DefaultListModel<Operation>();  //filling the JList with the functions
	     listModel.addElement(modulo);
	     listModel.addElement(exponentiation);
	     listModel.addElement(factorial);
	     listModel.addElement(e);
	     listModel.addElement(Bern);
	     listModel.addElement(L);
	     listModel.addElement(fib);
	     listModel.addElement(radians);
	     listModel.addElement(abs);
	     listModel.addElement(ln);
	     listModel.addElement(exp);
	     listModel.addElement(last);
   	     
   	     list= new JList<Operation>(listModel);  // right component
   	     list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
   	     p3.add(list,BorderLayout.CENTER); 
   	     list.addMouseListener(new MouseAdapter() {
   	    	 public void mouseClicked(MouseEvent ev) {
   	    		 int n=list.getSelectedIndex();
   	    		 super.mouseClicked(ev);
   	    		 if (ev.getClickCount()==2) {
   	    			    enterb.setText(enterb.getText()+listModel.getElementAt(n).getCode());
  			            enterb.requestFocusInWindow();
  			          if (listModel.getElementAt(n).getCode().contains("()")) {
  			        	enterb.setCaretPosition(enterb.getText().length()-1);
                      }
   	    		 }
   	    	 }
   	     });
   	     btn= new JButton("Evaluate");
   	     btn.addActionListener(handler);
   	     p3.add(btn,BorderLayout.SOUTH);
   	     add(p3,BorderLayout.EAST); 	    
     }   
         Operation radians= new Operation("radians to degrees function", "deg()");           // five functions
	     Operation fib= new Operation("Fibonacci number", "Fib()");
	     Operation abs= new Operation("absolut value function","abs()");
  	     Operation ln= new Operation("natural logarithm","ln()");
  	     Operation exp= new Operation("exponential function","exp()");     
     
     
         Operation e = new Operation("Euler's number","e");         // three constants
	     Operation Bern= new Operation("Bernstein's constant","[Bern]");
	     Operation L= new Operation("Levy's constant","[L.]");
         
     
   	     Operation modulo = new Operation("modulo function","#");        // three operators
  	     Operation exponentiation= new Operation("exponentiation","^");
  	     Operation factorial= new Operation("factorial","!");
  	     
  	     Operation last= new Operation("last result","");    // last result
  	     
  	     public void evalfunc() {
  	    	    string=enterb.getText();
				Expression expression = new Expression(enterb.getText());
				if (expression.checkSyntax()) {
					Double result = expression.calculate();
					String lastr=Double.toString(result);
					enterb.setText("");
					JOptionPane.showMessageDialog(null,string+"="+result);
					area.append("\n");
			   	    area.append(string+"="+result);
			   	    last.setCode(lastr);
					}
					else {
						 String error = (expression.getErrorMessage());
				         JOptionPane.showMessageDialog(null,error,"Error!",JOptionPane.ERROR_MESSAGE);
					     }					
  	     }	  
  	     
  	     
  	   public class TheHandler implements ActionListener {   // the Handler class

  			public void actionPerformed(ActionEvent event) {
  				if (event.getSource()==enterb){
  					evalfunc();
  				}  				 			
  				else if (event.getSource()==btn){
  					evalfunc();  						 
  				} 	
  				
  				else if (event.getSource()==reset) {
  					area.setText("");
  					enterb.setText("");
  				}  				
  				else if (event.getSource()==exit) {
  					System.exit(0); 
  				}
  			}			
  		}
	}
  
