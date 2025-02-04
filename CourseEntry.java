/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fcake
 */
public class CourseEntry {
    private String semester;
    private String courseCode;
    private String description;
    private int numOfSeats;
    
    public CourseEntry(String semester, String courseCode, String description, int numOfSeats){
        this.semester = semester;
        this.courseCode = courseCode;
        this.description = description;
        this.numOfSeats = numOfSeats;
    }
    
    public String getSemester(){
        return semester;
    }
    
    public String getCourseCode(){
        return courseCode;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getSeats(){
        return numOfSeats;
    }
    
    public void setSeats(int num){
        this.numOfSeats -= num;
    }
}
