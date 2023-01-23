//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXPathResult extends nsISupports
{
    public static final String NS_IDOMXPATHRESULT_IID = "{75506f84-b504-11d5-a7f2-ca108ab8b6fc}";
    public static final int ANY_TYPE = 0;
    public static final int NUMBER_TYPE = 1;
    public static final int STRING_TYPE = 2;
    public static final int BOOLEAN_TYPE = 3;
    public static final int UNORDERED_NODE_ITERATOR_TYPE = 4;
    public static final int ORDERED_NODE_ITERATOR_TYPE = 5;
    public static final int UNORDERED_NODE_SNAPSHOT_TYPE = 6;
    public static final int ORDERED_NODE_SNAPSHOT_TYPE = 7;
    public static final int ANY_UNORDERED_NODE_TYPE = 8;
    public static final int FIRST_ORDERED_NODE_TYPE = 9;
    
    int getResultType();
    
    double getNumberValue();
    
    String getStringValue();
    
    boolean getBooleanValue();
    
    nsIDOMNode getSingleNodeValue();
    
    boolean getInvalidIteratorState();
    
    long getSnapshotLength();
    
    nsIDOMNode iterateNext();
    
    nsIDOMNode snapshotItem(final long p0);
}
