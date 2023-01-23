//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IPropertyBag extends IUnknown
{
    public IPropertyBag(final long address) {
        super(address);
    }
    
    public int Read(final long pszPropName, final long pVar, final long[] pErrorLog) {
        return COM.VtblCall(3, this.getAddress(), pszPropName, pVar, pErrorLog);
    }
}
