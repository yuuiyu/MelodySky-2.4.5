//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class IAccessible extends IDispatch
{
    public IAccessible(final long address) {
        super(address);
    }
    
    public int get_accParent(final long ppdispParent) {
        return COM.VtblCall(7, this.address, ppdispParent);
    }
    
    public int get_accChildCount(final long pcountChildren) {
        return COM.VtblCall(8, this.address, pcountChildren);
    }
    
    public int get_accChild(final long variant, final long ppdispChild) {
        return COM.VtblCall(9, this.address, variant, ppdispChild);
    }
    
    public int get_accName(final long variant, final long pszName) {
        return COM.VtblCall(10, this.address, variant, pszName);
    }
    
    public int get_accValue(final long variant, final long pszValue) {
        return COM.VtblCall(11, this.address, variant, pszValue);
    }
    
    public int get_accDescription(final long variant, final long pszDescription) {
        return COM.VtblCall(12, this.address, variant, pszDescription);
    }
    
    public int get_accRole(final long variant, final long pvarRole) {
        return COM.VtblCall(13, this.address, variant, pvarRole);
    }
    
    public int get_accState(final long variant, final long pvarState) {
        return COM.VtblCall(14, this.address, variant, pvarState);
    }
    
    public int get_accHelp(final long variant, final long pszHelp) {
        return COM.VtblCall(15, this.address, variant, pszHelp);
    }
    
    public int get_accHelpTopic(final long pszHelpFile, final long variant, final long pidTopic) {
        return COM.VtblCall(16, this.address, pszHelpFile, variant, pidTopic);
    }
    
    public int get_accKeyboardShortcut(final long variant, final long pszKeyboardShortcut) {
        return COM.VtblCall(17, this.address, variant, pszKeyboardShortcut);
    }
    
    public int get_accFocus(final long pvarChild) {
        return COM.VtblCall(18, this.address, pvarChild);
    }
    
    public int get_accSelection(final long pvarChildren) {
        return COM.VtblCall(19, this.address, pvarChildren);
    }
    
    public int get_accDefaultAction(final long variant, final long pszDefaultAction) {
        return COM.VtblCall(20, this.address, variant, pszDefaultAction);
    }
    
    public int accSelect(final int flagsSelect, final long variant) {
        return COM.VtblCall(21, this.address, flagsSelect, variant);
    }
    
    public int accLocation(final long pxLeft, final long pyTop, final long pcxWidth, final long pcyHeight, final long variant) {
        return COM.VtblCall(22, this.address, pxLeft, pyTop, pcxWidth, pcyHeight, variant);
    }
    
    public int accNavigate(final int navDir, final long variant, final long pvarEndUpAt) {
        return COM.VtblCall(23, this.address, navDir, variant, pvarEndUpAt);
    }
    
    public int accHitTest(final int xLeft, final int yTop, final long pvarChild) {
        return COM.VtblCall(24, this.address, xLeft, (long)yTop, pvarChild);
    }
    
    public int accDoDefaultAction(final long variant) {
        return COM.VtblCall(25, this.address, variant);
    }
    
    public int put_accName(final long variant, final long szName) {
        return COM.VtblCall(26, this.address, variant, szName);
    }
    
    public int put_accValue(final long variant, final long szValue) {
        return COM.VtblCall(27, this.address, variant, szValue);
    }
}
