//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.security.*;

class lIIIl implements PrivilegedAction<String>
{
    final /* synthetic */ String val$defaultValue;
    final /* synthetic */ SystemProperty this$0;
    
    lIIIl(final SystemProperty this$0, final String val$defaultValue) {
        this.this$0 = this$0;
        this.val$defaultValue = val$defaultValue;
    }
    
    @Override
    public String run() {
        return System.getProperty(this.this$0.getName(), this.val$defaultValue);
    }
}
