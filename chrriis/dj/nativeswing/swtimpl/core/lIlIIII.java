//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.ole.win32.*;
import java.util.*;

class lIlIIII implements Comparator<OleFunctionDescription>
{
    final /* synthetic */ SWTOleNativeComponent.CMN_dumpOleInterfaceDefinitions this$0;
    
    lIlIIII(final SWTOleNativeComponent.CMN_dumpOleInterfaceDefinitions this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final OleFunctionDescription o1, final OleFunctionDescription o2) {
        return o1.name.toLowerCase(Locale.ENGLISH).compareTo(o2.name.toLowerCase(Locale.ENGLISH));
    }
}
