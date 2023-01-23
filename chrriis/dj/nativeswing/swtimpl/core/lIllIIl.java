//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.events.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lIllIIl implements DisposeListener
{
    final /* synthetic */ int val$componentID;
    final /* synthetic */ SWTNativeComponent.CMN_createControl this$0;
    
    lIllIIl(final SWTNativeComponent.CMN_createControl this$0, final int val$componentID) {
        this.this$0 = this$0;
        this.val$componentID = val$componentID;
    }
    
    public void widgetDisposed(final DisposeEvent e) {
        if (Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_DEBUG_PRINTDISPOSAL.get())) {
            System.err.println("Disposed control: " + this.val$componentID);
        }
    }
}
