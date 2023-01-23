//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Gaming;

import xyz.Melody.System.Managers.*;
import net.minecraft.client.*;
import xyz.Melody.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Event.*;
import xyz.Melody.Utils.other.*;
import xyz.Melody.Utils.*;
import net.minecraft.scoreboard.*;

public class GameListenerHook implements Manager
{
    private Minecraft mc;
    private Thread clientMainThread;
    
    public GameListenerHook() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void init() {
        Client.instance.logger.info("[Melody] [CONSOLE] Initializing Melody -> Main Thread.");
        this.initMainThread();
        EventBus.getInstance().register(new Object[] { this });
    }
    
    @EventHandler
    private void onFR(final EventDrawText event) {
        if (Client.playerName != null) {
            event.setText(event.getText().replaceAll(this.mc.getSession().getUsername(), Client.playerName));
        }
        if (event.getText().contains("Wither Impact")) {
            event.setText(event.getText().replaceAll("Wither Impact", "Genshin Impact"));
        }
    }
    
    private void initMainThread() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Ljava/lang/Thread;
        //     4: dup            
        //     5: invokedynamic   BootstrapMethod #0, run:()Ljava/lang/Runnable;
        //    10: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
        //    13: putfield        xyz/Melody/System/Managers/Gaming/GameListenerHook.clientMainThread:Ljava/lang/Thread;
        //    16: aload_0         /* this */
        //    17: getfield        xyz/Melody/System/Managers/Gaming/GameListenerHook.clientMainThread:Ljava/lang/Thread;
        //    20: ldc             "Melody -> Main Thread"
        //    22: invokevirtual   java/lang/Thread.setName:(Ljava/lang/String;)V
        //    25: aload_0         /* this */
        //    26: getfield        xyz/Melody/System/Managers/Gaming/GameListenerHook.clientMainThread:Ljava/lang/Thread;
        //    29: invokevirtual   java/lang/Thread.start:()V
        //    32: return         
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
}
