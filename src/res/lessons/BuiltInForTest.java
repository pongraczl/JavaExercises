package res.lessons;

import model.exerciseElements.ExerciseSettings;
import model.exerciseElements.LessonDescription;
import model.langElements.general.Value;

import java.util.ArrayList;
import java.util.List;

public class BuiltInForTest {
    public static LessonDescription STATIC_NONSTATIC_LESSON;
    public static LessonDescription STATIC_NONSTATIC_PRACTICE;
    public static LessonDescription STATIC_NONSTATIC_EXAM;
    public static List<LessonDescription> BUILTIN_LESSONS = new ArrayList<>();
    static {
        STATIC_NONSTATIC_LESSON = new LessonDescription();
        STATIC_NONSTATIC_LESSON.setName("Static vs non-static - learning");
        STATIC_NONSTATIC_LESSON.applyLessonStyle(LessonDescription.LessonStyle.LEARNING);
        List<ExerciseSettings> eSets = new ArrayList<>();
        ExerciseSettings ex1 = new ExerciseSettings();
            ex1.setCustomTitle("Static vs non-static access");
            ex1.setCustomDescription("Accessing an instance field");
            ex1.setCustomSolutionExplanation("An instance field can be accessed through an object,\n" +
                    "because all created objects have their own, separate instance of that field");
            ex1.setExerciseFactoryCode("StaticNonStaticAccess");
            ex1.addExerciseParameter("is it method?", Value.getBool(false));
            ex1.addExerciseParameter("is static?", Value.getBool(false));
            ex1.addExerciseParameter("notation by object name?", Value.getBool(true));
            eSets.add(ex1);
        ExerciseSettings ex2 = new ExerciseSettings();
            ex2.setCustomTitle("Static vs non-static access");
            ex2.setCustomDescription("Accessing an instance field");
            ex2.setCustomSolutionExplanation("An instance field can't be accessed through the class itself,\n" +
                "because the class will not know, which object's field we want to get");
            ex2.setExerciseFactoryCode("StaticNonStaticAccess");
            ex2.addExerciseParameter("is it method?", Value.getBool(false));
            ex2.addExerciseParameter("is static?", Value.getBool(false));
            ex2.addExerciseParameter("notation by object name?", Value.getBool(false));
            eSets.add(ex2);
        ExerciseSettings ex3 = new ExerciseSettings();
            ex3.setCustomTitle("Static vs non-static access");
            ex3.setCustomDescription("Accessing a static field");
            ex3.setCustomSolutionExplanation("A static field can be accessed through the class itself,\n" +
                "there is only one piece of it, and that belongs to the class");
            ex3.setExerciseFactoryCode("StaticNonStaticAccess");
            ex3.addExerciseParameter("is it method?", Value.getBool(false));
            ex3.addExerciseParameter("is static?", Value.getBool(true));
            ex3.addExerciseParameter("notation by object name?", Value.getBool(false));
            eSets.add(ex3);
        ExerciseSettings ex4 = new ExerciseSettings();
            ex4.setCustomTitle("Static vs non-static access");
            ex4.setCustomDescription("Accessing a static field");
            ex4.setCustomSolutionExplanation("Although a static field can be accessed through the class itself,\n" +
                "it can also be accessed through an object of that class, \n" +
                "because all objects can access the common static fields of their own class.\n" +
                    "At the same time, that kind of access is not recommended due to confusion.");
            ex4.setExerciseFactoryCode("StaticNonStaticAccess");
            ex4.addExerciseParameter("is it method?", Value.getBool(false));
            ex4.addExerciseParameter("is static?", Value.getBool(true));
            ex4.addExerciseParameter("notation by object name?", Value.getBool(true));
            eSets.add(ex4);
        ExerciseSettings ex5 = new ExerciseSettings();
            ex5.setCustomTitle("Static vs non-static access");
            ex5.setCustomDescription("Accessing an instance method");
            ex5.setCustomSolutionExplanation("Accessing instance and static methods works the same way as fields.\n" +
                    "Though we cannot speak directly about different values, because the code of method is the same,\n" +
                    "but an instance method can access the fields of the given object.\n" +
                    "That's why it makes sense to look at an instance method as if it would have separate\n" +
                    "instances for every object.");
            ex5.setExerciseFactoryCode("StaticNonStaticAccess");
            ex5.addExerciseParameter("is it method?", Value.getBool(true));
            ex5.addExerciseParameter("is static?", Value.getBool(false));
            ex5.addExerciseParameter("notation by object name?", Value.getBool(true));
            eSets.add(ex5);
        ExerciseSettings ex6 = new ExerciseSettings();
            ex6.setCustomTitle("Static vs non-static access");
            ex6.setCustomDescription("Accessing a static method");
            ex6.setCustomSolutionExplanation("Accessing instance and static methods works the same way as fields.\n" +
                "A static method can only access static fields.");
            ex6.setExerciseFactoryCode("StaticNonStaticAccess");
            ex6.addExerciseParameter("is it method?", Value.getBool(true));
            ex6.addExerciseParameter("is static?", Value.getBool(true));
            ex6.addExerciseParameter("notation by object name?", Value.getBool(false));
            eSets.add(ex6);
        STATIC_NONSTATIC_LESSON.setPossibleExercises(eSets);
        BUILTIN_LESSONS.add(STATIC_NONSTATIC_LESSON);

        STATIC_NONSTATIC_PRACTICE = new LessonDescription();
        STATIC_NONSTATIC_PRACTICE.setName("Static vs non-static - practice");
        STATIC_NONSTATIC_PRACTICE.applyLessonStyle(LessonDescription.LessonStyle.PRACTICE);
        List<ExerciseSettings> eSets2 = new ArrayList<>();
        ExerciseSettings ex21 = new ExerciseSettings();
            ex21.setCustomTitle("Accessing static or instance members");
            ex21.setExerciseFactoryCode("StaticNonStaticAccess");
            eSets2.add(ex21);
        STATIC_NONSTATIC_PRACTICE.setPossibleExercises(eSets2);
        BUILTIN_LESSONS.add(STATIC_NONSTATIC_PRACTICE);

        STATIC_NONSTATIC_EXAM = new LessonDescription();
        STATIC_NONSTATIC_EXAM.setName("Static vs non-static - exam");
        STATIC_NONSTATIC_EXAM.applyLessonStyle(LessonDescription.LessonStyle.EXAM);
        STATIC_NONSTATIC_EXAM.setOrder(LessonDescription.OrderType.RANDOM_ANY);
        STATIC_NONSTATIC_EXAM.setMaxExercises(10);
        List<ExerciseSettings> eSets3 = new ArrayList<>();
        ExerciseSettings ex31 = new ExerciseSettings();
            ex31.setCustomTitle("Accessing static or instance members");
            ex31.setExerciseFactoryCode("StaticNonStaticAccess");
            ex31.setBalanced(true);
            eSets3.add(ex31);
        STATIC_NONSTATIC_EXAM.setPossibleExercises(eSets3);
        BUILTIN_LESSONS.add(STATIC_NONSTATIC_EXAM);
    }
}
