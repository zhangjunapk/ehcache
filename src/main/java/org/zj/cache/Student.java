package org.zj.cache;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/12 21:17
 * @Description:
 */
public class Student {
    private String username;
    private String password;
    private int age;



    public Student() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
