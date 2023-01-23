//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.*;
import java.io.*;
import java.util.*;
import java.text.*;

public final class Compatibility
{
    private static ResourceBundle msgs;
    
    public static int ceil(final int p, final int q) {
        return (int)Math.ceil(p / (float)q);
    }
    
    public static int round(final int p, final int q) {
        return Math.round(p / (float)q);
    }
    
    public static int pow2(final int n) {
        if (n >= 1 && n <= 30) {
            return 2 << n - 1;
        }
        if (n != 0) {
            SWT.error(6);
        }
        return 1;
    }
    
    public static void exec(final String[] prog, final String[] envp, final String workingDir) throws IOException {
        Runtime.getRuntime().exec(prog, null, (workingDir != null) ? new File(workingDir) : null);
    }
    
    public static String getMessage(final String key) {
        String answer = key;
        if (key == null) {
            SWT.error(4);
        }
        if (Compatibility.msgs == null) {
            try {
                Compatibility.msgs = ResourceBundle.getBundle("org.eclipse.swt.internal.SWTMessages");
            }
            catch (MissingResourceException ex) {
                answer = key + " (no resource bundle)";
            }
        }
        if (Compatibility.msgs != null) {
            try {
                answer = Compatibility.msgs.getString(key);
            }
            catch (MissingResourceException ex2) {}
        }
        return answer;
    }
    
    public static String getMessage(final String key, final Object[] args) {
        String answer = key;
        if (key == null || args == null) {
            SWT.error(4);
        }
        if (Compatibility.msgs == null) {
            try {
                Compatibility.msgs = ResourceBundle.getBundle("org.eclipse.swt.internal.SWTMessages");
            }
            catch (MissingResourceException ex) {
                answer = key + " (no resource bundle)";
            }
        }
        if (Compatibility.msgs != null) {
            try {
                final MessageFormat formatter = new MessageFormat("");
                formatter.applyPattern(Compatibility.msgs.getString(key));
                answer = formatter.format(args);
            }
            catch (MissingResourceException ex2) {}
        }
        return answer;
    }
    
    static {
        Compatibility.msgs = null;
    }
}
