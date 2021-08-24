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
    Account account;
    IRequestCommand requester;

    String string = "2013-03-05T18:05:05.000Z";
    String defaultTimezone = TimeZone.getDefault().getID();
    public TransactionManager(RequestFactory factory, Account account) {
        this.account = account;
        this.requester = factory.getRequestCommand(RequestType.GET);
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
        StringBuilder builder = new StringBuilder(String.format(endpoint,
                account.getAccountUid(),
                account.getDefaultCategory()));
        builder.append(String.format("?minTransactionTimestamp=%s&maxTransactionTimestamp=%s",
                startDateString,
                endDateString));
        requester.sendRequest(builder.toString());
        return getOutFiguresOnly(requester.getResponseBody());
    }

    private List<Integer> getOutFiguresOnly(String response) {
        List<Integer> result = new ArrayList<>();
        List<String> feedItems = Arrays.asList(response.split("\\["));
        List<String> outItemsOnly = feedItems.subList(1, feedItems.size())
                .stream()
                .filter(e -> e.contains("\"direction\": \"OUT\""))
                .collect(Collectors
                .toList());
        for (String item : feedItems) {
            if( item.contains("\"direction\": \"OUT\"")) {
                outItemsOnly.add(item);
            }
        }
        for (String feedItem : outItemsOnly) {
            String[] item = feedItem.split(",");
            int amount = Integer.parseInt(
                    item[5].split(": ")[1]
                        .replaceAll("\\n      }", "")
            );
            result.add(amount);
        }
        return result;
    }
}
