package co.il.dieselnet;

import co.il.dieselnet.broker.ChannelFactory;
import co.il.dieselnet.broker.SmsConsumer;
import co.il.dieselnet.sms.Service;
import com.rabbitmq.client.Channel;

public class Application {

    private final static String QUEUE_NAME = "sms_verification_code";

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        Channel channel = ChannelFactory.factor(QUEUE_NAME, true, false, false);
        Service smsService = new Service();

        SmsConsumer smsConsumer = new SmsConsumer(channel, smsService);

        channel.basicConsume(QUEUE_NAME, smsConsumer);
    }
}
