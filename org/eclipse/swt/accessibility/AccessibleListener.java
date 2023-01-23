//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface AccessibleListener extends SWTEventListener
{
    void getName(final AccessibleEvent p0);
    
    void getHelp(final AccessibleEvent p0);
    
    void getKeyboardShortcut(final AccessibleEvent p0);
    
    void getDescription(final AccessibleEvent p0);
    
    default AccessibleListener getNameAdapter(final Consumer<AccessibleEvent> c) {
        class lIlll extends AccessibleAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ AccessibleListener this$0;
            
            lIlll(final AccessibleListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void getName(final AccessibleEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/accessibility/lIlll.<init>:(Lorg/eclipse/swt/accessibility/AccessibleListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/accessibility/AccessibleEvent;>;)Lorg/eclipse/swt/accessibility/AccessibleListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default AccessibleListener getHelpAdapter(final Consumer<AccessibleEvent> c) {
        class lIIIIl extends AccessibleAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ AccessibleListener this$0;
            
            lIIIIl(final AccessibleListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void getHelp(final AccessibleEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/accessibility/lIIIIl.<init>:(Lorg/eclipse/swt/accessibility/AccessibleListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/accessibility/AccessibleEvent;>;)Lorg/eclipse/swt/accessibility/AccessibleListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default AccessibleListener getKeyboardShortcutAdapter(final Consumer<AccessibleEvent> c) {
        class lIlII extends AccessibleAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ AccessibleListener this$0;
            
            lIlII(final AccessibleListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void getKeyboardShortcut(final AccessibleEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/accessibility/lIlII.<init>:(Lorg/eclipse/swt/accessibility/AccessibleListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/accessibility/AccessibleEvent;>;)Lorg/eclipse/swt/accessibility/AccessibleListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    default AccessibleListener getDescriptionAdapter(final Consumer<AccessibleEvent> c) {
        class lllll extends AccessibleAdapter
        {
            final /* synthetic */ Consumer val$c;
            final /* synthetic */ AccessibleListener this$0;
            
            lllll(final AccessibleListener this$0, final Consumer val$c) {
                this.this$0 = this$0;
                this.val$c = val$c;
            }
            
            public void getDescription(final AccessibleEvent e) {
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
        //     6: invokespecial   org/eclipse/swt/accessibility/lllll.<init>:(Lorg/eclipse/swt/accessibility/AccessibleListener;Ljava/util/function/Consumer;)V
        //     9: areturn        
        //    Signature:
        //  (Ljava/util/function/Consumer<Lorg/eclipse/swt/accessibility/AccessibleEvent;>;)Lorg/eclipse/swt/accessibility/AccessibleListener;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
