package Java.task4_quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private List<String> options;
    private char correctAnswer;

    public Question(String questionText, List<String> options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private boolean isAnswerSubmitted;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
        this.isAnswerSubmitted = false;
    }

    public void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
            startTimer();
        } else {
            endQuiz();
        }
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println("Question: " + currentQuestion.getQuestionText());
        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((char) ('A' + i) + ". " + options.get(i));
        }
        System.out.print("Your answer: ");
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isAnswerSubmitted) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    nextQuestion();
                }
            }
        }, 15000); // 15 seconds timer
    }

    public void submitAnswer(char userAnswer) {
        if (!isAnswerSubmitted) {
            isAnswerSubmitted = true;
            Question currentQuestion = questions.get(currentQuestionIndex);
            char correctAnswer = currentQuestion.getCorrectAnswer();
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is " + correctAnswer + ".\n");
            }
            nextQuestion();
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        isAnswerSubmitted = false;
        timer.cancel();
        startQuiz();
    }

    private void endQuiz() {
        System.out.println("Quiz completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            char correctAnswer = question.getCorrectAnswer();
            System.out.println("Q" + (i + 1) + ": Correct Answer - " + correctAnswer);
        }
    }

    // Corrected method name
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Create quiz questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Madrid"), 'B'));
        questions.add(new Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Jupiter"), 'B'));
        // Add more questions as needed

        // Create and start the quiz
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();

        // Allow the user to input answers
        Scanner scanner = new Scanner(System.in);
        while (quiz.getCurrentQuestionIndex() < questions.size()) {
            char userAnswer = scanner.next().toUpperCase().charAt(0);
            quiz.submitAnswer(userAnswer);
        }
    }
}
