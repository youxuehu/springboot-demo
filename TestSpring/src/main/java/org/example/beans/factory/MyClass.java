package org.example.beans.factory;

public class MyClass {

    private String message = "<<<<<<<<<<<<<<<<<<  今天是个好日子   >>>>>>>>>>>>>>>>>";

    @Override
    public String toString() {
        return "MyClass{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
