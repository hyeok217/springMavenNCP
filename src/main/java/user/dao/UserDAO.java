package user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserImageDTO;

@Mapper	//나는 매퍼파일과 관련된 애들이야(구현체 생략->하면 스프링 환경설정 springConfiguration가야함)
public interface UserDAO {

	public void upload(List<UserImageDTO> userImageList);

	public List<UserImageDTO> getUploadList();

	
	
}
