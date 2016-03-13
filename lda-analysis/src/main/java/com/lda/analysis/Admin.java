package com.lda.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import org.omg.CORBA.NVList;

public class Admin {
	public static void main(String[] args) {
		 //方案一 先训练出model 再去预测文本
	      /* FrameModelFirst frameWindow=new FrameModelFirst();
	       frameWindow.showWindow();*/
		//方案二 直接训练的相关主题  3个主题 每个主题10个关键词 选择该方案
	       FrameModelSecond frameModelSecond=new FrameModelSecond();
	       frameModelSecond.showWindow();
	}

}
