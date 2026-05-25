/*
 * Copyright 2019 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.filter.Filter;
import nl.altindag.log.appender.InMemoryAppender;
import nl.altindag.log.model.LogEvent;
import nl.altindag.log.util.AppenderUtils;
import nl.altindag.log.util.JavaUtilLoggingLoggerUtils;
import nl.altindag.log.util.LogbackUtils;
import nl.altindag.log.util.Mappers;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.slf4j.Logger.ROOT_LOGGER_NAME;

/**
 * @author Hakan Altindag
 */
public final class LogCaptor implements AutoCloseable {

    private static final Map<String, Level> logLevelContainer = new ConcurrentHashMap<>();

    private final Logger logger;

    private final InMemoryAppender<ILoggingEvent> inMemoryAppender;

    private ConsoleAppender<ILoggingEvent> consoleAppender;

    private final List<ILoggingEvent> eventsCollector = new CopyOnWriteArrayList<>();

    private LogCaptor(String loggerName) {
        logger = LogbackUtils.getLogger(loggerName);
        inMemoryAppender = AppenderUtils.configureInMemoryAppender(logger, eventsCollector);
        JavaUtilLoggingLoggerUtils.redirectToSlf4j(loggerName);
        logLevelContainer.putIfAbsent(logger.getName(), logger.getEffectiveLevel());
    }

    /**
     * Captures all log messages
     *
     * @return LogCaptor instance for the root logger
     */
    public static LogCaptor forRoot() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Captures log messages for the provided class
     *
     * @param clazz Class for capturing
     * @return LogCaptor instance for the provided class
     */
    public static LogCaptor forClass(Class<?> clazz) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Captures log messages for the provided logger name
     *
     * @param name Logger name for capturing
     * @return LogCaptor instance for the provided logger name
     */
    public static LogCaptor forName(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getInfoLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getDebugLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getWarnLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getErrorLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getTraceLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private List<String> getLogs(Level level) {
        return getLogs(logEvent -> logEvent.getLevel() == level, ILoggingEvent::getFormattedMessage);
    }

    public List<LogEvent> getLogEvents() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> List<T> getLogs(Predicate<ILoggingEvent> logEventPredicate, Function<ILoggingEvent, T> logEventMapper) {
        synchronized (eventsCollector) {
            return eventsCollector.stream().filter(logEventPredicate).map(logEventMapper).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        }
    }

    public boolean hasMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasInfoMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasDebugMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasWarnMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasErrorMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasTraceMessage(String message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean hasMessage(Level level, String message) {
        return getLogs(logEvent -> logEvent.getLevel() == level, ILoggingEvent::getFormattedMessage).stream().anyMatch(log -> log.contains(message));
    }

    public void addFilter(Filter<ILoggingEvent> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Overrides the log level property of the target logger. This may result that the overridden property
     * of the target logger is still active even though a new instance of {@link LogCaptor} has been created.
     * To roll-back to the initial state use: {@link LogCaptor#resetLogLevel()}
     *
     * This option will implicitly include the following log levels: WARN and ERROR
     */
    public void setLogLevelToInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Overrides the log level property of the target logger. This may result that the overridden property
     * of the target logger is still active even though a new instance of {@link LogCaptor} has been created.
     * To roll-back to the initial state use: {@link LogCaptor#resetLogLevel()}
     *
     * This option will implicitly include the following log levels: INFO, WARN and ERROR
     */
    public void setLogLevelToDebug() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Overrides the log level property of the target logger. This may result that the overridden property
     * of the target logger is still active even though a new instance of {@link LogCaptor} has been created.
     * To roll-back to the initial state use: {@link LogCaptor#resetLogLevel()}
     *
     * This option will implicitly include the following log levels: INFO, DEBUG, WARN and ERROR
     */
    public void setLogLevelToTrace() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Overrides the log level property of the target logger. This may result that the overridden property
     * of the target logger is still active even though a new instance of {@link LogCaptor} has been created.
     * To roll-back to the initial state use: {@link LogCaptor#resetLogLevel()}
     */
    public void disableLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Disables the output of the log entries to the console. To revert this option use {@link LogCaptor#enableConsoleOutput()}.
     * LogCaptor will still be capturing the log entries.
     */
    public void disableConsoleOutput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The output of the log entries to the console are enabled by default but can be re-enabled if
     * they are disabled earlier by {@link LogCaptor#disableConsoleOutput()}
     */
    public void enableConsoleOutput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Logger getRootLogger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Logger getLogger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Resets the log level of the target logger to the initial value which was available before
     * changing it with {@link LogCaptor#setLogLevelToInfo()}, {@link LogCaptor#setLogLevelToDebug()} or with {@link LogCaptor#setLogLevelToTrace()}
     */
    public void resetLogLevel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearLogs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void reconfigure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
