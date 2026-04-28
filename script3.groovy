import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper

Message processData(Message message) {

    // STEP 1: Get body as String
    def body = message.getBody(String)

    // STEP 2: Check for empty response
    if (!body || body.trim().isEmpty()) {
        throw new Exception("Empty response received from API")
    }

    // STEP 3: Parse JSON
    def jsonSlurper = new JsonSlurper()
    def json = jsonSlurper.parseText(body)

    // STEP 4: Extract currency value safely
    def currency = json?.currency

    // STEP 5: Handle null currency
    if (currency == null) {
        throw new Exception("Currency field not found in response")
    }

    // STEP 6: Store as property
    message.setProperty("currencyValue", currency)

    return message
}
