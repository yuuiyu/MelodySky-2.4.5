//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;
import java.util.*;

public class BidiUtil
{
    public static final int KEYBOARD_NON_BIDI = 0;
    public static final int KEYBOARD_BIDI = 1;
    static int isBidiPlatform;
    public static final int CLASSIN = 1;
    public static final int LINKBEFORE = 2;
    public static final int LINKAFTER = 4;
    static Map<LONG, Runnable> languageMap;
    static Map<LONG, LONG> oldProcMap;
    static Callback callback;
    static final int GCP_REORDER = 2;
    static final int GCP_GLYPHSHAPE = 16;
    static final int GCP_LIGATE = 32;
    static final int GCP_CLASSIN = 524288;
    static final byte GCPCLASS_ARABIC = 2;
    static final byte GCPCLASS_HEBREW = 2;
    static final byte GCPCLASS_LOCALNUMBER = 4;
    static final byte GCPCLASS_LATINNUMBER = 5;
    static final int GCPGLYPH_LINKBEFORE = 32768;
    static final int GCPGLYPH_LINKAFTER = 16384;
    static final int ETO_CLIPPED = 4;
    static final int ETO_GLYPH_INDEX = 16;
    static final int LANG_ARABIC = 1;
    static final int LANG_HEBREW = 13;
    static final int LANG_FARSI = 41;
    static final int HKL_NEXT = 1;
    static final int HKL_PREV = 0;
    public static final int CLASS_HEBREW = 2;
    public static final int CLASS_ARABIC = 2;
    public static final int CLASS_LOCALNUMBER = 4;
    public static final int CLASS_LATINNUMBER = 5;
    public static final int REORDER = 2;
    public static final int LIGATE = 32;
    public static final int GLYPHSHAPE = 16;
    
    public static void addLanguageListener(final long hwnd, final Runnable runnable) {
        BidiUtil.languageMap.put(new LONG(hwnd), runnable);
        subclass(hwnd);
    }
    
    public static void addLanguageListener(final Control control, final Runnable runnable) {
        addLanguageListener(control.handle, runnable);
    }
    
    static long EnumSystemLanguageGroupsProc(final long lpLangGrpId, final long lpLangGrpIdString, final long lpLangGrpName, final long options, final long lParam) {
        if ((int)lpLangGrpId == 12) {
            BidiUtil.isBidiPlatform = 1;
            return 0L;
        }
        if ((int)lpLangGrpId == 13) {
            BidiUtil.isBidiPlatform = 1;
            return 0L;
        }
        return 1L;
    }
    
    public static void drawGlyphs(final GC gc, final char[] renderBuffer, final int[] renderDx, final int x, final int y) {
        final int length = renderDx.length;
        if (OS.GetLayout(gc.handle) != 0) {
            reverse(renderDx);
            final int n2;
            final int n = n2 = length - 1;
            --renderDx[n2];
            reverse(renderBuffer);
        }
        final int oldBkMode = OS.SetBkMode(gc.handle, 1);
        OS.ExtTextOut(gc.handle, x, y, 16, null, renderBuffer, renderBuffer.length, renderDx);
        OS.SetBkMode(gc.handle, oldBkMode);
    }
    
    public static char[] getRenderInfo(final GC gc, final String text, final int[] order, final byte[] classBuffer, final int[] dx, final int flags, final int[] offsets) {
        final int fontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        final long hHeap = OS.GetProcessHeap();
        final boolean isRightOriented = OS.GetLayout(gc.handle) != 0;
        final char[] textBuffer = text.toCharArray();
        final int byteCount = textBuffer.length;
        final boolean linkBefore = (flags & 0x2) == 0x2;
        final boolean linkAfter = (flags & 0x4) == 0x4;
        final GCP_RESULTS result = new GCP_RESULTS();
        result.lStructSize = GCP_RESULTS.sizeof;
        result.nGlyphs = byteCount;
        final GCP_RESULTS gcp_RESULTS = result;
        final long heapAlloc = OS.HeapAlloc(hHeap, 8, byteCount * 4);
        gcp_RESULTS.lpOrder = heapAlloc;
        final long lpOrder = heapAlloc;
        final GCP_RESULTS gcp_RESULTS2 = result;
        final long heapAlloc2 = OS.HeapAlloc(hHeap, 8, byteCount * 4);
        gcp_RESULTS2.lpDx = heapAlloc2;
        final long lpDx = heapAlloc2;
        final GCP_RESULTS gcp_RESULTS3 = result;
        final long heapAlloc3 = OS.HeapAlloc(hHeap, 8, byteCount);
        gcp_RESULTS3.lpClass = heapAlloc3;
        final long lpClass = heapAlloc3;
        final GCP_RESULTS gcp_RESULTS4 = result;
        final long heapAlloc4 = OS.HeapAlloc(hHeap, 8, byteCount * 2);
        gcp_RESULTS4.lpGlyphs = heapAlloc4;
        final long lpGlyphs = heapAlloc4;
        int dwFlags = 0;
        int glyphFlags = 0;
        dwFlags |= 0x2;
        if ((fontLanguageInfo & 0x20) == 0x20) {
            dwFlags |= 0x20;
            glyphFlags |= 0x0;
        }
        if ((fontLanguageInfo & 0x10) == 0x10) {
            dwFlags |= 0x10;
            if (linkBefore) {
                glyphFlags |= 0x8000;
            }
            if (linkAfter) {
                glyphFlags |= 0x4000;
            }
        }
        byte[] lpGlyphs2;
        if (linkBefore || linkAfter) {
            lpGlyphs2 = new byte[] { (byte)glyphFlags, (byte)(glyphFlags >> 8) };
        }
        else {
            lpGlyphs2 = new byte[] { (byte)glyphFlags };
        }
        OS.MoveMemory(result.lpGlyphs, lpGlyphs2, lpGlyphs2.length);
        if ((flags & 0x1) == 0x1) {
            dwFlags |= 0x80000;
            OS.MoveMemory(result.lpClass, classBuffer, classBuffer.length);
        }
        final char[] glyphBuffer = new char[result.nGlyphs];
        int glyphCount = 0;
        for (int i = 0; i < offsets.length - 1; ++i) {
            final int offset = offsets[i];
            final int length = offsets[i + 1] - offsets[i];
            text.getChars(offset, offset + (result.nGlyphs = length), textBuffer, 0);
            OS.GetCharacterPlacement(gc.handle, textBuffer, length, 0, result, dwFlags);
            if (dx != null) {
                final int[] dx2 = new int[result.nGlyphs];
                OS.MoveMemory(dx2, result.lpDx, dx2.length * 4);
                if (isRightOriented) {
                    reverse(dx2);
                }
                System.arraycopy(dx2, 0, dx, glyphCount, dx2.length);
            }
            if (order != null) {
                final int[] order2 = new int[length];
                OS.MoveMemory(order2, result.lpOrder, order2.length * 4);
                translateOrder(order2, glyphCount, isRightOriented);
                System.arraycopy(order2, 0, order, offset, length);
            }
            if (classBuffer != null) {
                final byte[] classBuffer2 = new byte[length];
                OS.MoveMemory(classBuffer2, result.lpClass, classBuffer2.length);
                System.arraycopy(classBuffer2, 0, classBuffer, offset, length);
            }
            final char[] glyphBuffer2 = new char[result.nGlyphs];
            OS.MoveMemory(glyphBuffer2, result.lpGlyphs, glyphBuffer2.length * 2);
            if (isRightOriented) {
                reverse(glyphBuffer2);
            }
            System.arraycopy(glyphBuffer2, 0, glyphBuffer, glyphCount, glyphBuffer2.length);
            glyphCount += glyphBuffer2.length;
            final GCP_RESULTS gcp_RESULTS9;
            final GCP_RESULTS gcp_RESULTS5 = gcp_RESULTS9 = result;
            gcp_RESULTS9.lpOrder += length * 4;
            final GCP_RESULTS gcp_RESULTS10;
            final GCP_RESULTS gcp_RESULTS6 = gcp_RESULTS10 = result;
            gcp_RESULTS10.lpDx += length * 4;
            final GCP_RESULTS gcp_RESULTS11;
            final GCP_RESULTS gcp_RESULTS7 = gcp_RESULTS11 = result;
            gcp_RESULTS11.lpClass += length;
            final GCP_RESULTS gcp_RESULTS12;
            final GCP_RESULTS gcp_RESULTS8 = gcp_RESULTS12 = result;
            gcp_RESULTS12.lpGlyphs += glyphBuffer2.length * 2;
        }
        OS.HeapFree(hHeap, 0, lpGlyphs);
        OS.HeapFree(hHeap, 0, lpClass);
        OS.HeapFree(hHeap, 0, lpDx);
        OS.HeapFree(hHeap, 0, lpOrder);
        return glyphBuffer;
    }
    
    public static void getOrderInfo(final GC gc, final String text, final int[] order, final byte[] classBuffer, final int flags, final int[] offsets) {
        final int fontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        final long hHeap = OS.GetProcessHeap();
        final char[] textBuffer = text.toCharArray();
        final int byteCount = textBuffer.length;
        final boolean isRightOriented = OS.GetLayout(gc.handle) != 0;
        final GCP_RESULTS result = new GCP_RESULTS();
        result.lStructSize = GCP_RESULTS.sizeof;
        result.nGlyphs = byteCount;
        final GCP_RESULTS gcp_RESULTS = result;
        final long heapAlloc = OS.HeapAlloc(hHeap, 8, byteCount * 4);
        gcp_RESULTS.lpOrder = heapAlloc;
        final long lpOrder = heapAlloc;
        final GCP_RESULTS gcp_RESULTS2 = result;
        final long heapAlloc2 = OS.HeapAlloc(hHeap, 8, byteCount);
        gcp_RESULTS2.lpClass = heapAlloc2;
        final long lpClass = heapAlloc2;
        int dwFlags = 0;
        dwFlags |= 0x2;
        if ((fontLanguageInfo & 0x20) == 0x20) {
            dwFlags |= 0x20;
        }
        if ((fontLanguageInfo & 0x10) == 0x10) {
            dwFlags |= 0x10;
        }
        if ((flags & 0x1) == 0x1) {
            dwFlags |= 0x80000;
            OS.MoveMemory(result.lpClass, classBuffer, classBuffer.length);
        }
        int glyphCount = 0;
        for (int i = 0; i < offsets.length - 1; ++i) {
            final int offset = offsets[i];
            final int length = offsets[i + 1] - offsets[i];
            text.getChars(offset, offset + (result.nGlyphs = length), textBuffer, 0);
            OS.GetCharacterPlacement(gc.handle, textBuffer, length, 0, result, dwFlags);
            if (order != null) {
                final int[] order2 = new int[length];
                OS.MoveMemory(order2, result.lpOrder, order2.length * 4);
                translateOrder(order2, glyphCount, isRightOriented);
                System.arraycopy(order2, 0, order, offset, length);
            }
            if (classBuffer != null) {
                final byte[] classBuffer2 = new byte[length];
                OS.MoveMemory(classBuffer2, result.lpClass, classBuffer2.length);
                System.arraycopy(classBuffer2, 0, classBuffer, offset, length);
            }
            glyphCount += result.nGlyphs;
            final GCP_RESULTS gcp_RESULTS5;
            final GCP_RESULTS gcp_RESULTS3 = gcp_RESULTS5 = result;
            gcp_RESULTS5.lpOrder += length * 4;
            final GCP_RESULTS gcp_RESULTS6;
            final GCP_RESULTS gcp_RESULTS4 = gcp_RESULTS6 = result;
            gcp_RESULTS6.lpClass += length;
        }
        OS.HeapFree(hHeap, 0, lpClass);
        OS.HeapFree(hHeap, 0, lpOrder);
    }
    
    public static int getFontBidiAttributes(final GC gc) {
        int fontStyle = 0;
        final int fontLanguageInfo = OS.GetFontLanguageInfo(gc.handle);
        if ((fontLanguageInfo & 0x2) != 0x0) {
            fontStyle |= 0x2;
        }
        if ((fontLanguageInfo & 0x20) != 0x0) {
            fontStyle |= 0x20;
        }
        if ((fontLanguageInfo & 0x10) != 0x0) {
            fontStyle |= 0x10;
        }
        return fontStyle;
    }
    
    public static int getKeyboardLanguage() {
        final long layout = OS.GetKeyboardLayout(0);
        return isBidiLang(layout) ? 1 : 0;
    }
    
    static long[] getKeyboardLanguageList() {
        final int maxSize = 10;
        final long[] tempList = new long[10];
        final int size = OS.GetKeyboardLayoutList(10, tempList);
        final long[] list = new long[size];
        System.arraycopy(tempList, 0, list, 0, size);
        return list;
    }
    
    static boolean isBidiLang(final long lang) {
        final int id = OS.PRIMARYLANGID(OS.LOWORD(lang));
        return id == 1 || id == 13 || id == 41;
    }
    
    public static boolean isBidiPlatform() {
        if (BidiUtil.isBidiPlatform != -1) {
            return BidiUtil.isBidiPlatform == 1;
        }
        BidiUtil.isBidiPlatform = 0;
        if (!isKeyboardBidi()) {
            return false;
        }
        final Callback callback = new Callback(BidiUtil.class, "EnumSystemLanguageGroupsProc", 5);
        OS.EnumSystemLanguageGroups(callback.getAddress(), 1, 0L);
        callback.dispose();
        return BidiUtil.isBidiPlatform == 1;
    }
    
    public static boolean isKeyboardBidi() {
        for (final long language : getKeyboardLanguageList()) {
            if (isBidiLang(language)) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeLanguageListener(final long hwnd) {
        BidiUtil.languageMap.remove(new LONG(hwnd));
        unsubclass(hwnd);
    }
    
    public static void removeLanguageListener(final Control control) {
        removeLanguageListener(control.handle);
    }
    
    public static int resolveTextDirection(final String text) {
        if (text == null) {
            return 0;
        }
        int textDirection = 0;
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            final byte directionality = Character.getDirectionality(c);
            final int strongDirection = getStrongDirection(directionality);
            if (strongDirection != 0) {
                textDirection = strongDirection;
            }
            if (textDirection == 67108864) {
                break;
            }
        }
        return textDirection;
    }
    
    static int getStrongDirection(final byte directionality) {
        switch (directionality) {
            case 0: {
                return 33554432;
            }
            case 1:
            case 2: {
                return 67108864;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static void setKeyboardLanguage(final int language) {
        if (language == getKeyboardLanguage()) {
            return;
        }
        final boolean bidi = language == 1;
        for (final long element : getKeyboardLanguageList()) {
            if (bidi == isBidiLang(element)) {
                OS.ActivateKeyboardLayout(element, 0);
                return;
            }
        }
    }
    
    public static boolean setOrientation(final long hwnd, final int orientation) {
        int bits = OS.GetWindowLong(hwnd, -20);
        if ((orientation & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        else {
            bits &= 0xFFBFFFFF;
        }
        OS.SetWindowLong(hwnd, -20, bits);
        return true;
    }
    
    public static boolean setOrientation(final Control control, final int orientation) {
        return setOrientation(control.handle, orientation);
    }
    
    static void subclass(final long hwnd) {
        final LONG key = new LONG(hwnd);
        if (BidiUtil.oldProcMap.get(key) == null) {
            final long oldProc = OS.GetWindowLongPtr(hwnd, -4);
            BidiUtil.oldProcMap.put(key, new LONG(oldProc));
            OS.SetWindowLongPtr(hwnd, -4, BidiUtil.callback.getAddress());
        }
    }
    
    static void reverse(final char[] charArray) {
        for (int length = charArray.length, i = 0; i <= (length - 1) / 2; ++i) {
            final char tmp = charArray[i];
            charArray[i] = charArray[length - 1 - i];
            charArray[length - 1 - i] = tmp;
        }
    }
    
    static void reverse(final int[] intArray) {
        for (int length = intArray.length, i = 0; i <= (length - 1) / 2; ++i) {
            final int tmp = intArray[i];
            intArray[i] = intArray[length - 1 - i];
            intArray[length - 1 - i] = tmp;
        }
    }
    
    static void translateOrder(final int[] orderArray, final int glyphCount, final boolean isRightOriented) {
        int maxOrder = 0;
        final int length = orderArray.length;
        if (isRightOriented) {
            for (int i = 0; i < length; ++i) {
                maxOrder = Math.max(maxOrder, orderArray[i]);
            }
        }
        for (int i = 0; i < length; ++i) {
            if (isRightOriented) {
                orderArray[i] = maxOrder - orderArray[i];
            }
            final int n2;
            final int n = n2 = i;
            orderArray[n2] += glyphCount;
        }
    }
    
    static void unsubclass(final long hwnd) {
        final LONG key = new LONG(hwnd);
        if (BidiUtil.languageMap.get(key) == null) {
            final LONG proc = BidiUtil.oldProcMap.remove(key);
            if (proc == null) {
                return;
            }
            OS.SetWindowLongPtr(hwnd, -4, proc.value);
        }
    }
    
    static long windowProc(final long hwnd, final long msg, final long wParam, final long lParam) {
        final LONG key = new LONG(hwnd);
        switch ((int)msg) {
            case 81: {
                final Runnable runnable = BidiUtil.languageMap.get(key);
                if (runnable != null) {
                    runnable.run();
                    break;
                }
                break;
            }
        }
        final LONG oldProc = BidiUtil.oldProcMap.get(key);
        return OS.CallWindowProc(oldProc.value, hwnd, (int)msg, wParam, lParam);
    }
    
    static {
        BidiUtil.isBidiPlatform = -1;
        BidiUtil.languageMap = new HashMap<LONG, Runnable>();
        BidiUtil.oldProcMap = new HashMap<LONG, LONG>();
        BidiUtil.callback = new Callback(BidiUtil.class, "windowProc", 4);
    }
}
