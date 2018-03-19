package io.shelves.common.utils;

/**
 * 常量
 *
 * @author Five.liu
 * @since 1.0.0 2016-11-15
 */
public class Constant {

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
