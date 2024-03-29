//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import java.io.*;
import java.util.regex.*;

public class VersionNumber implements Comparable<VersionNumber>, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final VersionNumber NONE;
    private static final Pattern versionNumberPattern;
    private final long value;
    private final String suffix;
    
    private VersionNumber() {
        this.value = 0L;
        this.suffix = "";
    }
    
    private VersionNumber(final short[] parts) {
        this(parts, null);
    }
    
    private VersionNumber(final short[] parts, final String suffix) {
        this.value = pack(parts);
        this.suffix = ((suffix != null) ? suffix : "");
    }
    
    private VersionNumber(final short major, final short minor, final short revision, final short build) {
        this(major, minor, revision, build, null);
    }
    
    private VersionNumber(final short major, final short minor, final short revision, final short build, final String suffix) {
        this.value = pack(major, minor, revision, build);
        this.suffix = ((suffix != null) ? suffix : "");
    }
    
    @Override
    public String toString() {
        final short[] parts = unpack(this.value);
        return String.format("%d.%d%3$s%4$s%5$s", parts[0], parts[1], ((this.value & 0x7FFFFFFFL) > 0L) ? String.format(".%d", parts[2]) : "", ((this.value & 0x7FFFL) > 0L) ? String.format(".%d", parts[3]) : "", this.suffix);
    }
    
    @Override
    public int compareTo(final VersionNumber other) {
        if (other == null) {
            return 1;
        }
        final long delta = this.value - other.value;
        return (delta > 0L) ? 1 : ((delta < 0L) ? -1 : 0);
    }
    
    @Override
    public boolean equals(final Object other) {
        return other instanceof VersionNumber && ((VersionNumber)other).value == this.value;
    }
    
    @Override
    public int hashCode() {
        return (int)(this.value >> 32) ^ (int)(this.value & -1L);
    }
    
    private static long pack(final short... shorts) {
        return (long)shorts[0] << 48 | (long)shorts[1] << 32 | (long)(shorts[2] << 16) | (long)shorts[3];
    }
    
    private static short[] unpack(final long along) {
        return new short[] { (short)(along >> 48), (short)(along >> 32 & 0x7FFFL), (short)(along >> 16 & 0x7FFFL), (short)(along & 0x7FFFL) };
    }
    
    public static final VersionNumber parse(final String version) {
        return parse(version, VersionNumber.NONE);
    }
    
    public static VersionNumber parse(final String version, final String defaultVersion) {
        return parse(version, parse(defaultVersion));
    }
    
    private static VersionNumber parse(final String version, final VersionNumber defaultVersion) {
        if (version == null) {
            return defaultVersion;
        }
        final Matcher versionNumberPatternMatcher = VersionNumber.versionNumberPattern.matcher(version);
        if (!versionNumberPatternMatcher.matches()) {
            return defaultVersion;
        }
        final short[] parts = new short[4];
        for (int pos = 0; pos < 4; ++pos) {
            final String part = versionNumberPatternMatcher.group(pos + 1);
            if (part != null) {
                final int value = Integer.parseInt(part);
                if (value > 32767) {
                    throw new IllegalArgumentException("Version parts cannot exceed 32767, found " + value);
                }
                parts[pos] = (short)value;
            }
        }
        return new VersionNumber(parts, versionNumberPatternMatcher.group(5));
    }
    
    static {
        NONE = new VersionNumber();
        versionNumberPattern = Pattern.compile("^(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5}))?)?)?(-[a-zA-Z0-9_\\-]+)?$");
    }
}
