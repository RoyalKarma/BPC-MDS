package mds.uvod;

public class Student {
  private String name;
  private String surname;
  private int id;
  private int year;

    public Student(String name, String surname, int id, int year) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;

    }
    @Override
    public String toString(){
        return String.format("<b> %s %s : %d </b>", name,surname,id);
    }
}
