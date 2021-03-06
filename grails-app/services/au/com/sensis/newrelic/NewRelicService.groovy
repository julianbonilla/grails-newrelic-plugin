package au.com.sensis.newrelic

import com.newrelic.api.agent.NewRelic
import com.newrelic.api.agent.Request
import com.newrelic.api.agent.Response
import grails.util.Environment
import org.codehaus.groovy.grails.commons.GrailsApplication

class NewRelicService {
    static transactional = false

    def grailsApplication

    // Record metrics
   	def recordResponseTimeMetric(String name, long millis) {
        if (enabled) {
            NewRelic.recordResponseTimeMetric(name, millis);
        }
    }

    def recordMetric(String name, float value) {
        if (enabled) {
            NewRelic.recordMetric(name, value);
        }
    }

   	def incrementCounter(String name) {
        if (enabled) {
            NewRelic.incrementCounter(name);
        }
    }

    def incrementCounter(String name, int count) {
        if (enabled) {
            NewRelic.incrementCounter(name, count);
        }
    }

    // Record errors
   	def noticeError(Throwable throwable, Map<String, String> params) {
        if (enabled) {
            NewRelic.noticeError(throwable, params);
        }
    }

   	def noticeError(Throwable throwable) {
        if (enabled) {
            NewRelic.noticeError(throwable);
        }
    }

   	def noticeError(String message, Map<String, String> params) {
        if (enabled) {
            NewRelic.noticeError(message, params);
        }
    }

    def noticeError(String message) {
        if (enabled) {
            NewRelic.noticeError(message);
        }
    }

    // Record transaction info
    def setTransactionName(String name, String url) {
        if (enabled) {
            NewRelic.setTransactionName(name, url);
        }
    }

    def addCustomParameter(String key, String value) {
        if (enabled) {
            NewRelic.addCustomParameter(key, value);
        }
    }

    def addCustomParameter(String key, Number value) {
        if (enabled) {
            NewRelic.addCustomParameter(key, value);
        }
    }

    def setRequestResponse(Request request, Response response) {
        if (enabled) {
            NewRelic.setRequestAndResponse(request, response);
        }
    }

    def ignoreTransaction() {
        if (enabled) {
            NewRelic.ignoreTransaction();
        }
    }

    def ignoreApdex() {
        if (enabled) {
            NewRelic.ignoreApdex();
        }
    }

    boolean isEnabled() {
        // default enabled for PROD
        boolean configEnabled = (Environment.current == Environment.PRODUCTION)

        // if config specified, use that instead
        if (grailsApplication.config.newrelic) {
            configEnabled = grailsApplication.config.newrelic.enabled
        }

        return configEnabled
    }
}
