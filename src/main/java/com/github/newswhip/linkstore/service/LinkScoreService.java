package com.github.newswhip.linkstore.service;

import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkScoreRepo;

import java.util.Map;
import java.util.stream.Collectors;


public enum LinkScoreService {

    INSTANCE;

    private LinkVORepository linkVORepository;

    LinkScoreService() {
        this.linkVORepository = new InMemoryLinkScoreRepo();
    }

    public void addLink(String url, Long score) {
        if (score < 0)
            throw new IllegalArgumentException("Invalid score. It should be a whole number, greater or equal to zero");
        this.linkVORepository.addLinkWithScore(new LinkVO(url, score));
    }

    public void removeLink(String url) {
        this.linkVORepository.removeLink(new LinkVO(url));
    }

    public void flushStore() {
        this.linkVORepository.deleteAll();
    }

    public Map<String, Long> getDomainStats() {
        return this.linkVORepository.getLinks()
                .collect(Collectors.groupingBy(LinkVO::getDomain, Collectors.summingLong(LinkVO::getScore)));
    }
}
