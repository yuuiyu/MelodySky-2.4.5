//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;

public class VLCPluginOptions
{
    private Map<String, String> keyToValueParameterMap;
    
    public VLCPluginOptions() {
        this.setParameters(null);
    }
    
    public Map<String, String> getParameters() {
        return this.keyToValueParameterMap;
    }
    
    public void setParameters(final Map<String, String> keyToValueParameterMap) {
        if (keyToValueParameterMap == null) {
            this.keyToValueParameterMap = Collections.synchronizedMap(new HashMap<String, String>());
        }
        else {
            this.keyToValueParameterMap = Collections.synchronizedMap(new HashMap<String, String>(keyToValueParameterMap));
        }
    }
}
