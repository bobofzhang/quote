import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.WARN

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger.%M[%C{0}:%L] - %msg%n"
  }
}
appender("FILE", RollingFileAppender) {
  file = "logs/stdout.log"
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger.%M[%C{0}:%L] - %msg%n"
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/stdout.%d{yyyy-MM-dd}.log"
    maxHistory = 30
  }
}
logger("com.gildata", DEBUG)
logger("io.netty", DEBUG)
logger("org.springframework", WARN)
root(WARN, ["STDOUT", "FILE"])