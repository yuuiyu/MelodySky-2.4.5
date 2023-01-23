//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

class lllIIl implements TextChangeListener
{
    final /* synthetic */ StyledText this$0;
    
    lllIIl(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void textChanging(final TextChangingEvent event) {
        this.this$0.handleTextChanging(event);
    }
    
    @Override
    public void textChanged(final TextChangedEvent event) {
        this.this$0.handleTextChanged(event);
    }
    
    @Override
    public void textSet(final TextChangedEvent event) {
        this.this$0.handleTextSet(event);
    }
}
