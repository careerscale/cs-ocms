package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.model.LoginMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractService
{
	@Autowired
	protected MasterDataRepository masterDataRepository;

	public LoginMaster getLoggedInUser()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		ExtendedUser user = (ExtendedUser) authentication.getPrincipal();

		return (LoginMaster) masterDataRepository.getById(LoginMaster.class, user.getId());
	}

	public Integer getLoggedInUserId()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		ExtendedUser user = (ExtendedUser) authentication.getPrincipal();

		return user.getId();
	}
}
