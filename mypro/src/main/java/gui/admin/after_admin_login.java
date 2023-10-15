package gui.admin;

import gui.MyFrame;
import gui.layout.LayoutAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static gui.swing_demo.*;

public class after_admin_login extends MyFrame {
    JButton admin_room=new JButton("管理客房信息");
    JButton admin_guest=new JButton("管理客户信息");
    JButton admin_pay=new JButton("结账");
    JButton go_out=new JButton("退出");
    public after_admin_login(){
        super("管理员");

        panel.setLayout(new exclusiveLayout());

        panel.add(admin_room);
        panel.add(admin_guest);
        panel.add(admin_pay);
        panel.add(go_out);

        admin_room.addActionListener((e) -> {
            //进入到客房信息管理（）
            test_admin_room();
            this.dispose();
        });
        admin_guest.addActionListener((e -> {
            //进入到客户信息管理
            test_admin_guest();
            this.dispose();
        }));
        admin_pay.addActionListener((e)->{
            //进入到房费报账
            try {
                test_admin_check_out();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });
        go_out.addActionListener((e)->{
            //退出
            this.dispose();
        });
    }
    class exclusiveLayout extends LayoutAdapter {
        public void layoutContainer(Container parent) {
            width = parent.getWidth();
            height = parent.getHeight();
            Component[] component = parent.getComponents();
            int h =height/15;

            for (int i = 0; i < 4; i++) {
                component[i].setBounds(width / 3-width/40, h, width*2/5, height / 8);
                h += component[0].getHeight()+height*3/45;
            }

        }
    }
}
