package gui_swing;

import model.exerciseElements.LessonDescription;
import res.lessons.BuiltInForTest;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static gui_swing.MainSwing.FONT_BIG_TITLE;

public class LessonsPanel extends JPanel {
    private List<LessonDescription> lessons;
    private JFrame window;
    private JList<LessonDescription> lessonDescriptionJList;

    public static LessonsPanel getInstance(JFrame window) {

        LessonsPanel lp = new LessonsPanel();
        lp.window = window;
        lp.loadLessons();
        lp.setLayout(new BorderLayout());

        JPanel pTitle = new JPanel();
        pTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
        JLabel lblTitle = new JLabel("Java Exercises");
        lblTitle.setFont(FONT_BIG_TITLE);
        pTitle.add(lblTitle);
        lp.add(pTitle, BorderLayout.NORTH);

        JPanel pLessons = new JPanel();
        pLessons.setLayout(new BoxLayout(pLessons, BoxLayout.Y_AXIS));
        pLessons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblLessons = new JLabel("Lessons");
        pLessons.add(lblLessons);

        DefaultListModel<LessonDescription> lessonListModel = new DefaultListModel<>();
        if (lp.lessons != null) lessonListModel.addAll(lp.lessons);
        lp.lessonDescriptionJList = new JList<>(lessonListModel);
        JScrollPane lessonListScroller = new JScrollPane(lp.lessonDescriptionJList);
        lessonListScroller.setSize(new Dimension(500, 300));
        pLessons.add(lessonListScroller);

        JButton btnStartLesson = new JButton("Start lesson");
        btnStartLesson.addActionListener(aL -> {
            LessonDescription selectedLesson = lp.lessonDescriptionJList.getSelectedValue();
            if (selectedLesson == null) {
                JOptionPane.showMessageDialog(null, "Select a lesson to start");
            } else {
                ExercisePanel.initAndStartLesson(lp.window, selectedLesson);
            }
        });
        pLessons.add(btnStartLesson);

        lp.add(pLessons, BorderLayout.CENTER);
        return lp;
    }

    private void loadLessons() {
        lessons = BuiltInForTest.BUILTIN_LESSONS;
    }
}
