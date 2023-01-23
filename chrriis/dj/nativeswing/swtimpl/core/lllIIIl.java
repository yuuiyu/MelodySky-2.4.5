//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

class lllIIIl implements Runnable
{
    final /* synthetic */ llIIIll this$1;
    
    lllIIIl(final llIIIll this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.val$control.isDisposed()) {
            return;
        }
        this.this$1.val$control.setSize(this.this$1.val$control.getParent().getSize());
    }
}
