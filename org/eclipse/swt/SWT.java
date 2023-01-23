//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt;

import org.eclipse.swt.internal.*;
import chrriis.dj.nativeswing.swtimpl.*;

public class SWT
{
    public static final int None = 0;
    public static final int KeyDown = 1;
    public static final int KeyUp = 2;
    public static final int MouseDown = 3;
    public static final int MouseUp = 4;
    public static final int MouseMove = 5;
    public static final int MouseEnter = 6;
    public static final int MouseExit = 7;
    public static final int MouseDoubleClick = 8;
    public static final int Paint = 9;
    public static final int Move = 10;
    public static final int Resize = 11;
    public static final int Dispose = 12;
    public static final int Selection = 13;
    public static final int DefaultSelection = 14;
    public static final int FocusIn = 15;
    public static final int FocusOut = 16;
    public static final int Expand = 17;
    public static final int Collapse = 18;
    public static final int Iconify = 19;
    public static final int Deiconify = 20;
    public static final int Close = 21;
    public static final int Show = 22;
    public static final int Hide = 23;
    public static final int Modify = 24;
    public static final int Verify = 25;
    public static final int Activate = 26;
    public static final int Deactivate = 27;
    public static final int Help = 28;
    public static final int DragDetect = 29;
    public static final int Arm = 30;
    public static final int Traverse = 31;
    public static final int MouseHover = 32;
    public static final int HardKeyDown = 33;
    public static final int HardKeyUp = 34;
    public static final int MenuDetect = 35;
    public static final int SetData = 36;
    public static final int MouseVerticalWheel = 37;
    public static final int MouseHorizontalWheel = 38;
    public static final int MouseWheel = 37;
    public static final int Settings = 39;
    public static final int EraseItem = 40;
    public static final int MeasureItem = 41;
    public static final int PaintItem = 42;
    public static final int ImeComposition = 43;
    public static final int OrientationChange = 44;
    public static final int Skin = 45;
    public static final int OpenDocument = 46;
    public static final int Touch = 47;
    public static final int Gesture = 48;
    public static final int Segments = 49;
    public static final int PreEvent = 50;
    public static final int PostEvent = 51;
    public static final int PreExternalEventDispatch = 52;
    public static final int PostExternalEventDispatch = 53;
    @Deprecated
    public static final int Sleep = 52;
    @Deprecated
    public static final int Wakeup = 53;
    public static final int OpenUrl = 54;
    public static final int ZoomChanged = 55;
    public static final int EmptinessChanged = 56;
    public static final int COMPOSITION_CHANGED = 1;
    public static final int COMPOSITION_OFFSET = 2;
    public static final int COMPOSITION_SELECTION = 3;
    public static final int DRAG = 1;
    public static final int SELECTED = 2;
    public static final int FOCUSED = 4;
    public static final int BACKGROUND = 8;
    public static final int FOREGROUND = 16;
    public static final int HOT = 32;
    public static final int TRAVERSE_NONE = 0;
    public static final int TRAVERSE_ESCAPE = 2;
    public static final int TRAVERSE_RETURN = 4;
    public static final int TRAVERSE_TAB_PREVIOUS = 8;
    public static final int TRAVERSE_TAB_NEXT = 16;
    public static final int TRAVERSE_ARROW_PREVIOUS = 32;
    public static final int TRAVERSE_ARROW_NEXT = 64;
    public static final int TRAVERSE_MNEMONIC = 128;
    public static final int TRAVERSE_PAGE_PREVIOUS = 256;
    public static final int TRAVERSE_PAGE_NEXT = 512;
    public static final int GESTURE_BEGIN = 2;
    public static final int GESTURE_END = 4;
    public static final int GESTURE_ROTATE = 8;
    public static final int GESTURE_SWIPE = 16;
    public static final int GESTURE_MAGNIFY = 32;
    public static final int GESTURE_PAN = 64;
    public static final int TOUCHSTATE_DOWN = 1;
    public static final int TOUCHSTATE_MOVE = 2;
    public static final int TOUCHSTATE_UP = 4;
    public static final int MENU_MOUSE = 0;
    public static final int MENU_KEYBOARD = 1;
    public static final int CHANGED = 2;
    public static final int DEFER = 4;
    public static final int NONE = 0;
    public static final int NULL = 0;
    public static final int DEFAULT = -1;
    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int LOW = 1;
    public static final int HIGH = 2;
    public static final int BAR = 2;
    public static final int DROP_DOWN = 4;
    public static final int POP_UP = 8;
    public static final int SEPARATOR = 2;
    public static final int SEPARATOR_FILL = -2;
    public static final int TOGGLE = 2;
    public static final int ARROW = 4;
    public static final int PUSH = 8;
    public static final int RADIO = 16;
    public static final int CHECK = 32;
    public static final int CASCADE = 64;
    public static final int MULTI = 2;
    public static final int SINGLE = 4;
    public static final int READ_ONLY = 8;
    public static final int WRAP = 64;
    public static final int SEARCH = 128;
    public static final int SIMPLE = 64;
    public static final int PASSWORD = 4194304;
    public static final int SHADOW_IN = 4;
    public static final int SHADOW_OUT = 8;
    public static final int SHADOW_ETCHED_IN = 16;
    public static final int SHADOW_ETCHED_OUT = 64;
    public static final int SHADOW_NONE = 32;
    public static final int INDETERMINATE = 2;
    public static final int TOOL = 4;
    public static final int NO_TRIM = 8;
    public static final int RESIZE = 16;
    public static final int TITLE = 32;
    public static final int CLOSE = 64;
    public static final int MENU = 64;
    public static final int MIN = 128;
    public static final int MAX = 1024;
    public static final int NO_MOVE = 8388608;
    public static final int H_SCROLL = 256;
    public static final int V_SCROLL = 512;
    public static final int NO_SCROLL = 16;
    public static final int BORDER = 2048;
    public static final int CLIP_CHILDREN = 4096;
    public static final int CLIP_SIBLINGS = 8192;
    public static final int ON_TOP = 16384;
    public static final int SHEET = 268435456;
    public static final int SHELL_TRIM = 1264;
    public static final int DIALOG_TRIM = 2144;
    public static final int MODELESS = 0;
    public static final int PRIMARY_MODAL = 32768;
    public static final int APPLICATION_MODAL = 65536;
    public static final int SYSTEM_MODAL = 131072;
    public static final int HIDE_SELECTION = 32768;
    public static final int FULL_SELECTION = 65536;
    public static final int FLAT = 8388608;
    public static final int SMOOTH = 65536;
    public static final int NO_BACKGROUND = 262144;
    public static final int NO_FOCUS = 524288;
    public static final int NO_REDRAW_RESIZE = 1048576;
    public static final int NO_MERGE_PAINTS = 2097152;
    public static final int NO_RADIO_GROUP = 4194304;
    public static final int LEFT_TO_RIGHT = 33554432;
    public static final int RIGHT_TO_LEFT = 67108864;
    public static final int MIRRORED = 134217728;
    public static final int EMBEDDED = 16777216;
    public static final int VIRTUAL = 268435456;
    public static final int DOUBLE_BUFFERED = 536870912;
    public static final int TRANSPARENT = 1073741824;
    public static final int FLIP_TEXT_DIRECTION = Integer.MIN_VALUE;
    public static final int AUTO_TEXT_DIRECTION = 100663296;
    public static final int UP = 128;
    public static final int UNDERLINE_SINGLE = 0;
    public static final int UNDERLINE_DOUBLE = 1;
    public static final int UNDERLINE_ERROR = 2;
    public static final int UNDERLINE_SQUIGGLE = 3;
    public static final int UNDERLINE_LINK = 4;
    public static final int BORDER_SOLID = 1;
    public static final int BORDER_DASH = 2;
    public static final int BORDER_DOT = 4;
    public static final int TOP = 128;
    public static final int DOWN = 1024;
    public static final int BOTTOM = 1024;
    public static final int LEAD = 16384;
    public static final int LEFT = 16384;
    public static final int TRAIL = 131072;
    public static final int RIGHT = 131072;
    public static final int CENTER = 16777216;
    public static final int HORIZONTAL = 256;
    public static final int VERTICAL = 512;
    public static final int DATE = 32;
    public static final int TIME = 128;
    public static final int CALENDAR = 1024;
    public static final int CALENDAR_WEEKNUMBERS = 16384;
    public static final int SHORT = 32768;
    public static final int MEDIUM = 65536;
    public static final int LONG = 268435456;
    @Deprecated
    public static final int MOZILLA = 32768;
    public static final int WEBKIT = 65536;
    @Deprecated
    public static final int CHROMIUM = 131072;
    public static final int EDGE = 262144;
    public static final int BALLOON = 4096;
    public static final int BEGINNING = 1;
    public static final int FILL = 4;
    public static final int DBCS = 2;
    public static final int ALPHA = 4;
    public static final int NATIVE = 8;
    public static final int PHONETIC = 16;
    public static final int ROMAN = 32;
    public static final char BS = '\b';
    public static final char CR = '\r';
    public static final char DEL = '\u007f';
    public static final char ESC = '\u001b';
    public static final char LF = '\n';
    public static final char TAB = '\t';
    public static final char SPACE = ' ';
    public static final int ALT_GR = 32768;
    public static final int ALT = 65536;
    public static final int SHIFT = 131072;
    public static final int CTRL = 262144;
    public static final int CONTROL = 262144;
    public static final int COMMAND = 4194304;
    public static final int MODIFIER_MASK;
    public static final int BUTTON1 = 524288;
    public static final int BUTTON2 = 1048576;
    public static final int BUTTON3 = 2097152;
    public static final int BUTTON4 = 8388608;
    public static final int BUTTON5 = 33554432;
    public static final int BUTTON_MASK;
    public static final int MOD1;
    public static final int MOD2;
    public static final int MOD3;
    public static final int MOD4;
    public static final int SCROLL_LINE = 1;
    public static final int SCROLL_PAGE = 2;
    public static final int KEYCODE_BIT = 16777216;
    public static final int KEY_MASK = 16842751;
    public static final int ARROW_UP = 16777217;
    public static final int ARROW_DOWN = 16777218;
    public static final int ARROW_LEFT = 16777219;
    public static final int ARROW_RIGHT = 16777220;
    public static final int PAGE_UP = 16777221;
    public static final int PAGE_DOWN = 16777222;
    public static final int HOME = 16777223;
    public static final int END = 16777224;
    public static final int INSERT = 16777225;
    public static final int F1 = 16777226;
    public static final int F2 = 16777227;
    public static final int F3 = 16777228;
    public static final int F4 = 16777229;
    public static final int F5 = 16777230;
    public static final int F6 = 16777231;
    public static final int F7 = 16777232;
    public static final int F8 = 16777233;
    public static final int F9 = 16777234;
    public static final int F10 = 16777235;
    public static final int F11 = 16777236;
    public static final int F12 = 16777237;
    public static final int F13 = 16777238;
    public static final int F14 = 16777239;
    public static final int F15 = 16777240;
    public static final int F16 = 16777241;
    public static final int F17 = 16777242;
    public static final int F18 = 16777243;
    public static final int F19 = 16777244;
    public static final int F20 = 16777245;
    public static final int KEYPAD = 2;
    public static final int KEYPAD_MULTIPLY = 16777258;
    public static final int KEYPAD_ADD = 16777259;
    public static final int KEYPAD_SUBTRACT = 16777261;
    public static final int KEYPAD_DECIMAL = 16777262;
    public static final int KEYPAD_DIVIDE = 16777263;
    public static final int KEYPAD_0 = 16777264;
    public static final int KEYPAD_1 = 16777265;
    public static final int KEYPAD_2 = 16777266;
    public static final int KEYPAD_3 = 16777267;
    public static final int KEYPAD_4 = 16777268;
    public static final int KEYPAD_5 = 16777269;
    public static final int KEYPAD_6 = 16777270;
    public static final int KEYPAD_7 = 16777271;
    public static final int KEYPAD_8 = 16777272;
    public static final int KEYPAD_9 = 16777273;
    public static final int KEYPAD_EQUAL = 16777277;
    public static final int KEYPAD_CR = 16777296;
    public static final int HELP = 16777297;
    public static final int CAPS_LOCK = 16777298;
    public static final int NUM_LOCK = 16777299;
    public static final int SCROLL_LOCK = 16777300;
    public static final int PAUSE = 16777301;
    public static final int BREAK = 16777302;
    public static final int PRINT_SCREEN = 16777303;
    public static final int ICON_ERROR = 1;
    public static final int ICON_INFORMATION = 2;
    public static final int ICON_QUESTION = 4;
    public static final int ICON_WARNING = 8;
    public static final int ICON_WORKING = 16;
    public static final int ICON_SEARCH = 512;
    public static final int ICON_CANCEL = 256;
    public static final int OK = 32;
    public static final int YES = 64;
    public static final int NO = 128;
    public static final int CANCEL = 256;
    public static final int ABORT = 512;
    public static final int RETRY = 1024;
    public static final int IGNORE = 2048;
    public static final int OPEN = 4096;
    public static final int SAVE = 8192;
    public static final int INHERIT_NONE = 0;
    public static final int INHERIT_DEFAULT = 1;
    public static final int INHERIT_FORCE = 2;
    public static final int COLOR_WHITE = 1;
    public static final int COLOR_BLACK = 2;
    public static final int COLOR_RED = 3;
    public static final int COLOR_DARK_RED = 4;
    public static final int COLOR_GREEN = 5;
    public static final int COLOR_DARK_GREEN = 6;
    public static final int COLOR_YELLOW = 7;
    public static final int COLOR_DARK_YELLOW = 8;
    public static final int COLOR_BLUE = 9;
    public static final int COLOR_DARK_BLUE = 10;
    public static final int COLOR_MAGENTA = 11;
    public static final int COLOR_DARK_MAGENTA = 12;
    public static final int COLOR_CYAN = 13;
    public static final int COLOR_DARK_CYAN = 14;
    public static final int COLOR_GRAY = 15;
    public static final int COLOR_DARK_GRAY = 16;
    public static final int COLOR_WIDGET_DARK_SHADOW = 17;
    public static final int COLOR_WIDGET_NORMAL_SHADOW = 18;
    public static final int COLOR_WIDGET_LIGHT_SHADOW = 19;
    public static final int COLOR_WIDGET_HIGHLIGHT_SHADOW = 20;
    public static final int COLOR_WIDGET_FOREGROUND = 21;
    public static final int COLOR_WIDGET_BACKGROUND = 22;
    public static final int COLOR_WIDGET_BORDER = 23;
    public static final int COLOR_LIST_FOREGROUND = 24;
    public static final int COLOR_LIST_BACKGROUND = 25;
    public static final int COLOR_LIST_SELECTION = 26;
    public static final int COLOR_LIST_SELECTION_TEXT = 27;
    public static final int COLOR_INFO_FOREGROUND = 28;
    public static final int COLOR_INFO_BACKGROUND = 29;
    public static final int COLOR_TITLE_FOREGROUND = 30;
    public static final int COLOR_TITLE_BACKGROUND = 31;
    public static final int COLOR_TITLE_BACKGROUND_GRADIENT = 32;
    public static final int COLOR_TITLE_INACTIVE_FOREGROUND = 33;
    public static final int COLOR_TITLE_INACTIVE_BACKGROUND = 34;
    public static final int COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT = 35;
    public static final int COLOR_LINK_FOREGROUND = 36;
    public static final int COLOR_TRANSPARENT = 37;
    public static final int COLOR_TEXT_DISABLED_BACKGROUND = 38;
    public static final int COLOR_WIDGET_DISABLED_FOREGROUND = 39;
    public static final int DRAW_TRANSPARENT = 1;
    public static final int DRAW_DELIMITER = 2;
    public static final int DRAW_TAB = 4;
    public static final int DRAW_MNEMONIC = 8;
    public static final int DELIMITER_SELECTION = 131072;
    public static final int LAST_LINE_SELECTION = 1048576;
    public static final int ERROR_UNSPECIFIED = 1;
    public static final int ERROR_NO_HANDLES = 2;
    public static final int ERROR_NO_MORE_CALLBACKS = 3;
    public static final int ERROR_NULL_ARGUMENT = 4;
    public static final int ERROR_INVALID_ARGUMENT = 5;
    public static final int ERROR_INVALID_RANGE = 6;
    public static final int ERROR_CANNOT_BE_ZERO = 7;
    public static final int ERROR_CANNOT_GET_ITEM = 8;
    public static final int ERROR_CANNOT_GET_SELECTION = 9;
    public static final int ERROR_CANNOT_INVERT_MATRIX = 10;
    public static final int ERROR_CANNOT_GET_ITEM_HEIGHT = 11;
    public static final int ERROR_CANNOT_GET_TEXT = 12;
    public static final int ERROR_CANNOT_SET_TEXT = 13;
    public static final int ERROR_ITEM_NOT_ADDED = 14;
    public static final int ERROR_ITEM_NOT_REMOVED = 15;
    public static final int ERROR_NO_GRAPHICS_LIBRARY = 16;
    public static final int ERROR_NOT_IMPLEMENTED = 20;
    public static final int ERROR_MENU_NOT_DROP_DOWN = 21;
    public static final int ERROR_THREAD_INVALID_ACCESS = 22;
    public static final int ERROR_WIDGET_DISPOSED = 24;
    public static final int ERROR_MENUITEM_NOT_CASCADE = 27;
    public static final int ERROR_CANNOT_SET_SELECTION = 28;
    public static final int ERROR_CANNOT_SET_MENU = 29;
    public static final int ERROR_CANNOT_SET_ENABLED = 30;
    public static final int ERROR_CANNOT_GET_ENABLED = 31;
    public static final int ERROR_INVALID_PARENT = 32;
    public static final int ERROR_MENU_NOT_BAR = 33;
    public static final int ERROR_CANNOT_GET_COUNT = 36;
    public static final int ERROR_MENU_NOT_POP_UP = 37;
    public static final int ERROR_UNSUPPORTED_DEPTH = 38;
    public static final int ERROR_IO = 39;
    public static final int ERROR_INVALID_IMAGE = 40;
    public static final int ERROR_UNSUPPORTED_FORMAT = 42;
    public static final int ERROR_INVALID_SUBCLASS = 43;
    public static final int ERROR_GRAPHIC_DISPOSED = 44;
    public static final int ERROR_DEVICE_DISPOSED = 45;
    public static final int ERROR_FAILED_EXEC = 46;
    public static final int ERROR_FAILED_LOAD_LIBRARY = 47;
    public static final int ERROR_INVALID_FONT = 48;
    public static final int ERROR_FUNCTION_DISPOSED = 49;
    public static final int ERROR_FAILED_EVALUATE = 50;
    public static final int ERROR_INVALID_RETURN_VALUE = 51;
    public static final int BITMAP = 0;
    public static final int ICON = 1;
    public static final int IMAGE_COPY = 0;
    public static final int IMAGE_DISABLE = 1;
    public static final int IMAGE_GRAY = 2;
    public static final int ERROR = 1;
    public static final int PAUSED = 4;
    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int CURSOR_ARROW = 0;
    public static final int CURSOR_WAIT = 1;
    public static final int CURSOR_CROSS = 2;
    public static final int CURSOR_APPSTARTING = 3;
    public static final int CURSOR_HELP = 4;
    public static final int CURSOR_SIZEALL = 5;
    public static final int CURSOR_SIZENESW = 6;
    public static final int CURSOR_SIZENS = 7;
    public static final int CURSOR_SIZENWSE = 8;
    public static final int CURSOR_SIZEWE = 9;
    public static final int CURSOR_SIZEN = 10;
    public static final int CURSOR_SIZES = 11;
    public static final int CURSOR_SIZEE = 12;
    public static final int CURSOR_SIZEW = 13;
    public static final int CURSOR_SIZENE = 14;
    public static final int CURSOR_SIZESE = 15;
    public static final int CURSOR_SIZESW = 16;
    public static final int CURSOR_SIZENW = 17;
    public static final int CURSOR_UPARROW = 18;
    public static final int CURSOR_IBEAM = 19;
    public static final int CURSOR_NO = 20;
    public static final int CURSOR_HAND = 21;
    public static final int CAP_FLAT = 1;
    public static final int CAP_ROUND = 2;
    public static final int CAP_SQUARE = 3;
    public static final int JOIN_MITER = 1;
    public static final int JOIN_ROUND = 2;
    public static final int JOIN_BEVEL = 3;
    public static final int LINE_SOLID = 1;
    public static final int LINE_DASH = 2;
    public static final int LINE_DOT = 3;
    public static final int LINE_DASHDOT = 4;
    public static final int LINE_DASHDOTDOT = 5;
    public static final int LINE_CUSTOM = 6;
    public static final int PATH_MOVE_TO = 1;
    public static final int PATH_LINE_TO = 2;
    public static final int PATH_QUAD_TO = 3;
    public static final int PATH_CUBIC_TO = 4;
    public static final int PATH_CLOSE = 5;
    public static final int FILL_EVEN_ODD = 1;
    public static final int FILL_WINDING = 2;
    public static final int IMAGE_UNDEFINED = -1;
    public static final int IMAGE_BMP = 0;
    public static final int IMAGE_BMP_RLE = 1;
    public static final int IMAGE_GIF = 2;
    public static final int IMAGE_ICO = 3;
    public static final int IMAGE_JPEG = 4;
    public static final int IMAGE_PNG = 5;
    public static final int IMAGE_TIFF = 6;
    public static final int IMAGE_OS2_BMP = 7;
    public static final int IMAGE_SVG = 8;
    public static final int DM_UNSPECIFIED = 0;
    public static final int DM_FILL_NONE = 1;
    public static final int DM_FILL_BACKGROUND = 2;
    public static final int DM_FILL_PREVIOUS = 3;
    public static final int TRANSPARENCY_NONE = 0;
    public static final int TRANSPARENCY_ALPHA = 1;
    public static final int TRANSPARENCY_MASK = 2;
    public static final int TRANSPARENCY_PIXEL = 4;
    public static final int MOVEMENT_CHAR = 1;
    public static final int MOVEMENT_CLUSTER = 2;
    public static final int MOVEMENT_WORD = 4;
    public static final int MOVEMENT_WORD_END = 8;
    public static final int MOVEMENT_WORD_START = 16;
    public static final int ALL = 1;
    public static final int ID_ABOUT = -1;
    public static final int ID_PREFERENCES = -2;
    public static final int ID_HIDE = -3;
    public static final int ID_HIDE_OTHERS = -4;
    public static final int ID_SHOW_ALL = -5;
    public static final int ID_QUIT = -6;
    public static final String SKIN_CLASS = "org.eclipse.swt.skin.class";
    public static final String SKIN_ID = "org.eclipse.swt.skin.id";
    public static final int SCROLLBAR_OVERLAY = 2;
    
    public static boolean isLoadable() {
        return Platform.isLoadable();
    }
    
    static String findErrorText(final int code) {
        switch (code) {
            case 1: {
                return "Unspecified error";
            }
            case 2: {
                return "No more handles";
            }
            case 3: {
                return "No more callbacks";
            }
            case 4: {
                return "Argument cannot be null";
            }
            case 5: {
                return "Argument not valid";
            }
            case 51: {
                return "Return value not valid";
            }
            case 6: {
                return "Index out of bounds";
            }
            case 7: {
                return "Argument cannot be zero";
            }
            case 8: {
                return "Cannot get item";
            }
            case 9: {
                return "Cannot get selection";
            }
            case 11: {
                return "Cannot get item height";
            }
            case 12: {
                return "Cannot get text";
            }
            case 13: {
                return "Cannot set text";
            }
            case 14: {
                return "Item not added";
            }
            case 15: {
                return "Item not removed";
            }
            case 20: {
                return "Not implemented";
            }
            case 21: {
                return "Menu must be a drop down";
            }
            case 22: {
                return "Invalid thread access";
            }
            case 24: {
                return "Widget is disposed";
            }
            case 27: {
                return "Menu item is not a CASCADE";
            }
            case 28: {
                return "Cannot set selection";
            }
            case 29: {
                return "Cannot set menu";
            }
            case 30: {
                return "Cannot set the enabled state";
            }
            case 31: {
                return "Cannot get the enabled state";
            }
            case 32: {
                return "Widget has the wrong parent";
            }
            case 33: {
                return "Menu is not a BAR";
            }
            case 36: {
                return "Cannot get count";
            }
            case 37: {
                return "Menu is not a POP_UP";
            }
            case 38: {
                return "Unsupported color depth";
            }
            case 39: {
                return "i/o error";
            }
            case 40: {
                return "Invalid image";
            }
            case 42: {
                return "Unsupported or unrecognized format";
            }
            case 43: {
                return "Subclassing not allowed";
            }
            case 44: {
                return "Graphic is disposed";
            }
            case 45: {
                return "Device is disposed";
            }
            case 49: {
                return "BrowserFunction is disposed";
            }
            case 46: {
                return "Failed to execute runnable";
            }
            case 50: {
                return "Failed to evaluate javascript expression";
            }
            case 47: {
                return "Unable to load library";
            }
            case 10: {
                return "Cannot invert matrix";
            }
            case 16: {
                return "Unable to load graphics library";
            }
            case 48: {
                return "Font not valid";
            }
            default: {
                return "Unknown error";
            }
        }
    }
    
    public static String getMessage(final String key) {
        return Compatibility.getMessage(key);
    }
    
    public static String getMessage(final String key, final Object[] args) {
        return Compatibility.getMessage(key, args);
    }
    
    public static String getPlatform() {
        return "win32";
    }
    
    public static int getVersion() {
        return Library.SWT_VERSION;
    }
    
    public static void error(final int code) {
        error(code, null);
    }
    
    public static void error(final int code, final Throwable throwable) {
        error(code, throwable, null);
    }
    
    public static void error(final int code, final Throwable throwable, final String detail) {
        if (code != 46) {
            if (throwable instanceof SWTError) {
                throw (SWTError)throwable;
            }
            if (throwable instanceof SWTException) {
                throw (SWTException)throwable;
            }
        }
        String message = findErrorText(code);
        if (detail != null) {
            message += detail;
        }
        switch (code) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 21:
            case 27:
            case 32:
            case 33:
            case 37: {
                throw new IllegalArgumentException(message);
            }
            case 10:
            case 16:
            case 22:
            case 24:
            case 38:
            case 39:
            case 40:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 49:
            case 50:
            case 51: {
                final SWTException exception = new SWTException(code, message);
                exception.throwable = throwable;
                throw exception;
            }
            case 1:
            case 2:
            case 3:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 20:
            case 28:
            case 29:
            case 30:
            case 31:
            case 36:
            case 47: {
                final SWTError error = new SWTError(code, message);
                error.throwable = throwable;
                NativeInterface.close();
                throw error;
            }
            default: {
                final SWTError error = new SWTError(code, message);
                error.throwable = throwable;
                NativeInterface.close();
                throw error;
            }
        }
    }
    
    static {
        BUTTON_MASK = 45613056;
        MODIFIER_MASK = 4685824;
        final String platform = getPlatform();
        if ("cocoa".equals(platform)) {
            MOD1 = 4194304;
            MOD2 = 131072;
            MOD3 = 65536;
            MOD4 = 262144;
        }
        else {
            MOD1 = 262144;
            MOD2 = 131072;
            MOD3 = 65536;
            MOD4 = 0;
        }
    }
}
