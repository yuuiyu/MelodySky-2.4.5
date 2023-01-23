//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.net.*;
import java.io.*;

final class llII extends WebServer.WebServerContent
{
    final /* synthetic */ String val$resourceURL_;
    
    llII(final String val$resourceURL_) {
        this.val$resourceURL_ = val$resourceURL_;
    }
    
    @Override
    public long getContentLength() {
        final File file = Utils.getLocalFile(this.val$resourceURL_);
        if (file != null) {
            return file.length();
        }
        return super.getContentLength();
    }
    
    @Override
    public String getContentType() {
        final int index = this.val$resourceURL_.lastIndexOf(46);
        return WebServer.WebServerContent.getDefaultMimeType((index == -1) ? null : this.val$resourceURL_.substring(index));
    }
    
    @Override
    public InputStream getInputStream() {
        try {
            return new URL(this.val$resourceURL_).openStream();
        }
        catch (Exception ex) {
            try {
                return new FileInputStream("/" + this.val$resourceURL_);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
