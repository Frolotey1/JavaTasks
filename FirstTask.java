import java.util.*;

public static class Student {
    private final String firstName;
    private final String lastName;
    private final int id;
    private final int grade;
    ArrayList<Student> studentsInfo;

    public Student(int id, String firstName, String lastName, int grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grade = grade;
        studentsInfo = new ArrayList<>();
    }

    public void addStudent() {
        this.studentsInfo.add(new Student(id, firstName, lastName, grade));
    }

    public void removeStudent(String targetFirstName) {
        studentsInfo.removeIf(findStudent -> Objects.equals(findStudent.firstName, targetFirstName));
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void searchFirstNameStudent(String targetFirstName) {
        studentsInfo.stream().filter(findStudent ->
                        Objects.equals(findStudent.firstName, targetFirstName)).
                forEach(System.out::println);
    }

    public void sortByLastName() {
        studentsInfo.sort(Comparator.comparing(Student::getLastName));
        studentsInfo.forEach(System.out::println);
    }

    public void searchByGrade(int targetGrade) {
        for (Student findStudent : studentsInfo) {
            if (findStudent.grade > targetGrade) {
                System.out.println(findStudent);
            }
        }
    }

    public void all() {
        studentsInfo.sort(Comparator.comparing(Student::getFirstName));
        studentsInfo.forEach(System.out::println);
    }
}

void main() {
    Student student = new Student(1,"Michael","Jackson",5);
    student.addStudent();
    student.all();
    student.searchFirstNameStudent("Michael");
    student.sortByLastName();
    student.all();
    student.removeStudent("Michael");
}
