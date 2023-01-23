//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMenuBoxObject extends nsISupports
{
    public static final String NS_IMENUBOXOBJECT_IID = "{f5099746-5049-4e81-a03e-945d5110fee2}";
    
    void openMenu(final boolean p0);
    
    nsIDOMElement getActiveChild();
    
    void setActiveChild(final nsIDOMElement p0);
    
    boolean handleKeyPress(final nsIDOMKeyEvent p0);
}
