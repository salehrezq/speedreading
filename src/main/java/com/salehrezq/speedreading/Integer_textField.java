/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salehrezq.speedreading;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFormattedTextField;

/**
 *
 * @author S
 */
public class Integer_textField extends JFormattedTextField implements FocusListener {

    String oldString = null;
    String newString = null;
    boolean validInteger;
    boolean zeroAllowed;

    public Integer_textField(int columne) {
        super.setColumns(columne);
        this.addFocusListener(this);
    }

    public void setAllowZeroValue(boolean isAllowed) {
        this.zeroAllowed = isAllowed;
    }

    private void isDigitString() {
        StringBuilder strResult = new StringBuilder();
        String strInput = this.getText();
        int i;
        for (i = 0; i < strInput.length(); i++) {
            if (Character.isDigit(strInput.charAt(i))) {
                strResult.append(strInput.charAt(i));
            } else {
                break;
            }
        }
        if (strResult.length() > 0) {
            validInteger = true;
            newString = strResult.toString();

        } else {
            validInteger = false;
        }
    }

    private void isPostitve() {
        if (validInteger) {
            int integer = Integer.parseInt(newString);
            if (zeroAllowed) {
                if (integer >= 0) {
                    validInteger = true;
                } else {
                    validInteger = false;
                }
            } else {
                if (integer > 0) {
                    validInteger = true;
                } else {
                    validInteger = false;
                }
            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Not required.
    }

    @Override
    public void focusLost(FocusEvent e) {
        isDigitString();
        isPostitve();

        if (this.validInteger) {
            oldString = newString;
            this.setText(newString);
        } else {
            this.setText(oldString);
        }
    }
}
