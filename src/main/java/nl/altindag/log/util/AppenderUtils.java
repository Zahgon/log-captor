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
package nl.altindag.log.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import nl.altindag.log.appender.InMemoryAppender;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;
import static org.slf4j.Logger.ROOT_LOGGER_NAME;

/**
 * @author Hakan Altindag
 */
public final class AppenderUtils {

    public static final String CONSOLE_APPENDER_NAME = "console";

    public static final String IN_MEMORY_APPENDER_NAME = "logcaptor-in-memory-appender";

    private static final String DEFAULT_LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSSXXX} %-5level [%thread] %logger{36} - %msg%n";

    private AppenderUtils() {
    }

    public static ConsoleAppender<ILoggingEvent> createConsoleAppender(LoggerContext loggerContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Encoder<ILoggingEvent> createEncoder(LoggerContext loggerContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Optional<ConsoleAppender<ILoggingEvent>> getConsoleAppender(Logger logger) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static InMemoryAppender<ILoggingEvent> configureInMemoryAppender(Logger logger, List<ILoggingEvent> eventsCollector) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ConsoleAppender<ILoggingEvent> createConsoleAppender(Logger logger, ConsoleAppender<ILoggingEvent> consoleAppender) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Logger getRootLogger(Logger logger) {
        return logger.getLoggerContext().getLogger(ROOT_LOGGER_NAME);
    }
}
