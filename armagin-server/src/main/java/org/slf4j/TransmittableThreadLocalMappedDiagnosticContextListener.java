package org.slf4j;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import org.slf4j.spi.MDCAdapter;
import org.springframework.util.Assert;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/24 13:52
 */
public class TransmittableThreadLocalMappedDiagnosticContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {

    }

    @Override
    public void onReset(LoggerContext loggerContext) {

    }

    @Override
    public void onStop(LoggerContext loggerContext) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }

    @Override
    public void start() {
        addInfo("load TransmittableThreadLocalMappedDiagnosticContextAdapter...");
        MDCAdapter instance = TransmittableThreadLocalMappedDiagnosticContextAdapter.getInstance();
        Assert.notNull(instance, "Instance of TransmittableThreadLocalMappedDiagnosticContextAdapter");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
