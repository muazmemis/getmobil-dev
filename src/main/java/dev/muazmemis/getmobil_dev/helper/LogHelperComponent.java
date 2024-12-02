package dev.muazmemis.getmobil_dev.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogHelperComponent {

    public static void logInfo(String var1, Object... var2) {
        log.info(var1, var2);
    }

    public static void logError(String var1, Object... var2) {
        log.error(var1, var2);
    }

}
