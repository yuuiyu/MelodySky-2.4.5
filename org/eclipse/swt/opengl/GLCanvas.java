//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.opengl;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.opengl.win32.*;
import org.eclipse.swt.widgets.*;

public class GLCanvas extends Canvas
{
    long context;
    int pixelFormat;
    static final String USE_OWNDC_KEY = "org.eclipse.swt.internal.win32.useOwnDC";
    
    public GLCanvas(final Composite parent, final int style, final GLData data) {
        super(parent, checkStyle(parent, style));
        parent.getDisplay().setData("org.eclipse.swt.internal.win32.useOwnDC", false);
        if (data == null) {
            SWT.error(4);
        }
        final PIXELFORMATDESCRIPTOR pfd = new PIXELFORMATDESCRIPTOR();
        pfd.nSize = 40;
        pfd.nVersion = 1;
        pfd.dwFlags = 36;
        pfd.dwLayerMask = 0;
        pfd.iPixelType = 0;
        if (data.doubleBuffer) {
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor3;
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor = pixelformatdescriptor3 = pfd;
            pixelformatdescriptor3.dwFlags |= 0x1;
        }
        if (data.stereo) {
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor4;
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor2 = pixelformatdescriptor4 = pfd;
            pixelformatdescriptor4.dwFlags |= 0x2;
        }
        pfd.cRedBits = (byte)data.redSize;
        pfd.cGreenBits = (byte)data.greenSize;
        pfd.cBlueBits = (byte)data.blueSize;
        pfd.cAlphaBits = (byte)data.alphaSize;
        pfd.cDepthBits = (byte)data.depthSize;
        pfd.cStencilBits = (byte)data.stencilSize;
        pfd.cAccumRedBits = (byte)data.accumRedSize;
        pfd.cAccumGreenBits = (byte)data.accumGreenSize;
        pfd.cAccumBlueBits = (byte)data.accumBlueSize;
        pfd.cAccumAlphaBits = (byte)data.accumAlphaSize;
        pfd.cAccumBits = (byte)(pfd.cAccumRedBits + pfd.cAccumGreenBits + pfd.cAccumBlueBits + pfd.cAccumAlphaBits);
        final long hDC = OS.GetDC(this.handle);
        this.pixelFormat = WGL.ChoosePixelFormat(hDC, pfd);
        if (this.pixelFormat == 0 || !WGL.SetPixelFormat(hDC, this.pixelFormat, pfd)) {
            OS.ReleaseDC(this.handle, hDC);
            this.dispose();
            SWT.error(38);
        }
        this.context = WGL.wglCreateContext(hDC);
        if (this.context == 0L) {
            OS.ReleaseDC(this.handle, hDC);
            SWT.error(2);
        }
        OS.ReleaseDC(this.handle, hDC);
        if (data.shareContext != null) {
            WGL.wglShareLists(data.shareContext.context, this.context);
        }
        final Listener listener = event -> {
            switch (event.type) {
                case 12: {
                    WGL.wglDeleteContext(this.context);
                    break;
                }
            }
            return;
        };
        this.addListener(12, listener);
    }
    
    static int checkStyle(final Composite parent, final int style) {
        if (parent != null) {
            parent.getDisplay().setData("org.eclipse.swt.internal.win32.useOwnDC", true);
        }
        return style;
    }
    
    public GLData getGLData() {
        this.checkWidget();
        final GLData data = new GLData();
        final PIXELFORMATDESCRIPTOR pfd = new PIXELFORMATDESCRIPTOR();
        final long hDC = OS.GetDC(this.handle);
        WGL.DescribePixelFormat(hDC, this.pixelFormat, 40, pfd);
        OS.ReleaseDC(this.handle, hDC);
        data.doubleBuffer = ((pfd.dwFlags & 0x1) != 0x0);
        data.stereo = ((pfd.dwFlags & 0x2) != 0x0);
        data.redSize = pfd.cRedBits;
        data.greenSize = pfd.cGreenBits;
        data.blueSize = pfd.cBlueBits;
        data.alphaSize = pfd.cAlphaBits;
        data.depthSize = pfd.cDepthBits;
        data.stencilSize = pfd.cStencilBits;
        data.accumRedSize = pfd.cAccumRedBits;
        data.accumGreenSize = pfd.cAccumGreenBits;
        data.accumBlueSize = pfd.cAccumBlueBits;
        data.accumAlphaSize = pfd.cAccumAlphaBits;
        return data;
    }
    
    public boolean isCurrent() {
        this.checkWidget();
        return WGL.wglGetCurrentContext() == this.context;
    }
    
    public void setCurrent() {
        this.checkWidget();
        if (WGL.wglGetCurrentContext() == this.context) {
            return;
        }
        final long hDC = OS.GetDC(this.handle);
        WGL.wglMakeCurrent(hDC, this.context);
        OS.ReleaseDC(this.handle, hDC);
    }
    
    public void swapBuffers() {
        this.checkWidget();
        final long hDC = OS.GetDC(this.handle);
        WGL.SwapBuffers(hDC);
        OS.ReleaseDC(this.handle, hDC);
    }
}
