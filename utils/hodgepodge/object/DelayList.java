//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import utils.hodgepodge.object.time.*;
import java.util.*;
import java.util.function.*;

public final class DelayList<E>
{
    private final TimerUtils timerUtils;
    private final List<E> currentList;
    private int index;
    
    public DelayList(final List<E> currentList) {
        this.timerUtils = new TimerUtils(true);
        this.currentList = currentList;
    }
    
    public void forEach(final long delay, final Consumer<E> consumer) {
        if (delay < 0L) {
            throw new IllegalArgumentException("Delay cannot be negative");
        }
        while (this.index < this.currentList.size()) {
            this.implement(delay, consumer);
        }
        this.reset();
    }
    
    public void forEachNoStoppage(final int delay, final Consumer<E> consumer) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay cannot be negative");
        }
        if (this.index < this.currentList.size()) {
            this.implement(delay, consumer);
        }
        else {
            this.reset();
        }
    }
    
    private void implement(final long delay, final Consumer<E> consumer) {
        if (delay == 0L) {
            consumer.accept(this.currentList.get(this.index));
            ++this.index;
        }
        else if (this.timerUtils.hasReached((double)delay)) {
            consumer.accept(this.currentList.get(this.index));
            ++this.index;
        }
    }
    
    public void reset() {
        this.index = 0;
    }
    
    public List<E> getCurrentList() {
        return this.currentList;
    }
}
