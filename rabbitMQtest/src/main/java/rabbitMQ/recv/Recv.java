package rabbitMQ.recv;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.management.Query;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {

	public static void main(String[] args) throws Exception, Exception {
		String QUEUE_NAME = "hello";
		//打开连接和创建频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明转发器和类型
		channel.exchangeDeclare(QUEUE_NAME, "fanout");
		//声明队列，主要为了防止消息接受者先运行此程序，队列还不存在时创建队列。
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(message);
		}
	}

}
