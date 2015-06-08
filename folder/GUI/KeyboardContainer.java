/*
 * A
 */

package edu.phonesimulator.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * KeyboardContainer
 * 
 * @author Samuel I. Gunadi
 */
public class KeyboardContainer
extends JPanel
implements ActionListener
{
    public enum ShiftKeyState
    {
        NONE,
        SHIFT,
        CAPS_LK
    };
    
    private JButton[] _firstRowAlphaBtns;
    private JButton[] _secondRowAlphaBtns;
    private JButton[] _thirdRowAlphaBtns;
    private JButton[] _numSymBtns;
    private JButton _bkspBtn;
    private JButton _switchBtn;
    private JButton _spaceBtn;
    private JButton _returnBtn;
    private JToggleButton _shiftBtn;
    private JPanel _alphaPanel;
    private JPanel _numPanel;
    private boolean _numToggled;
    private Object _ref;
    private String _newLine;
    private ShiftKeyState _shiftKeyState;
    
    public KeyboardContainer()
    {
        setPreferredSize(new Dimension (360, 176));
        _newLine = System.getProperty("line.separator");
        _alphaPanel = new JPanel();
        _alphaPanel.setPreferredSize(new Dimension (360, 176));
        _alphaPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        _numPanel = new JPanel();
        _numPanel.setPreferredSize(new Dimension (360, 176));
        _numPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        _firstRowAlphaBtns = new JButton[10];
        _secondRowAlphaBtns = new JButton[9];
        _thirdRowAlphaBtns = new JButton[7];
        
        for (int i = 0; i < 10; i++)
        {
            _firstRowAlphaBtns[i] = new JButton();
            _firstRowAlphaBtns[i].setPreferredSize(new Dimension(35, 41));
            _firstRowAlphaBtns[i].setMargin(new Insets(0, -4, 0, 0));
            _firstRowAlphaBtns[i].addActionListener(this);
            _alphaPanel.add(_firstRowAlphaBtns[i]);
        }
        
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(16, 41));
        _alphaPanel.add(spacer);
        
        for (int i = 0; i < 9; i++)
        {
            _secondRowAlphaBtns[i] = new JButton();
            _secondRowAlphaBtns[i].setPreferredSize(new Dimension(35, 41));
            _secondRowAlphaBtns[i].setMargin(new Insets(0, -4, 0, 0));
            _secondRowAlphaBtns[i].addActionListener(this);
            _alphaPanel.add(_secondRowAlphaBtns[i]);
        }
        
        _shiftBtn = new JToggleButton("<html>Shift</html>");
        _shiftBtn.setPreferredSize(new Dimension(41, 41));
        _shiftBtn.setMargin(new Insets(0, -7, 0, 0));
        _shiftBtn.addActionListener(this);
        _alphaPanel.add(_shiftBtn);
        
        for (int i = 0; i < 7; i++)
        {
            _thirdRowAlphaBtns[i] = new JButton();
            _thirdRowAlphaBtns[i].setPreferredSize(new Dimension(35, 41));
            _thirdRowAlphaBtns[i].setMargin(new Insets(0, -4, 0, 0));
            _thirdRowAlphaBtns[i].addActionListener(this);
            _alphaPanel.add(_thirdRowAlphaBtns[i]);
        }
        
        _bkspBtn = new JButton("<html>Bksp</html>");
        _bkspBtn.setPreferredSize(new Dimension(52, 41));
        _bkspBtn.setMargin(new Insets(0, -8, 0, 0));
        _bkspBtn.addActionListener(this);
        _alphaPanel.add(_bkspBtn);
        
        _switchBtn = new JButton("<html>123</html>");
        _switchBtn.setPreferredSize(new Dimension(41, 41));
        _switchBtn.setMargin(new Insets(0, -6, 0, 0));
        _switchBtn.addActionListener(this);
        _alphaPanel.add(_switchBtn);
        
        _spaceBtn = new JButton(" ");
        _spaceBtn.setPreferredSize(new Dimension(255, 41));
        _spaceBtn.addActionListener(this);
        _alphaPanel.add(_spaceBtn);
        
        _returnBtn = new JButton("<html>Ret</html>");
        _returnBtn.setMargin(new Insets(0, -6, 0, 0));
        _returnBtn.setPreferredSize(new Dimension(41, 41));
        _returnBtn.addActionListener(this);
        _alphaPanel.add(_returnBtn);
        
        _firstRowAlphaBtns[0].setText("q");
        _firstRowAlphaBtns[1].setText("w");
        _firstRowAlphaBtns[2].setText("e");
        _firstRowAlphaBtns[3].setText("r");
        _firstRowAlphaBtns[4].setText("t");
        _firstRowAlphaBtns[5].setText("y");
        _firstRowAlphaBtns[6].setText("u");
        _firstRowAlphaBtns[7].setText("i");
        _firstRowAlphaBtns[8].setText("o");
        _firstRowAlphaBtns[9].setText("p");
        
        _secondRowAlphaBtns[0].setText("a");
        _secondRowAlphaBtns[1].setText("s");
        _secondRowAlphaBtns[2].setText("d");
        _secondRowAlphaBtns[3].setText("f");
        _secondRowAlphaBtns[4].setText("g");
        _secondRowAlphaBtns[5].setText("h");
        _secondRowAlphaBtns[6].setText("j");
        _secondRowAlphaBtns[7].setText("k");
        _secondRowAlphaBtns[8].setText("l");
        
        _thirdRowAlphaBtns[0].setText("z");
        _thirdRowAlphaBtns[1].setText("x");
        _thirdRowAlphaBtns[2].setText("c");
        _thirdRowAlphaBtns[3].setText("v");
        _thirdRowAlphaBtns[4].setText("b");
        _thirdRowAlphaBtns[5].setText("n");
        _thirdRowAlphaBtns[6].setText("m");
        add(_alphaPanel);
        
        _numToggled = false;
        _numSymBtns = new JButton[30];
        for(int i = 0; i < 30; i++)
        {
            _numSymBtns[i] = new JButton();
            _numSymBtns[i].setPreferredSize(new Dimension(35, 41));
            _numSymBtns[i].setMargin(new Insets(0, -5, 0, 0));
            _numSymBtns[i].addActionListener(this);
            _numPanel.add(_numSymBtns[i]);
        }
        for(int i = 0; i < 10; i++)
        {
            _numSymBtns[i].setText(String.valueOf( (i + 1) % 10) );
        }
        _numSymBtns[10].setText("@");
        _numSymBtns[11].setText("#");
        _numSymBtns[12].setText("$");
        _numSymBtns[13].setText("%");
        _numSymBtns[14].setText("&");
        _numSymBtns[15].setText("-");
        _numSymBtns[16].setText("+");
        _numSymBtns[17].setText("(");
        _numSymBtns[18].setText(")");
        _numSymBtns[19].setText("*");
        _numSymBtns[20].setText("\"");
        _numSymBtns[21].setText("'");
        _numSymBtns[22].setText(":");
        _numSymBtns[23].setText(";");
        _numSymBtns[24].setText("!");
        _numSymBtns[25].setText("?");
        _numSymBtns[26].setText(".");
        _numSymBtns[27].setText(",");
        _numSymBtns[28].setText("_");
        _numSymBtns[29].setText("/");
        
        _shiftKeyState = ShiftKeyState.NONE;
    }
    
    public void setTarget(Object field)
    {
        _ref = field;
    }
    
    public ShiftKeyState getShiftKeyState()
    {
        return _shiftKeyState;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == _shiftBtn)
        {
            if (getShiftKeyState() == ShiftKeyState.NONE)
            {
                _firstRowAlphaBtns[0].setText("Q");
                _firstRowAlphaBtns[1].setText("W");
                _firstRowAlphaBtns[2].setText("E");
                _firstRowAlphaBtns[3].setText("R");
                _firstRowAlphaBtns[4].setText("T");
                _firstRowAlphaBtns[5].setText("Y");
                _firstRowAlphaBtns[6].setText("U");
                _firstRowAlphaBtns[7].setText("I");
                _firstRowAlphaBtns[8].setText("O");
                _firstRowAlphaBtns[9].setText("P");

                _secondRowAlphaBtns[0].setText("A");
                _secondRowAlphaBtns[1].setText("S");
                _secondRowAlphaBtns[2].setText("D");
                _secondRowAlphaBtns[3].setText("F");
                _secondRowAlphaBtns[4].setText("G");
                _secondRowAlphaBtns[5].setText("H");
                _secondRowAlphaBtns[6].setText("J");
                _secondRowAlphaBtns[7].setText("K");
                _secondRowAlphaBtns[8].setText("L");

                _thirdRowAlphaBtns[0].setText("Z");
                _thirdRowAlphaBtns[1].setText("X");
                _thirdRowAlphaBtns[2].setText("C");
                _thirdRowAlphaBtns[3].setText("V");
                _thirdRowAlphaBtns[4].setText("B");
                _thirdRowAlphaBtns[5].setText("N");
                _thirdRowAlphaBtns[6].setText("M");
                
                _shiftBtn.setSelected(false);
                
                _shiftKeyState = ShiftKeyState.SHIFT;
            }
            else if (_shiftKeyState == ShiftKeyState.SHIFT)
            {                
                _shiftKeyState = ShiftKeyState.CAPS_LK;
            }
            else if (_shiftKeyState == ShiftKeyState.CAPS_LK)
            {
                _firstRowAlphaBtns[0].setText("q");
                _firstRowAlphaBtns[1].setText("w");
                _firstRowAlphaBtns[2].setText("e");
                _firstRowAlphaBtns[3].setText("r");
                _firstRowAlphaBtns[4].setText("t");
                _firstRowAlphaBtns[5].setText("y");
                _firstRowAlphaBtns[6].setText("u");
                _firstRowAlphaBtns[7].setText("i");
                _firstRowAlphaBtns[8].setText("o");
                _firstRowAlphaBtns[9].setText("p");

                _secondRowAlphaBtns[0].setText("a");
                _secondRowAlphaBtns[1].setText("s");
                _secondRowAlphaBtns[2].setText("d");
                _secondRowAlphaBtns[3].setText("f");
                _secondRowAlphaBtns[4].setText("g");
                _secondRowAlphaBtns[5].setText("h");
                _secondRowAlphaBtns[6].setText("j");
                _secondRowAlphaBtns[7].setText("k");
                _secondRowAlphaBtns[8].setText("l");

                _thirdRowAlphaBtns[0].setText("z");
                _thirdRowAlphaBtns[1].setText("x");
                _thirdRowAlphaBtns[2].setText("c");
                _thirdRowAlphaBtns[3].setText("v");
                _thirdRowAlphaBtns[4].setText("b");
                _thirdRowAlphaBtns[5].setText("n");
                _thirdRowAlphaBtns[6].setText("m");
                
                _shiftKeyState = ShiftKeyState.NONE;
            }
        }
        else if (e.getSource() == _switchBtn)
        {
            if (_numToggled)
            {
                _numToggled = false;
                _switchBtn.setText("<html>123</html>");
                _spaceBtn.setPreferredSize(new Dimension(255, 41));
                _alphaPanel.add(_bkspBtn);
                _alphaPanel.add(_switchBtn);
                _alphaPanel.add(_spaceBtn);
                _alphaPanel.add(_returnBtn);
                add(_alphaPanel);
                remove(_numPanel);
                validate();
                repaint();
            }
            else
            {
                _numToggled = true;
                _switchBtn.setText("<html>ABC</html>");
                _spaceBtn.setPreferredSize(new Dimension(215, 41));
                _numPanel.add(_switchBtn);
                _numPanel.add(_spaceBtn);
                _numPanel.add(_bkspBtn);
                _numPanel.add(_returnBtn);
                add(_numPanel);
                remove(_alphaPanel);
                validate();
                repaint();
            }
        }
        else if (e.getSource() == _bkspBtn)
        {
            if (_ref instanceof JTextField)
            {
                if(((JTextField)_ref).getText().length() != 0)
                    ((JTextField) _ref).setText(((JTextField)_ref).getText().substring(0, ((JTextField)_ref).getText().length() - 1));
            }
            else if (_ref instanceof JTextArea)
            {
                if(((JTextArea)_ref).getText().length() != 0)
                    ((JTextArea) _ref).setText(((JTextArea)_ref).getText().substring(0, ((JTextArea)_ref).getText().length() - 1));
            }
        }
        else if (e.getSource() == _returnBtn)
        {
            if (_ref instanceof JTextArea)
            {
                ((JTextArea)_ref).setText(((JTextArea)_ref).getText() + _newLine);
            }
        }
        else
        {
            if (_ref instanceof JTextField)
            {
                ((JTextField) _ref).setText(((JTextField)_ref).getText() + ((JButton) e.getSource()).getText());
            }
            else if (_ref instanceof JTextArea)
            {
                ((JTextArea) _ref).setText(((JTextArea)_ref).getText() + ((JButton) e.getSource()).getText());
            }
            if (_shiftKeyState == ShiftKeyState.SHIFT)
            {
                if(Character.isLetter(((JButton) e.getSource()).getText().charAt(0)))
                {
                    _firstRowAlphaBtns[0].setText("q");
                    _firstRowAlphaBtns[1].setText("w");
                    _firstRowAlphaBtns[2].setText("e");
                    _firstRowAlphaBtns[3].setText("r");
                    _firstRowAlphaBtns[4].setText("t");
                    _firstRowAlphaBtns[5].setText("y");
                    _firstRowAlphaBtns[6].setText("u");
                    _firstRowAlphaBtns[7].setText("i");
                    _firstRowAlphaBtns[8].setText("o");
                    _firstRowAlphaBtns[9].setText("p");

                    _secondRowAlphaBtns[0].setText("a");
                    _secondRowAlphaBtns[1].setText("s");
                    _secondRowAlphaBtns[2].setText("d");
                    _secondRowAlphaBtns[3].setText("f");
                    _secondRowAlphaBtns[4].setText("g");
                    _secondRowAlphaBtns[5].setText("h");
                    _secondRowAlphaBtns[6].setText("j");
                    _secondRowAlphaBtns[7].setText("k");
                    _secondRowAlphaBtns[8].setText("l");

                    _thirdRowAlphaBtns[0].setText("z");
                    _thirdRowAlphaBtns[1].setText("x");
                    _thirdRowAlphaBtns[2].setText("c");
                    _thirdRowAlphaBtns[3].setText("v");
                    _thirdRowAlphaBtns[4].setText("b");
                    _thirdRowAlphaBtns[5].setText("n");
                    _thirdRowAlphaBtns[6].setText("m");

                    _shiftKeyState = ShiftKeyState.NONE;
                }
            }
        }
    }
    
};
