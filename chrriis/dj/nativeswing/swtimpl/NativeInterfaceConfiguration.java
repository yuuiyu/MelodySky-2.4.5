//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.util.*;

public class NativeInterfaceConfiguration
{
    private boolean isNativeSideRespawnedOnError;
    private List<Class<?>> nativeClassPathReferenceClassList;
    private List<String> nativeClassPathReferenceResourceList;
    private String[] peerVMParams;
    private PeerVMProcessFactory peerVMProcessFactory;
    
    NativeInterfaceConfiguration() {
        this.isNativeSideRespawnedOnError = true;
        this.nativeClassPathReferenceClassList = new ArrayList<Class<?>>();
        this.nativeClassPathReferenceResourceList = new ArrayList<String>();
    }
    
    public void setPeerVMProcessFactory(final PeerVMProcessFactory peerVMProcessFactory) {
        this.peerVMProcessFactory = peerVMProcessFactory;
    }
    
    public PeerVMProcessFactory getPeerVMProcessFactory() {
        return this.peerVMProcessFactory;
    }
    
    public void setNativeSideRespawnedOnError(final boolean isNativeSideRespawnedOnError) {
        this.isNativeSideRespawnedOnError = isNativeSideRespawnedOnError;
    }
    
    public boolean isNativeSideRespawnedOnError() {
        return this.isNativeSideRespawnedOnError;
    }
    
    public void addNativeClassPathReferenceClasses(final Class<?>... nativeClassPathReferenceClasses) {
        this.nativeClassPathReferenceClassList.addAll(Arrays.asList(nativeClassPathReferenceClasses));
    }
    
    Class<?>[] getNativeClassPathReferenceClasses() {
        return this.nativeClassPathReferenceClassList.toArray(new Class[0]);
    }
    
    public void addNativeClassPathReferenceResources(final String... nativeClassPathReferenceResources) {
        this.nativeClassPathReferenceResourceList.addAll(Arrays.asList(nativeClassPathReferenceResources));
    }
    
    String[] getNativeClassPathReferenceResources() {
        return this.nativeClassPathReferenceResourceList.toArray(new String[0]);
    }
    
    public void setPeerVMParams(final String... peerVMParams) {
        this.peerVMParams = peerVMParams;
    }
    
    String[] getPeerVMParams() {
        return this.peerVMParams;
    }
}
