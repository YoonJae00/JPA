package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeBookService {

    @Autowired
    private LikeBookRepository likeBookRepository;

    @Transactional
    public void generateLikeBook(LikeDTO likeDTO) {
        Like like = new Like(
               new LikedCompositeKey(
                       new LikedMemberNo(likeDTO.getLikedMemberNo()),
                       new LikedBookNo(likeDTO.getLikedBookNo())
               )
               );

               likeBookRepository.save(like);
    }
}
