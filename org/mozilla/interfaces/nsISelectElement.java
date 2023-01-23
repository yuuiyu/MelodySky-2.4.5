//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelectElement extends nsISupports
{
    public static final String NS_ISELECTELEMENT_IID = "{35bd8ed5-5f34-4126-8c4f-38ba01681836}";
    
    boolean isOptionDisabled(final int p0);
    
    boolean setOptionsSelectedByIndex(final int p0, final int p1, final boolean p2, final boolean p3, final boolean p4, final boolean p5);
    
    int getOptionIndex(final nsIDOMHTMLOptionElement p0, final int p1, final boolean p2);
    
    boolean getHasOptGroups();
}
