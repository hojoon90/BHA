package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;

public interface CollectPersister {
    void postArticle(AllBoard allBoard);
    void putArticle(AllBoard allBoard);
    void deleteArticle(AllBoard allBoard);
}
