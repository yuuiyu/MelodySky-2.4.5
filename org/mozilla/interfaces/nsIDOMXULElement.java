//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULElement extends nsIDOMElement
{
    public static final String NS_IDOMXULELEMENT_IID = "{0574ed81-c088-11d2-96ed-00104b7b7deb}";
    
    String getId();
    
    void setId(final String p0);
    
    String getClassName();
    
    void setClassName(final String p0);
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getDir();
    
    void setDir(final String p0);
    
    String getFlex();
    
    void setFlex(final String p0);
    
    String getFlexGroup();
    
    void setFlexGroup(final String p0);
    
    String getOrdinal();
    
    void setOrdinal(final String p0);
    
    String getOrient();
    
    void setOrient(final String p0);
    
    String getPack();
    
    void setPack(final String p0);
    
    boolean getHidden();
    
    void setHidden(final boolean p0);
    
    boolean getCollapsed();
    
    void setCollapsed(final boolean p0);
    
    String getObserves();
    
    void setObserves(final String p0);
    
    String getMenu();
    
    void setMenu(final String p0);
    
    String getContextMenu();
    
    void setContextMenu(final String p0);
    
    String getTooltip();
    
    void setTooltip(final String p0);
    
    String getWidth();
    
    void setWidth(final String p0);
    
    String getHeight();
    
    void setHeight(final String p0);
    
    String getMinWidth();
    
    void setMinWidth(final String p0);
    
    String getMinHeight();
    
    void setMinHeight(final String p0);
    
    String getMaxWidth();
    
    void setMaxWidth(final String p0);
    
    String getMaxHeight();
    
    void setMaxHeight(final String p0);
    
    String getPersist();
    
    void setPersist(final String p0);
    
    String getLeft();
    
    void setLeft(final String p0);
    
    String getTop();
    
    void setTop(final String p0);
    
    String getDatasources();
    
    void setDatasources(final String p0);
    
    String getRef();
    
    void setRef(final String p0);
    
    String getTooltipText();
    
    void setTooltipText(final String p0);
    
    String getStatusText();
    
    void setStatusText(final String p0);
    
    boolean getAllowEvents();
    
    void setAllowEvents(final boolean p0);
    
    nsIDOMCSSStyleDeclaration getStyle();
    
    nsIRDFCompositeDataSource getDatabase();
    
    nsIXULTemplateBuilder getBuilder();
    
    nsIRDFResource getResource();
    
    nsIControllers getControllers();
    
    nsIBoxObject getBoxObject();
    
    void focus();
    
    void blur();
    
    void click();
    
    void doCommand();
    
    nsIDOMNodeList getElementsByAttribute(final String p0, final String p1);
}
