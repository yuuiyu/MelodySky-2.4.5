//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class llIlIIl implements Runnable
{
    final /* synthetic */ Thread val$currentThread;
    final /* synthetic */ lIlllIl this$2;
    
    llIlIIl(final lIlllIl this$2, final Thread val$currentThread) {
        this.this$2 = this$2;
        this.val$currentThread = val$currentThread;
    }
    
    @Override
    public void run() {
        if (this.val$currentThread != DefaultVLCPlayerDecorator.VLCPlayerControlBar.access$600(this.this$2.this$1)) {
            return;
        }
        if (!DefaultVLCPlayerDecorator.access$000(this.this$2.this$1.this$0).isNativePeerValid()) {
            return;
        }
        DefaultVLCPlayerDecorator.VLCPlayerControlBar.access$800(this.this$2.this$1);
    }
}
