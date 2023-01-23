//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocumentLoaderFactory extends nsISupports
{
    public static final String NS_IDOCUMENTLOADERFACTORY_IID = "{df15f850-5d98-11d4-9f4d-0010a4053fd0}";
    
    nsIContentViewer createInstance(final String p0, final nsIChannel p1, final nsILoadGroup p2, final String p3, final nsISupports p4, final nsISupports p5, final nsIStreamListener[] p6);
    
    nsIContentViewer createInstanceForDocument(final nsISupports p0, final nsISupports p1, final String p2);
    
    nsISupports createBlankDocument(final nsILoadGroup p0);
}
