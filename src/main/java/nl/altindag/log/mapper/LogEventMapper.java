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
package nl.altindag.log.mapper;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import nl.altindag.log.model.LogEvent;
import nl.altindag.log.model.LogMarker;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <strong>NOTE:</strong>
 * Please don't use this class directly as it is part of the internal API. Class name and methods can be changed any time.
 *
 * @author Hakan Altindag
 */
public final class LogEventMapper implements Function<ILoggingEvent, LogEvent> {

    private static final LogEventMapper INSTANCE = new LogEventMapper();

    private LogEventMapper() {
    }

    @Override
    public LogEvent apply(ILoggingEvent iLoggingEvent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<Map.Entry<String, Object>> mapKeyValuePairs(ILoggingEvent iLoggingEvent) {
        if (iLoggingEvent.getKeyValuePairs() == null) {
            return Collections.emptyList();
        }
        return iLoggingEvent.getKeyValuePairs().stream().map(keyValuePair -> new SimpleImmutableEntry<>(keyValuePair.key, keyValuePair.value)).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private static Throwable mapThrowable(ILoggingEvent iLoggingEvent) {
        return Optional.ofNullable(iLoggingEvent.getThrowableProxy()).filter(ThrowableProxy.class::isInstance).map(ThrowableProxy.class::cast).map(ThrowableProxy::getThrowable).orElse(null);
    }

    private static List<Object> mapArguments(ILoggingEvent iLoggingEvent) {
        return Optional.ofNullable(iLoggingEvent.getArgumentArray()).map(Arrays::asList).map(Collections::unmodifiableList).orElseGet(Collections::emptyList);
    }

    private static List<LogMarker> mapMarkers(ILoggingEvent iLoggingEvent) {
        if (iLoggingEvent.getMarkerList() == null) {
            return Collections.emptyList();
        }
        return iLoggingEvent.getMarkerList().stream().map(MarkerMapper.getInstance()).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public static LogEventMapper getInstance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
