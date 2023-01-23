//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICertVerificationListener extends nsISupports
{
    public static final String NS_ICERTVERIFICATIONLISTENER_IID = "{6684bce9-50db-48e1-81b7-98102bf81357}";
    
    void _notify(final nsIX509Cert3 p0, final nsICertVerificationResult p1);
}
