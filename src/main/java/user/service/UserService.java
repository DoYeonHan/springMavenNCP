package user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import user.bean.UserImageDTO;

@Service
public interface UserService {

	void upload(List<UserImageDTO> userImageList);

	List<UserImageDTO> getUploadList();
}
