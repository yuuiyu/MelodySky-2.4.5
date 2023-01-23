//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMJSWindow extends nsISupports
{
    public static final String NS_IDOMJSWINDOW_IID = "{c8188620-1dd1-11b2-bc88-df8440498add}";
    
    void dump(final String p0);
    
    int setTimeout();
    
    int setInterval();
    
    void clearTimeout();
    
    void clearInterval();
    
    void setResizable(final boolean p0);
    
    void captureEvents(final int p0);
    
    void releaseEvents(final int p0);
    
    void routeEvent(final nsIDOMEvent p0);
    
    void enableExternalCapture();
    
    void disableExternalCapture();
    
    String prompt();
    
    nsIDOMWindow open();
    
    nsIDOMWindow openDialog();
    
    nsIDOMWindow getFrames();
    
    boolean find();
}
