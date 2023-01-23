//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.svg;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import org.xml.sax.*;
import java.io.*;
import javax.xml.parsers.*;
import org.newdawn.slick.geom.*;
import org.w3c.dom.*;
import org.newdawn.slick.svg.inkscape.*;

public class InkscapeLoader implements Loader
{
    public static int RADIAL_TRIANGULATION_LEVEL;
    private static ArrayList processors;
    private Diagram diagram;
    
    public static void addElementProcessor(final ElementProcessor proc) {
        InkscapeLoader.processors.add(proc);
    }
    
    public static Diagram load(final String ref, final boolean offset) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), offset);
    }
    
    public static Diagram load(final String ref) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), false);
    }
    
    public static Diagram load(final InputStream in, final boolean offset) throws SlickException {
        return new InkscapeLoader().loadDiagram(in, offset);
    }
    
    private InkscapeLoader() {
    }
    
    private Diagram loadDiagram(final InputStream in) throws SlickException {
        return this.loadDiagram(in, false);
    }
    
    private Diagram loadDiagram(final InputStream in, final boolean offset) throws SlickException {
        class l implements EntityResolver
        {
            final /* synthetic */ InkscapeLoader this$0;
            
            l(final InkscapeLoader this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
                return new InputSource(new ByteArrayInputStream(new byte[0]));
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_3        /* factory */
        //     4: aload_3         /* factory */
        //     5: iconst_0       
        //     6: invokevirtual   javax/xml/parsers/DocumentBuilderFactory.setValidating:(Z)V
        //     9: aload_3         /* factory */
        //    10: iconst_1       
        //    11: invokevirtual   javax/xml/parsers/DocumentBuilderFactory.setNamespaceAware:(Z)V
        //    14: aload_3         /* factory */
        //    15: invokevirtual   javax/xml/parsers/DocumentBuilderFactory.newDocumentBuilder:()Ljavax/xml/parsers/DocumentBuilder;
        //    18: astore          builder
        //    20: aload           builder
        //    22: new             Lorg/newdawn/slick/svg/l;
        //    25: dup            
        //    26: aload_0         /* this */
        //    27: invokespecial   org/newdawn/slick/svg/l.<init>:(Lorg/newdawn/slick/svg/InkscapeLoader;)V
        //    30: invokevirtual   javax/xml/parsers/DocumentBuilder.setEntityResolver:(Lorg/xml/sax/EntityResolver;)V
        //    33: aload           builder
        //    35: aload_1         /* in */
        //    36: invokevirtual   javax/xml/parsers/DocumentBuilder.parse:(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
        //    39: astore          doc
        //    41: aload           doc
        //    43: invokeinterface org/w3c/dom/Document.getDocumentElement:()Lorg/w3c/dom/Element;
        //    48: astore          root
        //    50: aload           root
        //    52: ldc             "width"
        //    54: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //    59: astore          widthString
        //    61: aload           widthString
        //    63: aload           widthString
        //    65: invokevirtual   java/lang/String.length:()I
        //    68: iconst_1       
        //    69: isub           
        //    70: invokevirtual   java/lang/String.charAt:(I)C
        //    73: invokestatic    java/lang/Character.isLetter:(C)Z
        //    76: ifeq            97
        //    79: aload           widthString
        //    81: iconst_0       
        //    82: aload           widthString
        //    84: invokevirtual   java/lang/String.length:()I
        //    87: iconst_1       
        //    88: isub           
        //    89: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    92: astore          widthString
        //    94: goto            61
        //    97: aload           root
        //    99: ldc             "height"
        //   101: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //   106: astore          heightString
        //   108: aload           heightString
        //   110: aload           heightString
        //   112: invokevirtual   java/lang/String.length:()I
        //   115: iconst_1       
        //   116: isub           
        //   117: invokevirtual   java/lang/String.charAt:(I)C
        //   120: invokestatic    java/lang/Character.isLetter:(C)Z
        //   123: ifeq            144
        //   126: aload           heightString
        //   128: iconst_0       
        //   129: aload           heightString
        //   131: invokevirtual   java/lang/String.length:()I
        //   134: iconst_1       
        //   135: isub           
        //   136: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   139: astore          heightString
        //   141: goto            108
        //   144: aload           widthString
        //   146: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   149: fstore          docWidth
        //   151: aload           heightString
        //   153: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   156: fstore          docHeight
        //   158: aload_0         /* this */
        //   159: new             Lorg/newdawn/slick/svg/Diagram;
        //   162: dup            
        //   163: fload           docWidth
        //   165: fload           docHeight
        //   167: invokespecial   org/newdawn/slick/svg/Diagram.<init>:(FF)V
        //   170: putfield        org/newdawn/slick/svg/InkscapeLoader.diagram:Lorg/newdawn/slick/svg/Diagram;
        //   173: iload_2         /* offset */
        //   174: ifne            180
        //   177: fconst_0       
        //   178: fstore          docHeight
        //   180: aload_0         /* this */
        //   181: aload           root
        //   183: fconst_0       
        //   184: fload           docHeight
        //   186: fneg           
        //   187: invokestatic    org/newdawn/slick/geom/Transform.createTranslateTransform:(FF)Lorg/newdawn/slick/geom/Transform;
        //   190: invokevirtual   org/newdawn/slick/svg/InkscapeLoader.loadChildren:(Lorg/w3c/dom/Element;Lorg/newdawn/slick/geom/Transform;)V
        //   193: aload_0         /* this */
        //   194: getfield        org/newdawn/slick/svg/InkscapeLoader.diagram:Lorg/newdawn/slick/svg/Diagram;
        //   197: areturn        
        //   198: astore_3        /* factory */
        //   199: new             Lorg/newdawn/slick/SlickException;
        //   202: dup            
        //   203: ldc             "Failed to load inkscape document"
        //   205: aload_3         /* e */
        //   206: invokespecial   org/newdawn/slick/SlickException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   209: athrow         
        //    Exceptions:
        //  throws org.newdawn.slick.SlickException
        //    StackMapTable: 00 06 FF 00 3D 00 08 07 00 02 07 00 6A 01 07 00 3E 07 00 52 07 00 5C 07 00 64 07 00 6C 00 00 23 FC 00 0A 07 00 6C 23 FD 00 23 02 02 FF 00 11 00 03 07 00 02 07 00 6A 01 00 01 07 00 3C
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      197    198    210    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void loadChildren(final Element element, final Transform t) throws ParsingException {
        final NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i) instanceof Element) {
                this.loadElement((Element)list.item(i), t);
            }
        }
    }
    
    private void loadElement(final Element element, final Transform t) throws ParsingException {
        for (int i = 0; i < InkscapeLoader.processors.size(); ++i) {
            final ElementProcessor processor = InkscapeLoader.processors.get(i);
            if (processor.handles(element)) {
                processor.process((Loader)this, element, this.diagram, t);
            }
        }
    }
    
    static {
        InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 1;
        InkscapeLoader.processors = new ArrayList();
        addElementProcessor((ElementProcessor)new RectProcessor());
        addElementProcessor((ElementProcessor)new EllipseProcessor());
        addElementProcessor((ElementProcessor)new PolygonProcessor());
        addElementProcessor((ElementProcessor)new PathProcessor());
        addElementProcessor((ElementProcessor)new LineProcessor());
        addElementProcessor((ElementProcessor)new GroupProcessor());
        addElementProcessor((ElementProcessor)new DefsProcessor());
        addElementProcessor((ElementProcessor)new UseProcessor());
    }
}
