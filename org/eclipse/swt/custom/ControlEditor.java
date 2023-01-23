//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class ControlEditor
{
    public int horizontalAlignment;
    public boolean grabHorizontal;
    public int minimumWidth;
    public int verticalAlignment;
    public boolean grabVertical;
    public int minimumHeight;
    Composite parent;
    Control editor;
    private boolean hadFocus;
    private Listener controlListener;
    private Listener scrollbarListener;
    private static final int[] EVENTS;
    
    public ControlEditor(final Composite parent) {
        this.horizontalAlignment = 16777216;
        this.grabHorizontal = false;
        this.minimumWidth = 0;
        this.verticalAlignment = 16777216;
        this.grabVertical = false;
        this.minimumHeight = 0;
        this.parent = parent;
        this.controlListener = (e -> this.layout());
        for (final int event : ControlEditor.EVENTS) {
            parent.addListener(event, this.controlListener);
        }
        this.scrollbarListener = this::scroll;
        final ScrollBar hBar = parent.getHorizontalBar();
        if (hBar != null) {
            hBar.addListener(13, this.scrollbarListener);
        }
        final ScrollBar vBar = parent.getVerticalBar();
        if (vBar != null) {
            vBar.addListener(13, this.scrollbarListener);
        }
    }
    
    Rectangle computeBounds() {
        final Rectangle clientArea = this.parent.getClientArea();
        final Rectangle editorRect = new Rectangle(clientArea.x, clientArea.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
            editorRect.width = Math.max(clientArea.width, this.minimumWidth);
        }
        if (this.grabVertical) {
            editorRect.height = Math.max(clientArea.height, this.minimumHeight);
        }
        switch (this.horizontalAlignment) {
            case 131072: {
                final Rectangle rectangle5;
                final Rectangle rectangle = rectangle5 = editorRect;
                rectangle5.x += clientArea.width - editorRect.width;
                break;
            }
            case 16384: {
                break;
            }
            default: {
                final Rectangle rectangle6;
                final Rectangle rectangle2 = rectangle6 = editorRect;
                rectangle6.x += (clientArea.width - editorRect.width) / 2;
                break;
            }
        }
        switch (this.verticalAlignment) {
            case 1024: {
                final Rectangle rectangle7;
                final Rectangle rectangle3 = rectangle7 = editorRect;
                rectangle7.y += clientArea.height - editorRect.height;
                break;
            }
            case 128: {
                break;
            }
            default: {
                final Rectangle rectangle8;
                final Rectangle rectangle4 = rectangle8 = editorRect;
                rectangle8.y += (clientArea.height - editorRect.height) / 2;
                break;
            }
        }
        return editorRect;
    }
    
    public void dispose() {
        if (this.parent != null && !this.parent.isDisposed()) {
            for (final int event : ControlEditor.EVENTS) {
                this.parent.removeListener(event, this.controlListener);
            }
            final ScrollBar hBar = this.parent.getHorizontalBar();
            if (hBar != null) {
                hBar.removeListener(13, this.scrollbarListener);
            }
            final ScrollBar vBar = this.parent.getVerticalBar();
            if (vBar != null) {
                vBar.removeListener(13, this.scrollbarListener);
            }
        }
        this.parent = null;
        this.editor = null;
        this.hadFocus = false;
        this.controlListener = null;
        this.scrollbarListener = null;
    }
    
    public Control getEditor() {
        return this.editor;
    }
    
    public void layout() {
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        if (this.editor.getVisible()) {
            this.hadFocus = this.editor.isFocusControl();
        }
        this.editor.setBounds(this.computeBounds());
        if (this.hadFocus) {
            if (this.editor == null || this.editor.isDisposed()) {
                return;
            }
            this.editor.setFocus();
        }
    }
    
    void scroll(final Event e) {
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        this.layout();
    }
    
    public void setEditor(final Control editor) {
        if (editor == null) {
            this.editor = null;
            return;
        }
        this.editor = editor;
        this.layout();
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        editor.setVisible(true);
    }
    
    static {
        EVENTS = new int[] { 1, 2, 3, 4, 11 };
    }
}
