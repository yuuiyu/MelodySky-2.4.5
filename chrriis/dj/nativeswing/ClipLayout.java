//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.io.*;
import java.awt.*;

class ClipLayout implements LayoutManager2, Serializable
{
    private Component component;
    private Rectangle clip;
    
    @Override
    public void addLayoutComponent(final Component component, Object constraints) {
        synchronized (component.getTreeLock()) {
            if (constraints != null && !(constraints instanceof String)) {
                constraints = null;
            }
            this.addLayoutComponent((String)constraints, component);
        }
    }
    
    @Deprecated
    @Override
    public void addLayoutComponent(final String name, final Component component) {
        synchronized (component.getTreeLock()) {
            this.component = component;
        }
    }
    
    @Override
    public void removeLayoutComponent(final Component component) {
        synchronized (component.getTreeLock()) {
            if (component == this.component) {
                this.component = null;
            }
        }
    }
    
    @Override
    public void layoutContainer(final Container target) {
        synchronized (target.getTreeLock()) {
            if (this.component == null) {
                return;
            }
            final Insets insets = target.getInsets();
            int top = insets.top;
            int left = insets.left;
            int width;
            int height;
            if (this.clip != null) {
                left += this.clip.x;
                top += this.clip.y;
                width = this.clip.width;
                height = this.clip.height;
            }
            else {
                final int right = target.getWidth() - insets.right;
                width = right - left;
                final int bottom = target.getHeight() - insets.bottom;
                height = bottom - top;
            }
            this.component.setBounds(left, top, width, height);
        }
    }
    
    @Override
    public Dimension minimumLayoutSize(final Container target) {
        synchronized (target.getTreeLock()) {
            final Insets insets = target.getInsets();
            final Dimension size = new Dimension(insets.left + insets.right, insets.top + insets.bottom);
            if (this.component != null) {
                final Dimension d = this.component.getMinimumSize();
                final Dimension dimension = size;
                dimension.width += d.width;
                final Dimension dimension2 = size;
                dimension2.height += d.height;
            }
            return size;
        }
    }
    
    @Override
    public Dimension preferredLayoutSize(final Container target) {
        synchronized (target.getTreeLock()) {
            final Insets insets = target.getInsets();
            final Dimension size = new Dimension(insets.left + insets.right, insets.top + insets.bottom);
            if (this.component != null) {
                final Dimension d = this.component.getPreferredSize();
                final Dimension dimension = size;
                dimension.width += d.width;
                final Dimension dimension2 = size;
                dimension2.height += d.height;
            }
            return size;
        }
    }
    
    @Override
    public Dimension maximumLayoutSize(final Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    @Override
    public void invalidateLayout(final Container target) {
    }
    
    @Override
    public float getLayoutAlignmentX(final Container parent) {
        return 0.5f;
    }
    
    @Override
    public float getLayoutAlignmentY(final Container parent) {
        return 0.5f;
    }
    
    public void setClip(final Rectangle clip) {
        this.clip = clip;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + ",clip=" + ((this.clip == null) ? "" : this.clip.toString());
    }
}
