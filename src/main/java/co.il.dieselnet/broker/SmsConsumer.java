package co.il.dieselnet.broker;

import co.il.dieselnet.sms.Message;
import co.il.dieselnet.sms.Service;
import com.rabbitmq.client.*;

import java.io.IOException;

public class SmsConsumer extends DefaultConsumer {
    private Service service;

    public SmsConsumer(Channel channel, Service service) {
        super(channel);
        this.service = service;
    }

    @Override
    public void handleDelivery(String consumerTag,
                               Envelope envelope,
                               AMQP.BasicProperties properties,
                               byte[] body)
            throws IOException {
        Message smsMessage = Message.JsonSerializer.deserialize(new String(body));

        System.out.println("Received message: " + smsMessage.toString());

        try {
            this.service.send(smsMessage);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            this.getChannel().basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
