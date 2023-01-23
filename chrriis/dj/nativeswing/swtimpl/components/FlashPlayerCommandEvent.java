//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;

public class FlashPlayerCommandEvent extends EventObject
{
    private JFlashPlayer flashPlayer;
    private String command;
    private Object[] parameters;
    
    public FlashPlayerCommandEvent(final JFlashPlayer flashPlayer, final String command, final Object[] parameters) {
        super(flashPlayer);
        this.flashPlayer = flashPlayer;
        this.command = command;
        this.parameters = parameters;
    }
    
    public JFlashPlayer getFlashPlayer() {
        return this.flashPlayer;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public Object[] getParameters() {
        return this.parameters;
    }
}
