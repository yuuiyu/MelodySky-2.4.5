//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import java.io.*;
import org.eclipse.swt.internal.ole.win32.*;

public class TaskBar extends Widget
{
    int itemCount;
    TaskItem[] items;
    ITaskbarList3 mTaskbarList3;
    String iconsDir;
    static final char[] EXE_PATH;
    static final PROPERTYKEY PKEY_Title;
    static final PROPERTYKEY PKEY_AppUserModel_IsDestListSeparator;
    static final String EXE_PATH_KEY = "org.eclipse.swt.win32.taskbar.executable";
    static final String EXE_ARGS_KEY = "org.eclipse.swt.win32.taskbar.arguments";
    static final String ICON_KEY = "org.eclipse.swt.win32.taskbar.icon";
    static final String ICON_INDEX_KEY = "org.eclipse.swt.win32.taskbar.icon.index";
    
    TaskBar(final Display display, final int style) {
        this.items = new TaskItem[4];
        this.display = display;
        this.createHandle();
        this.reskinWidget();
    }
    
    void createHandle() {
        final long[] ppv = { 0L };
        final int hr = COM.CoCreateInstance(COM.CLSID_TaskbarList, 0L, 1, COM.IID_ITaskbarList3, ppv);
        if (hr == -2147221164) {
            this.error(20);
        }
        if (hr != 0) {
            this.error(2);
        }
        this.mTaskbarList3 = new ITaskbarList3(ppv[0]);
    }
    
    void createItem(final TaskItem item, int index) {
        if (index == -1) {
            index = this.itemCount;
        }
        if (0 > index || index > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final TaskItem[] newItems = new TaskItem[this.items.length + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        System.arraycopy(this.items, index, this.items, index + 1, this.itemCount++ - index);
        this.items[index] = item;
    }
    
    void createItems() {
        for (final Shell shell : this.display.getShells()) {
            this.getItem(shell);
        }
        this.getItem(null);
    }
    
    IShellLink createShellLink(final MenuItem item) {
        final int style = item.getStyle();
        if ((style & 0x40) != 0x0) {
            return null;
        }
        final long[] ppv = { 0L };
        int hr = COM.CoCreateInstance(COM.CLSID_ShellLink, 0L, 1, COM.IID_IShellLinkW, ppv);
        if (hr != 0) {
            this.error(2);
        }
        final IShellLink pLink = new IShellLink(ppv[0]);
        final long hHeap = OS.GetProcessHeap();
        final long pv = OS.HeapAlloc(hHeap, 8, OS.PROPVARIANT_sizeof());
        long titlePtr = 0L;
        PROPERTYKEY key;
        if ((style & 0x2) != 0x0) {
            OS.MoveMemory(pv, new short[] { 11 }, 2);
            OS.MoveMemory(pv + 8L, new short[] { -1 }, 2);
            key = TaskBar.PKEY_AppUserModel_IsDestListSeparator;
        }
        else {
            String text = item.getText();
            int length = text.length();
            char[] buffer = new char[length + 1];
            text.getChars(0, length, buffer, 0);
            titlePtr = OS.HeapAlloc(hHeap, 8, buffer.length * 2);
            OS.MoveMemory(titlePtr, buffer, buffer.length * 2);
            OS.MoveMemory(pv, new short[] { 31 }, 2);
            OS.MoveMemory(pv + 8L, new long[] { titlePtr }, C.PTR_SIZEOF);
            key = TaskBar.PKEY_Title;
            final String exePath = (String)item.getData("org.eclipse.swt.win32.taskbar.executable");
            if (exePath != null) {
                length = exePath.length();
                buffer = new char[length + 1];
                exePath.getChars(0, length, buffer, 0);
            }
            else {
                buffer = TaskBar.EXE_PATH;
            }
            hr = pLink.SetPath(buffer);
            if (hr != 0) {
                this.error(5);
            }
            text = (String)item.getData("org.eclipse.swt.win32.taskbar.arguments");
            if (text == null) {
                text = "--launcher.openFile /SWTINTERNAL_ID" + item.id;
            }
            length = text.length();
            buffer = new char[length + 1];
            text.getChars(0, length, buffer, 0);
            hr = pLink.SetArguments(buffer);
            if (hr != 0) {
                this.error(5);
            }
            String icon = (String)item.getData("org.eclipse.swt.win32.taskbar.icon");
            int index = 0;
            if (icon != null) {
                text = (String)item.getData("org.eclipse.swt.win32.taskbar.icon.index");
                if (text != null) {
                    index = Integer.parseInt(text);
                }
            }
            else {
                String directory = null;
                final Image image = item.getImage();
                if (image != null) {
                    directory = this.getIconsDir();
                }
                if (directory != null) {
                    icon = directory + "\\menu" + item.id + ".ico";
                    ImageData data;
                    if (item.hBitmap != 0L) {
                        final Image image2 = Image.win32_new((Device)this.display, 0, item.hBitmap);
                        data = image2.getImageData(DPIUtil.getDeviceZoom());
                        image2.handle = 0L;
                    }
                    else {
                        data = image.getImageData(DPIUtil.getDeviceZoom());
                    }
                    final ImageLoader loader = new ImageLoader();
                    loader.data = new ImageData[] { data };
                    loader.save(icon, 3);
                }
            }
            if (icon != null) {
                length = icon.length();
                buffer = new char[length + 1];
                icon.getChars(0, length, buffer, 0);
                hr = pLink.SetIconLocation(buffer, index);
                if (hr != 0) {
                    this.error(5);
                }
            }
        }
        hr = pLink.QueryInterface(COM.IID_IPropertyStore, ppv);
        if (hr != 0) {
            this.error(2);
        }
        final IPropertyStore pPropStore = new IPropertyStore(ppv[0]);
        hr = pPropStore.SetValue(key, pv);
        if (hr != 0) {
            this.error(5);
        }
        pPropStore.Commit();
        pPropStore.Release();
        OS.HeapFree(hHeap, 0, pv);
        if (titlePtr != 0L) {
            OS.HeapFree(hHeap, 0, titlePtr);
        }
        return pLink;
    }
    
    IObjectArray createShellLinkArray(final MenuItem[] items) {
        if (items == null) {
            return null;
        }
        if (items.length == 0) {
            return null;
        }
        final long[] ppv = { 0L };
        int hr = COM.CoCreateInstance(COM.CLSID_EnumerableObjectCollection, 0L, 1, COM.IID_IObjectCollection, ppv);
        if (hr != 0) {
            this.error(2);
        }
        final IObjectCollection pObjColl = new IObjectCollection(ppv[0]);
        for (final MenuItem item : items) {
            final IShellLink pLink = this.createShellLink(item);
            if (pLink != null) {
                pObjColl.AddObject((IUnknown)pLink);
                if (hr != 0) {
                    this.error(5);
                }
                pLink.Release();
            }
        }
        hr = pObjColl.QueryInterface(COM.IID_IObjectArray, ppv);
        if (hr != 0) {
            this.error(2);
        }
        final IObjectArray poa = new IObjectArray(ppv[0]);
        pObjColl.Release();
        return poa;
    }
    
    void destroyItem(final TaskItem item) {
        int index;
        for (index = 0; index < this.itemCount && this.items[index] != item; ++index) {}
        if (index == this.itemCount) {
            return;
        }
        System.arraycopy(this.items, index + 1, this.items, index, --this.itemCount - index);
        this.items[this.itemCount] = null;
    }
    
    String getIconsDir() {
        if (this.iconsDir != null) {
            return this.iconsDir;
        }
        final File dir = new File(this.display.appLocalDir + "\\ico_dir");
        if (dir.exists()) {
            for (final File file : dir.listFiles()) {
                file.delete();
            }
        }
        else if (!dir.mkdirs()) {
            return null;
        }
        return this.iconsDir = dir.getPath();
    }
    
    public TaskItem getItem(final int index) {
        this.checkWidget();
        this.createItems();
        if (0 > index || index >= this.itemCount) {
            this.error(6);
        }
        return this.items[index];
    }
    
    public TaskItem getItem(final Shell shell) {
        this.checkWidget();
        for (final TaskItem item : this.items) {
            if (item != null && item.shell == shell) {
                return item;
            }
        }
        final TaskItem item2 = new TaskItem(this, 0);
        if (shell != null) {
            item2.setShell(shell);
        }
        return item2;
    }
    
    public int getItemCount() {
        this.checkWidget();
        this.createItems();
        return this.itemCount;
    }
    
    public TaskItem[] getItems() {
        this.checkWidget();
        this.createItems();
        final TaskItem[] result = new TaskItem[this.itemCount];
        System.arraycopy(this.items, 0, result, 0, result.length);
        return result;
    }
    
    @Override
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final TaskItem item : this.items) {
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(destroy);
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if (this.display.taskBar == this) {
            this.display.taskBar = null;
        }
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.mTaskbarList3.Release();
        this.mTaskbarList3 = null;
    }
    
    @Override
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (final TaskItem item : this.items) {
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    void setMenu(final Menu menu) {
        final long[] ppv = { 0L };
        int hr = COM.CoCreateInstance(COM.CLSID_DestinationList, 0L, 1, COM.IID_ICustomDestinationList, ppv);
        if (hr != 0) {
            this.error(2);
        }
        final ICustomDestinationList pDestList = new ICustomDestinationList(ppv[0]);
        final String appName = Display.APP_NAME;
        char[] buffer = { 'S', 'W', 'T', '\0' };
        if (appName != null && appName.length() > 0) {
            final int length = appName.length();
            buffer = new char[length + 1];
            appName.getChars(0, length, buffer, 0);
        }
        MenuItem[] items = null;
        if (menu != null && (items = menu.getItems()).length != 0) {
            final IObjectArray poa = this.createShellLinkArray(items);
            if (poa != null) {
                hr = pDestList.SetAppID(buffer);
                if (hr != 0) {
                    this.error(5);
                }
                final int[] cMaxSlots = { 0 };
                pDestList.BeginList(cMaxSlots, COM.IID_IObjectArray, ppv);
                if (hr != 0) {
                    this.error(5);
                }
                final IObjectArray pRemovedItems = new IObjectArray(ppv[0]);
                final int[] count = { 0 };
                poa.GetCount(count);
                if (count[0] != 0) {
                    hr = pDestList.AddUserTasks((IUnknown)poa);
                    if (hr != 0) {
                        this.error(5);
                    }
                }
                for (final MenuItem item : items) {
                    if ((item.getStyle() & 0x40) != 0x0) {
                        final Menu subMenu = item.getMenu();
                        if (subMenu != null) {
                            final MenuItem[] subItems = subMenu.getItems();
                            final IObjectArray poa2 = this.createShellLinkArray(subItems);
                            if (poa2 != null) {
                                poa2.GetCount(count);
                                if (count[0] != 0) {
                                    final String text = item.getText();
                                    final int length2 = text.length();
                                    final char[] buffer2 = new char[length2 + 1];
                                    text.getChars(0, length2, buffer2, 0);
                                    hr = pDestList.AppendCategory(buffer2, poa2);
                                    if (hr != 0) {
                                        this.error(5);
                                    }
                                }
                                poa2.Release();
                            }
                        }
                    }
                }
                poa.Release();
                hr = pDestList.CommitList();
                if (hr != 0) {
                    this.error(5);
                }
                pRemovedItems.Release();
            }
        }
        else {
            hr = pDestList.DeleteList(buffer);
            if (hr != 0) {
                this.error(5);
            }
        }
        pDestList.Release();
    }
    
    static {
        PKEY_Title = new PROPERTYKEY();
        PKEY_AppUserModel_IsDestListSeparator = new PROPERTYKEY();
        OS.PSPropertyKeyFromString("{F29F85E0-4FF9-1068-AB91-08002B27B3D9} 2\u0000".toCharArray(), TaskBar.PKEY_Title);
        OS.PSPropertyKeyFromString("{9F4C2855-9F79-4B39-A8D0-E1D42DE1D5F3}, 6\u0000".toCharArray(), TaskBar.PKEY_AppUserModel_IsDestListSeparator);
        char[] buffer;
        for (buffer = new char[260]; OS.GetModuleFileName(0L, buffer, buffer.length) == buffer.length; buffer = new char[buffer.length + 260]) {}
        EXE_PATH = buffer;
    }
}
