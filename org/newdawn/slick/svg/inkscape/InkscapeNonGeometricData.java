//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.svg.*;
import org.w3c.dom.*;

public class InkscapeNonGeometricData extends NonGeometricData
{
    private Element element;
    
    public InkscapeNonGeometricData(final String metaData, final Element element) {
        super(metaData);
        this.element = element;
    }
    
    @Override
    public String getAttribute(final String attribute) {
        String result = super.getAttribute(attribute);
        if (result == null) {
            result = this.element.getAttribute(attribute);
        }
        return result;
    }
    
    public Element getElement() {
        return this.element;
    }
}
