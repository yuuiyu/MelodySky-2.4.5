//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class SashFormLayout extends Layout
{
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final SashForm sashForm = (SashForm)composite;
        final Control[] cArray = sashForm.getControls(true);
        int width = 0;
        int height = 0;
        if (cArray.length == 0) {
            if (wHint != -1) {
                width = wHint;
            }
            if (hHint != -1) {
                height = hHint;
            }
            return new Point(width, height);
        }
        final boolean vertical = sashForm.getOrientation() == 512;
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < cArray.length; ++i) {
            if (vertical) {
                final Point size = cArray[i].computeSize(wHint, -1, flushCache);
                if (size.y > maxValue) {
                    maxIndex = i;
                    maxValue = size.y;
                }
                width = Math.max(width, size.x);
            }
            else {
                final Point size = cArray[i].computeSize(-1, hHint, flushCache);
                if (size.x > maxValue) {
                    maxIndex = i;
                    maxValue = size.x;
                }
                height = Math.max(height, size.y);
            }
        }
        final long[] ratios = new long[cArray.length];
        long total = 0L;
        for (int j = 0; j < cArray.length; ++j) {
            Object data = cArray[j].getLayoutData();
            if (data instanceof SashFormData) {
                ratios[j] = ((SashFormData)data).weight;
            }
            else {
                data = new SashFormData();
                cArray[j].setLayoutData(data);
                final SashFormData sashFormData = (SashFormData)data;
                final long[] array = ratios;
                final int n = j;
                final long weight = 13108L;
                array[n] = 13108L;
                sashFormData.weight = 13108L;
            }
            total += ratios[j];
        }
        if (ratios[maxIndex] > 0L) {
            final int sashwidth = (sashForm.sashes.length > 0) ? (sashForm.SASH_WIDTH + sashForm.sashes[0].getBorderWidth() * 2) : sashForm.SASH_WIDTH;
            if (vertical) {
                height += (int)(total * maxValue / ratios[maxIndex]) + (cArray.length - 1) * sashwidth;
            }
            else {
                width += (int)(total * maxValue / ratios[maxIndex]) + (cArray.length - 1) * sashwidth;
            }
        }
        width += sashForm.getBorderWidth() * 2;
        height += sashForm.getBorderWidth() * 2;
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
        final SashForm sashForm = (SashForm)composite;
        final Rectangle area = sashForm.getClientArea();
        if (area.width <= 1 || area.height <= 1) {
            return;
        }
        final Control[] newControls = sashForm.getControls(true);
        if (sashForm.controls.length == 0 && newControls.length == 0) {
            return;
        }
        sashForm.controls = newControls;
        final Control[] controls = sashForm.controls;
        if (sashForm.maxControl != null && !sashForm.maxControl.isDisposed()) {
            for (final Control control : controls) {
                if (control != sashForm.maxControl) {
                    control.setBounds(-200, -200, 0, 0);
                }
                else {
                    control.setBounds(area);
                }
            }
            return;
        }
        if (sashForm.sashes.length < controls.length - 1) {
            final Sash[] newSashes = new Sash[controls.length - 1];
            System.arraycopy(sashForm.sashes, 0, newSashes, 0, sashForm.sashes.length);
            for (int i = sashForm.sashes.length; i < newSashes.length; ++i) {
                newSashes[i] = sashForm.createSash();
            }
            sashForm.sashes = newSashes;
        }
        if (sashForm.sashes.length > controls.length - 1) {
            if (controls.length == 0) {
                for (final Sash sash : sashForm.sashes) {
                    sash.dispose();
                }
                sashForm.sashes = new Sash[0];
            }
            else {
                final Sash[] newSashes = new Sash[controls.length - 1];
                System.arraycopy(sashForm.sashes, 0, newSashes, 0, newSashes.length);
                for (int i = controls.length - 1; i < sashForm.sashes.length; ++i) {
                    sashForm.sashes[i].dispose();
                }
                sashForm.sashes = newSashes;
            }
        }
        if (controls.length == 0) {
            return;
        }
        final Sash[] sashes = sashForm.sashes;
        final long[] ratios = new long[controls.length];
        long total = 0L;
        for (int j = 0; j < controls.length; ++j) {
            Object data = controls[j].getLayoutData();
            if (data instanceof SashFormData) {
                ratios[j] = ((SashFormData)data).weight;
            }
            else {
                data = new SashFormData();
                controls[j].setLayoutData(data);
                final SashFormData sashFormData = (SashFormData)data;
                final long[] array2 = ratios;
                final int n2 = j;
                final long weight = 13108L;
                array2[n2] = 13108L;
                sashFormData.weight = 13108L;
            }
            total += ratios[j];
        }
        final int sashwidth = (sashes.length > 0) ? (sashForm.SASH_WIDTH + sashes[0].getBorderWidth() * 2) : sashForm.SASH_WIDTH;
        if (sashForm.getOrientation() == 256) {
            int width = (int)(ratios[0] * (area.width - sashes.length * sashwidth) / total);
            int x = area.x;
            controls[0].setBounds(x, area.y, width, area.height);
            x += width;
            for (int k = 1; k < controls.length - 1; ++k) {
                sashes[k - 1].setBounds(x, area.y, sashwidth, area.height);
                x += sashwidth;
                width = (int)(ratios[k] * (area.width - sashes.length * sashwidth) / total);
                controls[k].setBounds(x, area.y, width, area.height);
                x += width;
            }
            if (controls.length > 1) {
                sashes[sashes.length - 1].setBounds(x, area.y, sashwidth, area.height);
                x += sashwidth;
                width = area.width - x;
                controls[controls.length - 1].setBounds(x, area.y, width, area.height);
            }
        }
        else {
            int height = (int)(ratios[0] * (area.height - sashes.length * sashwidth) / total);
            int y = area.y;
            controls[0].setBounds(area.x, y, area.width, height);
            y += height;
            for (int k = 1; k < controls.length - 1; ++k) {
                sashes[k - 1].setBounds(area.x, y, area.width, sashwidth);
                y += sashwidth;
                height = (int)(ratios[k] * (area.height - sashes.length * sashwidth) / total);
                controls[k].setBounds(area.x, y, area.width, height);
                y += height;
            }
            if (controls.length > 1) {
                sashes[sashes.length - 1].setBounds(area.x, y, area.width, sashwidth);
                y += sashwidth;
                height = area.height - y;
                controls[controls.length - 1].setBounds(area.x, y, area.width, height);
            }
        }
    }
}
