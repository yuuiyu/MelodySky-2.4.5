//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import java.util.*;

public class COMObject
{
    public long ppVtable;
    private static final int MAX_ARG_COUNT = 12;
    private static final int MAX_VTABLE_LENGTH = 80;
    private static Callback[][] Callbacks;
    private static Map<LONG, COMObject> ObjectMap;
    
    public COMObject(final int[] argCounts) {
        final long[] callbackAddresses = new long[argCounts.length];
        synchronized (COMObject.Callbacks) {
            for (int i = 0, length = argCounts.length; i < length; ++i) {
                if (COMObject.Callbacks[i][argCounts[i]] == null) {
                    COMObject.Callbacks[i][argCounts[i]] = new Callback((Object)this.getClass(), "callback" + i, argCounts[i] + 1, true, -2147467259L);
                }
                callbackAddresses[i] = COMObject.Callbacks[i][argCounts[i]].getAddress();
            }
        }
        final long pVtable = OS.GlobalAlloc(64, C.PTR_SIZEOF * argCounts.length);
        OS.MoveMemory(pVtable, callbackAddresses, C.PTR_SIZEOF * argCounts.length);
        OS.MoveMemory(this.ppVtable = OS.GlobalAlloc(64, C.PTR_SIZEOF), new long[] { pVtable }, C.PTR_SIZEOF);
        COMObject.ObjectMap.put(new LONG(this.ppVtable), this);
    }
    
    public static GUID IIDFromString(final String lpsz) {
        final char[] buffer = lpsz.toCharArray();
        final GUID lpiid = new GUID();
        if (COM.IIDFromString(buffer, lpiid) == 0) {
            return lpiid;
        }
        return null;
    }
    
    static long callback0(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method0(args);
    }
    
    static long callback1(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method1(args);
    }
    
    static long callback2(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method2(args);
    }
    
    static long callback3(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method3(args);
    }
    
    static long callback4(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method4(args);
    }
    
    static long callback5(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method5(args);
    }
    
    static long callback6(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method6(args);
    }
    
    static long callback7(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method7(args);
    }
    
    static long callback8(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method8(args);
    }
    
    static long callback9(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method9(args);
    }
    
    static long callback10(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method10(args);
    }
    
    static long callback11(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method11(args);
    }
    
    static long callback12(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method12(args);
    }
    
    static long callback13(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method13(args);
    }
    
    static long callback14(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method14(args);
    }
    
    static long callback15(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method15(args);
    }
    
    static long callback16(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method16(args);
    }
    
    static long callback17(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method17(args);
    }
    
    static long callback18(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method18(args);
    }
    
    static long callback19(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method19(args);
    }
    
    static long callback20(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method20(args);
    }
    
    static long callback21(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method21(args);
    }
    
    static long callback22(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method22(args);
    }
    
    static long callback23(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method23(args);
    }
    
    static long callback24(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method24(args);
    }
    
    static long callback25(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method25(args);
    }
    
    static long callback26(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method26(args);
    }
    
    static long callback27(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method27(args);
    }
    
    static long callback28(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method28(args);
    }
    
    static long callback29(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method29(args);
    }
    
    static long callback30(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method30(args);
    }
    
    static long callback31(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method31(args);
    }
    
    static long callback32(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method32(args);
    }
    
    static long callback33(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method33(args);
    }
    
    static long callback34(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method34(args);
    }
    
    static long callback35(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method35(args);
    }
    
    static long callback36(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method36(args);
    }
    
    static long callback37(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method37(args);
    }
    
    static long callback38(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method38(args);
    }
    
    static long callback39(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method39(args);
    }
    
    static long callback40(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method40(args);
    }
    
    static long callback41(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method41(args);
    }
    
    static long callback42(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method42(args);
    }
    
    static long callback43(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method43(args);
    }
    
    static long callback44(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method44(args);
    }
    
    static long callback45(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method45(args);
    }
    
    static long callback46(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method46(args);
    }
    
    static long callback47(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method47(args);
    }
    
    static long callback48(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method48(args);
    }
    
    static long callback49(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method49(args);
    }
    
    static long callback50(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method50(args);
    }
    
    static long callback51(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method51(args);
    }
    
    static long callback52(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method52(args);
    }
    
    static long callback53(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method53(args);
    }
    
    static long callback54(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method54(args);
    }
    
    static long callback55(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method55(args);
    }
    
    static long callback56(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method56(args);
    }
    
    static long callback57(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method57(args);
    }
    
    static long callback58(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method58(args);
    }
    
    static long callback59(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method59(args);
    }
    
    static long callback60(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method60(args);
    }
    
    static long callback61(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method61(args);
    }
    
    static long callback62(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method62(args);
    }
    
    static long callback63(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method63(args);
    }
    
    static long callback64(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method64(args);
    }
    
    static long callback65(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method65(args);
    }
    
    static long callback66(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method66(args);
    }
    
    static long callback67(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method67(args);
    }
    
    static long callback68(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method68(args);
    }
    
    static long callback69(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method69(args);
    }
    
    static long callback70(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method70(args);
    }
    
    static long callback71(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method71(args);
    }
    
    static long callback72(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method72(args);
    }
    
    static long callback73(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method73(args);
    }
    
    static long callback74(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method74(args);
    }
    
    static long callback75(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method75(args);
    }
    
    static long callback76(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method76(args);
    }
    
    static long callback77(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method77(args);
    }
    
    static long callback78(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method78(args);
    }
    
    static long callback79(final long[] callbackArgs) {
        final long address = callbackArgs[0];
        final COMObject object = COMObject.ObjectMap.get(new LONG(address));
        if (object == null) {
            return -2147467259L;
        }
        final long[] args = new long[callbackArgs.length - 1];
        System.arraycopy(callbackArgs, 1, args, 0, args.length);
        return object.method79(args);
    }
    
    public void dispose() {
        final long[] pVtable = { 0L };
        OS.MoveMemory(pVtable, this.ppVtable, C.PTR_SIZEOF);
        OS.GlobalFree(pVtable[0]);
        OS.GlobalFree(this.ppVtable);
        COMObject.ObjectMap.remove(new LONG(this.ppVtable));
        this.ppVtable = 0L;
    }
    
    public long getAddress() {
        return this.ppVtable;
    }
    
    public long method0(final long[] args) {
        return -2147467263L;
    }
    
    public long method1(final long[] args) {
        return -2147467263L;
    }
    
    public long method2(final long[] args) {
        return -2147467263L;
    }
    
    public long method3(final long[] args) {
        return -2147467263L;
    }
    
    public long method4(final long[] args) {
        return -2147467263L;
    }
    
    public long method5(final long[] args) {
        return -2147467263L;
    }
    
    public long method6(final long[] args) {
        return -2147467263L;
    }
    
    public long method7(final long[] args) {
        return -2147467263L;
    }
    
    public long method8(final long[] args) {
        return -2147467263L;
    }
    
    public long method9(final long[] args) {
        return -2147467263L;
    }
    
    public long method10(final long[] args) {
        return -2147467263L;
    }
    
    public long method11(final long[] args) {
        return -2147467263L;
    }
    
    public long method12(final long[] args) {
        return -2147467263L;
    }
    
    public long method13(final long[] args) {
        return -2147467263L;
    }
    
    public long method14(final long[] args) {
        return -2147467263L;
    }
    
    public long method15(final long[] args) {
        return -2147467263L;
    }
    
    public long method16(final long[] args) {
        return -2147467263L;
    }
    
    public long method17(final long[] args) {
        return -2147467263L;
    }
    
    public long method18(final long[] args) {
        return -2147467263L;
    }
    
    public long method19(final long[] args) {
        return -2147467263L;
    }
    
    public long method20(final long[] args) {
        return -2147467263L;
    }
    
    public long method21(final long[] args) {
        return -2147467263L;
    }
    
    public long method22(final long[] args) {
        return -2147467263L;
    }
    
    public long method23(final long[] args) {
        return -2147467263L;
    }
    
    public long method24(final long[] args) {
        return -2147467263L;
    }
    
    public long method25(final long[] args) {
        return -2147467263L;
    }
    
    public long method26(final long[] args) {
        return -2147467263L;
    }
    
    public long method27(final long[] args) {
        return -2147467263L;
    }
    
    public long method28(final long[] args) {
        return -2147467263L;
    }
    
    public long method29(final long[] args) {
        return -2147467263L;
    }
    
    public long method30(final long[] args) {
        return -2147467263L;
    }
    
    public long method31(final long[] args) {
        return -2147467263L;
    }
    
    public long method32(final long[] args) {
        return -2147467263L;
    }
    
    public long method33(final long[] args) {
        return -2147467263L;
    }
    
    public long method34(final long[] args) {
        return -2147467263L;
    }
    
    public long method35(final long[] args) {
        return -2147467263L;
    }
    
    public long method36(final long[] args) {
        return -2147467263L;
    }
    
    public long method37(final long[] args) {
        return -2147467263L;
    }
    
    public long method38(final long[] args) {
        return -2147467263L;
    }
    
    public long method39(final long[] args) {
        return -2147467263L;
    }
    
    public long method40(final long[] args) {
        return -2147467263L;
    }
    
    public long method41(final long[] args) {
        return -2147467263L;
    }
    
    public long method42(final long[] args) {
        return -2147467263L;
    }
    
    public long method43(final long[] args) {
        return -2147467263L;
    }
    
    public long method44(final long[] args) {
        return -2147467263L;
    }
    
    public long method45(final long[] args) {
        return -2147467263L;
    }
    
    public long method46(final long[] args) {
        return -2147467263L;
    }
    
    public long method47(final long[] args) {
        return -2147467263L;
    }
    
    public long method48(final long[] args) {
        return -2147467263L;
    }
    
    public long method49(final long[] args) {
        return -2147467263L;
    }
    
    public long method50(final long[] args) {
        return -2147467263L;
    }
    
    public long method51(final long[] args) {
        return -2147467263L;
    }
    
    public long method52(final long[] args) {
        return -2147467263L;
    }
    
    public long method53(final long[] args) {
        return -2147467263L;
    }
    
    public long method54(final long[] args) {
        return -2147467263L;
    }
    
    public long method55(final long[] args) {
        return -2147467263L;
    }
    
    public long method56(final long[] args) {
        return -2147467263L;
    }
    
    public long method57(final long[] args) {
        return -2147467263L;
    }
    
    public long method58(final long[] args) {
        return -2147467263L;
    }
    
    public long method59(final long[] args) {
        return -2147467263L;
    }
    
    public long method60(final long[] args) {
        return -2147467263L;
    }
    
    public long method61(final long[] args) {
        return -2147467263L;
    }
    
    public long method62(final long[] args) {
        return -2147467263L;
    }
    
    public long method63(final long[] args) {
        return -2147467263L;
    }
    
    public long method64(final long[] args) {
        return -2147467263L;
    }
    
    public long method65(final long[] args) {
        return -2147467263L;
    }
    
    public long method66(final long[] args) {
        return -2147467263L;
    }
    
    public long method67(final long[] args) {
        return -2147467263L;
    }
    
    public long method68(final long[] args) {
        return -2147467263L;
    }
    
    public long method69(final long[] args) {
        return -2147467263L;
    }
    
    public long method70(final long[] args) {
        return -2147467263L;
    }
    
    public long method71(final long[] args) {
        return -2147467263L;
    }
    
    public long method72(final long[] args) {
        return -2147467263L;
    }
    
    public long method73(final long[] args) {
        return -2147467263L;
    }
    
    public long method74(final long[] args) {
        return -2147467263L;
    }
    
    public long method75(final long[] args) {
        return -2147467263L;
    }
    
    public long method76(final long[] args) {
        return -2147467263L;
    }
    
    public long method77(final long[] args) {
        return -2147467263L;
    }
    
    public long method78(final long[] args) {
        return -2147467263L;
    }
    
    public long method79(final long[] args) {
        return -2147467263L;
    }
    
    static {
        COMObject.Callbacks = new Callback[80][12];
        COMObject.ObjectMap = new HashMap<LONG, COMObject>();
    }
}
