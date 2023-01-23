//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFileSpec extends nsISupports
{
    public static final String NS_IFILESPEC_IID = "{37ef2e71-edef-46c7-acd9-f0b6e0b15083}";
    
    void fromFileSpec(final nsIFileSpec p0);
    
    String getURLString();
    
    void setURLString(final String p0);
    
    String getUnixStyleFilePath();
    
    void setUnixStyleFilePath(final String p0);
    
    String getPersistentDescriptorString();
    
    void setPersistentDescriptorString(final String p0);
    
    String getNativePath();
    
    void setNativePath(final String p0);
    
    String getNSPRPath();
    
    void error();
    
    boolean isValid();
    
    boolean failed();
    
    String getLeafName();
    
    void setLeafName(final String p0);
    
    nsIFileSpec getParent();
    
    nsIInputStream getInputStream();
    
    nsIOutputStream getOutputStream();
    
    boolean isChildOf(final nsIFileSpec p0);
    
    String getFileContents();
    
    void setFileContents(final String p0);
    
    void makeUnique();
    
    void makeUniqueWithSuggestedName(final String p0);
    
    void makeUniqueDir();
    
    void makeUniqueDirWithSuggestedName(final String p0);
    
    long getModDate();
    
    boolean modDateChanged(final long p0);
    
    boolean isDirectory();
    
    boolean isFile();
    
    boolean exists();
    
    boolean isHidden();
    
    boolean _equals(final nsIFileSpec p0);
    
    long getFileSize();
    
    long getDiskSpaceAvailable();
    
    void appendRelativeUnixPath(final String p0);
    
    void createDir();
    
    void touch();
    
    boolean isSymlink();
    
    void resolveSymlink();
    
    void delete(final boolean p0);
    
    void truncate(final int p0);
    
    void rename(final String p0);
    
    void copyToDir(final nsIFileSpec p0);
    
    void moveToDir(final nsIFileSpec p0);
    
    void execute(final String p0);
    
    void openStreamForReading();
    
    void openStreamForWriting();
    
    void openStreamForReadingAndWriting();
    
    void closeStream();
    
    boolean isStreamOpen();
    
    boolean eof();
    
    int read(final String[] p0, final int p1);
    
    void readLine(final String[] p0, final int p1, final boolean[] p2);
    
    int write(final String p0, final int p1);
    
    void flush();
    
    void seek(final int p0);
    
    int tell();
    
    void endLine();
    
    String getUnicodePath();
    
    void setUnicodePath(final String p0);
}
