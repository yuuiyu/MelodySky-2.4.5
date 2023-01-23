//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;

public class DefaultFlashPlayerDecorator extends FlashPlayerDecorator
{
    private final ResourceBundle RESOURCES;
    private JFlashPlayer flashPlayer;
    private FlashPlayerControlBar controlBar;
    private JPanel nativeComponentBorderContainerPane;
    
    public DefaultFlashPlayerDecorator(final JFlashPlayer flashPlayer, final Component renderingComponent) {
        final String className = JFlashPlayer.class.getName();
        this.RESOURCES = ResourceBundle.getBundle(className.substring(0, className.lastIndexOf(46)).replace('.', '/') + "/resource/FlashPlayer");
        this.flashPlayer = flashPlayer;
        (this.nativeComponentBorderContainerPane = new JPanel(new BorderLayout())).add(renderingComponent, "Center");
        this.add(this.nativeComponentBorderContainerPane, "Center");
        this.setControlBarVisible(false);
    }
    
    protected JFlashPlayer getFlashPlayer() {
        return this.flashPlayer;
    }
    
    private void adjustBorder() {
        this.nativeComponentBorderContainerPane.setBorder(this.getInnerAreaBorder());
    }
    
    protected Border getInnerAreaBorder() {
        Border border;
        if (this.isControlBarVisible()) {
            border = BorderFactory.createBevelBorder(1);
        }
        else {
            border = null;
        }
        return border;
    }
    
    @Override
    public void setControlBarVisible(final boolean isControlBarVisible) {
        if (isControlBarVisible == this.isControlBarVisible()) {
            return;
        }
        if (isControlBarVisible) {
            this.add(this.controlBar = new FlashPlayerControlBar(), "South");
        }
        else {
            this.remove(this.controlBar);
            this.controlBar.disconnect();
            this.controlBar = null;
        }
        this.revalidate();
        this.repaint();
        this.adjustBorder();
    }
    
    @Override
    public boolean isControlBarVisible() {
        return this.controlBar != null;
    }
    
    protected void addControlBarComponents(final FlashPlayerControlBar controlBar, final JComponent buttonContainer) {
        buttonContainer.add(controlBar.getPlayButton());
        buttonContainer.add(controlBar.getPauseButton());
        buttonContainer.add(controlBar.getStopButton());
    }
    
    protected void configureComponent(final JComponent c, final FlashDecoratorComponentType componentType) {
        switch (lIllllI.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$DefaultFlashPlayerDecorator$FlashDecoratorComponentType[componentType.ordinal()]) {
            case 1: {
                ((AbstractButton)c).setIcon(this.createIcon("PlayIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("PlayText"));
            }
            case 2: {
                ((AbstractButton)c).setIcon(this.createIcon("PauseIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("PauseText"));
            }
            case 3: {
                ((AbstractButton)c).setIcon(this.createIcon("StopIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("StopText"));
            }
            default: {
                throw new IllegalStateException("Type not handled: " + componentType);
            }
        }
    }
    
    private Icon createIcon(final String resourceKey) {
        final String value = this.RESOURCES.getString(resourceKey);
        return (value.length() == 0) ? null : new ImageIcon(JFlashPlayer.class.getResource(value));
    }
    
    public enum FlashDecoratorComponentType
    {
        PLAY_BUTTON, 
        PAUSE_BUTTON, 
        STOP_BUTTON;
    }
    
    public class FlashPlayerControlBar extends JPanel
    {
        private JButton playButton;
        private JButton pauseButton;
        private JButton stopButton;
        private WebBrowserAdapter webBrowserListener;
        final /* synthetic */ DefaultFlashPlayerDecorator this$0;
        
        FlashPlayerControlBar(final DefaultFlashPlayerDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;
            //     5: aload_0         /* this */
            //     6: new             Ljava/awt/FlowLayout;
            //     9: dup            
            //    10: iconst_1       
            //    11: iconst_4       
            //    12: iconst_2       
            //    13: invokespecial   java/awt/FlowLayout.<init>:(III)V
            //    16: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //    19: aload_0         /* this */
            //    20: new             Ljavax/swing/JButton;
            //    23: dup            
            //    24: invokespecial   javax/swing/JButton.<init>:()V
            //    27: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    30: aload_1         /* this$0 */
            //    31: aload_0         /* this */
            //    32: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    35: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType.PLAY_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;
            //    38: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;)V
            //    41: aload_0         /* this */
            //    42: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    45: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIIIll;
            //    48: dup            
            //    49: aload_0         /* this */
            //    50: aload_1         /* this$0 */
            //    51: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;)V
            //    54: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    57: aload_0         /* this */
            //    58: new             Ljavax/swing/JButton;
            //    61: dup            
            //    62: invokespecial   javax/swing/JButton.<init>:()V
            //    65: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //    68: aload_1         /* this$0 */
            //    69: aload_0         /* this */
            //    70: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //    73: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType.PAUSE_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;
            //    76: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;)V
            //    79: aload_0         /* this */
            //    80: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //    83: new             Lchrriis/dj/nativeswing/swtimpl/components/lllIlll;
            //    86: dup            
            //    87: aload_0         /* this */
            //    88: aload_1         /* this$0 */
            //    89: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;)V
            //    92: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    95: aload_0         /* this */
            //    96: new             Ljavax/swing/JButton;
            //    99: dup            
            //   100: invokespecial   javax/swing/JButton.<init>:()V
            //   103: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   106: aload_1         /* this$0 */
            //   107: aload_0         /* this */
            //   108: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   111: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType.STOP_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;
            //   114: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashDecoratorComponentType;)V
            //   117: aload_0         /* this */
            //   118: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   121: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlIlIl;
            //   124: dup            
            //   125: aload_0         /* this */
            //   126: aload_1         /* this$0 */
            //   127: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIlIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;)V
            //   130: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   133: aload_0         /* this */
            //   134: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.adjustButtonState:()V
            //   137: aload_0         /* this */
            //   138: new             Lchrriis/dj/nativeswing/swtimpl/components/llIlIll;
            //   141: dup            
            //   142: aload_0         /* this */
            //   143: aload_1         /* this$0 */
            //   144: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIlIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;)V
            //   147: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.webBrowserListener:Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserAdapter;
            //   150: aload_1         /* this$0 */
            //   151: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator.access$000:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/JFlashPlayer;
            //   154: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.getWebBrowser:()Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
            //   157: aload_0         /* this */
            //   158: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar.webBrowserListener:Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserAdapter;
            //   161: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.addWebBrowserListener:(Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserListener;)V
            //   164: aload_1         /* this$0 */
            //   165: aload_0         /* this */
            //   166: aload_0         /* this */
            //   167: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator.addControlBarComponents:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultFlashPlayerDecorator$FlashPlayerControlBar;Ljavax/swing/JComponent;)V
            //   170: return         
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
        
        void disconnect() {
            this.this$0.flashPlayer.getWebBrowser().removeWebBrowserListener(this.webBrowserListener);
        }
        
        void adjustButtonState() {
            final String resourceLocation = this.this$0.flashPlayer.getWebBrowser().getResourceLocation();
            final boolean isEnabled = resourceLocation != null && resourceLocation.startsWith(WebServer.getDefaultWebServer().getURLPrefix());
            this.playButton.setEnabled(isEnabled);
            this.pauseButton.setEnabled(isEnabled);
            this.stopButton.setEnabled(isEnabled);
        }
        
        public JButton getPlayButton() {
            return this.playButton;
        }
        
        public JButton getPauseButton() {
            return this.pauseButton;
        }
        
        public JButton getStopButton() {
            return this.stopButton;
        }
    }
}
