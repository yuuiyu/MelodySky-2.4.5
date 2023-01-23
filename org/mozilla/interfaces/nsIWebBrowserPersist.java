//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserPersist extends nsICancelable
{
    public static final String NS_IWEBBROWSERPERSIST_IID = "{dd4e0a6a-210f-419a-ad85-40e8543b9465}";
    public static final long PERSIST_FLAGS_NONE = 0L;
    public static final long PERSIST_FLAGS_FROM_CACHE = 1L;
    public static final long PERSIST_FLAGS_BYPASS_CACHE = 2L;
    public static final long PERSIST_FLAGS_IGNORE_REDIRECTED_DATA = 4L;
    public static final long PERSIST_FLAGS_IGNORE_IFRAMES = 8L;
    public static final long PERSIST_FLAGS_NO_CONVERSION = 16L;
    public static final long PERSIST_FLAGS_REPLACE_EXISTING_FILES = 32L;
    public static final long PERSIST_FLAGS_NO_BASE_TAG_MODIFICATIONS = 64L;
    public static final long PERSIST_FLAGS_FIXUP_ORIGINAL_DOM = 128L;
    public static final long PERSIST_FLAGS_FIXUP_LINKS_TO_DESTINATION = 256L;
    public static final long PERSIST_FLAGS_DONT_FIXUP_LINKS = 512L;
    public static final long PERSIST_FLAGS_SERIALIZE_OUTPUT = 1024L;
    public static final long PERSIST_FLAGS_DONT_CHANGE_FILENAMES = 2048L;
    public static final long PERSIST_FLAGS_FAIL_ON_BROKEN_LINKS = 4096L;
    public static final long PERSIST_FLAGS_CLEANUP_ON_FAILURE = 8192L;
    public static final long PERSIST_FLAGS_AUTODETECT_APPLY_CONVERSION = 16384L;
    public static final long PERSIST_STATE_READY = 1L;
    public static final long PERSIST_STATE_SAVING = 2L;
    public static final long PERSIST_STATE_FINISHED = 3L;
    public static final long ENCODE_FLAGS_SELECTION_ONLY = 1L;
    public static final long ENCODE_FLAGS_FORMATTED = 2L;
    public static final long ENCODE_FLAGS_RAW = 4L;
    public static final long ENCODE_FLAGS_BODY_ONLY = 8L;
    public static final long ENCODE_FLAGS_PREFORMATTED = 16L;
    public static final long ENCODE_FLAGS_WRAP = 32L;
    public static final long ENCODE_FLAGS_FORMAT_FLOWED = 64L;
    public static final long ENCODE_FLAGS_ABSOLUTE_LINKS = 128L;
    public static final long ENCODE_FLAGS_ENCODE_W3C_ENTITIES = 256L;
    public static final long ENCODE_FLAGS_CR_LINEBREAKS = 512L;
    public static final long ENCODE_FLAGS_LF_LINEBREAKS = 1024L;
    public static final long ENCODE_FLAGS_NOSCRIPT_CONTENT = 2048L;
    public static final long ENCODE_FLAGS_NOFRAMES_CONTENT = 4096L;
    public static final long ENCODE_FLAGS_ENCODE_BASIC_ENTITIES = 8192L;
    public static final long ENCODE_FLAGS_ENCODE_LATIN1_ENTITIES = 16384L;
    public static final long ENCODE_FLAGS_ENCODE_HTML_ENTITIES = 32768L;
    
    long getPersistFlags();
    
    void setPersistFlags(final long p0);
    
    long getCurrentState();
    
    long getResult();
    
    nsIWebProgressListener getProgressListener();
    
    void setProgressListener(final nsIWebProgressListener p0);
    
    void saveURI(final nsIURI p0, final nsISupports p1, final nsIURI p2, final nsIInputStream p3, final String p4, final nsISupports p5);
    
    void saveChannel(final nsIChannel p0, final nsISupports p1);
    
    void saveDocument(final nsIDOMDocument p0, final nsISupports p1, final nsISupports p2, final String p3, final long p4, final long p5);
    
    void cancelSave();
}
