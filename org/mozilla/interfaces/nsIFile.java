//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFile extends nsISupports
{
    public static final String NS_IFILE_IID = "{c8c0a080-0868-11d3-915f-d9d889d48e3c}";
    public static final long NORMAL_FILE_TYPE = 0L;
    public static final long DIRECTORY_TYPE = 1L;
    
    void append(final String p0);
    
    void normalize();
    
    void create(final long p0, final long p1);
    
    String getLeafName();
    
    void setLeafName(final String p0);
    
    void copyTo(final nsIFile p0, final String p1);
    
    void copyToFollowingLinks(final nsIFile p0, final String p1);
    
    void moveTo(final nsIFile p0, final String p1);
    
    void remove(final boolean p0);
    
    long getPermissions();
    
    void setPermissions(final long p0);
    
    long getPermissionsOfLink();
    
    void setPermissionsOfLink(final long p0);
    
    long getLastModifiedTime();
    
    void setLastModifiedTime(final long p0);
    
    long getLastModifiedTimeOfLink();
    
    void setLastModifiedTimeOfLink(final long p0);
    
    long getFileSize();
    
    void setFileSize(final long p0);
    
    long getFileSizeOfLink();
    
    String getTarget();
    
    String getPath();
    
    boolean exists();
    
    boolean isWritable();
    
    boolean isReadable();
    
    boolean isExecutable();
    
    boolean isHidden();
    
    boolean isDirectory();
    
    boolean isFile();
    
    boolean isSymlink();
    
    boolean isSpecial();
    
    void createUnique(final long p0, final long p1);
    
    nsIFile _clone();
    
    boolean _equals(final nsIFile p0);
    
    boolean contains(final nsIFile p0, final boolean p1);
    
    nsIFile getParent();
    
    nsISimpleEnumerator getDirectoryEntries();
}
