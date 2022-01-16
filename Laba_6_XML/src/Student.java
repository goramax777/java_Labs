package com.devcolibri;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("Person")
public class Person {

    @XStreamAlias("Name")
    private String firstName;

    @XStreamAlias("LastName")
    private String lastName;

    @XStreamAlias("Age")
    @XStreamAsAttribute
    private int age;

    @XStreamAlias("Children")
    private List<Person> children = new ArrayList<Person>();

    public Person() {
    }

    public Person(String firstName, String lastName, int age, List<Person> children) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.children = children;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setChildren(Person children) {
        this.children.add(children);
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName: " + firstName + ", " +
                "lastName: " + firstName + ", " +
                "age: " + age + ", " +
                (children.size() > 0 ? "Children{ " + children + " } " : "") +
                "}";
    }
}
