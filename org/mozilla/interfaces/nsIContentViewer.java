//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentViewer extends nsISupports
{
    public static final String NS_ICONTENTVIEWER_IID = "{6a7ddb40-8a9e-4576-8ad1-71c5641d8780}";
    
    nsISupports getContainer();
    
    void setContainer(final nsISupports p0);
    
    void loadStart(final nsISupports p0);
    
    void loadComplete(final long p0);
    
    boolean permitUnload();
    
    void pageHide(final boolean p0);
    
    void close(final nsISHEntry p0);
    
    void destroy();
    
    void stop();
    
    nsIDOMDocument getDOMDocument();
    
    void setDOMDocument(final nsIDOMDocument p0);
    
    void move(final int p0, final int p1);
    
    void show();
    
    void hide();
    
    boolean getEnableRendering();
    
    void setEnableRendering(final boolean p0);
    
    boolean getSticky();
    
    void setSticky(final boolean p0);
    
    boolean requestWindowClose();
    
    void open(final nsISupports p0);
    
    void clearHistoryEntry();
}
