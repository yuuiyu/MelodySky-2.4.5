//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public interface ElementProcessor
{
    void process(final Loader p0, final Element p1, final Diagram p2, final Transform p3) throws ParsingException;
    
    boolean handles(final Element p0);
}
