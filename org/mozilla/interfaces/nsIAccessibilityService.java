//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibilityService extends nsIAccessibleRetrieval
{
    public static final String NS_IACCESSIBILITYSERVICE_IID = "{0e80f152-d676-4fba-8862-9dc4eb761442}";
    
    nsIAccessible createOuterDocAccessible(final nsIDOMNode p0);
    
    nsIAccessible createRootAccessible(final nsISupports p0, final nsISupports p1);
    
    nsIAccessible createHTML4ButtonAccessible(final nsISupports p0);
    
    nsIAccessible createXULAlertAccessible(final nsIDOMNode p0);
    
    nsIAccessible createHTMLAreaAccessible(final nsISupports p0, final nsIDOMNode p1, final nsIAccessible p2);
    
    nsIAccessible createHTMLBlockAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLButtonAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLButtonAccessibleXBL(final nsIDOMNode p0);
    
    nsIAccessible createHTMLAccessibleByMarkup(final nsISupports p0, final nsISupports p1, final nsIDOMNode p2, final String p3);
    
    nsIAccessible createHTMLLIAccessible(final nsISupports p0, final nsISupports p1, final String p2);
    
    nsIAccessible createHTMLCheckboxAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLCheckboxAccessibleXBL(final nsIDOMNode p0);
    
    nsIAccessible createHTMLComboboxAccessible(final nsIDOMNode p0, final nsISupports p1);
    
    nsIAccessible createHTMLGenericAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLGroupboxAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLHRAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLImageAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLLabelAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLListboxAccessible(final nsIDOMNode p0, final nsISupports p1);
    
    nsIAccessible createHTMLObjectFrameAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLRadioButtonAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLRadioButtonAccessibleXBL(final nsIDOMNode p0);
    
    nsIAccessible createHTMLSelectOptionAccessible(final nsIDOMNode p0, final nsIAccessible p1, final nsISupports p2);
    
    nsIAccessible createHTMLTableAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLTableCellAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLTableCaptionAccessible(final nsIDOMNode p0);
    
    nsIAccessible createHTMLTableHeadAccessible(final nsIDOMNode p0);
    
    nsIAccessible createHTMLTextAccessible(final nsISupports p0);
    
    nsIAccessible createHTMLTextFieldAccessible(final nsISupports p0);
    
    nsIAccessible createXULButtonAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULCheckboxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULColorPickerAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULColorPickerTileAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULComboboxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULDropmarkerAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULGroupboxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULImageAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULLinkAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULListboxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULListitemAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULMenubarAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULMenuitemAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULMenupopupAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULMenuSeparatorAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULProgressMeterAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULStatusBarAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULRadioButtonAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULRadioGroupAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULSelectOptionAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULSelectListAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTabAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTabBoxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTabPanelsAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTabsAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTextAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTextBoxAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTreeAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTreeColumnsAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTreeColumnitemAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULToolbarAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULToolbarSeparatorAccessible(final nsIDOMNode p0);
    
    nsIAccessible createXULTooltipAccessible(final nsIDOMNode p0);
}
