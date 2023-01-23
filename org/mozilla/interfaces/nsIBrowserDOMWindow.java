//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBrowserDOMWindow extends nsISupports
{
    public static final String NS_IBROWSERDOMWINDOW_IID = "{af25c296-aaec-4f7f-8885-dd37a1cc0a13}";
    public static final short OPEN_DEFAULTWINDOW = 0;
    public static final short OPEN_CURRENTWINDOW = 1;
    public static final short OPEN_NEWWINDOW = 2;
    public static final short OPEN_NEWTAB = 3;
    public static final short OPEN_EXTERNAL = 1;
    public static final short OPEN_NEW = 2;
    
    nsIDOMWindow openURI(final nsIURI p0, final nsIDOMWindow p1, final short p2, final short p3);
    
    boolean isTabContentWindow(final nsIDOMWindow p0);
}
