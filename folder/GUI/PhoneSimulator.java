/*
 * Copyright (c) Samuel
 */

package edu.phonesimulator.gui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * PhoneSimulator
 * 
 * @author Samuel I. Gunadi
 */
public class PhoneSimulator
{
    /**
     * Defines program main entry point.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                    {
                        if ("Nimbus".equals(info.getName()))
                        {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                }
                catch (ClassNotFoundException |
                        InstantiationException |
                        IllegalAccessException |
                        UnsupportedLookAndFeelException ex
                        )
                {
                    Logger.getLogger(PhoneSimulator.class.getName()).log(Level.SEVERE, null, ex);
                }
                new MainWindow();
            }
        });
    }
};
