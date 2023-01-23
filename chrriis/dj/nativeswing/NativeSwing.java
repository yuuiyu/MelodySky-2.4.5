//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.io.*;
import java.awt.datatransfer.*;
import chrriis.dj.nativeswing.common.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NativeSwing
{
    private static volatile List<NativeComponentWrapper> nativeComponentWrapperList;
    private static List<Window> windowList;
    private static volatile boolean isInitialized;
    private static volatile boolean isHeavyWeightForcerEnabled;
    
    static NativeComponentWrapper[] getNativeComponentWrappers() {
        if (NativeSwing.nativeComponentWrapperList == null) {
            return new NativeComponentWrapper[0];
        }
        return NativeSwing.nativeComponentWrapperList.toArray(new NativeComponentWrapper[0]);
    }
    
    static void addNativeComponentWrapper(final NativeComponentWrapper nativeComponentWrapper) {
        checkInitialized();
        if (NativeSwing.nativeComponentWrapperList == null) {
            NativeSwing.nativeComponentWrapperList = new ArrayList<NativeComponentWrapper>();
        }
        NativeSwing.nativeComponentWrapperList.add(nativeComponentWrapper);
        if (!NativeSwing.isHeavyWeightForcerEnabled) {
            HeavyweightForcer.activate(nativeComponentWrapper.getNativeComponent());
        }
    }
    
    static boolean removeNativeComponentWrapper(final NativeComponentWrapper nativeComponentWrapper) {
        return NativeSwing.nativeComponentWrapperList != null && NativeSwing.nativeComponentWrapperList.remove(nativeComponentWrapper);
    }
    
    static Window[] getWindows() {
        return (NativeSwing.windowList == null) ? new Window[0] : NativeSwing.windowList.toArray(new Window[0]);
    }
    
    private static boolean isInitialized() {
        return NativeSwing.isInitialized;
    }
    
    private static void checkInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("The Native Swing framework is not initialized! Please refer to the instructions to set it up properly.");
        }
    }
    
    private static void loadClipboardDebuggingProperties() {
        try {
            final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (!systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                return;
            }
            final BufferedReader reader = new BufferedReader(new StringReader((String)systemClipboard.getData(DataFlavor.stringFlavor)));
            if ("[nativeswing debug]".equals(reader.readLine().trim().toLowerCase(Locale.ENGLISH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() != 0) {
                        final int index = line.indexOf(61);
                        if (index <= 0) {
                            break;
                        }
                        final String propertyName = line.substring(0, index).trim();
                        final String propertyValue = line.substring(index + 1).trim();
                        if (!propertyName.startsWith("nativeswing.")) {
                            continue;
                        }
                        System.setProperty(propertyName, propertyValue);
                    }
                }
            }
            reader.close();
        }
        catch (Exception ex) {}
    }
    
    public static void initialize() {
        if (isInitialized()) {
            return;
        }
        loadClipboardDebuggingProperties();
        SystemProperty.SUN_AWT_NOERASEBACKGROUND.set("true");
        SystemProperty.SUN_AWT_XEMBEDSERVER.set("true");
        NSSystemProperty.JNA_FORCE_HW_POPUP.set("false");
        if (SystemProperty.JAVAWEBSTART_VERSION.get() != null && SystemProperty.JAVA_VERSION.get().compareTo("1.6.0_18") >= 0) {
            if (SystemProperty.SUN_AWT_DISABLEMIXING.get() == null) {
                System.err.println("Under WebStart on Java >= 1.6.0_18, the value of the \"" + SystemProperty.SUN_AWT_DISABLEMIXING.getName() + "\" system property needs to be defined in the JNLP descriptor with value \"true\" (or \"false\" if you really want the default behavior). When not set to \"true\", the content of the native components may not be displayed.");
                SystemProperty.SUN_AWT_DISABLEMIXING.set("false");
            }
        }
        else if (SystemProperty.SUN_AWT_DISABLEMIXING.get() == null) {
            SystemProperty.SUN_AWT_DISABLEMIXING.set("true");
        }
        final boolean isSunMixingEnabled = NativeSwing.isHeavyWeightForcerEnabled = (!"true".equals(SystemProperty.SUN_AWT_DISABLEMIXING.get()) && SystemProperty.JAVA_VERSION.get().compareTo("1.6.0_12") >= 0);
        NSSystemProperty.INTEGRATION_USEDEFAULTCLIPPING.set(String.valueOf(isSunMixingEnabled));
        long flags = 65L;
        if (Utils.IS_JAVA_7_OR_GREATER) {
            flags |= 0x14L;
        }
        Toolkit.getDefaultToolkit().addAWTEventListener(new NIAWTEventListener(null), flags);
        NativeSwing.isInitialized = true;
    }
    
    private NativeSwing() {
    }
    
    private static class HeavyweightForcerWindow extends Window
    {
        private boolean isPacked;
        private int count;
        
        public HeavyweightForcerWindow(final Window parent) {
            super(parent);
            this.pack();
            this.isPacked = true;
        }
        
        @Override
        public boolean isVisible() {
            return this.isPacked;
        }
        
        @Override
        public Rectangle getBounds() {
            final Window owner = this.getOwner();
            return (owner == null) ? super.getBounds() : owner.getBounds();
        }
        
        public void setCount(final int count) {
            this.count = count;
        }
        
        public int getCount() {
            return this.count;
        }
    }
    
    private static class HeavyweightForcer implements HierarchyListener
    {
        private Component component;
        private HeavyweightForcerWindow forcer;
        
        private HeavyweightForcer(final Component component) {
            this.component = component;
            if (component.isShowing()) {
                this.createForcer();
            }
        }
        
        public static void activate(final Component component) {
            component.addHierarchyListener(new HeavyweightForcer(component));
        }
        
        private void destroyForcer() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: ifne            18
            //     6: new             Lchrriis/dj/nativeswing/llIIl;
            //     9: dup            
            //    10: aload_0         /* this */
            //    11: invokespecial   chrriis/dj/nativeswing/llIIl.<init>:(Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcer;)V
            //    14: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
            //    17: return         
            //    18: aload_0         /* this */
            //    19: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    22: ifnonnull       26
            //    25: return         
            //    26: aload_0         /* this */
            //    27: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    30: invokevirtual   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.getCount:()I
            //    33: iconst_1       
            //    34: isub           
            //    35: istore_1        /* count */
            //    36: aload_0         /* this */
            //    37: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    40: iload_1         /* count */
            //    41: invokevirtual   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.setCount:(I)V
            //    44: iload_1         /* count */
            //    45: ifne            55
            //    48: aload_0         /* this */
            //    49: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    52: invokevirtual   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.dispose:()V
            //    55: aload_0         /* this */
            //    56: aconst_null    
            //    57: putfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    60: return         
            //    StackMapTable: 00 03 12 07 FC 00 1C 01
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
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
        
        private void createForcer() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: ifne            18
            //     6: new             Lchrriis/dj/nativeswing/lIlIlI;
            //     9: dup            
            //    10: aload_0         /* this */
            //    11: invokespecial   chrriis/dj/nativeswing/lIlIlI.<init>:(Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcer;)V
            //    14: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
            //    17: return         
            //    18: aload_0         /* this */
            //    19: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.component:Ljava/awt/Component;
            //    22: invokestatic    javax/swing/SwingUtilities.getWindowAncestor:(Ljava/awt/Component;)Ljava/awt/Window;
            //    25: astore_1        /* windowAncestor */
            //    26: aload_1         /* windowAncestor */
            //    27: ifnonnull       31
            //    30: return         
            //    31: aload_1         /* windowAncestor */
            //    32: invokevirtual   java/awt/Window.getOwnedWindows:()[Ljava/awt/Window;
            //    35: astore_2       
            //    36: aload_2        
            //    37: arraylength    
            //    38: istore_3       
            //    39: iconst_0       
            //    40: istore          4
            //    42: iload           4
            //    44: iload_3        
            //    45: if_icmpge       80
            //    48: aload_2        
            //    49: iload           4
            //    51: aaload         
            //    52: astore          window
            //    54: aload           window
            //    56: instanceof      Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    59: ifeq            74
            //    62: aload_0         /* this */
            //    63: aload           window
            //    65: checkcast       Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    68: putfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    71: goto            80
            //    74: iinc            4, 1
            //    77: goto            42
            //    80: aload_0         /* this */
            //    81: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    84: ifnonnull       99
            //    87: aload_0         /* this */
            //    88: new             Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    91: dup            
            //    92: aload_1         /* windowAncestor */
            //    93: invokespecial   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.<init>:(Ljava/awt/Window;)V
            //    96: putfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //    99: aload_0         /* this */
            //   100: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //   103: aload_0         /* this */
            //   104: getfield        chrriis/dj/nativeswing/NativeSwing$HeavyweightForcer.forcer:Lchrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow;
            //   107: invokevirtual   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.getCount:()I
            //   110: iconst_1       
            //   111: iadd           
            //   112: invokevirtual   chrriis/dj/nativeswing/NativeSwing$HeavyweightForcerWindow.setCount:(I)V
            //   115: return         
            //    StackMapTable: 00 06 12 FC 00 0C 07 00 51 FE 00 0A 07 00 57 01 01 FC 00 1F 07 00 51 FA 00 05 12
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
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
        
        @Override
        public void hierarchyChanged(final HierarchyEvent e) {
            final long changeFlags = e.getChangeFlags();
            if ((changeFlags & 0x2L) != 0x0L) {
                if (!this.component.isDisplayable()) {
                    this.component.removeHierarchyListener(this);
                    this.destroyForcer();
                }
            }
            else if ((changeFlags & 0x4L) != 0x0L) {
                if (this.component.isShowing()) {
                    this.createForcer();
                }
                else {
                    this.destroyForcer();
                }
            }
        }
    }
    
    private static class NIAWTEventListener implements AWTEventListener
    {
        private List<Dialog> dialogList;
        private volatile Set<Window> blockedWindowSet;
        
        private NIAWTEventListener() {
            this.dialogList = new ArrayList<Dialog>();
            this.blockedWindowSet = new HashSet<Window>();
        }
        
        private static boolean isDescendant(Window window, final Window ancestorWindow) {
            while (window != null) {
                if (window == ancestorWindow) {
                    return true;
                }
                window = window.getOwner();
            }
            return false;
        }
        
        private void computeBlockedDialogs() {
            this.blockedWindowSet.clear();
            final Window[] windows = NativeSwing.getWindows();
            final List<Dialog> applicationModalDialogList = new ArrayList<Dialog>();
            for (final Dialog dialog : this.dialogList) {
                if (dialog.isVisible()) {
                    boolean isApplicationModal = false;
                    if (Utils.IS_JAVA_6_OR_GREATER) {
                        switch (lIIlII.$SwitchMap$java$awt$Dialog$ModalityType[dialog.getModalityType().ordinal()]) {
                            case 1:
                            case 2: {
                                isApplicationModal = true;
                                break;
                            }
                        }
                    }
                    else if (dialog.isModal()) {
                        isApplicationModal = true;
                    }
                    if (!isApplicationModal) {
                        continue;
                    }
                    applicationModalDialogList.add(dialog);
                }
            }
            if (!applicationModalDialogList.isEmpty()) {
                for (int i = 0; i < windows.length; ++i) {
                    final Window window = windows[i];
                    boolean isIncluded = false;
                    for (final Dialog applicationModalDialog : applicationModalDialogList) {
                        if (window != applicationModalDialog && !isDescendant(window, applicationModalDialog)) {
                            isIncluded = true;
                            for (int j = 0; j < i; ++j) {
                                if (windows[j] == applicationModalDialog) {
                                    isIncluded = false;
                                    break;
                                }
                            }
                            if (isIncluded && Utils.IS_JAVA_6_OR_GREATER) {
                                switch (lIIlII.$SwitchMap$java$awt$Dialog$ModalExclusionType[window.getModalExclusionType().ordinal()]) {
                                    case 1:
                                    case 2: {
                                        isIncluded = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (isIncluded) {
                            break;
                        }
                    }
                    if (isIncluded) {
                        this.blockedWindowSet.add(window);
                    }
                }
            }
            if (Utils.IS_JAVA_6_OR_GREATER) {
                for (int i = this.dialogList.size() - 1; i >= 0; --i) {
                    final Dialog dialog = this.dialogList.get(i);
                    if (dialog.isVisible() && !this.blockedWindowSet.contains(dialog)) {
                        switch (lIIlII.$SwitchMap$java$awt$Dialog$ModalityType[dialog.getModalityType().ordinal()]) {
                            case 3: {
                                Window owner;
                                Window hierarchyOwnerWindow;
                                for (hierarchyOwnerWindow = (owner = dialog.getOwner()); owner != null; owner = owner.getOwner()) {
                                    hierarchyOwnerWindow = owner;
                                }
                                if (hierarchyOwnerWindow != null) {
                                    for (final Window window2 : windows) {
                                        if (window2 != dialog && !isDescendant(window2, dialog) && (window2 == hierarchyOwnerWindow || isDescendant(window2, hierarchyOwnerWindow))) {
                                            this.blockedWindowSet.add(window2);
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        private void adjustNativeComponents() {
            if (NativeSwing.nativeComponentWrapperList == null) {
                return;
            }
            for (int i = NativeSwing.nativeComponentWrapperList.size() - 1; i >= 0; --i) {
                final NativeComponentWrapper nativeComponentWrapper = NativeSwing.nativeComponentWrapperList.get(i);
                Component c;
                final Component component = c = nativeComponentWrapper.getNativeComponent();
                final Component componentProxy = (Component)nativeComponentWrapper.getNativeComponentProxy();
                if (componentProxy != null) {
                    c = componentProxy;
                }
                final Window embedderWindowAncestor = SwingUtilities.getWindowAncestor(c);
                final boolean isBlocked = this.blockedWindowSet.contains(embedderWindowAncestor);
                final boolean isShowing = c.isShowing();
                nativeComponentWrapper.setNativeComponentEnabled(!isBlocked && isShowing);
                if (!Utils.IS_MAC && !isShowing && component.hasFocus()) {
                    component.transferFocus();
                }
            }
        }
        
        @Override
        public void eventDispatched(final AWTEvent e) {
            final int eventID = e.getID();
            if (Utils.IS_JAVA_7_OR_GREATER) {
                switch (eventID) {
                    case 501:
                    case 1004: {
                        if (NativeSwing.nativeComponentWrapperList == null) {
                            return;
                        }
                        for (int i = NativeSwing.nativeComponentWrapperList.size() - 1; i >= 0; --i) {
                            final NativeComponentWrapper nativeComponentWrapper = NativeSwing.nativeComponentWrapperList.get(i);
                            if (nativeComponentWrapper.isNativeComponentEnabled()) {
                                nativeComponentWrapper.setNativeComponentEnabled(false);
                                nativeComponentWrapper.setNativeComponentEnabled(true);
                            }
                        }
                        return;
                    }
                }
            }
            boolean isAdjusting = false;
            switch (eventID) {
                case 102:
                case 103: {
                    isAdjusting = true;
                    break;
                }
            }
            if (e.getSource() instanceof Window) {
                if (NativeSwing.windowList == null) {
                    NativeSwing.windowList = (List<Window>)new ArrayList();
                }
                switch (eventID) {
                    case 102:
                    case 200: {
                        final Window w = (Window)e.getSource();
                        NativeSwing.windowList.remove(w);
                        NativeSwing.windowList.add(w);
                        break;
                    }
                    case 103:
                    case 202: {
                        NativeSwing.windowList.remove(e.getSource());
                        break;
                    }
                }
            }
            if (e.getSource() instanceof Dialog) {
                switch (eventID) {
                    case 102:
                    case 200: {
                        final Dialog d = (Dialog)e.getSource();
                        this.dialogList.remove(d);
                        this.dialogList.add(d);
                        break;
                    }
                    case 103:
                    case 202: {
                        this.dialogList.remove(e.getSource());
                        break;
                    }
                }
                switch (eventID) {
                    case 102:
                    case 103:
                    case 200:
                    case 202: {
                        this.computeBlockedDialogs();
                        isAdjusting = true;
                        break;
                    }
                }
            }
            if (isAdjusting) {
                this.adjustNativeComponents();
            }
            switch (eventID) {
                case 208: {
                    if (!(e.getSource() instanceof Dialog)) {
                        break;
                    }
                    final Dialog d = (Dialog)e.getSource();
                    if (d.getFocusableWindowState()) {
                        d.setFocusableWindowState(false);
                        final Thread t = (Thread)new lIIllI(this, "Dialog focus fixer", d);
                        t.setDaemon(true);
                        t.start();
                        break;
                    }
                    break;
                }
            }
        }
    }
}
