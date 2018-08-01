package com.shls.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 11/08/2017.
 */
public class CommonUtils
{

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月d日");
    private static final String EMPTY_DATE_STRING = "     年     月     日";
    private static final String AMOUNT_DIGIT[] = {"壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 转换日期为 xxxx年xx月xx日
     *
     * @param date
     * @return
     */
    public static String formatDate(java.sql.Date date)
    {
        if (date != null)
        {
            try
            {
                return dateFormat.format(date);
            }
            catch (Exception e)
            {
                logger.error("日期格式化失败, 返回默认字符串: " + EMPTY_DATE_STRING);
                return EMPTY_DATE_STRING;
            }
        }
        else
        {
            return EMPTY_DATE_STRING;
        }
    }


    /**
     * 转换日期为指定格式
     *
     * @param date
     * @return
     */
    public static String formatDate(java.sql.Date date, String format)
    {
        if (date != null)
        {
            if (format == null)
            {
                return formatDate(date);
            }
            return new SimpleDateFormat(format).format(date);
        }
        else
        {
            //return format.replaceAll("y", " ").replaceAll("M", "    ").replaceAll("d", "     ");
            return "";
        }
    }


    /**
     * 转换日期为指定格式
     *
     * @param date
     * @return
     */
    public static String formatDateDefault(java.sql.Date date, String format, String def)
    {
        if (date != null)
        {
            if (format == null)
            {
                return formatDate(date);
            }
            return new SimpleDateFormat(format).format(date);
        }
        else
        {
            return def == null ? "" : def;
        }
    }


    /**
     * 计算date加上指定days后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static String datePlus(java.sql.Date date, int days)
    {
        if (date != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, days);

            return dateFormat.format(calendar.getTime());
        }

        return EMPTY_DATE_STRING;
    }

    /**
     * 格式化金额，如123,456.79
     *
     * @param amount    原始金额
     * @param precision 小数位数
     * @return
     */
    public static String formatAmount(double amount, int precision)
    {
        return _formatAmount(amount, precision);
    }

    /**
     * 格式化金额，如123,456.79
     *
     * @param amount    原始金额
     * @param precision 小数位数
     * @return
     */
    public static String formatAmount(String amount, int precision)
    {
        BigDecimal decimal = new BigDecimal(amount);
        return _formatAmount(decimal, precision);
    }

    private static String _formatAmount(Object amount, int precision)
    {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        numberFormat.setMaximumFractionDigits(precision);
        String formated = numberFormat.format(amount);
        if (formated.length() > 0)
        {
            return formated.substring(1);
        }

        return "0.00";
    }


    /**
     * 金额转大写
     *
     * @return
     */
/*    public static String amountCapital(String num)
    {
        if (num.equals("null"))
        {
            return "";
        }

        String fraction[] = {"角", "分"};
        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        */

    /**
     * 仟        佰        拾         ' '
     * ' '    $4        $3        $2         $1
     * 万     $8        $7        $6         $5
     * 亿     $12       $11       $10        $9
     *//*
        String unit1[] = {"", "拾", "佰", "仟"};//把钱数分成段,每四个一段,实际上得到的是一个二维数组
        String unit2[] = {"元", "万", "亿", "万亿"}; //把钱数分成段,每四个一段,实际上得到的是一个二维数组
        BigDecimal bigDecimal = new BigDecimal(num);
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        String strVal = String.valueOf(bigDecimal.toBigInteger());
        if (strVal.equals("0"))
        {
            return "零元";
        }
        String head = strVal.substring(0, strVal.length() - 2);         //整数部分
        String end = strVal.substring(strVal.length() - 2);              //小数部分
        String endMoney = "";
        String headMoney = "";
        if ("00".equals(end))
        {
            endMoney = "整";
        }
        else
        {
            if (!end.substring(0, 1).equals("0"))
            {
                endMoney += digit[Integer.valueOf(end.substring(0, 1))] + "角";
            }
            else if (end.substring(0, 1).equals("0") && !end.substring(1, 2).equals("0"))
            {
                endMoney += "零";
            }
            if (!end.substring(1, 2).equals("0"))
            {
                endMoney += digit[Integer.valueOf(end.substring(1, 2))] + "分";
            }
        }
        char[] chars = head.toCharArray();
        Map<String, Boolean> map = new HashMap<>();//段位置是否已出现zero
        boolean zeroKeepFlag = false;//0连续出现标志
        int vidxtemp = 0;
        for (int i = 0; i < chars.length; i++)
        {
            int idx = (chars.length - 1 - i) % 4;//段内位置  unit1
            int vidx = (chars.length - 1 - i) / 4;//段位置 unit2
            String s = digit[Integer.valueOf(String.valueOf(chars[i]))];
            if (!"零".equals(s))
            {
                headMoney += s + unit1[idx] + unit2[vidx];
                zeroKeepFlag = false;
            }
            else if (i == chars.length - 1 || map.get("zero" + vidx) != null)
            {
                headMoney += "";
            }
            else
            {
                headMoney += s;
                zeroKeepFlag = true;
                map.put("zero" + vidx, true);//该段位已经出现0；
            }
            if (vidxtemp != vidx || i == chars.length - 1)
            {
                headMoney = headMoney.replaceAll(unit2[vidx], "");
                headMoney += unit2[vidx];
            }
            if (zeroKeepFlag && (chars.length - 1 - i) % 4 == 0)
            {
                headMoney = headMoney.replaceAll("零", "");
            }
        }
        return headMoney + endMoney;
    }*/
    public static java.sql.Date getDateEnd(Date beginDate)
    {
        if (beginDate != null)
        {


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            calendar.add(Calendar.YEAR, 1);
            calendar.add(Calendar.DAY_OF_YEAR, -1);

            Date date = calendar.getTime();

            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    /**
     * 比较两个对象，返回一个{@link BeanDifferent} 对象，包含比较的对象之前不相同的属性
     */
   /* public static List<BeanDifferent> beanDifferent(Object original, Object newObj, String... ignoreProperties)
    {
        if (original == newObj)
        {
            return null;
        }

        if (!original.getClass().equals(newObj.getClass()))
        {
            throw new IllegalArgumentException("比较的两个对象必须是同一类型");
        }

        Class clazz = original.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        fieldList.addAll(Arrays.asList(fields));
        fieldList.addAll(Arrays.asList(superFields));

        List<BeanDifferent> diffList = new ArrayList<>();

        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : new ArrayList<>());

        try
        {
            PropertyDescriptor pd;
            Method getMethod;

            BeanDifferent diff;
            for (Field field : fieldList)
            {
                //System.out.println(field);
                String fieldName = field.getName();
                if (ignoreList.contains(fieldName))
                {
                    continue;
                }

                boolean isBoolean = field.getType() == boolean.class || field.getType() == Boolean.class;

                String colName = (field.getName().startsWith("is") && isBoolean) ? field.getName().substring(2) : field.getName();
                pd = new PropertyDescriptor(colName, clazz);
                //pd = new PropertyDescriptor(field.getName(), clazz);
                getMethod = pd.getReadMethod();

                Object value1 = getMethod.invoke(original);
                Object value2 = getMethod.invoke(newObj);

                if (value1 != null && value2 != null)
                {
                    //System.out.println(fileName + " -- " + value1.equals(value2));
                    if (value1 instanceof Boolean && value2 instanceof Boolean)
                    {
                        boolean b1 = (boolean) value1;
                        boolean b2 = (boolean) value2;
                        value1 = b1 ? 1 : 0;
                        value2 = b2 ? 1 : 0;
                    }

                    if (!value1.equals(value2))
                    {
                        Class type = field.getType();
                        diff = new BeanDifferent(fieldName, value1, value2, field.getType());
                        diffList.add(diff);
                    }
                }
                else if (value1 == null && value2 != null)
                {
                    diff = new BeanDifferent(fieldName, null, value2, field.getType());
                    diffList.add(diff);
                }
                else if (value1 != null && value2 == null)
                {
                    diff = new BeanDifferent(fieldName, value1, null, field.getType());
                    diffList.add(diff);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return diffList;
    }*/

    /**
     * 获取文件后缀
     * Created by Island on 12/10/2017.
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName)
    {
        if (fileName == null) return "";
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }


    /**
     * 获取当年起始日期和结束日期的时间戳
     */
    public static long[] getStartAndEndTimeInYear()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        long startTime = calendar.getTimeInMillis();

        calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        long endTime = calendar.getTimeInMillis();

        return new long[]{startTime, endTime};
    }


    /**
     * 生成编号, 数字前自动补0, 如:0001
     *
     * @param index  编号数字部分
     * @param length 总编号长度, 如0001长度为4
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static String generateNumber(int index, int length, String prefix, String suffix)
    {
        String numberStr = String.valueOf(index);
        int zeroCount = length - numberStr.length();

        for (int i = 0; i < zeroCount; i++)
        {
            numberStr = "0" + numberStr;
        }

        if (prefix != null)
        {
            numberStr = prefix + numberStr;
        }

        if (suffix != null)
        {
            numberStr += suffix;
        }

        return numberStr;
    }

    /**
     * Created by Island on 2017/12/26
     * 去除remove中与compare重复的元素
     * return:removeList={remove}-{remove}∩{compare}
     */
    public static List removeDupElements(long[] remove, long[] compare)
    {
        List removeList = new ArrayList();
        for (int i = 0; i < remove.length; i++)
        {
            removeList.add(remove[i]);
        }
        List compareList = new ArrayList();
        for (int j = 0; j < compare.length; j++)
        {
            compareList.add(compare[j]);
        }

        removeList.removeAll(compareList);

        return removeList;
    }


    public static String amountCapital(String str)
    {
        str = str.replaceAll(",", "");
        if (str.indexOf(".") < 0)
        {
            str += ".00";
        }

        String s[] = str.split("\\.");
        StringBuilder builder = new StringBuilder();
        int zeroCount = 0;
        int digCount = 0;
        int pos = 0;
        for (int j = 0; j < s[0].length(); ++j)
        {
            int num = s[0].charAt(j) - '0';
            if (num == 0)
            {
                zeroCount++;
                if (zeroCount == s[0].length())
                {
                    builder.append("零");
                }
                else if (s[0].length() - j - 1 == 4)
                {
                    if (digCount == 1 && (s[0].length() - pos - 1) >= 5 && (s[0].length() - pos - 1) <= 7)
                    {
                        builder.append("万");
                    }
                }
                else if (s[0].length() - j - 1 == 8)
                {
                    if (digCount == 1 && (s[0].length() - pos - 1) >= 9 && (s[0].length() - pos - 1) <= 11)
                    {
                        builder.append("亿");
                    }
                }
            }
            else
            {
                digCount++;
                int flag = 0;
                if (digCount == 1)
                {
                    zeroCount = 0;
                    pos = j;
                }
                if (digCount == 2)
                {
                    flag = 1;
                    if (zeroCount > 0)
                    {
                        builder.append("零");
                    }
                    digCount = 1;
                    pos = j;
                    zeroCount = 0;
                }
                if (s[0].length() - j - 1 == 11)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "仟");
                }
                else if (s[0].length() - j - 1 == 10)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "佰");
                }
                else if (s[0].length() - j - 1 == 9)
                {
                    if (num == 1 && flag != 1)
                     builder.append("拾");
                    else
                        builder.append(AMOUNT_DIGIT[num - 1] + "拾");
                }
                else if (s[0].length() - j - 1 == 8)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "亿");
                }
                else if (s[0].length() - j - 1 == 7)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "仟");
                }
                else if (s[0].length() - j - 1 == 6)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "佰");
                }
                else if (s[0].length() - j - 1 == 5)
                {
                    if (num == 1 && flag != 1)
                        builder.append("拾");
                    else
                        builder.append(AMOUNT_DIGIT[num - 1] + "拾");
                }
                else if (s[0].length() - j - 1 == 4)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "万");
                }
                else if (s[0].length() - j - 1 == 3)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "仟");
                }
                else if (s[0].length() - j - 1 == 2)
                {
                    builder.append(AMOUNT_DIGIT[num - 1] + "佰");
                }
                else if (s[0].length() - j - 1 == 1)
                {
                    if (num == 1 && flag != 1)
                        builder.append("拾");
                    else
                        builder.append(AMOUNT_DIGIT[num - 1] + "拾");
                }
                else
                {
                    builder.append(AMOUNT_DIGIT[num - 1]);
                }
            }
        }
        builder.append("元");
        if (s[1].equals("00") || s[1].equals("0") || s[1].length() == 0)
        {
            builder.append("整");
        }
        else
        {
            for (int j = 0; j < s[1].length(); ++j)
            {
                int num = s[1].charAt(j) - '0';
                if (j == 0)
                {
                    if (num != 0)
                    {
                        builder.append(AMOUNT_DIGIT[num - 1] + "角");
                    }
                    else if (num == 0 && 1 < s[1].length() && s[1].charAt(1) != '0')
                    {
                        builder.append("零");
                    }
                }
                else if (j == 1)
                {
                    if (num != 0)
                    {
                        builder.append(AMOUNT_DIGIT[num - 1] + "分");
                    }
                    else
                    {
                        builder.append("整");
                    }
                }
            }
        }
        return builder.toString();
    }


    /*public static void main(String[] aa) throws ParseException, IllegalAccessException, java.text.ParseException, IntrospectionException, PredefinedObjectObtainFailedException, NoSuchMethodException, InvocationTargetException
    {

        System.out.println(amountCapital("123.01"));

    }*/


    /**
     * @author hujunhao
     * 2018/4/10
     * 判断时间格式是否正确
     * @param date
     * @return
     */
    public  static boolean isDate(String date)
    {
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }

    /**
     * @author hujunhao
     * 2018/4/10
     * 判断金额格式是否正确
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        Pattern pattern=Pattern.compile("(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{3})"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * @author hujunhao
     * 2018/4/13
     * 判断金额格式是否正确,可以是12,12.1,12.11
     * @param str
     * @return
     */
    public static boolean checkIsNumber(String str){
        Pattern pattern=Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }
}
