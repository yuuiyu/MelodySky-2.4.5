//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface CTabFolder2Listener extends SWTEventListener
{
    void close(final CTabFolderEvent p0);
    
    void minimize(final CTabFolderEvent p0);
    
    void maximize(final CTabFolderEvent p0);
    
    void restore(final CTabFolderEvent p0);
    
    void showList(final CTabFolderEvent p0);
    
    default CTabFolder2Listener closeAdapter(final Consumer<CTabFolderEvent> c) {
        class lIIIIlI extends CTabFolder2Adapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ CTabFolder2Listener this$0;
            
            lIIIIlI(final CTabFolder2Listener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void close(final CTabFolderEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: aload_1         /* c */
        //     6: invokespecial   org/eclipse/swt/custom/lIIIIlI.<init>:(Lorg/eclipse/swt/custom/CTabFolder2Listener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/custom/CTabFolderEvent;>;)Lorg/eclipse/swt/custom/CTabFolder2Listener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default CTabFolder2Listener minimizeAdapter(final Consumer<CTabFolderEvent> c) {
        class lIIIIII extends CTabFolder2Adapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ CTabFolder2Listener this$0;
            
            lIIIIII(final CTabFolder2Listener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void minimize(final CTabFolderEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: aload_1         /* c */
        //     6: invokespecial   org/eclipse/swt/custom/lIIIIII.<init>:(Lorg/eclipse/swt/custom/CTabFolder2Listener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/custom/CTabFolderEvent;>;)Lorg/eclipse/swt/custom/CTabFolder2Listener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default CTabFolder2Listener maximizeAdapter(final Consumer<CTabFolderEvent> c) {
        class llllIl extends CTabFolder2Adapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ CTabFolder2Listener this$0;
            
            llllIl(final CTabFolder2Listener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void maximize(final CTabFolderEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: aload_1         /* c */
        //     6: invokespecial   org/eclipse/swt/custom/llllIl.<init>:(Lorg/eclipse/swt/custom/CTabFolder2Listener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/custom/CTabFolderEvent;>;)Lorg/eclipse/swt/custom/CTabFolder2Listener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default CTabFolder2Listener restoreAdapter(final Consumer<CTabFolderEvent> c) {
        class lIllII extends CTabFolder2Adapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ CTabFolder2Listener this$0;
            
            lIllII(final CTabFolder2Listener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void restore(final CTabFolderEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: aload_1         /* c */
        //     6: invokespecial   org/eclipse/swt/custom/lIllII.<init>:(Lorg/eclipse/swt/custom/CTabFolder2Listener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/custom/CTabFolderEvent;>;)Lorg/eclipse/swt/custom/CTabFolder2Listener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default CTabFolder2Listener showListAdapter(final Consumer<CTabFolderEvent> c) {
        class lIIlll extends CTabFolder2Adapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ CTabFolder2Listener this$0;
            
            lIIlll(final CTabFolder2Listener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void showList(final CTabFolderEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: aload_1         /* c */
        //     6: invokespecial   org/eclipse/swt/custom/lIIlll.<init>:(Lorg/eclipse/swt/custom/CTabFolder2Listener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/custom/CTabFolderEvent;>;)Lorg/eclipse/swt/custom/CTabFolder2Listener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
