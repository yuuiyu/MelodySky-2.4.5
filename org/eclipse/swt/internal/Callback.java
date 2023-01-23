//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.*;
import java.lang.reflect.*;
import java.util.function.*;

public class Callback
{
    Object object;
    String method;
    String signature;
    int argCount;
    long address;
    long errorResult;
    boolean isStatic;
    boolean isArrayBased;
    static final boolean is32Bit;
    static final String PTR_SIGNATURE;
    static final String SIGNATURE_0;
    static final String SIGNATURE_1;
    static final String SIGNATURE_2;
    static final String SIGNATURE_3;
    static final String SIGNATURE_4;
    static final String SIGNATURE_N;
    
    public Callback(final Object object, final String method, final int argCount) {
        this(object, method, argCount, false);
    }
    
    public Callback(final Object object, final String method, final int argCount, final boolean isArrayBased) {
        this(object, method, argCount, isArrayBased, 0L);
    }
    
    public Callback(final Object object, final String method, final int argCount, final boolean isArrayBased, final long errorResult) {
        this.object = object;
        this.method = method;
        this.argCount = argCount;
        this.isStatic = (object instanceof Class);
        this.isArrayBased = isArrayBased;
        this.errorResult = errorResult;
        if (isArrayBased) {
            this.signature = Callback.SIGNATURE_N;
        }
        else {
            switch (argCount) {
                case 0: {
                    this.signature = Callback.SIGNATURE_0;
                    break;
                }
                case 1: {
                    this.signature = Callback.SIGNATURE_1;
                    break;
                }
                case 2: {
                    this.signature = Callback.SIGNATURE_2;
                    break;
                }
                case 3: {
                    this.signature = Callback.SIGNATURE_3;
                    break;
                }
                case 4: {
                    this.signature = Callback.SIGNATURE_4;
                    break;
                }
                default: {
                    this.signature = getSignature(argCount);
                    break;
                }
            }
        }
        this.address = bind(this, object, method, this.signature, argCount, this.isStatic, isArrayBased, errorResult);
        if (this.address == 0L) {
            SWT.error(3);
        }
    }
    
    public Callback(final Object object, final String method, final Type returnType, final Type[] arguments) {
        this.object = object;
        this.method = method;
        this.argCount = ((arguments != null) ? arguments.length : 0);
        this.isStatic = (object instanceof Class);
        this.isArrayBased = false;
        this.errorResult = 0L;
        final Function<Type, String> getTypeLetter = (Function<Type, String>)(type -> {
            if (Integer.TYPE.equals(type)) {
                return "I";
            }
            else if (Long.TYPE.equals(type)) {
                return "J";
            }
            else if (Void.TYPE.equals(type)) {
                return "V";
            }
            else if (Byte.TYPE.equals(type)) {
                return "B";
            }
            else if (Character.TYPE.equals(type)) {
                return "C";
            }
            else if (Double.TYPE.equals(type)) {
                return "D";
            }
            else if (Float.TYPE.equals(type)) {
                return "F";
            }
            else if (Short.TYPE.equals(type)) {
                return "S";
            }
            else if (Boolean.TYPE.equals(type)) {
                return "Z";
            }
            else {
                SWT.error(5, null, type.toString() + "Not supported");
                return null;
            }
        });
        final StringBuilder signature = new StringBuilder("(");
        if (this.argCount > 0) {
            for (final Type t : arguments) {
                if (t.equals(Void.TYPE)) {
                    SWT.error(5, null, "void is not a valid argument");
                }
                signature.append(getTypeLetter.apply(t));
            }
        }
        signature.append(")");
        signature.append(getTypeLetter.apply(returnType));
        this.signature = signature.toString();
        if (Callback.is32Bit) {
            this.signature = this.signature.replace("J", "I");
        }
        this.address = bind(this, this.object, this.method, this.signature, this.argCount, this.isStatic, this.isArrayBased, this.errorResult);
        if (this.address == 0L) {
            SWT.error(3);
        }
    }
    
    static synchronized native long bind(final Callback p0, final Object p1, final String p2, final String p3, final int p4, final boolean p5, final boolean p6, final long p7);
    
    public void dispose() {
        if (this.object == null) {
            return;
        }
        unbind(this);
        final String object = null;
        this.signature = object;
        this.method = object;
        this.object = object;
        this.address = 0L;
    }
    
    public long getAddress() {
        return this.address;
    }
    
    public static native String getPlatform();
    
    public static native int getEntryCount();
    
    static String getSignature(final int argCount) {
        String signature = "(";
        for (int i = 0; i < argCount; ++i) {
            signature += Callback.PTR_SIGNATURE;
        }
        signature = signature + ")" + Callback.PTR_SIGNATURE;
        return signature;
    }
    
    public static final synchronized native void setEnabled(final boolean p0);
    
    public static final synchronized native boolean getEnabled();
    
    @Deprecated
    static final void ignoreCallbacks(final boolean ignore) {
        setEnabled(!ignore);
    }
    
    public static final synchronized native void reset();
    
    static final synchronized native void unbind(final Callback p0);
    
    static {
        is32Bit = (C.PTR_SIZEOF == 4);
        PTR_SIGNATURE = (Callback.is32Bit ? "I" : "J");
        SIGNATURE_0 = getSignature(0);
        SIGNATURE_1 = getSignature(1);
        SIGNATURE_2 = getSignature(2);
        SIGNATURE_3 = getSignature(3);
        SIGNATURE_4 = getSignature(4);
        SIGNATURE_N = "([" + Callback.PTR_SIGNATURE + ")" + Callback.PTR_SIGNATURE;
    }
}
