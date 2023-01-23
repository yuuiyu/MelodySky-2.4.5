//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.accessibility.*;
import java.util.function.*;
import org.eclipse.swt.widgets.*;

public class CCombo extends Composite
{
    Text text;
    List list;
    int visibleItemCount;
    Shell popup;
    Button arrow;
    boolean hasFocus;
    Listener listener;
    Listener filter;
    Color foreground;
    Color background;
    Font font;
    Shell _shell;
    static final String PACKAGE_PREFIX = "org.eclipse.swt.custom.";
    
    public CCombo(final Composite parent, int style) {
        super(parent, style = checkStyle(style));
        this.visibleItemCount = 5;
        this._shell = super.getShell();
        this.listener = (event -> {
            if (this.isDisposed()) {
                return;
            }
            else if (this.popup == event.widget) {
                this.popupEvent(event);
                return;
            }
            else if (this.text == event.widget) {
                this.textEvent(event);
                return;
            }
            else if (this.list == event.widget) {
                this.listEvent(event);
                return;
            }
            else if (this.arrow == event.widget) {
                this.arrowEvent(event);
                return;
            }
            else if (this == event.widget) {
                this.comboEvent(event);
                return;
            }
            else {
                if (this.getShell() == event.widget) {
                    this.getDisplay().asyncExec(() -> {
                        if (!this.isDisposed()) {
                            this.handleFocus(16);
                        }
                    });
                }
                return;
            }
        });
        this.createText(style);
        int arrowStyle = 1028;
        if ((style & 0x800000) != 0x0) {
            arrowStyle |= 0x800000;
        }
        this.arrow = new Button(this, arrowStyle);
        Shell shell;
        this.filter = (event -> {
            if (this.isDisposed()) {
                return;
            }
            else if (event.type == 13) {
                if (event.widget instanceof ScrollBar) {
                    this.handleScroll(event);
                }
                return;
            }
            else {
                if (event.widget instanceof Control) {
                    shell = ((Control)event.widget).getShell();
                    if (shell == this.getShell()) {
                        this.handleFocus(16);
                    }
                }
                return;
            }
        });
        final int[] comboEvents;
        final int[] array3;
        final int[] array = array3 = (comboEvents = new int[] { 12, 15, 10, 11, 16 });
        for (final int comboEvent : array3) {
            this.addListener(comboEvent, this.listener);
        }
        final int[] arrowEvents;
        final int[] array4;
        final int[] array2 = array4 = (arrowEvents = new int[] { 29, 3, 6, 7, 32, 5, 4, 37, 13, 15 });
        for (final int arrowEvent : array4) {
            this.arrow.addListener(arrowEvent, this.listener);
        }
        this.createPopup(null, -1);
        if ((style & 0x40) == 0x0) {
            final int itemHeight = this.list.getItemHeight();
            if (itemHeight != 0) {
                final int maxHeight = this.getMonitor().getClientArea().height / 3;
                this.visibleItemCount = Math.max(this.visibleItemCount, maxHeight / itemHeight);
            }
        }
        this.initAccessible();
    }
    
    static int checkStyle(final int style) {
        final int mask = 125978632;
        return 0x80000 | (style & 0x7824808);
    }
    
    void createText(final int comboStyle) {
        String textValue = null;
        String tooltip = null;
        Point selection = null;
        int limit = 0;
        boolean enabled = false;
        boolean focus = false;
        boolean editable = false;
        Font font = null;
        Color fg = null;
        Color bg = null;
        Menu menu = null;
        if (this.text != null) {
            textValue = this.text.getText();
            tooltip = this.text.getToolTipText();
            selection = this.text.getSelection();
            limit = this.text.getTextLimit();
            enabled = this.text.isEnabled();
            editable = this.text.getEditable();
            focus = this.text.isFocusControl();
            font = this.text.getFont();
            fg = this.text.getForeground();
            bg = this.text.getBackground();
            menu = this.text.getMenu();
            this.text.dispose();
        }
        int textStyle = 4;
        if ((comboStyle & 0x8) != 0x0) {
            textStyle |= 0x8;
        }
        if ((comboStyle & 0x800000) != 0x0) {
            textStyle |= 0x800000;
        }
        textStyle |= (comboStyle & 0x1024000);
        this.text = new Text(this, textStyle);
        if (textValue != null) {
            this.text.setText(textValue);
            this.text.setToolTipText(tooltip);
            if (selection != null) {
                this.text.setSelection(selection);
            }
            this.text.setTextLimit(limit);
            this.text.setEnabled(enabled);
            this.text.setEditable(editable);
            if (focus) {
                this.text.setFocus();
            }
            if (font != null && !font.isDisposed()) {
                this.text.setFont(font);
            }
            if (fg != null && !fg.isDisposed()) {
                this.text.setForeground(fg);
            }
            if (bg != null && !bg.isDisposed()) {
                this.text.setBackground(bg);
            }
            if (menu != null && !menu.isDisposed()) {
                this.text.setMenu(menu);
            }
            this.internalLayout(true);
        }
        final int[] textEvents;
        final int[] array2;
        final int[] array = array2 = (textEvents = new int[] { 14, 29, 1, 2, 35, 24, 3, 4, 8, 6, 7, 32, 5, 37, 31, 15, 25 });
        for (final int textEvent : array2) {
            this.text.addListener(textEvent, this.listener);
        }
    }
    
    public void add(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        this.list.add(string);
    }
    
    public void add(final String string, final int index) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        this.list.add(string, index);
    }
    
    public void addModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(listener);
        this.addListener(24, typedListener);
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(listener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    public void addVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(listener);
        this.addListener(25, typedListener);
    }
    
    void arrowEvent(final Event event) {
        switch (event.type) {
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 29:
            case 32: {
                final Point pt = this.getDisplay().map(this.arrow, this, event.x, event.y);
                event.x = pt.x;
                event.y = pt.y;
                this.notifyListeners(event.type, event);
                event.type = 0;
                break;
            }
            case 37: {
                final Point pt = this.getDisplay().map(this.arrow, this, event.x, event.y);
                event.x = pt.x;
                event.y = pt.y;
                this.notifyListeners(37, event);
                event.type = 0;
                if (this.isDisposed()) {
                    break;
                }
                if (!event.doit) {
                    break;
                }
                if (event.count == 0) {
                    break;
                }
                event.doit = false;
                final int oldIndex = this.getSelectionIndex();
                if (event.count > 0) {
                    this.select(Math.max(oldIndex - 1, 0));
                }
                else {
                    this.select(Math.min(oldIndex + 1, this.getItemCount() - 1));
                }
                if (oldIndex != this.getSelectionIndex()) {
                    final Event e = new Event();
                    e.time = event.time;
                    e.stateMask = event.stateMask;
                    this.notifyListeners(13, e);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 13: {
                this.text.setFocus();
                this.dropDown(!this.isDropped());
                break;
            }
        }
    }
    
    @Override
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final int index = name.lastIndexOf(46);
        if (!name.substring(0, index + 1).equals("org.eclipse.swt.custom.")) {
            SWT.error(43);
        }
    }
    
    public void clearSelection() {
        this.checkWidget();
        this.text.clearSelection();
        this.list.deselectAll();
    }
    
    void comboEvent(final Event event) {
        switch (event.type) {
            case 12: {
                this.removeListener(12, this.listener);
                this.notifyListeners(12, event);
                event.type = 0;
                if (this.popup != null && !this.popup.isDisposed()) {
                    this.list.removeListener(12, this.listener);
                    this.popup.dispose();
                }
                final Shell shell = this.getShell();
                shell.removeListener(27, this.listener);
                final Display display = this.getDisplay();
                display.removeFilter(15, this.filter);
                this.popup = null;
                this.text = null;
                this.list = null;
                this.arrow = null;
                this._shell = null;
                break;
            }
            case 15: {
                final Control focusControl = this.getDisplay().getFocusControl();
                if (focusControl == this.arrow || focusControl == this.list) {
                    return;
                }
                if (this.isDropped()) {
                    this.list.setFocus();
                    break;
                }
                this.text.setFocus();
                break;
            }
            case 16: {
                this.text.clearSelection();
                break;
            }
            case 10: {
                this.dropDown(false);
                break;
            }
            case 11: {
                this.internalLayout(false);
                break;
            }
        }
    }
    
    @Override
    public Point computeSize(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        final GC gc = new GC(this.text);
        final int spacer = gc.stringExtent(" ").x;
        int textWidth = gc.stringExtent(this.text.getText()).x;
        for (final String item : this.list.getItems()) {
            textWidth = Math.max(gc.stringExtent(item).x, textWidth);
        }
        gc.dispose();
        final Point textSize = this.text.computeSize(-1, -1, changed);
        final Point arrowSize = this.arrow.computeSize(-1, -1, changed);
        final Point listSize = this.list.computeSize(-1, -1, changed);
        final int borderWidth = this.getBorderWidth();
        height = Math.max(textSize.y, arrowSize.y);
        width = Math.max(textWidth + 2 * spacer + arrowSize.x + 2 * borderWidth, listSize.x);
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        return new Point(width + 2 * borderWidth, height + 2 * borderWidth);
    }
    
    public void copy() {
        this.checkWidget();
        this.text.copy();
    }
    
    void createPopup(final String[] items, final int selectionIndex) {
        this.popup = new Shell(this.getShell(), 16392);
        final int style = this.getStyle();
        int listStyle = 772;
        if ((style & 0x800000) != 0x0) {
            listStyle |= 0x800000;
        }
        if ((style & 0x4000000) != 0x0) {
            listStyle |= 0x4000000;
        }
        if ((style & 0x2000000) != 0x0) {
            listStyle |= 0x2000000;
        }
        this.list = new List(this.popup, listStyle);
        if (this.font != null) {
            this.list.setFont(this.font);
        }
        if (this.foreground != null) {
            this.list.setForeground(this.foreground);
        }
        if (this.background != null) {
            this.list.setBackground(this.background);
        }
        final int[] popupEvents;
        final int[] array3;
        final int[] array = array3 = (popupEvents = new int[] { 21, 9 });
        for (final int popupEvent : array3) {
            this.popup.addListener(popupEvent, this.listener);
        }
        final int[] listEvents;
        final int[] array4;
        final int[] array2 = array4 = (listEvents = new int[] { 4, 13, 31, 1, 2, 15, 16, 12 });
        for (final int listEvent : array4) {
            this.list.addListener(listEvent, this.listener);
        }
        if (items != null) {
            this.list.setItems(items);
        }
        if (selectionIndex != -1) {
            this.list.setSelection(selectionIndex);
        }
    }
    
    public void cut() {
        this.checkWidget();
        this.text.cut();
    }
    
    public void deselect(final int index) {
        this.checkWidget();
        if (0 <= index && index < this.list.getItemCount() && index == this.list.getSelectionIndex() && this.text.getText().equals(this.list.getItem(index))) {
            this.text.setText("");
            this.list.deselect(index);
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        this.text.setText("");
        this.list.deselectAll();
    }
    
    void dropDown(final boolean drop) {
        if (drop == this.isDropped()) {
            return;
        }
        final Display display = this.getDisplay();
        if (!drop) {
            display.removeFilter(13, this.filter);
            this.popup.setVisible(false);
            if (!this.isDisposed() && this.isFocusControl()) {
                this.text.setFocus();
            }
            return;
        }
        if (!this.isVisible()) {
            return;
        }
        if (this.getShell() != this.popup.getParent()) {
            final String[] items = this.list.getItems();
            final int selectionIndex = this.list.getSelectionIndex();
            this.list.removeListener(12, this.listener);
            this.popup.dispose();
            this.popup = null;
            this.list = null;
            this.createPopup(items, selectionIndex);
        }
        final Point comboSize = this.getSize();
        int itemCount = this.list.getItemCount();
        itemCount = ((itemCount == 0) ? this.visibleItemCount : Math.min(this.visibleItemCount, itemCount));
        final int itemHeight = this.list.getItemHeight() * itemCount;
        final Point listSize = this.list.computeSize(-1, itemHeight, false);
        final Rectangle displayRect = this.getMonitor().getClientArea();
        this.list.setBounds(1, 1, Math.max(comboSize.x - 2, Math.min(listSize.x, displayRect.width - 2)), listSize.y);
        final int index = this.list.getSelectionIndex();
        if (index != -1) {
            this.list.setTopIndex(index);
        }
        final Rectangle listRect = this.list.getBounds();
        final Rectangle parentRect = display.map(this.getParent(), null, this.getBounds());
        final int width = listRect.width + 2;
        int height = listRect.height + 2;
        int x = parentRect.x;
        if (x + width > displayRect.x + displayRect.width) {
            x = displayRect.x + displayRect.width - width;
        }
        int y = parentRect.y + comboSize.y;
        if (y + height > displayRect.y + displayRect.height) {
            final int popUpwardsHeight = (parentRect.y - height < displayRect.y) ? (parentRect.y - displayRect.y) : height;
            final int popDownwardsHeight = displayRect.y + displayRect.height - y;
            if (popUpwardsHeight > popDownwardsHeight) {
                height = popUpwardsHeight;
                y = parentRect.y - popUpwardsHeight;
            }
            else {
                height = popDownwardsHeight;
            }
            listRect.height = height - 2;
        }
        final ScrollBar hBar = this.list.getHorizontalBar();
        final int emptyHBarSpace = hBar.isVisible() ? 0 : hBar.getSize().y;
        this.list.setSize(listRect.width, listRect.height - emptyHBarSpace);
        this.popup.setBounds(x, y, width, height - emptyHBarSpace);
        this.popup.setVisible(true);
        if (this.isFocusControl()) {
            this.list.setFocus();
        }
        display.removeFilter(13, this.filter);
        display.addFilter(13, this.filter);
    }
    
    char _findMnemonic(final String string) {
        if (string == null) {
            return '\0';
        }
        int index = 0;
        final int length = string.length();
        while (true) {
            if (index < length && string.charAt(index) != '&') {
                ++index;
            }
            else {
                if (++index >= length) {
                    return '\0';
                }
                if (string.charAt(index) != '&') {
                    return Character.toLowerCase(string.charAt(index));
                }
                if (++index >= length) {
                    return '\0';
                }
                continue;
            }
        }
    }
    
    String getAssociatedLabel() {
        final Control[] siblings = this.getParent().getChildren();
        int i = 0;
        while (i < siblings.length) {
            if (siblings[i] == this) {
                if (i <= 0) {
                    break;
                }
                final Control sibling = siblings[i - 1];
                if (sibling instanceof Label) {
                    return ((Label)sibling).getText();
                }
                if (sibling instanceof CLabel) {
                    return ((CLabel)sibling).getText();
                }
                break;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public int getAlignment() {
        return this.text.getStyle() & 0x1024000;
    }
    
    @Override
    public Control[] getChildren() {
        this.checkWidget();
        return new Control[0];
    }
    
    public boolean getEditable() {
        this.checkWidget();
        return this.text.getEditable();
    }
    
    public String getItem(final int index) {
        this.checkWidget();
        return this.list.getItem(index);
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.list.getItemCount();
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return this.list.getItemHeight();
    }
    
    public String[] getItems() {
        this.checkWidget();
        return this.list.getItems();
    }
    
    public boolean getListVisible() {
        this.checkWidget();
        return this.isDropped();
    }
    
    @Override
    public Menu getMenu() {
        return this.text.getMenu();
    }
    
    public Point getSelection() {
        this.checkWidget();
        return this.text.getSelection();
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        return this.list.getSelectionIndex();
    }
    
    @Override
    public Shell getShell() {
        this.checkWidget();
        final Shell shell = super.getShell();
        if (shell != this._shell) {
            if (this._shell != null && !this._shell.isDisposed()) {
                this._shell.removeListener(27, this.listener);
            }
            this._shell = shell;
        }
        return this._shell;
    }
    
    @Override
    public int getStyle() {
        int style = super.getStyle();
        style &= 0xFFFFFFF7;
        if (!this.text.getEditable()) {
            style |= 0x8;
        }
        style &= 0xFEFDBFFF;
        style |= this.getAlignment();
        return style;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text.getText();
    }
    
    public int getTextHeight() {
        this.checkWidget();
        return this.text.getLineHeight();
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return this.text.getTextLimit();
    }
    
    public int getVisibleItemCount() {
        this.checkWidget();
        return this.visibleItemCount;
    }
    
    void handleFocus(final int type) {
        switch (type) {
            case 15: {
                if (this.hasFocus) {
                    return;
                }
                if (this.getEditable()) {
                    this.text.selectAll();
                }
                this.hasFocus = true;
                final Shell shell = this.getShell();
                shell.removeListener(27, this.listener);
                shell.addListener(27, this.listener);
                final Display display = this.getDisplay();
                display.removeFilter(15, this.filter);
                display.addFilter(15, this.filter);
                final Event e = new Event();
                this.notifyListeners(15, e);
                break;
            }
            case 16: {
                if (!this.hasFocus) {
                    return;
                }
                final Control focusControl = this.getDisplay().getFocusControl();
                if (focusControl == this.arrow || focusControl == this.list || focusControl == this.text) {
                    return;
                }
                this.hasFocus = false;
                final Shell shell2 = this.getShell();
                shell2.removeListener(27, this.listener);
                final Display display2 = this.getDisplay();
                display2.removeFilter(15, this.filter);
                final Event e2 = new Event();
                this.notifyListeners(16, e2);
                break;
            }
        }
    }
    
    void handleScroll(final Event event) {
        final ScrollBar scrollBar = (ScrollBar)event.widget;
        final Control scrollableParent = scrollBar.getParent();
        if (scrollableParent.equals(this.list)) {
            return;
        }
        if (this.isParentScrolling(scrollableParent)) {
            this.dropDown(false);
        }
    }
    
    public int indexOf(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        return this.list.indexOf(string);
    }
    
    public int indexOf(final String string, final int start) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        return this.list.indexOf(string, start);
    }
    
    void initAccessible() {
        class lIIIlll extends AccessibleAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            lIIIlll(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                String name = null;
                final String text = this.this$0.getAssociatedLabel();
                if (text != null) {
                    name = this.this$0.stripMnemonic(text);
                }
                e.result = name;
            }
            
            public void getKeyboardShortcut(final AccessibleEvent e) {
                String shortcut = null;
                final String text = this.this$0.getAssociatedLabel();
                if (text != null) {
                    final char mnemonic = this.this$0._findMnemonic(text);
                    if (mnemonic != '\0') {
                        shortcut = "Alt+" + mnemonic;
                    }
                }
                e.result = shortcut;
            }
            
            public void getHelp(final AccessibleEvent e) {
                e.result = this.this$0.getToolTipText();
            }
        }
        class llIIlI extends AccessibleAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            llIIlI(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getName(final AccessibleEvent e) {
                e.result = (this.this$0.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
            }
            
            public void getKeyboardShortcut(final AccessibleEvent e) {
                e.result = "Alt+Down Arrow";
            }
            
            public void getHelp(final AccessibleEvent e) {
                e.result = this.this$0.getToolTipText();
            }
        }
        class lIIlIll extends AccessibleTextAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            lIIlIll(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getCaretOffset(final AccessibleTextEvent e) {
                e.offset = this.this$0.text.getCaretPosition();
            }
            
            public void getSelectionRange(final AccessibleTextEvent e) {
                final Point sel = this.this$0.text.getSelection();
                e.offset = sel.x;
                e.length = sel.y - sel.x;
            }
        }
        class lllIlI extends AccessibleControlAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            lllIlI(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getChildAtPoint(final AccessibleControlEvent e) {
                final Point testPoint = this.this$0.toControl(e.x, e.y);
                if (this.this$0.getBounds().contains(testPoint)) {
                    e.childID = -1;
                }
            }
            
            public void getLocation(final AccessibleControlEvent e) {
                final Rectangle location = this.this$0.getBounds();
                final Point pt = this.this$0.getParent().toDisplay(location.x, location.y);
                e.x = pt.x;
                e.y = pt.y;
                e.width = location.width;
                e.height = location.height;
            }
            
            public void getChildCount(final AccessibleControlEvent e) {
                e.detail = 0;
            }
            
            public void getRole(final AccessibleControlEvent e) {
                e.detail = 46;
            }
            
            public void getState(final AccessibleControlEvent e) {
                e.detail = 0;
            }
            
            public void getValue(final AccessibleControlEvent e) {
                e.result = this.this$0.getText();
            }
        }
        class lIIlIIl extends AccessibleControlAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            lIIlIIl(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getRole(final AccessibleControlEvent e) {
                e.detail = (this.this$0.text.getEditable() ? 42 : 41);
            }
        }
        class llIlII extends AccessibleControlAdapter
        {
            final /* synthetic */ CCombo this$0;
            
            llIlII(final CCombo this$0) {
                this.this$0 = this$0;
            }
            
            public void getDefaultAction(final AccessibleControlEvent e) {
                e.result = (this.this$0.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: aload_0         /* this */
        //     5: invokespecial   org/eclipse/swt/custom/lIIIlll.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //     8: astore_1        /* accessibleAdapter */
        //     9: aload_0         /* this */
        //    10: invokevirtual   org/eclipse/swt/custom/CCombo.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    13: aload_1         /* accessibleAdapter */
        //    14: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    17: aload_0         /* this */
        //    18: getfield        org/eclipse/swt/custom/CCombo.text:Lorg/eclipse/swt/widgets/Text;
        //    21: invokevirtual   org/eclipse/swt/widgets/Text.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    24: aload_1         /* accessibleAdapter */
        //    25: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    28: aload_0         /* this */
        //    29: getfield        org/eclipse/swt/custom/CCombo.list:Lorg/eclipse/swt/widgets/List;
        //    32: invokevirtual   org/eclipse/swt/widgets/List.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    35: aload_1         /* accessibleAdapter */
        //    36: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    39: aload_0         /* this */
        //    40: getfield        org/eclipse/swt/custom/CCombo.arrow:Lorg/eclipse/swt/widgets/Button;
        //    43: invokevirtual   org/eclipse/swt/widgets/Button.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    46: new             Lorg/eclipse/swt/custom/llIIlI;
        //    49: dup            
        //    50: aload_0         /* this */
        //    51: invokespecial   org/eclipse/swt/custom/llIIlI.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //    54: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //    57: aload_0         /* this */
        //    58: invokevirtual   org/eclipse/swt/custom/CCombo.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    61: new             Lorg/eclipse/swt/custom/lIIlIll;
        //    64: dup            
        //    65: aload_0         /* this */
        //    66: invokespecial   org/eclipse/swt/custom/lIIlIll.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //    69: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleTextListener:(Lorg/eclipse/swt/accessibility/AccessibleTextListener;)V
        //    72: aload_0         /* this */
        //    73: invokevirtual   org/eclipse/swt/custom/CCombo.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    76: new             Lorg/eclipse/swt/custom/lllIlI;
        //    79: dup            
        //    80: aload_0         /* this */
        //    81: invokespecial   org/eclipse/swt/custom/lllIlI.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //    84: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleControlListener:(Lorg/eclipse/swt/accessibility/AccessibleControlListener;)V
        //    87: aload_0         /* this */
        //    88: getfield        org/eclipse/swt/custom/CCombo.text:Lorg/eclipse/swt/widgets/Text;
        //    91: invokevirtual   org/eclipse/swt/widgets/Text.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //    94: new             Lorg/eclipse/swt/custom/lIIlIIl;
        //    97: dup            
        //    98: aload_0         /* this */
        //    99: invokespecial   org/eclipse/swt/custom/lIIlIIl.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //   102: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleControlListener:(Lorg/eclipse/swt/accessibility/AccessibleControlListener;)V
        //   105: aload_0         /* this */
        //   106: getfield        org/eclipse/swt/custom/CCombo.arrow:Lorg/eclipse/swt/widgets/Button;
        //   109: invokevirtual   org/eclipse/swt/widgets/Button.getAccessible:()Lorg/eclipse/swt/accessibility/Accessible;
        //   112: new             Lorg/eclipse/swt/custom/llIlII;
        //   115: dup            
        //   116: aload_0         /* this */
        //   117: invokespecial   org/eclipse/swt/custom/llIlII.<init>:(Lorg/eclipse/swt/custom/CCombo;)V
        //   120: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleControlListener:(Lorg/eclipse/swt/accessibility/AccessibleControlListener;)V
        //   123: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    boolean isDropped() {
        return !this.isDisposed() && this.popup.getVisible();
    }
    
    @Override
    public boolean isFocusControl() {
        this.checkWidget();
        final Predicate<Control> checkFocusControl = control -> control != null && !control.isDisposed() && control.isFocusControl();
        return checkFocusControl.test(this.text) || checkFocusControl.test(this.arrow) || checkFocusControl.test(this.list) || checkFocusControl.test(this.popup) || super.isFocusControl();
    }
    
    boolean isParentScrolling(final Control scrollableParent) {
        for (Control parent = this.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.equals(scrollableParent)) {
                return true;
            }
        }
        return false;
    }
    
    void internalLayout(final boolean changed) {
        if (this.isDropped()) {
            this.dropDown(false);
        }
        final Rectangle rect = this.getClientArea();
        final int width = rect.width;
        final int height = rect.height;
        final Point arrowSize = this.arrow.computeSize(-1, height, changed);
        this.text.setBounds(0, 0, width - arrowSize.x, height);
        this.arrow.setBounds(width - arrowSize.x, 0, arrowSize.x, arrowSize.y);
    }
    
    void listEvent(final Event event) {
        switch (event.type) {
            case 12: {
                if (this.getShell() != this.popup.getParent()) {
                    final String[] items = this.list.getItems();
                    final int selectionIndex = this.list.getSelectionIndex();
                    this.popup = null;
                    this.list = null;
                    this.createPopup(items, selectionIndex);
                    break;
                }
                break;
            }
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 16: {
                final Point point = this.arrow.toControl(this.getDisplay().getCursorLocation());
                final Point size = this.arrow.getSize();
                final Rectangle rect = new Rectangle(0, 0, size.x, size.y);
                if (!rect.contains(point)) {
                    this.dropDown(false);
                    break;
                }
                final boolean comboShellActivated = this.getDisplay().getActiveShell() == this.getShell();
                if (!comboShellActivated) {
                    this.dropDown(false);
                    break;
                }
                break;
            }
            case 4: {
                if (event.button != 1) {
                    return;
                }
                this.dropDown(false);
                break;
            }
            case 13: {
                final int index = this.list.getSelectionIndex();
                if (index == -1) {
                    return;
                }
                this.text.setText(this.list.getItem(index));
                if (this.text.getEditable() && this.text.isFocusControl()) {
                    this.text.selectAll();
                }
                this.list.setSelection(index);
                final Event e = new Event();
                e.time = event.time;
                e.stateMask = event.stateMask;
                e.doit = event.doit;
                this.notifyListeners(13, e);
                event.doit = e.doit;
                break;
            }
            case 31: {
                switch (event.detail) {
                    case 2:
                    case 4:
                    case 32:
                    case 64: {
                        event.doit = false;
                        break;
                    }
                    case 8:
                    case 16: {
                        event.doit = this.text.traverse(event.detail);
                        event.detail = 0;
                        if (event.doit) {
                            this.dropDown(false);
                        }
                        return;
                    }
                }
                final Event e2 = new Event();
                e2.time = event.time;
                e2.detail = event.detail;
                e2.doit = event.doit;
                e2.character = event.character;
                e2.keyCode = event.keyCode;
                e2.keyLocation = event.keyLocation;
                this.notifyListeners(31, e2);
                event.doit = e2.doit;
                event.detail = e2.detail;
                break;
            }
            case 2: {
                final Event e2 = new Event();
                e2.time = event.time;
                e2.character = event.character;
                e2.keyCode = event.keyCode;
                e2.keyLocation = event.keyLocation;
                e2.stateMask = event.stateMask;
                this.notifyListeners(2, e2);
                event.doit = e2.doit;
                break;
            }
            case 1: {
                if (event.character == '\u001b') {
                    this.dropDown(false);
                }
                if ((event.stateMask & 0x10000) != 0x0 && (event.keyCode == 16777217 || event.keyCode == 16777218)) {
                    this.dropDown(false);
                }
                if (event.character == '\r') {
                    this.dropDown(false);
                    final Event e2 = new Event();
                    e2.time = event.time;
                    e2.stateMask = event.stateMask;
                    this.notifyListeners(14, e2);
                }
                if (this.isDisposed()) {
                    break;
                }
                final Event e2 = new Event();
                e2.time = event.time;
                e2.character = event.character;
                e2.keyCode = event.keyCode;
                e2.keyLocation = event.keyLocation;
                e2.stateMask = event.stateMask;
                this.notifyListeners(1, e2);
                event.doit = e2.doit;
                break;
            }
        }
    }
    
    public void paste() {
        this.checkWidget();
        this.text.paste();
    }
    
    void popupEvent(final Event event) {
        switch (event.type) {
            case 9: {
                final Rectangle listRect = this.list.getBounds();
                final Color black = this.getDisplay().getSystemColor(2);
                event.gc.setForeground(black);
                event.gc.drawRectangle(0, 0, listRect.width + 1, listRect.height + 1);
                break;
            }
            case 21: {
                this.dropDown(event.doit = false);
                break;
            }
        }
    }
    
    @Override
    public void redraw() {
        super.redraw();
        this.text.redraw();
        this.arrow.redraw();
        if (this.popup.isVisible()) {
            this.list.redraw();
        }
    }
    
    @Override
    public void redraw(final int x, final int y, final int width, final int height, final boolean all) {
        super.redraw(x, y, width, height, true);
    }
    
    public void remove(final int index) {
        this.checkWidget();
        this.list.remove(index);
    }
    
    public void remove(final int start, final int end) {
        this.checkWidget();
        this.list.remove(start, end);
    }
    
    public void remove(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        this.list.remove(string);
    }
    
    public void removeAll() {
        this.checkWidget();
        this.text.setText("");
        this.list.removeAll();
    }
    
    public void removeModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(24, listener);
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(13, listener);
        this.removeListener(14, listener);
    }
    
    public void removeVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.removeListener(25, listener);
    }
    
    public void select(final int index) {
        this.checkWidget();
        if (index == -1) {
            this.list.deselectAll();
            this.text.setText("");
            return;
        }
        if (0 <= index && index < this.list.getItemCount() && index != this.getSelectionIndex()) {
            this.text.setText(this.list.getItem(index));
            if (this.text.getEditable() && this.text.isFocusControl()) {
                this.text.selectAll();
            }
            this.list.select(index);
            this.list.showSelection();
        }
    }
    
    public void setAlignment(final int align) {
        this.checkWidget();
        final int styleWithoutAlign = this.getStyle() & 0xFEFDBFFF;
        this.createText(styleWithoutAlign | align);
    }
    
    @Override
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.background = color;
        if (this.text != null) {
            this.text.setBackground(color);
        }
        if (this.list != null) {
            this.list.setBackground(color);
        }
        if (this.arrow != null) {
            this.arrow.setBackground(color);
        }
    }
    
    public void setEditable(final boolean editable) {
        this.checkWidget();
        this.text.setEditable(editable);
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (this.popup != null) {
            this.popup.setVisible(false);
        }
        if (this.text != null) {
            this.text.setEnabled(enabled);
        }
        if (this.arrow != null) {
            this.arrow.setEnabled(enabled);
        }
    }
    
    @Override
    public boolean setFocus() {
        this.checkWidget();
        return this.isEnabled() && this.getVisible() && (this.isFocusControl() || this.text.setFocus());
    }
    
    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        this.font = font;
        this.text.setFont(font);
        this.list.setFont(font);
        this.internalLayout(true);
    }
    
    @Override
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.foreground = color;
        if (this.text != null) {
            this.text.setForeground(color);
        }
        if (this.list != null) {
            this.list.setForeground(color);
        }
        if (this.arrow != null) {
            this.arrow.setForeground(color);
        }
    }
    
    public void setItem(final int index, final String string) {
        this.checkWidget();
        this.list.setItem(index, string);
    }
    
    public void setItems(final String[] items) {
        this.checkWidget();
        this.list.setItems(items);
        if (!this.text.getEditable()) {
            this.text.setText("");
        }
    }
    
    @Override
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setListVisible(final boolean visible) {
        this.checkWidget();
        this.dropDown(visible);
    }
    
    @Override
    public void setMenu(final Menu menu) {
        this.text.setMenu(menu);
    }
    
    public void setSelection(final Point selection) {
        this.checkWidget();
        if (selection == null) {
            SWT.error(4);
        }
        this.text.setSelection(selection.x, selection.y);
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        final int index = this.list.indexOf(string);
        if (index == -1) {
            this.list.deselectAll();
            this.text.setText(string);
            return;
        }
        this.text.setText(string);
        if (this.text.getEditable() && this.text.isFocusControl()) {
            this.text.selectAll();
        }
        this.list.setSelection(index);
        this.list.showSelection();
    }
    
    public void setTextLimit(final int limit) {
        this.checkWidget();
        this.text.setTextLimit(limit);
    }
    
    @Override
    public void setToolTipText(final String string) {
        this.checkWidget();
        super.setToolTipText(string);
        this.arrow.setToolTipText(string);
        this.text.setToolTipText(string);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (this.isDisposed()) {
            return;
        }
        if (this.popup == null || this.popup.isDisposed()) {
            return;
        }
        if (!visible) {
            this.popup.setVisible(false);
        }
    }
    
    public void setVisibleItemCount(final int count) {
        this.checkWidget();
        if (count < 0) {
            return;
        }
        this.visibleItemCount = count;
    }
    
    String stripMnemonic(final String string) {
        int index = 0;
        final int length = string.length();
        while (true) {
            if (index < length && string.charAt(index) != '&') {
                ++index;
            }
            else {
                if (++index >= length) {
                    return string;
                }
                if (string.charAt(index) != '&') {
                    return string.substring(0, index - 1) + string.substring(index, length);
                }
                if (++index >= length) {
                    return string;
                }
                continue;
            }
        }
    }
    
    void textEvent(final Event event) {
        switch (event.type) {
            case 15: {
                this.handleFocus(15);
                break;
            }
            case 14: {
                this.dropDown(false);
                final Event e = new Event();
                e.time = event.time;
                e.stateMask = event.stateMask;
                this.notifyListeners(14, e);
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 29:
            case 32: {
                final Point pt = this.getDisplay().map(this.text, this, event.x, event.y);
                event.x = pt.x;
                event.y = pt.y;
                this.notifyListeners(event.type, event);
                event.type = 0;
                break;
            }
            case 1: {
                final Event keyEvent = new Event();
                keyEvent.time = event.time;
                keyEvent.character = event.character;
                keyEvent.keyCode = event.keyCode;
                keyEvent.keyLocation = event.keyLocation;
                keyEvent.stateMask = event.stateMask;
                this.notifyListeners(1, keyEvent);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = keyEvent.doit)) {
                    break;
                }
                if (event.keyCode != 16777217 && event.keyCode != 16777218) {
                    break;
                }
                event.doit = false;
                if ((event.stateMask & 0x10000) != 0x0) {
                    final boolean dropped = this.isDropped();
                    if (this.text.getEditable() && this.text.isFocusControl()) {
                        this.text.selectAll();
                    }
                    if (!dropped) {
                        this.setFocus();
                    }
                    this.dropDown(!dropped);
                    break;
                }
                final int oldIndex = this.getSelectionIndex();
                if (event.keyCode == 16777217) {
                    this.select(Math.max(oldIndex - 1, 0));
                }
                else {
                    this.select(Math.min(oldIndex + 1, this.getItemCount() - 1));
                }
                if (oldIndex != this.getSelectionIndex()) {
                    final Event e2 = new Event();
                    e2.time = event.time;
                    e2.stateMask = event.stateMask;
                    this.notifyListeners(13, e2);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 2: {
                final Event e = new Event();
                e.time = event.time;
                e.character = event.character;
                e.keyCode = event.keyCode;
                e.keyLocation = event.keyLocation;
                e.stateMask = event.stateMask;
                this.notifyListeners(2, e);
                event.doit = e.doit;
                break;
            }
            case 35: {
                final Event e = new Event();
                e.time = event.time;
                e.detail = event.detail;
                e.x = event.x;
                e.y = event.y;
                if (event.detail == 1) {
                    final Point pt2 = this.getDisplay().map(this.text, null, this.text.getCaretLocation());
                    e.x = pt2.x;
                    e.y = pt2.y;
                }
                this.notifyListeners(35, e);
                event.doit = e.doit;
                event.x = e.x;
                event.y = e.y;
                break;
            }
            case 24: {
                this.list.deselectAll();
                final Event e = new Event();
                e.time = event.time;
                this.notifyListeners(24, e);
                break;
            }
            case 3: {
                final Point pt = this.getDisplay().map(this.text, this, event.x, event.y);
                final Event mouseEvent = new Event();
                mouseEvent.button = event.button;
                mouseEvent.count = event.count;
                mouseEvent.stateMask = event.stateMask;
                mouseEvent.time = event.time;
                mouseEvent.x = pt.x;
                mouseEvent.y = pt.y;
                this.notifyListeners(3, mouseEvent);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = mouseEvent.doit)) {
                    break;
                }
                if (event.button != 1) {
                    return;
                }
                if (this.text.getEditable()) {
                    return;
                }
                final boolean dropped2 = this.isDropped();
                if (this.text.getEditable() && this.text.isFocusControl()) {
                    this.text.selectAll();
                }
                if (!dropped2) {
                    this.setFocus();
                }
                this.dropDown(!dropped2);
                break;
            }
            case 4: {
                final Point pt = this.getDisplay().map(this.text, this, event.x, event.y);
                final Event mouseEvent = new Event();
                mouseEvent.button = event.button;
                mouseEvent.count = event.count;
                mouseEvent.stateMask = event.stateMask;
                mouseEvent.time = event.time;
                mouseEvent.x = pt.x;
                mouseEvent.y = pt.y;
                this.notifyListeners(4, mouseEvent);
                if (this.isDisposed()) {
                    break;
                }
                if (!(event.doit = mouseEvent.doit)) {
                    break;
                }
                if (event.button != 1) {
                    return;
                }
                if (this.text.getEditable()) {
                    return;
                }
                if (this.text.getEditable() && this.text.isFocusControl()) {
                    this.text.selectAll();
                    break;
                }
                break;
            }
            case 37: {
                this.notifyListeners(37, event);
                event.type = 0;
                if (this.isDisposed()) {
                    break;
                }
                if (!event.doit) {
                    break;
                }
                if (event.count == 0) {
                    break;
                }
                event.doit = false;
                final int oldIndex2 = this.getSelectionIndex();
                if (event.count > 0) {
                    this.select(Math.max(oldIndex2 - 1, 0));
                }
                else {
                    this.select(Math.min(oldIndex2 + 1, this.getItemCount() - 1));
                }
                if (oldIndex2 != this.getSelectionIndex()) {
                    final Event e3 = new Event();
                    e3.time = event.time;
                    e3.stateMask = event.stateMask;
                    this.notifyListeners(13, e3);
                }
                if (this.isDisposed()) {
                    break;
                }
                break;
            }
            case 31: {
                switch (event.detail) {
                    case 32:
                    case 64: {
                        event.doit = false;
                        break;
                    }
                    case 8: {
                        event.doit = this.traverse(8);
                        event.detail = 0;
                        return;
                    }
                }
                final Event e = new Event();
                e.time = event.time;
                e.detail = event.detail;
                e.doit = event.doit;
                e.character = event.character;
                e.keyCode = event.keyCode;
                e.keyLocation = event.keyLocation;
                this.notifyListeners(31, e);
                event.doit = e.doit;
                event.detail = e.detail;
                break;
            }
            case 25: {
                final Event e = new Event();
                e.text = event.text;
                e.start = event.start;
                e.end = event.end;
                e.character = event.character;
                e.keyCode = event.keyCode;
                e.keyLocation = event.keyLocation;
                e.stateMask = event.stateMask;
                this.notifyListeners(25, e);
                event.text = e.text;
                event.doit = e.doit;
                break;
            }
        }
    }
    
    @Override
    public boolean traverse(final int event) {
        if (event == 64 || event == 16) {
            return this.text.traverse(event);
        }
        return super.traverse(event);
    }
}
