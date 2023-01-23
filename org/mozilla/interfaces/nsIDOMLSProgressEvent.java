//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSProgressEvent extends nsIDOMEvent
{
    public static final String NS_IDOMLSPROGRESSEVENT_IID = "{b9a2371f-70e9-4657-b0e8-28e15b40857e}";
    
    nsIDOMLSInput getInput();
    
    long getPosition();
    
    long getTotalSize();
}
