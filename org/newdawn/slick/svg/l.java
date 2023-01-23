//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.svg;

import org.xml.sax.*;
import java.io.*;

class l implements EntityResolver
{
    final /* synthetic */ InkscapeLoader this$0;
    
    l(final InkscapeLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        return new InputSource(new ByteArrayInputStream(new byte[0]));
    }
}
