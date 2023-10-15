package pojo;

public class room {
   private String id;
   private String type;
   private int price;
   private String status;

   private String arrival_expected;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }


   @Override
   public String toString() {
      return "房间号:" + id +" "+
              " 类型:" + type + " " +
              " 价格(每晚):" + price +
              " 状态:" + status + " " ;
   }

   public String getArrival_expected() {
      return arrival_expected;
   }

   public void setArrival_expected(String arrival_expected) {
      this.arrival_expected = arrival_expected;
   }
}
