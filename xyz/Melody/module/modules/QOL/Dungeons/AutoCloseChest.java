//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.client.gui.inventory.*;
import xyz.Melody.*;
import net.minecraft.client.gui.*;
import xyz.Melody.Event.*;
import net.minecraft.inventory.*;

public class AutoCloseChest extends Module
{
    public AutoCloseChest() {
        super("AutoCloseChest", new String[] { "acc" }, ModuleType.Dungeons);
        this.setModInfo("Auto Close Secret Chests.");
    }
    
    @EventHandler
    public void onGuiDraw(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (gui instanceof GuiChest && Client.inSkyblock && Client.inDungeons && this.getGuiName(gui).equals("Chest")) {
            this.mc.thePlayer.closeScreen();
        }
    }
    
    public String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }
}
