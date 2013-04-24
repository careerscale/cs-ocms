package in.careerscale.apps.ocms.notification;

import in.careerscale.apps.ocms.dao.NotificationRepository;
import in.careerscale.apps.ocms.dao.model.Notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("notificationService")
public class NotificationService {

		Log log = LogFactory.getLog(NotificationService.class);
		
		@Autowired
		private NotificationRepository notificationRepository;
		
		public void createNotification(){
			Notification notification = new Notification();
			notificationRepository.save(notification);
		}
		
		
}
