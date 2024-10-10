import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator {
    private JFrame frame;
    private JTextField textField;

    // Declare all the buttons
    private JButton bz, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton bp, bmn, bml, bd, be, bc;

    private String currentInput = "";
    private double firstValue = 0;
    private String operation = "";
    private boolean isOperatorPressed = false;  // Track whether an operator is pressed

    public calculator() {
        // Initialize the frame and text field
        frame = new JFrame("Calculator");
        textField = new JTextField();
        textField.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false); // Display only

        // Call a method to initialize the buttons
        initComponents();

        // Set up the UI
        setupLayout();
    }

    private void initComponents() {
        // Initialize the number buttons
        bz = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        // Initialize operation buttons
        bp = new JButton("+");
        bmn = new JButton("-");
        bml = new JButton("*");
        bd = new JButton("/");
        be = new JButton("=");
        bc = new JButton("C");

        // Add listeners to the buttons
        addFunctionality();
    }

    private void setupLayout() {
        // Set up the layout and frame
        frame.setSize(400, 500);
        frame.setLayout(new java.awt.BorderLayout());

        // Add text field to the top
        frame.add(textField, java.awt.BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.GridLayout(5, 4, 10, 10));

        // Add the buttons to the panel
        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(bd);

        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(bml);

        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(bmn);

        buttonPanel.add(bz);
        buttonPanel.add(bc);
        buttonPanel.add(be);
        buttonPanel.add(bp);

        // Add button panel to the frame
        frame.add(buttonPanel, java.awt.BorderLayout.CENTER);

        // Set the frame to close on exit and make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addFunctionality() {
        // Number buttons' action listeners
        bz.addActionListener(e -> appendToInput("0"));
        b1.addActionListener(e -> appendToInput("1"));
        b2.addActionListener(e -> appendToInput("2"));
        b3.addActionListener(e -> appendToInput("3"));
        b4.addActionListener(e -> appendToInput("4"));
        b5.addActionListener(e -> appendToInput("5"));
        b6.addActionListener(e -> appendToInput("6"));
        b7.addActionListener(e -> appendToInput("7"));
        b8.addActionListener(e -> appendToInput("8"));
        b9.addActionListener(e -> appendToInput("9"));

        // Operation buttons' action listeners
        bp.addActionListener(e -> setOperation("+"));
        bmn.addActionListener(e -> setOperation("-"));
        bml.addActionListener(e -> setOperation("*"));
        bd.addActionListener(e -> setOperation("/"));

        // Equals button to calculate the result
        be.addActionListener(e -> calculateResult());

        // Clear button to reset the calculator
        bc.addActionListener(e -> clearInput());
    }

    private void appendToInput(String value) {
        // If an operator was pressed, clear the input for the second number
        if (isOperatorPressed) {
            currentInput = "";
            isOperatorPressed = false;  // Reset operator flag after entering the second number
        }
        currentInput += value;
        textField.setText(currentInput);
    }

    private void setOperation(String operation) {
        this.operation = operation;
        firstValue = Double.parseDouble(currentInput);
        textField.setText(currentInput);  // Display the first value after operator selection
        isOperatorPressed = true;  // Set flag to indicate that an operator was pressed
    }

    private void calculateResult() {
        double secondValue = Double.parseDouble(currentInput);
        double result = 0;

        switch (operation) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
        }

        textField.setText(String.valueOf(result));
        currentInput = String.valueOf(result); // to continue calculating with the result
        isOperatorPressed = false;  // Reset the operator pressed flag after calculation
    }

    private void clearInput() {
        currentInput = "";
        firstValue = 0;
        operation = "";
        textField.setText("");
        isOperatorPressed = false;  // Reset operator flag when clearing input
    }

    public static void main(String[] args) {
        new calculator();
    }
}
