import conversion.Converter;
import domain.Car;
import java.util.List;
import service.Service;
import view.InputView;
import view.IterativeReader;
import view.OutputView;

public class Main {

    public static void main(String[] args) {
        List<Car> cars = IterativeReader.readUntilNoError(Main::findCars);
        int round = IterativeReader.readUntilNoError(Main::findRound);

        OutputView.printRoundResult();

        for (int i = 0; i < round; i++) {
            Service.playOneRound(cars);
            OutputView.printScore(cars);
        }

        List<String> winnerNames = Service.getWinnerNames(cars);
        OutputView.printWinners(winnerNames);
    }

    private static List<Car> findCars() {
        OutputView.printCarNames();
        String rawCarNames = InputView.read();
        return Converter.toCars(rawCarNames);
    }

    private static int findRound() {
        OutputView.printRound();
        String rawRound = InputView.read();
        return Converter.toRound(rawRound);
    }
}
