//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.events.misc;

import xyz.Melody.Event.*;

public class EventChat extends Event
{
    private String message;
    
    public EventChat(final String message) {
        this.message = message;
        this.setType((byte)0);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
}
