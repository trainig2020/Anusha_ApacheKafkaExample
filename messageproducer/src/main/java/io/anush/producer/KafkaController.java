package io.anush.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/kafka")
public class KafkaController {

	@Autowired
	KafkaTemplate<String, Message> kafkaTemplate;

	@Value(value = "${message.topic.name}")
	private String topicName;

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message, @RequestParam("title") String title) {
		ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName,
				new Message(message, title));
		future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

			@Override
			public void onSuccess(SendResult<String, Message> result) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});

	}
}