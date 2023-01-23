//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;

public class DefaultVLCPlayerDecorator extends VLCPlayerDecorator
{
    private final ResourceBundle RESOURCES;
    private int lastVolume;
    private JVLCPlayer vlcPlayer;
    private VLCPlayerControlBar controlBar;
    private JPanel nativeComponentBorderContainerPane;
    
    public DefaultVLCPlayerDecorator(final JVLCPlayer vlcPlayer, final Component renderingComponent) {
        final String className = JVLCPlayer.class.getName();
        this.RESOURCES = ResourceBundle.getBundle(className.substring(0, className.lastIndexOf(46)).replace('.', '/') + "/resource/VLCPlayer");
        this.lastVolume = 50;
        this.vlcPlayer = vlcPlayer;
        (this.nativeComponentBorderContainerPane = new JPanel(new BorderLayout())).add(renderingComponent, "Center");
        this.add(this.nativeComponentBorderContainerPane, "Center");
        this.setControlBarVisible(false);
    }
    
    protected JVLCPlayer getFlashPlayer() {
        return this.vlcPlayer;
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
            this.add(this.controlBar = new VLCPlayerControlBar(), "South");
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
    
    protected String getTimeDisplay(final int currentTime, final int totalTime) {
        final boolean showHours = totalTime >= 3600000;
        return this.formatTime(currentTime, showHours) + " / " + this.formatTime(totalTime, showHours);
    }
    
    private String formatTime(final int milliseconds, final boolean showHours) {
        int seconds = milliseconds / 1000;
        final int hours = seconds / 3600;
        final int minutes = seconds % 3600 / 60;
        seconds %= 60;
        final StringBuilder sb = new StringBuilder();
        if (hours != 0 || showHours) {
            sb.append(hours).append(':');
        }
        sb.append((minutes < 10) ? "0" : "").append(minutes).append(':');
        sb.append((seconds < 10) ? "0" : "").append(seconds);
        return sb.toString();
    }
    
    protected void addControlBarComponents(final VLCPlayerControlBar controlBar, final JComponent buttonContainer) {
        buttonContainer.add(controlBar.getPlayButton());
        buttonContainer.add(controlBar.getPauseButton());
        buttonContainer.add(controlBar.getStopButton());
    }
    
    protected void configureComponent(final JComponent c, final VLCDecoratorComponentType componentType) {
        switch (lIIIlllI.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$DefaultVLCPlayerDecorator$VLCDecoratorComponentType[componentType.ordinal()]) {
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
            case 4: {
                ((AbstractButton)c).setIcon(this.createIcon("VolumeOffIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("VolumeOffText"));
            }
            case 5: {
                ((AbstractButton)c).setIcon(this.createIcon("VolumeOnIcon"));
                ((AbstractButton)c).setToolTipText(this.RESOURCES.getString("VolumeOnText"));
            }
            default: {
                throw new IllegalStateException("Type not handled: " + componentType);
            }
        }
    }
    
    private Icon createIcon(final String resourceKey) {
        final String value = this.RESOURCES.getString(resourceKey);
        return (value.length() == 0) ? null : new ImageIcon(JVLCPlayer.class.getResource(value));
    }
    
    public enum VLCDecoratorComponentType
    {
        PLAY_BUTTON, 
        PAUSE_BUTTON, 
        STOP_BUTTON, 
        VOLUME_BUTTON_ON, 
        VOLUME_BUTTON_OFF;
    }
    
    public class VLCPlayerControlBar extends JPanel
    {
        private JButton playButton;
        private JButton pauseButton;
        private JButton stopButton;
        private JSlider seekBarSlider;
        private volatile boolean isAdjustingSeekBar;
        private volatile Thread updateThread;
        private JLabel timeLabel;
        private JButton volumeButton;
        private JSlider volumeSlider;
        private boolean isAdjustingVolume;
        private WebBrowserAdapter webBrowserListener;
        private boolean isMute;
        private int volume;
        final /* synthetic */ DefaultVLCPlayerDecorator this$0;
        
        VLCPlayerControlBar(final DefaultVLCPlayerDecorator this$0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;
            //     5: aload_0         /* this */
            //     6: new             Ljava/awt/BorderLayout;
            //     9: dup            
            //    10: invokespecial   java/awt/BorderLayout.<init>:()V
            //    13: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //    16: aload_0         /* this */
            //    17: bipush          -2
            //    19: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volume:I
            //    22: new             Ljavax/swing/JPanel;
            //    25: dup            
            //    26: new             Ljava/awt/FlowLayout;
            //    29: dup            
            //    30: iconst_1       
            //    31: iconst_4       
            //    32: iconst_2       
            //    33: invokespecial   java/awt/FlowLayout.<init>:(III)V
            //    36: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //    39: astore_2        /* buttonPanel */
            //    40: aload_0         /* this */
            //    41: new             Ljavax/swing/JButton;
            //    44: dup            
            //    45: invokespecial   javax/swing/JButton.<init>:()V
            //    48: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    51: aload_1         /* this$0 */
            //    52: aload_0         /* this */
            //    53: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    56: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType.PLAY_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;
            //    59: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;)V
            //    62: aload_0         /* this */
            //    63: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.playButton:Ljavax/swing/JButton;
            //    66: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIIIll;
            //    69: dup            
            //    70: aload_0         /* this */
            //    71: aload_1         /* this$0 */
            //    72: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIIIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //    75: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //    78: aload_0         /* this */
            //    79: new             Ljavax/swing/JButton;
            //    82: dup            
            //    83: invokespecial   javax/swing/JButton.<init>:()V
            //    86: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //    89: aload_1         /* this$0 */
            //    90: aload_0         /* this */
            //    91: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //    94: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType.PAUSE_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;
            //    97: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;)V
            //   100: aload_0         /* this */
            //   101: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.pauseButton:Ljavax/swing/JButton;
            //   104: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIllIIl;
            //   107: dup            
            //   108: aload_0         /* this */
            //   109: aload_1         /* this$0 */
            //   110: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIllIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   113: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   116: aload_0         /* this */
            //   117: new             Ljavax/swing/JButton;
            //   120: dup            
            //   121: invokespecial   javax/swing/JButton.<init>:()V
            //   124: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   127: aload_1         /* this$0 */
            //   128: aload_0         /* this */
            //   129: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   132: getstatic       chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType.STOP_BUTTON:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;
            //   135: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.configureComponent:(Ljavax/swing/JComponent;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCDecoratorComponentType;)V
            //   138: aload_0         /* this */
            //   139: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.stopButton:Ljavax/swing/JButton;
            //   142: new             Lchrriis/dj/nativeswing/swtimpl/components/llIIIlI;
            //   145: dup            
            //   146: aload_0         /* this */
            //   147: aload_1         /* this$0 */
            //   148: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIIIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   151: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   154: aload_1         /* this$0 */
            //   155: aload_0         /* this */
            //   156: aload_2         /* buttonPanel */
            //   157: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.addControlBarComponents:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Ljavax/swing/JComponent;)V
            //   160: aload_0         /* this */
            //   161: new             Ljavax/swing/JSlider;
            //   164: dup            
            //   165: iconst_0       
            //   166: sipush          10000
            //   169: iconst_0       
            //   170: invokespecial   javax/swing/JSlider.<init>:(III)V
            //   173: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.seekBarSlider:Ljavax/swing/JSlider;
            //   176: aload_0         /* this */
            //   177: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.seekBarSlider:Ljavax/swing/JSlider;
            //   180: iconst_0       
            //   181: invokevirtual   javax/swing/JSlider.setVisible:(Z)V
            //   184: aload_0         /* this */
            //   185: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.seekBarSlider:Ljavax/swing/JSlider;
            //   188: new             Lchrriis/dj/nativeswing/swtimpl/components/llIIIIl;
            //   191: dup            
            //   192: aload_0         /* this */
            //   193: aload_1         /* this$0 */
            //   194: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIIIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   197: invokevirtual   javax/swing/JSlider.addChangeListener:(Ljavax/swing/event/ChangeListener;)V
            //   200: aload_0         /* this */
            //   201: aload_0         /* this */
            //   202: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.seekBarSlider:Ljavax/swing/JSlider;
            //   205: ldc             "North"
            //   207: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   210: new             Ljavax/swing/JPanel;
            //   213: dup            
            //   214: new             Ljava/awt/FlowLayout;
            //   217: dup            
            //   218: iconst_2       
            //   219: iconst_0       
            //   220: iconst_2       
            //   221: invokespecial   java/awt/FlowLayout.<init>:(III)V
            //   224: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //   227: astore_3        /* volumePanel */
            //   228: aload_0         /* this */
            //   229: new             Ljavax/swing/JButton;
            //   232: dup            
            //   233: invokespecial   javax/swing/JButton.<init>:()V
            //   236: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   239: aload_0         /* this */
            //   240: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   243: invokevirtual   javax/swing/JButton.getMargin:()Ljava/awt/Insets;
            //   246: astore          margin
            //   248: aload           margin
            //   250: iconst_2       
            //   251: aload           margin
            //   253: getfield        java/awt/Insets.left:I
            //   256: invokestatic    java/lang/Math.min:(II)I
            //   259: putfield        java/awt/Insets.left:I
            //   262: aload           margin
            //   264: iconst_2       
            //   265: aload           margin
            //   267: getfield        java/awt/Insets.left:I
            //   270: invokestatic    java/lang/Math.min:(II)I
            //   273: putfield        java/awt/Insets.right:I
            //   276: aload_0         /* this */
            //   277: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   280: aload           margin
            //   282: invokevirtual   javax/swing/JButton.setMargin:(Ljava/awt/Insets;)V
            //   285: aload_0         /* this */
            //   286: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   289: new             Lchrriis/dj/nativeswing/swtimpl/components/llIllII;
            //   292: dup            
            //   293: aload_0         /* this */
            //   294: aload_1         /* this$0 */
            //   295: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIllII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   298: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   301: aload_3         /* volumePanel */
            //   302: aload_0         /* this */
            //   303: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   306: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   309: pop            
            //   310: aload_0         /* this */
            //   311: new             Ljavax/swing/JSlider;
            //   314: dup            
            //   315: invokespecial   javax/swing/JSlider.<init>:()V
            //   318: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   321: aload_0         /* this */
            //   322: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   325: new             Lchrriis/dj/nativeswing/swtimpl/components/lllllll;
            //   328: dup            
            //   329: aload_0         /* this */
            //   330: aload_1         /* this$0 */
            //   331: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllllll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   334: invokevirtual   javax/swing/JSlider.addChangeListener:(Ljavax/swing/event/ChangeListener;)V
            //   337: aload_0         /* this */
            //   338: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   341: new             Ljava/awt/Dimension;
            //   344: dup            
            //   345: bipush          60
            //   347: aload_0         /* this */
            //   348: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   351: invokevirtual   javax/swing/JSlider.getPreferredSize:()Ljava/awt/Dimension;
            //   354: getfield        java/awt/Dimension.height:I
            //   357: invokespecial   java/awt/Dimension.<init>:(II)V
            //   360: invokevirtual   javax/swing/JSlider.setPreferredSize:(Ljava/awt/Dimension;)V
            //   363: aload_3         /* volumePanel */
            //   364: aload_0         /* this */
            //   365: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   368: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   371: pop            
            //   372: aload_0         /* this */
            //   373: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeButton:Ljavax/swing/JButton;
            //   376: iconst_0       
            //   377: invokevirtual   javax/swing/JButton.setEnabled:(Z)V
            //   380: aload_0         /* this */
            //   381: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.volumeSlider:Ljavax/swing/JSlider;
            //   384: iconst_0       
            //   385: invokevirtual   javax/swing/JSlider.setEnabled:(Z)V
            //   388: new             Ljava/awt/GridBagLayout;
            //   391: dup            
            //   392: invokespecial   java/awt/GridBagLayout.<init>:()V
            //   395: astore          gridBag
            //   397: new             Ljava/awt/GridBagConstraints;
            //   400: dup            
            //   401: invokespecial   java/awt/GridBagConstraints.<init>:()V
            //   404: astore          cons
            //   406: new             Ljavax/swing/JPanel;
            //   409: dup            
            //   410: aload           gridBag
            //   412: invokespecial   javax/swing/JPanel.<init>:(Ljava/awt/LayoutManager;)V
            //   415: astore          buttonBarPanel
            //   417: aload           cons
            //   419: iconst_0       
            //   420: putfield        java/awt/GridBagConstraints.gridx:I
            //   423: aload           cons
            //   425: iconst_0       
            //   426: putfield        java/awt/GridBagConstraints.gridy:I
            //   429: aload           cons
            //   431: dconst_1       
            //   432: putfield        java/awt/GridBagConstraints.weightx:D
            //   435: aload           cons
            //   437: bipush          17
            //   439: putfield        java/awt/GridBagConstraints.anchor:I
            //   442: aload           cons
            //   444: iconst_2       
            //   445: putfield        java/awt/GridBagConstraints.fill:I
            //   448: aload_0         /* this */
            //   449: new             Ljavax/swing/JLabel;
            //   452: dup            
            //   453: ldc             " "
            //   455: invokespecial   javax/swing/JLabel.<init>:(Ljava/lang/String;)V
            //   458: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.timeLabel:Ljavax/swing/JLabel;
            //   461: aload_0         /* this */
            //   462: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.timeLabel:Ljavax/swing/JLabel;
            //   465: new             Ljava/awt/Dimension;
            //   468: dup            
            //   469: iconst_0       
            //   470: aload_0         /* this */
            //   471: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.timeLabel:Ljavax/swing/JLabel;
            //   474: invokevirtual   javax/swing/JLabel.getPreferredSize:()Ljava/awt/Dimension;
            //   477: getfield        java/awt/Dimension.height:I
            //   480: invokespecial   java/awt/Dimension.<init>:(II)V
            //   483: invokevirtual   javax/swing/JLabel.setPreferredSize:(Ljava/awt/Dimension;)V
            //   486: aload           gridBag
            //   488: aload_0         /* this */
            //   489: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.timeLabel:Ljavax/swing/JLabel;
            //   492: aload           cons
            //   494: invokevirtual   java/awt/GridBagLayout.setConstraints:(Ljava/awt/Component;Ljava/awt/GridBagConstraints;)V
            //   497: aload           buttonBarPanel
            //   499: aload_0         /* this */
            //   500: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.timeLabel:Ljavax/swing/JLabel;
            //   503: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   506: pop            
            //   507: aload           cons
            //   509: dup            
            //   510: getfield        java/awt/GridBagConstraints.gridx:I
            //   513: iconst_1       
            //   514: iadd           
            //   515: putfield        java/awt/GridBagConstraints.gridx:I
            //   518: aload           cons
            //   520: dconst_0       
            //   521: putfield        java/awt/GridBagConstraints.weightx:D
            //   524: aload           cons
            //   526: bipush          10
            //   528: putfield        java/awt/GridBagConstraints.anchor:I
            //   531: aload           cons
            //   533: iconst_0       
            //   534: putfield        java/awt/GridBagConstraints.fill:I
            //   537: aload           gridBag
            //   539: aload_2         /* buttonPanel */
            //   540: aload           cons
            //   542: invokevirtual   java/awt/GridBagLayout.setConstraints:(Ljava/awt/Component;Ljava/awt/GridBagConstraints;)V
            //   545: aload           buttonBarPanel
            //   547: aload_2         /* buttonPanel */
            //   548: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   551: pop            
            //   552: aload           buttonBarPanel
            //   554: aload           buttonBarPanel
            //   556: invokevirtual   javax/swing/JPanel.getPreferredSize:()Ljava/awt/Dimension;
            //   559: invokevirtual   javax/swing/JPanel.setMinimumSize:(Ljava/awt/Dimension;)V
            //   562: aload           cons
            //   564: dup            
            //   565: getfield        java/awt/GridBagConstraints.gridx:I
            //   568: iconst_1       
            //   569: iadd           
            //   570: putfield        java/awt/GridBagConstraints.gridx:I
            //   573: aload           cons
            //   575: dconst_1       
            //   576: putfield        java/awt/GridBagConstraints.weightx:D
            //   579: aload           cons
            //   581: bipush          13
            //   583: putfield        java/awt/GridBagConstraints.anchor:I
            //   586: aload           cons
            //   588: iconst_2       
            //   589: putfield        java/awt/GridBagConstraints.fill:I
            //   592: aload_3         /* volumePanel */
            //   593: new             Ljava/awt/Dimension;
            //   596: dup            
            //   597: iconst_0       
            //   598: aload_3         /* volumePanel */
            //   599: invokevirtual   javax/swing/JPanel.getPreferredSize:()Ljava/awt/Dimension;
            //   602: getfield        java/awt/Dimension.height:I
            //   605: invokespecial   java/awt/Dimension.<init>:(II)V
            //   608: invokevirtual   javax/swing/JPanel.setPreferredSize:(Ljava/awt/Dimension;)V
            //   611: aload           gridBag
            //   613: aload_3         /* volumePanel */
            //   614: aload           cons
            //   616: invokevirtual   java/awt/GridBagLayout.setConstraints:(Ljava/awt/Component;Ljava/awt/GridBagConstraints;)V
            //   619: aload           buttonBarPanel
            //   621: aload_3         /* volumePanel */
            //   622: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   625: pop            
            //   626: aload_0         /* this */
            //   627: aload           buttonBarPanel
            //   629: ldc             "Center"
            //   631: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   634: aload_0         /* this */
            //   635: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.adjustButtonState:()V
            //   638: aload_0         /* this */
            //   639: invokespecial   chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.updateControlBar:()V
            //   642: aload_0         /* this */
            //   643: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIlIIII;
            //   646: dup            
            //   647: aload_0         /* this */
            //   648: aload_1         /* this$0 */
            //   649: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIlIIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)V
            //   652: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.webBrowserListener:Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserAdapter;
            //   655: aload_1         /* this$0 */
            //   656: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.access$000:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/JVLCPlayer;
            //   659: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JVLCPlayer.getWebBrowser:()Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
            //   662: aload_0         /* this */
            //   663: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.webBrowserListener:Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserAdapter;
            //   666: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.addWebBrowserListener:(Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserListener;)V
            //   669: return         
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
            this.stopUpdateThread();
            this.this$0.vlcPlayer.getWebBrowser().removeWebBrowserListener(this.webBrowserListener);
        }
        
        void adjustButtonState() {
            final String resourceLocation = this.this$0.vlcPlayer.getWebBrowser().getResourceLocation();
            final boolean isEnabled = resourceLocation != null && resourceLocation.startsWith(WebServer.getDefaultWebServer().getURLPrefix());
            this.playButton.setEnabled(isEnabled);
            this.pauseButton.setEnabled(isEnabled);
            this.stopButton.setEnabled(isEnabled);
            this.volumeButton.setEnabled(isEnabled);
            this.volumeSlider.setEnabled(isEnabled);
            if (isEnabled) {
                this.adjustVolumePanel();
                this.startUpdateThread();
            }
        }
        
        void adjustVolumePanel() {
            final VLCAudio vlcAudio = this.this$0.vlcPlayer.getVLCAudio();
            final boolean isMute = vlcAudio.isMute();
            final int volume = vlcAudio.getVolume();
            this.volumeButton.setEnabled(true);
            this.volumeSlider.setEnabled(!isMute);
            if (isMute == this.isMute && this.volume == volume) {
                return;
            }
            if (isMute) {
                this.this$0.configureComponent(this.volumeButton, VLCDecoratorComponentType.VOLUME_BUTTON_OFF);
            }
            else {
                this.this$0.configureComponent(this.volumeButton, VLCDecoratorComponentType.VOLUME_BUTTON_ON);
            }
            this.isAdjustingVolume = true;
            if (!isMute) {
                this.volumeSlider.setValue(volume);
                this.this$0.lastVolume = volume;
            }
            else {
                this.volumeSlider.setValue(this.this$0.lastVolume);
            }
            this.isAdjustingVolume = false;
            this.isMute = isMute;
            this.volume = volume;
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
        
        @Override
        public void removeNotify() {
            this.stopUpdateThread();
            super.removeNotify();
        }
        
        @Override
        public void addNotify() {
            super.addNotify();
            this.adjustButtonState();
        }
        
        private void stopUpdateThread() {
            this.updateThread = null;
        }
        
        private void startUpdateThread() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.updateThread:Ljava/lang/Thread;
            //     4: ifnull          8
            //     7: return         
            //     8: aload_0         /* this */
            //     9: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;
            //    12: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.access$000:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/JVLCPlayer;
            //    15: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JVLCPlayer.isNativePeerDisposed:()Z
            //    18: ifeq            22
            //    21: return         
            //    22: aload_0         /* this */
            //    23: new             Lchrriis/dj/nativeswing/swtimpl/components/lIlllIl;
            //    26: dup            
            //    27: aload_0         /* this */
            //    28: ldc_w           "NativeSwing - VLC Player control bar update"
            //    31: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlllIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;Ljava/lang/String;)V
            //    34: putfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.updateThread:Ljava/lang/Thread;
            //    37: aload_0         /* this */
            //    38: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.updateThread:Ljava/lang/Thread;
            //    41: iconst_1       
            //    42: invokevirtual   java/lang/Thread.setDaemon:(Z)V
            //    45: aload_0         /* this */
            //    46: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.updateThread:Ljava/lang/Thread;
            //    49: invokevirtual   java/lang/Thread.start:()V
            //    52: return         
            //    StackMapTable: 00 02 08 0D
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
        
        private void updateControlBar() {
            final VLCInput vlcInput = this.this$0.vlcPlayer.getVLCInput();
            final VLCInput.VLCMediaState state = vlcInput.getMediaState();
            boolean isValid = state == VLCInput.VLCMediaState.OPENING || state == VLCInput.VLCMediaState.BUFFERING || state == VLCInput.VLCMediaState.PLAYING || state == VLCInput.VLCMediaState.PAUSED || state == VLCInput.VLCMediaState.STOPPING;
            if (isValid) {
                final int time = vlcInput.getAbsolutePosition();
                final int length = vlcInput.getDuration();
                isValid = (time >= 0 && length > 0);
                if (isValid) {
                    this.isAdjustingSeekBar = true;
                    this.seekBarSlider.setValue(Math.round(time * 10000.0f / length));
                    this.isAdjustingSeekBar = false;
                    this.timeLabel.setText(this.this$0.getTimeDisplay(time, length));
                }
            }
            if (!isValid) {
                this.timeLabel.setText("");
            }
            this.seekBarSlider.setVisible(isValid);
            this.adjustVolumePanel();
        }
    }
}
