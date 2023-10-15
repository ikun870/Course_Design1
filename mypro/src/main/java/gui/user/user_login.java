package gui.user;

import com.formdev.flatlaf.FlatLightLaf;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import gui.layout.MyLayout2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static gui.Sqllink.gM;
import static gui.swing_demo.test_after_user_login;
import static gui.swing_demo.test_start;

public class user_login extends MyFrame {
    JTextField user_name=new JTextField();
    JPasswordField password = new JPasswordField();
    JButton last_step=new JButton("上一步");
    JButton confirm=new JButton("确认");
    public static String bookphonenum;
    public user_login(){
        super("用户登录");

        panel.setLayout(new exclusiveLayout());

        Label l1=new Label("用户名：");
        l1.setFont(new Font("楷体",Font.BOLD,14));
        panel.add(l1);

        panel.add(user_name);
        user_name.setColumns(20);

        Label l2=new Label("密码：");
        l2.setFont(new Font("楷体",Font.BOLD,14));
        panel.add(l2);

        panel.add(password);
        password.setColumns(20);
        panel.add(last_step);
        panel.add(confirm);

        last_step.addActionListener((e -> {
            //返回start初始界面
            try {
                test_start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();

        }));



        password.setEchoChar('*');

        ImageIcon imageIcon=new ImageIcon("mypro/src/main/java/gui/layout/view1.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(40,20,Image.SCALE_DEFAULT));

//添加显示密码图标按钮
        JButton viewBtn = new JButton(imageIcon);

        ImageIcon imageIcon2=new ImageIcon("mypro/src/main/java/gui/layout/view2.jpg");
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(40,20,Image.SCALE_DEFAULT));


//添加隐藏密码图标按钮
        JButton viewHideBtn = new JButton(imageIcon2);

//将图标按钮添加进密码框里面，需要使用FlatLightLaf主题库
//导入依赖后在所有swing组件的最前面（或者在方法一开始）添加 FlatLightLaf.setup(); 即可，maven地址放最下面了

//如果不需要将按钮添加进框里则无需使用FlatLightLaf，添加相应按钮到面板相应位置即可
        password.putClientProperty("JTextField.trailingComponent", viewHideBtn);

//给显示密码图标绑定单击事件
        viewBtn.addActionListener((e -> {

            password.putClientProperty("JTextField.trailingComponent", viewHideBtn);//设置隐藏按钮显示，未使用FlatLightLaf则不需要
            password.setEchoChar('*');//设置密码显示
        }));

//给隐藏密码图标绑定单击事件
        viewHideBtn.addActionListener((e -> {
            password.putClientProperty("JTextField.trailingComponent", viewBtn);//设置显示按钮显示，未使用FlatLightLaf则不需要
            password.setEchoChar((char) 0);//设置密码隐藏

        }));

        confirm.addActionListener((e -> {
            //先确认用户名和密码是否正确
            boolean b= false;
            try {
                b = judge();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(b){
                //进入到用户界面
                test_after_user_login();
                this.dispose();
            }
            else{
                //提示重新输入
                JOptionPane.showMessageDialog(this,"登陆失败！请检查用户名和密码！");
            }
        }));

    }

    public boolean judge() throws IOException {
        String user_name=this.user_name.getText();
        String password=this.password.getText();

        Sqllink.open();
        Sqllink.getguestMapper();
        String getpwd= gM.get_password(user_name);
        bookphonenum=gM.get_bookphonenum(user_name);
        Sqllink.close();

        return getpwd!=null&&getpwd.equals(password);//用&&先判断getpwd为不为空
    }

    private class exclusiveLayout extends LayoutAdapter {

        @Override
        public void layoutContainer(Container parent) {
            width = parent.getWidth();
            height = parent.getHeight();
            Component[] component=parent.getComponents();
            int h=0;

            //用户名
            component[0].setBounds(width/4,0,width/5,height/8);
            h+=component[0].getHeight();
            //输入框
            component[1].setBounds(width/4,h,width/2,height/10);
            h+=component[1].getHeight();
            //密码
            component[2].setBounds(width/4,h,width/5,height/8);
            h+=component[2].getHeight();
            //输入框
            component[3].setBounds(width/4,h,width/2,height/10);

            h+=component[3].getHeight();
            //两个按钮
            component[4].setBounds(width/5+width/60,h+height/15,width/4,height/10);
            component[5].setBounds(width/5+width/4+width/20,h+height/15,width/4,height/10);

        }
    }


}
