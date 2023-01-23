//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMKeyEvent extends nsIDOMUIEvent
{
    public static final String NS_IDOMKEYEVENT_IID = "{028e0e6e-8b01-11d3-aae7-0010838a3123}";
    public static final long DOM_VK_CANCEL = 3L;
    public static final long DOM_VK_HELP = 6L;
    public static final long DOM_VK_BACK_SPACE = 8L;
    public static final long DOM_VK_TAB = 9L;
    public static final long DOM_VK_CLEAR = 12L;
    public static final long DOM_VK_RETURN = 13L;
    public static final long DOM_VK_ENTER = 14L;
    public static final long DOM_VK_SHIFT = 16L;
    public static final long DOM_VK_CONTROL = 17L;
    public static final long DOM_VK_ALT = 18L;
    public static final long DOM_VK_PAUSE = 19L;
    public static final long DOM_VK_CAPS_LOCK = 20L;
    public static final long DOM_VK_ESCAPE = 27L;
    public static final long DOM_VK_SPACE = 32L;
    public static final long DOM_VK_PAGE_UP = 33L;
    public static final long DOM_VK_PAGE_DOWN = 34L;
    public static final long DOM_VK_END = 35L;
    public static final long DOM_VK_HOME = 36L;
    public static final long DOM_VK_LEFT = 37L;
    public static final long DOM_VK_UP = 38L;
    public static final long DOM_VK_RIGHT = 39L;
    public static final long DOM_VK_DOWN = 40L;
    public static final long DOM_VK_PRINTSCREEN = 44L;
    public static final long DOM_VK_INSERT = 45L;
    public static final long DOM_VK_DELETE = 46L;
    public static final long DOM_VK_0 = 48L;
    public static final long DOM_VK_1 = 49L;
    public static final long DOM_VK_2 = 50L;
    public static final long DOM_VK_3 = 51L;
    public static final long DOM_VK_4 = 52L;
    public static final long DOM_VK_5 = 53L;
    public static final long DOM_VK_6 = 54L;
    public static final long DOM_VK_7 = 55L;
    public static final long DOM_VK_8 = 56L;
    public static final long DOM_VK_9 = 57L;
    public static final long DOM_VK_SEMICOLON = 59L;
    public static final long DOM_VK_EQUALS = 61L;
    public static final long DOM_VK_A = 65L;
    public static final long DOM_VK_B = 66L;
    public static final long DOM_VK_C = 67L;
    public static final long DOM_VK_D = 68L;
    public static final long DOM_VK_E = 69L;
    public static final long DOM_VK_F = 70L;
    public static final long DOM_VK_G = 71L;
    public static final long DOM_VK_H = 72L;
    public static final long DOM_VK_I = 73L;
    public static final long DOM_VK_J = 74L;
    public static final long DOM_VK_K = 75L;
    public static final long DOM_VK_L = 76L;
    public static final long DOM_VK_M = 77L;
    public static final long DOM_VK_N = 78L;
    public static final long DOM_VK_O = 79L;
    public static final long DOM_VK_P = 80L;
    public static final long DOM_VK_Q = 81L;
    public static final long DOM_VK_R = 82L;
    public static final long DOM_VK_S = 83L;
    public static final long DOM_VK_T = 84L;
    public static final long DOM_VK_U = 85L;
    public static final long DOM_VK_V = 86L;
    public static final long DOM_VK_W = 87L;
    public static final long DOM_VK_X = 88L;
    public static final long DOM_VK_Y = 89L;
    public static final long DOM_VK_Z = 90L;
    public static final long DOM_VK_CONTEXT_MENU = 93L;
    public static final long DOM_VK_NUMPAD0 = 96L;
    public static final long DOM_VK_NUMPAD1 = 97L;
    public static final long DOM_VK_NUMPAD2 = 98L;
    public static final long DOM_VK_NUMPAD3 = 99L;
    public static final long DOM_VK_NUMPAD4 = 100L;
    public static final long DOM_VK_NUMPAD5 = 101L;
    public static final long DOM_VK_NUMPAD6 = 102L;
    public static final long DOM_VK_NUMPAD7 = 103L;
    public static final long DOM_VK_NUMPAD8 = 104L;
    public static final long DOM_VK_NUMPAD9 = 105L;
    public static final long DOM_VK_MULTIPLY = 106L;
    public static final long DOM_VK_ADD = 107L;
    public static final long DOM_VK_SEPARATOR = 108L;
    public static final long DOM_VK_SUBTRACT = 109L;
    public static final long DOM_VK_DECIMAL = 110L;
    public static final long DOM_VK_DIVIDE = 111L;
    public static final long DOM_VK_F1 = 112L;
    public static final long DOM_VK_F2 = 113L;
    public static final long DOM_VK_F3 = 114L;
    public static final long DOM_VK_F4 = 115L;
    public static final long DOM_VK_F5 = 116L;
    public static final long DOM_VK_F6 = 117L;
    public static final long DOM_VK_F7 = 118L;
    public static final long DOM_VK_F8 = 119L;
    public static final long DOM_VK_F9 = 120L;
    public static final long DOM_VK_F10 = 121L;
    public static final long DOM_VK_F11 = 122L;
    public static final long DOM_VK_F12 = 123L;
    public static final long DOM_VK_F13 = 124L;
    public static final long DOM_VK_F14 = 125L;
    public static final long DOM_VK_F15 = 126L;
    public static final long DOM_VK_F16 = 127L;
    public static final long DOM_VK_F17 = 128L;
    public static final long DOM_VK_F18 = 129L;
    public static final long DOM_VK_F19 = 130L;
    public static final long DOM_VK_F20 = 131L;
    public static final long DOM_VK_F21 = 132L;
    public static final long DOM_VK_F22 = 133L;
    public static final long DOM_VK_F23 = 134L;
    public static final long DOM_VK_F24 = 135L;
    public static final long DOM_VK_NUM_LOCK = 144L;
    public static final long DOM_VK_SCROLL_LOCK = 145L;
    public static final long DOM_VK_COMMA = 188L;
    public static final long DOM_VK_PERIOD = 190L;
    public static final long DOM_VK_SLASH = 191L;
    public static final long DOM_VK_BACK_QUOTE = 192L;
    public static final long DOM_VK_OPEN_BRACKET = 219L;
    public static final long DOM_VK_BACK_SLASH = 220L;
    public static final long DOM_VK_CLOSE_BRACKET = 221L;
    public static final long DOM_VK_QUOTE = 222L;
    public static final long DOM_VK_META = 224L;
    
    long getCharCode();
    
    long getKeyCode();
    
    boolean getAltKey();
    
    boolean getCtrlKey();
    
    boolean getShiftKey();
    
    boolean getMetaKey();
    
    void initKeyEvent(final String p0, final boolean p1, final boolean p2, final nsIDOMAbstractView p3, final boolean p4, final boolean p5, final boolean p6, final boolean p7, final long p8, final long p9);
}
