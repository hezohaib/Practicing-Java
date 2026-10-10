import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator extends JFrame implements ActionListener {

    // Special symbols written as unicode escapes so they work on every system
    private static final String DIVIDE = "\u00F7";     // division sign
    private static final String MULTIPLY = "\u00D7";   // multiplication sign
    private static final String MINUS = "\u2212";      // minus sign
    private static final String PLUS = "+";
    private static final String PLUS_MINUS = "\u00B1"; // plus/minus sign

    // Colors used for the buttons and the window
    private static final Color BACKGROUND = new Color(30, 30, 30);
    private static final Color FUNCTION_COLOR = new Color(166, 166, 166);
    private static final Color OPERATOR_COLOR = new Color(255, 149, 0);
    private static final Color NUMBER_COLOR = new Color(51, 51, 51);

    private final JTextField display;

    // Calculator state
    private double firstNumber = 0;        // number stored before the operator
    private String operator = "";          // pending operator (empty if none)
    private boolean startNewNumber = true; // true = next digit replaces the display

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(380, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // open in the center of the screen
        setResizable(false);
        getContentPane().setBackground(BACKGROUND);

        // Display field at the top (read-only)
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(15, 15, 15));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(display, BorderLayout.NORTH);

        // Panel holding all buttons in a 5 x 4 grid
        JPanel panel = new JPanel(new GridLayout(5, 4, 8, 8));
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "C", PLUS_MINUS, "%", DIVIDE,
            "7", "8", "9", MULTIPLY,
            "4", "5", "6", MINUS,
            "1", "2", "3", PLUS,
            "0", ".", "DEL", "="
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            styleButton(btn, text);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    // Sets font and colors of a button depending on its type
    private void styleButton(JButton btn, String text) {
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true); // makes sure the background color is visible

        if (text.equals("C") || text.equals(PLUS_MINUS)
                || text.equals("%") || text.equals("DEL")) {
            btn.setBackground(FUNCTION_COLOR);
            btn.setForeground(Color.BLACK);
        } else if (isOperator(text) || text.equals("=")) {
            btn.setBackground(OPERATOR_COLOR);
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(NUMBER_COLOR);
            btn.setForeground(Color.WHITE);
        }
    }

    private boolean isOperator(String text) {
        return text.equals(PLUS) || text.equals(MINUS)
                || text.equals(MULTIPLY) || text.equals(DIVIDE);
    }

    // Called every time a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            // After an error, start fresh before handling the new click
            if (display.getText().equals("Error")) {
                resetCalculator();
            }

            if (command.matches("[0-9]")) {
                appendDigit(command);
            } else if (command.equals(".")) {
                appendDot();
            } else if (command.equals("C")) {
                resetCalculator();
            } else if (command.equals("DEL")) {
                deleteLast();
            } else if (command.equals(PLUS_MINUS)) {
                double value = Double.parseDouble(display.getText());
                display.setText(format(-value));
            } else if (command.equals("%")) {
                double value = Double.parseDouble(display.getText());
                display.setText(format(value / 100));
            } else if (command.equals("=")) {
                calculateResult();
            } else {
                chooseOperator(command);
            }
        } catch (Exception ex) {
            // Any problem (for example divide by zero) shows "Error"
            display.setText("Error");
            operator = "";
            startNewNumber = true;
        }
    }

    // Adds a digit (0-9) to the display
    private void appendDigit(String digit) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(digit);
        } else {
            display.setText(display.getText() + digit);
        }
        startNewNumber = false;
    }

    // Adds a decimal point, but only one per number
    private void appendDot() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    // Removes the last typed character
    private void deleteLast() {
        if (startNewNumber) {
            return; // nothing typed yet, so nothing to delete
        }
        String text = display.getText();
        text = text.substring(0, text.length() - 1);
        if (text.isEmpty() || text.equals("-")) {
            text = "0";
        }
        display.setText(text);
    }

    // Stores the first number and the chosen operator
    private void chooseOperator(String newOperator) {
        // If an operator is already waiting and a second number was typed,
        // calculate it first so that 5 + 3 + 2 works correctly
        if (!operator.isEmpty() && !startNewNumber) {
            calculateResult();
        }
        firstNumber = Double.parseDouble(display.getText());
        operator = newOperator;
        startNewNumber = true; // the next digit starts the second number
    }

    // Calculates the result and shows it on the display
    private void calculateResult() {
        if (operator.isEmpty()) {
            return; // no operator selected, nothing to calculate
        }
        double secondNumber = Double.parseDouble(display.getText());
        double result = compute(firstNumber, secondNumber, operator);
        display.setText(format(result));
        operator = "";
        startNewNumber = true; // the next digit starts a new calculation
    }

    // Performs the actual math operation
    private double compute(double a, double b, String op) {
        switch (op) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new IllegalStateException("Unknown operator: " + op);
        }
    }

    // Formats a number nicely: 8.0 becomes 8, and 0.1 + 0.2 becomes 0.3
    private String format(double value) {
        return new BigDecimal(value)
                .setScale(10, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }

    // Clears everything and returns to the starting state
    private void resetCalculator() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        // Swing windows should be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}