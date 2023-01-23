//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNavigator extends nsISupports
{
    public static final String NS_IDOMNAVIGATOR_IID = "{8758b72b-63d4-4685-b908-4275126410fb}";
    
    String getAppCodeName();
    
    String getAppName();
    
    String getAppVersion();
    
    String getLanguage();
    
    nsIDOMMimeTypeArray getMimeTypes();
    
    String getPlatform();
    
    String getOscpu();
    
    String getVendor();
    
    String getVendorSub();
    
    String getProduct();
    
    String getProductSub();
    
    nsIDOMPluginArray getPlugins();
    
    String getSecurityPolicy();
    
    String getUserAgent();
    
    boolean getCookieEnabled();
    
    boolean getOnLine();
    
    boolean javaEnabled();
    
    boolean taintEnabled();
}
