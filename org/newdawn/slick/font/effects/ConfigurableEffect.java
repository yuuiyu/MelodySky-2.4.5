//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import java.util.*;

public interface ConfigurableEffect extends Effect
{
    List getValues();
    
    void setValues(final List p0);
    
    public interface Value
    {
        String getName();
        
        void setString(final String p0);
        
        String getString();
        
        Object getObject();
        
        void showDialog();
    }
}
