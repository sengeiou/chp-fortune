package com.creditharmony.fortune.exocr.exocrengine;

import java.io.UnsupportedEncodingException;

public final class EXIDCardResult  {
	
	public String imgtype;
	//recognition data
	public int type;
	public String cardnum;
	public String name;
	public String sex;
	public String address;
	public String nation;
	public String office;
	public String validdate;
	public int nColorType;   //1 color, 0 gray

	/*
	public Bitmap stdCardIm = null;
	public Rect rtIDNum;
	public Rect rtName;
	public Rect rtSex;
	public Rect rtNation;
	public Rect rtAddress;
	public Rect rtFace;
	public Rect rtOffice;
	public Rect rtValid;
	*/
	public EXIDCardResult() {
		type = 0;
		imgtype = "Preview";
	}
	
    
	////////////////////////////////////////////////////////////
	/** decode from stream
	 *  return the len of decoded data int the buf */
	public static EXIDCardResult decode(byte []bResultBuf, int reslen) {
		byte code;
		int i, j, rdcount;
		String content = null;
		
		EXIDCardResult idcard = new EXIDCardResult();
		
		////////////////////////////////////////////////////////////
		//type
		rdcount = 0;
		//idcard.type = bResultBuf[rdcount++];
		while(rdcount < reslen){
			code = bResultBuf[rdcount++];
			System.out.println(rdcount);
			System.out.println(code);
			i = 0;
			j = rdcount;
			while(rdcount < reslen){
				i++; rdcount++;
				if(bResultBuf[rdcount] == 0x20) break;
			}
			try {
				content = new String(bResultBuf, j, i, "gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (code == 0x21){ 		 idcard.cardnum = content;
			}else if (code == 0x22){ idcard.name = content;
			}else if (code == 0x23){ idcard.sex = content;
			}else if (code == 0x24){ idcard.nation = content;
			}else if (code == 0x25){ idcard.address = content;
			}else if (code == 0x26){ idcard.office = content;
			}else if (code == 0x27){ idcard.validdate = content;
			}
			rdcount++;
		}
		//is it correct, check it!

		return idcard;
	}

	//rects存放各个块的矩形，4个一组，这么做是为了将JNI的接口简单化
	// [0, 1, 2, 3]  idnum			issue
	// [4, 5, 6, 7]	 name			validate
	// [8, 9, 10,11] sex
	// [12,13,14,15] nation
	// [16,17,18,19] address
	// [20,21,22,23] face
	/*
	public void setRects( int []rects)
	{
		if(type == 1){
			rtIDNum 	= new Rect(rects[0], rects[1], rects[2], rects[3]);
			rtName  	= new Rect(rects[4], rects[5], rects[6], rects[7]);
			rtSex     	= new Rect(rects[8], rects[9], rects[10], rects[11]);
			rtNation  	= new Rect(rects[12], rects[13], rects[14], rects[15]);
			rtAddress 	= new Rect(rects[16], rects[17], rects[18], rects[19]);
			rtFace    	= new Rect(rects[20], rects[21], rects[22], rects[23]);
		}else if(type == 2){
			rtOffice 	= new Rect(rects[0], rects[1], rects[2], rects[3]);
			rtValid  	= new Rect(rects[4], rects[5], rects[6], rects[7]);			
		}else{
			return;
		}
	}
	*/
	public void SetViewType(String viewtype) {
		this.imgtype = viewtype;
	}
	
	public void SetColorType(int aColorType) {
		nColorType = aColorType;
	}
	/*
	public void SetBitmap(Bitmap imcard) {
		if(stdCardIm != null)
			stdCardIm.recycle();
		stdCardIm = imcard;
	}
	
	public Bitmap GetIDNumBitmap(){
		if(stdCardIm == null) return null;
		Bitmap bmIDNum = Bitmap.createBitmap(stdCardIm, rtIDNum.left, rtIDNum.top, rtIDNum.width(), rtIDNum.height());
		return bmIDNum;
	}
	public Bitmap GetNameBitmap(){
		if(stdCardIm == null) return null;
		Bitmap bmIDNum = Bitmap.createBitmap(stdCardIm, rtName.left, rtName.top, rtName.width(), rtName.height());
		return bmIDNum;
	}

	public Bitmap GetFaceBitmap(){
		if(stdCardIm == null) return null;
		Bitmap bmFace = Bitmap.createBitmap(stdCardIm, rtFace.left, rtFace.top, rtFace.width(), rtFace.height());
		return bmFace;
	}
	*/
	
	/** @return raw text to show */
	public String getText() {
		String text = "\nVeiwType = " + imgtype;
		if(nColorType == 1){
			text += "  类型:  彩色";
		}else{
			text += "  类型:  扫描";
		}
		if(type == 1){
			text += "\nname:" + name;
			text += "\nnumber:" + cardnum;
			text += "\nsex:" + sex;
			text += "\nnation:" + nation;
			text += "\naddress:" + address;
		}else if (type == 2){
			text += "\noffice:" + office;
			text += "\nValDate:" + validdate;
		}
		return text;
	}
	

	
}
