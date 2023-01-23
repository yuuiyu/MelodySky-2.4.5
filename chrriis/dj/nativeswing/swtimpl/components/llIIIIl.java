//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.event.*;

class llIIIIl implements ChangeListener
{
    final /* synthetic */ DefaultVLCPlayerDecorator val$this$0;
    final /* synthetic */ DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1;
    
    llIIIIl(final DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1, final DefaultVLCPlayerDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void stateChanged(final ChangeEvent e) {
        if (!DefaultVLCPlayerDecorator.VLCPlayerControlBar.access$100(this.this$1)) {
            DefaultVLCPlayerDecorator.access$000(this.this$1.this$0).getVLCInput().setRelativePosition(DefaultVLCPlayerDecorator.VLCPlayerControlBar.access$200(this.this$1).getValue() / 10000.0f);
        }
    }
}
