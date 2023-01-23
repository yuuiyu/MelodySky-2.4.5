//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class AnnotationNode extends AnnotationVisitor
{
    public String desc;
    public List<Object> values;
    
    public AnnotationNode(final String desc) {
        this(327680, desc);
        if (this.getClass() != AnnotationNode.class) {
            throw new IllegalStateException();
        }
    }
    
    public AnnotationNode(final int api, final String desc) {
        super(api);
        this.desc = desc;
    }
    
    AnnotationNode(final List<Object> values) {
        super(327680);
        this.values = values;
    }
    
    public void visit(final String name, final Object value) {
        if (this.values == null) {
            this.values = new ArrayList<Object>((this.desc != null) ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(name);
        }
        this.values.add(value);
    }
    
    public void visitEnum(final String name, final String desc, final String value) {
        if (this.values == null) {
            this.values = new ArrayList<Object>((this.desc != null) ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(name);
        }
        this.values.add(new String[] { desc, value });
    }
    
    public AnnotationVisitor visitAnnotation(final String name, final String desc) {
        if (this.values == null) {
            this.values = new ArrayList<Object>((this.desc != null) ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(name);
        }
        final AnnotationNode annotation = new AnnotationNode(desc);
        this.values.add(annotation);
        return annotation;
    }
    
    public AnnotationVisitor visitArray(final String name) {
        if (this.values == null) {
            this.values = new ArrayList<Object>((this.desc != null) ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(name);
        }
        final List<Object> array = new ArrayList<Object>();
        this.values.add(array);
        return new AnnotationNode(array);
    }
    
    public void visitEnd() {
    }
    
    public void check(final int api) {
    }
    
    public void accept(final AnnotationVisitor av) {
        if (av != null) {
            if (this.values != null) {
                for (int i = 0; i < this.values.size(); i += 2) {
                    final String name = this.values.get(i);
                    final Object value = this.values.get(i + 1);
                    accept(av, name, value);
                }
            }
            av.visitEnd();
        }
    }
    
    static void accept(final AnnotationVisitor av, final String name, final Object value) {
        if (av != null) {
            if (value instanceof String[]) {
                final String[] typeconst = (String[])value;
                av.visitEnum(name, typeconst[0], typeconst[1]);
            }
            else if (value instanceof AnnotationNode) {
                final AnnotationNode an = (AnnotationNode)value;
                an.accept(av.visitAnnotation(name, an.desc));
            }
            else if (value instanceof List) {
                final AnnotationVisitor v = av.visitArray(name);
                final List<?> array = (List<?>)value;
                for (int j = 0; j < array.size(); ++j) {
                    accept(v, null, array.get(j));
                }
                v.visitEnd();
            }
            else {
                av.visit(name, value);
            }
        }
    }
}
