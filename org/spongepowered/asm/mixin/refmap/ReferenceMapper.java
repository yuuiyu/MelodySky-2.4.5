//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.refmap;

import com.google.common.collect.*;
import java.util.*;
import net.minecraft.launchwrapper.*;
import java.io.*;
import com.google.gson.*;
import org.apache.logging.log4j.*;

public final class ReferenceMapper implements Serializable
{
    private static final long serialVersionUID = 2L;
    public static final String DEFAULT_RESOURCE = "mixin.refmap.json";
    public static final ReferenceMapper DEFAULT_MAPPER;
    private static final Logger logger;
    private final Map<String, Map<String, String>> mappings;
    private final Map<String, Map<String, Map<String, String>>> data;
    private final transient boolean readOnly;
    private transient String context;
    
    public ReferenceMapper() {
        this(false);
    }
    
    private ReferenceMapper(final boolean readOnly) {
        this.mappings = (Map<String, Map<String, String>>)Maps.newHashMap();
        this.data = (Map<String, Map<String, Map<String, String>>>)Maps.newHashMap();
        this.context = null;
        this.readOnly = readOnly;
    }
    
    public String getContext() {
        return this.context;
    }
    
    public void setContext(final String context) {
        this.context = context;
    }
    
    public String remap(final String className, final String reference) {
        return this.remapWithContext(this.context, className, reference);
    }
    
    public String remapWithContext(final String context, final String className, final String reference) {
        Map<String, Map<String, String>> mappings = this.mappings;
        if (context != null) {
            mappings = this.data.get(context);
            if (mappings == null) {
                mappings = this.mappings;
            }
        }
        return this.remap(mappings, className, reference);
    }
    
    private String remap(final Map<String, Map<String, String>> mappings, final String className, final String reference) {
        if (className == null) {
            for (final Map<String, String> mapping : mappings.values()) {
                if (mapping.containsKey(reference)) {
                    return mapping.get(reference);
                }
            }
        }
        final Map<String, String> classMappings = mappings.get(className);
        if (classMappings == null) {
            return reference;
        }
        final String remappedReference = classMappings.get(reference);
        return (remappedReference != null) ? remappedReference : reference;
    }
    
    public String addMapping(final String context, final String className, final String reference, final String newReference) {
        if (this.readOnly || reference == null || newReference == null || reference.equals(newReference)) {
            return null;
        }
        Map<String, Map<String, String>> mappings = this.mappings;
        if (context != null) {
            mappings = this.data.get(context);
            if (mappings == null) {
                mappings = (Map<String, Map<String, String>>)Maps.newHashMap();
                this.data.put(context, mappings);
            }
        }
        Map<String, String> classMappings = mappings.get(className);
        if (classMappings == null) {
            classMappings = new HashMap<String, String>();
            mappings.put(className, classMappings);
        }
        return classMappings.put(reference, newReference);
    }
    
    public void write(final Appendable writer) {
        new GsonBuilder().setPrettyPrinting().create().toJson((Object)this, writer);
    }
    
    public static ReferenceMapper read(final String resourcePath) {
        Reader reader = null;
        try {
            final InputStream resource = Launch.classLoader.getResourceAsStream(resourcePath);
            if (resource != null) {
                reader = new InputStreamReader(resource);
                return readJson(reader);
            }
        }
        catch (JsonParseException ex) {
            ReferenceMapper.logger.error("Invalid REFMAP JSON in " + resourcePath + ": " + ex.getClass().getName() + " " + ex.getMessage());
        }
        catch (Exception ex2) {
            ReferenceMapper.logger.error("Failed reading REFMAP JSON from " + resourcePath + ": " + ex2.getClass().getName() + " " + ex2.getMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ex3) {}
            }
        }
        return ReferenceMapper.DEFAULT_MAPPER;
    }
    
    public static ReferenceMapper read(final Reader reader) {
        try {
            return readJson(reader);
        }
        catch (Exception ex) {
            return ReferenceMapper.DEFAULT_MAPPER;
        }
    }
    
    private static ReferenceMapper readJson(final Reader reader) {
        return (ReferenceMapper)new Gson().fromJson(reader, (Class)ReferenceMapper.class);
    }
    
    static {
        DEFAULT_MAPPER = new ReferenceMapper(true);
        logger = LogManager.getLogger("mixin");
    }
}
