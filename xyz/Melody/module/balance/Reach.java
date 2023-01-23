//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;

public class Reach extends Module
{
    public Numbers<Double> size;
    
    public Reach() {
        super("Reach", new String[] { "hitBox" }, ModuleType.Balance);
        this.size = (Numbers<Double>)new Numbers("Size", (Number)3.5, (Number)0.0, (Number)6.0, (Number)0.1);
        this.addValues((Value)this.size);
    }
}
