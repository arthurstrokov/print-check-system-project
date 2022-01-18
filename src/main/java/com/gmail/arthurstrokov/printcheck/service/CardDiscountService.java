package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CardDiscountService {
    private static final Logger log = LoggerFactory.getLogger(CardDiscountService.class);
    @Autowired
    private final CardRepository cardRepository;

    public CardDiscountService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public BigDecimal getCardDiscount(List<String> checkInValues) {
        BigDecimal cardDiscount = BigDecimal.ZERO;
        Pattern pattern = Pattern.compile("card*");
        for (String card : checkInValues
        ) {
            Matcher matcher = pattern.matcher(card);
            boolean matchFound = matcher.find();
            if (matchFound) {
                String presentedCard = checkInValues.get(checkInValues.size() - 1);
                String[] part = presentedCard.split("-");
                String cardId = part[1];
                Card availableCard = cardRepository.findById(Long.parseLong(cardId));
                try {
                    cardDiscount = availableCard.getDiscount();
                } catch (NullPointerException e) {
                    log.info(e.getMessage());
                    log.info(String.format("There is no card with its id: %s", cardId));
                }
            }
        }
        return cardDiscount;
    }
}
