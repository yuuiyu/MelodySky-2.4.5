//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Swappings;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.Item.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.misc.*;

public class ItemSwitcher extends Module
{
    private boolean shouldSwitch;
    private String customItemID;
    private TimerUtil timer;
    private Option<Boolean> auto;
    private Option<Boolean> custom;
    private Option<Boolean> left;
    private Mode<Enum> type;
    private Numbers<Double> delay;
    
    public ItemSwitcher() {
        super("ItemSwitcher", new String[] { "is", "1tick", "itemmacro" }, ModuleType.Swapping);
        this.timer = new TimerUtil();
        this.auto = (Option<Boolean>)new Option("Auto", (Object)false);
        this.custom = (Option<Boolean>)new Option("Custom", (Object)false);
        this.left = (Option<Boolean>)new Option("LeftClick", (Object)false);
        this.type = (Mode<Enum>)new Mode("Item", (Enum[])itemType.values(), (Enum)itemType.AOTS);
        this.delay = (Numbers<Double>)new Numbers("Delay/ms", (Number)600.0, (Number)50.0, (Number)3000.0, (Number)10.0);
        this.addValues(new Value[] { (Value)this.auto, (Value)this.custom, (Value)this.left, (Value)this.type, (Value)this.delay });
        this.setModInfo("Provides Custom Item Swapping.");
    }
    
    public void onEnable() {
        this.timer.reset();
    }
    
    public void setCustomItemID(final String customItemID) {
        this.customItemID = customItemID;
    }
    
    public String getCustomItemID() {
        return this.customItemID;
    }
    
    @EventHandler
    private void underTick(final EventTick e) {
        if (!(boolean)this.custom.getValue()) {
            return;
        }
        if (this.customItemID == null && this.shouldSwitch) {
            Helper.sendMessage("Use <.is [ItemID]> to set value.");
            this.shouldSwitch = false;
            return;
        }
        if ((boolean)this.auto.getValue() && this.timer.hasReached((double)((Double)this.delay.getValue()).longValue())) {
            ItemUtils.useSBItem(this.customItemID, (boolean)this.left.getValue());
            this.timer.reset();
            return;
        }
        if (this.shouldSwitch && !(boolean)this.auto.getValue()) {
            ItemUtils.useSBItem(this.customItemID, (boolean)this.left.getValue());
            this.shouldSwitch = false;
        }
    }
    
    @EventHandler
    private void onTick(final EventTick e) {
        if (this.custom.getValue()) {
            return;
        }
        final String name = ((Enum)this.type.getValue()).toString();
        if ((boolean)this.auto.getValue() && this.timer.hasReached((double)((Double)this.delay.getValue()).longValue())) {
            final String s = name;
            switch (s) {
                case "AOTS": {
                    ItemUtils.useSBItem("AXE_OF_THE_SHREDDED", (boolean)this.left.getValue());
                    break;
                }
                case "Ice_Spray": {
                    ItemUtils.useSBItem("ICE_SPRAY_WAND", (boolean)this.left.getValue());
                    break;
                }
                case "Soul_Whip": {
                    ItemUtils.useSBItem("SOUL_WHIP", (boolean)this.left.getValue());
                    break;
                }
                case "Juju": {
                    ItemUtils.useSBItem("JUJU_SHORTBOW", (boolean)this.left.getValue());
                    break;
                }
                case "Terminator": {
                    ItemUtils.useSBItem("TERMINATOR", (boolean)this.left.getValue());
                    break;
                }
                case "AOTV": {
                    ItemUtils.useSBItem("ASPECT_OF_THE_VOID", (boolean)this.left.getValue());
                    break;
                }
            }
            this.timer.reset();
        }
        if (this.shouldSwitch && !(boolean)this.auto.getValue()) {
            final String s2 = name;
            switch (s2) {
                case "AOTS": {
                    ItemUtils.useSBItem("AXE_OF_THE_SHREDDED", (boolean)this.left.getValue());
                    break;
                }
                case "Ice_Spray": {
                    ItemUtils.useSBItem("ICE_SPRAY_WAND", (boolean)this.left.getValue());
                    break;
                }
                case "Soul_Whip": {
                    ItemUtils.useSBItem("SOUL_WHIP", (boolean)this.left.getValue());
                    break;
                }
                case "Juju": {
                    ItemUtils.useSBItem("JUJU_SHORTBOW", (boolean)this.left.getValue());
                    break;
                }
                case "Terminator": {
                    ItemUtils.useSBItem("TERMINATOR", (boolean)this.left.getValue());
                    break;
                }
                case "AOTV": {
                    ItemUtils.useSBItem("ASPECT_OF_THE_VOID", (boolean)this.left.getValue());
                    break;
                }
            }
            this.shouldSwitch = false;
        }
    }
    
    @EventHandler
    private void onKey(final EventKey event) {
        if (event.getKey() == this.getKey() && !(boolean)this.auto.getValue()) {
            this.setEnabled(this.shouldSwitch = true);
        }
    }
    
    public void onDisable() {
        this.timer.reset();
    }
    
    enum itemType
    {
        Ice_Spray, 
        AOTS, 
        Soul_Whip, 
        Juju, 
        Terminator, 
        AOTV;
    }
}
