//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInlineSpellChecker extends nsISupports
{
    public static final String NS_IINLINESPELLCHECKER_IID = "{f5d1ec9e-4d30-11d8-8053-da0cc7df1f20}";
    
    nsIEditorSpellCheck getSpellChecker();
    
    boolean getEnableRealTimeSpell();
    
    void setEnableRealTimeSpell(final boolean p0);
    
    void spellCheckAfterEditorChange(final int p0, final nsISelection p1, final nsIDOMNode p2, final int p3, final nsIDOMNode p4, final int p5, final nsIDOMNode p6, final int p7);
    
    void spellCheckRange(final nsIDOMRange p0);
    
    nsIDOMRange getMispelledWord(final nsIDOMNode p0, final int p1);
    
    void replaceWord(final nsIDOMNode p0, final int p1, final String p2);
    
    void addWordToDictionary(final String p0);
    
    void ignoreWord(final String p0);
    
    void ignoreWords(final String[] p0, final long p1);
}
