//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageDataSet extends nsISupports
{
    public static final String MOZISTORAGEDATASET_IID = "{57826606-3c8a-4243-9f2f-cb3fe6e91148}";
    
    nsIArray getDataRows();
    
    nsISimpleEnumerator getRowEnumerator();
}
