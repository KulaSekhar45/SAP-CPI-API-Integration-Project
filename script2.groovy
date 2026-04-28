import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {

    def currencyBody = message.getProperty("currencyResponse") as String

    def currencyJson = new groovy.json.JsonSlurper().parseText(currencyBody)

    def usdToInr = currencyJson.rates.INR

    message.setBody("USD to INR: " + usdToInr)
    return message;
}