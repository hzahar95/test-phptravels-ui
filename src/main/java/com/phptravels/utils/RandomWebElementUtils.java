package com.phptravels.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomWebElementUtils {

    public static void selectCertainNumberOfRandomWebElements(int numberOfElements, List<WebElement> webElements) {
        Set<Integer> randomWebElementIndexes = new HashSet<>(numberOfElements);
        while (randomWebElementIndexes.size() < numberOfElements) {
            int index = new Random().nextInt(webElements.size());
            randomWebElementIndexes.add(index);
        }
        for (Integer randomWebElementIndex : randomWebElementIndexes) {
            webElements.get(randomWebElementIndex).click();
        }
    }

}
