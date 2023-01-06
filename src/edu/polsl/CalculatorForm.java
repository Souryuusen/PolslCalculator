package edu.polsl;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorForm {
    private JPanel panelMain;
    private JPanel panelDisplay;
    private JPanel panelControl;
    private JTextField fieldValue;
    private JButton btnClear;
    private JButton btnDivide;
    private JButton btnMultiply;
    private JButton btnSubtract;
    private JButton btnSeven;
    private JButton btnFour;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnFive;
    private JButton btnEight;
    private JButton btnThree;
    private JButton btnSix;
    private JButton btnNine;
    private JButton btnZero;
    private JButton btnComma;
    private JButton btnEqual;
    private JButton btnAdd;

    private BigDecimal firstValue, secondValue;
    private boolean operationSelected;
    private boolean valueSwitchDemand;
    private char operation;

    public CalculatorForm() {
        btnZero.addActionListener(new NumberButtonAction());
        btnComma.addActionListener(new NumberButtonAction());
        btnOne.addActionListener(new NumberButtonAction());
        btnTwo.addActionListener(new NumberButtonAction());
        btnThree.addActionListener(new NumberButtonAction());
        btnFour.addActionListener(new NumberButtonAction());
        btnFive.addActionListener(new NumberButtonAction());
        btnSix.addActionListener(new NumberButtonAction());
        btnSeven.addActionListener(new NumberButtonAction());
        btnEight.addActionListener(new NumberButtonAction());
        btnNine.addActionListener(new NumberButtonAction());

        btnEqual.addActionListener(new OperationButtonAction());
        btnAdd.addActionListener(new OperationButtonAction());
        btnSubtract.addActionListener(new OperationButtonAction());
        btnMultiply.addActionListener(new OperationButtonAction());
        btnDivide.addActionListener(new OperationButtonAction());

        btnClear.addActionListener((e) -> {
            firstValue = BigDecimal.valueOf(0.0);
            secondValue = BigDecimal.valueOf(0.0);
            fieldValue.setText("0");
            valueSwitchDemand = false;
            operationSelected = false;
            operation = ' ';
        });
    }

    private void calculateResult() {
        BigDecimal result = null;
        boolean success = false;
        switch (operation) {
            case '+': {
                result = firstValue.add(secondValue, MathContext.DECIMAL128).stripTrailingZeros();
                success = true;
                break;
            }
            case '-': {
                result = firstValue.subtract(secondValue, MathContext.DECIMAL128).stripTrailingZeros();
                success = true;
                break;
            }
            case '*': {
                result = firstValue.multiply(secondValue, MathContext.DECIMAL128).stripTrailingZeros();
                success = true;
                break;
            }
            case '/': {
                try {
                    result = firstValue.divide(secondValue, RoundingMode.HALF_EVEN).stripTrailingZeros();
                    success = true;
                } catch (ArithmeticException ex) {
                    success = false;
                    System.err.println("Cannot Divide By 0!!");
                }
                break;
            }
            default: {
                result = null;
                success = false;
                System.err.println("Unsupported Math Operation Detected!!");
                break;
            }
        }
        if (result != null && success) {
            String resultText = result.toPlainString();
            fieldValue.setText(resultText);
            firstValue = result;
            operationSelected = false;
            valueSwitchDemand = true;
        }
    }

    private class NumberButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Read Of Current Value Field Text
            String currentText = fieldValue.getText().trim();
            String newValue = ((JButton) (e.getSource())).getText();
            // Addition Of New Value To Current String
            if (!valueSwitchDemand) {
                if (currentText.equals("0")) {
                    if (newValue.equals(".")) {
                        currentText += newValue;
                    } else {
                        currentText = newValue;
                    }
                } else {
                    if (newValue.equals(".") && !currentText.contains(".")) {
                        currentText += newValue;
                    } else if (!newValue.equals(".")) {
                        currentText += newValue;
                    }
                }
            } else {
                valueSwitchDemand = false;
                currentText = newValue;
            }
            // Set Result String To Value Field
            fieldValue.setText(currentText);
        }
    }

    private class OperationButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            char operationType = ((JButton) e.getSource()).getText().charAt(0);

            if (operationSelected && operationType == '=') {
                secondValue = BigDecimal.valueOf(Double.parseDouble(fieldValue.getText()));
                calculateResult();
            } else if (operationType != '=' && !operationSelected) {
                operation = operationType;
                if (!operationSelected) {
                    operationSelected = true;
                    valueSwitchDemand = true;
                    firstValue = BigDecimal.valueOf(Double.parseDouble(fieldValue.getText()));
                }
            } else if ((operationSelected && !valueSwitchDemand) || operationType == '=') {
                if (!firstValue.toPlainString().equalsIgnoreCase(fieldValue.getText().trim())) {
                    secondValue = BigDecimal.valueOf(Double.parseDouble(fieldValue.getText()));
                }
                calculateResult();
            }
            System.out.println();
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(0, 0));
        panelMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panelDisplay = new JPanel();
        panelDisplay.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelDisplay, BorderLayout.NORTH);
        fieldValue = new JTextField();
        fieldValue.setEditable(false);
        fieldValue.setEnabled(true);
        fieldValue.setHorizontalAlignment(0);
        fieldValue.setText("0");
        panelDisplay.add(fieldValue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelControl = new JPanel();
        panelControl.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelControl, BorderLayout.CENTER);
        btnClear = new JButton();
        btnClear.setText("CL");
        panelControl.add(btnClear, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnDivide = new JButton();
        btnDivide.setText("/");
        panelControl.add(btnDivide, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMultiply = new JButton();
        btnMultiply.setText("*");
        panelControl.add(btnMultiply, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSubtract = new JButton();
        btnSubtract.setText("-");
        panelControl.add(btnSubtract, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSeven = new JButton();
        btnSeven.setText("7");
        panelControl.add(btnSeven, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnFour = new JButton();
        btnFour.setText("4");
        panelControl.add(btnFour, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnOne = new JButton();
        btnOne.setText("1");
        panelControl.add(btnOne, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnTwo = new JButton();
        btnTwo.setText("2");
        panelControl.add(btnTwo, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnFive = new JButton();
        btnFive.setText("5");
        panelControl.add(btnFive, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnEight = new JButton();
        btnEight.setText("8");
        panelControl.add(btnEight, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnThree = new JButton();
        btnThree.setText("3");
        panelControl.add(btnThree, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSix = new JButton();
        btnSix.setText("6");
        panelControl.add(btnSix, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNine = new JButton();
        btnNine.setText("9");
        panelControl.add(btnNine, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnComma = new JButton();
        btnComma.setText(".");
        panelControl.add(btnComma, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnZero = new JButton();
        btnZero.setContentAreaFilled(true);
        btnZero.setText("0");
        panelControl.add(btnZero, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnEqual = new JButton();
        btnEqual.setText("=");
        panelControl.add(btnEqual, new GridConstraints(2, 3, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAdd = new JButton();
        btnAdd.setText("+");
        panelControl.add(btnAdd, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public JPanel getPanelMain() {
        return this.panelMain;
    }


}
