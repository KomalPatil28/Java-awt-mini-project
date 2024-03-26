package miniProjects;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame 
{
    private static final long serialVersionUID = 1L;
    private TextField display;
    private Button[][] buttons;

    public Calculator() 
    {
        setTitle("Calculator");
        setSize(300, 500);
        setLayout(new BorderLayout());

        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridLayout(4, 4));
        add(buttonPanel, BorderLayout.CENTER);

        String[] buttonLabels = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+" };

        buttons = new Button[4][4];
        ButtonClickListener buttonClickListener = new ButtonClickListener();

        for (int i = 0; i < 16; i++) 
        {
            buttons[i / 4][i % 4] = new Button(buttonLabels[i]);
            buttons[i / 4][i % 4].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i / 4][i % 4].addActionListener(buttonClickListener);
            buttonPanel.add(buttons[i / 4][i % 4]);
        }
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

        setVisible(true);
    }
    private class ButtonClickListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    	    Button source = (Button) e.getSource();
    	    String command = source.getLabel();
    	    switch (command)
    	    {
    	        case "C":
    	            display.setText("");
    	            break;
    	        case "=":
    	            try 
    	            {
    	                String expression = display.getText();
    	                double result = evaluateExpression(expression);
    	                display.setText(expression + " = " + Double.toString(result)); 
    	            } 
    	            catch (Exception ex) 
    	            {
    	                display.setText("Error");
    	            }
    	            break;
    	        case "+":
    	        case "-":
    	        case "*":
    	        case "/":
    	            display.setText(display.getText() + " " + command + " ");
    	            break;
    	        default:
    	            display.setText(display.getText() + command);
    	    }
    	}
    }
    private double evaluateExpression(String expression) 
    {
        String str = "(?<=[-+*/])|(?=[-+*/])";
        String[] taken = expression.split(str);
        double result = Double.parseDouble(taken[0]);
        for (int i = 1; i < taken.length; i += 2) 
        
        {
            String operator = taken[i];
            double operand = Double.parseDouble(taken[i + 1]);
            switch (operator) 
            {
            case "+":
                result += operand;
                break;
            case "-":
                result -= operand;
                break;
            case "*":
                result *= operand;
                break;
            case "/":
                result /= operand;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) 
    {
        new Calculator();
    }
}
