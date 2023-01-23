//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;
import java.util.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class BusyIndicator
{
    static int nextBusyId;
    static final String BUSYID_NAME = "SWT BusyIndicator";
    static final String BUSY_CURSOR = "SWT BusyIndicator Cursor";
    
    public static void showWhile(Display display, final Runnable runnable) {
        if (runnable == null) {
            SWT.error(4);
        }
        if (display == null) {
            display = Display.getCurrent();
            if (display == null) {
                runnable.run();
                return;
            }
        }
        final Integer busyId = BusyIndicator.nextBusyId;
        ++BusyIndicator.nextBusyId;
        final Cursor cursor = display.getSystemCursor(1);
        Shell[] shells3;
        final Shell[] array;
        final Shell[] shells2 = array = (shells3 = display.getShells());
        for (final Shell shell : array) {
            final Integer id = (Integer)shell.getData("SWT BusyIndicator");
            if (id == null) {
                setCursorAndId(shell, cursor, busyId);
            }
        }
        try {
            runnable.run();
        }
        finally {
            final Shell[] array2;
            final Shell[] shells4 = array2 = (shells3 = display.getShells());
            for (final Shell shell2 : array2) {
                final Integer id2 = (Integer)shell2.getData("SWT BusyIndicator");
                if (Objects.equals(id2, busyId)) {
                    setCursorAndId(shell2, null, null);
                }
            }
        }
    }
    
    private static void setCursorAndId(final Shell shell, final Cursor cursor, final Integer busyId) {
        if (!shell.isDisposed()) {
            shell.setCursor(cursor);
        }
        if (!shell.isDisposed()) {
            shell.setData("SWT BusyIndicator", busyId);
        }
    }
    
    static {
        BusyIndicator.nextBusyId = 1;
    }
}
