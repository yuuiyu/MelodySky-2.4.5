//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.particles;

import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.opengl.*;
import java.security.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import java.util.*;
import java.io.*;

public class ParticleSystem
{
    protected SGL GL;
    public static final int BLEND_ADDITIVE = 1;
    public static final int BLEND_COMBINE = 2;
    private static final int DEFAULT_PARTICLES = 100;
    private ArrayList removeMe;
    protected HashMap particlesByEmitter;
    protected int maxParticlesPerEmitter;
    protected ArrayList emitters;
    protected Particle dummy;
    private int blendingMode;
    private int pCount;
    private boolean usePoints;
    private float x;
    private float y;
    private boolean removeCompletedEmitters;
    private Image sprite;
    private boolean visible;
    private String defaultImageName;
    private Color mask;
    
    public static void setRelativePath(final String path) {
        ConfigurableEmitter.setRelativePath(path);
    }
    
    public ParticleSystem(final Image defaultSprite) {
        this(defaultSprite, 100);
    }
    
    public ParticleSystem(final String defaultSpriteRef) {
        this(defaultSpriteRef, 100);
    }
    
    public void reset() {
        for (final ParticlePool pool : this.particlesByEmitter.values()) {
            pool.reset(this);
        }
        for (int i = 0; i < this.emitters.size(); ++i) {
            final ParticleEmitter emitter = this.emitters.get(i);
            emitter.resetState();
        }
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setRemoveCompletedEmitters(final boolean remove) {
        this.removeCompletedEmitters = remove;
    }
    
    public void setUsePoints(final boolean usePoints) {
        this.usePoints = usePoints;
    }
    
    public boolean usePoints() {
        return this.usePoints;
    }
    
    public ParticleSystem(final String defaultSpriteRef, final int maxParticles) {
        this(defaultSpriteRef, maxParticles, null);
    }
    
    public ParticleSystem(final String defaultSpriteRef, final int maxParticles, final Color mask) {
        this.GL = Renderer.get();
        this.removeMe = new ArrayList();
        this.particlesByEmitter = new HashMap();
        this.emitters = new ArrayList();
        this.blendingMode = 2;
        this.removeCompletedEmitters = true;
        this.visible = true;
        this.maxParticlesPerEmitter = maxParticles;
        this.mask = mask;
        this.setDefaultImageName(defaultSpriteRef);
        this.dummy = this.createParticle(this);
    }
    
    public ParticleSystem(final Image defaultSprite, final int maxParticles) {
        this.GL = Renderer.get();
        this.removeMe = new ArrayList();
        this.particlesByEmitter = new HashMap();
        this.emitters = new ArrayList();
        this.blendingMode = 2;
        this.removeCompletedEmitters = true;
        this.visible = true;
        this.maxParticlesPerEmitter = maxParticles;
        this.sprite = defaultSprite;
        this.dummy = this.createParticle(this);
    }
    
    public void setDefaultImageName(final String ref) {
        this.defaultImageName = ref;
        this.sprite = null;
    }
    
    public int getBlendingMode() {
        return this.blendingMode;
    }
    
    protected Particle createParticle(final ParticleSystem system) {
        return new Particle(system);
    }
    
    public void setBlendingMode(final int mode) {
        this.blendingMode = mode;
    }
    
    public int getEmitterCount() {
        return this.emitters.size();
    }
    
    public ParticleEmitter getEmitter(final int index) {
        return this.emitters.get(index);
    }
    
    public void addEmitter(final ParticleEmitter emitter) {
        this.emitters.add(emitter);
        final ParticlePool pool = new ParticlePool(this, this.maxParticlesPerEmitter);
        this.particlesByEmitter.put(emitter, pool);
    }
    
    public void removeEmitter(final ParticleEmitter emitter) {
        this.emitters.remove(emitter);
        this.particlesByEmitter.remove(emitter);
    }
    
    public void removeAllEmitters() {
        for (int i = 0; i < this.emitters.size(); --i, ++i) {
            this.removeEmitter(this.emitters.get(i));
        }
    }
    
    public float getPositionX() {
        return this.x;
    }
    
    public float getPositionY() {
        return this.y;
    }
    
    public void setPosition(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render() {
        this.render(this.x, this.y);
    }
    
    public void render(final float x, final float y) {
        if (this.sprite == null && this.defaultImageName != null) {
            this.loadSystemParticleImage();
        }
        if (!this.visible) {
            return;
        }
        this.GL.glTranslatef(x, y, 0.0f);
        if (this.blendingMode == 1) {
            this.GL.glBlendFunc(770, 1);
        }
        if (this.usePoints()) {
            this.GL.glEnable(2832);
            TextureImpl.bindNone();
        }
        for (int emitterIdx = 0; emitterIdx < this.emitters.size(); ++emitterIdx) {
            final ParticleEmitter emitter = this.emitters.get(emitterIdx);
            if (emitter.isEnabled()) {
                if (emitter.useAdditive()) {
                    this.GL.glBlendFunc(770, 1);
                }
                final ParticlePool pool = this.particlesByEmitter.get(emitter);
                Image image = emitter.getImage();
                if (image == null) {
                    image = this.sprite;
                }
                if (!emitter.isOriented() && !emitter.usePoints(this)) {
                    image.startUse();
                }
                for (int i = 0; i < pool.particles.length; ++i) {
                    if (pool.particles[i].inUse()) {
                        pool.particles[i].render();
                    }
                }
                if (!emitter.isOriented() && !emitter.usePoints(this)) {
                    image.endUse();
                }
                if (emitter.useAdditive()) {
                    this.GL.glBlendFunc(770, 771);
                }
            }
        }
        if (this.usePoints()) {
            this.GL.glDisable(2832);
        }
        if (this.blendingMode == 1) {
            this.GL.glBlendFunc(770, 771);
        }
        Color.white.bind();
        this.GL.glTranslatef(-x, -y, 0.0f);
    }
    
    private void loadSystemParticleImage() {
        class llI implements PrivilegedAction
        {
            final /* synthetic */ ParticleSystem this$0;
            
            llI(final ParticleSystem this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public Object run() {
                try {
                    if (this.this$0.mask != null) {
                        this.this$0.sprite = new Image(this.this$0.defaultImageName, this.this$0.mask);
                    }
                    else {
                        this.this$0.sprite = new Image(this.this$0.defaultImageName);
                    }
                }
                catch (SlickException e) {
                    Log.error(e);
                    this.this$0.defaultImageName = null;
                }
                return null;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: invokespecial   org/newdawn/slick/particles/llI.<init>:(Lorg/newdawn/slick/particles/ParticleSystem;)V
        //     8: invokestatic    java/security/AccessController.doPrivileged:(Ljava/security/PrivilegedAction;)Ljava/lang/Object;
        //    11: pop            
        //    12: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void update(final int delta) {
        if (this.sprite == null && this.defaultImageName != null) {
            this.loadSystemParticleImage();
        }
        this.removeMe.clear();
        final ArrayList emitters = new ArrayList(this.emitters);
        for (int i = 0; i < emitters.size(); ++i) {
            final ParticleEmitter emitter = emitters.get(i);
            if (emitter.isEnabled()) {
                emitter.update(this, delta);
                if (this.removeCompletedEmitters && emitter.completed()) {
                    this.removeMe.add(emitter);
                    this.particlesByEmitter.remove(emitter);
                }
            }
        }
        this.emitters.removeAll(this.removeMe);
        this.pCount = 0;
        if (!this.particlesByEmitter.isEmpty()) {
            for (final ParticleEmitter emitter : this.particlesByEmitter.keySet()) {
                if (emitter.isEnabled()) {
                    final ParticlePool pool = this.particlesByEmitter.get(emitter);
                    for (int j = 0; j < pool.particles.length; ++j) {
                        if (pool.particles[j].life > 0.0f) {
                            pool.particles[j].update(delta);
                            ++this.pCount;
                        }
                    }
                }
            }
        }
    }
    
    public int getParticleCount() {
        return this.pCount;
    }
    
    public Particle getNewParticle(final ParticleEmitter emitter, final float life) {
        final ParticlePool pool = this.particlesByEmitter.get(emitter);
        final ArrayList available = pool.available;
        if (available.size() > 0) {
            final Particle p = available.remove(available.size() - 1);
            p.init(emitter, life);
            p.setImage(this.sprite);
            return p;
        }
        Log.warn("Ran out of particles (increase the limit)!");
        return this.dummy;
    }
    
    public void release(final Particle particle) {
        if (particle != this.dummy) {
            final ParticlePool pool = this.particlesByEmitter.get(particle.getEmitter());
            pool.available.add(particle);
        }
    }
    
    public void releaseAll(final ParticleEmitter emitter) {
        if (!this.particlesByEmitter.isEmpty()) {
            for (final ParticlePool pool : this.particlesByEmitter.values()) {
                for (int i = 0; i < pool.particles.length; ++i) {
                    if (pool.particles[i].inUse() && pool.particles[i].getEmitter() == emitter) {
                        pool.particles[i].setLife(-1.0f);
                        this.release(pool.particles[i]);
                    }
                }
            }
        }
    }
    
    public void moveAll(final ParticleEmitter emitter, final float x, final float y) {
        final ParticlePool pool = this.particlesByEmitter.get(emitter);
        for (int i = 0; i < pool.particles.length; ++i) {
            if (pool.particles[i].inUse()) {
                pool.particles[i].move(x, y);
            }
        }
    }
    
    public ParticleSystem duplicate() throws SlickException {
        for (int i = 0; i < this.emitters.size(); ++i) {
            if (!(this.emitters.get(i) instanceof ConfigurableEmitter)) {
                throw new SlickException("Only systems contianing configurable emitters can be duplicated");
            }
        }
        ParticleSystem theCopy = null;
        try {
            final ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ParticleIO.saveConfiguredSystem((OutputStream)bout, this);
            final ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            theCopy = ParticleIO.loadConfiguredSystem((InputStream)bin);
        }
        catch (IOException e) {
            Log.error("Failed to duplicate particle system");
            throw new SlickException("Unable to duplicated particle system", e);
        }
        return theCopy;
    }
    
    private class ParticlePool
    {
        public Particle[] particles;
        public ArrayList available;
        
        public ParticlePool(final ParticleSystem system, final int maxParticles) {
            this.particles = new Particle[maxParticles];
            this.available = new ArrayList();
            for (int i = 0; i < this.particles.length; ++i) {
                this.particles[i] = ParticleSystem.this.createParticle(system);
            }
            this.reset(system);
        }
        
        public void reset(final ParticleSystem system) {
            this.available.clear();
            for (int i = 0; i < this.particles.length; ++i) {
                this.available.add(this.particles[i]);
            }
        }
    }
}
