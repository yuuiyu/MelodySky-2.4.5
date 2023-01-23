//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI;

import xyz.Melody.System.Managers.*;
import xyz.Melody.GUI.CustomUI.Functions.*;
import xyz.Melody.Event.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;

public class HUDManager implements Manager
{
    public static boolean loaded;
    public static List<HUDApi> apis;
    
    @Override
    public void init() {
        HUDManager.apis.add((HUDApi)new FishingPotion());
        HUDManager.apis.add((HUDApi)new TargetHUD());
        HUDManager.apis.add((HUDApi)new NPlayerList());
        HUDManager.apis.add((HUDApi)new Day());
        HUDManager.apis.add((HUDApi)new BigRat());
        HUDManager.apis.add((HUDApi)new MiningOverlay());
        HUDManager.apis.add((HUDApi)new CurrentServerInfo());
        HUDManager.apis.add((HUDApi)new KeyStrokes());
        HUDManager.apis.add((HUDApi)new LCPS());
        HUDManager.apis.add((HUDApi)new RCPS());
        HUDManager.apis.add((HUDApi)new FPS());
        this.readXYE();
        EventBus.getInstance().register(new Object[] { this });
        HUDManager.loaded = true;
    }
    
    public static List<HUDApi> getApis() {
        return HUDManager.apis;
    }
    
    public static HUDApi getApiByName(final String name) {
        for (final HUDApi h : HUDManager.apis) {
            if (!h.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return h;
        }
        return null;
    }
    
    private void readXYE() {
        final List<String> hud = FileManager.read("HUD.txt");
        for (final String v : hud) {
            final String name = v.split(":")[0];
            final String x1 = v.split(":")[1];
            final String x2 = x1.split(":")[0];
            final String y1 = v.split(":")[2];
            final String y2 = y1.split(":")[0];
            final String e = v.split(":")[3];
            final HUDApi m = getApiByName(name);
            if (m == null) {
                continue;
            }
            m.x = Integer.parseInt(x2);
            m.y = Integer.parseInt(y2);
            m.setEnabled(Boolean.parseBoolean(e));
        }
    }
    
    static {
        HUDManager.loaded = false;
        HUDManager.apis = new ArrayList<HUDApi>();
    }
}
