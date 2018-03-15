package co.il.dieselnet.broker;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ChannelFactory {

    public static Channel factor(String queueName,
                                 boolean durable,
                                 boolean exclusive,
                                 boolean autoDelete)
            throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, durable, exclusive, autoDelete, null);

        return channel;
    }
}
