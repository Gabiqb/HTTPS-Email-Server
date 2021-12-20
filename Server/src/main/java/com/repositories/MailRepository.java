package com.repositories;

import com.model.Mail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends MongoRepository<Mail, String> {

    @Query("{to:'?0',draft:false}")
    List<Mail> findAllMailsByReceiver(String mail);

    @Query("{from:'?0',draft:false}")
    List<Mail> findAllMailsBySender(String mail);

    @Query("{to:'?0',draft:true}")
    List<Mail> findAllDraftMails(String mail);


}
