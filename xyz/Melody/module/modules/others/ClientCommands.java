//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;

public class ClientCommands extends Module
{
    public Mode<Enum> mode;
    
    public ClientCommands() {
        super("ClientCommands", new String[] { "cc", "ccm", "command" }, ModuleType.Others);
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])commandMode.values(), (Enum)commandMode.dot);
        this.addValues(new Value[] { (Value)this.mode });
        this.setModInfo("dot: .xxx | bar: -xxx | wavy_line: ~xxx");
        this.setEnabled(true);
    }
    
    enum commandMode
    {
        dot, 
        bar, 
        wavy_line;
    }
}
