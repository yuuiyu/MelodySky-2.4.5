//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.util.*;

class lllIlII implements Comparator<String>
{
    final /* synthetic */ SWTOleNativeComponent.CMN_dumpOleInterfaceDefinitions this$0;
    
    lllIlII(final SWTOleNativeComponent.CMN_dumpOleInterfaceDefinitions this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final String o1, final String o2) {
        return o1.toLowerCase(Locale.ENGLISH).compareTo(o2.toLowerCase(Locale.ENGLISH));
    }
}
