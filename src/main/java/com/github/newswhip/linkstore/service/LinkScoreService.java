package com.github.newswhip.linkstore.service;

import com.github.newswhip.linkstore.common.UrlUtils;
import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkScoreRepo;

import java.net.MalformedURLException;

public enum LinkScoreService {

    INSTANCE;

    private LinkVORepository linkVORepository;

    LinkScoreService() {
        this.linkVORepository = new InMemoryLinkScoreRepo();
    }

    public void addLink(String url, Long score) {
        this.linkVORepository.addLinkWithScore(new LinkVO(url, UrlUtils.getDomain(url), score));
    }
}
