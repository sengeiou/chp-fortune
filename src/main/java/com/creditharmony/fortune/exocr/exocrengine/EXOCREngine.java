package com.creditharmony.fortune.exocr.exocrengine;



public final class EXOCREngine{
	private static final String tag = "EXOCREngine";
	
	public static final int mMaxStreamBuf = 4096;
	//time
	public long timestart;
	public long timeend;
	
	//Results
	public byte []bResultBuf;
	public int    nResultLen;
	
	/////////////////////////////////////////////////////////////
	// NDK STUFF
	static {
		//System.loadLibrary("libexocrengine.so");
		System.load("/home/bomber/Downloads/src/libexocrengine.so");
	}
	////////////////////////////////////////////////////////////
	public EXOCREngine(){
		bResultBuf = new byte[mMaxStreamBuf];
		nResultLen = 0;
	}
	//natives/////////////////////////////////////////////////////
	public static native int nativeInit(byte []dbpath);
	public static native int nativeDone();
	
	//双面复印在一张A4纸上，同时识别
//	STD_API(int)	EXCARDS_RecoIDCard2FaceFileServerST(const char *szImgFile, int bWantImg, EXIDCard *pstIDCardF, EXIDCard *pstIDCardB);
//	STD_API(int)	EXCARDS_RecoIDCard2FaceDataServerST(unsigned char *pbImage, int nWidth, int nHeight, int nPitch, int nBitCount, 
//													    int bWantImg, EXIDCard *pstIDCardF, EXIDCard *pstIDCardB);
	
	public static native int EXCARDSRecoIDCard2FaceFileServer(byte[] szImgFile, byte[] szResBuf, int nResBufSize);
	public static native int EXCARDSRecoIDCard2FaceDataServer(byte[] pbImage, int nWidth, int nHeight, int nPitch, int nBitCount, 
			byte[] szResBuf, int nResBufSize);
	
	
	
//	public static native int nativeCheckSignature(Context context);
//	//////////////////////////////////////////////////////////////
//	//IDCardRecognition
//	public static native int nativeRecoIDCardBitmap(Bitmap bitmap, byte[]bresult, int maxsize);
//	public static native int nativeRecoIDCardRawdat(byte []imgdata, int width, int height, int pitch,  int imgfmt, byte []bresult, int maxsize);	
//	public static native Bitmap nativeGetIDCardStdImg(byte []NV21, int width, int height, byte []bresult, int maxsize, int []rects);
//
//	/////////////////////////////////////////////////////////////
//	//VECardRecognition
//	public static native int nativeRecoVECardBitmap(Bitmap bitmap, byte[]bresult, int maxsize);
//	public static native int nativeRecoVECardRawdat(byte []imgdata, int width, int height, int pitch,  int imgfmt, byte []bresult, int maxsize);
//	public static native Bitmap nativeGetVECardStdImg(byte []NV21, int width, int height, byte []bresult, int maxsize, int []rects);
//	
//	/////////////////////////////////////////////////////////////
//	/////////////////////////////////////////////////////////////
//	//Scan Line Recogition
//	public static native int nativeRecoScanLineRawdata(byte []imgdata, int width, int height, int imgfmt,
//			int lft, int rgt, int top, int btm, int nRecoType, byte []bresult, int maxsize);

}