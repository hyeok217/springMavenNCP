package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserImageDTO;
import user.dao.UserDAO;

@Service
@Transactional//여기에 걸던가 메소드마다 걸던가
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void upload(List<UserImageDTO> userImageList) {
		userDAO.upload(userImageList);	//리스트 들고 가라고 시켜주세요
		
	}

	@Override
	public List<UserImageDTO> getUploadList() {
		return userDAO.getUploadList();
	}
}
