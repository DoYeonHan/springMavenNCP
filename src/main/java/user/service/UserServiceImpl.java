package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserImageDTO;
import user.dao.UserDAO;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void upload(List<UserImageDTO> userImageList) {
		userDAO.upload(userImageList);
	}

	@Override
	public List<UserImageDTO> getUploadList() {
		return userDAO.getUploadList();
	}
	
}
