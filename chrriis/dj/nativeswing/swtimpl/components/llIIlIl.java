//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.border.*;
import java.awt.*;

final class llIIlIl extends AbstractBorder
{
    @Override
    public Insets getBorderInsets(final Component c) {
        return new Insets(1, 1, 1, 1);
    }
    
    @Override
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        final Color background = c.getBackground();
        g.setColor((background == null) ? Color.LIGHT_GRAY : background.darker());
        g.drawLine(0, 0, width - 1, 0);
        g.drawLine(width - 1, 0, width - 1, height - 1);
        g.drawLine(0, height - 1, width - 1, height - 1);
        g.drawLine(0, 0, 0, height - 1);
    }
}
