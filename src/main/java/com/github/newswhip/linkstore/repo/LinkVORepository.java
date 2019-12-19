package com.github.newswhip.linkstore.repo;

import com.github.newswhip.linkstore.model.LinkDomainStats;
import com.github.newswhip.linkstore.model.LinkVO;

public interface LinkVORepository {
    void addLinkWithScore(LinkVO linkVO);
    void removeLink(LinkVO linkVO);
    LinkDomainStats exportLinkDomainStats();
}
