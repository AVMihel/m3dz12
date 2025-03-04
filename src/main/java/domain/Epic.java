package domain;

public class Epic extends Task {
    protected String[] subtasks; //массив из подзадач, каждая из которых является простым текстом

    public Epic(int id, String[] subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    public String[] getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean matches(String query) {
        for (String subtask : subtasks) {
            if (subtask.contains(query)) {
                return true;
            }
        }

        return false;
    }
}

