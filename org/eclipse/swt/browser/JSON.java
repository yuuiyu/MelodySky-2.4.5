//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.*;
import java.util.*;

class JSON
{
    public static Object parse(final char[] input) {
        return new Reader(input, 0, input.length).readTop();
    }
    
    public static Object parse(final String input) {
        return parse(input.toCharArray());
    }
    
    public static String stringify(final Object object) {
        return new Writer(object).toString();
    }
    
    static class Reader
    {
        char[] input;
        int pos;
        int end;
        StringBuilder sb;
        
        public Reader(final char[] input, final int start, final int end) {
            this.input = input;
            this.pos = start;
            this.end = end;
        }
        
        char nextChar() {
            return (this.pos < this.end) ? this.input[this.pos++] : '\0';
        }
        
        void error() {
            SWT.error(5, null, " [decoding error at " + (this.pos - 1));
        }
        
        void readLiteral(final String literal) {
            for (int i = 0, len = literal.length(); i < len; ++i) {
                if (this.nextChar() != literal.charAt(i)) {
                    this.error();
                }
            }
        }
        
        int nextHexDigit() {
            final char c = this.nextChar();
            if ('0' <= c && c <= '9') {
                return c - '0';
            }
            if ('a' <= c && c <= 'f') {
                return c - 'a' + 10;
            }
            if ('A' <= c && c <= 'F') {
                return c - 'A' + 10;
            }
            this.error();
            return 0;
        }
        
        char readEscape() {
            char c = this.nextChar();
            switch (c) {
                case '\"':
                case '/':
                case '\\': {
                    break;
                }
                case 'b': {
                    c = '\b';
                    break;
                }
                case 'f': {
                    c = '\f';
                    break;
                }
                case 'n': {
                    c = '\n';
                    break;
                }
                case 'r': {
                    c = '\r';
                    break;
                }
                case 't': {
                    c = '\t';
                    break;
                }
                case 'u': {
                    c = (char)(this.nextHexDigit() << 12 | this.nextHexDigit() << 8 | this.nextHexDigit() << 4 | this.nextHexDigit());
                    break;
                }
                default: {
                    this.error();
                    break;
                }
            }
            return c;
        }
        
        String readString() {
            int start = this.pos;
            char c;
            do {
                c = this.nextChar();
                if (c < ' ') {
                    this.error();
                }
                if (c == '\\') {
                    if (this.sb == null) {
                        this.sb = new StringBuilder();
                    }
                    this.sb.append(this.input, start, this.pos - start - 1);
                    this.sb.append(this.readEscape());
                    start = this.pos;
                }
            } while (c != '\"');
            if (this.sb != null) {
                this.sb.append(this.input, start, this.pos - 1 - start);
                final String result = this.sb.toString();
                this.sb.setLength(0);
                return result;
            }
            return String.valueOf(this.input, start, this.pos - start - 1);
        }
        
        double readNumber() {
            final int start = this.pos - 1;
            while (true) {
                final char c = this.nextChar();
                switch (c) {
                    case '+':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'E':
                    case 'e': {
                        continue;
                    }
                    default: {
                        --this.pos;
                    }
                    case '\0': {
                        try {
                            return Double.parseDouble(String.valueOf(this.input, start, this.pos - start));
                        }
                        catch (NumberFormatException e) {
                            this.error();
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        Object readAny() {
            while (true) {
                final char c = this.nextChar();
                switch (c) {
                    case '\t':
                    case '\n':
                    case '\r':
                    case ' ': {
                        continue;
                    }
                    case '\0': {
                        return Control.END;
                    }
                    case '[': {
                        return this.readArray();
                    }
                    case ']': {
                        return Control.ARRAY_END;
                    }
                    case ',': {
                        return Control.COMMA;
                    }
                    case '\"': {
                        return this.readString();
                    }
                    case '-':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        return this.readNumber();
                    }
                    case 'n': {
                        this.readLiteral("ull");
                        return null;
                    }
                    case 't': {
                        this.readLiteral("rue");
                        return true;
                    }
                    case 'f': {
                        this.readLiteral("alse");
                        return false;
                    }
                    default: {
                        this.error();
                        continue;
                    }
                }
            }
        }
        
        Object readArray() {
            Object item = this.readAny();
            if (item == Control.ARRAY_END) {
                return new Object[0];
            }
            if (item instanceof Control) {
                this.error();
            }
            final List<Object> items = new ArrayList<Object>();
            items.add(item);
            while (true) {
                final Object sep = this.readAny();
                if (sep == Control.ARRAY_END) {
                    break;
                }
                if (sep != Control.COMMA) {
                    this.error();
                }
                item = this.readAny();
                if (item instanceof Control) {
                    this.error();
                }
                items.add(item);
            }
            return items.toArray();
        }
        
        Object readTop() {
            final Object item = this.readAny();
            if (item instanceof Control) {
                this.error();
            }
            if (this.readAny() != Control.END) {
                this.error();
            }
            return item;
        }
        
        enum Control
        {
            END, 
            ARRAY_END, 
            COMMA;
        }
    }
    
    static class Writer
    {
        static final String[] ESCAPED;
        StringBuilder sb;
        
        public Writer(final Object object) {
            this.sb = new StringBuilder();
            this.writeAny(object);
        }
        
        void writeAny(final Object object) {
            if (object == null) {
                this.sb.append("null");
            }
            else if (object instanceof Boolean) {
                this.sb.append(object.toString());
            }
            else if (object instanceof Long) {
                this.sb.append((long)object);
            }
            else if (object instanceof Integer) {
                this.sb.append((int)object);
            }
            else if (object instanceof Short) {
                this.sb.append((short)object);
            }
            else if (object instanceof Byte) {
                this.sb.append((byte)object);
            }
            else if (object instanceof Double) {
                this.sb.append((double)object);
            }
            else if (object instanceof Float) {
                this.sb.append((float)object);
            }
            else if (object instanceof String) {
                this.writeString(object.toString());
            }
            else if (object instanceof Object[]) {
                this.writeArray((Object[])object);
            }
            else {
                SWT.error(5, null, " [object not encodable: " + object.getClass());
            }
        }
        
        void writeString(final String s) {
            this.sb.append('\"');
            int start = 0;
            for (int i = 0, len = s.length(); i < len; ++i) {
                final char c = s.charAt(i);
                if (c < Writer.ESCAPED.length && Writer.ESCAPED[c] != null) {
                    this.sb.append(s, start, i);
                    this.sb.append(Writer.ESCAPED[c]);
                    start = i + 1;
                }
            }
            this.sb.append(s, start, s.length());
            this.sb.append('\"');
        }
        
        void writeArray(final Object[] array) {
            this.sb.append('[');
            boolean first = true;
            for (final Object item : array) {
                if (!first) {
                    this.sb.append(',');
                }
                this.writeAny(item);
                first = false;
            }
            this.sb.append(']');
        }
        
        @Override
        public String toString() {
            return this.sb.toString();
        }
        
        static {
            ESCAPED = new String[96];
            for (int i = 0; i < 32; ++i) {
                Writer.ESCAPED[i] = String.format("\\u%04x", i);
            }
            Writer.ESCAPED[8] = "\\b";
            Writer.ESCAPED[12] = "\\f";
            Writer.ESCAPED[10] = "\\n";
            Writer.ESCAPED[13] = "\\r";
            Writer.ESCAPED[9] = "\\t";
            Writer.ESCAPED[34] = "\\\"";
        }
    }
}
