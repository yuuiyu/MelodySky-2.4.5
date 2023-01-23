//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import chrriis.dj.nativeswing.common.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;

class NativeComponentProxyFinalizationPanel extends NativeComponentProxy
{
    private EmbeddedPanel embeddedPanel;
    private boolean isProxied;
    
    NativeComponentProxyFinalizationPanel(final NativeComponentWrapper nativeComponentWrapper) {
        super(nativeComponentWrapper);
    }
    
    public void addNotify() {
        super.addNotify();
        final JLayeredPane layeredPane = findLayeredPane((Component)this);
        if (this.embeddedPanel != null && this.embeddedPanel.isHiddenReparenting) {
            layeredPane.setLayer(this.embeddedPanel, Integer.MIN_VALUE);
            layeredPane.add(this.embeddedPanel);
            layeredPane.invalidate();
            layeredPane.validate();
            this.nativeComponentWrapper.restoreFromHiddenParent();
            this.embeddedPanel.isHiddenReparenting = false;
        }
        final boolean isEmbeddedPanelCreated = this.embeddedPanel != null;
        if (isEmbeddedPanelCreated) {
            final JLayeredPane oldLayeredPane = findLayeredPane((Component)this.embeddedPanel);
            if (layeredPane != oldLayeredPane) {
                this.nativeComponentWrapper.storeInHiddenParent();
                final Container oldParent = this.embeddedPanel.getParent();
                oldParent.remove(this.embeddedPanel);
                UIUtils.revalidate((Component)oldParent);
                oldParent.repaint();
                layeredPane.setLayer(this.embeddedPanel, Integer.MIN_VALUE);
                layeredPane.add(this.embeddedPanel);
                this.nativeComponentWrapper.restoreFromHiddenParent();
                UIUtils.revalidate((Component)layeredPane);
                layeredPane.repaint();
                this.revalidate();
                this.repaint();
            }
        }
        else {
            (this.embeddedPanel = new EmbeddedPanel(this.nativeComponentWrapper)).add(this.nativeComponentWrapper.getNativeComponent(), "Center");
        }
        this.isProxied = false;
        final JComponent oldParent2 = (JComponent)this.embeddedPanel.getParent();
        if (oldParent2 != this) {
            if (oldParent2 == null) {
                this.add((Component)this.embeddedPanel);
            }
            else {
                this.setComponentZOrder((Component)this.embeddedPanel, 0);
                if (oldParent2 instanceof JLayeredPane) {
                    try {
                        final Method getComponentToLayerMethod = JLayeredPane.class.getDeclaredMethod("getComponentToLayer", (Class<?>[])new Class[0]);
                        getComponentToLayerMethod.setAccessible(true);
                        ((Hashtable)getComponentToLayerMethod.invoke(oldParent2, new Object[0])).remove(this.embeddedPanel);
                    }
                    catch (Throwable t) {}
                }
                oldParent2.revalidate();
                oldParent2.repaint();
            }
            this.revalidate();
            this.repaint();
            this.embeddedPanel.setVisible(true);
        }
    }
    
    public void removeNotify() {
        try {
            if (this.embeddedPanel != null) {
                this.nativeComponentWrapper.storeInHiddenParent();
                this.embeddedPanel.isHiddenReparenting = true;
            }
        }
        catch (Exception e) {
            if (!this.isProxied) {
                this.embeddedPanel.setVisible(false);
                this.isProxied = true;
                try {
                    final JLayeredPane layeredPane = findLayeredPane((Component)this);
                    layeredPane.setLayer(this.embeddedPanel, Integer.MIN_VALUE);
                    layeredPane.setComponentZOrder(this.embeddedPanel, 0);
                    layeredPane.revalidate();
                    layeredPane.repaint();
                    this.revalidate();
                    this.repaint();
                }
                catch (RuntimeException ex) {
                    super.removeNotify();
                    throw ex;
                }
            }
        }
        super.removeNotify();
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.embeddedPanel != null) {
            SwingUtilities.invokeLater((Runnable)new llIlI(this));
        }
    }
    
    public void dispose() {
        if (this.embeddedPanel == null) {
            return;
        }
        final EmbeddedPanel panel = this.embeddedPanel;
        this.embeddedPanel.removeNotify();
        this.embeddedPanel = null;
        final Container parent = panel.getParent();
        if (parent != null) {
            panel.isRemovingFromParent = true;
            parent.remove(panel);
            parent.invalidate();
            parent.validate();
            parent.repaint();
        }
    }
    
    private static class EmbeddedPanel extends Panel implements NativeComponentWrapper.NativeComponentHolder
    {
        private NativeComponentWrapper nativeComponentWrapper;
        private boolean isHiddenReparenting;
        private boolean isRemovingFromParent;
        
        public EmbeddedPanel(final NativeComponentWrapper nativeComponentWrapper) {
            super(new BorderLayout());
            this.nativeComponentWrapper = nativeComponentWrapper;
            this.enableEvents(131072L);
        }
        
        @Override
        public void removeNotify() {
            super.removeNotify();
            if (!this.isRemovingFromParent) {
                final Container parent = this.getParent();
                if (parent != null) {
                    this.isRemovingFromParent = true;
                    parent.remove(this);
                    parent.invalidate();
                    parent.validate();
                    this.isRemovingFromParent = false;
                }
            }
        }
        
        @Override
        protected void finalize() throws Throwable {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        chrriis/dj/nativeswing/NativeComponentProxyFinalizationPanel$EmbeddedPanel.isHiddenReparenting:Z
            //     4: ifeq            18
            //     7: new             Lchrriis/dj/nativeswing/lIllIl;
            //    10: dup            
            //    11: aload_0         /* this */
            //    12: invokespecial   chrriis/dj/nativeswing/lIllIl.<init>:(Lchrriis/dj/nativeswing/NativeComponentProxyFinalizationPanel$EmbeddedPanel;)V
            //    15: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
            //    18: return         
            //    Exceptions:
            //  throws java.lang.Throwable
            //    StackMapTable: 00 01 12
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
    }
}
