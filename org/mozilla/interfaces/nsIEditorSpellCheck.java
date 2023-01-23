//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorSpellCheck extends nsISupports
{
    public static final String NS_IEDITORSPELLCHECK_IID = "{6088a862-1229-11d9-941d-c035b2e390c6}";
    
    void initSpellChecker(final nsIEditor p0, final boolean p1);
    
    String getNextMisspelledWord();
    
    String getSuggestedWord();
    
    boolean checkCurrentWord(final String p0);
    
    void replaceWord(final String p0, final String p1, final boolean p2);
    
    void ignoreWordAllOccurrences(final String p0);
    
    void getPersonalDictionary();
    
    String getPersonalDictionaryWord();
    
    void addWordToDictionary(final String p0);
    
    void removeWordFromDictionary(final String p0);
    
    void getDictionaryList(final String[][] p0, final long[] p1);
    
    String getCurrentDictionary();
    
    void setCurrentDictionary(final String p0);
    
    void uninitSpellChecker();
    
    void setFilter(final nsITextServicesFilter p0);
    
    boolean checkCurrentWordNoSuggest(final String p0);
}
