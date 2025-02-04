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

public class StudentQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentID, firstname, lastname) values (?,?,?)");
            addStudent.setString(1, student.getID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> studentsList = new ArrayList<StudentEntry>();

        try
        {
            getStudent = connection.prepareStatement("select * from app.student");
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next()){
                StudentEntry student = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                studentsList.add(student);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return studentsList;
        
    }
    
    public static StudentEntry getStudentByID(String studentID){
        connection = DBConnection.getConnection();

        try
        {
            getStudent = connection.prepareStatement("select * from app.student");
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next()){
                if(resultSet.getString(1).equals(studentID)){
                    return new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static void DropStudent(String studentID){
        try {
            dropStudent = connection.prepareStatement("Delete from app.student where studentid=(?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
}
