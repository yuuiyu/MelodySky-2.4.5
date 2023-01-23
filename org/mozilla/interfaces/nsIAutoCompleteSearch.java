//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompleteSearch extends nsISupports
{
    public static final String NS_IAUTOCOMPLETESEARCH_IID = "{de8db85f-c1de-4d87-94ba-7844890f91fe}";
    
    void startSearch(final String p0, final String p1, final nsIAutoCompleteResult p2, final nsIAutoCompleteObserver p3);
    
    void stopSearch();
}
