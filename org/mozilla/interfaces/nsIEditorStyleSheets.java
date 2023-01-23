//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorStyleSheets extends nsISupports
{
    public static final String NS_IEDITORSTYLESHEETS_IID = "{4805e682-49b9-11d3-9ce4-ed60bd6cb5bc}";
    
    void replaceStyleSheet(final String p0);
    
    void addStyleSheet(final String p0);
    
    void replaceOverrideStyleSheet(final String p0);
    
    void addOverrideStyleSheet(final String p0);
    
    void removeStyleSheet(final String p0);
    
    void removeOverrideStyleSheet(final String p0);
    
    void enableStyleSheet(final String p0, final boolean p1);
}
