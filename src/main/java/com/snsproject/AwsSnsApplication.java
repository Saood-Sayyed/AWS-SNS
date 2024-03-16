package com.snsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@SpringBootApplication
@RestController
public class AwsSnsApplication {
	
	@Autowired
    private AmazonSNSClient snsClient;

    String TOPIC_ARN="" ;//Add your ARN here

	@GetMapping("/addSubscription/{email}")
	public String addSubscription(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
		snsClient.subscribe(request);
		return "Subscription request is pending. To confirm the subscription, check your email : " + email;
	}

	 @GetMapping("/sendNotification")
	public String publishMessageToTopic(){
		 PublishRequest publishRequest=new PublishRequest(TOPIC_ARN,buildEmailBody(),"Subject of the email");
		 snsClient.publish(publishRequest);
		 return "Notification send successfully !!";
	}

	 private String buildEmailBody(){
		    return "This email is been sent using AWS SNS service";
		}


	public static void main(String[] args) {
		SpringApplication.run(AwsSnsApplication.class, args);
		
		
	}

}
