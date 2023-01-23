//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.common.*;
import java.util.*;

public class FlashPluginOptions
{
    private Map<String, String> keyToValueVariableMap;
    private Map<String, String> keyToValueParameterMap;
    
    public FlashPluginOptions() {
        this.setParameters(null);
        this.setVariables(null);
    }
    
    public Map<String, String> getVariables() {
        return this.keyToValueVariableMap;
    }
    
    public void setVariables(final Map<String, String> keyToValueVariableMap) {
        if (keyToValueVariableMap == null) {
            this.keyToValueVariableMap = Collections.synchronizedMap(new HashMap<String, String>());
        }
        else {
            this.keyToValueVariableMap = Collections.synchronizedMap(new HashMap<String, String>(keyToValueVariableMap));
        }
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
    
    Map<String, String> getHTMLParameters() {
        final HashMap<String, String> htmlParameters = new HashMap<String, String>(this.getParameters());
        final StringBuilder variablesSB = new StringBuilder();
        for (final Map.Entry<String, String> variable : this.getVariables().entrySet()) {
            if (variablesSB.length() > 0) {
                variablesSB.append('&');
            }
            variablesSB.append(Utils.encodeURL((String)variable.getKey())).append('=').append(Utils.encodeURL((String)variable.getValue()));
        }
        if (variablesSB.length() > 0) {
            htmlParameters.put("flashvars", variablesSB.toString());
        }
        htmlParameters.put("allowScriptAccess", "always");
        htmlParameters.put("swliveconnect", "true");
        return htmlParameters;
    }
}
