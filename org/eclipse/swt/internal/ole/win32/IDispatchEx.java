//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IDispatchEx extends IDispatch
{
    public IDispatchEx(final long address) {
        super(address);
    }
    
    public int InvokeEx(final int id, final int lcid, final int wFlags, final DISPPARAMS pdp, final long pvarRes, final EXCEPINFO pei, final long pspCaller) {
        return COM.VtblCall(8, this.address, id, lcid, wFlags, pdp, pvarRes, pei, pspCaller);
    }
}
