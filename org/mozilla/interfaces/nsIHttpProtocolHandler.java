//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpProtocolHandler extends nsIProxiedProtocolHandler
{
    public static final String NS_IHTTPPROTOCOLHANDLER_IID = "{122c91c0-2485-40ba-89c9-b895934921bc}";
    
    String getUserAgent();
    
    String getAppName();
    
    String getAppVersion();
    
    String getVendor();
    
    void setVendor(final String p0);
    
    String getVendorSub();
    
    void setVendorSub(final String p0);
    
    String getVendorComment();
    
    void setVendorComment(final String p0);
    
    String getProduct();
    
    void setProduct(final String p0);
    
    String getProductSub();
    
    void setProductSub(final String p0);
    
    String getProductComment();
    
    void setProductComment(final String p0);
    
    String getPlatform();
    
    String getOscpu();
    
    String getLanguage();
    
    void setLanguage(final String p0);
    
    String getMisc();
    
    void setMisc(final String p0);
}
