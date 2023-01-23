//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

public class SashForm extends Composite
{
    public int SASH_WIDTH;
    int sashStyle;
    Sash[] sashes;
    Color background;
    Color foreground;
    Control[] controls;
    Control maxControl;
    Listener sashListener;
    static final int DRAG_MINIMUM = 20;
    
    public SashForm(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.SASH_WIDTH = 3;
        this.sashes = new Sash[0];
        this.background = null;
        this.foreground = null;
        this.controls = new Control[0];
        this.maxControl = null;
        super.setLayout(new SashFormLayout());
        this.sashStyle = (((style & 0x200) != 0x0) ? 256 : 512);
        if ((style & 0x800) != 0x0) {
            this.sashStyle |= 0x800;
        }
        if ((style & 0x10000) != 0x0) {
            this.sashStyle |= 0x10000;
        }
        this.sashListener = this::onDragSash;
    }
    
    static int checkStyle(final int style) {
        final int mask = 100665344;
        return style & 0x6000800;
    }
    
    Sash createSash() {
        final Sash sash = new Sash(this, this.sashStyle);
        sash.setBackground(this.background);
        sash.setForeground(this.foreground);
        sash.setToolTipText(this.getToolTipText());
        sash.addListener(13, this.sashListener);
        return sash;
    }
    
    @Override
    public int getOrientation() {
        return ((this.sashStyle & 0x200) != 0x0) ? 256 : 512;
    }
    
    public int getSashWidth() {
        this.checkWidget();
        return this.SASH_WIDTH;
    }
    
    @Override
    public int getStyle() {
        int style = super.getStyle();
        style |= ((this.getOrientation() == 512) ? 512 : 256);
        if ((this.sashStyle & 0x10000) != 0x0) {
            style |= 0x10000;
        }
        return style;
    }
    
    public Control getMaximizedControl() {
        return this.maxControl;
    }
    
    public int[] getWeights() {
        this.checkWidget();
        final Control[] cArray = this.getControls(false);
        final int[] ratios = new int[cArray.length];
        for (int i = 0; i < cArray.length; ++i) {
            final Object data = cArray[i].getLayoutData();
            if (data instanceof SashFormData) {
                ratios[i] = (int)(((SashFormData)data).weight * 1000L >> 16);
            }
            else {
                ratios[i] = 200;
            }
        }
        return ratios;
    }
    
    Control[] getControls(final boolean onlyVisible) {
        Control[] result = new Control[0];
        for (final Control element : this.getChildren()) {
            if (!(element instanceof Sash) && (!onlyVisible || element.getVisible())) {
                final Control[] newResult = new Control[result.length + 1];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[result.length] = element;
                result = newResult;
            }
        }
        return result;
    }
    
    void onDragSash(final Event event) {
        final Sash sash = (Sash)event.widget;
        int sashIndex = -1;
        for (int i = 0; i < this.sashes.length; ++i) {
            if (this.sashes[i] == sash) {
                sashIndex = i;
                break;
            }
        }
        if (sashIndex == -1) {
            return;
        }
        final Control c1 = this.controls[sashIndex];
        final Control c2 = this.controls[sashIndex + 1];
        final Rectangle b1 = c1.getBounds();
        final Rectangle b2 = c2.getBounds();
        final Rectangle sashBounds = sash.getBounds();
        final Rectangle area = this.getClientArea();
        boolean correction = false;
        if (this.getOrientation() == 256) {
            correction = (b1.width < 20 || b2.width < 20);
            final int totalWidth = b2.x + b2.width - b1.x;
            final int shift = event.x - sashBounds.x;
            final Rectangle rectangle7;
            final Rectangle rectangle = rectangle7 = b1;
            rectangle7.width += shift;
            final Rectangle rectangle8;
            final Rectangle rectangle2 = rectangle8 = b2;
            rectangle8.x += shift;
            final Rectangle rectangle9;
            final Rectangle rectangle3 = rectangle9 = b2;
            rectangle9.width -= shift;
            if (b1.width < 20) {
                b1.width = 20;
                b2.x = b1.x + b1.width + sashBounds.width;
                b2.width = totalWidth - b2.x;
                event.x = b1.x + b1.width;
                event.doit = false;
            }
            if (b2.width < 20) {
                b1.width = totalWidth - 20 - sashBounds.width;
                b2.x = b1.x + b1.width + sashBounds.width;
                b2.width = 20;
                event.x = b1.x + b1.width;
                event.doit = false;
            }
            Object data1 = c1.getLayoutData();
            if (data1 == null || !(data1 instanceof SashFormData)) {
                data1 = new SashFormData();
                c1.setLayoutData(data1);
            }
            Object data2 = c2.getLayoutData();
            if (data2 == null || !(data2 instanceof SashFormData)) {
                data2 = new SashFormData();
                c2.setLayoutData(data2);
            }
            ((SashFormData)data1).weight = (((long)b1.width << 16) + area.width - 1L) / area.width;
            ((SashFormData)data2).weight = (((long)b2.width << 16) + area.width - 1L) / area.width;
        }
        else {
            correction = (b1.height < 20 || b2.height < 20);
            final int totalHeight = b2.y + b2.height - b1.y;
            final int shift = event.y - sashBounds.y;
            final Rectangle rectangle10;
            final Rectangle rectangle4 = rectangle10 = b1;
            rectangle10.height += shift;
            final Rectangle rectangle11;
            final Rectangle rectangle5 = rectangle11 = b2;
            rectangle11.y += shift;
            final Rectangle rectangle12;
            final Rectangle rectangle6 = rectangle12 = b2;
            rectangle12.height -= shift;
            if (b1.height < 20) {
                b1.height = 20;
                b2.y = b1.y + b1.height + sashBounds.height;
                b2.height = totalHeight - b2.y;
                event.y = b1.y + b1.height;
                event.doit = false;
            }
            if (b2.height < 20) {
                b1.height = totalHeight - 20 - sashBounds.height;
                b2.y = b1.y + b1.height + sashBounds.height;
                b2.height = 20;
                event.y = b1.y + b1.height;
                event.doit = false;
            }
            Object data1 = c1.getLayoutData();
            if (data1 == null || !(data1 instanceof SashFormData)) {
                data1 = new SashFormData();
                c1.setLayoutData(data1);
            }
            Object data2 = c2.getLayoutData();
            if (data2 == null || !(data2 instanceof SashFormData)) {
                data2 = new SashFormData();
                c2.setLayoutData(data2);
            }
            ((SashFormData)data1).weight = (((long)b1.height << 16) + area.height - 1L) / area.height;
            ((SashFormData)data2).weight = (((long)b2.height << 16) + area.height - 1L) / area.height;
        }
        if (correction || (event.doit && event.detail != 1)) {
            c1.setBounds(b1);
            sash.setBounds(event.x, event.y, event.width, event.height);
            c2.setBounds(b2);
        }
    }
    
    @Override
    public void setOrientation(final int orientation) {
        this.checkWidget();
        if (orientation == 67108864 || orientation == 33554432) {
            super.setOrientation(orientation);
            return;
        }
        if (this.getOrientation() == orientation) {
            return;
        }
        if (orientation != 256 && orientation != 512) {
            SWT.error(5);
        }
        this.sashStyle &= 0xFFFFFCFF;
        this.sashStyle |= ((orientation == 512) ? 256 : 512);
        for (int i = 0; i < this.sashes.length; ++i) {
            this.sashes[i].dispose();
            this.sashes[i] = this.createSash();
        }
        this.layout(false);
    }
    
    @Override
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.background = color;
        for (final Sash sash : this.sashes) {
            sash.setBackground(this.background);
        }
    }
    
    @Override
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.foreground = color;
        for (final Sash sash : this.sashes) {
            sash.setForeground(this.foreground);
        }
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMaximizedControl(final Control control) {
        this.checkWidget();
        if (control == null) {
            if (this.maxControl != null) {
                this.maxControl = null;
                this.layout(false);
                for (final Sash sashe : this.sashes) {
                    sashe.setVisible(true);
                }
            }
            return;
        }
        for (final Sash sash : this.sashes) {
            sash.setVisible(false);
        }
        this.maxControl = control;
        this.layout(false);
    }
    
    public void setSashWidth(final int width) {
        this.checkWidget();
        if (this.SASH_WIDTH == width) {
            return;
        }
        this.SASH_WIDTH = width;
        this.layout(false);
    }
    
    @Override
    public void setToolTipText(final String string) {
        super.setToolTipText(string);
        for (final Sash sash : this.sashes) {
            sash.setToolTipText(string);
        }
    }
    
    public void setWeights(final int... weights) {
        this.checkWidget();
        final Control[] cArray = this.getControls(false);
        if (weights == null || weights.length != cArray.length) {
            SWT.error(5);
        }
        int total = 0;
        for (final int weight : weights) {
            if (weight < 0) {
                SWT.error(5);
            }
            total += weight;
        }
        if (total == 0) {
            SWT.error(5);
        }
        for (int i = 0; i < cArray.length; ++i) {
            Object data = cArray[i].getLayoutData();
            if (data == null || !(data instanceof SashFormData)) {
                data = new SashFormData();
                cArray[i].setLayoutData(data);
            }
            ((SashFormData)data).weight = (((long)weights[i] << 16) + total - 1L) / total;
        }
        this.layout(false);
    }
}
