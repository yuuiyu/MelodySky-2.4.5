//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.common.*;

class lIIlll implements Runnable
{
    final /* synthetic */ String val$newStatus;
    final /* synthetic */ String val$oldStatus;
    final /* synthetic */ lIIIlI this$0;
    
    lIIlll(final lIIIlI this$0, final String val$newStatus, final String val$oldStatus) {
        this.this$0 = this$0;
        this.val$newStatus = val$newStatus;
        this.val$oldStatus = val$oldStatus;
    }
    
    @Override
    public void run() {
        this.this$0.val$browser.execute(fixJavascript(this.this$0.val$browser, "if(decodeURIComponent('" + Utils.encodeURL(this.val$newStatus) + "') == window.status) {window.status = decodeURIComponent('" + Utils.encodeURL((this.val$oldStatus == null) ? "" : this.val$oldStatus) + "');}"));
    }
}
