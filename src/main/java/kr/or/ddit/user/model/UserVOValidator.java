package kr.or.ddit.user.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.isAssignableFrom(clazz);
	}

	 //이제 여기서 체크를 해주면 돼요. type이 object로 되어있죠? 유연성을 생각해서 object로 되어있는데 그래서 사용할려면 형변환을 해야해요
	@Override
	public void validate(Object target, Errors errors) {
		
		//데이터 검증
		UserVO userVO = (UserVO) target;
		
		//사용자 아이디 길이 4글자 이상, 3글자 이하면 체크를 하겠다는 거죵
		if(userVO.getUserId().length() <= 3) {
			errors.rejectValue("userId", "length"); //이름은 정의하기 나름이에용
		}
		
		//사용자 이름이 2글자 이상
		if(userVO.getName().length() < 2) {
			errors.rejectValue("name", "length");
		}
		
	}

}
