/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salehrezq.speedreading;

import java.io.File;
import javax.swing.filechooser.*;

/* TextFilter.java is used by FileChooserDemo2.java. */
public class TextFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.txt)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Text Files";
    }

}
