package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that allowed get card discount
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    /**
     * @param checkInValues List where stored card discount id
     * @return card discount
     */
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
                Card availableCard;
                String cardId = "";
                try {
                    cardId = part[1];
                    availableCard = cardRepository.findById(Long.parseLong(cardId));
                    cardDiscount = availableCard.getDiscount();
                } catch (Exception e) {
                    log.error("Check input card value.");
                    log.error("There is no card id: {}", cardId);
                    log.error(e.getMessage());
                }
            }
        }
        return cardDiscount;
    }

    /**
     * @param presentedCard String where stored card discount id
     * @return card discount
     */
    public BigDecimal getCardDiscount(String presentedCard) {
        BigDecimal cardDiscount = BigDecimal.ZERO;
        Card availableCard;
        String cardId = "";
        try {
            String[] part = presentedCard.split("-");
            cardId = part[1];
            availableCard = cardRepository.findById(Long.parseLong(cardId));
            cardDiscount = availableCard.getDiscount();
        } catch (Exception e) {
            log.error("Check input card value.");
            log.error("There is no card id: {}", cardId);
            log.error(e.getMessage());
        }
        return cardDiscount;
    }
}
