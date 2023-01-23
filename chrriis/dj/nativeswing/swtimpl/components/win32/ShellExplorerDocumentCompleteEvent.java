//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

public class ShellExplorerDocumentCompleteEvent
{
    private JWShellExplorer shellExplorer;
    private String location;
    
    public ShellExplorerDocumentCompleteEvent(final JWShellExplorer shellExplorer, final String location) {
        this.shellExplorer = shellExplorer;
        this.location = location;
    }
    
    public JWShellExplorer getShellExplorer() {
        return this.shellExplorer;
    }
    
    public String getLocation() {
        return this.location;
    }
}
