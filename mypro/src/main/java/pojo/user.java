package pojo;

//alt+鼠标左键 整列编辑
public class user {
   private String username  ;
   private String password  ;
   private String phonenum   ;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        public String getPhonenum() {
        return phonenum;
    }

        public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + phonenum + '\'' +
                '}';
    }
}
