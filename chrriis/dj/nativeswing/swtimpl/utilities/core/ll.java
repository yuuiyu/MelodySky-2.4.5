//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.utilities.core;

import java.util.*;

class ll implements Comparator<NativeFileTypeLauncherStatic.FileTypeLauncherInfo>
{
    final /* synthetic */ NativeFileTypeLauncherStatic.CMN_getLauncherIDs this$0;
    
    ll(final NativeFileTypeLauncherStatic.CMN_getLauncherIDs this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final NativeFileTypeLauncherStatic.FileTypeLauncherInfo o1, final NativeFileTypeLauncherStatic.FileTypeLauncherInfo o2) {
        return o1.getProgram().getName().toLowerCase().compareTo(o2.getProgram().getName().toLowerCase());
    }
}
