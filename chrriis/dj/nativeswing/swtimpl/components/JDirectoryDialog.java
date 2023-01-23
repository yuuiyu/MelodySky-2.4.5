//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import java.awt.*;

public class JDirectoryDialog
{
    private INativeDirectoryDialog nativeDirectoryDialog;
    
    public JDirectoryDialog() {
        this.nativeDirectoryDialog = NativeCoreObjectFactory.create(INativeDirectoryDialog.class, "chrriis.dj.nativeswing.swtimpl.components.core.NativeDirectoryDialog", new Class[0], new Object[0]);
    }
    
    public void show(final Component component) {
        this.nativeDirectoryDialog.show(component);
    }
    
    public String getSelectedDirectory() {
        return this.nativeDirectoryDialog.getSelectedDirectory();
    }
    
    public void setSelectedDirectory(final String selectedDirectory) {
        this.nativeDirectoryDialog.setSelectedDirectory(selectedDirectory);
    }
    
    public void setTitle(final String title) {
        this.nativeDirectoryDialog.setTitle(title);
    }
    
    public String getTitle() {
        return this.nativeDirectoryDialog.getTitle();
    }
    
    public void setMessage(final String message) {
        this.nativeDirectoryDialog.setMessage(message);
    }
    
    public String getMessage() {
        return this.nativeDirectoryDialog.getMessage();
    }
}
