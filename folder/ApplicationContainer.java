/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.gui;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * ApplicationContainer
 * 
 * @author Samuel I. Gunadi
 */
public class ApplicationContainer
extends JPanel
{
    MainWindow _parent;
    
    public ApplicationContainer(MainWindow parent)
    {
        _parent = parent;
        setPreferredSize(new Dimension(360, 574));
        setVisible(true);
    }
};
