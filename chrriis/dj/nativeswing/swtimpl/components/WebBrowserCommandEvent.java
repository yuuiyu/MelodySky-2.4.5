//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class WebBrowserCommandEvent extends WebBrowserEvent
{
    private String command;
    private Object[] parameters;
    
    public WebBrowserCommandEvent(final JWebBrowser webBrowser, final String command, final Object[] parameters) {
        super(webBrowser);
        this.command = command;
        this.parameters = parameters;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public Object[] getParameters() {
        return this.parameters;
    }
}
