//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.svg;

import org.newdawn.slick.*;
import org.w3c.dom.*;

public class ParsingException extends SlickException
{
    public ParsingException(final String nodeID, final String message, final Throwable cause) {
        super("(" + nodeID + ") " + message, cause);
    }
    
    public ParsingException(final Element element, final String message, final Throwable cause) {
        super("(" + element.getAttribute("id") + ") " + message, cause);
    }
    
    public ParsingException(final String nodeID, final String message) {
        super("(" + nodeID + ") " + message);
    }
    
    public ParsingException(final Element element, final String message) {
        super("(" + element.getAttribute("id") + ") " + message);
    }
}
