//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.code;

import org.spongepowered.asm.mixin.injection.struct.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.struct.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;

public class MethodSlice
{
    private final InjectionInfo info;
    private final String id;
    private final InjectionPoint from;
    private final InjectionPoint to;
    private final String name;
    
    private MethodSlice(final InjectionInfo info, final String id, final InjectionPoint from, final InjectionPoint to) {
        if (from == null && to == null) {
            throw new InvalidSliceException(info, String.format("%s is redundant. No 'from' or 'to' value specified", this));
        }
        this.info = info;
        this.id = Strings.nullToEmpty(id);
        this.from = from;
        this.to = to;
        this.name = getSliceName(id);
    }
    
    public String getId() {
        return this.id;
    }
    
    public ReadOnlyInsnList getSlice(final MethodNode method) {
        final int max = method.instructions.size() - 1;
        final int start = this.find(method, this.from, 0, this.name + "(from)");
        final int end = this.find(method, this.to, max, this.name + "(to)");
        if (start > end) {
            throw new InvalidSliceException(this.info, String.format("%s is negative size. Range(%d -> %d)", this.describe(), start, end));
        }
        if (start < 0 || end < 0 || start > max || end > max) {
            throw new InjectionError("Unexpected critical error in " + this + ": out of bounds start=" + start + " end=" + end + " lim=" + max);
        }
        if (start == 0 && end == max) {
            return new ReadOnlyInsnList(method.instructions);
        }
        return new InsnListSlice(method.instructions, start, end);
    }
    
    private int find(final MethodNode method, final InjectionPoint injectionPoint, final int defaultValue, final String description) {
        if (injectionPoint == null) {
            return defaultValue;
        }
        final Deque<AbstractInsnNode> nodes = new LinkedList<AbstractInsnNode>();
        final ReadOnlyInsnList insns = new ReadOnlyInsnList(method.instructions);
        final boolean result = injectionPoint.find(method.desc, insns, nodes);
        final InjectionPoint.Selector select = injectionPoint.getSelector();
        if (nodes.size() != 1 && select == InjectionPoint.Selector.ONE) {
            throw new InvalidSliceException(this.info, String.format("%s requires 1 result but found %d", this.describe(description), nodes.size()));
        }
        if (!result) {
            return defaultValue;
        }
        return method.instructions.indexOf((select == InjectionPoint.Selector.FIRST) ? nodes.getFirst() : nodes.getLast());
    }
    
    @Override
    public String toString() {
        return this.describe();
    }
    
    private String describe() {
        return this.describe(this.name);
    }
    
    private String describe(final String description) {
        return describeSlice(description, this.info);
    }
    
    private static String describeSlice(final String description, final InjectionInfo info) {
        final String annotation = ASMHelper.getSimpleName(info.getAnnotation());
        final MethodNode method = info.getMethod();
        return String.format("%s->%s(%s)::%s%s", info.getContext(), annotation, description, method.name, method.desc);
    }
    
    private static String getSliceName(final String id) {
        return String.format("@Slice[%s]", Strings.nullToEmpty(id));
    }
    
    public static MethodSlice parse(final InjectionInfo info, final Slice slice) {
        final String id = slice.id();
        final At from = slice.from();
        final At to = slice.to();
        final InjectionPoint fromPoint = (from != null) ? InjectionPoint.parse(info, from) : null;
        final InjectionPoint toPoint = (to != null) ? InjectionPoint.parse(info, to) : null;
        return new MethodSlice(info, id, fromPoint, toPoint);
    }
    
    public static MethodSlice parse(final InjectionInfo info, final AnnotationNode node) {
        final String id = ASMHelper.getAnnotationValue(node, "id");
        final AnnotationNode from = ASMHelper.getAnnotationValue(node, "from");
        final AnnotationNode to = ASMHelper.getAnnotationValue(node, "to");
        final InjectionPoint fromPoint = (from != null) ? InjectionPoint.parse(info, from) : null;
        final InjectionPoint toPoint = (to != null) ? InjectionPoint.parse(info, to) : null;
        return new MethodSlice(info, id, fromPoint, toPoint);
    }
    
    static final class InsnListSlice extends ReadOnlyInsnList
    {
        private final int start;
        private final int end;
        
        protected InsnListSlice(final InsnList inner, final int start, final int end) {
            super(inner);
            this.start = start;
            this.end = end;
        }
        
        @Override
        public ListIterator<AbstractInsnNode> iterator() {
            return this.iterator(0);
        }
        
        @Override
        public ListIterator<AbstractInsnNode> iterator(final int index) {
            return new SliceIterator(super.iterator(this.start + index), this.start, this.end, this.start + index);
        }
        
        @Override
        public AbstractInsnNode[] toArray() {
            final AbstractInsnNode[] all = super.toArray();
            final AbstractInsnNode[] subset = new AbstractInsnNode[this.size()];
            System.arraycopy(all, this.start, subset, 0, subset.length);
            return subset;
        }
        
        @Override
        public int size() {
            return this.end - this.start + 1;
        }
        
        @Override
        public AbstractInsnNode getFirst() {
            return super.get(this.start);
        }
        
        @Override
        public AbstractInsnNode getLast() {
            return super.get(this.end);
        }
        
        @Override
        public AbstractInsnNode get(final int index) {
            return super.get(this.start + index);
        }
        
        @Override
        public boolean contains(final AbstractInsnNode insn) {
            for (final AbstractInsnNode node : this.toArray()) {
                if (node == insn) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public int indexOf(final AbstractInsnNode insn) {
            final int index = super.indexOf(insn);
            return (index >= this.start && index <= this.end) ? (index - this.start) : -1;
        }
        
        public int realIndexOf(final AbstractInsnNode insn) {
            return super.indexOf(insn);
        }
        
        static class SliceIterator implements ListIterator<AbstractInsnNode>
        {
            private final ListIterator<AbstractInsnNode> iter;
            private int start;
            private int end;
            private int index;
            
            public SliceIterator(final ListIterator<AbstractInsnNode> iter, final int start, final int end, final int index) {
                this.iter = iter;
                this.start = start;
                this.end = end;
                this.index = index;
            }
            
            @Override
            public boolean hasNext() {
                return this.index <= this.end && this.iter.hasNext();
            }
            
            @Override
            public AbstractInsnNode next() {
                if (this.index > this.end) {
                    throw new NoSuchElementException();
                }
                ++this.index;
                return this.iter.next();
            }
            
            @Override
            public boolean hasPrevious() {
                return this.index > this.start;
            }
            
            @Override
            public AbstractInsnNode previous() {
                if (this.index <= this.start) {
                    throw new NoSuchElementException();
                }
                --this.index;
                return this.iter.previous();
            }
            
            @Override
            public int nextIndex() {
                return this.index - this.start;
            }
            
            @Override
            public int previousIndex() {
                return this.index - this.start - 1;
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove insn from slice");
            }
            
            @Override
            public void set(final AbstractInsnNode e) {
                throw new UnsupportedOperationException("Cannot set insn using slice");
            }
            
            @Override
            public void add(final AbstractInsnNode e) {
                throw new UnsupportedOperationException("Cannot add insn using slice");
            }
        }
    }
}
