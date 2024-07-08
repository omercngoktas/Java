package com.in28minutes.springboot.first_rest_api.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {

	// http://localhost:8080/surveys/Survey1/questions/Question3
	String str = """
			
			{
			    "id": "Question3",
			    "description": "Most Popular DevOps Tool",
			    "options": [
			        "Kubernetes",
			        "Docker",
			        "Terraform",
			        "Azure DevOps"
			    ],
			    "correctAnswer": "Kubernetes"
			}
			
			""";
	
	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question3";
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() {
		ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
		
		String expectedResponse = 
				"""
				{"id":"Question3","description":"Most Popular DevOps Tool","options":["Kubernetes","Docker","Terraform","Azure DevOps"],"correctAnswer":"Kubernetes"}
				""";
		assertEquals(expectedResponse.trim(), responseEntity.getBody());
		
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}
}
