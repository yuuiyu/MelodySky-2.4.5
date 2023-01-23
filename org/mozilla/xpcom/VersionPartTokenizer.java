//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import java.util.*;

class VersionPartTokenizer implements Enumeration
{
    String part;
    
    public VersionPartTokenizer(final String part) {
        this.part = part;
    }
    
    @Override
    public boolean hasMoreElements() {
        return this.part.length() != 0;
    }
    
    public boolean hasMoreTokens() {
        return this.part.length() != 0;
    }
    
    @Override
    public Object nextElement() {
        if (this.part.matches("[\\+\\-]?[0-9].*")) {
            int n = 0;
            if (this.part.charAt(0) == '+' || this.part.charAt(0) == '-') {
                n = 1;
            }
            while (n < this.part.length() && Character.isDigit(this.part.charAt(n))) {
                ++n;
            }
            final String substring = this.part.substring(0, n);
            this.part = this.part.substring(n);
            return substring;
        }
        int n2;
        for (n2 = 0; n2 < this.part.length() && !Character.isDigit(this.part.charAt(n2)); ++n2) {}
        final String substring2 = this.part.substring(0, n2);
        this.part = this.part.substring(n2);
        return substring2;
    }
    
    public String nextToken() {
        return (String)this.nextElement();
    }
    
    public String getRemainder() {
        return this.part;
    }
}
