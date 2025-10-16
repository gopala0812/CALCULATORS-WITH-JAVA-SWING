package js;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator implements ActionListener {

    JFrame frame;
    JTextField displayField;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, decButton, eqButton, clrButton, delButton;
    JButton sinButton, cosButton, tanButton, logButton, lnButton, sqrtButton, sqButton, powButton, factButton, piButton, eButton;
    JPanel mainPanel, numberPanel, operatorPanel, scientificPanel;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Display
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        frame.add(displayField, gbc);

        // Number Buttons Panel
        numberPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberPanel.add(numberButtons[i]);
        }
        numberButtons[0] = new JButton("0");
        numberButtons[0].addActionListener(this);
        numberPanel.add(new JLabel("")); // empty space
        numberPanel.add(numberButtons[0]);
        decButton = new JButton(".");
        decButton.addActionListener(this);
        numberPanel.add(decButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.7;
        gbc.weighty = 0.7;
        frame.add(numberPanel, gbc);

        // Operator Buttons Panel
        operatorPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");

        JButton[] ops = {addButton, subButton, mulButton, divButton, eqButton, clrButton};
        for (JButton btn : ops) {
            btn.addActionListener(this);
            operatorPanel.add(btn);
        }

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        frame.add(operatorPanel, gbc);

        // Scientific Panel
        scientificPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        sqrtButton = new JButton("√");
        sqButton = new JButton("x²");
        powButton = new JButton("x^y");
        factButton = new JButton("!");
        piButton = new JButton("π");
        eButton = new JButton("e");
        delButton = new JButton("Del");

        JButton[] sciBtns = {sinButton, cosButton, tanButton, logButton, lnButton,
                sqrtButton, sqButton, powButton, factButton, piButton, eButton, delButton};

        for (JButton btn : sciBtns) {
            btn.addActionListener(this);
            scientificPanel.add(btn);
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        frame.add(scientificPanel, gbc);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Number buttons
            for (int i = 0; i <= 9; i++) {
                if (e.getSource() == numberButtons[i]) {
                    displayField.setText(displayField.getText().concat(String.valueOf(i)));
                    return;
                }
            }

            if (e.getSource() == decButton) displayField.setText(displayField.getText().concat("."));

            // Operators
            if (e.getSource() == addButton || e.getSource() == subButton ||
                    e.getSource() == mulButton || e.getSource() == divButton || e.getSource() == powButton) {
                num1 = Double.parseDouble(displayField.getText());
                if (e.getSource() == addButton) operator = '+';
                if (e.getSource() == subButton) operator = '-';
                if (e.getSource() == mulButton) operator = '*';
                if (e.getSource() == divButton) operator = '/';
                if (e.getSource() == powButton) operator = '^';
                displayField.setText("");
            }

            if (e.getSource() == eqButton) {
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                    case '^': result = Math.pow(num1, num2); break;
                }
                displayField.setText(String.valueOf(result));
                num1 = result;
            }

            if (e.getSource() == clrButton) displayField.setText("");
            if (e.getSource() == delButton) {
                String s = displayField.getText();
                if (!s.isEmpty()) displayField.setText(s.substring(0, s.length() - 1));
            }

            // Scientific functions
            if (e.getSource() == sinButton) displayField.setText(String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(displayField.getText())))));
            if (e.getSource() == cosButton) displayField.setText(String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(displayField.getText())))));
            if (e.getSource() == tanButton) displayField.setText(String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(displayField.getText())))));
            if (e.getSource() == logButton) displayField.setText(String.valueOf(Math.log10(Double.parseDouble(displayField.getText()))));
            if (e.getSource() == lnButton) displayField.setText(String.valueOf(Math.log(Double.parseDouble(displayField.getText()))));
            if (e.getSource() == sqrtButton) displayField.setText(String.valueOf(Math.sqrt(Double.parseDouble(displayField.getText()))));
            if (e.getSource() == sqButton) displayField.setText(String.valueOf(Math.pow(Double.parseDouble(displayField.getText()), 2)));
            if (e.getSource() == factButton) {
                int n = Integer.parseInt(displayField.getText());
                int f = 1;
                for (int i = 1; i <= n; i++) f *= i;
                displayField.setText(String.valueOf(f));
            }
            if (e.getSource() == piButton) displayField.setText(displayField.getText() + Math.PI);
            if (e.getSource() == eButton) displayField.setText(displayField.getText() + Math.E);

        } catch (Exception ex) {
            displayField.setText("Error");
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
