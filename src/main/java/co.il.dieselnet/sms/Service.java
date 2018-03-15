package co.il.dieselnet.sms;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

public class Service {

    private static final String TWILLIO_ACCOUNT_SID = "AC43b6773c93efbe400c0f7cf31690c71d";
    private static final String TWILLIO_AUTH_TOKEN = "5ad8e9593b575611e81c42b80cd482d2";
    private static final String TWILLIO_PHONE = "13187753239";

    public Service() {
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
