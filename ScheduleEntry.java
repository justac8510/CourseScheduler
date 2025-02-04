/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fcake
 */
import java.sql.Timestamp;

public class ScheduleEntry {
    private String studentID;
    private String courseCode;
    private String semester;
    private String status;
    private Timestamp timestamp;
    
    public ScheduleEntry(String semester, String studentID, String courseCode, String status, Timestamp timestamp){
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.semester = semester;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public String getCourseID(){
        return courseCode;
    }
    
    public String getSemester(){
        return semester;
    }
    
    public String getStatus(){
        return status;
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
    
    
}
