//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import java.awt.image.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;

public class EffectUtil
{
    private static BufferedImage scratchImage;
    
    public static BufferedImage getScratchImage() {
        final Graphics2D g = (Graphics2D)EffectUtil.scratchImage.getGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, 256, 256);
        g.setComposite(AlphaComposite.SrcOver);
        g.setColor(Color.white);
        return EffectUtil.scratchImage;
    }
    
    public static ConfigurableEffect.Value colorValue(final String name, final Color currentValue) {
        final class lIIll extends DefaultValue
        {
            lIIll(final String name, final String value) {
                super(name, value);
            }
            
            public void showDialog() {
                final Color newColor = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
                if (newColor != null) {
                    this.value = EffectUtil.toString(newColor);
                }
            }
            
            public Object getObject() {
                return EffectUtil.fromString(this.value);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* name */
        //     5: aload_1         /* currentValue */
        //     6: invokestatic    org/newdawn/slick/font/effects/EffectUtil.toString:(Ljava/awt/Color;)Ljava/lang/String;
        //     9: invokespecial   org/newdawn/slick/font/effects/lIIll.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    12: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static ConfigurableEffect.Value intValue(final String name, final int currentValue, final String description) {
        final class llIl extends DefaultValue
        {
            final /* synthetic */ int val$currentValue;
            final /* synthetic */ String val$description;
            
            llIl(final String name, final String value, final int val$currentValue, final String val$description) {
                this.val$currentValue = val$currentValue;
                this.val$description = val$description;
                super(name, value);
            }
            
            public void showDialog() {
                final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, -32768, 32767, 1));
                if (this.showValueDialog(spinner, this.val$description)) {
                    this.value = String.valueOf(spinner.getValue());
                }
            }
            
            public Object getObject() {
                return Integer.valueOf(this.value);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* name */
        //     5: iload_1         /* currentValue */
        //     6: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //     9: iload_1         /* currentValue */
        //    10: aload_2         /* description */
        //    11: invokespecial   org/newdawn/slick/font/effects/llIl.<init>:(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
        //    14: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static ConfigurableEffect.Value floatValue(final String name, final float currentValue, final float min, final float max, final String description) {
        final class lIIIl extends DefaultValue
        {
            final /* synthetic */ float val$currentValue;
            final /* synthetic */ float val$min;
            final /* synthetic */ float val$max;
            final /* synthetic */ String val$description;
            
            lIIIl(final String name, final String value, final float val$currentValue, final float val$min, final float val$max, final String val$description) {
                this.val$currentValue = val$currentValue;
                this.val$min = val$min;
                this.val$max = val$max;
                this.val$description = val$description;
                super(name, value);
            }
            
            public void showDialog() {
                final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, this.val$min, this.val$max, 0.10000000149011612));
                if (this.showValueDialog(spinner, this.val$description)) {
                    this.value = String.valueOf(((Double)spinner.getValue()).floatValue());
                }
            }
            
            public Object getObject() {
                return Float.valueOf(this.value);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* name */
        //     5: fload_1         /* currentValue */
        //     6: invokestatic    java/lang/String.valueOf:(F)Ljava/lang/String;
        //     9: fload_1         /* currentValue */
        //    10: fload_2         /* min */
        //    11: fload_3         /* max */
        //    12: aload           description
        //    14: invokespecial   org/newdawn/slick/font/effects/lIIIl.<init>:(Ljava/lang/String;Ljava/lang/String;FFFLjava/lang/String;)V
        //    17: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static ConfigurableEffect.Value booleanValue(final String name, final boolean currentValue, final String description) {
        final class lIll extends DefaultValue
        {
            final /* synthetic */ boolean val$currentValue;
            final /* synthetic */ String val$description;
            
            lIll(final String name, final String value, final boolean val$currentValue, final String val$description) {
                this.val$currentValue = val$currentValue;
                this.val$description = val$description;
                super(name, value);
            }
            
            public void showDialog() {
                final JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(this.val$currentValue);
                if (this.showValueDialog(checkBox, this.val$description)) {
                    this.value = String.valueOf(checkBox.isSelected());
                }
            }
            
            public Object getObject() {
                return Boolean.valueOf(this.value);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* name */
        //     5: iload_1         /* currentValue */
        //     6: invokestatic    java/lang/String.valueOf:(Z)Ljava/lang/String;
        //     9: iload_1         /* currentValue */
        //    10: aload_2         /* description */
        //    11: invokespecial   org/newdawn/slick/font/effects/lIll.<init>:(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V
        //    14: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static ConfigurableEffect.Value optionValue(final String name, final String currentValue, final String[][] options, final String description) {
        final class lIIlI extends DefaultValue
        {
            final /* synthetic */ String[][] val$options;
            final /* synthetic */ String val$currentValue;
            final /* synthetic */ String val$description;
            
            lIIlI(final String name, final String value, final String[][] val$options, final String val$currentValue, final String val$description) {
                this.val$options = val$options;
                this.val$currentValue = val$currentValue;
                this.val$description = val$description;
                super(name, value);
            }
            
            public void showDialog() {
                int selectedIndex = -1;
                final DefaultComboBoxModel model = new DefaultComboBoxModel();
                for (int i = 0; i < this.val$options.length; ++i) {
                    model.addElement(this.val$options[i][0]);
                    if (this.getValue(i).equals(this.val$currentValue)) {
                        selectedIndex = i;
                    }
                }
                final JComboBox comboBox = new JComboBox(model);
                comboBox.setSelectedIndex(selectedIndex);
                if (this.showValueDialog(comboBox, this.val$description)) {
                    this.value = this.getValue(comboBox.getSelectedIndex());
                }
            }
            
            private String getValue(final int i) {
                if (this.val$options[i].length == 1) {
                    return this.val$options[i][0];
                }
                return this.val$options[i][1];
            }
            
            @Override
            public String toString() {
                for (int i = 0; i < this.val$options.length; ++i) {
                    if (this.getValue(i).equals(this.value)) {
                        return this.val$options[i][0].toString();
                    }
                }
                return "";
            }
            
            public Object getObject() {
                return this.value;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* name */
        //     5: aload_1         /* currentValue */
        //     6: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //     9: aload_2         /* options */
        //    10: aload_1         /* currentValue */
        //    11: aload_3         /* description */
        //    12: invokespecial   org/newdawn/slick/font/effects/lIIlI.<init>:(Ljava/lang/String;Ljava/lang/String;[[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    15: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String toString(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        String r = Integer.toHexString(color.getRed());
        if (r.length() == 1) {
            r = "0" + r;
        }
        String g = Integer.toHexString(color.getGreen());
        if (g.length() == 1) {
            g = "0" + g;
        }
        String b = Integer.toHexString(color.getBlue());
        if (b.length() == 1) {
            b = "0" + b;
        }
        return r + g + b;
    }
    
    public static Color fromString(final String rgb) {
        if (rgb == null || rgb.length() != 6) {
            return Color.white;
        }
        return new Color(Integer.parseInt(rgb.substring(0, 2), 16), Integer.parseInt(rgb.substring(2, 4), 16), Integer.parseInt(rgb.substring(4, 6), 16));
    }
    
    static {
        EffectUtil.scratchImage = new BufferedImage(256, 256, 2);
    }
    
    private abstract static class DefaultValue implements ConfigurableEffect.Value
    {
        String value;
        String name;
        
        public DefaultValue(final String name, final String value) {
            this.value = value;
            this.name = name;
        }
        
        public void setString(final String value) {
            this.value = value;
        }
        
        public String getString() {
            return this.value;
        }
        
        public String getName() {
            return this.name;
        }
        
        @Override
        public String toString() {
            if (this.value == null) {
                return "";
            }
            return this.value.toString();
        }
        
        public boolean showValueDialog(final JComponent component, final String description) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_1         /* component */
            //     5: aload_0         /* this */
            //     6: getfield        org/newdawn/slick/font/effects/EffectUtil$DefaultValue.name:Ljava/lang/String;
            //     9: aload_2         /* description */
            //    10: invokespecial   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.<init>:(Ljavax/swing/JComponent;Ljava/lang/String;Ljava/lang/String;)V
            //    13: astore_3        /* dialog */
            //    14: aload_3         /* dialog */
            //    15: aload_0         /* this */
            //    16: getfield        org/newdawn/slick/font/effects/EffectUtil$DefaultValue.name:Ljava/lang/String;
            //    19: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setTitle:(Ljava/lang/String;)V
            //    22: aload_3         /* dialog */
            //    23: aconst_null    
            //    24: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setLocationRelativeTo:(Ljava/awt/Component;)V
            //    27: new             Lorg/newdawn/slick/font/effects/lllI;
            //    30: dup            
            //    31: aload_0         /* this */
            //    32: aload_1         /* component */
            //    33: invokespecial   org/newdawn/slick/font/effects/lllI.<init>:(Lorg/newdawn/slick/font/effects/EffectUtil$DefaultValue;Ljavax/swing/JComponent;)V
            //    36: invokestatic    java/awt/EventQueue.invokeLater:(Ljava/lang/Runnable;)V
            //    39: aload_3         /* dialog */
            //    40: iconst_1       
            //    41: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setVisible:(Z)V
            //    44: aload_3         /* dialog */
            //    45: getfield        org/newdawn/slick/font/effects/EffectUtil$ValueDialog.okPressed:Z
            //    48: ireturn        
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    private static class ValueDialog extends JDialog
    {
        public boolean okPressed;
        
        public ValueDialog(final JComponent component, final String name, final String description) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokespecial   javax/swing/JDialog.<init>:()V
            //     4: aload_0         /* this */
            //     5: iconst_0       
            //     6: putfield        org/newdawn/slick/font/effects/EffectUtil$ValueDialog.okPressed:Z
            //     9: aload_0         /* this */
            //    10: iconst_2       
            //    11: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setDefaultCloseOperation:(I)V
            //    14: aload_0         /* this */
            //    15: new             Ljava/awt/GridBagLayout;
            //    18: dup            
            //    19: invokespecial   java/awt/GridBagLayout.<init>:()V
            //    22: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setLayout:(Ljava/awt/LayoutManager;)V
            //    25: aload_0         /* this */
            //    26: iconst_1       
            //    27: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setModal:(Z)V
            //    30: aload_1         /* component */
            //    31: instanceof      Ljavax/swing/JSpinner;
            //    34: ifeq            54
            //    37: aload_1         /* component */
            //    38: checkcast       Ljavax/swing/JSpinner;
            //    41: invokevirtual   javax/swing/JSpinner.getEditor:()Ljavax/swing/JComponent;
            //    44: checkcast       Ljavax/swing/JSpinner$DefaultEditor;
            //    47: invokevirtual   javax/swing/JSpinner$DefaultEditor.getTextField:()Ljavax/swing/JFormattedTextField;
            //    50: iconst_4       
            //    51: invokevirtual   javax/swing/JFormattedTextField.setColumns:(I)V
            //    54: new             Ljavax/swing/JPanel;
            //    57: dup            
            //    58: invokespecial   javax/swing/JPanel.<init>:()V
            //    61: astore          descriptionPanel
            //    63: aload           descriptionPanel
            //    65: new             Ljava/awt/GridBagLayout;
            //    68: dup            
            //    69: invokespecial   java/awt/GridBagLayout.<init>:()V
            //    72: invokevirtual   javax/swing/JPanel.setLayout:(Ljava/awt/LayoutManager;)V
            //    75: aload_0         /* this */
            //    76: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.getContentPane:()Ljava/awt/Container;
            //    79: aload           descriptionPanel
            //    81: new             Ljava/awt/GridBagConstraints;
            //    84: dup            
            //    85: iconst_0       
            //    86: iconst_0       
            //    87: iconst_2       
            //    88: iconst_1       
            //    89: dconst_1       
            //    90: dconst_0       
            //    91: bipush          10
            //    93: iconst_1       
            //    94: new             Ljava/awt/Insets;
            //    97: dup            
            //    98: iconst_0       
            //    99: iconst_0       
            //   100: iconst_0       
            //   101: iconst_0       
            //   102: invokespecial   java/awt/Insets.<init>:(IIII)V
            //   105: iconst_0       
            //   106: iconst_0       
            //   107: invokespecial   java/awt/GridBagConstraints.<init>:(IIIIDDIILjava/awt/Insets;II)V
            //   110: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   113: aload           descriptionPanel
            //   115: getstatic       java/awt/Color.white:Ljava/awt/Color;
            //   118: invokevirtual   javax/swing/JPanel.setBackground:(Ljava/awt/Color;)V
            //   121: aload           descriptionPanel
            //   123: iconst_0       
            //   124: iconst_0       
            //   125: iconst_1       
            //   126: iconst_0       
            //   127: getstatic       java/awt/Color.black:Ljava/awt/Color;
            //   130: invokestatic    javax/swing/BorderFactory.createMatteBorder:(IIIILjava/awt/Color;)Ljavax/swing/border/MatteBorder;
            //   133: invokevirtual   javax/swing/JPanel.setBorder:(Ljavax/swing/border/Border;)V
            //   136: new             Ljavax/swing/JTextArea;
            //   139: dup            
            //   140: aload_3         /* description */
            //   141: invokespecial   javax/swing/JTextArea.<init>:(Ljava/lang/String;)V
            //   144: astore          descriptionText
            //   146: aload           descriptionPanel
            //   148: aload           descriptionText
            //   150: new             Ljava/awt/GridBagConstraints;
            //   153: dup            
            //   154: iconst_0       
            //   155: iconst_0       
            //   156: iconst_1       
            //   157: iconst_1       
            //   158: dconst_1       
            //   159: dconst_0       
            //   160: bipush          10
            //   162: iconst_1       
            //   163: new             Ljava/awt/Insets;
            //   166: dup            
            //   167: iconst_5       
            //   168: iconst_5       
            //   169: iconst_5       
            //   170: iconst_5       
            //   171: invokespecial   java/awt/Insets.<init>:(IIII)V
            //   174: iconst_0       
            //   175: iconst_0       
            //   176: invokespecial   java/awt/GridBagConstraints.<init>:(IIIIDDIILjava/awt/Insets;II)V
            //   179: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   182: aload           descriptionText
            //   184: iconst_1       
            //   185: invokevirtual   javax/swing/JTextArea.setWrapStyleWord:(Z)V
            //   188: aload           descriptionText
            //   190: iconst_1       
            //   191: invokevirtual   javax/swing/JTextArea.setLineWrap:(Z)V
            //   194: aload           descriptionText
            //   196: iconst_0       
            //   197: iconst_0       
            //   198: iconst_0       
            //   199: iconst_0       
            //   200: invokestatic    javax/swing/BorderFactory.createEmptyBorder:(IIII)Ljavax/swing/border/Border;
            //   203: invokevirtual   javax/swing/JTextArea.setBorder:(Ljavax/swing/border/Border;)V
            //   206: aload           descriptionText
            //   208: iconst_0       
            //   209: invokevirtual   javax/swing/JTextArea.setEditable:(Z)V
            //   212: new             Ljavax/swing/JPanel;
            //   215: dup            
            //   216: invokespecial   javax/swing/JPanel.<init>:()V
            //   219: astore          panel
            //   221: aload_0         /* this */
            //   222: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.getContentPane:()Ljava/awt/Container;
            //   225: aload           panel
            //   227: new             Ljava/awt/GridBagConstraints;
            //   230: dup            
            //   231: iconst_0       
            //   232: iconst_1       
            //   233: iconst_1       
            //   234: iconst_1       
            //   235: dconst_1       
            //   236: dconst_1       
            //   237: bipush          10
            //   239: iconst_0       
            //   240: new             Ljava/awt/Insets;
            //   243: dup            
            //   244: iconst_5       
            //   245: iconst_5       
            //   246: iconst_0       
            //   247: iconst_5       
            //   248: invokespecial   java/awt/Insets.<init>:(IIII)V
            //   251: iconst_0       
            //   252: iconst_0       
            //   253: invokespecial   java/awt/GridBagConstraints.<init>:(IIIIDDIILjava/awt/Insets;II)V
            //   256: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   259: aload           panel
            //   261: new             Ljavax/swing/JLabel;
            //   264: dup            
            //   265: new             Ljava/lang/StringBuilder;
            //   268: dup            
            //   269: invokespecial   java/lang/StringBuilder.<init>:()V
            //   272: aload_2         /* name */
            //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   276: ldc             ":"
            //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   284: invokespecial   javax/swing/JLabel.<init>:(Ljava/lang/String;)V
            //   287: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   290: pop            
            //   291: aload           panel
            //   293: aload_1         /* component */
            //   294: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   297: pop            
            //   298: new             Ljavax/swing/JPanel;
            //   301: dup            
            //   302: invokespecial   javax/swing/JPanel.<init>:()V
            //   305: astore          buttonPanel
            //   307: aload_0         /* this */
            //   308: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.getContentPane:()Ljava/awt/Container;
            //   311: aload           buttonPanel
            //   313: new             Ljava/awt/GridBagConstraints;
            //   316: dup            
            //   317: iconst_0       
            //   318: iconst_2       
            //   319: iconst_2       
            //   320: iconst_1       
            //   321: dconst_0       
            //   322: dconst_0       
            //   323: bipush          13
            //   325: iconst_0       
            //   326: new             Ljava/awt/Insets;
            //   329: dup            
            //   330: iconst_0       
            //   331: iconst_0       
            //   332: iconst_0       
            //   333: iconst_0       
            //   334: invokespecial   java/awt/Insets.<init>:(IIII)V
            //   337: iconst_0       
            //   338: iconst_0       
            //   339: invokespecial   java/awt/GridBagConstraints.<init>:(IIIIDDIILjava/awt/Insets;II)V
            //   342: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;Ljava/lang/Object;)V
            //   345: new             Ljavax/swing/JButton;
            //   348: dup            
            //   349: ldc             "OK"
            //   351: invokespecial   javax/swing/JButton.<init>:(Ljava/lang/String;)V
            //   354: astore          okButton
            //   356: aload           buttonPanel
            //   358: aload           okButton
            //   360: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   363: pop            
            //   364: aload           okButton
            //   366: new             Lorg/newdawn/slick/font/effects/lIlI;
            //   369: dup            
            //   370: aload_0         /* this */
            //   371: invokespecial   org/newdawn/slick/font/effects/lIlI.<init>:(Lorg/newdawn/slick/font/effects/EffectUtil$ValueDialog;)V
            //   374: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   377: new             Ljavax/swing/JButton;
            //   380: dup            
            //   381: ldc             "Cancel"
            //   383: invokespecial   javax/swing/JButton.<init>:(Ljava/lang/String;)V
            //   386: astore          cancelButton
            //   388: aload           buttonPanel
            //   390: aload           cancelButton
            //   392: invokevirtual   javax/swing/JPanel.add:(Ljava/awt/Component;)Ljava/awt/Component;
            //   395: pop            
            //   396: aload           cancelButton
            //   398: new             Lorg/newdawn/slick/font/effects/llll;
            //   401: dup            
            //   402: aload_0         /* this */
            //   403: invokespecial   org/newdawn/slick/font/effects/llll.<init>:(Lorg/newdawn/slick/font/effects/EffectUtil$ValueDialog;)V
            //   406: invokevirtual   javax/swing/JButton.addActionListener:(Ljava/awt/event/ActionListener;)V
            //   409: aload_0         /* this */
            //   410: new             Ljava/awt/Dimension;
            //   413: dup            
            //   414: sipush          320
            //   417: sipush          175
            //   420: invokespecial   java/awt/Dimension.<init>:(II)V
            //   423: invokevirtual   org/newdawn/slick/font/effects/EffectUtil$ValueDialog.setSize:(Ljava/awt/Dimension;)V
            //   426: return         
            //    StackMapTable: 00 01 FF 00 36 00 04 07 00 02 07 00 38 07 00 3A 07 00 3A 00 00
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
}
