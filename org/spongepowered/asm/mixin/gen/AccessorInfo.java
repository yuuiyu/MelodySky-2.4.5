//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.mixin.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.transformer.*;
import java.lang.annotation.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.gen.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.*;
import org.apache.logging.log4j.*;
import java.util.regex.*;
import java.util.*;
import com.google.common.collect.*;

public class AccessorInfo extends SpecialMethodInfo
{
    protected static final Pattern PATTERN_ACCESSOR;
    protected final Type[] argTypes;
    protected final Type returnType;
    protected final AccessorType type;
    private final Type targetFieldType;
    protected final MemberInfo target;
    protected FieldNode targetField;
    protected MethodNode targetMethod;
    
    public AccessorInfo(final MixinTargetContext mixin, final MethodNode method) {
        this(mixin, method, (Class<? extends Annotation>)Accessor.class);
    }
    
    protected AccessorInfo(final MixinTargetContext mixin, final MethodNode method, final Class<? extends Annotation> annotationClass) {
        super(mixin, method, ASMHelper.getVisibleAnnotation(method, annotationClass));
        this.argTypes = Type.getArgumentTypes(method.desc);
        this.returnType = Type.getReturnType(method.desc);
        this.type = this.initType();
        this.targetFieldType = this.initTargetFieldType();
        this.target = this.initTarget();
    }
    
    protected AccessorType initType() {
        if (this.returnType.equals((Object)Type.VOID_TYPE)) {
            return AccessorType.FIELD_SETTER;
        }
        return AccessorType.FIELD_GETTER;
    }
    
    protected Type initTargetFieldType() {
        switch (this.type) {
            case FIELD_GETTER: {
                if (this.argTypes.length > 0) {
                    throw new InvalidAccessorException(this.mixin, this + " must take exactly 0 arguments, found " + this.argTypes.length);
                }
                return this.returnType;
            }
            case FIELD_SETTER: {
                if (this.argTypes.length != 1) {
                    throw new InvalidAccessorException(this.mixin, this + " must take exactly 1 argument, found " + this.argTypes.length);
                }
                return this.argTypes[0];
            }
            default: {
                throw new InvalidAccessorException(this.mixin, "Computed unsupported accessor type " + this.type + " for " + this);
            }
        }
    }
    
    protected MemberInfo initTarget() {
        final MemberInfo target = new MemberInfo(this.getTargetName(), null, this.targetFieldType.getDescriptor());
        this.annotation.visit("target", (Object)target.toString());
        return target;
    }
    
    protected String getTargetName() {
        final String name = ASMHelper.getAnnotationValue(this.annotation);
        if (!Strings.isNullOrEmpty(name)) {
            return MemberInfo.parse(name, this.mixin).name;
        }
        final String inflectedTarget = this.inflectTarget();
        if (inflectedTarget == null) {
            throw new InvalidAccessorException(this.mixin, "Failed to inflect target name for " + this + ", supported prefixes: [get, set, is]");
        }
        return inflectedTarget;
    }
    
    protected String inflectTarget() {
        return inflectTarget(this.method.name, this.type, this.toString(), this.mixin, this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE));
    }
    
    public static String inflectTarget(final String accessorName, final AccessorType accessorType, final String accessorDescription, final IReferenceMapperContext context, final boolean verbose) {
        final Matcher nameMatcher = AccessorInfo.PATTERN_ACCESSOR.matcher(accessorName);
        if (nameMatcher.matches()) {
            final String prefix = nameMatcher.group(1);
            final String firstChar = nameMatcher.group(3);
            final String remainder = nameMatcher.group(4);
            final String name = String.format("%s%s", toLowerCase(firstChar, !isUpperCase(remainder)), remainder);
            if (!accessorType.isExpectedPrefix(prefix) && verbose) {
                LogManager.getLogger("mixin").warn("Unexpected prefix for {}, found [{}] expecting {}", new Object[] { accessorDescription, prefix, accessorType.getExpectedPrefixes() });
            }
            return MemberInfo.parse(name, context).name;
        }
        return null;
    }
    
    public final MemberInfo getTarget() {
        return this.target;
    }
    
    public final Type getTargetFieldType() {
        return this.targetFieldType;
    }
    
    public final FieldNode getTargetField() {
        return this.targetField;
    }
    
    public final MethodNode getTargetMethod() {
        return this.targetMethod;
    }
    
    public final Type getReturnType() {
        return this.returnType;
    }
    
    public final Type[] getArgTypes() {
        return this.argTypes;
    }
    
    @Override
    public String toString() {
        return String.format("%s->@%s[%s]::%s%s", this.mixin.toString(), ASMHelper.getSimpleName(this.annotation), this.type.toString(), this.method.name, this.method.desc);
    }
    
    public void locate() {
        this.targetField = this.findTargetField();
    }
    
    public MethodNode generate() {
        return this.type.getGenerator(this).generate();
    }
    
    private FieldNode findTargetField() {
        return this.findTarget((List<FieldNode>)this.classNode.fields);
    }
    
    protected <TNode> TNode findTarget(final List<TNode> nodes) {
        TNode exactMatch = null;
        final List<TNode> candidates = new ArrayList<TNode>();
        for (final TNode node : nodes) {
            final String desc = getNodeDesc(node);
            if (desc != null) {
                if (!desc.equals(this.target.desc)) {
                    continue;
                }
                final String name = getNodeName(node);
                if (name == null) {
                    continue;
                }
                if (name.equals(this.target.name)) {
                    exactMatch = node;
                }
                if (!name.equalsIgnoreCase(this.target.name)) {
                    continue;
                }
                candidates.add(node);
            }
        }
        if (exactMatch != null) {
            if (candidates.size() > 1) {
                LogManager.getLogger("mixin").debug("{} found an exact match for {} but other candidates were found!", new Object[] { this, this.target });
            }
            return exactMatch;
        }
        if (candidates.size() == 1) {
            return candidates.get(0);
        }
        throw new InvalidAccessorException(this, "Multiple candidates were found matching " + this.target + " in " + this.classNode.name + " for " + this);
    }
    
    private static <TNode> String getNodeDesc(final TNode node) {
        return (node instanceof MethodNode) ? ((MethodNode)node).desc : ((node instanceof FieldNode) ? ((FieldNode)node).desc : null);
    }
    
    private static <TNode> String getNodeName(final TNode node) {
        return (node instanceof MethodNode) ? ((MethodNode)node).name : ((node instanceof FieldNode) ? ((FieldNode)node).name : null);
    }
    
    public static AccessorInfo of(final MixinTargetContext mixin, final MethodNode method, final Class<? extends Annotation> type) {
        if (type == Accessor.class) {
            return new AccessorInfo(mixin, method);
        }
        if (type == Invoker.class) {
            return new InvokerInfo(mixin, method);
        }
        throw new InvalidAccessorException(mixin, "Could not parse accessor for unknown type " + type.getName());
    }
    
    private static String toLowerCase(final String string, final boolean condition) {
        return condition ? string.toLowerCase() : string;
    }
    
    private static boolean isUpperCase(final String string) {
        return string.toUpperCase().equals(string);
    }
    
    static {
        PATTERN_ACCESSOR = Pattern.compile("^(get|set|is|invoke|call)(([A-Z])(.*))$");
    }
    
    public enum AccessorType
    {
        FIELD_GETTER("FIELD_GETTER", 0, (Set)ImmutableSet.of((Object)"get", (Object)"is")), 
        FIELD_SETTER("FIELD_SETTER", 1, (Set)ImmutableSet.of((Object)"set")), 
        METHOD_PROXY("METHOD_PROXY", 2, (Set)ImmutableSet.of((Object)"call", (Object)"invoke"));
        
        private final Set<String> expectedPrefixes;
        
        private AccessorType(final Set<String> expectedPrefixes) {
            this.expectedPrefixes = expectedPrefixes;
        }
        
        public boolean isExpectedPrefix(final String prefix) {
            return this.expectedPrefixes.contains(prefix);
        }
        
        public String getExpectedPrefixes() {
            return this.expectedPrefixes.toString();
        }
        
        abstract AccessorGenerator getGenerator(final AccessorInfo p0);
    }
}
