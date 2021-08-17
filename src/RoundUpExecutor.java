import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RoundUpExecutor {
    private final String baseUrl = "https://api-sandbox.starlingbank.com";
    private final String authKey = "eyJhbGciOiJQUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_31Sy5LbIBD8lZTOO1t6IK-lW275gXzACAabMgIVoN1spfLvQQJZlrOVG909rx7md6G8L_oCJwWCRvvqAzqtzGVAc3vldixeCj8PMeKtYaxiTEIpGwHszDjg6VRDNVTD0IoTIutiMP2air461V1TVW3XvhQKQyLquq0WAjm3swk_rBbkfiqx1O5YKWtqoZFvLTB6a6BjTQdDV3fn6lxShyzWDvZGJmW0bdtx3p2hFAMDJtkJzh0_Q9swPJXEZFu1MSPa-s45eZ-yJOfYlQMCcllFD7yETjQMSlaXQzM051LKxTC3Ey1LSZPCdR0VDI7UO0Lx7UkIn9OToASZoKQid-S18uHAZCCEi0P2JFS4g6SEgPw60j1yxx9OBfqGc7hap3z8MlBGqHclZtQpeECNhufRODoB3JrgrE6NFiZr1kjlRgzKGrAS5GyEv0v-3n0DqTWffbDjZpFGVLmwpjiIufQ4TfrzjtaoEY3AQL0gTbHEBrPmbhQWI5MjSY7i7P5_UhojaZNGTnEDgS5u9fGY-K-YU8nxK27uRgoYp8GeR7iqGa-mJvwk2qQEsokE9iBQI16yp6TtTwgOjUe-TxhpGGZ967efpJ3auyW8N0x4K7DcQ7ytUYW9prY83sFDhZUAuxzEM5uznJVKb-MnPwdqjXLESU3hAPxRSsv1-B4_zMPF7nMcuOzmwK11Hpm0sPjlX5XYxS9q7WIqyq8kZk0C8sIyTSFEg_OUYcD1WsE68dDzyG7NjuwX-WA_zJ0PtH4T9-_P1CRkpubBcxdXuFzH1uWRW6MeT2j9peebKv78BcYubv3XBQAA.MNVBqVyyU0b4KX8UH3LLCGdGtWqxPo9YimBDCO6g-A7U9WQAbq63cnHFH1D7xGgbKfyVGkqFpG-UytnYhMOeSDyz1Vvl5gppvbnZ-0K6-usV80LVXUDXgAAxmNJtWIYEMurqbTVCvLIgDirxf6AxTbMARH3-IDbbc4mzpdMA7MA6sXfkL_hO72D7vjqoc0pxoB3M5SM1p3ufBsGt98u5gNNMc3yR8MhsK_Kc0dp8BWa76K210HAKQ33XWlcs9ucgTvKMDNTuEESgrMcjQbI7eaAb17LaqWdDKYkW_mgs8fl0GjvQmgeva6z6gN13bz1UM1QLD7yd-XP7U6jclMFzFfpxWoPu72A6EXg_XbD7pojW-XnhfpLn6hwh5edZ_aUrtJo56ZZLQ3h1_MTWsbPBQaVrHHlU8fQI-PqU-t6TyY41gEnD846oMCYjCPr4Hh6RKEKyvC2jHGSi6zM4C1gv0joZ0hjXu16VRAJPhbA1Ayk-anYkfDekrBijmdLQq7jPmpZgtElYLV_qXRLY6H3O1MssUUZBp58Y9GyRfm7r3P1pHYWQlL4xY9XG5V-7D-pHLlMpNVGdDUXyUm1bNDzJuhRAm-h5wXh6HdXTL6RnynCSyQjgAnNxbyHz1dP5uzdd2N17PkN6T07nADYvxvUdh45rG5L8rGV0LEqKRzaY-8I";

    /**
     * Executes the round up
     */
    public void execute() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/v2/account-holder/individual"))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + authKey)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
