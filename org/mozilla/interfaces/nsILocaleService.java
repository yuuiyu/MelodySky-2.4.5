//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsILocaleService extends nsISupports
{
    public static final String NS_ILOCALESERVICE_IID = "{48ab1fa0-4550-11d3-91cd-00105aa3f7dc}";
    
    nsILocale newLocale(final String p0);
    
    nsILocale newLocaleObject(final nsILocaleDefinition p0);
    
    nsILocale getSystemLocale();
    
    nsILocale getApplicationLocale();
    
    nsILocale getLocaleFromAcceptLanguage(final String p0);
    
    String getLocaleComponentForUserAgent();
}
