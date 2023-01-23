//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransferable extends nsISupports
{
    public static final String NS_ITRANSFERABLE_IID = "{8b5314bc-db01-11d2-96ce-0060b0fb9956}";
    public static final int kFlavorHasDataProvider = 0;
    
    nsISupportsArray flavorsTransferableCanExport();
    
    void getTransferData(final String p0, final nsISupports[] p1, final long[] p2);
    
    void getAnyTransferData(final String[] p0, final nsISupports[] p1, final long[] p2);
    
    boolean isLargeDataSet();
    
    nsISupportsArray flavorsTransferableCanImport();
    
    void setTransferData(final String p0, final nsISupports p1, final long p2);
    
    void addDataFlavor(final String p0);
    
    void removeDataFlavor(final String p0);
    
    nsIFormatConverter getConverter();
    
    void setConverter(final nsIFormatConverter p0);
}
