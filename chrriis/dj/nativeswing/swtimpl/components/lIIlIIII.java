//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIIlIIII extends WebBrowserAdapter
{
    final /* synthetic */ DefaultVLCPlayerDecorator val$this$0;
    final /* synthetic */ DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1;
    
    lIIlIIII(final DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1, final DefaultVLCPlayerDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void locationChanged(final WebBrowserNavigationEvent e) {
        this.this$1.adjustButtonState();
    }
}
