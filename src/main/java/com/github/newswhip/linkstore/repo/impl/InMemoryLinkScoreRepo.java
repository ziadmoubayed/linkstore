package com.github.newswhip.linkstore.repo.impl;

import com.github.newswhip.linkstore.model.LinkDomainStats;
import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;

import java.util.HashSet;
import java.util.Set;

public class InMemoryLinkScoreRepo implements LinkVORepository {

    private Set<LinkVO> store = new HashSet<>();

    @Override
    public void addLinkWithScore(LinkVO linkVO) {
        store.add(linkVO);
    }

    @Override
    public void removeLink(LinkVO linkVO) {
        store.remove(linkVO);
    }

    @Override
    public LinkDomainStats exportLinkDomainStats() {
        return null;
    }
}
