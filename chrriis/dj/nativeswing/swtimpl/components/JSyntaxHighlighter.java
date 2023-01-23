//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;

public class JSyntaxHighlighter extends NSPanelComponent
{
    private static final String PACKAGE_PREFIX = "/dp.SyntaxHighlighter/";
    private JWebBrowser webBrowser;
    private static final String LS;
    
    public JSyntaxHighlighter(final NSOption... options) {
        if (this.getClass().getResource("/dp.SyntaxHighlighter/Styles/SyntaxHighlighter.css") == null) {
            throw new IllegalStateException("The SyntaxHighlighter distribution is missing from the classpath!");
        }
        this.webBrowser = new JWebBrowser(options);
        this.initialize(this.webBrowser.getNativeComponent());
        this.webBrowser.setDefaultPopupMenuRegistered(false);
        this.webBrowser.setBarsVisible(false);
        this.add(this.webBrowser, "Center");
    }
    
    public JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
    
    public void setContent(final String content, final ContentLanguage language) {
        this.setContent(content, language, null);
    }
    
    public void setContent(final String content, final ContentLanguage language, SyntaxHighlighterOptions options) {
        if (language == null) {
            throw new IllegalArgumentException("The language cannot be null!");
        }
        if (options == null) {
            options = new SyntaxHighlighterOptions();
        }
        final String hContent = "<html>" + JSyntaxHighlighter.LS + "  <head>" + JSyntaxHighlighter.LS + "    <link type=\"text/css\" rel=\"stylesheet\" href=\"" + WebServer.getDefaultWebServer().getClassPathResourceURL((String)null, "/dp.SyntaxHighlighter/Styles/SyntaxHighlighter.css") + "\"></link>" + JSyntaxHighlighter.LS + "    <script language=\"javascript\" src=\"" + WebServer.getDefaultWebServer().getClassPathResourceURL((String)null, "/dp.SyntaxHighlighter/Scripts/shCore.js") + "\"></script>" + JSyntaxHighlighter.LS + "    <script language=\"javascript\" src=\"" + WebServer.getDefaultWebServer().getClassPathResourceURL((String)null, "/dp.SyntaxHighlighter/Scripts/shBrush" + language.getFileName() + ".js") + "\"></script>" + JSyntaxHighlighter.LS + "    <script language=\"JavaScript\" type=\"text/javascript\">" + JSyntaxHighlighter.LS + "      <!--" + JSyntaxHighlighter.LS + "      function init() {" + JSyntaxHighlighter.LS + "        dp.SyntaxHighlighter.ClipboardSwf = '" + WebServer.getDefaultWebServer().getClassPathResourceURL((String)null, "/dp.SyntaxHighlighter/Scripts/clipboard.swf") + "';" + JSyntaxHighlighter.LS + "        dp.SyntaxHighlighter.HighlightAll('code');" + JSyntaxHighlighter.LS + "      }" + JSyntaxHighlighter.LS + "      //-->" + JSyntaxHighlighter.LS + "    </script>" + JSyntaxHighlighter.LS + "    <style type=\"text/css\">" + JSyntaxHighlighter.LS + "      html, body { width: 100%; height: 100%; min-height: 100%; margin: 0; padding: 0; white-space: nowrap; background-color: #FFFFFF; }" + JSyntaxHighlighter.LS + "      div.dp-highlighter { overflow: visible; }" + JSyntaxHighlighter.LS + "      div.wrapper { width: 100%; height: 100%; min-height: 100%; padding: 0; margin: -18px 0; white-space: nowrap; }" + JSyntaxHighlighter.LS + "    </style>" + JSyntaxHighlighter.LS + "  </head>" + JSyntaxHighlighter.LS + "  <body id=\"body\">    <div class=\"wrapper\">      <pre name=\"code\" class=\"" + language.getLanguage() + "\">" + escapeXML(content) + "</pre>" + JSyntaxHighlighter.LS + "    </div>  </body>" + JSyntaxHighlighter.LS + "<script language=\"JavaScript\" type=\"text/javascript\">" + JSyntaxHighlighter.LS + "  <!--" + JSyntaxHighlighter.LS + "    init();" + JSyntaxHighlighter.LS + "  //-->" + JSyntaxHighlighter.LS + "</script>" + JSyntaxHighlighter.LS + "</html>";
        this.webBrowser.setHTMLContent(hContent);
    }
    
    private static String escapeXML(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        final StringBuilder sb = new StringBuilder((int)(s.length() * 1.1));
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            switch (c) {
                case '<': {
                    sb.append("&lt;");
                    break;
                }
                case '>': {
                    sb.append("&gt;");
                    break;
                }
                case '&': {
                    sb.append("&amp;");
                    break;
                }
                case '\"': {
                    sb.append("&quot;");
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    static {
        LS = Utils.LINE_SEPARATOR;
    }
    
    public enum ContentLanguage
    {
        Cpp("c++", "Cpp"), 
        CSharp("c#", "CSharp"), 
        CSS("css", "Css"), 
        Delphi("delphi", "Delphi"), 
        Java("java", "Java"), 
        Javascript("js", "JScript"), 
        PHP("php", "Php"), 
        Python("python", "Python"), 
        Ruby("ruby", "Ruby"), 
        SQL("sql", "Sql"), 
        VB("vb", "Vb"), 
        XML("xml", "Xml"), 
        HTML("html", "Xml");
        
        private String language;
        private String fileName;
        
        private ContentLanguage(final String language, final String fileName) {
            this.language = language;
            this.fileName = fileName;
        }
        
        String getLanguage() {
            return this.language;
        }
        
        String getFileName() {
            return this.fileName;
        }
    }
}
