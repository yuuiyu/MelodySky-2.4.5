//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.debug;

import java.util.*;
import com.google.common.collect.*;
import org.apache.logging.log4j.*;
import org.apache.commons.io.*;
import java.io.*;
import org.jetbrains.java.decompiler.main.*;
import org.jetbrains.java.decompiler.main.extern.*;
import com.google.common.base.*;
import com.google.common.io.*;
import java.util.jar.*;

public class RuntimeDecompiler extends IFernflowerLogger implements IDecompiler, IResultSaver
{
    private final Map<String, Object> options;
    private final File outputPath;
    protected final Logger logger;
    
    public RuntimeDecompiler(final File outputPath) {
        this.options = (Map<String, Object>)ImmutableMap.builder().put((Object)"din", (Object)"0").put((Object)"rbr", (Object)"0").put((Object)"dgs", (Object)"1").put((Object)"asc", (Object)"1").put((Object)"den", (Object)"1").put((Object)"hdc", (Object)"1").put((Object)"ind", (Object)"    ").build();
        this.logger = LogManager.getLogger("fernflower");
        this.outputPath = outputPath;
        if (this.outputPath.exists()) {
            try {
                FileUtils.deleteDirectory(this.outputPath);
            }
            catch (IOException ex) {
                this.logger.warn("Error cleaning output directory: {}", new Object[] { ex.getMessage() });
            }
        }
    }
    
    public void decompile(final File file) {
        try {
            final Fernflower fernflower = new Fernflower((IBytecodeProvider)new l(this), (IResultSaver)this, (Map)this.options, (IFernflowerLogger)this);
            fernflower.getStructContext().addSpace(file, true);
            fernflower.decompileContext();
        }
        catch (Throwable ex) {
            this.logger.warn("Decompilation error while processing {}", new Object[] { file.getName() });
        }
    }
    
    public void saveFolder(final String path) {
    }
    
    public void saveClassFile(final String path, final String qualifiedName, final String entryName, final String content, final int[] mapping) {
        final File file = new File(this.outputPath, qualifiedName + ".java");
        file.getParentFile().mkdirs();
        try {
            this.logger.info("Writing {}", new Object[] { file.getAbsolutePath() });
            Files.write((CharSequence)content, file, Charsets.UTF_8);
        }
        catch (IOException ex) {
            this.writeMessage("Cannot write source file " + file, ex);
        }
    }
    
    public void startReadingClass(final String className) {
        this.logger.info("Decompiling {}", new Object[] { className });
    }
    
    public void writeMessage(final String message, final IFernflowerLogger.Severity severity) {
        this.logger.info(message);
    }
    
    public void writeMessage(final String message, final Throwable t) {
        this.logger.warn("{} {}: {}", new Object[] { message, t.getClass().getSimpleName(), t.getMessage() });
    }
    
    public void copyFile(final String source, final String path, final String entryName) {
    }
    
    public void createArchive(final String path, final String archiveName, final Manifest manifest) {
    }
    
    public void saveDirEntry(final String path, final String archiveName, final String entryName) {
    }
    
    public void copyEntry(final String source, final String path, final String archiveName, final String entry) {
    }
    
    public void saveClassEntry(final String path, final String archiveName, final String qualifiedName, final String entryName, final String content) {
    }
    
    public void closeArchive(final String path, final String archiveName) {
    }
}
