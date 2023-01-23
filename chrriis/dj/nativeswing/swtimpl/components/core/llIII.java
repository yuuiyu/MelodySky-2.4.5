//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;
import chrriis.dj.nativeswing.common.*;
import java.util.*;

final class llIII implements LocationListener
{
    final /* synthetic */ Browser val$browser;
    
    llIII(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void changed(final LocationEvent e) {
        this.val$browser.setData("Browser.loading", (Object)false);
        new NativeWebBrowser.CMJ_locationChanged(null).asyncExec((Control)this.val$browser, e.location, e.top);
    }
    
    public void changing(final LocationEvent e) {
        final String location = e.location;
        if (location.startsWith("command://")) {
            e.doit = false;
            String query = location.substring("command://".length());
            if (query.endsWith("/")) {
                query = query.substring(0, query.length() - 1);
            }
            final List<String> queryElementList = new ArrayList<String>();
            final StringTokenizer st = new StringTokenizer(query, "&", true);
            String lastToken = null;
            while (st.hasMoreTokens()) {
                final String token = st.nextToken();
                if ("&".equals(token)) {
                    if (lastToken == null) {
                        queryElementList.add("");
                    }
                    lastToken = null;
                }
                else {
                    lastToken = token;
                    queryElementList.add(Utils.decodeURL(token));
                }
            }
            if (lastToken == null) {
                queryElementList.add("");
            }
            final String command = queryElementList.isEmpty() ? "" : queryElementList.remove(0);
            final String[] args = queryElementList.toArray(new String[0]);
            new NativeWebBrowser.CMJ_commandReceived(null).asyncExec((Control)this.val$browser, command, args);
            return;
        }
        if (location.startsWith("javascript:")) {
            return;
        }
        this.val$browser.setData("CMJ_updateStatus.status", (Object)null);
        this.val$browser.setData("CMJ_updateProgress.progress", (Object)null);
        this.val$browser.setData("Browser.loading", (Object)true);
        if (!(e.doit = Boolean.TRUE.equals(new NativeWebBrowser.CMJ_locationChanging(null).syncExec((Control)this.val$browser, location, e.top)))) {
            this.val$browser.setData("Browser.loading", (Object)false);
            new NativeWebBrowser.CMJ_locationChangeCanceled(null).asyncExec((Control)this.val$browser, location, e.top);
        }
    }
}
