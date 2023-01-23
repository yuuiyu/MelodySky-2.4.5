//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Client;

import xyz.Melody.System.Managers.*;
import xyz.Melody.System.Commands.*;
import xyz.Melody.Event.events.misc.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import java.util.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Event.*;

public class CommandManager implements Manager
{
    private List<Command> commands;
    
    @Override
    public void init() {
        class lII extends Command
        {
            final /* synthetic */ CommandManager this$0;
            
            lII(final CommandManager this$0, final String name, final String[] alias, final String syntax, final String help) {
                this.this$0 = this$0;
                super(name, alias, syntax, help);
            }
            
            public String execute(final String[] args) {
                for (Command command : this.this$0.commands) {}
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Ljava/util/ArrayList;
        //     4: dup            
        //     5: invokespecial   java/util/ArrayList.<init>:()V
        //     8: putfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    11: aload_0         /* this */
        //    12: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    15: new             Lxyz/Melody/System/Managers/Client/lII;
        //    18: dup            
        //    19: aload_0         /* this */
        //    20: ldc             "test"
        //    22: iconst_1       
        //    23: anewarray       Ljava/lang/String;
        //    26: dup            
        //    27: iconst_0       
        //    28: ldc             "test"
        //    30: aastore        
        //    31: ldc             ""
        //    33: ldc             "testing"
        //    35: invokespecial   xyz/Melody/System/Managers/Client/lII.<init>:(Lxyz/Melody/System/Managers/Client/CommandManager;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    38: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    43: pop            
        //    44: aload_0         /* this */
        //    45: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    48: new             Lxyz/Melody/System/Commands/commands/AutoRubyCMD;
        //    51: dup            
        //    52: invokespecial   xyz/Melody/System/Commands/commands/AutoRubyCMD.<init>:()V
        //    55: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    60: pop            
        //    61: aload_0         /* this */
        //    62: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    65: new             Lxyz/Melody/System/Commands/commands/RankCommand;
        //    68: dup            
        //    69: invokespecial   xyz/Melody/System/Commands/commands/RankCommand.<init>:()V
        //    72: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    77: pop            
        //    78: aload_0         /* this */
        //    79: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    82: new             Lxyz/Melody/System/Commands/commands/GaoNengCommands;
        //    85: dup            
        //    86: invokespecial   xyz/Melody/System/Commands/commands/GaoNengCommands.<init>:()V
        //    89: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    94: pop            
        //    95: aload_0         /* this */
        //    96: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //    99: new             Lxyz/Melody/System/Commands/commands/AuthMe;
        //   102: dup            
        //   103: invokespecial   xyz/Melody/System/Commands/commands/AuthMe.<init>:()V
        //   106: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   111: pop            
        //   112: aload_0         /* this */
        //   113: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   116: new             Lxyz/Melody/System/Commands/commands/NameCommand;
        //   119: dup            
        //   120: invokespecial   xyz/Melody/System/Commands/commands/NameCommand.<init>:()V
        //   123: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   128: pop            
        //   129: aload_0         /* this */
        //   130: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   133: new             Lxyz/Melody/System/Commands/commands/CustomLbinColor;
        //   136: dup            
        //   137: invokespecial   xyz/Melody/System/Commands/commands/CustomLbinColor.<init>:()V
        //   140: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   145: pop            
        //   146: aload_0         /* this */
        //   147: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   150: new             Lxyz/Melody/System/Commands/commands/ShowItemSBID;
        //   153: dup            
        //   154: invokespecial   xyz/Melody/System/Commands/commands/ShowItemSBID.<init>:()V
        //   157: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   162: pop            
        //   163: aload_0         /* this */
        //   164: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   167: new             Lxyz/Melody/System/Commands/commands/IRCCommands;
        //   170: dup            
        //   171: invokespecial   xyz/Melody/System/Commands/commands/IRCCommands.<init>:()V
        //   174: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   179: pop            
        //   180: aload_0         /* this */
        //   181: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   184: new             Lxyz/Melody/System/Commands/commands/CustomItemSwitch;
        //   187: dup            
        //   188: invokespecial   xyz/Melody/System/Commands/commands/CustomItemSwitch.<init>:()V
        //   191: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   196: pop            
        //   197: aload_0         /* this */
        //   198: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   201: new             Lxyz/Melody/System/Commands/commands/Help;
        //   204: dup            
        //   205: invokespecial   xyz/Melody/System/Commands/commands/Help.<init>:()V
        //   208: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   213: pop            
        //   214: aload_0         /* this */
        //   215: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   218: new             Lxyz/Melody/System/Commands/commands/Toggle;
        //   221: dup            
        //   222: invokespecial   xyz/Melody/System/Commands/commands/Toggle.<init>:()V
        //   225: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   230: pop            
        //   231: aload_0         /* this */
        //   232: getfield        xyz/Melody/System/Managers/Client/CommandManager.commands:Ljava/util/List;
        //   235: new             Lxyz/Melody/System/Commands/commands/Bind;
        //   238: dup            
        //   239: invokespecial   xyz/Melody/System/Commands/commands/Bind.<init>:()V
        //   242: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   247: pop            
        //   248: invokestatic    xyz/Melody/Event/EventBus.getInstance:()Lxyz/Melody/Event/EventBus;
        //   251: iconst_1       
        //   252: anewarray       Ljava/lang/Object;
        //   255: dup            
        //   256: iconst_0       
        //   257: aload_0         /* this */
        //   258: aastore        
        //   259: invokevirtual   xyz/Melody/Event/EventBus.register:([Ljava/lang/Object;)V
        //   262: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public List<Command> getCommands() {
        return this.commands;
    }
    
    public Optional<Command> getCommandByName(final String name) {
        boolean isAlias;
        final String[] arrstring;
        final int n;
        int n2;
        String str;
        return this.commands.stream().filter(c2 -> {
            isAlias = false;
            arrstring = c2.getAlias();
            n = arrstring.length;
            n2 = 0;
            while (n2 < n) {
                str = arrstring[n2];
                if (str.equalsIgnoreCase(name)) {
                    isAlias = true;
                    break;
                }
                else {
                    ++n2;
                }
            }
            return c2.getName().equalsIgnoreCase(name) || isAlias;
        }).findFirst();
    }
    
    public void add(final Command command) {
        this.commands.add(command);
    }
    
    @EventHandler
    private void onChat(final EventChat e) {
        final ClientCommands cmd = (ClientCommands)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)ClientCommands.class);
        String prefix = ".";
        final String string = ((Enum)cmd.mode.getValue()).toString();
        switch (string) {
            case "dot": {
                prefix = ".";
                break;
            }
            case "bar": {
                prefix = "-";
                break;
            }
            case "wavy_line": {
                prefix = "~";
                break;
            }
        }
        if (Client.clientChat && (e.getMessage().length() <= 1 || !e.getMessage().startsWith(prefix)) && !e.getMessage().startsWith("/")) {
            final String msg = e.getMessage().replace("&", "§");
            Client.instance.irc.sendPrefixMsg(msg, true);
            e.setCancelled(true);
            return;
        }
        if (cmd.isEnabled() && e.getMessage().length() > 1 && e.getMessage().startsWith(prefix)) {
            e.setCancelled(true);
            final String[] args = e.getMessage().trim().substring(1).split(" ");
            final Optional<Command> possibleCmd = this.getCommandByName(args[0]);
            if (possibleCmd.isPresent()) {
                final String result = possibleCmd.get().execute((String[])Arrays.copyOfRange(args, 1, args.length));
                if (result != null && !result.isEmpty()) {
                    Helper.sendMessage(result);
                }
            }
            else {
                Helper.sendMessage(String.format("Command not found Try '%shelp'", "."));
            }
        }
    }
}
