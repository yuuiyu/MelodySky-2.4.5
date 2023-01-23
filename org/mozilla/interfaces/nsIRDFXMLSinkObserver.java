//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFXMLSinkObserver extends nsISupports
{
    public static final String NS_IRDFXMLSINKOBSERVER_IID = "{eb1a5d30-ab33-11d2-8ec6-00805f29f370}";
    
    void onBeginLoad(final nsIRDFXMLSink p0);
    
    void onInterrupt(final nsIRDFXMLSink p0);
    
    void onResume(final nsIRDFXMLSink p0);
    
    void onEndLoad(final nsIRDFXMLSink p0);
    
    void onError(final nsIRDFXMLSink p0, final long p1, final String p2);
}
