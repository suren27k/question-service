package com.suren.questionservice.service;

import com.suren.questionservice.dao.QuestionDao;
import com.suren.questionservice.entity.Question;
import com.suren.questionservice.entity.QuestionWrapper;
import com.suren.questionservice.entity.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
	{
		return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<Question> addNewQuestion(Question question)
	{
		return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer noOfQuestions)
	{
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, noOfQuestions);

		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds)
	{
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		for(Integer id : questionIds)
		{
			questions.add(questionDao.findById(id).get());
		}

		for(Question question : questions)
		{
			QuestionWrapper wrapper = new QuestionWrapper();

			wrapper.setId(question.getId());
			wrapper.setQuestion(question.getQuestion());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());

			wrappers.add(wrapper);
		}


		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<QuizResponse> quizResponses)
	{
		Integer score = 0;

		for(QuizResponse quizResponse : quizResponses)
		{
			Question question = questionDao.findById(quizResponse.getId()).get();

			if(question.getCorrectAnswer().equals(quizResponse.getAnswer()))
			{
				score++;
			}

		}

		return new ResponseEntity<>(score, HttpStatus.OK);
	}
}
