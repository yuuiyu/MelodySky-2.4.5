//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;

class lIIlIl extends BrowserFunction
{
    final /* synthetic */ NativeWebBrowser.CMN_registerFunction this$0;
    
    lIIlIl(final NativeWebBrowser.CMN_registerFunction this$0, final Browser browser, final String name) {
        this.this$0 = this$0;
        super(browser, name);
    }
    
    public Object function(final Object[] arguments) {
        return new NativeWebBrowser.CMJ_invokeFunction(null).syncExec((Control)this.getBrowser(), this.getName(), arguments);
    }
}
