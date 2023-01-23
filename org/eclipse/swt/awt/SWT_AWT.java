//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.awt;

import java.lang.reflect.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import java.awt.*;
import org.eclipse.swt.graphics.*;
import java.awt.event.*;
import org.eclipse.swt.widgets.*;

public class SWT_AWT
{
    public static String embeddedFrameClass;
    static String EMBEDDED_FRAME_KEY;
    static boolean loaded;
    static boolean swingInitialized;
    
    static final native long getAWTHandle(final Canvas p0);
    
    static final native Object initFrame(final long p0, final String p1);
    
    static final native void synthesizeWindowActivation(final Frame p0, final boolean p1);
    
    static synchronized void loadLibrary() {
        if (SWT_AWT.loaded) {
            return;
        }
        SWT_AWT.loaded = true;
        Toolkit.getDefaultToolkit();
        try {
            System.loadLibrary("jawt");
        }
        catch (Throwable t) {}
        Library.loadLibrary("swt-awt");
    }
    
    static synchronized void initializeSwing() {
        if (SWT_AWT.swingInitialized) {
            return;
        }
        SWT_AWT.swingInitialized = true;
        try {
            final Class<?> clazz = Class.forName("javax.swing.UIManager");
            final Method method = clazz.getMethod("getDefaults", (Class<?>[])new Class[0]);
            if (method != null) {
                method.invoke(clazz, new Object[0]);
            }
        }
        catch (Throwable t) {}
    }
    
    public static Frame getFrame(final Composite parent) {
        if (parent == null) {
            SWT.error(4);
        }
        if ((parent.getStyle() & 0x1000000) == 0x0) {
            return null;
        }
        return (Frame)parent.getData(SWT_AWT.EMBEDDED_FRAME_KEY);
    }
    
    public static Frame new_Frame(final Composite parent) {
        if (parent == null) {
            SWT.error(4);
        }
        if ((parent.getStyle() & 0x1000000) == 0x0) {
            SWT.error(5);
        }
        final long handle = parent.handle;
        final Frame[] result = { null };
        final Throwable[] exception = { null };
        String className;
        final Object o;
        final long n;
        Object value;
        Throwable t;
        final int code;
        final int n2;
        final Object o2;
        final Runnable runnable = () -> {
            try {
                className = ((SWT_AWT.embeddedFrameClass != null) ? SWT_AWT.embeddedFrameClass : "sun.awt.windows.WEmbeddedFrame");
                try {
                    if (SWT_AWT.embeddedFrameClass != null) {
                        Class.forName(className);
                    }
                    loadLibrary();
                }
                catch (ClassNotFoundException cne) {
                    SWT.error(20, cne);
                }
                catch (Throwable e2) {
                    o[0] = e2;
                    return;
                }
                initializeSwing();
                value = initFrame(n, className);
                if (value == null || !(value instanceof Frame)) {
                    t = new Throwable("[Error while creating AWT embedded frame]");
                    SWT.error(code, o[n2] = t);
                }
                else {
                    o2[0] = (Frame)value;
                }
            }
            finally {
                synchronized (o2) {
                    o2.notify();
                }
            }
            return;
        };
        if (EventQueue.isDispatchThread() || parent.getDisplay().getSyncThread() != null) {
            runnable.run();
        }
        else {
            EventQueue.invokeLater(runnable);
            OS.ReplyMessage(0L);
            boolean interrupted = false;
            final MSG msg = new MSG();
            final int flags = 4194306;
            while (result[0] == null && exception[0] == null) {
                OS.PeekMessage(msg, 0L, 0, 0, 4194306);
                try {
                    synchronized (result) {
                        result.wait(50L);
                    }
                }
                catch (InterruptedException e3) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
        if (exception[0] != null) {
            SWT.error(20, exception[0]);
        }
        final Frame frame = result[0];
        parent.setData(SWT_AWT.EMBEDDED_FRAME_KEY, frame);
        final Window window;
        final Window window2;
        final Listener shellListener = e -> {
            switch (e.type) {
                case 20: {
                    EventQueue.invokeLater(() -> window.dispatchEvent(new WindowEvent(window, 204)));
                    break;
                }
                case 19: {
                    EventQueue.invokeLater(() -> window2.dispatchEvent(new WindowEvent(window2, 203)));
                    break;
                }
            }
            return;
        };
        final Shell shell = parent.getShell();
        shell.addListener(20, shellListener);
        shell.addListener(19, shellListener);
        final Shell shell2;
        final Listener listener2;
        final Window window3;
        final Frame frame2;
        final Frame frame3;
        final Listener listener = e -> {
            switch (e.type) {
                case 12: {
                    shell2 = parent.getShell();
                    shell2.removeListener(20, listener2);
                    shell2.removeListener(19, listener2);
                    parent.setVisible(false);
                    EventQueue.invokeLater(() -> {
                        try {
                            window3.dispose();
                        }
                        catch (Throwable t2) {}
                        return;
                    });
                    break;
                }
                case 15:
                case 26: {
                    EventQueue.invokeLater(() -> {
                        if (frame2.isActive()) {
                            return;
                        }
                        else {
                            synthesizeWindowActivation(frame2, true);
                            return;
                        }
                    });
                    break;
                }
                case 27: {
                    EventQueue.invokeLater(() -> {
                        if (!frame3.isActive()) {
                            return;
                        }
                        else {
                            synthesizeWindowActivation(frame3, false);
                            return;
                        }
                    });
                    break;
                }
            }
            return;
        };
        parent.addListener(15, listener);
        parent.addListener(27, listener);
        parent.addListener(12, listener);
        Rectangle clientArea;
        final Window window4;
        final Rectangle rectangle;
        parent.getDisplay().asyncExec(() -> {
            if (parent.isDisposed()) {
                return;
            }
            else {
                clientArea = DPIUtil.autoScaleUp(parent.getClientArea());
                EventQueue.invokeLater(() -> {
                    window4.setSize(rectangle.width, rectangle.height);
                    window4.validate();
                });
                return;
            }
        });
        return frame;
    }
    
    public static Shell new_Shell(final Display display, final Canvas parent) {
        if (display == null) {
            SWT.error(4);
        }
        if (parent == null) {
            SWT.error(4);
        }
        long handle = 0L;
        try {
            loadLibrary();
            handle = getAWTHandle(parent);
        }
        catch (Throwable e) {
            SWT.error(20, e);
        }
        if (handle == 0L) {
            SWT.error(5, null, " [peer not created]");
        }
        final Shell shell = Shell.win32_new(display, handle);
        final ComponentListener listener = (ComponentListener)new l(display, shell, parent);
        parent.addComponentListener(listener);
        shell.addListener(12, event -> parent.removeComponentListener(listener));
        shell.setVisible(true);
        return shell;
    }
    
    static {
        SWT_AWT.EMBEDDED_FRAME_KEY = "org.eclipse.swt.awt.SWT_AWT.embeddedFrame";
    }
}
