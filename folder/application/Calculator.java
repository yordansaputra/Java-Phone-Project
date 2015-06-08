/*
 * Copyright (C) 2014, Ryan Hermansyah, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */
package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Calculator
 *
 * @author Ryan Hermansyah, Samuel I. Gunadi
 */
public class Calculator
        extends Application {

    String simpan;
    private JTextField displayField;
    private boolean angkaPertama = true;
    private String operator = "=";
    private OtakKalkulator logik = new OtakKalkulator();

    public Calculator() {
        _id = "Calculator";

        displayField = new JTextField("0", 20);
        displayField.setPreferredSize(new Dimension(369, 86));
        displayField.setHorizontalAlignment(JTextField.LEFT);

        // Non-editable
        displayField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));
        ActionListener numListener = new NumListener();
        ActionListener clearListener = new ClearListener();
        JButton tombol0 = new JButton("0");
        JButton tombol1 = new JButton("1");
        JButton tombol2 = new JButton("2");
        JButton tombol3 = new JButton("3");
        JButton tombol4 = new JButton("4");
        JButton tombol5 = new JButton("5");
        JButton tombol6 = new JButton("6");
        JButton tombol7 = new JButton("7");
        JButton tombol8 = new JButton("8");
        JButton tombolHapusSemua = new JButton("C");
        JButton tombol9 = new JButton("9");
        JButton tombolKoma = new JButton(".");
        buttonPanel.add(tombol1);
        tombol1.addActionListener(numListener);
        buttonPanel.add(tombol2);
        tombol2.addActionListener(numListener);
        buttonPanel.add(tombol3);
        tombol3.addActionListener(numListener);
        buttonPanel.add(tombol4);
        tombol4.addActionListener(numListener);
        buttonPanel.add(tombol5);
        tombol5.addActionListener(numListener);
        buttonPanel.add(tombol6);
        tombol6.addActionListener(numListener);
        buttonPanel.add(tombol7);
        tombol7.addActionListener(numListener);
        buttonPanel.add(tombol8);
        tombol8.addActionListener(numListener);
        buttonPanel.add(tombol9);
        tombol9.addActionListener(numListener);
        buttonPanel.add(tombolHapusSemua);
        tombolHapusSemua.addActionListener(clearListener);
        buttonPanel.add(tombol0);
        tombol0.addActionListener(numListener);
        buttonPanel.add(tombolKoma);
        tombolKoma.addActionListener(numListener);

        ActionListener opListener = new OpListener();
        ActionListener spaceListener = new SpaceListener();

        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(6, 1));
        JButton plus = new JButton("+");

        // Samuel: better symbols
        JButton minus = new JButton("\u2212");
        JButton kali = new JButton("\u00D7");
        JButton bagi = new JButton("\u00F7");
        JButton samadengan = new JButton("=");
        JButton hapusangka = new JButton("Backspace");
        opPanel.add(plus);
        plus.addActionListener(opListener);
        opPanel.add(minus);
        minus.addActionListener(opListener);
        opPanel.add(kali);
        kali.addActionListener(opListener);
        opPanel.add(bagi);
        bagi.addActionListener(opListener);
        opPanel.add(samadengan);
        samadengan.addActionListener(opListener);
        opPanel.add(hapusangka);
        hapusangka.addActionListener(spaceListener);

        JPanel isi = new JPanel();
        isi.setLayout(new BorderLayout());
        isi.add(displayField, BorderLayout.NORTH);
        isi.add(buttonPanel, BorderLayout.CENTER);
        isi.add(opPanel, BorderLayout.EAST);
        
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(360, 86));
        isi.add(spacer, BorderLayout.SOUTH);
        
        setLayout(new GridLayout(1, 1));
        add(isi);

    }

    private void actionHapusSemua() {
        angkaPertama = true;
        displayField.setText("0");
        operator = "=";
        logik.totalSekarang("0");
    }

    class OpListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            angkaPertama = true;
            String displayText = displayField.getText();

            // Samuel: Error handler.
            try {
                if (operator.equals("=")) {
                    logik.totalSekarang(displayText);
                } else if (operator.equals("+")) {
                    logik.tambah(displayText);
                } else if (operator.equals("\u2212")) {
                    logik.kurang(displayText);
                } else if (operator.equals("\u00D7")) {
                    logik.kali(displayText);
                } else if (operator.equals("\u00F7")) {
                    logik.bagi(displayText);
                }
            } catch (Exception ex) {
                return;
            }
            displayField.setText("" + logik.keluarkanInput());
            operator = e.getActionCommand();

        }
    }

    class NumListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String numerik = e.getActionCommand();
            if (angkaPertama) {
                displayField.setText(numerik);
                numerik = simpan;
                angkaPertama = false;
            } else {

                displayField.setText(displayField.getText() + numerik);
            }
        }
    }

    class SpaceListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));

            if (displayField.getText().length() < 1) {
                displayField.setText("0");
            }
        }
    }

    class ClearListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            actionHapusSemua();
        }
    }

    public class OtakKalkulator {

        private double totalSekarang;

        public OtakKalkulator() {
            totalSekarang = 0.0;
        }

        public String keluarkanInput() {
            return "" + totalSekarang;
        }

        public void totalSekarang(String n) {
            totalSekarang = StringKeInterger(n);
        }

        public void tambah(String n) {
            totalSekarang += StringKeInterger(n);
        }

        public void kurang(String n) {
            totalSekarang -= StringKeInterger(n);
        }

        public void kali(String n) {
            totalSekarang *= StringKeInterger(n);
        }

        public void bagi(String n) {
            totalSekarang /= StringKeInterger(n);
        }

        private double StringKeInterger(String n) {
            return Double.parseDouble(n);
        }
    }
};
