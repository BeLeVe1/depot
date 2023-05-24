package com.hd.microblog.util;

import java.time.Duration;

public class time {
    public static String getTimeString(long startTime, long endTime) {
        long durationMillis = endTime - startTime; // 计算时间间隔，单位为毫秒

        Duration duration = Duration.ofMillis(durationMillis); // 转换为Duration对象

        // 使用Duration对象计算时、分、秒信息
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        String timeString;
        if (hours > 0) {
            timeString = String.format("%d小时:%02d分:%02d秒", hours, minutes, seconds);
        } else if (minutes > 0) {
            timeString = String.format("%d分:%02d秒", minutes, seconds);
        } else {
            timeString = String.format("%d秒", seconds);
        }
        return  timeString;
    }
}
