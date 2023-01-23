//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWSDLOperation extends nsISupports
{
    public static final String NS_IWSDLOPERATION_IID = "{0458dac2-65de-11d5-9b42-00104bdf5339}";
    
    String getName();
    
    nsIDOMElement getDocumentation();
    
    nsIWSDLBinding getBinding();
    
    nsIWSDLMessage getInput();
    
    nsIWSDLMessage getOutput();
    
    long getFaultCount();
    
    nsIWSDLMessage getFault(final long p0);
    
    long getParameterOrderCount();
    
    String getParameter(final long p0);
    
    long getParameterIndex(final String p0);
}
