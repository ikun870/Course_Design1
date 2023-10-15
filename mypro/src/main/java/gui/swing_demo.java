package gui;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import gui.admin.*;
import gui.teambook.*;


import gui.user.single_add_information;
import gui.user.*;
import pojo.guest;
import pojo.room;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static gui.Sqllink.rM;

public class swing_demo {
    public static void main(String[] args) throws IOException, ParseException {
        //test();
        FlatLightLaf.setup();
        test_init();
      //  test_start();

       // test_double_add_information("202",100);
       // test_select_and_register();
        //test_user_login();
       //test_admin_login();
       // test_user_register();
        //test_single_book();
        //test_team_book();
        //test_team_add_information1();
       // test_team_add_information2();
       // test_team_add_information3();
        //test_single_add_information("101",50);
        //test_after_user_login();
        //test_check_out();
        //test_team_add_information1();
        //test_team_add_information2();
        //test_team_add_information3();
       // test_after_admin_login();
        //test_admin_room();
        //test_add_room();
        //test_admin_guest();
        //test_search_guest();
        //test_view_guest();
        //test_get_pay();
        //test_delete_room();
        test_view_room();
        //test_update_room();
        //test_delete_guest();
        //test_update_guest();
        //test_add_guest();
        //test_admin_check_out();
    }

    public static void test_init() throws IOException, ParseException {
        Sqllink.open();
        Sqllink.getroomMapper();
        List<room> list= rM.get_be_booked();
        for(room r:list){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d1=sdf.parse(r.getArrival_expected());
            Date d2=new Date();//现在的时间
            if(d1.getTime()+86400000<d2.getTime())
                rM.inti(r.getId());
        }

        Sqllink.close();
    }
    public static void test_team_book() {
        JFrame frame_start=new team_book();

        frame_start.setVisible(true);//让窗口显示出来
    }

    public static void  test(){
        //JFrame指定一个窗口，构造方法里面是窗口标题，多态形式
        JFrame frame=new MyFrame("酒店管理系统登录");

        //关闭窗口时，退出整个程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口大小
        frame.setSize(400,300);

        //显示窗口
        frame.setVisible(true);
    }
    public static void test_start() throws IOException {
        JFrame frame_start=new Start("客房管理系统登录");
        frame_start.setVisible(true);//让窗口显示出来

    }
    public static void test_user_login(){
        JFrame frame_start=new user_login();
        frame_start.setVisible(true);
    }
    public static void test_admin_login(){
        JFrame frame_start=new admin_login();

        frame_start.setVisible(true);
    }
    public static void test_user_register(){
        JFrame frame_start=new user_register();

        frame_start.setVisible(true);
    }
    public static void test_single_book(){
        JFrame frame_start=new single_book();

        frame_start.setVisible(true);
    }
    public static void test_single_add_information( String rid,int price){
        JFrame frame_start=new single_add_information(rid,price);

        frame_start.setVisible(true);
    }
    public static void test_after_user_login(){
        JFrame frame_start=new after_user_login();

        frame_start.setVisible(true);

    }
    public static void test_after_admin_login(){
        JFrame frame_start=new after_admin_login();
        frame_start.setVisible(true);

    }
    public static void test_admin_room(){
        JFrame frame_start=new admin_room();

        frame_start.setVisible(true);

    }
    public static void test_add_room(){
        JFrame frame_start=new add_room();

        frame_start.setVisible(true);

    }
    public static void test_admin_guest(){
        JFrame frame_start=new admin_guest();

        frame_start.setVisible(true);

    }

    public static  void test_view_book() throws IOException {
        JFrame frame_start=new view_book();

        frame_start.setVisible(true);
    }
    public static void test_register_cancel() throws IOException {
        JFrame frame_start=new register_cancel();

        frame_start.setVisible(true);
    }
    public static void test_double_add_information(String rid,int price) throws IOException {
        JFrame frame_start=new double_add_information(rid,price);

        frame_start.setVisible(true);
    }
    public static void test_delete_room(){
        JFrame frame_start=new delete_room();

        frame_start.setVisible(true);
    }
    public static void test_view_room(){
        JFrame frame_start=new view_room();

        frame_start.setVisible(true);
    }
    public static void test_update_room(){
        JFrame frame_start=new update_room();

        frame_start.setVisible(true);
    }
    public static void test_add_guest(){
        JFrame frame_start=new add_guest();

        frame_start.setVisible(true);
    }
    public static void test_delete_guest(){
        JFrame frame_start=new delete_guest();
        frame_start.setVisible(true);
    }
    public static void test_view_guest(){
        JFrame frame_start=new view_guest();
        frame_start.setVisible(true);
    }
    public static void test_update_guest(){
        JFrame frame_start=new update_guest();
        frame_start.setVisible(true);
    }
    public static void test_update_guest_information(int price, guest g){
        JFrame frame_start=new update_guest_information(price,g);
        frame_start.setVisible(true);
    }
    public static void test_check_out() throws IOException {
        JFrame frame_start=new check_out();
        frame_start.setVisible(true);
    }
    public static void test_admin_check_out() throws IOException {
        JFrame frame_start=new admin_check_out();
        frame_start.setVisible(true);
    }

}
