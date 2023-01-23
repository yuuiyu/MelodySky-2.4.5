//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWindowMediator extends nsISupports
{
    public static final String NS_IWINDOWMEDIATOR_IID = "{0659cb81-faad-11d2-8e19-b206620a657c}";
    public static final long zLevelTop = 1L;
    public static final long zLevelBottom = 2L;
    public static final long zLevelBelow = 3L;
    
    nsISimpleEnumerator getEnumerator(final String p0);
    
    nsISimpleEnumerator getXULWindowEnumerator(final String p0);
    
    nsISimpleEnumerator getZOrderDOMWindowEnumerator(final String p0, final boolean p1);
    
    nsISimpleEnumerator getZOrderXULWindowEnumerator(final String p0, final boolean p1);
    
    nsIDOMWindowInternal getMostRecentWindow(final String p0);
    
    void addListener(final nsIWindowMediatorListener p0);
    
    void removeListener(final nsIWindowMediatorListener p0);
}
