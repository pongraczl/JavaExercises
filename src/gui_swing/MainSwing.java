package gui_swing;

import javax.swing.*;
import java.awt.*;

public class MainSwing {
    //TODO: resources/text/exerciseElements
    public static final String WINDOW_LABEL = "Java Class Exercises";
    public static final Font FONT_BASE = new Font("SanSerif", Font.PLAIN, 12);
    public static final Font FONT_HIGHLIGHTED = new Font("SanSerif", Font.BOLD, 12);
    public static final Font FONT_BIG_TITLE = FONT_HIGHLIGHTED.deriveFont(30.0F);

    protected JButton nextExerciseButton;
    protected JFrame window;

    public static void main(String[] args) {
        MainSwing main = new MainSwing();
        main.window = new JFrame();

        main.window.setContentPane(LessonsPanel.getInstance(main.window));

        //window.pack();
        main.window.setSize(900, 500);
        main.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.window.setVisible(true);
    }


}
