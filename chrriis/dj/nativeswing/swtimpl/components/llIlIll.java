//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class llIlIll extends WebBrowserAdapter
{
    final /* synthetic */ DefaultFlashPlayerDecorator val$this$0;
    final /* synthetic */ DefaultFlashPlayerDecorator.FlashPlayerControlBar this$1;
    
    llIlIll(final DefaultFlashPlayerDecorator.FlashPlayerControlBar this$1, final DefaultFlashPlayerDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void locationChanged(final WebBrowserNavigationEvent e) {
        this.this$1.adjustButtonState();
    }
}
