package com.gmail.arthurstrokov.printcheck.util;

import com.gmail.arthurstrokov.printcheck.model.Card;
import com.gmail.arthurstrokov.printcheck.repository.CardRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCardDiscount {

    public static Integer getCardDiscount(List<String> checkInValues, CardRepository cardRepository) {
        int cardDiscount = 0;
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
                cardDiscount = availableCard.getDiscount();
            }
        }
        return cardDiscount;
    }
}
