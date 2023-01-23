//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.struct.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import com.google.common.collect.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.mixin.injection.modify.*;
import org.spongepowered.asm.mixin.injection.points.*;
import com.google.common.base.*;
import java.lang.reflect.*;
import java.util.*;
import java.lang.annotation.*;

public abstract class InjectionPoint
{
    private static Map<String, Class<? extends InjectionPoint>> types;
    private final String slice;
    private final Selector selector;
    
    protected InjectionPoint() {
        this("", Selector.DEFAULT);
    }
    
    protected InjectionPoint(final InjectionPointData data) {
        this(data.getSlice(), data.getSelector());
    }
    
    public InjectionPoint(final String slice, final Selector selector) {
        this.slice = slice;
        this.selector = selector;
    }
    
    public String getSlice() {
        return this.slice;
    }
    
    public Selector getSelector() {
        return this.selector;
    }
    
    public abstract boolean find(final String p0, final InsnList p1, final Collection<AbstractInsnNode> p2);
    
    @Override
    public String toString() {
        return "InjectionPoint(" + this.getClass().getSimpleName() + ")";
    }
    
    protected static AbstractInsnNode nextNode(final InsnList insns, final AbstractInsnNode insn) {
        final int index = insns.indexOf(insn) + 1;
        if (index > 0 && index < insns.size()) {
            return insns.get(index);
        }
        return insn;
    }
    
    public static InjectionPoint and(final InjectionPoint... operands) {
        return new Intersection(operands);
    }
    
    public static InjectionPoint or(final InjectionPoint... operands) {
        return new Union(operands);
    }
    
    public static InjectionPoint after(final InjectionPoint point) {
        return new Shift(point, 1);
    }
    
    public static InjectionPoint before(final InjectionPoint point) {
        return new Shift(point, -1);
    }
    
    public static InjectionPoint shift(final InjectionPoint point, final int count) {
        return new Shift(point, count);
    }
    
    public static List<InjectionPoint> parse(final SpecialMethodInfo info, final List<AnnotationNode> ats) {
        return parse(info.getContext(), info.getMethod(), info.getAnnotation(), ats);
    }
    
    public static List<InjectionPoint> parse(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode parent, final List<AnnotationNode> ats) {
        final ImmutableList.Builder<InjectionPoint> injectionPoints = (ImmutableList.Builder<InjectionPoint>)ImmutableList.builder();
        for (final AnnotationNode at : ats) {
            final InjectionPoint injectionPoint = parse(mixin, method, parent, at);
            if (injectionPoint != null) {
                injectionPoints.add((Object)injectionPoint);
            }
        }
        return (List<InjectionPoint>)injectionPoints.build();
    }
    
    public static InjectionPoint parse(final SpecialMethodInfo info, final At at) {
        return parse(info.getContext(), info.getMethod(), info.getAnnotation(), at.value(), at.shift(), at.by(), Arrays.asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode());
    }
    
    public static InjectionPoint parse(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode parent, final At at) {
        return parse(mixin, method, parent, at.value(), at.shift(), at.by(), Arrays.asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode());
    }
    
    public static InjectionPoint parse(final SpecialMethodInfo info, final AnnotationNode node) {
        return parse(info.getContext(), info.getMethod(), info.getAnnotation(), node);
    }
    
    public static InjectionPoint parse(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode parent, final AnnotationNode node) {
        final String at = ASMHelper.getAnnotationValue(node, "value");
        List<String> args = ASMHelper.getAnnotationValue(node, "args");
        final String target = ASMHelper.getAnnotationValue(node, "target", "");
        final String slice = ASMHelper.getAnnotationValue(node, "slice", "");
        final At.Shift shift = ASMHelper.getAnnotationValue(node, "shift", At.Shift.class, At.Shift.NONE);
        final int by = ASMHelper.getAnnotationValue(node, "by", 0);
        final int ordinal = ASMHelper.getAnnotationValue(node, "ordinal", -1);
        final int opcode = ASMHelper.getAnnotationValue(node, "opcode", 0);
        if (args == null) {
            args = (List<String>)ImmutableList.of();
        }
        return parse(mixin, method, parent, at, shift, by, args, target, slice, ordinal, opcode);
    }
    
    public static InjectionPoint parse(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode parent, final String at, final At.Shift shift, final int by, final List<String> args, final String target, final String slice, final int ordinal, final int opcode) {
        final InjectionPointData data = new InjectionPointData(mixin, method, parent, at, args, target, slice, ordinal, opcode);
        final Class<? extends InjectionPoint> ipClass = findClass(mixin, data);
        final InjectionPoint point = create(mixin, data, ipClass);
        return shift(point, shift, by);
    }
    
    private static Class<? extends InjectionPoint> findClass(final MixinTargetContext mixin, final InjectionPointData data) {
        final String type = data.getType();
        Class<? extends InjectionPoint> ipClass = InjectionPoint.types.get(type);
        if (ipClass == null) {
            if (type.matches("^([A-Za-z_][A-Za-z0-9_]*\\.)+[A-Za-z_][A-Za-z0-9_]*$")) {
                try {
                    ipClass = (Class<? extends InjectionPoint>)Class.forName(type);
                    InjectionPoint.types.put(type, ipClass);
                    return ipClass;
                }
                catch (Exception ex) {
                    throw new InvalidInjectionException(mixin, data + " could not be loaded or is not a valid InjectionPoint", ex);
                }
            }
            throw new InvalidInjectionException(mixin, data + " is not a valid injection point specifier");
        }
        return ipClass;
    }
    
    private static InjectionPoint create(final MixinTargetContext mixin, final InjectionPointData data, final Class<? extends InjectionPoint> ipClass) {
        Constructor<? extends InjectionPoint> ipCtor = null;
        try {
            ipCtor = ipClass.getDeclaredConstructor(InjectionPointData.class);
            ipCtor.setAccessible(true);
        }
        catch (NoSuchMethodException ex) {
            throw new InvalidInjectionException(mixin, ipClass.getName() + " must contain a constructor which accepts an InjectionPointData", ex);
        }
        InjectionPoint point = null;
        try {
            point = (InjectionPoint)ipCtor.newInstance(data);
        }
        catch (Exception ex2) {
            throw new InvalidInjectionException(mixin, "Error whilst instancing injection point " + ipClass.getName() + " for " + data.getAt(), ex2);
        }
        return point;
    }
    
    private static InjectionPoint shift(final InjectionPoint point, final At.Shift shift, final int by) {
        if (point != null) {
            if (shift == At.Shift.BEFORE) {
                return before(point);
            }
            if (shift == At.Shift.AFTER) {
                return after(point);
            }
            if (shift == At.Shift.BY) {
                return shift(point, by);
            }
        }
        return point;
    }
    
    public static void register(final Class<? extends InjectionPoint> type) {
        final AtCode code = type.getAnnotation(AtCode.class);
        if (code == null) {
            throw new IllegalArgumentException("Injection point class " + type + " is not annotated with @AtCode");
        }
        final Class<? extends InjectionPoint> existing = InjectionPoint.types.get(code.value());
        if (existing != null && !existing.equals(type)) {
            LogManager.getLogger("mixin").debug("Overriding InjectionPoint {} with {} (previously {})", new Object[] { code.value(), type.getName(), existing.getName() });
        }
        InjectionPoint.types.put(code.value(), type);
    }
    
    static {
        InjectionPoint.types = new HashMap<String, Class<? extends InjectionPoint>>();
        register(BeforeFieldAccess.class);
        register(BeforeInvoke.class);
        register(BeforeNew.class);
        register(BeforeReturn.class);
        register(BeforeStringInvoke.class);
        register(JumpInsnPoint.class);
        register(MethodHead.class);
        register(AfterInvoke.class);
        register(BeforeLoadLocal.class);
        register(AfterStoreLocal.class);
        register(BeforeFinalReturn.class);
    }
    
    public enum Selector
    {
        FIRST, 
        LAST, 
        ONE;
        
        public static final Selector DEFAULT;
        
        static {
            DEFAULT = Selector.FIRST;
        }
    }
    
    abstract static class CompositeInjectionPoint extends InjectionPoint
    {
        protected final InjectionPoint[] components;
        
        protected CompositeInjectionPoint(final InjectionPoint... components) {
            if (components == null || components.length < 2) {
                throw new IllegalArgumentException("Must supply two or more component injection points for composite point!");
            }
            this.components = components;
        }
        
        @Override
        public String toString() {
            return "CompositeInjectionPoint(" + this.getClass().getSimpleName() + ")[" + Joiner.on(',').join((Object[])this.components) + "]";
        }
    }
    
    static final class Intersection extends CompositeInjectionPoint
    {
        public Intersection(final InjectionPoint... points) {
            super(points);
        }
        
        @Override
        public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
            boolean found = false;
            final ArrayList<AbstractInsnNode>[] allNodes = (ArrayList<AbstractInsnNode>[])Array.newInstance(ArrayList.class, this.components.length);
            for (int i = 0; i < this.components.length; ++i) {
                allNodes[i] = new ArrayList<AbstractInsnNode>();
                this.components[i].find(desc, insns, allNodes[i]);
            }
            final ArrayList<AbstractInsnNode> alpha = allNodes[0];
            for (int nodeIndex = 0; nodeIndex < alpha.size(); ++nodeIndex) {
                final AbstractInsnNode node = alpha.get(nodeIndex);
                final boolean in = true;
                for (int b = 1; b < allNodes.length && allNodes[b].contains(node); ++b) {}
                if (in) {
                    nodes.add(node);
                    found = true;
                }
            }
            return found;
        }
    }
    
    static final class Union extends CompositeInjectionPoint
    {
        public Union(final InjectionPoint... points) {
            super(points);
        }
        
        @Override
        public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
            final LinkedHashSet<AbstractInsnNode> allNodes = new LinkedHashSet<AbstractInsnNode>();
            for (int i = 0; i < this.components.length; ++i) {
                this.components[i].find(desc, insns, allNodes);
            }
            nodes.addAll(allNodes);
            return allNodes.size() > 0;
        }
    }
    
    static final class Shift extends InjectionPoint
    {
        private final InjectionPoint input;
        private final int shift;
        
        public Shift(final InjectionPoint input, final int shift) {
            if (input == null) {
                throw new IllegalArgumentException("Must supply an input injection point for SHIFT");
            }
            this.input = input;
            this.shift = shift;
        }
        
        @Override
        public String toString() {
            return "InjectionPoint(" + this.getClass().getSimpleName() + ")[" + this.input + "]";
        }
        
        @Override
        public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
            final List<AbstractInsnNode> list = (nodes instanceof List) ? ((List)nodes) : new ArrayList<AbstractInsnNode>(nodes);
            this.input.find(desc, insns, nodes);
            for (int i = 0; i < list.size(); ++i) {
                list.set(i, insns.get(insns.indexOf((AbstractInsnNode)list.get(i)) + this.shift));
            }
            if (nodes != list) {
                nodes.clear();
                nodes.addAll(list);
            }
            return nodes.size() > 0;
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public @interface AtCode {
        String value();
    }
}
