//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

final class llIl extends ScalableGame
{
    llIl(final Game held, final int normalWidth, final int normalHeight, final boolean maintainAspect) {
        super(held, normalWidth, normalHeight, maintainAspect);
    }
    
    protected void renderOverlay(final GameContainer container, final Graphics g) {
        g.setColor(Color.white);
        g.drawString("Outside The Game", 350.0f, 10.0f);
        g.drawString(container.getInput().getMouseX() + "," + container.getInput().getMouseY(), 400.0f, 20.0f);
    }
}
