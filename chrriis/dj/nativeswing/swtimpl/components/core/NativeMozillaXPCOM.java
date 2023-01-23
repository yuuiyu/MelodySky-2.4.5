//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import java.lang.ref.*;
import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import org.mozilla.xpcom.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;
import chrriis.dj.nativeswing.swtimpl.components.*;
import chrriis.dj.nativeswing.swtimpl.core.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

class NativeMozillaXPCOM implements INativeMozillaXPCOM
{
    private static Map<Integer, Object> idToNativeInterfaceMap;
    private static Map<Integer, WeakReference<NativeSwingProxy>> idToProxyInterfaceReferenceMap;
    private static Map<InterfaceInfo, Integer> interfaceInfoToIDMap;
    private static int nextNativeSideID;
    private static int nextSwingSideID;
    
    @Override
    public Object getWebBrowser(final JWebBrowser webBrowser) {
        return this.unpack(webBrowser.getNativeComponent().runSync(new CMN_getWebBrowser(null), new Object[0]));
    }
    
    @Override
    public boolean initialize() {
        String path = NSSystemPropertySWT.WEBBROWSER_XULRUNNER_HOME.get();
        if (path == null) {
            path = NSSystemPropertySWT.ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH.get();
        }
        else {
            NSSystemPropertySWT.ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH.set(path);
        }
        if (Utils.IS_MAC) {
            if (path == null) {
                path = System.getenv("XULRUNNER_HOME");
            }
            if (path == null) {
                return false;
            }
            final File file = new File(path);
            if (!file.exists()) {
                return false;
            }
            Mozilla.getInstance().initialize(file);
        }
        else {
            final Shell shell = new Shell(0);
            new Browser((Composite)shell, 32768);
            shell.dispose();
        }
        return true;
    }
    
    @Override
    public Object pack(final Object o, final boolean isNativeSide) {
        return pack_(o, isNativeSide);
    }
    
    static Object pack_(final Object o, final boolean isNativeSide) {
        if (o == null) {
            return null;
        }
        if (o instanceof Object[]) {
            final Object[] array = (Object[])o;
            final Object[] newArray = new Object[array.length];
            for (int i = 0; i < array.length; ++i) {
                newArray[i] = pack_(array[i], isNativeSide);
            }
            return new ArrayInfo(array.getClass(), newArray);
        }
        final Package pckage = o.getClass().getPackage();
        if (pckage != null && pckage.getName().equals("java.lang")) {
            return o;
        }
        final InterfaceInfo interfaceInfo = new InterfaceInfo(o);
        Integer id = NativeMozillaXPCOM.interfaceInfoToIDMap.get(interfaceInfo);
        if (id == null) {
            final List<Class<?>> interfaceList = new ArrayList<Class<?>>();
            final ClassLoader cl = MozillaXPCOM.class.getClassLoader();
            for (final Class<?> intrface : o.getClass().getInterfaces()) {
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intrface.getName(), false, cl);
                }
                catch (ClassNotFoundException ex) {}
                if (interfaceClass == intrface) {
                    interfaceList.add(intrface);
                }
            }
            interfaceList.add(NativeSwingProxy.class);
            if (isNativeSide) {
                id = NativeMozillaXPCOM.nextNativeSideID++;
            }
            else {
                id = NativeMozillaXPCOM.nextSwingSideID--;
            }
            NativeMozillaXPCOM.idToNativeInterfaceMap.put(id, o);
            NativeMozillaXPCOM.interfaceInfoToIDMap.put(interfaceInfo, id);
            return new InterfaceDefinition(id, interfaceList.toArray(new Class[0]), true, !isNativeSide, null);
        }
        return new InterfaceDefinition(id, null, !(o instanceof NativeSwingProxy), !isNativeSide, null);
    }
    
    @Override
    public Object unpack(final Object o) {
        return unpack_(o);
    }
    
    static Object unpack_(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof ArrayInfo) {
            final ArrayInfo arrayInfo = (ArrayInfo)o;
            final Class<?> arrayClass = arrayInfo.getArrayClass();
            final Object[] items = arrayInfo.getItems();
            final Object[] array = (Object[])Array.newInstance(arrayClass.getComponentType(), items.length);
            for (int i = 0; i < items.length; ++i) {
                array[i] = unpack_(items[i]);
            }
            return array;
        }
        if (!(o instanceof InterfaceDefinition)) {
            return o;
        }
        final InterfaceDefinition interfaceDefinition = (InterfaceDefinition)o;
        final Integer id = interfaceDefinition.getID();
        if (interfaceDefinition.isProxy()) {
            final WeakReference<NativeSwingProxy> proxyInterfaceReference = NativeMozillaXPCOM.idToProxyInterfaceReferenceMap.get(id);
            NativeSwingProxy proxyInterface = (proxyInterfaceReference == null) ? null : proxyInterfaceReference.get();
            if (proxyInterface == null) {
                final Class<?>[] interfaces = interfaceDefinition.getInterfaces();
                final boolean isNativeSide = interfaceDefinition.isNativeSide();
                proxyInterface = (NativeSwingProxy)Proxy.newProxyInstance(MozillaXPCOM.class.getClassLoader(), interfaces, (InvocationHandler)new lIIIll(id, isNativeSide));
                NativeMozillaXPCOM.idToProxyInterfaceReferenceMap.put(id, new WeakReference<NativeSwingProxy>(proxyInterface));
                NativeMozillaXPCOM.interfaceInfoToIDMap.put(new InterfaceInfo(proxyInterface), id);
            }
            return proxyInterface;
        }
        return NativeMozillaXPCOM.idToNativeInterfaceMap.get(id);
    }
    
    static {
        NativeMozillaXPCOM.idToNativeInterfaceMap = new HashMap<Integer, Object>();
        NativeMozillaXPCOM.idToProxyInterfaceReferenceMap = new HashMap<Integer, WeakReference<NativeSwingProxy>>();
        NativeMozillaXPCOM.interfaceInfoToIDMap = new HashMap<InterfaceInfo, Integer>();
        NativeMozillaXPCOM.nextNativeSideID = 1;
        NativeMozillaXPCOM.nextSwingSideID = -1;
    }
    
    private static class CMN_getWebBrowser extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return NativeMozillaXPCOM.pack_(((Browser)this.getControl()).getWebBrowser(), true);
        }
    }
    
    private static class InterfaceDefinition implements Serializable
    {
        private int id;
        private Class<?>[] interfaces;
        private boolean isProxy;
        private boolean isNativeSide;
        
        private InterfaceDefinition(final int id, final Class<?>[] interfaces, final boolean isProxy, final boolean isNativeSide) {
            this.id = id;
            this.interfaces = interfaces;
            this.isProxy = isProxy;
            this.isNativeSide = isNativeSide;
        }
        
        public int getID() {
            return this.id;
        }
        
        public Class<?>[] getInterfaces() {
            return this.interfaces;
        }
        
        public boolean isProxy() {
            return this.isProxy;
        }
        
        public boolean isNativeSide() {
            return this.isNativeSide;
        }
    }
    
    private static class InterfaceInfo
    {
        private int id;
        
        public InterfaceInfo(final Object intrface) {
            this.id = System.identityHashCode(intrface);
        }
        
        @Override
        public int hashCode() {
            return this.id;
        }
        
        @Override
        public boolean equals(final Object o) {
            return ((InterfaceInfo)o).id == this.id;
        }
    }
    
    private static class ArrayInfo implements Serializable
    {
        private Class<?> arrayClass;
        private Object[] content;
        
        public ArrayInfo(final Class<?> arrayClass, final Object[] content) {
            final ClassLoader cl = MozillaXPCOM.class.getClassLoader();
            Class<?> arrayClass_ = null;
            while (true) {
                try {
                    arrayClass_ = Class.forName(arrayClass.getName(), false, cl);
                }
                catch (ClassNotFoundException ex) {}
                if (arrayClass_ == arrayClass) {
                    break;
                }
                arrayClass_ = arrayClass_.getSuperclass();
            }
            this.arrayClass = arrayClass;
            this.content = content;
        }
        
        public Class<?> getArrayClass() {
            return this.arrayClass;
        }
        
        public Object[] getItems() {
            return this.content;
        }
        
        @Override
        public String toString() {
            return Arrays.deepToString(this.content);
        }
    }
    
    private static class RunMethodResult implements Serializable
    {
        private Object result;
        private Object[] outParams;
        
        public RunMethodResult(final Object result, final Object[] outParams) {
            this.result = result;
            this.outParams = outParams;
        }
        
        public Object getResult() {
            return this.result;
        }
        
        public Object[] getOutParams() {
            return this.outParams;
        }
    }
    
    private static class CM_runMethod extends CommandMessage
    {
        public Object run(final Object[] args) {
            final Integer interfaceID = (Integer)args[0];
            final String methodName = (String)args[1];
            final Class<?>[] parameterTypes = (Class<?>[])args[2];
            final Object[] parameterValues = (Object[])NativeMozillaXPCOM.unpack_(args[3]);
            final boolean isNativeSide = (boolean)args[4];
            final Object nativeInterface = NativeMozillaXPCOM.idToNativeInterfaceMap.get(interfaceID);
            try {
                final Method method = nativeInterface.getClass().getMethod(methodName, parameterTypes);
                method.setAccessible(true);
                final Object result = method.invoke(nativeInterface, parameterValues);
                List<Object> outParamList = null;
                if (parameterValues != null) {
                    outParamList = new ArrayList<Object>();
                    for (final Object o : parameterValues) {
                        if (o instanceof Object[]) {
                            outParamList.add(NativeMozillaXPCOM.pack_(o, isNativeSide));
                        }
                    }
                }
                return new RunMethodResult(NativeMozillaXPCOM.pack_(result, isNativeSide), (Object[])((outParamList == null || outParamList.isEmpty()) ? null : outParamList.toArray()));
            }
            catch (Exception e) {
                throw new IllegalStateException("The method " + methodName + " could not be invoked on interface " + nativeInterface + "!", e);
            }
        }
    }
    
    private static class CM_disposeResources extends CommandMessage
    {
        public Object run(final Object[] args) {
            final Object nativeInterface = NativeMozillaXPCOM.idToNativeInterfaceMap.remove(args[0]);
            NativeMozillaXPCOM.interfaceInfoToIDMap.remove(new InterfaceInfo(nativeInterface));
            return null;
        }
    }
    
    private interface NativeSwingProxy
    {
        void finalize();
    }
}
