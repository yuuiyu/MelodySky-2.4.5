//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Notification;

import xyz.Melody.Utils.*;
import xyz.Melody.Utils.shader.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;
import java.util.*;
import xyz.Melody.Utils.animate.*;
import java.util.concurrent.*;

public final class NotificationPublisher
{
    private static final List<Notification> NOTIFICATIONS;
    public static TimerUtil timer;
    private static GaussianBlur gb;
    
    public static void publish(final ScaledResolution sr) {
        if (NotificationPublisher.NOTIFICATIONS.isEmpty()) {
            return;
        }
        final int srScaledHeight = sr.getScaledHeight() - 14;
        final int scaledWidth = sr.getScaledWidth();
        int y = srScaledHeight - 30;
        final CFontRenderer fr = FontLoaders.NMSL20;
        final CFontRenderer fr2 = FontLoaders.NMSL16;
        for (final Notification notification : NotificationPublisher.NOTIFICATIONS) {
            final Translate translate = notification.getTranslate();
            final float width = notification.getWidth() + 10.0f;
            if (!notification.getTimer().elapsed(notification.getTime()) && !notification.getTimer().elapsed(500L)) {
                notification.scissorBoxWidth = AnimationUtil.animate(width, notification.scissorBoxWidth, 0.2);
            }
            else if (notification.getTimer().elapsed(notification.getTime())) {
                notification.scissorBoxWidth = AnimationUtil.animate(0.0, notification.scissorBoxWidth, 0.2);
                if (notification.scissorBoxWidth < 10.0) {
                    NotificationPublisher.NOTIFICATIONS.remove(notification);
                }
                y += 32;
            }
            if (!notification.getTimer().elapsed(notification.getTime())) {
                translate.interpolate(scaledWidth - width, (float)y, 5.0);
            }
            final float translateX = translate.getX();
            final float translateY = translate.getY();
            final float appX = (float)(notification.scissorBoxWidth * 1.4);
            final ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
            final int factor = scale.getScaleFactor();
            if (!notification.getTimer().elapsed(notification.getTime())) {
                final float right = translateX + notification.getWidth() * 1.25f - appX;
                final float right2 = (float)scaledWidth;
                final float height = translateY;
                final float height2 = translateY + 30.0f;
                Scissor.start(right * factor, height * factor, right2 * factor, height2 * factor);
                NotificationPublisher.gb.renderBlur(40.0f, 1, 0, 0, 0);
                Scissor.end();
            }
            final float jbx = translateX + notification.getWidth() * 1.25f - appX;
            RenderUtil.drawFastRoundedRect(jbx, translateY, (float)scaledWidth, translateY + 30.0f, 2.0f, new Color(198, 198, 198, 100).getRGB());
            final float timer = (width + 40.0f) / notification.getTime();
            RenderUtil.drawFastRoundedRect(jbx, translateY + 30.0f - 1.0f, jbx + timer * notification.getTimer().getElapsedTime(), translateY + 30.0f + 1.0f, 2.0f, notification.getType().getColor());
            RenderUtil.drawImage(notification.getType().getIcon(), translateX + notification.getWidth() * 1.25f - appX + 7.0f, translateY + 5.0f, 20.0f, 20.0f);
            final float shabi = translateX + notification.getWidth() * 1.25f - appX + 7.0f + 25.0f;
            fr.drawString(notification.getTitle(), shabi + 3.0f, translateY + 5.0f, -1);
            fr2.drawString(notification.getContent(), shabi + 3.0f, translateY + 18.0f, -1);
            y -= 32;
        }
    }
    
    public static void queue(final String title, final String content, final NotificationType type, final int time, final boolean showTime) {
        final CFontRenderer fr = FontLoaders.NMSL16;
        NotificationPublisher.NOTIFICATIONS.add(new Notification(title, content, type, fr, time, showTime));
    }
    
    public static void queue(final String title, final String content, final NotificationType type, final int time) {
        final CFontRenderer fr = FontLoaders.NMSL16;
        NotificationPublisher.NOTIFICATIONS.add(new Notification(title, content, type, fr, time));
    }
    
    static {
        NOTIFICATIONS = new CopyOnWriteArrayList<Notification>();
        NotificationPublisher.gb = new GaussianBlur();
    }
}
