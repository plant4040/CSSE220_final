import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		
		
		JFrame window = new JFrame("");
        
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        
        // Creates the main game panel
        Panel gamePanel = new Panel();
        window.add(gamePanel);

        // Sets window size
        window.setSize(1000, 400);


        // Centers the window
        window.setLocationRelativeTo(null);
       
        
        window.setVisible(true);

        }
   }



