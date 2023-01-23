//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.debug;

import org.jetbrains.java.decompiler.main.extern.*;
import org.jetbrains.java.decompiler.util.*;
import java.io.*;

class l implements IBytecodeProvider
{
    private byte[] byteCode;
    final /* synthetic */ RuntimeDecompiler this$0;
    
    l(final RuntimeDecompiler this$0) {
        this.this$0 = this$0;
    }
    
    public byte[] getBytecode(final String externalPath, final String internalPath) throws IOException {
        if (this.byteCode == null) {
            this.byteCode = InterpreterUtil.getBytes(new File(externalPath));
        }
        return this.byteCode;
    }
}
