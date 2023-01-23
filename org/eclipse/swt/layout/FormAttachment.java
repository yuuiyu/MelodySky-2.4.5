//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public final class FormAttachment
{
    public int numerator;
    public int denominator;
    public int offset;
    public Control control;
    public int alignment;
    
    public FormAttachment() {
        this.denominator = 100;
    }
    
    public FormAttachment(final int numerator) {
        this(numerator, 100, 0);
    }
    
    public FormAttachment(final int numerator, final int offset) {
        this(numerator, 100, offset);
    }
    
    public FormAttachment(final int numerator, final int denominator, final int offset) {
        this.denominator = 100;
        if (denominator == 0) {
            SWT.error(7);
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.offset = offset;
    }
    
    public FormAttachment(final Control control) {
        this(control, 0, -1);
    }
    
    public FormAttachment(final Control control, final int offset) {
        this(control, offset, -1);
    }
    
    public FormAttachment(final Control control, final int offset, final int alignment) {
        this.denominator = 100;
        this.control = control;
        this.offset = offset;
        this.alignment = alignment;
    }
    
    FormAttachment divide(final int value) {
        return new FormAttachment(this.numerator, this.denominator * value, this.offset / value);
    }
    
    int gcd(int m, int n) {
        if ((m = Math.abs(m)) < (n = Math.abs(n))) {
            final int temp = m;
            m = n;
            n = temp;
        }
        while (n != 0) {
            final int temp = m;
            m = n;
            n = temp % n;
        }
        return m;
    }
    
    FormAttachment minus(final FormAttachment attachment) {
        final FormAttachment solution = new FormAttachment();
        solution.numerator = this.numerator * attachment.denominator - this.denominator * attachment.numerator;
        solution.denominator = this.denominator * attachment.denominator;
        final int gcd = this.gcd(solution.denominator, solution.numerator);
        final FormAttachment formAttachment = solution;
        formAttachment.numerator /= gcd;
        final FormAttachment formAttachment2 = solution;
        formAttachment2.denominator /= gcd;
        solution.offset = this.offset - attachment.offset;
        return solution;
    }
    
    FormAttachment minus(final int value) {
        return new FormAttachment(this.numerator, this.denominator, this.offset - value);
    }
    
    FormAttachment plus(final FormAttachment attachment) {
        final FormAttachment solution = new FormAttachment();
        solution.numerator = this.numerator * attachment.denominator + this.denominator * attachment.numerator;
        solution.denominator = this.denominator * attachment.denominator;
        final int gcd = this.gcd(solution.denominator, solution.numerator);
        final FormAttachment formAttachment = solution;
        formAttachment.numerator /= gcd;
        final FormAttachment formAttachment2 = solution;
        formAttachment2.denominator /= gcd;
        solution.offset = this.offset + attachment.offset;
        return solution;
    }
    
    FormAttachment plus(final int value) {
        return new FormAttachment(this.numerator, this.denominator, this.offset + value);
    }
    
    int solveX(final int value) {
        if (this.denominator == 0) {
            SWT.error(7);
        }
        return this.numerator * value / this.denominator + this.offset;
    }
    
    int solveY(final int value) {
        if (this.numerator == 0) {
            SWT.error(7);
        }
        return (value - this.offset) * this.denominator / this.numerator;
    }
    
    @Override
    public String toString() {
        final String string = (this.control != null) ? this.control.toString() : (this.numerator + "/" + this.denominator);
        return "{y = (" + string + ((this.offset >= 0) ? (")x + " + this.offset) : (")x - " + -this.offset)) + "}";
    }
}
