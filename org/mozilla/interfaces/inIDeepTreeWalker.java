//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inIDeepTreeWalker extends nsIDOMTreeWalker
{
    public static final String INIDEEPTREEWALKER_IID = "{91fca0e9-99d6-406b-9d78-4c96f11e9ee4}";
    
    boolean getShowAnonymousContent();
    
    void setShowAnonymousContent(final boolean p0);
    
    boolean getShowSubDocuments();
    
    void setShowSubDocuments(final boolean p0);
    
    void init(final nsIDOMNode p0, final long p1);
}
