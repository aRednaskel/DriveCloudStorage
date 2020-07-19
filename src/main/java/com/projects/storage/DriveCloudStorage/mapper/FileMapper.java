package com.projects.storage.DriveCloudStorage.mapper;

import com.projects.storage.DriveCloudStorage.model.StoredFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE (filename = #{fileName} AND userid = #{userId})")
    StoredFile getFile(String fileName, Integer userId);

    @Select("SELECT f.filename FROM FILES f WHERE userid = #{userId}")
    List<String> getUserFiles(Integer userId);

    @Select("SELECT f.userid FROM FILES f WHERE fileid = #{fileid}")
    int getUserId(Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insert(StoredFile storedFile);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    int delete(String filename);
}
