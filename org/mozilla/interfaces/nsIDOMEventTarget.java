//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMEventTarget extends nsISupports
{
    public static final String NS_IDOMEVENTTARGET_IID = "{1c773b30-d1cf-11d2-bd95-00805f8ae3f4}";
    
    void addEventListener(final String p0, final nsIDOMEventListener p1, final boolean p2);
    
    void removeEventListener(final String p0, final nsIDOMEventListener p1, final boolean p2);
    
    boolean dispatchEvent(final nsIDOMEvent p0);
}
