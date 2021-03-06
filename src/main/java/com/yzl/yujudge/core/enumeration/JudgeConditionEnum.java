package com.yzl.yujudge.core.enumeration;

/**
 * 判题状态枚举类
 *
 * @author yuzhanglong
 * @date 2020-7-29 13:32:03
 */

public enum JudgeConditionEnum {
    // 等待排队中
    WAITING,

    // 判题中
    PENDING,

    // 判题完成
    SUCCESS,

    // 判题出现异常
    ERROR
}
