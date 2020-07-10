package com.projects.storage.DriveCloudStorage.mapper;

import com.projects.storage.DriveCloudStorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{notetitle}")
    Note getNote(String title);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getUsetNotes(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Note note);
}
