//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class PopupList
{
    Shell shell;
    List list;
    int minimumWidth;
    
    public PopupList(final Shell parent) {
        this(parent, 0);
    }
    
    public PopupList(final Shell parent, final int style) {
        int listStyle = 516;
        if ((style & 0x100) != 0x0) {
            listStyle |= 0x100;
        }
        this.shell = new Shell(parent, checkStyle(style));
        this.list = new List(this.shell, listStyle);
        this.shell.addListener(27, e -> this.shell.setVisible(false));
        final Rectangle shellSize;
        this.shell.addControlListener(ControlListener.controlResizedAdapter(e -> {
            shellSize = this.shell.getClientArea();
            this.list.setSize(shellSize.width, shellSize.height);
            return;
        }));
        this.list.addMouseListener((MouseListener)new llIIll(this));
        this.list.addKeyListener((KeyListener)new lIIIlIl(this));
    }
    
    private static int checkStyle(final int style) {
        final int mask = 100663296;
        return style & 0x6000000;
    }
    
    public Font getFont() {
        return this.list.getFont();
    }
    
    public String[] getItems() {
        return this.list.getItems();
    }
    
    public int getMinimumWidth() {
        return this.minimumWidth;
    }
    
    public String open(final Rectangle rect) {
        final Point listSize = this.list.computeSize(rect.width, -1, false);
        final Rectangle screenSize = this.shell.getDisplay().getBounds();
        final int spaceBelow = screenSize.height - (rect.y + rect.height) - 30;
        final int spaceAbove = rect.y - 30;
        int y = 0;
        if (spaceAbove > spaceBelow && listSize.y > spaceBelow) {
            if (listSize.y > spaceAbove) {
                listSize.y = spaceAbove;
            }
            else {
                final Point point3;
                final Point point = point3 = listSize;
                point3.y += 2;
            }
            y = rect.y - listSize.y;
        }
        else {
            if (listSize.y > spaceBelow) {
                listSize.y = spaceBelow;
            }
            else {
                final Point point4;
                final Point point2 = point4 = listSize;
                point4.y += 2;
            }
            y = rect.y + rect.height;
        }
        listSize.x = rect.width;
        if (listSize.x < this.minimumWidth) {
            listSize.x = this.minimumWidth;
        }
        final int x = rect.x + rect.width - listSize.x;
        this.shell.setBounds(x, y, listSize.x, listSize.y);
        this.shell.open();
        this.list.setFocus();
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed() && this.shell.isVisible()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        String result = null;
        if (!this.shell.isDisposed()) {
            final String[] strings = this.list.getSelection();
            this.shell.dispose();
            if (strings.length != 0) {
                result = strings[0];
            }
        }
        return result;
    }
    
    public void select(final String string) {
        final String[] items = this.list.getItems();
        if (string != null) {
            for (final String item : items) {
                if (item.startsWith(string)) {
                    final int index = this.list.indexOf(item);
                    this.list.select(index);
                    break;
                }
            }
        }
    }
    
    public void setFont(final Font font) {
        this.list.setFont(font);
    }
    
    public void setItems(final String[] strings) {
        this.list.setItems(strings);
    }
    
    public void setMinimumWidth(final int width) {
        if (width < 0) {
            SWT.error(5);
        }
        this.minimumWidth = width;
    }
}
