package tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	//Creating the objects we will need. 
	
	Random random = new Random(); 
	JFrame frame= new JFrame();
	JPanel title_panel= new JPanel();
	JPanel button_panel= new JPanel();
	JLabel textfield= new JLabel();
	JButton[] buttons= new JButton[9];
	JButton replaybutton=new JButton();
	boolean player1_turn;
	JLabel player1ScoreLabel;
    JLabel player2ScoreLabel;
    int player1Score;
    int player2Score;
	
	TicTacToe(){
		
		
		//Create the frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,1000);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//Create the textfield that appears either "TicTacToe","Draw" or who is the Winner if there is one.
		
		textfield.setBackground(new Color(0,139,139));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.HANGING_BASELINE,50));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TicTacToe");
		textfield.setOpaque(true);
		
		//Create the Layout for the title.
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(4,0,800,400);
		
		//Create the Layout for the Background.
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(125,125,125));
		
		// Creating the buttons , the boxes each player will use .
		
		for(int i=0;i<9;i++) {
			
			buttons[i]= new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.ITALIC,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(new Color(95,158,160));
		}
		
		//Adding the title and placing it properly.
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		
		// Create the labels for player scores
        player1ScoreLabel = new JLabel("Player 1 (O) Score: 0");
        player1ScoreLabel.setForeground(new Color(64,224,208));
        player1ScoreLabel.setFont(new Font("Ink Free", Font.PLAIN, 37));
        player1ScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        

        player2ScoreLabel = new JLabel("Player 2 (X) Score: 0");
        player2ScoreLabel.setForeground(new Color(64,224,208));
        player2ScoreLabel.setFont(new Font("Ink Free", Font.PLAIN, 37));
        player2ScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        

        // Add the labels to the title panel
        title_panel.add(player1ScoreLabel, BorderLayout.WEST);
        title_panel.add(player2ScoreLabel, BorderLayout.EAST);
		//Calling the turn method to see who starts.
		
		Turn();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Pressing a button each time
		
		for(int i=0;i<9;i++) {
		
			if(e.getSource()==buttons[i]) {
				
				//Whose player turn is
				
				if(player1_turn) {
					
					//If the box is not filled
					
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(205,92,92));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O turn");
						
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						
					}
				}
				
				//Checking each time if we have a winner or a draw
				
				check(); 
				
			}
		}
		
		
	}
	
	public void Turn() {
		
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else {
			player1_turn=false;
			textfield.setText("0 turn");
		}
	}
	
	public void check() {
		
		//Checking if we have a draw
		boolean isDraw=true;
		for(int i=0;i<9;i++) {
			if (buttons[i].getText().isEmpty()) {
				isDraw=false;
				break;
			}
		}
		if(isDraw) {
			textfield.setText("It is a Draw!");
			endGame();
		}
		
		//Check first row for X
		if( 
				(buttons[0].getText()=="X")&& 
				(buttons[1].getText()=="X")&&
				(buttons[2].getText()=="X") 
				) {
			XWins(0,1,2);
		}
		//Check second row for X
		if( 
				(buttons[3].getText()=="X")&& 
				(buttons[4].getText()=="X")&&
				(buttons[5].getText()=="X") 
				) {
			XWins(3,4,5);
		}
		//Check third row for X
		if( 
				(buttons[6].getText()=="X")&& 
				(buttons[7].getText()=="X")&&
				(buttons[8].getText()=="X") 
				) {
			XWins(6,7,8);
		}
		
		//Check first column for X
		
		if( 
				(buttons[0].getText()=="X")&& 
				(buttons[3].getText()=="X")&&
				(buttons[6].getText()=="X") 
				) {
			XWins(0,3,6);
		}
		
		//Check second column for X
		
		if( 
				(buttons[1].getText()=="X")&& 
				(buttons[4].getText()=="X")&&
				(buttons[7].getText()=="X") 
				) {
			XWins(1,4,7);
		}
		
		//Check third column for X
		
		if( 
				(buttons[2].getText()=="X")&& 
				(buttons[5].getText()=="X")&&
				(buttons[8].getText()=="X") 
				) {
			XWins(2,5,8);
		}
		
		//Check left starting diagonal

		if( 
				(buttons[0].getText()=="X")&& 
				(buttons[4].getText()=="X")&&
				(buttons[8].getText()=="X") 
				) {
			XWins(0,4,8);
		}
		
		//Check right starting diagonal
		
		if( 
				(buttons[2].getText()=="X")&& 
				(buttons[4].getText()=="X")&&
				(buttons[6].getText()=="X") 
				) {
			XWins(2,4,6);
		}

		//Check first row for O
		
		if( 
				(buttons[0].getText()=="O")&& 
				(buttons[1].getText()=="O")&&
				(buttons[2].getText()=="O") 
				) {
			OWins(0,1,2);
		}
		
		//Check second row for O
		
		if( 
				(buttons[3].getText()=="O")&& 
				(buttons[4].getText()=="O")&&
				(buttons[5].getText()=="O") 
				) {
			OWins(3,4,5);
		}
		
		//Check third row for O
		
		if( 
				(buttons[6].getText()=="O")&& 
				(buttons[7].getText()=="O")&&
				(buttons[8].getText()=="O") 
				) {
			OWins(6,7,8);
		}
		
		//Check first column for O
		
		if( 
				(buttons[0].getText()=="O")&& 
				(buttons[3].getText()=="O")&&
				(buttons[6].getText()=="O") 
				) {
			OWins(0,3,6);
		}
		
		//Check second column for O
		
		if( 
				(buttons[1].getText()=="O")&& 
				(buttons[4].getText()=="O")&&
				(buttons[7].getText()=="O") 
				) {
			OWins(1,4,7);
		}
		
		//Check third column for O
		
		if( 
				(buttons[2].getText()=="O")&& 
				(buttons[5].getText()=="O")&&
				(buttons[8].getText()=="O") 
				) {
			OWins(2,5,8);
		}
		
		//Check left starting diagonal

		if( 
				(buttons[0].getText()=="O")&& 
				(buttons[4].getText()=="O")&&
				(buttons[8].getText()=="O") 
				) {
			OWins(0,4,8);
		}
		
		//Check right starting diagonal
		
		if( 
				(buttons[2].getText()=="O")&& 
				(buttons[4].getText()=="O")&&
				(buttons[6].getText()=="O") 
				) {
			OWins(2,4,6);
		}
		
		
	}
	
	//If XWins 
	
	public void XWins(int a,int b,int c) {
		buttons[a].setBackground(new Color(255,215,0));
		buttons[b].setBackground(new Color(255,215,0));
		buttons[c].setBackground(new Color(255,215,0));
		
		// Increment Player 2(X) score
		
        player2Score++; 
        player2ScoreLabel.setText("Player 2 (X) Score: " + player2Score);
		textfield.setText("X won !");
		endGame();
	}
	
	//If OWins
	
	public void OWins(int a ,int b ,int c) {
		buttons[a].setBackground(new Color(255,215,0));
		buttons[b].setBackground(new Color(255,215,0));
		buttons[c].setBackground(new Color(255,215,0));
		
		// Increment Player 1(O) score
		
		player1Score++; 
	    player1ScoreLabel.setText("Player 1 (O) Score: " + player1Score);
		textfield.setText("O won !");
		endGame();
		
	}
	
	//Ending the game and calling for ReplayButton
	
	public boolean endGame() {
		for(int i=0;i<9;i++) {
			buttons[i].removeActionListener(this);
			
		}
		
		initializeReplayButton();
		return true;
	}
	
	//Enabling the feature for a replay
	
	public void initializeReplayButton() {
		
		//Creating the button.
	    
		replaybutton = new JButton("Replay"); 
	    replaybutton.setFont(new Font("Ink Free", Font.BOLD, 40));
	    replaybutton.setFocusable(false);
	    replaybutton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// Call resetGame() when the replay button is pressed
	            
	    		resetGame(); 
	    		// Remove the replay button from the title_panel
	    		
	    		title_panel.remove(replaybutton); 
	            
	    	}
	    });
	    
	  //Placing the replay button South of the game.
	    
	  title_panel.add(replaybutton, BorderLayout.SOUTH); 
	}
	
	//Reseting everything
	
	public void resetGame() {
		
		//Clear the included text on buttons
		
		for(int i=0;i<9;i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setBackground(new Color(95,158,160));
			
	}
		
	//Reset turn and update text fields
		
	Turn();
	
	
	}
	
	
}
