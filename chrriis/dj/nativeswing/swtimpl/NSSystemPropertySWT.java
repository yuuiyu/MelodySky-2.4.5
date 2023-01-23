//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import chrriis.dj.nativeswing.*;
import java.security.*;

public enum NSSystemPropertySWT
{
    LOCALHOSTADDRESS(NSSystemProperty.LOCALHOSTADDRESS), 
    LOCALHOSTADDRESS_DEBUG_PRINTDETECTION(NSSystemProperty.LOCALHOSTADDRESS_DEBUG_PRINTDETECTION), 
    LOCALHOSTADDRESS_DEBUG_PRINT(NSSystemProperty.LOCALHOSTADDRESS_DEBUG_PRINT), 
    WEBSERVER_DEBUG_PRINTPORT(NSSystemProperty.WEBSERVER_DEBUG_PRINTPORT), 
    WEBSERVER_DEBUG_PRINTREQUESTS(NSSystemProperty.WEBSERVER_DEBUG_PRINTREQUESTS), 
    COMPONENTS_DEBUG_PRINTOPTIONS(NSSystemProperty.COMPONENTS_DEBUG_PRINTOPTIONS), 
    COMPONENTS_DEBUG_PRINTSHAPECOMPUTING(NSSystemProperty.COMPONENTS_DEBUG_PRINTSHAPECOMPUTING), 
    INTEGRATION_ACTIVE(NSSystemProperty.INTEGRATION_ACTIVE), 
    DEPENDENCIES_CHECKVERSIONS(NSSystemProperty.DEPENDENCIES_CHECKVERSIONS), 
    INTERFACE_PORT("nativeswing.interface.port", Type.READ_WRITE), 
    INTERFACE_STREAMRESETTHRESHOLD("nativeswing.interface.streamResetThreshold", Type.READ_WRITE), 
    INTERFACE_SYNCMESSAGES("nativeswing.interface.syncMessages", Type.READ_WRITE), 
    INTERFACE_DEBUG_PRINTMESSAGES("nativeswing.interface.debug.printMessages", Type.READ_WRITE), 
    INTERFACE_INPROCESS("nativeswing.interface.inProcess", Type.READ_WRITE), 
    INTERFACE_OUTPROCESS_COMMUNICATION("nativeswing.interface.outProcess.communication", Type.READ_WRITE), 
    INTERFACE_OUTPROCESS_CONNECTIONTIMEOUT("nativeswing.interface.outProcess.connectionTimeout", Type.READ_WRITE), 
    INTERFACE_INPROCESS_PRINTNONSERIALIZABLEMESSAGES("nativeswing.interface.inProcess.printNonSerializableMessages", Type.READ_WRITE), 
    PEERVM_CREATE("nativeswing.peervm.create", Type.READ_WRITE), 
    PEERVM_KEEPALIVE("nativeswing.peervm.keepAlive", Type.READ_WRITE), 
    PEERVM_FORCEPROXYCLASSLOADER("nativeswing.peervm.forceProxyClassLoader", Type.READ_WRITE), 
    PEERVM_DEBUG_PRINTSTARTMESSAGE("nativeswing.peervm.debug.printStartMessage", Type.READ_WRITE), 
    PEERVM_DEBUG_PRINTSTOPMESSAGE("nativeswing.peervm.debug.printStopMessage", Type.READ_WRITE), 
    PEERVM_DEBUG_PRINTCOMMANDLINE("nativeswing.peervm.debug.printCommandLine", Type.READ_WRITE), 
    COMPONENTS_DEBUG_PRINTFAILEDMESSAGES("nativeswing.components.debug.printFailedMessages", Type.READ_WRITE), 
    COMPONENTS_DEBUG_PRINTCREATION("nativeswing.components.debug.printCreation", Type.READ_WRITE), 
    COMPONENTS_DEBUG_PRINTDISPOSAL("nativeswing.components.debug.printDisposal", Type.READ_WRITE), 
    COMPONENTS_SWALLOWRUNTIMEEXCEPTIONS("nativeswing.components.swallowRuntimeExceptions", Type.READ_WRITE), 
    WEBBROWSER_RUNTIME("nativeswing.webbrowser.runtime", Type.READ_WRITE), 
    WEBBROWSER_XULRUNNER_HOME("nativeswing.webbrowser.xulrunner.home", Type.READ_WRITE), 
    HTMLEDITOR_GETHTMLCONTENT_TIMEOUT("nativeswing.htmleditor.getHTMLContent.timeout", Type.READ_WRITE), 
    VLCPLAYER_FIXPLAYLISTAUTOPLAYNEXT("nativeswing.vlcplayer.fixPlaylistAutoPlayNext", Type.READ_WRITE), 
    SWT_DEVICE_DEBUG("nativeswing.swt.device.debug", Type.READ_WRITE), 
    SWT_LIBRARY_PATH("swt.library.path", Type.READ_WRITE), 
    SWT_DEVICEDATA_DEBUG("nativeswing.swt.devicedata.debug", Type.READ_WRITE), 
    SWT_DEVICEDATA_TRACKING("nativeswing.swt.devicedata.tracking", Type.READ_WRITE), 
    ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH("org.eclipse.swt.browser.XULRunnerPath", Type.READ_WRITE), 
    COMPONENTS_DISABLEHIDDENPARENTREPARENTING("nativeswing.components.disableHiddenParentReparenting", Type.READ_WRITE), 
    COMPONENTS_PRINTINGHACK("nativeswing.components.printingHack", Type.READ_WRITE), 
    COMPONENTS_USECOMPONENTIMAGECLOSINGTHREAD("nativeswing.components.useComponentImageClosingThread", Type.READ_WRITE), 
    INTERFACE_INPROCESS_FORCESHUTDOWNHOOK("nativeswing.interface.inprocess.forceShutdownHook", Type.READ_WRITE), 
    INTERFACE_INPROCESS_USEEXTERNALSWTDISPLAY("nativeswing.interface.inprocess.useExternalSWTDisplay", Type.READ_WRITE), 
    INTERFACE_OUTPROCESS_SYNCCLOSING("nativeswing.interface.outprocess.syncclosing", Type.READ_WRITE), 
    INTERFACE_SYNCSEND_LOCAL_TIMEOUT("nativeswing.interface.syncsend.local.timeout", Type.READ_WRITE), 
    INTERFACE_SYNCSEND_NATIVE_TIMEOUT("nativeswing.interface.syncsend.native.timeout", Type.READ_WRITE);
    
    private final String _name;
    private final boolean _readOnly;
    
    private NSSystemPropertySWT(final NSSystemProperty property) {
        this._name = property.getName();
        this._readOnly = property.isReadOnly();
    }
    
    private NSSystemPropertySWT(final String name) {
        this(name, Type.READ_ONLY);
    }
    
    private NSSystemPropertySWT(String name, final Type type) {
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
        return AccessController.doPrivileged((PrivilegedAction<String>)new lIIlI(this, defaultValue));
    }
    
    public String set(final String value) {
        if (this.isReadOnly()) {
            throw new UnsupportedOperationException(this.getName() + " is a read-only property");
        }
        return AccessController.doPrivileged((PrivilegedAction<String>)new lIIIl(this, value));
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
