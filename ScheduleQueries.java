/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fcake
 */
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.*;

public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSchedule;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduleByCourse;
    private static PreparedStatement dropScheduleByStudent;
    private static PreparedStatement dropScheduleByCourse;
    
    private static ResultSet resultSet;
    
    public static ArrayList<ScheduleEntry> scheduleList;
    
    public static ArrayList<ScheduleEntry> getScheduleList(){
        return scheduleList;
    }
    
    public static void initScheduleList(){
        scheduleList = ScheduleQueries.getAllSchedule();
    }
    
    public static void addSchedule(ScheduleEntry schedule)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSchedule = connection.prepareStatement("insert into app.schedule (semester, studentID, courseCode, status, timestamp) values (?,?,?,?,?)");
            addSchedule.setString(1, schedule.getSemester());
            addSchedule.setString(2, schedule.getStudentID());
            addSchedule.setString(3, schedule.getCourseID());
            addSchedule.setString(4, schedule.getStatus());
            addSchedule.setTimestamp(5, schedule.getTimestamp());
            
            addSchedule.executeUpdate();
            scheduleList.add(schedule);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    private static ArrayList<ScheduleEntry> getAllSchedule(){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> list = new ArrayList<ScheduleEntry>();
        try
        {
            getSchedule = connection.prepareStatement("select * from app.schedule");
            resultSet = getSchedule.executeQuery();
            
            
            while(resultSet.next())
            {
                //System.out.print(resultSet.getString(1));
                list.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return list;
    }
    
        public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleList = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select * from app.schedule");
            resultSet = getScheduleByStudent.executeQuery();
            
            
            while(resultSet.next())
            {
                //System.out.print(resultSet.getString(1));
                if(resultSet.getString(1).equals(semester) && resultSet.getString(2).equals(studentID)){
                    scheduleList.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduleList;
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> list = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select * from app.schedule");
            resultSet = getScheduleByStudent.executeQuery();
            
            
            while(resultSet.next())
            {
                //System.out.print(resultSet.getString(1));
                if(resultSet.getString(2).equals(studentID)){
                    list.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByCourse(String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> list = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByCourse = connection.prepareStatement("select * from app.schedule");
            resultSet = getScheduleByCourse.executeQuery();
            
            
            while(resultSet.next())
            {
                //System.out.print(resultSet.getString(1));
                if(resultSet.getString(3).equals(courseCode)){
                    list.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return list;
    }
    
    
    
    public static void DropScheduleByStudent(String studentID){
        try {
            dropScheduleByStudent = connection.prepareStatement("Delete from app.schedule where studentid=(?)",ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            dropScheduleByStudent.setString(1, studentID);
            dropScheduleByStudent.executeUpdate();
            
            
            ScheduleQueries.initScheduleList();//update schedule
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static void DropScheduleByCourse(String courseCode){
        try {
            dropScheduleByCourse = connection.prepareStatement("Delete from app.schedule where courseCode=(?)",ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            dropScheduleByCourse.setString(1, courseCode);
            dropScheduleByCourse.executeUpdate();
            
            
            ScheduleQueries.initScheduleList();//update schedule
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static void setStudentEnrollStatus(String studentID){
        try {
            java.sql.Statement stm = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet query = stm.executeQuery("select * from app.schedule");
            
            while(query.next()){
                if(query.getString(2).equals(studentID)){
                    query.updateString(4, "S");
                    query.updateRow();
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}


