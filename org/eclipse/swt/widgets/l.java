//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

class l implements Listener
{
    final /* synthetic */ TaskItem this$0;
    
    l(final TaskItem this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void handleEvent(final Event event) {
        if (this.this$0.isDisposed()) {
            return;
        }
        this.this$0.dispose();
    }
}
