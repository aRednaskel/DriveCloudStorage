package com.projects.storage.DriveCloudStorage.mapper;

import com.projects.storage.DriveCloudStorage.model.Credential;
import com.projects.storage.DriveCloudStorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url}")
    Credential getUrl(String url);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getUsetNotes(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) VALUES(#{url}, #{username}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Credential credential);
}
