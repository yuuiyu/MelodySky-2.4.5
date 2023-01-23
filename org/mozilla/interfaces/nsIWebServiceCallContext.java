//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebServiceCallContext extends nsISupports
{
    public static final String NS_IWEBSERVICECALLCONTEXT_IID = "{87d87900-f102-4a15-b345-7b77a49d2df2}";
    public static final long PENDING = 0L;
    public static final long SUCCEEDED = 1L;
    public static final long FAILED = 2L;
    public static final long ABORTED = 3L;
    
    nsIWebServiceProxy getProxy();
    
    String getMethodName();
    
    long getStatus();
    
    nsIException getPendingException();
    
    nsIWSDLOperation getOperation();
    
    void abort(final nsIException p0);
}
