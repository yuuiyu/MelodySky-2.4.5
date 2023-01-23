//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.settings.*;
import xyz.Melody.Event.*;
import net.minecraftforge.event.world.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ActionMacro extends Module
{
    private BlockPos blockPos;
    private BlockPos lastBlockPos;
    private float currentDamage;
    private Option<Boolean> forward;
    private Option<Boolean> backward;
    private Option<Boolean> left;
    private Option<Boolean> right;
    private Option<Boolean> space;
    private Option<Boolean> lmb;
    
    public ActionMacro() {
        super("ActionMacro", new String[] { "" }, ModuleType.Macros);
        this.lastBlockPos = null;
        this.currentDamage = 0.0f;
        this.forward = (Option<Boolean>)new Option("W", (Object)false);
        this.backward = (Option<Boolean>)new Option("S", (Object)false);
        this.left = (Option<Boolean>)new Option("A", (Object)false);
        this.right = (Option<Boolean>)new Option("D", (Object)false);
        this.space = (Option<Boolean>)new Option("Jump", (Object)false);
        this.lmb = (Option<Boolean>)new Option("Lmb", (Object)false);
        this.addValues(new Value[] { (Value)this.forward, (Value)this.backward, (Value)this.left, (Value)this.right, (Value)this.space, (Value)this.lmb });
        this.setModInfo("Press Keyboard or Mouse Buttons.");
    }
    
    @EventHandler
    private void idk(final EventPreUpdate event) {
        if (this.forward.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindForward.getKeyCode(), true);
        }
        if (this.backward.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindBack.getKeyCode(), true);
        }
        if (this.left.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindLeft.getKeyCode(), true);
        }
        if (this.right.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindRight.getKeyCode(), true);
        }
        if (this.space.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindJump.getKeyCode(), true);
        }
        if (this.lmb.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindAttack.getKeyCode(), true);
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        KeyBinding.unPressAllKeys();
        super.onDisable();
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        KeyBinding.unPressAllKeys();
        this.setEnabled(false);
    }
}
