import javax.swing.*;

public class test {
    public static void main(String[] args) {
        //JFrame指定一个窗口，构造方法里面是窗口标题
        JFrame frame=new JFrame("酒店管理系统登录");

        //关闭窗口时，退出整个程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建一个容器
        JPanel panel=new JPanel();
        frame.setContentPane(panel);

        //将button放入Panel
        JButton button=new JButton("测试");
        panel.add(button);

        //添加label控件
        JLabel label=new JLabel("nb");
        panel.add(label);
        //另一个写法panel.add(new JLabel("nb"));

        //设置窗口大小
        frame.setSize(500,400);

        //显示窗口
        frame.setVisible(true);

    }
}
