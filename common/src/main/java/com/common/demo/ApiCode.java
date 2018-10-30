package com.common.demo;

public enum ApiCode {
    Public_SUCCESSFULLY(0) {
        public String getMessage() {
            return "调用接口成功!";
        }
    },
    Public_Error(1) {
        public String getMessage() {
            return "调用接口失败!";
        }
    },
    USERNAME_OR_PASSWORD_ERROR(8000) {
        public String getMessage() {
            return "用户名或密码错误!";
        }
    },
    ERROR_WHEN_LOGIN(8001) {
        public String getMessage() {
            return "登录过程中发生错误!";
        }
    },
    NOT_LOGIN_YET(8002) {
        public String getMessage() {
            return "尚未登录!";
        }
    },
    USERNAME_IS_EMPTY(8003) {
        public String getMessage() {
            return "用户名为空!";
        }
    },
    PASSWORD_IS_EMPTY(8004) {
        public String getMessage() {
            return "密码为空!";
        }
    },
    TOKEN_IS_EMPTY(8005) {
        public String getMessage() {
            return "用户凭证不完整!";
        }
    },
    TOKEN_IS_INVALID(8006) {
        public String getMessage() {
            return "非法的用户凭证!";
        }
    },
    VERIFYCODE_IS_NULL(8007) {
        public String getMessage() {
            return "验证码为空!";
        }
    },
    USERNAME_LENGTH_INVALID(8008) {
        public String getMessage() {
            return "用户名只能由4-20个字符组成!";
        }
    },

    USER_ALREADY_EXISTS(8009) {
        public String getMessage() {
            return "账号名已被注册";
        }
    },
    REGISTER_SUCCESSFULLY(8011) {
        public String getMessage() {
            return "注册成功!";
        }
    },
    LOGIN_SUCCESSFULLY(8012) {
        public String getMessage() {
            return "登录成功!";
        }
    },
    NO_AUTHORITY(8013) {
        public String getMessage() {
            return "无权限访问该接口";
        }
    };


    private final int value;

    private ApiCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract String getMessage();


}
