//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import java.io.*;
import java.util.*;

public class DefaultLogSystem implements LogSystem
{
    public static PrintStream out;
    
    @Override
    public void error(final String message, final Throwable e) {
        this.error(message);
        this.error(e);
    }
    
    @Override
    public void error(final Throwable e) {
        DefaultLogSystem.out.println(new Date() + " ERROR:" + e.getMessage());
        e.printStackTrace(DefaultLogSystem.out);
    }
    
    @Override
    public void error(final String message) {
        DefaultLogSystem.out.println(new Date() + " ERROR:" + message);
    }
    
    @Override
    public void warn(final String message) {
        DefaultLogSystem.out.println(new Date() + " WARN:" + message);
    }
    
    @Override
    public void info(final String message) {
        DefaultLogSystem.out.println(new Date() + " INFO:" + message);
    }
    
    @Override
    public void debug(final String message) {
        DefaultLogSystem.out.println(new Date() + " DEBUG:" + message);
    }
    
    @Override
    public void warn(final String message, final Throwable e) {
        this.warn(message);
        e.printStackTrace(DefaultLogSystem.out);
    }
    
    static {
        DefaultLogSystem.out = System.out;
    }
}
