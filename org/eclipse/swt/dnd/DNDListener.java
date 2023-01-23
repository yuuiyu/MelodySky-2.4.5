//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.widgets.*;

class DNDListener extends TypedListener
{
    Widget dndWidget;
    
    DNDListener(final SWTEventListener listener) {
        super(listener);
    }
    
    @Override
    public void handleEvent(final Event e) {
        switch (e.type) {
            case 2008: {
                final DragSourceEvent event = new DragSourceEvent((DNDEvent)e);
                final DragSource dragSource = (DragSource)this.dndWidget;
                final DragSourceEffect sourceEffect = dragSource.getDragSourceEffect();
                ((DragSourceListener)this.eventListener).dragStart(event);
                if (event.doit && dragSource.canBeginDrag() && sourceEffect != null) {
                    sourceEffect.dragStart(event);
                }
                event.updateEvent((DNDEvent)e);
                break;
            }
            case 2000: {
                final DragSourceEvent event = new DragSourceEvent((DNDEvent)e);
                final DragSourceEffect sourceEffect2 = ((DragSource)this.dndWidget).getDragSourceEffect();
                if (sourceEffect2 != null) {
                    sourceEffect2.dragFinished(event);
                }
                ((DragSourceListener)this.eventListener).dragFinished(event);
                event.updateEvent((DNDEvent)e);
                break;
            }
            case 2001: {
                final DragSourceEvent event = new DragSourceEvent((DNDEvent)e);
                final DragSourceEffect sourceEffect2 = ((DragSource)this.dndWidget).getDragSourceEffect();
                if (sourceEffect2 != null) {
                    sourceEffect2.dragSetData(event);
                }
                ((DragSourceListener)this.eventListener).dragSetData(event);
                event.updateEvent((DNDEvent)e);
                break;
            }
            case 2002: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).dragEnter(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.dragEnter(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
            case 2003: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).dragLeave(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.dragLeave(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
            case 2004: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).dragOver(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.dragOver(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
            case 2006: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).drop(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.drop(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
            case 2007: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).dropAccept(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.dropAccept(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
            case 2005: {
                final DropTargetEvent event2 = new DropTargetEvent((DNDEvent)e);
                ((DropTargetListener)this.eventListener).dragOperationChanged(event2);
                final DropTargetEffect dropEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropEffect != null) {
                    dropEffect.dragOperationChanged(event2);
                }
                event2.updateEvent((DNDEvent)e);
                break;
            }
        }
    }
}
