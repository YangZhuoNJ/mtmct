//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.catalina.connector.http;

public final class HttpHeader {
    public static final int INITIAL_NAME_SIZE = 32;
    public static final int INITIAL_VALUE_SIZE = 64;
    public static final int MAX_NAME_SIZE = 128;
    public static final int MAX_VALUE_SIZE = 4096;
    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;
    protected int hashCode;

    public HttpHeader() {
        this(new char[32], 0, new char[64], 0);
    }

    public HttpHeader(String name, String value) {
        this.hashCode = 0;
        this.name = name.toLowerCase().toCharArray();
        this.nameEnd = name.length();
        this.value = value.toCharArray();
        this.valueEnd = value.length();
    }

    public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
        this.hashCode = 0;
        this.name = name;
        this.nameEnd = nameEnd;
        this.value = value;
        this.valueEnd = valueEnd;
    }

    public boolean equals(Object obj) {
        return obj instanceof String?this.equals(((String)obj).toLowerCase()):(obj instanceof HttpHeader?this.equals((HttpHeader)obj):false);
    }

    public boolean equals(String str) {
        return this.equals(str.toCharArray(), str.length());
    }

    public boolean equals(HttpHeader header) {
        return this.equals(header.name, header.nameEnd);
    }

    public boolean equals(char[] buf) {
        return this.equals(buf, buf.length);
    }

    public boolean equals(char[] buf, int end) {
        if(end != this.nameEnd) {
            return false;
        } else {
            for(int i = 0; i < end; ++i) {
                if(buf[i] != this.name[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    public int hashCode() {
        int h = this.hashCode;
        if(h == 0) {
            int off = 0;
            char[] val = this.name;
            int len = this.nameEnd;

            for(int i = 0; i < len; ++i) {
                h = 31 * h + val[off++];
            }

            this.hashCode = h;
        }

        return h;
    }

    public boolean headerEquals(HttpHeader header) {
        return this.equals(header.name, header.nameEnd) && this.valueEquals(header.value, header.valueEnd);
    }

    public void recycle() {
        this.nameEnd = 0;
        this.valueEnd = 0;
        this.hashCode = 0;
    }

    public boolean valueEquals(String str) {
        return this.valueEquals(str.toCharArray(), str.length());
    }

    public boolean valueEquals(char[] buf) {
        return this.valueEquals(buf, buf.length);
    }

    public boolean valueEquals(char[] buf, int end) {
        if(end != this.valueEnd) {
            return false;
        } else {
            for(int i = 0; i < end; ++i) {
                if(buf[i] != this.value[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean valueIncludes(String str) {
        return this.valueIncludes(str.toCharArray(), str.length());
    }

    public boolean valueIncludes(char[] buf) {
        return this.valueIncludes(buf, buf.length);
    }

    public boolean valueIncludes(char[] buf, int end) {
        char firstChar = buf[0];

        for(int pos = 0; pos < this.valueEnd; ++pos) {
            pos = this.valueIndexOf(firstChar, pos);
            if(pos == -1) {
                return false;
            }

            if(this.valueEnd - pos < end) {
                return false;
            }

            for(int i = 0; i < end && this.value[i + pos] == buf[i]; ++i) {
                if(i == end - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public int valueIndexOf(char c, int start) {
        for(int i = start; i < this.valueEnd; ++i) {
            if(this.value[i] == c) {
                return i;
            }
        }

        return -1;
    }
}
