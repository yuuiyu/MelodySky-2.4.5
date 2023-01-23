//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.printing;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.widgets.*;

public class PrintDialog extends Dialog
{
    static final TCHAR DialogClass;
    PrinterData printerData;
    
    public PrintDialog(final Shell parent) {
        this(parent, 32768);
    }
    
    public PrintDialog(final Shell parent, final int style) {
        super(parent, checkStyle(parent, style));
        this.printerData = new PrinterData();
        this.checkSubclass();
    }
    
    static int checkBits(int style, final int int0, final int int1, final int int2, final int int3, final int int4, final int int5) {
        final int mask = int0 | int1 | int2 | int3 | int4 | int5;
        if ((style & mask) == 0x0) {
            style |= int0;
        }
        if ((style & int0) != 0x0) {
            style = ((style & ~mask) | int0);
        }
        if ((style & int1) != 0x0) {
            style = ((style & ~mask) | int1);
        }
        if ((style & int2) != 0x0) {
            style = ((style & ~mask) | int2);
        }
        if ((style & int3) != 0x0) {
            style = ((style & ~mask) | int3);
        }
        if ((style & int4) != 0x0) {
            style = ((style & ~mask) | int4);
        }
        if ((style & int5) != 0x0) {
            style = ((style & ~mask) | int5);
        }
        return style;
    }
    
    static int checkStyle(final Shell parent, int style) {
        final int mask = 229376;
        if ((style & 0x10000000) != 0x0) {
            style &= 0xEFFFFFFF;
            if ((style & 0x38000) == 0x0) {
                style |= ((parent == null) ? 65536 : 32768);
            }
        }
        if ((style & 0x38000) == 0x0) {
            style |= 0x10000;
        }
        style &= 0xF7FFFFFF;
        if ((style & 0x6000000) == 0x0 && parent != null) {
            if ((parent.getStyle() & 0x2000000) != 0x0) {
                style |= 0x2000000;
            }
            if ((parent.getStyle() & 0x4000000) != 0x0) {
                style |= 0x4000000;
            }
        }
        return checkBits(style, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    public void setPrinterData(PrinterData data) {
        if (data == null) {
            data = new PrinterData();
        }
        this.printerData = data;
    }
    
    public PrinterData getPrinterData() {
        return this.printerData;
    }
    
    public int getScope() {
        return this.printerData.scope;
    }
    
    public void setScope(final int scope) {
        this.printerData.scope = scope;
    }
    
    public int getStartPage() {
        return this.printerData.startPage;
    }
    
    public void setStartPage(final int startPage) {
        this.printerData.startPage = startPage;
    }
    
    public int getEndPage() {
        return this.printerData.endPage;
    }
    
    public void setEndPage(final int endPage) {
        this.printerData.endPage = endPage;
    }
    
    public boolean getPrintToFile() {
        return this.printerData.printToFile;
    }
    
    public void setPrintToFile(final boolean printToFile) {
        this.printerData.printToFile = printToFile;
    }
    
    @Override
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final String validName = PrintDialog.class.getName();
        if (!validName.equals(name)) {
            SWT.error(43);
        }
    }
    
    public PrinterData open() {
        final Control parent = this.getParent();
        final int style = this.getStyle();
        long hwndOwner = parent.handle;
        final long hwndParent = parent.handle;
        boolean enabled = false;
        final int dialogOrientation = style & 0x6000000;
        final int parentOrientation = parent.getStyle() & 0x6000000;
        if (dialogOrientation != parentOrientation) {
            int exStyle = 1048576;
            if (dialogOrientation == 67108864) {
                exStyle |= 0x400000;
            }
            hwndOwner = OS.CreateWindowEx(exStyle, PrintDialog.DialogClass, (TCHAR)null, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, hwndParent, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
            enabled = OS.IsWindowEnabled(hwndParent);
            if (enabled) {
                OS.EnableWindow(hwndParent, false);
            }
        }
        PrinterData data = null;
        final PRINTDLG pd = new PRINTDLG();
        pd.lStructSize = PRINTDLG.sizeof;
        pd.hwndOwner = hwndOwner;
        boolean success = false;
        if (this.printerData.name != null) {
            for (final PrinterData element : Printer.getPrinterList()) {
                if (element.name.equals(this.printerData.name)) {
                    success = true;
                    break;
                }
            }
            if (success) {
                final TCHAR buffer = new TCHAR(0, this.printerData.name, true);
                final int size = buffer.length() * 2;
                final short[] offsets = new short[4];
                final int offsetsSize = offsets.length * 2;
                offsets[1] = (short)offsets.length;
                final long hMem = OS.GlobalAlloc(66, offsetsSize + size);
                final long ptr = OS.GlobalLock(hMem);
                OS.MoveMemory(ptr, offsets, offsetsSize);
                OS.MoveMemory(ptr + offsetsSize, buffer, size);
                OS.GlobalUnlock(hMem);
                pd.hDevNames = hMem;
            }
        }
        final Display display = parent.getDisplay();
        final String externalLoopKey = "org.eclipse.swt.internal.win32.externalEventLoop";
        if (!success) {
            pd.Flags = 1024;
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.TRUE);
            display.sendPreExternalEventDispatchEvent();
            success = OS.PrintDlg(pd);
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.FALSE);
            display.sendPostExternalEventDispatchEvent();
            if (success && pd.hDevNames != 0L) {
                OS.GlobalFree(pd.hDevNames);
                pd.hDevNames = 0L;
            }
        }
        if (success) {
            final byte[] devmodeData = this.printerData.otherData;
            if (devmodeData != null && devmodeData.length != 0) {
                final long hMem2 = OS.GlobalAlloc(66, devmodeData.length);
                final long ptr2 = OS.GlobalLock(hMem2);
                OS.MoveMemory(ptr2, devmodeData, devmodeData.length);
                OS.GlobalUnlock(hMem2);
                if (pd.hDevMode != 0L) {
                    OS.GlobalFree(pd.hDevMode);
                }
                pd.hDevMode = hMem2;
            }
            long hMem2 = pd.hDevMode;
            if (hMem2 == 0L) {
                hMem2 = OS.GlobalAlloc(66, DEVMODE.sizeof);
                pd.hDevMode = hMem2;
            }
            long ptr2 = OS.GlobalLock(hMem2);
            DEVMODE devmode = new DEVMODE();
            OS.MoveMemory(devmode, ptr2, DEVMODE.sizeof);
            if (this.printerData.name != null) {
                for (int max = Math.min(this.printerData.name.length(), 31), i = 0; i < max; ++i) {
                    devmode.dmDeviceName[i] = this.printerData.name.charAt(i);
                }
            }
            final DEVMODE devmode6;
            final DEVMODE devmode2 = devmode6 = devmode;
            devmode6.dmFields |= 0x1;
            devmode.dmOrientation = (short)((this.printerData.orientation == 1) ? 1 : 2);
            if (this.printerData.copyCount != 1) {
                final DEVMODE devmode7;
                final DEVMODE devmode3 = devmode7 = devmode;
                devmode7.dmFields |= 0x100;
                devmode.dmCopies = (short)this.printerData.copyCount;
            }
            if (this.printerData.collate) {
                final DEVMODE devmode8;
                final DEVMODE devmode4 = devmode8 = devmode;
                devmode8.dmFields |= 0x8000;
                devmode.dmCollate = 1;
            }
            if (this.printerData.duplex != -1) {
                final DEVMODE devmode9;
                final DEVMODE devmode5 = devmode9 = devmode;
                devmode9.dmFields |= 0x1000;
                switch (this.printerData.duplex) {
                    case 2: {
                        devmode.dmDuplex = 3;
                        break;
                    }
                    case 1: {
                        devmode.dmDuplex = 2;
                        break;
                    }
                    default: {
                        devmode.dmDuplex = 1;
                        break;
                    }
                }
            }
            OS.MoveMemory(ptr2, devmode, DEVMODE.sizeof);
            OS.GlobalUnlock(hMem2);
            pd.Flags = 262144;
            if (this.printerData.printToFile) {
                final PRINTDLG printdlg5;
                final PRINTDLG printdlg = printdlg5 = pd;
                printdlg5.Flags |= 0x20;
            }
            switch (this.printerData.scope) {
                case 1: {
                    final PRINTDLG printdlg6;
                    final PRINTDLG printdlg2 = printdlg6 = pd;
                    printdlg6.Flags |= 0x2;
                    break;
                }
                case 2: {
                    final PRINTDLG printdlg7;
                    final PRINTDLG printdlg3 = printdlg7 = pd;
                    printdlg7.Flags |= 0x1;
                    break;
                }
                default: {
                    final PRINTDLG printdlg8;
                    final PRINTDLG printdlg4 = printdlg8 = pd;
                    printdlg8.Flags |= 0x0;
                    break;
                }
            }
            pd.nMinPage = 1;
            pd.nMaxPage = -1;
            pd.nFromPage = (short)Math.min(65535, Math.max(1, this.printerData.startPage));
            pd.nToPage = (short)Math.min(65535, Math.max(1, this.printerData.endPage));
            final Shell[] shells = display.getShells();
            if ((this.getStyle() & 0x30000) != 0x0) {
                for (int j = 0; j < shells.length; ++j) {
                    if (shells[j].isEnabled() && shells[j] != parent) {
                        shells[j].setEnabled(false);
                    }
                    else {
                        shells[j] = null;
                    }
                }
            }
            final String key = "org.eclipse.swt.internal.win32.runMessagesInIdle";
            final Object oldValue = display.getData("org.eclipse.swt.internal.win32.runMessagesInIdle");
            display.setData("org.eclipse.swt.internal.win32.runMessagesInIdle", Boolean.TRUE);
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.TRUE);
            display.sendPreExternalEventDispatchEvent();
            success = OS.PrintDlg(pd);
            display.setData("org.eclipse.swt.internal.win32.externalEventLoop", Boolean.FALSE);
            display.sendPostExternalEventDispatchEvent();
            display.setData("org.eclipse.swt.internal.win32.runMessagesInIdle", oldValue);
            if ((this.getStyle() & 0x30000) != 0x0) {
                for (final Shell shell : shells) {
                    if (shell != null && !shell.isDisposed()) {
                        shell.setEnabled(true);
                    }
                }
            }
            if (success) {
                hMem2 = pd.hDevNames;
                int size2 = OS.GlobalSize(hMem2) / 2 * 2;
                ptr2 = OS.GlobalLock(hMem2);
                final short[] offsets2 = new short[4];
                OS.MoveMemory(offsets2, ptr2, 2 * offsets2.length);
                final char[] buffer2 = new char[size2];
                OS.MoveMemory(buffer2, ptr2, size2);
                OS.GlobalUnlock(hMem2);
                int driverOffset;
                int k;
                for (driverOffset = offsets2[0], k = 0; driverOffset + k < size2 && buffer2[driverOffset + k] != '\0'; ++k) {}
                final String driver = new String(buffer2, driverOffset, k);
                int deviceOffset;
                for (deviceOffset = offsets2[1], k = 0; deviceOffset + k < size2 && buffer2[deviceOffset + k] != '\0'; ++k) {}
                final String device = new String(buffer2, deviceOffset, k);
                data = new PrinterData(driver, device);
                if ((pd.Flags & 0x2) != 0x0) {
                    data.scope = 1;
                    data.startPage = (pd.nFromPage & 0xFFFF);
                    data.endPage = (pd.nToPage & 0xFFFF);
                }
                else if ((pd.Flags & 0x1) != 0x0) {
                    data.scope = 2;
                }
                data.printToFile = ((pd.Flags & 0x20) != 0x0);
                if (data.printToFile) {
                    data.fileName = this.printerData.fileName;
                }
                data.copyCount = pd.nCopies;
                data.collate = ((pd.Flags & 0x10) != 0x0);
                hMem2 = pd.hDevMode;
                size2 = OS.GlobalSize(hMem2);
                ptr2 = OS.GlobalLock(hMem2);
                OS.MoveMemory(data.otherData = new byte[size2], ptr2, size2);
                devmode = new DEVMODE();
                OS.MoveMemory(devmode, ptr2, DEVMODE.sizeof);
                if ((devmode.dmFields & 0x1) != 0x0) {
                    final int dmOrientation = devmode.dmOrientation;
                    data.orientation = ((dmOrientation == 2) ? 2 : 1);
                }
                if ((devmode.dmFields & 0x1000) != 0x0) {
                    final short dmDuplex = devmode.dmDuplex;
                    data.duplex = ((dmDuplex == 1) ? 0 : ((dmDuplex == 3) ? 2 : 1));
                }
                OS.GlobalUnlock(hMem2);
                this.printerData = data;
            }
        }
        if (pd.hDevNames != 0L) {
            OS.GlobalFree(pd.hDevNames);
            pd.hDevNames = 0L;
        }
        if (pd.hDevMode != 0L) {
            OS.GlobalFree(pd.hDevMode);
            pd.hDevMode = 0L;
        }
        if (hwndParent != hwndOwner) {
            if (enabled) {
                OS.EnableWindow(hwndParent, true);
            }
            OS.SetActiveWindow(hwndParent);
            OS.DestroyWindow(hwndOwner);
        }
        return data;
    }
    
    static {
        DialogClass = new TCHAR(0, "#32770", true);
    }
}
