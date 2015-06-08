/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */
//INPUT BARU BUAT TUGAS//

package edu.phonesimulator.common;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Application
 * 
 * @author Samuel I. Gunadi
 */
public abstract class Application
extends JPanel
{
    protected String _id;
    
    public Application()
    {
        setDefaultPreferredSize();
    }
    
    public String getAppId()
    {
        return _id;
    }
    
    public void setDefaultPreferredSize()
    {
        setPreferredSize(new Dimension(ApplicationManager.getMgr().getContainer().getPreferredSize()));
    }
    
    public boolean sendBackKeyInterrupt()
    {
        return false;
    }
};
