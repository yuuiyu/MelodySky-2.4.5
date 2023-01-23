//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public abstract class WebBrowserFunction
{
    private String name;
    
    public WebBrowserFunction(final String functionName) {
        this.name = functionName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public abstract Object invoke(final JWebBrowser p0, final Object... p1);
}
