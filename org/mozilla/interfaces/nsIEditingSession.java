//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditingSession extends nsISupports
{
    public static final String NS_IEDITINGSESSION_IID = "{d39fd2b4-3978-45d2-a4be-ba448171b61b}";
    public static final int eEditorOK = 0;
    public static final int eEditorCreationInProgress = 1;
    public static final int eEditorErrorCantEditMimeType = 2;
    public static final int eEditorErrorFileNotFound = 3;
    public static final int eEditorErrorCantEditFramesets = 8;
    public static final int eEditorErrorUnknown = 9;
    
    long getEditorStatus();
    
    void makeWindowEditable(final nsIDOMWindow p0, final String p1, final boolean p2);
    
    boolean windowIsEditable(final nsIDOMWindow p0);
    
    nsIEditor getEditorForWindow(final nsIDOMWindow p0);
    
    void setupEditorOnWindow(final nsIDOMWindow p0);
    
    void tearDownEditorOnWindow(final nsIDOMWindow p0);
    
    void setEditorOnControllers(final nsIDOMWindow p0, final nsIEditor p1);
}
