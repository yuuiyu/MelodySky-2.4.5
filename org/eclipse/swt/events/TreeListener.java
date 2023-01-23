//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface TreeListener extends SWTEventListener
{
    void treeCollapsed(final TreeEvent p0);
    
    void treeExpanded(final TreeEvent p0);
    
    default TreeListener treeCollapsedAdapter(final Consumer<TreeEvent> c) {
        return (TreeListener)new lIIIII(this, (Consumer)c);
    }
    
    default TreeListener treeExpandedAdapter(final Consumer<TreeEvent> c) {
        return (TreeListener)new lllll(this, (Consumer)c);
    }
}
