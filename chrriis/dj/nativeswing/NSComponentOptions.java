//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

public abstract class NSComponentOptions
{
    static final String DEACTIVATE_NATIVE_INTEGRATION_OPTION_KEY = "Deactivate Native Integration";
    private static final NSOption DEACTIVATE_NATIVE_INTEGRATION_OPTION;
    static final String DESTROY_ON_FINALIZATION_OPTION_KEY = "Destroy On Finalization";
    private static final NSOption DESTROY_ON_FINALIZATION_OPTION;
    static final String PROXY_COMPONENT_HIERARCHY_OPTION_KEY = "Proxy Component Hierarchy";
    private static final NSOption PROXY_COMPONENT_HIERARCHY_OPTION;
    static final String CONSTRAIN_VISIBILITY_OPTION_KEY = "Constrain Visibility";
    private static final NSOption CONSTRAIN_VISIBILITY_OPTION;
    
    public static NSOption deactivateNativeIntegration() {
        return NSComponentOptions.DEACTIVATE_NATIVE_INTEGRATION_OPTION;
    }
    
    public static NSOption destroyOnFinalization() {
        return NSComponentOptions.DESTROY_ON_FINALIZATION_OPTION;
    }
    
    public static NSOption proxyComponentHierarchy() {
        return NSComponentOptions.PROXY_COMPONENT_HIERARCHY_OPTION;
    }
    
    public static NSOption constrainVisibility() {
        return NSComponentOptions.CONSTRAIN_VISIBILITY_OPTION;
    }
    
    static {
        DEACTIVATE_NATIVE_INTEGRATION_OPTION = new NSOption("Deactivate Native Integration");
        DESTROY_ON_FINALIZATION_OPTION = new NSOption("Destroy On Finalization");
        PROXY_COMPONENT_HIERARCHY_OPTION = new NSOption("Proxy Component Hierarchy");
        CONSTRAIN_VISIBILITY_OPTION = new NSOption("Constrain Visibility");
    }
}
