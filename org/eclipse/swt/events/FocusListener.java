//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface FocusListener extends SWTEventListener
{
    void focusGained(final FocusEvent p0);
    
    void focusLost(final FocusEvent p0);
    
    default FocusListener focusGainedAdapter(final Consumer<FocusEvent> c) {
        class llIll extends FocusAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ FocusListener this$0;
            
            llIll(final FocusListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void focusGained(final FocusEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/llIll.<init>:(Lorg/eclipse/swt/events/FocusListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/FocusEvent;>;)Lorg/eclipse/swt/events/FocusListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default FocusListener focusLostAdapter(final Consumer<FocusEvent> c) {
        class lIIIlI extends FocusAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ FocusListener this$0;
            
            lIIIlI(final FocusListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void focusLost(final FocusEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lIIIlI.<init>:(Lorg/eclipse/swt/events/FocusListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/FocusEvent;>;)Lorg/eclipse/swt/events/FocusListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
