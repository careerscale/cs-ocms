package in.careerscale.apps.ocms.web.upload;

import in.careerscale.apps.ocms.web.upload.model.UploadedFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@Controller
public class UploadController {

	Log log = LogFactory.getLog(UploadController.class);

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public @ResponseBody
	List<UploadedFile> upload(@RequestParam("file") MultipartFile file) {
		System.out.println("inside upload method  ::" + file.getName()
				+ "  Original name ::" + file.getOriginalFilename());
		// TODO
		// i.e. Save the file to a temporary location or database
		// logger.debug("Writing file to disk...done");
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		UploadedFile u = new UploadedFile(file.getOriginalFilename(), Long
				.valueOf(file.getSize()).intValue(),
				"http://localhost:90/resources/" + file.getOriginalFilename());

		uploadedFiles.add(u);

		return uploadedFiles;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	List<UploadedFile> addFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {

			if (!(request instanceof DefaultMultipartHttpServletRequest)) {
				throw new IllegalArgumentException(
						"Request is not multipart, please 'multipart/form-data' enctype for your form.");
			}

			DefaultMultipartHttpServletRequest dmhsRequest = (DefaultMultipartHttpServletRequest) request;
			MultipartFile multipartFile = (MultipartFile) dmhsRequest
					.getFile("image");

			System.out.println("inside upload method  ::"
					+ multipartFile.getName() + "  Original name ::"
					+ multipartFile.getOriginalFilename());
			// TODO
			// i.e. Save the file to a temporary location or database
			// logger.debug("Writing file to disk...done");
			List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
			UploadedFile u = new UploadedFile(
					multipartFile.getOriginalFilename(), Long.valueOf(
							multipartFile.getSize()).intValue(),
					"http://localhost:90/resources/"
							+ multipartFile.getOriginalFilename());

			uploadedFiles.add(u);

			return uploadedFiles;
		}
		return null;
	}

}
