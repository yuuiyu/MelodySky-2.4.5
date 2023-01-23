//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

import java.util.*;

public class GameData
{
    private ArrayList entities;
    
    public GameData() {
        this.entities = new ArrayList();
    }
    
    private void add(final Entity entity) {
        this.entities.add(entity);
    }
    
    public void dump(final String prefix) {
        System.out.println(prefix + "GameData");
        for (int i = 0; i < this.entities.size(); ++i) {
            this.entities.get(i).dump(prefix + "\t");
        }
    }
}
