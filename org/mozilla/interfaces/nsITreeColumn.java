//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeColumn extends nsISupports
{
    public static final String NS_ITREECOLUMN_IID = "{58a8574d-15a8-4678-99a5-e1be56104093}";
    public static final short TYPE_TEXT = 1;
    public static final short TYPE_CHECKBOX = 2;
    public static final short TYPE_PROGRESSMETER = 3;
    
    nsIDOMElement getElement();
    
    nsITreeColumns getColumns();
    
    int getX();
    
    int getWidth();
    
    String getId();
    
    int getIndex();
    
    boolean getPrimary();
    
    boolean getCycler();
    
    boolean getEditable();
    
    short getType();
    
    nsITreeColumn getNext();
    
    nsITreeColumn getPrevious();
    
    void invalidate();
}
