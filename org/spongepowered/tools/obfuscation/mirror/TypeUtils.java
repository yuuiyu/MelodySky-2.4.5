//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import javax.lang.model.element.*;
import java.util.*;
import javax.lang.model.type.*;
import javax.annotation.processing.*;

public abstract class TypeUtils
{
    private static final int MAX_GENERIC_RECURSION_DEPTH = 5;
    private static final String OBJECT_SIG = "java.lang.Object";
    private static final String OBJECT_REF = "java/lang/Object";
    
    private TypeUtils() {
    }
    
    public static PackageElement getPackage(final TypeMirror type) {
        if (!(type instanceof DeclaredType)) {
            return null;
        }
        return getPackage((TypeElement)((DeclaredType)type).asElement());
    }
    
    public static PackageElement getPackage(final TypeElement type) {
        Element parent;
        for (parent = type.getEnclosingElement(); parent != null && !(parent instanceof PackageElement); parent = parent.getEnclosingElement()) {}
        return (PackageElement)parent;
    }
    
    public static String getElementType(final Element parent) {
        if (parent instanceof TypeElement) {
            return "TypeElement";
        }
        if (parent instanceof ExecutableElement) {
            return "ExecutableElement";
        }
        if (parent instanceof VariableElement) {
            return "VariableElement";
        }
        if (parent instanceof PackageElement) {
            return "PackageElement";
        }
        if (parent instanceof TypeParameterElement) {
            return "TypeParameterElement";
        }
        return parent.getClass().getSimpleName();
    }
    
    public static String getJavaSignature(final Element element) {
        if (element instanceof ExecutableElement) {
            final ExecutableElement method = (ExecutableElement)element;
            final StringBuilder desc = new StringBuilder().append("(");
            boolean extra = false;
            for (final VariableElement arg : method.getParameters()) {
                if (extra) {
                    desc.append(',');
                }
                desc.append(getTypeName(arg.asType()));
                extra = true;
            }
            desc.append(')').append(getTypeName(method.getReturnType()));
            return desc.toString();
        }
        return getTypeName(element.asType());
    }
    
    public static String stripGenerics(final String type) {
        final StringBuilder sb = new StringBuilder();
        int pos = 0;
        int depth = 0;
        while (pos < type.length()) {
            final char c = type.charAt(pos);
            if (c == '<') {
                ++depth;
            }
            if (depth == 0) {
                sb.append(c);
            }
            else if (c == '>') {
                --depth;
            }
            ++pos;
        }
        return sb.toString();
    }
    
    public static String getDescriptor(final Element elem) {
        if (elem instanceof ExecutableElement) {
            return getDescriptor((ExecutableElement)elem);
        }
        if (elem instanceof VariableElement) {
            return getInternalName((VariableElement)elem);
        }
        return getInternalName(elem.asType());
    }
    
    public static String getTypeName(final TypeMirror type) {
        switch (TypeUtils.l.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()]) {
            case 1: {
                return getTypeName(((ArrayType)type).getComponentType()) + "[]";
            }
            case 2: {
                return getTypeName((DeclaredType)type);
            }
            case 3: {
                return getTypeName(getUpperBound(type));
            }
            case 4: {
                return "java.lang.Object";
            }
            default: {
                return type.toString();
            }
        }
    }
    
    public static String getTypeName(final DeclaredType type) {
        if (type == null) {
            return "java.lang.Object";
        }
        return getTypeName((TypeElement)type.asElement());
    }
    
    private static String getTypeName(final TypeElement elem) {
        return getInternalName(elem).replace('/', '.');
    }
    
    public static String getName(final VariableElement field) {
        return (field != null) ? field.getSimpleName().toString() : null;
    }
    
    public static String getName(final ExecutableElement method) {
        return (method != null) ? method.getSimpleName().toString() : null;
    }
    
    public static String getDescriptor(final ExecutableElement method) {
        if (method == null) {
            return null;
        }
        final StringBuilder signature = new StringBuilder();
        for (final VariableElement var : method.getParameters()) {
            signature.append(getInternalName(var));
        }
        final String returnType = getInternalName(method.getReturnType());
        return String.format("(%s)%s", signature, returnType);
    }
    
    public static String getInternalName(final VariableElement var) {
        if (var == null) {
            return null;
        }
        return getInternalName(var.asType());
    }
    
    public static String getInternalName(final TypeMirror type) {
        switch (TypeUtils.l.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()]) {
            case 1: {
                return "[" + getInternalName(((ArrayType)type).getComponentType());
            }
            case 2: {
                return "L" + getInternalName((DeclaredType)type) + ";";
            }
            case 3: {
                return "L" + getInternalName(getUpperBound(type)) + ";";
            }
            case 5: {
                return "Z";
            }
            case 6: {
                return "B";
            }
            case 7: {
                return "C";
            }
            case 8: {
                return "D";
            }
            case 9: {
                return "F";
            }
            case 10: {
                return "I";
            }
            case 11: {
                return "J";
            }
            case 12: {
                return "S";
            }
            case 13: {
                return "V";
            }
            case 4: {
                return "Ljava/lang/Object;";
            }
            default: {
                throw new IllegalArgumentException("Unable to parse type symbol " + type + " with " + type.getKind() + " to equivalent bytecode type");
            }
        }
    }
    
    private static DeclaredType getUpperBound(final TypeMirror type) {
        try {
            return getUpperBound0(type, 5);
        }
        catch (IllegalStateException ex) {
            throw new IllegalArgumentException("Type symbol \"" + type + "\" is too complex", ex);
        }
        catch (IllegalArgumentException ex2) {
            throw new IllegalArgumentException("Unable to compute upper bound of type symbol " + type, ex2);
        }
    }
    
    private static DeclaredType getUpperBound0(final TypeMirror type, int depth) {
        if (depth == 0) {
            throw new IllegalStateException("Generic symbol \"" + type + "\" is too complex, exceeded " + 5 + " iterations attempting to determine upper bound");
        }
        if (type instanceof DeclaredType) {
            return (DeclaredType)type;
        }
        if (type instanceof TypeVariable) {
            try {
                final TypeMirror upper = ((TypeVariable)type).getUpperBound();
                return getUpperBound0(upper, --depth);
            }
            catch (IllegalStateException ex) {
                throw ex;
            }
            catch (IllegalArgumentException ex2) {
                throw ex2;
            }
            catch (Exception ex3) {
                throw new IllegalArgumentException("Unable to compute upper bound of type symbol " + type);
            }
        }
        return null;
    }
    
    public static String getInternalName(final DeclaredType type) {
        if (type == null) {
            return "java/lang/Object";
        }
        return getInternalName((TypeElement)type.asElement());
    }
    
    public static String getInternalName(final TypeElement elem) {
        if (elem == null) {
            return null;
        }
        final StringBuilder reference = new StringBuilder();
        reference.append(elem.getSimpleName());
        for (Element parent = elem.getEnclosingElement(); parent != null; parent = parent.getEnclosingElement()) {
            if (parent instanceof TypeElement) {
                reference.insert(0, "$").insert(0, parent.getSimpleName());
            }
            else if (parent instanceof PackageElement) {
                reference.insert(0, "/").insert(0, ((PackageElement)parent).getQualifiedName().toString().replace('.', '/'));
            }
        }
        return reference.toString();
    }
    
    public static boolean isAssignable(final ProcessingEnvironment processingEnv, final TypeMirror targetType, final TypeMirror superClass) {
        final boolean assignable = processingEnv.getTypeUtils().isAssignable(targetType, superClass);
        if (!assignable && targetType instanceof DeclaredType && superClass instanceof DeclaredType) {
            final TypeMirror rawTargetType = toRawType(processingEnv, (DeclaredType)targetType);
            final TypeMirror rawSuperType = toRawType(processingEnv, (DeclaredType)superClass);
            return processingEnv.getTypeUtils().isAssignable(rawTargetType, rawSuperType);
        }
        return assignable;
    }
    
    private static TypeMirror toRawType(final ProcessingEnvironment processingEnv, final DeclaredType targetType) {
        return processingEnv.getElementUtils().getTypeElement(((TypeElement)targetType.asElement()).getQualifiedName()).asType();
    }
}
