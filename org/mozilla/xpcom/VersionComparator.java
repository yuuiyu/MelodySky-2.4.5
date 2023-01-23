//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import org.mozilla.interfaces.*;
import java.util.*;

public class VersionComparator implements nsIVersionComparator
{
    public nsISupports queryInterface(final String s) {
        return Mozilla.queryInterface((nsISupports)this, s);
    }
    
    public int compare(final String s, final String s2) {
        String versionPart = s;
        String versionPart2 = s2;
        int compareVersionPart;
        do {
            final VersionPart versionPart3 = new VersionPart((l)null);
            final VersionPart versionPart4 = new VersionPart((l)null);
            versionPart = parseVersionPart(versionPart, versionPart3);
            versionPart2 = parseVersionPart(versionPart2, versionPart4);
            compareVersionPart = this.compareVersionPart(versionPart3, versionPart4);
            if (compareVersionPart != 0) {
                break;
            }
        } while (versionPart != null || versionPart2 != null);
        return compareVersionPart;
    }
    
    private static String parseVersionPart(final String s, final VersionPart versionPart) {
        if (s == null || s.length() == 0) {
            return s;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ".");
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.equals("*")) {
            versionPart.numA = Integer.MAX_VALUE;
            versionPart.strB = "";
        }
        else {
            final VersionPartTokenizer versionPartTokenizer = new VersionPartTokenizer(nextToken);
            try {
                versionPart.numA = Integer.parseInt(versionPartTokenizer.nextToken());
            }
            catch (NumberFormatException ex) {
                versionPart.numA = 0;
            }
            if (versionPartTokenizer.hasMoreElements()) {
                final String nextToken2 = versionPartTokenizer.nextToken();
                if (nextToken2.charAt(0) == '+') {
                    ++versionPart.numA;
                    versionPart.strB = "pre";
                }
                else {
                    versionPart.strB = nextToken2;
                    if (versionPartTokenizer.hasMoreTokens()) {
                        try {
                            versionPart.numC = Integer.parseInt(versionPartTokenizer.nextToken());
                        }
                        catch (NumberFormatException ex2) {
                            versionPart.numC = 0;
                        }
                        if (versionPartTokenizer.hasMoreTokens()) {
                            versionPart.extraD = versionPartTokenizer.getRemainder();
                        }
                    }
                }
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            return s.substring(nextToken.length() + 1);
        }
        return null;
    }
    
    private int compareVersionPart(final VersionPart versionPart, final VersionPart versionPart2) {
        final int compareInt = this.compareInt(versionPart.numA, versionPart2.numA);
        if (compareInt != 0) {
            return compareInt;
        }
        final int compareString = this.compareString(versionPart.strB, versionPart2.strB);
        if (compareString != 0) {
            return compareString;
        }
        final int compareInt2 = this.compareInt(versionPart.numC, versionPart2.numC);
        if (compareInt2 != 0) {
            return compareInt2;
        }
        return this.compareString(versionPart.extraD, versionPart2.extraD);
    }
    
    private int compareInt(final int n, final int n2) {
        return n - n2;
    }
    
    private int compareString(final String s, final String s2) {
        if (s == null) {
            return (s2 != null) ? 1 : 0;
        }
        if (s2 == null) {
            return -1;
        }
        return s.compareTo(s2);
    }
    
    private class VersionPart
    {
        int numA;
        String strB;
        int numC;
        String extraD;
        
        private VersionPart() {
            this.numA = 0;
            this.numC = 0;
        }
    }
}
