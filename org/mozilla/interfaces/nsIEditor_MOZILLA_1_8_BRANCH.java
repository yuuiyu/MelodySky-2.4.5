//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditor_MOZILLA_1_8_BRANCH extends nsIEditor
{
    public static final String NS_IEDITOR_MOZILLA_1_8_BRANCH_IID = "{60fbf998-e021-4f81-bdf0-749cc651e221}";
    
    nsIInlineSpellChecker getInlineSpellCheckerOptionally(final boolean p0);
    
    void syncRealTimeSpell();
    
    void setSpellcheckUserOverride(final boolean p0);
}
