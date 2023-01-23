//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.concurrent.atomic.*;

class lIIlIIIl implements JHTMLEditor.InitializationListener
{
    final /* synthetic */ AtomicBoolean val$result;
    final /* synthetic */ JHTMLEditor this$0;
    
    lIIlIIIl(final JHTMLEditor this$0, final AtomicBoolean val$result) {
        this.this$0 = this$0;
        this.val$result = val$result;
    }
    
    public void objectInitialized() {
        JHTMLEditor.access$200(this.this$0, (JHTMLEditor.InitializationListener)this);
        this.val$result.set(true);
    }
}
