//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFXMLSink extends nsISupports
{
    public static final String NS_IRDFXMLSINK_IID = "{eb1a5d31-ab33-11d2-8ec6-00805f29f370}";
    
    boolean getReadOnly();
    
    void setReadOnly(final boolean p0);
    
    void beginLoad();
    
    void interrupt();
    
    void resume();
    
    void endLoad();
    
    void addXMLSinkObserver(final nsIRDFXMLSinkObserver p0);
    
    void removeXMLSinkObserver(final nsIRDFXMLSinkObserver p0);
}
