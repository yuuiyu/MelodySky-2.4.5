//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class WebBrowserNavigationEvent extends WebBrowserEvent
{
    private String newResourceLocation;
    private boolean isTopFrame;
    private boolean isConsumed;
    
    public WebBrowserNavigationEvent(final JWebBrowser webBrowser, final String newResourceLocation, final boolean isTopFrame) {
        super(webBrowser);
        this.newResourceLocation = newResourceLocation;
        this.isTopFrame = isTopFrame;
    }
    
    public String getNewResourceLocation() {
        return this.newResourceLocation;
    }
    
    public boolean isTopFrame() {
        return this.isTopFrame;
    }
    
    public void consume() {
        this.isConsumed = true;
    }
    
    public boolean isConsumed() {
        return this.isConsumed;
    }
}
