//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDragService extends nsISupports
{
    public static final String NS_IDRAGSERVICE_IID = "{8b5314bb-db01-11d2-96ce-0060b0fb9956}";
    public static final int DRAGDROP_ACTION_NONE = 0;
    public static final int DRAGDROP_ACTION_COPY = 1;
    public static final int DRAGDROP_ACTION_MOVE = 2;
    public static final int DRAGDROP_ACTION_LINK = 4;
    
    void invokeDragSession(final nsIDOMNode p0, final nsISupportsArray p1, final nsIScriptableRegion p2, final long p3);
    
    nsIDragSession getCurrentSession();
    
    void startDragSession();
    
    void endDragSession();
}
