//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.widgets.*;

public class OleEvent
{
    public int type;
    public Widget widget;
    public int detail;
    public boolean doit;
    public Variant[] arguments;
    
    public OleEvent() {
        this.doit = true;
    }
}
