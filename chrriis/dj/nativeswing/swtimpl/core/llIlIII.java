//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

class llIlIII extends Thread
{
    final /* synthetic */ llIIlIl this$0;
    
    llIlIII(final llIIlIl this$0, final String x0) {
        this.this$0 = this$0;
        super(x0);
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(20000L);
        }
        catch (InterruptedException ex) {}
        Runtime.getRuntime().halt(-1);
    }
}
