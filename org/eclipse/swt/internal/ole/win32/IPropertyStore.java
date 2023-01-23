//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class IPropertyStore extends IUnknown
{
    public IPropertyStore(final long address) {
        super(address);
    }
    
    public int SetValue(final PROPERTYKEY key, final long propvar) {
        return COM.VtblCall(6, this.address, key, propvar);
    }
    
    public int Commit() {
        return COM.VtblCall(7, this.address);
    }
}
