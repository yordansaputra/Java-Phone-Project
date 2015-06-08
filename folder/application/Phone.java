/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Phone
 *
 * @author Samuel I. Gunadi
 */
public class Phone
extends Application
implements ActionListener
{
    private Timer _timer;
    private JPanel _dialPadPanel;
    private JPanel _dialingPanel;
    /*
    _dialButtons
    A two-dimensional JButton array.
    [0][0] 1
    [0][1] 2
    [0][2] 3
    [1][0] 4
    [1][1] 5
    [1][2] 6
    [2][0] 7
    [2][1] 8
    [2][2] 9
    [3][0] *
    [3][1] 0
    [3][2] #
    [4][0] Message
    [4][1] Call
    [4][2] Backspace
    */
    private JButton[][] _dialButtons; 
    private JScrollPane _scrollPane;
    private JTextArea _numberField;
    
    public Phone()
    {
        _id = "Phone";
        _dialPadPanel = new JPanel();
        _dialingPanel = new JPanel();
        
        _dialPadPanel.setPreferredSize(new Dimension(360, 572));
        _dialingPanel.setPreferredSize(new Dimension(360, 572));

        
        _dialButtons = new JButton[5][3];
        
        _scrollPane = new JScrollPane();
        _numberField = new JTextArea();
        _numberField.setMargin(new Insets(8, 2, 2, 2));
        _numberField.setEditable(false);
        _numberField.setFont(new Font("Tahoma", 0, 48));
        _numberField.setRows(1);
        _numberField.setWrapStyleWord(true);
        
        _scrollPane.setViewportView(_numberField);
        _scrollPane.setPreferredSize(new Dimension(350, 90));
        _dialPadPanel.add(_scrollPane);
        
        for(byte i = 0; i < 5; i++)
        {
            for (byte j = 0; j < 3; j++)
            {
                _dialButtons[i][j] = new JButton();
                _dialButtons[i][j].setPreferredSize(new Dimension(108, 82));
                _dialButtons[i][j].addActionListener(this);
                _dialPadPanel.add(_dialButtons[i][j]);
            }
        }
    
        _dialButtons[0][0].setText("1");
        _dialButtons[0][1].setText("2");
        _dialButtons[0][2].setText("3");
        _dialButtons[1][0].setText("4");
        _dialButtons[1][1].setText("5");
        _dialButtons[1][2].setText("6");
        _dialButtons[2][0].setText("7");
        _dialButtons[2][1].setText("8");
        _dialButtons[2][2].setText("9");
        _dialButtons[3][0].setText("*");
        _dialButtons[3][1].setText("0");
        _dialButtons[3][2].setText("#");
        _dialButtons[4][0].setText("Message");
        _dialButtons[4][1].setText("Call");
        _dialButtons[4][2].setText("Backspace");
        
        add(_dialPadPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == _dialButtons[0][0])
            _numberField.setText(_numberField.getText() + "1");
        else if (e.getSource() == _dialButtons[0][1])
            _numberField.setText(_numberField.getText() + "2");
        else if (e.getSource() == _dialButtons[0][2])
            _numberField.setText(_numberField.getText() + "3");
        else if (e.getSource() == _dialButtons[1][0])
            _numberField.setText(_numberField.getText() + "4");
        else if (e.getSource() == _dialButtons[1][1])
            _numberField.setText(_numberField.getText() + "5");
        else if (e.getSource() == _dialButtons[1][2])
            _numberField.setText(_numberField.getText() + "6");
        else if (e.getSource() == _dialButtons[2][0])
            _numberField.setText(_numberField.getText() + "7");
        else if (e.getSource() == _dialButtons[2][1])
            _numberField.setText(_numberField.getText() + "8");
        else if (e.getSource() == _dialButtons[2][2])
            _numberField.setText(_numberField.getText() + "9");
        else if (e.getSource() == _dialButtons[3][0])
            _numberField.setText(_numberField.getText() + "*");
        else if (e.getSource() == _dialButtons[3][1])
            _numberField.setText(_numberField.getText() + "0");
        else if (e.getSource() == _dialButtons[3][2])
            _numberField.setText(_numberField.getText() + "#");
        else if (e.getSource() == _dialButtons[4][2])
        {
            if(_numberField.getText().length() != 0)
                _numberField.setText(_numberField.getText().substring(0, _numberField.getText().length() - 1));
        }
        else if (e.getSource() == _dialButtons[4][1])
        {
            if (_numberField.getText().length() == 0) return;
            call(_numberField.getText());
        }
        else if (e.getSource() == _timer)
        {
            _timer.stop();
            _dialingPanel.removeAll();
            remove(_dialingPanel);
            add(_dialPadPanel);
            revalidate();
            repaint();
        }
    }
    
    public void call(String number)
    {
            _timer = new Timer(5000, this);
            ArrayList<String> tokens = new ArrayList<>();
            for (int start = 0; start < number.length(); start += 12)
                tokens.add(number.substring(start, Math.min(number.length(), start + 12)));
            String wrappedNumber = new String();
            for (String str : tokens)
                wrappedNumber += "<br/>" + str;
            _dialingPanel.removeAll();
            _dialingPanel.add(
                new JLabel
                (
                    "<html><body style=\"font-size: 48pt;\">Dialing " + wrappedNumber + "<br/>....</body></html>"
                )
            );
            remove(_dialPadPanel);
            add(_dialingPanel);
            revalidate();
            repaint();
            _timer.start();
    }
};
