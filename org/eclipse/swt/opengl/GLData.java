//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.opengl;

public class GLData
{
    public boolean doubleBuffer;
    public boolean stereo;
    public int redSize;
    public int greenSize;
    public int blueSize;
    public int alphaSize;
    public int depthSize;
    public int stencilSize;
    public int accumRedSize;
    public int accumGreenSize;
    public int accumBlueSize;
    public int accumAlphaSize;
    public int sampleBuffers;
    public int samples;
    public GLCanvas shareContext;
    
    @Override
    public String toString() {
        return (this.doubleBuffer ? "doubleBuffer," : "") + (this.stereo ? "stereo," : "") + "r:" + this.redSize + " g:" + this.greenSize + " b:" + this.blueSize + " a:" + this.alphaSize + ",depth:" + this.depthSize + ",stencil:" + this.stencilSize + ",accum r:" + this.accumRedSize + "g:" + this.accumGreenSize + "b:" + this.accumBlueSize + "a:" + this.accumAlphaSize + ",sampleBuffers:" + this.sampleBuffers + ",samples:" + this.samples;
    }
}
