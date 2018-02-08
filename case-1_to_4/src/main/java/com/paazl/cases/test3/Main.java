package com.paazl.cases.test3;

import com.paazl.exception.AnswerException;

import java.io.IOException;
import java.util.*;

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
     * Implement a main method that is functionally identical to Test #2. In
     * this case, use a Singleton that poses the questions and gathers the answers.
     */

    public static void main(String[] args) throws IOException {
        int score = Questions.getInstance().getScore();
        new DeveloperFactory().getDeveloper(score).print();
    }

    static Collection<String> getQuestions() {
        return new ArrayList(QUESTIONS.keySet());
    }

    //TODO: method that checks the correct answer
    static boolean isTrueAnswer(String question, String userAnswer) {
        return getAnswer(question).toString().toLowerCase().equals(
                userAnswer.toLowerCase());
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

    static class Questions {
        private int score;

        private Questions() {
            for (String question: getQuestions()) {
                System.out.println(question);
                if (isTrueAnswer(question, getUserAnswer())) score++;
            }
        }

        private static class QuestionsHolder {
            private final static Questions instance = new Questions();
        }

        public static Questions getInstance() {
            return QuestionsHolder.instance;
        }

        public int getScore() {
            return score;
        }
    }
}


interface Developer {
    void print();
}

class DeveloperFactory {
    public Developer getDeveloper(int score) {
        if (8<=score) return new DeveloperSenior();
        if (4<=score) return new DeveloperMedior();
        return new DeveloperJunior();
    }
}

class DeveloperJunior implements Developer {
    public void print() {
        System.out.println("You are a junior Java developer");
    }
}

class DeveloperMedior implements Developer {
    public void print() {
        System.out.println("You are a medior Java developer");
    }
}

class DeveloperSenior implements Developer {
    public void print() {
        System.out.println("You are a senior Java developer");
    }
}