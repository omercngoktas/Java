package com.in28minutesjpa.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutesjpa.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	//@Autowired
	//private CourseJpaRepository repository; 
	
	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn AWS Jpa", "in28minutes"));
		repository.save(new Course(2, "Learn Azure Jpa", "in28minutes"));
		repository.save(new Course(3, "Learn DevOps Jpa", "in28minutes"));
		
		repository.deleteById(1l);
		
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
		System.out.println(repository.findAll());
		System.out.println("-------------");
		System.out.println(repository.findByAuthor("in28minutes"));
		System.out.println("-------------");
		System.out.println(repository.findByName("Learn Azure Jpa"));
	}
	
}
