/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salehrezq.speedreading;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author S
 */
public class ColorChanger implements ActionListener, ChangeListener {

    JColorChooser colorChoBackground;
    JColorChooser colorChoText;
    JDialog dialogBackground;
    JDialog dialogText;
    Color backgroundColor;
    Color textColor;

    public ColorChanger() {
        backgroundColor = new Color(255, 255, 255);
        textColor = new Color(0, 0, 0);
        SpeedReading.label.setBackground(backgroundColor);
        SpeedReading.label.setForeground(textColor);
        ////
        SpeedReading.backGroundColorItem.addActionListener(this);
        SpeedReading.backGroundColorItem.addChangeListener(this);
        //////////
        SpeedReading.textColorItem.addActionListener(this);
        SpeedReading.textColorItem.addChangeListener(this);

        colorChoBackground = new JColorChooser(SpeedReading.label.getBackground());
        colorChoText = new JColorChooser(SpeedReading.label.getForeground());

        dialogBackground = JColorChooser.createDialog(SpeedReading.backGroundColorItem,
                "Pick a Color",
                true, //modal
                colorChoBackground,
                this, //OK button handler
                null); //no CANCEL button handler

        dialogText = JColorChooser.createDialog(SpeedReading.textColorItem,
                "Pick a Color",
                true, //modal
                colorChoText,
                this, //OK button handler
                null); //no CANCEL button handler
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == SpeedReading.backGroundColorItem) {
            System.out.println("background color");
            dialogBackground.setVisible(true);
        }
        if (source == SpeedReading.textColorItem) {
            System.out.println("Text color");
            dialogText.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == SpeedReading.backGroundColorItem) {
            backgroundColor = colorChoBackground.getColor();
            SpeedReading.label.setBackground(backgroundColor);
        }
        if (source == SpeedReading.textColorItem) {
            textColor = colorChoText.getColor();
            SpeedReading.label.setForeground(textColor);
        }
    }
}
