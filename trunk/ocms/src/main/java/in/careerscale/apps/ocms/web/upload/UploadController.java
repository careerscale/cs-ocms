package in.careerscale.apps.ocms.web.upload;

import in.careerscale.apps.ocms.web.upload.model.UploadedFile;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {

	Log log = LogFactory.getLog(UploadController.class);


	@RequestMapping(value = "/file", method = RequestMethod.POST)
	
	public @ResponseBody
	List<UploadedFile> upload(@RequestParam("file") MultipartFile file) {
		System.out.println("inside upload method  ::" + file.getName() +   "  Original name ::" + file.getOriginalFilename()) ;
	   //TODO
		// i.e. Save the file to a temporary location or database
		//	logger.debug("Writing file to disk...done");
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		UploadedFile u = new UploadedFile(file.getOriginalFilename(), Long
				.valueOf(file.getSize()).intValue(),
				"http://localhost:90/resources/"
						+ file.getOriginalFilename());

		uploadedFiles.add(u);
	
		return uploadedFiles;
	}


}
