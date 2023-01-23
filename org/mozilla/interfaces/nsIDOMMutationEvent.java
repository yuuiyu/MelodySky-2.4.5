//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMMutationEvent extends nsIDOMEvent
{
    public static final String NS_IDOMMUTATIONEVENT_IID = "{8e440d86-886a-4e76-9e59-c13b939c9a4b}";
    public static final int MODIFICATION = 1;
    public static final int ADDITION = 2;
    public static final int REMOVAL = 3;
    
    nsIDOMNode getRelatedNode();
    
    String getPrevValue();
    
    String getNewValue();
    
    String getAttrName();
    
    int getAttrChange();
    
    void initMutationEvent(final String p0, final boolean p1, final boolean p2, final nsIDOMNode p3, final String p4, final String p5, final String p6, final int p7);
}
