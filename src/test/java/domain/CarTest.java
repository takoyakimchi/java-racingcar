package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("이름 정상 입력: 자동차의 이름 정상 입력")
    void carName_ok() {
        Assertions.assertAll(
            () -> new Car("a"),
            () -> new Car("aBcDe")
        );
    }

    @Test
    @DisplayName("이름 예외 입력: 자동차의 이름은 공백일 수 없다.")
    void carName_exception_noBlank_noEmpty() {
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> new Car(""))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> new Car(" "))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("이름 예외 입력: 자동차의 이름은 1~5자만 허용된다.")
    void carName_exception_cantViolateLengthRegulations() {
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> new Car(""))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> new Car("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("이름 예외 입력: 자동차의 이름은 알파벳만 가능하다.")
    void carName_exception_() {
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> new Car("a123"))
                .isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> new Car("A!"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("자동차는 정상적으로 움직인다.")
    void move() {
        // given
        Car carA = new Car("CarA");

        // when
        carA.move();

        // then
        assertThat(carA.getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차끼리 score를 기준으로 최댓값을 구할 수 있다.")
    void compare() {
        // given
        Car carA = new Car("CarA");
        Car carB = new Car("CarB");
        Car carC = new Car("CarC");
        List<Car> cars = new ArrayList<>(List.of(carA, carB, carC));

        // when
        carA.move();
        carB.move();
        carC.move();
        carC.move();

        // then
        assertThat(Collections.max(cars)).isEqualTo(carC);
    }

    @Test
    @DisplayName("두 Car 객체가 score를 기준으로 같으면 equal하다고 판정한다.")
    void equals() {
        // given
        Car carA = new Car("CarA");
        Car carB = new Car("CarB");

        // when
        carA.move();
        carA.move();
        carB.move();
        carB.move();

        // then
        assertThat(carA).isEqualTo(carB);
    }
}
