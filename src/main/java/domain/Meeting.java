package domain;

public class Meeting extends Task {
    private String topic; //тема обсуждения
    private String project; //название проекта
    private String start; //дата и время старта текстом

    public Meeting(int id, String topic, String project, String start) {
        super(id);
        this.topic = topic;
        this.project = project;
        this.start = start;
    }

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {
        return start;
    }

    @Override
    public boolean matches(String query) {
        if (topic.contains(query)) {
            return true;
        }
        if (project.contains(query)) {
            return true;
        }
        if (start.contains(query)) {
            return true;
        }
        return false;
    }
}
