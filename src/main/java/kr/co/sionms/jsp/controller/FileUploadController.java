package kr.co.sionms.jsp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.co.sionms.jsp.domain.Attach;
import kr.co.sionms.jsp.util.ParamSolver;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;


@MultipartConfig( location = ParamSolver.UPLOAD_PATH, fileSizeThreshold = 1024)
@WebServlet("/fileupload")
public class FileUploadController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("file.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getContentType());
		
		Collection<Part> parts = req.getParts();
		
		List<Attach> attachs = new ArrayList<>();
		
		for(Part p : parts) {
			if(p.getContentType() == null) {
				continue;
			}
				//file
				// 파일의 원본이름
				String origin = p.getSubmittedFileName();
				
				//파일명 중 마지막 .의 위치
				int dotIdx = origin.lastIndexOf(".");
				
				// 확장자를 담을 변수
				String ext = "";
				
				// 확장자 구하기
				if (dotIdx > -1) {
					ext = origin.substring(dotIdx); // .txt
				}
				// uuid 문자열 생성
				String uuid = UUID.randomUUID().toString();
//				fileName += ext;
				
				// 경로문자열 반환
				String path = getTodayStr();
				
				// 경로 문자열에 대한 폴더 생성
				File targetPath = new File(ParamSolver.UPLOAD_PATH, path);
				if(!targetPath.exists()) {
					targetPath.mkdirs();
				}
				
				// 원본에 대한 저장
				File fs = new File(targetPath, uuid + ext);
				p.write(fs.getPath());
				
				// 이미지여부 확인 
				// image/x-icon, image/webp 
//				List<String> exceptImgMime = Arrays.asList("image/x-icon","image/webp");
				boolean image = p.getContentType().startsWith("image"); // && !exceptImgMime.contains(p.getContentType());
				
				if(image) {
					// 섬네일 생성
					try {
						File out = new File(targetPath, uuid + "_t" + ext);
						Thumbnailator.createThumbnail(fs, out, 200, 200);
					}catch(UnsupportedFormatException ig) {
						
					}
				}
				attachs.add(new Attach(uuid, origin, image, path));
				// uuid, orgin, image, path 
			
			attachs.forEach(System.out::println);
		}
	}
	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
}
