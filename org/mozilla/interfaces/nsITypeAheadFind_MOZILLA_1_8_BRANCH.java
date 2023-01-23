//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITypeAheadFind_MOZILLA_1_8_BRANCH extends nsITypeAheadFind
{
    public static final String NS_ITYPEAHEADFIND_MOZILLA_1_8_BRANCH_IID = "{6e934f17-1975-49c2-880e-4f9fa79a1b2e}";
    
    void setSelectionModeAndRepaint(final short p0);
    
    void collapseSelection();
    
    nsIDOMElement getFoundEditable();
}
