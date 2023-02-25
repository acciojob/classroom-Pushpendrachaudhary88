package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    static HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> teacherstudentHashMap = new HashMap<>();

    public static void addStudent(Student student){
        String studentName = student.getName();
        studentHashMap.put(studentName,student);
    }
    public void addTeacher(Teacher teacher){
        String teacherName = teacher.getName();
        teacherHashMap.put(teacherName,teacher);
    }
    public void addStudentTeacherPair(String teacherName, String studentName){
        List<String> studentsList = new ArrayList<>();
        if (teacherstudentHashMap.containsKey(teacherName)) {
            studentsList = teacherstudentHashMap.get(teacherName);

            studentsList.add(studentName);
            teacherstudentHashMap.put(teacherName, studentsList);
        }
        else {
            studentsList.add(studentName);
            teacherstudentHashMap.put(teacherName,studentsList);
        }
    }
    public Student getStudentByName(String studentName){
        for(String sName : studentHashMap.keySet()){
            if(sName.equals(studentName)){
                return studentHashMap.get(sName);
            }
        }
        return null;
    }
    public Teacher getTeacherByName(String teacherName){
        for(String tName: teacherHashMap.keySet()){
            if(tName.equals(teacherName)){
                return teacherHashMap.get(tName);
            }
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacherName){
        List<String> studentsList = new ArrayList<>();
        if(teacherstudentHashMap.containsKey(teacherName)){
            return teacherstudentHashMap.get(teacherName);
        }
        return studentsList;
    }
    public List<String> getAllStudents(){
        List<String >studentsList = new ArrayList<>();
        for(String sName : studentHashMap.keySet()){
            studentsList.add(sName);
        }
        return studentsList;
    }
    public void deleteTeacherByName(String teacherName){
        List<String> studentList = new ArrayList<>();
        if(teacherstudentHashMap.containsKey(teacherName)){
            studentList = teacherstudentHashMap.get(teacherName);
            for(String student : studentList){
                if(studentHashMap.containsKey(student)){
                    studentHashMap.remove(student);
                }
            }
            if(teacherHashMap.containsKey(teacherName)){
                teacherHashMap.remove(teacherName);
            }
            teacherstudentHashMap.remove(teacherName);
        }
    }
    public void deleteAllTeachers(){
        teacherHashMap = new HashMap<>();
        HashSet<String> studentSet = new HashSet<>();
        for(String tName : teacherstudentHashMap.keySet()){
            for(String sName : teacherstudentHashMap.get(tName)){
                studentSet.add(sName);
            }
        }
        for(String sName: studentSet){
            if(studentHashMap.containsKey(sName)){
                studentHashMap.remove(sName);
            }
        }
        teacherstudentHashMap = new HashMap<>();

    }




}
