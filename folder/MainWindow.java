/*
 * A
 */

package edu.phonesimulator.gui;

import edu.phonesimulator.common.ApplicationManager;
import edu.phonesimulator.common.KeyboardManager;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * MainWindow
 * 
 * @author Samuel I. Gunadi
 */
public class MainWindow
extends JFrame
{
    private StatusBarContainer _statusBarContainer;
    private ApplicationContainer _applicationContainer;
    private KeyboardContainer _keyboardContainer;
    private SoftKeysContainer _softKeysContainer;
    
    public MainWindow()
    {
        _statusBarContainer = new StatusBarContainer();
        _applicationContainer = new ApplicationContainer(this);
        _keyboardContainer = new KeyboardContainer();
       _softKeysContainer = new SoftKeysContainer();
        ApplicationManager.createMgr(_applicationContainer);
        KeyboardManager.createMgr(this,
                _applicationContainer,
                _keyboardContainer,
                _softKeysContainer);
        setResizable(false);
        setTitle("Phone Simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setPreferredSize(new Dimension(360, 640));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(_statusBarContainer);
        contentPane.add(_applicationContainer);
        contentPane.add(_softKeysContainer);
        pack();
        setVisible(true);
    }
    
};
