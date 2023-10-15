package gui.user;

import gui.MyFrame;
import gui.layout.LayoutAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static gui.swing_demo.*;

public class after_user_login extends MyFrame {
    JButton view_my_room=new JButton("查看我的客房信息");
    JButton sign_in=new JButton("入住登记\\取消预订");
    JButton single_book=new JButton("单人订房");
    JButton team_book=new JButton("团体订房");
    JButton check_out=new JButton("退房");
    JButton go_out=new JButton("退出");

    public after_user_login(){
        super("用户");

        panel.setLayout(new exclusiveLayout());

        panel.add(view_my_room);
        panel.add(sign_in);
        panel.add(single_book);
        panel.add(team_book);
        panel.add(check_out);
        panel.add(go_out);

        view_my_room.addActionListener((e) -> {
            //进入到客户信息界面（仅用户查看）
            try {
                test_view_book();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });
        sign_in.addActionListener((e)->{
            //进行登记
            try {
                test_register_cancel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });
        single_book.addActionListener((e -> {
            //进入到单人订房
            test_single_book();
            this.dispose();
        }));
        team_book.addActionListener((e)->{
            //进入到团体订房
            test_team_book();
            this.dispose();
        });
        check_out.addActionListener((e)->{
            //退房
            try {
                test_check_out();
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
            int h = height/30;

            for (int i = 0; i <6; i++) {
                component[i].setBounds(width / 3-width/20, h, width*2/5, height / 8);
                h += component[0].getHeight()+height/30;
            }

        }
    }
}
