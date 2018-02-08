package com.paazl.cases.test1;

import com.paazl.exception.AnswerException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	@SuppressWarnings("serial")
	private static final Map<String, Character> QUESTIONS = new HashMap<String, Character>() {
		{
			put("A Thread is a process", 'N');
			put("A List cannot contain duplicates", 'N');
			put("A Set can contain duplicates", 'N');
			put("Java is platform independent", 'Y');
			put("Java supports copy constructors like C++", 'Y');
			put("The primitive data types supported by the Java programming language are: byte, short, int, long, float, double, boolean, char", 'Y');
			put("Constructor overloading is similar to method overloading in Java", 'N');
			put("The purpose of garbage collection in Java is to reclaim and reuse objects which are no longer used", 'Y');
			put("Autoboxing is the automatic conversion made by the Java compiler between the primitive types and their corresponding object wrapper classes", 'Y');
			put("JDBC is an abstraction layer that allows users to choose between databases", 'Y');
			put("Java supports the usage of pointers", 'N');
		}
	};

	/*
	 * Implement the main method:
	 * * Show the user the questions on a console and collect the answers.
	 * * If the user scores 0-3 points, print "You are a junior Java developer".
	 * * If the user scores 4-7 points, print "You are a medior Java developer".
	 * * If the user scores 8-10 points, print "You are a senior Java developer".
	 */
	public static void main(String[] args) {
		int score = 0;
		for (String question: getQuestions()) {
			System.out.println(question);
			if (isTrueAnswer(question, getUserAnswer())) score++;
		}
		System.out.println( getEstimate(score) );
	}

	static Collection<String> getQuestions() {
		return new ArrayList(QUESTIONS.keySet());
	}

	//TODO: method that checks the correct answer
	static boolean isTrueAnswer(String question, String userAnswer) {
		return getAnswer(question).toString().toLowerCase().equals(
				userAnswer.toLowerCase());
	}

	static String getEstimate(int score) {
		if (8<=score) return "You are a senior Java developer";
		if (4<=score) return "You are a medior Java developer";
		return "You are a junior Java developer";
	}

	//TODO: method allows you to do the correct input
	static String getUserAnswer() {
		try {
			return getUserAnswer(new Scanner(System.in).next());
		} catch (AnswerException e) {
			System.err.println(e.getMessage());
			return getUserAnswer();
		}
	}

	private static Character getAnswer(String question) {
		return QUESTIONS.get(question);
	}

	//TODO: method allows you to do the correct input
	private static String getUserAnswer(String userAnswer) throws AnswerException {
		if (userAnswer.matches("[yY]|[nN]")) return userAnswer;
		throw new AnswerException("You must input only 'Y' or 'N'");
	}
}