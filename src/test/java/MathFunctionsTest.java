import net.jqwik.api.*;
import org.example.MathFunctions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathFunctionsTest {

    //Propriedade: O dobro de um número sempre é maior ou igual ao próprio número.
    @Property
    void multiplyByTwoMustBeGreaterOrEqualThanEntry(
            @ForAll("positiveIntegers") int positiveInteger) {
        int result = MathFunctions.multiplyByTwo(positiveInteger);
        assertTrue(result >= positiveInteger, "O resultado deve " +
                "ser maior ou igual ao número de entrada");
    }

    //Provedor de números inteiros positivos aleatórios
    @Provide
    Arbitrary<Integer> positiveIntegers() {
        //Na primeira vez, eu tinha colocado qualquer valor acima de 0, entretanto,
        //após várias execuções isso gerou um erro, pois ele tentou multiplicar o
        //1474098424 por 2, ultrapassando o limite que o java permite para valores
        //inteiros, que é 2147483647. Então dividi esse número por 2, obtendo o
        //1073741823, que passou a ser o valor máximo para os valores gerados
        //automaticamente, evitando esse problema de overflow.
        return Arbitraries.integers().between(0, 1073741823);
    }

    //Propriedade: Se o número de entrada for par, então multiplyByTwo retornará um número par.
    @Property
    void multiplyByTwoMustBeEven(
            @ForAll("evenIntegers") int evenInteger){
        int result = MathFunctions.multiplyByTwo(evenInteger);
        assertTrue(result % 2 == 0, "Se a entrada for par, o resultado também deve ser par.");
    }

    //Provedor de números inteiros pares aleatórios
    @Provide
    Arbitrary<Integer> evenIntegers() {
        return Arbitraries.integers()
                .filter(integer -> integer % 2 == 0);
    }
}
