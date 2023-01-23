//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import org.objectweb.asm.*;
import net.minecraft.launchwrapper.*;
import java.io.*;
import org.objectweb.asm.commons.*;
import xyz.Melody.Performance.FoamFix.shared.*;
import java.util.*;
import com.google.common.collect.*;

public class FoamFixTransformer implements IClassTransformer
{
    private static final Multimap<String, TransformerFunction> transformFunctions;
    
    public static byte[] replaceConstructor(final byte[] data, final String className, final String from, final String to, final String... methods) {
        final ClassReader reader = new ClassReader(data);
        final ClassWriter writer = new ClassWriter(0);
        reader.accept(new FoamFixConstructorReplacer(from, to, methods).getClassVisitor(327680, (ClassVisitor)writer), 0);
        return writer.toByteArray();
    }
    
    public static byte[] spliceClasses(final byte[] data, final String className, final String targetClassName, final String... methods) {
        try {
            final byte[] dataSplice = ((LaunchClassLoader)FoamFixTransformer.class.getClassLoader()).getClassBytes(className);
            return spliceClasses(data, dataSplice, className, targetClassName, methods);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static byte[] spliceClasses(final byte[] data, final byte[] dataSplice, final String className, final String targetClassName, final String... methods) {
        final class lIIl extends Remapper
        {
            final /* synthetic */ String val$className2;
            final /* synthetic */ String val$targetClassName2;
            
            lIIl(final String val$className2, final String val$targetClassName2) {
                this.val$className2 = val$className2;
                this.val$targetClassName2 = val$targetClassName2;
            }
            
            public String map(final String name) {
                return this.val$className2.equals(name) ? this.val$targetClassName2 : name;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       36
        //     4: new             Ljava/lang/RuntimeException;
        //     7: dup            
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc             "Class "
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_2         /* className */
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: ldc             " not found! This is a FoamFix bug!"
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    35: athrow         
        //    36: aload           methods
        //    38: invokestatic    com/google/common/collect/Sets.newHashSet:([Ljava/lang/Object;)Ljava/util/HashSet;
        //    41: astore          methodSet
        //    43: aload           methods
        //    45: invokestatic    com/google/common/collect/Lists.newArrayList:([Ljava/lang/Object;)Ljava/util/ArrayList;
        //    48: astore          methodList
        //    50: new             Lorg/objectweb/asm/ClassReader;
        //    53: dup            
        //    54: aload_0         /* data */
        //    55: invokespecial   org/objectweb/asm/ClassReader.<init>:([B)V
        //    58: astore          readerData
        //    60: new             Lorg/objectweb/asm/ClassReader;
        //    63: dup            
        //    64: aload_1         /* dataSplice */
        //    65: invokespecial   org/objectweb/asm/ClassReader.<init>:([B)V
        //    68: astore          readerSplice
        //    70: new             Lorg/objectweb/asm/ClassWriter;
        //    73: dup            
        //    74: iconst_0       
        //    75: invokespecial   org/objectweb/asm/ClassWriter.<init>:(I)V
        //    78: astore          writer
        //    80: aload_2         /* className */
        //    81: bipush          46
        //    83: bipush          47
        //    85: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //    88: astore          className2
        //    90: aload_3         /* targetClassName */
        //    91: bipush          46
        //    93: bipush          47
        //    95: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //    98: astore          targetClassName2
        //   100: new             Lxyz/Melody/Performance/FoamFix/coremod/lIIl;
        //   103: dup            
        //   104: aload           className2
        //   106: aload           targetClassName2
        //   108: invokespecial   xyz/Melody/Performance/FoamFix/coremod/lIIl.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   111: astore          remapper
        //   113: new             Lorg/objectweb/asm/tree/ClassNode;
        //   116: dup            
        //   117: invokespecial   org/objectweb/asm/tree/ClassNode.<init>:()V
        //   120: astore          nodeData
        //   122: new             Lorg/objectweb/asm/tree/ClassNode;
        //   125: dup            
        //   126: invokespecial   org/objectweb/asm/tree/ClassNode.<init>:()V
        //   129: astore          nodeSplice
        //   131: aload           readerData
        //   133: aload           nodeData
        //   135: iconst_0       
        //   136: invokevirtual   org/objectweb/asm/ClassReader.accept:(Lorg/objectweb/asm/ClassVisitor;I)V
        //   139: aload           readerSplice
        //   141: new             Lorg/objectweb/asm/commons/RemappingClassAdapter;
        //   144: dup            
        //   145: aload           nodeSplice
        //   147: aload           remapper
        //   149: invokespecial   org/objectweb/asm/commons/RemappingClassAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;Lorg/objectweb/asm/commons/Remapper;)V
        //   152: bipush          8
        //   154: invokevirtual   org/objectweb/asm/ClassReader.accept:(Lorg/objectweb/asm/ClassVisitor;I)V
        //   157: aload           nodeSplice
        //   159: getfield        org/objectweb/asm/tree/ClassNode.interfaces:Ljava/util/List;
        //   162: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   167: astore          15
        //   169: aload           15
        //   171: invokeinterface java/util/Iterator.hasNext:()Z
        //   176: ifeq            243
        //   179: aload           15
        //   181: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   186: checkcast       Ljava/lang/String;
        //   189: astore          s
        //   191: aload           s
        //   193: ldc             "IFoamFix"
        //   195: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   198: ifeq            240
        //   201: aload           nodeData
        //   203: getfield        org/objectweb/asm/tree/ClassNode.interfaces:Ljava/util/List;
        //   206: aload           s
        //   208: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   213: pop            
        //   214: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   217: new             Ljava/lang/StringBuilder;
        //   220: dup            
        //   221: invokespecial   java/lang/StringBuilder.<init>:()V
        //   224: ldc             "Added INTERFACE: "
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: aload           s
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   237: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   240: goto            169
        //   243: iconst_0       
        //   244: istore          i
        //   246: iload           i
        //   248: aload           nodeSplice
        //   250: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   253: invokeinterface java/util/List.size:()I
        //   258: if_icmpge       593
        //   261: aload           methodSet
        //   263: aload           nodeSplice
        //   265: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   268: iload           i
        //   270: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   275: checkcast       Lorg/objectweb/asm/tree/MethodNode;
        //   278: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   281: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   286: ifeq            587
        //   289: aload           nodeSplice
        //   291: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   294: iload           i
        //   296: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   301: checkcast       Lorg/objectweb/asm/tree/MethodNode;
        //   304: astore          mn
        //   306: iconst_0       
        //   307: istore          added
        //   309: iconst_0       
        //   310: istore          j
        //   312: iload           j
        //   314: aload           nodeData
        //   316: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   319: invokeinterface java/util/List.size:()I
        //   324: if_icmpge       528
        //   327: aload           nodeData
        //   329: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   332: iload           j
        //   334: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   339: checkcast       Lorg/objectweb/asm/tree/MethodNode;
        //   342: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   345: aload           mn
        //   347: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   350: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   353: ifeq            522
        //   356: aload           nodeData
        //   358: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   361: iload           j
        //   363: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   368: checkcast       Lorg/objectweb/asm/tree/MethodNode;
        //   371: getfield        org/objectweb/asm/tree/MethodNode.desc:Ljava/lang/String;
        //   374: aload           mn
        //   376: getfield        org/objectweb/asm/tree/MethodNode.desc:Ljava/lang/String;
        //   379: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   382: ifeq            522
        //   385: aload           nodeData
        //   387: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   390: iload           j
        //   392: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   397: checkcast       Lorg/objectweb/asm/tree/MethodNode;
        //   400: astore          oldMn
        //   402: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   405: new             Ljava/lang/StringBuilder;
        //   408: dup            
        //   409: invokespecial   java/lang/StringBuilder.<init>:()V
        //   412: ldc             "Spliced in METHOD: "
        //   414: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   417: aload_3         /* targetClassName */
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: ldc             "."
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: aload           mn
        //   428: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   431: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   434: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   437: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   440: aload           nodeData
        //   442: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   445: iload           j
        //   447: aload           mn
        //   449: invokeinterface java/util/List.set:(ILjava/lang/Object;)Ljava/lang/Object;
        //   454: pop            
        //   455: aload           oldMn
        //   457: new             Ljava/lang/StringBuilder;
        //   460: dup            
        //   461: invokespecial   java/lang/StringBuilder.<init>:()V
        //   464: aload           methodList
        //   466: aload           methodList
        //   468: aload           oldMn
        //   470: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   473: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   478: bipush          -2
        //   480: iand           
        //   481: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   486: checkcast       Ljava/lang/String;
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: ldc             "_foamfix_old"
        //   494: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   497: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   500: putfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   503: aload           nodeData
        //   505: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   508: aload           oldMn
        //   510: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   515: pop            
        //   516: iconst_1       
        //   517: istore          added
        //   519: goto            528
        //   522: iinc            j, 1
        //   525: goto            312
        //   528: iload           added
        //   530: ifne            587
        //   533: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   536: new             Ljava/lang/StringBuilder;
        //   539: dup            
        //   540: invokespecial   java/lang/StringBuilder.<init>:()V
        //   543: ldc             "Added METHOD: "
        //   545: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   548: aload_3         /* targetClassName */
        //   549: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   552: ldc             "."
        //   554: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   557: aload           mn
        //   559: getfield        org/objectweb/asm/tree/MethodNode.name:Ljava/lang/String;
        //   562: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   568: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   571: aload           nodeData
        //   573: getfield        org/objectweb/asm/tree/ClassNode.methods:Ljava/util/List;
        //   576: aload           mn
        //   578: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   583: pop            
        //   584: iconst_1       
        //   585: istore          added
        //   587: iinc            i, 1
        //   590: goto            246
        //   593: iconst_0       
        //   594: istore          i
        //   596: iload           i
        //   598: aload           nodeSplice
        //   600: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   603: invokeinterface java/util/List.size:()I
        //   608: if_icmpge       865
        //   611: aload           methodSet
        //   613: aload           nodeSplice
        //   615: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   618: iload           i
        //   620: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   625: checkcast       Lorg/objectweb/asm/tree/FieldNode;
        //   628: getfield        org/objectweb/asm/tree/FieldNode.name:Ljava/lang/String;
        //   631: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   636: ifeq            859
        //   639: aload           nodeSplice
        //   641: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   644: iload           i
        //   646: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   651: checkcast       Lorg/objectweb/asm/tree/FieldNode;
        //   654: astore          mn
        //   656: iconst_0       
        //   657: istore          added
        //   659: iconst_0       
        //   660: istore          j
        //   662: iload           j
        //   664: aload           nodeData
        //   666: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   669: invokeinterface java/util/List.size:()I
        //   674: if_icmpge       800
        //   677: aload           nodeData
        //   679: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   682: iload           j
        //   684: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   689: checkcast       Lorg/objectweb/asm/tree/FieldNode;
        //   692: getfield        org/objectweb/asm/tree/FieldNode.name:Ljava/lang/String;
        //   695: aload           mn
        //   697: getfield        org/objectweb/asm/tree/FieldNode.name:Ljava/lang/String;
        //   700: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   703: ifeq            794
        //   706: aload           nodeData
        //   708: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   711: iload           j
        //   713: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   718: checkcast       Lorg/objectweb/asm/tree/FieldNode;
        //   721: getfield        org/objectweb/asm/tree/FieldNode.desc:Ljava/lang/String;
        //   724: aload           mn
        //   726: getfield        org/objectweb/asm/tree/FieldNode.desc:Ljava/lang/String;
        //   729: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   732: ifeq            794
        //   735: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   738: new             Ljava/lang/StringBuilder;
        //   741: dup            
        //   742: invokespecial   java/lang/StringBuilder.<init>:()V
        //   745: ldc             "Spliced in FIELD: "
        //   747: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   750: aload_3         /* targetClassName */
        //   751: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   754: ldc             "."
        //   756: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   759: aload           mn
        //   761: getfield        org/objectweb/asm/tree/FieldNode.name:Ljava/lang/String;
        //   764: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   767: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   770: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   773: aload           nodeData
        //   775: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   778: iload           j
        //   780: aload           mn
        //   782: invokeinterface java/util/List.set:(ILjava/lang/Object;)Ljava/lang/Object;
        //   787: pop            
        //   788: iconst_1       
        //   789: istore          added
        //   791: goto            800
        //   794: iinc            j, 1
        //   797: goto            662
        //   800: iload           added
        //   802: ifne            859
        //   805: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   808: new             Ljava/lang/StringBuilder;
        //   811: dup            
        //   812: invokespecial   java/lang/StringBuilder.<init>:()V
        //   815: ldc             "Added FIELD: "
        //   817: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   820: aload_3         /* targetClassName */
        //   821: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   824: ldc             "."
        //   826: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: aload           mn
        //   831: getfield        org/objectweb/asm/tree/FieldNode.name:Ljava/lang/String;
        //   834: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   837: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   840: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   843: aload           nodeData
        //   845: getfield        org/objectweb/asm/tree/ClassNode.fields:Ljava/util/List;
        //   848: aload           mn
        //   850: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   855: pop            
        //   856: iconst_1       
        //   857: istore          added
        //   859: iinc            i, 1
        //   862: goto            596
        //   865: aload           nodeData
        //   867: aload           writer
        //   869: invokevirtual   org/objectweb/asm/tree/ClassNode.accept:(Lorg/objectweb/asm/ClassVisitor;)V
        //   872: aload           writer
        //   874: invokevirtual   org/objectweb/asm/ClassWriter.toByteArray:()[B
        //   877: areturn        
        //    StackMapTable: 00 10 24 FF 00 84 00 10 07 00 9A 07 00 9A 07 00 80 07 00 80 07 00 9B 07 00 9D 07 00 9F 07 00 1E 07 00 1E 07 00 23 07 00 80 07 00 80 07 00 11 07 00 89 07 00 89 07 00 A1 00 00 FC 00 46 07 00 80 FA 00 02 FF 00 02 00 10 07 00 9A 07 00 9A 07 00 80 07 00 80 07 00 9B 07 00 9D 07 00 9F 07 00 1E 07 00 1E 07 00 23 07 00 80 07 00 80 07 00 11 07 00 89 07 00 89 01 00 00 FE 00 41 07 00 CC 01 01 FB 00 D1 05 F8 00 3A 05 02 FE 00 41 07 00 EE 01 01 FB 00 83 05 F8 00 3A 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void init() {
        final class lll implements TransformerFunction
        {
            @Override
            public byte[] transform(final byte[] data, final String transformedName) {
                return FoamFixTransformer.spliceClasses(data, "xyz.Melody.FoamFix.common.FoamyBlockStateContainer", transformedName, "createState", "createState");
            }
        }
        final class llI implements TransformerFunction
        {
            @Override
            public byte[] transform(final byte[] data, final String transformedName) {
                return FoamFixTransformer.spliceClasses(data, "xyz.Melody.FoamFix.common.FoamyExtendedBlockStateContainer", transformedName, "createState", "createState");
            }
        }
        final class lIl implements TransformerFunction
        {
            @Override
            public byte[] transform(final byte[] data, final String transformedName) {
                return FoamFixTransformer.spliceClasses(data, "xyz.Melody.FoamFix.client.FoamFixDynamicItemModels", transformedName, "bake", "bake");
            }
        }
        final class lIII implements TransformerFunction
        {
            @Override
            public byte[] transform(final byte[] data, final String transformedName) {
                final ClassReader reader = new ClassReader(data);
                final ClassWriter writer = new ClassWriter(0);
                reader.accept(new FoamFixReplaceClassSimpleName(new String[] { "updateEntities", "updateEntities" }).getClassVisitor(327680, (ClassVisitor)writer), 0);
                return writer.toByteArray();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        xyz/Melody/Performance/FoamFix/shared/FoamFixConfig.geSmallPropertyStorage:Z
        //     6: ifeq            47
        //     9: getstatic       xyz/Melody/Performance/FoamFix/coremod/FoamFixTransformer.transformFunctions:Lcom/google/common/collect/Multimap;
        //    12: ldc_w           "net.minecraft.block.state.BlockStateContainer"
        //    15: new             Lxyz/Melody/Performance/FoamFix/coremod/lll;
        //    18: dup            
        //    19: invokespecial   xyz/Melody/Performance/FoamFix/coremod/lll.<init>:()V
        //    22: invokeinterface com/google/common/collect/Multimap.put:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    27: pop            
        //    28: getstatic       xyz/Melody/Performance/FoamFix/coremod/FoamFixTransformer.transformFunctions:Lcom/google/common/collect/Multimap;
        //    31: ldc_w           "net.minecraftforge.common.property.ExtendedBlockState"
        //    34: new             Lxyz/Melody/Performance/FoamFix/coremod/llI;
        //    37: dup            
        //    38: invokespecial   xyz/Melody/Performance/FoamFix/coremod/llI.<init>:()V
        //    41: invokeinterface com/google/common/collect/Multimap.put:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    46: pop            
        //    47: getstatic       xyz/Melody/Performance/FoamFix/shared/FoamFixShared.config:Lxyz/Melody/Performance/FoamFix/shared/FoamFixConfig;
        //    50: getfield        xyz/Melody/Performance/FoamFix/shared/FoamFixConfig.clDynamicItemModels:Z
        //    53: ifeq            75
        //    56: getstatic       xyz/Melody/Performance/FoamFix/coremod/FoamFixTransformer.transformFunctions:Lcom/google/common/collect/Multimap;
        //    59: ldc_w           "net.minecraftforge.client.model.ItemLayerModel"
        //    62: new             Lxyz/Melody/Performance/FoamFix/coremod/lIl;
        //    65: dup            
        //    66: invokespecial   xyz/Melody/Performance/FoamFix/coremod/lIl.<init>:()V
        //    69: invokeinterface com/google/common/collect/Multimap.put:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    74: pop            
        //    75: getstatic       xyz/Melody/Performance/FoamFix/shared/FoamFixShared.config:Lxyz/Melody/Performance/FoamFix/shared/FoamFixConfig;
        //    78: getfield        xyz/Melody/Performance/FoamFix/shared/FoamFixConfig.geReplaceSimpleName:Z
        //    81: ifeq            103
        //    84: getstatic       xyz/Melody/Performance/FoamFix/coremod/FoamFixTransformer.transformFunctions:Lcom/google/common/collect/Multimap;
        //    87: ldc_w           "net.minecraft.world.World"
        //    90: new             Lxyz/Melody/Performance/FoamFix/coremod/lIII;
        //    93: dup            
        //    94: invokespecial   xyz/Melody/Performance/FoamFix/coremod/lIII.<init>:()V
        //    97: invokeinterface com/google/common/collect/Multimap.put:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   102: pop            
        //   103: return         
        //    StackMapTable: 00 03 2F 1B 1B
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public byte[] transform(final String name, final String transformedName, final byte[] dataOrig) {
        if (dataOrig == null) {
            return null;
        }
        byte[] data = dataOrig;
        if (FoamFixShared.config.geBlockPosPatch) {
            if ("net.minecraft.util.math.Vec3i".equals(transformedName)) {
                data = BlockPosPatch.patchVec3i(data);
            }
            else {
                data = BlockPosPatch.patchOtherClass(data, "net.minecraft.util.math.BlockPos$MutableBlockPos".equals(transformedName));
            }
        }
        for (final TransformerFunction function : FoamFixTransformer.transformFunctions.get((Object)transformedName)) {
            data = function.transform(data, transformedName);
        }
        return data;
    }
    
    static {
        transformFunctions = (Multimap)HashMultimap.create();
    }
}
