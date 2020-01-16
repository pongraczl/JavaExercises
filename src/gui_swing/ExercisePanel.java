package gui_swing;

import model.exerciseElements.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static gui_swing.MainSwing.FONT_BASE;
import static gui_swing.MainSwing.FONT_HIGHLIGHTED;

public class ExercisePanel extends JPanel {
    public static final String NEXT_EXERCISE_LABEL = "Next";

    private static final Color buttonBackgroundCorrect = Color.GREEN;
    private static final Color buttonBackgroundFail = Color.RED;
    private static final Color buttonBackgroundCorrectWas = Color.decode("#88ff88");
    private static final Border standardBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black),
            new EmptyBorder(10, 10, 10, 10));

    private JFrame window;
    private JPanel solutionFigurePanel;
    private JButton nextExerciseButton;
    private LessonState currentLesson;
    private Exercise currentExercise;
    private List<AnswerButton> answerButtons = new ArrayList<>();


    public static void initAndStartLesson(JFrame window, LessonDescription lessonDescription) {
        ExercisePanel ep = new ExercisePanel();
        ep.window = new JFrame();
        ep.window.setSize(900, 500);
        ep.window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ep.window.setVisible(true);
        ep.currentLesson = LessonState.createNewLesson(lessonDescription);
        window.setLayout(new BorderLayout());
        window.setTitle(MainSwing.WINDOW_LABEL);

        ep.nextExercise();
    }

    private void nextExercise() {
        window.getContentPane().removeAll();
        Exercise exercise = currentLesson.getNextExercise();
        if (exercise == null) {
            finishLesson();
            return;
        }
        window.add(makeFooter(), BorderLayout.SOUTH);
        prepareExercisePanel(exercise);
        if (currentLesson.getLessonDescription().isShowAnswersImmediately()) {
            showCorrectAnswer(null);
        }
        window.add(this, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }

    private void prepareExercisePanel(Exercise exercise) {
        ExercisePanel ep = this;
        ep.removeAll();
        ep.currentExercise = exercise;
        ep.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        ep.setLayout(new BorderLayout(5, 5));

        //Description Panel
        JPanel  descriptionPanel = new JPanel();
                descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
                descriptionPanel.setBorder(standardBorder);
        JLabel exerciseTitleLabel = new JLabel(exercise.getExerciseTitleText());
                exerciseTitleLabel.setFont(FONT_HIGHLIGHTED);
        JLabel exerciseDescriptionLabel = new JLabel(exercise.getExerciseDescription());
                exerciseDescriptionLabel.setFont(FONT_BASE);
        descriptionPanel.add(exerciseTitleLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0,5)));
        descriptionPanel.add(exerciseDescriptionLabel);
        ep.add(descriptionPanel, BorderLayout.NORTH);

        //Figure (code, picture...) panel
        JComponent figurePanel = generateFigureComponent(exercise.getExerciseFigure());
                    figurePanel.setBorder(standardBorder);
        ep.add(figurePanel, BorderLayout.CENTER);

        //Question panel
        JPanel  questionPanel = new JPanel();
                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                questionPanel.setBorder(standardBorder);
        JLabel questionText = new JLabel(exercise.getQuestionText());
                questionText.setFont(FONT_HIGHLIGHTED);
                questionText.setHorizontalAlignment(JTextField.LEFT);
                questionText.setBorder(standardBorder);
                questionPanel.add(questionText);
        JPanel answersPanel = new JPanel(new FlowLayout());
        questionPanel.add(answersPanel);
        ep.add(questionPanel, BorderLayout.EAST);
        ep.addAnswersToPanel(answersPanel, exercise.getSolution());

        //SolutionExplanation panel
        solutionFigurePanel = new JPanel();
        ep.add(solutionFigurePanel, BorderLayout.SOUTH);

    }

    protected static JComponent generateFigureComponent(Figure figure) {
        if (figure instanceof Figure.CodeFigure) {
            Figure.CodeFigure codeFigure = (Figure.CodeFigure) figure;

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            contentPanel.setAutoscrolls(true);
            for (Figure.CodeFigure.FileNode file : codeFigure.getFileNodes()) {
                String fullPackagePath = file.fullPackagePath;
                JLabel filePathAndName = new JLabel(
                        (fullPackagePath == "" ? "" : fullPackagePath + "/")
                        + file.fileName
                );
                filePathAndName.setFont(FONT_HIGHLIGHTED);
                contentPanel.add(filePathAndName);

                JTextArea fileContent = new JTextArea(file.codeText);
                fileContent.setFont(FONT_BASE);
                fileContent.setEditable(false);
                fileContent.setColumns(50);
                JScrollPane fileContentContainer = new JScrollPane(fileContent);
                fileContentContainer.setAutoscrolls(true);
                //fileContentContainer.setPreferredSize(new Dimension(200, 100));
                contentPanel.add(fileContentContainer);
            }
            return scrollPane;
        } else if (figure instanceof Figure.SimpleTextFigure) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (String line : ((Figure.SimpleTextFigure) figure).getText().split("\n")) {
                JLabel newLabel = new JLabel(line);
                        newLabel.setFont(FONT_BASE);
                panel.add(newLabel);
            }
            return panel;
        } else if (figure instanceof Figure.PictureFigure) {
            //TODO
            return null;
        } else {
            return null;
        }
    }

    protected void addAnswersToPanel(JPanel panel, Solution solution) {
        if (solution == null || solution.getPossibleAnswers() == null) return;

        for (Answer answer : solution.getPossibleAnswers()) {
            AnswerButton newButton = new AnswerButton(answer);
            newButton.addActionListener(actionEvent -> showCorrectAnswer((AnswerButton) actionEvent.getSource()));
            panel.add(newButton);
            this.answerButtons.add(newButton);
        }
    }

    protected void showCorrectAnswer(AnswerButton clickedButton) {
        Answer correctAnswer = currentExercise.getSolution().getCorrectAnswer();
        boolean isTheCorrectAnswerClicked;
        if (clickedButton != null) {
            Answer givenAnswer = clickedButton.answer;
            isTheCorrectAnswerClicked = currentExercise.getSolution().isTheAnswerCorrect(givenAnswer);
        } else {
            isTheCorrectAnswerClicked = true;
        }
        JButton correctButton = null;
        //Button clickedButton;
        for (AnswerButton button : answerButtons) {
            button.setEnabled(false);
            if (button.answer.isTheSame(correctAnswer)) correctButton = button;
        }
        nextExerciseButton.setEnabled(true);
        if (!isTheCorrectAnswerClicked) clickedButton.setBackground(buttonBackgroundFail);
        if (correctButton != null) {
            correctButton.setBackground(isTheCorrectAnswerClicked ? buttonBackgroundCorrect : buttonBackgroundCorrectWas);
        }

        JComponent solutionFigure = generateFigureComponent(currentExercise.getSolutionExplanationFigure());
        solutionFigure.setBorder(standardBorder);
        solutionFigurePanel.add(solutionFigure);

    }

    private void finishLesson() {
        JOptionPane.showMessageDialog(null,
                "\"" + currentLesson.getLessonDescription().getName() + "\" is over"
        );
        window.dispose();
    }

    protected JPanel makeFooter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(Box.createHorizontalGlue());
        nextExerciseButton = new JButton(NEXT_EXERCISE_LABEL);
        nextExerciseButton.addActionListener(actionEvent -> nextExercise());
        nextExerciseButton.setEnabled(false);
        panel.add(nextExerciseButton);
        return panel;
    }


    protected static class AnswerButton extends JButton {
        Answer answer;

        AnswerButton(Answer answer) {
            this.answer = answer;
            if (answer != null) {
                this.setText(answer.getAnswerText());
            }
            this.setFont(FONT_BASE);
        }
    }
}
