/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.common;

import edu.phonesimulator.gui.ApplicationContainer;
import edu.phonesimulator.gui.KeyboardContainer;
import edu.phonesimulator.gui.MainWindow;
import edu.phonesimulator.gui.SoftKeysContainer;

import java.awt.Dimension;

/**
 * KeyboardManager
 * 
 * This is a singleton class.
 * @author Samuel I. Gunadi
 */
public class KeyboardManager
{
    static private KeyboardManager _mgr = null;
    
    private MainWindow _mainWindow;
    private ApplicationContainer _applicationContainer;
    private KeyboardContainer _keyboardContainer;
    private SoftKeysContainer _softKeysContainer;
    
    boolean _isKeyboardOpened;
    
    public static void createMgr(
            MainWindow mainWindow,
            ApplicationContainer applicationContainer,
            KeyboardContainer keyboardContainer,
            SoftKeysContainer softKeysContainer)
    {
        if(_mgr == null) _mgr = new KeyboardManager(
            mainWindow,
            applicationContainer,
            keyboardContainer,
            softKeysContainer);
    }
    
    public static KeyboardManager getMgr()
    {
        return _mgr;
    }
    
    private KeyboardManager(
            MainWindow mainWindow,
            ApplicationContainer applicationContainer,
            KeyboardContainer keyboardContainer,
            SoftKeysContainer softKeysContainer)
    {
        _mainWindow = mainWindow;
        _applicationContainer = applicationContainer;
        _keyboardContainer = keyboardContainer;
        _softKeysContainer = softKeysContainer;
        _isKeyboardOpened = false;
    }
    
    public void openKeyboard(Application app, Object field)
    {
        _keyboardContainer.setTarget(field);
        _applicationContainer.setPreferredSize(new Dimension(360, 398));
        app.setDefaultPreferredSize();
        _mainWindow.getContentPane().remove(_softKeysContainer);
        _mainWindow.getContentPane().add(_keyboardContainer);
        _mainWindow.getContentPane().add(_softKeysContainer);
        _mainWindow.getContentPane().validate();
        _mainWindow.getContentPane().repaint();
        _mainWindow.pack();
        _isKeyboardOpened = true;
    }
    
    public void closeKeyboard(Application app)
    {
        _applicationContainer.setPreferredSize(new Dimension(360, 574));
        app.setDefaultPreferredSize();
        _mainWindow.getContentPane().remove(_keyboardContainer);
        _mainWindow.getContentPane().validate();
        _mainWindow.getContentPane().repaint();
        _isKeyboardOpened = false;
    }
    
    public boolean isKeyboardOpened()
    {
        return _isKeyboardOpened;
    }
    
};
