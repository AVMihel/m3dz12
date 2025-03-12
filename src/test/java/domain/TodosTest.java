package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода matches для класса SimpleTask
    @Test
    public void shouldReturnTrueTaskContainsQuery() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        boolean result = task.matches("Позвонить родителям");

        boolean[] expected = {true};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseTaskContainsQuery() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        boolean result = task.matches("Спросить у деда");

        boolean[] expected = {false};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода matches для класса Epic
    @Test
    public void shouldReturnTrueSubtasksContainsQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("Молоко");

        boolean[] expected = {true};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseSubtasksContainsQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("Масло");

        boolean[] expected = {false};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода matches для класса Meeting
    @Test
    public void shouldReturnTrueMeetingContainsTopic() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("Выкатка 3й версии приложения");

        boolean[] expected = {true};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrueMeetingContainsProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("Приложение НетоБанка");

        boolean[] expected = {true};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseMeetingContainsQuery() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("БингоБанк");

        boolean[] expected = {false};
        boolean[] actual = {result};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода search для класса SimpleTask
    @Test
    public void shouldReturnTasksTitleContainsQuery() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(task);

        Task[] actual = todos.search("Позвонить родителям");
        Task[] expected = {task};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayTitleNotContainQuery() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(task);

        Task[] actual = todos.search("Написать друзьям");

        Task[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода search для класса Epic
    @Test
    public void shouldReturnTasksSubtasksContainsQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);

        Task[] actual = todos.search("Хлеб");
        Task[] expected = {epic};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArraySubtasksNotContainQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);

        Task[] actual = todos.search("Квас");

        Task[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода search для класса Meeting
    @Test
    public void shouldReturnTasksMeetingContainsQuery() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);

        Task[] actual = todos.search("Приложение НетоБанка");
        Task[] expected = {meeting};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayMeetingNotContainQuery() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);

        Task[] actual = todos.search("Завтра");

        Task[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты метода search для нескольких задач
    @Test
    public void shouldReturnMultipleValuesContainingQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Билеты купить");

        String[] subtasks = {"Билеты", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Билеты");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnSingleValueContainingQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expectedSimpleTask = {};
        Task[] actualSimpleTask = todos.search("Call me");
        Assertions.assertArrayEquals(expectedSimpleTask, actualSimpleTask);

        Task[] expectedEpic = {epic};
        Task[] actualEpic = todos.search("Молоко");
        Assertions.assertArrayEquals(expectedEpic, actualEpic);

        Task[] expectedMeeting = {};
        Task[] actualMeeting = todos.search("Without apps");
        Assertions.assertArrayEquals(expectedMeeting, actualMeeting);
    }

    @Test
    public void shouldReturnQueryContainingNoValues() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expectedSimpleTask = {};
        Task[] actualSimpleTask = todos.search("Should I call my mom");
        Assertions.assertArrayEquals(expectedSimpleTask, actualSimpleTask);

        Task[] expectedEpic = {};
        Task[] actualEpic = todos.search("Milk");
        Assertions.assertArrayEquals(expectedEpic, actualEpic);

        Task[] expectedMeeting = {};
        Task[] actualMeeting = todos.search("Tomorrow");
        Assertions.assertArrayEquals(expectedMeeting, actualMeeting);
    }
}

