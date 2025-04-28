package me.choicore.samples.configuration.spring.async

import org.slf4j.MDC
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator

@Configuration(proxyBeanMethods = false)
class AsyncConfiguration {
    @Bean
    fun mappedDiagnosticContextTaskDecorator(): TaskDecorator =
        TaskDecorator { runnable ->
            val contextMap = MDC.getCopyOfContextMap()
            Runnable {
                contextMap?.let { MDC.setContextMap(it) }
                try {
                    runnable.run()
                } finally {
                    MDC.clear()
                }
            }
        }
}
