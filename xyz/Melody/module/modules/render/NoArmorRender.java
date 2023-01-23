//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;

public class NoArmorRender extends Module
{
    public Option<Boolean> selfOnly;
    public Option<Boolean> chead;
    public Option<Boolean> armor;
    public Option<Boolean> arrows;
    
    public NoArmorRender() {
        super("NoArmorRender", new String[] { "armor" }, ModuleType.Render);
        this.selfOnly = (Option<Boolean>)new Option("SelfOnly", (Object)true);
        this.chead = (Option<Boolean>)new Option("HideCustomHead", (Object)true);
        this.armor = (Option<Boolean>)new Option("HideArmor", (Object)true);
        this.arrows = (Option<Boolean>)new Option("HideArrows", (Object)true);
        this.addValues(new Value[] { (Value)this.selfOnly, (Value)this.chead, (Value)this.armor, (Value)this.arrows });
        this.setModInfo("Armor Invisible, Hide Arrows on Your Body.");
    }
}
