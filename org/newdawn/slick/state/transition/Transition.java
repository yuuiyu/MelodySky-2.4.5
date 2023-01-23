//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.state.transition;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public interface Transition
{
    void update(final StateBasedGame p0, final GameContainer p1, final int p2) throws SlickException;
    
    void preRender(final StateBasedGame p0, final GameContainer p1, final Graphics p2) throws SlickException;
    
    void postRender(final StateBasedGame p0, final GameContainer p1, final Graphics p2) throws SlickException;
    
    boolean isComplete();
    
    void init(final GameState p0, final GameState p1);
}
