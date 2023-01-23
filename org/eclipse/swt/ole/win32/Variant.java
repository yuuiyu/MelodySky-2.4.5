//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.ole.win32.*;

public final class Variant
{
    public static final int sizeof;
    private short type;
    private boolean booleanData;
    private byte byteData;
    private short shortData;
    private char charData;
    private int intData;
    private long longData;
    private float floatData;
    private double doubleData;
    private String stringData;
    private long byRefPtr;
    private IDispatch dispatchData;
    private IUnknown unknownData;
    public static final Variant NULL;
    
    public static void win32_copy(final long pVarDest, final Variant varSrc) {
        varSrc.getData(pVarDest);
    }
    
    public static Variant win32_new(final long pVariant) {
        final Variant variant = new Variant();
        variant.setData(pVariant);
        return variant;
    }
    
    public Variant() {
        this.type = 0;
    }
    
    public Variant(final float val) {
        this.type = 4;
        this.floatData = val;
    }
    
    public Variant(final double val) {
        this.type = 5;
        this.doubleData = val;
    }
    
    public Variant(final int val) {
        this.type = 3;
        this.intData = val;
    }
    
    public Variant(final long ptr, final short byRefType) {
        this.type = byRefType;
        this.byRefPtr = ptr;
    }
    
    public Variant(final OleAutomation automation) {
        this.type = 9;
        this.dispatchData = new IDispatch(automation.getAddress());
    }
    
    public Variant(final IDispatch idispatch) {
        this.type = 9;
        this.dispatchData = idispatch;
    }
    
    public Variant(final IUnknown unknown) {
        this.type = 13;
        this.unknownData = unknown;
    }
    
    public Variant(final long val) {
        this.type = 20;
        this.longData = val;
    }
    
    public Variant(final String string) {
        this.type = 8;
        this.stringData = string;
    }
    
    public Variant(final short val) {
        this.type = 2;
        this.shortData = val;
    }
    
    public Variant(final boolean val) {
        this.type = 11;
        this.booleanData = val;
    }
    
    public void dispose() {
        if ((this.type & 0x4000) == 0x4000) {
            return;
        }
        switch (this.type) {
            case 9: {
                this.dispatchData.Release();
                break;
            }
            case 13: {
                this.unknownData.Release();
                break;
            }
        }
    }
    
    public OleAutomation getAutomation() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 9) {
            return new OleAutomation(this.dispatchData);
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)9);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant autoVar = new Variant();
            autoVar.setData(newPtr);
            return autoVar.getAutomation();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public IDispatch getDispatch() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 9) {
            return this.dispatchData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)9);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant autoVar = new Variant();
            autoVar.setData(newPtr);
            return autoVar.getDispatch();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public boolean getBoolean() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 11) {
            return this.booleanData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)11);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant boolVar = new Variant();
            boolVar.setData(newPtr);
            return boolVar.getBoolean();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public long getByRef() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if ((this.type & 0x4000) == 0x4000) {
            return this.byRefPtr;
        }
        return 0L;
    }
    
    public byte getByte() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 16) {
            return this.byteData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)16);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant byteVar = new Variant();
            byteVar.setData(newPtr);
            return byteVar.getByte();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public char getChar() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 18) {
            return this.charData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)18);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant charVar = new Variant();
            charVar.setData(newPtr);
            return charVar.getChar();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    void getData(final long pData) {
        if (pData == 0L) {
            OLE.error(1007);
        }
        COM.VariantInit(pData);
        if ((this.type & 0x4000) == 0x4000) {
            OS.MoveMemory(pData, new short[] { this.type }, 2);
            OS.MoveMemory(pData + 8L, new long[] { this.byRefPtr }, C.PTR_SIZEOF);
            return;
        }
        switch (this.type) {
            case 0:
            case 1: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                break;
            }
            case 11: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new short[] { (short)(this.booleanData ? -1 : 0) }, 2);
                break;
            }
            case 16: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new byte[] { this.byteData }, 1);
                break;
            }
            case 2: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new short[] { this.shortData }, 2);
                break;
            }
            case 18: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new char[] { this.charData }, 2);
                break;
            }
            case 3: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new int[] { this.intData }, 4);
                break;
            }
            case 20: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new long[] { this.longData }, 8);
                break;
            }
            case 4: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new float[] { this.floatData }, 4);
                break;
            }
            case 5: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new double[] { this.doubleData }, 8);
                break;
            }
            case 9: {
                this.dispatchData.AddRef();
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new long[] { this.dispatchData.getAddress() }, C.PTR_SIZEOF);
                break;
            }
            case 13: {
                this.unknownData.AddRef();
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                OS.MoveMemory(pData + 8L, new long[] { this.unknownData.getAddress() }, C.PTR_SIZEOF);
                break;
            }
            case 8: {
                OS.MoveMemory(pData, new short[] { this.type }, 2);
                final char[] data = this.stringData.toCharArray();
                final long ptr = COM.SysAllocString(data);
                OS.MoveMemory(pData + 8L, new long[] { ptr }, C.PTR_SIZEOF);
                break;
            }
            default: {
                OLE.error(20);
                break;
            }
        }
    }
    
    public double getDouble() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 5) {
            return this.doubleData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)5);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant doubleVar = new Variant();
            doubleVar.setData(newPtr);
            return doubleVar.getDouble();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public float getFloat() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 4) {
            return this.floatData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)4);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant floatVar = new Variant();
            floatVar.setData(newPtr);
            return floatVar.getFloat();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public int getInt() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 3) {
            return this.intData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)3);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant intVar = new Variant();
            intVar.setData(newPtr);
            return intVar.getInt();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public long getLong() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 20) {
            return this.longData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)20);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant longVar = new Variant();
            longVar.setData(newPtr);
            return longVar.getLong();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public short getShort() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 2) {
            return this.shortData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)2);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant shortVar = new Variant();
            shortVar.setData(newPtr);
            return shortVar.getShort();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public String getString() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 8) {
            return this.stringData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)8);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant stringVar = new Variant();
            stringVar.setData(newPtr);
            return stringVar.getString();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public short getType() {
        return this.type;
    }
    
    public IUnknown getUnknown() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 13) {
            return this.unknownData;
        }
        final long oldPtr = OS.GlobalAlloc(64, Variant.sizeof);
        final long newPtr = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(oldPtr);
            final int result = COM.VariantChangeType(newPtr, oldPtr, (short)0, (short)13);
            if (result != 0) {
                OLE.error(1010, result);
            }
            final Variant unknownVar = new Variant();
            unknownVar.setData(newPtr);
            return unknownVar.getUnknown();
        }
        finally {
            COM.VariantClear(oldPtr);
            OS.GlobalFree(oldPtr);
            COM.VariantClear(newPtr);
            OS.GlobalFree(newPtr);
        }
    }
    
    public void setByRef(final boolean val) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0xB) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new short[] { (short)(val ? -1 : 0) }, 2);
    }
    
    public void setByRef(final float val) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x4) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new float[] { val }, 4);
    }
    
    public void setByRef(final long val) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x14) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new long[] { val }, C.PTR_SIZEOF);
    }
    
    public void setByRef(final short val) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x2) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new short[] { val }, 2);
    }
    
    void setData(final long pData) {
        if (pData == 0L) {
            OLE.error(5);
        }
        final short[] dataType = { 0 };
        OS.MoveMemory(dataType, pData, 2);
        this.type = dataType[0];
        if ((this.type & 0x4000) == 0x4000) {
            final long[] newByRefPtr = { 0L };
            OS.MoveMemory(newByRefPtr, pData + 8L, C.PTR_SIZEOF);
            this.byRefPtr = newByRefPtr[0];
            return;
        }
        switch (this.type) {
            case 0:
            case 1: {
                break;
            }
            case 11: {
                final short[] newBooleanData = { 0 };
                OS.MoveMemory(newBooleanData, pData + 8L, 2);
                this.booleanData = (newBooleanData[0] != 0);
                break;
            }
            case 16: {
                final byte[] newByteData = { 0 };
                OS.MoveMemory(newByteData, pData + 8L, 1);
                this.byteData = newByteData[0];
                break;
            }
            case 2: {
                final short[] newShortData = { 0 };
                OS.MoveMemory(newShortData, pData + 8L, 2);
                this.shortData = newShortData[0];
                break;
            }
            case 18: {
                final char[] newCharData = { '\0' };
                OS.MoveMemory(newCharData, pData + 8L, 2);
                this.charData = newCharData[0];
                break;
            }
            case 3: {
                final int[] newIntData = { 0 };
                OS.MoveMemory(newIntData, pData + 8L, 4);
                this.intData = newIntData[0];
                break;
            }
            case 20: {
                final long[] newLongData = { 0L };
                OS.MoveMemory(newLongData, pData + 8L, 8);
                this.longData = newLongData[0];
                break;
            }
            case 4: {
                final float[] newFloatData = { 0.0f };
                OS.MoveMemory(newFloatData, pData + 8L, 4);
                this.floatData = newFloatData[0];
                break;
            }
            case 5: {
                final double[] newDoubleData = { 0.0 };
                OS.MoveMemory(newDoubleData, pData + 8L, 8);
                this.doubleData = newDoubleData[0];
                break;
            }
            case 9: {
                final long[] ppvObject = { 0L };
                OS.MoveMemory(ppvObject, pData + 8L, C.PTR_SIZEOF);
                if (ppvObject[0] == 0L) {
                    this.type = 0;
                    break;
                }
                (this.dispatchData = new IDispatch(ppvObject[0])).AddRef();
                break;
            }
            case 13: {
                final long[] ppvObject = { 0L };
                OS.MoveMemory(ppvObject, pData + 8L, C.PTR_SIZEOF);
                if (ppvObject[0] == 0L) {
                    this.type = 0;
                    break;
                }
                (this.unknownData = new IUnknown(ppvObject[0])).AddRef();
                break;
            }
            case 8: {
                final long[] hMem = { 0L };
                OS.MoveMemory(hMem, pData + 8L, C.PTR_SIZEOF);
                if (hMem[0] == 0L) {
                    this.type = 0;
                    break;
                }
                final int size = COM.SysStringByteLen(hMem[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, hMem[0], size);
                    this.stringData = new String(buffer);
                    break;
                }
                this.stringData = "";
                break;
            }
            default: {
                final long newPData = OS.GlobalAlloc(64, Variant.sizeof);
                if (COM.VariantChangeType(newPData, pData, (short)0, (short)5) == 0) {
                    this.setData(newPData);
                }
                else if (COM.VariantChangeType(newPData, pData, (short)0, (short)20) == 0) {
                    this.setData(newPData);
                }
                else if (COM.VariantChangeType(newPData, pData, (short)0, (short)8) == 0) {
                    this.setData(newPData);
                }
                COM.VariantClear(newPData);
                OS.GlobalFree(newPData);
                break;
            }
        }
    }
    
    @Override
    public String toString() {
        switch (this.type) {
            case 11: {
                return "VT_BOOL{" + this.booleanData;
            }
            case 16: {
                return "VT_I1{" + this.byteData;
            }
            case 2: {
                return "VT_I2{" + this.shortData;
            }
            case 18: {
                return "VT_UI2{" + this.charData;
            }
            case 3: {
                return "VT_I4{" + this.intData;
            }
            case 20: {
                return "VT_I8{" + this.longData;
            }
            case 4: {
                return "VT_R4{" + this.floatData;
            }
            case 5: {
                return "VT_R8{" + this.doubleData;
            }
            case 8: {
                return "VT_BSTR{" + this.stringData;
            }
            case 9: {
                return "VT_DISPATCH{" + ((this.dispatchData == null) ? 0L : this.dispatchData.getAddress());
            }
            case 13: {
                return "VT_UNKNOWN{" + ((this.unknownData == null) ? 0L : this.unknownData.getAddress());
            }
            case 0: {
                return "VT_EMPTY";
            }
            case 1: {
                return "VT_NULL";
            }
            default: {
                if ((this.type & 0x4000) != 0x0) {
                    return "VT_BYREF|" + (this.type & 0xFFFFBFFF) + "{" + this.byRefPtr;
                }
                return "Unsupported Type " + this.type;
            }
        }
    }
    
    static {
        sizeof = VARIANT.sizeof;
        NULL = new Variant();
        Variant.NULL.type = 1;
    }
}
