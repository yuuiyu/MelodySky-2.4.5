//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import java.util.function.*;
import java.util.stream.*;
import org.eclipse.swt.graphics.*;
import java.util.*;

public class BorderLayout extends Layout
{
    private static final String LAYOUT_KEY;
    private static final ToIntFunction<Point> WIDTH;
    private static final ToIntFunction<Point> HEIGHT;
    public int type;
    public int marginWidth;
    public int marginHeight;
    public int spacing;
    public int controlSpacing;
    public double widthDistributionFactor;
    public double heightDistributionFactor;
    
    public BorderLayout() {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 0;
        this.controlSpacing = 0;
        this.widthDistributionFactor = 0.5;
        this.heightDistributionFactor = 0.5;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        if (hHint > -1 && wHint > -1) {
            return new Point(wHint, hHint);
        }
        final Stream<Map.Entry<Control, BorderData>> children = Arrays.stream(composite.getChildren()).map(control -> this.borderDataControl(control, flushCache));
        final Map<Integer, List<Map.Entry<Control, BorderData>>> regionMap = children.collect((Collector<? super Map.Entry<Control, BorderData>, ?, Map<Integer, List<Map.Entry<Control, BorderData>>>>)Collectors.groupingBy((Function<? super Map.Entry<Control, BorderData>, ? extends Integer>)BorderLayout::region));
        int width;
        if (wHint <= -1) {
            final IntStream.Builder widthBuilder = IntStream.builder();
            final int northWidth = this.getTotal(BorderLayout.WIDTH, 128, regionMap);
            final int southWidth = this.getTotal(BorderLayout.WIDTH, 1024, regionMap);
            int centerWidth;
            if (this.type == 256) {
                centerWidth = this.getTotal(BorderLayout.WIDTH, 16777216, regionMap);
            }
            else {
                centerWidth = getMax(BorderLayout.WIDTH, 16777216, regionMap);
            }
            final int westWidth = getMax(BorderLayout.WIDTH, 16384, regionMap);
            final int eastWidth = getMax(BorderLayout.WIDTH, 131072, regionMap);
            int middleWidth = westWidth + centerWidth + eastWidth;
            if (centerWidth > 0) {
                if (westWidth > 0) {
                    middleWidth += this.spacing;
                }
                if (eastWidth > 0) {
                    middleWidth += this.spacing;
                }
            }
            else if (westWidth > 0 && eastWidth > 0) {
                middleWidth += this.spacing;
            }
            widthBuilder.add(middleWidth);
            widthBuilder.add(northWidth);
            widthBuilder.add(southWidth);
            width = widthBuilder.build().max().orElse(0) + 2 * this.marginWidth;
        }
        else {
            width = wHint;
        }
        int height;
        if (hHint <= -1) {
            final IntStream.Builder heightBuilder = IntStream.builder();
            final int northHeight = getMax(BorderLayout.HEIGHT, 128, regionMap);
            final int southHeight = getMax(BorderLayout.HEIGHT, 1024, regionMap);
            int westHeight = this.getTotal(BorderLayout.HEIGHT, 16384, regionMap);
            int eastHeight = this.getTotal(BorderLayout.HEIGHT, 131072, regionMap);
            int centerHeight;
            if (this.type == 256) {
                centerHeight = getMax(BorderLayout.HEIGHT, 16777216, regionMap);
            }
            else {
                centerHeight = this.getTotal(BorderLayout.HEIGHT, 16777216, regionMap);
            }
            if (centerHeight > 0) {
                if (northHeight > 0) {
                    centerHeight += this.spacing;
                }
                if (southHeight > 0) {
                    centerHeight += this.spacing;
                }
            }
            if (westHeight > 0) {
                if (northHeight > 0) {
                    westHeight += this.spacing;
                }
                if (southHeight > 0) {
                    westHeight += this.spacing;
                }
            }
            if (eastHeight > 0) {
                if (northHeight > 0) {
                    eastHeight += this.spacing;
                }
                if (southHeight > 0) {
                    eastHeight += this.spacing;
                }
            }
            final int sum = northHeight + southHeight;
            heightBuilder.add(westHeight + sum);
            heightBuilder.add(centerHeight + sum);
            heightBuilder.add(eastHeight + sum);
            height = heightBuilder.build().max().orElse(0) + 2 * this.marginHeight;
        }
        else {
            height = hHint;
        }
        return new Point(width, height);
    }
    
    private int getTotal(final ToIntFunction<Point> extractor, final int region, final Map<Integer, List<Map.Entry<Control, BorderData>>> regionMap) {
        final List<Map.Entry<Control, BorderData>> list = regionMap.getOrDefault(region, Collections.emptyList());
        if (list.isEmpty()) {
            return 0;
        }
        return list.stream().mapToInt(entry -> extractor.applyAsInt(entry.getValue().getSize((Control)entry.getKey()))).sum() + (list.size() - 1) * this.controlSpacing;
    }
    
    private static int getMax(final ToIntFunction<Point> extractor, final int region, final Map<Integer, List<Map.Entry<Control, BorderData>>> regionMap) {
        final List<Map.Entry<Control, BorderData>> list = regionMap.getOrDefault(region, Collections.emptyList());
        return getMax(extractor, list, -1, -1, false);
    }
    
    private static int getMax(final ToIntFunction<Point> extractor, final List<Map.Entry<Control, BorderData>> list, final int maxW, final int maxH, final boolean flushCache) {
        if (list.isEmpty()) {
            return 0;
        }
        if (maxW != -1 || maxH != -1) {
            return list.stream().mapToInt(entry -> extractor.applyAsInt(entry.getValue().computeSize((Control)entry.getKey(), maxW, maxH, flushCache))).max().orElse(0);
        }
        return list.stream().mapToInt(entry -> extractor.applyAsInt(entry.getValue().getSize((Control)entry.getKey()))).max().orElse(0);
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle clientArea = composite.getClientArea();
        final int clientX = clientArea.x + this.marginWidth;
        final int clientY = clientArea.y + this.marginHeight;
        final int clientWidth = clientArea.width - 2 * this.marginWidth;
        final int clientHeight = clientArea.height - 2 * this.marginHeight;
        final Stream<Map.Entry<Control, BorderData>> children = Arrays.stream(composite.getChildren()).map(control -> this.borderDataControl(control, flushCache));
        final Map<Integer, List<Map.Entry<Control, BorderData>>> regionMap = children.collect((Collector<? super Map.Entry<Control, BorderData>, ?, Map<Integer, List<Map.Entry<Control, BorderData>>>>)Collectors.groupingBy((Function<? super Map.Entry<Control, BorderData>, ? extends Integer>)BorderLayout::region));
        Map.Entry<Control, BorderData> entry2 = null;
        regionMap.getOrDefault(0, Collections.emptyList()).forEach(entry1 -> entry1.getKey().setBounds(clientX, clientY, 0, 0));
        final List<Map.Entry<Control, BorderData>> northList = regionMap.getOrDefault(128, Collections.emptyList());
        final List<Map.Entry<Control, BorderData>> southList = regionMap.getOrDefault(1024, Collections.emptyList());
        final List<Map.Entry<Control, BorderData>> westList = regionMap.getOrDefault(16384, Collections.emptyList());
        final List<Map.Entry<Control, BorderData>> eastList = regionMap.getOrDefault(131072, Collections.emptyList());
        final List<Map.Entry<Control, BorderData>> centerList = regionMap.getOrDefault(16777216, Collections.emptyList());
        final int northControlCount = northList.size();
        final int northPerControlWidth = (northControlCount > 0) ? ((clientWidth - (northControlCount - 1) * this.controlSpacing) / northControlCount) : 0;
        int northControlHeight = getMax(BorderLayout.HEIGHT, northList, northPerControlWidth, -1, flushCache);
        final int southControlCount = southList.size();
        final int southPerControlWidth = (southControlCount > 0) ? ((clientWidth - (southControlCount - 1) * this.controlSpacing) / southControlCount) : 0;
        int southControlHeight = getMax(BorderLayout.HEIGHT, southList, southPerControlWidth, -1, flushCache);
        if (northControlHeight + southControlHeight > clientHeight) {
            final int distributionSize = (int)(clientHeight * this.heightDistributionFactor);
            if (northControlHeight > distributionSize) {
                northControlHeight = distributionSize;
            }
            southControlHeight = clientHeight - northControlHeight;
        }
        final int centerControlHeight = clientHeight - northControlHeight - southControlHeight;
        final int westControlCount = westList.size();
        int westControlWidth = getMax(BorderLayout.WIDTH, westList, -1, -1, flushCache);
        final int eastControlCount = eastList.size();
        int eastControlWidth = getMax(BorderLayout.WIDTH, eastList, -1, -1, flushCache);
        if (westControlWidth + eastControlWidth > clientWidth) {
            final int distributionSize2 = (int)(clientWidth * this.widthDistributionFactor);
            if (westControlWidth > distributionSize2) {
                westControlWidth = distributionSize2;
            }
            eastControlWidth = clientWidth - westControlWidth;
        }
        final int centerControlWidth = clientWidth - westControlWidth - eastControlWidth;
        final int centerControlCount = centerList.size();
        if (northControlCount > 0) {
            int x = clientX;
            final int y = clientY;
            final Iterator<Map.Entry<Control, BorderData>> iterator = northList.iterator();
            while (iterator.hasNext()) {
                entry2 = iterator.next();
                entry2.getKey().setBounds(x, y, northPerControlWidth, northControlHeight);
                x += northPerControlWidth + this.controlSpacing;
            }
        }
        if (southControlCount > 0) {
            int x = clientX;
            final int y = clientY + centerControlHeight + northControlHeight;
            final Iterator<Map.Entry<Control, BorderData>> iterator2 = southList.iterator();
            while (iterator2.hasNext()) {
                entry2 = iterator2.next();
                entry2.getKey().setBounds(x, y, southPerControlWidth, southControlHeight);
                x += southPerControlWidth + this.controlSpacing;
            }
        }
        if (westControlCount > 0) {
            final int x = clientX;
            int y = clientY + northControlHeight;
            int h = clientHeight - northControlHeight - southControlHeight;
            if (northControlCount > 0) {
                y += this.spacing;
                h -= this.spacing;
            }
            if (southControlCount > 0) {
                h -= this.spacing;
            }
            final int controlHeight = (h - (westControlCount - 1) * this.controlSpacing) / westControlCount;
            for (final Map.Entry<Control, BorderData> entry3 : westList) {
                entry3.getKey().setBounds(x, y, westControlWidth, controlHeight);
                y += controlHeight + this.controlSpacing;
            }
        }
        if (eastControlCount > 0) {
            final int x = clientX + centerControlWidth + westControlWidth;
            int y = clientY + northControlHeight;
            int h = clientHeight - northControlHeight - southControlHeight;
            if (northControlCount > 0) {
                y += this.spacing;
                h -= this.spacing;
            }
            if (southControlCount > 0) {
                h -= this.spacing;
            }
            final int controlHeight = (h - (eastControlCount - 1) * this.controlSpacing) / eastControlCount;
            for (final Map.Entry<Control, BorderData> entry3 : eastList) {
                entry3.getKey().setBounds(x, y, eastControlWidth, controlHeight);
                y += controlHeight + this.controlSpacing;
            }
        }
        if (centerControlCount > 0) {
            int x = clientX + westControlWidth;
            int y = clientY + northControlHeight;
            int h = centerControlHeight;
            int w = centerControlWidth;
            if (westControlCount > 0) {
                x += this.spacing;
                w -= this.spacing;
            }
            if (eastControlCount > 0) {
                w -= this.spacing;
            }
            if (northControlCount > 0) {
                y += this.spacing;
                h -= this.spacing;
            }
            if (southControlCount > 0) {
                h -= this.spacing;
            }
            int controlHeight2;
            int controlWidth;
            if (this.type == 256) {
                controlHeight2 = h;
                controlWidth = (w - (centerControlCount - 1) * this.controlSpacing) / centerControlCount;
            }
            else {
                controlWidth = w;
                controlHeight2 = (h - (centerControlCount - 1) * this.controlSpacing) / centerControlCount;
            }
            for (final Map.Entry<Control, BorderData> entry4 : centerList) {
                entry4.getKey().setBounds(x, y, controlWidth, controlHeight2);
                if (this.type == 256) {
                    x += controlWidth + this.controlSpacing;
                }
                else {
                    y += controlHeight2 + this.controlSpacing;
                }
            }
        }
    }
    
    private <C extends Control> Map.Entry<C, BorderData> borderDataControl(final C control, final boolean flushCache) {
        final Object layoutData = control.getLayoutData();
        if (layoutData instanceof BorderData) {
            final BorderData borderData = (BorderData)layoutData;
            if (flushCache) {
                borderData.flushCache((Control)control);
            }
            return new AbstractMap.SimpleEntry<C, BorderData>(control, borderData);
        }
        BorderData borderData = flushCache ? null : ((BorderData)control.getData(BorderLayout.LAYOUT_KEY));
        if (borderData == null) {
            control.setData(BorderLayout.LAYOUT_KEY, borderData = new BorderData());
        }
        return new AbstractMap.SimpleEntry<C, BorderData>(control, borderData);
    }
    
    private static int region(final Map.Entry<Control, BorderData> entry) {
        final BorderData borderData = entry.getValue();
        if (borderData == null) {
            return 16777216;
        }
        return borderData.getRegion();
    }
    
    @Override
    public String toString() {
        return "BorderLayout [type=" + ((this.type == 256) ? "SWT.HORIZONTAL" : "SWT.VERTICAL") + ", marginWidth=" + this.marginWidth + ", marginHeight=" + this.marginHeight + ", spacing=" + this.spacing + ", controlSpacing=" + this.controlSpacing + ", widthDistributionFactor=" + this.widthDistributionFactor + ", heightDistributionFactor=" + this.heightDistributionFactor;
    }
    
    static {
        LAYOUT_KEY = BorderLayout.class.getName() + ".layoutData";
        WIDTH = (p -> p.x);
        HEIGHT = (p -> p.y);
    }
}
