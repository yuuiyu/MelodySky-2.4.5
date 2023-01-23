//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHistory extends nsISupports
{
    public static final String NS_IDOMHISTORY_IID = "{896d1d20-b4c4-11d2-bd93-00805f8ae3f4}";
    
    int getLength();
    
    String getCurrent();
    
    String getPrevious();
    
    String getNext();
    
    void back();
    
    void forward();
    
    String item(final long p0);
}
