//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public class StackLayout extends Layout
{
    public int marginWidth;
    public int marginHeight;
    public Control topControl;
    
    public StackLayout() {
        this.marginWidth = 0;
        this.marginHeight = 0;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        int maxWidth = 0;
        int maxHeight = 0;
        for (final Control element : composite.getChildren()) {
            final Point size = element.computeSize(wHint, hHint, flushCache);
            maxWidth = Math.max(size.x, maxWidth);
            maxHeight = Math.max(size.y, maxHeight);
        }
        int width = maxWidth + 2 * this.marginWidth;
        int height = maxHeight + 2 * this.marginHeight;
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        return new Point(width, height);
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle rect;
        final Rectangle rectangle4;
        final Rectangle clientArea = rectangle4 = (rect = composite.getClientArea());
        rectangle4.x += this.marginWidth;
        final Rectangle rectangle5;
        final Rectangle rectangle = rectangle5 = rect;
        rectangle5.y += this.marginHeight;
        final Rectangle rectangle6;
        final Rectangle rectangle2 = rectangle6 = rect;
        rectangle6.width -= 2 * this.marginWidth;
        final Rectangle rectangle7;
        final Rectangle rectangle3 = rectangle7 = rect;
        rectangle7.height -= 2 * this.marginHeight;
        for (final Control element : composite.getChildren()) {
            element.setBounds(rect);
            element.setVisible(element == this.topControl);
        }
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        if (this.marginWidth != 0) {
            string = string + "marginWidth=" + this.marginWidth;
        }
        if (this.marginHeight != 0) {
            string = string + "marginHeight=" + this.marginHeight;
        }
        if (this.topControl != null) {
            string = string + "topControl=" + this.topControl;
        }
        string = string.trim();
        return string;
    }
}
