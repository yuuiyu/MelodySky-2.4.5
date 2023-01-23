//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import gnu.trove.set.hash.*;
import gnu.trove.strategy.*;

public class DeduplicatingStorageTrove<T> extends TCustomHashSet<T> implements IDeduplicatingStorage<T>
{
    public DeduplicatingStorageTrove(final HashingStrategy<T> strategy) {
        super((HashingStrategy)strategy);
    }
    
    public T deduplicate(final T o) {
        final int i = this.index((Object)o);
        if (i >= 0) {
            return (T)this._set[i];
        }
        this.add((Object)o);
        return o;
    }
}
