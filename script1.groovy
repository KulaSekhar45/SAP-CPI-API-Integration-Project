import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.json.JsonSlurper;
import groovy.json.JsonOutput;

def Message processData(Message message) {

    def currencyBody = message.getProperty("currencyResponse") as String
    def goldBody = message.getProperty("goldResponse") as String

    def currencyJson = new JsonSlurper().parseText(currencyBody)
    def goldJson = new JsonSlurper().parseText(goldBody)

    def usdToInr = currencyJson.rates.INR
    def goldPrice = goldJson.gold_price

    def result = [
        currency: [
            USD_INR: usdToInr
        ],
        gold_price: [
            price: goldPrice,
            unit: "INR per gram"
        ]
    ]

    message.setBody(JsonOutput.toJson(result))
    return message;
}