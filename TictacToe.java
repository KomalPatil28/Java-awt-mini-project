package miniProjects;
import java.awt.*;
import java.awt.event.*;
public class TictacToe extends Frame{
	private static final long serialVersionUID = 1L;
	private Button[][] buttons;
	private boolean xTurn = true;
    private Label resultLabel;
    private Button restartButton; 
    private Label turnLabel;
	public TictacToe()
	{
		setTitle("Tic Tac Toe Game");
        setSize(300, 400);
        setLayout(new GridLayout(4,3));
        setVisible(true);
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        buttons=new Button[3][3];
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                buttons[i][j] = new Button();
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        buttonClicked(row, col);
                    }
                });
                Font font = new Font("Arial", Font.BOLD, 40);
                buttons[i][j].setFont(font);
                add(buttons[i][j]);
            }
        }
        resultLabel = new Label("Game Result Here", Label.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        resultLabel.setFont(labelFont);
        add(resultLabel);

        restartButton = new Button("Restart");
        restartButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                restartGame();
            }
        });
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        restartButton.setFont(buttonFont);
        add(restartButton);
        
        turnLabel = new Label("X's Turn", Label.CENTER);
        turnLabel.setFont(labelFont);
        add(turnLabel);
    }
	 private void buttonClicked(int row, int col) 
	 {
	        if (buttons[row][col].getLabel().isEmpty()) 
	        {
	            if (xTurn) 
	            {
	                buttons[row][col].setLabel("X");
	                turnLabel.setText("O's Turn");
	            } 
	            else 
	            {
	                buttons[row][col].setLabel("O");
	                turnLabel.setText("X's Turn");
	            }
	            xTurn = !xTurn;
	            checkGameResult();
	        }
	    }
	 private void checkGameResult() 
	 {
	        for (int i = 0; i < 3; i++) 
	        {
	            if (!buttons[i][0].getLabel().isEmpty() &&
	                buttons[i][0].getLabel().equals(buttons[i][1].getLabel()) &&
	                buttons[i][0].getLabel().equals(buttons[i][2].getLabel())) 
	            {
	                displayResult(buttons[i][0].getLabel() + " wins!");
	                return;
	            }
	        }
	        for (int i = 0; i < 3; i++) 
	        {
	            if (!buttons[0][i].getLabel().isEmpty() &&
	                buttons[0][i].getLabel().equals(buttons[1][i].getLabel()) &&
	                buttons[0][i].getLabel().equals(buttons[2][i].getLabel())) 
	            {
	                displayResult(buttons[0][i].getLabel() + " wins!");
	                return;
	            }
	        }
	        if (!buttons[0][0].getLabel().isEmpty() &&
	                buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) &&
	                buttons[0][0].getLabel().equals(buttons[2][2].getLabel())) 
	        	{
	                displayResult(buttons[0][0].getLabel() + " wins!");
	                return;
	            }
	        if (!buttons[0][2].getLabel().isEmpty() &&
	                buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) &&
	                buttons[0][2].getLabel().equals(buttons[2][0].getLabel())) 
	        	{
	                displayResult(buttons[0][2].getLabel() + " wins!");
	                return;
	            }
	        boolean draw = true;
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++) 
	            {
	                if (buttons[i][j].getLabel().isEmpty()) 
	                {
	                    draw = false;
	                    break;
	                }
	            }
	            if (!draw) 
	            {
	                break; 
	            }
	        }
	        if (draw) 
	        {
	            displayResult("It's a draw!");
	        }
	    }
	 private void displayResult(String result) 
	 {
	        resultLabel.setText(result);
	        for (int i = 0; i < 3; i++) 
	        {
	            for (int j = 0; j < 3; j++) 
	            {
	                buttons[i][j].setEnabled(false);
	            }
	        }
	    }
	 private void restartGame() 
	 {
	        for (int i = 0; i < 3; i++) 
	        {
	            for (int j = 0; j < 3; j++) 
	            {
	                buttons[i][j].setLabel("");
	                buttons[i][j].setEnabled(true);
	            }
	        }
	        resultLabel.setText("Game Result Here");
	        xTurn = true;
	    }
	 public static void main(String[]args)
	 {
		 new TictacToe();
	 }
}
