//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISyncLoadDOMService extends nsISupports
{
    public static final String NS_ISYNCLOADDOMSERVICE_IID = "{96a13c30-695a-492c-918b-04ae3edb4e4c}";
    
    nsIDOMDocument loadDocument(final nsIChannel p0, final nsIURI p1);
    
    nsIDOMDocument loadDocumentAsXML(final nsIChannel p0, final nsIURI p1);
    
    nsIDOMDocument loadLocalDocument(final nsIChannel p0, final nsIURI p1);
    
    nsIDOMDocument loadLocalXBLDocument(final nsIChannel p0);
}
