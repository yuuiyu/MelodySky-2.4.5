//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface KeyListener extends SWTEventListener
{
    void keyPressed(final KeyEvent p0);
    
    void keyReleased(final KeyEvent p0);
    
    default KeyListener keyPressedAdapter(final Consumer<KeyEvent> c) {
        class lllII extends KeyAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ KeyListener this$0;
            
            lllII(final KeyListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void keyPressed(final KeyEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lllII.<init>:(Lorg/eclipse/swt/events/KeyListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/KeyEvent;>;)Lorg/eclipse/swt/events/KeyListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default KeyListener keyReleasedAdapter(final Consumer<KeyEvent> c) {
        class lIlIlI extends KeyAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ KeyListener this$0;
            
            lIlIlI(final KeyListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void keyReleased(final KeyEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/events/lIlIlI.<init>:(Lorg/eclipse/swt/events/KeyListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/events/KeyEvent;>;)Lorg/eclipse/swt/events/KeyListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
