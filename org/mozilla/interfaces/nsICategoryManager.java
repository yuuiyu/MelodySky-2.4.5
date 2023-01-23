//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICategoryManager extends nsISupports
{
    public static final String NS_ICATEGORYMANAGER_IID = "{3275b2cd-af6d-429a-80d7-f0c5120342ac}";
    
    String getCategoryEntry(final String p0, final String p1);
    
    String addCategoryEntry(final String p0, final String p1, final String p2, final boolean p3, final boolean p4);
    
    void deleteCategoryEntry(final String p0, final String p1, final boolean p2);
    
    void deleteCategory(final String p0);
    
    nsISimpleEnumerator enumerateCategory(final String p0);
    
    nsISimpleEnumerator enumerateCategories();
}
