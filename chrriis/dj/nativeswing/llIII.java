//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.security.*;

class llIII implements PrivilegedAction<String>
{
    final /* synthetic */ String val$value;
    final /* synthetic */ NSSystemProperty this$0;
    
    llIII(final NSSystemProperty this$0, final String val$value) {
        this.this$0 = this$0;
        this.val$value = val$value;
    }
    
    @Override
    public String run() {
        return System.setProperty(this.this$0.getName(), this.val$value);
    }
}
