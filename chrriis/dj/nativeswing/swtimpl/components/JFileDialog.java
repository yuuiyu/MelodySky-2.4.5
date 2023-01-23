//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import java.awt.*;

public class JFileDialog
{
    private INativeFileDialog nativeFileDialog;
    
    public JFileDialog() {
        this.nativeFileDialog = NativeCoreObjectFactory.create(INativeFileDialog.class, "chrriis.dj.nativeswing.swtimpl.components.core.NativeFileDialog", new Class[0], new Object[0]);
    }
    
    public void show(final Component component) {
        this.nativeFileDialog.show(component);
    }
    
    public String getSelectedFileName() {
        return this.nativeFileDialog.getSelectedFileName();
    }
    
    public String[] getSelectedFileNames() {
        return this.nativeFileDialog.getSelectedFileNames();
    }
    
    public void setSelectedFileName(final String selectedFileName) {
        this.nativeFileDialog.setSelectedFileName(selectedFileName);
    }
    
    public SelectionMode getSelectionMode() {
        return this.nativeFileDialog.getSelectionMode();
    }
    
    public void setSelectionMode(final SelectionMode selectionMode) {
        this.nativeFileDialog.setSelectionMode(selectionMode);
    }
    
    public DialogType getDialogType() {
        return this.nativeFileDialog.getDialogType();
    }
    
    public void setDialogType(final DialogType dialogType) {
        this.nativeFileDialog.setDialogType(dialogType);
    }
    
    public void setConfirmedOverwrite(final boolean isConfirmedOverwrite) {
        this.nativeFileDialog.setConfirmedOverwrite(isConfirmedOverwrite);
    }
    
    public boolean isConfirmedOverwrite() {
        return this.nativeFileDialog.isConfirmedOverwrite();
    }
    
    public String getParentDirectory() {
        return this.nativeFileDialog.getParentDirectory();
    }
    
    public void setParentDirectory(final String parentDirectory) {
        this.nativeFileDialog.setParentDirectory(parentDirectory);
    }
    
    public void setExtensionFilters(final String[] extensionFilters, final String[] extensionFiltersNames, final int selectedExtensionFilterIndex) {
        this.nativeFileDialog.setExtensionFilters(extensionFilters, extensionFiltersNames, selectedExtensionFilterIndex);
    }
    
    public String[] getExtensionFilters() {
        return this.nativeFileDialog.getExtensionFilters();
    }
    
    public String[] getExtensionFiltersNames() {
        return this.nativeFileDialog.getExtensionFiltersNames();
    }
    
    public int getSelectedExtensionFilterIndex() {
        return this.nativeFileDialog.getSelectedExtensionFilterIndex();
    }
    
    public void setTitle(final String title) {
        this.nativeFileDialog.setTitle(title);
    }
    
    public String getTitle() {
        return this.nativeFileDialog.getTitle();
    }
    
    public enum SelectionMode
    {
        SINGLE_SELECTION, 
        MULTIPLE_SELECTION;
    }
    
    public enum DialogType
    {
        OPEN_DIALOG_TYPE, 
        SAVE_DIALOG_TYPE;
    }
}
