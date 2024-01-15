import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentSerializable {

    public static final String FILE_JSON = "tasks.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static List<Student> students = new ArrayList<>();
   private final Input input;

    public void addNewStudent() {
        String name = input.input("Введите имя студента");
        Integer age = input.inputInt("Введите возраст");
        double GPA = input.inputDouble("Введите средний балл");
        students.add(new Student(name,age,GPA));
        saveStudentToFile(FILE_JSON, students);
        saveStudentToFile(FILE_BIN, students);
        saveStudentToFile(FILE_XML, students);
        System.out.println("Новая студент добавлен.");
    }

   public  List<Student> loadStudentsFromFile(String fileName) {

       File file = new File(fileName);
        if (file.exists()) {
           try {
               if (fileName.endsWith(".json")) {
                   students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
                else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) ois.readObject();
                    }

                }
                else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return students;
   }

   public  void saveStudentToFile(String fileName, List<Student> students) {
       try {
           if (fileName.endsWith(".json")) {
               objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
               objectMapper.writeValue(new File(fileName), students);
           }
            else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(students);
                }
            }
           else if (fileName.endsWith(".xml")) {
               xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), students);
            }
       }
        catch(IOException e){
           e.printStackTrace();
       }
   }
}
