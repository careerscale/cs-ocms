package in.careerscale.apps.ocms.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.LoginMaster;



import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();

	@Mock
	private UserRepository userRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldInitializeWithOneDemoUser() {
		// act
		userService.initialize();
		// assert
		//verify(userRepositoryMock, times(2)).save(any(LoginMaster.class));
	}

	@Test
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

		when(userRepositoryMock.findByUsername("user@careerscale.in")).thenReturn(null);
		// act
		userService.loadUserByUsername("user@careerscale.in");
	}

	/*@Test
	public void shouldReturnUserDetails() {
		// arrange
		LoginMaster demoUser = new LoginMaster("user@careerscale.in", "test123","demo last name", "ROLE_USER");
		when(userRepositoryMock.findByUsername("user")).thenReturn(demoUser);

		// act
		UserDetails userDetails = userService.loadUserByUsername("user");

		// assert
		assertEquals(demoUser.getEmailId(), userDetails.getUsername());
		assertEquals(demoUser.getPassword(), userDetails.getPassword());
		//assertTrue(hasAuthority(userDetails, demoUser.getRole()));
	}*/

	private boolean hasAuthority(UserDetails userDetails, String role) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
}