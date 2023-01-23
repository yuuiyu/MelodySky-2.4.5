//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.util.*;

public class MimeTypes
{
    private static Map<String, String> extensionToMimeTypeMap;
    
    private MimeTypes() {
    }
    
    public static String getMimeType(String extension) {
        if (extension == null) {
            return null;
        }
        if (extension.startsWith(".")) {
            extension = extension.substring(1);
        }
        extension = extension.toLowerCase(Locale.ENGLISH);
        synchronized (MimeTypes.extensionToMimeTypeMap) {
            return MimeTypes.extensionToMimeTypeMap.get(extension);
        }
    }
    
    public static void addMissingMimeType(String extension, final String mimeType) {
        if (extension == null) {
            return;
        }
        if (extension.startsWith(".")) {
            extension = extension.substring(1);
        }
        extension = extension.toLowerCase(Locale.ENGLISH);
        synchronized (MimeTypes.extensionToMimeTypeMap) {
            MimeTypes.extensionToMimeTypeMap.put(extension, mimeType);
        }
    }
    
    static {
        synchronized (MimeTypes.extensionToMimeTypeMap = new HashMap<String, String>()) {
            MimeTypes.extensionToMimeTypeMap.put("323", "text/h323");
            MimeTypes.extensionToMimeTypeMap.put("acx", "application/internet-property-stream");
            MimeTypes.extensionToMimeTypeMap.put("ai", "application/postscript");
            MimeTypes.extensionToMimeTypeMap.put("aif", "audio/x-aiff");
            MimeTypes.extensionToMimeTypeMap.put("aifc", "audio/x-aiff");
            MimeTypes.extensionToMimeTypeMap.put("aiff", "audio/x-aiff");
            MimeTypes.extensionToMimeTypeMap.put("asf", "video/x-ms-asf");
            MimeTypes.extensionToMimeTypeMap.put("asr", "video/x-ms-asf");
            MimeTypes.extensionToMimeTypeMap.put("asx", "video/x-ms-asf");
            MimeTypes.extensionToMimeTypeMap.put("au", "audio/basic");
            MimeTypes.extensionToMimeTypeMap.put("avi", "video/x-msvideo");
            MimeTypes.extensionToMimeTypeMap.put("axs", "application/olescript");
            MimeTypes.extensionToMimeTypeMap.put("bas", "text/plain");
            MimeTypes.extensionToMimeTypeMap.put("bcpio", "application/x-bcpio");
            MimeTypes.extensionToMimeTypeMap.put("bin", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("bmp", "image/bmp");
            MimeTypes.extensionToMimeTypeMap.put("c", "text/plain");
            MimeTypes.extensionToMimeTypeMap.put("cat", "application/vnd.ms-pkiseccat");
            MimeTypes.extensionToMimeTypeMap.put("cdf", "application/x-cdf");
            MimeTypes.extensionToMimeTypeMap.put("cer", "application/x-x509-ca-cert");
            MimeTypes.extensionToMimeTypeMap.put("class", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("clp", "application/x-msclip");
            MimeTypes.extensionToMimeTypeMap.put("cmx", "image/x-cmx");
            MimeTypes.extensionToMimeTypeMap.put("cod", "image/cis-cod");
            MimeTypes.extensionToMimeTypeMap.put("cpio", "application/x-cpio");
            MimeTypes.extensionToMimeTypeMap.put("crd", "application/x-mscardfile");
            MimeTypes.extensionToMimeTypeMap.put("crl", "application/pkix-crl");
            MimeTypes.extensionToMimeTypeMap.put("crt", "application/x-x509-ca-cert");
            MimeTypes.extensionToMimeTypeMap.put("csh", "application/x-csh");
            MimeTypes.extensionToMimeTypeMap.put("css", "text/css");
            MimeTypes.extensionToMimeTypeMap.put("dcr", "application/x-director");
            MimeTypes.extensionToMimeTypeMap.put("der", "application/x-x509-ca-cert");
            MimeTypes.extensionToMimeTypeMap.put("dir", "application/x-director");
            MimeTypes.extensionToMimeTypeMap.put("dll", "application/x-msdownload");
            MimeTypes.extensionToMimeTypeMap.put("dms", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("doc", "application/msword");
            MimeTypes.extensionToMimeTypeMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            MimeTypes.extensionToMimeTypeMap.put("dot", "application/msword");
            MimeTypes.extensionToMimeTypeMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
            MimeTypes.extensionToMimeTypeMap.put("dvi", "application/x-dvi");
            MimeTypes.extensionToMimeTypeMap.put("dxr", "application/x-director");
            MimeTypes.extensionToMimeTypeMap.put("eml", "message/rfc822");
            MimeTypes.extensionToMimeTypeMap.put("eps", "application/postscript");
            MimeTypes.extensionToMimeTypeMap.put("etx", "text/x-setext");
            MimeTypes.extensionToMimeTypeMap.put("evy", "application/envoy");
            MimeTypes.extensionToMimeTypeMap.put("exe", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("fif", "application/fractals");
            MimeTypes.extensionToMimeTypeMap.put("flr", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("gif", "image/gif");
            MimeTypes.extensionToMimeTypeMap.put("gtar", "application/x-gtar");
            MimeTypes.extensionToMimeTypeMap.put("gz", "application/x-gzip");
            MimeTypes.extensionToMimeTypeMap.put("h", "text/plain");
            MimeTypes.extensionToMimeTypeMap.put("hdf", "application/x-hdf");
            MimeTypes.extensionToMimeTypeMap.put("hlp", "application/winhlp");
            MimeTypes.extensionToMimeTypeMap.put("hqx", "application/mac-binhex40");
            MimeTypes.extensionToMimeTypeMap.put("hta", "application/hta");
            MimeTypes.extensionToMimeTypeMap.put("htc", "text/x-component");
            MimeTypes.extensionToMimeTypeMap.put("htm", "text/html");
            MimeTypes.extensionToMimeTypeMap.put("html", "text/html");
            MimeTypes.extensionToMimeTypeMap.put("htt", "text/webviewhtml");
            MimeTypes.extensionToMimeTypeMap.put("ico", "image/x-icon");
            MimeTypes.extensionToMimeTypeMap.put("ief", "image/ief");
            MimeTypes.extensionToMimeTypeMap.put("iii", "application/x-iphone");
            MimeTypes.extensionToMimeTypeMap.put("ins", "application/x-internet-signup");
            MimeTypes.extensionToMimeTypeMap.put("isp", "application/x-internet-signup");
            MimeTypes.extensionToMimeTypeMap.put("jfif", "image/pipeg");
            MimeTypes.extensionToMimeTypeMap.put("jnlp", "application/x-java-jnlp-file");
            MimeTypes.extensionToMimeTypeMap.put("jpe", "image/jpeg");
            MimeTypes.extensionToMimeTypeMap.put("jpeg", "image/jpeg");
            MimeTypes.extensionToMimeTypeMap.put("jpg", "image/jpeg");
            MimeTypes.extensionToMimeTypeMap.put("js", "application/x-javascript");
            MimeTypes.extensionToMimeTypeMap.put("latex", "application/x-latex");
            MimeTypes.extensionToMimeTypeMap.put("lha", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("lsf", "video/x-la-asf");
            MimeTypes.extensionToMimeTypeMap.put("lsx", "video/x-la-asf");
            MimeTypes.extensionToMimeTypeMap.put("lzh", "application/octet-stream");
            MimeTypes.extensionToMimeTypeMap.put("m13", "application/x-msmediaview");
            MimeTypes.extensionToMimeTypeMap.put("m14", "application/x-msmediaview");
            MimeTypes.extensionToMimeTypeMap.put("m3u", "audio/x-mpegurl");
            MimeTypes.extensionToMimeTypeMap.put("man", "application/x-troff-man");
            MimeTypes.extensionToMimeTypeMap.put("mdb", "application/x-msaccess");
            MimeTypes.extensionToMimeTypeMap.put("me", "application/x-troff-me");
            MimeTypes.extensionToMimeTypeMap.put("mht", "message/rfc822");
            MimeTypes.extensionToMimeTypeMap.put("mhtml", "message/rfc822");
            MimeTypes.extensionToMimeTypeMap.put("mid", "audio/mid");
            MimeTypes.extensionToMimeTypeMap.put("mny", "application/x-msmoney");
            MimeTypes.extensionToMimeTypeMap.put("mov", "video/quicktime");
            MimeTypes.extensionToMimeTypeMap.put("movie", "video/x-sgi-movie");
            MimeTypes.extensionToMimeTypeMap.put("mp2", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mp3", "audio/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mp4", "video/mp4");
            MimeTypes.extensionToMimeTypeMap.put("mpa", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mpe", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mpeg", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mpg", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("mpp", "application/vnd.ms-project");
            MimeTypes.extensionToMimeTypeMap.put("mpv2", "video/mpeg");
            MimeTypes.extensionToMimeTypeMap.put("ms", "application/x-troff-ms");
            MimeTypes.extensionToMimeTypeMap.put("msg", "application/vnd.ms-outlook");
            MimeTypes.extensionToMimeTypeMap.put("mvb", "application/x-msmediaview");
            MimeTypes.extensionToMimeTypeMap.put("nws", "message/rfc822");
            MimeTypes.extensionToMimeTypeMap.put("oda", "application/oda");
            MimeTypes.extensionToMimeTypeMap.put("odp", "application/vnd.oasis.opendocument.presentation");
            MimeTypes.extensionToMimeTypeMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
            MimeTypes.extensionToMimeTypeMap.put("odt", "application/vnd.oasis.opendocument.text");
            MimeTypes.extensionToMimeTypeMap.put("oga", "audio/ogg");
            MimeTypes.extensionToMimeTypeMap.put("ogg", "audio/ogg");
            MimeTypes.extensionToMimeTypeMap.put("ogv", "video/ogg");
            MimeTypes.extensionToMimeTypeMap.put("p10", "application/pkcs10");
            MimeTypes.extensionToMimeTypeMap.put("p12", "application/x-pkcs12");
            MimeTypes.extensionToMimeTypeMap.put("p7b", "application/x-pkcs7-certificates");
            MimeTypes.extensionToMimeTypeMap.put("p7c", "application/x-pkcs7-mime");
            MimeTypes.extensionToMimeTypeMap.put("p7m", "application/x-pkcs7-mime");
            MimeTypes.extensionToMimeTypeMap.put("p7r", "application/x-pkcs7-certreqresp");
            MimeTypes.extensionToMimeTypeMap.put("p7s", "application/x-pkcs7-signature");
            MimeTypes.extensionToMimeTypeMap.put("pbm", "image/x-portable-bitmap");
            MimeTypes.extensionToMimeTypeMap.put("pdf", "application/pdf");
            MimeTypes.extensionToMimeTypeMap.put("pfx", "application/x-pkcs12");
            MimeTypes.extensionToMimeTypeMap.put("pgm", "image/x-portable-graymap");
            MimeTypes.extensionToMimeTypeMap.put("pko", "application/ynd.ms-pkipko");
            MimeTypes.extensionToMimeTypeMap.put("pma", "application/x-perfmon");
            MimeTypes.extensionToMimeTypeMap.put("pmc", "application/x-perfmon");
            MimeTypes.extensionToMimeTypeMap.put("pml", "application/x-perfmon");
            MimeTypes.extensionToMimeTypeMap.put("pmr", "application/x-perfmon");
            MimeTypes.extensionToMimeTypeMap.put("pmw", "application/x-perfmon");
            MimeTypes.extensionToMimeTypeMap.put("png", "image/png");
            MimeTypes.extensionToMimeTypeMap.put("pnm", "image/x-portable-anymap");
            MimeTypes.extensionToMimeTypeMap.put("pot,", "application/vnd.ms-powerpoint");
            MimeTypes.extensionToMimeTypeMap.put("potm,", "application/vnd.ms-powerpoint.template.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("potx,", "application/vnd.openxmlformats-officedocument.presentationml.template");
            MimeTypes.extensionToMimeTypeMap.put("ppa", "application/vnd.ms-powerpoint");
            MimeTypes.extensionToMimeTypeMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("ppm", "image/x-portable-pixmap");
            MimeTypes.extensionToMimeTypeMap.put("pps", "application/vnd.ms-powerpoint");
            MimeTypes.extensionToMimeTypeMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
            MimeTypes.extensionToMimeTypeMap.put("ppt", "application/vnd.ms-powerpoint");
            MimeTypes.extensionToMimeTypeMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
            MimeTypes.extensionToMimeTypeMap.put("prf", "application/pics-rules");
            MimeTypes.extensionToMimeTypeMap.put("ps", "application/postscript");
            MimeTypes.extensionToMimeTypeMap.put("pub", "application/x-mspublisher");
            MimeTypes.extensionToMimeTypeMap.put("qt", "video/quicktime");
            MimeTypes.extensionToMimeTypeMap.put("ra", "audio/x-pn-realaudio");
            MimeTypes.extensionToMimeTypeMap.put("ram", "audio/x-pn-realaudio");
            MimeTypes.extensionToMimeTypeMap.put("ras", "image/x-cmu-raster");
            MimeTypes.extensionToMimeTypeMap.put("rgb", "image/x-rgb");
            MimeTypes.extensionToMimeTypeMap.put("rmi", "audio/mid");
            MimeTypes.extensionToMimeTypeMap.put("roff", "application/x-troff");
            MimeTypes.extensionToMimeTypeMap.put("rtf", "application/rtf");
            MimeTypes.extensionToMimeTypeMap.put("rtx", "text/richtext");
            MimeTypes.extensionToMimeTypeMap.put("scd", "application/x-msschedule");
            MimeTypes.extensionToMimeTypeMap.put("sct", "text/scriptlet");
            MimeTypes.extensionToMimeTypeMap.put("setpay", "application/set-payment-initiation");
            MimeTypes.extensionToMimeTypeMap.put("setreg", "application/set-registration-initiation");
            MimeTypes.extensionToMimeTypeMap.put("sh", "application/x-sh");
            MimeTypes.extensionToMimeTypeMap.put("shar", "application/x-shar");
            MimeTypes.extensionToMimeTypeMap.put("sit", "application/x-stuffit");
            MimeTypes.extensionToMimeTypeMap.put("snd", "audio/basic");
            MimeTypes.extensionToMimeTypeMap.put("spc", "application/x-pkcs7-certificates");
            MimeTypes.extensionToMimeTypeMap.put("spl", "application/futuresplash");
            MimeTypes.extensionToMimeTypeMap.put("src", "application/x-wais-source");
            MimeTypes.extensionToMimeTypeMap.put("sst", "application/vnd.ms-pkicertstore");
            MimeTypes.extensionToMimeTypeMap.put("stl", "application/vnd.ms-pkistl");
            MimeTypes.extensionToMimeTypeMap.put("stm", "text/html");
            MimeTypes.extensionToMimeTypeMap.put("svg", "image/svg+xml");
            MimeTypes.extensionToMimeTypeMap.put("sv4cpio", "application/x-sv4cpio");
            MimeTypes.extensionToMimeTypeMap.put("sv4crc", "application/x-sv4crc");
            MimeTypes.extensionToMimeTypeMap.put("swf", "application/x-shockwave-flash");
            MimeTypes.extensionToMimeTypeMap.put("t", "application/x-troff");
            MimeTypes.extensionToMimeTypeMap.put("tar", "application/x-tar");
            MimeTypes.extensionToMimeTypeMap.put("tcl", "application/x-tcl");
            MimeTypes.extensionToMimeTypeMap.put("tex", "application/x-tex");
            MimeTypes.extensionToMimeTypeMap.put("texi", "application/x-texinfo");
            MimeTypes.extensionToMimeTypeMap.put("texinfo", "application/x-texinfo");
            MimeTypes.extensionToMimeTypeMap.put("tgz", "application/x-compressed");
            MimeTypes.extensionToMimeTypeMap.put("tif", "image/tiff");
            MimeTypes.extensionToMimeTypeMap.put("tiff", "image/tiff");
            MimeTypes.extensionToMimeTypeMap.put("tr", "application/x-troff");
            MimeTypes.extensionToMimeTypeMap.put("trm", "application/x-msterminal");
            MimeTypes.extensionToMimeTypeMap.put("tsv", "text/tab-separated-values");
            MimeTypes.extensionToMimeTypeMap.put("txt", "text/plain");
            MimeTypes.extensionToMimeTypeMap.put("uls", "text/iuls");
            MimeTypes.extensionToMimeTypeMap.put("ustar", "application/x-ustar");
            MimeTypes.extensionToMimeTypeMap.put("vcf", "text/x-vcard");
            MimeTypes.extensionToMimeTypeMap.put("vrml", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("wav", "audio/x-wav");
            MimeTypes.extensionToMimeTypeMap.put("wax", "audio/x-ms-wax");
            MimeTypes.extensionToMimeTypeMap.put("wcm", "application/vnd.ms-works");
            MimeTypes.extensionToMimeTypeMap.put("wdb", "application/vnd.ms-works");
            MimeTypes.extensionToMimeTypeMap.put("webm", "video/webm");
            MimeTypes.extensionToMimeTypeMap.put("wks", "application/vnd.ms-works");
            MimeTypes.extensionToMimeTypeMap.put("wm", "video/x-ms-wm");
            MimeTypes.extensionToMimeTypeMap.put("wma", "audio/x-ms-wma");
            MimeTypes.extensionToMimeTypeMap.put("wmd", "application/x-ms-wmd");
            MimeTypes.extensionToMimeTypeMap.put("wmf", "application/x-msmetafile");
            MimeTypes.extensionToMimeTypeMap.put("wmv", "audio/x-ms-wmv");
            MimeTypes.extensionToMimeTypeMap.put("wmx", "video/x-ms-wmx");
            MimeTypes.extensionToMimeTypeMap.put("wmz", "application/x-ms-wmz");
            MimeTypes.extensionToMimeTypeMap.put("wps", "application/vnd.ms-works");
            MimeTypes.extensionToMimeTypeMap.put("wri", "application/x-mswrite");
            MimeTypes.extensionToMimeTypeMap.put("wrl", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("wrz", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("wvx", "video/x-ms-wvx");
            MimeTypes.extensionToMimeTypeMap.put("xaf", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("xbm", "image/x-xbitmap");
            MimeTypes.extensionToMimeTypeMap.put("xla", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("xlc", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xlm", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xls", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeTypes.extensionToMimeTypeMap.put("xlt", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
            MimeTypes.extensionToMimeTypeMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
            MimeTypes.extensionToMimeTypeMap.put("xlw", "application/vnd.ms-excel");
            MimeTypes.extensionToMimeTypeMap.put("xml", "application/xml");
            MimeTypes.extensionToMimeTypeMap.put("xof", "x-world/x-vrml");
            MimeTypes.extensionToMimeTypeMap.put("xpm", "image/x-xpixmap");
            MimeTypes.extensionToMimeTypeMap.put("xwd", "image/x-xwindowdump");
            MimeTypes.extensionToMimeTypeMap.put("z", "application/x-compress");
            MimeTypes.extensionToMimeTypeMap.put("zip", "application/zip");
        }
    }
}
