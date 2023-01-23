//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;
import javax.swing.border.*;
import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import java.awt.event.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;

public class DefaultWebBrowserDecorator extends WebBrowserDecorator
{
    private final ResourceBundle RESOURCES;
    private boolean isViewMenuVisible;
    private static final Border STATUS_BAR_BORDER;
    private WebBrowserMenuBar menuBar;
    private WebBrowserButtonBar buttonBar;
    private WebBrowserLocationBar locationBar;
    private WebBrowserStatusBar statusBar;
    private JWebBrowser webBrowser;
    private INativeWebBrowser nativeWebBrowser;
    private JPanel menuToolAndLocationBarPanel;
    private JPanel nativeWebBrowserBorderContainerPane;
    
    private void updateNavigationButtons() {
        if (!this.nativeWebBrowser.isNativePeerDisposed() && (this.isViewMenuVisible || this.isButtonBarVisible())) {
            final boolean isBackEnabled = this.nativeWebBrowser.isNativePeerInitialized() && this.nativeWebBrowser.isBackNavigationEnabled();
            if (this.buttonBar != null) {
                this.buttonBar.getBackButton().setEnabled(isBackEnabled);
            }
            this.menuBar.backMenuItem.setEnabled(isBackEnabled);
            final boolean isForwardEnabled = this.nativeWebBrowser.isNativePeerInitialized() && this.nativeWebBrowser.isForwardNavigationEnabled();
            if (this.buttonBar != null) {
                this.buttonBar.getForwardButton().setEnabled(isForwardEnabled);
            }
            this.menuBar.forwardMenuItem.setEnabled(isForwardEnabled);
        }
    }
    
    protected void addButtonBarComponents(final WebBrowserButtonBar buttonBar) {
        buttonBar.add(buttonBar.getBackButton());
        buttonBar.add(buttonBar.getForwardButton());
        buttonBar.add(buttonBar.getReloadButton());
        buttonBar.add(buttonBar.getStopButton());
    }
    
    protected void addLocationBarComponents(final WebBrowserLocationBar locationBar) {
        final JPanel locationToolBarInnerPanel = new JPanel(new GridBagLayout());
        locationToolBarInnerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
        locationToolBarInnerPanel.setOpaque(false);
        locationToolBarInnerPanel.add(locationBar.getLocationField(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
        locationBar.add(locationToolBarInnerPanel);
        locationBar.add(locationBar.getGoButton());
    }
    
    protected void addMenuBarComponents(final WebBrowserMenuBar menuBar) {
        menuBar.add(menuBar.getFileMenu());
        menuBar.add(menuBar.getViewMenu());
    }
    
    public DefaultWebBrowserDecorator(final JWebBrowser webBrowser, final Component renderingComponent) {
        final String className = JWebBrowser.class.getName();
        this.RESOURCES = ResourceBundle.getBundle(className.substring(0, className.lastIndexOf(46)).replace('.', '/') + "/resource/WebBrowser");
        this.webBrowser = webBrowser;
        this.nativeWebBrowser = (INativeWebBrowser)webBrowser.getNativeComponent();
        this.menuToolAndLocationBarPanel = new JPanel(new BorderLayout());
        this.menuBar = new WebBrowserMenuBar();
        this.menuToolAndLocationBarPanel.add(this.menuBar, "North");
        this.add(this.menuToolAndLocationBarPanel, "North");
        (this.nativeWebBrowserBorderContainerPane = new JPanel(new BorderLayout())).add(renderingComponent, "Center");
        this.add(this.nativeWebBrowserBorderContainerPane, "Center");
        this.nativeWebBrowser.addWebBrowserListener(new NWebBrowserListener(null));
        this.adjustBorder();
        this.setButtonBarVisible(true);
        this.setLocationBarVisible(true);
        this.setStatusBarVisible(true);
    }
    
    protected JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
    
    private void adjustBorder() {
        this.nativeWebBrowserBorderContainerPane.setBorder(this.getInnerAreaBorder());
    }
    
    protected Border getInnerAreaBorder() {
        Border border;
        if (this.isMenuBarVisible() || this.isButtonBarVisible() || this.isLocationBarVisible() || this.isStatusBarVisible()) {
            border = BorderFactory.createBevelBorder(1);
        }
        else {
            border = null;
        }
        return border;
    }
    
    @Override
    public void setStatusBarVisible(final boolean isStatusBarVisible) {
        if (isStatusBarVisible == this.isStatusBarVisible()) {
            return;
        }
        if (isStatusBarVisible) {
            this.statusBar = new WebBrowserStatusBar();
            this.webBrowser.add(this.statusBar, "South");
        }
        else {
            this.webBrowser.remove(this.statusBar);
            this.statusBar = null;
        }
        this.webBrowser.revalidate();
        this.webBrowser.repaint();
        this.menuBar.statusBarCheckBoxMenuItem.setSelected(isStatusBarVisible);
        this.adjustBorder();
    }
    
    @Override
    public boolean isStatusBarVisible() {
        return this.statusBar != null;
    }
    
    @Override
    public void setMenuBarVisible(final boolean isMenuBarVisible) {
        if (isMenuBarVisible == this.isMenuBarVisible()) {
            return;
        }
        this.menuBar.setVisible(isMenuBarVisible);
        this.adjustBorder();
    }
    
    @Override
    public boolean isMenuBarVisible() {
        return this.menuBar.isVisible();
    }
    
    @Override
    public void setButtonBarVisible(final boolean isButtonBarVisible) {
        if (isButtonBarVisible == this.isButtonBarVisible()) {
            return;
        }
        if (isButtonBarVisible) {
            this.buttonBar = new WebBrowserButtonBar();
            this.menuToolAndLocationBarPanel.add(this.buttonBar, "West");
        }
        else {
            this.menuToolAndLocationBarPanel.remove(this.buttonBar);
            this.buttonBar = null;
        }
        this.menuToolAndLocationBarPanel.revalidate();
        this.menuToolAndLocationBarPanel.repaint();
        this.menuBar.buttonBarCheckBoxMenuItem.setSelected(isButtonBarVisible);
        this.adjustBorder();
        if (isButtonBarVisible && !this.isViewMenuVisible) {
            this.updateNavigationButtons();
        }
    }
    
    @Override
    public boolean isButtonBarVisible() {
        return this.buttonBar != null;
    }
    
    @Override
    public void setLocationBarVisible(final boolean isLocationBarVisible) {
        if (isLocationBarVisible == this.isLocationBarVisible()) {
            return;
        }
        if (isLocationBarVisible) {
            this.locationBar = new WebBrowserLocationBar();
            this.menuToolAndLocationBarPanel.add(this.locationBar, "Center");
        }
        else {
            this.menuToolAndLocationBarPanel.remove(this.locationBar);
            this.locationBar = null;
        }
        this.menuToolAndLocationBarPanel.revalidate();
        this.menuToolAndLocationBarPanel.repaint();
        this.menuBar.locationBarCheckBoxMenuItem.setSelected(isLocationBarVisible);
        this.adjustBorder();
    }
    
    @Override
    public boolean isLocationBarVisible() {
        return this.locationBar != null;
    }
    
    @Override
    public void configureForWebBrowserWindow(final JWebBrowserWindow webBrowserWindow) {
        class llIIlll implements ActionListener
        {
            final /* synthetic */ JWebBrowserWindow val$webBrowserWindow;
            final /* synthetic */ DefaultWebBrowserDecorator this$0;
            
            llIIlll(final DefaultWebBrowserDecorator this$0, final JWebBrowserWindow val$webBrowserWindow) {
                this.this$0 = this$0;
                this.val$webBrowserWindow = val$webBrowserWindow;
            }
            
            @Override
            public void actionPerformed(final ActionEvent e) {
                this.val$webBrowserWindow.dispose();
            }
        }
        class lIIIllll extends WebBrowserAdapter
        {
            final /* synthetic */ JWebBrowserWindow val$webBrowserWindow;
            final /* synthetic */ DefaultWebBrowserDecorator this$0;
            
            lIIIllll(final DefaultWebBrowserDecorator this$0, final JWebBrowserWindow val$webBrowserWindow) {
                this.this$0 = this$0;
                this.val$webBrowserWindow = val$webBrowserWindow;
            }
            
            @Override
            public void titleChanged(final WebBrowserEvent e) {
                this.this$0.setWebBrowserWindowTitle(this.val$webBrowserWindow, e.getWebBrowser().getPageTitle());
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.menuBar:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;
        //     4: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.access$1700:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;)Ljavax/swing/JMenu;
        //     7: astore_2        /* fileMenu */
        //     8: aload_2         /* fileMenu */
        //     9: invokevirtual   javax/swing/JMenu.addSeparator:()V
        //    12: new             Ljavax/swing/JMenuItem;
        //    15: dup            
        //    16: invokespecial   javax/swing/JMenuItem.<init>:()V
        //    19: astore_3        /* fileCloseMenuItem */
        //    20: aload_0         /* this */
        //    21: aload_3         /* fileCloseMenuItem */
        //    22: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FILE_CLOSE_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
        //    25: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
        //    28: aload_3         /* fileCloseMenuItem */
        //    29: new             Lchrriis/dj/nativeswing/swtimpl/components/llIIlll;
        //    32: dup            
        //    33: aload_0         /* this */
        //    34: aload_1         /* webBrowserWindow */
        //    35: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowserWindow;)V
        //    38: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
        //    41: aload_2         /* fileMenu */
        //    42: aload_3         /* fileCloseMenuItem */
        //    43: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
        //    46: pop            
        //    47: aload_0         /* this */
        //    48: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    51: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIllll;
        //    54: dup            
        //    55: aload_0         /* this */
        //    56: aload_1         /* webBrowserWindow */
        //    57: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIllll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowserWindow;)V
        //    60: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.addWebBrowserListener:(Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserListener;)V
        //    63: aload_0         /* this */
        //    64: aload_1         /* webBrowserWindow */
        //    65: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.setWebBrowserWindowIcon:(Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowserWindow;)V
        //    68: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void setWebBrowserWindowTitle(final JWebBrowserWindow webBrowserWindow, final String pageTitle) {
        webBrowserWindow.setTitle(new MessageFormat(this.RESOURCES.getString("BrowserTitle")).format(new Object[] { pageTitle }));
    }
    
    protected void setWebBrowserWindowIcon(final JWebBrowserWindow webBrowserWindow) {
        final String value = this.RESOURCES.getString("BrowserIcon");
        if (value.length() > 0) {
            webBrowserWindow.setIconImage(new ImageIcon(JWebBrowserWindow.class.getResource(value)).getImage());
        }
    }
    
    protected String askLocation() {
        return JOptionPane.showInputDialog(this.webBrowser, this.RESOURCES.getString("FileOpenLocationDialogMessage"), this.RESOURCES.getString("FileOpenLocationDialogTitle"), 3);
    }
    
    protected void configureComponent(final JComponent c, final WebBrowserDecoratorComponentType componentType) {
        switch (lIIlIIlI.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType[componentType.ordinal()]) {
            case 1: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("FileMenu"));
            }
            case 2: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("FileNewWindowMenu"));
            }
            case 3: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("FileOpenLocationMenu"));
            }
            case 4: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("FileOpenFileMenu"));
            }
            case 5: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("FileCloseMenu"));
            }
            case 6: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewMenu"));
            }
            case 7: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewToolbarsMenu"));
            }
            case 8: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewToolbarsButtonBarMenu"));
            }
            case 9: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewToolbarsLocationBarMenu"));
            }
            case 10: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewStatusBarMenu"));
            }
            case 11: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewMenuBack"));
                ((AbstractButton)c).setIcon(this.createIcon("ViewMenuBackIcon"));
            }
            case 12: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewMenuForward"));
                ((AbstractButton)c).setIcon(this.createIcon("ViewMenuForwardIcon"));
            }
            case 13: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewMenuReload"));
                ((AbstractButton)c).setIcon(this.createIcon("ViewMenuReloadIcon"));
            }
            case 14: {
                ((AbstractButton)c).setText(this.RESOURCES.getString("ViewMenuStop"));
                ((AbstractButton)c).setIcon(this.createIcon("ViewMenuStopIcon"));
            }
            case 15: {
                ((AbstractButton)c).setIcon(this.createIcon("BackIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("BackText"));
            }
            case 16: {
                ((AbstractButton)c).setIcon(this.createIcon("ForwardIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("ForwardText"));
            }
            case 17: {
                ((AbstractButton)c).setIcon(this.createIcon("ReloadIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("ReloadText"));
            }
            case 18: {
                ((AbstractButton)c).setIcon(this.createIcon("StopIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("StopText"));
            }
            case 19: {
                ((AbstractButton)c).setIcon(this.createIcon("GoIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("GoText"));
            }
            case 20: {}
            default: {
                throw new IllegalStateException("Type not handled: " + componentType);
            }
        }
    }
    
    private Icon createIcon(final String resourceKey) {
        final String value = this.RESOURCES.getString(resourceKey);
        return (value.length() == 0) ? null : new ImageIcon(JWebBrowser.class.getResource(value));
    }
    
    static {
        STATUS_BAR_BORDER = new llIIlIl();
    }
    
    public enum WebBrowserDecoratorComponentType
    {
        FILE_MENU, 
        FILE_NEW_WINDOW_MENU_ITEM, 
        FILE_OPEN_LOCATION_MENU_ITEM, 
        FILE_OPEN_FILE_MENU_ITEM, 
        FILE_CLOSE_MENU_ITEM, 
        VIEW_MENU, 
        VIEW_TOOLBARS_MENU, 
        VIEW_TOOLBARS_BUTTON_BAR_CHECKBOX_MENU_ITEM, 
        VIEW_TOOLBARS_LOCATION_BAR_CHECKBOX_MENU_ITEM, 
        VIEW_STATUS_BAR_CHECKBOX_MENU_ITEM, 
        VIEW_BACK_MENU_ITEM, 
        VIEW_FORWARD_MENU_ITEM, 
        VIEW_RELOAD_MENU_ITEM, 
        VIEW_STOP_MENU_ITEM, 
        BACK_BUTTON, 
        FORWARD_BUTTON, 
        RELOAD_BUTTON, 
        STOP_BUTTON, 
        GO_BUTTON, 
        STATUS_LABEL;
    }
    
    private static class NWebBrowserListener extends WebBrowserAdapter
    {
        @Override
        public void locationChanged(final WebBrowserNavigationEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            this.updateStopButton(webBrowser, false);
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (e.isTopFrame() && decorator.locationBar != null) {
                decorator.locationBar.updateLocation();
            }
            decorator.updateNavigationButtons();
        }
        
        @Override
        public void locationChanging(final WebBrowserNavigationEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (e.isTopFrame() && decorator.locationBar != null) {
                decorator.locationBar.updateLocation(e.getNewResourceLocation());
            }
            this.updateStopButton(webBrowser, true);
        }
        
        @Override
        public void locationChangeCanceled(final WebBrowserNavigationEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            this.updateStopButton(webBrowser, false);
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (e.isTopFrame() && decorator.locationBar != null) {
                decorator.locationBar.updateLocation();
            }
            decorator.updateNavigationButtons();
        }
        
        @Override
        public void statusChanged(final WebBrowserEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (decorator.statusBar != null) {
                decorator.statusBar.updateStatus();
            }
        }
        
        @Override
        public void loadingProgressChanged(final WebBrowserEvent e) {
            final JWebBrowser webBrowser = e.getWebBrowser();
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (decorator.statusBar != null) {
                decorator.statusBar.updateProgressValue();
            }
            this.updateStopButton(webBrowser, false);
        }
        
        private void updateStopButton(final JWebBrowser webBrowser, final boolean isForcedOn) {
            final boolean isStopEnabled = isForcedOn || webBrowser.getLoadingProgress() != 100;
            final DefaultWebBrowserDecorator decorator = (DefaultWebBrowserDecorator)webBrowser.getWebBrowserDecorator();
            if (decorator.buttonBar != null) {
                decorator.buttonBar.getStopButton().setEnabled(isStopEnabled);
            }
            decorator.menuBar.stopMenuItem.setEnabled(isStopEnabled);
        }
    }
    
    public class WebBrowserMenuBar extends JMenuBar
    {
        private JMenu fileMenu;
        private JMenu viewMenu;
        private JCheckBoxMenuItem buttonBarCheckBoxMenuItem;
        private JCheckBoxMenuItem locationBarCheckBoxMenuItem;
        private JCheckBoxMenuItem statusBarCheckBoxMenuItem;
        private JMenuItem backMenuItem;
        private JMenuItem forwardMenuItem;
        private JMenuItem reloadMenuItem;
        private JMenuItem stopMenuItem;
        
        WebBrowserMenuBar(final DefaultWebBrowserDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;
            //     5: aload_0         /* this */
            //     6: invokespecial   javax/swing/JMenuBar.<init>:()V
            //     9: aload_0         /* this */
            //    10: new             Ljavax/swing/JMenu;
            //    13: dup            
            //    14: invokespecial   javax/swing/JMenu.<init>:()V
            //    17: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.fileMenu:Ljavax/swing/JMenu;
            //    20: aload_1         /* this$0 */
            //    21: aload_0         /* this */
            //    22: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.fileMenu:Ljavax/swing/JMenu;
            //    25: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FILE_MENU:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    28: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    31: new             Ljavax/swing/JMenuItem;
            //    34: dup            
            //    35: invokespecial   javax/swing/JMenuItem.<init>:()V
            //    38: astore_2        /* fileNewWindowMenuItem */
            //    39: aload_1         /* this$0 */
            //    40: aload_2         /* fileNewWindowMenuItem */
            //    41: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FILE_NEW_WINDOW_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    44: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    47: aload_2         /* fileNewWindowMenuItem */
            //    48: new             Lchrriis/dj/nativeswing/swtimpl/components/lllIlIl;
            //    51: dup            
            //    52: aload_0         /* this */
            //    53: aload_1         /* this$0 */
            //    54: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllIlIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    57: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    60: aload_0         /* this */
            //    61: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.fileMenu:Ljavax/swing/JMenu;
            //    64: aload_2         /* fileNewWindowMenuItem */
            //    65: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //    68: pop            
            //    69: new             Ljavax/swing/JMenuItem;
            //    72: dup            
            //    73: invokespecial   javax/swing/JMenuItem.<init>:()V
            //    76: astore_3        /* fileOpenLocationMenuItem */
            //    77: aload_1         /* this$0 */
            //    78: aload_3         /* fileOpenLocationMenuItem */
            //    79: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FILE_OPEN_LOCATION_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    82: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    85: aload_3         /* fileOpenLocationMenuItem */
            //    86: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIIlII;
            //    89: dup            
            //    90: aload_0         /* this */
            //    91: aload_1         /* this$0 */
            //    92: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIlII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    95: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    98: aload_0         /* this */
            //    99: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.fileMenu:Ljavax/swing/JMenu;
            //   102: aload_3         /* fileOpenLocationMenuItem */
            //   103: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   106: pop            
            //   107: new             Ljavax/swing/JMenuItem;
            //   110: dup            
            //   111: invokespecial   javax/swing/JMenuItem.<init>:()V
            //   114: astore          fileOpenFileMenuItem
            //   116: aload_1         /* this$0 */
            //   117: aload           fileOpenFileMenuItem
            //   119: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FILE_OPEN_FILE_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   122: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   125: aload           fileOpenFileMenuItem
            //   127: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIIIII;
            //   130: dup            
            //   131: aload_0         /* this */
            //   132: aload_1         /* this$0 */
            //   133: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIIIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   136: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   139: aload_0         /* this */
            //   140: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.fileMenu:Ljavax/swing/JMenu;
            //   143: aload           fileOpenFileMenuItem
            //   145: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   148: pop            
            //   149: aload_0         /* this */
            //   150: new             Ljavax/swing/JMenu;
            //   153: dup            
            //   154: invokespecial   javax/swing/JMenu.<init>:()V
            //   157: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   160: aload_1         /* this$0 */
            //   161: aload_0         /* this */
            //   162: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   165: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_MENU:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   168: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   171: new             Ljavax/swing/JMenu;
            //   174: dup            
            //   175: invokespecial   javax/swing/JMenu.<init>:()V
            //   178: astore          viewToolbarsMenu
            //   180: aload_1         /* this$0 */
            //   181: aload           viewToolbarsMenu
            //   183: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_TOOLBARS_MENU:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   186: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   189: aload_0         /* this */
            //   190: new             Ljavax/swing/JCheckBoxMenuItem;
            //   193: dup            
            //   194: invokespecial   javax/swing/JCheckBoxMenuItem.<init>:()V
            //   197: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.buttonBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   200: aload_1         /* this$0 */
            //   201: aload_0         /* this */
            //   202: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.buttonBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   205: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_TOOLBARS_BUTTON_BAR_CHECKBOX_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   208: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   211: aload_0         /* this */
            //   212: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.buttonBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   215: aload_1         /* this$0 */
            //   216: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.isButtonBarVisible:()Z
            //   219: invokevirtual   javax/swing/JCheckBoxMenuItem.setSelected:(Z)V
            //   222: aload_0         /* this */
            //   223: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.buttonBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   226: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIIlIl;
            //   229: dup            
            //   230: aload_0         /* this */
            //   231: aload_1         /* this$0 */
            //   232: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIlIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   235: invokevirtual   javax/swing/JCheckBoxMenuItem.addItemListener:(Ljava/awt/event/ItemListener;)V
            //   238: aload           viewToolbarsMenu
            //   240: aload_0         /* this */
            //   241: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.buttonBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   244: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   247: pop            
            //   248: aload_0         /* this */
            //   249: new             Ljavax/swing/JCheckBoxMenuItem;
            //   252: dup            
            //   253: invokespecial   javax/swing/JCheckBoxMenuItem.<init>:()V
            //   256: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.locationBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   259: aload_1         /* this$0 */
            //   260: aload_0         /* this */
            //   261: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.locationBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   264: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_TOOLBARS_LOCATION_BAR_CHECKBOX_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   267: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   270: aload_0         /* this */
            //   271: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.locationBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   274: aload_1         /* this$0 */
            //   275: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.isLocationBarVisible:()Z
            //   278: invokevirtual   javax/swing/JCheckBoxMenuItem.setSelected:(Z)V
            //   281: aload_0         /* this */
            //   282: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.locationBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   285: new             Lchrriis/dj/nativeswing/swtimpl/components/llllIll;
            //   288: dup            
            //   289: aload_0         /* this */
            //   290: aload_1         /* this$0 */
            //   291: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llllIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   294: invokevirtual   javax/swing/JCheckBoxMenuItem.addItemListener:(Ljava/awt/event/ItemListener;)V
            //   297: aload           viewToolbarsMenu
            //   299: aload_0         /* this */
            //   300: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.locationBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   303: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   306: pop            
            //   307: aload_0         /* this */
            //   308: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   311: aload           viewToolbarsMenu
            //   313: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   316: pop            
            //   317: aload_0         /* this */
            //   318: new             Ljavax/swing/JCheckBoxMenuItem;
            //   321: dup            
            //   322: invokespecial   javax/swing/JCheckBoxMenuItem.<init>:()V
            //   325: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.statusBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   328: aload_1         /* this$0 */
            //   329: aload_0         /* this */
            //   330: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.statusBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   333: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_STATUS_BAR_CHECKBOX_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   336: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   339: aload_0         /* this */
            //   340: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.statusBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   343: aload_1         /* this$0 */
            //   344: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.isStatusBarVisible:()Z
            //   347: invokevirtual   javax/swing/JCheckBoxMenuItem.setSelected:(Z)V
            //   350: aload_0         /* this */
            //   351: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.statusBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   354: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIIIlI;
            //   357: dup            
            //   358: aload_0         /* this */
            //   359: aload_1         /* this$0 */
            //   360: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   363: invokevirtual   javax/swing/JCheckBoxMenuItem.addItemListener:(Ljava/awt/event/ItemListener;)V
            //   366: aload_0         /* this */
            //   367: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   370: aload_0         /* this */
            //   371: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.statusBarCheckBoxMenuItem:Ljavax/swing/JCheckBoxMenuItem;
            //   374: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   377: pop            
            //   378: aload_0         /* this */
            //   379: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   382: invokevirtual   javax/swing/JMenu.addSeparator:()V
            //   385: aload_0         /* this */
            //   386: new             Ljavax/swing/JMenuItem;
            //   389: dup            
            //   390: invokespecial   javax/swing/JMenuItem.<init>:()V
            //   393: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.backMenuItem:Ljavax/swing/JMenuItem;
            //   396: aload_1         /* this$0 */
            //   397: aload_0         /* this */
            //   398: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.backMenuItem:Ljavax/swing/JMenuItem;
            //   401: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_BACK_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   404: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   407: aload_0         /* this */
            //   408: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.backMenuItem:Ljavax/swing/JMenuItem;
            //   411: new             Lchrriis/dj/nativeswing/swtimpl/components/llllIII;
            //   414: dup            
            //   415: aload_0         /* this */
            //   416: aload_1         /* this$0 */
            //   417: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llllIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   420: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   423: aload_0         /* this */
            //   424: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.backMenuItem:Ljavax/swing/JMenuItem;
            //   427: iconst_0       
            //   428: invokevirtual   javax/swing/JMenuItem.setEnabled:(Z)V
            //   431: aload_0         /* this */
            //   432: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   435: aload_0         /* this */
            //   436: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.backMenuItem:Ljavax/swing/JMenuItem;
            //   439: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   442: pop            
            //   443: aload_0         /* this */
            //   444: new             Ljavax/swing/JMenuItem;
            //   447: dup            
            //   448: invokespecial   javax/swing/JMenuItem.<init>:()V
            //   451: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.forwardMenuItem:Ljavax/swing/JMenuItem;
            //   454: aload_1         /* this$0 */
            //   455: aload_0         /* this */
            //   456: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.forwardMenuItem:Ljavax/swing/JMenuItem;
            //   459: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_FORWARD_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   462: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   465: aload_0         /* this */
            //   466: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.forwardMenuItem:Ljavax/swing/JMenuItem;
            //   469: new             Lchrriis/dj/nativeswing/swtimpl/components/llllllI;
            //   472: dup            
            //   473: aload_0         /* this */
            //   474: aload_1         /* this$0 */
            //   475: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llllllI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   478: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   481: aload_0         /* this */
            //   482: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.forwardMenuItem:Ljavax/swing/JMenuItem;
            //   485: iconst_0       
            //   486: invokevirtual   javax/swing/JMenuItem.setEnabled:(Z)V
            //   489: aload_0         /* this */
            //   490: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   493: aload_0         /* this */
            //   494: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.forwardMenuItem:Ljavax/swing/JMenuItem;
            //   497: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   500: pop            
            //   501: aload_0         /* this */
            //   502: new             Ljavax/swing/JMenuItem;
            //   505: dup            
            //   506: invokespecial   javax/swing/JMenuItem.<init>:()V
            //   509: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.reloadMenuItem:Ljavax/swing/JMenuItem;
            //   512: aload_1         /* this$0 */
            //   513: aload_0         /* this */
            //   514: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.reloadMenuItem:Ljavax/swing/JMenuItem;
            //   517: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_RELOAD_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   520: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   523: aload_0         /* this */
            //   524: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.reloadMenuItem:Ljavax/swing/JMenuItem;
            //   527: new             Lchrriis/dj/nativeswing/swtimpl/components/lllllII;
            //   530: dup            
            //   531: aload_0         /* this */
            //   532: aload_1         /* this$0 */
            //   533: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllllII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   536: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   539: aload_0         /* this */
            //   540: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   543: aload_0         /* this */
            //   544: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.reloadMenuItem:Ljavax/swing/JMenuItem;
            //   547: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   550: pop            
            //   551: aload_0         /* this */
            //   552: new             Ljavax/swing/JMenuItem;
            //   555: dup            
            //   556: invokespecial   javax/swing/JMenuItem.<init>:()V
            //   559: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.stopMenuItem:Ljavax/swing/JMenuItem;
            //   562: aload_1         /* this$0 */
            //   563: aload_0         /* this */
            //   564: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.stopMenuItem:Ljavax/swing/JMenuItem;
            //   567: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.VIEW_STOP_MENU_ITEM:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   570: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   573: aload_0         /* this */
            //   574: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.stopMenuItem:Ljavax/swing/JMenuItem;
            //   577: new             Lchrriis/dj/nativeswing/swtimpl/components/llIlIlI;
            //   580: dup            
            //   581: aload_0         /* this */
            //   582: aload_1         /* this$0 */
            //   583: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIlIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   586: invokevirtual   javax/swing/JMenuItem.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   589: aload_0         /* this */
            //   590: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.stopMenuItem:Ljavax/swing/JMenuItem;
            //   593: iconst_0       
            //   594: invokevirtual   javax/swing/JMenuItem.setEnabled:(Z)V
            //   597: aload_0         /* this */
            //   598: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   601: aload_0         /* this */
            //   602: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.stopMenuItem:Ljavax/swing/JMenuItem;
            //   605: invokevirtual   javax/swing/JMenu.add:(Ljavax/swing/JMenuItem;)Ljavax/swing/JMenuItem;
            //   608: pop            
            //   609: aload_0         /* this */
            //   610: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.viewMenu:Ljavax/swing/JMenu;
            //   613: invokevirtual   javax/swing/JMenu.getPopupMenu:()Ljavax/swing/JPopupMenu;
            //   616: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIIlII;
            //   619: dup            
            //   620: aload_0         /* this */
            //   621: aload_1         /* this$0 */
            //   622: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIIlII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   625: invokevirtual   javax/swing/JPopupMenu.addPopupMenuListener:(Ljavax/swing/event/PopupMenuListener;)V
            //   628: aload_1         /* this$0 */
            //   629: aload_0         /* this */
            //   630: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.addMenuBarComponents:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;)V
            //   633: return         
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
            //     at java.base/java.lang.Thread.run(Unknown Source)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public JMenu getFileMenu() {
            return this.fileMenu;
        }
        
        public JMenu getViewMenu() {
            return this.viewMenu;
        }
    }
    
    public class WebBrowserButtonBar extends JToolBar
    {
        private JButton backButton;
        private JButton forwardButton;
        private JButton reloadButton;
        private JButton stopButton;
        
        WebBrowserButtonBar(final DefaultWebBrowserDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;
            //     5: aload_0         /* this */
            //     6: invokespecial   javax/swing/JToolBar.<init>:()V
            //     9: aload_0         /* this */
            //    10: iconst_0       
            //    11: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.setFloatable:(Z)V
            //    14: aload_0         /* this */
            //    15: new             Ljavax/swing/JButton;
            //    18: dup            
            //    19: invokespecial   javax/swing/JButton.<init>:()V
            //    22: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.backButton:Ljavax/swing/JButton;
            //    25: aload_1         /* this$0 */
            //    26: aload_0         /* this */
            //    27: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.backButton:Ljavax/swing/JButton;
            //    30: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.BACK_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    33: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    36: aload_0         /* this */
            //    37: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.backButton:Ljavax/swing/JButton;
            //    40: aload_1         /* this$0 */
            //    41: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.access$400:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;
            //    44: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.access$600:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;)Ljavax/swing/JMenuItem;
            //    47: invokevirtual   javax/swing/JMenuItem.isEnabled:()Z
            //    50: invokevirtual   javax/swing/JButton.setEnabled:(Z)V
            //    53: aload_0         /* this */
            //    54: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.backButton:Ljavax/swing/JButton;
            //    57: new             Lchrriis/dj/nativeswing/swtimpl/components/llllIlI;
            //    60: dup            
            //    61: aload_0         /* this */
            //    62: aload_1         /* this$0 */
            //    63: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llllIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    66: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    69: aload_0         /* this */
            //    70: new             Ljavax/swing/JButton;
            //    73: dup            
            //    74: invokespecial   javax/swing/JButton.<init>:()V
            //    77: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.forwardButton:Ljavax/swing/JButton;
            //    80: aload_1         /* this$0 */
            //    81: aload_0         /* this */
            //    82: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.forwardButton:Ljavax/swing/JButton;
            //    85: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.FORWARD_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    88: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    91: aload_0         /* this */
            //    92: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.forwardButton:Ljavax/swing/JButton;
            //    95: aload_1         /* this$0 */
            //    96: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.access$400:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;
            //    99: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.access$700:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;)Ljavax/swing/JMenuItem;
            //   102: invokevirtual   javax/swing/JMenuItem.isEnabled:()Z
            //   105: invokevirtual   javax/swing/JButton.setEnabled:(Z)V
            //   108: aload_0         /* this */
            //   109: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.forwardButton:Ljavax/swing/JButton;
            //   112: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIIIII;
            //   115: dup            
            //   116: aload_0         /* this */
            //   117: aload_1         /* this$0 */
            //   118: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   121: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   124: aload_0         /* this */
            //   125: new             Ljavax/swing/JButton;
            //   128: dup            
            //   129: invokespecial   javax/swing/JButton.<init>:()V
            //   132: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.reloadButton:Ljavax/swing/JButton;
            //   135: aload_1         /* this$0 */
            //   136: aload_0         /* this */
            //   137: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.reloadButton:Ljavax/swing/JButton;
            //   140: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.RELOAD_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   143: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   146: aload_0         /* this */
            //   147: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.reloadButton:Ljavax/swing/JButton;
            //   150: new             Lchrriis/dj/nativeswing/swtimpl/components/llllIIl;
            //   153: dup            
            //   154: aload_0         /* this */
            //   155: aload_1         /* this$0 */
            //   156: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llllIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   159: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   162: aload_0         /* this */
            //   163: new             Ljavax/swing/JButton;
            //   166: dup            
            //   167: invokespecial   javax/swing/JButton.<init>:()V
            //   170: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.stopButton:Ljavax/swing/JButton;
            //   173: aload_1         /* this$0 */
            //   174: aload_0         /* this */
            //   175: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.stopButton:Ljavax/swing/JButton;
            //   178: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.STOP_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //   181: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //   184: aload_0         /* this */
            //   185: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.stopButton:Ljavax/swing/JButton;
            //   188: aload_1         /* this$0 */
            //   189: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.access$400:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;
            //   192: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar.access$500:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserMenuBar;)Ljavax/swing/JMenuItem;
            //   195: invokevirtual   javax/swing/JMenuItem.isEnabled:()Z
            //   198: invokevirtual   javax/swing/JButton.setEnabled:(Z)V
            //   201: aload_0         /* this */
            //   202: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.stopButton:Ljavax/swing/JButton;
            //   205: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIIIlI;
            //   208: dup            
            //   209: aload_0         /* this */
            //   210: aload_1         /* this$0 */
            //   211: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIIIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //   214: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   217: aload_1         /* this$0 */
            //   218: aload_0         /* this */
            //   219: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.addButtonBarComponents:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar;)V
            //   222: aload_0         /* this */
            //   223: iconst_2       
            //   224: invokestatic    javax/swing/Box.createHorizontalStrut:(I)Ljava/awt/Component;
            //   227: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserButtonBar.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   230: pop            
            //   231: return         
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
            //     at java.base/java.lang.Thread.run(Unknown Source)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public JButton getBackButton() {
            return this.backButton;
        }
        
        public JButton getForwardButton() {
            return this.forwardButton;
        }
        
        public JButton getReloadButton() {
            return this.reloadButton;
        }
        
        public JButton getStopButton() {
            return this.stopButton;
        }
    }
    
    public class WebBrowserLocationBar extends JToolBar
    {
        private JTextField locationField;
        private JButton goButton;
        final /* synthetic */ DefaultWebBrowserDecorator this$0;
        
        WebBrowserLocationBar(final DefaultWebBrowserDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;
            //     5: aload_0         /* this */
            //     6: invokespecial   javax/swing/JToolBar.<init>:()V
            //     9: aload_0         /* this */
            //    10: new             Ljavax/swing/BoxLayout;
            //    13: dup            
            //    14: aload_0         /* this */
            //    15: iconst_2       
            //    16: invokespecial   javax/swing/BoxLayout.<init>:(Ljava/awt/Container;I)V
            //    19: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.setLayout:(Ljava/awt/LayoutManager;)V
            //    22: aload_0         /* this */
            //    23: iconst_0       
            //    24: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.setFloatable:(Z)V
            //    27: aload_0         /* this */
            //    28: new             Ljavax/swing/JTextField;
            //    31: dup            
            //    32: invokespecial   javax/swing/JTextField.<init>:()V
            //    35: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.locationField:Ljavax/swing/JTextField;
            //    38: aload_0         /* this */
            //    39: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.locationField:Ljavax/swing/JTextField;
            //    42: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIllIlI;
            //    45: dup            
            //    46: aload_0         /* this */
            //    47: aload_1         /* this$0 */
            //    48: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIllIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    51: invokevirtual   javax/swing/JTextField.addKeyListener:(Ljava/awt/event/KeyListener;)V
            //    54: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIlIII;
            //    57: dup            
            //    58: aload_0         /* this */
            //    59: aload_1         /* this$0 */
            //    60: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIlIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    63: astore_2        /* goActionListener */
            //    64: aload_0         /* this */
            //    65: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.locationField:Ljavax/swing/JTextField;
            //    68: aload_2         /* goActionListener */
            //    69: invokevirtual   javax/swing/JTextField.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    72: aload_0         /* this */
            //    73: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.updateLocation:()V
            //    76: aload_0         /* this */
            //    77: new             Ljavax/swing/JButton;
            //    80: dup            
            //    81: invokespecial   javax/swing/JButton.<init>:()V
            //    84: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.goButton:Ljavax/swing/JButton;
            //    87: aload_1         /* this$0 */
            //    88: aload_0         /* this */
            //    89: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.goButton:Ljavax/swing/JButton;
            //    92: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.GO_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    95: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    98: aload_0         /* this */
            //    99: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar.goButton:Ljavax/swing/JButton;
            //   102: aload_2         /* goActionListener */
            //   103: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   106: aload_1         /* this$0 */
            //   107: aload_0         /* this */
            //   108: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.addLocationBarComponents:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserLocationBar;)V
            //   111: return         
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
            //     at java.base/java.lang.Thread.run(Unknown Source)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public JTextField getLocationField() {
            return this.locationField;
        }
        
        public JButton getGoButton() {
            return this.goButton;
        }
        
        void updateLocation(final String location) {
            this.locationField.setText(location);
        }
        
        void updateLocation() {
            this.locationField.setText((this.this$0.nativeWebBrowser.isNativePeerInitialized() && !this.this$0.nativeWebBrowser.isNativePeerDisposed()) ? this.this$0.nativeWebBrowser.getResourceLocation() : "");
        }
    }
    
    private class WebBrowserStatusBar extends JPanel
    {
        private JLabel statusLabel;
        private JProgressBar progressBar;
        final /* synthetic */ DefaultWebBrowserDecorator this$0;
        
        public WebBrowserStatusBar(final DefaultWebBrowserDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: aload_1        
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;
            //     5: aload_0         /* this */
            //     6: new             Ljava/awt/BorderLayout;
            //     9: dup            
            //    10: invokespecial   java/awt/BorderLayout.<init>:()V
            //    13: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //    16: aload_0         /* this */
            //    17: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.access$1200:()Ljavax/swing/border/Border;
            //    20: iconst_2       
            //    21: iconst_2       
            //    22: iconst_2       
            //    23: iconst_2       
            //    24: invokestatic    javax/swing/BorderFactory.createEmptyBorder:(IIII)Ljavax/swing/border/Border;
            //    27: invokestatic    javax/swing/BorderFactory.createCompoundBorder:(Ljavax/swing/border/Border;Ljavax/swing/border/Border;)Ljavax/swing/border/CompoundBorder;
            //    30: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.setBorder:(Ljavax/swing/border/Border;)V
            //    33: aload_0         /* this */
            //    34: new             Ljavax/swing/JLabel;
            //    37: dup            
            //    38: invokespecial   javax/swing/JLabel.<init>:()V
            //    41: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.statusLabel:Ljavax/swing/JLabel;
            //    44: aload_1        
            //    45: aload_0         /* this */
            //    46: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.statusLabel:Ljavax/swing/JLabel;
            //    49: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType.STATUS_LABEL:Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;
            //    52: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserDecoratorComponentType;)V
            //    55: aload_0         /* this */
            //    56: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.updateStatus:()V
            //    59: aload_0         /* this */
            //    60: aload_0         /* this */
            //    61: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.statusLabel:Ljavax/swing/JLabel;
            //    64: ldc             "Center"
            //    66: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //    69: aload_0         /* this */
            //    70: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIIllI;
            //    73: dup            
            //    74: aload_0         /* this */
            //    75: aload_1        
            //    76: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIIllI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator;)V
            //    79: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.progressBar:Ljavax/swing/JProgressBar;
            //    82: aload_0         /* this */
            //    83: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.updateProgressValue:()V
            //    86: aload_0         /* this */
            //    87: aload_0         /* this */
            //    88: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.progressBar:Ljavax/swing/JProgressBar;
            //    91: ldc             "East"
            //    93: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultWebBrowserDecorator$WebBrowserStatusBar.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //    96: return         
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
            //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
            //     at java.base/java.lang.Thread.run(Unknown Source)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void updateProgressValue() {
            final int loadingProgress = this.this$0.nativeWebBrowser.isNativePeerInitialized() ? this.this$0.nativeWebBrowser.getLoadingProgress() : 100;
            this.progressBar.setValue(loadingProgress);
            this.progressBar.setVisible(loadingProgress < 100);
        }
        
        public void updateStatus() {
            final String status = this.this$0.nativeWebBrowser.isNativePeerInitialized() ? this.this$0.nativeWebBrowser.getStatusText() : "";
            this.statusLabel.setText((status.length() == 0) ? " " : status);
        }
    }
}
