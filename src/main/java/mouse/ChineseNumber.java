package mouse;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将汉字数字转换为数字
 */
public class ChineseNumber {
    private ChineseNumber() {
    }

    private static Pattern pattern = Pattern.compile("[一二三四五六七八九][十百千]?");
    private static Map<Character, Integer> unit = new HashMap<Character, Integer>();
    private static Map<Character, Integer> num = new HashMap<Character, Integer>();
    static {
        unit.put('十', 10);
        unit.put('百', 100);
        unit.put('千', 1000);
        unit.put('万', 10000);
        unit.put('亿', 10000000);
        num.put('一', 1);
        num.put('二', 2);
        num.put('三', 3);
        num.put('四', 4);
        num.put('五', 5);
        num.put('六', 6);
        num.put('七', 7);
        num.put('八', 8);
        num.put('九', 9);
    }

    /**
     * 将小于一万的汉字数字，转换为BigInteger
     * 
     * @param chnNum
     * @return
     */
    private static BigInteger getNumber(String chnNum) {
        BigInteger number = BigInteger.valueOf(0);
        Matcher m = pattern.matcher(chnNum);
        m.reset(chnNum);
        while (m.find()) {
            String subNumber = m.group();
            if (subNumber.length() == 1) {
                number = number.add(BigInteger.valueOf(num.get(subNumber.charAt(0))));
            } else if (subNumber.length() == 2) {
                number = number.add(BigInteger.valueOf(num.get(subNumber.charAt(0))).multiply(BigInteger.valueOf(unit.get(subNumber.charAt(1)))));
            }
        }
        return number;
    }

    /**
     * 分拆实现
     * 将汉字转换为数字
     * 
     * @param num
     * @return
     */
    public static int parseNumber(String chnNum) {
        chnNum = chnNum.replaceAll("(?<![一二三四五六七八九])十", "一十").replaceAll("零", "");
        Pattern pattern = Pattern.compile("[万亿]");
        Matcher m = pattern.matcher(chnNum);
        BigInteger result = BigInteger.valueOf(0);
        int index = 0;
        while (m.find()) {
            int end = m.end();
            int multiple = unit.get(m.group().charAt(0));
            String num = chnNum.substring(index, m.start());
            result = result.add(getNumber(num)).multiply(BigInteger.valueOf(multiple));
            index = end;
        }
        String num = chnNum.substring(index);
        result = result.add(getNumber(num));
        return result.intValue();
    }
    
    public static void main(String[] args) {
    		System.out.println(ChineseNumber.parseNumber("第一百万五千三百二十五章节"));
    }
}