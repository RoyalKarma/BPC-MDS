package mds.uvod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class StudentsWork {


    public StudentsWork() {
        students.add(new Student("Blažek","Jan",200666,2001));
        students.add(new Student("Kubiš","Adam",213431,1998));
        students.add(new Student("Moravec","Vojtěch",219263,1999));
        students.add(new Student("Nakládalová","Kateřina",220713,1999));
        students.add(new Student("Kurmanova","Aidana",227247,2002));
        students.add(new Student("Rezek","Jan",227374,2001));
        students.add(new Student("Tuček","Adam",227855,2001));
        students.add(new Student("Písek","Miroslav",229096,2001));
        students.add(new Student("Leitmann","Peter",230110,2000));
        students.add(new Student("Fabík","Václav",230548,2001));
        students.add(new Student("Mačina","Martin",230620,2000));
        students.add(new Student("Misskii","Anton",230622,2001));
        students.add(new Student("Rosa","Martin",230648,2001));
        students.add(new Student("Ramiš","Karel",231110,2001));
        students.add(new Student("Dolák","Martin",231232,2000));
        students.add(new Student("Kolář","Ondřej",231245,2001));
        students.add(new Student("Maczkó","Adam",231252,2000));
        students.add(new Student("Marek","Vojtěch",231253,1999));
        students.add(new Student("Nagy","Tomáš",231258,2000));
        students.add(new Student("Nečas","Martin",231259,1999));
        students.add(new Student("Olenočin","Štefan",231262,2000));
        students.add(new Student("Ruman","Róbert",231273,2001));
        students.add(new Student("Sladký","Martin",231280,2000));
        students.add(new Student("Szymutko","Marek",231285,2000));
        students.add(new Student("Kohout","David",195823,1996));
        students.add(new Student("Číka","Petr",10,1982));
        students.add(new Student("Masaryk","Tomáš",123456,1850));
    }

    ArrayList<Student> students = new ArrayList<>();
    @GetMapping("student")
    public String getStudent(@RequestParam(defaultValue = "Tuček",required = false) String name, @RequestParam(defaultValue = "227855",required = false) Integer id)
    {
        return String.format("<b> %s : %d </b>", name, id);
    }
    @GetMapping("students")
    public String getStudents(@RequestParam(required = false) Integer vutid){
        if (vutid==null){
            String w = "";
            for (Student s:students
                 ) {
                w+= s.toString();
                w+= "<br>";

            }
            return w;


        }
        Optional<Student> result = students.stream().filter(item -> item.getId() == vutid).findFirst();
        if (result.isEmpty()) return "neexistuje";
        return result.get().toString();
    }



}
