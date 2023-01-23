//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.security.*;

public enum NSSystemProperty
{
    LOCALHOSTADDRESS("nativeswing.localhostAddress", Type.READ_WRITE), 
    LOCALHOSTADDRESS_DEBUG_PRINTDETECTION("nativeswing.localhostAddress.debug.printDetection", Type.READ_WRITE), 
    LOCALHOSTADDRESS_DEBUG_PRINT("nativeswing.localhostAddress.debug.print", Type.READ_WRITE), 
    WEBSERVER_DEBUG_PRINTPORT("nativeswing.webserver.debug.printPort", Type.READ_WRITE), 
    WEBSERVER_DEBUG_PRINTREQUESTS("nativeswing.webserver.debug.printRequests", Type.READ_WRITE), 
    WEBSERVER_DEBUG_PRINTDATA("nativeswing.webserver.debug.printData", Type.READ_WRITE), 
    WEBSERVER_ACTIVATEOLDRESOURCEMETHOD("nativeswing.webserver.activateOldResourceMethod", Type.READ_WRITE), 
    COMPONENTS_DEBUG_PRINTOPTIONS("nativeswing.components.debug.printOptions", Type.READ_WRITE), 
    COMPONENTS_DEBUG_PRINTSHAPECOMPUTING("nativeswing.components.debug.printShapeComputing", Type.READ_WRITE), 
    COMPONENTS_FORCESINGLERECTANGLESHAPES("nativeswing.components.forceSingleRectangleShapes", Type.READ_WRITE), 
    INTEGRATION_ACTIVE("nativeswing.integration.active", Type.READ_WRITE), 
    DEPENDENCIES_CHECKVERSIONS("nativeswing.dependencies.checkVersions", Type.READ_WRITE), 
    JNA_FORCE_HW_POPUP("jna.force_hw_popups", Type.READ_WRITE), 
    DEPLOYMENT_TYPE("nativeswing.deployment.type", Type.READ_WRITE), 
    INTEGRATION_USEDEFAULTCLIPPING("nativeswing.integration.useDefaultClipping", Type.READ_WRITE);
    
    private final String _name;
    private final boolean _readOnly;
    
    private NSSystemProperty(final String name) {
        this(name, Type.READ_ONLY);
    }
    
    private NSSystemProperty(String name, final Type type) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        name = name.trim();
        if ("".equals(name)) {
            throw new IllegalArgumentException();
        }
        this._name = name;
        this._readOnly = (type == Type.READ_ONLY);
    }
    
    public String get() {
        return this.get(null);
    }
    
    public String get(final String defaultValue) {
        return AccessController.doPrivileged((PrivilegedAction<String>)new llIll(this, defaultValue));
    }
    
    public String set(final String value) {
        if (this.isReadOnly()) {
            throw new UnsupportedOperationException(this.getName() + " is a read-only property");
        }
        return AccessController.doPrivileged((PrivilegedAction<String>)new llIII(this, value));
    }
    
    public String getName() {
        return this._name;
    }
    
    public boolean isReadOnly() {
        return this._readOnly;
    }
    
    @Override
    public String toString() {
        return this.get();
    }
    
    public String toDebugString() {
        final StringBuilder buf = new StringBuilder();
        buf.append(this.name()).append(": ");
        buf.append(this.getName()).append("=");
        buf.append(this.get());
        if (this.isReadOnly()) {
            buf.append(" (read-only)");
        }
        return buf.toString();
    }
    
    private enum Type
    {
        READ_WRITE, 
        READ_ONLY;
    }
}
