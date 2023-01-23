//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.lang.ref.*;
import java.util.*;

public class ObjectRegistry
{
    private static Thread cleanUpThread;
    private static Set<ObjectRegistry> registrySet;
    private static int nextThreadNumber;
    private int nextInstanceID;
    private Map<Integer, WeakReference<Object>> instanceIDToObjectReferenceMap;
    private static ObjectRegistry registry;
    
    private static void startThread(final ObjectRegistry objectRegistry) {
        final class lIIll extends Thread
        {
            lIIll(final String x0) {
                super(x0);
            }
            
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                    final ObjectRegistry[] registries;
                    synchronized (ObjectRegistry.registrySet) {
                        registries = ObjectRegistry.registrySet.toArray(new ObjectRegistry[0]);
                    }
                    for (final ObjectRegistry registry : registries) {
                        synchronized (registry) {
                            for (final Integer instanceID : (Integer[])registry.instanceIDToObjectReferenceMap.keySet().toArray(new Integer[0])) {
                                if (registry.instanceIDToObjectReferenceMap.get(instanceID).get() == null) {
                                    registry.instanceIDToObjectReferenceMap.remove(instanceID);
                                }
                            }
                            if (registry.instanceIDToObjectReferenceMap.isEmpty()) {
                                synchronized (ObjectRegistry.registrySet) {
                                    ObjectRegistry.registrySet.remove(registry);
                                }
                            }
                        }
                    }
                    synchronized (ObjectRegistry.registrySet) {
                        if (ObjectRegistry.registrySet.isEmpty()) {
                            ObjectRegistry.cleanUpThread = null;
                            return;
                        }
                        continue;
                    }
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: astore_1       
        //     5: monitorenter   
        //     6: getstatic       chrriis/dj/nativeswing/common/ObjectRegistry.registrySet:Ljava/util/Set;
        //     9: aload_0         /* objectRegistry */
        //    10: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    15: pop            
        //    16: getstatic       chrriis/dj/nativeswing/common/ObjectRegistry.cleanUpThread:Ljava/lang/Thread;
        //    19: ifnull          25
        //    22: aload_1        
        //    23: monitorexit    
        //    24: return         
        //    25: new             Lchrriis/dj/nativeswing/common/lIIll;
        //    28: dup            
        //    29: new             Ljava/lang/StringBuilder;
        //    32: dup            
        //    33: invokespecial   java/lang/StringBuilder.<init>:()V
        //    36: ldc             "Registry cleanup thread-"
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: getstatic       chrriis/dj/nativeswing/common/ObjectRegistry.nextThreadNumber:I
        //    44: dup            
        //    45: iconst_1       
        //    46: iadd           
        //    47: putstatic       chrriis/dj/nativeswing/common/ObjectRegistry.nextThreadNumber:I
        //    50: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    53: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    56: invokespecial   chrriis/dj/nativeswing/common/lIIll.<init>:(Ljava/lang/String;)V
        //    59: putstatic       chrriis/dj/nativeswing/common/ObjectRegistry.cleanUpThread:Ljava/lang/Thread;
        //    62: ldc             "applet"
        //    64: getstatic       chrriis/dj/nativeswing/NSSystemProperty.DEPLOYMENT_TYPE:Lchrriis/dj/nativeswing/NSSystemProperty;
        //    67: invokevirtual   chrriis/dj/nativeswing/NSSystemProperty.get:()Ljava/lang/String;
        //    70: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    73: istore_2       
        //    74: getstatic       chrriis/dj/nativeswing/common/ObjectRegistry.cleanUpThread:Ljava/lang/Thread;
        //    77: iload_2        
        //    78: ifne            85
        //    81: iconst_1       
        //    82: goto            86
        //    85: iconst_0       
        //    86: invokevirtual   java/lang/Thread.setDaemon:(Z)V
        //    89: getstatic       chrriis/dj/nativeswing/common/ObjectRegistry.cleanUpThread:Ljava/lang/Thread;
        //    92: invokevirtual   java/lang/Thread.start:()V
        //    95: aload_1        
        //    96: monitorexit    
        //    97: goto            105
        //   100: astore_3       
        //   101: aload_1        
        //   102: monitorexit    
        //   103: aload_3        
        //   104: athrow         
        //   105: return         
        //    StackMapTable: 00 05 FC 00 19 07 00 1A FF 00 3B 00 03 07 00 02 07 00 1A 01 00 01 07 00 4A FF 00 00 00 03 07 00 02 07 00 1A 01 00 02 07 00 4A 01 FF 00 0D 00 02 07 00 02 07 00 1A 00 01 07 00 53 FC 00 04 01
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      24     100    105    Any
        //  25     97     100    105    Any
        //  100    103    100    105    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformSynchronized(AstMethodBodyBuilder.java:529)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:375)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
    
    public ObjectRegistry() {
        this.nextInstanceID = 1;
        this.instanceIDToObjectReferenceMap = new HashMap<Integer, WeakReference<Object>>();
    }
    
    public int add(final Object o) {
        boolean isStartingThread = false;
        int instanceID;
        synchronized (this) {
            do {
                instanceID = this.nextInstanceID++;
            } while (this.instanceIDToObjectReferenceMap.containsKey(instanceID));
            if (o != null) {
                this.instanceIDToObjectReferenceMap.put(instanceID, new WeakReference<Object>(o));
                isStartingThread = true;
            }
        }
        if (isStartingThread) {
            startThread(this);
        }
        return instanceID;
    }
    
    public void add(final Object o, final int instanceID) {
        synchronized (this) {
            final Object o2 = this.get(instanceID);
            if (o2 != null && o2 != o) {
                throw new IllegalStateException("An object is already registered with the id \"" + instanceID + "\" for object: " + o);
            }
            this.instanceIDToObjectReferenceMap.put(instanceID, new WeakReference<Object>(o));
        }
        startThread(this);
    }
    
    public synchronized Object get(final int instanceID) {
        final WeakReference<Object> weakReference = this.instanceIDToObjectReferenceMap.get(instanceID);
        if (weakReference == null) {
            return null;
        }
        final Object o = weakReference.get();
        if (o == null) {
            this.instanceIDToObjectReferenceMap.remove(instanceID);
        }
        return o;
    }
    
    public synchronized void remove(final int instanceID) {
        this.instanceIDToObjectReferenceMap.remove(instanceID);
    }
    
    public synchronized int[] getInstanceIDs() {
        final Object[] instanceIDObjects = this.instanceIDToObjectReferenceMap.keySet().toArray();
        final int[] instanceIDs = new int[instanceIDObjects.length];
        for (int i = 0; i < instanceIDObjects.length; ++i) {
            instanceIDs[i] = (int)instanceIDObjects[i];
        }
        return instanceIDs;
    }
    
    public static ObjectRegistry getInstance() {
        return ObjectRegistry.registry;
    }
    
    static {
        ObjectRegistry.registrySet = new HashSet<ObjectRegistry>();
        ObjectRegistry.registry = new ObjectRegistry();
    }
}
