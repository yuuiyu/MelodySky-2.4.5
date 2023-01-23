//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.*;
import java.nio.file.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.ole.win32.*;

public class FileDialog extends Dialog
{
    String[] filterNames;
    String[] filterExtensions;
    String[] fileNames;
    String filterPath;
    String fileName;
    int filterIndex;
    boolean overwrite;
    static final String DEFAULT_FILTER = "*.*";
    static final String LONG_PATH_PREFIX = "\\\\?\\";
    
    public FileDialog(final Shell parent) {
        this(parent, 65536);
    }
    
    public FileDialog(final Shell parent, final int style) {
        super(parent, Dialog.checkStyle(parent, style));
        this.filterNames = new String[0];
        this.filterExtensions = new String[0];
        this.fileNames = new String[0];
        this.filterPath = "";
        this.fileName = "";
        this.filterIndex = 0;
        this.overwrite = false;
        this.checkSubclass();
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String[] getFileNames() {
        return this.fileNames;
    }
    
    public String[] getFilterExtensions() {
        return this.filterExtensions;
    }
    
    public int getFilterIndex() {
        return this.filterIndex;
    }
    
    public String[] getFilterNames() {
        return this.filterNames;
    }
    
    public String getFilterPath() {
        return this.filterPath;
    }
    
    public boolean getOverwrite() {
        return this.overwrite;
    }
    
    static Path getItemPath(final IShellItem psi) {
        final long[] ppsz = { 0L };
        if (psi.GetDisplayName(-2147123200, ppsz) == 0) {
            final int length = OS.wcslen(ppsz[0]);
            final char[] buffer = new char[length];
            OS.MoveMemory(buffer, ppsz[0], length * 2);
            OS.CoTaskMemFree(ppsz[0]);
            String path = String.valueOf(buffer);
            if (path.startsWith("\\\\?\\")) {
                path = path.substring("\\\\?\\".length());
            }
            return Paths.get(path, new String[0]);
        }
        return null;
    }
    
    public String open() {
        final long[] ppv = { 0L };
        int hr;
        if ((this.style & 0x2000) != 0x0) {
            hr = COM.CoCreateInstance(COM.CLSID_FileSaveDialog, 0L, 1, COM.IID_IFileSaveDialog, ppv);
        }
        else {
            hr = COM.CoCreateInstance(COM.CLSID_FileOpenDialog, 0L, 1, COM.IID_IFileOpenDialog, ppv);
        }
        if (hr != 0) {
            SWT.error(2);
        }
        final IFileDialog fileDialog = new IFileDialog(ppv[0]);
        final int[] options = { 0 };
        fileDialog.GetOptions(options);
        final int[] array = options;
        final int n = 0;
        final int[] array5 = array;
        final int n5 = 0;
        array5[n5] |= 0x48;
        final int[] array2 = options;
        final int n2 = 0;
        final int[] array6 = array2;
        final int n6 = 0;
        array6[n6] &= 0xFFFFEFFF;
        if ((this.style & 0x2000) != 0x0) {
            if (!this.overwrite) {
                final int[] array3 = options;
                final int n3 = 0;
                final int[] array7 = array3;
                final int n7 = 0;
                array7[n7] &= 0xFFFFFFFD;
            }
        }
        else if ((this.style & 0x2) != 0x0) {
            final int[] array4 = options;
            final int n4 = 0;
            final int[] array8 = array4;
            final int n8 = 0;
            array8[n8] |= 0x200;
        }
        fileDialog.SetOptions(options[0]);
        if (!this.title.isEmpty()) {
            fileDialog.SetTitle(this.title.toCharArray());
        }
        String[] filterExtensions = this.filterExtensions;
        String[] filterNames = this.filterNames;
        if (filterExtensions == null || filterExtensions.length == 0) {
            filterExtensions = (filterNames = new String[] { "*.*" });
        }
        final long hHeap = OS.GetProcessHeap();
        final long[] filterSpec = new long[filterExtensions.length * 2];
        for (int i = 0; i < filterExtensions.length; ++i) {
            final String extension = filterExtensions[i];
            String name = (filterNames != null && i < filterNames.length) ? filterNames[i] : extension;
            if (!name.contains("*.")) {
                final int[] result = OS.readRegistryDwords(-2147483647, "Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced", "HideFileExt");
                if (result != null && result[0] == 0) {
                    name = name.replace(" (" + extension, "");
                }
            }
            final long lpstrName = OS.HeapAlloc(hHeap, 8, (name.length() + 1) * 2);
            final long lpstrExt = OS.HeapAlloc(hHeap, 8, (extension.length() + 1) * 2);
            OS.MoveMemory(lpstrName, name.toCharArray(), name.length() * 2);
            OS.MoveMemory(lpstrExt, extension.toCharArray(), extension.length() * 2);
            filterSpec[i * 2] = lpstrName;
            filterSpec[i * 2 + 1] = lpstrExt;
        }
        fileDialog.SetFileTypes(filterExtensions.length, filterSpec);
        for (int i = 0; i < filterSpec.length; ++i) {
            OS.HeapFree(hHeap, 0, filterSpec[i]);
        }
        fileDialog.SetDefaultExtension(new char[1]);
        fileDialog.SetFileTypeIndex(this.filterIndex + 1);
        if (this.filterPath != null) {
            final char[] path = this.filterPath.replace('/', '\\').toCharArray();
            if (COM.SHCreateItemFromParsingName(path, 0L, COM.IID_IShellItem, ppv) == 0) {
                final IShellItem psi = new IShellItem(ppv[0]);
                fileDialog.SetDefaultFolder(psi);
                psi.Release();
            }
        }
        if (this.fileName != null) {
            final char[] name2 = this.fileName.replace('/', '\\').toCharArray();
            fileDialog.SetFileName(name2);
        }
        Dialog oldModal = null;
        final Display display = this.parent.getDisplay();
        if ((this.style & 0x30000) != 0x0) {
            oldModal = display.getModalDialog();
            display.setModalDialog((Dialog)this);
        }
        display.externalEventLoop = true;
        display.sendPreExternalEventDispatchEvent();
        hr = fileDialog.Show(this.parent.handle);
        display.externalEventLoop = false;
        display.sendPostExternalEventDispatchEvent();
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(oldModal);
        }
        String fullPath = null;
        this.fileNames = new String[0];
        if (hr == 0) {
            if ((this.style & 0x2000) != 0x0) {
                if (fileDialog.GetResult(ppv) == 0) {
                    final IShellItem psi2 = new IShellItem(ppv[0]);
                    final Path itemPath = getItemPath(psi2);
                    psi2.Release();
                    this.fileName = itemPath.getFileName().toString();
                    this.filterPath = itemPath.getParent().toString();
                    this.fileNames = new String[] { this.fileName };
                    fullPath = itemPath.toString();
                }
            }
            else if (fileDialog.GetResults(ppv) == 0) {
                final IShellItemArray psia = new IShellItemArray(ppv[0]);
                final int[] piCount = { 0 };
                psia.GetCount(piCount);
                this.fileNames = new String[piCount[0]];
                Path parentPath = null;
                for (int j = 0; j < piCount[0]; ++j) {
                    psia.GetItemAt(j, ppv);
                    final IShellItem psi3 = new IShellItem(ppv[0]);
                    final Path itemPath2 = getItemPath(psi3);
                    psi3.Release();
                    if (parentPath == null) {
                        parentPath = itemPath2.getParent();
                        this.filterPath = parentPath.toString();
                        fullPath = itemPath2.toString();
                    }
                    if (itemPath2.getParent().equals(parentPath)) {
                        this.fileNames[j] = itemPath2.getFileName().toString();
                    }
                    else {
                        this.fileNames[j] = itemPath2.toString();
                    }
                }
                this.fileName = this.fileNames[0];
                psia.Release();
            }
            final int[] piIndex = { 0 };
            if (fileDialog.GetFileTypeIndex(piIndex) == 0) {
                this.filterIndex = piIndex[0] - 1;
            }
        }
        fileDialog.Release();
        return fullPath;
    }
    
    public void setFileName(final String string) {
        this.fileName = string;
    }
    
    public void setFilterExtensions(final String[] extensions) {
        this.filterExtensions = extensions;
    }
    
    public void setFilterIndex(final int index) {
        this.filterIndex = index;
    }
    
    public void setFilterNames(final String[] names) {
        this.filterNames = names;
    }
    
    public void setFilterPath(final String string) {
        this.filterPath = string;
    }
    
    public void setOverwrite(final boolean overwrite) {
        this.overwrite = overwrite;
    }
}
