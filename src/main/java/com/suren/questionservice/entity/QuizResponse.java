package com.suren.questionservice.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse
{
	private Integer id;     //question id
	private String answer;
}
