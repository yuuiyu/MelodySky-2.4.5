//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLDocument extends nsISupports
{
    public static final String NS_IDOMNSHTMLDOCUMENT_IID = "{79beb289-3644-4b54-9432-9fb993945629}";
    
    int getWidth();
    
    int getHeight();
    
    String getAlinkColor();
    
    void setAlinkColor(final String p0);
    
    String getLinkColor();
    
    void setLinkColor(final String p0);
    
    String getVlinkColor();
    
    void setVlinkColor(final String p0);
    
    String getBgColor();
    
    void setBgColor(final String p0);
    
    String getFgColor();
    
    void setFgColor(final String p0);
    
    String getDomain();
    
    void setDomain(final String p0);
    
    nsIDOMHTMLCollection getEmbeds();
    
    String getSelection();
    
    nsIDOMDocument open(final String p0, final boolean p1);
    
    void write();
    
    void writeln();
    
    void clear();
    
    void captureEvents(final int p0);
    
    void releaseEvents(final int p0);
    
    void routeEvent(final nsIDOMEvent p0);
    
    String getCompatMode();
    
    nsIDOMHTMLCollection getPlugins();
    
    String getDesignMode();
    
    void setDesignMode(final String p0);
    
    boolean execCommand(final String p0, final boolean p1, final String p2);
    
    boolean execCommandShowHelp(final String p0);
    
    boolean queryCommandEnabled(final String p0);
    
    boolean queryCommandIndeterm(final String p0);
    
    boolean queryCommandState(final String p0);
    
    boolean queryCommandSupported(final String p0);
    
    String queryCommandText(final String p0);
    
    String queryCommandValue(final String p0);
}
