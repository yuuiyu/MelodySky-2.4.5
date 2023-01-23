//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32.core;

import chrriis.dj.nativeswing.swtimpl.core.*;
import chrriis.dj.nativeswing.swtimpl.components.win32.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.ole.win32.*;
import org.eclipse.swt.*;
import java.util.*;
import java.awt.*;

class NativeWMediaPlayer extends SWTOleNativeComponent implements INativeWMediaPlayer
{
    protected static Control createControl(final Composite parent, final Object[] parameters) {
        final OleFrame frame = new OleFrame(parent, 0);
        OleClientSite site;
        try {
            site = new OleClientSite((Composite)frame, 0, "WMPlayer.OCX");
            SWTOleNativeComponent.configureOleFrame(site, frame);
        }
        catch (SWTException e) {
            e.printStackTrace();
            frame.dispose();
            return null;
        }
        site.doVerb(-5);
        return (Control)frame;
    }
    
    @Override
    public Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
        return super.createEmbeddableComponent(optionMap);
    }
    
    @Override
    protected void disposeNativePeer() {
        super.disposeNativePeer();
    }
}
