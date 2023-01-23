//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTMLObjectResizeListener extends nsISupports
{
    public static final String NS_IHTMLOBJECTRESIZELISTENER_IID = "{27b00295-349c-429f-ad0c-87b859e77130}";
    
    void onStartResizing(final nsIDOMElement p0);
    
    void onEndResizing(final nsIDOMElement p0, final int p1, final int p2, final int p3, final int p4);
}
