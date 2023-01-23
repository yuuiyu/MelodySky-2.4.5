//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import java.util.concurrent.*;
import java.util.function.*;
import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

public class Synchronizer
{
    Display display;
    final ConcurrentLinkedQueue<RunnableLock> messages;
    Thread syncThread;
    static final int GROW_SIZE = 4;
    static final int MESSAGE_LIMIT = 64;
    static final boolean IS_COCOA;
    static final boolean IS_GTK;
    
    public Synchronizer(final Display display) {
        this.messages = new ConcurrentLinkedQueue<RunnableLock>();
        this.display = display;
    }
    
    void moveAllEventsTo(final Synchronizer toReceiveTheEvents) {
        final List<RunnableLock> tail = new ArrayList<RunnableLock>();
        final ConcurrentLinkedQueue<RunnableLock> messages = toReceiveTheEvents.messages;
        final List<RunnableLock> list = tail;
        Objects.requireNonNull((ArrayList)list);
        messages.removeIf(list::add);
        final ConcurrentLinkedQueue<RunnableLock> messages2 = this.messages;
        final ConcurrentLinkedQueue<RunnableLock> messages3 = toReceiveTheEvents.messages;
        Objects.requireNonNull(messages3);
        messages2.removeIf(messages3::add);
        toReceiveTheEvents.messages.addAll(tail);
    }
    
    void addLast(final RunnableLock lock) {
        final boolean wake = this.messages.isEmpty();
        this.messages.add(lock);
        if (wake) {
            this.display.wakeThread();
        }
    }
    
    protected void asyncExec(final Runnable runnable) {
        if (runnable == null && !Synchronizer.IS_GTK && !Synchronizer.IS_COCOA) {
            this.display.wake();
            return;
        }
        this.addLast(new RunnableLock(runnable));
    }
    
    boolean isMessagesEmpty() {
        return this.messages.isEmpty();
    }
    
    void releaseSynchronizer() {
        this.display = null;
        this.messages.clear();
        this.syncThread = null;
    }
    
    RunnableLock removeFirst() {
        return this.messages.poll();
    }
    
    boolean runAsyncMessages() {
        return this.runAsyncMessages(false);
    }
    
    boolean runAsyncMessages(final boolean all) {
        boolean run = false;
        do {
            final RunnableLock lock = this.removeFirst();
            if (lock == null) {
                return run;
            }
            run = true;
            synchronized (lock) {
                this.syncThread = lock.thread;
                this.display.sendPreEvent(0);
                try {
                    lock.run(this.display);
                }
                catch (Throwable t) {
                    SWT.error(46, lock.throwable = t);
                }
                finally {
                    if (this.display != null && !this.display.isDisposed()) {
                        this.display.sendPostEvent(0);
                    }
                    this.syncThread = null;
                    lock.notifyAll();
                }
            }
        } while (all);
        return run;
    }
    
    protected void syncExec(final Runnable runnable) {
        RunnableLock lock = null;
        synchronized (Device.class) {
            if (this.display == null || this.display.isDisposed()) {
                SWT.error(45);
            }
            if (!this.display.isValidThread()) {
                if (runnable == null) {
                    this.display.wake();
                    return;
                }
                lock = new RunnableLock(runnable);
                lock.thread = Thread.currentThread();
                this.addLast(lock);
            }
        }
        if (lock == null) {
            if (runnable != null) {
                this.display.sendPreEvent(0);
                try {
                    runnable.run();
                }
                catch (RuntimeException exception) {
                    this.display.getRuntimeExceptionHandler().accept(exception);
                }
                catch (Error error) {
                    this.display.getErrorHandler().accept(error);
                }
                finally {
                    if (this.display != null && !this.display.isDisposed()) {
                        this.display.sendPostEvent(0);
                    }
                }
            }
            return;
        }
        synchronized (lock) {
            boolean interrupted = false;
            while (!lock.done()) {
                try {
                    lock.wait();
                }
                catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            if (lock.throwable != null) {
                SWT.error(46, lock.throwable);
            }
        }
    }
    
    static {
        IS_COCOA = "cocoa".equals(SWT.getPlatform());
        IS_GTK = "gtk".equals(SWT.getPlatform());
    }
}
