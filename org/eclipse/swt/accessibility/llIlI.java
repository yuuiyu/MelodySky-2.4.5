//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class llIlI extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    llIlI(final Accessible this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    @Override
    public long method0(final long[] args) {
        return this.this$0.QueryInterface(args[0], args[1]);
    }
    
    @Override
    public long method1(final long[] args) {
        return this.this$0.AddRef();
    }
    
    @Override
    public long method2(final long[] args) {
        return this.this$0.Release();
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.get_accParent(args[0]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.get_accChildCount(args[0]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.get_accChild(args[0], args[1]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.get_accName(args[0], args[1]);
    }
    
    @Override
    public long method11(final long[] args) {
        return this.this$0.get_accValue(args[0], args[1]);
    }
    
    @Override
    public long method12(final long[] args) {
        return this.this$0.get_accDescription(args[0], args[1]);
    }
    
    @Override
    public long method13(final long[] args) {
        return this.this$0.get_accRole(args[0], args[1]);
    }
    
    @Override
    public long method14(final long[] args) {
        return this.this$0.get_accState(args[0], args[1]);
    }
    
    @Override
    public long method15(final long[] args) {
        return this.this$0.get_accHelp(args[0], args[1]);
    }
    
    @Override
    public long method16(final long[] args) {
        return this.this$0.get_accHelpTopic(args[0], args[1], args[2]);
    }
    
    @Override
    public long method17(final long[] args) {
        return this.this$0.get_accKeyboardShortcut(args[0], args[1]);
    }
    
    @Override
    public long method18(final long[] args) {
        return this.this$0.get_accFocus(args[0]);
    }
    
    @Override
    public long method19(final long[] args) {
        return this.this$0.get_accSelection(args[0]);
    }
    
    @Override
    public long method20(final long[] args) {
        return this.this$0.get_accDefaultAction(args[0], args[1]);
    }
    
    @Override
    public long method21(final long[] args) {
        return this.this$0.accSelect((int)args[0], args[1]);
    }
    
    @Override
    public long method22(final long[] args) {
        return this.this$0.accLocation(args[0], args[1], args[2], args[3], args[4]);
    }
    
    @Override
    public long method23(final long[] args) {
        return this.this$0.accNavigate((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method24(final long[] args) {
        return this.this$0.accHitTest((int)args[0], (int)args[1], args[2]);
    }
    
    @Override
    public long method25(final long[] args) {
        return this.this$0.accDoDefaultAction(args[0]);
    }
    
    @Override
    public long method26(final long[] args) {
        return this.this$0.put_accName(args[0], args[1]);
    }
    
    @Override
    public long method27(final long[] args) {
        return this.this$0.put_accValue(args[0], args[1]);
    }
    
    @Override
    public long method28(final long[] args) {
        return this.this$0.get_nRelations(args[0]);
    }
    
    @Override
    public long method29(final long[] args) {
        return this.this$0.get_relation((int)args[0], args[1]);
    }
    
    @Override
    public long method30(final long[] args) {
        return this.this$0.get_relations((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method31(final long[] args) {
        return this.this$0.get_role(args[0]);
    }
    
    @Override
    public long method32(final long[] args) {
        return this.this$0.scrollTo((int)args[0]);
    }
    
    @Override
    public long method33(final long[] args) {
        return this.this$0.scrollToPoint((int)args[0], (int)args[1], (int)args[2]);
    }
    
    @Override
    public long method34(final long[] args) {
        return this.this$0.get_groupPosition(args[0], args[1], args[2]);
    }
    
    @Override
    public long method35(final long[] args) {
        return this.this$0.get_states(args[0]);
    }
    
    @Override
    public long method36(final long[] args) {
        return this.this$0.get_extendedRole(args[0]);
    }
    
    @Override
    public long method37(final long[] args) {
        return this.this$0.get_localizedExtendedRole(args[0]);
    }
    
    @Override
    public long method38(final long[] args) {
        return this.this$0.get_nExtendedStates(args[0]);
    }
    
    @Override
    public long method39(final long[] args) {
        return this.this$0.get_extendedStates((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method40(final long[] args) {
        return this.this$0.get_localizedExtendedStates((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method41(final long[] args) {
        return this.this$0.get_uniqueID(args[0]);
    }
    
    @Override
    public long method42(final long[] args) {
        return this.this$0.get_windowHandle(args[0]);
    }
    
    @Override
    public long method43(final long[] args) {
        return this.this$0.get_indexInParent(args[0]);
    }
    
    @Override
    public long method44(final long[] args) {
        return this.this$0.get_locale(args[0]);
    }
    
    @Override
    public long method45(final long[] args) {
        return this.this$0.get_attributes(args[0]);
    }
}
