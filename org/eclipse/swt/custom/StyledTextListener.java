//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;

class StyledTextListener extends TypedListener
{
    StyledTextListener(final SWTEventListener listener) {
        super(listener);
    }
    
    @Override
    public void handleEvent(final Event e) {
        switch (e.type) {
            case 3000: {
                final ExtendedModifyEvent extendedModifyEvent = new ExtendedModifyEvent((StyledTextEvent)e);
                ((ExtendedModifyListener)this.eventListener).modifyText(extendedModifyEvent);
                break;
            }
            case 3001: {
                final LineBackgroundEvent lineBgEvent = new LineBackgroundEvent((StyledTextEvent)e);
                ((LineBackgroundListener)this.eventListener).lineGetBackground(lineBgEvent);
                ((StyledTextEvent)e).lineBackground = lineBgEvent.lineBackground;
                break;
            }
            case 3007: {
                final BidiSegmentEvent segmentEvent = new BidiSegmentEvent((StyledTextEvent)e);
                ((BidiSegmentListener)this.eventListener).lineGetSegments(segmentEvent);
                ((StyledTextEvent)e).segments = segmentEvent.segments;
                ((StyledTextEvent)e).segmentsChars = segmentEvent.segmentsChars;
                break;
            }
            case 3002: {
                final LineStyleEvent lineStyleEvent = new LineStyleEvent((StyledTextEvent)e);
                ((LineStyleListener)this.eventListener).lineGetStyle(lineStyleEvent);
                ((StyledTextEvent)e).ranges = lineStyleEvent.ranges;
                ((StyledTextEvent)e).styles = lineStyleEvent.styles;
                ((StyledTextEvent)e).alignment = lineStyleEvent.alignment;
                ((StyledTextEvent)e).indent = lineStyleEvent.indent;
                ((StyledTextEvent)e).verticalIndent = lineStyleEvent.verticalIndent;
                ((StyledTextEvent)e).wrapIndent = lineStyleEvent.wrapIndent;
                ((StyledTextEvent)e).justify = lineStyleEvent.justify;
                ((StyledTextEvent)e).bullet = lineStyleEvent.bullet;
                ((StyledTextEvent)e).bulletIndex = lineStyleEvent.bulletIndex;
                ((StyledTextEvent)e).tabStops = lineStyleEvent.tabStops;
                break;
            }
            case 3008: {
                final PaintObjectEvent paintObjectEvent = new PaintObjectEvent((StyledTextEvent)e);
                ((PaintObjectListener)this.eventListener).paintObject(paintObjectEvent);
                break;
            }
            case 3005: {
                final VerifyEvent verifyEvent = new VerifyEvent(e);
                ((VerifyKeyListener)this.eventListener).verifyKey(verifyEvent);
                e.doit = verifyEvent.doit;
                break;
            }
            case 3006: {
                final TextChangedEvent textChangedEvent = new TextChangedEvent((StyledTextContent)e.data);
                ((TextChangeListener)this.eventListener).textChanged(textChangedEvent);
                break;
            }
            case 3003: {
                final TextChangingEvent textChangingEvent = new TextChangingEvent((StyledTextContent)e.data, (StyledTextEvent)e);
                ((TextChangeListener)this.eventListener).textChanging(textChangingEvent);
                break;
            }
            case 3004: {
                final TextChangedEvent textChangedEvent2 = new TextChangedEvent((StyledTextContent)e.data);
                ((TextChangeListener)this.eventListener).textSet(textChangedEvent2);
                break;
            }
            case 3009: {
                final MovementEvent wordBoundaryEvent = new MovementEvent((StyledTextEvent)e);
                ((MovementListener)this.eventListener).getNextOffset(wordBoundaryEvent);
                ((StyledTextEvent)e).end = wordBoundaryEvent.newOffset;
                break;
            }
            case 3010: {
                final MovementEvent wordBoundaryEvent = new MovementEvent((StyledTextEvent)e);
                ((MovementListener)this.eventListener).getPreviousOffset(wordBoundaryEvent);
                ((StyledTextEvent)e).end = wordBoundaryEvent.newOffset;
                break;
            }
            case 3011: {
                final CaretEvent caretEvent = new CaretEvent((StyledTextEvent)e);
                ((CaretListener)this.eventListener).caretMoved(caretEvent);
                ((StyledTextEvent)e).end = caretEvent.caretOffset;
                break;
            }
        }
    }
}
