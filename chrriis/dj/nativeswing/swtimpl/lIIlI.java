//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.security.*;
import chrriis.dj.nativeswing.common.*;

class lIIlI implements PrivilegedAction<String>
{
    final /* synthetic */ String val$defaultValue;
    final /* synthetic */ NSSystemPropertySWT this$0;
    
    lIIlI(final NSSystemPropertySWT this$0, final String val$defaultValue) {
        this.this$0 = this$0;
        this.val$defaultValue = val$defaultValue;
    }
    
    @Override
    public String run() {
        final String name = this.this$0.getName();
        String value = System.getProperty(name);
        if (value != null) {
            return value;
        }
        if (Utils.IS_WEBSTART) {
            value = System.getProperty("jnlp." + name);
            if (value != null) {
                return value;
            }
        }
        return this.val$defaultValue;
    }
}
