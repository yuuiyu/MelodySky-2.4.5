//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module;

import xyz.Melody.System.Commands.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.math.*;
import xyz.Melody.Event.value.*;
import java.util.*;

class l extends Command
{
    private final Module m;
    final Module this$0;
    
    l(final Module var1, final String $anonymous0, final String[] $anonymous1, final String $anonymous2, final String $anonymous3) {
        super($anonymous0, $anonymous1, $anonymous2, $anonymous3);
        this.this$0 = var1;
        this.m = var1;
    }
    
    @Override
    public String execute(final String[] args) {
        if (args.length >= 2) {
            Option<Boolean> option = null;
            Numbers<Double> fuck = null;
            Mode<?> xd = null;
            for (final Value<?> v : this.m.values) {
                if (v instanceof Option && v.getName().equalsIgnoreCase(args[0])) {
                    option = (Option<Boolean>)v;
                }
            }
            if (option != null) {
                option.setValue((Object)!(boolean)option.getValue());
                Helper.sendMessage(String.format("> %s has been set to %s", option.getName(), option.getValue()));
            }
            else {
                for (final Value<?> v : this.m.values) {
                    if (v instanceof Numbers && v.getName().equalsIgnoreCase(args[0])) {
                        fuck = (Numbers<Double>)v;
                    }
                }
                if (fuck != null) {
                    if (MathUtil.parsable(args[1], (byte)4)) {
                        final double v2 = MathUtil.round(Double.parseDouble(args[1]), 1);
                        fuck.setValue((Object)v2);
                        Helper.sendMessage(String.format("> %s has been set to %s", fuck.getName(), fuck.getValue()));
                    }
                    else {
                        Helper.sendMessage("> " + args[1] + " is not a number");
                    }
                }
                for (final Value<?> v : this.m.values) {
                    if (args[0].equalsIgnoreCase(v.getDisplayName()) && v instanceof Mode) {
                        xd = (Mode<?>)v;
                    }
                }
                if (xd != null) {
                    if (xd.isValid(args[1])) {
                        xd.setMode(args[1]);
                        Helper.sendMessage(String.format("> %s set to %s", xd.getName(), xd.getModeAsString()));
                    }
                    else {
                        Helper.sendMessage("> " + args[1] + " is an invalid mode");
                    }
                }
            }
            if (fuck == null && option == null && xd == null) {
                this.syntaxError("Valid .<module> <setting> <mode if needed>");
            }
        }
        else if (args.length >= 1) {
            Option<Boolean> option = null;
            for (final Value<?> fuck2 : this.m.values) {
                if (fuck2 instanceof Option && fuck2.getName().equalsIgnoreCase(args[0])) {
                    option = (Option<Boolean>)fuck2;
                }
            }
            if (option != null) {
                option.setValue((Object)!(boolean)option.getValue());
                final String fuck3 = option.getName().substring(1);
                final String xd3 = option.getName().substring(0, 1).toUpperCase();
                if (option.getValue()) {
                    Helper.sendMessage(String.format("> %s has been set to §a%s", xd3 + fuck3, option.getValue()));
                }
                else {
                    Helper.sendMessage(String.format("> %s has been set to §c%s", xd3 + fuck3, option.getValue()));
                }
            }
            else {
                this.syntaxError("Valid .<module> <setting> <mode if needed>");
            }
        }
        else {
            Helper.sendMessage(String.format("%s Values: \n %s", this.getName().substring(0, 1).toUpperCase() + this.getName().substring(1).toLowerCase(), this.getSyntax(), "false"));
        }
        return null;
    }
}
