/*
 * The MIT License
 *
 * Copyright 2024 Saleh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.salehrezq.speedreading;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.Document;

/**
 *
 * @author S
 */
public class SpeedReading extends JPanel {

    int testInt = 0;
    ///
    final static StringFunction stringFunc = new StringFunction();
    FileChooser openFile;
    ColorChanger colorChanger;
    ////////
    static JFrame frame;
    static JTextArea textArea;
    static JLabel label;
    JPanel upperPanel;
    JPanel mainLowerPanel;
    JPanel controlPanel;
    JPanel sliderPanel;
    JPanel widgetPanel_1;
    JPanel widgetPanel_2;
    /////
    JMenuBar menubar;
    JMenu fileMenue;
    JMenu optionsMenue;
    JMenu subOptionMenue;
    JMenu operationsMenue;
    static JMenuItem textColorItem;
    static JMenuItem backGroundColorItem;
    static JMenuItem openFileItem;
    JMenuItem default_Item;
    JMenuItem normal_item;
    JMenuItem trainer_item;
    JMenuItem item_start;
    JMenuItem item_pause;
    JMenuItem item_stop;
    JMenuItem item_fullscreen;
    JMenuItem item_stop_start_increaser;
    ////
    JToolBar toolbar;
    ////
    JButton btn_fullscreen;
    JLabel lb_emptySpaceLabel;
    ////
    JButton startButton;
    JButton pauseButton;
    JButton stopButton;
    Integer_textField field_wordsPerMinute;
    Integer_textField field_flashedWords;
    Integer_textField field_increaserEvery;
    Integer_textField field_increaserAmount;
    Integer_textField field_speedMaxLimit;
    Integer_textField field_duration;
    Integer_textField field_delay;
    JComboBox<Integer> combo_fontSize;
    JComboBox<String> combo_fontTypes;
    JLabel label_wordsPerMinute;
    JLabel label_flashedWords;
    JLabel label_increaserEvery;
    JLabel label_increaserAmount;
    JLabel label_speedMaxLimit;
    JLabel label_fontSize;
    JLabel lb_delay;
    JLabel lb_duration;
    JLabel lb_yourSpeed;
    JLabel lb_realTimeSpeed;
    ///
    boolean docOrFlashedWordsChange = false;
    boolean timerStopped = true;
    boolean inPauseState = true;
    boolean inStopState = true;
    boolean pauseIndexboolean = false;
    ////
    boolean field_timeBased_changed = false;
    ////
    boolean allowIncreaserTimer = true;
    boolean boolSpeedMaxLimit = false;
    boolean increaseContinue = true;
    ////
    boolean booleanAllFields;
    String input;
    ////
    JScrollBar sliderScroolForFlashedWords;
    JButton sliderLeftButton;
    JButton sliderRightButton;
    JCheckBox hideSliderCheckBox;
    JCheckBox checkBox_timerIncreaser;
    JCheckBox checkBox_speedMaxLimit;
    ///
    Font labels_font = new Font("san Serif", Font.ROMAN_BASELINE, 15);
    int fontSize;
    String fontType;
    GraphicsEnvironment ge;
    String[] fontFamilies;
    ///
    List<String> listChunks_of_Strig;
    List<String> listOfFlashedWordsFromSentenceTokensArray;
    int listOfFlashedWordsIndexer;
    int pauseIndex;
    ////
    int numOfFlashedWords;
    int numberOfWordsPerMinute;
    int eachWordTime;
    int flashTime;
    int increaseEvery;
    int increaseAmount;
    int delay;
    final int minuteOfMSec = 60000;
    ////
    Timer timer;
    Timer timerIncreaser;
    String[] sentencesArray;
    ///
    boolean mode = true;
    boolean endOfSlider;
    boolean mouseReleased_OK = false;
    ////
    private Container previousContentPane;
    boolean fullScreen = false;
    ////

    public SpeedReading() {
        label = new JLabel();
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        ///////
        upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        upperPanel.add(label, BorderLayout.CENTER);
        ///////
        menubar = new JMenuBar();
        fileMenue = new JMenu("File");
        optionsMenue = new JMenu("Options");
        operationsMenue = new JMenu("Operations");
        textColorItem = new JMenuItem("Text color");
        backGroundColorItem = new JMenuItem("Background color");
        ///
        item_fullscreen = new JMenuItem("Full screen");
        item_fullscreen.addActionListener(eventWatcher);
        item_fullscreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "ExitFullScreen");
        item_fullscreen.getActionMap().put("ExitFullScreen", keyAction_item_exitFullScreen);
        ////
        item_stop_start_increaser = new JMenuItem("Stop gradual acceleration");
        item_stop_start_increaser.addActionListener(eventWatcher);
        item_stop_start_increaser.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "increaser");
        item_stop_start_increaser.getActionMap().put("increaser", keyAction_item_stopStartIncreaser);
        ////
        default_Item = new JMenuItem("Set defaults");
        default_Item.addActionListener(eventWatcher);
        /////
        openFileItem = new JMenuItem("Open file");
        fileMenue.add(openFileItem);
        optionsMenue.add(textColorItem);
        optionsMenue.add(backGroundColorItem);
        subOptionMenue = new JMenu("Mode");
        normal_item = new JMenuItem("<HTML><b><font color=\"red\">> </font></b><font color=\"black\"/>Normal</font></HTML>");
        normal_item.addActionListener(eventWatcher);
        trainer_item = new JMenuItem("<HTML>&nbsp;&nbsp;&nbsp;<font color=\"black\"/>Trainer</font></HTML>");
        trainer_item.addActionListener(eventWatcher);
        subOptionMenue.add(normal_item);
        subOptionMenue.add(trainer_item);
        optionsMenue.addSeparator();
        optionsMenue.add(subOptionMenue);
        optionsMenue.addSeparator();
        optionsMenue.add(item_fullscreen);
        optionsMenue.addSeparator();
        optionsMenue.add(default_Item);
        ////
        item_start = new JMenuItem("Start");
        item_start.addActionListener(eventWatcher);
        item_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_start");
        item_start.getActionMap().put("item_start", keyAction_item_start);
        ///
        item_pause = new JMenuItem("Pause");
        item_pause.addActionListener(eventWatcher);
        item_pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_pause");
        item_pause.getActionMap().put("item_pause", keyAction_item_pause);
        ///
        item_stop = new JMenuItem("Stop");
        item_stop.addActionListener(eventWatcher);
        item_stop.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "item_stop");
        item_stop.getActionMap().put("item_stop", keyAction_item_stop);
        ///
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        label.getActionMap().put("left", keyAction_item_left);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left_released");
        label.getActionMap().put("left_released", keyAction_item_left_released);
        ///
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        label.getActionMap().put("right", keyAction_item_right);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right_released");
        label.getActionMap().put("right_released", keyAction_item_right_released);
        ///
        operationsMenue.add(item_start);
        operationsMenue.add(item_pause);
        operationsMenue.add(item_stop);
        operationsMenue.addSeparator();
        operationsMenue.add(item_stop_start_increaser);
        ////
        menubar.add(fileMenue);
        menubar.add(optionsMenue);
        menubar.add(operationsMenue);
        frame.setJMenuBar(menubar);
        ///////
        openFile = new FileChooser();
        colorChanger = new ColorChanger();
        //////
        UIManager.put("ToolTip.background", new ColorUIResource(255, 255, 215)); // The color is #fff7c8.
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0)); // The color is #4c4f53.
        UIManager.put("ToolTip.border", border);
        ToolTipManager.sharedInstance().setDismissDelay(1500);// 1.5 seconds  
        ToolTipManager.sharedInstance().setInitialDelay(150);
        //////
        btn_fullscreen = new JButton(new ImageIcon(getClass().getResource("/images/fullscreen.png")));
        btn_fullscreen.addActionListener(eventWatcher);
        btn_fullscreen.setBorder(BorderFactory.createEmptyBorder());
        btn_fullscreen.setToolTipText("Full screen");
        ////
        lb_emptySpaceLabel = new JLabel("       ");
        ////
        startButton = new JButton("Start");
        startButton.setToolTipText("Shortcut SPACE bar");
        startButton.addActionListener(eventWatcher);
        startButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Start");
        startButton.getActionMap().put("Start", startButtonAction);
        ///////
        pauseButton = new JButton("Pause");
        pauseButton.setToolTipText("Shortcut SPACE bar");
        pauseButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Pause");
        pauseButton.getActionMap().put("Pause", pauseButtonAction);
        // pauseButton.setEnabled(false);
        setPauseEnabled(false);
        pauseButton.setActionCommand("Pause");
        pauseButton.addActionListener(eventWatcher);
        ///////
        stopButton = new JButton("Stop");
        stopButton.setToolTipText("Shortcut 'S'");

        // stopButton.setEnabled(false);
        setStopEnabled(false);
        stopButton.addActionListener(eventWatcher);
        stopButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
        stopButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "Stop");
        stopButton.getActionMap().put("Stop", stopButtonAction);
        ///////
        label_wordsPerMinute = new JLabel("Words/minute");
        label_flashedWords = new JLabel("Words group");
        label_increaserEvery = new JLabel("Increase every");
        label_increaserAmount = new JLabel("Increase amount");
        label_speedMaxLimit = new JLabel("Max speed");
        label_speedMaxLimit.setEnabled(false);
        //////
        field_wordsPerMinute = new Integer_textField(3);
        field_wordsPerMinute.addPropertyChangeListener("value", new FieldChangeListener());
        field_wordsPerMinute.addFocusListener(FocusManager);
        field_wordsPerMinute.setToolTipText("Number of words per minute");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                field_wordsPerMinute.requestFocus();
            }
        });
        //////////
        field_flashedWords = new Integer_textField(3);
        field_flashedWords.addPropertyChangeListener("value", new FlahedWordsChangeListener());
        field_flashedWords.addFocusListener(FocusManager);
        field_flashedWords.setToolTipText("Number of words group");
        /////////
        field_increaserEvery = new Integer_textField(3);
        field_increaserEvery.addPropertyChangeListener("value", new FieldChangeListener());
        field_increaserEvery.addFocusListener(FocusManager);
        field_increaserEvery.setToolTipText("Increase every n of milliseconds");
        ////
        field_increaserAmount = new Integer_textField(3);
        field_increaserAmount.addPropertyChangeListener("value", new FieldChangeListener());
        field_increaserAmount.addFocusListener(FocusManager);
        field_increaserAmount.setToolTipText("Number of words to increase");
        ////////
        field_speedMaxLimit = new Integer_textField(3);
        field_speedMaxLimit.addPropertyChangeListener("value", new FieldChangeListener());
        field_speedMaxLimit.addFocusListener(FocusManager);
        field_speedMaxLimit.setToolTipText("Words/minute max limit (speed)");
        field_speedMaxLimit.setEnabled(false);
        ////
        field_duration = new Integer_textField(4);
        field_duration.addPropertyChangeListener("value", new FieldChangeListener());
        field_duration.addFocusListener(FocusManager);
        field_duration.setToolTipText("Duration to stay in milliseconds");
        ///
        field_delay = new Integer_textField(4);
        field_delay.setAllowZeroValue(true);
        field_delay.addPropertyChangeListener("value", new FieldChangeListener());
        field_delay.addFocusListener(FocusManager);
        field_delay.setToolTipText("Delay between two durations in milliseconds");
        ///
        lb_duration = new JLabel("Duration");
        lb_delay = new JLabel("Delay");

        lb_yourSpeed = new JLabel("Your speed = 0 w/m");
        lb_realTimeSpeed = new JLabel(", +Delay speed = 0 w/m");
        ////////////
        combo_fontSize = new JComboBox<>();
        combo_fontSize.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
        combo_fontSize.addItemListener(new itemListenerClass());
        for (int i = 15; i < 151; i++) {
            combo_fontSize.addItem(i);
        }
        combo_fontSize.setSelectedItem(26);
        ///////
        combo_fontTypes = new JComboBox<>();
        combo_fontTypes.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
        combo_fontTypes.addItemListener(new itemListenerClass());
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontFamilies = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fontFamilies.length; i++) {
            combo_fontTypes.addItem(fontFamilies[i]);
        }
        combo_fontTypes.setSelectedItem("Serif");
        ///////
        checkBox_timerIncreaser = new JCheckBox("Gradual acceleration");
        checkBox_timerIncreaser.setSelected(true);
        checkBox_timerIncreaser.addItemListener(checkBoxIncreaseListener);
        checkBox_timerIncreaser.setToolTipText("Shortcut 'A'");
        /////
        checkBox_speedMaxLimit = new JCheckBox("Max speed limit");
        checkBox_speedMaxLimit.setSelected(false);
        checkBox_speedMaxLimit.addItemListener(checkBoxSpeedMaxListener);
        ////
        toolbar = new JToolBar();
        toolbar.setMaximumSize(new Dimension(270, 40));
        combo_fontTypes.setMaximumSize(new Dimension(200, 18));
        combo_fontSize.setMaximumSize(new Dimension(50, 18));
        toolbar.add(combo_fontTypes);
        toolbar.add(combo_fontSize);
        toolbar.add(checkBox_timerIncreaser);
        toolbar.add(checkBox_speedMaxLimit);
        toolbar.add(lb_emptySpaceLabel);
        toolbar.add(btn_fullscreen);
        frame.add(toolbar, BorderLayout.PAGE_START);
        /////
        sliderScroolForFlashedWords = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 0);
        sliderScroolForFlashedWords.addAdjustmentListener(sliderScrollListener);
        sliderScroolForFlashedWords.addMouseListener(new MouseLisenerForSlider());
        ///
        sliderLeftButton = (JButton) sliderScroolForFlashedWords.getAccessibleContext().getAccessibleChild(1);
        sliderLeftButton.addMouseListener(new MouseLisenerForSlider());
        ///
        sliderRightButton = (JButton) sliderScroolForFlashedWords.getAccessibleContext().getAccessibleChild(0);
        sliderRightButton.addMouseListener(new MouseLisenerForSlider());
        ///
        hideSliderCheckBox = new JCheckBox("Hide slider");
        hideSliderCheckBox.setToolTipText("Shortcut 'H'");
        hideSliderCheckBox.addItemListener(checkBoxListener);
        hideSliderCheckBox.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
        hideSliderCheckBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), "Hide");
        hideSliderCheckBox.getActionMap().put("Hide", hideCheckAction);
        ///////
        widgetPanel_1 = new JPanel();
        widgetPanel_1.add(label_wordsPerMinute);
        widgetPanel_1.add(field_wordsPerMinute);
        widgetPanel_1.add(label_flashedWords);
        widgetPanel_1.add(field_flashedWords);
        widgetPanel_1.add(label_increaserEvery);
        widgetPanel_1.add(field_increaserEvery);
        widgetPanel_1.add(label_increaserAmount);
        widgetPanel_1.add(field_increaserAmount);
        widgetPanel_1.add(label_speedMaxLimit);
        widgetPanel_1.add(field_speedMaxLimit);
        /////
        widgetPanel_2 = new JPanel();
        ///////
        widgetPanel_2.add(startButton);
        widgetPanel_2.add(pauseButton);
        widgetPanel_2.add(stopButton);
        widgetPanel_2.add(hideSliderCheckBox);
        /////
        sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.add(sliderScroolForFlashedWords);

        controlPanel = new JPanel(new GridLayout(3, 1));
        controlPanel.add(widgetPanel_1);
        controlPanel.add(widgetPanel_2);
        controlPanel.add(sliderPanel);
        ///////
        textArea = new JTextArea(0, 0);
        textArea.setFont(textArea.getFont().deriveFont(0, 20));
        JScrollPane scrollTextArea = new JScrollPane(textArea);
        scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(true);
        textArea.setWrapStyleWord(true);
        textArea.getDocument().addDocumentListener(new DocumentUpdateListener());
        textArea.addFocusListener(FocusManager);
        //////
        mainLowerPanel = new JPanel();
        mainLowerPanel.setLayout(new BorderLayout());
        //////
        mainLowerPanel.add(controlPanel, BorderLayout.NORTH);
        mainLowerPanel.add(scrollTextArea, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());

        Dimension upperPanelMinimumSize = new Dimension(0, 5);
        Dimension mainLowerPanelMinimumSize = new Dimension(0, 5);
        upperPanel.setMinimumSize(upperPanelMinimumSize);
        mainLowerPanel.setMinimumSize(mainLowerPanelMinimumSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                upperPanel, mainLowerPanel);
        splitPane.setDividerLocation(140); //location up and down vertically.
        splitPane.setResizeWeight(.5d); //resizing weight

        this.add(splitPane);
    }

    private void ifFullScreenAndErrorMessageExitFullScreen() {
        if (fullScreen) {
            ungoFullScreen();
        }
    }

    private boolean checkTextArea() {
        input = textArea.getText();
        if (input.length() >= 1) {
            return true;
        } else {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkWordsPerMinute() {
        try {
            Integer.parseInt(field_wordsPerMinute.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkFlashedWrods() {
        try {
            Integer.parseInt(field_flashedWords.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkIncreaserEvery() {
        try {
            Integer.parseInt(field_increaserEvery.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkIncreaserAmount() {
        try {
            Integer.parseInt(field_increaserAmount.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkSpeedMaxLimit() {
        try {
            Integer.parseInt(field_speedMaxLimit.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkDuration() {
        try {
            Integer.parseInt(field_duration.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    private boolean checkDelay() {
        try {
            Integer.parseInt(field_delay.getText().replaceAll(",", ""));
            return true;
        } catch (java.lang.NumberFormatException err) {
            ifFullScreenAndErrorMessageExitFullScreen();
            return false;
        }
    }

    public boolean checkFields() {
        if (mode) {
            if (!checkTextArea()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Text area:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (!checkWordsPerMinute()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Words/minute:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (!checkFlashedWrods()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Words group:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (allowIncreaserTimer) {
                if (!checkIncreaserEvery()) {
                    JOptionPane.showMessageDialog(frame, "<HTML><b>Increase every:</b> <font color=\"red\">Empty</font></HTML>");
                }
                if (!checkIncreaserAmount()) {
                    JOptionPane.showMessageDialog(frame, "<HTML><b>Increase amount:</b> <font color=\"red\">Empty</font></HTML>");
                }
                if (boolSpeedMaxLimit) {
                    if (!checkSpeedMaxLimit()) {
                        JOptionPane.showMessageDialog(frame, "<HTML><b>Speed max:</b> <font color=\"red\">Empty</font></HTML>");
                    }
                }
            }
            if (allowIncreaserTimer) {
                if (boolSpeedMaxLimit) {
                    booleanAllFields = (checkTextArea() && checkWordsPerMinute() && checkFlashedWrods() && checkIncreaserEvery() && checkIncreaserAmount() && checkSpeedMaxLimit());
                } else {
                    booleanAllFields = (checkTextArea() && checkWordsPerMinute() && checkFlashedWrods() && checkIncreaserEvery() && checkIncreaserAmount());
                }

            } else {
                booleanAllFields = (checkTextArea() && checkWordsPerMinute() && checkFlashedWrods());
            }

        } else {
            if (!checkTextArea()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Text area:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (!checkFlashedWrods()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Words group:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (!checkDuration()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Duration:</b> <font color=\"red\">Empty</font></HTML>");
            }
            if (!checkDelay()) {
                JOptionPane.showMessageDialog(frame, "<HTML><b>Delay:</b> <font color=\"red\">Empty</font></HTML>");
            }
            booleanAllFields = (checkTextArea() && checkFlashedWrods() && checkDuration() && checkDelay());
        }
        return booleanAllFields;
    }

    public void takeInputTextArea() {
        testInt++;
        if (checkFields()) {
            mouseReleased_OK = true;
            if ((docOrFlashedWordsChange) || (field_timeBased_changed)) {
                if (docOrFlashedWordsChange) {
                    System.out.println(testInt + " Start Dramatic Change");
                    docOrFlashedWordsChange = false;
                    field_timeBased_changed = false;
                    ///
                    String processedText = stringFunc.trim_eatSpaces_eatDots_eatNewlines_breakScentence_keepEndDots((input));
                    textArea.setText(processedText);
                    sentencesArray = StringFunction.sentenceTokens(processedText);
                    setNumOfFlashedWords(Integer.parseInt(field_flashedWords.getText().replaceAll(",", "")));
                    calculateHowToFlash(getNumOfFlashedWords(), sentencesArray);
                    int listSize = getListOfFlashedWordsFromSentenceTokensArray().size();
                    sliderScroolForFlashedWords.setValues(0, 0, 0, listSize - 1);
                    setFlashTime_ModeBased();
                    settingIncreasingTime();
                    setTimerSwitch().start();
                    setUpTimerIncreaser();
                    setDelayIncreaser();
                    startTimerIncreaser();
                } else {
                    System.out.println(testInt + " Start Time Change");
                    field_timeBased_changed = false;
                    setFlashTime_ModeBased();
                    settingIncreasingTime();
                    setUpTimerIncreaser();
                    setDelayFlashedTime();
                    setDelayIncreaser();
                    timer.start();
                    startTimerIncreaser();
                }
            } else {
                System.out.println(testInt + " Start No thing Change");
                setFlashTime_ModeBased();
                settingIncreasingTime();
                setUpTimerIncreaser();
                setDelayFlashedTime();
                setDelayIncreaser();
                timer.start();
                startTimerIncreaser();
            }
        }
    }

    public void calculateHowToFlash(int wordsChunck, String[] tokensArray) {
        listOfFlashedWordsFromSentenceTokensArray = new ArrayList<>();
        for (int i = 0; i < tokensArray.length; i++) {
            listOfFlashedWordsFromSentenceTokensArray.addAll(getSentenceFlashChunks(wordsChunck, tokensArray[i]));
        }
    }

    public List<String> getListOfFlashedWordsFromSentenceTokensArray() {
        return this.listOfFlashedWordsFromSentenceTokensArray;
    }

    public void printListOfCalculatedFlashingWords() {
        for (int i = 0; i < this.listOfFlashedWordsFromSentenceTokensArray.size(); i++) {
            System.out.println(listOfFlashedWordsFromSentenceTokensArray.get(i));
        }
    }

    public List<String> getSentenceFlashChunks(int wordsChunk, String str) {
        String trimedString = StringFunction.eatExtraSpaces(str);

        StringBuilder strBuilder = new StringBuilder(50);
        listChunks_of_Strig = new ArrayList<>();

        int wordsCounter = 0;
        int i = 0;
        int listCounter = 0;
        while (i < trimedString.length()) {
            while (i < trimedString.length()) {
                if (wordsCounter == wordsChunk) {
                    break;
                }
                strBuilder.append(trimedString.charAt(i));

                if (trimedString.charAt(i) == ' ') //assumed extra spaces eated
                {
                    wordsCounter++;
                }
                i++;
            }

            String tempStr = strBuilder.toString();
            listChunks_of_Strig.add(listCounter, StringFunction.trimString(tempStr));
            strBuilder.setLength(0);
            listCounter++;
            wordsCounter = 0;
        }
        return listChunks_of_Strig;
    }

    public int get_eachWordTime() {
        return eachWordTime;
    }

    public void setNumOfFlashedWords(int n) {
        this.numOfFlashedWords = n;
    }

    public int getNumOfFlashedWords() {
        return this.numOfFlashedWords;
    }

    public int getNumberOfWordsPerMinute() {
        return numberOfWordsPerMinute;
    }

    public void setNumberOfWordsPerMinute(int numberOfWordsPerMinute) {
        this.numberOfWordsPerMinute = numberOfWordsPerMinute;
    }

    public int get_flashTime() {
        return flashTime;
    }

    public void set_flashTime(int flashT) {
        flashTime = flashT;
    }

    public void set_eachWordTime(int echWT) {
        eachWordTime = echWT;
    }

    public int getIncreaseEvery() {
        return increaseEvery;
    }

    public void setIncreaseEvery(int increaseEvery) {
        this.increaseEvery = increaseEvery;
    }

    public int getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public void setDelayTime(int delay) {
        this.delay = delay;
    }

    public int getDelayTime() {
        return this.delay;
    }

    public void settingFlashingTime() {
        setNumberOfWordsPerMinute(Integer.parseInt(field_wordsPerMinute.getText().replaceAll(",", "")));
        set_eachWordTime((minuteOfMSec / getNumberOfWordsPerMinute()));
        setNumOfFlashedWords(Integer.parseInt(field_flashedWords.getText().replaceAll(",", "")));
        set_flashTime(get_eachWordTime() * getNumOfFlashedWords());
    }

    public void settingFlashingTime_Eye_TeainMode() {
        setNumOfFlashedWords(Integer.parseInt(field_flashedWords.getText().replaceAll(",", "")));
        set_flashTime(Integer.parseInt(field_duration.getText().replaceAll(",", "")));
        setDelayTime(Integer.parseInt(field_delay.getText().replaceAll(",", "")));
        int readSpeed = (minuteOfMSec / get_flashTime()) * getNumOfFlashedWords();
        String speed = "Read speed = " + readSpeed + " w/m";
        lb_yourSpeed.setText(speed);
        int realTimeSpeed = (minuteOfMSec / (get_flashTime() + getDelayTime())) * getNumOfFlashedWords();
        String realTime = ", +Delay speed = " + realTimeSpeed + " w/m";
        lb_realTimeSpeed.setText(realTime);
    }

    public boolean getMode() {
        return mode;
    }

    public void setFlashTime_ModeBased() {
        if (getMode()) {
            settingFlashingTime();
            // System.out.println("mode normal");
        } else {
            settingFlashingTime_Eye_TeainMode();
            System.out.println("mode traner");
        }
    }

    public void setDelayFlashedTime() {
        //Set delay time only in normal mode. Trainer mode takes its time delay from JtextFields.
        if (mode) {
            timer.setDelay(this.get_flashTime());
        }
    }

    /**
     * *************************************************
     */
    public Timer setTimerSwitch() {
        if (mode) {
            timer = new Timer(get_flashTime(), normalAction);
            timer.setRepeats(true);
        } else {
            timer = new Timer(getDelayTime(), timerSwitcher);
            timer.setRepeats(false);
        }
        return timer;
    }
    AbstractAction timerSwitcher = new AbstractAction() {
        private boolean flag = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            //This if condition solves the bug of repeating the last item twice.
            if (listOfFlashedWordsIndexer < getListOfFlashedWordsFromSentenceTokensArray().size() - 1) {
                final Action action = flag ? normalAction : trainerAction;
                final int delay = flag ? get_flashTime() : getDelayTime();
                flag = !flag;
                // System.out.println("duration " + get_flashTime());//problem
                // System.out.println("delay " + getDelayTime());//no problem
                action.actionPerformed(e);
                timer.setInitialDelay(delay);
                timer.start();
            }
        }
    };
    Action normalAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pauseIndexboolean) {
                listOfFlashedWordsIndexer = pauseIndex;
                pauseIndexboolean = false;
            }

            int listLength = getListOfFlashedWordsFromSentenceTokensArray().size();

            if (listLength > 0) {
                if (listOfFlashedWordsIndexer == listLength) {
                    timer.stop();
                    stopTimerIncreaser();
                    timerStopped = true;
                } else {
                    if (listOfFlashedWordsIndexer < getListOfFlashedWordsFromSentenceTokensArray().size()) {
                        label.setText(getListOfFlashedWordsFromSentenceTokensArray().get(listOfFlashedWordsIndexer++));
                        sliderScroolForFlashedWords.setValue(listOfFlashedWordsIndexer);
                    }
                }
            } else {
                // stopButton.setEnabled(false);
                setStopEnabled(false);
                // pauseButton.setEnabled(false);
                setPauseEnabled(false);
                timer.stop();
                stopTimerIncreaser();
                System.out.println("timer stopped");
                // startButton.setEnabled(true);
                setStartEnabled(true);
            }
        }
    };
    Action trainerAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("");
        }
    };

    public void settingIncreasingTime() {
        if ((allowIncreaserTimer) && (mode)) {
            setIncreaseEvery(Integer.parseInt(field_increaserEvery.getText().replaceAll(",", "")));
            setIncreaseAmount(Integer.parseInt(field_increaserAmount.getText().replaceAll(",", "")));
        }
    }

    public void setDelayIncreaser() {
        if ((allowIncreaserTimer) && (mode)) {
            timerIncreaser.setDelay(getIncreaseEvery());
        }
    }

    public Timer getTimerIncreaser() {
        return timerIncreaser = new Timer(getIncreaseEvery(), timerIncreaserAction);
    }

    public void setUpTimerIncreaser() {
        if ((timerIncreaser == null) && (allowIncreaserTimer) && (mode)) {
            getTimerIncreaser().start();
        }
    }

    public void startTimerIncreaser() {
        if ((timerIncreaser != null) && (allowIncreaserTimer) && (mode) && (!timerIncreaser.isRunning())) {
            timerIncreaser.start();
        }
    }

    public void stopTimerIncreaser() {
        if ((mode) && (timerIncreaser != null) && ((allowIncreaserTimer) || (inStopState) || (inPauseState))) {
            timerIncreaser.stop();
        }
    }
    Action timerIncreaserAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ((mode) && (allowIncreaserTimer)) {
                if ((boolSpeedMaxLimit) && (!field_speedMaxLimit.getText().equals(""))) {
                    int speed = Integer.parseInt(field_wordsPerMinute.getText());
                    int max = Integer.parseInt(field_speedMaxLimit.getText());

                    if (speed >= max) {
                        field_wordsPerMinute.setText(String.valueOf(max));
                        setFlashTime_ModeBased();
                        setDelayFlashedTime();
                        stopTimerIncreaser();
                        increaseContinue = false;
                    } else {
                        if (speed < max) {
                            increaseContinue = true;
                        }
                    }
                }
                if (increaseContinue) {
                    int increaseAmount = getIncreaseAmount();
                    int afterIncreasing = getNumberOfWordsPerMinute() + increaseAmount;
                    field_wordsPerMinute.setText(String.valueOf(afterIncreasing));
                    setFlashTime_ModeBased();
                    setDelayFlashedTime();
                    afterIncreasing = afterIncreasing + increaseAmount;
                }
            }
        }
    };
    AbstractAction startButtonAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            startButton.doClick();
        }
    };
    AbstractAction pauseButtonAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            pauseButton.doClick();
        }
    };
    AbstractAction stopButtonAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            stopButton.doClick();
        }
    };
    AbstractAction hideCheckAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            hideSliderCheckBox.doClick();
        }
    };
    AbstractAction keyAction_item_start = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (fullScreen) {
                startButton.doClick();
                item_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
                item_pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_pause");
            }
        }
    };
    AbstractAction keyAction_item_pause = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (fullScreen) {
                pauseButton.doClick();
            }
        }
    };
    AbstractAction keyAction_item_stop = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (fullScreen) {
                stopButton.doClick();
                item_pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
                item_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_start");
            }
        }
    };
    AbstractAction keyAction_item_left = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if ((timer != null) && (timer.isRunning())) {
                timer.stop();
            }
            if ((sliderScroolForFlashedWords != null) && (sliderScroolForFlashedWords.getMaximum() > 0)) {
                int scrollableIncrement = sliderScroolForFlashedWords.getUnitIncrement();
                int value = sliderScroolForFlashedWords.getValue();
                value -= scrollableIncrement;
                sliderScroolForFlashedWords.setValue(value);
            }
        }
    };
    AbstractAction keyAction_item_left_released = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if ((timer != null) && (!timer.isRunning()) && (!inPauseState) && (!inStopState)) {
                timer.start();
            }
        }
    };
    AbstractAction keyAction_item_right = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if ((timer != null) && (timer.isRunning())) {
                timer.stop();
            }
            if ((sliderScroolForFlashedWords != null) && (sliderScroolForFlashedWords.getMaximum() > 0)) {
                int scrollableIncrement = sliderScroolForFlashedWords.getUnitIncrement();
                int value = sliderScroolForFlashedWords.getValue();
                value += scrollableIncrement;
                sliderScroolForFlashedWords.setValue(value);
            }
        }
    };
    AbstractAction keyAction_item_right_released = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if ((timer != null) && (!timer.isRunning()) && (!inPauseState) && (!inStopState)) {
                timer.start();
            }
        }
    };
    AbstractAction keyAction_item_exitFullScreen = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (fullScreen) {
                item_fullscreen.doClick();
            }
        }
    };
    AbstractAction keyAction_item_stopStartIncreaser = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (mode) {
                if (checkBox_timerIncreaser.isSelected()) {
                    checkBox_timerIncreaser.setSelected(false);
                    item_stop_start_increaser.setText("Start gradual acceleration");
                } else {
                    checkBox_timerIncreaser.setSelected(true);
                    item_stop_start_increaser.setText("Stop gradual acceleration");

                }
            }
        }
    };
    FocusListener FocusManager = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            openFile.openFileAction.setEnabled(false);
            startButtonAction.setEnabled(false);
            pauseButtonAction.setEnabled(false);
            stopButtonAction.setEnabled(false);
            hideCheckAction.setEnabled(false);
            keyAction_item_stopStartIncreaser.setEnabled(false);

            if (!inPauseState) {
                pauseButton.doClick();
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            openFile.openFileAction.setEnabled(true);
            startButtonAction.setEnabled(true);
            pauseButtonAction.setEnabled(true);
            stopButtonAction.setEnabled(true);
            hideCheckAction.setEnabled(true);
            keyAction_item_stopStartIncreaser.setEnabled(true);
        }
    };
    ItemListener checkBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getItemSelectable();
            if (source == hideSliderCheckBox) {
                sliderScroolForFlashedWords.setVisible(false);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                sliderScroolForFlashedWords.setVisible(true);
            }
        }
    };
    ItemListener checkBoxIncreaseListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getItemSelectable();
            if (source == checkBox_timerIncreaser) {
                allowIncreaserTimer = true;
                if ((booleanAllFields) && (!field_increaserEvery.getText().equals("") && (!field_increaserAmount.getText().equals("")) && (timer.isRunning()))) {
                    settingIncreasingTime();
                    setUpTimerIncreaser();
                    setDelayIncreaser();
                    startTimerIncreaser();
                }
                item_stop_start_increaser.setText("Stop gradual acceleration");
                label_increaserEvery.setEnabled(true);
                field_increaserEvery.setEnabled(true);
                label_increaserAmount.setEnabled(true);
                field_increaserAmount.setEnabled(true);
                checkBox_speedMaxLimit.setEnabled(true);
                if (checkBox_speedMaxLimit.isSelected()) {
                    label_speedMaxLimit.setEnabled(true);
                    field_speedMaxLimit.setEnabled(true);
                    boolSpeedMaxLimit = true;
                }
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                allowIncreaserTimer = false;
                item_stop_start_increaser.setText("Start gradual acceleration");
                label_increaserEvery.setEnabled(false);
                field_increaserEvery.setEnabled(false);
                label_increaserAmount.setEnabled(false);
                field_increaserAmount.setEnabled(false);
                checkBox_speedMaxLimit.setEnabled(false);
                label_speedMaxLimit.setEnabled(false);
                field_speedMaxLimit.setEnabled(false);
                boolSpeedMaxLimit = false;
            }
        }
    };
    ItemListener checkBoxSpeedMaxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getItemSelectable();
            if (source == checkBox_speedMaxLimit) {
                boolSpeedMaxLimit = true;
                label_speedMaxLimit.setEnabled(true);
                field_speedMaxLimit.setEnabled(true);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                boolSpeedMaxLimit = false;
                increaseContinue = true;
                startTimerIncreaser();
                label_speedMaxLimit.setEnabled(false);
                field_speedMaxLimit.setEnabled(false);
            }
        }
    };
    ActionListener eventWatcher = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == startButton) {
                listOfFlashedWordsIndexer = 0;
                takeInputTextArea();
                if (booleanAllFields) {
                    setStartEnabled(false);

                    docOrFlashedWordsChange = false;
                    field_timeBased_changed = false;

                    inStopState = false;
                    inPauseState = false;

                    item_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
                    item_pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_pause");

                    setStopEnabled(true);
                    setPauseEnabled(true);
                } else {
                    // stopButton.setEnabled(false);
                    setStopEnabled(false);
                    //  pauseButton.setEnabled(false);
                    setPauseEnabled(false);
                    sentencesArray = null;
                    if (listOfFlashedWordsFromSentenceTokensArray != null) {
                        listOfFlashedWordsFromSentenceTokensArray.clear();
                    }
                    if (listChunks_of_Strig != null) {
                        listChunks_of_Strig.clear();
                    }
                    listOfFlashedWordsIndexer = 0;
                    sliderScroolForFlashedWords.setValues(0, 0, 0, 0);
                }
            }
            if (source == stopButton) {
                item_pause.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
                item_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "item_start");
                ///
                inStopState = true;
                pauseIndexboolean = false;
                pauseIndex = 0;
                //  stopButton.setEnabled(false);
                setStopEnabled(false);
                // pauseButton.setEnabled(false);
                setPauseEnabled(false);
                //  startButton.setEnabled(true);
                setStartEnabled(true);
                pauseButton.setActionCommand("Pause");
                //pauseButton.setText("Pause");
                setPauseText("Pause");
                label.setText("");

                if (timer != null) {
                    sliderScroolForFlashedWords.setValue(0);
                    timer.stop();
                    stopTimerIncreaser();
                    // startButton.setEnabled(true);
                    setStartEnabled(true);
                }
            }
            if (source == pauseButton) {
                if (e.getActionCommand().equals("Pause")) {
                    inPauseState = true;
                    pauseIndexboolean = true;
                    pauseIndex = listOfFlashedWordsIndexer;
                    /////
                    // startButton.setEnabled(false);
                    setStartEnabled(false);
                    // pauseButton.setText("Resume");
                    setPauseText("Resume");
                    pauseButton.setActionCommand("Resume");
                    sliderScroolForFlashedWords.setValue(listOfFlashedWordsIndexer);
                    timer.stop();
                    stopTimerIncreaser();
                }
                if (e.getActionCommand().equals("Resume") && checkFields()) {
                    inPauseState = false;
                    if (!(textArea.getText().length() >= 1)) {
                        timer.stop();
                        stopTimerIncreaser();
                        // startButton.setEnabled(true);
                        setStartEnabled(true);
                        // stopButton.setEnabled(false);
                        setStopEnabled(false);
                        //  pauseButton.setText("Pause");
                        setPauseText("Pause");
                        pauseButton.setActionCommand("Pause");
                        // pauseButton.setEnabled(false);
                        setPauseEnabled(false);
                        listOfFlashedWordsIndexer = 0;
                        sentencesArray = null;
                        if (listOfFlashedWordsFromSentenceTokensArray != null) {
                            listOfFlashedWordsFromSentenceTokensArray.clear();
                        }
                        if (listChunks_of_Strig != null) {
                            listChunks_of_Strig.clear();
                        }
                        sliderScroolForFlashedWords.setValues(0, 0, 0, 0);
                        label.setText("");
                        JOptionPane.showMessageDialog(frame, "There are no Text!");
                    } else {
                        if ((docOrFlashedWordsChange) || (field_timeBased_changed)) {
                            if (docOrFlashedWordsChange) {
                                // pauseButton.setText("Pause");
                                setPauseText("Pause");
                                pauseButton.setActionCommand("Pause");
                                takeInputTextArea();
                                //
                                docOrFlashedWordsChange = false;
                                field_timeBased_changed = false;
                                System.out.println(testInt + " On resume takeInputTextArea called");
                                listOfFlashedWordsIndexer = 0;
                                pauseIndexboolean = false;
                                setFlashTime_ModeBased();
                                settingIncreasingTime();
                                setUpTimerIncreaser();
                                setDelayFlashedTime();
                                setDelayIncreaser();
                                timer.start();
                                startTimerIncreaser();
                            } else {
                                testInt++;
                                field_timeBased_changed = false;
                                //  pauseButton.setText("Pause");
                                setPauseText("Pause");
                                pauseButton.setActionCommand("Pause");
                                System.out.println(testInt + " On resume only time changed");
                                setFlashTime_ModeBased();
                                settingIncreasingTime();
                                setUpTimerIncreaser();
                                setDelayFlashedTime();
                                setDelayIncreaser();
                                timer.start();
                                startTimerIncreaser();
                            }
                        } else {
                            testInt++;
                            System.out.println(testInt + " On resum No thing changed");
                            // pauseButton.setText("Pause");
                            setPauseText("Pause");
                            pauseButton.setActionCommand("Pause");
                            setFlashTime_ModeBased();
                            settingIncreasingTime();
                            setUpTimerIncreaser();
                            setDelayFlashedTime();
                            setDelayIncreaser();
                            timer.start();
                            startTimerIncreaser();
                        }
                    }
                }
            }
            if (source == default_Item) {
                colorChanger.colorChoBackground.setColor(Color.WHITE);
                colorChanger.colorChoText.setColor(Color.BLACK);
                /////
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
                combo_fontSize.setSelectedItem(26);
                combo_fontTypes.setSelectedItem("Serif");
                label.setFont(new Font(fontType, Font.PLAIN, fontSize));
            }
            if (source == normal_item) {
                if (!mode) {
                    mode = true;
                    frame.setTitle("Speed Reading - Mode: Normal");
                    normal_item.setText("<HTML><b><font color=\"red\">> </font></b><font color=\"black\"/>Normal</font></HTML>");
                    trainer_item.setText("<HTML>&nbsp;&nbsp;&nbsp;<font color=\"black\"/>Trainer</font></HTML>");

                    if ((timer != null) && (timer.isRunning())) {
                        pauseButton.doClick();
                    } else {
                        if (endOfSlider) {
                            stopButton.doClick();
                        }
                    }

                    setTimerSwitch();
                    widgetPanel_1.remove(lb_duration);
                    widgetPanel_1.remove(field_duration);
                    widgetPanel_1.remove(lb_delay);
                    widgetPanel_1.remove(field_delay);
                    widgetPanel_1.remove(lb_yourSpeed);
                    widgetPanel_1.remove(lb_realTimeSpeed);

                    toolbar.add(checkBox_timerIncreaser, 2);
                    toolbar.add(checkBox_speedMaxLimit, 3);
                    widgetPanel_1.add(label_wordsPerMinute, 0);
                    widgetPanel_1.add(field_wordsPerMinute, 1);
                    widgetPanel_1.add(label_increaserEvery, 4);
                    widgetPanel_1.add(field_increaserEvery, 5);
                    widgetPanel_1.add(label_increaserAmount, 6);
                    widgetPanel_1.add(field_increaserAmount, 7);
                    widgetPanel_1.add(label_speedMaxLimit, 8);
                    widgetPanel_1.add(field_speedMaxLimit, 9);

                    widgetPanel_1.revalidate();
                    widgetPanel_1.repaint();
                    frame.revalidate();
                    frame.repaint();
                }
            }
            if (source == trainer_item) {
                if (mode) {
                    stopTimerIncreaser();
                    mode = false;
                    frame.setTitle("Speed Reading - Mode: Trainer");
                    trainer_item.setText("<HTML><b><font color=\"red\">> </font></b><font color=\"black\"/>Trainer</font></HTML>");
                    normal_item.setText("<HTML>&nbsp;&nbsp;&nbsp;<font color=\"black\"/>Normal</font></HTML>");

                    if ((timer != null) && (timer.isRunning())) {
                        pauseButton.doClick();
                    } else {
                        if (endOfSlider) {
                            stopButton.doClick();
                        }
                    }

                    setTimerSwitch();
                    toolbar.remove(checkBox_timerIncreaser);
                    toolbar.remove(checkBox_speedMaxLimit);
                    widgetPanel_1.remove(label_wordsPerMinute);
                    widgetPanel_1.remove(field_wordsPerMinute);
                    widgetPanel_1.remove(label_increaserEvery);
                    widgetPanel_1.remove(field_increaserEvery);
                    widgetPanel_1.remove(label_increaserAmount);
                    widgetPanel_1.remove(field_increaserAmount);
                    widgetPanel_1.remove(label_speedMaxLimit);
                    widgetPanel_1.remove(field_speedMaxLimit);

                    widgetPanel_1.add(lb_duration, 2);
                    widgetPanel_1.add(field_duration, 3);
                    widgetPanel_1.add(lb_delay, 4);
                    widgetPanel_1.add(field_delay, 5);
                    widgetPanel_1.add(lb_yourSpeed, 6);
                    widgetPanel_1.add(lb_realTimeSpeed, 7);

                    widgetPanel_1.revalidate();
                    widgetPanel_1.repaint();
                    frame.revalidate();
                    frame.repaint();
                }
            }
            if (source == btn_fullscreen) {
                if (!fullScreen) {
                    goFullScreen();
                } else {
                    ungoFullScreen();
                }
            }
            if (source == item_fullscreen) {
                if (!fullScreen) {
                    goFullScreen();
                } else {
                    ungoFullScreen();
                }
            }
            if (source == item_start) {
                startButton.doClick();
            }
            if (source == item_pause) {
                pauseButton.doClick();
            }
            if (source == item_stop) {
                stopButton.doClick();
            }
            if (source == item_stop_start_increaser) {
                if (checkBox_timerIncreaser.isSelected()) {
                    checkBox_timerIncreaser.setSelected(false);
                    item_stop_start_increaser.setText("Start gradual acceleration");
                } else {
                    checkBox_timerIncreaser.setSelected(true);
                    item_stop_start_increaser.setText("Stop gradual acceleration");

                }
            }
        }
    };

    private class itemListenerClass implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == combo_fontSize) {
                fontSize = (Integer) combo_fontSize.getSelectedItem();
                label.setFont(new Font(fontType, Font.PLAIN, fontSize));
            }

            if (e.getSource() == combo_fontTypes) {
                fontType = (String) combo_fontTypes.getSelectedItem();
                label.setFont(new Font(fontType, Font.PLAIN, fontSize));
            }
        }
    }
    AdjustmentListener sliderScrollListener = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            listOfFlashedWordsIndexer = e.getValue();

            if ((e.getValue() == sliderScroolForFlashedWords.getMaximum()) || (inStopState)) {
                // pauseButton.setEnabled(false);
                setPauseEnabled(false);
                endOfSlider = true;
            } else {
                //  pauseButton.setEnabled(true);
                setPauseEnabled(true);
                endOfSlider = false;
            }

            if ((e.getAdjustmentType() == AdjustmentEvent.TRACK) && (pauseIndexboolean)) {
                pauseIndex = sliderScroolForFlashedWords.getValue();
            }

            if (getListOfFlashedWordsFromSentenceTokensArray() != null) {
                if (listOfFlashedWordsIndexer < getListOfFlashedWordsFromSentenceTokensArray().size()) {
                    label.setText(getListOfFlashedWordsFromSentenceTokensArray().get(listOfFlashedWordsIndexer));
                }
                if ((e.getValueIsAdjusting()) && (timerStopped) && (!inPauseState) && (e.getValue() == sliderScroolForFlashedWords.getMaximum())) {
                    if (timer != null) {
                        timer.start();
                        startTimerIncreaser();
                        timerStopped = false;
                    }
                }
            }
        }
    };

    class DocumentUpdateListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            Document doc = (Document) e.getDocument();
            if (doc == textArea.getDocument()) {
                // System.out.println("textfield insert update");
                docOrFlashedWordsChange = true;
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            Document doc = (Document) e.getDocument();
            if (doc == textArea.getDocument()) {
                // System.out.println("textfield  remove update");
                docOrFlashedWordsChange = true;
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            Document doc = (Document) e.getDocument();
            if (doc == textArea.getDocument()) {
                //   System.out.println("textfield  change update");
                docOrFlashedWordsChange = true;
            }
        }
    }

    private class FlahedWordsChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Object source = evt.getSource();
            if (source == field_flashedWords) {
                docOrFlashedWordsChange = true;
            }
        }
    }

    private class FieldChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            // registered fields: field_wordsPerMinute, field_delay, field_duration
            field_timeBased_changed = true;
        }
    }

    private class MouseLisenerForSlider extends MouseAdapter {

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            Object source = e.getSource();
            if ((source == sliderScroolForFlashedWords) || (source == sliderLeftButton) || (source == sliderRightButton)) {
                if (timer != null) {
                    timer.stop();
                    stopTimerIncreaser();
                }
            }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            Object source = e.getSource();
            if ((source == sliderScroolForFlashedWords) || (source == sliderLeftButton) || (source == sliderRightButton)) {
                if ((timer != null) && (mouseReleased_OK) && (!inPauseState) && (!inStopState)) {
                    timer.start();
                    startTimerIncreaser();
                    sliderScroolForFlashedWords.requestFocusInWindow();
                }
            }
        }
    }

    private void setStartEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
        item_start.setEnabled(enabled);
    }

    private void setPauseEnabled(boolean enabled) {
        pauseButton.setEnabled(enabled);
        item_pause.setEnabled(enabled);
    }

    private void setStopEnabled(boolean enabled) {
        stopButton.setEnabled(enabled);
        item_stop.setEnabled(enabled);
    }

    private void setPauseText(String test) {
        pauseButton.setText(test);
        item_pause.setText(test);
    }

    private void goFullScreen() {
        Window w = SwingUtilities.windowForComponent(label);
        if (w instanceof JFrame) {
            frame = (JFrame) w;
            frame.dispose();
            frame.setUndecorated(true);
            frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(w);
            previousContentPane = frame.getContentPane();
            frame.setContentPane(label);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
            label.setFocusable(false);
            fullScreen = true;
            item_fullscreen.setText("Exit full screen");
        }
    }

    private void ungoFullScreen() {
        Window w = SwingUtilities.windowForComponent(label);
        if (w instanceof JFrame) {
            item_fullscreen.setText("Full screen");
            frame = (JFrame) w;
            frame.dispose();
            frame.setUndecorated(false);
            frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
            frame.setContentPane(previousContentPane);
            upperPanel.add(label);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
            label.setFocusable(false);
            fullScreen = false;
            frame.repaint();//Extra
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Speed Reading - Mode: Normal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(920, 440);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();
        int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
        frame.setLocation(windowX, windowY);
        frame.add(new SpeedReading(), BorderLayout.CENTER);
        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    private class StringInfo {

        private int Wcount;
        private String string;

        public StringInfo(int count, String str) {
            Wcount = count;
            string = str;
        }

        public int getWcount() {
            return Wcount;
        }

        public String getString() {
            return string;
        }
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
