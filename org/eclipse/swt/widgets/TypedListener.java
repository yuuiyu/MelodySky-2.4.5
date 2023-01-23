//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.events.*;

public class TypedListener implements Listener
{
    protected SWTEventListener eventListener;
    
    public TypedListener(final SWTEventListener listener) {
        this.eventListener = listener;
    }
    
    public SWTEventListener getEventListener() {
        return this.eventListener;
    }
    
    public void handleEvent(final Event e) {
        switch (e.type) {
            case 26: {
                ((ShellListener)this.eventListener).shellActivated(new ShellEvent(e));
                break;
            }
            case 30: {
                ((ArmListener)this.eventListener).widgetArmed(new ArmEvent(e));
                break;
            }
            case 21: {
                final ShellEvent event = new ShellEvent(e);
                ((ShellListener)this.eventListener).shellClosed(event);
                e.doit = event.doit;
                break;
            }
            case 18: {
                if (this.eventListener instanceof TreeListener) {
                    ((TreeListener)this.eventListener).treeCollapsed(new TreeEvent(e));
                    break;
                }
                ((ExpandListener)this.eventListener).itemCollapsed(new ExpandEvent(e));
                break;
            }
            case 27: {
                ((ShellListener)this.eventListener).shellDeactivated(new ShellEvent(e));
                break;
            }
            case 20: {
                ((ShellListener)this.eventListener).shellDeiconified(new ShellEvent(e));
                break;
            }
            case 14: {
                ((SelectionListener)this.eventListener).widgetDefaultSelected(new SelectionEvent(e));
                break;
            }
            case 12: {
                ((DisposeListener)this.eventListener).widgetDisposed(new DisposeEvent(e));
                break;
            }
            case 29: {
                ((DragDetectListener)this.eventListener).dragDetected(new DragDetectEvent(e));
                break;
            }
            case 17: {
                if (this.eventListener instanceof TreeListener) {
                    ((TreeListener)this.eventListener).treeExpanded(new TreeEvent(e));
                    break;
                }
                ((ExpandListener)this.eventListener).itemExpanded(new ExpandEvent(e));
                break;
            }
            case 15: {
                ((FocusListener)this.eventListener).focusGained(new FocusEvent(e));
                break;
            }
            case 16: {
                ((FocusListener)this.eventListener).focusLost(new FocusEvent(e));
                break;
            }
            case 48: {
                final GestureEvent event2 = new GestureEvent(e);
                ((GestureListener)this.eventListener).gesture(event2);
                e.doit = event2.doit;
                break;
            }
            case 28: {
                ((HelpListener)this.eventListener).helpRequested(new HelpEvent(e));
                break;
            }
            case 23: {
                ((MenuListener)this.eventListener).menuHidden(new MenuEvent(e));
                break;
            }
            case 19: {
                ((ShellListener)this.eventListener).shellIconified(new ShellEvent(e));
                break;
            }
            case 1: {
                final KeyEvent event3 = new KeyEvent(e);
                ((KeyListener)this.eventListener).keyPressed(event3);
                e.doit = event3.doit;
                break;
            }
            case 2: {
                final KeyEvent event3 = new KeyEvent(e);
                ((KeyListener)this.eventListener).keyReleased(event3);
                e.doit = event3.doit;
                break;
            }
            case 24: {
                ((ModifyListener)this.eventListener).modifyText(new ModifyEvent(e));
                break;
            }
            case 35: {
                final MenuDetectEvent event4 = new MenuDetectEvent(e);
                ((MenuDetectListener)this.eventListener).menuDetected(event4);
                e.x = event4.x;
                e.y = event4.y;
                e.doit = event4.doit;
                e.detail = event4.detail;
                break;
            }
            case 3: {
                ((MouseListener)this.eventListener).mouseDown(new MouseEvent(e));
                break;
            }
            case 8: {
                ((MouseListener)this.eventListener).mouseDoubleClick(new MouseEvent(e));
                break;
            }
            case 6: {
                ((MouseTrackListener)this.eventListener).mouseEnter(new MouseEvent(e));
                break;
            }
            case 7: {
                ((MouseTrackListener)this.eventListener).mouseExit(new MouseEvent(e));
                break;
            }
            case 32: {
                ((MouseTrackListener)this.eventListener).mouseHover(new MouseEvent(e));
                break;
            }
            case 5: {
                ((MouseMoveListener)this.eventListener).mouseMove(new MouseEvent(e));
            }
            case 37: {
                ((MouseWheelListener)this.eventListener).mouseScrolled(new MouseEvent(e));
            }
            case 4: {
                ((MouseListener)this.eventListener).mouseUp(new MouseEvent(e));
                break;
            }
            case 10: {
                ((ControlListener)this.eventListener).controlMoved(new ControlEvent(e));
                break;
            }
            case 9: {
                final PaintEvent event5 = new PaintEvent(e);
                ((PaintListener)this.eventListener).paintControl(event5);
                e.gc = event5.gc;
                break;
            }
            case 11: {
                ((ControlListener)this.eventListener).controlResized(new ControlEvent(e));
                break;
            }
            case 49: {
                final SegmentEvent event6 = new SegmentEvent(e);
                ((SegmentListener)this.eventListener).getSegments(event6);
                e.segments = event6.segments;
                e.segmentsChars = event6.segmentsChars;
                break;
            }
            case 13: {
                final SelectionEvent event7 = new SelectionEvent(e);
                ((SelectionListener)this.eventListener).widgetSelected(event7);
                e.x = event7.x;
                e.y = event7.y;
                e.doit = event7.doit;
                break;
            }
            case 22: {
                ((MenuListener)this.eventListener).menuShown(new MenuEvent(e));
                break;
            }
            case 47: {
                ((TouchListener)this.eventListener).touch(new TouchEvent(e));
                break;
            }
            case 31: {
                final TraverseEvent event8 = new TraverseEvent(e);
                ((TraverseListener)this.eventListener).keyTraversed(event8);
                e.detail = event8.detail;
                e.doit = event8.doit;
                break;
            }
            case 25: {
                final VerifyEvent event9 = new VerifyEvent(e);
                ((VerifyListener)this.eventListener).verifyText(event9);
                e.text = event9.text;
                e.doit = event9.doit;
                break;
            }
        }
    }
}
