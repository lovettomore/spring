package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService{

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	/**
	 * 
	* Method 		: userList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}
	
	/**
	 * 
	* Method 		: insertUser
	* 작성자 			: 
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 등록
	 */
	@Override
	public int insertUser(UserVO userVO) {
		return userDao.insertUser(userVO);
	}

	/**
	 * 
	* Method 		: deleteUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		: 
	* @param userId
	* @return
	* Method 설명 	: 특정 사용자 조회
	 */
	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}

	/**
	 * 
	* Method 		: updateUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 수정
	 */
	@Override
	public int updateUser(UserVO userVO) {
		return userDao.updateUser(userVO);
	}

	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @return
	* Method 설명 	: 사용자 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> userPagingList(PageVO pageVO) {
		/*
			하나의 메소드에서 두개를 리턴해야되요. 두개의 값을 동시에 넣을 수 있는 객체가 필요해요
			------------------------------------------------------------------
			1. List<UserVO>, userCnt를 필드로 하는 vo
			2. List<Object> resultList = new ArrayList<Object>();
			   resultList.add(userList);
			   resultList.add(userCnt);
			3. Map<String, Object> resultMap = new HashMap<String, Object>();
			   resultMap.put("userList", userList);
			   resultMap.put("userCnt", userCnt);
			   
			------------------------------------------------------------------
			저는 3번째 방법을 추천 드려용 옛썰!
		*/
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVO));
		
		//usersCnt > paginationSize 으로 변경
		int userCnt = userDao.usersCnt();
		
		//pageSize > pageVO.getPageSize();
		int paginationSize = (int)Math.ceil((double)userCnt/pageVO.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	 * 
	* Method 		: encryptPassAllUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 비밀번호 암호화 일괄 적용 배치
	 */
	/********************** 사용 하지 마세요. 이미 암호화 적용 되어 있어용 *************************/
	@Override
	public int encryptPassAllUser(SqlSession sqlSession) { 
		//1. sql 실행에 필요한 sqlSession 객체를 생성
		//2. 모든 사용자 정보를 조회 (단, 기존 암호화 적용 사용자 제외, brown, lazysunday 제외)
		List<UserVO> userList = userDao.userListForPassEncrypt(sqlSession);
		
		//3. 조회된 사요자의 비밀번호를 암호화 적용후 사용자 업데이트
		int updateCntSum = 0;
		for(UserVO userVO : userList) {
			String encryptPass = KISA_SHA256.encrypt(userVO.getPass());
			userVO.setPass(encryptPass);
			
			int updateCnt = UserDao.updateUserEncryptPass(sqlSession, userVO); //얘가 사용자 비밀번호를 업데이트 해줄거에요
			updateCntSum += updateCnt; //누적된 결과를 더해줌. 몇건이나 처리 했는지 알려고ㅋㅋ
			
			//얘는 비정상
			if(updateCnt != 1){
				sqlSession.rollback();
				break;
			}
		}
		
		//4. sqlSession 객체를 commit, close
		sqlSession.commit();
		sqlSession.close();
		
		return updateCntSum; //몇번이나 리턴했는지 합을 보려고
	}

}
