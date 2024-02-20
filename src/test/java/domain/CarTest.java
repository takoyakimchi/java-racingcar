package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "ABCde"})
    @DisplayName("이름 입력 성공")
    void carName_ok(String carName) {
        assertThatCode(() -> new Car(carName)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" "})
    @DisplayName("이름 입력 실패: 공백 입력")
    void carName_exception_noBlank_noEmpty(String name) {
        assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("자의 알파벳만 입력 가능합니다.");
    }

    @Test
    @DisplayName("이름 입력 실패: null 입력")
    void carName_exception_notNull() {
        assertThatThrownBy(() -> new Car(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이름은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", ""})
    @DisplayName("이름 입력 실패: 경계값 미만 0글자, 경계값 초과 6글자")
    void carName_exception_cantViolateLengthRegulations(String name) {
        assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("자의 알파벳만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a123", "A!"})
    @DisplayName("이름 입력 실패: 알파벳 이외 문자 입력")
    void carName_exception_cantIncludeNonAlphabets(String name) {
        assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("자의 알파벳만 입력 가능합니다.");
    }

    @Test
    @DisplayName("성공: 자동차는 정상적으로 움직인다.")
    void move() {
        // given
        Car carA = new Car("CarA");

        // when
        carA.move();

        // then
        assertThat(carA.getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공: score를 기준으로 최댓값 구하기")
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
    @DisplayName("성공: 두 Car 객체가 score를 기준으로 같으면 equal하다.")
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
