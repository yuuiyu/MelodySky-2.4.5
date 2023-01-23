//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.ole.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.ole.win32.*;
import java.util.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Accessible
{
    static final int MAX_RELATION_TYPES = 15;
    static final int TABLE_MODEL_CHANGE_SIZE = 5;
    static final int TEXT_CHANGE_SIZE = 4;
    static final int SCROLL_RATE = 100;
    static final boolean DEBUG = false;
    static final String PROPERTY_USEIA2 = "org.eclipse.swt.accessibility.UseIA2";
    static boolean UseIA2;
    static int UniqueID;
    int refCount;
    int enumIndex;
    Runnable timer;
    COMObject objIAccessible;
    COMObject objIEnumVARIANT;
    COMObject objIServiceProvider;
    COMObject objIAccessibleApplication;
    COMObject objIAccessibleEditableText;
    COMObject objIAccessibleHyperlink;
    COMObject objIAccessibleHypertext;
    COMObject objIAccessibleTable2;
    COMObject objIAccessibleTableCell;
    COMObject objIAccessibleValue;
    IAccessible iaccessible;
    List<AccessibleListener> accessibleListeners;
    List<AccessibleControlListener> accessibleControlListeners;
    List<AccessibleTextListener> accessibleTextListeners;
    List<AccessibleActionListener> accessibleActionListeners;
    List<AccessibleEditableTextListener> accessibleEditableTextListeners;
    List<AccessibleHyperlinkListener> accessibleHyperlinkListeners;
    List<AccessibleTableListener> accessibleTableListeners;
    List<AccessibleTableCellListener> accessibleTableCellListeners;
    List<AccessibleTextExtendedListener> accessibleTextExtendedListeners;
    List<AccessibleValueListener> accessibleValueListeners;
    List<AccessibleAttributeListener> accessibleAttributeListeners;
    Relation[] relations;
    Object[] variants;
    Accessible parent;
    List<Accessible> children;
    Control control;
    int uniqueID;
    int[] tableChange;
    Object[] textDeleted;
    Object[] textInserted;
    ToolItem item;
    
    public Accessible(final Accessible parent) {
        this.refCount = 0;
        this.enumIndex = 0;
        this.relations = new Relation[15];
        this.children = new ArrayList<Accessible>();
        this.uniqueID = -1;
        this.parent = checkNull(parent);
        this.control = parent.control;
        parent.children.add(this);
        this.AddRef();
    }
    
    @Deprecated
    protected Accessible() {
        this.refCount = 0;
        this.enumIndex = 0;
        this.relations = new Relation[15];
        this.children = new ArrayList<Accessible>();
        this.uniqueID = -1;
    }
    
    Accessible(final Control control) {
        this.refCount = 0;
        this.enumIndex = 0;
        this.relations = new Relation[15];
        this.children = new ArrayList<Accessible>();
        this.uniqueID = -1;
        this.control = control;
        final long[] ppvObject = { 0L };
        final int result = COM.CreateStdAccessibleObject(control.handle, -4, COM.IIDIAccessible, ppvObject);
        if (ppvObject[0] == 0L) {
            return;
        }
        if (result != 0) {
            OLE.error(1001, result);
        }
        this.iaccessible = new IAccessible(ppvObject[0]);
        this.createIAccessible();
        this.AddRef();
    }
    
    Accessible(final Accessible parent, final long iaccessible_address) {
        this(parent);
        this.iaccessible = new IAccessible(iaccessible_address);
    }
    
    static Accessible checkNull(final Accessible parent) {
        if (parent == null) {
            SWT.error(4);
        }
        return parent;
    }
    
    void createIAccessible() {
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
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          46
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_3       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_5       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: bipush          8
        //    39: iastore        
        //    40: dup            
        //    41: bipush          7
        //    43: iconst_1       
        //    44: iastore        
        //    45: dup            
        //    46: bipush          8
        //    48: iconst_1       
        //    49: iastore        
        //    50: dup            
        //    51: bipush          9
        //    53: iconst_2       
        //    54: iastore        
        //    55: dup            
        //    56: bipush          10
        //    58: iconst_2       
        //    59: iastore        
        //    60: dup            
        //    61: bipush          11
        //    63: iconst_2       
        //    64: iastore        
        //    65: dup            
        //    66: bipush          12
        //    68: iconst_2       
        //    69: iastore        
        //    70: dup            
        //    71: bipush          13
        //    73: iconst_2       
        //    74: iastore        
        //    75: dup            
        //    76: bipush          14
        //    78: iconst_2       
        //    79: iastore        
        //    80: dup            
        //    81: bipush          15
        //    83: iconst_2       
        //    84: iastore        
        //    85: dup            
        //    86: bipush          16
        //    88: iconst_3       
        //    89: iastore        
        //    90: dup            
        //    91: bipush          17
        //    93: iconst_2       
        //    94: iastore        
        //    95: dup            
        //    96: bipush          18
        //    98: iconst_1       
        //    99: iastore        
        //   100: dup            
        //   101: bipush          19
        //   103: iconst_1       
        //   104: iastore        
        //   105: dup            
        //   106: bipush          20
        //   108: iconst_2       
        //   109: iastore        
        //   110: dup            
        //   111: bipush          21
        //   113: iconst_2       
        //   114: iastore        
        //   115: dup            
        //   116: bipush          22
        //   118: iconst_5       
        //   119: iastore        
        //   120: dup            
        //   121: bipush          23
        //   123: iconst_3       
        //   124: iastore        
        //   125: dup            
        //   126: bipush          24
        //   128: iconst_3       
        //   129: iastore        
        //   130: dup            
        //   131: bipush          25
        //   133: iconst_1       
        //   134: iastore        
        //   135: dup            
        //   136: bipush          26
        //   138: iconst_2       
        //   139: iastore        
        //   140: dup            
        //   141: bipush          27
        //   143: iconst_2       
        //   144: iastore        
        //   145: dup            
        //   146: bipush          28
        //   148: iconst_1       
        //   149: iastore        
        //   150: dup            
        //   151: bipush          29
        //   153: iconst_2       
        //   154: iastore        
        //   155: dup            
        //   156: bipush          30
        //   158: iconst_3       
        //   159: iastore        
        //   160: dup            
        //   161: bipush          31
        //   163: iconst_1       
        //   164: iastore        
        //   165: dup            
        //   166: bipush          32
        //   168: iconst_1       
        //   169: iastore        
        //   170: dup            
        //   171: bipush          33
        //   173: iconst_3       
        //   174: iastore        
        //   175: dup            
        //   176: bipush          34
        //   178: iconst_3       
        //   179: iastore        
        //   180: dup            
        //   181: bipush          35
        //   183: iconst_1       
        //   184: iastore        
        //   185: dup            
        //   186: bipush          36
        //   188: iconst_1       
        //   189: iastore        
        //   190: dup            
        //   191: bipush          37
        //   193: iconst_1       
        //   194: iastore        
        //   195: dup            
        //   196: bipush          38
        //   198: iconst_1       
        //   199: iastore        
        //   200: dup            
        //   201: bipush          39
        //   203: iconst_3       
        //   204: iastore        
        //   205: dup            
        //   206: bipush          40
        //   208: iconst_3       
        //   209: iastore        
        //   210: dup            
        //   211: bipush          41
        //   213: iconst_1       
        //   214: iastore        
        //   215: dup            
        //   216: bipush          42
        //   218: iconst_1       
        //   219: iastore        
        //   220: dup            
        //   221: bipush          43
        //   223: iconst_1       
        //   224: iastore        
        //   225: dup            
        //   226: bipush          44
        //   228: iconst_1       
        //   229: iastore        
        //   230: dup            
        //   231: bipush          45
        //   233: iconst_1       
        //   234: iastore        
        //   235: invokespecial   org/eclipse/swt/accessibility/llIlI.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //   238: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessible:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   241: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleApplication() {
        class lIllI extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            lIllI(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.get_appName(args[0]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.get_appVersion(args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_toolkitName(args[0]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_toolkitVersion(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          7
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: invokespecial   org/eclipse/swt/accessibility/lIllI.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    42: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleApplication:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    45: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleEditableText() {
        class lIIIII extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            lIIIII(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.copyText((int)args[0], (int)args[1]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.deleteText((int)args[0], (int)args[1]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.insertText((int)args[0], args[1]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.cutText((int)args[0], (int)args[1]);
            }
            
            @Override
            public long method7(final long[] args) {
                return this.this$0.pasteText((int)args[0]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.replaceText((int)args[0], (int)args[1], args[2]);
            }
            
            @Override
            public long method9(final long[] args) {
                return this.this$0.setAttributes((int)args[0], (int)args[1], args[2]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          10
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_2       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_2       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_2       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_2       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_1       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_3       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_3       
        //    53: iastore        
        //    54: invokespecial   org/eclipse/swt/accessibility/lIIIII.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    57: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleEditableText:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    60: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleHyperlink() {
        class lIIll extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            lIIll(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.get_nActions(args[0]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.doAction((int)args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_description((int)args[0], args[1]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_keyBinding((int)args[0], (int)args[1], args[2], args[3]);
            }
            
            @Override
            public long method7(final long[] args) {
                return this.this$0.get_name((int)args[0], args[1]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.get_localizedName((int)args[0], args[1]);
            }
            
            @Override
            public long method9(final long[] args) {
                return this.this$0.get_anchor((int)args[0], args[1]);
            }
            
            @Override
            public long method10(final long[] args) {
                return this.this$0.get_anchorTarget((int)args[0], args[1]);
            }
            
            @Override
            public long method11(final long[] args) {
                return this.this$0.get_startIndex(args[0]);
            }
            
            @Override
            public long method12(final long[] args) {
                return this.this$0.get_endIndex(args[0]);
            }
            
            @Override
            public long method13(final long[] args) {
                return this.this$0.get_valid(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          14
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_2       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_4       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_2       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_2       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_2       
        //    53: iastore        
        //    54: dup            
        //    55: bipush          10
        //    57: iconst_2       
        //    58: iastore        
        //    59: dup            
        //    60: bipush          11
        //    62: iconst_1       
        //    63: iastore        
        //    64: dup            
        //    65: bipush          12
        //    67: iconst_1       
        //    68: iastore        
        //    69: dup            
        //    70: bipush          13
        //    72: iconst_1       
        //    73: iastore        
        //    74: invokespecial   org/eclipse/swt/accessibility/lIIll.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    77: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleHyperlink:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    80: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleHypertext() {
        class lllIl extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            lllIl(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.addSelection((int)args[0], (int)args[1]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.get_attributes((int)args[0], args[1], args[2], args[3]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_caretOffset(args[0]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_characterExtents((int)args[0], (int)args[1], args[2], args[3], args[4], args[5]);
            }
            
            @Override
            public long method7(final long[] args) {
                return this.this$0.get_nSelections(args[0]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.get_offsetAtPoint((int)args[0], (int)args[1], (int)args[2], args[3]);
            }
            
            @Override
            public long method9(final long[] args) {
                return this.this$0.get_selection((int)args[0], args[1], args[2]);
            }
            
            @Override
            public long method10(final long[] args) {
                return this.this$0.get_text((int)args[0], (int)args[1], args[2]);
            }
            
            @Override
            public long method11(final long[] args) {
                return this.this$0.get_textBeforeOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
            }
            
            @Override
            public long method12(final long[] args) {
                return this.this$0.get_textAfterOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
            }
            
            @Override
            public long method13(final long[] args) {
                return this.this$0.get_textAtOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
            }
            
            @Override
            public long method14(final long[] args) {
                return this.this$0.removeSelection((int)args[0]);
            }
            
            @Override
            public long method15(final long[] args) {
                return this.this$0.setCaretOffset((int)args[0]);
            }
            
            @Override
            public long method16(final long[] args) {
                return this.this$0.setSelection((int)args[0], (int)args[1], (int)args[2]);
            }
            
            @Override
            public long method17(final long[] args) {
                return this.this$0.get_nCharacters(args[0]);
            }
            
            @Override
            public long method18(final long[] args) {
                return this.this$0.scrollSubstringTo((int)args[0], (int)args[1], (int)args[2]);
            }
            
            @Override
            public long method19(final long[] args) {
                return this.this$0.scrollSubstringToPoint((int)args[0], (int)args[1], (int)args[2], (int)args[3], (int)args[4]);
            }
            
            @Override
            public long method20(final long[] args) {
                return this.this$0.get_newText(args[0]);
            }
            
            @Override
            public long method21(final long[] args) {
                return this.this$0.get_oldText(args[0]);
            }
            
            @Override
            public long method22(final long[] args) {
                return this.this$0.get_nHyperlinks(args[0]);
            }
            
            @Override
            public long method23(final long[] args) {
                return this.this$0.get_hyperlink((int)args[0], args[1]);
            }
            
            @Override
            public long method24(final long[] args) {
                return this.this$0.get_hyperlinkIndex((int)args[0], args[1]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          25
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_2       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_4       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: bipush          6
        //    39: iastore        
        //    40: dup            
        //    41: bipush          7
        //    43: iconst_1       
        //    44: iastore        
        //    45: dup            
        //    46: bipush          8
        //    48: iconst_4       
        //    49: iastore        
        //    50: dup            
        //    51: bipush          9
        //    53: iconst_3       
        //    54: iastore        
        //    55: dup            
        //    56: bipush          10
        //    58: iconst_3       
        //    59: iastore        
        //    60: dup            
        //    61: bipush          11
        //    63: iconst_5       
        //    64: iastore        
        //    65: dup            
        //    66: bipush          12
        //    68: iconst_5       
        //    69: iastore        
        //    70: dup            
        //    71: bipush          13
        //    73: iconst_5       
        //    74: iastore        
        //    75: dup            
        //    76: bipush          14
        //    78: iconst_1       
        //    79: iastore        
        //    80: dup            
        //    81: bipush          15
        //    83: iconst_1       
        //    84: iastore        
        //    85: dup            
        //    86: bipush          16
        //    88: iconst_3       
        //    89: iastore        
        //    90: dup            
        //    91: bipush          17
        //    93: iconst_1       
        //    94: iastore        
        //    95: dup            
        //    96: bipush          18
        //    98: iconst_3       
        //    99: iastore        
        //   100: dup            
        //   101: bipush          19
        //   103: iconst_5       
        //   104: iastore        
        //   105: dup            
        //   106: bipush          20
        //   108: iconst_1       
        //   109: iastore        
        //   110: dup            
        //   111: bipush          21
        //   113: iconst_1       
        //   114: iastore        
        //   115: dup            
        //   116: bipush          22
        //   118: iconst_1       
        //   119: iastore        
        //   120: dup            
        //   121: bipush          23
        //   123: iconst_2       
        //   124: iastore        
        //   125: dup            
        //   126: bipush          24
        //   128: iconst_2       
        //   129: iastore        
        //   130: invokespecial   org/eclipse/swt/accessibility/lllIl.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //   133: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleHypertext:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   136: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleTable2() {
        class llIIl extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            llIIl(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.get_cellAt((int)args[0], (int)args[1], args[2]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.get_caption(args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_columnDescription((int)args[0], args[1]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_nColumns(args[0]);
            }
            
            @Override
            public long method7(final long[] args) {
                return this.this$0.get_nRows(args[0]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.get_nSelectedCells(args[0]);
            }
            
            @Override
            public long method9(final long[] args) {
                return this.this$0.get_nSelectedColumns(args[0]);
            }
            
            @Override
            public long method10(final long[] args) {
                return this.this$0.get_nSelectedRows(args[0]);
            }
            
            @Override
            public long method11(final long[] args) {
                return this.this$0.get_rowDescription((int)args[0], args[1]);
            }
            
            @Override
            public long method12(final long[] args) {
                return this.this$0.get_selectedCells(args[0], args[1]);
            }
            
            @Override
            public long method13(final long[] args) {
                return this.this$0.get_selectedColumns(args[0], args[1]);
            }
            
            @Override
            public long method14(final long[] args) {
                return this.this$0.get_selectedRows(args[0], args[1]);
            }
            
            @Override
            public long method15(final long[] args) {
                return this.this$0.get_summary(args[0]);
            }
            
            @Override
            public long method16(final long[] args) {
                return this.this$0.get_isColumnSelected((int)args[0], args[1]);
            }
            
            @Override
            public long method17(final long[] args) {
                return this.this$0.get_isRowSelected((int)args[0], args[1]);
            }
            
            @Override
            public long method18(final long[] args) {
                return this.this$0.selectRow((int)args[0]);
            }
            
            @Override
            public long method19(final long[] args) {
                return this.this$0.selectColumn((int)args[0]);
            }
            
            @Override
            public long method20(final long[] args) {
                return this.this$0.unselectRow((int)args[0]);
            }
            
            @Override
            public long method21(final long[] args) {
                return this.this$0.unselectColumn((int)args[0]);
            }
            
            @Override
            public long method22(final long[] args) {
                return this.this$0.get_modelChange(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          23
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_3       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_2       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_1       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_1       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_1       
        //    53: iastore        
        //    54: dup            
        //    55: bipush          10
        //    57: iconst_1       
        //    58: iastore        
        //    59: dup            
        //    60: bipush          11
        //    62: iconst_2       
        //    63: iastore        
        //    64: dup            
        //    65: bipush          12
        //    67: iconst_2       
        //    68: iastore        
        //    69: dup            
        //    70: bipush          13
        //    72: iconst_2       
        //    73: iastore        
        //    74: dup            
        //    75: bipush          14
        //    77: iconst_2       
        //    78: iastore        
        //    79: dup            
        //    80: bipush          15
        //    82: iconst_1       
        //    83: iastore        
        //    84: dup            
        //    85: bipush          16
        //    87: iconst_2       
        //    88: iastore        
        //    89: dup            
        //    90: bipush          17
        //    92: iconst_2       
        //    93: iastore        
        //    94: dup            
        //    95: bipush          18
        //    97: iconst_1       
        //    98: iastore        
        //    99: dup            
        //   100: bipush          19
        //   102: iconst_1       
        //   103: iastore        
        //   104: dup            
        //   105: bipush          20
        //   107: iconst_1       
        //   108: iastore        
        //   109: dup            
        //   110: bipush          21
        //   112: iconst_1       
        //   113: iastore        
        //   114: dup            
        //   115: bipush          22
        //   117: iconst_1       
        //   118: iastore        
        //   119: invokespecial   org/eclipse/swt/accessibility/llIIl.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //   122: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleTable2:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //   125: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleTableCell() {
        class llllI extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            llllI(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.get_columnExtent(args[0]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.get_columnHeaderCells(args[0], args[1]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_columnIndex(args[0]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_rowExtent(args[0]);
            }
            
            @Override
            public long method7(final long[] args) {
                return this.this$0.get_rowHeaderCells(args[0], args[1]);
            }
            
            @Override
            public long method8(final long[] args) {
                return this.this$0.get_rowIndex(args[0]);
            }
            
            @Override
            public long method9(final long[] args) {
                return this.this$0.get_isSelected(args[0]);
            }
            
            @Override
            public long method10(final long[] args) {
                return this.this$0.get_rowColumnExtents(args[0], args[1], args[2], args[3], args[4]);
            }
            
            @Override
            public long method11(final long[] args) {
                return this.this$0.get_table(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          12
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_2       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: dup            
        //    40: bipush          7
        //    42: iconst_2       
        //    43: iastore        
        //    44: dup            
        //    45: bipush          8
        //    47: iconst_1       
        //    48: iastore        
        //    49: dup            
        //    50: bipush          9
        //    52: iconst_1       
        //    53: iastore        
        //    54: dup            
        //    55: bipush          10
        //    57: iconst_5       
        //    58: iastore        
        //    59: dup            
        //    60: bipush          11
        //    62: iconst_1       
        //    63: iastore        
        //    64: invokespecial   org/eclipse/swt/accessibility/llllI.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    67: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleTableCell:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    70: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIAccessibleValue() {
        class llIII extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            llIII(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.get_currentValue(args[0]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.setCurrentValue(args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.get_maximumValue(args[0]);
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.get_minimumValue(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          7
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_1       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_1       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: invokespecial   org/eclipse/swt/accessibility/llIII.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    42: putfield        org/eclipse/swt/accessibility/Accessible.objIAccessibleValue:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    45: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIEnumVARIANT() {
        class lIIlI extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            lIIlI(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.Next((int)args[0], args[1], args[2]);
            }
            
            @Override
            public long method4(final long[] args) {
                return this.this$0.Skip((int)args[0]);
            }
            
            @Override
            public long method5(final long[] args) {
                return this.this$0.Reset();
            }
            
            @Override
            public long method6(final long[] args) {
                return this.this$0.Clone(args[0]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: bipush          7
        //     8: newarray        I
        //    10: dup            
        //    11: iconst_0       
        //    12: iconst_2       
        //    13: iastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: iconst_0       
        //    17: iastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: iconst_0       
        //    21: iastore        
        //    22: dup            
        //    23: iconst_3       
        //    24: iconst_3       
        //    25: iastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: iconst_1       
        //    29: iastore        
        //    30: dup            
        //    31: iconst_5       
        //    32: iconst_0       
        //    33: iastore        
        //    34: dup            
        //    35: bipush          6
        //    37: iconst_1       
        //    38: iastore        
        //    39: invokespecial   org/eclipse/swt/accessibility/lIIlI.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    42: putfield        org/eclipse/swt/accessibility/Accessible.objIEnumVARIANT:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    45: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void createIServiceProvider() {
        class llIll extends COMObject
        {
            final /* synthetic */ Accessible this$0;
            
            llIll(final Accessible this$0, final int[] argCounts) {
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
            public long method3(final long[] args) {
                return this.this$0.QueryService(args[0], args[1], args[2]);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: dup            
        //     5: aload_0         /* this */
        //     6: iconst_4       
        //     7: newarray        I
        //     9: dup            
        //    10: iconst_0       
        //    11: iconst_2       
        //    12: iastore        
        //    13: dup            
        //    14: iconst_1       
        //    15: iconst_0       
        //    16: iastore        
        //    17: dup            
        //    18: iconst_2       
        //    19: iconst_0       
        //    20: iastore        
        //    21: dup            
        //    22: iconst_3       
        //    23: iconst_3       
        //    24: iastore        
        //    25: invokespecial   org/eclipse/swt/accessibility/llIll.<init>:(Lorg/eclipse/swt/accessibility/Accessible;[I)V
        //    28: putfield        org/eclipse/swt/accessibility/Accessible.objIServiceProvider:Lorg/eclipse/swt/internal/ole/win32/COMObject;
        //    31: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Accessible internal_new_Accessible(final Control control) {
        return new Accessible(control);
    }
    
    public void addAccessibleListener(final AccessibleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleListeners == null) {
            this.accessibleListeners = new ArrayList<AccessibleListener>();
        }
        this.accessibleListeners.add(listener);
    }
    
    public void addAccessibleControlListener(final AccessibleControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleControlListeners == null) {
            this.accessibleControlListeners = new ArrayList<AccessibleControlListener>();
        }
        this.accessibleControlListeners.add(listener);
    }
    
    public void addAccessibleTextListener(final AccessibleTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (listener instanceof AccessibleTextExtendedListener) {
            if (this.accessibleTextExtendedListeners == null) {
                this.accessibleTextExtendedListeners = new ArrayList<AccessibleTextExtendedListener>();
            }
            this.accessibleTextExtendedListeners.add((AccessibleTextExtendedListener)listener);
        }
        else {
            if (this.accessibleTextListeners == null) {
                this.accessibleTextListeners = new ArrayList<AccessibleTextListener>();
            }
            this.accessibleTextListeners.add(listener);
        }
    }
    
    public void addAccessibleActionListener(final AccessibleActionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleActionListeners == null) {
            this.accessibleActionListeners = new ArrayList<AccessibleActionListener>();
        }
        this.accessibleActionListeners.add(listener);
    }
    
    public void addAccessibleEditableTextListener(final AccessibleEditableTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleEditableTextListeners == null) {
            this.accessibleEditableTextListeners = new ArrayList<AccessibleEditableTextListener>();
        }
        this.accessibleEditableTextListeners.add(listener);
    }
    
    public void addAccessibleHyperlinkListener(final AccessibleHyperlinkListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleHyperlinkListeners == null) {
            this.accessibleHyperlinkListeners = new ArrayList<AccessibleHyperlinkListener>();
        }
        this.accessibleHyperlinkListeners.add(listener);
    }
    
    public void addAccessibleTableListener(final AccessibleTableListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleTableListeners == null) {
            this.accessibleTableListeners = new ArrayList<AccessibleTableListener>();
        }
        this.accessibleTableListeners.add(listener);
    }
    
    public void addAccessibleTableCellListener(final AccessibleTableCellListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleTableCellListeners == null) {
            this.accessibleTableCellListeners = new ArrayList<AccessibleTableCellListener>();
        }
        this.accessibleTableCellListeners.add(listener);
    }
    
    public void addAccessibleValueListener(final AccessibleValueListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleValueListeners == null) {
            this.accessibleValueListeners = new ArrayList<AccessibleValueListener>();
        }
        this.accessibleValueListeners.add(listener);
    }
    
    public void addAccessibleAttributeListener(final AccessibleAttributeListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleAttributeListeners == null) {
            this.accessibleAttributeListeners = new ArrayList<AccessibleAttributeListener>();
        }
        this.accessibleAttributeListeners.add(listener);
    }
    
    public void addRelation(final int type, final Accessible target) {
        this.checkWidget();
        if (target == null) {
            SWT.error(4);
        }
        if (this.relations[type] == null) {
            this.relations[type] = new Relation(this, type);
        }
        this.relations[type].addTarget(target);
    }
    
    public void dispose() {
        if (this.parent == null) {
            return;
        }
        this.Release();
        this.parent.children.remove(this);
        this.parent = null;
    }
    
    long getAddress() {
        if (this.objIAccessible == null) {
            this.createIAccessible();
        }
        return this.objIAccessible.getAddress();
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public void internal_dispose_Accessible() {
        if (this.iaccessible != null) {
            this.iaccessible.Release();
        }
        this.iaccessible = null;
        this.Release();
        final List<Accessible> list = new ArrayList<Accessible>(this.children);
        for (final Accessible accChild : list) {
            accChild.dispose();
        }
    }
    
    public long internal_WM_GETOBJECT(final long wParam, final long lParam) {
        if (this.objIAccessible == null) {
            return 0L;
        }
        if ((int)lParam == -4) {
            return COM.LresultFromObject(COM.IIDIAccessible, wParam, this.objIAccessible.getAddress());
        }
        return 0L;
    }
    
    public void removeAccessibleListener(final AccessibleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleListeners != null) {
            this.accessibleListeners.remove(listener);
            if (this.accessibleListeners.isEmpty()) {
                this.accessibleListeners = null;
            }
        }
    }
    
    public void removeAccessibleControlListener(final AccessibleControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleControlListeners != null) {
            this.accessibleControlListeners.remove(listener);
            if (this.accessibleControlListeners.isEmpty()) {
                this.accessibleControlListeners = null;
            }
        }
    }
    
    public void removeAccessibleTextListener(final AccessibleTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (listener instanceof AccessibleTextExtendedListener) {
            if (this.accessibleTextExtendedListeners != null) {
                this.accessibleTextExtendedListeners.remove(listener);
                if (this.accessibleTextExtendedListeners.isEmpty()) {
                    this.accessibleTextExtendedListeners = null;
                }
            }
        }
        else if (this.accessibleTextListeners != null) {
            this.accessibleTextListeners.remove(listener);
            if (this.accessibleTextListeners.isEmpty()) {
                this.accessibleTextListeners = null;
            }
        }
    }
    
    public void removeAccessibleActionListener(final AccessibleActionListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleActionListeners != null) {
            this.accessibleActionListeners.remove(listener);
            if (this.accessibleActionListeners.isEmpty()) {
                this.accessibleActionListeners = null;
            }
        }
    }
    
    public void removeAccessibleEditableTextListener(final AccessibleEditableTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleEditableTextListeners != null) {
            this.accessibleEditableTextListeners.remove(listener);
            if (this.accessibleEditableTextListeners.isEmpty()) {
                this.accessibleEditableTextListeners = null;
            }
        }
    }
    
    public void removeAccessibleHyperlinkListener(final AccessibleHyperlinkListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleHyperlinkListeners != null) {
            this.accessibleHyperlinkListeners.remove(listener);
            if (this.accessibleHyperlinkListeners.isEmpty()) {
                this.accessibleHyperlinkListeners = null;
            }
        }
    }
    
    public void removeAccessibleTableListener(final AccessibleTableListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleTableListeners != null) {
            this.accessibleTableListeners.remove(listener);
            if (this.accessibleTableListeners.isEmpty()) {
                this.accessibleTableListeners = null;
            }
        }
    }
    
    public void removeAccessibleTableCellListener(final AccessibleTableCellListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleTableCellListeners != null) {
            this.accessibleTableCellListeners.remove(listener);
            if (this.accessibleTableCellListeners.isEmpty()) {
                this.accessibleTableCellListeners = null;
            }
        }
    }
    
    public void removeAccessibleValueListener(final AccessibleValueListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleValueListeners != null) {
            this.accessibleValueListeners.remove(listener);
            if (this.accessibleValueListeners.isEmpty()) {
                this.accessibleValueListeners = null;
            }
        }
    }
    
    public void removeAccessibleAttributeListener(final AccessibleAttributeListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        if (this.accessibleAttributeListeners != null) {
            this.accessibleAttributeListeners.remove(listener);
            if (this.accessibleAttributeListeners.isEmpty()) {
                this.accessibleAttributeListeners = null;
            }
        }
    }
    
    public void removeRelation(final int type, final Accessible target) {
        this.checkWidget();
        if (target == null) {
            SWT.error(4);
        }
        final Relation relation = this.relations[type];
        if (relation != null) {
            relation.removeTarget(target);
            if (!relation.hasTargets()) {
                this.relations[type].Release();
                this.relations[type] = null;
            }
        }
    }
    
    public void sendEvent(final int event, final Object eventData) {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        if (!Accessible.UseIA2) {
            return;
        }
        switch (event) {
            case 518: {
                if (!(eventData instanceof int[])) {
                    break;
                }
                if (((int[])eventData).length != 5) {
                    break;
                }
                this.tableChange = (int[])eventData;
                OS.NotifyWinEvent(278, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 524: {
                if (!(eventData instanceof Object[])) {
                    break;
                }
                if (((Object[])eventData).length != 4) {
                    break;
                }
                final Object[] data = (Object[])eventData;
                final int type = (int)data[0];
                switch (type) {
                    case 1: {
                        this.textDeleted = (Object[])eventData;
                        OS.NotifyWinEvent(287, this.control.handle, -4, this.eventChildID());
                        break;
                    }
                    case 0: {
                        this.textInserted = (Object[])eventData;
                        OS.NotifyWinEvent(286, this.control.handle, -4, this.eventChildID());
                        break;
                    }
                }
                break;
            }
            case 268: {
                if (!(eventData instanceof Integer)) {
                    break;
                }
                OS.NotifyWinEvent(268, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32782: {
                OS.NotifyWinEvent(32782, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32778: {
                OS.NotifyWinEvent(32778, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32777: {
                OS.NotifyWinEvent(32777, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32788: {
                OS.NotifyWinEvent(32788, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32779: {
                OS.NotifyWinEvent(32779, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32780: {
                OS.NotifyWinEvent(32780, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32781: {
                OS.NotifyWinEvent(32781, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 261: {
                OS.NotifyWinEvent(261, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 262: {
                OS.NotifyWinEvent(262, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 263: {
                OS.NotifyWinEvent(263, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 273: {
                OS.NotifyWinEvent(273, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 274: {
                OS.NotifyWinEvent(274, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 256: {
                OS.NotifyWinEvent(257, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 269: {
                OS.NotifyWinEvent(269, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 264: {
                OS.NotifyWinEvent(264, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 265: {
                OS.NotifyWinEvent(265, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 266: {
                OS.NotifyWinEvent(266, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 267: {
                OS.NotifyWinEvent(267, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 271: {
                OS.NotifyWinEvent(271, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 512: {
                OS.NotifyWinEvent(272, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 515: {
                OS.NotifyWinEvent(275, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 516: {
                OS.NotifyWinEvent(276, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 517: {
                OS.NotifyWinEvent(277, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 519: {
                OS.NotifyWinEvent(279, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 520: {
                OS.NotifyWinEvent(280, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 521: {
                OS.NotifyWinEvent(281, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 522: {
                OS.NotifyWinEvent(282, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 283: {
                OS.NotifyWinEvent(283, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 285: {
                OS.NotifyWinEvent(285, this.control.handle, -4, this.eventChildID());
                break;
            }
        }
    }
    
    public void sendEvent(final int event, final Object eventData, final int childID) {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        if (!Accessible.UseIA2) {
            return;
        }
        final int osChildID = (childID == -1) ? this.eventChildID() : this.childIDToOs(childID);
        switch (event) {
            case 32778: {
                OS.NotifyWinEvent(32778, this.control.handle, -4, osChildID);
                break;
            }
            case 32780: {
                OS.NotifyWinEvent(32780, this.control.handle, -4, osChildID);
                break;
            }
            case 32782: {
                OS.NotifyWinEvent(32782, this.control.handle, -4, osChildID);
                break;
            }
            case 32779: {
                OS.NotifyWinEvent(32779, this.control.handle, -4, osChildID);
                break;
            }
            case 32777: {
                OS.NotifyWinEvent(32777, this.control.handle, -4, osChildID);
                break;
            }
            case 32788: {
                OS.NotifyWinEvent(32788, this.control.handle, -4, osChildID);
                break;
            }
            case 32781: {
                OS.NotifyWinEvent(32781, this.control.handle, -4, osChildID);
                break;
            }
        }
    }
    
    public void selectionChanged() {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        OS.NotifyWinEvent(32777, this.control.handle, -4, this.eventChildID());
    }
    
    public void setFocus(final int childID) {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        final int osChildID = (childID == -1) ? this.eventChildID() : this.childIDToOs(childID);
        OS.NotifyWinEvent(32773, this.control.handle, -4, osChildID);
    }
    
    public void textCaretMoved(final int index) {
        class lIlIl implements Runnable
        {
            final /* synthetic */ Accessible this$0;
            
            lIlIl(final Accessible this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                if (!this.this$0.isATRunning()) {
                    return;
                }
                OS.NotifyWinEvent(32779, this.this$0.control.handle, -8, this.this$0.eventChildID());
                if (!Accessible.UseIA2) {
                    return;
                }
                OS.NotifyWinEvent(283, this.this$0.control.handle, -4, this.this$0.eventChildID());
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/eclipse/swt/accessibility/Accessible.checkWidget:()V
        //     4: aload_0         /* this */
        //     5: getfield        org/eclipse/swt/accessibility/Accessible.timer:Ljava/lang/Runnable;
        //     8: ifnonnull       23
        //    11: aload_0         /* this */
        //    12: new             Lorg/eclipse/swt/accessibility/lIlIl;
        //    15: dup            
        //    16: aload_0         /* this */
        //    17: invokespecial   org/eclipse/swt/accessibility/lIlIl.<init>:(Lorg/eclipse/swt/accessibility/Accessible;)V
        //    20: putfield        org/eclipse/swt/accessibility/Accessible.timer:Ljava/lang/Runnable;
        //    23: aload_0         /* this */
        //    24: getfield        org/eclipse/swt/accessibility/Accessible.control:Lorg/eclipse/swt/widgets/Control;
        //    27: invokevirtual   org/eclipse/swt/widgets/Control.getDisplay:()Lorg/eclipse/swt/widgets/Display;
        //    30: bipush          100
        //    32: aload_0         /* this */
        //    33: getfield        org/eclipse/swt/accessibility/Accessible.timer:Ljava/lang/Runnable;
        //    36: invokevirtual   org/eclipse/swt/widgets/Display.timerExec:(ILjava/lang/Runnable;)V
        //    39: return         
        //    StackMapTable: 00 01 17
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void textChanged(final int type, final int startIndex, final int length) {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = startIndex;
        event.end = startIndex + length;
        event.count = 0;
        event.type = 5;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getText(event);
        }
        if (event.result != null) {
            final Object[] eventData = { type, startIndex, startIndex + length, event.result };
            this.sendEvent(524, eventData);
            return;
        }
        OS.NotifyWinEvent(32782, this.control.handle, -4, this.eventChildID());
    }
    
    public void textSelectionChanged() {
        this.checkWidget();
        if (!this.isATRunning()) {
            return;
        }
        OS.NotifyWinEvent(32788, this.control.handle, -4, this.eventChildID());
    }
    
    int QueryInterface(final long iid, final long ppvObject) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        final GUID guid = new GUID();
        COM.MoveMemory(guid, iid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDispatch) || COM.IsEqualGUID(guid, COM.IIDIAccessible)) {
            if (this.objIAccessible == null) {
                this.createIAccessible();
            }
            OS.MoveMemory(ppvObject, new long[] { this.objIAccessible.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIEnumVARIANT)) {
            if (this.objIEnumVARIANT == null) {
                this.createIEnumVARIANT();
            }
            OS.MoveMemory(ppvObject, new long[] { this.objIEnumVARIANT.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return this.enumIndex = 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIServiceProvider)) {
            if (!Accessible.UseIA2) {
                return -2147467262;
            }
            if (this.accessibleActionListenersSize() > 0 || this.accessibleAttributeListenersSize() > 0 || this.accessibleHyperlinkListenersSize() > 0 || this.accessibleTableListenersSize() > 0 || this.accessibleTableCellListenersSize() > 0 || this.accessibleTextExtendedListenersSize() > 0 || this.accessibleValueListenersSize() > 0 || this.accessibleControlListenersSize() > 0 || this.getRelationCount() > 0 || (this.control instanceof Button && (this.control.getStyle() & 0x10) != 0x0) || this.control instanceof Composite) {
                if (this.objIServiceProvider == null) {
                    this.createIServiceProvider();
                }
                OS.MoveMemory(ppvObject, new long[] { this.objIServiceProvider.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else {
            int code = this.queryAccessible2Interfaces(guid, ppvObject);
            if (code != 1) {
                return code;
            }
            if (this.iaccessible != null) {
                final long[] ppv = { 0L };
                code = this.iaccessible.QueryInterface(guid, ppv);
                OS.MoveMemory(ppvObject, ppv, C.PTR_SIZEOF);
                return code;
            }
            return -2147467262;
        }
    }
    
    int accessibleListenersSize() {
        return (this.accessibleListeners == null) ? 0 : this.accessibleListeners.size();
    }
    
    int accessibleControlListenersSize() {
        return (this.accessibleControlListeners == null) ? 0 : this.accessibleControlListeners.size();
    }
    
    int accessibleValueListenersSize() {
        return (this.accessibleValueListeners == null) ? 0 : this.accessibleValueListeners.size();
    }
    
    int accessibleTextExtendedListenersSize() {
        return (this.accessibleTextExtendedListeners == null) ? 0 : this.accessibleTextExtendedListeners.size();
    }
    
    int accessibleTextListenersSize() {
        return (this.accessibleTextListeners == null) ? 0 : this.accessibleTextListeners.size();
    }
    
    int accessibleTableCellListenersSize() {
        return (this.accessibleTableCellListeners == null) ? 0 : this.accessibleTableCellListeners.size();
    }
    
    int accessibleTableListenersSize() {
        return (this.accessibleTableListeners == null) ? 0 : this.accessibleTableListeners.size();
    }
    
    int accessibleHyperlinkListenersSize() {
        return (this.accessibleHyperlinkListeners == null) ? 0 : this.accessibleHyperlinkListeners.size();
    }
    
    int accessibleEditableTextListenersSize() {
        return (this.accessibleEditableTextListeners == null) ? 0 : this.accessibleEditableTextListeners.size();
    }
    
    int accessibleAttributeListenersSize() {
        return (this.accessibleAttributeListeners == null) ? 0 : this.accessibleAttributeListeners.size();
    }
    
    int accessibleActionListenersSize() {
        return (this.accessibleActionListeners == null) ? 0 : this.accessibleActionListeners.size();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            if (this.objIAccessible != null) {
                this.objIAccessible.dispose();
            }
            this.objIAccessible = null;
            if (this.objIEnumVARIANT != null) {
                this.objIEnumVARIANT.dispose();
            }
            this.objIEnumVARIANT = null;
            if (this.objIServiceProvider != null) {
                this.objIServiceProvider.dispose();
            }
            this.objIServiceProvider = null;
            if (this.objIAccessibleApplication != null) {
                this.objIAccessibleApplication.dispose();
            }
            this.objIAccessibleApplication = null;
            if (this.objIAccessibleEditableText != null) {
                this.objIAccessibleEditableText.dispose();
            }
            this.objIAccessibleEditableText = null;
            if (this.objIAccessibleHyperlink != null) {
                this.objIAccessibleHyperlink.dispose();
            }
            this.objIAccessibleHyperlink = null;
            if (this.objIAccessibleHypertext != null) {
                this.objIAccessibleHypertext.dispose();
            }
            this.objIAccessibleHypertext = null;
            if (this.objIAccessibleTable2 != null) {
                this.objIAccessibleTable2.dispose();
            }
            this.objIAccessibleTable2 = null;
            if (this.objIAccessibleTableCell != null) {
                this.objIAccessibleTableCell.dispose();
            }
            this.objIAccessibleTableCell = null;
            if (this.objIAccessibleValue != null) {
                this.objIAccessibleValue.dispose();
            }
            this.objIAccessibleValue = null;
            for (final Relation relation : this.relations) {
                if (relation != null) {
                    relation.Release();
                }
            }
        }
        return this.refCount;
    }
    
    int QueryService(final long guidService, final long riid, final long ppvObject) {
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        final GUID service = new GUID();
        COM.MoveMemory(service, guidService, GUID.sizeof);
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(service, COM.IIDIAccessible)) {
            if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIDispatch) || COM.IsEqualGUID(guid, COM.IIDIAccessible)) {
                if (this.objIAccessible == null) {
                    this.createIAccessible();
                }
                OS.MoveMemory(ppvObject, new long[] { this.objIAccessible.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            final int code = this.queryAccessible2Interfaces(guid, ppvObject);
            if (code != 1) {
                return code;
            }
        }
        if (COM.IsEqualGUID(service, COM.IIDIAccessible2)) {
            final int code = this.queryAccessible2Interfaces(guid, ppvObject);
            if (code != 1) {
                return code;
            }
        }
        if (this.iaccessible != null) {
            final long[] ppv = { 0L };
            int code2 = this.iaccessible.QueryInterface(COM.IIDIServiceProvider, ppv);
            if (code2 == 0) {
                final IServiceProvider iserviceProvider = new IServiceProvider(ppv[0]);
                final long[] ppvx = { 0L };
                code2 = iserviceProvider.QueryService(service, guid, ppvx);
                OS.MoveMemory(ppvObject, ppvx, C.PTR_SIZEOF);
                return code2;
            }
        }
        return -2147467262;
    }
    
    int queryAccessible2Interfaces(final GUID guid, final long ppvObject) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAccessible2)) {
            if (this.accessibleActionListenersSize() > 0 || this.accessibleAttributeListenersSize() > 0 || this.accessibleHyperlinkListenersSize() > 0 || this.accessibleTableListenersSize() > 0 || this.accessibleTableCellListenersSize() > 0 || this.accessibleTextExtendedListenersSize() > 0 || this.accessibleValueListenersSize() > 0 || this.accessibleControlListenersSize() > 0 || this.getRelationCount() > 0 || (this.control instanceof Button && (this.control.getStyle() & 0x10) != 0x0) || this.control instanceof Composite) {
                if (this.objIAccessible == null) {
                    this.createIAccessible();
                }
                OS.MoveMemory(ppvObject, new long[] { this.objIAccessible.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleAction)) {
            if (this.accessibleActionListenersSize() > 0) {
                if (this.objIAccessibleHyperlink == null) {
                    this.createIAccessibleHyperlink();
                }
                OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleHyperlink.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else {
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleApplication)) {
                if (this.objIAccessibleApplication == null) {
                    this.createIAccessibleApplication();
                }
                OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleApplication.getAddress() }, C.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleComponent)) {
                return -2147467262;
            }
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleEditableText)) {
                if (this.accessibleEditableTextListenersSize() > 0) {
                    if (this.objIAccessibleEditableText == null) {
                        this.createIAccessibleEditableText();
                    }
                    OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleEditableText.getAddress() }, C.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleHyperlink)) {
                if (this.accessibleHyperlinkListenersSize() > 0) {
                    if (this.objIAccessibleHyperlink == null) {
                        this.createIAccessibleHyperlink();
                    }
                    OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleHyperlink.getAddress() }, C.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleHypertext)) {
                if (this.accessibleTextExtendedListenersSize() > 0) {
                    if (this.objIAccessibleHypertext == null) {
                        this.createIAccessibleHypertext();
                    }
                    OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleHypertext.getAddress() }, C.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else {
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleImage)) {
                    return -2147467262;
                }
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTable)) {
                    return -2147467262;
                }
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTable2)) {
                    if (this.accessibleTableListenersSize() > 0) {
                        if (this.objIAccessibleTable2 == null) {
                            this.createIAccessibleTable2();
                        }
                        OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleTable2.getAddress() }, C.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTableCell)) {
                    if (this.accessibleTableCellListenersSize() > 0) {
                        if (this.objIAccessibleTableCell == null) {
                            this.createIAccessibleTableCell();
                        }
                        OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleTableCell.getAddress() }, C.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleText)) {
                    if (this.accessibleTextExtendedListenersSize() > 0 || this.accessibleAttributeListenersSize() > 0) {
                        if (this.objIAccessibleHypertext == null) {
                            this.createIAccessibleHypertext();
                        }
                        OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleHypertext.getAddress() }, C.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else {
                    if (!COM.IsEqualGUID(guid, COM.IIDIAccessibleValue)) {
                        return 1;
                    }
                    if (this.accessibleValueListenersSize() > 0) {
                        if (this.objIAccessibleValue == null) {
                            this.createIAccessibleValue();
                        }
                        OS.MoveMemory(ppvObject, new long[] { this.objIAccessibleValue.getAddress() }, C.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
            }
        }
    }
    
    int accDoDefaultAction(final long varChild) {
        if (this.accessibleActionListenersSize() > 0) {
            final VARIANT v = this.getVARIANT(varChild);
            if (v.vt != 3) {
                return -2147024809;
            }
            if (v.lVal == 0) {
                return this.doAction(0);
            }
        }
        int code = -2147352573;
        if (this.iaccessible != null) {
            code = this.iaccessible.accDoDefaultAction(varChild);
            if (code == -2147024809) {
                code = -2147352573;
            }
        }
        return code;
    }
    
    int accHitTest(final int xLeft, final int yTop, final long pvarChild) {
        int osChild = -2;
        long osChildObject = 0L;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.accHitTest(xLeft, yTop, pvarChild);
            if (code == 0) {
                final VARIANT v = this.getVARIANT(pvarChild);
                if (v.vt == 3) {
                    osChild = v.lVal;
                }
                else if (v.vt == 9) {
                    osChildObject = v.lVal;
                }
            }
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = ((osChild == -2) ? -2 : this.osToChildID(osChild));
        event.x = xLeft;
        event.y = yTop;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getChildAtPoint(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(pvarChild, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = event.childID;
        if (childID != -2) {
            this.setIntVARIANT(pvarChild, (short)3, this.childIDToOs(childID));
            return 0;
        }
        if (osChildObject != 0L) {
            return 0;
        }
        this.setIntVARIANT(pvarChild, (short)0, 0);
        return 1;
    }
    
    int accLocation(final long pxLeft, final long pyTop, final long pcxWidth, final long pcyHeight, final long varChild) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int osLeft = 0;
        int osTop = 0;
        int osWidth = 0;
        int osHeight = 0;
        if (this.iaccessible != null) {
            int code = this.iaccessible.accLocation(pxLeft, pyTop, pcxWidth, pcyHeight, varChild);
            if (code == -2147024809) {
                code = -2147352573;
            }
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
            if (code == 0) {
                final int[] pLeft = { 0 };
                final int[] pTop = { 0 };
                final int[] pWidth = { 0 };
                final int[] pHeight = { 0 };
                OS.MoveMemory(pLeft, pxLeft, 4);
                OS.MoveMemory(pTop, pyTop, 4);
                OS.MoveMemory(pWidth, pcxWidth, 4);
                OS.MoveMemory(pHeight, pcyHeight, 4);
                osLeft = pLeft[0];
                osTop = pTop[0];
                osWidth = pWidth[0];
                osHeight = pHeight[0];
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.x = osLeft;
        event.y = osTop;
        event.width = osWidth;
        event.height = osHeight;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getLocation(event);
        }
        OS.MoveMemory(pxLeft, new int[] { event.x }, 4);
        OS.MoveMemory(pyTop, new int[] { event.y }, 4);
        OS.MoveMemory(pcxWidth, new int[] { event.width }, 4);
        OS.MoveMemory(pcyHeight, new int[] { event.height }, 4);
        return 0;
    }
    
    int accNavigate(final int navDir, final long varStart, final long pvarEndUpAt) {
        int code = -2147352573;
        if (this.iaccessible != null) {
            code = this.iaccessible.accNavigate(navDir, varStart, pvarEndUpAt);
            if (code == -2147024809) {
                code = -2147352573;
            }
        }
        return code;
    }
    
    int accSelect(final int flagsSelect, final long varChild) {
        int code = -2147352573;
        if (this.iaccessible != null) {
            code = this.iaccessible.accSelect(flagsSelect, varChild);
            if (code == -2147024809) {
                code = -2147352573;
            }
        }
        return code;
    }
    
    int get_accChild(final long varChild, final long ppdispChild) {
        class lIIIl extends AccessibleAdapter
        {
            final /* synthetic */ int val$childID;
            final /* synthetic */ Accessible this$0;
            
            lIIIl(final Accessible this$0, final int val$childID) {
                this.this$0 = this$0;
                this.val$childID = val$childID;
            }
            
            @Override
            public void getName(final AccessibleEvent e) {
                if (e.childID == -1) {
                    final AccessibleEvent event = new AccessibleEvent(this.this$0);
                    event.childID = this.val$childID;
                    for (int i = 0; i < this.this$0.accessibleListenersSize(); ++i) {
                        final AccessibleListener listener = this.this$0.accessibleListeners.get(i);
                        listener.getName(event);
                    }
                    e.result = event.result;
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: invokevirtual   org/eclipse/swt/accessibility/Accessible.getVARIANT:(J)Lorg/eclipse/swt/internal/ole/win32/VARIANT;
        //     5: astore          v
        //     7: aload           v
        //     9: getfield        org/eclipse/swt/internal/ole/win32/VARIANT.vt:S
        //    12: iconst_3       
        //    13: if_icmpeq       20
        //    16: ldc_w           -2147024809
        //    19: ireturn        
        //    20: aload           v
        //    22: getfield        org/eclipse/swt/internal/ole/win32/VARIANT.lVal:I
        //    25: ifne            52
        //    28: aload_0         /* this */
        //    29: invokevirtual   org/eclipse/swt/accessibility/Accessible.AddRef:()I
        //    32: pop            
        //    33: lload_3         /* ppdispChild */
        //    34: iconst_1       
        //    35: newarray        J
        //    37: dup            
        //    38: iconst_0       
        //    39: aload_0         /* this */
        //    40: invokevirtual   org/eclipse/swt/accessibility/Accessible.getAddress:()J
        //    43: lastore        
        //    44: getstatic       org/eclipse/swt/internal/C.PTR_SIZEOF:I
        //    47: invokestatic    org/eclipse/swt/internal/win32/OS.MoveMemory:(J[JI)V
        //    50: iconst_0       
        //    51: ireturn        
        //    52: aload_0         /* this */
        //    53: aload           v
        //    55: getfield        org/eclipse/swt/internal/ole/win32/VARIANT.lVal:I
        //    58: invokevirtual   org/eclipse/swt/accessibility/Accessible.osToChildID:(I)I
        //    61: istore          childID
        //    63: iconst_1       
        //    64: istore          code
        //    66: aconst_null    
        //    67: astore          osAccessible
        //    69: aload_0         /* this */
        //    70: getfield        org/eclipse/swt/accessibility/Accessible.iaccessible:Lorg/eclipse/swt/internal/ole/win32/IAccessible;
        //    73: ifnull          286
        //    76: aload_0         /* this */
        //    77: getfield        org/eclipse/swt/accessibility/Accessible.iaccessible:Lorg/eclipse/swt/internal/ole/win32/IAccessible;
        //    80: lload_1         /* varChild */
        //    81: lload_3         /* ppdispChild */
        //    82: invokevirtual   org/eclipse/swt/internal/ole/win32/IAccessible.get_accChild:(JJ)I
        //    85: istore          code
        //    87: iload           code
        //    89: ldc_w           -2147024809
        //    92: if_icmpne       98
        //    95: iconst_1       
        //    96: istore          code
        //    98: iload           code
        //   100: ifne            286
        //   103: aload_0         /* this */
        //   104: getfield        org/eclipse/swt/accessibility/Accessible.control:Lorg/eclipse/swt/widgets/Control;
        //   107: instanceof      Lorg/eclipse/swt/widgets/ToolBar;
        //   110: ifeq            286
        //   113: aload_0         /* this */
        //   114: getfield        org/eclipse/swt/accessibility/Accessible.control:Lorg/eclipse/swt/widgets/Control;
        //   117: checkcast       Lorg/eclipse/swt/widgets/ToolBar;
        //   120: astore          toolBar
        //   122: aload           toolBar
        //   124: iload           childID
        //   126: invokevirtual   org/eclipse/swt/widgets/ToolBar.getItem:(I)Lorg/eclipse/swt/widgets/ToolItem;
        //   129: astore          item
        //   131: aload           item
        //   133: ifnull          286
        //   136: aload           item
        //   138: invokevirtual   org/eclipse/swt/widgets/ToolItem.getStyle:()I
        //   141: iconst_4       
        //   142: iand           
        //   143: ifeq            286
        //   146: iconst_1       
        //   147: newarray        J
        //   149: dup            
        //   150: iconst_0       
        //   151: lconst_0       
        //   152: lastore        
        //   153: astore          addr
        //   155: aload           addr
        //   157: lload_3         /* ppdispChild */
        //   158: getstatic       org/eclipse/swt/internal/C.PTR_SIZEOF:I
        //   161: invokestatic    org/eclipse/swt/internal/win32/OS.MoveMemory:([JJI)V
        //   164: iconst_0       
        //   165: istore          found
        //   167: aload_0         /* this */
        //   168: getfield        org/eclipse/swt/accessibility/Accessible.children:Ljava/util/List;
        //   171: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   176: astore          13
        //   178: aload           13
        //   180: invokeinterface java/util/Iterator.hasNext:()Z
        //   185: ifeq            230
        //   188: aload           13
        //   190: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   195: checkcast       Lorg/eclipse/swt/accessibility/Accessible;
        //   198: astore          accChild
        //   200: aload           accChild
        //   202: getfield        org/eclipse/swt/accessibility/Accessible.item:Lorg/eclipse/swt/widgets/ToolItem;
        //   205: aload           item
        //   207: if_acmpne       227
        //   210: aload           accChild
        //   212: invokevirtual   org/eclipse/swt/accessibility/Accessible.dispose:()V
        //   215: aload           accChild
        //   217: aconst_null    
        //   218: putfield        org/eclipse/swt/accessibility/Accessible.item:Lorg/eclipse/swt/widgets/ToolItem;
        //   221: iconst_1       
        //   222: istore          found
        //   224: goto            230
        //   227: goto            178
        //   230: new             Lorg/eclipse/swt/accessibility/Accessible;
        //   233: dup            
        //   234: aload_0         /* this */
        //   235: aload           addr
        //   237: iconst_0       
        //   238: laload         
        //   239: invokespecial   org/eclipse/swt/accessibility/Accessible.<init>:(Lorg/eclipse/swt/accessibility/Accessible;J)V
        //   242: astore          osAccessible
        //   244: aload           osAccessible
        //   246: aload           item
        //   248: putfield        org/eclipse/swt/accessibility/Accessible.item:Lorg/eclipse/swt/widgets/ToolItem;
        //   251: iload           found
        //   253: ifne            271
        //   256: aload           item
        //   258: bipush          12
        //   260: aload_0         /* this */
        //   261: aload           item
        //   263: invokedynamic   BootstrapMethod #0, handleEvent:(Lorg/eclipse/swt/accessibility/Accessible;Lorg/eclipse/swt/widgets/ToolItem;)Lorg/eclipse/swt/widgets/Listener;
        //   268: invokevirtual   org/eclipse/swt/widgets/ToolItem.addListener:(ILorg/eclipse/swt/widgets/Listener;)V
        //   271: aload           osAccessible
        //   273: new             Lorg/eclipse/swt/accessibility/lIIIl;
        //   276: dup            
        //   277: aload_0         /* this */
        //   278: iload           childID
        //   280: invokespecial   org/eclipse/swt/accessibility/lIIIl.<init>:(Lorg/eclipse/swt/accessibility/Accessible;I)V
        //   283: invokevirtual   org/eclipse/swt/accessibility/Accessible.addAccessibleListener:(Lorg/eclipse/swt/accessibility/AccessibleListener;)V
        //   286: new             Lorg/eclipse/swt/accessibility/AccessibleControlEvent;
        //   289: dup            
        //   290: aload_0         /* this */
        //   291: invokespecial   org/eclipse/swt/accessibility/AccessibleControlEvent.<init>:(Ljava/lang/Object;)V
        //   294: astore          event
        //   296: aload           event
        //   298: iload           childID
        //   300: putfield        org/eclipse/swt/accessibility/AccessibleControlEvent.childID:I
        //   303: iconst_0       
        //   304: istore          i
        //   306: iload           i
        //   308: aload_0         /* this */
        //   309: invokevirtual   org/eclipse/swt/accessibility/Accessible.accessibleControlListenersSize:()I
        //   312: if_icmpge       346
        //   315: aload_0         /* this */
        //   316: getfield        org/eclipse/swt/accessibility/Accessible.accessibleControlListeners:Ljava/util/List;
        //   319: iload           i
        //   321: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   326: checkcast       Lorg/eclipse/swt/accessibility/AccessibleControlListener;
        //   329: astore          listener
        //   331: aload           listener
        //   333: aload           event
        //   335: invokeinterface org/eclipse/swt/accessibility/AccessibleControlListener.getChild:(Lorg/eclipse/swt/accessibility/AccessibleControlEvent;)V
        //   340: iinc            i, 1
        //   343: goto            306
        //   346: aload           event
        //   348: getfield        org/eclipse/swt/accessibility/AccessibleControlEvent.accessible:Lorg/eclipse/swt/accessibility/Accessible;
        //   351: astore          accessible
        //   353: aload           accessible
        //   355: ifnonnull       362
        //   358: aload           osAccessible
        //   360: astore          accessible
        //   362: aload           accessible
        //   364: ifnull          393
        //   367: aload           accessible
        //   369: invokevirtual   org/eclipse/swt/accessibility/Accessible.AddRef:()I
        //   372: pop            
        //   373: lload_3         /* ppdispChild */
        //   374: iconst_1       
        //   375: newarray        J
        //   377: dup            
        //   378: iconst_0       
        //   379: aload           accessible
        //   381: invokevirtual   org/eclipse/swt/accessibility/Accessible.getAddress:()J
        //   384: lastore        
        //   385: getstatic       org/eclipse/swt/internal/C.PTR_SIZEOF:I
        //   388: invokestatic    org/eclipse/swt/internal/win32/OS.MoveMemory:(J[JI)V
        //   391: iconst_0       
        //   392: ireturn        
        //   393: iload           code
        //   395: ireturn        
        //    StackMapTable: 00 0C FC 00 14 07 02 92 1F FE 00 2D 01 01 05 FF 00 4F 00 0C 07 00 02 04 04 07 02 92 01 01 05 07 02 FD 07 03 03 07 00 A8 01 07 01 4E 00 00 FC 00 30 07 00 02 FA 00 02 FF 00 28 00 0C 07 00 02 04 04 07 02 92 01 01 07 00 02 07 02 FD 07 03 03 07 00 A8 01 07 01 4E 00 00 FF 00 0E 00 07 07 00 02 04 04 07 02 92 01 01 07 00 02 00 00 FD 00 13 07 02 A9 01 27 FF 00 0F 00 09 07 00 02 04 04 07 02 92 01 01 07 00 02 07 02 A9 07 00 02 00 00 1E
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    int get_accChildCount(final long pcountChildren) {
        int osChildCount = 0;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.get_accChildCount(pcountChildren);
            if (code == 0) {
                final int[] pChildCount = { 0 };
                OS.MoveMemory(pChildCount, pcountChildren, 4);
                osChildCount = pChildCount[0];
            }
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = -1;
        event.detail = osChildCount;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getChildCount(event);
        }
        OS.MoveMemory(pcountChildren, new int[] { event.detail }, 4);
        return 0;
    }
    
    int get_accDefaultAction(final long varChild, final long pszDefaultAction) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        String osDefaultAction = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accDefaultAction(varChild, pszDefaultAction);
            if (code == -2147024809) {
                code = 1;
            }
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
            if (code == 0) {
                final long[] pDefaultAction = { 0L };
                OS.MoveMemory(pDefaultAction, pszDefaultAction, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pDefaultAction[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pDefaultAction[0], size);
                    osDefaultAction = new String(buffer);
                }
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osDefaultAction;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getDefaultAction(event);
        }
        if ((event.result == null || event.result.length() == 0) && v.lVal == 0) {
            code = this.get_name(0, pszDefaultAction);
        }
        if (event.result == null) {
            return code;
        }
        if (event.result.length() == 0) {
            return 1;
        }
        this.setString(pszDefaultAction, event.result);
        return 0;
    }
    
    int get_accDescription(final long varChild, final long pszDescription) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        String osDescription = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accDescription(varChild, pszDescription);
            if (code == -2147024809) {
                code = 1;
            }
            if (this.accessibleListenersSize() == 0 && !(this.control instanceof Tree)) {
                return code;
            }
            if (code == 0) {
                final long[] pDescription = { 0L };
                OS.MoveMemory(pDescription, pszDescription, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pDescription[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pDescription[0], size);
                    osDescription = new String(buffer);
                }
            }
        }
        final AccessibleEvent event = new AccessibleEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osDescription;
        if (v.lVal != 0 && this.control instanceof Tree) {
            final Tree tree = (Tree)this.control;
            final int columnCount = tree.getColumnCount();
            if (columnCount > 1) {
                final long hwnd = this.control.handle;
                long hItem = 0L;
                hItem = OS.SendMessage(hwnd, 4394, v.lVal, 0L);
                final Widget widget = tree.getDisplay().findWidget(hwnd, hItem);
                event.result = "";
                if (widget instanceof TreeItem) {
                    final TreeItem item = (TreeItem)widget;
                    for (int i = 1; i < columnCount; ++i) {
                        if (tree.isDisposed() || item.isDisposed()) {
                            event.result = "";
                            return 0;
                        }
                        final AccessibleEvent accessibleEvent = event;
                        accessibleEvent.result = accessibleEvent.result + tree.getColumn(i).getText() + ": " + item.getText(i);
                        if (i + 1 < columnCount) {
                            final AccessibleEvent accessibleEvent2 = event;
                            final StringBuilder sb = new StringBuilder();
                            final AccessibleEvent accessibleEvent3 = accessibleEvent2;
                            accessibleEvent3.result = sb.append(accessibleEvent3.result).append(", ").toString();
                        }
                    }
                }
            }
        }
        for (int j = 0; j < this.accessibleListenersSize(); ++j) {
            final AccessibleListener listener = this.accessibleListeners.get(j);
            listener.getDescription(event);
        }
        if (event.result == null) {
            return code;
        }
        if (event.result.length() == 0) {
            return 1;
        }
        this.setString(pszDescription, event.result);
        return 0;
    }
    
    int get_accFocus(final long pvarChild) {
        int osChild = -2;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.get_accFocus(pvarChild);
            if (code == 0) {
                final VARIANT v = this.getVARIANT(pvarChild);
                if (v.vt == 3) {
                    osChild = v.lVal;
                }
            }
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = ((osChild == -2) ? -2 : this.osToChildID(osChild));
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getFocus(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(pvarChild, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = event.childID;
        if (childID == -2) {
            this.setIntVARIANT(pvarChild, (short)0, 0);
            return 1;
        }
        if (childID == -1) {
            this.AddRef();
            this.setIntVARIANT(pvarChild, (short)3, 0);
            return 0;
        }
        this.setIntVARIANT(pvarChild, (short)3, this.childIDToOs(childID));
        return 0;
    }
    
    int get_accHelp(final long varChild, final long pszHelp) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        String osHelp = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accHelp(varChild, pszHelp);
            if (code == -2147024809) {
                code = 1;
            }
            if (this.accessibleListenersSize() == 0) {
                return code;
            }
            if (code == 0) {
                final long[] pHelp = { 0L };
                OS.MoveMemory(pHelp, pszHelp, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pHelp[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pHelp[0], size);
                    osHelp = new String(buffer);
                }
            }
        }
        final AccessibleEvent event = new AccessibleEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osHelp;
        for (int i = 0; i < this.accessibleListenersSize(); ++i) {
            final AccessibleListener listener = this.accessibleListeners.get(i);
            listener.getHelp(event);
        }
        if (event.result == null) {
            return code;
        }
        if (event.result.length() == 0) {
            return 1;
        }
        this.setString(pszHelp, event.result);
        return 0;
    }
    
    int get_accHelpTopic(final long pszHelpFile, final long varChild, final long pidTopic) {
        int code = -2147352573;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accHelpTopic(pszHelpFile, varChild, pidTopic);
            if (code == -2147024809) {
                code = -2147352573;
            }
        }
        return code;
    }
    
    int get_accKeyboardShortcut(final long varChild, final long pszKeyboardShortcut) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        String osKeyboardShortcut = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accKeyboardShortcut(varChild, pszKeyboardShortcut);
            if (code == -2147024809) {
                code = 1;
            }
            if (this.accessibleListenersSize() == 0 && !(this.control instanceof TabFolder)) {
                return code;
            }
            if (code == 0) {
                final long[] pKeyboardShortcut = { 0L };
                OS.MoveMemory(pKeyboardShortcut, pszKeyboardShortcut, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pKeyboardShortcut[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pKeyboardShortcut[0], size);
                    osKeyboardShortcut = new String(buffer);
                }
            }
        }
        final AccessibleEvent event = new AccessibleEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osKeyboardShortcut;
        if (v.lVal == 0 && this.control instanceof TabFolder) {
            event.result = SWT.getMessage("SWT_SwitchPage_Shortcut");
        }
        for (int i = 0; i < this.accessibleListenersSize(); ++i) {
            final AccessibleListener listener = this.accessibleListeners.get(i);
            listener.getKeyboardShortcut(event);
        }
        if (event.result == null) {
            return code;
        }
        if (event.result.length() == 0) {
            return 1;
        }
        this.setString(pszKeyboardShortcut, event.result);
        return 0;
    }
    
    int get_accName(final long varChild, final long pszName) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = 1;
        String osName = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accName(varChild, pszName);
            if (code == 0) {
                final long[] pName = { 0L };
                OS.MoveMemory(pName, pszName, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pName[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pName[0], size);
                    osName = new String(buffer);
                }
            }
            if (code == -2147024809) {
                code = 1;
            }
            if (this.accessibleListenersSize() == 0 && !(this.control instanceof Text)) {
                return code;
            }
        }
        final AccessibleEvent event = new AccessibleEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osName;
        if (this.control instanceof Text && (this.control.getStyle() & 0x80) != 0x0 && osName == null) {
            event.result = ((Text)this.control).getMessage();
        }
        for (int i = 0; i < this.accessibleListenersSize(); ++i) {
            final AccessibleListener listener = this.accessibleListeners.get(i);
            listener.getName(event);
        }
        if (event.result == null) {
            return code;
        }
        if (event.result.length() == 0) {
            return 1;
        }
        this.setString(pszName, event.result);
        return 0;
    }
    
    int get_accParent(final long ppdispParent) {
        int code = -2147352573;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accParent(ppdispParent);
        }
        if (this.parent != null) {
            this.parent.AddRef();
            OS.MoveMemory(ppdispParent, new long[] { this.parent.getAddress() }, C.PTR_SIZEOF);
            code = 0;
        }
        return code;
    }
    
    int get_accRole(final long varChild, final long pvarRole) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int osRole = 10;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.get_accRole(varChild, pvarRole);
            if (code == 0) {
                final VARIANT v2 = this.getVARIANT(pvarRole);
                if (v2.vt == 3) {
                    osRole = v2.lVal;
                }
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.detail = this.osToRole(osRole);
        if ((this.control instanceof Tree || this.control instanceof Table) && v.lVal != 0 && (this.control.getStyle() & 0x20) != 0x0) {
            event.detail = 44;
        }
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getRole(event);
        }
        this.setIntVARIANT(pvarRole, (short)3, this.roleToOs(event.detail));
        return 0;
    }
    
    int get_accSelection(final long pvarChildren) {
        int osChild = -2;
        long osChildObject = 0L;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.get_accSelection(pvarChildren);
            if (this.accessibleControlListenersSize() == 0) {
                return code;
            }
            if (code == 0) {
                final VARIANT v = this.getVARIANT(pvarChildren);
                if (v.vt == 3) {
                    osChild = this.osToChildID(v.lVal);
                }
                else if (v.vt == 9) {
                    osChildObject = v.lVal;
                }
                else if (v.vt == 13) {
                    osChild = -3;
                }
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = osChild;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getSelection(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(pvarChildren, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = event.childID;
        if (childID == -2) {
            if (osChildObject != 0L) {
                return 0;
            }
            this.setIntVARIANT(pvarChildren, (short)0, 0);
            return 1;
        }
        else {
            if (childID == -3) {
                return 0;
            }
            if (childID == -1) {
                this.AddRef();
                this.setPtrVARIANT(pvarChildren, (short)9, this.getAddress());
                return 0;
            }
            this.setIntVARIANT(pvarChildren, (short)3, this.childIDToOs(childID));
            return 0;
        }
    }
    
    int get_accState(final long varChild, final long pvarState) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int osState = 0;
        if (this.iaccessible != null) {
            final int code = this.iaccessible.get_accState(varChild, pvarState);
            if (code == 0) {
                final VARIANT v2 = this.getVARIANT(pvarState);
                if (v2.vt == 3) {
                    osState = v2.lVal;
                }
            }
        }
        boolean grayed = false;
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.detail = this.osToState(osState);
        if (v.lVal != 0) {
            if (this.control instanceof Tree && (this.control.getStyle() & 0x20) != 0x0) {
                final long hwnd = this.control.handle;
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 24;
                tvItem.stateMask = 61440;
                tvItem.hItem = OS.SendMessage(hwnd, 4394, v.lVal, 0L);
                final long result = OS.SendMessage(hwnd, 4414, 0L, tvItem);
                final boolean checked = result != 0L && (tvItem.state >> 12 & 0x1) == 0x0;
                if (checked) {
                    final AccessibleControlEvent accessibleControlEvent3;
                    final AccessibleControlEvent accessibleControlEvent = accessibleControlEvent3 = event;
                    accessibleControlEvent3.detail |= 0x10;
                }
                grayed = (tvItem.state >> 12 > 2);
            }
            else if (this.control instanceof Table && (this.control.getStyle() & 0x20) != 0x0) {
                final Table table = (Table)this.control;
                final int index = event.childID;
                if (0 <= index && index < table.getItemCount()) {
                    final TableItem item = table.getItem(index);
                    if (item.getChecked()) {
                        final AccessibleControlEvent accessibleControlEvent4;
                        final AccessibleControlEvent accessibleControlEvent2 = accessibleControlEvent4 = event;
                        accessibleControlEvent4.detail |= 0x10;
                    }
                    if (item.getGrayed()) {
                        grayed = true;
                    }
                }
            }
        }
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getState(event);
        }
        int state = this.stateToOs(event.detail);
        if ((state & 0x10) != 0x0 && grayed) {
            state &= 0xFFFFFFEF;
            state |= 0x20;
        }
        this.setIntVARIANT(pvarState, (short)3, state);
        return 0;
    }
    
    int get_accValue(final long varChild, final long pszValue) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        String osValue = null;
        if (this.iaccessible != null) {
            code = this.iaccessible.get_accValue(varChild, pszValue);
            if (code == 0) {
                final long[] pValue = { 0L };
                OS.MoveMemory(pValue, pszValue, C.PTR_SIZEOF);
                final int size = COM.SysStringByteLen(pValue[0]);
                if (size > 0) {
                    final char[] buffer = new char[(size + 1) / 2];
                    OS.MoveMemory(buffer, pValue[0], size);
                    osValue = new String(buffer);
                }
            }
            if (code == -2147024809) {
                code = -2147352573;
            }
            if (this.accessibleControlListenersSize() == 0 && !(this.control instanceof Text)) {
                return code;
            }
        }
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = this.osToChildID(v.lVal);
        event.result = osValue;
        if (this.control instanceof Text && (this.control.getStyle() & 0x80) != 0x0 && !this.control.isFocusControl()) {
            event.result = ((Text)this.control).getMessage();
        }
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getValue(event);
        }
        if (event.result == null) {
            return code;
        }
        this.setString(pszValue, event.result);
        return 0;
    }
    
    int put_accName(final long varChild, final long szName) {
        return -2147467263;
    }
    
    int put_accValue(final long varChild, final long szValue) {
        final VARIANT v = this.getVARIANT(varChild);
        if (v.vt != 3) {
            return -2147024809;
        }
        int code = -2147352573;
        if (v.lVal == 0 && this.accessibleEditableTextListenersSize() > 0) {
            final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
            event.start = 0;
            event.end = this.getCharacterCount();
            if (event.end >= 0) {
                final int size = COM.SysStringByteLen(szValue);
                final char[] buffer = new char[(size + 1) / 2];
                OS.MoveMemory(buffer, szValue, size);
                event.string = new String(buffer);
                for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
                    final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
                    listener.replaceText(event);
                }
                if (event.result != null && event.result.equals("OK")) {
                    code = 0;
                }
            }
        }
        if (code != 0 && this.iaccessible != null) {
            code = this.iaccessible.put_accValue(varChild, szValue);
            if (code == -2147024809) {
                code = -2147352573;
            }
        }
        return code;
    }
    
    int Next(final int celt, final long rgvar, final long pceltFetched) {
        if (this.iaccessible != null && this.accessibleControlListenersSize() == 0) {
            final long[] ppvObject = { 0L };
            int code = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, ppvObject);
            if (code != 0) {
                return code;
            }
            final IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
            final int[] celtFetched = { 0 };
            code = ienumvariant.Next(celt, rgvar, celtFetched);
            ienumvariant.Release();
            OS.MoveMemory(pceltFetched, celtFetched, 4);
            return code;
        }
        else {
            if (rgvar == 0L) {
                return -2147024809;
            }
            if (pceltFetched == 0L && celt != 1) {
                return -2147024809;
            }
            if (this.enumIndex == 0) {
                final AccessibleControlEvent event = new AccessibleControlEvent(this);
                event.childID = -1;
                for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
                    final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
                    listener.getChildren(event);
                }
                this.variants = event.children;
            }
            Object[] nextItems = null;
            if (this.variants != null && celt >= 1) {
                int endIndex = this.enumIndex + celt - 1;
                if (endIndex > this.variants.length - 1) {
                    endIndex = this.variants.length - 1;
                }
                if (this.enumIndex <= endIndex) {
                    nextItems = new Object[endIndex - this.enumIndex + 1];
                    for (int j = 0; j < nextItems.length; ++j) {
                        final Object child = this.variants[this.enumIndex];
                        if (child instanceof Integer) {
                            nextItems[j] = this.childIDToOs((int)child);
                        }
                        else {
                            nextItems[j] = child;
                        }
                        ++this.enumIndex;
                    }
                }
            }
            if (nextItems != null) {
                for (int i = 0; i < nextItems.length; ++i) {
                    final Object nextItem = nextItems[i];
                    if (nextItem instanceof Integer) {
                        final int item = (int)nextItem;
                        this.setIntVARIANT(rgvar + i * VARIANT.sizeof, (short)3, item);
                    }
                    else {
                        final Accessible accessible = (Accessible)nextItem;
                        accessible.AddRef();
                        this.setPtrVARIANT(rgvar + i * VARIANT.sizeof, (short)9, accessible.getAddress());
                    }
                }
                if (pceltFetched != 0L) {
                    OS.MoveMemory(pceltFetched, new int[] { nextItems.length }, 4);
                }
                if (nextItems.length == celt) {
                    return 0;
                }
            }
            else if (pceltFetched != 0L) {
                OS.MoveMemory(pceltFetched, new int[] { 0 }, 4);
            }
            return 1;
        }
    }
    
    int Skip(final int celt) {
        if (this.iaccessible != null && this.accessibleControlListenersSize() == 0) {
            final long[] ppvObject = { 0L };
            int code = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, ppvObject);
            if (code != 0) {
                return code;
            }
            final IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
            code = ienumvariant.Skip(celt);
            ienumvariant.Release();
            return code;
        }
        else {
            if (celt < 1) {
                return -2147024809;
            }
            this.enumIndex += celt;
            if (this.enumIndex > this.variants.length - 1) {
                this.enumIndex = this.variants.length - 1;
                return 1;
            }
            return 0;
        }
    }
    
    int Reset() {
        if (this.iaccessible == null || this.accessibleControlListenersSize() != 0) {
            return this.enumIndex = 0;
        }
        final long[] ppvObject = { 0L };
        int code = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, ppvObject);
        if (code != 0) {
            return code;
        }
        final IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
        code = ienumvariant.Reset();
        ienumvariant.Release();
        return code;
    }
    
    int Clone(final long ppEnum) {
        if (this.iaccessible != null && this.accessibleControlListenersSize() == 0) {
            final long[] ppvObject = { 0L };
            int code = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, ppvObject);
            if (code != 0) {
                return code;
            }
            final IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
            final long[] pEnum = { 0L };
            code = ienumvariant.Clone(pEnum);
            ienumvariant.Release();
            OS.MoveMemory(ppEnum, pEnum, C.PTR_SIZEOF);
            return code;
        }
        else {
            if (ppEnum == 0L) {
                return -2147024809;
            }
            OS.MoveMemory(ppEnum, new long[] { this.objIEnumVARIANT.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
    }
    
    int get_nRelations(final long pNRelations) {
        final int count = this.getRelationCount();
        OS.MoveMemory(pNRelations, new int[] { count }, 4);
        return 0;
    }
    
    int get_relation(final int relationIndex, final long ppRelation) {
        int i = -1;
        for (int type = 0; type < 15; ++type) {
            final Relation relation = this.relations[type];
            if (relation != null) {
                ++i;
            }
            if (i == relationIndex) {
                relation.AddRef();
                OS.MoveMemory(ppRelation, new long[] { relation.getAddress() }, C.PTR_SIZEOF);
                return 0;
            }
        }
        return -2147024809;
    }
    
    int get_relations(final int maxRelations, final long ppRelations, final long pNRelations) {
        int count = 0;
        for (int type = 0; type < 15 && count != maxRelations; ++type) {
            final Relation relation = this.relations[type];
            if (relation != null) {
                relation.AddRef();
                OS.MoveMemory(ppRelations + count * C.PTR_SIZEOF, new long[] { relation.getAddress() }, C.PTR_SIZEOF);
                ++count;
            }
        }
        OS.MoveMemory(pNRelations, new int[] { count }, 4);
        return 0;
    }
    
    int get_role(final long pRole) {
        int role = this.getRole();
        if (role == 0) {
            role = this.getDefaultRole();
        }
        OS.MoveMemory(pRole, new int[] { role }, 4);
        return 0;
    }
    
    int scrollTo(final int scrollType) {
        if (scrollType < 4 || scrollType > 6) {
            return -2147024809;
        }
        return -2147467263;
    }
    
    int scrollToPoint(final int coordinateType, final int x, final int y) {
        if (coordinateType != 0) {
            return -2147024809;
        }
        return -2147467263;
    }
    
    int get_groupPosition(final long pGroupLevel, final long pSimilarItemsInGroup, final long pPositionInGroup) {
        if (this.control != null && this.control.isDisposed()) {
            return -2147220995;
        }
        final AccessibleAttributeEvent accessibleAttributeEvent3;
        final AccessibleAttributeEvent accessibleAttributeEvent2;
        final AccessibleAttributeEvent event;
        final AccessibleAttributeEvent accessibleAttributeEvent = event = (accessibleAttributeEvent2 = (accessibleAttributeEvent3 = new AccessibleAttributeEvent(this)));
        final int groupLevel2 = -1;
        accessibleAttributeEvent.groupIndex = -1;
        accessibleAttributeEvent2.groupCount = -1;
        accessibleAttributeEvent3.groupLevel = -1;
        for (int i = 0; i < this.accessibleAttributeListenersSize(); ++i) {
            final AccessibleAttributeListener listener = this.accessibleAttributeListeners.get(i);
            listener.getAttributes(event);
        }
        final int groupLevel3 = (event.groupLevel != -1) ? event.groupLevel : 0;
        int similarItemsInGroup = (event.groupCount != -1) ? event.groupCount : 0;
        int positionInGroup = (event.groupIndex != -1) ? event.groupIndex : 0;
        if (similarItemsInGroup == 0 && positionInGroup == 0 && this.control instanceof Button && (this.control.getStyle() & 0x10) != 0x0) {
            positionInGroup = 1;
            similarItemsInGroup = 1;
            for (final Control child : this.control.getParent().getChildren()) {
                if (child instanceof Button && (child.getStyle() & 0x10) != 0x0) {
                    if (child == this.control) {
                        positionInGroup = similarItemsInGroup;
                    }
                    else {
                        ++similarItemsInGroup;
                    }
                }
            }
        }
        OS.MoveMemory(pGroupLevel, new int[] { groupLevel3 }, 4);
        OS.MoveMemory(pSimilarItemsInGroup, new int[] { similarItemsInGroup }, 4);
        OS.MoveMemory(pPositionInGroup, new int[] { positionInGroup }, 4);
        if (groupLevel3 == 0 && similarItemsInGroup == 0 && positionInGroup == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_states(final long pStates) {
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = -1;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getState(event);
        }
        final int states = event.detail;
        int ia2States = 0;
        if ((states & 0x4000000) != 0x0) {
            ia2States |= 0x1;
        }
        if ((states & 0x8000000) != 0x0) {
            ia2States |= 0x2000;
        }
        if ((states & 0x10000000) != 0x0) {
            ia2States |= 0x200;
        }
        if ((states & 0x2000000) != 0x0) {
            ia2States |= 0x800;
        }
        if ((states & 0x20000000) != 0x0) {
            ia2States |= 0x40;
        }
        if ((states & 0x40000000) != 0x0) {
            ia2States |= 0x8000;
        }
        if (this.getRole() == 42 && this.accessibleTextExtendedListenersSize() > 0) {
            ia2States |= 0x8;
        }
        OS.MoveMemory(pStates, new int[] { ia2States }, 4);
        return 0;
    }
    
    int get_extendedRole(final long pbstrExtendedRole) {
        this.setString(pbstrExtendedRole, null);
        return 1;
    }
    
    int get_localizedExtendedRole(final long pbstrLocalizedExtendedRole) {
        this.setString(pbstrLocalizedExtendedRole, null);
        return 1;
    }
    
    int get_nExtendedStates(final long pNExtendedStates) {
        OS.MoveMemory(pNExtendedStates, new int[] { 0 }, 4);
        return 0;
    }
    
    int get_extendedStates(final int maxExtendedStates, final long ppbstrExtendedStates, final long pNExtendedStates) {
        this.setString(ppbstrExtendedStates, null);
        OS.MoveMemory(pNExtendedStates, new int[] { 0 }, 4);
        return 1;
    }
    
    int get_localizedExtendedStates(final int maxLocalizedExtendedStates, final long ppbstrLocalizedExtendedStates, final long pNLocalizedExtendedStates) {
        this.setString(ppbstrLocalizedExtendedStates, null);
        OS.MoveMemory(pNLocalizedExtendedStates, new int[] { 0 }, 4);
        return 1;
    }
    
    int get_uniqueID(final long pUniqueID) {
        if (this.uniqueID == -1) {
            this.uniqueID = Accessible.UniqueID--;
        }
        OS.MoveMemory(pUniqueID, new long[] { this.uniqueID }, 4);
        return 0;
    }
    
    int get_windowHandle(final long pWindowHandle) {
        OS.MoveMemory(pWindowHandle, new long[] { this.control.handle }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_indexInParent(final long pIndexInParent) {
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = -5;
        event.detail = -1;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getChild(event);
        }
        final int indexInParent = event.detail;
        if (indexInParent == -1) {}
        OS.MoveMemory(pIndexInParent, new int[] { indexInParent }, 4);
        return (indexInParent == -1) ? 1 : 0;
    }
    
    int get_locale(final long pLocale) {
        final Locale locale = Locale.getDefault();
        char[] data = locale.getLanguage().toCharArray();
        long ptr = COM.SysAllocString(data);
        OS.MoveMemory(pLocale, new long[] { ptr }, C.PTR_SIZEOF);
        data = locale.getCountry().toCharArray();
        ptr = COM.SysAllocString(data);
        OS.MoveMemory(pLocale + C.PTR_SIZEOF, new long[] { ptr }, C.PTR_SIZEOF);
        data = locale.getVariant().toCharArray();
        ptr = COM.SysAllocString(data);
        OS.MoveMemory(pLocale + 2 * C.PTR_SIZEOF, new long[] { ptr }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_attributes(final long pbstrAttributes) {
        final AccessibleAttributeEvent event = new AccessibleAttributeEvent(this);
        for (int i = 0; i < this.accessibleAttributeListenersSize(); ++i) {
            final AccessibleAttributeListener listener = this.accessibleAttributeListeners.get(i);
            listener.getAttributes(event);
        }
        String attributes = "";
        attributes = attributes + "margin-left:" + event.leftMargin;
        attributes = attributes + "margin-top:" + event.topMargin;
        attributes = attributes + "margin-right:" + event.rightMargin;
        attributes = attributes + "margin-bottom:" + event.bottomMargin;
        if (event.tabStops != null) {
            for (final int tabStop : event.tabStops) {
                attributes = attributes + "tab-stop:position=" + tabStop;
            }
        }
        if (event.justify) {
            attributes += "text-align:justify;";
        }
        attributes = attributes + "text-align:" + ((event.alignment == 16384) ? "left" : ((event.alignment == 131072) ? "right" : "center"));
        attributes = attributes + "text-indent:" + event.indent;
        if (event.attributes != null) {
            for (int j = 0; j + 1 < event.attributes.length; j += 2) {
                attributes = attributes + event.attributes[j] + ":" + event.attributes[j + 1];
            }
        }
        if (this.getRole() == 42) {
            attributes += "text-model:a1;";
        }
        this.setString(pbstrAttributes, attributes);
        if (attributes.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_nActions(final long pNActions) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.getActionCount(event);
        }
        OS.MoveMemory(pNActions, new int[] { event.count }, 4);
        return 0;
    }
    
    int doAction(final int actionIndex) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        event.index = actionIndex;
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.doAction(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_description(final int actionIndex, final long pbstrDescription) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        event.index = actionIndex;
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.getDescription(event);
        }
        this.setString(pbstrDescription, event.result);
        if (event.result == null || event.result.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_keyBinding(final int actionIndex, final int nMaxBindings, final long ppbstrKeyBindings, final long pNBindings) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        event.index = actionIndex;
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.getKeyBinding(event);
        }
        final String keyBindings = event.result;
        int length = 0;
        if (keyBindings != null) {
            length = keyBindings.length();
        }
        int j;
        int count;
        int k;
        for (j = 0, count = 0; j < length && count != nMaxBindings; j = k + 1) {
            k = keyBindings.indexOf(59, j);
            if (k == -1) {
                k = length;
            }
            final String keyBinding = keyBindings.substring(j, k);
            if (keyBinding.length() > 0) {
                this.setString(ppbstrKeyBindings + count * C.PTR_SIZEOF, keyBinding);
                ++count;
            }
        }
        OS.MoveMemory(pNBindings, new int[] { count }, 4);
        if (count == 0) {
            this.setString(ppbstrKeyBindings, null);
            return 1;
        }
        return 0;
    }
    
    int get_name(final int actionIndex, final long pbstrName) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        event.index = actionIndex;
        event.localized = false;
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.getName(event);
        }
        if (event.result == null || event.result.length() == 0) {
            this.setString(pbstrName, null);
            return 1;
        }
        this.setString(pbstrName, event.result);
        return 0;
    }
    
    int get_localizedName(final int actionIndex, final long pbstrLocalizedName) {
        final AccessibleActionEvent event = new AccessibleActionEvent(this);
        event.index = actionIndex;
        event.localized = true;
        for (int i = 0; i < this.accessibleActionListenersSize(); ++i) {
            final AccessibleActionListener listener = this.accessibleActionListeners.get(i);
            listener.getName(event);
        }
        if (event.result == null || event.result.length() == 0) {
            this.setString(pbstrLocalizedName, null);
            return 1;
        }
        this.setString(pbstrLocalizedName, event.result);
        return 0;
    }
    
    int get_appName(final long pbstrName) {
        final String appName = Display.getAppName();
        if (appName == null || appName.length() == 0) {
            this.setString(pbstrName, null);
            return 1;
        }
        this.setString(pbstrName, appName);
        return 0;
    }
    
    int get_appVersion(final long pbstrVersion) {
        final String appVersion = Display.getAppVersion();
        if (appVersion == null || appVersion.length() == 0) {
            this.setString(pbstrVersion, null);
            return 1;
        }
        this.setString(pbstrVersion, appVersion);
        return 0;
    }
    
    int get_toolkitName(final long pbstrName) {
        final String toolkitName = "SWT";
        this.setString(pbstrName, "SWT");
        return 0;
    }
    
    int get_toolkitVersion(final long pbstrVersion) {
        final String toolkitVersion = "" + SWT.getVersion();
        this.setString(pbstrVersion, toolkitVersion);
        return 0;
    }
    
    int copyText(final int startOffset, final int endOffset) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.copyText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int deleteText(final int startOffset, final int endOffset) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        event.string = "";
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.replaceText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int insertText(final int offset, final long pbstrText) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((offset == -1) ? this.getCharacterCount() : offset);
        event.end = event.start;
        event.string = this.getString(pbstrText);
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.replaceText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int cutText(final int startOffset, final int endOffset) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.cutText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int pasteText(final int offset) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((offset == -1) ? this.getCharacterCount() : offset);
        event.end = event.start;
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.pasteText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int replaceText(final int startOffset, final int endOffset, final long pbstrText) {
        final AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        event.string = this.getString(pbstrText);
        for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
            final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
            listener.replaceText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setAttributes(final int startOffset, final int endOffset, final long pbstrAttributes) {
        final AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
        final String string = this.getString(pbstrAttributes);
        if (string != null && string.length() > 0) {
            event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
            event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
            final TextStyle style = new TextStyle();
            FontData fontData = null;
            int points = 10;
            String[] attributes = new String[0];
            for (int begin = 0, end = string.indexOf(59); end != -1 && end < string.length(); end = string.indexOf(59, begin)) {
                final String keyValue = string.substring(begin, end).trim();
                final int colonIndex = keyValue.indexOf(58);
                if (colonIndex != -1 && colonIndex + 1 < keyValue.length()) {
                    final String[] newAttributes = new String[attributes.length + 2];
                    System.arraycopy(attributes, 0, newAttributes, 0, attributes.length);
                    newAttributes[attributes.length] = keyValue.substring(0, colonIndex).trim();
                    newAttributes[attributes.length + 1] = keyValue.substring(colonIndex + 1).trim();
                    attributes = newAttributes;
                }
                begin = end + 1;
            }
            for (int i = 0; i + 1 < attributes.length; i += 2) {
                final String key = attributes[i];
                final String value = attributes[i + 1];
                if (key.equals("text-position")) {
                    if (value.equals("super")) {
                        style.rise = points / 2;
                    }
                    else if (value.equals("sub")) {
                        style.rise = -points / 2;
                    }
                }
                else if (key.equals("text-underline-type")) {
                    style.underline = true;
                    if (value.equals("double")) {
                        style.underlineStyle = 1;
                    }
                    else if (value.equals("single") && style.underlineStyle != 3 && style.underlineStyle != 2) {
                        style.underlineStyle = 0;
                    }
                }
                else if (key.equals("text-underline-style") && value.equals("wave")) {
                    style.underline = true;
                    style.underlineStyle = 3;
                }
                else if (key.equals("invalid") && value.equals("true")) {
                    style.underline = true;
                    style.underlineStyle = 2;
                }
                else if (key.equals("text-line-through-type")) {
                    if (value.equals("single")) {
                        style.strikeout = true;
                    }
                }
                else if (key.equals("font-family")) {
                    if (fontData == null) {
                        fontData = new FontData();
                    }
                    fontData.setName(value);
                }
                else if (key.equals("font-size")) {
                    try {
                        final String pts = value.endsWith("pt") ? value.substring(0, value.length() - 2) : value;
                        points = Integer.parseInt(pts);
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setHeight(points);
                        if (style.rise > 0) {
                            style.rise = points / 2;
                        }
                        else if (style.rise < 0) {
                            style.rise = -points / 2;
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                else if (key.equals("font-style")) {
                    if (value.equals("italic")) {
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setStyle(fontData.getStyle() | 0x2);
                    }
                }
                else if (key.equals("font-weight")) {
                    if (value.equals("bold")) {
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setStyle(fontData.getStyle() | 0x1);
                    }
                    else {
                        try {
                            final int weight = Integer.parseInt(value);
                            if (fontData == null) {
                                fontData = new FontData();
                            }
                            if (weight > 400) {
                                fontData.setStyle(fontData.getStyle() | 0x1);
                            }
                        }
                        catch (NumberFormatException ex2) {}
                    }
                }
                else if (key.equals("color")) {
                    style.foreground = this.colorFromString(value);
                }
                else if (key.equals("background-color")) {
                    style.background = this.colorFromString(value);
                }
            }
            if (attributes.length > 0) {
                event.attributes = attributes;
                if (fontData != null) {
                    style.font = new Font(this.control.getDisplay(), fontData);
                }
                if (!style.equals(new TextStyle())) {
                    event.textStyle = style;
                }
            }
            for (int i = 0; i < this.accessibleEditableTextListenersSize(); ++i) {
                final AccessibleEditableTextListener listener = this.accessibleEditableTextListeners.get(i);
                listener.setTextAttributes(event);
            }
            if (style.font != null) {
                style.font.dispose();
            }
            if (style.foreground != null) {
                style.foreground.dispose();
            }
            if (style.background != null) {
                style.background.dispose();
            }
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_anchor(final int index, final long pAnchor) {
        final AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
        event.index = index;
        for (int i = 0; i < this.accessibleHyperlinkListenersSize(); ++i) {
            final AccessibleHyperlinkListener listener = this.accessibleHyperlinkListeners.get(i);
            listener.getAnchor(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(pAnchor, (short)9, accessible.getAddress());
            return 0;
        }
        this.setStringVARIANT(pAnchor, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_anchorTarget(final int index, final long pAnchorTarget) {
        final AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
        event.index = index;
        for (int i = 0; i < this.accessibleHyperlinkListenersSize(); ++i) {
            final AccessibleHyperlinkListener listener = this.accessibleHyperlinkListeners.get(i);
            listener.getAnchorTarget(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(pAnchorTarget, (short)9, accessible.getAddress());
            return 0;
        }
        this.setStringVARIANT(pAnchorTarget, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_startIndex(final long pIndex) {
        final AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
        for (int i = 0; i < this.accessibleHyperlinkListenersSize(); ++i) {
            final AccessibleHyperlinkListener listener = this.accessibleHyperlinkListeners.get(i);
            listener.getStartIndex(event);
        }
        OS.MoveMemory(pIndex, new int[] { event.index }, 4);
        return 0;
    }
    
    int get_endIndex(final long pIndex) {
        final AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
        for (int i = 0; i < this.accessibleHyperlinkListenersSize(); ++i) {
            final AccessibleHyperlinkListener listener = this.accessibleHyperlinkListeners.get(i);
            listener.getEndIndex(event);
        }
        OS.MoveMemory(pIndex, new int[] { event.index }, 4);
        return 0;
    }
    
    int get_valid(final long pValid) {
        return -2147467263;
    }
    
    int get_nHyperlinks(final long pHyperlinkCount) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getHyperlinkCount(event);
        }
        OS.MoveMemory(pHyperlinkCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_hyperlink(final int index, final long ppHyperlink) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.index = index;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getHyperlink(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible == null) {
            this.setIntVARIANT(ppHyperlink, (short)0, 0);
            return -2147024809;
        }
        accessible.AddRef();
        OS.MoveMemory(ppHyperlink, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_hyperlinkIndex(final int charIndex, final long pHyperlinkIndex) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.offset = charIndex;
        event.index = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getHyperlinkIndex(event);
        }
        OS.MoveMemory(pHyperlinkIndex, new int[] { event.index }, 4);
        if (event.index == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_cellAt(final int row, final int column, final long ppCell) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.row = row;
        event.column = column;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getCell(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible == null) {
            return -2147024809;
        }
        accessible.AddRef();
        OS.MoveMemory(ppCell, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_caption(final long ppAccessible) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getCaption(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible == null) {
            OS.MoveMemory(ppAccessible, new long[] { 0L }, C.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(ppAccessible, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_columnDescription(final int column, final long pbstrDescription) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.column = column;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getColumnDescription(event);
        }
        this.setString(pbstrDescription, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_nColumns(final long pColumnCount) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getColumnCount(event);
        }
        OS.MoveMemory(pColumnCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_nRows(final long pRowCount) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getRowCount(event);
        }
        OS.MoveMemory(pRowCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_nSelectedCells(final long pCellCount) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedCellCount(event);
        }
        OS.MoveMemory(pCellCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_nSelectedColumns(final long pColumnCount) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedColumnCount(event);
        }
        OS.MoveMemory(pColumnCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_nSelectedRows(final long pRowCount) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedRowCount(event);
        }
        OS.MoveMemory(pRowCount, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_rowDescription(final int row, final long pbstrDescription) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.row = row;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getRowDescription(event);
        }
        this.setString(pbstrDescription, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_selectedCells(final long ppCells, final long pNSelectedCells) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedCells(event);
        }
        if (event.accessibles == null || event.accessibles.length == 0) {
            OS.MoveMemory(ppCells, new long[] { 0L }, C.PTR_SIZEOF);
            OS.MoveMemory(pNSelectedCells, new int[] { 0 }, 4);
            return 1;
        }
        final int length = event.accessibles.length;
        final long pv = OS.CoTaskMemAlloc(length * C.PTR_SIZEOF);
        int count = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = event.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(pv + j * C.PTR_SIZEOF, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
                ++count;
            }
        }
        OS.MoveMemory(ppCells, new long[] { pv }, C.PTR_SIZEOF);
        OS.MoveMemory(pNSelectedCells, new int[] { count }, 4);
        return 0;
    }
    
    int get_selectedColumns(final long ppSelectedColumns, final long pNColumns) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedColumns(event);
        }
        final int count = (event.selected == null) ? 0 : event.selected.length;
        if (count == 0) {
            OS.MoveMemory(ppSelectedColumns, new long[] { 0L }, C.PTR_SIZEOF);
            OS.MoveMemory(pNColumns, new int[] { 0 }, 4);
            return 1;
        }
        final long pv = OS.CoTaskMemAlloc(count * 4);
        OS.MoveMemory(pv, event.selected, count * 4);
        OS.MoveMemory(ppSelectedColumns, new long[] { pv }, C.PTR_SIZEOF);
        OS.MoveMemory(pNColumns, new int[] { count }, 4);
        return 0;
    }
    
    int get_selectedRows(final long ppSelectedRows, final long pNRows) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSelectedRows(event);
        }
        final int count = (event.selected == null) ? 0 : event.selected.length;
        if (count == 0) {
            OS.MoveMemory(ppSelectedRows, new long[] { 0L }, C.PTR_SIZEOF);
            OS.MoveMemory(pNRows, new int[] { 0 }, 4);
            return 1;
        }
        final long pv = OS.CoTaskMemAlloc(count * 4);
        OS.MoveMemory(pv, event.selected, count * 4);
        OS.MoveMemory(ppSelectedRows, new long[] { pv }, C.PTR_SIZEOF);
        OS.MoveMemory(pNRows, new int[] { count }, 4);
        return 0;
    }
    
    int get_summary(final long ppAccessible) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.getSummary(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible == null) {
            OS.MoveMemory(ppAccessible, new long[] { 0L }, C.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(ppAccessible, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int get_isColumnSelected(final int column, final long pIsSelected) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.column = column;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.isColumnSelected(event);
        }
        OS.MoveMemory(pIsSelected, new int[] { event.isSelected ? 1 : 0 }, 4);
        return 0;
    }
    
    int get_isRowSelected(final int row, final long pIsSelected) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.row = row;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.isRowSelected(event);
        }
        OS.MoveMemory(pIsSelected, new int[] { event.isSelected ? 1 : 0 }, 4);
        return 0;
    }
    
    int selectRow(final int row) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.row = row;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.setSelectedRow(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int selectColumn(final int column) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.column = column;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.setSelectedColumn(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int unselectRow(final int row) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.row = row;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.deselectRow(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int unselectColumn(final int column) {
        final AccessibleTableEvent event = new AccessibleTableEvent(this);
        event.column = column;
        for (int i = 0; i < this.accessibleTableListenersSize(); ++i) {
            final AccessibleTableListener listener = this.accessibleTableListeners.get(i);
            listener.deselectColumn(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_modelChange(final long pModelChange) {
        if (this.tableChange == null) {
            OS.MoveMemory(pModelChange, new long[] { 0L }, C.PTR_SIZEOF);
            return 1;
        }
        OS.MoveMemory(pModelChange, this.tableChange, this.tableChange.length * 4);
        return 0;
    }
    
    int get_columnExtent(final long pNColumnsSpanned) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getColumnSpan(event);
        }
        OS.MoveMemory(pNColumnsSpanned, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_columnHeaderCells(final long ppCellAccessibles, final long pNColumnHeaderCells) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getColumnHeaders(event);
        }
        if (event.accessibles == null || event.accessibles.length == 0) {
            OS.MoveMemory(ppCellAccessibles, new long[] { 0L }, C.PTR_SIZEOF);
            OS.MoveMemory(pNColumnHeaderCells, new int[] { 0 }, 4);
            return 1;
        }
        final int length = event.accessibles.length;
        final long pv = OS.CoTaskMemAlloc(length * C.PTR_SIZEOF);
        int count = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = event.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(pv + j * C.PTR_SIZEOF, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
                ++count;
            }
        }
        OS.MoveMemory(ppCellAccessibles, new long[] { pv }, C.PTR_SIZEOF);
        OS.MoveMemory(pNColumnHeaderCells, new int[] { count }, 4);
        return 0;
    }
    
    int get_columnIndex(final long pColumnIndex) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getColumnIndex(event);
        }
        OS.MoveMemory(pColumnIndex, new int[] { event.index }, 4);
        return 0;
    }
    
    int get_rowExtent(final long pNRowsSpanned) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getRowSpan(event);
        }
        OS.MoveMemory(pNRowsSpanned, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_rowHeaderCells(final long ppCellAccessibles, final long pNRowHeaderCells) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getRowHeaders(event);
        }
        if (event.accessibles == null || event.accessibles.length == 0) {
            OS.MoveMemory(ppCellAccessibles, new long[] { 0L }, C.PTR_SIZEOF);
            OS.MoveMemory(pNRowHeaderCells, new int[] { 0 }, 4);
            return 1;
        }
        final int length = event.accessibles.length;
        final long pv = OS.CoTaskMemAlloc(length * C.PTR_SIZEOF);
        int count = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = event.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(pv + j * C.PTR_SIZEOF, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
                ++count;
            }
        }
        OS.MoveMemory(ppCellAccessibles, new long[] { pv }, C.PTR_SIZEOF);
        OS.MoveMemory(pNRowHeaderCells, new int[] { count }, 4);
        return 0;
    }
    
    int get_rowIndex(final long pRowIndex) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getRowIndex(event);
        }
        OS.MoveMemory(pRowIndex, new int[] { event.index }, 4);
        return 0;
    }
    
    int get_isSelected(final long pIsSelected) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.isSelected(event);
        }
        OS.MoveMemory(pIsSelected, new int[] { event.isSelected ? 1 : 0 }, 4);
        return 0;
    }
    
    int get_rowColumnExtents(final long pRow, final long pColumn, final long pRowExtents, final long pColumnExtents, final long pIsSelected) {
        return -2147352573;
    }
    
    int get_table(final long ppTable) {
        final AccessibleTableCellEvent event = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListenersSize(); ++i) {
            final AccessibleTableCellListener listener = this.accessibleTableCellListeners.get(i);
            listener.getTable(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible == null) {
            OS.MoveMemory(ppTable, new long[] { 0L }, C.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(ppTable, new long[] { accessible.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
    
    int addSelection(final int startOffset, final int endOffset) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.addSelection(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_attributes(final int offset, final long pStartOffset, final long pEndOffset, final long pbstrTextAttributes) {
        final AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
        event.offset = ((offset == -1) ? this.getCharacterCount() : offset);
        for (int i = 0; i < this.accessibleAttributeListenersSize(); ++i) {
            final AccessibleAttributeListener listener = this.accessibleAttributeListeners.get(i);
            listener.getTextAttributes(event);
        }
        String textAttributes = "";
        final TextStyle style = event.textStyle;
        if (style != null) {
            if (style.rise != 0) {
                textAttributes += "text-position:";
                if (style.rise > 0) {
                    textAttributes += "super";
                }
                else {
                    textAttributes += "sub";
                }
            }
            if (style.underline) {
                textAttributes += "text-underline-type:";
                switch (style.underlineStyle) {
                    case 0: {
                        textAttributes += "single;";
                        break;
                    }
                    case 1: {
                        textAttributes += "double;";
                        break;
                    }
                    case 3: {
                        textAttributes += "single;text-underline-style:wave;";
                        break;
                    }
                    case 2: {
                        textAttributes += "single;text-underline-style:wave;invalid:true;";
                        break;
                    }
                    default: {
                        textAttributes += "none;";
                        break;
                    }
                }
            }
            if (style.strikeout) {
                textAttributes += "text-line-through-type:single;";
            }
            final Font font = style.font;
            if (font != null && !font.isDisposed()) {
                final FontData fontData = font.getFontData()[0];
                textAttributes = textAttributes + "font-family:" + fontData.getName();
                textAttributes = textAttributes + "font-size:" + fontData.getHeight() + "pt;";
                textAttributes = textAttributes + "font-style:" + ((fontData.data.lfItalic != 0) ? "italic" : "normal");
                textAttributes = textAttributes + "font-weight:" + fontData.data.lfWeight;
            }
            Color color = style.foreground;
            if (color != null && !color.isDisposed()) {
                textAttributes = textAttributes + "color:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");";
            }
            color = style.background;
            if (color != null && !color.isDisposed()) {
                textAttributes = textAttributes + "background-color:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");";
            }
        }
        if (event.attributes != null) {
            for (int j = 0; j + 1 < event.attributes.length; j += 2) {
                textAttributes = textAttributes + event.attributes[j] + ":" + event.attributes[j + 1];
            }
        }
        OS.MoveMemory(pStartOffset, new int[] { event.start }, 4);
        OS.MoveMemory(pEndOffset, new int[] { event.end }, 4);
        this.setString(pbstrTextAttributes, textAttributes);
        if (textAttributes.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_caretOffset(final long pOffset) {
        final int offset = this.getCaretOffset();
        OS.MoveMemory(pOffset, new int[] { offset }, 4);
        if (offset == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_characterExtents(final int offset, final int coordType, final long pX, final long pY, final long pWidth, final long pHeight) {
        final int length = this.getCharacterCount();
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = ((offset == -1) ? length : ((offset < 0) ? 0 : offset));
        event.end = ((offset == -1 || offset >= length) ? length : (offset + 1));
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getTextBounds(event);
        }
        OS.MoveMemory(pX, new int[] { event.x }, 4);
        OS.MoveMemory(pY, new int[] { event.y }, 4);
        OS.MoveMemory(pWidth, new int[] { event.width }, 4);
        OS.MoveMemory(pHeight, new int[] { event.height }, 4);
        if (event.width == 0 && event.height == 0) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_nSelections(final long pNSelections) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.count = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getSelectionCount(event);
        }
        if (event.count == -1) {
            event.childID = -1;
            event.offset = -1;
            event.length = 0;
            for (int i = 0; i < this.accessibleTextListenersSize(); ++i) {
                final AccessibleTextListener listener2 = this.accessibleTextListeners.get(i);
                listener2.getSelectionRange(event);
            }
            event.count = ((event.offset != -1 && event.length > 0) ? 1 : 0);
        }
        OS.MoveMemory(pNSelections, new int[] { event.count }, 4);
        return 0;
    }
    
    int get_offsetAtPoint(final int x, final int y, final int coordType, final long pOffset) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.x = x;
        event.y = y;
        event.offset = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getOffsetAtPoint(event);
        }
        OS.MoveMemory(pOffset, new int[] { event.offset }, 4);
        if (event.offset == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_selection(final int selectionIndex, final long pStartOffset, final long pEndOffset) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.index = selectionIndex;
        event.start = -1;
        event.end = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getSelection(event);
        }
        if (event.start == -1 && selectionIndex == 0) {
            event.childID = -1;
            event.offset = -1;
            event.length = 0;
            for (int i = 0; i < this.accessibleTextListenersSize(); ++i) {
                final AccessibleTextListener listener2 = this.accessibleTextListeners.get(i);
                listener2.getSelectionRange(event);
            }
            event.start = event.offset;
            event.end = event.offset + event.length;
        }
        OS.MoveMemory(pStartOffset, new int[] { event.start }, 4);
        OS.MoveMemory(pEndOffset, new int[] { event.end }, 4);
        if (event.start == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_text(final int startOffset, final int endOffset, final long pbstrText) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        if (event.start > event.end) {
            final int temp = event.start;
            event.start = event.end;
            event.end = temp;
        }
        event.count = 0;
        event.type = 5;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getText(event);
        }
        if (event.result == null) {
            final AccessibleControlEvent e = new AccessibleControlEvent(this);
            e.childID = -1;
            for (int j = 0; j < this.accessibleControlListenersSize(); ++j) {
                final AccessibleControlListener listener2 = this.accessibleControlListeners.get(j);
                listener2.getRole(e);
                listener2.getValue(e);
            }
            if (e.detail == 42) {
                event.result = e.result;
            }
        }
        this.setString(pbstrText, event.result);
        if (event.result == null) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_textBeforeOffset(final int offset, final int boundaryType, final long pStartOffset, final long pEndOffset, final long pbstrText) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        final int charCount = this.getCharacterCount();
        event.start = ((offset == -1) ? charCount : ((offset == -2) ? this.getCaretOffset() : offset));
        event.end = event.start;
        event.count = -1;
        switch (boundaryType) {
            case 0: {
                event.type = 0;
                break;
            }
            case 1: {
                event.type = 1;
                break;
            }
            case 2: {
                event.type = 2;
                break;
            }
            case 3: {
                event.type = 3;
                break;
            }
            case 4: {
                event.type = 4;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int eventStart = event.start;
        final int eventEnd = event.end;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getText(event);
        }
        if (event.end < charCount) {
            switch (boundaryType) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start = event.start;
                    event.start = eventStart;
                    event.end = eventEnd;
                    event.count = 0;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    event.end = event.start;
                    event.start = start;
                    event.type = 5;
                    event.count = 0;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(pStartOffset, new int[] { event.start }, 4);
        OS.MoveMemory(pEndOffset, new int[] { event.end }, 4);
        this.setString(pbstrText, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_textAfterOffset(final int offset, final int boundaryType, final long pStartOffset, final long pEndOffset, final long pbstrText) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        final int charCount = this.getCharacterCount();
        event.start = ((offset == -1) ? charCount : ((offset == -2) ? this.getCaretOffset() : offset));
        event.end = event.start;
        event.count = 1;
        switch (boundaryType) {
            case 0: {
                event.type = 0;
                break;
            }
            case 1: {
                event.type = 1;
                break;
            }
            case 2: {
                event.type = 2;
                break;
            }
            case 3: {
                event.type = 3;
                break;
            }
            case 4: {
                event.type = 4;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int eventStart = event.start;
        final int eventEnd = event.end;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getText(event);
        }
        if (event.end < charCount) {
            switch (boundaryType) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start = event.start;
                    event.start = eventStart;
                    event.end = eventEnd;
                    event.count = 2;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    event.end = event.start;
                    event.start = start;
                    event.type = 5;
                    event.count = 0;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(pStartOffset, new int[] { event.start }, 4);
        OS.MoveMemory(pEndOffset, new int[] { event.end }, 4);
        this.setString(pbstrText, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_textAtOffset(final int offset, final int boundaryType, final long pStartOffset, final long pEndOffset, final long pbstrText) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        final int charCount = this.getCharacterCount();
        event.start = ((offset == -1) ? charCount : ((offset == -2) ? this.getCaretOffset() : offset));
        event.end = event.start;
        event.count = 0;
        switch (boundaryType) {
            case 0: {
                event.type = 0;
                break;
            }
            case 1: {
                event.type = 1;
                break;
            }
            case 2: {
                event.type = 2;
                break;
            }
            case 3: {
                event.type = 3;
                break;
            }
            case 4: {
                event.type = 4;
                break;
            }
            case 5: {
                event.type = 5;
                event.start = 0;
                event.end = charCount;
                event.count = 0;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int eventStart = event.start;
        final int eventEnd = event.end;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getText(event);
        }
        if (event.end < charCount) {
            switch (boundaryType) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start = event.start;
                    event.start = eventStart;
                    event.end = eventEnd;
                    event.count = 1;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    event.end = event.start;
                    event.start = start;
                    event.type = 5;
                    event.count = 0;
                    for (int j = 0; j < this.accessibleTextExtendedListenersSize(); ++j) {
                        final AccessibleTextExtendedListener listener2 = this.accessibleTextExtendedListeners.get(j);
                        listener2.getText(event);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(pStartOffset, new int[] { event.start }, 4);
        OS.MoveMemory(pEndOffset, new int[] { event.end }, 4);
        this.setString(pbstrText, event.result);
        if (event.result == null) {
            return 1;
        }
        return 0;
    }
    
    int removeSelection(final int selectionIndex) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.index = selectionIndex;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.removeSelection(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setCaretOffset(final int offset) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.offset = ((offset == -1) ? this.getCharacterCount() : offset);
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.setCaretOffset(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setSelection(final int selectionIndex, final int startOffset, final int endOffset) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.index = selectionIndex;
        event.start = ((startOffset == -1) ? this.getCharacterCount() : startOffset);
        event.end = ((endOffset == -1) ? this.getCharacterCount() : endOffset);
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.setSelection(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_nCharacters(final long pNCharacters) {
        final int count = this.getCharacterCount();
        OS.MoveMemory(pNCharacters, new int[] { count }, 4);
        return 0;
    }
    
    int scrollSubstringTo(final int startIndex, final int endIndex, final int scrollType) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = startIndex;
        event.end = endIndex;
        switch (scrollType) {
            case 0: {
                event.type = 0;
                break;
            }
            case 1: {
                event.type = 1;
                break;
            }
            case 2: {
                event.type = 2;
                break;
            }
            case 3: {
                event.type = 3;
                break;
            }
            case 4: {
                event.type = 4;
                break;
            }
            case 5: {
                event.type = 5;
                break;
            }
            case 6: {
                event.type = 6;
                break;
            }
        }
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.scrollText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int scrollSubstringToPoint(final int startIndex, final int endIndex, final int coordinateType, final int x, final int y) {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.start = startIndex;
        event.end = endIndex;
        event.type = 7;
        event.x = x;
        event.y = y;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.scrollText(event);
        }
        if (event.result == null || !event.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_newText(final long pNewText) {
        String text = null;
        int start = 0;
        int end = 0;
        if (this.textInserted != null) {
            text = (String)this.textInserted[3];
            start = (int)this.textInserted[1];
            end = (int)this.textInserted[2];
        }
        this.setString(pNewText, text);
        OS.MoveMemory(pNewText + C.PTR_SIZEOF, new int[] { start }, 4);
        OS.MoveMemory(pNewText + C.PTR_SIZEOF + 4L, new int[] { end }, 4);
        if (this.textInserted == null) {
            return 1;
        }
        return 0;
    }
    
    int get_oldText(final long pOldText) {
        String text = null;
        int start = 0;
        int end = 0;
        if (this.textDeleted != null) {
            text = (String)this.textDeleted[3];
            start = (int)this.textDeleted[1];
            end = (int)this.textDeleted[2];
        }
        this.setString(pOldText, text);
        OS.MoveMemory(pOldText + C.PTR_SIZEOF, new int[] { start }, 4);
        OS.MoveMemory(pOldText + C.PTR_SIZEOF + 4L, new int[] { end }, 4);
        if (this.textDeleted == null) {
            return 1;
        }
        return 0;
    }
    
    int get_currentValue(final long pCurrentValue) {
        final AccessibleValueEvent event = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListenersSize(); ++i) {
            final AccessibleValueListener listener = this.accessibleValueListeners.get(i);
            listener.getCurrentValue(event);
        }
        this.setNumberVARIANT(pCurrentValue, event.value);
        return 0;
    }
    
    int setCurrentValue(final long value) {
        final AccessibleValueEvent event = new AccessibleValueEvent(this);
        event.value = this.getNumberVARIANT(value);
        for (int i = 0; i < this.accessibleValueListenersSize(); ++i) {
            final AccessibleValueListener listener = this.accessibleValueListeners.get(i);
            listener.setCurrentValue(event);
        }
        return 0;
    }
    
    int get_maximumValue(final long pMaximumValue) {
        final AccessibleValueEvent event = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListenersSize(); ++i) {
            final AccessibleValueListener listener = this.accessibleValueListeners.get(i);
            listener.getMaximumValue(event);
        }
        this.setNumberVARIANT(pMaximumValue, event.value);
        return 0;
    }
    
    int get_minimumValue(final long pMinimumValue) {
        final AccessibleValueEvent event = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListenersSize(); ++i) {
            final AccessibleValueListener listener = this.accessibleValueListeners.get(i);
            listener.getMinimumValue(event);
        }
        this.setNumberVARIANT(pMinimumValue, event.value);
        return 0;
    }
    
    int eventChildID() {
        if (this.parent == null) {
            return 0;
        }
        if (this.uniqueID == -1) {
            this.uniqueID = Accessible.UniqueID--;
        }
        return this.uniqueID;
    }
    
    void checkUniqueID(final int childID) {
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = childID;
        for (int l = 0; l < this.accessibleControlListenersSize(); ++l) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(l);
            listener.getChild(event);
        }
        final Accessible accessible = event.accessible;
        if (accessible != null && accessible.uniqueID == -1) {
            accessible.uniqueID = childID;
        }
    }
    
    int childIDToOs(final int childID) {
        if (childID == -1) {
            return 0;
        }
        int osChildID = childID + 1;
        if (this.control instanceof Tree) {
            osChildID = (int)OS.SendMessage(this.control.handle, 4395, childID, 0L);
        }
        this.checkUniqueID(osChildID);
        return osChildID;
    }
    
    int osToChildID(final int osChildID) {
        if (osChildID == 0) {
            return -1;
        }
        if (!(this.control instanceof Tree)) {
            return osChildID - 1;
        }
        return (int)OS.SendMessage(this.control.handle, 4394, osChildID, 0L);
    }
    
    int stateToOs(final int state) {
        int osState = 0;
        if ((state & 0x2) != 0x0) {
            osState |= 0x2;
        }
        if ((state & 0x200000) != 0x0) {
            osState |= 0x200000;
        }
        if ((state & 0x1000000) != 0x0) {
            osState |= 0x1000000;
        }
        if ((state & 0x4) != 0x0) {
            osState |= 0x4;
        }
        if ((state & 0x100000) != 0x0) {
            osState |= 0x100000;
        }
        if ((state & 0x8) != 0x0) {
            osState |= 0x8;
        }
        if ((state & 0x10) != 0x0) {
            osState |= 0x10;
        }
        if ((state & 0x200) != 0x0) {
            osState |= 0x200;
        }
        if ((state & 0x400) != 0x0) {
            osState |= 0x400;
        }
        if ((state & 0x80) != 0x0) {
            osState |= 0x80;
        }
        if ((state & 0x800) != 0x0) {
            osState |= 0x800;
        }
        if ((state & 0x40) != 0x0) {
            osState |= 0x40;
        }
        if ((state & 0x8000) != 0x0) {
            osState |= 0x8000;
        }
        if ((state & 0x10000) != 0x0) {
            osState |= 0x10000;
        }
        if ((state & 0x20000) != 0x0) {
            osState |= 0x20000;
        }
        if ((state & 0x400000) != 0x0) {
            osState |= 0x400000;
        }
        if ((state & 0x1) != 0x0) {
            osState |= 0x1;
        }
        return osState;
    }
    
    int osToState(final int osState) {
        int state = 0;
        if ((osState & 0x2) != 0x0) {
            state |= 0x2;
        }
        if ((osState & 0x200000) != 0x0) {
            state |= 0x200000;
        }
        if ((osState & 0x1000000) != 0x0) {
            state |= 0x1000000;
        }
        if ((osState & 0x4) != 0x0) {
            state |= 0x4;
        }
        if ((osState & 0x100000) != 0x0) {
            state |= 0x100000;
        }
        if ((osState & 0x8) != 0x0) {
            state |= 0x8;
        }
        if ((osState & 0x10) != 0x0) {
            state |= 0x10;
        }
        if ((osState & 0x200) != 0x0) {
            state |= 0x200;
        }
        if ((osState & 0x400) != 0x0) {
            state |= 0x400;
        }
        if ((osState & 0x80) != 0x0) {
            state |= 0x80;
        }
        if ((osState & 0x800) != 0x0) {
            state |= 0x800;
        }
        if ((osState & 0x40) != 0x0) {
            state |= 0x40;
        }
        if ((osState & 0x8000) != 0x0) {
            state |= 0x8000;
        }
        if ((osState & 0x10000) != 0x0) {
            state |= 0x10000;
        }
        if ((osState & 0x20000) != 0x0) {
            state |= 0x20000;
        }
        if ((osState & 0x400000) != 0x0) {
            state |= 0x400000;
        }
        if ((osState & 0x1) != 0x0) {
            state |= 0x1;
        }
        return state;
    }
    
    int roleToOs(final int role) {
        switch (role) {
            case 10: {
                return 10;
            }
            case 9: {
                return 9;
            }
            case 2: {
                return 2;
            }
            case 11: {
                return 11;
            }
            case 12: {
                return 12;
            }
            case 21: {
                return 21;
            }
            case 13: {
                return 13;
            }
            case 3: {
                return 3;
            }
            case 18: {
                return 18;
            }
            case 41: {
                return 41;
            }
            case 43: {
                return 43;
            }
            case 44: {
                return 44;
            }
            case 45: {
                return 45;
            }
            case 62: {
                return 62;
            }
            case 46: {
                return 46;
            }
            case 42: {
                return 42;
            }
            case 22: {
                return 22;
            }
            case 33: {
                return 33;
            }
            case 34: {
                return 34;
            }
            case 24: {
                return 24;
            }
            case 29: {
                return 29;
            }
            case 25: {
                return 25;
            }
            case 26: {
                return 26;
            }
            case 35: {
                return 35;
            }
            case 36: {
                return 36;
            }
            case 60: {
                return 60;
            }
            case 37: {
                return 37;
            }
            case 48: {
                return 48;
            }
            case 51: {
                return 51;
            }
            case 30: {
                return 30;
            }
            case 8: {
                return 8;
            }
            case 54: {
                return 54;
            }
            case 27: {
                return 27;
            }
            case 15: {
                return 15;
            }
            case 40: {
                return 40;
            }
            case 20: {
                return 20;
            }
            case 28: {
                return 28;
            }
            case 52: {
                return 52;
            }
            case 23: {
                return 23;
            }
            case 61: {
                return 61;
            }
            case 47: {
                return 47;
            }
            case 1025: {
                return 10;
            }
            case 1027: {
                return 12;
            }
            case 1073: {
                return 12;
            }
            case 1029: {
                return 47;
            }
            case 1038: {
                return 10;
            }
            case 1040: {
                return 10;
            }
            case 1043: {
                return 10;
            }
            case 1044: {
                return 10;
            }
            case 1053: {
                return 10;
            }
            case 1054: {
                return 10;
            }
            case 1060: {
                return 10;
            }
            default: {
                return 10;
            }
        }
    }
    
    int osToRole(final int osRole) {
        switch (osRole) {
            case 10: {
                return 10;
            }
            case 9: {
                return 9;
            }
            case 2: {
                return 2;
            }
            case 11: {
                return 11;
            }
            case 12: {
                return 12;
            }
            case 21: {
                return 21;
            }
            case 13: {
                return 13;
            }
            case 3: {
                return 3;
            }
            case 18: {
                return 18;
            }
            case 41: {
                return 41;
            }
            case 43: {
                return 43;
            }
            case 44: {
                return 44;
            }
            case 45: {
                return 45;
            }
            case 62: {
                return 62;
            }
            case 46: {
                return 46;
            }
            case 42: {
                return 42;
            }
            case 22: {
                return 22;
            }
            case 33: {
                return 33;
            }
            case 34: {
                return 34;
            }
            case 24: {
                return 24;
            }
            case 29: {
                return 29;
            }
            case 25: {
                return 25;
            }
            case 26: {
                return 26;
            }
            case 35: {
                return 35;
            }
            case 36: {
                return 36;
            }
            case 60: {
                return 60;
            }
            case 37: {
                return 37;
            }
            case 48: {
                return 48;
            }
            case 51: {
                return 51;
            }
            case 30: {
                return 30;
            }
            case 8: {
                return 8;
            }
            case 54: {
                return 54;
            }
            case 27: {
                return 27;
            }
            case 15: {
                return 15;
            }
            case 40: {
                return 40;
            }
            case 20: {
                return 20;
            }
            case 28: {
                return 28;
            }
            case 52: {
                return 52;
            }
            case 23: {
                return 23;
            }
            case 61: {
                return 61;
            }
            case 47: {
                return 47;
            }
            default: {
                return 10;
            }
        }
    }
    
    Color colorFromString(final String rgbString) {
        try {
            final int open = rgbString.indexOf(40);
            final int comma1 = rgbString.indexOf(44);
            final int comma2 = rgbString.indexOf(44, comma1 + 1);
            final int close = rgbString.indexOf(41);
            final int r = Integer.parseInt(rgbString.substring(open + 1, comma1));
            final int g = Integer.parseInt(rgbString.substring(comma1 + 1, comma2));
            final int b = Integer.parseInt(rgbString.substring(comma2 + 1, close));
            return new Color(this.control.getDisplay(), r, g, b);
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    int getCaretOffset() {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.offset = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getCaretOffset(event);
        }
        if (event.offset == -1) {
            for (int i = 0; i < this.accessibleTextListenersSize(); ++i) {
                event.childID = -1;
                final AccessibleTextListener listener = this.accessibleTextListeners.get(i);
                listener.getCaretOffset(event);
            }
        }
        return event.offset;
    }
    
    int getCharacterCount() {
        final AccessibleTextEvent event = new AccessibleTextEvent(this);
        event.count = -1;
        for (int i = 0; i < this.accessibleTextExtendedListenersSize(); ++i) {
            final AccessibleTextExtendedListener listener = this.accessibleTextExtendedListeners.get(i);
            listener.getCharacterCount(event);
        }
        if (event.count == -1) {
            final AccessibleControlEvent e = new AccessibleControlEvent(this);
            e.childID = -1;
            for (int j = 0; j < this.accessibleControlListenersSize(); ++j) {
                final AccessibleControlListener listener2 = this.accessibleControlListeners.get(j);
                listener2.getRole(e);
                listener2.getValue(e);
            }
            event.count = ((e.detail == 42 && e.result != null) ? e.result.length() : 0);
        }
        return event.count;
    }
    
    int getRelationCount() {
        int count = 0;
        for (int type = 0; type < 15; ++type) {
            if (this.relations[type] != null) {
                ++count;
            }
        }
        return count;
    }
    
    int getRole() {
        final AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = -1;
        for (int i = 0; i < this.accessibleControlListenersSize(); ++i) {
            final AccessibleControlListener listener = this.accessibleControlListeners.get(i);
            listener.getRole(event);
        }
        return event.detail;
    }
    
    int getDefaultRole() {
        int role = 10;
        if (this.iaccessible != null) {
            final long varChild = OS.GlobalAlloc(64, VARIANT.sizeof);
            this.setIntVARIANT(varChild, (short)3, 0);
            final long pvarRole = OS.GlobalAlloc(64, VARIANT.sizeof);
            final int code = this.iaccessible.get_accRole(varChild, pvarRole);
            if (code == 0) {
                final VARIANT v = this.getVARIANT(pvarRole);
                if (v.vt == 3) {
                    role = v.lVal;
                }
            }
            OS.GlobalFree(varChild);
            OS.GlobalFree(pvarRole);
        }
        return role;
    }
    
    String getString(final long psz) {
        final long[] ptr = { 0L };
        OS.MoveMemory(ptr, psz, C.PTR_SIZEOF);
        final int size = COM.SysStringByteLen(ptr[0]);
        if (size == 0) {
            return "";
        }
        final char[] buffer = new char[(size + 1) / 2];
        OS.MoveMemory(buffer, ptr[0], size);
        return new String(buffer);
    }
    
    VARIANT getVARIANT(final long variant) {
        final VARIANT v = new VARIANT();
        COM.MoveMemory(v, variant, VARIANT.sizeof);
        return v;
    }
    
    Number getNumberVARIANT(final long variant) {
        final VARIANT v = new VARIANT();
        COM.MoveMemory(v, variant, VARIANT.sizeof);
        if (v.vt == 20) {
            return v.lVal;
        }
        return v.lVal;
    }
    
    void setIntVARIANT(final long variant, final short vt, final int lVal) {
        if (vt == 3 || vt == 0) {
            OS.MoveMemory(variant, new short[] { vt }, 2);
            OS.MoveMemory(variant + 8L, new int[] { lVal }, 4);
        }
    }
    
    void setPtrVARIANT(final long variant, final short vt, final long lVal) {
        if (vt == 9 || vt == 13) {
            OS.MoveMemory(variant, new short[] { vt }, 2);
            OS.MoveMemory(variant + 8L, new long[] { lVal }, C.PTR_SIZEOF);
        }
    }
    
    void setNumberVARIANT(final long variant, final Number number) {
        if (number == null) {
            OS.MoveMemory(variant, new short[] { 0 }, 2);
            OS.MoveMemory(variant + 8L, new int[] { 0 }, 4);
        }
        else if (number instanceof Double) {
            OS.MoveMemory(variant, new short[] { 5 }, 2);
            OS.MoveMemory(variant + 8L, new double[] { number.doubleValue() }, 8);
        }
        else if (number instanceof Float) {
            OS.MoveMemory(variant, new short[] { 4 }, 2);
            OS.MoveMemory(variant + 8L, new float[] { number.floatValue() }, 4);
        }
        else if (number instanceof Long) {
            OS.MoveMemory(variant, new short[] { 20 }, 2);
            OS.MoveMemory(variant + 8L, new long[] { number.longValue() }, 8);
        }
        else {
            OS.MoveMemory(variant, new short[] { 3 }, 2);
            OS.MoveMemory(variant + 8L, new int[] { number.intValue() }, 4);
        }
    }
    
    void setString(final long psz, final String string) {
        long ptr = 0L;
        if (string != null) {
            final char[] data = string.toCharArray();
            ptr = COM.SysAllocString(data);
        }
        OS.MoveMemory(psz, new long[] { ptr }, C.PTR_SIZEOF);
    }
    
    void setStringVARIANT(final long variant, final String string) {
        long ptr = 0L;
        if (string != null) {
            final char[] data = string.toCharArray();
            ptr = COM.SysAllocString(data);
        }
        OS.MoveMemory(variant, new short[] { (short)((ptr == 0L) ? 0 : 8) }, 2);
        OS.MoveMemory(variant + 8L, new long[] { ptr }, C.PTR_SIZEOF);
    }
    
    void checkWidget() {
        if (!this.isValidThread()) {
            SWT.error(22);
        }
        if (this.control.isDisposed()) {
            SWT.error(24);
        }
    }
    
    boolean isATRunning() {
        return true;
    }
    
    boolean isValidThread() {
        return this.control.getDisplay().getThread() == Thread.currentThread();
    }
    
    static void print(final String str) {
    }
    
    String getRoleString(final int role) {
        return "Unknown role (" + role;
    }
    
    String getStateString(final int state) {
        if (state == 0) {
            return " no state bits set";
        }
        final StringBuilder stateString = new StringBuilder();
        return stateString.toString();
    }
    
    String getIA2StatesString(final int ia2States) {
        if (ia2States == 0) {
            return " no state bits set";
        }
        final StringBuilder stateString = new StringBuilder();
        return stateString.toString();
    }
    
    String getEventString(final int event) {
        return "Unknown event (" + event;
    }
    
    private String hresult(final int code) {
        return " HRESULT=" + code;
    }
    
    boolean interesting(final GUID guid) {
        return false;
    }
    
    String guidString(final GUID guid) {
        return guid.toString();
    }
    
    static GUID IIDFromString(final String lpsz) {
        return null;
    }
    
    @Override
    public String toString() {
        final String toString = super.toString();
        return toString;
    }
    
    static {
        Accessible.UseIA2 = true;
        Accessible.UniqueID = -16;
        final String property = System.getProperty("org.eclipse.swt.accessibility.UseIA2");
        if (property != null && property.equalsIgnoreCase("false")) {
            Accessible.UseIA2 = false;
        }
    }
}
