package com.github.newswhip.linkstore.repo;

import com.github.newswhip.linkstore.model.LinkVO;

import java.util.stream.Stream;

public interface LinkVORepository {
    void addLink(LinkVO linkVO);

    void removeLink(LinkVO linkVO);

    Stream<LinkVO> getLinks();

    void deleteAll();
}
