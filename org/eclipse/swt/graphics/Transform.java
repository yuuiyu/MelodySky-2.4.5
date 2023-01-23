//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gdip.*;
import org.eclipse.swt.*;

public class Transform extends Resource
{
    public long handle;
    
    public Transform(final Device device) {
        this(device, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    public Transform(final Device device, final float[] elements) {
        this(device, checkTransform(elements)[0], elements[1], elements[2], elements[3], elements[4], elements[5]);
    }
    
    public Transform(final Device device, final float m11, final float m12, final float m21, final float m22, final float dx, final float dy) {
        super(device);
        this.device.checkGDIP();
        this.handle = Gdip.Matrix_new(m11, m12, m21, m22, DPIUtil.autoScaleUp((Drawable)this.device, dx), DPIUtil.autoScaleUp((Drawable)this.device, dy));
        if (this.handle == 0L) {
            SWT.error(2);
        }
        this.init();
    }
    
    static float[] checkTransform(final float[] elements) {
        if (elements == null) {
            SWT.error(4);
        }
        if (elements.length < 6) {
            SWT.error(5);
        }
        return elements;
    }
    
    void destroy() {
        Gdip.Matrix_delete(this.handle);
        this.handle = 0L;
    }
    
    public void getElements(final float[] elements) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (elements == null) {
            SWT.error(4);
        }
        if (elements.length < 6) {
            SWT.error(5);
        }
        Gdip.Matrix_GetElements(this.handle, elements);
        final Drawable drawable = (Drawable)this.getDevice();
        elements[4] = DPIUtil.autoScaleDown(drawable, elements[4]);
        elements[5] = DPIUtil.autoScaleDown(drawable, elements[5]);
    }
    
    public void identity() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_SetElements(this.handle, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    public void invert() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (Gdip.Matrix_Invert(this.handle) != 0) {
            SWT.error(10);
        }
    }
    
    public boolean isDisposed() {
        return this.handle == 0L;
    }
    
    public boolean isIdentity() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return Gdip.Matrix_IsIdentity(this.handle);
    }
    
    public void multiply(final Transform matrix) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (matrix == null) {
            SWT.error(4);
        }
        if (matrix.isDisposed()) {
            SWT.error(5);
        }
        Gdip.Matrix_Multiply(this.handle, matrix.handle, 0);
    }
    
    public void rotate(final float angle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Rotate(this.handle, angle, 0);
    }
    
    public void scale(final float scaleX, final float scaleY) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Scale(this.handle, scaleX, scaleY, 0);
    }
    
    public void setElements(final float m11, final float m12, final float m21, final float m22, final float dx, final float dy) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final Drawable drawable = (Drawable)this.getDevice();
        Gdip.Matrix_SetElements(this.handle, m11, m12, m21, m22, DPIUtil.autoScaleUp(drawable, dx), DPIUtil.autoScaleUp(drawable, dy));
    }
    
    public void shear(final float shearX, final float shearY) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Shear(this.handle, shearX, shearY, 0);
    }
    
    public void transform(final float[] pointArray) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (pointArray == null) {
            SWT.error(4);
        }
        final int length = pointArray.length;
        final Drawable drawable = (Drawable)this.getDevice();
        for (int i = 0; i < length; ++i) {
            pointArray[i] = DPIUtil.autoScaleUp(drawable, pointArray[i]);
        }
        Gdip.Matrix_TransformPoints(this.handle, pointArray, length / 2);
        for (int i = 0; i < length; ++i) {
            pointArray[i] = DPIUtil.autoScaleDown(drawable, pointArray[i]);
        }
    }
    
    public void translate(final float offsetX, final float offsetY) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final Drawable drawable = (Drawable)this.getDevice();
        Gdip.Matrix_Translate(this.handle, DPIUtil.autoScaleUp(drawable, offsetX), DPIUtil.autoScaleUp(drawable, offsetY), 0);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Transform {*DISPOSED*}";
        }
        final float[] elements = new float[6];
        this.getElements(elements);
        return "Transform {" + elements[0] + "," + elements[1] + "," + elements[2] + "," + elements[3] + "," + elements[4] + "," + elements[5];
    }
}
