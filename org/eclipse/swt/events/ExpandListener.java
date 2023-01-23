//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface ExpandListener extends SWTEventListener
{
    void itemCollapsed(final ExpandEvent p0);
    
    void itemExpanded(final ExpandEvent p0);
    
    default ExpandListener itemCollapsedAdapter(final Consumer<ExpandEvent> c) {
        class lIIIll extends ExpandAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ ExpandListener this$0;
            
            lIIIll(final ExpandListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void itemCollapsed(final ExpandEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lIIIll.<init>:(Lorg/eclipse/swt/events/ExpandListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/ExpandEvent;>;)Lorg/eclipse/swt/events/ExpandListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default ExpandListener itemExpandedAdapter(final Consumer<ExpandEvent> c) {
        class lIlllI extends ExpandAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ ExpandListener this$0;
            
            lIlllI(final ExpandListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void itemExpanded(final ExpandEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lIlllI.<init>:(Lorg/eclipse/swt/events/ExpandListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/ExpandEvent;>;)Lorg/eclipse/swt/events/ExpandListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
