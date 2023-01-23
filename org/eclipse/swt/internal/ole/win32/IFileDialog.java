//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IFileDialog extends IUnknown
{
    public IFileDialog(final long address) {
        super(address);
    }
    
    public int Show(final long hwndOwner) {
        return COM.VtblCall(3, this.address, hwndOwner);
    }
    
    public int SetFileTypes(final int cFileTypes, final long[] rgFilterSpec) {
        return COM.VtblCall(4, this.address, cFileTypes, rgFilterSpec);
    }
    
    public int SetFileTypeIndex(final int iFileType) {
        return COM.VtblCall(5, this.address, iFileType);
    }
    
    public int GetFileTypeIndex(final int[] piFileType) {
        return COM.VtblCall(6, this.address, piFileType);
    }
    
    public int SetOptions(final int fos) {
        return COM.VtblCall(9, this.address, fos);
    }
    
    public int GetOptions(final int[] pfos) {
        return COM.VtblCall(10, this.address, pfos);
    }
    
    public int SetDefaultFolder(final IShellItem psi) {
        return COM.VtblCall(11, this.address, psi.address);
    }
    
    public int SetFolder(final IShellItem psi) {
        return COM.VtblCall(12, this.address, psi.address);
    }
    
    public int SetFileName(final char[] pszName) {
        return COM.VtblCall(15, this.address, pszName);
    }
    
    public int SetTitle(final char[] pszTitle) {
        return COM.VtblCall(17, this.address, pszTitle);
    }
    
    public int GetResult(final long[] ppsi) {
        return COM.VtblCall(20, this.address, ppsi);
    }
    
    public int SetDefaultExtension(final char[] pszDefaultExtension) {
        return COM.VtblCall(22, this.address, pszDefaultExtension);
    }
    
    public int ClearClientData() {
        return COM.VtblCall(25, this.address);
    }
    
    public int GetResults(final long[] ppenum) {
        return COM.VtblCall(27, this.address, ppenum);
    }
}
