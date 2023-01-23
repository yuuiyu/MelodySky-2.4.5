//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleHyperlinkListener extends SWTEventListener
{
    void getAnchor(final AccessibleHyperlinkEvent p0);
    
    void getAnchorTarget(final AccessibleHyperlinkEvent p0);
    
    void getStartIndex(final AccessibleHyperlinkEvent p0);
    
    void getEndIndex(final AccessibleHyperlinkEvent p0);
}
