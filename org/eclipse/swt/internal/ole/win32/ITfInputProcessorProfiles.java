//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ITfInputProcessorProfiles extends IUnknown
{
    public ITfInputProcessorProfiles(final long address) {
        super(address);
    }
    
    public int GetDefaultLanguageProfile(final int langid, final GUID catid, final GUID pclsid, final GUID pguidProfile) {
        return COM.VtblCall(8, this.address, langid, catid, pclsid, pguidProfile);
    }
}
