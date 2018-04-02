package com.roof.fpa.customer.entity;

import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * com.roof.fpa.customer.entity
 *
 * @author liht
 * @date 2018/3/31
 */
public class CustomerTypeTransform {

    /**
     * 测试用户标记位
     */
    private final static int TEST_USER_TAG_OFFSET = 0;

    /**
     * 1为测试用户 0为正常的用户
     */
    private final static int TEST_USER = (1 << TEST_USER_TAG_OFFSET);

    private final static String TEST_USER_VIEW = "test_user";

    /**
     * xx用户标记位
     */
    private final static int XX_USER_TAG_OFFSET = 1;

    /**
     * 1为xx用户 0为正常的用户
     */
    private final static int XX_USER = (1 << XX_USER_TAG_OFFSET);

    private final static String XX_USER_VIEW = "xx_user";

    /**
     * 判断是否为test user
     *
     * @param usertag
     * @return
     */
    public static boolean isTestUser(long usertag) {
        return (usertag & TEST_USER) == TEST_USER;
    }

    /**
     * 判断是否为xx user
     *
     * @param usertag
     * @return
     */
    public static boolean isXXUser(long usertag) {
        return (usertag & XX_USER) == XX_USER;
    }

    /**
     * 创建默认的用户
     *
     * @param userTag
     * @return
     */
    public static long defaultAddUserTag(long... userTag) {
        long i = 0;
        for (long l : userTag
                ) {
            i = i | l;
        }
        return i;
    }

    /**
     * 添加用户状态
     *
     * @param i
     * @param userTag
     * @return
     */
    public static long addUserTag(long i, long... userTag) {
        for (long l : userTag
                ) {
            i = i | l;
        }
        return i;
    }

    /**
     * 删除用户状态
     *
     * @param i
     * @param userTag
     * @return
     */
    public static long deleteUserTag(long i, long... userTag) {
        for (long l : userTag
                ) {
            i = i ^ l;
        }
        return i;
    }

    /**
     * 获取用户的标签
     *
     * @param i
     * @return
     */
    public static String[] getAllUserTag(long i) {
        String[] a = new String[0];
        if (isTestUser(i)) {
            a = StringUtils.addStringToArray(a, TEST_USER_VIEW);
        }
        if (isXXUser(i)) {
            a = StringUtils.addStringToArray(a, XX_USER_VIEW);
        }

        return a;
    }

    public static void main(String[] args) {
        long usertag = defaultAddUserTag(TEST_USER, XX_USER);
        System.out.println(usertag);
        System.out.println(Long.toBinaryString(usertag));

        System.out.println(isTestUser(usertag));
        System.out.println(isXXUser(usertag));

        System.out.println(Arrays.asList(getAllUserTag(usertag)));

        usertag = deleteUserTag(usertag, XX_USER);
        System.out.println(usertag);
        System.out.println(Long.toBinaryString(usertag));

        System.out.println(isTestUser(usertag));
        System.out.println(isXXUser(usertag));

        System.out.println(Arrays.asList(getAllUserTag(usertag)));

    }
}
