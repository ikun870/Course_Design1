package gui.user;

import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static gui.Sqllink.gM;
import static gui.swing_demo.test_start;

//监听器还有问题
public class user_register extends MyFrame {
    JTextField user_name=new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField confirm_password = new JPasswordField();
    JTextField phonenum=new JTextField();
    JCheckBox agree=new JCheckBox("同意用户协议");
    JButton last_step=new JButton("上一步");
    JButton confirm=new JButton("确认注册");
    public user_register(){
        super("用户注册");

        panel.setLayout(new exclusiveLayout());

        Label l1=new Label("用户名：");
        panel.add(l1);
        l1.setFont(new Font("楷体",Font.BOLD,12));
        panel.add(user_name);
        user_name.setColumns(20);

        Label l2=new Label("密码：");
        panel.add(l2);
        l2.setFont(new Font("楷体",Font.BOLD,12));
        panel.add(password);
        password.setColumns(20);

        Label l3=new Label("确认密码：");
        panel.add(l3);
        l3.setFont(new Font("楷体",Font.BOLD,12));
        panel.add(confirm_password);
        confirm_password.setColumns(20);

        Label l4=new Label("电话号码：");
        panel.add(l4);
        l4.setFont(new Font("楷体",Font.BOLD,12));
        panel.add(phonenum);
        phonenum.setColumns(20);

        Label l5=new Label("(用户名和密码分别不要超过10位)");
        l5.setFont(new Font("楷体",Font.BOLD,12));
        panel.add(l5);

        panel.add(last_step);
        panel.add(confirm);
        panel.add(agree);
        confirm.setEnabled(false);

        last_step.addActionListener((e -> {
            //返回start初始界面
            try {
                test_start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();

        }));
        confirm.addActionListener((e -> {
            //先确认管理名和密码是否正确
            boolean b= false;
            try {
                b = judge();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(b){
                //返回登录界面
                JOptionPane.showMessageDialog(this,"注册成功");
                try {
                    test_start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
            }
            else{
                //提示重新输入
                JOptionPane.showMessageDialog(this,"注册失败！请检查用户名和密码以及电话号码！");

            }
        }));

        agree.addActionListener((e)->{
           judeg_null();
        });

        //下面这几个judge方法可能会删
        user_name.addActionListener((e)->{
            judeg_null();
        });
        password.addActionListener((e)->{
            judeg_null();
        });
        confirm_password.addActionListener((e)->{
            judeg_null();
        });
        phonenum.addActionListener((e)->{
            judeg_null();
        });

        password.setEchoChar('*');

        ImageIcon imageIcon=new ImageIcon("mypro/src/main/java/gui/layout/view2.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(40,20,Image.SCALE_DEFAULT));
//添加显示密码图标按钮
        JButton viewBtn = new JButton(imageIcon);
        ImageIcon imageIcon2=new ImageIcon("mypro/src/main/java/gui/layout/view1.jpg");
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(40,20,Image.SCALE_DEFAULT));
//添加隐藏密码图标按钮
        JButton viewHideBtn = new JButton(imageIcon2);

        password.putClientProperty("JTextField.trailingComponent", viewBtn);

//给显示密码图标绑定单击事件
        viewBtn.addActionListener((e -> {

            password.putClientProperty("JTextField.trailingComponent", viewHideBtn);//设置隐藏按钮显示，未使用FlatLightLaf则不需要
            password.setEchoChar((char) 0);//设置密码显示
        }));

//给隐藏密码图标绑定单击事件
        viewHideBtn.addActionListener((e -> {
            password.putClientProperty("JTextField.trailingComponent", viewBtn);//设置显示按钮显示，未使用FlatLightLaf则不需要
            password.setEchoChar('*');//设置密码隐藏

        }));

        // 确认密码框
//添加显示密码图标按钮
        JButton viewBtn2 = new JButton(imageIcon);
        JButton viewHideBtn2 = new JButton(imageIcon2);
        confirm_password.setEchoChar('*');

        confirm_password.putClientProperty("JTextField.trailingComponent", viewBtn2);

//给显示密码图标绑定单击事件
        viewBtn2.addActionListener((e -> {

            confirm_password.putClientProperty("JTextField.trailingComponent", viewHideBtn2);//设置隐藏按钮显示，未使用FlatLightLaf则不需要
            confirm_password.setEchoChar((char) 0);//设置密码显示
        }));

//给隐藏密码图标绑定单击事件
        viewHideBtn2.addActionListener((e -> {
            confirm_password.putClientProperty("JTextField.trailingComponent", viewBtn2);//设置显示按钮显示，未使用FlatLightLaf则不需要
            confirm_password.setEchoChar('*');//设置密码隐藏

        }));
    }
    public void judeg_null(){
        boolean b=agree.isSelected();
        if(b&&!user_name.getText().equals("")&&!password.getText().equals("")&&!phonenum.getText().equals("")) {
            confirm.setEnabled(true);//不输入是“”，不是null
        }
        else confirm.setEnabled(false);
    }
    public boolean judge() throws IOException {
        String username=this.user_name.getText();
        String password=this.password.getText();
        String confirm_password=this.confirm_password.getText();
        String phonenum=this.phonenum.getText();

        if(password.equals(confirm_password)&&username.length()!=0&&username.length()<=10&&password.length()!=0&&password.length()<=10&&phonenum.length()==11) {
            Sqllink.open();
            Sqllink.getguestMapper();
            gM.user_register(username,password,phonenum);
            Sqllink.close();
            return true;
        }

        return false;
    }
    private class exclusiveLayout extends LayoutAdapter {

        @Override
        public void layoutContainer(Container parent) {
            width = parent.getWidth();
            height = parent.getHeight();
            Component[] component=parent.getComponents();
            int h=height/45;


            for(int i=0;i<8;i++){
                if(i%2==0)
                component[i].setBounds(width/6,h,width/6,height/10);
                else {
                    component[i].setBounds(width/3, h, width /2, height / 10);
                    h += component[i].getHeight()+height/90;
                }
            }
            component[8].setBounds(width/3,h,width/2,height/10);
            h += component[8].getHeight()+height/90;
            component[11].setBounds(2*width/5,h-height/25,width/2,height/10);
            component[9].setBounds(width/5+height/45,h+height/15,width/4,height/10);
            component[10].setBounds(width/5+width/4+height/15,h+height/15,width/4,height/10);

        }
    }
}
