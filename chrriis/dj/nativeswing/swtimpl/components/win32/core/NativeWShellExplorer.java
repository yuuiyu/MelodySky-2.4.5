//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32.core;

import chrriis.dj.nativeswing.swtimpl.components.win32.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.ole.win32.*;
import java.lang.ref.*;
import java.util.*;
import java.awt.*;
import javax.swing.event.*;
import chrriis.dj.nativeswing.swtimpl.core.*;
import chrriis.dj.nativeswing.swtimpl.components.win32.*;

class NativeWShellExplorer extends SWTOleNativeComponent implements INativeWShellExplorer
{
    private static String IID_DWebBrowserEvents2;
    private static int DocumentComplete;
    private Reference<JWShellExplorer> shellExplorer;
    
    protected static Control createControl(final Composite parent, final Object[] parameters) {
        final OleFrame frame = new OleFrame(parent, 0);
        OleControlSite site;
        try {
            site = new OleControlSite((Composite)frame, 0, "Shell.Explorer");
            SWTOleNativeComponent.configureOleFrame((OleClientSite)site, frame);
            final OleAutomation shellExplorer = new OleAutomation((OleClientSite)site);
            final int[] dispIDs = shellExplorer.getIDsOfNames(new String[] { "Application" });
            final Variant pVarResult = shellExplorer.getProperty(dispIDs[0]);
            final OleAutomation application = pVarResult.getAutomation();
            frame.addDisposeListener((DisposeListener)new lI(application));
            pVarResult.dispose();
            shellExplorer.dispose();
            final OleListener listener = (OleListener)new ll(frame);
            site.addEventListener(application, NativeWShellExplorer.IID_DWebBrowserEvents2, NativeWShellExplorer.DocumentComplete, listener);
        }
        catch (SWTException e) {
            e.printStackTrace();
            frame.dispose();
            return null;
        }
        site.doVerb(-5);
        return (Control)frame;
    }
    
    public NativeWShellExplorer(final JWShellExplorer shellExplorer) {
        this.shellExplorer = new WeakReference<JWShellExplorer>(shellExplorer);
    }
    
    @Override
    public void addShellExplorerListener(final ShellExplorerListener listener) {
        this.listenerList.add(ShellExplorerListener.class, listener);
    }
    
    @Override
    public void removeShellExplorerListener(final ShellExplorerListener listener) {
        this.listenerList.remove(ShellExplorerListener.class, listener);
    }
    
    @Override
    public Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
        return super.createEmbeddableComponent(optionMap);
    }
    
    @Override
    protected void disposeNativePeer() {
        super.disposeNativePeer();
    }
    
    static {
        NativeWShellExplorer.IID_DWebBrowserEvents2 = "{34A715A0-6587-11D0-924A-0020AFC7AC4D}";
        NativeWShellExplorer.DocumentComplete = 259;
    }
    
    private static class CMJ_sendDocumentCompleteEvent extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWShellExplorer nativeShellExplorer = (NativeWShellExplorer)this.getNativeComponent();
            final JWShellExplorer shellExplorer = (nativeShellExplorer == null) ? null : nativeShellExplorer.shellExplorer.get();
            if (shellExplorer == null) {
                return null;
            }
            final Object[] listeners = nativeShellExplorer.listenerList.getListenerList();
            ShellExplorerDocumentCompleteEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == ShellExplorerListener.class) {
                    if (e == null) {
                        e = new ShellExplorerDocumentCompleteEvent(shellExplorer, (String)args[0]);
                    }
                    ((ShellExplorerListener)listeners[i + 1]).documentComplete(e);
                }
            }
            return null;
        }
    }
}
