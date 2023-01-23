//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.tree.*;

public class Frame<V extends Value>
{
    private V returnValue;
    private V[] values;
    private int locals;
    private int top;
    
    public Frame(final int nLocals, final int nStack) {
        this.values = (V[])new Value[nLocals + nStack];
        this.locals = nLocals;
    }
    
    public Frame(final Frame<? extends V> src) {
        this(src.locals, src.values.length - src.locals);
        this.init(src);
    }
    
    public Frame<V> init(final Frame<? extends V> src) {
        this.returnValue = (V)src.returnValue;
        System.arraycopy(src.values, 0, this.values, 0, this.values.length);
        this.top = src.top;
        return this;
    }
    
    public void setReturn(final V v) {
        this.returnValue = v;
    }
    
    public int getLocals() {
        return this.locals;
    }
    
    public int getMaxStackSize() {
        return this.values.length - this.locals;
    }
    
    public V getLocal(final int i) throws IndexOutOfBoundsException {
        if (i >= this.locals) {
            throw new IndexOutOfBoundsException("Trying to access an inexistant local variable");
        }
        return this.values[i];
    }
    
    public void setLocal(final int i, final V value) throws IndexOutOfBoundsException {
        if (i >= this.locals) {
            throw new IndexOutOfBoundsException("Trying to access an inexistant local variable " + i);
        }
        this.values[i] = value;
    }
    
    public int getStackSize() {
        return this.top;
    }
    
    public V getStack(final int i) throws IndexOutOfBoundsException {
        return this.values[i + this.locals];
    }
    
    public void clearStack() {
        this.top = 0;
    }
    
    public V pop() throws IndexOutOfBoundsException {
        if (this.top == 0) {
            throw new IndexOutOfBoundsException("Cannot pop operand off an empty stack.");
        }
        final V[] values = this.values;
        final int top = this.top - 1;
        this.top = top;
        return values[top + this.locals];
    }
    
    public void push(final V value) throws IndexOutOfBoundsException {
        if (this.top + this.locals >= this.values.length) {
            throw new IndexOutOfBoundsException("Insufficient maximum stack size.");
        }
        this.values[this.top++ + this.locals] = value;
    }
    
    public void execute(final AbstractInsnNode insn, final Interpreter<V> interpreter) throws AnalyzerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/spongepowered/asm/lib/tree/AbstractInsnNode.getOpcode:()I
        //     4: tableswitch {
        //                0: 820
        //                1: 823
        //                2: 823
        //                3: 823
        //                4: 823
        //                5: 823
        //                6: 823
        //                7: 823
        //                8: 823
        //                9: 823
        //               10: 823
        //               11: 823
        //               12: 823
        //               13: 823
        //               14: 823
        //               15: 823
        //               16: 823
        //               17: 823
        //               18: 823
        //               19: 2512
        //               20: 2512
        //               21: 835
        //               22: 835
        //               23: 835
        //               24: 835
        //               25: 835
        //               26: 2512
        //               27: 2512
        //               28: 2512
        //               29: 2512
        //               30: 2512
        //               31: 2512
        //               32: 2512
        //               33: 2512
        //               34: 2512
        //               35: 2512
        //               36: 2512
        //               37: 2512
        //               38: 2512
        //               39: 2512
        //               40: 2512
        //               41: 2512
        //               42: 2512
        //               43: 2512
        //               44: 2512
        //               45: 2512
        //               46: 858
        //               47: 858
        //               48: 858
        //               49: 858
        //               50: 858
        //               51: 858
        //               52: 858
        //               53: 858
        //               54: 884
        //               55: 884
        //               56: 884
        //               57: 884
        //               58: 884
        //               59: 2512
        //               60: 2512
        //               61: 2512
        //               62: 2512
        //               63: 2512
        //               64: 2512
        //               65: 2512
        //               66: 2512
        //               67: 2512
        //               68: 2512
        //               69: 2512
        //               70: 2512
        //               71: 2512
        //               72: 2512
        //               73: 2512
        //               74: 2512
        //               75: 2512
        //               76: 2512
        //               77: 2512
        //               78: 2512
        //               79: 983
        //               80: 983
        //               81: 983
        //               82: 983
        //               83: 983
        //               84: 983
        //               85: 983
        //               86: 983
        //               87: 1014
        //               88: 1038
        //               89: 1075
        //               90: 1123
        //               91: 1191
        //               92: 1307
        //               93: 1405
        //               94: 1546
        //               95: 1799
        //               96: 1866
        //               97: 1866
        //               98: 1866
        //               99: 1866
        //              100: 1866
        //              101: 1866
        //              102: 1866
        //              103: 1866
        //              104: 1866
        //              105: 1866
        //              106: 1866
        //              107: 1866
        //              108: 1866
        //              109: 1866
        //              110: 1866
        //              111: 1866
        //              112: 1866
        //              113: 1866
        //              114: 1866
        //              115: 1866
        //              116: 1892
        //              117: 1892
        //              118: 1892
        //              119: 1892
        //              120: 1908
        //              121: 1908
        //              122: 1908
        //              123: 1908
        //              124: 1908
        //              125: 1908
        //              126: 1908
        //              127: 1908
        //              128: 1908
        //              129: 1908
        //              130: 1908
        //              131: 1908
        //              132: 1934
        //              133: 1963
        //              134: 1963
        //              135: 1963
        //              136: 1963
        //              137: 1963
        //              138: 1963
        //              139: 1963
        //              140: 1963
        //              141: 1963
        //              142: 1963
        //              143: 1963
        //              144: 1963
        //              145: 1963
        //              146: 1963
        //              147: 1963
        //              148: 1979
        //              149: 1979
        //              150: 1979
        //              151: 1979
        //              152: 1979
        //              153: 2005
        //              154: 2005
        //              155: 2005
        //              156: 2005
        //              157: 2005
        //              158: 2005
        //              159: 2018
        //              160: 2018
        //              161: 2018
        //              162: 2018
        //              163: 2018
        //              164: 2018
        //              165: 2018
        //              166: 2018
        //              167: 2041
        //              168: 2044
        //              169: 2056
        //              170: 2059
        //              171: 2059
        //              172: 2072
        //              173: 2072
        //              174: 2072
        //              175: 2072
        //              176: 2072
        //              177: 2100
        //              178: 2118
        //              179: 2130
        //              180: 2143
        //              181: 2159
        //              182: 2182
        //              183: 2182
        //              184: 2182
        //              185: 2182
        //              186: 2289
        //              187: 2374
        //              188: 2386
        //              189: 2386
        //              190: 2386
        //              191: 2402
        //              192: 2415
        //              193: 2415
        //              194: 2431
        //              195: 2431
        //              196: 2512
        //              197: 2444
        //              198: 2499
        //              199: 2499
        //          default: 2512
        //        }
        //   820: goto            2542
        //   823: aload_0         /* this */
        //   824: aload_2         /* interpreter */
        //   825: aload_1         /* insn */
        //   826: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   829: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   832: goto            2542
        //   835: aload_0         /* this */
        //   836: aload_2         /* interpreter */
        //   837: aload_1         /* insn */
        //   838: aload_0         /* this */
        //   839: aload_1         /* insn */
        //   840: checkcast       Lorg/spongepowered/asm/lib/tree/VarInsnNode;
        //   843: getfield        org/spongepowered/asm/lib/tree/VarInsnNode.var:I
        //   846: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.getLocal:(I)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   849: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   852: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   855: goto            2542
        //   858: aload_0         /* this */
        //   859: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   862: astore_3        /* value2 */
        //   863: aload_0         /* this */
        //   864: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   867: astore          value1
        //   869: aload_0         /* this */
        //   870: aload_2         /* interpreter */
        //   871: aload_1         /* insn */
        //   872: aload           value1
        //   874: aload_3         /* value2 */
        //   875: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   878: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   881: goto            2542
        //   884: aload_2         /* interpreter */
        //   885: aload_1         /* insn */
        //   886: aload_0         /* this */
        //   887: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   890: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   893: astore          value1
        //   895: aload_1         /* insn */
        //   896: checkcast       Lorg/spongepowered/asm/lib/tree/VarInsnNode;
        //   899: getfield        org/spongepowered/asm/lib/tree/VarInsnNode.var:I
        //   902: istore          var
        //   904: aload_0         /* this */
        //   905: iload           var
        //   907: aload           value1
        //   909: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.setLocal:(ILorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   912: aload           value1
        //   914: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //   919: iconst_2       
        //   920: if_icmpne       936
        //   923: aload_0         /* this */
        //   924: iload           var
        //   926: iconst_1       
        //   927: iadd           
        //   928: aload_2         /* interpreter */
        //   929: aconst_null    
        //   930: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newValue:(Lorg/spongepowered/asm/lib/Type;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   933: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.setLocal:(ILorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   936: iload           var
        //   938: ifle            2542
        //   941: aload_0         /* this */
        //   942: iload           var
        //   944: iconst_1       
        //   945: isub           
        //   946: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.getLocal:(I)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   949: astore          local
        //   951: aload           local
        //   953: ifnull          980
        //   956: aload           local
        //   958: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //   963: iconst_2       
        //   964: if_icmpne       980
        //   967: aload_0         /* this */
        //   968: iload           var
        //   970: iconst_1       
        //   971: isub           
        //   972: aload_2         /* interpreter */
        //   973: aconst_null    
        //   974: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newValue:(Lorg/spongepowered/asm/lib/Type;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   977: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.setLocal:(ILorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //   980: goto            2542
        //   983: aload_0         /* this */
        //   984: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   987: astore          value3
        //   989: aload_0         /* this */
        //   990: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   993: astore_3        /* value2 */
        //   994: aload_0         /* this */
        //   995: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //   998: astore          value1
        //  1000: aload_2         /* interpreter */
        //  1001: aload_1         /* insn */
        //  1002: aload           value1
        //  1004: aload_3         /* value2 */
        //  1005: aload           value3
        //  1007: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.ternaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1010: pop            
        //  1011: goto            2542
        //  1014: aload_0         /* this */
        //  1015: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1018: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1023: iconst_2       
        //  1024: if_icmpne       2542
        //  1027: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1030: dup            
        //  1031: aload_1         /* insn */
        //  1032: ldc             "Illegal use of POP"
        //  1034: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1037: athrow         
        //  1038: aload_0         /* this */
        //  1039: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1042: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1047: iconst_1       
        //  1048: if_icmpne       2542
        //  1051: aload_0         /* this */
        //  1052: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1055: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1060: iconst_1       
        //  1061: if_icmpeq       2542
        //  1064: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1067: dup            
        //  1068: aload_1         /* insn */
        //  1069: ldc             "Illegal use of POP2"
        //  1071: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1074: athrow         
        //  1075: aload_0         /* this */
        //  1076: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1079: astore          value1
        //  1081: aload           value1
        //  1083: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1088: iconst_1       
        //  1089: if_icmpeq       1103
        //  1092: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1095: dup            
        //  1096: aload_1         /* insn */
        //  1097: ldc             "Illegal use of DUP"
        //  1099: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1102: athrow         
        //  1103: aload_0         /* this */
        //  1104: aload           value1
        //  1106: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1109: aload_0         /* this */
        //  1110: aload_2         /* interpreter */
        //  1111: aload_1         /* insn */
        //  1112: aload           value1
        //  1114: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1117: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1120: goto            2542
        //  1123: aload_0         /* this */
        //  1124: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1127: astore          value1
        //  1129: aload_0         /* this */
        //  1130: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1133: astore_3        /* value2 */
        //  1134: aload           value1
        //  1136: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1141: iconst_1       
        //  1142: if_icmpne       1155
        //  1145: aload_3         /* value2 */
        //  1146: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1151: iconst_1       
        //  1152: if_icmpeq       1166
        //  1155: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1158: dup            
        //  1159: aload_1         /* insn */
        //  1160: ldc             "Illegal use of DUP_X1"
        //  1162: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1165: athrow         
        //  1166: aload_0         /* this */
        //  1167: aload_2         /* interpreter */
        //  1168: aload_1         /* insn */
        //  1169: aload           value1
        //  1171: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1174: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1177: aload_0         /* this */
        //  1178: aload_3         /* value2 */
        //  1179: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1182: aload_0         /* this */
        //  1183: aload           value1
        //  1185: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1188: goto            2542
        //  1191: aload_0         /* this */
        //  1192: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1195: astore          value1
        //  1197: aload           value1
        //  1199: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1204: iconst_1       
        //  1205: if_icmpne       1296
        //  1208: aload_0         /* this */
        //  1209: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1212: astore_3        /* value2 */
        //  1213: aload_3         /* value2 */
        //  1214: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1219: iconst_1       
        //  1220: if_icmpne       1271
        //  1223: aload_0         /* this */
        //  1224: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1227: astore          value3
        //  1229: aload           value3
        //  1231: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1236: iconst_1       
        //  1237: if_icmpne       1296
        //  1240: aload_0         /* this */
        //  1241: aload_2         /* interpreter */
        //  1242: aload_1         /* insn */
        //  1243: aload           value1
        //  1245: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1248: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1251: aload_0         /* this */
        //  1252: aload           value3
        //  1254: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1257: aload_0         /* this */
        //  1258: aload_3        
        //  1259: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1262: aload_0         /* this */
        //  1263: aload           value1
        //  1265: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1268: goto            2542
        //  1271: aload_0         /* this */
        //  1272: aload_2         /* interpreter */
        //  1273: aload_1         /* insn */
        //  1274: aload           value1
        //  1276: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1279: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1282: aload_0         /* this */
        //  1283: aload_3        
        //  1284: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1287: aload_0         /* this */
        //  1288: aload           value1
        //  1290: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1293: goto            2542
        //  1296: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1299: dup            
        //  1300: aload_1         /* insn */
        //  1301: ldc             "Illegal use of DUP_X2"
        //  1303: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1306: athrow         
        //  1307: aload_0         /* this */
        //  1308: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1311: astore          value1
        //  1313: aload           value1
        //  1315: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1320: iconst_1       
        //  1321: if_icmpne       1374
        //  1324: aload_0         /* this */
        //  1325: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1328: astore_3        /* value2 */
        //  1329: aload_3         /* value2 */
        //  1330: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1335: iconst_1       
        //  1336: if_icmpne       1394
        //  1339: aload_0         /* this */
        //  1340: aload_3         /* value2 */
        //  1341: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1344: aload_0         /* this */
        //  1345: aload           value1
        //  1347: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1350: aload_0         /* this */
        //  1351: aload_2         /* interpreter */
        //  1352: aload_1         /* insn */
        //  1353: aload_3         /* value2 */
        //  1354: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1357: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1360: aload_0         /* this */
        //  1361: aload_2         /* interpreter */
        //  1362: aload_1         /* insn */
        //  1363: aload           value1
        //  1365: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1368: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1371: goto            2542
        //  1374: aload_0         /* this */
        //  1375: aload           value1
        //  1377: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1380: aload_0         /* this */
        //  1381: aload_2         /* interpreter */
        //  1382: aload_1         /* insn */
        //  1383: aload           value1
        //  1385: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1388: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1391: goto            2542
        //  1394: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1397: dup            
        //  1398: aload_1         /* insn */
        //  1399: ldc             "Illegal use of DUP2"
        //  1401: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1404: athrow         
        //  1405: aload_0         /* this */
        //  1406: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1409: astore          value1
        //  1411: aload           value1
        //  1413: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1418: iconst_1       
        //  1419: if_icmpne       1495
        //  1422: aload_0         /* this */
        //  1423: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1426: astore_3        /* value2 */
        //  1427: aload_3         /* value2 */
        //  1428: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1433: iconst_1       
        //  1434: if_icmpne       1535
        //  1437: aload_0         /* this */
        //  1438: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1441: astore          value3
        //  1443: aload           value3
        //  1445: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1450: iconst_1       
        //  1451: if_icmpne       1535
        //  1454: aload_0         /* this */
        //  1455: aload_2         /* interpreter */
        //  1456: aload_1         /* insn */
        //  1457: aload_3         /* value2 */
        //  1458: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1461: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1464: aload_0         /* this */
        //  1465: aload_2         /* interpreter */
        //  1466: aload_1         /* insn */
        //  1467: aload           value1
        //  1469: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1472: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1475: aload_0         /* this */
        //  1476: aload           7
        //  1478: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1481: aload_0         /* this */
        //  1482: aload_3         /* value2 */
        //  1483: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1486: aload_0         /* this */
        //  1487: aload           value1
        //  1489: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1492: goto            2542
        //  1495: aload_0         /* this */
        //  1496: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1499: astore_3        /* value2 */
        //  1500: aload_3         /* value2 */
        //  1501: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1506: iconst_1       
        //  1507: if_icmpne       1535
        //  1510: aload_0         /* this */
        //  1511: aload_2         /* interpreter */
        //  1512: aload_1         /* insn */
        //  1513: aload           value1
        //  1515: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1518: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1521: aload_0         /* this */
        //  1522: aload_3         /* value2 */
        //  1523: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1526: aload_0         /* this */
        //  1527: aload           value1
        //  1529: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1532: goto            2542
        //  1535: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1538: dup            
        //  1539: aload_1         /* insn */
        //  1540: ldc             "Illegal use of DUP2_X1"
        //  1542: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1545: athrow         
        //  1546: aload_0         /* this */
        //  1547: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1550: astore          value1
        //  1552: aload           value1
        //  1554: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1559: iconst_1       
        //  1560: if_icmpne       1700
        //  1563: aload_0         /* this */
        //  1564: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1567: astore_3        /* value2 */
        //  1568: aload_3         /* value2 */
        //  1569: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1574: iconst_1       
        //  1575: if_icmpne       1788
        //  1578: aload_0         /* this */
        //  1579: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1582: astore          value3
        //  1584: aload           value3
        //  1586: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1591: iconst_1       
        //  1592: if_icmpne       1659
        //  1595: aload_0         /* this */
        //  1596: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1599: astore          value4
        //  1601: aload           value4
        //  1603: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1608: iconst_1       
        //  1609: if_icmpne       1788
        //  1612: aload_0         /* this */
        //  1613: aload_2         /* interpreter */
        //  1614: aload_1         /* insn */
        //  1615: aload_3         /* value2 */
        //  1616: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1619: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1622: aload_0         /* this */
        //  1623: aload_2         /* interpreter */
        //  1624: aload_1         /* insn */
        //  1625: aload           value1
        //  1627: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1630: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1633: aload_0         /* this */
        //  1634: aload           value4
        //  1636: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1639: aload_0         /* this */
        //  1640: aload           7
        //  1642: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1645: aload_0         /* this */
        //  1646: aload_3         /* value2 */
        //  1647: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1650: aload_0         /* this */
        //  1651: aload           value1
        //  1653: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1656: goto            2542
        //  1659: aload_0         /* this */
        //  1660: aload_2         /* interpreter */
        //  1661: aload_1         /* insn */
        //  1662: aload_3         /* value2 */
        //  1663: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1666: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1669: aload_0         /* this */
        //  1670: aload_2         /* interpreter */
        //  1671: aload_1         /* insn */
        //  1672: aload           value1
        //  1674: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1677: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1680: aload_0         /* this */
        //  1681: aload           7
        //  1683: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1686: aload_0         /* this */
        //  1687: aload_3         /* value2 */
        //  1688: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1691: aload_0         /* this */
        //  1692: aload           value1
        //  1694: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1697: goto            2542
        //  1700: aload_0         /* this */
        //  1701: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1704: astore_3        /* value2 */
        //  1705: aload_3         /* value2 */
        //  1706: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1711: iconst_1       
        //  1712: if_icmpne       1763
        //  1715: aload_0         /* this */
        //  1716: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1719: astore          value3
        //  1721: aload           value3
        //  1723: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1728: iconst_1       
        //  1729: if_icmpne       1788
        //  1732: aload_0         /* this */
        //  1733: aload_2         /* interpreter */
        //  1734: aload_1         /* insn */
        //  1735: aload           value1
        //  1737: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1740: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1743: aload_0         /* this */
        //  1744: aload           value3
        //  1746: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1749: aload_0         /* this */
        //  1750: aload_3         /* value2 */
        //  1751: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1754: aload_0         /* this */
        //  1755: aload           value1
        //  1757: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1760: goto            2542
        //  1763: aload_0         /* this */
        //  1764: aload_2         /* interpreter */
        //  1765: aload_1         /* insn */
        //  1766: aload           value1
        //  1768: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1771: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1774: aload_0         /* this */
        //  1775: aload_3         /* value2 */
        //  1776: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1779: aload_0         /* this */
        //  1780: aload           value1
        //  1782: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1785: goto            2542
        //  1788: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1791: dup            
        //  1792: aload_1         /* insn */
        //  1793: ldc             "Illegal use of DUP2_X2"
        //  1795: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1798: athrow         
        //  1799: aload_0         /* this */
        //  1800: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1803: astore_3        /* value2 */
        //  1804: aload_0         /* this */
        //  1805: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1808: astore          value1
        //  1810: aload           value1
        //  1812: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1817: iconst_1       
        //  1818: if_icmpne       1831
        //  1821: aload_3         /* value2 */
        //  1822: invokeinterface org/spongepowered/asm/lib/tree/analysis/Value.getSize:()I
        //  1827: iconst_1       
        //  1828: if_icmpeq       1842
        //  1831: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  1834: dup            
        //  1835: aload_1         /* insn */
        //  1836: ldc             "Illegal use of SWAP"
        //  1838: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  1841: athrow         
        //  1842: aload_0         /* this */
        //  1843: aload_2         /* interpreter */
        //  1844: aload_1         /* insn */
        //  1845: aload_3         /* value2 */
        //  1846: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1849: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1852: aload_0         /* this */
        //  1853: aload_2         /* interpreter */
        //  1854: aload_1         /* insn */
        //  1855: aload           value1
        //  1857: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.copyOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1860: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1863: goto            2542
        //  1866: aload_0         /* this */
        //  1867: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1870: astore_3        /* value2 */
        //  1871: aload_0         /* this */
        //  1872: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1875: astore          value1
        //  1877: aload_0         /* this */
        //  1878: aload_2         /* interpreter */
        //  1879: aload_1         /* insn */
        //  1880: aload           value1
        //  1882: aload_3         /* value2 */
        //  1883: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1886: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1889: goto            2542
        //  1892: aload_0         /* this */
        //  1893: aload_2         /* interpreter */
        //  1894: aload_1         /* insn */
        //  1895: aload_0         /* this */
        //  1896: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1899: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1902: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1905: goto            2542
        //  1908: aload_0         /* this */
        //  1909: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1912: astore_3        /* value2 */
        //  1913: aload_0         /* this */
        //  1914: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1917: astore          value1
        //  1919: aload_0         /* this */
        //  1920: aload_2         /* interpreter */
        //  1921: aload_1         /* insn */
        //  1922: aload           value1
        //  1924: aload_3         /* value2 */
        //  1925: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1928: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1931: goto            2542
        //  1934: aload_1         /* insn */
        //  1935: checkcast       Lorg/spongepowered/asm/lib/tree/IincInsnNode;
        //  1938: getfield        org/spongepowered/asm/lib/tree/IincInsnNode.var:I
        //  1941: istore          var
        //  1943: aload_0         /* this */
        //  1944: iload           var
        //  1946: aload_2         /* interpreter */
        //  1947: aload_1         /* insn */
        //  1948: aload_0         /* this */
        //  1949: iload           var
        //  1951: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.getLocal:(I)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1954: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1957: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.setLocal:(ILorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1960: goto            2542
        //  1963: aload_0         /* this */
        //  1964: aload_2         /* interpreter */
        //  1965: aload_1         /* insn */
        //  1966: aload_0         /* this */
        //  1967: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1970: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1973: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  1976: goto            2542
        //  1979: aload_0         /* this */
        //  1980: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1983: astore_3        /* value2 */
        //  1984: aload_0         /* this */
        //  1985: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1988: astore          value1
        //  1990: aload_0         /* this */
        //  1991: aload_2         /* interpreter */
        //  1992: aload_1         /* insn */
        //  1993: aload           value1
        //  1995: aload_3         /* value2 */
        //  1996: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  1999: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2002: goto            2542
        //  2005: aload_2         /* interpreter */
        //  2006: aload_1         /* insn */
        //  2007: aload_0         /* this */
        //  2008: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2011: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2014: pop            
        //  2015: goto            2542
        //  2018: aload_0         /* this */
        //  2019: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2022: astore_3        /* value2 */
        //  2023: aload_0         /* this */
        //  2024: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2027: astore          value1
        //  2029: aload_2         /* interpreter */
        //  2030: aload_1         /* insn */
        //  2031: aload           value1
        //  2033: aload_3         /* value2 */
        //  2034: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2037: pop            
        //  2038: goto            2542
        //  2041: goto            2542
        //  2044: aload_0         /* this */
        //  2045: aload_2         /* interpreter */
        //  2046: aload_1         /* insn */
        //  2047: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2050: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2053: goto            2542
        //  2056: goto            2542
        //  2059: aload_2         /* interpreter */
        //  2060: aload_1         /* insn */
        //  2061: aload_0         /* this */
        //  2062: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2065: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2068: pop            
        //  2069: goto            2542
        //  2072: aload_0         /* this */
        //  2073: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2076: astore          value1
        //  2078: aload_2         /* interpreter */
        //  2079: aload_1         /* insn */
        //  2080: aload           value1
        //  2082: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2085: pop            
        //  2086: aload_2         /* interpreter */
        //  2087: aload_1         /* insn */
        //  2088: aload           value1
        //  2090: aload_0         /* this */
        //  2091: getfield        org/spongepowered/asm/lib/tree/analysis/Frame.returnValue:Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2094: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.returnOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2097: goto            2542
        //  2100: aload_0         /* this */
        //  2101: getfield        org/spongepowered/asm/lib/tree/analysis/Frame.returnValue:Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2104: ifnull          2542
        //  2107: new             Lorg/spongepowered/asm/lib/tree/analysis/AnalyzerException;
        //  2110: dup            
        //  2111: aload_1         /* insn */
        //  2112: ldc             "Incompatible return type"
        //  2114: invokespecial   org/spongepowered/asm/lib/tree/analysis/AnalyzerException.<init>:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/lang/String;)V
        //  2117: athrow         
        //  2118: aload_0         /* this */
        //  2119: aload_2         /* interpreter */
        //  2120: aload_1         /* insn */
        //  2121: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2124: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2127: goto            2542
        //  2130: aload_2         /* interpreter */
        //  2131: aload_1         /* insn */
        //  2132: aload_0         /* this */
        //  2133: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2136: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2139: pop            
        //  2140: goto            2542
        //  2143: aload_0         /* this */
        //  2144: aload_2         /* interpreter */
        //  2145: aload_1         /* insn */
        //  2146: aload_0         /* this */
        //  2147: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2150: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2153: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2156: goto            2542
        //  2159: aload_0         /* this */
        //  2160: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2163: astore_3        /* value2 */
        //  2164: aload_0         /* this */
        //  2165: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2168: astore          value1
        //  2170: aload_2         /* interpreter */
        //  2171: aload_1         /* insn */
        //  2172: aload           value1
        //  2174: aload_3         /* value2 */
        //  2175: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.binaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2178: pop            
        //  2179: goto            2542
        //  2182: new             Ljava/util/ArrayList;
        //  2185: dup            
        //  2186: invokespecial   java/util/ArrayList.<init>:()V
        //  2189: astore          values
        //  2191: aload_1         /* insn */
        //  2192: checkcast       Lorg/spongepowered/asm/lib/tree/MethodInsnNode;
        //  2195: getfield        org/spongepowered/asm/lib/tree/MethodInsnNode.desc:Ljava/lang/String;
        //  2198: astore          desc
        //  2200: aload           desc
        //  2202: invokestatic    org/spongepowered/asm/lib/Type.getArgumentTypes:(Ljava/lang/String;)[Lorg/spongepowered/asm/lib/Type;
        //  2205: arraylength    
        //  2206: istore          i
        //  2208: iload           i
        //  2210: ifle            2231
        //  2213: aload           values
        //  2215: iconst_0       
        //  2216: aload_0         /* this */
        //  2217: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2220: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //  2225: iinc            i, -1
        //  2228: goto            2208
        //  2231: aload_1         /* insn */
        //  2232: invokevirtual   org/spongepowered/asm/lib/tree/AbstractInsnNode.getOpcode:()I
        //  2235: sipush          184
        //  2238: if_icmpeq       2253
        //  2241: aload           values
        //  2243: iconst_0       
        //  2244: aload_0         /* this */
        //  2245: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2248: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //  2253: aload           desc
        //  2255: invokestatic    org/spongepowered/asm/lib/Type.getReturnType:(Ljava/lang/String;)Lorg/spongepowered/asm/lib/Type;
        //  2258: getstatic       org/spongepowered/asm/lib/Type.VOID_TYPE:Lorg/spongepowered/asm/lib/Type;
        //  2261: if_acmpne       2275
        //  2264: aload_2         /* interpreter */
        //  2265: aload_1         /* insn */
        //  2266: aload           values
        //  2268: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.naryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/util/List;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2271: pop            
        //  2272: goto            2542
        //  2275: aload_0         /* this */
        //  2276: aload_2         /* interpreter */
        //  2277: aload_1         /* insn */
        //  2278: aload           values
        //  2280: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.naryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/util/List;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2283: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2286: goto            2542
        //  2289: new             Ljava/util/ArrayList;
        //  2292: dup            
        //  2293: invokespecial   java/util/ArrayList.<init>:()V
        //  2296: astore          values
        //  2298: aload_1         /* insn */
        //  2299: checkcast       Lorg/spongepowered/asm/lib/tree/InvokeDynamicInsnNode;
        //  2302: getfield        org/spongepowered/asm/lib/tree/InvokeDynamicInsnNode.desc:Ljava/lang/String;
        //  2305: astore          desc
        //  2307: aload           desc
        //  2309: invokestatic    org/spongepowered/asm/lib/Type.getArgumentTypes:(Ljava/lang/String;)[Lorg/spongepowered/asm/lib/Type;
        //  2312: arraylength    
        //  2313: istore          i
        //  2315: iload           i
        //  2317: ifle            2338
        //  2320: aload           values
        //  2322: iconst_0       
        //  2323: aload_0         /* this */
        //  2324: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2327: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //  2332: iinc            i, -1
        //  2335: goto            2315
        //  2338: aload           desc
        //  2340: invokestatic    org/spongepowered/asm/lib/Type.getReturnType:(Ljava/lang/String;)Lorg/spongepowered/asm/lib/Type;
        //  2343: getstatic       org/spongepowered/asm/lib/Type.VOID_TYPE:Lorg/spongepowered/asm/lib/Type;
        //  2346: if_acmpne       2360
        //  2349: aload_2         /* interpreter */
        //  2350: aload_1         /* insn */
        //  2351: aload           values
        //  2353: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.naryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/util/List;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2356: pop            
        //  2357: goto            2542
        //  2360: aload_0         /* this */
        //  2361: aload_2         /* interpreter */
        //  2362: aload_1         /* insn */
        //  2363: aload           values
        //  2365: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.naryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/util/List;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2368: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2371: goto            2542
        //  2374: aload_0         /* this */
        //  2375: aload_2         /* interpreter */
        //  2376: aload_1         /* insn */
        //  2377: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.newOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2380: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2383: goto            2542
        //  2386: aload_0         /* this */
        //  2387: aload_2         /* interpreter */
        //  2388: aload_1         /* insn */
        //  2389: aload_0         /* this */
        //  2390: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2393: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2396: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2399: goto            2542
        //  2402: aload_2         /* interpreter */
        //  2403: aload_1         /* insn */
        //  2404: aload_0         /* this */
        //  2405: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2408: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2411: pop            
        //  2412: goto            2542
        //  2415: aload_0         /* this */
        //  2416: aload_2         /* interpreter */
        //  2417: aload_1         /* insn */
        //  2418: aload_0         /* this */
        //  2419: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2422: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2425: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2428: goto            2542
        //  2431: aload_2         /* interpreter */
        //  2432: aload_1         /* insn */
        //  2433: aload_0         /* this */
        //  2434: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2437: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2440: pop            
        //  2441: goto            2542
        //  2444: new             Ljava/util/ArrayList;
        //  2447: dup            
        //  2448: invokespecial   java/util/ArrayList.<init>:()V
        //  2451: astore          values
        //  2453: aload_1         /* insn */
        //  2454: checkcast       Lorg/spongepowered/asm/lib/tree/MultiANewArrayInsnNode;
        //  2457: getfield        org/spongepowered/asm/lib/tree/MultiANewArrayInsnNode.dims:I
        //  2460: istore          i
        //  2462: iload           i
        //  2464: ifle            2485
        //  2467: aload           values
        //  2469: iconst_0       
        //  2470: aload_0         /* this */
        //  2471: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2474: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //  2479: iinc            i, -1
        //  2482: goto            2462
        //  2485: aload_0         /* this */
        //  2486: aload_2         /* interpreter */
        //  2487: aload_1         /* insn */
        //  2488: aload           values
        //  2490: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.naryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Ljava/util/List;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2493: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.push:(Lorg/spongepowered/asm/lib/tree/analysis/Value;)V
        //  2496: goto            2542
        //  2499: aload_2         /* interpreter */
        //  2500: aload_1         /* insn */
        //  2501: aload_0         /* this */
        //  2502: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Frame.pop:()Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2505: invokevirtual   org/spongepowered/asm/lib/tree/analysis/Interpreter.unaryOperation:(Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Value;)Lorg/spongepowered/asm/lib/tree/analysis/Value;
        //  2508: pop            
        //  2509: goto            2542
        //  2512: new             Ljava/lang/RuntimeException;
        //  2515: dup            
        //  2516: new             Ljava/lang/StringBuilder;
        //  2519: dup            
        //  2520: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2523: ldc             "Illegal opcode "
        //  2525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2528: aload_1         /* insn */
        //  2529: invokevirtual   org/spongepowered/asm/lib/tree/AbstractInsnNode.getOpcode:()I
        //  2532: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  2535: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2538: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //  2541: athrow         
        //  2542: return         
        //    Exceptions:
        //  throws org.spongepowered.asm.lib.tree.analysis.AnalyzerException
        //    Signature:
        //  (Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;Lorg/spongepowered/asm/lib/tree/analysis/Interpreter<TV;>;)V
        //    StackMap: 00 46 03 34 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 37 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 43 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 5A 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 74 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 A8 00 06 07 00 02 07 00 66 07 00 6B 00 07 00 16 01 00 00 03 D4 00 07 07 00 02 07 00 66 07 00 6B 00 07 00 16 01 07 00 16 00 00 03 D7 00 03 07 00 02 07 00 66 07 00 6B 00 00 03 F6 00 03 07 00 02 07 00 66 07 00 6B 00 00 04 0E 00 03 07 00 02 07 00 66 07 00 6B 00 00 04 33 00 03 07 00 02 07 00 66 07 00 6B 00 00 04 4F 00 05 07 00 02 07 00 66 07 00 6B 00 07 00 16 00 00 04 63 00 03 07 00 02 07 00 66 07 00 6B 00 00 04 83 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 04 8E 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 04 A7 00 03 07 00 02 07 00 66 07 00 6B 00 00 04 F7 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 05 10 00 05 07 00 02 07 00 66 07 00 6B 00 07 00 16 00 00 05 1B 00 03 07 00 02 07 00 66 07 00 6B 00 00 05 5E 00 05 07 00 02 07 00 66 07 00 6B 00 07 00 16 00 00 05 72 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 05 7D 00 03 07 00 02 07 00 66 07 00 6B 00 00 05 D7 00 05 07 00 02 07 00 66 07 00 6B 00 07 00 16 00 00 05 FF 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 06 0A 00 03 07 00 02 07 00 66 07 00 6B 00 00 06 7B 00 08 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 07 00 16 00 00 06 A4 00 05 07 00 02 07 00 66 07 00 6B 00 07 00 16 00 00 06 E3 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 06 FC 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 07 07 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 27 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 07 32 00 05 07 00 02 07 00 66 07 00 6B 07 00 16 07 00 16 00 00 07 4A 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 64 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 74 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 8E 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 AB 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 BB 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 D5 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 E2 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 F9 00 03 07 00 02 07 00 66 07 00 6B 00 00 07 FC 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 08 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 0B 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 18 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 34 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 46 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 52 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 5F 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 6F 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 86 00 03 07 00 02 07 00 66 07 00 6B 00 00 08 A0 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 08 B7 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 08 CD 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 08 E3 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 08 F1 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 0B 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 09 22 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 09 38 00 0B 07 00 02 07 00 66 07 00 6B 00 00 00 07 00 C1 00 00 07 00 B2 01 00 00 09 46 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 52 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 62 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 6F 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 7F 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 8C 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 9E 00 0A 07 00 02 07 00 66 07 00 6B 00 00 00 01 00 00 07 00 B2 00 00 09 B5 00 0A 07 00 02 07 00 66 07 00 6B 00 00 00 01 00 00 07 00 B2 00 00 09 C3 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 D0 00 03 07 00 02 07 00 66 07 00 6B 00 00 09 EE 00 03 07 00 02 07 00 66 07 00 6B 00 00
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2895)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean merge(final Frame<? extends V> frame, final Interpreter<V> interpreter) throws AnalyzerException {
        if (this.top != frame.top) {
            throw new AnalyzerException((AbstractInsnNode)null, "Incompatible stack heights");
        }
        boolean changes = false;
        for (int i = 0; i < this.locals + this.top; ++i) {
            final V v = interpreter.merge(this.values[i], (V)frame.values[i]);
            if (!v.equals(this.values[i])) {
                this.values[i] = v;
                changes = true;
            }
        }
        return changes;
    }
    
    public boolean merge(final Frame<? extends V> frame, final boolean[] access) {
        boolean changes = false;
        for (int i = 0; i < this.locals; ++i) {
            if (!access[i] && !this.values[i].equals(frame.values[i])) {
                this.values[i] = (V)frame.values[i];
                changes = true;
            }
        }
        return changes;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getLocals(); ++i) {
            sb.append(this.getLocal(i));
        }
        sb.append(' ');
        for (int i = 0; i < this.getStackSize(); ++i) {
            sb.append(this.getStack(i).toString());
        }
        return sb.toString();
    }
}
