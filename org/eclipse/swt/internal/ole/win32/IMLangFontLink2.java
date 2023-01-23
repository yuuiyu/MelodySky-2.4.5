//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IMLangFontLink2 extends IUnknown
{
    public IMLangFontLink2(final long address) {
        super(address);
    }
    
    public int GetStrCodePages(final char[] pszSrc, final int cchSrc, final int dwPriorityCodePages, final int[] pdwCodePages, final int[] pcchCodePages) {
        return COM.VtblCall(4, this.address, pszSrc, cchSrc, dwPriorityCodePages, pdwCodePages, pcchCodePages);
    }
    
    public int ReleaseFont(final long hFont) {
        return COM.VtblCall(8, this.address, hFont);
    }
    
    public int MapFont(final long hDC, final int dwCodePages, final char chSrc, final long[] phDestFont) {
        return COM.VtblCall(10, this.address, hDC, dwCodePages, (int)chSrc, phDestFont);
    }
}
