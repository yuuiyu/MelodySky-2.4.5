//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.code;

import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.util.*;
import java.util.*;

public class MethodSlices
{
    private final InjectionInfo info;
    private final Map<String, MethodSlice> slices;
    
    private MethodSlices(final InjectionInfo info) {
        this.slices = new HashMap<String, MethodSlice>(4);
        this.info = info;
    }
    
    private void add(final MethodSlice slice) {
        final String id = this.info.getSliceId(slice.getId());
        if (this.slices.containsKey(id)) {
            throw new InvalidSliceException(this.info, slice + " has a duplicate id, '" + id + "' was already defined");
        }
        this.slices.put(id, slice);
    }
    
    public MethodSlice get(final String id) {
        return this.slices.get(id);
    }
    
    @Override
    public String toString() {
        return String.format("MethodSlices%s", this.slices.keySet());
    }
    
    public static MethodSlices parse(final InjectionInfo info) {
        final MethodSlices slices = new MethodSlices(info);
        final AnnotationNode annotation = info.getAnnotation();
        if (annotation != null) {
            final List<AnnotationNode> sliceNodes = new ArrayList<AnnotationNode>();
            final Object sliceValue = ASMHelper.getAnnotationValue(annotation, "slice");
            if (sliceValue instanceof List) {
                sliceNodes.addAll((Collection<? extends AnnotationNode>)sliceValue);
            }
            else if (sliceValue instanceof AnnotationNode) {
                sliceNodes.add((AnnotationNode)sliceValue);
            }
            for (final AnnotationNode node : sliceNodes) {
                final MethodSlice slice = MethodSlice.parse(info, node);
                slices.add(slice);
            }
        }
        return slices;
    }
}
