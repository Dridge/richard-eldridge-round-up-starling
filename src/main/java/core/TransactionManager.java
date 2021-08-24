package core;

import requests.IRequestCommand;
import requests.RequestFactory;
import requests.RequestType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

public class TransactionManager {
    String endpoint = "/api/v2/feed/account/%s/category/%s/transactions-between";
    String accountUid;
    String categoryUid;
    IRequestCommand requester;

    public TransactionManager(RequestFactory factory, String accountUid, String categoryUid) {
        this.requester = factory.getRequestCommand(RequestType.GET);
        this.accountUid = accountUid;
        this.categoryUid = categoryUid;
    }

    /**
     * Get transactions for the last seven days
     *
     * @return
     */
    public List<Integer> getTransactionsFromPastWeek() {
        LocalDateTime dateTime = now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String endDateString = dateTime.format(formatter);
        String startDateString = dateTime.minusDays(7).format(formatter);
        StringBuilder builder = new StringBuilder(String.format(endpoint, accountUid, categoryUid));
        builder.append(String.format("?minTransactionTimestamp=%s&maxTransactionTimestamp=%s",
                startDateString,
                endDateString));
        requester.sendRequest(builder.toString());
        return getOutFiguresOnly(requester.getResponseBody());
    }

    private List<Integer> getOutFiguresOnly(String response) {
        List<Integer> result = new ArrayList<>();
        List<String> feedItems = Arrays.asList(response.split("\\[|\\{"));
        List<String> outItemsOnly = feedItems.subList(1, feedItems.size())
                .stream()
                .filter(e -> e.contains("\"direction\":\"OUT\""))
                .collect(Collectors
                .toList());
        for (String feedItem : outItemsOnly) {
            String[] item = feedItem.split(",");
            int amount = Integer.parseInt(
                    item[1].split(":")[1]
                        .replace("}", "").trim()
            );
            result.add(amount);
        }
        return result;
    }
}
