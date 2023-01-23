//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIASN1Sequence extends nsIASN1Object
{
    public static final String NS_IASN1SEQUENCE_IID = "{b6b957e6-1dd1-11b2-89d7-e30624f50b00}";
    
    nsIMutableArray getASN1Objects();
    
    void setASN1Objects(final nsIMutableArray p0);
    
    boolean getIsValidContainer();
    
    void setIsValidContainer(final boolean p0);
    
    boolean getIsExpanded();
    
    void setIsExpanded(final boolean p0);
}
