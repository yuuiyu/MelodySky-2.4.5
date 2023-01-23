//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod.transformer;

import org.objectweb.asm.*;
import java.util.*;

class lI extends FieldVisitor
{
    final /* synthetic */ String val$name1;
    final /* synthetic */ FoamySideTransformer.SideCapturingClassVisitor this$0;
    
    lI(final FoamySideTransformer.SideCapturingClassVisitor this$0, final int x0, final FieldVisitor x1, final String val$name1) {
        this.this$0 = this$0;
        this.val$name1 = val$name1;
        super(x0, x1);
    }
    
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        if (desc.equals(FoamySideTransformer.SIDEONLY_DESCRIPTOR)) {
            return (AnnotationVisitor)new FoamySideTransformer.SideCapturingAnnotationVisitor(this.api, (Collection)this.this$0.removableFields, this.val$name1);
        }
        return null;
    }
}
