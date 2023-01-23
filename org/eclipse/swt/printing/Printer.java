//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.printing;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public final class Printer extends Device
{
    public long handle;
    PrinterData data;
    boolean isGCCreated;
    static TCHAR profile;
    static TCHAR appName;
    static TCHAR keyName;
    
    public static PrinterData[] getPrinterList() {
        final int length = 1024;
        final TCHAR buf = new TCHAR(0, 1024);
        final TCHAR nullBuf = new TCHAR(0, 1);
        final int n = OS.GetProfileString(Printer.profile, (TCHAR)null, nullBuf, buf, 1024);
        if (n == 0) {
            return new PrinterData[0];
        }
        String[] deviceNames = new String[5];
        int nameCount = 0;
        int index = 0;
        for (int i = 0; i < n; ++i) {
            if (buf.tcharAt(i) == 0) {
                if (nameCount == deviceNames.length) {
                    final String[] newNames = new String[deviceNames.length + 5];
                    System.arraycopy(deviceNames, 0, newNames, 0, deviceNames.length);
                    deviceNames = newNames;
                }
                deviceNames[nameCount] = buf.toString(index, i - index);
                ++nameCount;
                index = i + 1;
            }
        }
        final PrinterData[] printerList = new PrinterData[nameCount];
        for (int p = 0; p < nameCount; ++p) {
            final String device = deviceNames[p];
            String driver = "";
            if (OS.GetProfileString(Printer.profile, new TCHAR(0, device, true), nullBuf, buf, 1024) > 0) {
                int commaIndex;
                for (commaIndex = 0; buf.tcharAt(commaIndex) != 44 && commaIndex < 1024; ++commaIndex) {}
                if (commaIndex < 1024) {
                    driver = buf.toString(0, commaIndex);
                }
            }
            printerList[p] = new PrinterData(driver, device);
        }
        return printerList;
    }
    
    public static PrinterData getDefaultPrinterData() {
        String deviceName = null;
        final int length = 1024;
        final TCHAR buf = new TCHAR(0, 1024);
        final TCHAR nullBuf = new TCHAR(0, 1);
        final int n = OS.GetProfileString(Printer.appName, Printer.keyName, nullBuf, buf, 1024);
        if (n == 0) {
            return null;
        }
        int commaIndex;
        for (commaIndex = 0; buf.tcharAt(commaIndex) != 44 && commaIndex < 1024; ++commaIndex) {}
        if (commaIndex < 1024) {
            deviceName = buf.toString(0, commaIndex);
        }
        if (deviceName == null) {
            return null;
        }
        String driver = "";
        if (OS.GetProfileString(Printer.profile, new TCHAR(0, deviceName, true), nullBuf, buf, 1024) > 0) {
            for (commaIndex = 0; buf.tcharAt(commaIndex) != 44 && commaIndex < 1024; ++commaIndex) {}
            if (commaIndex < 1024) {
                driver = buf.toString(0, commaIndex);
            }
        }
        return new PrinterData(driver, deviceName);
    }
    
    static DeviceData checkNull(PrinterData data) {
        if (data == null) {
            data = new PrinterData();
        }
        if (data.driver == null || data.name == null) {
            final PrinterData defaultPrinter = getDefaultPrinterData();
            if (defaultPrinter == null) {
                SWT.error(2);
            }
            data.driver = defaultPrinter.driver;
            data.name = defaultPrinter.name;
        }
        return data;
    }
    
    public Printer() {
        this(null);
    }
    
    public Printer(final PrinterData data) {
        super(checkNull(data));
        this.isGCCreated = false;
    }
    
    protected void create(final DeviceData deviceData) {
        this.data = (PrinterData)deviceData;
        final TCHAR driver = new TCHAR(0, this.data.driver, true);
        final TCHAR device = new TCHAR(0, this.data.name, true);
        long lpInitData = 0L;
        final byte[] devmodeData = this.data.otherData;
        final long hHeap = OS.GetProcessHeap();
        if (devmodeData != null && devmodeData.length != 0) {
            lpInitData = OS.HeapAlloc(hHeap, 8, devmodeData.length);
            OS.MoveMemory(lpInitData, devmodeData, devmodeData.length);
        }
        else {
            final long[] hPrinter = { 0L };
            OS.OpenPrinter(device, hPrinter, 0L);
            if (hPrinter[0] != 0L) {
                final int dwNeeded = OS.DocumentProperties(0L, hPrinter[0], device, 0L, 0L, 0);
                if (dwNeeded >= 0) {
                    lpInitData = OS.HeapAlloc(hHeap, 8, dwNeeded);
                    final int rc = OS.DocumentProperties(0L, hPrinter[0], device, lpInitData, 0L, 2);
                    if (rc != 1) {
                        OS.HeapFree(hHeap, 0, lpInitData);
                        lpInitData = 0L;
                    }
                }
                OS.ClosePrinter(hPrinter[0]);
            }
        }
        if (lpInitData != 0L) {
            final DEVMODE devmode = new DEVMODE();
            OS.MoveMemory(devmode, lpInitData, DEVMODE.sizeof);
            final DEVMODE devmode6;
            final DEVMODE devmode2 = devmode6 = devmode;
            devmode6.dmFields |= 0x1;
            devmode.dmOrientation = (short)((this.data.orientation == 2) ? 2 : 1);
            if (this.data.copyCount != 1) {
                final DEVMODE devmode7;
                final DEVMODE devmode3 = devmode7 = devmode;
                devmode7.dmFields |= 0x100;
                devmode.dmCopies = (short)this.data.copyCount;
            }
            if (this.data.collate) {
                final DEVMODE devmode8;
                final DEVMODE devmode4 = devmode8 = devmode;
                devmode8.dmFields |= 0x8000;
                devmode.dmCollate = 1;
            }
            if (this.data.duplex != -1) {
                final DEVMODE devmode9;
                final DEVMODE devmode5 = devmode9 = devmode;
                devmode9.dmFields |= 0x1000;
                switch (this.data.duplex) {
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
            OS.MoveMemory(lpInitData, devmode, DEVMODE.sizeof);
        }
        this.handle = OS.CreateDC(driver, device, 0L, lpInitData);
        if (lpInitData != 0L) {
            OS.HeapFree(hHeap, 0, lpInitData);
        }
        if (this.handle == 0L) {
            SWT.error(2);
        }
    }
    
    public long internal_new_GC(final GCData data) {
        if (this.handle == 0L) {
            SWT.error(2);
        }
        if (data != null) {
            if (this.isGCCreated) {
                SWT.error(5);
            }
            final int mask = 100663296;
            if ((data.style & 0x6000000) != 0x0) {
                data.layout = (((data.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                data.style |= 0x2000000;
            }
            data.device = this;
            data.font = Font.win32_new((Device)this, OS.GetCurrentObject(this.handle, 6));
            this.isGCCreated = true;
        }
        return this.handle;
    }
    
    public void internal_dispose_GC(final long hDC, final GCData data) {
        if (data != null) {
            this.isGCCreated = false;
        }
    }
    
    public boolean isAutoScalable() {
        return false;
    }
    
    public boolean startJob(final String jobName) {
        this.checkDevice();
        final DOCINFO di = new DOCINFO();
        di.cbSize = DOCINFO.sizeof;
        final long hHeap = OS.GetProcessHeap();
        long lpszDocName = 0L;
        if (jobName != null && jobName.length() != 0) {
            final TCHAR buffer = new TCHAR(0, jobName, true);
            final int byteCount = buffer.length() * 2;
            lpszDocName = OS.HeapAlloc(hHeap, 8, byteCount);
            OS.MoveMemory(lpszDocName, buffer, byteCount);
            di.lpszDocName = lpszDocName;
        }
        long lpszOutput = 0L;
        if (this.data.printToFile) {
            if (this.data.fileName == null) {
                this.data.fileName = "FILE:";
            }
            final TCHAR buffer2 = new TCHAR(0, this.data.fileName, true);
            final int byteCount2 = buffer2.length() * 2;
            lpszOutput = OS.HeapAlloc(hHeap, 8, byteCount2);
            OS.MoveMemory(lpszOutput, buffer2, byteCount2);
            di.lpszOutput = lpszOutput;
        }
        final int rc = OS.StartDoc(this.handle, di);
        if (lpszDocName != 0L) {
            OS.HeapFree(hHeap, 0, lpszDocName);
        }
        if (lpszOutput != 0L) {
            OS.HeapFree(hHeap, 0, lpszOutput);
        }
        return rc > 0;
    }
    
    public void endJob() {
        this.checkDevice();
        OS.EndDoc(this.handle);
    }
    
    public void cancelJob() {
        this.checkDevice();
        OS.AbortDoc(this.handle);
    }
    
    public boolean startPage() {
        this.checkDevice();
        final int rc = OS.StartPage(this.handle);
        if (rc <= 0) {
            OS.AbortDoc(this.handle);
        }
        return rc > 0;
    }
    
    public void endPage() {
        this.checkDevice();
        OS.EndPage(this.handle);
    }
    
    public Point getDPI() {
        this.checkDevice();
        final int dpiX = OS.GetDeviceCaps(this.handle, 88);
        final int dpiY = OS.GetDeviceCaps(this.handle, 90);
        return new Point(dpiX, dpiY);
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        final int width = OS.GetDeviceCaps(this.handle, 110);
        final int height = OS.GetDeviceCaps(this.handle, 111);
        return new Rectangle(0, 0, width, height);
    }
    
    public Rectangle getClientArea() {
        this.checkDevice();
        final int width = OS.GetDeviceCaps(this.handle, 8);
        final int height = OS.GetDeviceCaps(this.handle, 10);
        return new Rectangle(0, 0, width, height);
    }
    
    public Rectangle computeTrim(final int x, final int y, final int width, final int height) {
        this.checkDevice();
        final int printX = -OS.GetDeviceCaps(this.handle, 112);
        final int printY = -OS.GetDeviceCaps(this.handle, 113);
        final int printWidth = OS.GetDeviceCaps(this.handle, 8);
        final int printHeight = OS.GetDeviceCaps(this.handle, 10);
        final int paperWidth = OS.GetDeviceCaps(this.handle, 110);
        final int paperHeight = OS.GetDeviceCaps(this.handle, 111);
        final int hTrim = paperWidth - printWidth;
        final int vTrim = paperHeight - printHeight;
        return new Rectangle(x + printX, y + printY, width + hTrim, height + vTrim);
    }
    
    public PrinterData getPrinterData() {
        return this.data;
    }
    
    protected void checkDevice() {
        if (this.handle == 0L) {
            SWT.error(45);
        }
    }
    
    protected void release() {
        super.release();
        this.data = null;
    }
    
    protected void destroy() {
        if (this.handle != 0L) {
            OS.DeleteDC(this.handle);
        }
        this.handle = 0L;
    }
    
    static {
        Printer.profile = new TCHAR(0, "PrinterPorts", true);
        Printer.appName = new TCHAR(0, "windows", true);
        Printer.keyName = new TCHAR(0, "device", true);
    }
}
