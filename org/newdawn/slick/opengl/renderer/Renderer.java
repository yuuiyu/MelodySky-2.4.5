//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl.renderer;

public class Renderer
{
    public static final int IMMEDIATE_RENDERER = 1;
    public static final int VERTEX_ARRAY_RENDERER = 2;
    public static final int DEFAULT_LINE_STRIP_RENDERER = 3;
    public static final int QUAD_BASED_LINE_STRIP_RENDERER = 4;
    private static SGL renderer;
    private static LineStripRenderer lineStripRenderer;
    
    public static void setRenderer(final int type) {
        switch (type) {
            case 1: {
                setRenderer((SGL)new ImmediateModeOGLRenderer());
            }
            case 2: {
                setRenderer((SGL)new VAOGLRenderer());
            }
            default: {
                throw new RuntimeException("Unknown renderer type: " + type);
            }
        }
    }
    
    public static void setLineStripRenderer(final int type) {
        switch (type) {
            case 3: {
                setLineStripRenderer((LineStripRenderer)new DefaultLineStripRenderer());
            }
            case 4: {
                setLineStripRenderer((LineStripRenderer)new QuadBasedLineStripRenderer());
            }
            default: {
                throw new RuntimeException("Unknown line strip renderer type: " + type);
            }
        }
    }
    
    public static void setLineStripRenderer(final LineStripRenderer renderer) {
        Renderer.lineStripRenderer = renderer;
    }
    
    public static void setRenderer(final SGL r) {
        Renderer.renderer = r;
    }
    
    public static SGL get() {
        return Renderer.renderer;
    }
    
    public static LineStripRenderer getLineStripRenderer() {
        return Renderer.lineStripRenderer;
    }
    
    static {
        Renderer.renderer = (SGL)new ImmediateModeOGLRenderer();
        Renderer.lineStripRenderer = (LineStripRenderer)new DefaultLineStripRenderer();
    }
}
