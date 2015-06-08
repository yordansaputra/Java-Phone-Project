/*
 * Copyright (C) 2014, Samuel I. Gunadi.
 * All rights reserved.
 * 
 */
package edu.phonesimulator.application;

import edu.phonesimulator.common.Application;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Messaging
 *
 * @author Samuel I. Gunadi
 */
public class Messaging
        extends Application {

    JTabbedPane _tabbedPane;
    JPanel _composePanel;

    public Messaging() {
        _id = "Messaging";
        MessagingApp app = new MessagingApp();
        app.setVisible(true);
    }

    public class InsidePanelInbox extends JPanel //PANEL WHERE MBP IS
    {

        int lines;
        Inbox inbox;

        public InsidePanelInbox(int lines, Inbox inbox) {
            super();

            this.lines = lines;
            this.inbox = inbox;
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //CREATING THE MBP ARRAYS//////////////////////////////////////////////
            lines = (countLinesOfFile("Isi Pesan") - 2) / 3 + 1;
            MultiButtonPanelInbox[] mbp = new MultiButtonPanelInbox[lines];

            //ADDING THE MBP ARRAYS///////////////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            //c.gridy diatur di (for) statement di bawah
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 10;
            c.insets = new Insets(0, 0, 0, 0);

            for (int i = 0; i < lines; i++) //MAKING MBP
            {
                String nameTemp = ReadFile("Isi Pesan", 3 * i + 1);
                String numberTemp = "";
                int contactLines = countLinesOfFile("Nama dan Nomor");

                for (int j = 1; j < contactLines; j = j + 3) {
                    String nameFromFile = ReadFile("Nama dan Nomor", j);
                    if (nameFromFile.equals(nameTemp)) {
                        numberTemp = ReadFile("Nama dan Nomor", j + 1);
                        break;
                    }
                }
                mbp[i] = new MultiButtonPanelInbox(nameTemp, ReadFile("Isi Pesan", 3 * i + 2), numberTemp, inbox);
                c.gridy = i;
                add(mbp[i], c);
            }
            setLines(lines);
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public int getLines() {
            return lines;
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class InsidePanelSentMessage extends JPanel //PANEL WHERE MBP IS
    {

        int lines;
        SentMessage sentMessage;

        public InsidePanelSentMessage(int lines, SentMessage sentMessage) {
            super();

            this.lines = lines;
            this.sentMessage = sentMessage;
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //CREATING THE MBP ARRAYS//////////////////////////////////////////////
            lines = (countLinesOfFile("Sent Message") - 2) / 3 + 1;
            MultiButtonPanelSentMessage[] mbp = new MultiButtonPanelSentMessage[lines];

            //ADDING THE MBP ARRAYS///////////////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            //c.gridy diatur di (for) statement di bawah
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 10;
            c.insets = new Insets(0, 0, 0, 0);

            for (int i = 0; i < lines; i++) //MAKING MBP
            {
                String nameTemp = ReadFile("Sent Message", 3 * i + 1);
                String numberTemp = "";
                int contactLines = countLinesOfFile("Nama dan Nomor");

                for (int j = 1; j < contactLines; j = j + 3) {
                    String nameFromFile = ReadFile("Nama dan Nomor", j);
                    if (nameFromFile.equals(nameTemp)) {
                        numberTemp = ReadFile("Nama dan Nomor", j + 1);
                        break;
                    }
                }
                mbp[i] = new MultiButtonPanelSentMessage(nameTemp, ReadFile("Sent Message", 3 * i + 2), numberTemp, sentMessage);
                c.gridy = i;
                add(mbp[i], c);
            }
            setLines(lines);
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public int getLines() {
            return lines;
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class MessageWindowDraft extends JFrame {

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel buttonPanel = new JPanel(new BorderLayout(5, 5));
        ContactPanel contactPanel = new ContactPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("New Message", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton send = new JButton("send");
        JButton back = new JButton("back");
	//END OF JBUTTON////////////////////////////////////////////////////////

        //JTEXTAREA/////////////////////////////////////////////////////////
        JTextArea textArea = new JTextArea();
	//END OF JTEXTAREA//////////////////////////////////////////////////

        //JSCROLLPANE////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(textArea);
        //END OF JSCROLLPANE/////////////////////////////////////////////////

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        String name, message, number;
        MultiButtonPanelDraft mbp;

        public MessageWindowDraft(String name, String message, String number, MultiButtonPanelDraft mbp) {
            super();
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;
            this.mbp = mbp;

            setHeader(getName());
            setPanelNumber(getNumber());
            contactPanel.number.setEditable(false);
            contactPanel.search.setVisible(false);
            setTextArea(message);

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            send.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(send, BorderLayout.WEST);
            buttonPanel.add(back, BorderLayout.EAST);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //CONTACT PANEL////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 5;
            c.insets = new Insets(0, 5, 0, 5);
            add(contactPanel, c);

            //TEXT AREA////////////////////////////////
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(scrollPane, c);

            //SEND BUTTON////////////////////////////
            send.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to send this message?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) //YES
                    {
                        //deleteNtoMthLine(int n, int m, String filename);
                        JOptionPane.showMessageDialog(null, "Your message has been sent", "Messsage", JOptionPane.PLAIN_MESSAGE);

                        MessagingApp messaging = new MessagingApp();
                        messaging.setVisible(true);
                        dispose();
                    } else //dialogResult == 1 (NO)
                    {
                        //DO NOTHING
                    }
                }
            });

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String check = textArea.getText().trim();
                    if (!check.equals("")) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Your message hasn't been sent yet. Do you want to save it to draft?", "Save to draft?", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == 0) //YES
                        {
                            int lines = countLinesOfFile("Nama dan Nomor");

                            int match = 0; //FALSE
                            String number = contactPanel.getNumber(); //GETTING NUMBER FROM CONTACT PANEL
                            for (int i = 2; i < lines + 1; i = i + 3) {
                                String numberFromFile = ReadFile("Nama dan Nomor", i);
                                if (numberFromFile.equals(number)) {
                                    match = 1; //TRUE
                                    break;
                                }
                            }

                            if (number.equals("")) {
                                match = 2; //FOR EMPTY NUMBER
                            }

                            int stringLength = number.length();
                            for (int i = 0; i < stringLength; i++) {
                                char isItNumber = number.charAt(i);
                                if (Character.isLetter(isItNumber)) {
                                    match = 3;
                                    break;
                                }
                            }

                            if (match == 1) {
                                JOptionPane.showMessageDialog(null, "Your message has been saved to draft", "Messsage", JOptionPane.PLAIN_MESSAGE);
                                int lineToBeChanged = 0;
                                for (int i = 0; i < countLinesOfFile("Draft"); i++) {
                                    String nameFromFile = ReadFile("Draft", i + 1);
                                    String messageFromFile = ReadFile("Draft", i + 1);
                                    if (getName().equals(nameFromFile) && getMessage().equals(messageFromFile)) {
                                        lineToBeChanged = i + 2;
                                    }
                                }
                                changeNthLine(lineToBeChanged, "Draft", getTextArea());
                                Draft draft = new Draft();
                                draft.setVisible(true);
                                dispose();
                            } else if (match == 0) //FALSE
                            {
                                JOptionPane.showMessageDialog(null, "You don't have a contact with this number, please try with another number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            } else if (match == 2) //CONTACT PANEL IS EMPTY
                            {
                                JOptionPane.showMessageDialog(null, "You haven't put a contact number. Please input a contact number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            } else if (match == 3) //CONTACT PANEL CONTAINS LETTERS
                            {
                                JOptionPane.showMessageDialog(null, "Your contact number contains characters other than numbers, please try again", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            }
                        } else //DIALOGRESULT == 1 (NO)
                        {
                            Draft draft = new Draft();
                            draft.setVisible(true);
                            dispose();
                        }
                    }
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(5, 5, 5, 5);
            add(buttonPanel, c);
        }

        public void setHeader(String name) {
            header.setText("To: " + name);
        }

        public void setPanelNumber(String number) {
            contactPanel.setNumber(number);
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public String getNumber() {
            return number;
        }

        public String getTextArea() {
            return textArea.getText();
        }

        public MultiButtonPanelDraft getMbp() {
            return mbp;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setTextArea(String text) {
            textArea.setText(text);
        }

        public void changeNthLine(int n, String fileName, String newWord) //editing the draft file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            if (n != fileLines) {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + newWord + "\n";
                for (int i = n; i < fileLines - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, fileLines);
            } else //n ==fileLines
            {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + newWord;
                for (int i = n; i < fileLines; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
            }

            System.out.println(stringTemp);
            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class MessageWindowInbox extends JFrame //WHEN YOU CLICK THE BIG BUTTON IN INBOX
    {

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel buttonPanel = new JPanel(new BorderLayout(5, 5));
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton reply = new JButton("reply");
        JButton back = new JButton("back");
	//END OF JBUTTON////////////////////////////////////////////////////////

        //JTEXTAREA/////////////////////////////////////////////////////////
        JTextArea textArea = new JTextArea("Type your message here");
	//END OF JTEXTAREA//////////////////////////////////////////////////

        //JSCROLLPANE////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(textArea);
        //END OF JSCROLLPANE/////////////////////////////////////////////////

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        String name, message, number;

        public MessageWindowInbox(String name, String message, String number) {
            super("Message");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            header.setText(name);
            textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            textArea.setText(message);
            textArea.setEditable(false);
            reply.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(reply, BorderLayout.WEST);
            buttonPanel.add(back, BorderLayout.EAST);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //TEXT AREA////////////////////////////////
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(scrollPane, c);

            //REPLY BUTTON////////////////////////////
            reply.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ComposeMessage composeMessage = new ComposeMessage();
                    composeMessage.setHeader(getName());
                    composeMessage.setNumber(getNumber());
                    composeMessage.contactPanel.number.setEditable(false);
                    composeMessage.contactPanel.search.setVisible(false);
                    composeMessage.setVisible(true);
                    dispose();
                }
            });

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Inbox inbox = new Inbox();
                    inbox.setVisible(true);
                    dispose();
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(5, 5, 5, 5);
            add(buttonPanel, c);
        }
        //END OF MESSAGEWINDOWINBOX()

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public String getNumber() {
            return number;
        }

        public void deleteNtoMthLine(int n, int m, String fileName) //editing the sent message file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");

            int fileLines = countLinesOfFile(fileName);

            String stringTemp = new String();

            for (int i = 0; i < n - 1; i++) {
                stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
            }

            for (int i = m; i < fileLines - 1; i++) {
                stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
            }

            stringTemp = stringTemp + ReadFile(fileName, fileLines);

            System.out.println(stringTemp);
            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }

        public void clearTextFile(String fileName) {
            try {
                FileOutputStream eraser = new FileOutputStream(fileName + ".txt");
                String s = new String();
                byte[] data = s.getBytes();
                eraser.write(data);
                eraser.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }
    }

    public class MessageWindowSentMessage extends JFrame {

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel buttonPanel = new JPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton reply = new JButton("reply");
        JButton back = new JButton("back");
	//END OF JBUTTON////////////////////////////////////////////////////////

        //JTEXTAREA/////////////////////////////////////////////////////////
        JTextArea textArea = new JTextArea("Type your message here");
	//END OF JTEXTAREA//////////////////////////////////////////////////

        //JSCROLLPANE////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(textArea);
        //END OF JSCROLLPANE/////////////////////////////////////////////////

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        String name, message, number;

        public MessageWindowSentMessage(String name, String message, String number) {
            super("Message");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            header.setText("To: " + name);
            textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            textArea.setText(message);
            textArea.setEditable(false);
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(back);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //TEXT AREA////////////////////////////////
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(scrollPane, c);

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SentMessage sentMessage = new SentMessage();
                    sentMessage.setVisible(true);
                    dispose();
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(buttonPanel, c);
        }

        public void FileOut() //makes a file
        {
            int i = 0;
            String num = Integer.toString(i);
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\Draft" + num + ".txt");
            while (Files.exists(file)) {
                i++;
                num = Integer.toString(i);
                file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\Draft" + num + ".txt");
            }

            String s = textArea.getText();
            byte[] data = s.getBytes();
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                output.write(data);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }
    }

    public class MultiButtonPanelDraft extends JPanel {

        JButton bigButton = new JButton();
        JButton deleteButton = new JButton("X");

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();

        String name;
        String message;
        String number;
        Draft draft;

        public MultiButtonPanelDraft(String name, String message, String number, Draft draft) {
            super();
            setLayout(new GridBagLayout()); //untuk Panelnya

            label1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            label2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            deleteButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            bigButton.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;
            this.draft = draft;

            label1.setText(name);

            if (message.length() < 28) {
                label2.setText(message);
            } else {
                String subMessage = message.substring(0, 24);
                label2.setText(subMessage + "...");
            }

            //BIG BUTTON//////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = GridBagConstraints.REMAINDER;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(bigButton, c);

            //LABEL 1 (NAME)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.25;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label1, c);

            //LABEL 2 (MESSAGE)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.75;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label2, c);
            bigButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessageWindowDraft messageWindow = new MessageWindowDraft(getName(), getMessage(), getNumber(), getMbp());
                    messageWindow.setVisible(true);
                    getDraft().dispose();
                }
            });

            //DELETE BUTTON//////////////////////////////////
            c.weightx = 0;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(deleteButton, c);
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this message?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) //YES
                    {
                        getDraft().setLines(getDraft().getLines() - 1); //MINUS LINES WITH 1
                        for (int i = 0; i < countLinesOfFile("Draft"); i++) //////////////////
                        {
                            String nameFromFile = ReadFile("Draft", i + 1);
                            String messageFromFile = ReadFile("Draft", i + 2);
                            if (getName().equals(nameFromFile) && getMessage().equals(messageFromFile)) {
                                if (i + 1 == countLinesOfFile("Draft") - 1) {
                                    deleteNtoMthLine(i, i + 2, "Draft");
                                } else {
                                    deleteNtoMthLine(i + 1, i + 3, "Draft");
                                } //////////////////EDIT
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Your message has been deleted", "Messsage", JOptionPane.PLAIN_MESSAGE);
                        setVisible(false);

                        if (getDraft().getLines() == 6) {
                            getDraft().dispose();

                            Draft draft = new Draft();
                            draft.setVisible(true);
                        }
                    } else //dialogResult == 1 (NO)
                    {
                        //DO NOTHING
                    }
                    setVisible(false);
                }
            });
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public String getNumber() {
            return number;
        }

        public Draft getDraft() {
            return draft;
        }

        public MultiButtonPanelDraft getMbp() {
            return this;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void deleteNtoMthLine(int n, int m, String fileName) //editing the sent message file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");

            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            if (m != fileLines) {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }

                for (int i = m; i < fileLines - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, fileLines);
            } else //m == fileLines
            {
                for (int i = 0; i < n - 2; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, n - 1);
            }

            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class MultiButtonPanelInbox extends JPanel {

        JButton bigButton = new JButton();
        JButton replyButton = new JButton("R");
        JButton deleteButton = new JButton("X");

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();

        String name;
        String message;
        String number;
        Inbox inbox;

        public MultiButtonPanelInbox(String name, String message, String number, Inbox inbox) {
            super();
            setLayout(new GridBagLayout()); //untuk Panelnya

            label1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            label2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            replyButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            deleteButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            bigButton.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;
            this.inbox = inbox;

            label1.setText(name);

            if (message.length() < 28) {
                label2.setText(message);
            } else {
                String subMessage = message.substring(0, 24);
                label2.setText(subMessage + "...");
            }

            //BIG BUTTON//////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = GridBagConstraints.REMAINDER;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(bigButton, c);

            //LABEL 1 (NAME)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.25;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label1, c);

            //LABEL 2 (MESSAGE)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.75;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label2, c);
            bigButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessageWindowInbox messageWindow = new MessageWindowInbox(getName(), getMessage(), getNumber());
                    messageWindow.setVisible(true);
                    getInbox().dispose();
                }
            });

            //REPLY BUTTON//////////////////////////////////
            c.weightx = 0;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(replyButton, c);
            replyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ComposeMessage composeMessage = new ComposeMessage();
                    composeMessage.setHeader(getName());
                    composeMessage.setNumber(getNumber());
                    composeMessage.contactPanel.number.setEditable(false);
                    composeMessage.contactPanel.search.setVisible(false);
                    composeMessage.setVisible(true);
                    getInbox().dispose();
                }
            });

            //DELETE BUTTON//////////////////////////////////
            c.weightx = 0;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(deleteButton, c);
            String abc = message;
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this message?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) //YES
                    {
                        getInbox().setLines(getInbox().getLines() - 1); //MINUS LINES WITH 1
                        for (int i = 0; i < countLinesOfFile("Isi Pesan"); i++) {
                            String nameFromFile = ReadFile("Isi Pesan", i + 1);
                            String messageFromFile = ReadFile("Isi Pesan", i + 2);
                            if (getName().equals(nameFromFile) && getMessage().equals(messageFromFile)) {
                                if (i + 1 == countLinesOfFile("Isi Pesan") - 1) {
                                    deleteNtoMthLine(i, i + 2, "Isi Pesan");
                                } else {
                                    deleteNtoMthLine(i + 1, i + 3, "Isi Pesan");
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Your message has been deleted", "Messsage", JOptionPane.PLAIN_MESSAGE);
                        setVisible(false);

                        if (getInbox().getLines() == 6) {
                            getInbox().dispose();

                            Inbox inbox = new Inbox();
                            inbox.setVisible(true);
                        }
                    } else //dialogResult == 1 (NO)
                    {
                        //DO NOTHING
                    }
                    setVisible(false);
                }
            });
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public String getNumber() {
            return number;
        }

        public Inbox getInbox() {
            return inbox;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void deleteNtoMthLine(int n, int m, String fileName) //editing the sent message file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            if (m != fileLines) {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }

                for (int i = m; i < fileLines - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, fileLines);
            } else //m == fileLines
            {
                for (int i = 0; i < n - 2; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, n - 1);
            }

            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class MultiButtonPanelSentMessage extends JPanel {

        JButton bigButton = new JButton();
        JButton deleteButton = new JButton("X");

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();

        String name;
        String message;
        String number;
        SentMessage sentMessage;

        public MultiButtonPanelSentMessage(String name, String message, String number, SentMessage sentMessage) {
            super();
            setLayout(new GridBagLayout()); //untuk Panelnya

            label1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            label2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            deleteButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            bigButton.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            this.name = name;
            this.message = message;
            this.number = number;
            this.sentMessage = sentMessage;

            label1.setText(name);

            if (message.length() < 28) {
                label2.setText(message);
            } else {
                String subMessage = message.substring(0, 24);
                label2.setText(subMessage + "...");
            }

            //BIG BUTTON//////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = GridBagConstraints.REMAINDER;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(bigButton, c);

            //LABEL 1 (NAME)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.25;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label1, c);

            //LABEL 2 (MESSAGE)////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.75;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            bigButton.add(label2, c);
            bigButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessageWindowSentMessage messageWindow = new MessageWindowSentMessage(getName(), getMessage(), getNumber());
                    messageWindow.setVisible(true);
                    getSentMessage().dispose();
                }
            });

            //DELETE BUTTON//////////////////////////////////
            c.weightx = 0;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(deleteButton, c);
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this message?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) //YES
                    {
                        getSentMessage().setLines(getSentMessage().getLines() - 1); //MINUS LINES WITH 1
                        for (int i = 0; i < countLinesOfFile("Sent Message"); i++) {
                            String nameFromFile = ReadFile("Sent Message", i + 1);
                            String messageFromFile = ReadFile("Sent Message", i + 2);
                            if (getName().equals(nameFromFile) && getMessage().equals(messageFromFile)) {
                                if (i + 1 == countLinesOfFile("Sent Message") - 1) {
                                    deleteNtoMthLine(i, i + 2, "Sent Message");
                                } else {
                                    deleteNtoMthLine(i + 1, i + 3, "Sent Message");
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Your message has been deleted", "Messsage", JOptionPane.PLAIN_MESSAGE);
                        setVisible(false);

                        if (getSentMessage().getLines() == 6) {
                            getSentMessage().dispose();

                            SentMessage sentMessage = new SentMessage();
                            sentMessage.setVisible(true);
                        }
                    } else //dialogResult == 1 (NO)
                    {
                        //DO NOTHING
                    }
                    setVisible(false);
                }
            });
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public String getNumber() {
            return number;
        }

        public SentMessage getSentMessage() {
            return sentMessage;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void deleteNtoMthLine(int n, int m, String fileName) //editing the sent message file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");

            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            if (m != fileLines) {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }

                for (int i = m; i < fileLines - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, fileLines);
            } else //m == fileLines
            {
                for (int i = 0; i < n - 2; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, n - 1);
            }

            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class SentMessage extends JFrame {

        int lines; //this also determines how much mbp there are

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel mainPanel = new JPanel(new BorderLayout());
        InsidePanelSentMessage insidePanel = new InsidePanelSentMessage(lines, this);
        JPanel buttonPanel = new JPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("Sent Message", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton back = new JButton("back");
        //END OF JBUTTON////////////////////////////////////////////////////////

        JScrollPane scrollPane = new JScrollPane(insidePanel);

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        public SentMessage() {
            super("Sent Message");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(back);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //MAIN PANEL////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(mainPanel, c);

            lines = insidePanel.getLines();
            checkLines();

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessagingApp messaging = new MessagingApp();
                    messaging.setVisible(true);
                    dispose();
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(buttonPanel, c);
        }
        //END OF SENTMESSAGE()////////////////////////////

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int getLines() {
            return lines;
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public void checkLines() {
            if (lines <= 6) {
                mainPanel.add(insidePanel, BorderLayout.NORTH);
            } else //line>6
            {
                mainPanel.add(scrollPane);
            }
        }
    }

    public class MessagingApp extends JFrame {

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("Message Box", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
	//END OF JPANEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton composeMessage = new JButton("Compose Message"); //creating a composeMessage button
        JButton inbox = new JButton("Inbox"); //creating an inbox button
        JButton sentMessage = new JButton("Sent Message"); //creating a sentMessage button
        JButton draft = new JButton("Draft"); //creating a draft button
        //END OF JBUTTON////////////////////////////////////////////////////////

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        public MessagingApp() {
            super("Messaging");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            composeMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            inbox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            sentMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            draft.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0.6;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 30;
            mainPanel.add(header, c);

            //COMPOSE MESSAGE////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 100;
            mainPanel.add(composeMessage, c);
            composeMessage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ComposeMessage composeMessage = new ComposeMessage();
                    composeMessage.setVisible(true);
                    dispose(); //untuk menutup JFrame yang awal (messaging)
                }
            });

            //INBOX/////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 100;
            mainPanel.add(inbox, c);
            inbox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Inbox inbox = new Inbox();
                    inbox.setVisible(true);
                    dispose();
                }
            });

            //SENT MESSAGE/////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 100;
            mainPanel.add(sentMessage, c);
            sentMessage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SentMessage sentMessage = new SentMessage();
                    sentMessage.setVisible(true);
                    dispose();
                }
            });

            //DRAFT/////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 100;
            mainPanel.add(draft, c);
            draft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Draft draft = new Draft();
                    draft.setVisible(true);
                    dispose();
                }
            });

            add(mainPanel);
        }
    }

    public class ComposeMessage extends JFrame {

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel buttonPanel = new JPanel(new BorderLayout(5, 5));
        ContactPanel contactPanel = new ContactPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("New Message", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton send = new JButton("send");
        JButton back = new JButton("back");
	//END OF JBUTTON////////////////////////////////////////////////////////

        //JTEXTAREA/////////////////////////////////////////////////////////
        JTextArea textArea = new JTextArea("Type your message here...");
	//END OF JTEXTAREA//////////////////////////////////////////////////

        //JSCROLLPANE////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(textArea);
        //END OF JSCROLLPANE/////////////////////////////////////////////////

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        public ComposeMessage() {
            super("New Message");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            send.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(send, BorderLayout.WEST);
            buttonPanel.add(back, BorderLayout.EAST);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //CONTACT PANEL////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 5;
            c.insets = new Insets(0, 5, 0, 5);
            add(contactPanel, c);

            //TEXT AREA////////////////////////////////
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(scrollPane, c);

            //SEND BUTTON////////////////////////////
            send.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int lines = countLinesOfFile("Nama dan Nomor");

                    int match = 0; //FALSE
                    String number = contactPanel.getNumber(); //GETTING NUMBER FROM CONTACT PANEL
                    int gettingName = 0;
                    for (int i = 2; i < lines; i = i + 3) {
                        String numberFromFile = ReadFile("Nama dan Nomor", i);
                        if (numberFromFile.equals(number)) {
                            gettingName = i - 1;
                            match = 1; //TRUE
                            break;
                        }
                    }

                    if (number.equals("")) {
                        match = 2; //FOR EMPTY NUMBER
                    }

                    int stringLength = number.length();
                    for (int i = 0; i < stringLength; i++) {
                        char isItNumber = number.charAt(i);
                        if (Character.isLetter(isItNumber)) {
                            match = 3;
                            break;
                        }
                    }

                    if (match == 1) {
                        String check = textArea.getText().trim();
                        if (!check.equals("")) {
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to send this message?", "Message", JOptionPane.PLAIN_MESSAGE);
                            if (dialogResult == 0) //YES
                            {
                                String s = ReadFile("Nama dan Nomor", gettingName) + "\n" + textArea.getText();
                                AddingTextToFile("Sent Message", s); //SAVING TO DRAFT
                                JOptionPane.showMessageDialog(null, "Your message has been sent", "Messsage", JOptionPane.PLAIN_MESSAGE);
                                if (header.getText().startsWith("To: ")) //IF FROM INBOX
                                {
                                    Inbox inbox = new Inbox();
                                    inbox.setVisible(true);
                                    dispose();
                                } else {
                                    MessagingApp messaging = new MessagingApp();
                                    messaging.setVisible(true);
                                    dispose();
                                }
                            } else //DIALOGRESULT == 1 (NO)
                            {
                                //DO NOTHING
                            }
                        } else //TEXTAREA IS EMPTY
                        {
                            JOptionPane.showMessageDialog(null, "Your message is empty. You can't send an empty message", "Sorry", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else if (match == 0) //FALSE
                    {
                        JOptionPane.showMessageDialog(null, "You don't have a contact with this number, please try with another number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                    } else if (match == 2) //CONTACT PANEL IS EMPTY
                    {
                        JOptionPane.showMessageDialog(null, "You haven't put a contact number. Please input a contact number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                    } else if (match == 3) //CONTACT PANEL CONTAINS LETTERS
                    {
                        JOptionPane.showMessageDialog(null, "Your contact number contains characters other than numbers, please try again", "Sorry", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String check = textArea.getText().trim();
                    if (!check.equals("") && !check.equals("Type your message here...")) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Your message hasn't been sent yet. Do you want to save it to draft?", "Save to draft?", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == 0) //YES
                        {
                            int lines = countLinesOfFile("Nama dan Nomor");

                            int match = 0; //FALSE
                            String number = contactPanel.getNumber(); //GETTING NUMBER FROM CONTACT PANEL
                            int gettingName = 0;
                            for (int i = 2; i < lines; i = i + 3) {
                                String numberFromFile = ReadFile("Nama dan Nomor", i);
                                if (numberFromFile.equals(number)) {
                                    gettingName = i - 1;
                                    match = 1; //TRUE
                                    break;
                                }
                            }

                            if (number.equals("")) {
                                match = 2; //FOR EMPTY NUMBER
                            }

                            int stringLength = number.length();
                            for (int i = 0; i < stringLength; i++) {
                                char isItNumber = number.charAt(i);
                                if (Character.isLetter(isItNumber)) {
                                    match = 3;
                                    break;
                                }
                            }

                            if (match == 1) {
                                String s = ReadFile("Nama dan Nomor", gettingName) + "\n" + textArea.getText();
                                AddingTextToFile("Draft", s); //SAVING TO DRAFT
                                JOptionPane.showMessageDialog(null, "Your message has been saved to draft", "Messsage", JOptionPane.PLAIN_MESSAGE);
                            } else if (match == 0) //FALSE
                            {
                                JOptionPane.showMessageDialog(null, "You don't have a contact with this number, please try with another number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            } else if (match == 2) //CONTACT PANEL IS EMPTY
                            {
                                JOptionPane.showMessageDialog(null, "You haven't put a contact number. Please input a contact number", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            } else if (match == 3) //CONTACT PANEL CONTAINS LETTERS
                            {
                                JOptionPane.showMessageDialog(null, "Your contact number contains characters other than numbers, please try again", "Sorry", JOptionPane.PLAIN_MESSAGE);
                            }

                        }
                    }
                    if (header.getText().startsWith("To: ")) //IF FROM INBOX
                    {
                        Inbox inbox = new Inbox();
                        inbox.setVisible(true);
                        dispose();
                    } else {
                        MessagingApp messaging = new MessagingApp();
                        messaging.setVisible(true);
                        dispose();
                    }
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(5, 5, 5, 5);
            add(buttonPanel, c);
        }

        public void setHeader(String name) {
            header.setText("To: " + name);
        }

        public void setNumber(String number) {
            contactPanel.setNumber(number);
        }

        public void AddingTextToFile(String fileName, String text) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            for (int i = 0; i < fileLines; i++) {
                stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
            }
            stringTemp = stringTemp + "\n" + text;

            System.out.println(stringTemp);
            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public void changeNthLine(int n, String fileName, String newWord) //editing the draft file
        {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            int fileLines = countLinesOfFile(fileName);
            String stringTemp = new String();

            if (n != fileLines) {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + newWord + "\n";
                for (int i = n; i < fileLines - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + ReadFile(fileName, fileLines);
            } else //n ==fileLines
            {
                for (int i = 0; i < n - 1; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
                stringTemp = stringTemp + newWord;
                for (int i = n; i < fileLines; i++) {
                    stringTemp = stringTemp + ReadFile(fileName, i + 1) + "\n";
                }
            }

            System.out.println(stringTemp);
            byte[] stringData = stringTemp.getBytes();
            try {
                FileOutputStream output = new FileOutputStream(fileName + ".txt");
                output.write(stringData);
                output.flush();
                output.close();
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }

    public class Inbox extends JFrame {

        int lines; //this also determines how much mbp there are

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel mainPanel = new JPanel(new BorderLayout());
        InsidePanelInbox insidePanel = new InsidePanelInbox(lines, this);
        JPanel buttonPanel = new JPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("Inbox", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton back = new JButton("back");
        //END OF JBUTTON////////////////////////////////////////////////////////

        JScrollPane scrollPane = new JScrollPane(insidePanel);

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        public Inbox() {
            super("Inbox");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(back);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //MAIN PANEL////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(mainPanel, c);

            lines = insidePanel.getLines();
            checkLines();

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessagingApp messaging = new MessagingApp();
                    messaging.setVisible(true);
                    dispose();
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(buttonPanel, c);

            /*for(int i=0; i<lines; i++)
             {
             insidePanel.mbp[i].deleteButton.addActionListener(new ActionListener()
             {
             public void actionPerformed(ActionEvent e)
             {	
             System.out.println("lala");
             }
             });
             }*/
        }
        //END OF INBOX()///////////////////////////////////

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int getLines() {
            return lines;
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public void checkLines() {
            if (lines <= 6) {
                mainPanel.add(insidePanel, BorderLayout.NORTH);
            } else //line>6
            {
                mainPanel.add(scrollPane);
            }
        }
    }

    public class Draft extends JFrame {

        int lines; //this also determines how much mbp there are

        //JPANEL/////////////////////////////////////////////////////////////
        JPanel mainPanel = new JPanel(new BorderLayout());
        InsidePanelDraft insidePanel = new InsidePanelDraft(lines, this);
        JPanel buttonPanel = new JPanel();
	//END OF JPANEL//////////////////////////////////////////////////////

        //JLABEL/////////////////////////////////////////////////////////////
        JLabel header = new JLabel("Draft", JLabel.CENTER);
	//END OF JLABEL//////////////////////////////////////////////////////

        //JBUTTON/////////////////////////////////////////////////////////////
        JButton back = new JButton("back");
        //END OF JBUTTON////////////////////////////////////////////////////////

        JScrollPane scrollPane = new JScrollPane(insidePanel);

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 500;

        public Draft() {
            super("Draft");
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            back.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            buttonPanel.add(back);

            //HEADER////////////////////////////////
            c.weightx = 1;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 20;
            add(header, c);

            //MAIN PANEL////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 450;
            c.insets = new Insets(0, 5, 0, 5);
            add(mainPanel, c);

            lines = insidePanel.getLines();
            checkLines();

            //BACK BUTTON////////////////////////////
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MessagingApp messaging = new MessagingApp();
                    messaging.setVisible(true);
                    dispose();
                }
            });

            //BUTTON PANEL///////////////////////////////
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(buttonPanel, c);
        }
        //END OF SENTMESSAGE()////////////////////////////

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int getLines() {
            return lines;
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public void checkLines() {
            if (lines <= 6) {
                mainPanel.add(insidePanel, BorderLayout.NORTH);
            } else //line>6
            {
                mainPanel.add(scrollPane);
            }
        }
    }

    public class ContactPanel extends JPanel {

        JLabel contact = new JLabel("Contact");
        JTextField number = new JTextField();
        JButton search = new JButton("...");

        public ContactPanel() {
            contact.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //CONTACT////////////////////////////////
            c.weightx = 0.01;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(contact, c);

            //TEXT FIELD (NUMBER)////////////////////////////////
            c.weightx = 0.5;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 5, 0, 5);
            add(number, c);

            //SEARCH////////////////////////////////
            c.weightx = 0.01;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 0;
            c.insets = new Insets(0, 0, 0, 0);
            add(search, c);
        }

        public void setNumber(String num) {
            number.setText(num);
        }

        public String getNumber() {
            String num = number.getText();
            return num;
        }
    }

    public class InsidePanelDraft extends JPanel //PANEL WHERE MBP IS
    {

        int lines;
        Draft draft;

        public InsidePanelDraft(int lines, Draft draft) {
            super();

            this.lines = lines;
            this.draft = draft;
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //CREATING THE MBP ARRAYS//////////////////////////////////////////////
            lines = (countLinesOfFile("Draft") - 2) / 3 + 1;
            MultiButtonPanelDraft[] mbp = new MultiButtonPanelDraft[lines];

            //ADDING THE MBP ARRAYS///////////////////////////////////////////////
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            //c.gridy diatur di (for) statement di bawah
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.ipadx = 0;
            c.ipady = 10;
            c.insets = new Insets(0, 0, 0, 0);

            for (int i = 0; i < lines; i++) //MAKING MBP
            {
                String nameTemp = ReadFile("Draft", 3 * i + 1);
                String numberTemp = "";
                int contactLines = countLinesOfFile("Nama dan Nomor");

                for (int j = 1; j < contactLines; j = j + 3) {
                    String nameFromFile = ReadFile("Nama dan Nomor", j);
                    if (nameFromFile.equals(nameTemp)) {
                        numberTemp = ReadFile("Nama dan Nomor", j + 1);
                        break;
                    }
                }
                mbp[i] = new MultiButtonPanelDraft(nameTemp, ReadFile("Draft", 3 * i + 2), numberTemp, draft);
                c.gridy = i;
                add(mbp[i], c);
            }
            setLines(lines);
        }

        public void setLines(int lines) {
            this.lines = lines;
        }

        public int getLines() {
            return lines;
        }

        public String ReadFile(String fileName, int line) {
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            String s = null;
            int whichLine = line;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int i = 0; i < whichLine; i++) {
                    s = reader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Message" + e);
            }
            return s;
        }

        public int countLinesOfFile(String fileName) {
            int i = 0;
            Path file = Paths.get("C:\\Users\\user\\Desktop\\Program Java\\Messaging Project\\" + fileName + ".txt");
            InputStream input = null;
            try {
                input = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                while (reader.readLine() != null) {
                    i++;
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return i;
        }
    }
};
