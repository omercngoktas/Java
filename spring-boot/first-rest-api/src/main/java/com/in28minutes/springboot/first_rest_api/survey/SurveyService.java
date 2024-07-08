package com.in28minutes.springboot.first_rest_api.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {
	private static List<Survey> surveys = new ArrayList<>();
	
	static {
		// asdasd asd asd
		Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                        "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                        "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                        "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");
 
        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));
 
        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);
 
        surveys.add(survey);
	}

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurveyById(String surveyId) {
		Predicate<? super Survey> predicate =
				survey -> survey.getId().equals(surveyId);
				
		Optional<Survey> optionalSurvey = surveys.stream().filter(predicate).findFirst();
		
		if(optionalSurvey.isEmpty())
			return null;
		else 
			return optionalSurvey.get();
	}

	public List<Question> retrieveAllSurveyQuestions(String surveyId) {
		Survey survey = retrieveSurveyById(surveyId);
		
		if(survey == null) 
			return null;
		
		return
			survey.getQuestions();
	
	}

	public Question retrieveSpecificSurveyQuestion(String surveyId, String questionId) {
		List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
		
		if(surveyQuestions == null)
			return null;
		
		else {
			Predicate<? super Question> predicate =
					question -> question.getId().equals(questionId);
					
			Optional<Question> optionalQuestion = surveyQuestions.stream().filter(predicate).findFirst();
			
			if(optionalQuestion.isEmpty())
				return null;
			else 
				return optionalQuestion.get();
		}
	}

	public String addNewSurveyQuestion(String surveyId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestions(surveyId);
		
		question.setId(generateRandomId());
		questions.add(question);
		return question.getId();
	}

	private String generateRandomId() {
		SecureRandom secureRandom = new SecureRandom();
		String randomId = new BigInteger(32, secureRandom).toString();
		return randomId;
	}
	
	public String deleteSurveyQuestion(String surveyId, String questionId) {
		List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
		
		if(surveyQuestions == null)
			return null;
		
		else {
			Predicate<? super Question> predicate =
					question -> question.getId().equals(questionId);
					
			
			boolean removed = surveyQuestions.removeIf(predicate);
			
			if(!removed)
				return null;
			else 
				return questionId;
		}
	}

	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestions(surveyId);
		questions.removeIf(q -> q.getId().equals(questionId));
		questions.add(question);
		
		
	}
	
}
