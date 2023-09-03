package com.suren.questionservice.controller;


import com.suren.questionservice.entity.Question;
import com.suren.questionservice.entity.QuestionWrapper;
import com.suren.questionservice.entity.QuizResponse;
import com.suren.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController
{
	@Autowired
	QuestionService questionService;
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}

	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("add")
	public ResponseEntity<Question> addNewQuestion(@RequestBody Question question)
	{
		return questionService.addNewQuestion(question);
	}

	@GetMapping("generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer noOfQuestions)
	{
		return questionService.generateQuestionsForQuiz(categoryName, noOfQuestions);
	}

	@GetMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds)
	{
		return questionService.getQuestionsForQuiz(questionIds);
	}

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> quizResponses)
	{
		return questionService.getScore(quizResponses);
	}
}
