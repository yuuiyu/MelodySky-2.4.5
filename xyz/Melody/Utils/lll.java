//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import chrriis.dj.nativeswing.*;
import javax.swing.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lll implements Runnable
{
    final /* synthetic */ String val$title;
    final /* synthetic */ String val$url;
    final /* synthetic */ NSOption[] val$runtime;
    final /* synthetic */ boolean val$visible;
    final /* synthetic */ boolean val$onTop;
    final /* synthetic */ boolean val$resizable;
    final /* synthetic */ int val$w;
    final /* synthetic */ int val$h;
    final /* synthetic */ Browser this$0;
    
    lll(final Browser this$0, final String val$title, final String val$url, final NSOption[] val$runtime, final boolean val$visible, final boolean val$onTop, final boolean val$resizable, final int val$w, final int val$h) {
        this.this$0 = this$0;
        this.val$title = val$title;
        this.val$url = val$url;
        this.val$runtime = val$runtime;
        this.val$visible = val$visible;
        this.val$onTop = val$onTop;
        this.val$resizable = val$resizable;
        this.val$w = val$w;
        this.val$h = val$h;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //     4: new             Ljavax/swing/JFrame;
        //     7: dup            
        //     8: aload_0         /* this */
        //     9: getfield        xyz/Melody/Utils/lll.val$title:Ljava/lang/String;
        //    12: invokespecial   javax/swing/JFrame.<init>:(Ljava/lang/String;)V
        //    15: invokestatic    xyz/Melody/Utils/Browser.access$002:(Lxyz/Melody/Utils/Browser;Ljavax/swing/JFrame;)Ljavax/swing/JFrame;
        //    18: pop            
        //    19: aload_0         /* this */
        //    20: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //    23: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //    26: iconst_2       
        //    27: invokevirtual   javax/swing/JFrame.setDefaultCloseOperation:(I)V
        //    30: invokestatic    chrriis/dj/nativeswing/swtimpl/NativeInterface.isOpen:()Z
        //    33: ifne            39
        //    36: invokestatic    chrriis/dj/nativeswing/swtimpl/NativeInterface.open:()V
        //    39: aload_0         /* this */
        //    40: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //    43: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //    46: new             Lxyz/Melody/Utils/lII;
        //    49: dup            
        //    50: aload_0         /* this */
        //    51: invokespecial   xyz/Melody/Utils/lII.<init>:(Lxyz/Melody/Utils/lll;)V
        //    54: invokevirtual   javax/swing/JFrame.addWindowListener:(Ljava/awt/event/WindowListener;)V
        //    57: new             Lxyz/Melody/Utils/Browser;
        //    60: dup            
        //    61: aload_0         /* this */
        //    62: getfield        xyz/Melody/Utils/lll.val$url:Ljava/lang/String;
        //    65: aload_0         /* this */
        //    66: getfield        xyz/Melody/Utils/lll.val$runtime:[Lchrriis/dj/nativeswing/NSOption;
        //    69: aconst_null    
        //    70: invokespecial   xyz/Melody/Utils/Browser.<init>:(Ljava/lang/String;[Lchrriis/dj/nativeswing/NSOption;Lxyz/Melody/Utils/lll;)V
        //    73: astore_1        /* sb */
        //    74: aload_0         /* this */
        //    75: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //    78: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //    81: invokevirtual   javax/swing/JFrame.getContentPane:()Ljava/awt/Container;
        //    84: aload_1         /* sb */
        //    85: ldc             "Center"
        //    87: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //    90: aload_0         /* this */
        //    91: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //    94: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //    97: bipush          6
        //    99: invokevirtual   javax/swing/JFrame.setExtendedState:(I)V
        //   102: aload_0         /* this */
        //   103: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   106: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   109: iconst_1       
        //   110: invokevirtual   javax/swing/JFrame.setLocationByPlatform:(Z)V
        //   113: aload_0         /* this */
        //   114: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   117: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   120: aload_0         /* this */
        //   121: getfield        xyz/Melody/Utils/lll.val$visible:Z
        //   124: invokevirtual   javax/swing/JFrame.setVisible:(Z)V
        //   127: aload_0         /* this */
        //   128: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   131: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   134: aload_0         /* this */
        //   135: getfield        xyz/Melody/Utils/lll.val$onTop:Z
        //   138: invokevirtual   javax/swing/JFrame.setAlwaysOnTop:(Z)V
        //   141: aload_0         /* this */
        //   142: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   145: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   148: aload_0         /* this */
        //   149: getfield        xyz/Melody/Utils/lll.val$resizable:Z
        //   152: invokevirtual   javax/swing/JFrame.setResizable:(Z)V
        //   155: aload_0         /* this */
        //   156: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   159: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   162: aload_0         /* this */
        //   163: getfield        xyz/Melody/Utils/lll.val$w:I
        //   166: aload_0         /* this */
        //   167: getfield        xyz/Melody/Utils/lll.val$h:I
        //   170: invokevirtual   javax/swing/JFrame.setSize:(II)V
        //   173: aload_0         /* this */
        //   174: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   177: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   180: aload_0         /* this */
        //   181: getfield        xyz/Melody/Utils/lll.this$0:Lxyz/Melody/Utils/Browser;
        //   184: invokestatic    xyz/Melody/Utils/Browser.access$000:(Lxyz/Melody/Utils/Browser;)Ljavax/swing/JFrame;
        //   187: invokevirtual   javax/swing/JFrame.getOwner:()Ljava/awt/Window;
        //   190: invokevirtual   javax/swing/JFrame.setLocationRelativeTo:(Ljava/awt/Component;)V
        //   193: return         
        //    StackMapTable: 00 01 27
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
