package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.File;

import java.util.List;

public interface FileDetailsDao {
    List<File> getAllFileByUsername(String username);
    void saveFile(File file);
    File getFileById(int id);

}
