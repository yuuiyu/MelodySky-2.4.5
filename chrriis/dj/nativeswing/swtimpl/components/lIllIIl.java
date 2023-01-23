//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIllIIl implements Runnable
{
    final /* synthetic */ Thread val$currentThread;
    final /* synthetic */ lIIlIlII this$1;
    
    lIllIIl(final lIIlIlII this$1, final Thread val$currentThread) {
        this.this$1 = this$1;
        this.val$currentThread = val$currentThread;
    }
    
    @Override
    public void run() {
        if (this.val$currentThread != this.this$1.this$0.playlistFixThread) {
            return;
        }
        if (!this.this$1.this$0.vlcPlayer.isNativePeerValid()) {
            return;
        }
        if (this.this$1.this$0.vlcPlayer.getVLCInput().getMediaState() == VLCInput.VLCMediaState.ERROR) {
            this.this$1.this$0.goNext();
        }
    }
}
