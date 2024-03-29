//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.lib.tree.*;

@InjectionPoint.AtCode("LOAD")
public class BeforeLoadLocal extends ModifyVariableInjector.ContextualInjectionPoint
{
    private final Type returnType;
    private final LocalVariableDiscriminator discriminator;
    private final int opcode;
    private final int ordinal;
    private boolean opcodeAfter;
    
    protected BeforeLoadLocal(final InjectionPointData data) {
        this(data, 21, false);
    }
    
    protected BeforeLoadLocal(final InjectionPointData data, final int opcode, final boolean opcodeAfter) {
        super(data.getMixin());
        this.returnType = data.getReturnType();
        this.discriminator = data.getLocalVariableDiscriminator();
        this.opcode = data.getOpcode(this.returnType.getOpcode(opcode));
        this.ordinal = data.getOrdinal();
        this.opcodeAfter = opcodeAfter;
    }
    
    @Override
    boolean find(final Target target, final Collection<AbstractInsnNode> nodes) {
        final SearchState state = new SearchState(this.ordinal);
        for (final AbstractInsnNode insn : target.method.instructions) {
            if (state.isPendingCheck()) {
                final int local = this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), target, insn);
                state.check(nodes, insn, local);
            }
            else {
                if (!(insn instanceof VarInsnNode) || insn.getOpcode() != this.opcode || (this.ordinal != -1 && state.success())) {
                    continue;
                }
                state.register((VarInsnNode)insn);
                if (this.opcodeAfter) {
                    state.setPendingCheck();
                }
                else {
                    final int local = this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), target, insn);
                    state.check(nodes, insn, local);
                }
            }
        }
        return state.success();
    }
    
    static class SearchState
    {
        private final int targetOrdinal;
        private int ordinal;
        private boolean pendingCheck;
        private boolean found;
        private VarInsnNode varNode;
        
        SearchState(final int targetOrdinal) {
            this.ordinal = 0;
            this.pendingCheck = false;
            this.found = false;
            this.targetOrdinal = targetOrdinal;
        }
        
        boolean success() {
            return this.found;
        }
        
        boolean isPendingCheck() {
            return this.pendingCheck;
        }
        
        void setPendingCheck() {
            this.pendingCheck = true;
        }
        
        void register(final VarInsnNode node) {
            this.varNode = node;
        }
        
        void check(final Collection<AbstractInsnNode> nodes, final AbstractInsnNode insn, final int local) {
            this.pendingCheck = false;
            if (local != this.varNode.var) {
                return;
            }
            if (this.targetOrdinal == -1 || this.targetOrdinal == this.ordinal) {
                nodes.add(insn);
                this.found = true;
            }
            ++this.ordinal;
            this.varNode = null;
        }
    }
}
