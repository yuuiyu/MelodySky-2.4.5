//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;
import org.eclipse.swt.graphics.*;

public class AccessibleTextAttributeEvent extends EventObject
{
    public int offset;
    public int start;
    public int end;
    public TextStyle textStyle;
    public String[] attributes;
    public String result;
    static final long serialVersionUID = 7131825608864332802L;
    
    public AccessibleTextAttributeEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleAttributeEvent { offset=" + this.offset + " start=" + this.start + " end=" + this.end + " textStyle=" + this.textStyle + " attributes=" + this.toAttributeString(this.attributes) + " result=" + this.result;
    }
    
    String toAttributeString(final String[] attributes) {
        if (attributes == null || attributes.length == 0) {
            return "" + attributes;
        }
        final StringBuilder attributeString = new StringBuilder();
        for (int i = 0; i < attributes.length; ++i) {
            attributeString.append(attributes[i]);
            attributeString.append((i % 2 == 0) ? ":" : ";");
        }
        return attributeString.toString();
    }
}
