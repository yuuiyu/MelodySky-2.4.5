//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.win32.*;

public class DirectoryDialog extends Dialog
{
    String message;
    String filterPath;
    String directoryPath;
    
    public DirectoryDialog(final Shell parent) {
        this(parent, 65536);
    }
    
    public DirectoryDialog(final Shell parent, final int style) {
        super(parent, Dialog.checkStyle(parent, style));
        this.message = "";
        this.filterPath = "";
        this.checkSubclass();
    }
    
    public String getFilterPath() {
        return this.filterPath;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String open() {
        this.directoryPath = null;
        final long[] ppv = { 0L };
        if (COM.CoCreateInstance(COM.CLSID_FileOpenDialog, 0L, 1, COM.IID_IFileOpenDialog, ppv) == 0) {
            final IFileDialog fileDialog = new IFileDialog(ppv[0]);
            final int[] options = { 0 };
            if (fileDialog.GetOptions(options) == 0) {
                final int[] array = options;
                final int n = 0;
                final int[] array2 = array;
                final int n2 = 0;
                array2[n2] |= 0x68;
                fileDialog.SetOptions(options[0]);
            }
            if (this.title == null) {
                this.title = "";
            }
            if (this.title.length() > 0) {
                final char[] buffer = new char[this.title.length() + 1];
                this.title.getChars(0, this.title.length(), buffer, 0);
                fileDialog.SetTitle(buffer);
            }
            if (this.filterPath != null && this.filterPath.length() > 0) {
                final String path = this.filterPath.replace('/', '\\');
                final char[] buffer2 = new char[path.length() + 1];
                path.getChars(0, path.length(), buffer2, 0);
                if (COM.SHCreateItemFromParsingName(buffer2, 0L, COM.IID_IShellItem, ppv) == 0) {
                    final IShellItem psi = new IShellItem(ppv[0]);
                    fileDialog.ClearClientData();
                    fileDialog.SetDefaultFolder(psi);
                    psi.Release();
                }
            }
            final Display display = this.parent.getDisplay();
            final long hwndOwner = this.parent.handle;
            display.externalEventLoop = true;
            if (fileDialog.Show(hwndOwner) == 0 && fileDialog.GetResult(ppv) == 0) {
                final IShellItem psi2 = new IShellItem(ppv[0]);
                if (psi2.GetDisplayName(-2147123200, ppv) == 0) {
                    final long wstr = ppv[0];
                    final int length = OS.wcslen(wstr);
                    final char[] buffer3 = new char[length];
                    OS.MoveMemory(buffer3, wstr, length * 2);
                    OS.CoTaskMemFree(wstr);
                    this.directoryPath = new String(buffer3);
                }
                psi2.Release();
            }
            display.externalEventLoop = false;
            fileDialog.Release();
        }
        return this.directoryPath;
    }
    
    public void setFilterPath(final String string) {
        this.filterPath = string;
    }
    
    public void setMessage(final String string) {
        if (string == null) {
            this.error(4);
        }
        this.message = string;
    }
}
