//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render.gl;

public enum GLClientState implements GLenum
{
    COLOR("GL_COLOR_ARRAY", 32886), 
    EDGE("GL_EDGE_FLAG_ARRAY", 32889), 
    FOG("GL_FOG_COORD_ARRAY", 33879), 
    INDEX("GL_INDEX_ARRAY", 32887), 
    NORMAL("GL_NORMAL_ARRAY", 32885), 
    SECONDARY_COLOR("GL_SECONDARY_COLOR_ARRAY", 33886), 
    TEXTURE("GL_TEXTURE_COORD_ARRAY", 32888), 
    VERTEX("GL_VERTEX_ARRAY", 32884);
    
    private final String name;
    private final int cap;
    
    private GLClientState(final String name, final int cap) {
        this.name = name;
        this.cap = cap;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getCap() {
        return this.cap;
    }
}
