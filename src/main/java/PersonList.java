import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
public class PersonList {
    private int nextUuid = 1;
    private List<Person> personList = new ArrayList<>();



    public void addPerson(Person person){
        personList.add(person);
    }

    public Person findByName(String name) throws IllegalArgumentException{
        String nameRegex = "^[a-zA-Z]+,\\s[a-zA-Z]+$";
        Person found = null;
        if(!Pattern.matches(nameRegex, name)) {
            throw new IllegalArgumentException("Not valid format");
        }else{
            for(Person person : personList){
                if(person.getName().equals(name)){
                    found = person;
                }
            }
        }
        return found;
    }

    public Person clone(Person person){
        int id = personList.size() + 1;
        return new Person(id, person.getName(), person.getAge(), person.getOccupation());
    }

    public void personToString(Person person) throws IOException {
        File file = new File("profilePerson/profile.txt");
        FileWriter writer = new FileWriter(file, false);
        try {
            writer.write("Id: " + " " + person.getId()
                    + "\n" + "Name: " + " " + person.getName()
                    + "\n" + "Age : " + " " + person.getAge()
                    + "\n" + "Occupation : " + " " + person.getOccupation());
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}