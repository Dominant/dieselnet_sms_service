package co.il.dieselnet.sms;

import co.il.dieselnet.Assert;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

public class Service {

    private static final String TWILLIO_ACCOUNT_SID = System.getenv("TWILLIO_ACCOUNT_SID");
    private static final String TWILLIO_AUTH_TOKEN = System.getenv("TWILLIO_AUTH_TOKEN");
    private static final String TWILLIO_PHONE = System.getenv("TWILLIO_PHONE");

    public Service() throws Exception {
        Assert.notNullOrEmpty(TWILLIO_ACCOUNT_SID)
            .notNullOrEmpty(TWILLIO_AUTH_TOKEN)
            .notNullOrEmpty(TWILLIO_PHONE);

        Twilio.init(TWILLIO_ACCOUNT_SID, TWILLIO_AUTH_TOKEN);
    }

    public void send(final Message message) {
        com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber(message.getPhone()),
                new PhoneNumber(TWILLIO_PHONE),
                String.format("Dieselnet verification code: %s", message.getCode())
        ).create();
    }
}
