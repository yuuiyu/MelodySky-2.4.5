//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class UIUtils
{
    private static String COMPONENT_TRANSPARENT_CLIENT_PROPERTY;
    
    private UIUtils() {
    }
    
    public static Rectangle[] subtract(final Rectangle[] rects, final Rectangle rect) {
        return subtract(rects, new Rectangle[] { rect });
    }
    
    public static Rectangle[] subtract(final Rectangle[] rects1, final Rectangle[] rects2) {
        final List<Rectangle> rectangleList = new ArrayList<Rectangle>(Arrays.asList(rects1));
        final List<Rectangle> newRectangleList = new ArrayList<Rectangle>();
        for (int i = 0; i < rects2.length; ++i) {
            final Rectangle r2 = rects2[i];
            for (final Rectangle r3 : rectangleList) {
                if (r3.intersects(r2)) {
                    subtract(r3, r2, newRectangleList);
                }
                else {
                    newRectangleList.add((Rectangle)r3.clone());
                }
            }
            rectangleList.clear();
            if (newRectangleList.isEmpty()) {
                break;
            }
            rectangleList.addAll(newRectangleList);
            newRectangleList.clear();
        }
        return rectangleList.toArray(new Rectangle[0]);
    }
    
    private static void subtract(final Rectangle r1, final Rectangle r2, final List<Rectangle> resultList) {
        final boolean left = r2.x <= r1.x && r2.x + r2.width > r1.x;
        final boolean right = r2.x < r1.x + r1.width && r2.x + r2.width >= r1.x + r1.width;
        final boolean top = r2.y <= r1.y && r2.y + r2.height > r1.y;
        final boolean bottom = r2.y < r1.y + r1.height && r2.y + r2.height >= r1.y + r1.height;
        if (!left || !right || !top || !bottom) {
            if (left && right && top) {
                final int y = r2.y + r2.height;
                final int height = r1.y + r1.height - y;
                resultList.add(new Rectangle(r1.x, y, r1.width, height));
            }
            else if (left && right && bottom) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
            }
            else if (top && bottom && left) {
                final int x = r2.x + r2.width;
                final int width = r1.x + r1.width - x;
                resultList.add(new Rectangle(x, r1.y, width, r1.height));
            }
            else if (top && bottom && right) {
                resultList.add(new Rectangle(r1.x, r1.y, r2.x - r1.x, r1.height));
            }
            else if (left && top) {
                final int x = r2.x + r2.width;
                final int y2 = r2.y + r2.height;
                resultList.add(new Rectangle(x, r1.y, r1.x + r1.width - x, y2 - r1.y));
                resultList.add(new Rectangle(r1.x, y2, r1.width, r1.y + r1.height - y2));
            }
            else if (left && bottom) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int x = r2.x + r2.width;
                resultList.add(new Rectangle(x, r2.y, r1.x + r1.width - x, r1.y + r1.height - r2.y));
            }
            else if (right && top) {
                final int y = r2.y + r2.height;
                resultList.add(new Rectangle(r1.x, r1.y, r2.x - r1.x, y - r1.y));
                resultList.add(new Rectangle(r1.x, y, r1.width, r1.y + r1.height - y));
            }
            else if (right && bottom) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                resultList.add(new Rectangle(r1.x, r2.y, r2.x - r1.x, r1.y + r1.height - r2.y));
            }
            else if (left && right) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int y = r2.y + r2.height;
                resultList.add(new Rectangle(r1.x, y, r1.width, r1.y + r1.height - y));
            }
            else if (top && bottom) {
                resultList.add(new Rectangle(r1.x, r1.y, r2.x - r1.x, r1.height));
                final int x = r2.x + r2.width;
                resultList.add(new Rectangle(x, r1.y, r1.x + r1.width - x, r1.height));
            }
            else if (left) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int y = r2.y + r2.height;
                resultList.add(new Rectangle(r1.x, y, r1.width, r1.y + r1.height - y));
                final int x2 = r2.x + r2.width;
                resultList.add(new Rectangle(x2, r2.y, r1.x + r1.width - x2, r2.height));
            }
            else if (right) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int y = r2.y + r2.height;
                resultList.add(new Rectangle(r1.x, y, r1.width, r1.y + r1.height - y));
                resultList.add(new Rectangle(r1.x, r2.y, r2.x - r1.x, r2.height));
            }
            else if (top) {
                resultList.add(new Rectangle(r1.x, r1.y, r2.x - r1.x, r1.height));
                final int x = r2.x + r2.width;
                resultList.add(new Rectangle(x, r1.y, r1.x + r1.width - x, r1.height));
                final int y2 = r2.y + r2.height;
                resultList.add(new Rectangle(r2.x, y2, r2.width, r1.y + r1.height - y2));
            }
            else if (bottom) {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int height2 = r1.y + r1.height - r2.y;
                resultList.add(new Rectangle(r1.x, r2.y, r2.x - r1.x, height2));
                final int x2 = r2.x + r2.width;
                resultList.add(new Rectangle(x2, r2.y, r1.x + r1.width - x2, height2));
            }
            else {
                resultList.add(new Rectangle(r1.x, r1.y, r1.width, r2.y - r1.y));
                final int y = r2.y + r2.height;
                resultList.add(new Rectangle(r1.x, y, r1.width, r1.y + r1.height - y));
                resultList.add(new Rectangle(r1.x, r2.y, r2.x - r1.x, r2.height));
                final int x2 = r2.x + r2.width;
                resultList.add(new Rectangle(x2, r2.y, r1.x + r1.width - x2, r2.height));
            }
        }
    }
    
    public static Rectangle[] getComponentVisibleArea(final Component component, final Filter<Component> filter) {
        final Window windowAncestor = SwingUtilities.getWindowAncestor(component);
        final int width = component.getWidth();
        final int height = component.getHeight();
        if (windowAncestor == null || !component.isShowing() || width <= 0 || height <= 0) {
            return new Rectangle[0];
        }
        final Rectangle tempRectangle = new Rectangle(0, 0, width, height);
        Rectangle[] shape = { new Rectangle(width, height) };
        if (component instanceof Container) {
            final Container container = (Container)component;
            for (int i = container.getComponentCount() - 1; i >= 0; --i) {
                final Component c = container.getComponent(i);
                if (c.isVisible()) {
                    switch (lIlII.$SwitchMap$chrriis$dj$nativeswing$common$Filter$Acceptance[filter.accept((Object)c).ordinal()]) {
                        case 1: {
                            tempRectangle.setBounds(c.getX(), c.getY(), c.getWidth(), c.getHeight());
                            shape = subtract(shape, tempRectangle);
                            break;
                        }
                        case 2: {
                            if (c instanceof Container) {
                                shape = getChildrenVisibleArea(component, filter, shape, (Container)c, null);
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (shape.length == 0) {
            return shape;
        }
        Component c2 = component;
        for (Container parent = c2.getParent(); parent != null && !(parent instanceof Window); parent = c2.getParent()) {
            final Dimension parentSize = parent.getSize();
            tempRectangle.setBounds(0, 0, parentSize.width, parentSize.height);
            final Rectangle parentBounds = SwingUtilities.convertRectangle(parent, tempRectangle, component);
            final List<Rectangle> newRectangleList = new ArrayList<Rectangle>();
            for (final Rectangle rectangle : shape) {
                final Rectangle r = rectangle.intersection(parentBounds);
                if (!r.isEmpty()) {
                    newRectangleList.add(r);
                }
            }
            shape = newRectangleList.toArray(new Rectangle[0]);
            if (parent instanceof JComponent && !((JComponent)parent).isOptimizedDrawingEnabled()) {
                shape = getChildrenVisibleArea(component, filter, shape, parent, c2);
            }
            if (shape.length == 0) {
                return shape;
            }
            c2 = parent;
        }
        return shape;
    }
    
    public static void setComponentTransparencyHint(final Component c, final TransparencyType transparencyType) {
        if (c instanceof JComponent) {
            ((JComponent)c).putClientProperty(UIUtils.COMPONENT_TRANSPARENT_CLIENT_PROPERTY, transparencyType);
        }
    }
    
    public static TransparencyType getComponentTransparency(final Component c) {
        if (!(c instanceof JComponent) || c.isOpaque()) {
            return TransparencyType.OPAQUE;
        }
        final TransparencyType transparencyType = (TransparencyType)((JComponent)c).getClientProperty(UIUtils.COMPONENT_TRANSPARENT_CLIENT_PROPERTY);
        if (transparencyType != null) {
            return transparencyType;
        }
        final Container parent = c.getParent();
        if (parent instanceof JRootPane && ((JRootPane)parent).getGlassPane() == c) {
            return TransparencyType.TRANSPARENT_WITH_OPAQUE_CHILDREN;
        }
        return TransparencyType.OPAQUE;
    }
    
    private static Rectangle[] getChildrenVisibleArea(final Component component, final Filter<Component> filter, Rectangle[] shape, final Container parent, final Component c) {
        Component[] children;
        if (parent instanceof JLayeredPane) {
            final JLayeredPane layeredPane = (JLayeredPane)parent;
            final List<Component> childList = new ArrayList<Component>(layeredPane.getComponentCount() - 1);
            for (int layer = (c == null) ? Integer.MIN_VALUE : layeredPane.getLayer(c), i = layeredPane.highestLayer(); i >= layer; --i) {
                final Component[] componentsInLayer;
                final Component[] components = componentsInLayer = layeredPane.getComponentsInLayer(i);
                for (final Component child : componentsInLayer) {
                    if (child == c) {
                        break;
                    }
                    childList.add(child);
                }
            }
            children = childList.toArray(new Component[0]);
        }
        else {
            children = parent.getComponents();
        }
        final Rectangle tempRectangle = new Rectangle();
        for (int j = 0; j < children.length; ++j) {
            final Component child2 = children[j];
            if (child2 == c) {
                break;
            }
            if (child2.isVisible()) {
                final Filter.Acceptance accept = filter.accept((Object)child2);
                if (accept == Filter.Acceptance.YES) {
                    tempRectangle.setBounds(child2.getX(), child2.getY(), child2.getWidth(), child2.getHeight());
                    shape = subtract(shape, SwingUtilities.convertRectangle(parent, tempRectangle, component));
                }
                else if (accept == Filter.Acceptance.TEST_CHILDREN && child2 instanceof Container) {
                    shape = getChildrenVisibleArea(component, filter, shape, (Container)child2, null);
                }
            }
        }
        return shape;
    }
    
    public static Rectangle getBounds(final Rectangle[] rectangles) {
        final Rectangle bounds = new Rectangle();
        if (rectangles.length > 0) {
            bounds.setBounds(rectangles[0]);
            for (int i = 1; i < rectangles.length; ++i) {
                Rectangle2D.union(bounds, rectangles[i], bounds);
            }
        }
        return bounds;
    }
    
    public static void setPreferredLookAndFeel() {
        try {
            final String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            if (!"com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(systemLookAndFeelClassName)) {
                UIManager.setLookAndFeel(systemLookAndFeelClassName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void revalidate(final Component c) {
        if (c instanceof JComponent) {
            ((JComponent)c).revalidate();
        }
        else {
            c.invalidate();
            c.validate();
        }
    }
    
    public static Point2D.Double getScaledFactor(final Component c) {
        final GraphicsConfiguration graphicsConfiguration = c.getGraphicsConfiguration();
        if (graphicsConfiguration != null) {
            final AffineTransform defaultTransform = graphicsConfiguration.getDefaultTransform();
            if (defaultTransform != null) {
                final double scaleX = defaultTransform.getScaleX();
                final double scaleY = defaultTransform.getScaleY();
                return new Point2D.Double(scaleX, scaleY);
            }
        }
        return new Point2D.Double(1.0, 1.0);
    }
    
    static {
        UIUtils.COMPONENT_TRANSPARENT_CLIENT_PROPERTY = "nsTransparent";
    }
    
    public enum TransparencyType
    {
        OPAQUE, 
        TRANSPARENT_WITH_OPAQUE_CHILDREN, 
        NOT_VISIBLE;
    }
}
