import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Program {
    /**
     * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
     * Обеспечьте поддержку сериализации для этого класса.
     * Создайте объект класса Student и инициализируйте его данными.
     * Сериализуйте этот объект в файл.
     * Десериализуйте объект обратно в программу из файла.
     * Выведите все поля объекта, включая GPA, и ответьте на вопрос,
     * почему значение GPA не было сохранено/восстановлено.
     *
     * 2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
     */

    public static  StudentSerializable studentSerializable = new StudentSerializable(new Input());

    public static void main(String[] args) {

        File f = new File(StudentSerializable.FILE_JSON);

        if (f.exists() && !f.isDirectory()) {
            StudentSerializable.students = studentSerializable.loadStudentsFromFile(StudentSerializable.FILE_JSON);
        } else {
            StudentSerializable.students = listStudent();
            saveStudents(StudentSerializable.students);
        }
        StudentSerializable.students.forEach(System.out::println);

        studentSerializable.addNewStudent();

        StudentSerializable.students.forEach(System.out::println);

    }

    public static List<Student> listStudent (){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Tom",25,4.5));
        students.add(new Student("Ann",24,4.3));
        students.add(new Student("Bob",27,4.7));
        return  students;
    }
    public static void saveStudents (List<Student> students){
        studentSerializable.saveStudentToFile(StudentSerializable.FILE_BIN, students);
        studentSerializable.saveStudentToFile(StudentSerializable.FILE_JSON, students);
        studentSerializable.saveStudentToFile(StudentSerializable.FILE_XML, students);
    }
}
