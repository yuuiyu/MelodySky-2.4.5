//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.invoke.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class ModifyConstantInjectionInfo extends InjectionInfo
{
    public ModifyConstantInjectionInfo(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode annotation) {
        super(mixin, method, annotation);
    }
    
    protected List<AnnotationNode> readInjectionPoints(final String type) {
        final AnnotationNode constantAnnotation = ASMHelper.getAnnotationValue(this.annotation, "constant");
        final List<AnnotationNode> ats = new ArrayList<AnnotationNode>();
        ats.add(constantAnnotation);
        return ats;
    }
    
    protected void parseInjectionPoints(final List<AnnotationNode> ats) {
        final Type returnType = Type.getReturnType(this.method.desc);
        for (final AnnotationNode at : ats) {
            this.injectionPoints.add(new BeforeConstant(this, at, returnType.getDescriptor()));
        }
    }
    
    protected Injector parseInjector(final AnnotationNode injectAnnotation) {
        return (Injector)new ModifyConstantInjector((InjectionInfo)this);
    }
    
    protected String getDescription() {
        return "Constant modifier method";
    }
    
    static class BeforeConstant extends InjectionPoint
    {
        private static final Logger logger;
        private final int ordinal;
        private final boolean nullValue;
        private final Integer intValue;
        private final Float floatValue;
        private final Long longValue;
        private final Double doubleValue;
        private final String stringValue;
        private final Type typeValue;
        private final String matchByType;
        private final boolean log;
        
        public BeforeConstant(final InjectionInfo info, final AnnotationNode node, String returnType) {
            final Boolean empty = ASMHelper.getAnnotationValue(node, "nullValue", (Boolean)null);
            this.ordinal = ASMHelper.getAnnotationValue(node, "ordinal", -1);
            this.nullValue = (empty != null && empty);
            this.intValue = ASMHelper.getAnnotationValue(node, "intValue", (Integer)null);
            this.floatValue = ASMHelper.getAnnotationValue(node, "floatValue", (Float)null);
            this.longValue = ASMHelper.getAnnotationValue(node, "longValue", (Long)null);
            this.doubleValue = ASMHelper.getAnnotationValue(node, "doubleValue", (Double)null);
            this.stringValue = ASMHelper.getAnnotationValue(node, "stringValue", (String)null);
            this.typeValue = ASMHelper.getAnnotationValue(node, "classValue", (Type)null);
            final int c = count(empty, this.intValue, this.floatValue, this.longValue, this.doubleValue, this.stringValue, this.typeValue);
            if (c == 1) {
                returnType = null;
            }
            else if (c > 1) {
                throw new InvalidInjectionException(info, "Conflicting constant discriminators specified on @Constant annotation for " + info);
            }
            this.matchByType = returnType;
            this.log = ASMHelper.getAnnotationValue(node, "log", Boolean.FALSE);
        }
        
        public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
            boolean found = false;
            if (this.log) {
                BeforeConstant.logger.info("BeforeConstant is searching for an constants in method with descriptor {}", new Object[] { desc });
            }
            final ListIterator<AbstractInsnNode> iter = (ListIterator<AbstractInsnNode>)insns.iterator();
            int ordinal = 0;
            while (iter.hasNext()) {
                final AbstractInsnNode insn = iter.next();
                final boolean matchesInsn = this.matchesInsn(insn);
                if (matchesInsn) {
                    if (this.log) {
                        final String byType = (this.matchByType != null) ? " TYPE" : " value";
                        BeforeConstant.logger.info("    BeforeConstant found a matching constant{} at ordinal {}", new Object[] { byType, ordinal });
                    }
                    if (this.ordinal == -1 || this.ordinal == ordinal) {
                        if (this.log) {
                            BeforeConstant.logger.info("      BeforeConstant found {}", new Object[] { ASMHelper.getNodeDescriptionForDebug(insn).trim() });
                        }
                        nodes.add(insn);
                        found = true;
                    }
                    ++ordinal;
                }
            }
            return found;
        }
        
        private boolean matchesInsn(final AbstractInsnNode insn) {
            if (!ASMHelper.isConstant(insn)) {
                return false;
            }
            final Object value = ASMHelper.getConstant(insn);
            if (value == null) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found NULL constant: nullValue = {}", new Object[] { this.nullValue });
                }
                return this.nullValue || "Ljava/lang/Object;".equals(this.matchByType);
            }
            if (value instanceof Integer) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found INTEGER constant: value = {}, intValue = {}", new Object[] { value, this.intValue });
                }
                return value.equals(this.intValue) || "I".equals(this.matchByType);
            }
            if (value instanceof Float) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found FLOAT constant: value = {}, floatValue = {}", new Object[] { value, this.floatValue });
                }
                return value.equals(this.floatValue) || "F".equals(this.matchByType);
            }
            if (value instanceof Long) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found LONG constant: value = {}, longValue = {}", new Object[] { value, this.longValue });
                }
                return value.equals(this.longValue) || "J".equals(this.matchByType);
            }
            if (value instanceof Double) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found DOUBLE constant: value = {}, doubleValue = {}", new Object[] { value, this.doubleValue });
                }
                return value.equals(this.doubleValue) || "D".equals(this.matchByType);
            }
            if (value instanceof String) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found STRING constant: value = {}, stringValue = {}", new Object[] { value, this.stringValue });
                }
                return value.equals(this.stringValue) || "Ljava/lang/String;".equals(this.matchByType);
            }
            if (value instanceof Type) {
                if (this.log) {
                    BeforeConstant.logger.info("  BeforeConstant found CLASS constant: value = {}, typeValue = {}", new Object[] { value, this.typeValue });
                }
                return value.equals(this.typeValue) || "Ljava/lang/Class;".equals(this.matchByType);
            }
            return false;
        }
        
        private static int count(final Object... values) {
            int counter = 0;
            for (final Object value : values) {
                if (value != null) {
                    ++counter;
                }
            }
            return counter;
        }
        
        static {
            logger = LogManager.getLogger("mixin");
        }
    }
}
