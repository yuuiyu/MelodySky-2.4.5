//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import org.mozilla.interfaces.*;
import chrriis.dj.nativeswing.swtimpl.*;
import org.mozilla.xpcom.*;

public class MozillaXPCOM
{
    private static INativeMozillaXPCOM nativeMozillaXPCOM;
    
    private MozillaXPCOM() {
    }
    
    public static nsIWebBrowser getWebBrowser(final JWebBrowser webBrowser) {
        return (nsIWebBrowser)MozillaXPCOM.nativeMozillaXPCOM.getWebBrowser(webBrowser);
    }
    
    private static Object pack(final Object o, final boolean isNativeSide) {
        return MozillaXPCOM.nativeMozillaXPCOM.pack(o, isNativeSide);
    }
    
    private static Object unpack(final Object o) {
        return MozillaXPCOM.nativeMozillaXPCOM.unpack(o);
    }
    
    static {
        MozillaXPCOM.nativeMozillaXPCOM = NativeCoreObjectFactory.create(INativeMozillaXPCOM.class, "chrriis.dj.nativeswing.swtimpl.components.core.NativeMozillaXPCOM", new Class[0], new Object[0]);
    }
    
    public static class Mozilla
    {
        private static boolean isInitialized;
        
        private static boolean initialize() {
            if (Mozilla.isInitialized) {
                return false;
            }
            Mozilla.isInitialized = true;
            return MozillaXPCOM.nativeMozillaXPCOM.initialize();
        }
        
        public static nsIComponentRegistrar getComponentRegistrar() {
            return (nsIComponentRegistrar)unpack(new CMN_getComponentRegistrar(null).syncExec(true, new Object[0]));
        }
        
        public static nsIComponentManager getComponentManager() {
            return (nsIComponentManager)unpack(new CMN_getComponentManager(null).syncExec(true, new Object[0]));
        }
        
        public static nsIServiceManager getServiceManager() {
            return (nsIServiceManager)unpack(new CMN_getServiceManager(null).syncExec(true, new Object[0]));
        }
        
        private static class CMN_getComponentRegistrar extends CommandMessage
        {
            public Object run(final Object[] args) {
                try {
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getComponentRegistrar(), true);
                }
                catch (XPCOMInitializationException e) {
                    if (!initialize()) {
                        throw e;
                    }
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getComponentRegistrar(), true);
                }
            }
        }
        
        private static class CMN_getComponentManager extends CommandMessage
        {
            public Object run(final Object[] args) {
                try {
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getComponentManager(), true);
                }
                catch (XPCOMInitializationException e) {
                    if (!initialize()) {
                        throw e;
                    }
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getComponentManager(), true);
                }
            }
        }
        
        private static class CMN_getServiceManager extends CommandMessage
        {
            public Object run(final Object[] args) {
                try {
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getServiceManager(), true);
                }
                catch (XPCOMInitializationException e) {
                    if (!initialize()) {
                        throw e;
                    }
                    return pack(org.mozilla.xpcom.Mozilla.getInstance().getServiceManager(), true);
                }
            }
        }
    }
}
