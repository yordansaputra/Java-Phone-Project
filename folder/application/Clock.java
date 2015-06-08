/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */
package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import edu.phonesimulator.common.KeyboardManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static java.lang.Math.abs;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Clock
 *
 * @author Roberto J. Kondurura, Samuel I. Gunadi
 */
public class Clock
        extends Application
        implements ActionListener, FocusListener {

    JTabbedPane _tabbedPane;

    // Timer Components
    JPanel _timerPanel;
    JLabel h, m, s, timerLabel;
    int counter;
    JTextField tfH, tfM, tfS;
    JButton _start, _cancel;
    Timer _timer1;

    // Clock Components
    private JPanel _clockPanel;
    private JLabel display;

    // Stopwatch Components
    private JPanel _stopwatchPanel;
    JLabel timeLabel, elapsedTime;
    JTextField tf_h, tf_m, tf_s, tf_ms;
    JButton start, get, cancel, reset;
    Timer timer;

    public Clock() {
        _id = "Clock";

        setLayout(new GridLayout(1, 1));
        _tabbedPane = new JTabbedPane();

        // Timer
        _timerPanel = new JPanel();
        _timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel spacer1 = new JPanel();
        spacer1.setPreferredSize(new Dimension(360, 180));
        _timerPanel.add(spacer1);

        tfH = new JTextField("0", 5);
        tfH.addFocusListener(this);
        tfH.setEditable(false);
        _timerPanel.add(tfH);

        m = new JLabel(":", SwingConstants.CENTER);
        _timerPanel.add(m);

        tfM = new JTextField("0", 5);
        tfM.addFocusListener(this);
        tfM.setEditable(false);
        _timerPanel.add(tfM);

        s = new JLabel(":", SwingConstants.CENTER);
        _timerPanel.add(s);

        tfS = new JTextField("0", 5);
        tfS.addFocusListener(this);
        tfS.setEditable(false);
        _timerPanel.add(tfS);

        _start = new JButton("Start");
        _timerPanel.add(_start);

        _cancel = new JButton("Stop");
        _timerPanel.add(_cancel);

        timerLabel = new JLabel("Ready", SwingConstants.CENTER);
        _timerPanel.add(timerLabel);

        _start.addActionListener(this);
        _cancel.addActionListener(this);
        _start.setEnabled(true);
        _cancel.setEnabled(false);

        // Clock
        _clockPanel = new JPanel();
        display = new JLabel();
        _clockPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR);
        int min = now.get(Calendar.MINUTE);
        int sec = now.get(Calendar.SECOND);
        int am_pm = now.get(Calendar.AM_PM);
        display.setText("<html><span style=\"font-size: 36px;\"> " + hour + " : " + min + " : " + sec + " ".concat(am_pm == 0 ? "AM" : "PM") + "</span></html>");
        Timer t = new Timer(1000,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR);
                        int min = now.get(Calendar.MINUTE);
                        int sec = now.get(Calendar.SECOND);
                        int am_pm = now.get(Calendar.AM_PM);
                        //display = new JLabel(hour + " : " + min + " : " + sec, SwingConstants.CENTER);
                        display.setText("<html><span style=\"font-size: 36px;\"> " + hour + " : " + min + " : " + sec + " ".concat(am_pm == 0 ? "AM" : "PM") + "</span></html>");

                    }
                });
        t.start();
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(360, 180));
        _clockPanel.add(spacer);
        _clockPanel.add(display);

        // Stopwatch
        _stopwatchPanel = new JPanel();
        _stopwatchPanel.setLayout(new GridLayout(7, 1, 5, 5));
        timeLabel = new JLabel("<html><span style=\"font-size: 18px\">" + 0 + " : " + 0 + " : " + 0 + " . " + 0 + "</span></html>", SwingConstants.CENTER);
        elapsedTime = new JLabel("<html><span style=\"font-size: 18px\">" + 0 + " : " + 0 + " : " + 0 + " . " + 0 + "</span></html>", SwingConstants.CENTER);
        _stopwatchPanel.add(timeLabel);

        start = new JButton("Start");
        _stopwatchPanel.add(start);
        start.setEnabled(true);

        get = new JButton("Split");
        _stopwatchPanel.add(get);
        get.setEnabled(false);

        cancel = new JButton("Stop");
        _stopwatchPanel.add(cancel);
        cancel.setEnabled(false);

        reset = new JButton("Reset");
        _stopwatchPanel.add(reset);
        reset.setEnabled(false);

        _stopwatchPanel.add(elapsedTime);

        StopwatchHandler e = new StopwatchHandler();
        start.addActionListener(e);
        get.addActionListener(e);
        cancel.addActionListener(e);
        reset.addActionListener(e);

        // Samuel: tabbed panels.
        _tabbedPane.addTab("Clock", _clockPanel);
        JLabel clockTabCom = new JLabel("<html><span style=\"font-size: 20px\">Clock</span></html>");
        _tabbedPane.setTabComponentAt(0, clockTabCom);

        _tabbedPane.addTab("Timer", _timerPanel);
        JLabel timerTabCom = new JLabel("<html><span style=\"font-size: 20px\">Timer</span></html>");
        _tabbedPane.setTabComponentAt(1, timerTabCom);

        _tabbedPane.addTab("Stopwatch", _stopwatchPanel);
        JLabel stopwatchTabCom = new JLabel("<html><span style=\"font-size: 20px\">Stopwatch</span></html>");
        _tabbedPane.setTabComponentAt(2, stopwatchTabCom);

        add(_tabbedPane);

    }

    public class StopwatchHandler implements ActionListener {

        TimeCounter tc;
        int sum = 1;
        long last_ms = 0;
        boolean pause = false;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start) {
                if (pause) {
                    tc = new TimeCounter(last_ms);
                } else {
                    tc = new TimeCounter(0);
                }
                timer = new Timer(1, tc);
                timer.setInitialDelay(0);
                timer.start();
                start.setEnabled(false);
                get.setEnabled(true);
                cancel.setEnabled(true);
                start.setText("Resume");
            }
            if (e.getSource() == get) {
                long msc = tc.getTime();
                // Samuel: use bigger font.
                // Samuel: second / 3600 + ":" + (second % 3600) / 60 + ":" + second % 60
                elapsedTime.setText("<html><span style=\"font-size: 18px\">" + msc / 3600000 + " : " + (msc % 3600000) / 60000 + " : " + msc / 1000 % 60 + " . " + msc % 1000 + "</span></html>");
                if (sum != 1) {
                    add(elapsedTime);
                }
            }
            if (e.getSource() == cancel) {
                timer.stop();
                pause = true;
                last_ms = tc.getTime();
                start.setEnabled(true);
                get.setEnabled(false);
                cancel.setEnabled(false);
                reset.setEnabled(true);

            }

            if (e.getSource() == reset) {
                timer.stop();
                pause = false;
                timeLabel.setText("<html><span style=\"font-size: 18px\">" + 0 + " : " + 0 + " : " + 0 + " . " + 0 + "</span></html>");
                elapsedTime.setText("<html><span style=\"font-size: 18px\">" + 0 + " : " + 0 + " : " + 0 + " . " + 0 + "</span></html>");
                start.setEnabled(true);
                start.setText("Start");
                get.setEnabled(false);
                cancel.setEnabled(false);
                reset.setEnabled(false);
            }

        }
    }

    public class TimeCounter implements ActionListener {

        // Samuel: more reliable timing.
        private long _lastMs;
        private long _ms;
        private long _lastNs;

        public TimeCounter(long ms) {
            _lastMs = ms;
            // Samuel: measure the difference of time rather than incrementing 1 ms per timer event.
            _lastNs = System.nanoTime();
        }

        public long getTime() {
            return _ms;
        }

        public void actionPerformed(ActionEvent tc) {
            // Samuel: get precise time from System.
            _ms = _lastMs + (long) ((System.nanoTime() - _lastNs) / 1e6);
            // Samuel: second / 3600 + ":" + (second % 3600) / 60 + ":" + second % 60
            timeLabel.setText("<html><span style=\"font-size: 18px\">" + _ms / 3600000 + " : " + (_ms % 3600000) / 60000 + " : " + _ms / 1000 % 60 + " . " + _ms % 1000 + "</span></html>");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _start) {
            int hr;
            int min;
            int sec;
            // Samuel: exception handler.
            try {
                hr = (int) (Double.parseDouble(tfH.getText()));
                min = (int) (Double.parseDouble(tfM.getText()));
                sec = (int) (Double.parseDouble(tfS.getText()));
            } catch (NumberFormatException ex) {
                return;
            }

            TimerHandler tc = new TimerHandler(hr, min, sec);

            if (tc.second <= 0) {
                return;
            }
            timerLabel.setText("Time left: " + hr + ":" + min + ":" + sec);
            _timer1 = new Timer(1000, tc);
            _timer1.start();
            _start.setEnabled(false);
            _cancel.setEnabled(true);
        }
        if (e.getSource() == _cancel) {
            _timer1.stop();
            timerLabel.setText("Ready");
            Toolkit.getDefaultToolkit().beep();
            _start.setEnabled(true);
            _cancel.setEnabled(false);
        }
    }

    public class TimerHandler implements ActionListener {

        public int second;

        public TimerHandler(int hour, int minute, int second) {
            this.second = (hour * 3600) + (minute * 60) + second;
        }

        public void actionPerformed(ActionEvent tc) {
            second--;
            if (second >= 1) {
                timerLabel.setText("Time left: " + second / 3600 + ":" + (second % 3600) / 60 + ":" + second % 60);
            } else {
                _timer1.stop();
                timerLabel.setText("Ready");
                Toolkit.getDefaultToolkit().beep();
                _start.setEnabled(true);
                _cancel.setEnabled(false);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        KeyboardManager.getMgr().openKeyboard(this, e.getSource());
    }

    @Override
    public void focusLost(FocusEvent e) {
        KeyboardManager.getMgr().closeKeyboard(this);
    }

};
