package com.ovs.diploma.web.dao;

import com.ovs.diploma.web.model.File;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FileDetailsDaoImp implements FileDetailsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<File> getAllFileByUsername(String username) {
        List<File> files ;
        Query query = sessionFactory.getCurrentSession().createQuery("from File where username= ?")
                .setParameter(0, username);
        files = query.list();
        return files;
    }

    @Transactional
    @Override
    public void saveFile(File file) {
        System.out.println(file.getFileName());
        sessionFactory.getCurrentSession().saveOrUpdate(file);
    }

    @Transactional(readOnly = true)
    @Override
    public File getFileById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from File where id= ?")
                .setParameter(0, id);
        return (File) query.list().get(0);
    }
}
