package gui;

import gui.layout.LayoutAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static gui.swing_demo.*;
import static java.awt.Color.red;

public class Start extends MyFrame {

    JButton button1=new JButton("用户登录");
    JButton button2=new JButton("管理员登录");
    JButton button3=new JButton("用户注册");

    Start(String title) throws IOException {
        super(title); //调用父类构造方法

        exclusiveLayout el=new exclusiveLayout();
        panel.setLayout(el);

        JLabel l=new JLabel("欢迎来到客房管理系统！");
        l.setFont(new Font("楷体",Font.BOLD,20));
        l.setOpaque(false);
        l.setForeground(red);
        panel.add(l);

        //将button放入Panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);


        ImageIcon imageIcon=new ImageIcon("mypro/src/main/java/gui/image/hotel.jpg");
        //这里设置比例是个麻烦
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        JLabel image=new JLabel();
        image.setIcon(imageIcon);
        panel.add(image);

        //添加监听
        button1.addActionListener((e)->{
            //进入用户登录界面
            test_user_login();
            this.dispose();
        });
        button2.addActionListener((e)->{
            //进入管理员登录界面
            test_admin_login();
            this.dispose();

        });
        button3.addActionListener((e)->{
            //进入用户注册界面
            test_user_register();
            this.dispose();

        });

    }


    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            width = parent.getWidth();
            height = parent.getHeight();
            Component[] children=parent.getComponents();

            int y=height/5;
            children[0].setBounds(width/3,height/15,width,height/8);
            for(int i=1;i<children.length-1;i++){
                Component c=children[i];
                c.setBounds(width/3,y,width/3,height/8);
                y+=height/5;
            }
           children[children.length-1].setBounds(0,0,width,height);
        }
    }
}
