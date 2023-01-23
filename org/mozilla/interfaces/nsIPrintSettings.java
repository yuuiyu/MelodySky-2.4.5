//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintSettings extends nsISupports
{
    public static final String NS_IPRINTSETTINGS_IID = "{f1094df6-ce0e-42c9-9847-2f663172c38d}";
    public static final long kInitSaveOddEvenPages = 1L;
    public static final long kInitSaveHeaderLeft = 2L;
    public static final long kInitSaveHeaderCenter = 4L;
    public static final long kInitSaveHeaderRight = 8L;
    public static final long kInitSaveFooterLeft = 16L;
    public static final long kInitSaveFooterCenter = 32L;
    public static final long kInitSaveFooterRight = 64L;
    public static final long kInitSaveBGColors = 128L;
    public static final long kInitSaveBGImages = 256L;
    public static final long kInitSavePaperSize = 512L;
    public static final long kInitSavePaperName = 1024L;
    public static final long kInitSavePaperSizeUnit = 2048L;
    public static final long kInitSavePaperSizeType = 4096L;
    public static final long kInitSavePaperData = 8192L;
    public static final long kInitSavePaperWidth = 16384L;
    public static final long kInitSavePaperHeight = 32768L;
    public static final long kInitSaveReversed = 65536L;
    public static final long kInitSaveInColor = 131072L;
    public static final long kInitSaveOrientation = 262144L;
    public static final long kInitSavePrintCommand = 524288L;
    public static final long kInitSavePrinterName = 1048576L;
    public static final long kInitSavePrintToFile = 2097152L;
    public static final long kInitSaveToFileName = 4194304L;
    public static final long kInitSavePageDelay = 8388608L;
    public static final long kInitSaveMargins = 16777216L;
    public static final long kInitSaveNativeData = 33554432L;
    public static final long kInitSavePlexName = 67108864L;
    public static final long kInitSaveShrinkToFit = 134217728L;
    public static final long kInitSaveScaling = 268435456L;
    public static final long kInitSaveColorspace = 536870912L;
    public static final long kInitSaveResolutionName = 1073741824L;
    public static final long kInitSaveDownloadFonts = 2147483648L;
    public static final long kInitSaveAll = 4294967295L;
    public static final int kPrintOddPages = 1;
    public static final int kPrintEvenPages = 2;
    public static final int kEnableSelectionRB = 4;
    public static final int kRangeAllPages = 0;
    public static final int kRangeSpecifiedPageRange = 1;
    public static final int kRangeSelection = 2;
    public static final int kRangeFocusFrame = 3;
    public static final int kJustLeft = 0;
    public static final int kJustCenter = 1;
    public static final int kJustRight = 2;
    public static final short kUseInternalDefault = 0;
    public static final short kUseSettingWhenPossible = 1;
    public static final short kPaperSizeNativeData = 0;
    public static final short kPaperSizeDefined = 1;
    public static final short kPaperSizeInches = 0;
    public static final short kPaperSizeMillimeters = 1;
    public static final short kPortraitOrientation = 0;
    public static final short kLandscapeOrientation = 1;
    public static final short kNoFrames = 0;
    public static final short kFramesAsIs = 1;
    public static final short kSelectedFrame = 2;
    public static final short kEachFrameSep = 3;
    public static final short kFrameEnableNone = 0;
    public static final short kFrameEnableAll = 1;
    public static final short kFrameEnableAsIsAndEach = 2;
    
    void setPrintOptions(final int p0, final boolean p1);
    
    boolean getPrintOptions(final int p0);
    
    int getPrintOptionsBits();
    
    void getPageSizeInTwips(final int[] p0, final int[] p1);
    
    nsIPrintSettings _clone();
    
    void assign(final nsIPrintSettings p0);
    
    int getStartPageRange();
    
    void setStartPageRange(final int p0);
    
    int getEndPageRange();
    
    void setEndPageRange(final int p0);
    
    double getMarginTop();
    
    void setMarginTop(final double p0);
    
    double getMarginLeft();
    
    void setMarginLeft(final double p0);
    
    double getMarginBottom();
    
    void setMarginBottom(final double p0);
    
    double getMarginRight();
    
    void setMarginRight(final double p0);
    
    double getScaling();
    
    void setScaling(final double p0);
    
    boolean getPrintBGColors();
    
    void setPrintBGColors(final boolean p0);
    
    boolean getPrintBGImages();
    
    void setPrintBGImages(final boolean p0);
    
    short getPrintRange();
    
    void setPrintRange(final short p0);
    
    String getTitle();
    
    void setTitle(final String p0);
    
    String getDocURL();
    
    void setDocURL(final String p0);
    
    String getHeaderStrLeft();
    
    void setHeaderStrLeft(final String p0);
    
    String getHeaderStrCenter();
    
    void setHeaderStrCenter(final String p0);
    
    String getHeaderStrRight();
    
    void setHeaderStrRight(final String p0);
    
    String getFooterStrLeft();
    
    void setFooterStrLeft(final String p0);
    
    String getFooterStrCenter();
    
    void setFooterStrCenter(final String p0);
    
    String getFooterStrRight();
    
    void setFooterStrRight(final String p0);
    
    short getHowToEnableFrameUI();
    
    void setHowToEnableFrameUI(final short p0);
    
    boolean getIsCancelled();
    
    void setIsCancelled(final boolean p0);
    
    short getPrintFrameTypeUsage();
    
    void setPrintFrameTypeUsage(final short p0);
    
    short getPrintFrameType();
    
    void setPrintFrameType(final short p0);
    
    boolean getPrintSilent();
    
    void setPrintSilent(final boolean p0);
    
    boolean getShrinkToFit();
    
    void setShrinkToFit(final boolean p0);
    
    boolean getShowPrintProgress();
    
    void setShowPrintProgress(final boolean p0);
    
    String getPaperName();
    
    void setPaperName(final String p0);
    
    short getPaperSizeType();
    
    void setPaperSizeType(final short p0);
    
    short getPaperData();
    
    void setPaperData(final short p0);
    
    double getPaperWidth();
    
    void setPaperWidth(final double p0);
    
    double getPaperHeight();
    
    void setPaperHeight(final double p0);
    
    short getPaperSizeUnit();
    
    void setPaperSizeUnit(final short p0);
    
    String getPlexName();
    
    void setPlexName(final String p0);
    
    String getColorspace();
    
    void setColorspace(final String p0);
    
    String getResolutionName();
    
    void setResolutionName(final String p0);
    
    boolean getDownloadFonts();
    
    void setDownloadFonts(final boolean p0);
    
    boolean getPrintReversed();
    
    void setPrintReversed(final boolean p0);
    
    boolean getPrintInColor();
    
    void setPrintInColor(final boolean p0);
    
    int getPaperSize();
    
    void setPaperSize(final int p0);
    
    int getOrientation();
    
    void setOrientation(final int p0);
    
    String getPrintCommand();
    
    void setPrintCommand(final String p0);
    
    int getNumCopies();
    
    void setNumCopies(final int p0);
    
    String getPrinterName();
    
    void setPrinterName(final String p0);
    
    boolean getPrintToFile();
    
    void setPrintToFile(final boolean p0);
    
    String getToFileName();
    
    void setToFileName(final String p0);
    
    int getPrintPageDelay();
    
    void setPrintPageDelay(final int p0);
    
    boolean getIsInitializedFromPrinter();
    
    void setIsInitializedFromPrinter(final boolean p0);
    
    boolean getIsInitializedFromPrefs();
    
    void setIsInitializedFromPrefs(final boolean p0);
}
