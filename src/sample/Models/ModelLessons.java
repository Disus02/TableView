package sample.Models;

public class ModelLessons {
    private String id;
    private String teacher;
    private String courses;
    private String room;
    private String date;

    public ModelLessons(String id, String teacher, String courses, String room, String date) {
        this.id = id;
        this.teacher = teacher;
        this.courses = courses;
        this.room = room;
        this.date = date;
    }
    public String getId(){
        return id;
    }
    public String getTeacher(){
        return teacher;
    }
    public String getCourses(){
        return courses;
    }
    public String getRoom(){
        return room;
    }
    public String getDate(){
        return date;
    }


}

