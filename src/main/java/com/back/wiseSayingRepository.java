package com.back;

import java.util.ArrayList;

public interface wiseSayingRepository {
    wiseSay save(String content, String author);
    ArrayList<wiseSay> findAll();
    wiseSay findById(int id);
    boolean removeById(int id);
    wiseSay update(int id, wiseSay target);
    boolean dataBuild();
}
