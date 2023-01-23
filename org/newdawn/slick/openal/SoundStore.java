//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.openal;

import java.util.*;
import java.nio.*;
import java.security.*;
import org.newdawn.slick.util.*;
import java.io.*;
import java.net.*;
import org.lwjgl.*;
import org.lwjgl.openal.*;

public class SoundStore
{
    private static SoundStore store;
    private boolean sounds;
    private boolean music;
    private boolean soundWorks;
    private int sourceCount;
    private HashMap loaded;
    private int currentMusic;
    private IntBuffer sources;
    private int nextSource;
    private boolean inited;
    private MODSound mod;
    private OpenALStreamPlayer stream;
    private float musicVolume;
    private float soundVolume;
    private float lastCurrentMusicVolume;
    private boolean paused;
    private boolean deferred;
    private FloatBuffer sourceVel;
    private FloatBuffer sourcePos;
    private int maxSources;
    
    private SoundStore() {
        this.loaded = new HashMap();
        this.currentMusic = -1;
        this.inited = false;
        this.musicVolume = 1.0f;
        this.soundVolume = 1.0f;
        this.lastCurrentMusicVolume = 1.0f;
        this.sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        this.sourcePos = BufferUtils.createFloatBuffer(3);
        this.maxSources = 64;
    }
    
    public void clear() {
        SoundStore.store = new SoundStore();
    }
    
    public void disable() {
        this.inited = true;
    }
    
    public void setDeferredLoading(final boolean deferred) {
        this.deferred = deferred;
    }
    
    public boolean isDeferredLoading() {
        return this.deferred;
    }
    
    public void setMusicOn(final boolean music) {
        if (this.soundWorks) {
            this.music = music;
            if (music) {
                this.restartLoop();
                this.setMusicVolume(this.musicVolume);
            }
            else {
                this.pauseLoop();
            }
        }
    }
    
    public boolean isMusicOn() {
        return this.music;
    }
    
    public void setMusicVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        }
        if (volume > 1.0f) {
            volume = 1.0f;
        }
        this.musicVolume = volume;
        if (this.soundWorks) {
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
        }
    }
    
    public float getCurrentMusicVolume() {
        return this.lastCurrentMusicVolume;
    }
    
    public void setCurrentMusicVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        }
        if (volume > 1.0f) {
            volume = 1.0f;
        }
        if (this.soundWorks) {
            this.lastCurrentMusicVolume = volume;
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
        }
    }
    
    public void setSoundVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        }
        this.soundVolume = volume;
    }
    
    public boolean soundWorks() {
        return this.soundWorks;
    }
    
    public boolean musicOn() {
        return this.music;
    }
    
    public float getSoundVolume() {
        return this.soundVolume;
    }
    
    public float getMusicVolume() {
        return this.musicVolume;
    }
    
    public int getSource(final int index) {
        if (!this.soundWorks) {
            return -1;
        }
        if (index < 0) {
            return -1;
        }
        return this.sources.get(index);
    }
    
    public void setSoundsOn(final boolean sounds) {
        if (this.soundWorks) {
            this.sounds = sounds;
        }
    }
    
    public boolean soundsOn() {
        return this.sounds;
    }
    
    public void setMaxSources(final int max) {
        this.maxSources = max;
    }
    
    public void init() {
        class l implements PrivilegedAction
        {
            final /* synthetic */ SoundStore this$0;
            
            l(final SoundStore this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public Object run() {
                try {
                    AL.create();
                    this.this$0.soundWorks = true;
                    this.this$0.sounds = true;
                    this.this$0.music = true;
                    Log.info("- Sound works");
                }
                catch (Exception e) {
                    Log.error("Sound initialisation failure.");
                    Log.error(e);
                    this.this$0.soundWorks = false;
                    this.this$0.sounds = false;
                    this.this$0.music = false;
                }
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/newdawn/slick/openal/SoundStore.inited:Z
        //     4: ifeq            8
        //     7: return         
        //     8: ldc             "Initialising sounds.."
        //    10: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //    13: aload_0         /* this */
        //    14: iconst_1       
        //    15: putfield        org/newdawn/slick/openal/SoundStore.inited:Z
        //    18: new             Lorg/newdawn/slick/openal/l;
        //    21: dup            
        //    22: aload_0         /* this */
        //    23: invokespecial   org/newdawn/slick/openal/l.<init>:(Lorg/newdawn/slick/openal/SoundStore;)V
        //    26: invokestatic    java/security/AccessController.doPrivileged:(Ljava/security/PrivilegedAction;)Ljava/lang/Object;
        //    29: pop            
        //    30: aload_0         /* this */
        //    31: getfield        org/newdawn/slick/openal/SoundStore.soundWorks:Z
        //    34: ifeq            307
        //    37: aload_0         /* this */
        //    38: iconst_0       
        //    39: putfield        org/newdawn/slick/openal/SoundStore.sourceCount:I
        //    42: aload_0         /* this */
        //    43: aload_0         /* this */
        //    44: getfield        org/newdawn/slick/openal/SoundStore.maxSources:I
        //    47: invokestatic    org/lwjgl/BufferUtils.createIntBuffer:(I)Ljava/nio/IntBuffer;
        //    50: putfield        org/newdawn/slick/openal/SoundStore.sources:Ljava/nio/IntBuffer;
        //    53: invokestatic    org/lwjgl/openal/AL10.alGetError:()I
        //    56: ifne            123
        //    59: iconst_1       
        //    60: invokestatic    org/lwjgl/BufferUtils.createIntBuffer:(I)Ljava/nio/IntBuffer;
        //    63: astore_1        /* temp */
        //    64: aload_1         /* temp */
        //    65: invokestatic    org/lwjgl/openal/AL10.alGenSources:(Ljava/nio/IntBuffer;)V
        //    68: invokestatic    org/lwjgl/openal/AL10.alGetError:()I
        //    71: ifne            113
        //    74: aload_0         /* this */
        //    75: dup            
        //    76: getfield        org/newdawn/slick/openal/SoundStore.sourceCount:I
        //    79: iconst_1       
        //    80: iadd           
        //    81: putfield        org/newdawn/slick/openal/SoundStore.sourceCount:I
        //    84: aload_0         /* this */
        //    85: getfield        org/newdawn/slick/openal/SoundStore.sources:Ljava/nio/IntBuffer;
        //    88: aload_1         /* temp */
        //    89: iconst_0       
        //    90: invokevirtual   java/nio/IntBuffer.get:(I)I
        //    93: invokevirtual   java/nio/IntBuffer.put:(I)Ljava/nio/IntBuffer;
        //    96: pop            
        //    97: aload_0         /* this */
        //    98: getfield        org/newdawn/slick/openal/SoundStore.sourceCount:I
        //   101: aload_0         /* this */
        //   102: getfield        org/newdawn/slick/openal/SoundStore.maxSources:I
        //   105: iconst_1       
        //   106: isub           
        //   107: if_icmple       113
        //   110: goto            123
        //   113: goto            120
        //   116: astore_2        /* e */
        //   117: goto            123
        //   120: goto            53
        //   123: new             Ljava/lang/StringBuilder;
        //   126: dup            
        //   127: invokespecial   java/lang/StringBuilder.<init>:()V
        //   130: ldc             "- "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload_0         /* this */
        //   136: getfield        org/newdawn/slick/openal/SoundStore.sourceCount:I
        //   139: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   142: ldc             " OpenAL source available"
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //   153: invokestatic    org/lwjgl/openal/AL10.alGetError:()I
        //   156: ifeq            182
        //   159: aload_0         /* this */
        //   160: iconst_0       
        //   161: putfield        org/newdawn/slick/openal/SoundStore.sounds:Z
        //   164: aload_0         /* this */
        //   165: iconst_0       
        //   166: putfield        org/newdawn/slick/openal/SoundStore.music:Z
        //   169: aload_0         /* this */
        //   170: iconst_0       
        //   171: putfield        org/newdawn/slick/openal/SoundStore.soundWorks:Z
        //   174: ldc             "- AL init failed"
        //   176: invokestatic    org/newdawn/slick/util/Log.error:(Ljava/lang/String;)V
        //   179: goto            307
        //   182: bipush          6
        //   184: invokestatic    org/lwjgl/BufferUtils.createFloatBuffer:(I)Ljava/nio/FloatBuffer;
        //   187: bipush          6
        //   189: newarray        F
        //   191: dup            
        //   192: iconst_0       
        //   193: fconst_0       
        //   194: fastore        
        //   195: dup            
        //   196: iconst_1       
        //   197: fconst_0       
        //   198: fastore        
        //   199: dup            
        //   200: iconst_2       
        //   201: ldc             -1.0
        //   203: fastore        
        //   204: dup            
        //   205: iconst_3       
        //   206: fconst_0       
        //   207: fastore        
        //   208: dup            
        //   209: iconst_4       
        //   210: fconst_1       
        //   211: fastore        
        //   212: dup            
        //   213: iconst_5       
        //   214: fconst_0       
        //   215: fastore        
        //   216: invokevirtual   java/nio/FloatBuffer.put:([F)Ljava/nio/FloatBuffer;
        //   219: astore_1        /* listenerOri */
        //   220: iconst_3       
        //   221: invokestatic    org/lwjgl/BufferUtils.createFloatBuffer:(I)Ljava/nio/FloatBuffer;
        //   224: iconst_3       
        //   225: newarray        F
        //   227: dup            
        //   228: iconst_0       
        //   229: fconst_0       
        //   230: fastore        
        //   231: dup            
        //   232: iconst_1       
        //   233: fconst_0       
        //   234: fastore        
        //   235: dup            
        //   236: iconst_2       
        //   237: fconst_0       
        //   238: fastore        
        //   239: invokevirtual   java/nio/FloatBuffer.put:([F)Ljava/nio/FloatBuffer;
        //   242: astore_2        /* listenerVel */
        //   243: iconst_3       
        //   244: invokestatic    org/lwjgl/BufferUtils.createFloatBuffer:(I)Ljava/nio/FloatBuffer;
        //   247: iconst_3       
        //   248: newarray        F
        //   250: dup            
        //   251: iconst_0       
        //   252: fconst_0       
        //   253: fastore        
        //   254: dup            
        //   255: iconst_1       
        //   256: fconst_0       
        //   257: fastore        
        //   258: dup            
        //   259: iconst_2       
        //   260: fconst_0       
        //   261: fastore        
        //   262: invokevirtual   java/nio/FloatBuffer.put:([F)Ljava/nio/FloatBuffer;
        //   265: astore_3        /* listenerPos */
        //   266: aload_3         /* listenerPos */
        //   267: invokevirtual   java/nio/FloatBuffer.flip:()Ljava/nio/Buffer;
        //   270: pop            
        //   271: aload_2         /* listenerVel */
        //   272: invokevirtual   java/nio/FloatBuffer.flip:()Ljava/nio/Buffer;
        //   275: pop            
        //   276: aload_1         /* listenerOri */
        //   277: invokevirtual   java/nio/FloatBuffer.flip:()Ljava/nio/Buffer;
        //   280: pop            
        //   281: sipush          4100
        //   284: aload_3         /* listenerPos */
        //   285: invokestatic    org/lwjgl/openal/AL10.alListener:(ILjava/nio/FloatBuffer;)V
        //   288: sipush          4102
        //   291: aload_2         /* listenerVel */
        //   292: invokestatic    org/lwjgl/openal/AL10.alListener:(ILjava/nio/FloatBuffer;)V
        //   295: sipush          4111
        //   298: aload_1         /* listenerOri */
        //   299: invokestatic    org/lwjgl/openal/AL10.alListener:(ILjava/nio/FloatBuffer;)V
        //   302: ldc             "- Sounds source generated"
        //   304: invokestatic    org/newdawn/slick/util/Log.info:(Ljava/lang/String;)V
        //   307: return         
        //    StackMapTable: 00 08 08 2C FC 00 3B 07 00 69 42 07 00 87 03 FA 00 02 3A FB 00 7C
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  64     110    116    120    Lorg/lwjgl/openal/OpenALException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void stopSource(final int index) {
        AL10.alSourceStop(this.sources.get(index));
    }
    
    int playAsSound(final int buffer, final float pitch, final float gain, final boolean loop) {
        return this.playAsSoundAt(buffer, pitch, gain, loop, 0.0f, 0.0f, 0.0f);
    }
    
    int playAsSoundAt(final int buffer, final float pitch, float gain, final boolean loop, final float x, final float y, final float z) {
        gain *= this.soundVolume;
        if (gain == 0.0f) {
            gain = 0.001f;
        }
        if (!this.soundWorks || !this.sounds) {
            return -1;
        }
        final int nextSource = this.findFreeSource();
        if (nextSource == -1) {
            return -1;
        }
        AL10.alSourceStop(this.sources.get(nextSource));
        AL10.alSourcei(this.sources.get(nextSource), 4105, buffer);
        AL10.alSourcef(this.sources.get(nextSource), 4099, pitch);
        AL10.alSourcef(this.sources.get(nextSource), 4106, gain);
        AL10.alSourcei(this.sources.get(nextSource), 4103, (int)(loop ? 1 : 0));
        this.sourcePos.clear();
        this.sourceVel.clear();
        this.sourceVel.put(new float[] { 0.0f, 0.0f, 0.0f });
        this.sourcePos.put(new float[] { x, y, z });
        this.sourcePos.flip();
        this.sourceVel.flip();
        AL10.alSource(this.sources.get(nextSource), 4100, this.sourcePos);
        AL10.alSource(this.sources.get(nextSource), 4102, this.sourceVel);
        AL10.alSourcePlay(this.sources.get(nextSource));
        return nextSource;
    }
    
    boolean isPlaying(final int index) {
        final int state = AL10.alGetSourcei(this.sources.get(index), 4112);
        return state == 4114;
    }
    
    private int findFreeSource() {
        for (int i = 1; i < this.sourceCount - 1; ++i) {
            final int state = AL10.alGetSourcei(this.sources.get(i), 4112);
            if (state != 4114 && state != 4115) {
                return i;
            }
        }
        return -1;
    }
    
    void playAsMusic(final int buffer, final float pitch, final float gain, final boolean loop) {
        this.paused = false;
        this.setMOD(null);
        if (this.soundWorks) {
            if (this.currentMusic != -1) {
                AL10.alSourceStop(this.sources.get(0));
            }
            this.getMusicSource();
            AL10.alSourcei(this.sources.get(0), 4105, buffer);
            AL10.alSourcef(this.sources.get(0), 4099, pitch);
            AL10.alSourcei(this.sources.get(0), 4103, (int)(loop ? 1 : 0));
            this.currentMusic = this.sources.get(0);
            if (!this.music) {
                this.pauseLoop();
            }
            else {
                AL10.alSourcePlay(this.sources.get(0));
            }
        }
    }
    
    private int getMusicSource() {
        return this.sources.get(0);
    }
    
    public void setMusicPitch(final float pitch) {
        if (this.soundWorks) {
            AL10.alSourcef(this.sources.get(0), 4099, pitch);
        }
    }
    
    public void pauseLoop() {
        if (this.soundWorks && this.currentMusic != -1) {
            this.paused = true;
            AL10.alSourcePause(this.currentMusic);
        }
    }
    
    public void restartLoop() {
        if (this.music && this.soundWorks && this.currentMusic != -1) {
            this.paused = false;
            AL10.alSourcePlay(this.currentMusic);
        }
    }
    
    boolean isPlaying(final OpenALStreamPlayer player) {
        return this.stream == player;
    }
    
    public Audio getMOD(final String ref) throws IOException {
        return this.getMOD(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getMOD(final InputStream in) throws IOException {
        return this.getMOD(in.toString(), in);
    }
    
    public Audio getMOD(final String ref, final InputStream in) throws IOException {
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return (Audio)new DeferredSound(ref, in, 3);
        }
        return (Audio)new MODSound(this, in);
    }
    
    public Audio getAIF(final String ref) throws IOException {
        return this.getAIF(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getAIF(final InputStream in) throws IOException {
        return this.getAIF(in.toString(), in);
    }
    
    public Audio getAIF(final String ref, InputStream in) throws IOException {
        in = new BufferedInputStream(in);
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return (Audio)new DeferredSound(ref, in, 4);
        }
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = this.loaded.get(ref);
        }
        else {
            try {
                final IntBuffer buf = BufferUtils.createIntBuffer(1);
                final AiffData data = AiffData.create(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            }
            catch (Exception e) {
                Log.error(e);
                final IOException x = new IOException("Failed to load: " + ref);
                x.initCause(e);
                throw x;
            }
        }
        if (buffer == -1) {
            throw new IOException("Unable to load: " + ref);
        }
        return (Audio)new AudioImpl(this, buffer);
    }
    
    public Audio getWAV(final String ref) throws IOException {
        return this.getWAV(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getWAV(final InputStream in) throws IOException {
        return this.getWAV(in.toString(), in);
    }
    
    public Audio getWAV(final String ref, final InputStream in) throws IOException {
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return (Audio)new DeferredSound(ref, in, 2);
        }
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = this.loaded.get(ref);
        }
        else {
            try {
                final IntBuffer buf = BufferUtils.createIntBuffer(1);
                final WaveData data = WaveData.create(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            }
            catch (Exception e) {
                Log.error(e);
                final IOException x = new IOException("Failed to load: " + ref);
                x.initCause(e);
                throw x;
            }
        }
        if (buffer == -1) {
            throw new IOException("Unable to load: " + ref);
        }
        return (Audio)new AudioImpl(this, buffer);
    }
    
    public Audio getOggStream(final String ref) throws IOException {
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        this.setMOD(null);
        this.setStream(null);
        if (this.currentMusic != -1) {
            AL10.alSourceStop(this.sources.get(0));
        }
        this.getMusicSource();
        this.currentMusic = this.sources.get(0);
        return (Audio)new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
    }
    
    public Audio getOggStream(final URL ref) throws IOException {
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        this.setMOD(null);
        this.setStream(null);
        if (this.currentMusic != -1) {
            AL10.alSourceStop(this.sources.get(0));
        }
        this.getMusicSource();
        this.currentMusic = this.sources.get(0);
        return (Audio)new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
    }
    
    public Audio getOgg(final String ref) throws IOException {
        return this.getOgg(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getOgg(final InputStream in) throws IOException {
        return this.getOgg(in.toString(), in);
    }
    
    public Audio getOgg(final String ref, final InputStream in) throws IOException {
        if (!this.soundWorks) {
            return (Audio)new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return (Audio)new DeferredSound(ref, in, 1);
        }
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = this.loaded.get(ref);
        }
        else {
            try {
                final IntBuffer buf = BufferUtils.createIntBuffer(1);
                final OggDecoder decoder = new OggDecoder();
                final OggData ogg = decoder.getData(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), (ogg.channels > 1) ? 4355 : 4353, ogg.data, ogg.rate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            }
            catch (Exception e) {
                Log.error(e);
                Sys.alert("Error", "Failed to load: " + ref + " - " + e.getMessage());
                throw new IOException("Unable to load: " + ref);
            }
        }
        if (buffer == -1) {
            throw new IOException("Unable to load: " + ref);
        }
        return (Audio)new AudioImpl(this, buffer);
    }
    
    void setMOD(final MODSound sound) {
        if (!this.soundWorks) {
            return;
        }
        this.currentMusic = this.sources.get(0);
        this.stopSource(0);
        if ((this.mod = sound) != null) {
            this.stream = null;
        }
        this.paused = false;
    }
    
    void setStream(final OpenALStreamPlayer stream) {
        if (!this.soundWorks) {
            return;
        }
        this.currentMusic = this.sources.get(0);
        if ((this.stream = stream) != null) {
            this.mod = null;
        }
        this.paused = false;
    }
    
    public void poll(final int delta) {
        if (!this.soundWorks) {
            return;
        }
        if (this.paused) {
            return;
        }
        if (this.music) {
            if (this.mod != null) {
                try {
                    this.mod.poll();
                }
                catch (OpenALException e) {
                    Log.error("Error with OpenGL MOD Player on this this platform");
                    Log.error((Throwable)e);
                    this.mod = null;
                }
            }
            if (this.stream != null) {
                try {
                    this.stream.update();
                }
                catch (OpenALException e) {
                    Log.error("Error with OpenGL Streaming Player on this this platform");
                    Log.error((Throwable)e);
                    this.mod = null;
                }
            }
        }
    }
    
    public boolean isMusicPlaying() {
        if (!this.soundWorks) {
            return false;
        }
        final int state = AL10.alGetSourcei(this.sources.get(0), 4112);
        return state == 4114 || state == 4115;
    }
    
    public static SoundStore get() {
        return SoundStore.store;
    }
    
    public void stopSoundEffect(final int id) {
        AL10.alSourceStop(id);
    }
    
    public int getSourceCount() {
        return this.sourceCount;
    }
    
    static {
        SoundStore.store = new SoundStore();
    }
}
