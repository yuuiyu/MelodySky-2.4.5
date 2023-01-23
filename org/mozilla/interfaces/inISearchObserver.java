//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inISearchObserver extends nsISupports
{
    public static final String INISEARCHOBSERVER_IID = "{46226d9b-e398-4106-8d9b-225d4d0589f5}";
    public static final short SUCCESS = 1;
    public static final short INTERRUPTED = 2;
    public static final short ERROR = 3;
    
    void onSearchStart(final inISearchProcess p0);
    
    void onSearchResult(final inISearchProcess p0);
    
    void onSearchEnd(final inISearchProcess p0, final short p1);
    
    void onSearchError(final inISearchProcess p0, final String p1);
}
