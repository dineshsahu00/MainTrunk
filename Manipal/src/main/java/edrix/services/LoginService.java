package edrix.services;

import edrix.dto.LoginDto;

public class LoginService {

	public boolean validateStudentLogin(LoginDto loginDto){
		boolean loginFlag = Boolean.TRUE;
		return loginDto.getUserId().equalsIgnoreCase("dineshsahu")&&loginDto.getPassword().equalsIgnoreCase("password");
	}
	
	public boolean validateGaurdianLogin(LoginDto loginDto){
		boolean loginFlag = Boolean.TRUE;
		return loginFlag;
	}
	
	public boolean validateOrganisationLogin(LoginDto loginDto){
		boolean loginFlag = Boolean.TRUE;
		return loginFlag;
	}
}
