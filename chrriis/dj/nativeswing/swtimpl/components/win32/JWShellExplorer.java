//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

import chrriis.dj.nativeswing.swtimpl.components.win32.internal.*;
import chrriis.dj.nativeswing.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import java.util.*;

public class JWShellExplorer extends NSPanelComponent
{
    private INativeWShellExplorer nativeComponent;
    private List<ClassLoader> referenceClassLoaderList;
    
    public JWShellExplorer(final NSOption... options) {
        this.referenceClassLoaderList = new ArrayList<ClassLoader>(1);
        this.nativeComponent = NativeCoreObjectFactory.create(INativeWShellExplorer.class, "chrriis.dj.nativeswing.swtimpl.components.win32.core.NativeWShellExplorer", new Class[] { JWShellExplorer.class }, new Object[] { this });
        this.initialize((NativeComponent)this.nativeComponent);
        this.add(this.nativeComponent.createEmbeddableComponent(NSOption.createOptionMap(options)), "Center");
    }
    
    public void load(final String resourcePath) {
        this.nativeComponent.invokeOleFunction("Navigate", new Object[] { (resourcePath == null) ? "" : resourcePath });
    }
    
    public void load(final Class<?> clazz, final String resourcePath) {
        this.addReferenceClassLoader(clazz.getClassLoader());
        this.load(WebServer.getDefaultWebServer().getClassPathResourceURL(clazz.getName(), resourcePath));
    }
    
    public void addShellExplorerListener(final ShellExplorerListener listener) {
        this.nativeComponent.addShellExplorerListener(listener);
    }
    
    public void removeShellExplorerListener(final ShellExplorerListener listener) {
        this.nativeComponent.removeShellExplorerListener(listener);
    }
    
    private void addReferenceClassLoader(final ClassLoader referenceClassLoader) {
        if (referenceClassLoader == null || referenceClassLoader == this.getClass().getClassLoader() || this.referenceClassLoaderList.contains(referenceClassLoader)) {
            return;
        }
        this.referenceClassLoaderList.add(referenceClassLoader);
        WebServer.getDefaultWebServer().addReferenceClassLoader(referenceClassLoader);
    }
    
    @Override
    protected void finalize() throws Throwable {
        for (final ClassLoader referenceClassLoader : this.referenceClassLoaderList) {
            WebServer.getDefaultWebServer().removeReferenceClassLoader(referenceClassLoader);
        }
        this.referenceClassLoaderList.clear();
        super.finalize();
    }
}
