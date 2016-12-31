package domein;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerzoekAfhandeling implements Runnable {

    private final Socket socket;
    private final Zoo zoo;
    private final VerzoekLogger verzoekLogger;

    VerzoekAfhandeling(Socket connection, Zoo zoo, VerzoekLogger verzoekLogger) {
        socket = connection;
        this.zoo = zoo;
        this.verzoekLogger = verzoekLogger;
    }

    @Override
    public void run() {
        //TODO
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.flush();

            Verzoek verzoek = (Verzoek) in.readObject();

            verwerkVerzoek(verzoek);

            out.writeObject(verzoek);
            socket.close();
        } catch (Exception e) {

        }

    }

    private void verwerkVerzoek(Verzoek verzoek) {
        //TODO
        String logBericht = String.format("%s%s",
                socket.getInetAddress().getHostName(),
                verzoek.getParameter());

        verzoekLogger.log(logBericht);
        switch (verzoek.getQuery()) {
            case "query1": {
                List<Dier> li = zoo.geefDierenVanSoortMetNaam(verzoek.getParameter());
                verzoek.setResult(li);
                break;
            }
            case "query2": {
                Double gemiddelde = zoo.geefGemiddeldeGewichtVanDierenInGebouwMetNaam(verzoek.getParameter());
                List<Double> li = new ArrayList<>(Arrays.asList(gemiddelde));
                verzoek.setResult(li);
                break;
            }
            case "query3": {
                List<String> li;
                try {
                    li = zoo.geefNamenVanDierenVanVerzorgerMetNummer(Integer.parseInt(verzoek.getParameter()));
                } catch (NumberFormatException ex) {
                    System.out.println("Geen numerieke waarde");
                    li = new ArrayList<>();
                }
                verzoek.setResult(li);
                break;
            }
            case "query4": {
                List<Verzorger> li = zoo.verzorgersInGebouwMetNaam(verzoek.getParameter());
                verzoek.setResult(li);
                break;
            }
            default:
                verzoek.setResult(new ArrayList<>());
        }
        verzoekLogger.log(String.format("%s afgehandeld", logBericht));
    }

}
