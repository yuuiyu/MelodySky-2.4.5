//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

public class GREVersionRange
{
    private String lower;
    private boolean lowerInclusive;
    private String upper;
    private boolean upperInclusive;
    
    public GREVersionRange(final String lower, final boolean lowerInclusive, final String upper, final boolean upperInclusive) {
        this.lower = lower;
        this.lowerInclusive = lowerInclusive;
        this.upper = upper;
        this.upperInclusive = upperInclusive;
    }
    
    public boolean check(final String s) {
        final VersionComparator versionComparator = new VersionComparator();
        final int compare = versionComparator.compare(s, this.lower);
        if (compare < 0) {
            return false;
        }
        if (compare == 0 && !this.lowerInclusive) {
            return false;
        }
        final int compare2 = versionComparator.compare(s, this.upper);
        return compare2 <= 0 && (compare2 != 0 || this.upperInclusive);
    }
}
