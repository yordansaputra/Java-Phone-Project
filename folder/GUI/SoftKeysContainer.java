/*
 * A
 */

package edu.phonesimulator.gui;

import edu.phonesimulator.common.ApplicationManager;
import edu.phonesimulator.common.KeyboardManager;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * SoftKeysContainer
 * 
 * @author Samuel I. Gunadi
 */
public class SoftKeysContainer
extends JPanel
implements MouseListener
{
    private JButton backBtn;
    private JButton homeBtn;
    private JButton menuBtn;
    
    public SoftKeysContainer()
    {
        setPreferredSize(new Dimension(360, 48));
        
        setLayout(new GridLayout(1, 3));
        backBtn = new JButton();
        homeBtn = new JButton();
        menuBtn = new JButton();

        backBtn.setText("Back");
        homeBtn.setText("Home");
        menuBtn.setText("Menu");
        
        backBtn.addMouseListener(this);
        homeBtn.addMouseListener(this);
        menuBtn.addMouseListener(this);
        
        add(backBtn);
        add(homeBtn);
        add(menuBtn);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getSource() == homeBtn)
            ApplicationManager.getMgr().open("Home Screen");
        else if (e.getSource() == backBtn)
        {
            // If keyboard is opened, then close the keyboard.
            if (KeyboardManager.getMgr().isKeyboardOpened())
            {
                KeyboardManager.getMgr().closeKeyboard(ApplicationManager.getMgr().getActiveApp());
            }
            // Else close the application.
            else if (ApplicationManager.getMgr().getActiveApp().getAppId() != "Home Screen")
            {
                if (!ApplicationManager.getMgr().getActiveApp().sendBackKeyInterrupt())
                {
                    try
                    {
                        ApplicationManager.getMgr().close("");
                    }
                    catch (IllegalArgumentException ex)
                    {
                    }
                    ApplicationManager.getMgr().open("Home Screen");
                }
            }
        }
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
