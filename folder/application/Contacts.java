/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import edu.phonesimulator.common.ApplicationManager;
import edu.phonesimulator.common.KeyboardManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Contacts
 * 
 * @author Samuel I. Gunadi
 */

public class Contacts
extends Application
implements ActionListener, FocusListener
{
    class ContactItem
    {
        public String _name;
        public String _number;
        public String _addresses;
        
        public ContactItem(String name, String number, String addresses)
        {
            _name = name;
            _number = number;
            _addresses = addresses;
        }
        
        public void update(String name, String number, String addresses)
        {
            _name = name;
            _number = number;
            _addresses = addresses;
        }
    }
    
    private ArrayList<ContactItem> _contactEntries;
    private JButton _addContactBtn;
    private JPanel _contactItemPanel;
    private JScrollPane _contactScrollPane;
    private JPanel _editPane;
    private JTextField _nameField;
    private JTextField _numField;
    private JTextArea _addrField;
    private JScrollPane _scrollPane;
    private JPanel _moreBtnPane;
    private JButton _callBtn;
    private JButton _messageBtn;
    private JButton _deleteBtn;
    private JButton _saveBtn;
    private JButton _cancelBtn;
    private int _editMode;
    private ContactItem _onEdit;
    
    public Contacts()
    {
        _id = "Contacts";
        
        _contactEntries = new ArrayList<>();
        
        _editMode = 0;
        
        _addContactBtn = new JButton("Add Contact");
        _addContactBtn.setPreferredSize(new Dimension(300, 36));
        _addContactBtn.addActionListener(this);
        add(_addContactBtn);
        _contactItemPanel = new JPanel();
        _contactItemPanel.setLayout(new BoxLayout(_contactItemPanel, BoxLayout.Y_AXIS));
        _contactScrollPane = new JScrollPane();
        _contactScrollPane.setViewportView(_contactItemPanel);
        _contactScrollPane.setPreferredSize(new Dimension(360, 520));
        _contactScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(_contactScrollPane);
        
        _editPane = new JPanel();
        _editPane.setLayout(new BoxLayout(_editPane, BoxLayout.Y_AXIS));
        
        
        _nameField = new JTextField();
        _nameField.setPreferredSize(new Dimension(256, 20));
        _nameField.setEditable(false);
        _nameField.addFocusListener(this);
        JPanel listPanel = new JPanel(new BorderLayout(0, 6));
        listPanel.setPreferredSize(new Dimension(360, 36));
        listPanel.add(new JLabel("Name"), BorderLayout.WEST);
        listPanel.add(_nameField, BorderLayout.EAST);
        _editPane.add(listPanel);
        
        _numField = new JTextField();
        _numField.setPreferredSize(new Dimension(256, 20));
        _numField.setEditable(false);
        _numField.addFocusListener(this);
        listPanel = new JPanel(new BorderLayout(0, 6));
        listPanel.setPreferredSize(new Dimension(360, 36));
        listPanel.add(new JLabel("Number"), BorderLayout.WEST);
        listPanel.add(_numField, BorderLayout.EAST);
        _editPane.add(listPanel);
        
        _addrField = new JTextArea();
        _addrField.setEditable(false);
        _addrField.addFocusListener(this);
        
        _scrollPane = new JScrollPane();
        _scrollPane.setPreferredSize(new Dimension(256, 64));
        _scrollPane.setViewportView(_addrField);
        
        listPanel = new JPanel(new BorderLayout(0, 6));
        listPanel.setPreferredSize(new Dimension(360, 64));
        listPanel.add(new JLabel("Address"), BorderLayout.WEST);
        listPanel.add(_scrollPane, BorderLayout.EAST);
        _editPane.add(listPanel);
        
        _moreBtnPane = new JPanel(new GridLayout(1, 3));
        
        _callBtn = new JButton("Call");
        _callBtn.addActionListener(this);
        
        _messageBtn = new JButton("Message");
        _messageBtn.addActionListener(this);
        
        _deleteBtn = new JButton("Delete");
        _deleteBtn.addActionListener(this);
        
        _moreBtnPane.add(_callBtn);
        _moreBtnPane.add(_messageBtn);
        _moreBtnPane.add(_deleteBtn);
        
        _editPane.add(_moreBtnPane);
        listPanel = new JPanel(new GridLayout(1, 2));
        
        _saveBtn = new JButton("Save");
        _saveBtn.addActionListener(this);
        
        _cancelBtn = new JButton("Cancel");
        _cancelBtn.addActionListener(this);
        
        listPanel.add(_saveBtn);
        listPanel.add(_cancelBtn);
        
        _editPane.add(listPanel);
        
    }
    
    
    private void updateList()
    {
        _contactItemPanel.removeAll();
        for (final ContactItem item : _contactEntries)
        {
            JPanel listPanel = new JPanel(new BorderLayout(0, 6));
            listPanel.setMaximumSize(new Dimension(320, 36));
            listPanel.setPreferredSize(new Dimension(320, 36));
            listPanel.add(new JLabel(item._name), BorderLayout.WEST);
            JButton openBtn = new JButton("Open");
            openBtn.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        _moreBtnPane.setVisible(true);
                        _nameField.setText(item._name);
                        _numField.setText(item._number);
                        _addrField.setText(item._addresses);
                        remove(_addContactBtn);
                        remove(_contactScrollPane);
                        add(_editPane);
                        validate();
                        repaint();
                        _editMode = 2;
                        _onEdit = item;
                    }
                }
            );
            
            listPanel.add(openBtn, BorderLayout.EAST);
            _contactItemPanel.add(listPanel);
        }
        _contactItemPanel.revalidate();
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
        if (e.getSource() == _addContactBtn)
        {
            remove(_addContactBtn);
            remove(_contactScrollPane);
            add(_editPane);
            validate();
            repaint();
            _moreBtnPane.setVisible(false);
            _editMode = 1;
        }
        else if (e.getSource() == _saveBtn)
        {
            if (_nameField.getText().equals(""))
                return;
            if (_editMode == 1)
            {
                _contactEntries.add(new ContactItem(_nameField.getText(), _numField.getText(), _addrField.getText()));
            }
            if (_editMode == 2)
            {
                _onEdit.update(_nameField.getText(), _numField.getText(), _addrField.getText());
            }
            _nameField.setText("");
            _numField.setText("");
            _addrField.setText("");
            updateList();
            remove(_editPane);
            add(_addContactBtn);
            add(_contactScrollPane);
            validate();
            repaint();
            _editMode = 0;
        }
        else if (e.getSource() == _cancelBtn)
        {
            _nameField.setText("");
            _numField.setText("");
            _addrField.setText("");
            remove(_editPane);
            add(_addContactBtn);
            add(_contactScrollPane);
            validate();
            repaint();
            _editMode = 0;
        }
        else if (e.getSource() == _callBtn)
        {
            ((Phone) ApplicationManager.getMgr().open("Phone")).call(_onEdit._number);
        }
        else if (e.getSource() == _deleteBtn)
        {
            _contactEntries.remove(_onEdit);
            _nameField.setText("");
            _numField.setText("");
            _addrField.setText("");
            updateList();
            remove(_editPane);
            add(_addContactBtn);
            add(_contactScrollPane);
            validate();
            repaint();
            _editMode = 0;
        }
    }

    @Override
    public boolean sendBackKeyInterrupt()
    {
        if (_editMode > 0)
        {
            _nameField.setText("");
            _numField.setText("");
            _addrField.setText("");
            remove(_editPane);
            add(_addContactBtn);
            add(_contactScrollPane);
            validate();
            repaint();
            _editMode = 0;
            return true;
        }
        return false;
    }
    
};
