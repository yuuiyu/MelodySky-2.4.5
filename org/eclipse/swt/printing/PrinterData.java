//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.printing;

import org.eclipse.swt.graphics.*;

public final class PrinterData extends DeviceData
{
    public String driver;
    public String name;
    public int scope;
    public int startPage;
    public int endPage;
    public boolean printToFile;
    public String fileName;
    public int copyCount;
    public boolean collate;
    public int orientation;
    public int duplex;
    public static final int ALL_PAGES = 0;
    public static final int PAGE_RANGE = 1;
    public static final int SELECTION = 2;
    public static final int PORTRAIT = 1;
    public static final int LANDSCAPE = 2;
    public static final int DUPLEX_NONE = 0;
    public static final int DUPLEX_LONG_EDGE = 1;
    public static final int DUPLEX_SHORT_EDGE = 2;
    byte[] otherData;
    
    public PrinterData() {
        this.scope = 0;
        this.startPage = 1;
        this.endPage = 1;
        this.printToFile = false;
        this.copyCount = 1;
        this.collate = false;
        this.orientation = 1;
        this.duplex = -1;
    }
    
    public PrinterData(final String driver, final String name) {
        this.scope = 0;
        this.startPage = 1;
        this.endPage = 1;
        this.printToFile = false;
        this.copyCount = 1;
        this.collate = false;
        this.orientation = 1;
        this.duplex = -1;
        this.driver = driver;
        this.name = name;
    }
    
    public String toString() {
        return "PrinterData {driver = " + this.driver + ", name = " + this.name;
    }
}
