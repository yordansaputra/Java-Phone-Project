/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import edu.phonesimulator.common.KeyboardManager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Memo
 *
 * @author Samuel I. Gunadi
 */
public class Memo
extends Application
implements FocusListener, MouseListener, ActionListener
{
    private JTextArea _memoTextArea;
    private JScrollPane _memoScrollPane;
    private JTabbedPane _tabbedPane;
    private JPanel _tabPanel;
    private JTextField _tabTitleField;
    private JLabel _addTabLabel;
    private JButton _deleteBtn;
    private Component _addTabContentCom;
    private int _memoId;
    
    public Memo()
    {
        _id = "Memo";
        setLayout(new GridLayout(1, 1));
        _tabbedPane = new JTabbedPane();
        
        _memoId = 0;
        
        _tabbedPane.addTab("New", _addTabContentCom);
        _addTabLabel = new JLabel("New Memo");
        _addTabLabel.setPreferredSize(new Dimension(72, 48));
        _addTabLabel.addMouseListener(this);
        _tabbedPane.setTabComponentAt(0, _addTabLabel);
        _tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        add(_tabbedPane);
    }
    
    public void addMemo()
    {
        _memoTextArea = new JTextArea();
        _memoTextArea.addFocusListener(this);
        _memoTextArea.setEditable(false);
        _memoScrollPane = new JScrollPane();
        _memoScrollPane.setViewportView(_memoTextArea);
        _tabPanel = new JPanel();
        _tabPanel.setOpaque(false);
        _tabTitleField = new JTextField("Memo " + (_memoId + 1));
        _tabTitleField.addFocusListener(this);
        _tabTitleField.setOpaque(false);
        _tabTitleField.setEditable(false);
        _deleteBtn = new JButton("<html><body style=\"font-size: 12px; font-weight: bold;\">\u00D7</body><html>");
        _deleteBtn.setName("" + _memoId);
        _deleteBtn.setMargin(new Insets(-2, -2, 0, 0));
        _deleteBtn.setPreferredSize(new Dimension(38, 38));
        _deleteBtn.addActionListener(this);
        _tabPanel.add(_tabTitleField);
        _tabPanel.add(_deleteBtn);
        _tabbedPane.addTab("" + _memoId, _memoScrollPane);
        _tabbedPane.setTabComponentAt(_tabbedPane.getTabCount() - 1, _tabPanel);
        _memoId++;
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        KeyboardManager.getMgr().openKeyboard(this, e.getSource());
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        KeyboardManager.getMgr().closeKeyboard(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int index = -1;
        for (int i = 0; i < _tabbedPane.getTabCount(); i++)
        {
            if (_tabbedPane.getTitleAt(i).equals(((JButton)(e.getSource())).getName()))
                index = i;
        }
        
        _tabbedPane.remove(index);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        addMemo();
        _tabbedPane.remove(_addTabContentCom);
        _tabbedPane.addTab("New", _addTabContentCom);
        _tabbedPane.setTabComponentAt(_tabbedPane.getTabCount() - 1, _addTabLabel);
        _tabbedPane.setSelectedIndex(_tabbedPane.getTabCount() - 2);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
};
