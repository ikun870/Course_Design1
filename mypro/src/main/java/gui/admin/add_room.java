package gui.admin;

import com.mysql.jdbc.StringUtils;
import gui.MyFrame;
import gui.Sqllink;
import gui.layout.LayoutAdapter;
import pojo.room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static gui.Sqllink.aM;
import static gui.swing_demo.test_admin_room;

public class add_room extends MyFrame {
    JTextField id=new JTextField();
    JTextField type=new JTextField();
    JTextField price=new JTextField();
    JButton back=new JButton("返回");
    JButton submit=new JButton("确认提交");
    public add_room(){
        super("增加客房:");

        panel.setLayout(new exclusiveLayout());

        JLabel l1= new JLabel("房间号：");
       // l1.setFont(new Font("楷体",Font.BOLD,14));
        panel.add(l1);
        panel.add(id);


        JLabel l2=new JLabel("房间类型：");
        panel.add(l2);
        panel.add(type);

        JLabel l3=new JLabel("房价：");
        panel.add(l3);
        panel.add(price);

        panel.add(submit);
        panel.add(back);

        back.addActionListener((e)->{
            test_admin_room();
            this.dispose();
        });

        submit.addActionListener((e)->{
            boolean b;
            try {
                b=judge();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(b)
            {
                JOptionPane.showMessageDialog(this,"房间添加成功");
                test_admin_room();
                this.dispose();
            }
            else JOptionPane.showMessageDialog(this,"请确认客房信息正确！");

        });
    }
    public boolean judge() throws IOException {

        String type=this.type.getText();
        String id=this.id.getText();
        String price_s=price.getText();
        int price=0;

        if(type.length() >= 3 && (type.endsWith("单人间") ||type.endsWith("双人间"))&&id.length()==3&& StringUtils.isStrictlyNumeric(price_s)&&!price_s.equals("0"))
        {
            price=Integer.parseInt(price_s);
            room r=new room();
            r.setPrice(price);
            r.setId(id);
            r.setType(type);
            Sqllink.open();
            Sqllink.getAdminMapper();
            aM.add_room(r);
            Sqllink.close();
            return true;
        }
        return false;
    }

    private class exclusiveLayout extends LayoutAdapter {
        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
            Component[] component = parent.getComponents();
            int h=30;
            for(int i=0;i<6;i++){
                if(i%2==0)
                    component[i].setBounds(width/7,h,width/6,height/10);
                else {
                    component[i].setBounds(width/3, h, width /3, height / 10);
                    h += component[i].getHeight()+20;
                }
            }
            component[6].setBounds(width/3, h, width /7, height / 10);
            component[7].setBounds(width*2/4, h, width /7, height / 10);
        }
    }
}
