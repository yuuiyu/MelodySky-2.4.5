//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

class lIIIl extends AccessibleAdapter
{
    final /* synthetic */ int val$childID;
    final /* synthetic */ Accessible this$0;
    
    lIIIl(final Accessible this$0, final int val$childID) {
        this.this$0 = this$0;
        this.val$childID = val$childID;
    }
    
    public void getName(final AccessibleEvent e) {
        if (e.childID == -1) {
            final AccessibleEvent event = new AccessibleEvent((Object)this.this$0);
            event.childID = this.val$childID;
            for (int i = 0; i < this.this$0.accessibleListenersSize(); ++i) {
                final AccessibleListener listener = this.this$0.accessibleListeners.get(i);
                listener.getName(event);
            }
            e.result = event.result;
        }
    }
}
