package sample.Models;

public class ModelCourses {
    private String id;
    private String title;
    private String length;
    private String description;

    public ModelCourses(String id, String title, String length, String description){
        this.id = id;
        this.title = title;
        this.length = length;
        this.description = description;
    }

    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getLength(){
        return length;
    }
    public String getDescription(){
        return description;
    }
}
