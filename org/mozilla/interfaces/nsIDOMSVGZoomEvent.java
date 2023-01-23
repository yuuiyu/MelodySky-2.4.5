//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGZoomEvent extends nsIDOMUIEvent
{
    public static final String NS_IDOMSVGZOOMEVENT_IID = "{339a8c7a-552e-4cbc-8d96-8370a3939358}";
    
    nsIDOMSVGRect getZoomRectScreen();
    
    float getPreviousScale();
    
    nsIDOMSVGPoint getPreviousTranslate();
    
    float getNewScale();
    
    nsIDOMSVGPoint getNewTranslate();
}
