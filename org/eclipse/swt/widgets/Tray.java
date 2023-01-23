//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

public class Tray extends Widget
{
    int itemCount;
    TrayItem[] items;
    
    Tray(final Display display, final int style) {
        this.items = new TrayItem[4];
        this.display = display;
        this.reskinWidget();
    }
    
    void createItem(final TrayItem item, final int index) {
        if (0 > index || index > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final TrayItem[] newItems = new TrayItem[this.items.length + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        System.arraycopy(this.items, index, this.items, index + 1, this.itemCount++ - index);
        this.items[index] = item;
    }
    
    void destroyItem(final TrayItem item) {
        int index;
        for (index = 0; index < this.itemCount && this.items[index] != item; ++index) {}
        if (index == this.itemCount) {
            return;
        }
        System.arraycopy(this.items, index + 1, this.items, index, --this.itemCount - index);
        this.items[this.itemCount] = null;
    }
    
    public TrayItem getItem(final int index) {
        this.checkWidget();
        if (0 > index || index >= this.itemCount) {
            this.error(6);
        }
        return this.items[index];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.itemCount;
    }
    
    public TrayItem[] getItems() {
        this.checkWidget();
        final TrayItem[] result = new TrayItem[this.itemCount];
        System.arraycopy(this.items, 0, result, 0, result.length);
        return result;
    }
    
    @Override
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final TrayItem item : this.items) {
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
        if (this.display.tray == this) {
            this.display.tray = null;
        }
    }
    
    @Override
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (final TrayItem item : this.items) {
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
}
