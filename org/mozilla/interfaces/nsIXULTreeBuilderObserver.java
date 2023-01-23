//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULTreeBuilderObserver extends nsISupports
{
    public static final String NS_IXULTREEBUILDEROBSERVER_IID = "{a5480e0d-ac7c-42e5-aca5-d7f0bbffa207}";
    public static final int DROP_BEFORE = -1;
    public static final int DROP_ON = 0;
    public static final int DROP_AFTER = 1;
    
    boolean canDrop(final int p0, final int p1);
    
    void onDrop(final int p0, final int p1);
    
    void onToggleOpenState(final int p0);
    
    void onCycleHeader(final String p0, final nsIDOMElement p1);
    
    void onCycleCell(final int p0, final String p1);
    
    void onSelectionChanged();
    
    void onPerformAction(final String p0);
    
    void onPerformActionOnRow(final String p0, final int p1);
    
    void onPerformActionOnCell(final String p0, final int p1, final String p2);
}
