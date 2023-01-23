//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.apache.logging.log4j.*;
import java.util.*;
import org.spongepowered.asm.lib.tree.*;

@InjectionPoint.AtCode("INVOKE")
public class BeforeInvoke extends InjectionPoint
{
    protected final MemberInfo target;
    protected final int ordinal;
    protected boolean logging;
    protected final Logger logger;
    protected final String className;
    
    public BeforeInvoke(final InjectionPointData data) {
        super(data);
        this.logging = false;
        this.logger = LogManager.getLogger("mixin");
        this.target = data.getTarget();
        this.ordinal = data.getOrdinal();
        this.logging = data.get("log", false);
        this.className = this.getClass().getSimpleName();
    }
    
    public BeforeInvoke setLogging(final boolean logging) {
        this.logging = logging;
        return this;
    }
    
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        int ordinal = 0;
        boolean found = false;
        if (this.logging) {
            this.logger.info("{} is searching for an injection point in method with descriptor {}", new Object[] { this.className, desc });
        }
        for (final AbstractInsnNode insn : insns) {
            if (this.matchesInsn(insn)) {
                final MemberInfo nodeInfo = new MemberInfo(insn);
                if (this.logging) {
                    this.logger.info("{} is considering insn {}", new Object[] { this.className, nodeInfo });
                }
                if (this.target.matches(nodeInfo.owner, nodeInfo.name, nodeInfo.desc)) {
                    if (this.logging) {
                        this.logger.info("{} > found a matching insn, checking preconditions...", new Object[] { this.className });
                    }
                    if (this.matchesInsn(nodeInfo, ordinal)) {
                        if (this.logging) {
                            this.logger.info("{} > > > found a matching insn at ordinal {}", new Object[] { this.className, ordinal });
                        }
                        found |= this.addInsn(insns, nodes, insn);
                        if (this.ordinal == ordinal) {
                            break;
                        }
                    }
                    ++ordinal;
                }
            }
            this.inspectInsn(desc, insns, insn);
        }
        return found;
    }
    
    protected boolean addInsn(final InsnList insns, final Collection<AbstractInsnNode> nodes, final AbstractInsnNode insn) {
        nodes.add(insn);
        return true;
    }
    
    protected boolean matchesInsn(final AbstractInsnNode insn) {
        return insn instanceof MethodInsnNode;
    }
    
    protected void inspectInsn(final String desc, final InsnList insns, final AbstractInsnNode insn) {
    }
    
    protected boolean matchesInsn(final MemberInfo nodeInfo, final int ordinal) {
        if (this.logging) {
            this.logger.info("{} > > comparing target ordinal {} with current ordinal {}", new Object[] { this.className, this.ordinal, ordinal });
        }
        return this.ordinal == -1 || this.ordinal == ordinal;
    }
}
