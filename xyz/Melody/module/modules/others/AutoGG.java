//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import xyz.Melody.Event.*;
import xyz.Melody.GUI.Notification.*;
import java.util.*;

public class AutoGG extends Module
{
    public AutoGG() {
        super("AutoPlay", new String[] { "AutoPlay", "AutoGG" }, ModuleType.Others);
    }
    
    @EventHandler
    public void onPacket(final EventPacketRecieve event) {
        if (event.getPacket() instanceof S02PacketChat) {
            final S02PacketChat packet = (S02PacketChat)event.getPacket();
            final String game = this.getSubString(packet.getChatComponent().toString(), "style=Style{hasParent=true, color=\ufffd\ufffdb, bold=true, italic=null, underlined=null, obfuscated=null, clickEvent=ClickEvent{action=RUN_COMMAND, value='/play ", "'},");
            if (!game.contains("TextComponent") && !game.equalsIgnoreCase("")) {
                this.next(game);
                this.mc.thePlayer.sendChatMessage("GG");
            }
        }
    }
    
    private String getSubString(final String text, final String left, final String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        }
        else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            }
            else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
    
    private void next(final String game) {
        class l extends TimerTask
        {
            final /* synthetic */ String val$game;
            final /* synthetic */ AutoGG this$0;
            
            l(final AutoGG this$0, final String val$game) {
                this.this$0 = this$0;
                this.val$game = val$game;
            }
            
            @Override
            public void run() {
                this.this$0.mc.thePlayer.sendChatMessage("/play " + this.val$game);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: ldc             "Sending You to a New Game In 3s."
        //     4: getstatic       xyz/Melody/GUI/Notification/NotificationType.INFO:Lxyz/Melody/GUI/Notification/NotificationType;
        //     7: sipush          3000
        //    10: iconst_1       
        //    11: invokestatic    xyz/Melody/GUI/Notification/NotificationPublisher.queue:(Ljava/lang/String;Ljava/lang/String;Lxyz/Melody/GUI/Notification/NotificationType;IZ)V
        //    14: new             Ljava/util/Timer;
        //    17: dup            
        //    18: invokespecial   java/util/Timer.<init>:()V
        //    21: new             Lxyz/Melody/module/modules/others/l;
        //    24: dup            
        //    25: aload_0         /* this */
        //    26: aload_1         /* game */
        //    27: invokespecial   xyz/Melody/module/modules/others/l.<init>:(Lxyz/Melody/module/modules/others/AutoGG;Ljava/lang/String;)V
        //    30: ldc2_w          3000
        //    33: invokevirtual   java/util/Timer.schedule:(Ljava/util/TimerTask;J)V
        //    36: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
