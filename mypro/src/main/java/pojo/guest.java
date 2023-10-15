package pojo;

public class guest {
   private Integer no;
   private String reg;
   private String name;
   private char gender;
   private String id;
   private String rid;
   private String arrival;
   private String leave_expected;
   private String status;
   private String leave_true;
   private int pay;
   private String contact;
   private String bookphonenum;

    public int getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getLeave_expected() {
        return leave_expected;
    }

    public void setLeave_expected(String leave_expected) {
        this.leave_expected = leave_expected;
    }

    public String getLeave_true() {
        return leave_true;
    }

    public void setLeave_true(String leave_true) {
        this.leave_true = leave_true;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "guest{" +
                "no=" + no +
                ", reg='" + reg + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", id='" + id + '\'' +
                ", rid='" + rid + '\'' +
                ", arrival='" + arrival + '\'' +
                ", leave_expected='" + leave_expected + '\'' +
                ", status='" + status + '\'' +
                ", leave_true='" + leave_true + '\'' +
                ", pay=" + pay +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getBookphonenum() {
        return bookphonenum;
    }

    public void setBookphonenum(String bookphonenum) {
        this.bookphonenum = bookphonenum;
    }
}
