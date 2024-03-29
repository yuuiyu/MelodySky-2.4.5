//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.code;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.lib.tree.*;
import com.google.common.base.*;
import org.apache.logging.log4j.*;
import java.util.*;

public abstract class Injector
{
    protected static final Logger logger;
    protected InjectionInfo info;
    protected final ClassNode classNode;
    protected final MethodNode methodNode;
    protected final Type[] methodArgs;
    protected final Type returnType;
    protected final boolean isStatic;
    
    public Injector(final InjectionInfo info) {
        this(info.getClassNode(), info.getMethod());
        this.info = info;
    }
    
    private Injector(final ClassNode classNode, final MethodNode methodNode) {
        this.classNode = classNode;
        this.methodNode = methodNode;
        this.methodArgs = Type.getArgumentTypes(this.methodNode.desc);
        this.returnType = Type.getReturnType(this.methodNode.desc);
        this.isStatic = ASMHelper.methodIsStatic(this.methodNode);
    }
    
    @Override
    public String toString() {
        return String.format("%s::%s", this.classNode.name, this.methodNode.name);
    }
    
    public final List<InjectionNodes.InjectionNode> find(final InjectorTarget injectorTarget, final List<InjectionPoint> injectionPoints) {
        this.sanityCheck(injectorTarget.getTarget(), injectionPoints);
        final List<InjectionNodes.InjectionNode> myNodes = new ArrayList<InjectionNodes.InjectionNode>();
        for (final TargetNode node : this.findTargetNodes(injectorTarget, injectionPoints)) {
            this.addTargetNode(injectorTarget.getTarget(), myNodes, node.node, node.nominators);
        }
        return myNodes;
    }
    
    protected void addTargetNode(final Target target, final List<InjectionNodes.InjectionNode> myNodes, final AbstractInsnNode node, final Set<InjectionPoint> nominators) {
        myNodes.add(target.injectionNodes.add(node));
    }
    
    public final void inject(final Target target, final List<InjectionNodes.InjectionNode> nodes) {
        for (final InjectionNodes.InjectionNode node : nodes) {
            if (node.isRemoved()) {
                if (!this.info.getContext().getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                    continue;
                }
                Injector.logger.warn("Target node for {} was removed by a previous injector in {}", new Object[] { this.info, target });
            }
            else {
                this.inject(target, node);
            }
        }
        for (final InjectionNodes.InjectionNode node : nodes) {
            this.postInject(target, node);
        }
    }
    
    private Collection<TargetNode> findTargetNodes(final InjectorTarget injectorTarget, final List<InjectionPoint> injectionPoints) {
        final Map<AbstractInsnNode, TargetNode> targetNodes = new HashMap<AbstractInsnNode, TargetNode>();
        final Collection<AbstractInsnNode> nodes = new ArrayList<AbstractInsnNode>(32);
        for (final InjectionPoint injectionPoint : injectionPoints) {
            nodes.clear();
            if (this.findTargetNodes(injectorTarget.getMethod(), injectionPoint, injectorTarget.getSlice(injectionPoint), nodes)) {
                for (final AbstractInsnNode node : nodes) {
                    TargetNode targetNode = targetNodes.get(node);
                    if (targetNode == null) {
                        targetNode = new TargetNode(node);
                        targetNodes.put(node, targetNode);
                    }
                    targetNode.nominators.add(injectionPoint);
                }
            }
        }
        return targetNodes.values();
    }
    
    protected boolean findTargetNodes(final MethodNode into, final InjectionPoint injectionPoint, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        return injectionPoint.find(into.desc, insns, nodes);
    }
    
    protected void sanityCheck(final Target target, final List<InjectionPoint> injectionPoints) {
        if (target.classNode != this.classNode) {
            throw new InvalidInjectionException(this.info, "Target class does not match injector class in " + this);
        }
    }
    
    protected abstract void inject(final Target p0, final InjectionNodes.InjectionNode p1);
    
    protected void postInject(final Target target, final InjectionNodes.InjectionNode node) {
    }
    
    protected AbstractInsnNode invokeHandler(final InsnList insns) {
        return this.invokeHandler(insns, this.methodNode);
    }
    
    protected AbstractInsnNode invokeHandler(final InsnList insns, final MethodNode handler) {
        final boolean isPrivate = (handler.access & 0x2) != 0x0;
        final int invokeOpcode = this.isStatic ? 184 : (isPrivate ? 183 : 182);
        final MethodInsnNode insn = new MethodInsnNode(invokeOpcode, this.classNode.name, handler.name, handler.desc, false);
        insns.add((AbstractInsnNode)insn);
        this.info.addCallbackInvocation(handler);
        return (AbstractInsnNode)insn;
    }
    
    protected void throwException(final InsnList insns, final String exceptionType, final String message) {
        insns.add((AbstractInsnNode)new TypeInsnNode(187, exceptionType));
        insns.add((AbstractInsnNode)new InsnNode(89));
        insns.add((AbstractInsnNode)new LdcInsnNode((Object)message));
        insns.add((AbstractInsnNode)new MethodInsnNode(183, exceptionType, "<init>", "(Ljava/lang/String;)V", false));
        insns.add((AbstractInsnNode)new InsnNode(191));
    }
    
    protected static String printArgs(final Type[] args) {
        return "(" + Joiner.on("").join((Object[])args) + ")";
    }
    
    public static boolean canCoerce(final Type from, final Type to) {
        return canCoerce(from.getDescriptor(), to.getDescriptor());
    }
    
    public static boolean canCoerce(final String from, final String to) {
        return from.length() <= 1 && to.length() <= 1 && canCoerce(from.charAt(0), to.charAt(0));
    }
    
    public static boolean canCoerce(final char from, final char to) {
        return to == 'I' && "IBSCZ".indexOf(from) > -1;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
    
    class TargetNode
    {
        final AbstractInsnNode node;
        final Set<InjectionPoint> nominators;
        
        TargetNode(final AbstractInsnNode node) {
            this.nominators = new HashSet<InjectionPoint>();
            this.node = node;
        }
    }
}
