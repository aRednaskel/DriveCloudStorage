package com.projects.storage.DriveCloudStorage.mapper;

import com.projects.storage.DriveCloudStorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{notetitle}")
    Note getNote(String title);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getUsetNotes(Integer userId);

    @Select("SELECT n.userid FROM NOTES n WHERE noteid = #{noteId}")
    int getUserId(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int update(Note note);

    @Delete("DELETE FROM NOTES WHERE userid = #{noteId}")
    int delete(Integer noteId);
}
