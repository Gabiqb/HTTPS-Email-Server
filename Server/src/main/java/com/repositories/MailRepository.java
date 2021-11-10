package com.repositories;

import com.model.Mail;

import com.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MailRepository extends MongoRepository<Mail, String> {

    @Query("{to:'?0'}")
    List<Mail> findAllMailsByReceiver(String mail);

}
