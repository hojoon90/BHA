package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import org.springframework.transaction.annotation.Transactional;

public interface CollectPersister {
    void postArticle(AllBoard allBoard);

    @Transactional
    void putArticle(AllBoard allBoard);
}
