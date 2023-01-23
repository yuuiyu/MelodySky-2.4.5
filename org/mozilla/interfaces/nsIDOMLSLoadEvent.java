//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSLoadEvent extends nsIDOMEvent
{
    public static final String NS_IDOMLSLOADEVENT_IID = "{6c16a810-a37d-4859-b557-337341631aee}";
    
    nsIDOMDocument getNewDocument();
    
    nsIDOMLSInput getInput();
}
