package com.devcolibri;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Person> myPerson = new ArrayList<Person>();
        List<Person> children = new ArrayList<Person>();
        children.add(new Person("Bob", "Jeckson", 2));
        myPerson.add(new Person("Adam", "Jeckson", 19, children));
        myPerson.add(new Person("Ketty", "Pretorius", 19));
        myPerson.add(new Person("Jon","Snow",  26));

        String nameXmlFile = "persons";
        marshaller(myPerson, nameXmlFile);
        List<Person> persons = unmarshalling(new File(nameXmlFile.concat(".xml")));

        for(Person p : persons){
            System.out.println(p);
        }
    }

    public static void marshaller(List<Person> object, String nameXmlFile) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(nameXmlFile, List.class);
        xStream.processAnnotations(Person.class);

        String xml = xStream.toXML(object);
        saveToFile(xml, nameXmlFile);
    }

    private static void saveToFile(String xml, String nameFile) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(nameFile+".xml")));
        bufferedWriter.write(xml);
        bufferedWriter.close();
    }

    public static List<Person> unmarshalling(File file) throws IOException, ClassNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persons", List.class);
        xStream.alias("Person", Person.class);
        xStream.aliasField("Name", Person.class, "firstName");
        xStream.aliasField("LastName", Person.class, "lastName");
        xStream.aliasField("Children", Person.class, "children");
        xStream.aliasAttribute(Person.class, "age", "Age");
        xStream.registerConverter((Converter) new EncodedByteArrayConverter());

        return (ArrayList<Person>) xStream.fromXML(file);
    }

}
