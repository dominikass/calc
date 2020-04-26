package pl.dominikas.calculatorApp;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.mariuszgromada.math.mxparser.*;

public class App 
{
    public static void main( String[] args )
    {
    	
    	        SwingUtilities.invokeLater(new Runnable() {
    	        public void run() {
    	    	MainFrame frame= new MainFrame();
    	      	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	      	frame.setVisible(true);
    	      }
    	    });
    	
    	  }
    			
    }

