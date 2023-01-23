//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.macros.*;
import net.minecraft.util.*;
import net.minecraft.client.audio.*;
import xyz.Melody.GUI.Notification.*;
import net.minecraftforge.common.*;
import org.lwjgl.input.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.System.Commands.*;

public class Module
{
    public String name;
    private String suffix;
    private int color;
    private String[] alias;
    private String modInfo;
    private boolean enabled;
    public boolean enabledOnStartup;
    private int key;
    public List<Value<?>> values;
    public ModuleType type;
    private boolean removed;
    public Minecraft mc;
    public ScaledResolution mainWindow;
    public static Random random;
    
    public Module(final String name, final String[] alias, final ModuleType type) {
        this.enabledOnStartup = false;
        this.mc = Minecraft.getMinecraft();
        this.mainWindow = new ScaledResolution(this.mc);
        this.name = name;
        this.alias = alias;
        this.type = type;
        this.suffix = "";
        this.key = 0;
        this.removed = false;
        this.enabled = false;
        this.values = new ArrayList<Value<?>>();
        this.modInfo = "";
    }
    
    public Module(final String name, final ModuleType type) {
        this.enabledOnStartup = false;
        this.mc = Minecraft.getMinecraft();
        this.mainWindow = new ScaledResolution(this.mc);
        this.name = name;
        this.alias = new String[0];
        this.type = type;
        this.suffix = "";
        this.key = 0;
        this.removed = false;
        this.enabled = false;
        this.values = new ArrayList<Value<?>>();
        this.modInfo = "";
    }
    
    public String getName() {
        return this.name;
    }
    
    public String[] getAlias() {
        return this.alias;
    }
    
    public ModuleType getType() {
        return this.type;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean wasRemoved() {
        return this.removed;
    }
    
    public void setRemoved(final boolean removed) {
        this.removed = removed;
    }
    
    public String getModInfo() {
        return this.modInfo;
    }
    
    public void setModInfo(final String modInfo) {
        this.modInfo = modInfo;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    public void setSuffix(final Object obj) {
        final String suffix = obj.toString();
        if (suffix.isEmpty()) {
            this.suffix = suffix;
        }
        else {
            this.suffix = String.format("§7- §f%s§7", EnumChatFormatting.GRAY + suffix);
        }
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            if (this.name == "FreeCam" && !this.mc.thePlayer.onGround) {
                Helper.sendMessage("[WARNING] FreeCam can only be used on Ground.");
                this.enabled = false;
                return;
            }
            this.onEnable();
            EventBus.getInstance().register(new Object[] { this });
            this.regFML(this);
            if (ModuleManager.loaded && this.getType() != ModuleType.QOL && this.getType() != ModuleType.Swapping && this.getName() != "ClickGui") {
                if (this.getType() == ModuleType.Macros && !Client.instance.getModuleManager().getModuleByClass(AutoRuby.class).isEnabled() && !(this instanceof GemstoneNuker)) {
                    Helper.sendMessage("[Macro] " + EnumChatFormatting.DARK_AQUA + this.getName() + EnumChatFormatting.GRAY + " Now" + EnumChatFormatting.GREEN + " Enabled" + EnumChatFormatting.GRAY + ".");
                }
                this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0f));
                NotificationPublisher.queue("Module", this.getName() + " Enabled", NotificationType.INFO, 1000);
            }
        }
        else {
            EventBus.getInstance().unregister(new Object[] { this });
            this.unregFML(this);
            if (ModuleManager.loaded && this.getType() != ModuleType.QOL && this.getType() != ModuleType.Swapping && this.getName() != "ClickGui") {
                if (this.getType() == ModuleType.Macros && !Client.instance.getModuleManager().getModuleByClass(AutoRuby.class).isEnabled() && !(this instanceof GemstoneNuker)) {
                    Helper.sendMessage("[Macro] " + EnumChatFormatting.DARK_AQUA + this.getName() + EnumChatFormatting.GRAY + " Now" + EnumChatFormatting.RED + " Disabled" + EnumChatFormatting.GRAY + ".");
                }
                this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 0.8f));
                NotificationPublisher.queue("Module", this.getName() + " Disabled", NotificationType.INFO, 1000);
            }
            this.onDisable();
        }
    }
    
    private void regFML(final Object obj) {
        MinecraftForge.EVENT_BUS.register(obj);
    }
    
    private void unregFML(final Object obj) {
        MinecraftForge.EVENT_BUS.unregister(obj);
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public int getColor() {
        return this.color;
    }
    
    protected void addValues(final Value<?>... values) {
        final Value<?>[] var5 = values;
        for (int var6 = values.length, var7 = 0; var7 < var6; ++var7) {
            final Value<?> value = var5[var7];
            this.values.add(value);
        }
    }
    
    public List<Value<?>> getValues() {
        return this.values;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
        String content = "";
        Client.instance.getModuleManager();
        for (final Module m : ModuleManager.getModules()) {
            content += String.format("%s:%s%s", m.getName(), Keyboard.getKeyName(m.getKey()), System.lineSeparator());
        }
        FileManager.save("Binds.txt", content, false);
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    public void makeCommand() {
        if (this.values.size() > 0) {
            String options = "";
            String other = "";
            for (final Value<?> v : this.values) {
                if (!(v instanceof Mode)) {
                    if (options.isEmpty()) {
                        options = String.valueOf(options) + v.getName();
                    }
                    else {
                        options = String.valueOf(options) + String.format(", %s", v.getName());
                    }
                }
            }
            for (final Value<?> v : this.values) {
                if (v instanceof Mode) {
                    final Mode<?> mode = (Mode<?>)v;
                    Enum<?>[] modes;
                    for (int length = (modes = (Enum<?>[])mode.getModes()).length, i = 0; i < length; ++i) {
                        final Enum<?> e = modes[i];
                        if (other.isEmpty()) {
                            other = String.valueOf(other) + e.name().toLowerCase();
                        }
                        else {
                            other = String.valueOf(other) + String.format(", %s", e.name().toLowerCase());
                        }
                    }
                }
            }
            Client.instance.getCommandManager().add((Command)new l(this, this.name, this.alias, String.format("%s%s", options.isEmpty() ? "" : String.format("%s,", options), other.isEmpty() ? "" : String.format("%s", other)), "Setup this module"));
        }
    }
    
    static {
        Module.random = new Random();
    }
}
