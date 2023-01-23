//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.security.*;
import org.newdawn.slick.util.*;

public class ImageDataFactory
{
    private static boolean usePngLoader;
    private static boolean pngLoaderPropertyChecked;
    private static final String PNG_LOADER = "org.newdawn.slick.pngloader";
    
    private static void checkProperty() {
        final class lIl implements PrivilegedAction
        {
            @Override
            public Object run() {
                final String val = System.getProperty("org.newdawn.slick.pngloader");
                if ("false".equalsIgnoreCase(val)) {
                    ImageDataFactory.usePngLoader = false;
                }
                Log.info("Use Java PNG Loader = " + ImageDataFactory.usePngLoader);
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifne            25
        //     6: iconst_1       
        //     7: putstatic       org/newdawn/slick/opengl/ImageDataFactory.pngLoaderPropertyChecked:Z
        //    10: new             Lorg/newdawn/slick/opengl/lIl;
        //    13: dup            
        //    14: invokespecial   org/newdawn/slick/opengl/lIl.<init>:()V
        //    17: invokestatic    java/security/AccessController.doPrivileged:(Ljava/security/PrivilegedAction;)Ljava/lang/Object;
        //    20: pop            
        //    21: goto            25
        //    24: astore_0       
        //    25: return         
        //    StackMapTable: 00 02 58 07 00 17 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     21     24     25     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static LoadableImageData getImageDataFor(String ref) {
        checkProperty();
        ref = ref.toLowerCase();
        if (ref.endsWith(".tga")) {
            return new TGAImageData();
        }
        if (ref.endsWith(".png")) {
            final CompositeImageData data = new CompositeImageData();
            if (ImageDataFactory.usePngLoader) {
                data.add((LoadableImageData)new PNGImageData());
            }
            data.add((LoadableImageData)new ImageIOImageData());
            return (LoadableImageData)data;
        }
        return new ImageIOImageData();
    }
    
    static {
        ImageDataFactory.usePngLoader = true;
        ImageDataFactory.pngLoaderPropertyChecked = false;
    }
}
