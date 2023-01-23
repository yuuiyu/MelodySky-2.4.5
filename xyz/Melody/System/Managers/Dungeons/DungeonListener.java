//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Dungeons;

import xyz.Melody.System.Managers.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import xyz.Melody.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import xyz.Melody.Event.*;
import java.util.*;
import xyz.Melody.Utils.*;
import java.util.regex.*;

public class DungeonListener implements Manager
{
    private Minecraft mc;
    public DungeonTypes floor;
    public boolean inBoss;
    public ArrayList<EntityPlayer> teammates;
    public int secretsFound;
    public int cryptsFound;
    public boolean foundMimic;
    public int deaths;
    public boolean activeRun;
    public int score;
    public Pattern scorePattern;
    public Thread dungeonThread;
    public TimerUtil daemonTimer;
    public List<String> entryMessages;
    private Pattern numberPattern;
    
    public DungeonListener() {
        this.mc = Minecraft.getMinecraft();
        this.floor = DungeonTypes.NULL;
        this.inBoss = false;
        this.teammates = new ArrayList<EntityPlayer>();
        this.secretsFound = 0;
        this.cryptsFound = 0;
        this.foundMimic = false;
        this.deaths = 0;
        this.activeRun = false;
        this.score = 0;
        this.scorePattern = Pattern.compile("Cleared: [0-9]{1,3}% \\((?<score>[0-9]+)\\)");
        this.daemonTimer = new TimerUtil();
        this.entryMessages = Arrays.asList("[BOSS] Bonzo:", "[BOSS] Scarf:", "[BOSS] The Professor:", "[BOSS] Thorn:", "[BOSS] Livid:", "[BOSS] Sadan:", "[BOSS] Maxor:");
        this.numberPattern = Pattern.compile("[^0-9.]");
    }
    
    @Override
    public void init() {
        Client.instance.logger.info("[Melody] [CONSOLE] Initializing Melody -> Dungeon Main Thread.");
        this.initDungeonThread();
        EventBus.getInstance().register(new Object[] { this });
    }
    
    private void initDungeonThread() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Ljava/lang/Thread;
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: invokedynamic   BootstrapMethod #0, run:(Lxyz/Melody/System/Managers/Dungeons/DungeonListener;)Ljava/lang/Runnable;
        //    11: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
        //    14: putfield        xyz/Melody/System/Managers/Dungeons/DungeonListener.dungeonThread:Ljava/lang/Thread;
        //    17: aload_0         /* this */
        //    18: getfield        xyz/Melody/System/Managers/Dungeons/DungeonListener.dungeonThread:Ljava/lang/Thread;
        //    21: ldc             "Melody -> Dungeon Thread"
        //    23: invokevirtual   java/lang/Thread.setName:(Ljava/lang/String;)V
        //    26: aload_0         /* this */
        //    27: getfield        xyz/Melody/System/Managers/Dungeons/DungeonListener.dungeonThread:Ljava/lang/Thread;
        //    30: invokevirtual   java/lang/Thread.start:()V
        //    33: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Unsupported node type: com.strobel.decompiler.ast.Lambda
        //     at com.strobel.decompiler.ast.Error.unsupportedNode(Error.java:32)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:612)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:598)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.transformLeaveStatements(GotoRemoval.java:625)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotosCore(GotoRemoval.java:57)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:53)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:276)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void reset() {
        this.floor = DungeonTypes.NULL;
        this.inBoss = false;
        this.secretsFound = 0;
        this.cryptsFound = 0;
        this.foundMimic = false;
        this.deaths = 0;
        this.activeRun = false;
        this.score = 0;
        this.teammates.clear();
    }
    
    @EventHandler
    public void onChatPacket(final EventPacketRecieve event) {
        if (this.mc.theWorld == null || this.mc.thePlayer == null) {
            return;
        }
        if (Client.inDungeons && event.getPacket() instanceof S02PacketChat && ((S02PacketChat)event.getPacket()).getType() != 2) {
            final String text = StringUtils.stripControlCodes(((S02PacketChat)event.getPacket()).getChatComponent().getUnformattedText());
            if ("[NPC] Mort: Here, I found this map when I first entered the dungeon.".equals(text)) {
                this.updateTeammates(this.getTabList());
            }
        }
    }
    
    public void updateTeammates(final List<String> tabList) {
        this.teammates.clear();
        for (int i = 0; i < 5; ++i) {
            final String text = StringUtils.stripControlCodes((String)tabList.get(1 + i * 4)).trim();
            final String username = text.split(" ")[0];
            if (!Objects.equals(username, "")) {
                for (final EntityPlayer playerEntity : this.mc.theWorld.playerEntities) {
                    if (playerEntity.getName().contains(username)) {
                        this.teammates.add(playerEntity);
                    }
                }
            }
        }
    }
    
    public void updateFloor() {
        if (Minecraft.getMinecraft().thePlayer == null || Minecraft.getMinecraft().theWorld == null) {
            return;
        }
        if (Client.inDungeons) {
            if (ScoreboardUtils.scoreboardContains("(F1)")) {
                this.floor = DungeonTypes.F1;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F2)")) {
                this.floor = DungeonTypes.F2;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F3)")) {
                this.floor = DungeonTypes.F3;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F4)")) {
                this.floor = DungeonTypes.F4;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F5)")) {
                this.floor = DungeonTypes.F5;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F6)")) {
                this.floor = DungeonTypes.F6;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(F7)")) {
                this.floor = DungeonTypes.F7;
                Client.isMMD = false;
            }
            else if (ScoreboardUtils.scoreboardContains("(M1)")) {
                this.floor = DungeonTypes.M1;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M2)")) {
                this.floor = DungeonTypes.M2;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M3)")) {
                this.floor = DungeonTypes.M3;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M4)")) {
                this.floor = DungeonTypes.M4;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M5)")) {
                this.floor = DungeonTypes.M5;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M6)")) {
                this.floor = DungeonTypes.M6;
                Client.isMMD = true;
            }
            else if (ScoreboardUtils.scoreboardContains("(M7)")) {
                this.floor = DungeonTypes.M7;
                Client.isMMD = true;
            }
            else if ((ScoreboardUtils.scoreboardContains("Dragon") || ScoreboardUtils.scoreboardContains("No Alive Dragons")) && ScoreboardUtils.scoreboardContains("Time Elapsed:") && ScoreboardUtils.scoreboardContains("Cleard: ")) {
                this.floor = DungeonTypes.M7;
                Client.isMMD = true;
            }
            else {
                this.floor = DungeonTypes.NULL;
                Client.isMMD = false;
            }
        }
        else {
            this.floor = DungeonTypes.NULL;
            Client.isMMD = false;
        }
    }
    
    public void updateStats(final List<String> tabList) {
        try {
            for (String item : tabList) {
                if (item.contains("Deaths: ")) {
                    item = StringUtils.stripControlCodes(item);
                    final String justNumbers = this.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    this.deaths = Integer.parseInt(justNumbers);
                }
                else if (item.contains("Secrets Found: ") && !item.contains("%")) {
                    item = StringUtils.stripControlCodes(item);
                    final String justNumbers = this.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    this.secretsFound = Integer.parseInt(justNumbers);
                }
                else {
                    if (!item.contains("Crypts: ")) {
                        continue;
                    }
                    item = StringUtils.stripControlCodes(item);
                    final String justNumbers = this.removeAllExceptNumbersAndPeriods(item);
                    if (justNumbers.isEmpty()) {
                        continue;
                    }
                    this.cryptsFound = Integer.parseInt(justNumbers);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            Client.instance.logger.error("Exception in class DungeonListener.");
        }
    }
    
    public List<String> getTabList() {
        final List<String> tabList = PlayerListUtils.getTabListListStr();
        if (!StringUtils.stripControlCodes((String)tabList.get(0)).contains("Party")) {
            return null;
        }
        return tabList;
    }
    
    public boolean inFloor(final DungeonTypes... floors) {
        for (final DungeonTypes floorToCheck : floors) {
            if (floorToCheck == this.floor) {
                return true;
            }
        }
        return false;
    }
    
    public void debug() {
        if (Client.inDungeons) {
            Helper.sendMessage("Floor: " + this.floor.name());
            Helper.sendMessage("In Boss: " + this.inBoss);
            Helper.sendMessage("Teams:");
            for (final EntityPlayer teammate : this.teammates) {
                Helper.sendMessage("- " + teammate.getName());
            }
        }
        else {
            Helper.sendMessage("You must be in a dungeon to debug a dungeon!");
        }
    }
    
    public String removeAllExceptNumbersAndPeriods(final String text) {
        return this.numberPattern.matcher(text).replaceAll("");
    }
}
