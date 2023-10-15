package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame {
   protected int width=600;
   protected int height=450;
   protected JPanel panel=new JPanel();
   protected MyFrame(String title){ //添加标题，创建容器，添加控件
        super(title);//设置标题
        //创建一个容器

        this.setContentPane(panel);

        //新的改动
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭时退掉进程
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();//调用Toolkit接口
        Dimension screenSize = defaultToolkit.getScreenSize();//获取screen的大小
        //先定义宽高，用当前屏幕大小减去自定义的宽高，再除以2就是居中的位置
        setLocation((screenSize.width - width) / 2,(screenSize.height - height) / 2);

    }

}
