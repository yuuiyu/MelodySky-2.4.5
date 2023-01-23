//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.*;
import java.awt.*;

public abstract class VLCPlayerDecorator extends JPanel
{
    public VLCPlayerDecorator() {
        super(new BorderLayout());
    }
    
    public abstract void setControlBarVisible(final boolean p0);
    
    public abstract boolean isControlBarVisible();
}
