//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.image.*;
import java.io.*;
import java.util.*;

public class ImageLoader
{
    public ImageData[] data;
    public int logicalScreenWidth;
    public int logicalScreenHeight;
    public int backgroundPixel;
    public int repeatCount;
    public int compression;
    List<ImageLoaderListener> imageLoaderListeners;
    
    public ImageLoader() {
        this.reset();
    }
    
    void reset() {
        this.data = null;
        this.logicalScreenWidth = 0;
        this.logicalScreenHeight = 0;
        this.backgroundPixel = -1;
        this.repeatCount = 1;
        this.compression = -1;
    }
    
    public ImageData[] load(final InputStream stream) {
        if (stream == null) {
            SWT.error(4);
        }
        this.reset();
        return this.data = FileFormat.load(stream, this);
    }
    
    public ImageData[] load(final String filename) {
        if (filename == null) {
            SWT.error(4);
        }
        InputStream stream = null;
        try {
            stream = new FileInputStream(filename);
            return this.load(stream);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            }
            catch (IOException ex) {}
        }
        return null;
    }
    
    public void save(final OutputStream stream, final int format) {
        if (stream == null) {
            SWT.error(4);
        }
        FileFormat.save(stream, format, this);
    }
    
    public void save(final String filename, final int format) {
        if (filename == null) {
            SWT.error(4);
        }
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filename);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        this.save(stream, format);
        try {
            stream.close();
        }
        catch (IOException ex) {}
    }
    
    public void addImageLoaderListener(final ImageLoaderListener listener) {
        if (listener == null) {
            SWT.error(4);
        }
        if (this.imageLoaderListeners == null) {
            this.imageLoaderListeners = new ArrayList<ImageLoaderListener>();
        }
        this.imageLoaderListeners.add(listener);
    }
    
    public void removeImageLoaderListener(final ImageLoaderListener listener) {
        if (listener == null) {
            SWT.error(4);
        }
        if (this.imageLoaderListeners == null) {
            return;
        }
        this.imageLoaderListeners.remove(listener);
    }
    
    public boolean hasListeners() {
        return this.imageLoaderListeners != null && this.imageLoaderListeners.size() > 0;
    }
    
    public void notifyListeners(final ImageLoaderEvent event) {
        if (!this.hasListeners()) {
            return;
        }
        for (int size = this.imageLoaderListeners.size(), i = 0; i < size; ++i) {
            final ImageLoaderListener listener = this.imageLoaderListeners.get(i);
            listener.imageDataLoaded(event);
        }
    }
}
