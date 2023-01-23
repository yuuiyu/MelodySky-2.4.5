//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface ControlListener extends SWTEventListener
{
    void controlMoved(final ControlEvent p0);
    
    void controlResized(final ControlEvent p0);
    
    default ControlListener controlMovedAdapter(final Consumer<ControlEvent> c) {
        class lIIlII extends ControlAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ ControlListener this$0;
            
            lIIlII(final ControlListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void controlMoved(final ControlEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lIIlII.<init>:(Lorg/eclipse/swt/events/ControlListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/ControlEvent;>;)Lorg/eclipse/swt/events/ControlListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default ControlListener controlResizedAdapter(final Consumer<ControlEvent> c) {
        final class llIIII extends ControlAdapter
        {
            final /* synthetic */ Consumer val$c;
            
            llIIII(final Consumer val$c) {
                this.val$c = val$c;
            }
            
            public void controlResized(final ControlEvent e) {
                this.val$c.accept(e);
            }
        }
        
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* c */
        //     5: invokespecial   org/eclipse/swt/events/llIIII.<init>:(Ljava/util/function/Consumer;)V
        //     8: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/ControlEvent;>;)Lorg/eclipse/swt/events/ControlListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
