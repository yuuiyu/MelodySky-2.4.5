//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAuthPromptProvider extends nsISupports
{
    public static final String NS_IAUTHPROMPTPROVIDER_IID = "{129d3bd5-8a26-4b0b-b8a0-19fdea029196}";
    public static final long PROMPT_NORMAL = 0L;
    public static final long PROMPT_PROXY = 1L;
    
    nsIAuthPrompt getAuthPrompt(final long p0);
}
