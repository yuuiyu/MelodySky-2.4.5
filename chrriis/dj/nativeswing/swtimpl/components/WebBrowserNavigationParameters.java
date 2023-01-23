//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;
import chrriis.dj.nativeswing.common.*;

public class WebBrowserNavigationParameters
{
    private String[] headers;
    private String postData;
    
    public void setHeaders(final Map<String, String> keyValueMap) {
        if (keyValueMap == null || keyValueMap.isEmpty()) {
            this.headers = null;
            return;
        }
        final List<String> headerList = new ArrayList<String>();
        for (final String key : keyValueMap.keySet()) {
            if (key != null && key.length() > 0) {
                headerList.add(key + ": " + keyValueMap.get(key));
            }
        }
        this.headers = headerList.toArray(new String[0]);
    }
    
    public String[] getHeaders() {
        return this.headers;
    }
    
    public void setPostData(final String postData) {
        this.postData = postData;
    }
    
    public void setPostData(final Map<String, String> keyValueMap) {
        if (keyValueMap == null || keyValueMap.isEmpty()) {
            this.postData = null;
            return;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : keyValueMap.keySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            if (key != null && key.length() > 0) {
                sb.append(Utils.encodeURL(key));
                sb.append('=');
            }
            sb.append(Utils.encodeURL((String)keyValueMap.get(key)));
        }
        this.postData = sb.toString();
    }
    
    public String getPostData() {
        return this.postData;
    }
}
