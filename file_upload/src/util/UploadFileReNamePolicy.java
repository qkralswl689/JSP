package util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UploadFileReNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
          // 중복된 파일 업로드 됐을 때 파일명 처리
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
          // 파일 형식 : 원본 파일명 + "_" + 날짜 포맷 + 해쉬코드 + 확장자
          String name = f.getName();
          String pureFileName = name.split("\\.")[0];
          String uniqueFileName = pureFileName + "_" 
        		  			    + sdf.format(new Date(System.currentTimeMillis())) 
        		  			    + Integer.toHexString(this.hashCode());
          						// 16진수 해쉬코드(최대 8자리)

          @SuppressWarnings("unused")
		  String body = null;
          String ext = null;
          
          System.out.println("### name : "+name);
          System.out.println("### uniqueFileName : "+uniqueFileName);

          int dot = name.lastIndexOf(".");
          if (dot != -1) {
               body = name.substring(0, dot);
               ext = name.substring(dot);  // includes "."
          }
          else {
               body = name;
               ext = "";
          }
    
          String tempName = uniqueFileName + ext;
          f = new File(f.getParent(), tempName);
          if (createNewFile(f)) {
               return f;
          }

          int count = 0;
          while (!createNewFile(f) && count < 9999) {
               count++;
               String newName = uniqueFileName + "_" + count + ext;
               f = new File(f.getParent(), newName);
          }

          return f;
     }

     private boolean createNewFile(File f) {
          try {
               return f.createNewFile();
          }
          catch (IOException ignored) {
               return false;
          }
     }
     
}	     