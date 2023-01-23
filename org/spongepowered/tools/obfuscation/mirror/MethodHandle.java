//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import org.spongepowered.asm.obfuscation.mapping.common.*;
import javax.lang.model.element.*;
import com.google.common.base.*;
import org.spongepowered.asm.obfuscation.mapping.*;

public class MethodHandle extends MemberHandle<MappingMethod>
{
    private final ExecutableElement element;
    
    public MethodHandle(final TypeElement owner, final ExecutableElement element) {
        this(TypeUtils.getInternalName(owner), element);
    }
    
    public MethodHandle(final String owner, final ExecutableElement element) {
        this(owner, TypeUtils.getName(element), TypeUtils.getDescriptor(element));
    }
    
    protected MethodHandle(final String owner, final String name, final String desc) {
        this(owner, null, name, desc);
    }
    
    private MethodHandle(final String owner, final ExecutableElement element, final String name, final String desc) {
        super(owner, name, desc);
        this.element = element;
    }
    
    public boolean isImaginary() {
        return this.element == null;
    }
    
    public ExecutableElement getElement() {
        return this.element;
    }
    
    public MappingMethod asMapping(final boolean includeOwner) {
        return new MappingMethod(includeOwner ? this.getOwner() : null, this.getName(), this.getDesc());
    }
    
    public String toString() {
        final String owner = (this.getOwner() != null) ? ("L" + this.getOwner() + ";") : "";
        final String name = Strings.nullToEmpty(this.getName());
        final String desc = Strings.nullToEmpty(this.getDesc());
        return String.format("%s%s%s", owner, name, desc);
    }
}
