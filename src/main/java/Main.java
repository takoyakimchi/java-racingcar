import domain.Car;
import domain.RacingGame;
import domain.Round;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Main {

    public static void main(String[] args) {
        try {
            // TODO: 아래 중복되는 부분을 메서드로 통합하기
            // TODO: 메인 메서드 15줄 이내로 변경
            OutputView.printCarNames();
            String rawCarNames = InputView.read();
            RacingGame racingGame = new RacingGame(rawCarNames); // TODO: 예외 발생 시 재입력받기 구현

            OutputView.printRound();
            String rawRound = InputView.read();
            Round round = new Round(rawRound);

            OutputView.printRoundResult();

            // TODO: for문을 없애는 방법을 생각해보기
            for (int i = 0; i < round.getRound(); i++) {
                racingGame.play();
                OutputView.printScore(racingGame.getCars());
            }

            List<Car> winners = racingGame.findWinners();
            OutputView.printCars(winners);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
