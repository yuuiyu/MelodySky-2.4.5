//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.openal;

import org.newdawn.slick.loading.*;
import org.newdawn.slick.util.*;
import java.io.*;

public class DeferredSound extends AudioImpl implements DeferredResource
{
    public static final int OGG = 1;
    public static final int WAV = 2;
    public static final int MOD = 3;
    public static final int AIF = 4;
    private int type;
    private String ref;
    private Audio target;
    private InputStream in;
    
    public DeferredSound(final String ref, final InputStream in, final int type) {
        this.ref = ref;
        this.type = type;
        if (ref.equals(in.toString())) {
            this.in = in;
        }
        LoadingList.get().add((DeferredResource)this);
    }
    
    private void checkTarget() {
        if (this.target == null) {
            throw new RuntimeException("Attempt to use deferred sound before loading");
        }
    }
    
    public void load() throws IOException {
        final boolean before = SoundStore.get().isDeferredLoading();
        SoundStore.get().setDeferredLoading(false);
        if (this.in != null) {
            switch (this.type) {
                case 1: {
                    this.target = SoundStore.get().getOgg(this.in);
                    break;
                }
                case 2: {
                    this.target = SoundStore.get().getWAV(this.in);
                    break;
                }
                case 3: {
                    this.target = SoundStore.get().getMOD(this.in);
                    break;
                }
                case 4: {
                    this.target = SoundStore.get().getAIF(this.in);
                    break;
                }
                default: {
                    Log.error("Unrecognised sound type: " + this.type);
                    break;
                }
            }
        }
        else {
            switch (this.type) {
                case 1: {
                    this.target = SoundStore.get().getOgg(this.ref);
                    break;
                }
                case 2: {
                    this.target = SoundStore.get().getWAV(this.ref);
                    break;
                }
                case 3: {
                    this.target = SoundStore.get().getMOD(this.ref);
                    break;
                }
                case 4: {
                    this.target = SoundStore.get().getAIF(this.ref);
                    break;
                }
                default: {
                    Log.error("Unrecognised sound type: " + this.type);
                    break;
                }
            }
        }
        SoundStore.get().setDeferredLoading(before);
    }
    
    public boolean isPlaying() {
        this.checkTarget();
        return this.target.isPlaying();
    }
    
    public int playAsMusic(final float pitch, final float gain, final boolean loop) {
        this.checkTarget();
        return this.target.playAsMusic(pitch, gain, loop);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop) {
        this.checkTarget();
        return this.target.playAsSoundEffect(pitch, gain, loop);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop, final float x, final float y, final float z) {
        this.checkTarget();
        return this.target.playAsSoundEffect(pitch, gain, loop, x, y, z);
    }
    
    public void stop() {
        this.checkTarget();
        this.target.stop();
    }
    
    public String getDescription() {
        return this.ref;
    }
}
