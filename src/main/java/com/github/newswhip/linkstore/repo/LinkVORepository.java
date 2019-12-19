package com.github.newswhip.linkstore.repo;

import com.github.newswhip.linkstore.model.DomainVOReport;
import com.github.newswhip.linkstore.model.LinkVO;

import java.util.Collection;

public interface LinkVORepository {
    void addLinkWithScore(LinkVO linkVO);
    void removeLink(LinkVO linkVO);
    Collection<LinkVO> getLinks();
    DomainVOReport exportLinkDomainStats();
}
