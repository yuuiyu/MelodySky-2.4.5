//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMWindowInternal extends nsIDOMWindow2
{
    public static final String NS_IDOMWINDOWINTERNAL_IID = "{f914492c-0138-4123-a634-6ef8e3f126f8}";
    
    nsIDOMWindowInternal getWindow();
    
    nsIDOMWindowInternal getSelf();
    
    nsIDOMNavigator getNavigator();
    
    nsIDOMScreen getScreen();
    
    nsIDOMHistory getHistory();
    
    nsIDOMWindow getContent();
    
    nsIDOMBarProp getMenubar();
    
    nsIDOMBarProp getToolbar();
    
    nsIDOMBarProp getLocationbar();
    
    nsIDOMBarProp getPersonalbar();
    
    nsIDOMBarProp getStatusbar();
    
    nsIDOMBarProp getDirectories();
    
    boolean getClosed();
    
    nsIDOMCrypto getCrypto();
    
    nsIDOMPkcs11 getPkcs11();
    
    nsIControllers getControllers();
    
    nsIDOMWindowInternal getOpener();
    
    void setOpener(final nsIDOMWindowInternal p0);
    
    String getStatus();
    
    void setStatus(final String p0);
    
    String getDefaultStatus();
    
    void setDefaultStatus(final String p0);
    
    nsIDOMLocation getLocation();
    
    int getInnerWidth();
    
    void setInnerWidth(final int p0);
    
    int getInnerHeight();
    
    void setInnerHeight(final int p0);
    
    int getOuterWidth();
    
    void setOuterWidth(final int p0);
    
    int getOuterHeight();
    
    void setOuterHeight(final int p0);
    
    int getScreenX();
    
    void setScreenX(final int p0);
    
    int getScreenY();
    
    void setScreenY(final int p0);
    
    int getPageXOffset();
    
    int getPageYOffset();
    
    int getScrollMaxX();
    
    int getScrollMaxY();
    
    long getLength();
    
    boolean getFullScreen();
    
    void setFullScreen(final boolean p0);
    
    void alert(final String p0);
    
    boolean confirm(final String p0);
    
    String prompt(final String p0, final String p1, final String p2, final long p3);
    
    void focus();
    
    void blur();
    
    void back();
    
    void forward();
    
    void home();
    
    void stop();
    
    void print();
    
    void moveTo(final int p0, final int p1);
    
    void moveBy(final int p0, final int p1);
    
    void resizeTo(final int p0, final int p1);
    
    void resizeBy(final int p0, final int p1);
    
    void scroll(final int p0, final int p1);
    
    void close();
    
    void updateCommands(final String p0);
    
    String atob(final String p0);
    
    String btoa(final String p0);
    
    nsIDOMElement getFrameElement();
}
