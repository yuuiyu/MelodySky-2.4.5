//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.io.*;
import java.util.*;

final class lIllI extends WebServer.WebServerContent
{
    final /* synthetic */ String val$resourcePath;
    final /* synthetic */ WebServer val$webServer;
    
    lIllI(final String val$resourcePath, final WebServer val$webServer) {
        this.val$resourcePath = val$resourcePath;
        this.val$webServer = val$webServer;
    }
    
    @Override
    public String getContentType() {
        final int index = this.val$resourcePath.lastIndexOf(46);
        return WebServer.WebServerContent.getDefaultMimeType((index == -1) ? null : this.val$resourcePath.substring(index));
    }
    
    @Override
    public InputStream getInputStream() {
        try {
            for (final ClassLoader referenceClassLoader : WebServer.access$400(this.val$webServer)) {
                final InputStream in = referenceClassLoader.getResourceAsStream(this.val$resourcePath);
                if (in != null) {
                    return in;
                }
            }
            return Utils.getResourceAsStreamWithinJavaModules(WebServer.class, '/' + this.val$resourcePath);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
