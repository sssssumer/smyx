package smyx;
/*****************
 * ����������
 * �˵�choice�����ƣ���1����ʼ 2������ 3������
 * �˵�pattern��ģʽ��:1��easy 2��love�� 3��arrow��ͷ�� 4�����ģʽ
 * �˵�speed���ٶȣ���1��100 2��1000 3��5000
 * �˵�help����������1��abstract����Ϸ������ܣ�2��editor�������ߣ�
 * ��ɫ����1��Number of remaining lives��ʣ������������2��step�������ݻ�����
 * ����� 30*30
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class set_frame {
    JFrame iFrame;
    //��Ϸ�����ٶ�
    static int speed = 1000;
    //ʣ����������
    static int life = 0;
    //�ݻ�����
    static int step_one = 0;
    myThread thread = null;
    static start_frame start_frame;
    //��ʾϸ��״̬���
    static JPanel[][] jPanel;
    //ģʽһ��love��
    static int pattern = 1;
    //��ʾ�����������
    static JPanel panel_1;
    static JLabel number;
    static JLabel step;
    static boolean end = true;
    public set_frame (int row ,int col) {
        //��������
        iFrame = new JFrame("������Ϸ");
        //��ʼ���߽�
        jPanel = new JPanel[row][col];
        iFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        iFrame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        number = new JLabel("ʣ���ϸ����: "+life+"               ");
        panel.add(number);
        
        step = new JLabel("step: "+step_one);
        panel.add(step);

        panel.setBackground(Color.red);
        panel_1 = new JPanel();
        iFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(30, 30, 2, 2));
        //��ʼ�����н���Ϊ��ɫ
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                jPanel[i][j] = new JPanel();
                jPanel[i][j].setBackground(Color.white);
                panel_1.add(jPanel[i][j]);
            }
        }
        
        //����ͼ�ν����С
        iFrame.setLocation(450, 180);
        iFrame.setSize(500, 500);        
        
        JMenuBar menuBar = new JMenuBar();
        iFrame.setJMenuBar(menuBar);
        
        JMenu choice = new JMenu("ѡ��");
        menuBar.add(choice);
        
        JMenuItem start = new JMenuItem("��ʼ");
        choice.add(start);
        start.addActionListener(new start());
        
        JMenuItem continue_one = new JMenuItem("����");
        choice.add(continue_one);
        continue_one.addActionListener(new continue_one());
        
        
        JMenuItem stop = new JMenuItem("��ͣ");
        choice.add(stop);
        stop.addActionListener(new stop());
        
        JMenu pattern = new JMenu("ģ��ѡ��");
        menuBar.add(pattern);
        
        JMenuItem easy = new JMenuItem("��ͼ��");
        pattern.add(easy);
        easy.addActionListener(new easy());
        
        JMenuItem love = new JMenuItem("love");
        pattern.add(love);
        love.addActionListener(new love());
        
        JMenuItem arrow = new JMenuItem("��ͷ");
        pattern.add(arrow);
        arrow.addActionListener(new arrow());
        
        JMenuItem random = new JMenuItem("���ͼ��");
        pattern.add(random);
        random.addActionListener(new random());
        
        
        JMenu speed = new JMenu("�ٶ�");
        menuBar.add(speed);
        
        JMenuItem speed_fast = new JMenuItem("����");
        speed.add(speed_fast);
        speed_fast.addActionListener(new speed_fast());
        
        JMenuItem speed_middle = new JMenuItem("����");
        speed.add(speed_middle);
        speed_middle.addActionListener(new speed_middle());
        
        JMenuItem speed_low = new JMenuItem("����");
        speed.add(speed_low);
        speed_low.addActionListener(new speed_low());
        
        iFrame.setVisible(true);
        
    }
    
    class myThread extends Thread{
        public myThread()    {    
        }
        public void run(){
            while(end)
            {
                life = 0;
                start_frame.judge.judge();
                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                for (int m = 1; m < start_frame.state_one.length - 1; m++)
                {
                    for (int n = 1; n < start_frame.state_one[m].length - 1; n++) 
                    {
                        if (start_frame.state_one[m][n]==true) {
                            life ++;
                        }
                    }
                }
                step_one++;
                number.setText("ʣ���ϸ��: "+life+"               ");
                step.setText("����: "+step_one);
                
                start_frame.set_color.paint();
                
                if (life==0) {
                    end = false;
                    JOptionPane.showMessageDialog(null, "�����ݻ�������\n"
                            + "        ��������Ϊ"+step_one);
                }
                
            }
        }
    }
    
        //������Ϸ�Ŀ�ʼ
        class start implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //��ʼ���߼���ͼ
                if (pattern==1) {
                    start_frame.init_data.init_one();
                }else if (pattern==2) {
                    start_frame.init_data.init_two();
                }else if (pattern==3) {

                    start_frame.init_data.init_three();
                }else {
                    start_frame.init_data.init_zero();
                }
                //���µ�ͼ��ɫ
                start_frame.set_color.paint();
                //��ʼ��������ʣ����������
                life = 0;
                step_one = 0;
                end = true;
                //�����̵߳Ŀ���
                if (thread != null)  
                    thread.stop();  
                thread = new myThread();  
                thread.start();  
            }
        }
        //������Ϸ�ļ���
        class continue_one implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(thread!=null)
                    thread.stop();
                thread = new myThread();
                thread.start();
            }
            
        }
        //������Ϸ��ֹͣ
        class stop implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //�����̵߳Ŀ���
                if (thread != null)  
                    thread.stop();  
                thread = null;  
            }
            
        }
        //�������������ٶȣ����٣�
        class speed_fast implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                speed = 100;
            }
        }
        //�������������ٶȣ����٣�
        class speed_middle implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                speed = 1000;
            }
        }
        //�������������ٶȣ����٣�
        class speed_low implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                speed = 5000;
            }
        }
        //ģʽ�� love��
        class love implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                pattern = 1;
            }
            
        }
        //ģʽ�� ��ͷ��
        class arrow implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                pattern = 2;
            }
            
        }
        //���ģʽ
        class random implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                pattern = 3;
            }
            
        }
        //���ģʽ
                class easy implements ActionListener
                {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        pattern = 0;
                    }
                    
                }
}

