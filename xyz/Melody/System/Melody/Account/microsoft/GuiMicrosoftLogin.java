//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.microsoft;

import xyz.Melody.Utils.*;
import xyz.Melody.Utils.shader.*;
import net.minecraft.client.gui.*;
import java.io.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public final class GuiMicrosoftLogin extends GuiScreen
{
    private volatile MicrosoftLogin microsoftLogin;
    private volatile boolean closed;
    private volatile boolean done;
    private final GuiScreen parentScreen;
    private String message;
    private TimerUtil timer;
    private GaussianBlur gb;
    private String[] s;
    
    public GuiMicrosoftLogin(final GuiScreen parentScreen) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   net/minecraft/client/gui/GuiScreen.<init>:()V
        //     4: aload_0         /* this */
        //     5: iconst_0       
        //     6: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.closed:Z
        //     9: aload_0         /* this */
        //    10: iconst_0       
        //    11: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.done:Z
        //    14: aload_0         /* this */
        //    15: ldc             "Initializing..."
        //    17: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.message:Ljava/lang/String;
        //    20: aload_0         /* this */
        //    21: new             Lxyz/Melody/Utils/TimerUtil;
        //    24: dup            
        //    25: invokespecial   xyz/Melody/Utils/TimerUtil.<init>:()V
        //    28: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.timer:Lxyz/Melody/Utils/TimerUtil;
        //    31: aload_0         /* this */
        //    32: new             Lxyz/Melody/Utils/shader/GaussianBlur;
        //    35: dup            
        //    36: invokespecial   xyz/Melody/Utils/shader/GaussianBlur.<init>:()V
        //    39: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.gb:Lxyz/Melody/Utils/shader/GaussianBlur;
        //    42: aload_0         /* this */
        //    43: iconst_5       
        //    44: anewarray       Ljava/lang/String;
        //    47: dup            
        //    48: iconst_0       
        //    49: ldc             "Stage 1: "
        //    51: aastore        
        //    52: dup            
        //    53: iconst_1       
        //    54: ldc             "Stage 2: "
        //    56: aastore        
        //    57: dup            
        //    58: iconst_2       
        //    59: ldc             "Stage 3: "
        //    61: aastore        
        //    62: dup            
        //    63: iconst_3       
        //    64: ldc             "Stage 4: "
        //    66: aastore        
        //    67: dup            
        //    68: iconst_4       
        //    69: ldc             "Stage 5: "
        //    71: aastore        
        //    72: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.s:[Ljava/lang/String;
        //    75: aload_0         /* this */
        //    76: aload_1         /* parentScreen */
        //    77: putfield        xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.parentScreen:Lnet/minecraft/client/gui/GuiScreen;
        //    80: new             Lxyz/Melody/System/Melody/Account/microsoft/ll;
        //    83: dup            
        //    84: aload_0         /* this */
        //    85: ldc             "MicrosoftLogin Thread"
        //    87: invokespecial   xyz/Melody/System/Melody/Account/microsoft/ll.<init>:(Lxyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin;Ljava/lang/String;)V
        //    90: invokevirtual   xyz/Melody/System/Melody/Account/microsoft/ll.start:()V
        //    93: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 0) {
            if (this.microsoftLogin != null) {
                this.microsoftLogin.bruhSir.close();
            }
            if (this.microsoftLogin != null && !this.closed) {
                this.microsoftLogin.close();
                this.closed = true;
            }
            if (this.closed) {
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }
    
    public void initGui() {
        this.buttonList.clear();
        if (this.done) {
            this.buttonList.add(new ClientButton(0, this.width / 2 - 75, this.height / 2 + 20, 150, 20, "Back", new Color(0, 0, 0, 110)));
        }
        else {
            this.buttonList.add(new ClientButton(0, this.width / 2 - 75, this.height / 2 + 20, 150, 20, "Cancle", new Color(0, 0, 0, 110)));
        }
        super.initGui();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.microsoftLogin != null) {
            if (this.microsoftLogin.stage == 1) {
                this.s[0] = "Stage 1: " + this.microsoftLogin.status;
            }
            if (this.microsoftLogin.stage == 2) {
                this.s[1] = "Stage 2: " + this.microsoftLogin.status;
            }
            if (this.microsoftLogin.stage == 3) {
                this.s[2] = "Stage 3: " + this.microsoftLogin.status;
            }
            if (this.microsoftLogin.stage == 4) {
                this.s[3] = "Stage 4: " + this.microsoftLogin.status;
            }
            if (this.microsoftLogin.stage == 5) {
                this.s[4] = "Stage 5: " + this.microsoftLogin.status;
            }
            if (this.microsoftLogin.stage == 0) {
                this.mc.fontRendererObj.drawStringWithShadow(this.microsoftLogin.status, this.width / 2.0f - this.mc.fontRendererObj.getStringWidth(this.microsoftLogin.status) / 2, this.height / 2.0f - 5.0f, -1);
            }
            else {
                for (int i = this.s.length - 1; i >= 0; --i) {
                    final String str = (i == this.microsoftLogin.stage - 1) ? (EnumChatFormatting.GREEN + this.s[i]) : this.s[i];
                    this.mc.fontRendererObj.drawStringWithShadow(str, this.width / 2.0f - this.mc.fontRendererObj.getStringWidth(str) / 2, this.height / 2.0f - 5.0f - i * 12, -1);
                }
                if (this.microsoftLogin.stage == 5 && !this.done) {
                    this.done = true;
                    this.initGui();
                }
            }
        }
        else {
            this.mc.fontRendererObj.drawStringWithShadow(this.message, this.width / 2.0f - this.mc.fontRendererObj.getStringWidth(this.message) / 2, this.height / 2.0f - 5.0f, -1);
        }
    }
    
    public void drawDefaultBackground() {
        BackgroundShader.BACKGROUND_SHADER.startShader();
        final Tessellator instance = Tessellator.getInstance();
        final WorldRenderer worldRenderer = instance.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(0.0, (double)this.height, 0.0).endVertex();
        worldRenderer.pos((double)this.width, (double)this.height, 0.0).endVertex();
        worldRenderer.pos((double)this.width, 0.0, 0.0).endVertex();
        worldRenderer.pos(0.0, 0.0, 0.0).endVertex();
        instance.draw();
        BackgroundShader.BACKGROUND_SHADER.stopShader();
        this.gb.renderBlur(140.0f);
    }
}
