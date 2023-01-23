//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.*;

class lIIll extends COMObject
{
    final /* synthetic */ WebSite this$0;
    
    lIIll(final WebSite this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    @Override
    public long method0(final long[] args) {
        return this.this$0.QueryInterface(args[0], args[1]);
    }
    
    @Override
    public long method1(final long[] args) {
        return this.this$0.AddRef();
    }
    
    @Override
    public long method2(final long[] args) {
        return this.this$0.Release();
    }
    
    @Override
    public long method3(final long[] args) {
        return this.this$0.SetSecuritySite(args[0]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.GetSecuritySite(args[0]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.MapUrlToZone(args[0], args[1], (int)args[2]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.GetSecurityId(args[0], args[1], args[2], args[3]);
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.ProcessUrlAction(args[0], (int)args[1], args[2], (int)args[3], args[4], (int)args[5], (int)args[6], (int)args[7]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.QueryCustomPolicy(args[0], args[1], args[2], args[3], args[4], (int)args[5], (int)args[6]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.SetZoneMapping((int)args[0], args[1], (int)args[2]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.GetZoneMappings((int)args[0], args[1], (int)args[2]);
    }
}
