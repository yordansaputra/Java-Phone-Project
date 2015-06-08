/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import edu.phonesimulator.common.ApplicationManager;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * HomeScreen
 * 
 * Home Screen Application should never be closed.
 * @author Samuel I. Gunadi
 */
public class HomeScreen
extends Application
implements ActionListener
{
    private JButton _taskMgrBtn;
    private JButton _phoneBtn;
    private JButton _messagingBtn;
    private JButton _contactsBtn;
    private JButton _clockBtn;
    private JButton _memoBtn;
    private JButton _calculatorBtn;
    /**
     * Creates new form HomeScreen
     */
    public HomeScreen()
    {
        _id = "Home Screen";
        
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(360, 36));
        
        _taskMgrBtn = new JButton("<html>Task<br/>Manager</html>");
        _phoneBtn = new JButton("Phone");
        _messagingBtn = new JButton("Messaging");
        _contactsBtn = new JButton("Contacts");
        _clockBtn = new JButton("Clock");
        _memoBtn = new JButton("Memo");
        _calculatorBtn = new JButton("Calculator");

        _taskMgrBtn.addActionListener(this);
        _phoneBtn.addActionListener(this);
        _messagingBtn.addActionListener(this);
        _contactsBtn.addActionListener(this);
        _clockBtn.addActionListener(this);
        _memoBtn.addActionListener(this);
        _calculatorBtn.addActionListener(this);
        
        Dimension iconSize = new Dimension(112, 112);
        _taskMgrBtn.setPreferredSize(iconSize);
        _phoneBtn.setPreferredSize(iconSize);
        _messagingBtn.setPreferredSize(iconSize);
        _contactsBtn.setPreferredSize(iconSize);
        _clockBtn.setPreferredSize(iconSize);
        _memoBtn.setPreferredSize(iconSize);
        _calculatorBtn.setPreferredSize(iconSize);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(spacer);
        add(_taskMgrBtn);
        add(_phoneBtn);
        add(_messagingBtn);
        add(_contactsBtn);
        add(_clockBtn);
        add(_memoBtn);
        add(_calculatorBtn);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == _taskMgrBtn)
            ApplicationManager.getMgr().open("Task Manager");
        else if (e.getSource() == _phoneBtn)
            ApplicationManager.getMgr().open("Phone");
        else if (e.getSource() == _messagingBtn)
            ApplicationManager.getMgr().open("Messaging");
        else if (e.getSource() == _contactsBtn)
            ApplicationManager.getMgr().open("Contacts");
        else if (e.getSource() == _clockBtn)
            ApplicationManager.getMgr().open("Clock");
        else if (e.getSource() == _memoBtn)
            ApplicationManager.getMgr().open("Memo");
        else if (e.getSource() == _calculatorBtn)
            ApplicationManager.getMgr().open("Calculator");
    }
};
