package smyx;
/*Ϳɫ��
 * ����state[][]�и�������״̬����Ϳɫ
 * ��ɫΪ��ɫ����Ծ����Ϊ��ɫ
 */
import java.awt.Color;

import javax.swing.JPanel;

public class set_color {
    static boolean [][] state_one;
    static JPanel[][] jPanel;
    //��ʼ����
    public set_color(boolean [][] state,JPanel[][] jpanel_one)
    {
        state_one = state;
        jPanel = jpanel_one;
    }
    //������ɫ
    public static void paint()
    {
            
        //ѭ���ж�״̬������ɫ
        for(int i=1;i<3;i++)
        {
            for(int j=1;j<2;j++)
            {
                if (state_one[i][j]==true) {
                jPanel[i][j].setBackground(Color.black);
                }
                else{
                    jPanel[i][j].setBackground(Color.white);
                }
            }
        }
    }
}