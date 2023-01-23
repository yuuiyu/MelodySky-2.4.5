//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPropertyBag2 extends nsIPropertyBag
{
    public static final String NS_IPROPERTYBAG2_IID = "{9bb35f13-0096-4a31-833a-acd97001132d}";
    
    int getPropertyAsInt32(final String p0);
    
    long getPropertyAsUint32(final String p0);
    
    long getPropertyAsInt64(final String p0);
    
    double getPropertyAsUint64(final String p0);
    
    double getPropertyAsDouble(final String p0);
    
    String getPropertyAsAString(final String p0);
    
    String getPropertyAsACString(final String p0);
    
    String getPropertyAsAUTF8String(final String p0);
    
    boolean getPropertyAsBool(final String p0);
    
    nsISupports getPropertyAsInterface(final String p0, final String p1);
}
