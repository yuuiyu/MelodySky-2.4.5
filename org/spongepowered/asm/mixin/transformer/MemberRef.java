//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;

public abstract class MemberRef
{
    public abstract boolean isField();
    
    public abstract int getOpcode();
    
    public abstract String getOwner();
    
    public abstract void setOwner(final String p0);
    
    public abstract String getName();
    
    public abstract String getDesc();
    
    public abstract void setDesc(final String p0);
    
    @Override
    public String toString() {
        return ASMHelper.getOpcodeName(this.getOpcode()) + " for " + this.getOwner() + "." + this.getName() + (this.isField() ? ":" : "") + this.getDesc();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof MemberRef)) {
            return false;
        }
        final MemberRef other = (MemberRef)obj;
        return this.getOpcode() == other.getOpcode() && this.getOwner().equals(other.getOwner()) && this.getName().equals(other.getName()) && this.getDesc().equals(other.getDesc());
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    public static final class Method extends MemberRef
    {
        public final MethodInsnNode insn;
        
        public Method(final MethodInsnNode insn) {
            this.insn = insn;
        }
        
        @Override
        public boolean isField() {
            return false;
        }
        
        @Override
        public int getOpcode() {
            return this.insn.getOpcode();
        }
        
        @Override
        public String getOwner() {
            return this.insn.owner;
        }
        
        @Override
        public void setOwner(final String owner) {
            this.insn.owner = owner;
        }
        
        @Override
        public String getName() {
            return this.insn.name;
        }
        
        @Override
        public String getDesc() {
            return this.insn.desc;
        }
        
        @Override
        public void setDesc(final String desc) {
            this.insn.desc = desc;
        }
    }
    
    public static final class Field extends MemberRef
    {
        public final FieldInsnNode insn;
        
        public Field(final FieldInsnNode insn) {
            this.insn = insn;
        }
        
        @Override
        public boolean isField() {
            return true;
        }
        
        @Override
        public int getOpcode() {
            return this.insn.getOpcode();
        }
        
        @Override
        public String getOwner() {
            return this.insn.owner;
        }
        
        @Override
        public void setOwner(final String owner) {
            this.insn.owner = owner;
        }
        
        @Override
        public String getName() {
            return this.insn.name;
        }
        
        @Override
        public String getDesc() {
            return this.insn.desc;
        }
        
        @Override
        public void setDesc(final String desc) {
            this.insn.desc = desc;
        }
    }
    
    public static final class Handle extends MemberRef
    {
        private org.spongepowered.asm.lib.Handle handle;
        
        public Handle(final org.spongepowered.asm.lib.Handle handle) {
            this.handle = handle;
        }
        
        public org.spongepowered.asm.lib.Handle getMethodHandle() {
            return this.handle;
        }
        
        @Override
        public boolean isField() {
            switch (this.handle.getTag()) {
                case 5:
                case 6:
                case 7:
                case 8:
                case 9: {
                    return false;
                }
                case 1:
                case 2:
                case 3:
                case 4: {
                    return true;
                }
                default: {
                    throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
                }
            }
        }
        
        @Override
        public int getOpcode() {
            switch (this.handle.getTag()) {
                case 5: {
                    return 182;
                }
                case 6: {
                    return 184;
                }
                case 9: {
                    return 185;
                }
                case 7:
                case 8: {
                    return 183;
                }
                case 1: {
                    return 180;
                }
                case 2: {
                    return 178;
                }
                case 3: {
                    return 181;
                }
                case 4: {
                    return 179;
                }
                default: {
                    throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
                }
            }
        }
        
        @Override
        public String getOwner() {
            return this.handle.getOwner();
        }
        
        @Override
        public void setOwner(final String owner) {
            this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), owner, this.handle.getName(), this.handle.getDesc());
        }
        
        @Override
        public String getName() {
            return this.handle.getName();
        }
        
        @Override
        public String getDesc() {
            return this.handle.getDesc();
        }
        
        @Override
        public void setDesc(final String desc) {
            this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), this.handle.getOwner(), this.handle.getName(), desc);
        }
    }
}
