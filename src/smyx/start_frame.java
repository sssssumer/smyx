package smyx;


import javax.swing.JPanel;

	public class start_frame extends Thread{
	    static start_frame start;
	    static Init_data init_data;
	    static state_one judge;
	    static set_color set_color;
	    static set_frame frame;
	    //存放细胞状态
	    static boolean[][] state_one;
	    static JPanel[][] jPanel;
	    //初始化GUI界面
	    public start_frame(int row,int col)
	    {
	        state_one = new boolean[row][col];
	        frame = new set_frame(row,col);
	        jPanel = frame.jPanel;
	    }
	    
	    //主函数
	    public static void main(String[] args)
	    {
	        //创建游戏对象
	            start = new start_frame(30,30);
	            init_data = new Init_data(state_one);
	            judge = new state_one(state_one, jPanel);
	            set_color = new set_color(state_one, jPanel);
	    }
	}

