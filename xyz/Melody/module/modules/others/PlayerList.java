//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;

public class PlayerList extends Module
{
    public Numbers<Double> scanDelay;
    public Numbers<Double> range;
    
    public PlayerList() {
        super("PlayerList", new String[] { "cc", "ccm", "command" }, ModuleType.Others);
        this.scanDelay = (Numbers<Double>)new Numbers("ScanDelay", (Number)500.0, (Number)100.0, (Number)2000.0, (Number)10.0);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)30.0, (Number)20.0, (Number)100.0, (Number)5.0);
        this.addValues(new Value[] { (Value)this.scanDelay, (Value)this.range });
    }
}
