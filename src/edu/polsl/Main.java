package edu.polsl;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        JFrame window = new JFrame();
        window.setTitle("Kalkulator v1.0");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setContentPane(new CalculatorForm().getPanelMain());

        window.pack();
        window.setVisible(true);
    }


}
