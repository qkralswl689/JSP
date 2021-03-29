package util;

public class UploadFileUtil {
	
	/**
	 * 원래 파일명 가져오기 
	 *  
	 * @param destFileName 암호화된 실제 저장 파일
	 * @return 업로드시 원래 파일명
	 */
	public static String getOriginalFileName(String destFileName) {

		String originalFileName;
		
		String[] str = destFileName.split("\\.");
		String ext = str[str.length-1]; // 확장자
		
		System.out.println("ext : "+ext);
		
		// "_" 가 여러개가 있을 경우 마지막 위치를 획득
		int index = destFileName.lastIndexOf("_");
		// System.out.println("index : "+index);
		
		originalFileName = destFileName.substring(0,index);
		
		return originalFileName +"."+ext;
	} //
		
	// Test
	public static void main(String[] args) {
		
		System.out.println(getOriginalFileName("Hydrangeas_201912160848355de515e7.jpg"));
		System.out.println(getOriginalFileName("Desert_77202_20191213_201912160845354647ceb2.jpg"));
	}

}
