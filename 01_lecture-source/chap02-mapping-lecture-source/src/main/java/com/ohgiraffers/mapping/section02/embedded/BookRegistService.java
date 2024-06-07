package com.ohgiraffers.mapping.section02.embedded;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRegistService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void registBook(BookRegistDTO bookRegist) {

        Price newPrice = new Price(
                bookRegist.getRegularPrice(),bookRegist.getDiscountRate()
        );
        Book book = new Book(
                bookRegist.getBookTitle(),bookRegist.getAuthor(),bookRegist.getPublisher(),bookRegist.getPublishedDate(),newPrice
        );

        bookRepository.registBook(book);
    }


}
