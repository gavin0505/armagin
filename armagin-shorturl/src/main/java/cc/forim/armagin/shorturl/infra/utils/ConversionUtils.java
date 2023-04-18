package cc.forim.armagin.shorturl.infra.utils;

/**
 * 进制转换器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 10:54
 */
public enum ConversionUtils {

    /**
     * 单例
     */
    X;

    private static final String CHARS = "oNWxUYwrXdCOIj4ck6M8RbiQa3H91pSmZTAh70zquLnKvt2VyEGlBsPJgDe5Ff";
    private static final int SCALE = 62;
    private static final int MIN_LENGTH = 5;

    /**
     * 数字转62进制
     *
     * @param num num
     * @return String
     */
    public String encode62(long num) {
        StringBuilder builder = new StringBuilder();
        int remainder;
        while (num > SCALE - 1) {
            remainder = Long.valueOf(num % SCALE).intValue();
            builder.append(CHARS.charAt(remainder));
            num = num / SCALE;
        }
        builder.append(CHARS.charAt(Long.valueOf(num).intValue()));
        String value = builder.reverse().toString();
        return leftPad(value, MIN_LENGTH, '0');
    }

    /**
     * 62进制转为数字
     *
     * @param string string
     * @return long
     */
    public long decode62(String string) {
        string = string.replace("^0*", "");
        long value = 0;
        char tempChar;
        int tempCharValue;
        for (int i = 0; i < string.length(); i++) {
            tempChar = string.charAt(i);
            tempCharValue = CHARS.indexOf(tempChar);
            value += (long) (tempCharValue * Math.pow(SCALE, string.length() - i - 1));
        }
        return value;
    }

    /**
     * 字符串左补齐
     *
     * @param str     源字符串
     * @param size    补齐下限长度
     * @param padChar 补齐填充字符
     * @return 完成补齐的字符串
     */
    private static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        } else {
            int pads = size - str.length();
            if (pads <= 0) {
                return str;
            } else {
                return pads > 8192 ? leftPad(str, size, String.valueOf(padChar)) : repeat(padChar, pads).concat(str);
            }
        }
    }


    private static String repeat(char ch, int repeat) {
        if (repeat <= 0) {
            return "";
        } else {
            char[] buf = new char[repeat];

            for (int i = repeat - 1; i >= 0; --i) {
                buf[i] = ch;
            }

            return new String(buf);
        }
    }

    private static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        } else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if (pads <= 0) {
                return str;
            } else if (padLen == 1 && pads <= 8192) {
                return leftPad(str, size, padStr.charAt(0));
            } else if (pads == padLen) {
                return padStr.concat(str);
            } else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for (int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}