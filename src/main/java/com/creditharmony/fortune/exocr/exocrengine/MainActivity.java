package com.creditharmony.fortune.exocr.exocrengine;

public class MainActivity {
	
    public native static int get();
    public native static void set(int i);
    public static void main(String[] args)
    {
    	String path = "/home/bomber/Downloads";
    	byte [] bPath = new byte[256];
    	for(int i = 0; i < path.length(); i++){
    		bPath[i]= (byte)path.charAt(i);
    	}
    	bPath[path.length()] = 0;
    	
    	int res = EXOCREngine.nativeInit(bPath); 
    	System.out.println(res);
    	String imgPath = "/home/bomber/Downloads/01.jpg";
 
    	EXOCREngine engine = new EXOCREngine();
    	
    	for(int i = 0; i < imgPath.length(); i++){
    		bPath[i]= (byte)imgPath.charAt(i);
    	}
    	bPath[imgPath.length()] = 0;
    	
    	int rstLen = EXOCREngine.EXCARDSRecoIDCard2FaceFileServer(bPath, engine.bResultBuf, 4096);
    	System.out.println(rstLen);
    	
    	if(rstLen > 0){
    		EXIDCardResult idcardRst = EXIDCardResult.decode(engine.bResultBuf, rstLen);   	
    		if(idcardRst != null)
        	{
        		System.out.println(idcardRst.name);
        		System.out.println(idcardRst.sex);
        		System.out.println(idcardRst.nation);
        		System.out.println(idcardRst.address);
        		System.out.println(idcardRst.cardnum);
        		
        		System.out.println(idcardRst.office);
        		System.out.println(idcardRst.validdate);
        	}
    	}
    	
    	int rt = EXOCREngine.nativeDone();
    	System.out.println(rt);
    	
    }
    
    

}
