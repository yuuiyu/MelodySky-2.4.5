//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class ObfuscationDataProvider implements IObfuscationDataProvider
{
    private final IMixinAnnotationProcessor ap;
    private final List<ObfuscationEnvironment> environments;
    
    public ObfuscationDataProvider(final IMixinAnnotationProcessor ap, final List<ObfuscationEnvironment> environments) {
        this.ap = ap;
        this.environments = environments;
    }
    
    public <T> ObfuscationData<T> getObfEntryRecursive(final MemberInfo targetMember) {
        MemberInfo currentTarget = targetMember;
        final ObfuscationData<String> obfTargetNames = this.getObfClass(currentTarget.owner);
        ObfuscationData<T> obfData = (ObfuscationData<T>)this.getObfEntry(currentTarget);
        try {
            while (obfData.isEmpty()) {
                final TypeHandle targetType = this.ap.getTypeProvider().getTypeHandle(currentTarget.owner);
                if (targetType == null) {
                    return obfData;
                }
                final TypeHandle superClass = targetType.getSuperclass();
                if (superClass == null) {
                    return obfData;
                }
                currentTarget = currentTarget.move(superClass.getName());
                obfData = (ObfuscationData<T>)this.getObfEntry(currentTarget);
                if (obfData.isEmpty()) {
                    continue;
                }
                for (final ObfuscationType type : obfData) {
                    final String obfClass = (String)obfTargetNames.get(type);
                    final T obfMember = (T)obfData.get(type);
                    obfData.add(type, (Object)MemberInfo.fromMapping((IMapping)obfMember).move(obfClass).asMapping());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return (ObfuscationData<T>)this.getObfEntry(targetMember);
        }
        return obfData;
    }
    
    public <T> ObfuscationData<T> getObfEntry(final MemberInfo targetMember) {
        if (targetMember.isField()) {
            return (ObfuscationData<T>)this.getObfField(targetMember);
        }
        return (ObfuscationData<T>)this.getObfMethod(targetMember.asMethodMapping());
    }
    
    public <T> ObfuscationData<T> getObfEntry(final IMapping<T> mapping) {
        if (mapping != null) {
            if (mapping.getType() == IMapping.Type.FIELD) {
                return (ObfuscationData<T>)this.getObfField((MappingField)mapping);
            }
            if (mapping.getType() == IMapping.Type.METHOD) {
                return (ObfuscationData<T>)this.getObfMethod((MappingMethod)mapping);
            }
        }
        return (ObfuscationData<T>)new ObfuscationData();
    }
    
    public ObfuscationData<MappingMethod> getObfMethodRecursive(final MemberInfo targetMember) {
        return this.getObfEntryRecursive(targetMember);
    }
    
    public ObfuscationData<MappingMethod> getObfMethod(final MemberInfo method) {
        return this.getRemappedMethod(method, "<init>".equals(method.name));
    }
    
    public ObfuscationData<MappingMethod> getRemappedMethod(final MemberInfo method) {
        return this.getRemappedMethod(method, true);
    }
    
    private ObfuscationData<MappingMethod> getRemappedMethod(final MemberInfo method, final boolean remapDescriptor) {
        final ObfuscationData<MappingMethod> data = (ObfuscationData<MappingMethod>)new ObfuscationData();
        for (final ObfuscationEnvironment env : this.environments) {
            final MappingMethod obfMethod = env.getObfMethod(method);
            if (obfMethod != null) {
                data.add(env.getType(), (Object)obfMethod);
            }
        }
        if (!data.isEmpty() || !remapDescriptor) {
            return data;
        }
        return this.remapDescriptor(data, method);
    }
    
    public ObfuscationData<MappingMethod> getObfMethod(final MappingMethod method) {
        return this.getRemappedMethod(method, "<init>".equals(method.getSimpleName()));
    }
    
    public ObfuscationData<MappingMethod> getRemappedMethod(final MappingMethod method) {
        return this.getRemappedMethod(method, true);
    }
    
    private ObfuscationData<MappingMethod> getRemappedMethod(final MappingMethod method, final boolean remapDescriptor) {
        final ObfuscationData<MappingMethod> data = (ObfuscationData<MappingMethod>)new ObfuscationData();
        for (final ObfuscationEnvironment env : this.environments) {
            final MappingMethod obfMethod = env.getObfMethod(method);
            if (obfMethod != null) {
                data.add(env.getType(), (Object)obfMethod);
            }
        }
        if (!data.isEmpty() || !remapDescriptor) {
            return data;
        }
        return this.remapDescriptor(data, new MemberInfo((IMapping)method));
    }
    
    public ObfuscationData<MappingMethod> remapDescriptor(final ObfuscationData<MappingMethod> data, final MemberInfo method) {
        for (final ObfuscationEnvironment env : this.environments) {
            final MemberInfo obfMethod = env.remapDescriptor(method);
            if (obfMethod != null) {
                data.add(env.getType(), (Object)obfMethod.asMethodMapping());
            }
        }
        return data;
    }
    
    public ObfuscationData<MappingField> getObfFieldRecursive(final MemberInfo targetMember) {
        return this.getObfEntryRecursive(targetMember);
    }
    
    public ObfuscationData<MappingField> getObfField(final MemberInfo field) {
        return this.getObfField(field.asFieldMapping());
    }
    
    public ObfuscationData<MappingField> getObfField(final MappingField field) {
        final ObfuscationData<MappingField> data = (ObfuscationData<MappingField>)new ObfuscationData();
        for (final ObfuscationEnvironment env : this.environments) {
            MappingField obfField = env.getObfField(field);
            if (obfField != null) {
                if (obfField.getDesc() == null && field.getDesc() != null) {
                    obfField = obfField.transform(env.remapDescriptor(field.getDesc()));
                }
                data.add(env.getType(), (Object)obfField);
            }
        }
        return data;
    }
    
    public ObfuscationData<String> getObfClass(final TypeHandle type) {
        return this.getObfClass(type.getName());
    }
    
    public ObfuscationData<String> getObfClass(final String className) {
        final ObfuscationData<String> data = (ObfuscationData<String>)new ObfuscationData((Object)className);
        for (final ObfuscationEnvironment env : this.environments) {
            final String obfClass = env.getObfClass(className);
            if (obfClass != null) {
                data.add(env.getType(), (Object)obfClass);
            }
        }
        return data;
    }
}
