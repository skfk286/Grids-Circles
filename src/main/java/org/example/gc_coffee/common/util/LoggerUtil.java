package org.example.gc_coffee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static Logger getLogger() {
        // 스택 트레이스에서 호출한 클래스의 이름을 가져옴
        return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}
