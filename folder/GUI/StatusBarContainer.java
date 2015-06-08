/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phonesimulator.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * StatusBarContainer
 * 
 * @author Samuel I. Gunadi
 */
public class StatusBarContainer
extends JPanel
implements ActionListener
{
    private JLabel _carrierLabel;
    private JLabel _timeLabel;
    private JLabel _batteryLabel;
    private Timer _updateTimer;
    private DateFormat _dateFormat;
    
    StatusBarContainer()
    {
        setPreferredSize(new Dimension(360, 18));
        setLayout(new GridLayout(1, 3));
        _carrierLabel = new JLabel(" |||||  AT&T  LTE");
        _dateFormat = new SimpleDateFormat("h:mm a");
        Date date = new Date();
        _timeLabel = new JLabel(_dateFormat.format(date));
        _batteryLabel = new JLabel("100% ");
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(_carrierLabel);
        add(panel);
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(_timeLabel);
        add(panel);
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(_batteryLabel);
        add(panel);
        setVisible(true);
        _updateTimer = new Timer(1000, this);
        _updateTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Date date = new Date();
        _timeLabel.setText(_dateFormat.format(date));
        _updateTimer.restart();
    }
    
};
