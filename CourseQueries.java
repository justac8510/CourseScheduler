/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fcake
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.derby.iapi.sql.Statement;

public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourse;
    private static PreparedStatement dropCourse;
    
    private static ResultSet resultSet;
    
    
     public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (semester, coursecode, description, numofseat) values (?,?,?,?)");
            
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
     
    public static ArrayList<CourseEntry> getCourses(){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> coursesList = new ArrayList<CourseEntry>();

        try
        {
            getCourse = connection.prepareStatement("select * from app.course");
            resultSet = getCourse.executeQuery();
            
            while(resultSet.next()){
                CourseEntry course = new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                coursesList.add(course);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return coursesList;
        
    }
    
    public static int getRemainSeats(String courseCode){
        connection = DBConnection.getConnection();

        try
        {
            getCourse = connection.prepareStatement("select * from app.course");
            resultSet = getCourse.executeQuery();
            
            while(resultSet.next()){
                if(resultSet.getString(2).equals(courseCode)){
                    return resultSet.getInt(4);
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return -1;
    }
    
    public static void setSeats(int num, String courseCode){
        connection = DBConnection.getConnection();
        
        try
        {
            java.sql.Statement stm = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet query = stm.executeQuery("select * from app.course");
            
            while(query.next()){
                if(query.getString(2).equals(courseCode)){
                    query.updateInt(4, query.getInt(4)+num);
                    query.updateRow();
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static CourseEntry getCourseByCode(String courseCode){
        connection = DBConnection.getConnection();

        try
        {
            getCourse = connection.prepareStatement("select * from app.course");
            resultSet = getCourse.executeQuery();
            
            
            while(resultSet.next()){
                if(resultSet.getString(2).equals(courseCode)){
                    return new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static void DropCourse(String courseCode){
        try {
            dropCourse = connection.prepareStatement("Delete from app.course where coursecode=(?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            dropCourse.setString(1, courseCode);
            dropCourse.executeUpdate();
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
}
