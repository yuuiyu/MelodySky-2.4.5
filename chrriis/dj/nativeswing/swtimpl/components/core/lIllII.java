//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.common.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;

final class lIllII implements ProgressListener
{
    final /* synthetic */ Browser val$browser;
    
    lIllII(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    private void updateProgress(final int loadingProgress) {
        final Integer oldLoadingProgress = (Integer)this.val$browser.getData("CMJ_updateProgress.progress");
        if (!Utils.equals((Object)oldLoadingProgress, (Object)loadingProgress)) {
            this.val$browser.setData("CMJ_updateProgress.progress", (Object)loadingProgress);
            new NativeWebBrowser.CMJ_updateLoadingProgress(null).asyncExec((Control)this.val$browser, loadingProgress);
        }
    }
    
    public void changed(final ProgressEvent e) {
        if (e.total <= 0 || e.total < e.current) {
            return;
        }
        this.val$browser.setData("Browser.loading", (Object)true);
        this.updateProgress((e.current == e.total) ? 100 : Math.min(e.current * 100 / e.total, 99));
    }
    
    public void completed(final ProgressEvent progressevent) {
        if ("edge".equals(this.val$browser.getBrowserType())) {
            this.val$browser.execute("if(!document.swtMouseHandled) {var f = function (event){nsBrowserFocus();};document.addEventListener('mousedown', f, true);document.swtMouseHandled = true;}");
        }
        this.val$browser.setData("Browser.loading", (Object)false);
        this.updateProgress(100);
    }
}
