//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraft.client.gui.*;
import chrriis.dj.nativeswing.*;
import chrriis.dj.nativeswing.swtimpl.components.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Event.*;

public class InternetSurfing extends Module
{
    private Browser bruhSir;
    private Option<Boolean> onTop;
    
    public InternetSurfing() {
        super("InternetSurfing", new String[] { "internet", "surfing", "browser" }, ModuleType.Others);
        this.onTop = (Option<Boolean>)new Option("AlwaysOnTop", (Object)true);
        this.addValues(new Value[] { (Value)this.onTop });
        this.setModInfo("A Simple Web Bruhsir.");
    }
    
    public void onEnable() {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        if (this.bruhSir == null) {
            this.bruhSir = new Browser("https://www.baidu.com/", "MelodySky Internet Surfing", true, true, (boolean)this.onTop.getValue(), (int)(sr.getScaledWidth() / 0.6f), (int)(sr.getScaledHeight() / 0.5), new NSOption[] { JWebBrowser.useEdgeRuntime() });
        }
        super.onEnable();
    }
    
    @EventHandler
    private void tickBruhsir(final EventTick e) {
        if (this.bruhSir != null && this.bruhSir.closed) {
            this.setEnabled(false);
        }
    }
    
    public void onDisable() {
        if (this.bruhSir != null && !this.bruhSir.closed) {
            this.bruhSir.close();
        }
        this.bruhSir = null;
        super.onDisable();
    }
}
