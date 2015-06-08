/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */

package edu.phonesimulator.common;

import edu.phonesimulator.application.*;
import edu.phonesimulator.gui.ApplicationContainer;

import java.util.ArrayList;

/**
 * ApplicationManager
 * 
 * This is a singleton class.
 * @author Samuel I. Gunadi
 */
public class ApplicationManager
{
    private static ApplicationManager _mgr;
    
    private ApplicationContainer _parent;
    private ArrayList<Application> _runningApps;
    private Application _activeApp;
    private Application _homeScreen;
    
    private ApplicationManager(ApplicationContainer parent)
    {
        _parent = parent;
        _mgr = this;
        _runningApps = new ArrayList<>();
        _homeScreen = new HomeScreen();
        _activeApp = _homeScreen;
        _runningApps.add(_activeApp);
        _parent.add(_activeApp);
    }
    
    public static ApplicationManager getMgr()
    {
        return _mgr;
    }
    
    public static void createMgr(ApplicationContainer parent)
    {
        if (_mgr == null) _mgr = new ApplicationManager(parent);
    }
    
    public Application getActiveApp()
    {
        return _activeApp;
    }
    
    public Application open(String appId)
    {
        Application appRef = null;
        for (Application app : _runningApps)
        {
            if (app.getAppId().equals(appId))
            {
                appRef = app;
                break;
            }
        }
        if (appRef == null)
        {
            switch (appId)
            {
                case "Task Manager":
                    appRef = new TaskManager();
                    break;
                case "Phone":
                    appRef = new Phone();
                    break;
                case "Messaging":
                    appRef = new Messaging();
                    break;
                case "Contacts":
                    appRef = new Contacts();
                    break;
                case "Clock":
                    appRef = new Clock();
                    break;
                case "Memo":
                    appRef = new Memo();
                    break;
                case "Calculator":
                    appRef = new Calculator();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            _runningApps.add(appRef);
        }
        _parent.remove(_activeApp);
        _parent.add(appRef);
        _parent.validate();
        _parent.repaint();
        _activeApp = appRef;
        return appRef;
    }
    
    public void close(String appId)
    {
        Application appRef = null;
        if (appId == "")
            appRef = _activeApp;
        else
        {
            for (Application app : _runningApps)
            {
                if (app.getAppId().equals(appId))
                {
                    appRef = app;
                    break;
                }
            }
            if (appRef == null)
                throw new IllegalArgumentException();
        }
        _parent.remove(appRef);
        _parent.validate();
        _parent.repaint();
        _runningApps.remove(appRef);
    }
    
    public ArrayList<String> getRunningAppsInfo()
    {
        ArrayList<String> ret = new ArrayList<>();
        for (Application app : _runningApps)
            ret.add(app.getAppId());
        return ret;
    }
    
    public ApplicationContainer getContainer()
    {
        return _parent;
    };
};
