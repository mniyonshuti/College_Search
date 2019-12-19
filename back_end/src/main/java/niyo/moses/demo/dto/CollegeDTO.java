package niyo.moses.demo.dto;

public class CollegeDTO {
   private String schoolName;
   private int studentsSize;
   private long id;

    public CollegeDTO() {
    }

    public CollegeDTO(String schoolName, int studentsSize, long id) {
        this.schoolName = schoolName;
        this.studentsSize = studentsSize;
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStudentsSize() {
        return studentsSize;
    }

    public void setStudentsSize(int studentsSize) {
        this.studentsSize = studentsSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"schoolName\":" + "\"" +schoolName +"\""+
                ", \"studentsSize\" :" + studentsSize +
                ", \"id\":" + id +
                '}';
    }
}
