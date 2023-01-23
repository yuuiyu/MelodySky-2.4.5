//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.*;

class Relation
{
    Accessible accessible;
    COMObject objIAccessibleRelation;
    int refCount;
    int type;
    Accessible[] targets;
    static final String[] relationTypeString;
    static final String[] localizedRelationTypeString;
    
    Relation(final Accessible accessible, final int type) {
        this.accessible = accessible;
        this.type = type;
        this.targets = new Accessible[0];
        this.AddRef();
    }
    
    long getAddress() {
        if (this.objIAccessibleRelation == null) {
            this.createIAccessibleRelation();
        }
        return this.objIAccessibleRelation.getAddress();
    }
    
    void createIAccessibleRelation() {
        this.objIAccessibleRelation = (COMObject)new lllII(this, new int[] { 2, 0, 0, 1, 1, 1, 2, 3 });
    }
    
    int QueryInterface(final long iid, final long ppvObject) {
        final GUID guid = new GUID();
        COM.MoveMemory(guid, iid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIAccessibleRelation)) {
            OS.MoveMemory(ppvObject, new long[] { this.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        return -2147467262;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            if (this.objIAccessibleRelation != null) {
                this.objIAccessibleRelation.dispose();
            }
            this.objIAccessibleRelation = null;
        }
        return this.refCount;
    }
    
    int get_relationType(final long pbstrRelationType) {
        this.setString(pbstrRelationType, Relation.relationTypeString[this.type]);
        return 0;
    }
    
    int get_localizedRelationType(final long pbstrLocalizedRelationType) {
        this.setString(pbstrLocalizedRelationType, Relation.localizedRelationTypeString[this.type]);
        return 0;
    }
    
    int get_nTargets(final long pNTargets) {
        OS.MoveMemory(pNTargets, new int[] { this.targets.length }, 4);
        return 0;
    }
    
    int get_target(final int targetIndex, final long ppTarget) {
        if (targetIndex < 0 || targetIndex >= this.targets.length) {
            return -2147024809;
        }
        final Accessible target = this.targets[targetIndex];
        target.AddRef();
        OS.MoveMemory(ppTarget, new long[] { target.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_targets(final int maxTargets, final long ppTargets, final long pNTargets) {
        final int count = Math.min(this.targets.length, maxTargets);
        for (int i = 0; i < count; ++i) {
            final Accessible target = this.targets[i];
            target.AddRef();
            OS.MoveMemory(ppTargets + i * C.PTR_SIZEOF, new long[] { target.getAddress() }, C.PTR_SIZEOF);
        }
        OS.MoveMemory(pNTargets, new int[] { count }, 4);
        return 0;
    }
    
    void addTarget(final Accessible target) {
        if (this.containsTarget(target)) {
            return;
        }
        final Accessible[] newTargets = new Accessible[this.targets.length + 1];
        System.arraycopy(this.targets, 0, newTargets, 0, this.targets.length);
        newTargets[this.targets.length] = target;
        this.targets = newTargets;
    }
    
    boolean containsTarget(final Accessible searched) {
        for (final Accessible target : this.targets) {
            if (target == searched) {
                return true;
            }
        }
        return false;
    }
    
    void removeTarget(final Accessible searched) {
        if (!this.containsTarget(searched)) {
            return;
        }
        final Accessible[] newTargets = new Accessible[this.targets.length - 1];
        int j = 0;
        for (final Accessible target : this.targets) {
            if (target != searched) {
                newTargets[j++] = target;
            }
        }
        this.targets = newTargets;
    }
    
    boolean hasTargets() {
        return this.targets.length > 0;
    }
    
    void setString(final long psz, final String string) {
        final char[] data = string.toCharArray();
        final long ptr = COM.SysAllocString(data);
        OS.MoveMemory(psz, new long[] { ptr }, C.PTR_SIZEOF);
    }
    
    static {
        relationTypeString = new String[] { "controlledBy", "controllerFor", "describedBy", "descriptionFor", "embeddedBy", "embeds", "flowsFrom", "flowsTo", "labelFor", "labelledBy", "memberOf", "nodeChildOf", "parentWindowOf", "popupFor", "subwindowOf" };
        localizedRelationTypeString = new String[] { SWT.getMessage("SWT_Controlled_By"), SWT.getMessage("SWT_Controller_For"), SWT.getMessage("SWT_Described_By"), SWT.getMessage("SWT_Description_For"), SWT.getMessage("SWT_Embedded_By"), SWT.getMessage("SWT_Embeds"), SWT.getMessage("SWT_Flows_From"), SWT.getMessage("SWT_Flows_To"), SWT.getMessage("SWT_Label_For"), SWT.getMessage("SWT_Labelled_By"), SWT.getMessage("SWT_Member_Of"), SWT.getMessage("SWT_Node_Child_Of"), SWT.getMessage("SWT_Parent_Window_Of"), SWT.getMessage("SWT_Popup_For"), SWT.getMessage("SWT_Subwindow_Of") };
    }
}
