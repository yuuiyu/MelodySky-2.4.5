//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPMessage extends nsISupports
{
    public static final String NS_ISOAPMESSAGE_IID = "{3970815e-1dd2-11b2-a475-db4dac6826f1}";
    public static final int VERSION_1_1 = 0;
    public static final int VERSION_1_2 = 1;
    public static final int VERSION_UNKNOWN = 65535;
    
    nsIDOMDocument getMessage();
    
    void setMessage(final nsIDOMDocument p0);
    
    nsIDOMElement getEnvelope();
    
    int getVersion();
    
    nsIDOMElement getHeader();
    
    nsIDOMElement getBody();
    
    String getMethodName();
    
    String getTargetObjectURI();
    
    void encode(final int p0, final String p1, final String p2, final long p3, final nsISOAPHeaderBlock[] p4, final long p5, final nsISOAPParameter[] p6);
    
    nsISOAPHeaderBlock[] getHeaderBlocks(final long[] p0);
    
    nsISOAPParameter[] getParameters(final boolean p0, final long[] p1);
    
    nsISOAPEncoding getEncoding();
    
    void setEncoding(final nsISOAPEncoding p0);
    
    String getActionURI();
    
    void setActionURI(final String p0);
}
