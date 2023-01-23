//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScrollBoxObject extends nsISupports
{
    public static final String NS_ISCROLLBOXOBJECT_IID = "{56e2ada8-4631-11d4-ba11-001083023c1e}";
    
    void scrollTo(final int p0, final int p1);
    
    void scrollBy(final int p0, final int p1);
    
    void scrollByLine(final int p0);
    
    void scrollByIndex(final int p0);
    
    void scrollToLine(final int p0);
    
    void scrollToElement(final nsIDOMElement p0);
    
    void scrollToIndex(final int p0);
    
    void getPosition(final int[] p0, final int[] p1);
    
    void getScrolledSize(final int[] p0, final int[] p1);
    
    void ensureElementIsVisible(final nsIDOMElement p0);
    
    void ensureIndexIsVisible(final int p0);
    
    void ensureLineIsVisible(final int p0);
}
