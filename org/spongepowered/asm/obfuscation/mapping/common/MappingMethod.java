//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.obfuscation.mapping.common;

import org.spongepowered.asm.obfuscation.mapping.*;
import com.google.common.base.*;

public class MappingMethod implements IMapping<MappingMethod>
{
    private final String owner;
    private final String name;
    private final String desc;
    
    public MappingMethod(final String fullyQualifiedName, final String desc) {
        this(getOwnerFromName(fullyQualifiedName), getBaseName(fullyQualifiedName), desc);
    }
    
    public MappingMethod(final String owner, final String simpleName, final String desc) {
        this.owner = owner;
        this.name = simpleName;
        this.desc = desc;
    }
    
    @Override
    public Type getType() {
        return Type.METHOD;
    }
    
    @Override
    public String getName() {
        if (this.name == null) {
            return null;
        }
        return ((this.owner != null) ? (this.owner + "/") : "") + this.name;
    }
    
    @Override
    public String getSimpleName() {
        return this.name;
    }
    
    @Override
    public String getOwner() {
        return this.owner;
    }
    
    @Override
    public String getDesc() {
        return this.desc;
    }
    
    @Override
    public MappingMethod move(final String newOwner) {
        return new MappingMethod(newOwner, this.getSimpleName(), this.getDesc());
    }
    
    @Override
    public MappingMethod remap(final String newName) {
        return new MappingMethod(this.getOwner(), newName, this.getDesc());
    }
    
    @Override
    public MappingMethod transform(final String newDesc) {
        return new MappingMethod(this.getOwner(), this.getSimpleName(), newDesc);
    }
    
    @Override
    public MappingMethod copy() {
        return new MappingMethod(this.getOwner(), this.getSimpleName(), this.getDesc());
    }
    
    public MappingMethod addPrefix(final String prefix) {
        final String simpleName = this.getSimpleName();
        if (simpleName == null || simpleName.startsWith(prefix)) {
            return this;
        }
        return new MappingMethod(this.getOwner(), prefix + simpleName, this.getDesc());
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.getName(), this.getDesc() });
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof MappingMethod && Objects.equal((Object)this.name, (Object)((MappingMethod)obj).name) && Objects.equal((Object)this.desc, (Object)((MappingMethod)obj).desc));
    }
    
    @Override
    public String serialise() {
        return this.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", this.getName(), this.getDesc());
    }
    
    private static String getBaseName(final String name) {
        if (name == null) {
            return null;
        }
        final int pos = name.lastIndexOf(47);
        return (pos > -1) ? name.substring(pos + 1) : name;
    }
    
    private static String getOwnerFromName(final String name) {
        if (name == null) {
            return null;
        }
        final int pos = name.lastIndexOf(47);
        return (pos > -1) ? name.substring(0, pos) : null;
    }
}
