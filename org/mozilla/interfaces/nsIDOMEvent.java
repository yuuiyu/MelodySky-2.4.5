//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMEvent extends nsISupports
{
    public static final String NS_IDOMEVENT_IID = "{a66b7b80-ff46-bd97-0080-5f8ae38add32}";
    public static final int CAPTURING_PHASE = 1;
    public static final int AT_TARGET = 2;
    public static final int BUBBLING_PHASE = 3;
    
    String getType();
    
    nsIDOMEventTarget getTarget();
    
    nsIDOMEventTarget getCurrentTarget();
    
    int getEventPhase();
    
    boolean getBubbles();
    
    boolean getCancelable();
    
    double getTimeStamp();
    
    void stopPropagation();
    
    void preventDefault();
    
    void initEvent(final String p0, final boolean p1, final boolean p2);
}
