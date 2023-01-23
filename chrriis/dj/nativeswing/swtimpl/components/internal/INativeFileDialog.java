//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.internal;

import java.awt.*;
import chrriis.dj.nativeswing.swtimpl.components.*;

public interface INativeFileDialog
{
    void show(final Component p0);
    
    String getSelectedFileName();
    
    String[] getSelectedFileNames();
    
    void setSelectedFileName(final String p0);
    
    JFileDialog.SelectionMode getSelectionMode();
    
    void setSelectionMode(final JFileDialog.SelectionMode p0);
    
    JFileDialog.DialogType getDialogType();
    
    void setDialogType(final JFileDialog.DialogType p0);
    
    void setConfirmedOverwrite(final boolean p0);
    
    boolean isConfirmedOverwrite();
    
    String getParentDirectory();
    
    void setParentDirectory(final String p0);
    
    void setExtensionFilters(final String[] p0, final String[] p1, final int p2);
    
    String[] getExtensionFilters();
    
    String[] getExtensionFiltersNames();
    
    int getSelectedExtensionFilterIndex();
    
    void setTitle(final String p0);
    
    String getTitle();
}
