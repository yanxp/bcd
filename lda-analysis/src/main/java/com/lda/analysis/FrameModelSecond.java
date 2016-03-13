package com.lda.analysis;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class FrameModelSecond extends Frame implements ActionListener {
	 private Frame frame;
     JComboBox list = null;
     String[] city = { "广州", "深圳", "东莞", "珠海", "佛山", "中山", "韶关" };
     private static TextArea textArea;   //textArea用于显示打开的内容
     private TextField textField;
     private Button Opnebtn;
     private FileDialog fd;
     private File file = null;
     Corpus corpus=null;
     ModelDB database=new ModelDB();
     ArrayList<String> military = new ArrayList<String> ();
     ArrayList<String> recruit=new ArrayList<String>();
     ArrayList<String> education=new ArrayList<String>();
     ArrayList<String> healthy=new ArrayList<String>();
     ArrayList<String>  tour=new ArrayList<String>();
     ArrayList<String> Finance=new ArrayList<String>();
     ArrayList<String> Culture=new ArrayList<String>();
     ArrayList<ArrayList> theme=new ArrayList<ArrayList>();
     //构造函数开始
      public FrameModelSecond(){
    	   Opnebtn = new Button("Open");
           textArea = new TextArea(18,70);
           textField=new TextField(43);
           Opnebtn.addActionListener(this);//给按钮添加事件监听器
           BuilderTheme();
           list = new JComboBox(city);
           list.setMaximumRowCount(3);
           list.setBorder(BorderFactory.createTitledBorder("请选择城市"));
             }
             //给按钮添加行为
      public void actionPerformed(ActionEvent e){
          if (e.getSource()==Opnebtn) { //单击打开按钮时   
                fd = new FileDialog(frame,"打开文件",FileDialog.LOAD);
                fd.setVisible(true);   //创建并显示打开文件对话框
                textArea.setText("");
               String path=null;
         if ((fd.getDirectory()!=null) && (fd.getFile()!=null)) {
               
                file = new File(fd.getDirectory(),fd.getFile());
				textField.setText(file.getAbsolutePath().replace('\\', '/'));
				path=fd.getDirectory().replace('\\', '/');
                ShowLdaCorpus(path);
                
         }
              }
          }
               
      public void showWindow () {
    	  frame = new Frame("LDA文本主题发现");         //初始化对象f
          frame.setSize(600,400);                //设置窗口f的大小
          //设置布局管理器为FlowLayout
          frame.setLayout(new FlowLayout(FlowLayout.LEFT,36,20));
          frame.add(Opnebtn);   
          frame.add(textField);
          frame.add(list);
          frame.add(textArea); 
         
         //为窗口frame添加WindowListener监听器
          frame.addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent evt){ //实现windowClosing方法
            	  frame.setVisible(false);         //设置窗口frame不可见
                  	  frame.dispose();            //释放窗口及其子组件的屏幕资源
                        System.exit(0);            //退出程序
                  }
              });
          frame.setLocation(300,200);
          frame.setVisible(true);                //设置窗口frame可视
          frame.setResizable(false);
	}
     
      public static void showResult(Map<String, Double>[] result)
      {
          int i = 0;
          for (Map<String, Double> topicMap : result)
          {
        	  textArea.append("\n");
             textArea.append("topic:"+(i++));
             textArea.append("\n");
              explain(topicMap);
            
          }
      }
      public static void explain(Map<String, Double> topicMap)
      {
          for (Map.Entry<String, Double> entry : topicMap.entrySet())
          {
        	  textArea.append(entry.getKey());
              textArea.append("\n");
               
          }
      }
          public    void   ShowLdaCorpus(String path) {
        	  Corpus corpus = null;
      		try {
      			corpus=Corpus.load(path);		
      			//System.out.println(corpus);
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
      	      //2. Create a LDA sampler
      	      LdaGibbsSampler ldaGibbsSampler = new LdaGibbsSampler(corpus.getDocument(), corpus.getVocabularySize());
      	       // 3. Train it
      	        ldaGibbsSampler.gibbs(3);
      	      // 4. The phi matrix is a LDA model, you can use LdaUtil to explain it.
      	      double[][] phi = ldaGibbsSampler.getPhi();
      	        Map<String, Double>[] topicMap = LdaUtil.translate(phi, corpus.getVocabulary(), 10);
      	         LdaUtil.explain(topicMap); 
      	         showResult(topicMap);
      	        // database.Save2DB(topicMap);
      	       //  database.QueryfromDB();
          }
      
     public String Comparetheme(ArrayList<ArrayList> arrayList,Map<String, Double> topic) {
    	 System.out.println(arrayList.size());
    	   int i=0;
    	 for (int j = 0; j < arrayList.size(); j++) {
		 
    		   System.out.println(arrayList.get(j));
    			 for (Map.Entry<String, Double> entry : topic.entrySet() )
    	         {  
    	             if (entry.getKey().equals(arrayList.get(j).get(i)) ) {
    	            	 return (String) arrayList.get(j).get(1);
    				}
    	             else {
    	            	 i=0;
						break;
					}
   
    	         }
    				
		 }
    	
    return "没有找到相关主题";

		
	}
     public void BuilderTheme() {
    	    military.add("美国");military.add("军事");
            recruit.add("中国");recruit.add("招聘");
            education.add("专业");education.add("教育");
            Finance.add("公司");Finance.add("财经");
            tour.add("旅游"); tour.add("旅游");
            Culture.add("没有");Culture.add("文化");
            healthy.add("医院");healthy.add("健康");
            theme.add(military);theme.add(recruit);
            theme.add(education);theme.add(Finance);
            theme.add(tour);theme.add(Culture);
            theme.add(healthy);
          
	}
}
