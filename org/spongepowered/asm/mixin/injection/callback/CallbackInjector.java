//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.callback;

import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.injection.points.*;
import java.lang.annotation.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.util.*;
import com.google.common.base.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.lib.tree.*;

public class CallbackInjector extends Injector
{
    private final boolean cancellable;
    private final LocalCapture localCapture;
    private final String identifier;
    
    public CallbackInjector(final InjectionInfo info, final boolean cancellable, final LocalCapture localCapture, final String identifier) {
        super(info);
        this.cancellable = cancellable;
        this.localCapture = localCapture;
        this.identifier = identifier;
    }
    
    @Override
    protected void sanityCheck(final Target target, final List<InjectionPoint> injectionPoints) {
        super.sanityCheck(target, injectionPoints);
        if (ASMHelper.methodIsStatic(target.method) != this.isStatic) {
            throw new InvalidInjectionException(this.info, "'static' modifier of callback method does not match target in " + this);
        }
        if ("<init>".equals(target.method.name)) {
            for (final InjectionPoint injectionPoint : injectionPoints) {
                if (!injectionPoint.getClass().equals(BeforeReturn.class)) {
                    throw new InvalidInjectionException(this.info, "Found injection point type " + injectionPoint.getClass().getSimpleName() + " targetting a ctor in " + this + ". Only RETURN allowed for a ctor target");
                }
            }
        }
    }
    
    @Override
    protected void inject(final Target target, final InjectionNodes.InjectionNode node) {
        LocalVariableNode[] locals = null;
        if (this.localCapture.isCaptureLocals() || this.localCapture.isPrintLocals()) {
            locals = Locals.getLocalsAt(this.classNode, target.method, node.getCurrentTarget());
        }
        this.inject(new Callback(this.methodNode, target, node, locals, this.localCapture.isCaptureLocals()));
    }
    
    private void inject(final Callback callback) {
        if (this.localCapture.isPrintLocals()) {
            this.printLocals(callback);
            this.info.addCallbackInvocation(this.methodNode);
            return;
        }
        MethodNode callbackMethod = this.methodNode;
        if (!callback.checkDescriptor(this.methodNode.desc)) {
            if (this.info.getTargets().size() > 1) {
                return;
            }
            if (callback.canCaptureLocals) {
                final MethodNode surrogateHandler = ASMHelper.findMethod(this.classNode, this.methodNode.name, callback.getDescriptor());
                if (surrogateHandler != null && ASMHelper.getVisibleAnnotation(surrogateHandler, Surrogate.class) != null) {
                    callbackMethod = surrogateHandler;
                }
                else {
                    final String message = this.generateBadLVTMessage(callback);
                    switch (this.localCapture) {
                        case CAPTURE_FAILEXCEPTION: {
                            Injector.logger.error("Injection error: {}", new Object[] { message });
                            callbackMethod = this.generateErrorMethod(callback, "org/spongepowered/asm/mixin/injection/InjectionError", message);
                            break;
                        }
                        case CAPTURE_FAILSOFT: {
                            Injector.logger.warn("Injection warning: {}", new Object[] { message });
                            return;
                        }
                        default: {
                            Injector.logger.error("Critical injection failure: {}", new Object[] { message });
                            throw new InjectionError(message);
                        }
                    }
                }
            }
            else {
                final String returnableSig = this.methodNode.desc.replace("Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;");
                if (callback.checkDescriptor(returnableSig)) {
                    throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! CallbackInfoReturnable is required!");
                }
                final MethodNode surrogateHandler2 = ASMHelper.findMethod(this.classNode, this.methodNode.name, callback.getDescriptor());
                if (surrogateHandler2 == null || ASMHelper.getVisibleAnnotation(surrogateHandler2, Surrogate.class) == null) {
                    throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! Expected " + callback.getDescriptor() + " but found " + this.methodNode.desc);
                }
                callbackMethod = surrogateHandler2;
            }
        }
        this.dupReturnValue(callback);
        if (this.cancellable) {
            this.createCallbackInfo(callback, true);
        }
        this.invokeCallback(callback, callbackMethod);
        this.injectCancellationCode(callback);
        callback.inject();
        this.info.notifyInjected(callback.target);
    }
    
    private String generateBadLVTMessage(final Callback callback) {
        final int position = callback.target.method.instructions.indexOf(callback.node);
        final List<String> expected = summariseLocals(this.methodNode.desc, callback.target.arguments.length + 1);
        final List<String> found = summariseLocals(callback.getDescriptorWithAllLocals(), callback.frameSize);
        final String message = String.format("LVT in %s has incompatible changes at opcode %d in callback %s.\nExpected: %s\n   Found: %s", callback.target, position, this, expected, found);
        return message;
    }
    
    private MethodNode generateErrorMethod(final Callback callback, final String errorClass, final String message) {
        final MethodNode method = this.info.addMethod(this.methodNode.access, this.methodNode.name + "$missing", callback.getDescriptor());
        method.maxLocals = ASMHelper.getFirstNonArgLocalIndex(Type.getArgumentTypes(callback.getDescriptor()), !this.isStatic);
        method.maxStack = 3;
        final InsnList insns = method.instructions;
        insns.add((AbstractInsnNode)new TypeInsnNode(187, errorClass));
        insns.add((AbstractInsnNode)new InsnNode(89));
        insns.add((AbstractInsnNode)new LdcInsnNode((Object)message));
        insns.add((AbstractInsnNode)new MethodInsnNode(183, errorClass, "<init>", "(Ljava/lang/String;)V", false));
        insns.add((AbstractInsnNode)new InsnNode(191));
        return method;
    }
    
    private void printLocals(final Callback callback) {
        final Type[] args = Type.getArgumentTypes(callback.getDescriptorWithAllLocals());
        final SignaturePrinter methodSig = new SignaturePrinter(callback.target.method, callback.argNames);
        final SignaturePrinter handlerSig = new SignaturePrinter(this.methodNode.name, callback.target.returnType, args, callback.argNames);
        handlerSig.setModifiers(this.methodNode);
        final PrettyPrinter printer = new PrettyPrinter();
        printer.kv("Target Class", (Object)this.classNode.name.replace('/', '.'));
        printer.kv("Target Method", methodSig);
        printer.kv("Target Max LOCALS", callback.target.getMaxLocals());
        printer.kv("Initial Frame Size", callback.frameSize);
        printer.kv("Callback Name", (Object)this.methodNode.name);
        printer.kv("Instruction", "%s %s", callback.node.getClass().getSimpleName(), ASMHelper.getOpcodeName(callback.node.getOpcode()));
        printer.hr();
        if (callback.locals.length > callback.frameSize) {
            printer.add("  %s  %20s  %s", "LOCAL", "TYPE", "NAME");
            for (int l = 0; l < callback.locals.length; ++l) {
                final String marker = (l == callback.frameSize) ? ">" : " ";
                if (callback.locals[l] != null) {
                    printer.add("%s [%3d]  %20s  %-50s %s", marker, l, SignaturePrinter.getTypeName(callback.localTypes[l], false), callback.locals[l].name, (l >= callback.frameSize) ? "<capture>" : "");
                }
                else {
                    final boolean isTop = l > 0 && callback.localTypes[l - 1] != null && callback.localTypes[l - 1].getSize() > 1;
                    printer.add("%s [%3d]  %20s", marker, l, isTop ? "<top>" : "-");
                }
            }
            printer.hr();
        }
        printer.add().add("/**").add(" * Expected callback signature").add(" * /");
        printer.add("%s {", handlerSig);
        printer.add("    // Method body").add("}").add().print(System.err);
    }
    
    private void createCallbackInfo(final Callback callback, final boolean store) {
        callback.add((AbstractInsnNode)new TypeInsnNode(187, callback.target.callbackInfoClass), true, !store);
        callback.add((AbstractInsnNode)new InsnNode(89), true, true);
        this.invokeCallbackInfoCtor(callback, store);
        if (store) {
            callback.add((AbstractInsnNode)new VarInsnNode(58, callback.marshallVar));
        }
    }
    
    private void loadOrCreateCallbackInfo(final Callback callback) {
        if (this.cancellable) {
            callback.add((AbstractInsnNode)new VarInsnNode(25, callback.marshallVar), false, true);
        }
        else {
            this.createCallbackInfo(callback, false);
        }
    }
    
    private void dupReturnValue(final Callback callback) {
        if (!callback.isAtReturn) {
            return;
        }
        callback.add((AbstractInsnNode)new InsnNode(89));
        callback.add((AbstractInsnNode)new VarInsnNode(callback.target.returnType.getOpcode(54), callback.marshallVar));
    }
    
    protected void invokeCallbackInfoCtor(final Callback callback, final boolean store) {
        callback.add((AbstractInsnNode)new LdcInsnNode((Object)this.getIdentifier(callback)), true, !store);
        callback.add((AbstractInsnNode)new InsnNode(this.cancellable ? 4 : 3), true, !store);
        if (callback.isAtReturn) {
            callback.add((AbstractInsnNode)new VarInsnNode(callback.target.returnType.getOpcode(21), callback.marshallVar), true, !store);
            callback.add((AbstractInsnNode)new MethodInsnNode(183, callback.target.callbackInfoClass, "<init>", CallbackInfo.getConstructorDescriptor(callback.target.returnType), false));
        }
        else {
            callback.add((AbstractInsnNode)new MethodInsnNode(183, callback.target.callbackInfoClass, "<init>", CallbackInfo.getConstructorDescriptor(), false));
        }
    }
    
    private void invokeCallback(final Callback callback, final MethodNode callbackMethod) {
        if (!this.isStatic) {
            callback.add((AbstractInsnNode)new VarInsnNode(25, 0), false, true);
        }
        if (callback.captureArgs()) {
            ASMHelper.loadArgs(callback.target.arguments, callback, this.isStatic ? 0 : 1);
        }
        this.loadOrCreateCallbackInfo(callback);
        if (callback.canCaptureLocals) {
            Locals.loadLocals(callback.localTypes, callback, callback.frameSize, callback.extraArgs);
        }
        this.invokeHandler(callback, callbackMethod);
    }
    
    private String getIdentifier(final Callback callback) {
        return Strings.isNullOrEmpty(this.identifier) ? callback.target.method.name : this.identifier;
    }
    
    protected void injectCancellationCode(final Callback callback) {
        if (!this.cancellable) {
            return;
        }
        callback.add((AbstractInsnNode)new VarInsnNode(25, callback.marshallVar));
        callback.add((AbstractInsnNode)new MethodInsnNode(182, callback.target.callbackInfoClass, CallbackInfo.getIsCancelledMethodName(), CallbackInfo.getIsCancelledMethodSig(), false));
        final LabelNode notCancelled = new LabelNode();
        callback.add((AbstractInsnNode)new JumpInsnNode(153, notCancelled));
        this.injectReturnCode(callback);
        callback.add((AbstractInsnNode)notCancelled);
    }
    
    protected void injectReturnCode(final Callback callback) {
        if (callback.target.returnType.equals((Object)Type.VOID_TYPE)) {
            callback.add((AbstractInsnNode)new InsnNode(177));
        }
        else {
            callback.add((AbstractInsnNode)new VarInsnNode(25, callback.marshallVar));
            final String accessor = CallbackInfoReturnable.getReturnAccessor(callback.target.returnType);
            final String descriptor = CallbackInfoReturnable.getReturnDescriptor(callback.target.returnType);
            callback.add((AbstractInsnNode)new MethodInsnNode(182, callback.target.callbackInfoClass, accessor, descriptor, false));
            if (callback.target.returnType.getSort() == 10) {
                callback.add((AbstractInsnNode)new TypeInsnNode(192, callback.target.returnType.getInternalName()));
            }
            callback.add((AbstractInsnNode)new InsnNode(callback.target.returnType.getOpcode(172)));
        }
    }
    
    protected boolean isStatic() {
        return this.isStatic;
    }
    
    private static List<String> summariseLocals(final String desc, final int pos) {
        return summariseLocals(Type.getArgumentTypes(desc), pos);
    }
    
    private static List<String> summariseLocals(final Type[] locals, int pos) {
        final List<String> list = new ArrayList<String>();
        if (locals != null) {
            while (pos < locals.length) {
                if (locals[pos] != null) {
                    list.add(locals[pos].toString());
                }
                ++pos;
            }
        }
        return list;
    }
    
    private class Callback extends InsnList
    {
        private final MethodNode handler;
        final Target target;
        final AbstractInsnNode node;
        final LocalVariableNode[] locals;
        final Type[] localTypes;
        final int frameSize;
        final int extraArgs;
        final boolean canCaptureLocals;
        final boolean isAtReturn;
        final String desc;
        final String descl;
        final String[] argNames;
        int ctor;
        int invoke;
        final int marshallVar;
        private boolean captureArgs;
        
        Callback(final MethodNode handler, final Target target, final InjectionNodes.InjectionNode node, final LocalVariableNode[] locals, final boolean captureLocals) {
            this.captureArgs = true;
            this.handler = handler;
            this.target = target;
            this.node = node.getCurrentTarget();
            this.locals = locals;
            this.localTypes = (Type[])((locals != null) ? new Type[locals.length] : null);
            this.frameSize = ASMHelper.getFirstNonArgLocalIndex(target.arguments, !CallbackInjector.this.isStatic());
            List<String> argNames = null;
            if (locals != null) {
                final int baseArgIndex = CallbackInjector.this.isStatic() ? 0 : 1;
                argNames = new ArrayList<String>();
                for (int l = 0; l <= locals.length; ++l) {
                    if (l == this.frameSize) {
                        argNames.add((target.returnType == Type.VOID_TYPE) ? "ci" : "cir");
                    }
                    if (l < locals.length && locals[l] != null) {
                        this.localTypes[l] = Type.getType(locals[l].desc);
                        if (l >= baseArgIndex) {
                            argNames.add(locals[l].name);
                        }
                    }
                }
            }
            this.extraArgs = Math.max(0, ASMHelper.getFirstNonArgLocalIndex(this.handler) - (this.frameSize + 1));
            this.argNames = (String[])((argNames != null) ? ((String[])argNames.toArray(new String[argNames.size()])) : null);
            this.canCaptureLocals = (captureLocals && locals != null && locals.length > this.frameSize);
            this.isAtReturn = (this.node instanceof InsnNode && this.isValueReturnOpcode(this.node.getOpcode()));
            this.desc = target.getCallbackDescriptor(this.localTypes, target.arguments);
            this.descl = target.getCallbackDescriptor(true, this.localTypes, target.arguments, this.frameSize, this.extraArgs);
            this.invoke = target.arguments.length + (this.canCaptureLocals ? (this.localTypes.length - this.frameSize) : 0);
            this.marshallVar = target.allocateLocal();
        }
        
        private boolean isValueReturnOpcode(final int opcode) {
            return opcode >= 172 && opcode < 177;
        }
        
        String getDescriptor() {
            return this.canCaptureLocals ? this.descl : this.desc;
        }
        
        String getDescriptorWithAllLocals() {
            return this.target.getCallbackDescriptor(true, this.localTypes, this.target.arguments, this.frameSize, 32767);
        }
        
        void add(final AbstractInsnNode insn, final boolean ctorStack, final boolean invokeStack) {
            this.add(insn);
            this.ctor += (ctorStack ? 1 : 0);
            this.invoke += (invokeStack ? 1 : 0);
        }
        
        void inject() {
            this.target.insns.insertBefore(this.node, (InsnList)this);
            this.target.addToStack(Math.max(this.invoke, this.ctor));
        }
        
        boolean checkDescriptor(final String desc) {
            if (this.getDescriptor().equals(desc)) {
                return true;
            }
            if (this.target.getSimpleCallbackDescriptor().equals(desc) && !this.canCaptureLocals) {
                this.captureArgs = false;
                return true;
            }
            if (this.extraArgs <= 0) {
                return false;
            }
            final Type[] inTypes = Type.getArgumentTypes(desc);
            final Type[] myTypes = Type.getArgumentTypes(this.descl);
            if (inTypes.length != myTypes.length) {
                return false;
            }
            for (int arg = this.frameSize + 1; arg < myTypes.length; ++arg) {
                final Type type = inTypes[arg];
                if (!type.equals((Object)myTypes[arg])) {
                    if (type.getSort() >= 9) {
                        return false;
                    }
                    final AnnotationNode coerce = ASMHelper.getInvisibleParameterAnnotation(this.handler, Coerce.class, arg);
                    if (coerce == null) {
                        return false;
                    }
                    final boolean canCoerce = Injector.canCoerce(inTypes[arg], myTypes[arg]);
                    if (!canCoerce) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        boolean captureArgs() {
            return this.captureArgs;
        }
    }
}
