//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.*;
import java.awt.image.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lIIllIl extends NativeComponentWrapper
{
    final /* synthetic */ SWTNativeComponent this$0;
    
    lIIllIl(final SWTNativeComponent this$0, final Component nativeComponent) {
        this.this$0 = this$0;
        super(nativeComponent);
    }
    
    protected String getComponentDescription() {
        return this.this$0.getComponentDescription();
    }
    
    protected void paintNativeComponent(final BufferedImage image, final Rectangle[] rectangles) {
        this.this$0.paintComponent(image, rectangles);
    }
    
    protected void setNativeComponentEnabled(final boolean isEnabled) {
        this.this$0.setControlParentEnabled(isEnabled, Utils.IS_MAC && isEnabled && this.this$0.isShowing());
    }
    
    protected boolean isNativeComponentEnabled() {
        return this.this$0.isControlParentEnabled();
    }
    
    protected void storeInHiddenParent() {
        if (Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_DISABLEHIDDENPARENTREPARENTING.get())) {
            throw new IllegalStateException("Storing to a hidden parent is not supported!");
        }
        this.this$0.storeInHiddenParent();
    }
    
    protected void restoreFromHiddenParent() {
        this.this$0.restoreFromHiddenParent();
    }
}
