//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFind extends nsISupports
{
    public static final String NS_IFIND_IID = "{75125d55-37ee-4575-b9b5-f33bfa68c2a1}";
    
    boolean getFindBackwards();
    
    void setFindBackwards(final boolean p0);
    
    boolean getCaseSensitive();
    
    void setCaseSensitive(final boolean p0);
    
    nsISupports getWordBreaker();
    
    void setWordBreaker(final nsISupports p0);
    
    nsIDOMRange find(final String p0, final nsIDOMRange p1, final nsIDOMRange p2, final nsIDOMRange p3);
}
