//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXTFElement extends nsISupports
{
    public static final String NS_IXTFELEMENT_IID = "{a8b607fd-24b6-4a8c-9a89-d9b24f8e2592}";
    public static final long ELEMENT_TYPE_GENERIC_ELEMENT = 0L;
    public static final long ELEMENT_TYPE_SVG_VISUAL = 1L;
    public static final long ELEMENT_TYPE_XML_VISUAL = 2L;
    public static final long ELEMENT_TYPE_XUL_VISUAL = 3L;
    public static final long ELEMENT_TYPE_BINDABLE = 4L;
    public static final long NOTIFY_WILL_CHANGE_DOCUMENT = 1L;
    public static final long NOTIFY_DOCUMENT_CHANGED = 2L;
    public static final long NOTIFY_WILL_CHANGE_PARENT = 4L;
    public static final long NOTIFY_PARENT_CHANGED = 8L;
    public static final long NOTIFY_WILL_INSERT_CHILD = 16L;
    public static final long NOTIFY_CHILD_INSERTED = 32L;
    public static final long NOTIFY_WILL_APPEND_CHILD = 64L;
    public static final long NOTIFY_CHILD_APPENDED = 128L;
    public static final long NOTIFY_WILL_REMOVE_CHILD = 256L;
    public static final long NOTIFY_CHILD_REMOVED = 512L;
    public static final long NOTIFY_WILL_SET_ATTRIBUTE = 1024L;
    public static final long NOTIFY_ATTRIBUTE_SET = 2048L;
    public static final long NOTIFY_WILL_REMOVE_ATTRIBUTE = 4096L;
    public static final long NOTIFY_ATTRIBUTE_REMOVED = 8192L;
    public static final long NOTIFY_BEGIN_ADDING_CHILDREN = 16384L;
    public static final long NOTIFY_DONE_ADDING_CHILDREN = 32768L;
    public static final long NOTIFY_HANDLE_DEFAULT = 65536L;
    
    void onDestroyed();
    
    long getElementType();
    
    boolean getIsAttributeHandler();
    
    String[] getScriptingInterfaces(final long[] p0);
    
    void willChangeDocument(final nsIDOMDocument p0);
    
    void documentChanged(final nsIDOMDocument p0);
    
    void willChangeParent(final nsIDOMElement p0);
    
    void parentChanged(final nsIDOMElement p0);
    
    void willInsertChild(final nsIDOMNode p0, final long p1);
    
    void childInserted(final nsIDOMNode p0, final long p1);
    
    void willAppendChild(final nsIDOMNode p0);
    
    void childAppended(final nsIDOMNode p0);
    
    void willRemoveChild(final long p0);
    
    void childRemoved(final long p0);
    
    void willSetAttribute(final nsIAtom p0, final String p1);
    
    void attributeSet(final nsIAtom p0, final String p1);
    
    void willRemoveAttribute(final nsIAtom p0);
    
    void attributeRemoved(final nsIAtom p0);
    
    void beginAddingChildren();
    
    void doneAddingChildren();
    
    boolean handleDefault(final nsIDOMEvent p0);
    
    void cloneState(final nsIDOMElement p0);
}
