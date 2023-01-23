//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Commands;

import net.minecraft.client.*;
import xyz.Melody.Utils.*;

public abstract class Command
{
    private String name;
    private String[] alias;
    private String syntax;
    private String help;
    public Minecraft mc;
    
    public Command(final String name, final String[] alias, final String syntax, final String help) {
        this.mc = Minecraft.getMinecraft();
        this.name = name.toLowerCase();
        this.syntax = syntax.toLowerCase();
        this.help = help;
        this.alias = alias;
    }
    
    public abstract String execute(final String[] p0);
    
    public String getName() {
        return this.name;
    }
    
    public String[] getAlias() {
        return this.alias;
    }
    
    public String getSyntax() {
        return this.syntax;
    }
    
    public String getHelp() {
        return this.help;
    }
    
    public void syntaxError(final String msg) {
        Helper.sendMessage(String.format("§7Invalid command usage", msg));
    }
    
    public void syntaxError(final byte errorType) {
        switch (errorType) {
            case 0: {
                this.syntaxError("bad argument");
                break;
            }
            case 1: {
                this.syntaxError("argument gay");
                break;
            }
        }
    }
}
