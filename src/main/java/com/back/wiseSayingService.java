package com.back;

import java.util.ArrayList;

public class wiseSayingService {
    private wiseSayingRepository repository;

    public wiseSayingService(wiseSayingRepository repository){
        this.repository = repository;
    }

    public wiseSay write(String content, String author) {
        return repository.save(content, author);
    }

    public ArrayList<wiseSay> findAll() {
        return repository.findAll();
    }

    public boolean removeById(int id) {
        return repository.removeById(id);
    }

    public wiseSay findById(int id) {
        return repository.findById(id);
    }

    public wiseSay update(int id, wiseSay target) {
        return repository.update(id, target);
    }

    public boolean databuild() {
        return repository.databuild();
    }
}
/*

 */