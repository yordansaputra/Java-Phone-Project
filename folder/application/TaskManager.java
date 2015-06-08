/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import edu.phonesimulator.common.ApplicationManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * TaskManager
 * 
 * @author Samuel I. Gunadi
 */
public class TaskManager
extends Application
implements ActionListener
{
    private Timer _updateTimer;
    private JButton _closeAllBtn;
    private JPanel _appItemPanel;
    private JScrollPane _appScrollPane;
    
    public TaskManager()
    {
        _id = "Task Manager";
        _closeAllBtn = new JButton("Close All");
        _closeAllBtn.setPreferredSize(new Dimension(300, 36));
        _closeAllBtn.addActionListener(this);
        add(_closeAllBtn);
        _appItemPanel = new JPanel();
        _appItemPanel.setLayout(new BoxLayout(_appItemPanel, BoxLayout.Y_AXIS));
        _appScrollPane = new JScrollPane();
        _appScrollPane.setViewportView(_appItemPanel);
        _appScrollPane.setPreferredSize(new Dimension(360, 520));
        _appScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(_appScrollPane);
        updateInfo();
        _updateTimer = new Timer(100, this);
        _updateTimer.start();
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == _updateTimer)
        {
            _appItemPanel.removeAll();
            updateInfo();
            _appItemPanel.validate();
            _appItemPanel.repaint();
            _updateTimer.restart();
        }
        else
        {
            closeAll();
        }
    }
    
    private void updateInfo()
    {
        ArrayList<String> appsInfo = ApplicationManager.getMgr().getRunningAppsInfo();
        for (final String str : appsInfo)
        {
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BorderLayout(0, 6));
            listPanel.setMaximumSize(new Dimension(320, 36));
            listPanel.setPreferredSize(new Dimension(320, 36));
            listPanel.add(new JLabel(str), BorderLayout.WEST);
            if (str != "Home Screen")
            {
                JButton closeBtn = new JButton("Close");
                closeBtn.addActionListener(
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            try
                            {
                                ApplicationManager.getMgr().close(str);
                            }
                            catch (IllegalArgumentException ex)
                            {
                            }
                        }
                    }
                );
                listPanel.add(closeBtn, BorderLayout.EAST);
            }
            _appItemPanel.add(listPanel);
        }
        _appItemPanel.revalidate();
    }
    
    private void closeAll()
    {
        ArrayList<String> appsInfo = ApplicationManager.getMgr().getRunningAppsInfo();
        for (final String str : appsInfo)
        {
            if (str != "Home Screen" && str != _id)
            {
                try
                {
                    ApplicationManager.getMgr().close(str);
                }
                catch (IllegalArgumentException ex)
                {
                }
            }
        }
    }
    
};
