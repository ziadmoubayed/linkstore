package com.github.newswhip.linkstore.repo;

import com.github.newswhip.linkstore.model.DomainVOReport;
import com.github.newswhip.linkstore.model.LinkVO;

public interface LinkVORepository {
    void addLinkWithScore(LinkVO linkVO);
    void removeLink(LinkVO linkVO);
    DomainVOReport exportLinkDomainStats();
}
