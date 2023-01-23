//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.*;
import java.util.regex.*;
import java.util.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.modify.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import com.google.common.base.*;

public class InjectionPointData
{
    private static final Pattern AT_PATTERN;
    private final Map<String, String> args;
    private final MixinTargetContext mixin;
    private final MethodNode method;
    private final AnnotationNode parent;
    private final String at;
    private final String type;
    private final InjectionPoint.Selector selector;
    private final String target;
    private final String slice;
    private final int ordinal;
    private final int opcode;
    
    public InjectionPointData(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode parent, final String at, final List<String> args, final String target, final String slice, final int ordinal, final int opcode) {
        this.args = new HashMap<String, String>();
        this.mixin = mixin;
        this.method = method;
        this.parent = parent;
        this.at = at;
        this.target = target;
        this.slice = Strings.nullToEmpty(slice);
        this.ordinal = Math.max(-1, ordinal);
        this.opcode = opcode;
        this.parseArgs(args);
        this.args.put("target", target);
        this.args.put("ordinal", String.valueOf(ordinal));
        this.args.put("opcode", String.valueOf(opcode));
        final Matcher matcher = InjectionPointData.AT_PATTERN.matcher(at);
        this.type = parseType(matcher, at);
        this.selector = parseSelector(matcher);
    }
    
    private void parseArgs(final List<String> args) {
        if (args == null) {
            return;
        }
        for (final String arg : args) {
            if (arg != null) {
                final int eqPos = arg.indexOf(61);
                if (eqPos > -1) {
                    this.args.put(arg.substring(0, eqPos), arg.substring(eqPos + 1));
                }
                else {
                    this.args.put(arg, "");
                }
            }
        }
    }
    
    public String getAt() {
        return this.at;
    }
    
    public String getType() {
        return this.type;
    }
    
    public InjectionPoint.Selector getSelector() {
        return this.selector;
    }
    
    public MixinTargetContext getMixin() {
        return this.mixin;
    }
    
    public MethodNode getMethod() {
        return this.method;
    }
    
    public AnnotationNode getParent() {
        return this.parent;
    }
    
    public String getSlice() {
        return this.slice;
    }
    
    public Type getReturnType() {
        return Type.getReturnType(this.method.desc);
    }
    
    public LocalVariableDiscriminator getLocalVariableDiscriminator() {
        return LocalVariableDiscriminator.parse(this.parent);
    }
    
    public String get(final String key, final String defaultValue) {
        final String value = this.args.get(key);
        return (value != null) ? value : defaultValue;
    }
    
    public int get(final String key, final int defaultValue) {
        return this.parseInt(this.get(key, String.valueOf(defaultValue)), defaultValue);
    }
    
    public boolean get(final String key, final boolean defaultValue) {
        return this.parseBoolean(this.get(key, String.valueOf(defaultValue)), defaultValue);
    }
    
    public MemberInfo get(final String key) {
        try {
            return MemberInfo.parseAndValidate(this.get(key, ""), this.mixin);
        }
        catch (InvalidMemberDescriptorException ex) {
            throw new InvalidInjectionPointException(this.mixin, "Failed parsing @At(\"%s\").%s descriptor \"%s\" on %s", new Object[] { this.at, key, this.target, InjectionInfo.describeInjector(this.mixin, this.parent, this.method) });
        }
    }
    
    private int parseInt(final String string, final int defaultValue) {
        try {
            return Integer.parseInt(string);
        }
        catch (Exception ex) {
            return defaultValue;
        }
    }
    
    private boolean parseBoolean(final String string, final boolean defaultValue) {
        try {
            return Boolean.parseBoolean(string);
        }
        catch (Exception ex) {
            return defaultValue;
        }
    }
    
    public MemberInfo getTarget() {
        try {
            return MemberInfo.parseAndValidate(this.target, this.mixin);
        }
        catch (InvalidMemberDescriptorException ex) {
            throw new InvalidInjectionPointException(this.mixin, "Failed parsing @At(\"%s\") descriptor \"%s\" on %s", new Object[] { this.at, this.target, InjectionInfo.describeInjector(this.mixin, this.parent, this.method) });
        }
    }
    
    public int getOrdinal() {
        return this.ordinal;
    }
    
    public int getOpcode() {
        return this.opcode;
    }
    
    public int getOpcode(final int defaultOpcode) {
        return (this.opcode > 0) ? this.opcode : defaultOpcode;
    }
    
    public int getOpcode(final int defaultOpcode, final int... validOpcodes) {
        for (final int validOpcode : validOpcodes) {
            if (this.opcode == validOpcode) {
                return this.opcode;
            }
        }
        return defaultOpcode;
    }
    
    @Override
    public String toString() {
        return this.type;
    }
    
    private static Pattern createPattern() {
        return Pattern.compile(String.format("^([^:]+):?(%s)?$", Joiner.on('|').join((Object[])InjectionPoint.Selector.values())));
    }
    
    private static String parseType(final Matcher matcher, final String at) {
        return matcher.matches() ? matcher.group(1) : at;
    }
    
    private static InjectionPoint.Selector parseSelector(final Matcher matcher) {
        return (matcher.matches() && matcher.group(2) != null) ? InjectionPoint.Selector.valueOf(matcher.group(2)) : InjectionPoint.Selector.DEFAULT;
    }
    
    public static String parseType(final String at) {
        final Matcher matcher = InjectionPointData.AT_PATTERN.matcher(at);
        return parseType(matcher, at);
    }
    
    static {
        AT_PATTERN = createPattern();
    }
}
