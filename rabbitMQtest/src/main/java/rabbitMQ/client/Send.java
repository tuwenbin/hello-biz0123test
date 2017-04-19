package rabbitMQ.client;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Send {

	public static void main(String[] args) throws Exception, TimeoutException {
		//队列名称
		String QUEUE_NAME = "hello";

		//创建连接到mabbitMQ
		ConnectionFactory factory = new ConnectionFactory();
		//设置mabbitMQ所在主机ip、或者主机名
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明转发器和类型
		channel.exchangeDeclare(QUEUE_NAME, "fanout");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		Scanner s = new Scanner(System.in);
		String user = s.nextLine();
		System.out.println(" hello "+user+", ni can chat now!");
		String massage = null;
		while(!"q".equals(massage)){
			Scanner msg = new Scanner(System.in);
			massage = msg.nextLine();
			//往队列中发出一条消息
			if(!massage.equals("q")){
				//往队列中发出一条消息
				massage = user+" said "+massage;
				channel.basicPublish("", QUEUE_NAME, null, massage.getBytes());
				System.out.println(massage);
			}
		}

		System.out.println("GoodBye ^_^");
		//关闭频道和连接
		channel.close();
		connection.close();
	}
}
